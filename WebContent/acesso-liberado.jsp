 <jsp:useBean id="calcula"  class="beans.BeanCursoJSP" type="beans.BeanCursoJSP" scope="page"/> 
  <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<a href="index.jsp" ><img alt="Inicio" src="resources/img/sair.png" height="30px" width="30px"></a>

	<jsp:setProperty property="*" name="calcula"/>
	<br>
	<h3>Seja bem vindo ao sistema</h3>

	<a href="salvarUsuario?acao=listarTodos"><img alt="Logar"
		src="resources/img/user.png" height="100px" width="100px"
		title="Logar"></a>

	<a href="salvarProduto?acao=listarTodos"> <img width="100px"
		height="100px" title="Cadastro de Produto" alt="Cadastro de Produto"
		src="resources/img/icone-produto.png">
	</a>
	
</body>
</html>

