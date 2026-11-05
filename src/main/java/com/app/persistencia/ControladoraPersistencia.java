
package com.app.persistencia;

import com.app.logica.Carta;
import com.app.logica.Expansion;
import java.util.List;

public class ControladoraPersistencia {

    // Creamos una instancia del controlador de Expansion
    ExpansionJpaController expansionJpa = new ExpansionJpaController();

    // Creamos una instancia del controlador de Carta
    CartaJpaController cartaJpa = new CartaJpaController();

    // CREAR UNA EXPANSION
    public void crearExpansion(Expansion expansion) {
        expansionJpa.crear(expansion);
    }

    // BUSCAR UNA EXPANSION
    public Expansion buscarExpansion(int id) {
        return expansionJpa.buscarUno(id);
    }

    // BUSCAR TODAS LAS EXPANSIONES
    public List<Expansion> buscarTodasLasExpansiones() {
        return expansionJpa.buscarTodos();
    }

    // EDITAR UNA EXPANSION
    public void editarExpansion(Expansion expansion) {
        expansionJpa.editar(expansion);
    }

    // ELIMINAR UNA EXPANSION
    public void eliminarExpansion(int id) {
        expansionJpa.eliminar(id);
    }

    // CREAR UNA CARTA
    public void crearCarta(Carta carta) {
        cartaJpa.crear(carta);
    }

    // BUSCAR UNA CARTA
    public Carta buscarCarta(int id) {
        return cartaJpa.buscarUno(id);
    }

    // BUSCAR TODAS LAS CARTAS
    public List<Carta> buscarTodasLasCartas() {
        return cartaJpa.buscarTodos();
    }

    // EDITAR UNA CARTA
    public void editarCarta(Carta carta) {
        cartaJpa.editar(carta);
    }

    // ELIMINAR UNA CARTA
    public void eliminarCarta(int id) {
        cartaJpa.eliminar(id);
    }
}
