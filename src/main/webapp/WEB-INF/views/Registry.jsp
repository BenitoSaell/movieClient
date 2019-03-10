<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>

<html>
<head>
<meta charset="ISO-8859-1">
<title>Registro</title>
<spring:url value="/resources" var="urlPublic" />
<spring:url value="/" var="urlRoot" />
<spring:url value="/registrar" var="urlSave" />
<link href="${urlRoot}webjars/bootstrap/4.1.3/css/bootstrap.min.css"
	rel="stylesheet">
<link href="/css/registry.css" rel="stylesheet">

</head>
<body>
	<jsp:include page="includes/Menu.jsp"></jsp:include>
	<div class="container theme-showcase" role="main">

		<!-- Formulario de registro -->
		<div class="container marketing">
			<form action="${urlSave}" method="post">

				<h1>Registro</h1>
				<spring:hasBindErrors name="user">
					<div class='alert alert-danger' role='alert'>
						Por favor corrija los siguientes errores:
						<ul>
							<c:forEach var="error" items="${errors.allErrors}">
								<li><spring:message message="${error}" /></li>
							</c:forEach>
						</ul>
					</div>
				</spring:hasBindErrors>

				<fieldset>
					<label for="name">Nombre:</label> 
					<input type="text" id="name" name="name"> 
					<label for="email">Correo:</label> 
					<input type="email" id="email" name="email"> 
					<label for="password">Contrase&ntilde;a:</label> 
					<input type="password" id="password" name="password">
				</fieldset>
				<button type="submit">Registrarse</button>
			</form>

		</div>
		<script src="${urlroot}/webjars/jquery/3.3.1/jquery.min.js"></script>
		<script src="${urlroot}webjars/bootstrap/4.1.3/js/bootstrap.min.js"></script>
	</div>

</body>
</html>