<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Index</title>
</head>
<body>
<div id="formConnect">
	<form action="Connection" method="post">
		<label for="pseudo">Pseudo: <input type=text name="pseudo" placeholder="pseudo..." id="pseudo"/></label></br>
		<label for="mdp">Mot de passe: <input type="password" name="mdp" placeholder="mot de passe..." id="mdp"/></label></br>
		<input type="submit" value="Connection"> 
	</form>
</div>
<h4>Si vous n'avez pas de compte inscrivez-vous.</h4>
<div id="formInscrip">
	<form action="Inscription" method="post">
		<label for="prenom">Prenom: <input type="text" name="prenom" id="prenom" placeholder="nom"/></label></br>
		<label for="nom">Nom: <input type="text" name="nom" id="prenom" placeholder="prenom"/></label></br>
		<label for="dateN">Anniversaire: <input type="text" name="dateN" id="dateN" placeholder="jj-mm-aaaa"/></label></br>
		<label for="email">Email: <input type="text" name="email" id="email" placeholder="email"/></label></br>
		<label for="Ipseudo">Pseudo: <input type=text name="pseudo" placeholder="pseudo" id="Ipseudo" placeholder="pseudo"/></label></br>
		<label for="ICpseudo">Confirme votre pseudo: <input type=text name="Cpseudo" placeholder="confirme votre pseudo" id="ICpseudo" placeholder="confirme votre pseudo"/></label></br>
		<label for="Imdp">Mot de passe: <input type="password" name="mdp" placeholder="mot de passe" id="Imdp" placeholder="mot de passe"></label><br>
		<label for="ICmdp">confirme votre mot de passe: <input type="password" name="Cmdp" placeholder="confirme votre mot de passe" id="ICmdp" placeholder="confirme votre mot de passe"/></label></br>
		<input type="submit" value="Inscription">
	</form>
</div>
</body>
</html>