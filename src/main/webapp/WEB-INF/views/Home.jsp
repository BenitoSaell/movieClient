<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Inicio</title>
<spring:url value="/resources" var="urlPublic" />
<spring:url value="/" var="urlRoot" />
<link href="webjars/bootstrap/4.1.3/css/bootstrap.min.css"
	rel="stylesheet">
<link href="/css/home.css" rel="stylesheet">
</head>
<body>
	<jsp:include page="includes/Menu.jsp"></jsp:include>
	<div class="container theme-showcase" role="main">
		<img alt="Portada" src="${urlRoot}images/portada.jpeg" width="100%">
		<h1 class="col-12 text-center">Bienvenido</h1>


		<div class="row">
			<h3 class="col-12">Lista de peliculas</h3>
			<br />
			<c:if test="${message != null }">
				<div class='alert alert-success' role="alert">${message}</div>
			</c:if>
			<c:forEach items="${movies}" var="movie">
				<div class="col-12 col-md-6 col-lg-6 item">
					<div class="border">
						<div class="col-12">
							<img class="img-rounded"
								src="${urlPublic}/images/${movie.poster}" alt="${movie.title}"
								width="100%"
								onerror="this.src='${urlRoot }images/sinImagen.png'">
						</div>
						<div class="row">
							<div class="col-8 text-left">
								<h3 class="col-12">${movie.title}</h3>
							</div>
							<div class="col-4 text-right">
								<h4 class="col-12">
									<fmt:formatDate value="${movie.releaseDate}"
										pattern="dd-MM-yyyy" />
								</h4>
							</div>
						</div>

						<p class="col-12">${movie.synopsis}</p>
						<div class="col-12 text-center">
							<a class="btn show" href="pelicula/${movie.id }" role="button">Ver&raquo;</a>
						</div>
					</div>
				</div>

			</c:forEach>


		</div>
		<script src="${urlRoot}webjars/jquery/3.3.1/jquery.min.js"></script>
		<script src="${urlRoot}webjars/bootstrap/4.1.3/js/bootstrap.min.js"></script>

	</div>
</body>
</html>