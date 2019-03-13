<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Error 400</title>
<spring:url value="/" var="urlRoot" />
<link href="${urlRoot}webjars/bootstrap/4.1.3/css/bootstrap.min.css"
	rel="stylesheet">
<link href="/css/error.css" rel="stylesheet">
</head>
<body>
	<jsp:include page="../includes/Menu.jsp"></jsp:include>
	<div class="container theme-showcase" role="main">
		<div class="col-12 text-center item">
			<div class="border">
				<h3>Error 400.</h3>
				<p>Solicitud incorrecta.</p>
				<a class="btn btn-success" href="/">Inicio</a>
			</div>
		</div>
	</div>
</body>
</html>