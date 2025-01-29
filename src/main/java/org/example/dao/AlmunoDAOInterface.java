package org.example.dao;

import org.example.entidades.Alumno;

import java.util.List;

public interface AlmunoDAOInterface {
    // Interfaz AlumnoDAOInterface: Define los métodos que deben implementarse para manejar los datos de los alumnos
    List<Alumno> devolverTodos();

    Alumno buscarporId(long id);

    Alumno create (Alumno alumno);

    Alumno actualizar(Alumno alumno);

    Boolean eliminar(long id);
}
