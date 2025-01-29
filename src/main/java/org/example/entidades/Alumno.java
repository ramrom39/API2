package org.example.entidades;

import jakarta.persistence.*;

@Entity
@Table(name = "alumnos")
public class Alumno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String apellidos;
    private String email;
    private String diasDisponibles;

    // Constructor vacío
    public Alumno() {
    }

    // Constructor con todos los atributos
    public Alumno(int id, String name, String apellidos, String email, String diasDisponibles) {
        this.id = id;
        this.name = name;
        this.apellidos = apellidos;
        this.email = email;
        this.diasDisponibles = diasDisponibles;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDiasDisponibles() {
        return diasDisponibles;
    }

    public void setDiasDisponibles(String diasDisponibles) {
        this.diasDisponibles = diasDisponibles;
    }

    // Método toString para depuración
    @Override
    public String toString() {
        return "Alumno{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", email='" + email + '\'' +
                ", diasDisponibles='" + diasDisponibles + '\'' +
                '}';
    }
}
