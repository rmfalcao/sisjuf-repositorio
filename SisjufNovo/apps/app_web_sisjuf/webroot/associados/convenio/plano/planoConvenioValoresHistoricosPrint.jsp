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
			<h1>Lista de Histórico de valores do plano</h1>
			<!-- Esses dados são vocês que irão determinar o que será exibido -->
			Data de impressão: <c:out value="${requestScope.dataAtual}"/>
			<br />
			Operador: <c:out value="${requestScope.nomeUsuario}"/>
			<br />
			Convênio: <c:out value="${requestScope.convenio}"/>
			<br />
			Plano: <c:out value="${requestScope.plano}"/>
		</span>
	</div>
	
	<table class="tabela_impressao" cellpadding="3" cellspacing="1">
		<thead>
			<tr>
				<th>Nome</th>
				<th>Valor</th>
				<th>Início Vigência</th>
			</tr>

		</thead>
				
		<tbody>
			<c:forEach items="${requestScope.listaPlanos}" var="item">
			
				<tr>
					<td><c:out value="${item.nome}"/></td>
					<td><c:out value="${item.valor}"/></td>
					<td><fmt:formatDate value="${item.dataInicio}" pattern="dd/MM/yyyy" /></td>
				</tr>
				
			</c:forEach>

		</tbody>

	</table>

</body>
</html>
<script language="JavaScript">
	window.print();
</script>
