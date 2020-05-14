<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cadastro de Usuário</title>
<link rel="stylesheet" href="resources/css/cadastro.css">

<!-- Adicionando JQuery -->
<script src="https://code.jquery.com/jquery-3.4.1.min.js"
	integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
	crossorigin="anonymous"></script>


</head>
<body>
	<a href="acesso-liberado.jsp"><img alt="Inicio"
		src="resources/img/inicio.png" height="30px" width="30px"></a>
	<a href="index.jsp"><img alt="Inicio" src="resources/img/sair.png"
		height="30px" width="30px"></a>
	<center>
		<h1>Cadastro de Usuário</h1>
		<h3 style="color: orange">${msg}</h3>
	</center>


	<form action="salvarUsuario" method="post" style="position: center"
		id="formUser" enctype="multipart/form-data">
		<ul class="form-style-1">
			<li>
				<table>
					<tr>
						<td>Código</td>
						<td><input type="text" id="id" name="id" value="${user.id}"
							class="field-long" placeholder="Não Obrigatório"></td>

						<td>Logradouro:</td>
						<td><input type="text" id="rua" name="rua"
							value="${user.rua}"></td>
					</tr>

					<tr>
						<td>Login:</td>
						<td><input type="text" id="login" name="login"
							value="${user.login}" class="field-long"></td>

						<td>Bairro:</td>
						<td><input type="text" id="bairro" name="bairro"
							value="${user.bairro}"></td>
					</tr>

					<tr>
						<td>Senha:</td>
						<td><input type="password" id="senha" name="senha"
							value="${user.senha}" class="field-long"></td>

						<td>Cidade:</td>
						<td><input type="text" id="cidade" name="cidade"
							value="${user.cidade}"></td>
					</tr>

					<tr>
						<td>Nome:</td>
						<td><input type="text" id="nome" name="nome"
							value="${user.nome}"></td>

						<td>UF:</td>
						<td><input type="text" id="uf" name="uf"
							value="${user.estado}"></td>
					</tr>

					<tr>
						<td>Telefone:</td>
						<td><input type="text" id="telefone" name="telefone"
							value="${user.telefone}" placeholder="99999-9999"></td>

						<td>IBGE:</td>
						<td><input type="text" id="ibge" name="ibge"
							value="${user.ibge}"></td>
					</tr>

					<tr>
						<td>CEP:</td>
						<td><input type="text" id="cep" name="cep"
							onblur="consultaCep();" value="${user.cep}"
							placeholder="00000-000"></td>
					</tr>

					<tr>
						<td>Foto:</td>
						<td><input type="file" name="foto"><input type="text"
							name="fotoTemp" style="display: none" readonly="readonly"
							value="${user.fotoBase64}" /> <input type="text"
							name="contentTypeTempImg" style="display: none"
							readonly="readonly" value="${user.contentType}" /></td>
					</tr>

					<tr>
						<td>Currículo:</td>
						<td><input type="file" name="curriculo"> <input
							type="text" name="curriculoTemp" style="display: none"
							readonly="readonly" value="${user.curriculoBase64}" /> <input
							type="text" name="contentTypeTempPdf" style="display: none"
							readonly="readonly" value="${user.contentTypeCurriculo}" /></td>
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
				<th align="center">Id</th>
				<th align="center">Login</th>
				<th align="center">Nome</th>
				<th align="center">Fones</th>
				<th align="center">Editar</th>
				<th align="center">Excluir</th>


			</tr>

			<c:forEach items="${usuarios}" var="user">
				<tr>

					<td style="width: 150px" align="center"><a
						href="salvarUsuario?acao=download&tipo=imagem&user=${user.id}"><img
							src='<c:out value="${user.tempFotoUser}"></c:out>' width="35px"
							height="35px"> </a></td>

					<td style="width: 150px" align="center"><a
						href="salvarUsuario?acao=download&tipo=curriculo&user=${user.id}">Currículo</a></td>

					<td style="width: 150px" align="center"><c:out
							value="${user.nome}"></c:out></td>

					<td style="width: 150px" align="center"><a
						href="salvarTelefones?acao=addFone&user=${user.id}"><img
							alt="Telefones" src="resources/img/phone.png" height="20px"
							width="20px" title="Telefone"></a></td>

					<td style="width: 150px" align="center"><a
						href="salvarUsuario?acao=editar&user=${user.id}"><img
							alt="Editar" src="resources/img/edit.png" height="20px"
							width="20px" title="Editar"></a></td>


					<td style="width: 150px" align="center"><a
						href="salvarUsuario?acao=delete&user=${user.id}"><img
							alt="Excluir" width="20px" height="20px"
							src="resources/img/excluir.png" title="Excluir"></a></td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<script type="text/javascript">
		function limpa_formulário_cep() {
			// Limpa valores do formulário de cep.
			$("#cep").val("");
			$("#rua").val("");
			$("#bairro").val("");
			$("#cidade").val("");
			$("#uf").val("");
			$("#ibge").val("");
		}

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

		function consultaCep() {
			var cep = $("#cep").val().replace(/\D/g, '');

			//Consulta o webservice viacep.com.br/
			$.getJSON("https://viacep.com.br/ws/" + cep + "/json/?callback=?",
					function(dados) {

						if (!("erro" in dados)) {
							//Atualiza os campos com os valores da consulta.
							$("#rua").val(dados.logradouro);
							$("#bairro").val(dados.bairro);
							$("#cidade").val(dados.localidade);
							$("#uf").val(dados.uf);
							$("#ibge").val(dados.ibge);

						} //end if.
						else {
							limpa_formulário_cep();
							alert("CEP não encontrado.");
						}
					});
		}
	</script>
</body>
</html>