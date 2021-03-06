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
			<h1>Lista de Benefici�rios</h1>
			<!-- Esses dados s�o voc�s que ir�o determinar o que ser� exibido -->
			Data de impress�o: <c:out value="${requestScope.dataAtual}"/>
			<br />
			Operador: <c:out value="${requestScope.nomeUsuario}"/>
			<br />
			Conv�nio: <c:out value="${requestScope.convenio}"/>
			<br />
			Plano: <c:out value="${requestScope.plano}"/>
			<br />
			Nome: <c:out value="${requestScope.nome}"/>
			<br />
			Matricula: <c:out value="${requestScope.matricula}"/>
			<br />
			Per�odo de vincula��o: <c:out value="${requestScope.dataInicio}"/> a <c:out value="${requestScope.dataFim}"/>
		</span>
	</div>
	
	<table class="tabela_impressao" cellpadding="3" cellspacing="1">
		<thead>
			<tr>
				<th>Nome</th>
				<th>Nome do titular</th>
				<th>Tipo de v�nculo</th>
				<th>Plano</th>
				<th>Valor</th>
			</tr>

		</thead>
				
		<tbody>
			<c:forEach items="${requestScope.listaBeneficiarios}" var="item">
			
				<tr>
					<td><c:out value="${item.nome}"/></td>
					<td><c:out value="${item.titular.nome}"/></td>
					<td><c:out value="${item.tipoBeneficiario}"/></td>
					<td><c:out value="${item.plano.nome}"/></td>
					<td><c:out value="${item.plano.valor}"/></td>
				</tr>
				
			</c:forEach>

		</tbody>

	</table>

</body>
</html>
<script language="JavaScript">
	window.print();
</script>
