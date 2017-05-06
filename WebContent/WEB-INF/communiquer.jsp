<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Communiquer</title>

<%@ page import="java.util.ArrayList" %>
<%@ page import="model.Utilisateur" %>
<%@ page import = "java.sql.SQLException" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<link rel="stylesheet" type="text/css" href="css/main.css"/>
<xml id="fichierxml" src="C:/Users/snianfo/Documents/Java_tp_et_projet/ProjetJavaEE/src/model/messages.xml/messages.xml"></xml>
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
					<li><a  href="Connection?ajAmi=1">+ ajouter un ami</a></li>
					<li><a href="Connection?aj=1"> + ajoutez un Document</a></li>
					<li><a href="Connection?doc=1">Mes Documents</a></li>
					<li><a href="Connection?docsparta=1">Documents partagés avec moi</a></li>
					
				</ul>
				<br>

    	
<ul>
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
			<span class="bout-profil"> <a href="#"> Voir Profil </a></span>
			<span class="bout-profil"><a href="Communiquer?id=${am.id}"> Communiquer </a></span>
		</div>
	</li>
	</c:forEach>
</ul>
</div>
<div id="conversation">
	<div id="zn_convers">
		<ul id="list_msg">
			<c:forEach var="msg" items="${communication}">
				<c:if test="${msg.expediteur.id == expediteur.id }">
					<li class="env">
						<div class="heure">${msg.dateMes}</div>
						<div class="cont">${msg.contenu}</div>
					</li>
				</c:if>
				<c:if test="${msg.expediteur.id == recepteur.id }">
					<li class="rec">
						<div class="heure">${msg.dateMes}</div>
						<div class="cont">${msg.contenu}</div>
					</li>
				</c:if> 
			</c:forEach>
		</ul>
	</div>
	
	<div id="message">
			<input type="text" id="zn_msg" data-exp="${expediteur.id}" data-recep="${recepteur.id}" 
			placeholder="Taper votre message"/>
			<button id="btn">Envoyer</button>
	</div>
</div>
</div>
<script type="text/javascript" src="js/profil.js"></script>
</body>
</html>