package ar.com.springboot.ms.productos.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ar.com.springboot.ms.productos.models.entity.Producto;
import ar.com.springboot.ms.productos.models.service.ProductoService;

@RestController //Convierte a JSON los retornos
public class ProductoController {
	
	@Autowired
	private ProductoService productoService;
	
	@GetMapping("/listar")
//	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	public List<Producto> listar(){
		return productoService.findAll();
	}
	
	@GetMapping("/ver/{id}")
//	@RequestMapping(value = "/listar/{id}", method = RequestMethod.GET)
	public Producto detalle(@PathVariable Long id){
		return productoService.findById(id);
	}
	

}
