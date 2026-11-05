
package com.app.persistencia;

import com.app.logica.Carta;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;

public class CartaJpaController {

    // Administrador de entidades para conectarnos con la base de datos.
    private EntityManagerFactory emf = null;

    // Indicamos qué unidad de persistencia vamos a utilizar.
    public CartaJpaController() {
        emf = Persistence.createEntityManagerFactory("ejemplojpa");
    }

    // Devuelve un EntityManager para realizar operaciones en la base.
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    // BUSCAR UNO: busca una carta por su id.
    public Carta buscarUno(int id) {
        EntityManager em = getEntityManager();

        try {
            return em.find(Carta.class, id);
        } finally {
            em.close();
        }
    }

    // BUSCAR TODOS: devuelve todas las cartas.
    public List<Carta> buscarTodos() {
        EntityManager em = getEntityManager();

        try {
            return em.createQuery(
                "SELECT c FROM Carta c", Carta.class
            ).getResultList();
        } finally {
            em.close();
        }
    }

    // CREAR: guarda una carta en la base de datos.
    public void crear(Carta carta) {
        EntityManager em = null;

        try {
            em = getEntityManager();
            em.getTransaction().begin();

            em.persist(carta);

            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    // EDITAR: modifica una carta existente.
    public void editar(Carta carta) {
        EntityManager em = null;

        try {
            em = getEntityManager();
            em.getTransaction().begin();

            em.merge(carta);

            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    // ELIMINAR: elimina una carta por su id.
    public void eliminar(int id) {
        EntityManager em = null;

        try {
            em = getEntityManager();
            em.getTransaction().begin();

            Carta carta = em.find(Carta.class, id);

            if (carta != null) {
                em.remove(carta);
            }

            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    public List<Carta> buscarPorNombre(String nombre) {

        EntityManager em = getEntityManager();

        try {
            return em.createQuery(
                "SELECT c FROM Carta c WHERE LOWER(c.nombre) LIKE LOWER(:nombre)",
                Carta.class
            )
            .setParameter("nombre", "%" + nombre + "%")
            .getResultList();

        } finally {
            em.close();
        }
    }
}


