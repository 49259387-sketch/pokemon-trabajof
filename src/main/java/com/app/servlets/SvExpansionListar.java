
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

@WebServlet(name = "SvExpansionListar", urlPatterns = {"/SvExpansionListar"})
public class SvExpansionListar extends HttpServlet {

    private final ControladoraLogica control = new ControladoraLogica();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            // Obtenemos todas las expansiones
            List<Expansion> listaExpansiones = control.traerExpansiones();

            // Enviamos la lista a la página JSP
            request.setAttribute("listaExpansiones", listaExpansiones);

            // Mostramos la página
            request.getRequestDispatcher("/WEB-INF/vistas/expansiones.jsp")
                    .forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();

            response.sendError(
                HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                "No se pudieron cargar las expansiones."
            );
        }
    }
}