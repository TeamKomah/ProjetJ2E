/**
 * 
 */
(function(){
	var ami = document.getElementsByClassName("nonLu");
	var list = document.getElementById("list_msg");
	var btn = document.getElementById("btn");
	var msg = document.getElementById("zn_msg");
	var exp = msg.getAttribute("data-exp");
	var recep = msg.getAttribute("data-recep");
	
	function envoiMsg(){
		
		var url = "EnvoiMessage?exp="+escape(exp)+"&cont="+escape(msg.value)+"&recep="+escape(recep);
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
	
	btn.addEventListener("click", function(){
		var li = document.createElement("li");
		var div1 = document.createElement("div");
		var div2 = document.createElement("div");
		li.setAttribute("class","env");
		div1.setAttribute("class","heure");
		div2.setAttribute("class","cont");
		div1.textContent = "A l'instant"
		div2.textContent = msg.value;
		li.appendChild(div1);
		li.appendChild(div2);
		list.appendChild(li);
		envoiMsg();
	});
	
	//fonction qui recharge la conversation courante
	function chargerConversation(){
	
		var url = "ChargerConversation?exp="+escape(exp)+"&recep="+escape(recep);
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
			var msg = rac.childNodes[i];
			var ex = msg.getElementsByTagName("expediteur")[0].childNodes[0].nodeValue;
			var heure = msg.getElementsByTagName("heure")[0].childNodes[0].nodeValue;
			var contenu =  msg.getElementsByTagName("cont")[0].childNodes[0].nodeValue;
			var li = document.createElement("li");
			var div1 = document.createElement("div");
			var div2 = document.createElement("div");
			if (ex == exp) {
				li.setAttribute("class","env");
			} else {
				li.setAttribute("class","rec");
			}
			
			div1.setAttribute("class","heure");
			div2.setAttribute("class","cont");
			div1.textContent = heure;
			div2.textContent = contenu;
			li.appendChild(div1);
			li.appendChild(div2);
			list.appendChild(li);
		}

	}
	
	setInterval(chargerConversation, 5000);
	
})();

