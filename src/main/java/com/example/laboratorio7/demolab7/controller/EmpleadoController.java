package com.example.laboratorio7.demolab7.controller;

import com.example.laboratorio7.demolab7.dto.CrearEmpleadoRequest;
import com.example.laboratorio7.demolab7.model.Empleado;
import com.example.laboratorio7.demolab7.service.EmpleadoService;
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
@RequestMapping("/api/empleados")
public class EmpleadoController {

    private final EmpleadoService empleadoService;

    public EmpleadoController(EmpleadoService empleadoService) {
        this.empleadoService = empleadoService;
    }

    @PostMapping
    public ResponseEntity<Empleado> crear(
            @Valid @RequestBody CrearEmpleadoRequest request
    ) {
        Empleado empleadoCreado = empleadoService.crear(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(empleadoCreado);
    }

    @GetMapping
    public List<Empleado> obtenerTodos(
            @RequestParam(required = false) String especialidad
    ) {
        return empleadoService.obtenerTodos(especialidad);
    }

    @GetMapping("/{id}")
    public Empleado obtenerPorId(@PathVariable Long id) {
        return empleadoService.obtenerPorId(id);
    }
}
