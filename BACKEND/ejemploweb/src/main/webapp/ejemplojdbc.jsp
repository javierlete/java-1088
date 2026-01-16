<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.sql.*"%>
<%
String url = "jdbc:sqlite:jdbc.db";
String user = "";
String pass = "";

Class.forName("org.sqlite.JDBC");

Connection con = DriverManager.getConnection(url, user, pass);
Statement st = con.createStatement();
st.executeUpdate("drop table if exists personas");
st.executeUpdate("create table personas (id integer primary key, nombre varchar(30))");

st.executeUpdate("insert into personas (nombre) values ('leo')");
st.executeUpdate("insert into personas (nombre) values ('yui')");

ResultSet rs = st.executeQuery("select * from personas");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ejemplo JDBC</title>
</head>
<body>
	<ul>
		<%

		while (rs.next()) {
		%>
		<li><%=rs.getString("id")%>, <%=rs.getString("nombre")%></li>
		<%
		}
		%>
	</ul>

</body>
</html>