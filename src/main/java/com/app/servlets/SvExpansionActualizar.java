
package com.app.servlets;

import com.app.logica.ControladoraLogica;
import com.app.logica.Expansion;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/SvExpansionActualizar")
public class SvExpansionActualizar extends HttpServlet {

    ControladoraLogica control = new ControladoraLogica();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            int id = Integer.parseInt(request.getParameter("id_expansion"));

            String nombre = request.getParameter("nombre");
            String fecha = request.getParameter("fecha_lanzamiento");

            Expansion expansion = control.buscarExpansion(id);

            if (expansion != null) {
                expansion.setNombre(nombre);
                expansion.setFecha_lanzamiento(fecha);

                control.editarExpansion(expansion);
            }

            response.sendRedirect(request.getContextPath() + "/SvExpansionListar");

        } catch (Exception e) {
            response.sendRedirect(request.getContextPath() + "/SvExpansionListar?error=actualizar");
        }
    }
}
