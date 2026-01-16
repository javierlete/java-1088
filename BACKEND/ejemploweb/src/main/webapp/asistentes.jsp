<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.sql.*"%>
<%
String url = "jdbc:mysql://localhost:3306/asistentes";
String user = "root";
String pass = "1234";

Class.forName("com.mysql.cj.jdbc.Driver");

Connection con = DriverManager.getConnection(url, user, pass);

String op = request.getParameter("op");
String sId = request.getParameter("id");
String nombre = request.getParameter("nombre");
String apellidos = request.getParameter("apellidos");

String idForm = "";
String nombreForm = "";
String apellidosForm = "";

Long id = sId == null || sId.isBlank() ? null : Long.parseLong(sId);

PreparedStatement pst = null;
ResultSet rsAsistente = null;

if (op != null) {
	switch (op) {
	case "borrar":
		pst = con.prepareStatement("delete from asistentes where id=?");

		pst.setLong(1, id);
		pst.executeUpdate();
		break;
	case "guardar":
		if (id == null) {
	pst = con.prepareStatement("insert into asistentes (nombre, apellidos) VALUES (?,?)");
		} else {
	pst = con.prepareStatement("update asistentes set nombre=?, apellidos=? where id=?");
	pst.setLong(3, id);
		}

		pst.setString(1, nombre);
		pst.setString(2, apellidos);

		pst.executeUpdate();
		break;
	case "editar":
		pst = con.prepareStatement("select * from asistentes where id=?");
		pst.setLong(1, id);

		rsAsistente = pst.executeQuery();
		rsAsistente.next();

		idForm = rsAsistente.getString("id");
		nombreForm = rsAsistente.getString("nombre");
		apellidosForm = rsAsistente.getString("apellidos");
		break;
	}
}

pst = con.prepareStatement("select * from asistentes");

ResultSet rs = pst.executeQuery();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Asistentes</title>
</head>
<body>
	<h1>Listado de asistentes</h1>

	<form>
		<input type="hidden" name="op" value="guardar"> <input
			readonly name="id" value="<%=idForm%>"> <input name="nombre"
			placeholder="Nombre" value="<%=nombreForm%>"> <input
			name="apellidos" placeholder="Apellidos" value="<%=apellidosForm%>">

		<button>Guardar</button>
		<a href="asistentes.jsp">[Vaciar formulario]</a>
	</form>

	<ul>
		<%
		while (rs.next()) {
		%>
		<li><%=rs.getString("id")%>, <%=rs.getString("nombre")%>, <%=rs.getString("apellidos")%>
			<a href="?op=editar&id=<%=rs.getString("id")%>">[Editar]</a> <a
			href="?op=borrar&id=<%=rs.getString("id")%>">[Borrar]</a></li>
		<%
		}
		%>
	</ul>

</body>
</html>