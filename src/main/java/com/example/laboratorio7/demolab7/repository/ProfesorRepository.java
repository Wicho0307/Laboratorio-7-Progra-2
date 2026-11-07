package com.example.laboratorio7.demolab7.repository;

import com.example.laboratorio7.demolab7.model.Profesor;

import java.util.List;
import java.util.Optional;

public interface ProfesorRepository {

    Profesor guardar(Profesor profesor);

    List<Profesor> buscarTodos();

    List<Profesor> buscarPorEspecialidad(String especialidad);

    Optional<Profesor> buscarPorId(Long id);
}
