
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="com.app.logica.Expansion"%>

<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Editar expansión | PokéCards TCG</title>

    <style>
        body {
            font-family: Arial, sans-serif;
            background: #f2f4f8;
            margin: 0;
            padding: 30px;
            color: #222;
        }

        .contenedor {
            max-width: 600px;
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
            margin: 8px 0;
            width: 100%;
            box-sizing: border-box;
        }

        button {
            background: #d62828;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }

        .volver {
            display: inline-block;
            margin-top: 15px;
            color: #d62828;
        }
    </style>
</head>

<body>

<div class="contenedor">

    <h1>PokéCards TCG</h1>
    <h2>Editar expansión</h2>

    <%
        Expansion expansion = (Expansion) request.getAttribute("expansion");

        if (expansion != null) {
    %>

    <form action="${pageContext.request.contextPath}/SvExpansionActualizar"
          method="POST">

        <input type="hidden" name="id_expansion"
               value="<%= expansion.getId_expansion() %>">

        <label>Nombre de la expansión:</label>
        <input type="text" name="nombre"
               value="<%= org.owasp.encoder.Encode.forHtml(expansion.getNombre()) %>"
               required>

        <label>Fecha de lanzamiento:</label>
        <input type="date" name="fecha_lanzamiento"
               value="<%= org.owasp.encoder.Encode.forHtml(expansion.getFecha_lanzamiento()) %>"
               required>

        <button type="submit">Guardar cambios</button>

    </form>

    <%
        } else {
    %>

        <p>No se encontró la expansión.</p>

    <%
        }
    %>

    <a class="volver"
       href="${pageContext.request.contextPath}/SvExpansionListar">
        Volver a expansiones
    </a>

</div>

</body>
</html>
