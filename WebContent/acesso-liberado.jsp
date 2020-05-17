
<jsp:useBean id="calcula" class="beans.BeanCursoJSP"
	type="beans.BeanCursoJSP" scope="page" />
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Inicio</title>
</head>
<body>
	<a href="index.jsp"><img alt="Inicio" src="resources/img/sair.png"
		height="30px" width="30px"></a>

	<jsp:setProperty property="*" name="calcula" />
	<br>

	<center>
		<h2>Acesso Liberado ao Sistema</h2>
		<table>

			<tr>
				<td><a href="salvarUsuario?acao=listarTodos"><img
						alt="Logar" src="resources/img/user.png" height="100px"
						width="100px" title="Logar"></a></td>

				<td><a href="salvarProduto?acao=listarTodos"> <img
						width="100px" height="100px" title="Cadastro de Produto"
						alt="Cadastro de Produto" src="resources/img/icone-produto.png"></a></td>

			</tr>
			<td>Cad. Usuário</td>
			<td>Cad. Produto</td>
			<tr>

			</tr>
		</table>

	</center>
</body>
</html>

