package ar.com.springboot.ms.productos.controllers;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ar.com.springboot.ms.commons.models.entity.Producto;
//import ar.com.springboot.ms.productos.models.entity.Producto;
import ar.com.springboot.ms.productos.models.service.ProductoService;

@RestController // Convierte a JSON los retornos
public class ProductoController {

	// Para obtener los valores de configuracion del ambiente
	@Autowired
	private Environment environment;

	// Para obtener valores de las properties
	@Value("${server.port}")
	private Integer port;
	
	// Para obtener valores de las properties
	@Value("${eureka.instance.instance-id}")
	private String instancia;

	@Autowired
	private ProductoService productoService;

	@GetMapping("/listar")
//	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	public List<Producto> listar() {

//		//Retorno sin modificar
//		return productoService.findAll();

//		//Retorno alterando el objeto
//		//int port = Integer.parseInt(environment.getProperty("local.server.port"));
//		producto.setPort(port);
//		List<Producto> prod = productoService.findAll();
//
//		List<Producto> produc = new ArrayList<Producto>();
//		for(Producto produ: prod) {
//			produ.setPort(port);
//			produc.add(produ);
//		}
//		
//		return produc;

		// Retorno alterando el objeto por Streams
		return productoService.findAll().stream().map(producto -> {
//			producto.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
			producto.setPort(port);
			producto.setInstancia(instancia);
			return producto;
		}).collect(Collectors.toList());

	}

	@GetMapping("/ver/{id}")
//	@RequestMapping(value = "/listar/{id}", method = RequestMethod.GET)
	public Producto detalle(@PathVariable Long id) throws Exception {

		Producto producto = productoService.findById(id);
//		producto.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
		producto.setPort(port);
		producto.setInstancia(instancia);

//		/* Pruebas con Hystrix */
//		// Genero un error random
//		Random rd = new Random();
//		if (rd.nextBoolean()) {
//			throw new Exception("No se puede cargar el producto");
//		}
//		// Genero un timeout
//		try {
//			Thread.sleep(2000L);
//		} catch (InterruptedException e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
		
		/* Pruebas con Resilience4J */
		// Tira error si el id = 9
		if(id.equals(9L)) {
			throw new IllegalStateException("Producto no encontrado.");
		}
		// Tarda 5 seg si el id = 7
		if(id.equals(7L)) {
			TimeUnit.SECONDS.sleep(5L);
		}
		

		return producto;
	}

	@PostMapping("/crear")
	@ResponseStatus(HttpStatus.CREATED)
	public Producto crear(@RequestBody Producto producto) {
		return productoService.save(producto);
	}
	
	@PutMapping("/editar/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Producto editar(@RequestBody Producto producto, @PathVariable Long id) {
		
		Producto productoDb = productoService.findById(id);
		
		productoDb.setNombre(producto.getNombre());
		productoDb.setPrecio(producto.getPrecio());
		
		return productoService.save(productoDb);
	}
	
	@DeleteMapping("/eliminar/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void eliminar(@PathVariable Long id) {
		productoService.deleteById(id);
	}

}
