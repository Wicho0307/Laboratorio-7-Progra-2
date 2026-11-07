package com.example.laboratorio7.demolab7.service;

import com.example.laboratorio7.demolab7.dto.CrearProveedorRequest;
import com.example.laboratorio7.demolab7.exception.ResourceNotFoundException;
import com.example.laboratorio7.demolab7.model.Proveedor;
import com.example.laboratorio7.demolab7.repository.ProveedorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

@Service
public class ProveedorService {

    private final ProveedorRepository proveedorRepository;

    public ProveedorService(ProveedorRepository proveedorRepository) {
        this.proveedorRepository = proveedorRepository;
    }

    public Proveedor crear(CrearProveedorRequest request) {
        Proveedor proveedor = new Proveedor(
                null,
                request.nombre().trim(),
                request.apellido().trim(),
                request.correo().trim().toLowerCase(Locale.ROOT),
                request.categoria().trim(),
                request.estado().trim()
        );

        return proveedorRepository.guardar(proveedor);
    }

    public List<Proveedor> obtenerTodos(String categoria) {
        if (categoria == null || categoria.isBlank()) {
            return proveedorRepository.buscarTodos();
        }

        return proveedorRepository.buscarPorCategoria(categoria.trim());
    }

    public Proveedor obtenerPorId(Long id) {
        return proveedorRepository.buscarPorId(id)
                .orElseThrow(() -> new ResourceNotFoundException("Proveedor", id));
    }
}
