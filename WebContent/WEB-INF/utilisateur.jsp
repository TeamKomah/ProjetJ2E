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
</head>
<body>
<c:if test="${! empty id }">
		<div class="tete">
		<h1>HELP ME</h1>
		<h4><a href="Connection">Accueil</a> <a href="Index?decon=1">Deconnexion</a></h4>
		</div>
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
						<h2>Mes Documents</h2>
						<c:forEach var="docs" items="${ mesdocs }">
							<li>
							<a href="DocumentS?iddoc=${docs.id }"  class="affichdoc">
								 <span class="nomdoc">${ docs.nom }</span>
								 <span class="date">${docs.dateC }</span>
								 
							</a>
							</li>
						</c:forEach>
					</ul>
				</c:if>
				<c:if test="${param.docsparta != null }">
					<ul class="doc">
						<h2>Documents partager avec moi</h2>
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
						<input type="text" name="descrip" id="descrip"/>
						<input type="file" name="fichier" id="fichier"/>
						<input type="submit" value="Envoyer">
					</form>
					
				</c:if>
				${docmodifMessage}
				 <c:if test="${param.iddoc != null && param.modif != null && docmodif != null }">
				<% request.setAttribute("docModif", null); %> 
					<form action="VersionDocS" method="post">
						<textarea name="newversion" rows="35px" cols="100%">
						<c:forEach var="content" items="${ContentFichier}">
						${ content }
						</c:forEach>
						</textarea>
						<input type="submit" value="enregistre">
					</form>
					
				</c:if> 
				
				<!--  gestion du fihier en modification, suppression ou accessibilite à un utilisateur -->
			
				<c:choose>
					<c:when test= "${! empty suppression}">
						<h4>	${suppression} </h4>
					
					</c:when>
					
					<c:otherwise>
						<c:if test="${param.iddoc !=null && param.modif == null}">
							<div class="lefichier">
								<div class="contenu">
									<ul class="button">
			
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
										<li><a href="VersionDocS?iddoc=${param.iddoc}&&version=1&&modif=0">Les versions</a></li>
										<li><a href="DocumentS?iddoc=${param.iddoc}&&modif=1">Modifier le fichier</a></li>
										<li><a href="DocumentS?iddoc=${param.iddoc}&&suppri=1">Supprimer le fichier</a></li>
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
								
								</div>
							</div>
						</c:if>
					</c:otherwise>
				</c:choose> 
				<c:if test="${! empty param.ajAmi }">
					<ul>
						<form action="Profile?user=${id}&&ajAmi=1" method="post">
							<input type="text" name="recherche" placeholder="nom ou pseudo">
							<input type="submit" value="Recherche">
						</form>
					</ul>
				
				</c:if>
				<c:if test="${!empty lesAmisDamis }">
					
					<ul>
						
						<h4>Vous pouvez peut-etre connaitre ces utilisateurs</h4>
						<c:forEach var="ami" items="${lesAmisDamis }">
						<li> ${ami.id} ${ami.nom } ${ami.prenom}<a href="Connection?ajoutAmi=${ami.id}&&Anom=${ami.nom}&&Aprenom=${ami.prenom}" >Ajouter</a></li>	
						</c:forEach>
					</ul>
				</c:if>
				<c:if test="${!empty amisPropose }">
					
					<ul>
						
						<h4>Vous pouvez peut-etre connaitre ces utilisateurs</h4>
						<c:forEach var="ami" items="${amisPropose}">
						<li>${ami.id} ${ami.nom } ${ami.prenom}<a href="Connection?ajoutAmi=${am.id }&&Anom=${ami.nom}&&Aprenom=${ami.prenom}" >Ajouter</a></li>	
						</c:forEach>
					</ul>
				</c:if>
				
				
				
				<!-- fin de  block-col2-->
			</div>
		</div>
		<script type="text/javascript"  src="js/projet.js"></script>
		<script type="text/javascript"  src="js/profil.js"></script>
		
</c:if>
<c:if test="${id == null }">
<h3>Vous etes deconnecte, reconnectez-vous pour pouvoir continuer <a href="Index">Connection</a></h3>
</c:if>
</body>
</html>