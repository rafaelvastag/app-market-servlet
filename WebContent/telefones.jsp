<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cadastro de Telefones</title>
<link rel="stylesheet" href="resources/css/cadastro.css">


</head>
<body>
	<a href="acesso-liberado.jsp"><img alt="Inicio"
		src="resources/img/inicio.png" height="30px" width="30px"></a>
	<a href="index.jsp"><img alt="Inicio" src="resources/img/sair.png"
		height="30px" width="30px"></a>
	<center>
		<h1>Cadastro de Telefones</h1>
		<h3 style="color: orange">${msg}</h3>
	</center>


	<form action="salvarTelefones" method="post" style="position: center"
		id="formUser">
		<ul class="form-style-1">
			<li>
				<table>
					<tr>
						<td>Código:</td>
						<td><input type="text" id="id" name="id"
							value="${userEscolhido.id}" class="field-long"
							readonly="readonly"></td>
					</tr>
					<tr>
						<td>Nome:</td>
						<td><input type="text" id="nome" name="nome"
							value="${userEscolhido.nome}" class="field-long"></td>
					</tr>

					<tr>
						<td>Número:</td>
						<td><input type="text" id="numero" name="numero"
							value="${telefone}" class="field-long"></td>
						<td><select id="tipo" name="tipo">
								<option>Celular</option>
								<option>Comercial</option>
								<option>Residencial</option>
						</select></td>
					</tr>

					<tr>
						<td></td>
						<td><input type="submit" value="Salvar"
							onclick="document.getElementById('formUser').action = 'salvarTelefones?acao=salvar'; return validarCampos() ? true : false">
						</td>
					</tr>
				</table>
			</li>
		</ul>
	</form>

	<div class="container">

		<table class="responsive-table">

			<tr>
				<th align="center">Usuário ID</th>
				<th align="center">Número</th>
				<th align="center">Tipo</th>
				<th align="center">Excluir</th>
			</tr>

			<c:forEach items="${telefones}" var="fone">
				<tr>

					<td style="width: 150px" align="center"><c:out
							value="${fone.id}"></c:out></td>

					<td style="width: 150px" align="center"><c:out
							value="${fone.numero}"></c:out></td>

					<td style="width: 150px" align="center"><c:out
							value="${fone.tipo}"></c:out></td>

					<td style="width: 150px" align="center"><a
						href="salvarTelefones?acao=delete&numero=${fone.id}&user=${fone.usuario}"><img
							alt="Excluir" width="20px" height="20px"
							src="resources/img/excluir.png" title="Excluir"></a></td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<script type="text/javascript">
		function validarCampos() {
			if (document.getElementById("numero").value == '') {
				alert('Informe o Número');
				return false;
			}
			return true;
		}
	</script>
</body>
</html>