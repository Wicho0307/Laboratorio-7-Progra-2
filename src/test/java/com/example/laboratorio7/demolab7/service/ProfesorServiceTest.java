package com.example.laboratorio7.demolab7.service;

import com.example.laboratorio7.demolab7.dto.CrearProfesorRequest;
import com.example.laboratorio7.demolab7.exception.ResourceNotFoundException;
import com.example.laboratorio7.demolab7.model.Profesor;
import com.example.laboratorio7.demolab7.repository.ProfesorRepository;
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
class ProfesorServiceTest {

    @Mock
    private ProfesorRepository profesorRepository;

    @InjectMocks
    private ProfesorService profesorService;

    @Test
    void debeCrearProfesor() {
        CrearProfesorRequest request = new CrearProfesorRequest(
                "Ana",
                "García",
                "ana@universidad.com",
                "Matemática",
                "ACTIVO"
        );

        Profesor profesorGuardado = new Profesor(
                1L,
                "Ana",
                "García",
                "ana@universidad.com",
                "Matemática",
                "ACTIVO"
        );

        when(profesorRepository.guardar(any(Profesor.class)))
                .thenReturn(profesorGuardado);

        Profesor resultado = profesorService.crear(request);

        assertEquals(profesorGuardado, resultado);
        verify(profesorRepository).guardar(any(Profesor.class));
    }

    @Test
    void debeObtenerProfesorExistente() {
        Profesor profesor = new Profesor(
                1L,
                "Ana",
                "García",
                "ana@universidad.com",
                "Matemática",
                "ACTIVO"
        );

        when(profesorRepository.buscarPorId(1L))
                .thenReturn(Optional.of(profesor));

        Profesor resultado = profesorService.obtenerPorId(1L);

        assertEquals(profesor, resultado);
        verify(profesorRepository).buscarPorId(1L);
    }

    @Test
    void debeLanzarExcepcionCuandoProfesorNoExiste() {
        when(profesorRepository.buscarPorId(99L))
                .thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(
                ResourceNotFoundException.class,
                () -> profesorService.obtenerPorId(99L)
        );

        assertEquals("Profesor con id 99 no existe", exception.getMessage());
        verify(profesorRepository).buscarPorId(99L);
    }
}
