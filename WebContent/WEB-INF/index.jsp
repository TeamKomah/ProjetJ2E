<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Index</title>
<link rel="stylesheet" type="text/css" href="css/main.css"/>
<link rel="stylesheet" type="text/css" href="css/style.css"/>
</head>
<body>
<div class="tete">
<h1>HELP ME</h1>
</div>
<div class="right box">
<table>
<div id="formConnect">
	<form action="Connection" method="post" class="form">
		<tr>
		<td> Pseudo: </td>
		<td><input type=text name="pseudo" placeholder="pseudo..." id="pseudo"/></td>
		</tr>
		<tr>
		<td> Mot de passe:</td>
		<td> <input type="password" name="mdp" placeholder="mot de passe..." id="mdp"/></td>
		</tr>
		<tr><td></td><td><input type="submit" value="Connection"></td></tr> 
	</form>
</div>
</table>
<h4>Si vous n'avez pas de compte inscrivez-vous.</h4>
<table>
<div id="formInscrip">
	<form action="Inscription" method="post" >
		<tr>
		<td>Prenom: </td>
		<td><input type="text" name="prenom" id="prenom" placeholder="nom"/></td>
		</tr>
		<tr>
		<td>Nom: </td>
		<td><input type="text" name="nom" id="prenom" placeholder="prenom"/></td>
		</tr>
		<tr>
		<td>Anniversaire:</td> 
		<td><input type="text" name="dateN" id="dateN" placeholder="jj-mm-aaaa"/></td>
		</tr>
		<tr>
		<td>Email:</td> 
		<td><input type="text" name="email" id="email" placeholder="email"/></td>
		</tr>
		<tr>
		<td>Pseudo: </td>
		<td><input type=text name="pseudo" placeholder="pseudo" id="Ipseudo" placeholder="pseudo"/></td>
		</tr>
		<tr>
		<td>Confirme votre pseudo: </td>
		<td><input type=text name="Cpseudo" placeholder="confirme votre pseudo" id="ICpseudo" placeholder="confirme votre pseudo"/></td>
		</tr>
		<tr>
		<td>Mot de passe:</td> 
		<td><input type="password" name="mdp" placeholder="mot de passe" id="Imdp" placeholder="mot de passe"></td>
		</tr>
		<tr>
		<td>confirme votre mot de passe:</td> 
		<td><input type="password" name="Cmdp" placeholder="confirme votre mot de passe" id="ICmdp" placeholder="confirme votre mot de passe"/></td>
		</tr>
		<tr>
		<td></td>
		<td><input type="submit" value="Inscription"></td>
		</tr>
	</form>
</div>
</table>
</div>
</body>
</html>