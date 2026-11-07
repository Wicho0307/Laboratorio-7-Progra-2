package com.example.laboratorio7.demolab7.repository;

import com.example.laboratorio7.demolab7.model.Proveedor;

import java.util.List;
import java.util.Optional;

public interface ProveedorRepository {

    Proveedor guardar(Proveedor proveedor);

    List<Proveedor> buscarTodos();

    List<Proveedor> buscarPorCategoria(String categoria);

    Optional<Proveedor> buscarPorId(Long id);
}
