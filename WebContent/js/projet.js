
function amis(objet){
	//document.getElementById(objet.id).style.display = "none";
	if(document.getElementById(objet.id)){
		document.getElementById(objet.id+" ul li").style.display = "block";
	}
}

function commentaire(id){
	
	var commente = document.getElementById(id);
	alert(commente.contains());
}