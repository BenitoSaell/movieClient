
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head >
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>Bienvenida</title>
<spring:url value="/" var="urlRoot" />
<link href="${urlRoot}webjars/bootstrap/4.1.3/css/bootstrap.min.css"
	rel="stylesheet">

</head>
<body>
	<jsp:include page="./includes/Menu.jsp"></jsp:include>
	<div class="container theme-showcase" role="main">

		<div>
			<h3>Administración del Sistema</h3>
			<p>
				Bienvenido(a) ${userAdmin.name}
			</p>
		</div>
	</div>
	<script src="${urlRoot}webjars/jquery/3.3.1/jquery.min.js"></script>
		<script src="${urlRoot}webjars/bootstrap/4.1.3/js/bootstrap.min.js"></script>
</body>
</html>