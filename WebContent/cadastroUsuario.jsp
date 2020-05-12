<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cadastro de Usu�rio</title>
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
						<td>CEP:</td>
						<td><input type="text" id="cep" name="cep"
							onblur="consultaCep();"></td>
					</tr>

					<tr>
						<td>Logradouro:</td>
						<td><input type="text" id="rua" name="rua"></td>
					</tr>

					<tr>
						<td>Bairro:</td>
						<td><input type="text" id="bairro" name="bairro"></td>
					</tr>
					
					<tr>
						<td>N�mero:</td>
						<td><input type="text" id="numero" name="numero"></td>
					</tr>
					
					<tr>
						<td>Complemento:</td>
						<td><input type="text" id="complemento" name="complemento"></td>
					</tr>

					<tr>
						<td>Cidade:</td>
						<td><input type="text" id="cidade" name="cidade"></td>
					</tr>

					<tr>
						<td>UF:</td>
						<td><input type="text" id="uf" name="uf"></td>
					</tr>

					<tr>
						<td>IBGE:</td>
						<td><input type="text" id="ibge" name="ibge"></td>
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

	function limpa_formul�rio_cep() {
        // Limpa valores do formul�rio de cep.
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
							limpa_formul�rio_cep();
							alert("CEP n�o encontrado.");
						}
					});
		}

	</script>
</body>
</html>