

var input = document.getElementById('rech');

function amis(objet){
	
	if(document.getElementById(objet.id)){
		document.getElementById(objet.id+" ul li").style.display = "block";
	}
}

function commentaire(id){
	
	var commente = document.getElementById(id);
	alert(commente.contains());
}


function rechercheAmis(text){
	
	var url = "RechercheAmis?text="+escape(text.value);
	var xhr = new XMLHttpRequest();
	xhr.open('GET',url,true);
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			
			retour(xhr.responseXML);
		}
	}
	
	xhr.send(null);
}

function retour(xml){
	//alert("entree");
	//var xmlDoc = xml.responseXML;
	var ul = document.getElementById('list_rech');
	ul.innerHTML="";
	var rac = xml.documentElement;
	
	for (var i = 0; i < rac.childNodes.length; i++) {
		var ami = rac.childNodes[i];
		var id = ami.getElementsByTagName("id")[0].childNodes[0].nodeValue;
		var nom = ami.getElementsByTagName("nom")[0].childNodes[0].nodeValue;
		var prenom =  ami.getElementsByTagName("prenom")[0].childNodes[0].nodeValue;
		var li = document.createElement('li');
		var lien = document.createElement('a');
		lien.setAttribute('href',"Profile?id="+id);
		li.setAttribute('class','amis_tr');
		lien.textContent = nom+" "+prenom;
		li.appendChild(lien);
		ul.appendChild(li);
		
	}
}

input.addEventListener("keyup", function(){
	
	rechercheAmis(this);
});
