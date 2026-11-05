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

@WebServlet("/SvCartaBuscar")
public class SvCartaBuscar extends HttpServlet {

    ControladoraLogica control = new ControladoraLogica();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String nombre = request.getParameter("nombre");

        try {
            List<Carta> lista = control.buscarCartasPorNombre(nombre);

            request.setAttribute("listaCartas", lista);

            request.getRequestDispatcher("/WEB-INF/vistas/listadoCartas.jsp")
                    .forward(request, response);

        } catch (Exception e) {
            response.sendRedirect(request.getContextPath() + "/SvCartaListar?error=busqueda");
        }
    }
}