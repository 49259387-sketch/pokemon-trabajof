
package com.app.servlets;

import com.app.logica.ControladoraLogica;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/SvExpansionEliminar")
public class SvExpansionEliminar extends HttpServlet {

    ControladoraLogica control = new ControladoraLogica();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            int id = Integer.parseInt(request.getParameter("id"));

            if (control.expansionTieneCartas(id)) {
                response.sendRedirect(request.getContextPath()
                        + "/SvExpansionListar?error=tieneCartas");
                return;
            }

            control.eliminarExpansion(id);

            response.sendRedirect(request.getContextPath() + "/SvExpansionListar");

        } catch (Exception e) {
            response.sendRedirect(request.getContextPath()
                    + "/SvExpansionListar?error=eliminar");
        }
    }
}
