
package com.app.persistencia;

import com.app.logica.Expansion;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;

public class ExpansionJpaController {

    // Administrador de entidades para conectarnos con la base de datos.
    private EntityManagerFactory emf = null;

    // Indicamos qué unidad de persistencia vamos a utilizar.
    public ExpansionJpaController() {
        emf = Persistence.createEntityManagerFactory("ejemplojpa");
    }

    // Devuelve un EntityManager para realizar operaciones en la base.
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    // BUSCAR UNO: busca una expansión por su id.
    public Expansion buscarUno(int id) {
        EntityManager em = getEntityManager();

        try {
            return em.find(Expansion.class, id);
        } finally {
            em.close();
        }
    }

    // BUSCAR TODOS: devuelve todas las expansiones.
    public List<Expansion> buscarTodos() {
        EntityManager em = getEntityManager();

        try {
            return em.createQuery(
                "SELECT e FROM Expansion e", Expansion.class
            ).getResultList();
        } finally {
            em.close();
        }
    }

    // CREAR: guarda una expansión en la base de datos.
    public void crear(Expansion expansion) {
        EntityManager em = null;

        try {
            em = getEntityManager();
            em.getTransaction().begin();

            em.persist(expansion);

            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    // EDITAR: modifica una expansión existente.
    public void editar(Expansion expansion) {
        EntityManager em = null;

        try {
            em = getEntityManager();
            em.getTransaction().begin();

            em.merge(expansion);

            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    // ELIMINAR: elimina una expansión por su id.
    public void eliminar(int id) {
        EntityManager em = null;

        try {
            em = getEntityManager();
            em.getTransaction().begin();

            Expansion expansion = em.find(Expansion.class, id);

            if (expansion != null) {
                em.remove(expansion);
            }

            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    

    // VERIFICAR: comprueba si una expansión tiene cartas asociadas.
    public boolean tieneCartas(int idExpansion) {
        EntityManager em = getEntityManager();

        try {
            Long cantidad = em.createQuery(
                "SELECT COUNT(c) FROM Carta c WHERE c.expansion.id_expansion = :id",
                Long.class
            ).setParameter("id", idExpansion)
             .getSingleResult();

            return cantidad > 0;

        } finally {
            em.close();
        }
    }

}
