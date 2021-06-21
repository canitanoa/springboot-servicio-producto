package ar.com.springboot.ms.productos.controllers;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import ar.com.springboot.ms.productos.models.entity.Producto;
import ar.com.springboot.ms.productos.models.service.ProductoService;

@RestController // Convierte a JSON los retornos
public class ProductoController {

	// Para obtener los valores de configuracion del ambiente
	@Autowired
	private Environment environment;

	// Para obtener valores de las properties
	@Value("${server.port}")
	private Integer port;

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
			return producto;
		}).collect(Collectors.toList());

	}

	@GetMapping("/ver/{id}")
//	@RequestMapping(value = "/listar/{id}", method = RequestMethod.GET)
	public Producto detalle(@PathVariable Long id) throws Exception {

		Producto producto = productoService.findById(id);
//		producto.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
		producto.setPort(port);

		// Prueba con Hystrix, genero un error random
//		Random rd = new Random();
//		if (rd.nextBoolean()) {
//			throw new Exception("No se puede cargar el producto");
//		}
		
		// Prueba con Hystrix, genero un timeout
//		try {
//			Thread.sleep(2000L);
//		} catch (InterruptedException e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
		

		return producto;
	}


}
