package com.example.laboratorio7.demolab7.repository;

import com.example.laboratorio7.demolab7.model.Proveedor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class InMemoryProveedorRepository implements ProveedorRepository {

    private final List<Proveedor> proveedores = new CopyOnWriteArrayList<>();
    private final AtomicLong secuenciaIds = new AtomicLong();

    @Override
    public Proveedor guardar(Proveedor proveedor) {
        Proveedor proveedorGuardado = new Proveedor(
                secuenciaIds.incrementAndGet(),
                proveedor.nombre(),
                proveedor.apellido(),
                proveedor.correo(),
                proveedor.categoria(),
                proveedor.estado()
        );

        proveedores.add(proveedorGuardado);
        return proveedorGuardado;
    }

    @Override
    public List<Proveedor> buscarTodos() {
        return List.copyOf(proveedores);
    }

    @Override
    public List<Proveedor> buscarPorCategoria(String categoria) {
        return proveedores.stream()
                .filter(proveedor -> proveedor.categoria().equalsIgnoreCase(categoria))
                .toList();
    }

    @Override
    public Optional<Proveedor> buscarPorId(Long id) {
        return proveedores.stream()
                .filter(proveedor -> proveedor.id().equals(id))
                .findFirst();
    }
}
