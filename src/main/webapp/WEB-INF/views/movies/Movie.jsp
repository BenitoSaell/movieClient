<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<title>Pelicula</title>
<spring:url value="/" var="urlRoot" />
<spring:url value="/resources" var="urlPublic" />

<spring:url value="/peliculas/guardar" var="urlSave" />
<link href="${urlRoot}webjars/bootstrap/4.1.3/css/bootstrap.min.css"
	rel="stylesheet">
<link href="/css/movie.css" rel="stylesheet">
</head>
<body>
	<!-- Inyeccion del menu -->
	<jsp:include page="../includes/Menu.jsp"></jsp:include>
	
	<!-- Pagina de edicion y creacion de peliculas -->
	<div class="container theme-showcase" role="main">

		<div class="col-12 text-center">
			<h3>Datos de la Pelicula</h3>
		</div>
		<br class="col-12"/>
		<!-- Seccion de errores -->
		<div class="col-12">
			<spring:hasBindErrors name="movie">
				<div class='alert alert-danger' role='alert'>
					Por favor corrija los siguientes errores:
					<ul>
						<c:forEach var="error" items="${errors.allErrors}">
							<li><spring:message message="${error}" /></li>
						</c:forEach>
					</ul>
				</div>
			</spring:hasBindErrors>
		</div>
		<!-- Seccion de formulario para crear o editar pelicula -->
		<div class="col-12">
			<form:form action="${urlSave}" method="post"
				enctype="multipart/form-data" modelAttribute="movie">
				<div class="col-12">
						<div class="row">
							<div class="col-sm-3">
								<div class="form-group">
									<img class="img-rounded"
										src="${urlPublic}/images/${movie.poster}"
										title="Imagen actual de la pelicula" width="300" height="200" onerror="this.src='${urlPublic }/images/sinImagen.png'">
								</div>

							</div>
						</div>
				</div>
				<div class="row">
					<div class="col-12 col-md-6">
						<div class="col-12">
							<div class="form-group">
								<label for="title">Título:</label>
								<form:hidden path="id" />
								<form:input type="text" class="form-control col-12" path="title"
									id="title" required="required" />
							</div>
						</div>
						<div class="col-12">
							<div class="form-group">
								<label for="releaseDate">Fecha Estreno:</label>
								<form:input type="text" class="form-control" path="releaseDate"
									id="releaseDate" required="required" />
							</div>
						</div>

						<div class="col-12">
							<div class="form-group">
								<label for="fileImage">Imagen:</label>
								<form:hidden path="poster" />
								<input type="file" id="fileImage" name="fileImage" />
							</div>
						</div>

					</div>
					<div class="col-12 col-md-6">
						<div class="col-12">
							<div class="form-group">
								<label for="synopsis">Sinopsis:</label>
								<form:textarea class="form-control col-12" rows="5"
									path="synopsis" id="synopsis"></form:textarea>
							</div>
						</div>
					</div>
				</div>

				<hr class="col-12" />

				<div class="row">
					<div class="col-12">
						<div class="form-group">
							<label for="review">Rese&ntilde;a:</label>
							<form:textarea class="form-control col-12" rows="5" path="review"
								id="review"></form:textarea>
						</div>
					</div>
				</div>
				<div class="col-12 text-center">
					<button type="submit">Guardar</button>
				</div>				
			</form:form>
		</div>



	</div>
	<!-- Inyeccion de dependencia de jquery y bootstrap -->
	<script src="${urlRoot}webjars/jquery/3.3.1/jquery.min.js"></script>
	<script src="${urlRoot}webjars/bootstrap/4.1.3/css/bootstrap.min.js"></script>
	
</body>
</html>