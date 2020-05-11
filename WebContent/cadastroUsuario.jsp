<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cadastro de Usu�rio</title>
<link rel="stylesheet" href="resources/css/cadastro.css">
</head>
<body>
	<center>
		<h1>Cadastro de Usu�rio</h1>
		<h3 style="color: orange">${msg}</h3>
	</center>


	<form action="salvarUsuario" method="post" style="position: center"
		id="formUser">
		<ul class="form-style-1">
			<li>
				<table>
					<tr>
						<td>C�digo</td>
						<td><input type="text" id="id" name="id" value="${user.id}"
							class="field-long"></td>
					</tr>
					<tr>
						<td>Login:</td>
						<td><input type="text" id="login" name="login"
							value="${user.login}" class="field-long"></td>
					</tr>
					<tr>
						<td>Senha:</td>
						<td><input type="password" id="senha" name="senha"
							value="${user.senha}" class="field-long"></td>
					</tr>
					<tr>
						<td>Nome:</td>
						<td><input type="text" id="nome" name="nome"
							value="${user.nome}"></td>
					</tr>
					<tr>
						<td>Telefone:</td>
						<td><input type="text" id="telefone" name="telefone"
							value="${user.telefone}"></td>
					</tr>
					<tr>
						<td></td>
						<td><input type="submit" value="Salvar"
							onclick="document.getElementById('formUser').action = 'salvarUsuario?acao=salvar'; return validarCampos() ? true : false">
							<input type="submit" value="Cancelar"
							onclick="document.getElementById('formUser').action = 'salvarUsuario?acao=reset' "></td>
					</tr>
				</table>
			</li>
		</ul>
	</form>

	<div class="container">

		<table class="responsive-table">

			<tr>
				<th>Id</th>
				<th>Login</th>
				<th>Nome</th>
				<th>Telefone</th>
				<th>Excluir</th>
				<th>Editar</th>
			</tr>

			<c:forEach items="${usuarios}" var="user">
				<tr>

					<td style="width: 150px" align="center"><c:out
							value="${user.id}"></c:out></td>

					<td style="width: 150px" align="center"><c:out
							value="${user.login}"></c:out></td>

					<td style="width: 150px" align="center"><c:out
							value="${user.nome}"></c:out></td>

					<td style="width: 150px" align="center"><c:out
							value="${user.telefone}"></c:out></td>

					<td style="width: 150px" align="center"><a
						href="salvarUsuario?acao=delete&user=${user.id}"><img
							alt="Excluir" width="20px" height="20px"
							src="resources/img/excluir.png" title="Excluir"></a></td>

					<td style="width: 150px" align="center"><a
						href="salvarUsuario?acao=editar&user=${user.id}"><img
							alt="Editar" src="resources/img/edit.png" height="20px"
							width="20px" title="Editar"></a></td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<script type="text/javascript">
		function validarCampos() {
			if (document.getElementById("login").value == '') {
				alert('Informe o Login');
				return false;
			} else if (document.getElementById("senha").value == '') {
				alert('Informe a Senha');
				return false;
			} else if (document.getElementById("nome").value == '') {
				alert('Informe o Nome');
				return false;
			} else if (document.getElementById("telefone").value == '') {
				alert('Informe o Telefone');
				return false;
			}
			return true;
		}
	</script>
</body>
</html>