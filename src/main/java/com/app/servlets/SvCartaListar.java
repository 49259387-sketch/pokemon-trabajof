
package com.app.servlets;

import com.app.logica.Carta;
import com.app.logica.ControladoraLogica;
import com.app.logica.Expansion;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "SvCartaListar", urlPatterns = {"/SvCartaListar"})
public class SvCartaListar extends HttpServlet {

    private final ControladoraLogica control = new ControladoraLogica();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            List<Carta> listaCartas = control.traerCartas();
            List<Expansion> listaExpansiones = control.traerExpansiones();

            request.setAttribute("listaCartas", listaCartas);
            request.setAttribute("listaExpansiones", listaExpansiones);

            request.getRequestDispatcher("/WEB-INF/vistas/listadoCartas.jsp")
                    .forward(request, response);

        } catch (Exception e) {
            response.sendError(
                    HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                    "No se pudieron cargar las cartas.");
        }
    }
}
