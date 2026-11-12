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
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@WebServlet(name = "SvCartaCrear", urlPatterns = {"/SvCartaCrear"})
public class SvCartaCrear extends HttpServlet {

    private final ControladoraLogica control = new ControladoraLogica();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute("listaExpansiones", control.traerExpansiones());

        request.getRequestDispatcher("/WEB-INF/vistas/cartas.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String nombre = request.getParameter("nombre");
        String tipo = request.getParameter("tipo");
        String rareza = request.getParameter("rareza");
        String numeroStr = request.getParameter("numero");
        String expansionStr = request.getParameter("id_expansion");

        try {
            if (nombre == null || nombre.isBlank()
                    || tipo == null || tipo.isBlank()
                    || rareza == null || rareza.isBlank()) {
                throw new IllegalArgumentException(
                        "Completá todos los campos de la carta.");
            }

            int numero = Integer.parseInt(numeroStr);
            int idExpansion = Integer.parseInt(expansionStr);

            if (numero <= 0) {
                throw new IllegalArgumentException(
                        "El número de la carta debe ser mayor que cero.");
            }

            Expansion expansion = control.buscarExpansion(idExpansion);

            if (expansion == null) {
                throw new IllegalArgumentException(
                        "Seleccioná una expansión válida.");
            }

            Carta carta = new Carta(
                    nombre.trim(),
                    tipo.trim(),
                    rareza.trim(),
                    numero,
                    expansion
            );

            control.crearCarta(carta);

            response.sendRedirect(
                    request.getContextPath() + "/SvCartaListar");

        } catch (NumberFormatException e) {
            redirigirConError(request, response,
                    "El número de la carta o la expansión no es válido.");
        } catch (IllegalArgumentException e) {
            redirigirConError(request, response, e.getMessage());
        } catch (Exception e) {
            redirigirConError(request, response,
                    "No se pudo registrar la carta.");
        }
    }

    private void redirigirConError(HttpServletRequest request,
            HttpServletResponse response, String mensaje) throws IOException {

        String msg = URLEncoder.encode(
                mensaje == null ? "Error al registrar la carta." : mensaje,
                StandardCharsets.UTF_8);

        response.sendRedirect(
                request.getContextPath() + "/SvCartaListar?error=" + msg);
    }
}