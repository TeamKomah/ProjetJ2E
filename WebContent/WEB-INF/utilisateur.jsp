<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Help me</title>

<%@ page import="java.util.ArrayList" %>
<%@ page import="model.Utilisateur" %>
<%@ page import = "java.sql.SQLException" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" type="text/css" href="css/main.css"/>
<xml id="fichierxml" src="C:/Users/snianfo/Documents/Java_tp_et_projet/ProjetJavaEE/WebContent/WEB-INF/message.xml"></xml>
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
	<h4 class="hh4"><a href="Connection">Accueil</a> <a href="Index?decon=1">Deconnexion</a></h4>
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
	<div class="block-col2">
	
	
		<c:if test="${param.doc != null }">
			<ul class="doc">
				<h4>Mes Documents</h4></br>
				<c:if test="${empty mesdocs }">
					Vous n'avez partagé aucun document.
				</c:if>
				<c:forEach var="docs" items="${ mesdocs }">
					<li>
					<a href="DocumentS?iddoc=${docs.id }&&userid=${id}"  class="affichdoc">
						 <span class="nomdoc">${ docs.nom }</span>
						 <span class="date">${docs.dateC }</span>
						 
					</a>
					</li>
				</c:forEach>
			</ul>
		</c:if>
		<c:if test="${param.docsparta != null }">
			<ul class="doc">
				<h4>Documents partager avec moi</h4></br>
				<c:if test="${empty docspartager }">
					Aucun document n'a été partager avec vous.
				</c:if>
				<c:forEach var="docs" items="${ docspartager }">
					<li>
					<a href="DocumentS?iddoc=${docs.id }"  class="affichdoc">
						 <span class="nomdoc">${ docs.nom }</span>
						 <span class="date">${docs.dateC }</span>
						 
					</a>
				</c:forEach>
			</ul>
		</c:if>
		
		
		
		<c:if test="${param.aj != null }">
			
			<h2>Ajoutez un nouveau document</h2>
			<form action="DocumentS" method="post" enctype="multipart/form-data">
				<%request.setAttribute("form1", 1); %>
				fichier: <input type="file" name="fichier" id="fichier"/></br></br>
				description: <textarea name="descript" rows="5px" cols="40px"></textarea>
				
				<input type="submit" value="Envoyer">
			</form>
			
		</c:if>
		
		<!--  gestion du fihier en modification, suppression ou accessibilite à un utilisateur -->
	
		
		
		
		<!-- fin de  block-col2-->
	</div>
</div>
		

<script type="text/javascript"  src="js/projet.js"></script> 
<script type="text/javascript"  src="js/message.js"></script> 
</body>
</html>