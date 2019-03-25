<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=utf-8" 
    pageEncoding="utf-8"%> 
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>Detalles de la pelicula</title>
<spring:url value="/resources" var="urlPublic" />
<spring:url value="/" var="urlRoot" />
<spring:url value="/admin/comentario" var="urlSave" />
<link href="${urlRoot}webjars/bootstrap/4.1.3/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<link href="/css/detail.css" rel="stylesheet">

<body>

	<jsp:include page="includes/Menu.jsp"></jsp:include>

	<div class="container theme-showcase" role="main">
		<div class="row">

			<div class="col-12 text-center">
				<h2>${movie.title}</h2>
			</div>
			<label class="col-12 text-left date">Fecha de estreno: <fmt:formatDate
					value="${movie.releaseDate}" pattern="dd-MM-yyyy" /></label> <br /> <br />
			<div class="row">
				<div class="col-12 col-md-6">
					<img class="img-rounded" src="${urlPublic}/images/${movie.poster}"
						alt="${movie.title }" width="100%"
						onerror="this.src='${urlPublic }/images/sinImagen.png'">
				</div>
				<div class="col-12 col-md-6">
					<h3 class="col-12">SINOPSIS</h3>
					<p class="col-12">${movie.synopsis}</p>
				</div>
			</div>

			<hr class="col-12" style="margin-top: 1rem">
			<!-- Reseña -->
			<div class="col-12">
				<h3 class="}col-12">Rese&ntilde;a</h3>
				<p class="col-12">${movie.review }</p>

			</div>

			<!-- Comentarios -->
			<div class="col-12">
				<h4 class="col-12">Comentarios</h4>
				<div class="col-12 item">
					<c:forEach var="comment" items="${movie.comments}">
						<div class="col-12 border">
							<div class="row">
								<div class="col-7">
									<label><b>${comment.email}</b></label>
								</div>
								<div class="col-5 date text-right">
									<fmt:formatDate value="${comment.date}" pattern="dd-MM-yyyy" />
								</div>
							</div>
							<hr class="col-11">

							<div class="col-12">
								<p>${comment.review}</p>
							</div>
						</div>
						<br />
					</c:forEach>
				</div>

				<c:choose>
					<c:when test="${userAdmin!=null}">
						<div class="col-12 text-center item">
							<form:form class="col-12 border" action="${urlSave}"
								method="post" enctype="multipart/form-data"
								modelAttribute="commentNew">
								<div class="form-group">
									<form:hidden path="movie.id" />
									<form:textarea type="text" class="form-control col-12"
										path="review" id="review" required="required"></form:textarea>
								</div>

								<button type="submit">Agregar</button>
							</form:form>
						</div>
					</c:when>
					<c:otherwise>
						<div class="col-12 text-center item">
							<label class="col-12">Se necesita ingresar para comentar.</label> 
							<a class="btn" href="${urlRoot }entrar">Ingresar</a>
						</div>
					</c:otherwise>
				</c:choose>

			</div>
		</div>
		<script src="${urlRoot}webjars/jquery/3.3.1/jquery.min.js"></script>
		<script src="${urlRoot}webjars/bootstrap/4.1.3/js/bootstrap.min.js"></script>
	</div>
</body>
</html>