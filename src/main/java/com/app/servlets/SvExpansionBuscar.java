
package com.app.servlets;

import com.app.logica.ControladoraLogica;
import com.app.logica.Expansion;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/SvExpansionBuscar")
public class SvExpansionBuscar extends HttpServlet {

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

            List<Expansion> lista;

            if (nombre.isEmpty()) {
                lista = control.traerExpansiones();
            } else {
                lista = control.buscarExpansionesPorNombre(nombre);
            }

            request.setAttribute("listaExpansiones", lista);
            request.setAttribute("nombreBuscado", nombre);

            request.getRequestDispatcher(
            	    "/WEB-INF/vistas/administrarExpansiones.jsp"
            	).forward(request, response);

        } catch (Exception e) {

            throw new ServletException(
                "No se pudieron buscar las expansiones.", e
            );
        }
    }
}
