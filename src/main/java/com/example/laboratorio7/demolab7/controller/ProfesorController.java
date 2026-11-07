package com.example.laboratorio7.demolab7.controller;

import com.example.laboratorio7.demolab7.dto.CrearProfesorRequest;
import com.example.laboratorio7.demolab7.model.Profesor;
import com.example.laboratorio7.demolab7.service.ProfesorService;
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
@RequestMapping("/api/profesores")
public class ProfesorController {

    private final ProfesorService profesorService;

    public ProfesorController(ProfesorService profesorService) {
        this.profesorService = profesorService;
    }

    @PostMapping
    public ResponseEntity<Profesor> crear(
            @Valid @RequestBody CrearProfesorRequest request
    ) {
        Profesor profesorCreado = profesorService.crear(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(profesorCreado);
    }

    @GetMapping
    public List<Profesor> obtenerTodos(
            @RequestParam(required = false) String especialidad
    ) {
        return profesorService.obtenerTodos(especialidad);
    }

    @GetMapping("/{id}")
    public Profesor obtenerPorId(@PathVariable Long id) {
        return profesorService.obtenerPorId(id);
    }
}
