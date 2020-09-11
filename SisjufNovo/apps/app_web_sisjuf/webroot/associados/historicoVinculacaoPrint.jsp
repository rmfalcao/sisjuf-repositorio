<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
<title>SISJUF - IMPRESSÃO</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="<c:url value="/nucleo/style/impressao.css"/>" type="text/css" rel="stylesheet" />
<link rel="Shortcut Icon" type="image/png" href="imagens/icone.png">
</head>
<body>

	<div id="logo_impressao"><img src="<c:url value="/nucleo/images/logo_impressao.gif"/>" /></div>
	<div id="topo_impressao">
		<strong>Sistema da Associação dos Servidores da Justiça Federal na Bahia</strong>

		<span>
			<h1>Lista de Histórico de Vinculações do plano</h1>
			<!-- Esses dados são vocês que irão determinar o que será exibido -->
			Data de impressão: <c:out value="${requestScope.dataAtual}"/>
			<br />
			Associado: <c:out value="${requestScope.associado}"/>
			<br />
			Operador: <c:out value="${requestScope.nomeUsuario}"/>
			<br />
			Convênio: <c:out value="${requestScope.convenio}"/>
			<br />
			Plano: <c:out value="${requestScope.plano}"/>
			<br />
		</span>
	</div>
	
	<table class="tabela_impressao" cellpadding="3" cellspacing="1">
		<thead>
			<tr>
				<th>Data Vinculação</th>
				<th>Data desvinculação</th>
				<th>Associado</th>
				<th>Convênio</th>
				<th>Plano</th>
				<th>Beneficiário</th>
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
