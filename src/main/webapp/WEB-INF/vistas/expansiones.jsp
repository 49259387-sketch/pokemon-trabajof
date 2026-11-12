
<%@ page contentType="text/html" pageEncoding="UTF-8"%>

<%@ page import="java.util.List"%>
<%@ page import="com.app.logica.Expansion"%>

<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Expansiones | PokéCards TCG</title>

    <style>
        body {
            font-family: Arial, sans-serif;
            background: #f2f4f8;
            margin: 0;
            padding: 30px;
            color: #222;
        }

        .contenedor {
            max-width: 900px;
            margin: auto;
            background: white;
            padding: 25px;
            border-radius: 10px;
        }

        h1 {
            color: #d62828;
        }

        input, button {
            padding: 10px;
            margin: 5px 0;
        }

        input {
            box-sizing: border-box;
            width: 100%;
        }

        button {
            background: #d62828;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }

        th, td {
            padding: 12px;
            border-bottom: 1px solid #ddd;
            text-align: left;
        }

        th {
            background: #222;
            color: white;
        }

        .error {
            color: #b00020;
            background: #ffe5e5;
            padding: 12px;
            border-radius: 5px;
        }

        .accion {
            display: inline-block;
            padding: 8px 12px;
            background: #d62828;
            color: white;
            text-decoration: none;
            border-radius: 5px;
            margin-right: 5px;
        }

        .editar {
            background: #333;
        }
    </style>
</head>

<body>

<div class="contenedor">

    <h1>PokéCards TCG</h1>
    <h2>Registrar expansión</h2>

    <%
        String error = request.getParameter("error");

        if ("tieneCartas".equals(error)) {
    %>

        <p class="error">
            No se puede eliminar esta expansión porque tiene cartas.
            Primero tenés que eliminar esas cartas de la expansion.
        </p>

    <%
        } else if ("eliminar".equals(error)) {
    %>

        <p class="error">
            Ocurrió un error al intentar eliminar la expansión.
        </p>

    <%
        } else if ("actualizar".equals(error)) {
    %>

        <p class="error">
            Ocurrió un error al intentar actualizar la expansión.
        </p>

    <%
        } else if (error != null) {
    %>

        <p class="error">
            No se pudo completar la operación. Revisá los datos ingresados.
        </p>

    <%
        }
    %>

    <form action="${pageContext.request.contextPath}/SvExpansionCrear"
          method="POST">

        <label>Nombre de la expansión:</label>
        <input type="text" name="nombre" required>

        <label>Fecha de lanzamiento:</label>
        <input type="date" name="fecha_lanzamiento" required>

        <button type="submit">Guardar expansión</button>

    </form>

    <h2>Expansiones registradas</h2>

    <table>

        <tr>
            <th>ID</th>
            <th>Nombre</th>
            <th>Fecha de lanzamiento</th>
            <th>Acciones</th>
        </tr>

        <%
            List<Expansion> lista =
                (List<Expansion>) request.getAttribute("listaExpansiones");

            if (lista != null && !lista.isEmpty()) {

                for (Expansion expansion : lista) {
        %>

        <tr>

            <td><%= expansion.getId_expansion() %></td>

            <td>
                <%= org.owasp.encoder.Encode.forHtml(expansion.getNombre()) %>
            </td>

            <td>
                <%= org.owasp.encoder.Encode.forHtml(expansion.getFecha_lanzamiento()) %>
            </td>

            <td>

                <a class="accion editar"
                   href="${pageContext.request.contextPath}/SvExpansionEditar?id=<%= expansion.getId_expansion() %>">
                    Editar
                </a>

                <a class="accion"
                   href="${pageContext.request.contextPath}/SvExpansionEliminar?id=<%= expansion.getId_expansion() %>"
                   onclick="return confirm('¿Querés eliminar esta expansión?');">
                    Eliminar
                </a>

            </td>

        </tr>

        <%
                }

            } else {
        %>

        <tr>
            <td colspan="4">
                Todavía no hay expansiones registradas.
            </td>
        </tr>

        <%
            }
        %>

    </table>

    <a href="${pageContext.request.contextPath}/index.html">
        <button type="button">Volver al menú</button>
    </a>

</div>

</body>
</html>