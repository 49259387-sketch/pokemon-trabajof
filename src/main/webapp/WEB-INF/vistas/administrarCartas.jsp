
<%@ page contentType="text/html" pageEncoding="UTF-8"%>

<%@ page import="java.util.List"%>
<%@ page import="com.app.logica.Carta"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Administrar cartas | PokéCards TCG</title>

    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/estilos.css">

    <style>
        body {
            background: #101820;
            color: #F5F5F5;
            font-family: Arial, sans-serif;
        }

        .contenedor {
            background: #192A3A;
            border: 2px solid #1769AA;
            border-radius: 14px;
            padding: 30px;
            max-width: 1000px;
            margin: 40px auto;
            box-shadow: 0 0 20px rgba(23, 105, 170, 0.2);
        }

        h1 {
            color: #E63946;
            text-align: center;
            font-size: 34px;
            margin-bottom: 8px;
        }

        h2 {
            color: #4DA3FF;
            border-bottom: 2px solid #1769AA;
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
            border: 1px solid #1769AA;
            border-radius: 6px;
            padding: 11px;
            width: 100%;
            margin: 10px 0;
            box-sizing: border-box;
        }

        input[type="text"]:focus {
            outline: none;
            border-color: #E63946;
            box-shadow: 0 0 6px rgba(230, 57, 70, 0.4);
        }

        button {
            background: #1769AA;
            color: white;
            border: none;
            border-radius: 6px;
            padding: 10px 18px;
            cursor: pointer;
            font-weight: bold;
            margin-top: 8px;
        }

        button:hover {
            background: #2588D0;
        }

        .btn-registrar {
            background: #E63946 !important;
            color: white !important;
        }

        .btn-registrar:hover {
            background: #C62836 !important;
        }

        .btn-mostrar {
            background: #1769AA !important;
            color: white !important;
        }

        .btn-mostrar:hover {
            background: #2588D0 !important;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
            border-radius: 8px;
            overflow: hidden;
        }

        th {
            background: #1769AA;
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
            color: #E63946;
            text-decoration: none;
        }

        a:hover {
            color: #FF6671;
        }

        .error {
            color: #FF7777;
            background: #421F2A;
            border: 1px solid #C94358;
            padding: 10px;
            border-radius: 6px;
        }

        @media (max-width: 700px) {
            .contenedor {
                margin: 15px;
                padding: 18px;
                overflow-x: auto;
            }

            table {
                font-size: 13px;
                min-width: 600px;
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

    <h2>Administrar cartas</h2>

    <!-- BUSCADOR -->
    <form action="<%= request.getContextPath() %>/SvCartaBuscar"
          method="GET">

        <label>Buscar por nombre:</label>

        <input type="text"
               name="nombre"
               placeholder="Nombre de la carta"
               required>

        <button type="submit">Buscar</button>

        <br>

        <!-- REGISTRAR NUEVA CARTA -->
        <a href="<%= request.getContextPath() %>/SvCartaCrear">
            <button type="button" class="btn-registrar">
                Registrar nueva carta
            </button>
        </a>

        <br><br>

        <!-- MOSTRAR TODAS -->
        <a href="<%= request.getContextPath() %>/SvCartaListar">
            <button type="button" class="btn-mostrar">
                Mostrar todas
            </button>
        </a>

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

        List<Carta> listaCartas =
            (List<Carta>) request.getAttribute("listaCartas");
    %>

    <h2>Cartas registradas</h2>

    <% if (listaCartas != null && !listaCartas.isEmpty()) { %>

        <table>

            <thead>
                <tr>
                    <th>ID</th>
                    <th>Nombre</th>
                    <th>Tipo</th>
                    <th>Rareza</th>
                    <th>Número</th>
                    <th>Expansión</th>
                </tr>
            </thead>

            <tbody>

                <% for (Carta carta : listaCartas) { %>

                    <tr>

                        <td>
                            <%= carta.getId_carta() %>
                        </td>

                        <td>
                            <%= org.owasp.encoder.Encode.forHtml(carta.getNombre()) %>
                        </td>

                        <td>
                            <%= org.owasp.encoder.Encode.forHtml(carta.getTipo()) %>
                        </td>

                        <td>
                            <%= org.owasp.encoder.Encode.forHtml(carta.getRareza()) %>
                        </td>

                        <td>
                            <%= carta.getNumero() %>
                        </td>

                        <td>
                            <%= org.owasp.encoder.Encode.forHtml(carta.getExpansion().getNombre()) %>
                        </td>

                    </tr>

                <% } %>

            </tbody>

        </table>

    <% } else { %>

        <p>No se encontraron cartas registradas.</p>

    <% } %>

    <br>

    <a href="<%= request.getContextPath() %>/index.html">
        Volver al menú
    </a>

</div>

</body>
</html>
