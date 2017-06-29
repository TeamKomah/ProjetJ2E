(function(){
	var input = document.getElementById('commenterId');
	var user = input.getAttribute('data-user');
	var doc = input.getAttribute('data-doc');
	var but = document.getElementById('but');
	var list = document.getElementById('list_com');
	
	function SaveComm(){
		
		var url = "SaveCommentaire?user="+escape(user)+"&cont="+escape(input.value)+"&doc="+escape(doc);
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
	//	var span = document.createElement('span');
	//	span.textContent = "Envoyé";
	}
	
	but.addEventListener("click", function(){
		var li = document.createElement("li");
		var span1 = document.createElement("span");
		var span2 = document.createElement("span");
		var span3 =document.createElement("span");
		
		li.setAttribute("class","com");
		
		span1.setAttribute("class","pseu");
		span1.textContent= "Moi";
		
		span2.setAttribute("class","commentaire");
		span2.textContent = input.value;
		
		span3.setAttribute("class","date_com");
		span3.textContent = "A l'instant";
		
		li.appendChild(span1);
		li.appendChild(span2);
		li.appendChild(span3);
		list.appendChild(li);
		
		SaveComm();
	});
	
	//fonction qui recharge la conversation courante
	function chargerComm(){
	
		var url = "ChargerCommentaire?doc="+escape(doc);
		var xhr = new XMLHttpRequest();
		xhr.open('GET',url,true);
		xhr.onreadystatechange = function(){
			if(xhr.readyState == 4 && xhr.status == 200){
				recharger(xhr.responseXML);
			}
		}
		xhr.send(null);
	}
	
	function recharger(xml){
		list.innerHTML = "";
		var rac = xml.documentElement;
		for (var i = 0; i < rac.childNodes.length; i++) {
			var com = rac.childNodes[i];
//			var id = com.getElementsByTagName("id")[0].childNodes[0].nodeValue;
			var date = com.getElementsByTagName("date")[0].childNodes[0].nodeValue;
			var contenu =  com.getElementsByTagName("cont")[0].childNodes[0].nodeValue;
			var idU =  com.getElementsByTagName("idU")[0].childNodes[0].nodeValue;
			var pseudo =  com.getElementsByTagName("pseudo")[0].childNodes[0].nodeValue;
			
			var li = document.createElement("li");
			var span1 = document.createElement("span");
			var lien = document.createElement("a");
			var span2 = document.createElement("span");
			var span3 =document.createElement("span");
			
			li.setAttribute("class","com");
			
			lien.setAttribute("href","Profile?id="+idU);
			lien.textContent = pseudo+" ";
			
			span1.setAttribute("class","pseu");
			span1.appendChild(lien);
			
			span2.setAttribute("class","commentaire");
			span2.textContent = ": "+contenu+" ";
			
			span3.setAttribute("class","date_com");
			span3.textContent = " "+date;
			
			li.appendChild(span1);
			li.appendChild(span2);
			li.appendChild(span3);
			
			list.appendChild(li);
		}

	}
	
	//setInterval(chargerComm,10000);
})();