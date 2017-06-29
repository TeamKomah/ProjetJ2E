(function(){
	alert("ça marche");
	var tel = document.getElementById("telecharger"),	
		id = tel.getAttribute("data-id");
	
	function telecharger(){
		alert("dans la function");
		var url = "Telechargement?iddoc="+escape(id);
		var xhr = new XMLHttpRequest();
		xhr.open('GET',url,true);
		xhr.onreadystatechange = function(){
			if(xhr.readyState == 4 && xhr.status == 200){
				retour();
			}
		}
		xhr.send(null);
	}
	
	function retour(){
		alert("ok");
	}
	
	tel.addEventListener("click", function(){
		alert('clické');
		telecharger();
	});
})();