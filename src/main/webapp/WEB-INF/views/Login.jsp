<%@ page language="java" contentType="text/html; charset=utf-8" 
    pageEncoding="utf-8"%> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>Acceso</title>
<spring:url value="/resources" var="urlPublic" />
<spring:url value="/" var="urlRoot" />
<link href="${urlRoot}webjars/bootstrap/4.1.3/css/bootstrap.min.css"
	rel="stylesheet">
<link href="/css/registry.css" rel="stylesheet">
</head>
<body>
	<jsp:include page="includes/Menu.jsp"></jsp:include>

	<form class="form-signin" action="${urlRoot}login" method="post">


		<h3 class="form-signin-heading text-center">Bienvenido</h3>

		<div class="col-12 text-center">
			<img src="${urlPublic}/images/login.png" width="200px">
		</div>

		<br />
		<c:if test="${param.error != null}">
			<h4 class="form-signin-heading" style="color: red">Acceso
				denegado</h4>
		</c:if>
		<c:if test="${message!= null }">
			<div class='alert alert-danger' role="alert">${message}</div>
		</c:if>
		<label for="username" class="sr-only">Correo</label> <input
			type="text" id="username" name="username" class="form-control"
			placeholder="Usuario" required autofocus> <label
			for="password" class="sr-only">Contraseña</label> <input
			type="password" id="password" name="password" class="form-control"
			placeholder="Contrase&ntilde;a" required>
		<div class="text-center">
			<button type="submit">Entrar</button>
		</div>

	</form>
</body>
</html>