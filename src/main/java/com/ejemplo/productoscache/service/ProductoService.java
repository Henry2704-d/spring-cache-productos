
package com.ejemplo.productoscache.service;

import com.ejemplo.productoscache.model.Producto;
import com.ejemplo.productoscache.repository.ProductoRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {

    private final ProductoRepository repository;

    public ProductoService(ProductoRepository repository) {
        this.repository = repository;
    }

    // Consulta directamente SQL Server
    public List<Producto> obtenerSinCache() {
        return repository.findAll();
    }

    // Reutiliza los resultados despues de la primera consulta
    @Cacheable("productos")
    public List<Producto> obtenerConCache() {
        return repository.findAll();
    }
}
