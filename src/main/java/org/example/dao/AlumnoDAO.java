package org.example.dao;

import jakarta.persistence.PersistenceException;
import org.example.entidades.Alumno;
import org.example.util.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class AlumnoDAO implements AlmunoDAOInterface{
    // Clase AlumnoDAO: Implementa la interfaz AlumnoDAOInterface utilizando Hibernate para interactuar con la base de datos
    @Override
    public List<Alumno> devolverTodos() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        List<Alumno> alumnos = session.createQuery("from Alumno",Alumno.class).list();

        session.close();
        return alumnos;
    }

    @Override
    public Alumno buscarporId(long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Alumno alumno = session.find(Alumno.class, id);

        session.close();
        return alumno;
    }

    @Override
    public Alumno create(Alumno alumno) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction(); // Sólo una vez
            session.save(alumno); // Guardar el alumno
            session.getTransaction().commit(); // Confirmamos la transacción

            return alumno; // Retorna el alumno creado
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback(); // En caso de error, revertimos
            return null; // Retorna null si hay un error
        } finally {
            session.close(); // Siempre cerramos la sesión
        }
    }

    @Override
    public Alumno actualizar(Alumno alumno) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            session.update(alumno);
            session.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
            session.getTransaction().rollback();
        }finally {
            session.close(); // Cerrar la sesión al final
        }

        return alumno;
    }

    @Override
    public Boolean eliminar(long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try{
            session.beginTransaction();
            Alumno alumno = this.buscarporId(id);
            if (alumno != null){
                session.delete(alumno);
            }else {
                return false;
            }
            session.getTransaction().commit();

        }catch (PersistenceException e){
            e.printStackTrace();
            session.getTransaction().rollback();
            return false;
        }finally {
            session.close();
        }
        return true;
    }
}
