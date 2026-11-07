package com.example.laboratorio7.demolab7.controller;

import com.example.laboratorio7.demolab7.dto.CrearProveedorRequest;
import com.example.laboratorio7.demolab7.model.Proveedor;
import com.example.laboratorio7.demolab7.service.ProveedorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/proveedores")
public class ProveedorController {

    private final ProveedorService proveedorService;

    public ProveedorController(ProveedorService proveedorService) {
        this.proveedorService = proveedorService;
    }

    @PostMapping
    public ResponseEntity<Proveedor> crear(
            @Valid @RequestBody CrearProveedorRequest request
    ) {
        Proveedor proveedorCreado = proveedorService.crear(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(proveedorCreado);
    }

    @GetMapping
    public List<Proveedor> obtenerTodos(
            @RequestParam(required = false) String categoria
    ) {
        return proveedorService.obtenerTodos(categoria);
    }

    @GetMapping("/{id}")
    public Proveedor obtenerPorId(@PathVariable Long id) {
        return proveedorService.obtenerPorId(id);
    }
}
