
package com.ejemplo.productoscache.controller;

import com.ejemplo.productoscache.model.Producto;
import com.ejemplo.productoscache.repository.ProductoRepository;
import com.ejemplo.productoscache.service.ProductoService;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = {
        "http://localhost:5500",
        "http://127.0.0.1:5500"
})
@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private final ProductoRepository repository;
    private final ProductoService service;

    public ProductoController(
            ProductoRepository repository,
            ProductoService service) {

        this.repository = repository;
        this.service = service;
    }

    @GetMapping("/cantidad")
    public long cantidad() {
        return repository.count();
    }

    @GetMapping("/todos")
    public List<Producto> todos() {
        return repository.findAll();
    }

    @GetMapping("/sin-cache")
    public Map<String, Object> sinCache() {

        long inicio = System.nanoTime();

        List<Producto> productos =
                service.obtenerSinCache();

        double tiempoMs =
                (System.nanoTime() - inicio) / 1_000_000.0;

        return Map.of(
                "tipo", "Sin cache",
                "cantidad", productos.size(),
                "tiempoMs", tiempoMs
        );
    }

    @GetMapping("/con-cache")
    public Map<String, Object> conCache() {

        long inicio = System.nanoTime();

        List<Producto> productos =
                service.obtenerConCache();

        double tiempoMs =
                (System.nanoTime() - inicio) / 1_000_000.0;

        return Map.of(
                "tipo", "Con cache",
                "cantidad", productos.size(),
                "tiempoMs", tiempoMs
        );
    }
}
