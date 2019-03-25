<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=utf-8" 
    pageEncoding="utf-8"%> 
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">

<title>Listado de Peliculas</title>
<spring:url value="/" var="urlRoot" />
<spring:url value="/resources" var="urlPublic" />

<spring:url value="/peliculas/nueva" var="urlCreate" />
<spring:url value="/peliculas/editar" var="urlEdit" />
<spring:url value="/peliculas/eliminar" var="urlDelete" />
<link href="${urlRoot}webjars/bootstrap/4.1.3/css/bootstrap.min.css"
	rel="stylesheet">

</head>

<body>

	<jsp:include page="../includes/Menu.jsp"></jsp:include>

	<div class="container theme-showcase" role="main">

		<h3>Listado de Peliculas</h3>
		<c:if test="${message != null }">
			<div class='alert alert-success' role="alert">${message}</div>
		</c:if>

		<a href="${urlCreate}" class="btn btn-success" role="button"
			title="Nueva Pelicula">Nueva</a><br> <br>

		<div class="table-responsive">
			<table class="table table-hover table-striped table-bordered">
				<thead>
					<tr>
						<th>Titulo</th>
						<th>Fecha Estreno</th>
						<th>Opciones</th>
					</tr>
				</thead>

				<tbody>

					<c:forEach items="${movies}" var="movie">

						<tr>
							<td>${movie.title }</td>
							<td><fmt:formatDate value="${movie.releaseDate}"
									pattern="dd-MM-yyyy" /></td>
							<td><a href="${urlEdit }/${movie.id}"
								class="btn btn-success btn-sm" role="button" title="Editar">Editar</a>
								<a href="${urlDelete }/${movie.id}"
								onclick='return confirm("¿Estas seguro?")'
								class="btn btn-danger btn-sm" role="button" title="Eliminar">Eliminar</span></a></td>
						</tr>
					</c:forEach>
				</tbody>

			</table>
		</div>


	</div>
	<script src="${urlRoot}webjars/jquery/3.3.1/jquery.min.js"></script>
	<script src="${urlRoot}/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>