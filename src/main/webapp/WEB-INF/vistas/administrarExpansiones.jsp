
<%@ page contentType="text/html" pageEncoding="UTF-8"%>

<%@ page import="java.util.List"%>
<%@ page import="com.app.logica.Expansion"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Administrar expansiones | PokéCards TCG</title>

    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/estilos.css">

    <style>
        body {
            background: #101820;
            color: #F5F5F5;
            font-family: Arial, sans-serif;
        }

        .contenedor {
            background: #192A3A;
            border: 2px solid #2879B9;
            border-radius: 14px;
            padding: 30px;
            max-width: 950px;
            margin: 40px auto;
            box-shadow: 0 0 20px rgba(0, 112, 192, 0.15);
        }

        h1 {
            color: #FFD43B;
            text-align: center;
            font-size: 34px;
            margin-bottom: 8px;
        }

        h2 {
            color: #39A9F4;
            border-bottom: 2px solid #2879B9;
            padding-bottom: 10px;
            margin-top: 25px;
        }

        label {
            color: #F5F5F5;
            font-weight: bold;
        }

        input[type="text"] {
            background: #101D29;
            color: white;
            border: 1px solid #2879B9;
            border-radius: 6px;
            padding: 11px;
            width: 100%;
            margin: 10px 0;
            box-sizing: border-box;
        }

        input[type="text"]:focus {
            outline: none;
            border-color: #FFD43B;
            box-shadow: 0 0 6px rgba(255, 212, 59, 0.4);
        }

        button {
            background: #087BC1;
            color: white;
            border: none;
            border-radius: 6px;
            padding: 10px 18px;
            cursor: pointer;
            font-weight: bold;
            margin-top: 8px;
        }

        button:hover {
            background: #159BE8;
        }

        .btn-registrar {
            background: #FFD43B !important;
            color: #172331 !important;
        }

        .btn-registrar:hover {
            background: #F0B900 !important;
        }

        .btn-mostrar {
            background: #087BC1 !important;
            color: white !important;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
            border-radius: 8px;
            overflow: hidden;
        }

        th {
            background: #087BC1;
            color: white;
            padding: 13px;
            text-align: left;
        }

        td {
            padding: 12px;
            border-bottom: 1px solid #31516B;
            color: #E8F3FA;
        }

        tr:nth-child(even) {
            background: #20374B;
        }

        tr:nth-child(odd) {
            background: #172B3D;
        }

        tr:hover {
            background: #294D69;
        }

        a {
            color: #FFD43B;
            text-decoration: none;
        }

        a:hover {
            color: #FFE477;
        }

        .error {
            color: #FF7777;
            background: #421F2A;
            border: 1px solid #C94358;
            padding: 10px;
            border-radius: 6px;
        }

        @media (max-width: 600px) {
            .contenedor {
                margin: 15px;
                padding: 18px;
            }

            table {
                font-size: 13px;
            }

            th, td {
                padding: 8px;
            }
        }
    </style>

</head>

<body>

<div class="contenedor">

    <h1>PokéCards TCG</h1>

    <h2>Administrar expansiones</h2>

    <!-- BUSCADOR -->
    <form action="<%= request.getContextPath() %>/SvExpansionBuscar"
          method="GET">

        <label>Buscar por nombre:</label>

        <input type="text"
               name="nombre"
               placeholder="Nombre de la expansión"
               value="<%= request.getAttribute("nombreBuscado") != null
                       ? org.owasp.encoder.Encode.forHtml((String) request.getAttribute("nombreBuscado"))
                       : "" %>"
               required>

        <button type="submit">Buscar</button>

        <br>

        <!-- REGISTRAR NUEVA EXPANSIÓN -->
        <a href="<%= request.getContextPath() %>/SvExpansionCrear">
            <button type="button" class="btn-registrar">
                Registrar nueva expansión
            </button>
        </a>

        <br><br>

        <!-- VOLVER AL MENÚ -->
        <a href="<%= request.getContextPath() %>/index.html">
            Volver al menú
        </a>

        <br><br>

    </form>

    <%
        String error = request.getParameter("error");

        if (error != null) {
    %>

        <p class="error">
            No se pudo realizar la operación.
        </p>

    <%
        }

        List<Expansion> listaExpansiones =
            (List<Expansion>) request.getAttribute("listaExpansiones");
    %>

    <h2>Expansiones registradas</h2>

    <% if (listaExpansiones != null && !listaExpansiones.isEmpty()) { %>

        <table>

            <thead>
                <tr>
                    <th>ID</th>
                    <th>Nombre</th>
                    <th>Fecha de lanzamiento</th>
                </tr>
            </thead>

            <tbody>

                <% for (Expansion expansion : listaExpansiones) { %>

                    <tr>

                        <td>
                            <%= expansion.getId_expansion() %>
                        </td>

                        <td>
                            <%= org.owasp.encoder.Encode.forHtml(expansion.getNombre()) %>
                        </td>

                        <td>
                            <%= org.owasp.encoder.Encode.forHtml(expansion.getFecha_lanzamiento()) %>
                        </td>

                    </tr>

                <% } %>

            </tbody>

        </table>

    <% } else { %>

        <p>No se encontraron expansiones registradas.</p>

    <% } %>

    <br>

</div>

</body>
</html>
