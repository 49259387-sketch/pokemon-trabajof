
package com.app.logica;

import com.app.persistencia.CartaJpaController;
import com.app.persistencia.ExpansionJpaController;

import java.util.List;

public class ControladoraLogica {

    // Controladores que trabajan con la base de datos.
    private CartaJpaController cartaJpa = new CartaJpaController();
    private ExpansionJpaController expansionJpa = new ExpansionJpaController();

    // =========================
    // MÉTODOS PARA EXPANSIONES
    // =========================

    public void crearExpansion(Expansion expansion) {
        expansionJpa.crear(expansion);
    }

    public List<Expansion> traerExpansiones() {
        return expansionJpa.buscarTodos();
    }

    public Expansion buscarExpansion(int id) {
        return expansionJpa.buscarUno(id);
    }

    public void editarExpansion(Expansion expansion) {
        expansionJpa.editar(expansion);
    }

    public void eliminarExpansion(int id) {
        expansionJpa.eliminar(id);
    }

    // =========================
    // MÉTODOS PARA CARTAS
    // =========================

    public void crearCarta(Carta carta) {
        cartaJpa.crear(carta);
    }

    public List<Carta> traerCartas() {
        return cartaJpa.buscarTodos();
    }
    
    public List<Carta> buscarCartasPorNombre(String nombre) {
        return cartaJpa.buscarPorNombre(nombre);
    }

    public Carta buscarCarta(int id) {
        return cartaJpa.buscarUno(id);
    }

    public void editarCarta(Carta carta) {
        cartaJpa.editar(carta);
    }

    public void eliminarCarta(int id) {
        cartaJpa.eliminar(id);
    }
    
    public boolean expansionTieneCartas(int id) {
        return expansionJpa.tieneCartas(id);
    }
}
