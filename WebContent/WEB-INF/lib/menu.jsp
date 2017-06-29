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