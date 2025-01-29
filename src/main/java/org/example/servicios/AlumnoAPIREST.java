package org.example.servicios;

import com.google.gson.Gson;
import org.example.dao.AlumnoDAO;
import org.example.entidades.Alumno;
import spark.Spark;

import java.util.List;

// SPARK : microframework PARA CONECTAR LA API CON EL CLIENTE

public class AlumnoAPIREST {
    private AlumnoDAO alumnoDAO;
    private Gson gson = new Gson();

    public AlumnoAPIREST(AlumnoDAO implementacion) {
        Spark.port(Integer.parseInt(System.getenv("PORT")));

        alumnoDAO = implementacion;
        //endpoint para obtener todos los muebles

        Spark.get("/alumnos", (request, response) -> {
            List<Alumno> alumnoList = alumnoDAO.devolverTodos();
            response.type("application/json");
            return gson.toJson(alumnoList);
        });

        //endpoint para obtener un mueble por id

        Spark.get("/alumnos/id/:id", (request, response) -> {

            int id = Integer.parseInt(request.params("id"));
            Alumno alumno = alumnoDAO.buscarporId(id);
            response.type("application/json");
            if (alumno != null) {
                return gson.toJson(alumno);
            } else {
                response.status(404);
                return "mueble no encontrado";
            }
        });


        Spark.post("/alumnos/crear", (request, response) -> {
            response.type("application/json");

            try {

                Alumno nuevoAlumno = gson.fromJson(request.body(), Alumno.class);


                Alumno creado = alumnoDAO.create(nuevoAlumno);

                if (creado != null) {
                    response.status(201);
                    return gson.toJson(creado);
                } else {
                    response.status(500);
                    return "{\"error\":\"No se pudo crear el alumno\"}";
                }
            } catch (Exception e) {
                response.status(400);
                return "{\"error\":\"Datos inválidos: " + e.getMessage() + "\"}";
            }
        });
        Spark.put("/alumnos/update/id/:id", (request, response) -> {
            try {
                // Obtener el ID del alumno desde los parámetros de la URL
                int id = Integer.parseInt(request.params("id"));

                // Obtener los datos del alumno desde el cuerpo de la solicitud
                Alumno cambiarAlumno = gson.fromJson(request.body(), Alumno.class);

                // Establecer el ID del alumno para la actualización
                cambiarAlumno.setId(id);

                // Llamar al DAO para actualizar el alumno
                Alumno alumnocambiado = alumnoDAO.actualizar(cambiarAlumno);

                if (alumnocambiado != null) {
                    response.status(200); // Actualización exitosa
                    return gson.toJson(alumnocambiado);
                } else {
                    response.status(404); // No encontrado
                    return "{\"error\":\"Alumno no encontrado\"}";
                }
            } catch (Exception e) {
                response.status(400); // Solicitud incorrecta
                return "{\"error\":\"Datos inválidos: " + e.getMessage() + "\"}";
            }
        });
;

        Spark.delete("/alumnos/delete/id/:id", (request, response) -> {
            int id = Integer.parseInt(request.params("id"));
            Alumno alumnoacambiar = alumnoDAO.buscarporId(id);
            if (alumnoacambiar != null) {
                alumnoDAO.eliminar(alumnoacambiar.getId());
                return "{\"message\":\"Alumno actualizado exitosamente\"}";
            }else {
                response.status(404);
                return  "{\"error\":\"Alumno no encontrado\"}";
            }
        });
    }
}
