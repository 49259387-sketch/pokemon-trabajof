<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="com.app.logica.Carta"%>
<%@ page import="com.app.logica.Expansion"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Cartas | PokéCards TCG</title>

    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/estilos.css">
</head>

<body>

<div class="contenedor">

    <h1>PokéCards TCG</h1>

    <h2>Registrar carta</h2>

    <%
        String error = request.getParameter("error");

        if (error != null) {
    %>
        <p class="error">No se pudo completar el registro. Revisá los datos ingresados.</p>
    <%
        }
    %>

    <form action="<%= request.getContextPath() %>/SvCartaCrear" method="POST">

        <label>Nombre de la carta:</label>
        <input type="text" name="nombre" required>

        <label>Tipo de Pokémon:</label>
        <select name="tipo" required>
            <option value="">Seleccioná un tipo</option>
            <option value="Planta">Planta</option>
            <option value="Fuego">Fuego</option>
            <option value="Agua">Agua</option>
            <option value="Rayo">Rayo</option>
            <option value="Psíquico">Psíquico</option>
            <option value="Lucha">Lucha</option>
            <option value="Oscuridad">Oscuridad</option>
            <option value="Metal">Metal</option>
            <option value="Dragón">Dragón</option>
            <option value="Incoloro">Incoloro</option>
        </select>

        <label>Rareza:</label>
        <select name="rareza" required>
            <option value="">Seleccioná una rareza</option>
            <option value="Común">Común</option>
            <option value="Poco común">Poco común</option>
            <option value="Rara">Rara</option>
            <option value="Doble rara">Doble rara</option>
            <option value="Ilustración especial">Ilustración especial</option>
        </select>

        <label>Número de carta:</label>
        <input type="number" name="numero" min="1" required>

        <label>Expansión:</label>
        <select name="id_expansion" required>
            <option value="">Seleccioná una expansión</option>

            <%
                List<Expansion> listaExpansiones =
                    (List<Expansion>) request.getAttribute("listaExpansiones");

                if (listaExpansiones != null) {
                    for (Expansion expansion : listaExpansiones) {
            %>
                <option value="<%= expansion.getId_expansion() %>">
                    <%= expansion.getNombre() %>
                </option>
            <%
                    }
                }
            %>
        </select>

        <button type="submit">Guardar carta</button>

    </form>
	

	
   

    
    
    
 	
		
	<!-- BUSCADOR -->
    <form class="buscador"
          action="<%= request.getContextPath() %>/SvCartaListar"
          method="GET">

       

        <button type="submit">Buscar Cartas</button>

    </form>	
		
    <p>
        <a href="<%= request.getContextPath() %>/SvExpansionListar">
            Administrar expansiones
        </a>
    </p>
	
    <a class="volver" href="<%= request.getContextPath() %>/index.html">
        <button type="button">Volver al menú</button>
    </a>

</div>

</body>
</html>