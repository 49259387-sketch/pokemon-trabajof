
package com.app.servlets;

import com.app.logica.ControladoraLogica;
import com.app.logica.Carta;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/SvCartaAdministrar")
public class SvCartaAdministrar extends HttpServlet {

    private final ControladoraLogica control = new ControladoraLogica();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String nombre = request.getParameter("nombre");

        if (nombre == null) {
            nombre = "";
        }

        nombre = nombre.trim();

        try {

            List<Carta> lista;

            if (nombre.isEmpty()) {
                lista = control.traerCartas();
            } else {
                lista = control.buscarCartasPorNombre(nombre);
            }

            request.setAttribute("listaCartas", lista);
            request.setAttribute("nombreBuscado", nombre);

            request.getRequestDispatcher(
                "/WEB-INF/vistas/administrarCartas.jsp"
            ).forward(request, response);

        } catch (Exception e) {

            throw new ServletException(
                "No se pudieron buscar las cartas.", e
            );
        }
    }
}
