
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

@WebServlet("/SvCartaEditar")
public class SvCartaEditar extends HttpServlet {

    ControladoraLogica control = new ControladoraLogica();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            int id = Integer.parseInt(request.getParameter("id"));

            Carta carta = control.buscarCarta(id);

            if (carta == null) {
                response.sendRedirect(request.getContextPath() + "/SvCartaListar");
                return;
            }

            request.setAttribute("carta", carta);
            request.setAttribute("listaExpansiones", control.traerExpansiones());

            request.getRequestDispatcher("/WEB-INF/vistas/editarCarta.jsp")
                    .forward(request, response);

        } catch (NumberFormatException e) {
            response.sendRedirect(request.getContextPath() + "/SvCartaListar");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        try {
            int id = Integer.parseInt(request.getParameter("id"));
            String nombre = request.getParameter("nombre");
            String tipo = request.getParameter("tipo");
            String rareza = request.getParameter("rareza");
            int numero = Integer.parseInt(request.getParameter("numero"));
            int idExpansion = Integer.parseInt(request.getParameter("id_expansion"));

            if (nombre == null || nombre.isBlank()
                    || tipo == null || tipo.isBlank()
                    || rareza == null || rareza.isBlank()
                    || numero <= 0) {
                throw new IllegalArgumentException();
            }

            Carta carta = control.buscarCarta(id);
            Expansion expansion = control.buscarExpansion(idExpansion);

            if (carta == null || expansion == null) {
                response.sendRedirect(request.getContextPath() + "/SvCartaListar");
                return;
            }

            carta.setNombre(nombre.trim());
            carta.setTipo(tipo.trim());
            carta.setRareza(rareza.trim());
            carta.setNumero(numero);
            carta.setExpansion(expansion);

            control.editarCarta(carta);

            response.sendRedirect(request.getContextPath() + "/SvCartaListar");

        } catch (Exception e) {
            response.sendRedirect(request.getContextPath() + "/SvCartaListar?error=edicion");
        }
    }
}
