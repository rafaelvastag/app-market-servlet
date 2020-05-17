
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="taglib" uri="WEB-INF/tag.tld"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html"
	;charset="ISO-8859-1">
<title>Insert title here</title>

<link rel="stylesheet" href="resources/css/estilo.css">

</head>
<body>
	<div class="login-page">
		<center>
			<h3>Projeto - JSP + Servlet + JBDC</h3>
		</center>
		<center>
			<span>Usuário: admin, Senha: admin</span>
		</center>
		<div class="form">
			<form action="LoginServlet" method="get" class="login-form">
				Login: <input type="text" id="login" name="login"> <br>
				Senha: <input type="password" id="senha" name="senha"> <br>
				<button type="submit" value="Login" style="border: thick;">Logar</button>
			</form>
		</div>
		<center>
			<h3>Rafael Vastag - Analista Desenvolvedor</h3>
			<h3>
				<a href="https://www.linkedin.com/in/rafaelvastag/"><img
					alt="LinkedIn" src="resources/img/linkedIn.png" width="30px"
					height="30px"></a>
			</h3>
		</center>
	</div>
</body>
</html>