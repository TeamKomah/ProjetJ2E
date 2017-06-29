<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/main.css"/>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.Utilisateur" %>
<%@ page import = "java.sql.SQLException" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" type="text/css" href="css/main.css"/>

</head>
<body>
<div class="tete">
			<div class="titre">
				<h1>HELP ME</h1>
			</div>
			<div class="formRech">
				<ul>
					<form action="Profile?user=${id}&&ajAmi=1" method="post">
						<input type="text" name="recherche" placeholder="nom ou pseudo" id="rech">
						<input type="submit" value="Recherche">
					</form>
					<ul id="list_rech">
								
					</ul>
				</ul>
			</div>	
		</div>
		<h4><a href="Connection">Accueil</a> <a href="Index?decon=1">Deconnexion</a></h4>
		<div class="block">
			<div class = "menu">
				<h3 class="utilNomPrenom">
				<span class="image"><a href="#"><img alt="image" src="css/index.jpg"></a></span>
				<span class="util">${nom} ${prenom}</span>
				</h3>
				Bienvenue sur ton site prefere
				<ul class="menuOpt">
					<li><a href="Connection?aj=1"> + ajoutez un Document</a></li>
					<li><a href="Connection?doc=1&&userid=${id}">Mes Documents</a></li>
					<li><a href="Connection?docsparta=1">Documents partagés avec moi</a></li>
					
				</ul>
				<br>
				
				    	
				<ul id="mes_amis" data-user="${id}">
					<h2>Amis</h2>
					<c:forEach var="am" items="${ amis }">
					<li class="amis">
						<div>
							<a href="#"> ${ am.nom } ${am.prenom }</a>
							<span class="nonLu" data-id="${am.id}">
								<c:forEach var="nbr" items="${nonLu}">
									<c:if test="${nbr[0] == am.id}">
										${nbr[1]}
									</c:if>
								</c:forEach>
							</span>
						</div>
						
						<div class="hide">
							<span class="bout-profil"> <a href="Profile?prof=${am.id}"> Voir Profil </a></span>
							<span class="bout-profil"><a href="Communiquer?id=${am.id}"> Communiquer </a></span>
						</div>
					</li>
					</c:forEach>
				</ul>
			</div>
<!-- fin de menu -->
	<div class="block-col2">
		
	
		<ul class="doc">
				<h4>les version du documents</h4></br>
				<c:if test="${empty lesVersions }">
					Ce document n'a pas de version.
				</c:if>
				<c:forEach var="docs" items="${ lesVersions }">
					<li>
					<a href="Version?iddoc=${docs.id }&userid=${id}""  class="affichdoc">
						 <span class="nomdoc">${ docs.nom }</span>
						 <span class="date">${docs.dateC }</span>
						 
					</a>
					</li>
				</c:forEach>
		</ul>
		
	</div>
</div>
<script type="text/javascript"  src="js/message.js"></script> 
</body>
</html>