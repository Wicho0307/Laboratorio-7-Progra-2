package com.example.laboratorio7.demolab7.service;

import com.example.laboratorio7.demolab7.dto.CrearProveedorRequest;
import com.example.laboratorio7.demolab7.exception.ResourceNotFoundException;
import com.example.laboratorio7.demolab7.model.Proveedor;
import com.example.laboratorio7.demolab7.repository.ProveedorRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProveedorServiceTest {

    @Mock
    private ProveedorRepository proveedorRepository;

    @InjectMocks
    private ProveedorService proveedorService;

    @Test
    void debeCrearProveedor() {
        CrearProveedorRequest request = new CrearProveedorRequest(
                "Carlos",
                "López",
                "carlos@proveedor.com",
                "Tecnología",
                "ACTIVO"
        );

        Proveedor proveedorGuardado = new Proveedor(
                1L,
                "Carlos",
                "López",
                "carlos@proveedor.com",
                "Tecnología",
                "ACTIVO"
        );

        when(proveedorRepository.guardar(any(Proveedor.class)))
                .thenReturn(proveedorGuardado);

        Proveedor resultado = proveedorService.crear(request);

        assertEquals(proveedorGuardado, resultado);
        verify(proveedorRepository).guardar(any(Proveedor.class));
    }

    @Test
    void debeObtenerProveedorExistente() {
        Proveedor proveedor = new Proveedor(
                1L,
                "Carlos",
                "López",
                "carlos@proveedor.com",
                "Tecnología",
                "ACTIVO"
        );

        when(proveedorRepository.buscarPorId(1L))
                .thenReturn(Optional.of(proveedor));

        Proveedor resultado = proveedorService.obtenerPorId(1L);

        assertEquals(proveedor, resultado);
        verify(proveedorRepository).buscarPorId(1L);
    }

    @Test
    void debeLanzarExcepcionCuandoProveedorNoExiste() {
        when(proveedorRepository.buscarPorId(99L))
                .thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(
                ResourceNotFoundException.class,
                () -> proveedorService.obtenerPorId(99L)
        );

        assertEquals("Proveedor con id 99 no existe", exception.getMessage());
        verify(proveedorRepository).buscarPorId(99L);
    }
}
