
package com.app.servlets;

import com.app.logica.ControladoraLogica;
import com.app.logica.Expansion;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/SvExpansionEditar")
public class SvExpansionEditar extends HttpServlet {

    ControladoraLogica control = new ControladoraLogica();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            int id = Integer.parseInt(request.getParameter("id"));

            Expansion expansion = control.buscarExpansion(id);

            request.setAttribute("expansion", expansion);

            request.getRequestDispatcher("/WEB-INF/vistas/editarExpansion.jsp")
                   .forward(request, response);

        } catch (Exception e) {
            response.sendRedirect(request.getContextPath() + "/SvExpansionListar?error=editar");
        }
    }
}