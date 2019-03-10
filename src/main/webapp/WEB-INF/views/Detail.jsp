<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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

<body>

	<jsp:include page="includes/Menu.jsp"></jsp:include>

	<div class="container theme-showcase" role="main">
		<h1>Informaci&oacute;n de la pelicula</h1>

		<div class="container marketing">

			<div class="page-header">
				<h2>${movie.title}</h2>
			</div>
			<div class="row">
				<div class="col-sm-5">
					<p class="text-center">
						<img class="img-rounded" src="${urlPublic}/images/${movie.poster}"
							alt="Generic placeholder image" width="155" height="220">
					</p>
				</div>
				<div class="col-sm-6">
					<div class="panel panel-default">
						<div class="panel-heading">
							<h3 class="panel-title">DETALLES</h3>
						</div>
						<div class="panel-body">
							<p>
								Título : ${movie.title} <br> Fecha Estreno:
								<fmt:formatDate value="${movie.releaseDate}"
									pattern="dd-MM-yyyy" />
							</p>

						</div>
					</div>
				</div>
			</div>

			<!-- Sinopsis y reseña -->
			<div class="row">
				<div class="col-sm-5">
					<div class="panel panel-default">
						<div class="panel-heading">
							<h3 class="panel-title">SINOPSIS</h3>
						</div>
						<div class="panel-body">
							<p>${movie.synopsis}</p>
						</div>
					</div>
				</div>

			</div>

			<div class="row">
				<div class="col-sm-5">
					<div class="panel panel-default">
						<div class="panel-heading">
							<h3 class="panel-title">Rese&ntilde;a</h3>
						</div>
						<div class="panel-body">
							<p>${movie.review }</p>
						</div>
					</div>
				</div>

			</div>

			<!-- Comentarios -->

			<div class="page-header">
				<h3>Comentarios</h3>
			</div>

			<div class="col-12">
				<c:forEach var="comment" items="${movie.comments}">
					<div class="col-12">
						<div class="col-12">
							<div class="col-6">
								<label><b>${comment.email}</b></label>
							</div>
							<div class="col-4">
								<fmt:formatDate value="${comment.date}" pattern="dd-MM-yyyy" />
							</div>
						</div>

						<div class="col-12">
							<p>${comment.review}</p>
						</div>
					</div>
					<br />
				</c:forEach>
			</div>

			<div class="col-12">
				<form:form action="${urlSave}" method="post" enctype="multipart/form-data" modelAttribute="commentNew">
					<div class="col-sm-3">
						<div class="form-group">
							<form:hidden path="movie.id"/> 
							<form:textarea type="text" class="form-control" path="review"
								id="review" required="required"></form:textarea>
						</div>
					</div>
					<button type="submit" class="btn btn-danger">Agregar</button>
				</form:form>
			</div>

		</div>
		<script src="${urlroot}webjars/jquery/3.3.1/jquery.min.js"></script>
		<script src="${urlroot}webjars/bootstrap/4.1.3/js/bootstrap.min.js"></script>
	</div>
</body>
</html>