<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cadastro de Usuário</title>
<link rel="stylesheet" href="resources/css/cadastro.css">
</head>
<body>
	<center>
		<h1>Cadastro de Usuário</h1>
	</center>

	<form action="salvarUsuario" method="post" style="position: center">
		<ul class="form-style-1">
			<li>
				<table>
					<tr>
						<td>Código</td>
						<td><input type="text" id="id" name="id" value="${user.id}"
							class="field-long"></td>
					</tr>
					<tr>
						<td>Login:</td>
						<td><input type="text" id="Login" name="login"
							value="${user.login}" class="field-long"></td>
					</tr>
					<tr>
						<td>Senha:</td>
						<td><input type="password" id="Senha" name="senha"
							value="${user.senha}" class="field-long"></td>
					</tr>
					<tr>
						<td></td>
						<td><input type="submit" value="Salvar"></td>
					</tr>
				</table>
			</li>
		</ul>
	</form>

	<div class="container">

		<table class="responsive-table">

			<tr>
				<th>Login</th>
				<th>Senha</th>
				<th>Excluir</th>
				<th>Editar</th>
			</tr>

			<c:forEach items="${usuarios}" var="user">
				<tr>

					<td style="width: 150px"><c:out value="${user.login}"></c:out></td>

					<td><c:out value="${user.senha}"></c:out></td>

					<td><a href="salvarUsuario?acao=delete&user=${user.login}"><img
							alt="Excluir" width="20px" height="20px"
							src="resources/img/excluir.png" title="Excluir"></a></td>

					<td><a href="salvarUsuario?acao=editar&user=${user.login}"><img
							alt="Editar" src="resources/img/edit.png" height="20px"
							width="20px" title="Editar"></a></td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>