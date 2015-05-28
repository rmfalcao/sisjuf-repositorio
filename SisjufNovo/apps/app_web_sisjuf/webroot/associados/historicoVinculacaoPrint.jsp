<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
<title>SISJUF - IMPRESS�O</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="<c:url value="/nucleo/style/impressao.css"/>" type="text/css" rel="stylesheet" />
<link rel="Shortcut Icon" type="image/png" href="imagens/icone.png">
</head>
<body>

	<div id="logo_impressao"><img src="<c:url value="/nucleo/images/logo_impressao.gif"/>" /></div>
	<div id="topo_impressao">
		<strong>Sistema da Associa��o dos Servidores da Justi�a Federal na Bahia</strong>

		<span>
			<h1>Lista de Hist�rico de Vincula��es do plano</h1>
			<!-- Esses dados s�o voc�s que ir�o determinar o que ser� exibido -->
			Data de impress�o: <c:out value="${requestScope.dataAtual}"/>
			<br />
			Associado: <c:out value="${requestScope.associado}"/>
			<br />
			Operador: <c:out value="${requestScope.nomeUsuario}"/>
			<br />
			Conv�nio: <c:out value="${requestScope.convenio}"/>
			<br />
			Plano: <c:out value="${requestScope.plano}"/>
			<br />
		</span>
	</div>
	
	<table class="tabela_impressao" cellpadding="3" cellspacing="1">
		<thead>
			<tr>
				<th>Data Vincula��o</th>
				<th>Data desvincula��o</th>
				<th>Associado</th>
				<th>Conv�nio</th>
				<th>Plano</th>
				<th>Benefici�rio</th>
			</tr>

		</thead>
				
		<tbody>
			<c:forEach items="${requestScope.listaVinculacoes}" var="item">
			
				<tr>
					<td><fmt:formatDate value="${item.dataVinculacao}" pattern="dd/MM/yyyy"/></td>
					<td><fmt:formatDate value="${item.dataDesVinculacao}" pattern="dd/MM/yyyy"/></td>
					<td><c:out value="${item.associado.nome}"/></td>
					<td><c:out value="${item.convenio.nomeFantasia}"/></td>
					<td><c:out value="${item.plano.descricao}"/></td>
				</tr>
				
			</c:forEach>

		</tbody>

	</table>

</body>
</html>
<script language="JavaScript">
	window.print();
</script>
