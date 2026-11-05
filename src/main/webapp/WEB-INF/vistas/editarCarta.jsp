
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.app.logica.Carta"%>
<%@page import="com.app.logica.Expansion"%>
<%@page import="java.util.List"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Modificar carta | PokéCards TCG</title>
    <link rel="stylesheet"
          href="<%= request.getContextPath() %>/css/estilos.css">
</head>
<body>

<div class="contenedor">

    <h1>PokéCards TCG</h1>
    <h2>Modificar carta</h2>

    <%
        Carta carta = (Carta) request.getAttribute("carta");
        List<Expansion> listaExpansiones =
                (List<Expansion>) request.getAttribute("listaExpansiones");
    %>

    <% if (carta != null) { %>

    <form action="<%= request.getContextPath() %>/SvCartaEditar"
          method="POST">

        <input type="hidden" name="id"
               value="<%= carta.getId_carta() %>">

        <label>Nombre de la carta:</label>
        <input type="text" name="nombre"
               value="<%= carta.getNombre() %>" required>

        <label>Tipo:</label>
        <select name="tipo" required>
            <option value="Planta" <%= "Planta".equals(carta.getTipo()) ? "selected" : "" %>>Planta</option>
            <option value="Fuego" <%= "Fuego".equals(carta.getTipo()) ? "selected" : "" %>>Fuego</option>
            <option value="Agua" <%= "Agua".equals(carta.getTipo()) ? "selected" : "" %>>Agua</option>
            <option value="Rayo" <%= "Rayo".equals(carta.getTipo()) ? "selected" : "" %>>Rayo</option>
            <option value="Psíquico" <%= "Psíquico".equals(carta.getTipo()) ? "selected" : "" %>>Psíquico</option>
            <option value="Lucha" <%= "Lucha".equals(carta.getTipo()) ? "selected" : "" %>>Lucha</option>
            <option value="Oscuridad" <%= "Oscuridad".equals(carta.getTipo()) ? "selected" : "" %>>Oscuridad</option>
            <option value="Metal" <%= "Metal".equals(carta.getTipo()) ? "selected" : "" %>>Metal</option>
            <option value="Dragón" <%= "Dragón".equals(carta.getTipo()) ? "selected" : "" %>>Dragón</option>
            <option value="Incoloro" <%= "Incoloro".equals(carta.getTipo()) ? "selected" : "" %>>Incoloro</option>
        </select>

        <label>Rareza:</label>
        <select name="rareza" required>
            <option value="Común" <%= "Común".equals(carta.getRareza()) ? "selected" : "" %>>Común</option>
            <option value="Poco común" <%= "Poco común".equals(carta.getRareza()) ? "selected" : "" %>>Poco común</option>
            <option value="Rara" <%= "Rara".equals(carta.getRareza()) ? "selected" : "" %>>Rara</option>
            <option value="Doble rara" <%= "Doble rara".equals(carta.getRareza()) ? "selected" : "" %>>Doble rara</option>
            <option value="Ilustración especial" <%= "Ilustración especial".equals(carta.getRareza()) ? "selected" : "" %>>Ilustración especial</option>
        </select>

        <label>Número:</label>
        <input type="number" name="numero"
               value="<%= carta.getNumero() %>" min="1" required>

        <label>Expansión:</label>
        <select name="id_expansion" required>
            <% if (listaExpansiones != null) {
                for (Expansion expansion : listaExpansiones) { %>

                <option value="<%= expansion.getId_expansion() %>"
                    <%= carta.getExpansion() != null
                        && carta.getExpansion().getId_expansion() == expansion.getId_expansion()
                        ? "selected" : "" %>>
                    <%= expansion.getNombre() %>
                </option>

            <%  }
            } %>
        </select>

        <button type="submit">Guardar cambios</button>

    </form>

    <% } else { %>
        <p>No se encontró la carta.</p>
    <% } %>

    <div class="botones-acciones">
        <a class="boton-accion boton-menu"
           href="<%= request.getContextPath() %>/SvCartaListar">
            Volver al listado
        </a>
    </div>

</div>

</body>
</html>
