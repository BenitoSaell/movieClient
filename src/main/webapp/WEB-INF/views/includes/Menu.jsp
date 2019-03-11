<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<spring:url value="/" var="urlRoot"></spring:url>

<nav class="navbar navbar-expand-lg navbar-light bg-light">

	<button class="navbar-toggler" type="button" data-toggle="collapse"
		data-target="#navbarNav" aria-controls="navbarNav"
		aria-expanded="false" aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>
	<div class="collapse navbar-collapse" id="navbarNav">
		<ul class="navbar-nav">
			<li class="nav-item active"><a class="nav-link" href="${urlRoot}">Inicio
					<span class="sr-only">(current)</span>
			</a></li>
			<li class="nav-item"><a class="nav-link" href="${urlRoot}peliculas/">Peliculas</a></li>
			
			<li class="nav-item"><a class="nav-link" href="${urlRoot}registro">Registrar</a></li>
			<li class="nav-item"><a class="nav-link" href="${urlRoot}entrar">Acceder</a></li>
			<li class="nav-item"><a class="nav-link" href="${urlRoot}admin/salir">Salir</a></li>
			
		</ul>
	</div>
</nav>
