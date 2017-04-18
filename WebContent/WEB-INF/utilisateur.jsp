<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<%@ page import="java.util.ArrayList" %>
<%@ page import="model.Utilisateur" %>
<%@ page import = "java.sql.SQLException" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
</head>
<body>
${nom} ${prenom}
Bienvenue sur ton site prefere
<ul>
	<li><a href="Connection?aj=1"> + ajoutez un Document</a></li>
	<li><a href="Connection?doc=1">Mes Documents</a></li>
	<li><a href="Connection?docp=1">Documents partagés avec moi</a></li>
</ul>
<br>

<h4>Les Amis</h4>
<ul>
	<c:forEach var="am" items="${ amis }">
	<li><a href="#"> ${ am.nom } ${am.prenom }</a></li>
	</c:forEach>
</ul>
<c:if test="${param.doc != null }">
	<ul>
		<h4>Mes Documents</h4>
		<c:forEach var="docs" items="${ mesdocs }">
			<li><a href="#"> ${ docs.nom }</a>
		</c:forEach>
	</ul>
</c:if>

<c:if test="${param.aj != null }">
	${param.aj}
	<h5>Ajoutez un nouveau dicument</h5>
	<form action="DocumentS" method="post" enctype="multipart/form-data">
		<%request.setAttribute("form1", 1); %>
		<input type="text" name="descrip" id="descrip">
		<input type="file" name="fichier" id="fichier"/>
		<input type="submit" value="Envoyer">
	</form>

</c:if>





</body>
</html>