package ar.com.springboot.ms.productos.models.service;

import java.util.List;

import ar.com.springboot.ms.productos.models.entity.Producto;

public interface ProductoService {
	
	public List<Producto> findAll();
	public Producto findById(Long id);

}
