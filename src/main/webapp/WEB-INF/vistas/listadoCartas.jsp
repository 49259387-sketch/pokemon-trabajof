
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="com.app.logica.Carta"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Listado de cartas | PokéCards TCG</title>

    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/estilos.css">
</head>

<body>

<div class="contenedor">

    <h1>PokéCards TCG</h1>
    <h2>Listado de cartas</h2>

    <!-- BUSCADOR -->
    <form class="buscador"
          action="<%= request.getContextPath() %>/SvCartaBuscar"
          method="GET">

        <input type="text" name="nombre"
               placeholder="Buscar carta por nombre..."
               value="<%= request.getAttribute("nombreBuscado") != null ? request.getAttribute("nombreBuscado") : "" %>">

        <button type="submit">Buscar</button>
    </form>

    <p>
        <a class="boton-accion"
           href="<%= request.getContextPath() %>/SvCartaCrear">
            Agregar nueva carta
        </a>
    </p>

    <table>

        <tr>
            <th>ID</th>
            <th>Nombre</th>
            <th>Tipo</th>
            <th>Rareza</th>
            <th>Número</th>
            <th>Expansión</th>
            <th>Acciones</th>
        </tr>

        <%
            List<Carta> listaCartas =
                (List<Carta>) request.getAttribute("listaCartas");

            if (listaCartas != null && !listaCartas.isEmpty()) {

                for (Carta carta : listaCartas) {
        %>

        <tr>

            <td><%= carta.getId_carta() %></td>

            <td><%= carta.getNombre() %></td>

            <td><%= carta.getTipo() %></td>

            <td><%= carta.getRareza() %></td>

            <td><%= carta.getNumero() %></td>

            <td>
                <%= carta.getExpansion() != null
                    ? carta.getExpansion().getNombre()
                    : "Sin expansión" %>
            </td>

            <!-- BOTONES DE ACCIONES -->
            <td>

                <!-- EDITAR -->
                <a class="boton-accion"
                   href="<%= request.getContextPath() %>/SvCartaEditar?id=<%= carta.getId_carta() %>">
                    Editar
                </a>

                <!-- ELIMINAR -->
                <a class="boton-accion"
                   href="<%= request.getContextPath() %>/SvCartaEliminar?id=<%= carta.getId_carta() %>"
                   onclick="return confirm('¿Estás seguro de que querés eliminar esta carta?');">
                    Eliminar
                </a>

            </td>

        </tr>

        <%
                }

            } else {
        %>

        <tr>
            <td colspan="7">No se encontraron cartas.</td>
        </tr>

        <%
            }
        %>

    </table>

    <div class="botones-acciones">

        <a class="boton-accion boton-menu"
           href="<%= request.getContextPath() %>/index.html">
            Volver al menú
        </a>

    </div>

</div>

</body>
</html>