package org.example;

import org.example.dao.AlumnoDAO;
import org.example.servicios.AlumnoAPIREST;

public class Main {
    public static void main(String[] args) {
        AlumnoAPIREST api=new AlumnoAPIREST(new AlumnoDAO());
    }
}