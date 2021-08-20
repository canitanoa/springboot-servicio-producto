package ar.com.springboot.ms.productos.models.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.springboot.ms.commons.models.entity.Producto;
import ar.com.springboot.ms.productos.models.dao.ProductoDao;
//import ar.com.springboot.ms.productos.models.entity.Producto;
import ar.com.springboot.ms.productos.models.service.ProductoService;

@Service //IoC para utilizar en el Controller
public class ProductoServiceImpl implements ProductoService{

	@Autowired //Para inyectar el bean
	private ProductoDao productoDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Producto> findAll() {
		return (List<Producto>) productoDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Producto findById(Long id) {
		return productoDao.findById(id).orElse(null); 
		//.orElse(null) -> si no encuentra el objeto retorna null evitando salir por NoSuchElementException
	}

	@Override
	@Transactional
	public Producto save(Producto producto) {
		return productoDao.save(producto);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		productoDao.deleteById(id);
	}

}
