<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Artistas</title>
<link
	href="<%=request.getContextPath()%>/resources/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="<%=request.getContextPath()%>/resources/css/sticky-footer-navbar.css"
	rel="stylesheet">
</head>

<body>

	<jsp:include page="/resources/templates/navbar.jsp" />

	<!-- Begin page content -->
	<div class="container">
		<div class="page-header">
				<h1>Detalhes do filme ${item.titulo}</h1>
		</div>

		<div>
			<ul class="list-group">
				<li class="list-group-item">Código: ${item.codFilme}</li>
				<li class="list-group-item">Titulo: ${item.titulo}</li>
				<li class="list-group-item">Duracao: ${item.duracao}</li>
				<li class="list-group-item">Ano: ${item.ano}</li>
				<li class="list-group-item">Gasto total em cahe:<fmt:setLocale value="pt_BR"/><fmt:formatNumber type="currency" value="${item.cacheTotal}"/></li>
			</ul>
		</div>
	
		<div>
			<a href="<%=request.getContextPath()%>/filme/pesquisar" class="btn btn-primary">Nova Pesquisa</a>
		</div>

	</div>



	<jsp:include page="/resources/templates/rodape.jsp"></jsp:include>

	<!-- Core JS -->
	<script src="<%=request.getContextPath()%>/resources/js/jquery.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/resources/js/bootstrap.min.js"></script>
</body>
</html>
