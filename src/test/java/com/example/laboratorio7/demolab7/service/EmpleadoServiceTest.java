package com.example.laboratorio7.demolab7.service;

import com.example.laboratorio7.demolab7.dto.CrearEmpleadoRequest;
import com.example.laboratorio7.demolab7.exception.ResourceNotFoundException;
import com.example.laboratorio7.demolab7.model.Empleado;
import com.example.laboratorio7.demolab7.repository.EmpleadoRepository;
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
class EmpleadoServiceTest {

    @Mock
    private EmpleadoRepository empleadoRepository;

    @InjectMocks
    private EmpleadoService empleadoService;

    @Test
    void debeCrearEmpleado() {
        CrearEmpleadoRequest request = new CrearEmpleadoRequest(
                "María",
                "Pérez",
                "maria@empresa.com",
                "Desarrollo Backend",
                "ACTIVO"
        );

        Empleado empleadoGuardado = new Empleado(
                1L,
                "María",
                "Pérez",
                "maria@empresa.com",
                "Desarrollo Backend",
                "ACTIVO"
        );

        when(empleadoRepository.guardar(any(Empleado.class)))
                .thenReturn(empleadoGuardado);

        Empleado resultado = empleadoService.crear(request);

        assertEquals(empleadoGuardado, resultado);
        verify(empleadoRepository).guardar(any(Empleado.class));
    }

    @Test
    void debeObtenerEmpleadoExistente() {
        Empleado empleado = new Empleado(
                1L,
                "María",
                "Pérez",
                "maria@empresa.com",
                "Desarrollo Backend",
                "ACTIVO"
        );

        when(empleadoRepository.buscarPorId(1L))
                .thenReturn(Optional.of(empleado));

        Empleado resultado = empleadoService.obtenerPorId(1L);

        assertEquals(empleado, resultado);
        verify(empleadoRepository).buscarPorId(1L);
    }

    @Test
    void debeLanzarExcepcionCuandoEmpleadoNoExiste() {
        when(empleadoRepository.buscarPorId(99L))
                .thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(
                ResourceNotFoundException.class,
                () -> empleadoService.obtenerPorId(99L)
        );

        assertEquals("Empleado con id 99 no existe", exception.getMessage());
        verify(empleadoRepository).buscarPorId(99L);
    }
}
