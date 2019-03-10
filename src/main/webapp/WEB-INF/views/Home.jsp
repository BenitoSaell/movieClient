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
</head>
<body>
	<jsp:include page="includes/Menu.jsp"></jsp:include>
	<div class="container theme-showcase" role="main">
		<h1>Bienvenido</h1>
		<div class="container marketing">

			<div class="row">

				<c:forEach items="${movies}" var="movie">
					<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
						<img class="img-rounded" src="${urlPublic}/images/${movie.poster}"
							alt="Generic placeholder image" width="150" height="200">
						<h4>${movie.title}</h4>
						<p>
							${movie.synopsis}
						</p>
						<p>
							<a class="btn btn-lg btn-primary" href="pelicula/${movie.id }"
								role="button">Ver&raquo;</a>
						</p>
					</div>
				</c:forEach>

			</div>
		</div>
		<script src="${urlRoot}webjars/jquery/3.3.1/jquery.min.js"></script>
        <script src="${urlRoot}webjars/bootstrap/4.1.3/js/bootstrap.min.js"></script>

	</div>
</body>
</html>