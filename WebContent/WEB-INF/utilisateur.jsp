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
<link rel="stylesheet" type="text/css" href="css/main.css"/>
</head>
<body>
<div class="tete">
<h1>HELP ME</h1>
</div>
<div class="block">
	<div class = "menu">
		<h3 class="utilNomPrenom">
		<span class="image"><a href="#"><img alt="image" src="css/index.jpg"></a></span>
		<span class="util">${nom} ${prenom}</span>
		</h3>
		Bienvenue sur ton site prefere
		<ul class="menuOpt">
			<li><a href="Connection?aj=1"> + ajoutez un Document</a></li>
			<li><a href="Connection?doc=1">Mes Documents</a></li>
			<li><a href="Connection?docp=1">Documents partagés avec moi</a></li>
		</ul>
		<br>
		
		    	
		<ul>
			<h2>Amis</h2>
			<c:forEach var="am" items="${ amis }">
			<li><a href="#"> ${ am.nom } ${am.prenom }</a></li>
			</c:forEach>
		</ul>
	</div>
	<div class="block-col2">
		<c:if test="${param.doc != null }">
			<ul class="doc">
				<h2>Mes Documents</h2>
				<c:forEach var="docs" items="${ mesdocs }">
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
				<input type="text" name="descrip" id="descrip"/>
				<input type="file" name="fichier" id="fichier"/>
				<input type="submit" value="Envoyer">
			</form>
			
		</c:if>

		<c:if test="${param.iddoc != null && param.modif != null }">
		
			<form action="VersionDocS" method="post">
				<textarea name="newversion" rows="35px" cols="100%">
				<c:forEach var="content" items="${ContentFichier}">
				${ content }</br>
				</c:forEach>
				</textarea>
				<input type="submit" value="enregistre">
			</form>
			
		</c:if> 
		
		<!--  gestion du fihier en modification, suppression ou accessibilite à un utilisateur -->
		<c:if test="${param.iddoc !=null && param.modif == null}">
			<div class="lefichier">
				<div class="contenu">
					<ul class="button">
						<li><a href="DocumentS?iddoc=${param.iddoc}&&modif=1">Modifier le fichier</a></li>
						<li><a href="DocumentS?iddoc=${param.iddoc}&&suppri=1">Supprimer le fichier</a></li>
						<li><a href="DocumentS?iddoc=${param.iddoc}&&access=1">rendre accessible</a></li>
						<li><a href="DocumentS?iddoc=${param.iddoc}&&version=1">Les versions</a></li>
						
					</ul>
					
					<hr>
					</br>
					
					<c:forEach var="content" items="${ContentFichier}">
					${ content }</br>
					</c:forEach>
					
					</br></br>
					<hr>
					<form action="DocumentS" method="post">
						<input type="text" name ="commentaire" id="commenterId" placeholder="votre commentaire..." >
						<input type="submit" value="valider">
					</form>
				</div>
				<div>
				<!-- publicité -->
				</div>
			</div>
		</c:if>
		
	</div>
</div>
<script type="text/javascript"  src="js/projet.js"></script>
</body>
</html>