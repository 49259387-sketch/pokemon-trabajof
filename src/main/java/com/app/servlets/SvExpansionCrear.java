
package com.app.servlets;

import com.app.logica.ControladoraLogica;
import com.app.logica.Expansion;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@WebServlet(name = "SvExpansionCrear", urlPatterns = {"/SvExpansionCrear"})
public class SvExpansionCrear extends HttpServlet {

    private final ControladoraLogica control = new ControladoraLogica();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect(request.getContextPath() + "/SvExpansionListar");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String nombre = request.getParameter("nombre");
        String fechaStr = request.getParameter("fecha_lanzamiento");

        try {
            // Validamos que los campos no estén vacíos
            if (nombre == null || nombre.isBlank()) {
                throw new IllegalArgumentException("El nombre de la expansión es obligatorio.");
            }

            if (fechaStr == null || fechaStr.isBlank()) {
                throw new IllegalArgumentException("La fecha de lanzamiento es obligatoria.");
            }

            // Creamos el objeto Expansion
            Expansion expansion = new Expansion(nombre.trim(), fechaStr.trim());

            // Guardamos la expansión en la base de datos
            control.crearExpansion(expansion);

            // Volvemos al listado
            response.sendRedirect(request.getContextPath() + "/SvExpansionListar");

        } catch (Exception e) {
            String mensaje = e instanceof IllegalArgumentException
                    ? e.getMessage()
                    : "No se pudo crear la expansión.";

            redirigirConError(request, response, mensaje);
        }
    }

    private void redirigirConError(HttpServletRequest request,
            HttpServletResponse response, String mensaje) throws IOException {

        String msg = URLEncoder.encode(
                mensaje == null ? "Error al crear la expansión." : mensaje,
                StandardCharsets.UTF_8);

        response.sendRedirect(
                request.getContextPath() + "/SvExpansionListar?error=" + msg);
    }
}
