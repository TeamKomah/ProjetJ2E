<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
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
					<li><a  href="Connection?ajAmi=1">+ ajouter un ami</a></li>
					<li><a href="Connection?aj=1"> + ajoutez un Document</a></li>
					<li><a href="Connection?doc=1&&userid=${id}">Mes Documents</a></li>
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
							<span class="bout-profil"> <a href="Profile?prof=${am.id}"> Voir Profil </a></span>
							<span class="bout-profil"><a href="Communiquer?id=${am.id}"> Communiquer </a></span>
						</div>
					</li>
					</c:forEach>
				</ul>
			</div>
			<div class="block-col2">
			
			
				<c:if test="${idd != null }">
						<div class="lefichier">
							<div class="contenu">
							<ul class="button">
									<c:if test="${userid != null }">
										<li class="deroule"><a href="DocumentS?iddoc=${param.iddoc}&&acces=1" >rendre accessible</a>
											
											<ul id = "deroule" >
												<form action="DocumentS1?iddoc=${param.iddoc }" method="post">
													<c:forEach var="am" items="${ amis }">
													<li><input type="checkbox" name="${am.id }" value="${am.id }"> <a href="#">${ am.nom } ${am.prenom }</a></li>
													</c:forEach>
													
													<li><input type="submit" value="Ajouter"/></li>
												</form>
											</ul>
										</li>
										<li><a href="DocumentS?iddoc=${param.iddoc}&&suppri=1">Supprimer le fichier</a></li>
										</c:if>
										<li><a href="VersionDocS?iddoc=${param.iddoc}&&version=1&&modif=0">Les versions</a></li>
										<li><a href="DocumentS?iddoc=${param.iddoc}&&modif=1">Modifier le fichier</a></li>
										<li><a href="DocumentS?iddoc=${idd }&&affiche=1&&userid=${id}">Apercu du document</a></li>
								</ul>
								</div>
								</div>
							
							<a href="DocumentS?iddoc=${idd }&&affiche=1"  class="affichdoc">
								 <span class="nomdoc">${nomdoc }</span>	 
							</a>
							<hr>
							<div class="commentaire">
							<h3>Description:</h3>
							</div>
							
							<!--  affichage du fichier -->
							<c:if test="${param.affiche != null }">
							<hr>
								<h3>Apercu du document:</h3>
								<c:forEach var="content" items="${ContentFichier}">
												
									${ content }</br>
								</c:forEach>
								
							</c:if>
							<hr>
							<div>
							<h3>Commentaire:</h3>
							<ul id="list_com">
								<c:forEach var="commt" items="${commentaires}">
								<li class="com">
									<span class="pseu"><a href="Profile?prof=${commt.expediteurCom.id}"> ${commt.expediteurCom.pseudo} </a></span>
									<span class="cont"> ${commt.contenu} </span>
									<span class="cont">${commt.dateCommentaire}</span>
								</li>
								</c:forEach>
							</ul>
							<br>
							<input type="text" name ="commentaire" id="commenterId" data-user="${id}" data-doc="${idd}" placeholder="votre commentaire..." >
							<button id="but">Valider</button>
							
							</div>
				</c:if>
				
				
				
				
				<!-- fin de  block-col2-->
			</div>
		</div>
		

<script type="text/javascript"  src="js/projet.js"></script> 
<script type="text/javascript"  src="js/commentaire.js"></script> 
</body>
</html>