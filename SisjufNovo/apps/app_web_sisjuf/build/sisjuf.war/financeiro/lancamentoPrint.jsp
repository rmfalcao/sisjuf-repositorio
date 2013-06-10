<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
			<h1>Impressão de extrato</h1>
			<!-- Esses dados são vocês que irão determinar o que será exibido -->
			Data de impressão: <c:out value="${requestScope.dataAtual}"/>
			<br />
			Operador: <c:out value="${requestScope.nomeUsuario}"/>
			<br />
			Diretoria financeira: <c:out value="${requestScope.nomeDiretor}"/>
		</span>
	</div>

	
	<table class="tabela_extrato" cellpadding="1" cellspacing="1">
		<thead>
			<tr>
				<th>Efetivação</th>
				<th>Previsão</th>
				<th>Conta</th>
				<th>Oper.</th>
				<th>Origem</th>
				<th>Descricao</th>
				<th>Forma de Pagamento</th>
				<th align="right">Lançado (R$)</th>
				<th align="right">Pago (R$)</th>
			</tr>

		</thead>

		<tbody>
		
			<c:forEach items="${requestScope.lancamentos}" var="item">

				<tr>
					<td><c:out value="${item.dataEfetivacaoFormatada}"/>&nbsp;</td>
					<td><c:out value="${item.dataPrevisaoFormatada}"/>&nbsp;</td>
					<td><c:out value="${item.contaVO.nome}"/></td>
					<td align="center"><c:out value="${item.tipoOperacaoVO.sigla}"/></td>
					<td><c:out value="${item.origemLancamentoVO.nome}"/></td>
					<td><c:out value="${item.descricao}"/>&nbsp;</td>
					<td><c:out value="${item.descricaoCompletaFormaPagamento}"/>&nbsp;</td>
					<td align="right"><c:out value="${item.valorFormatado}"/></td>
					<td align="right"><c:out value="${item.valorPagoFormatado}"/></td>
				</tr>
				
			</c:forEach>
			
		</tbody>
		<tfoot>
			<tr>
				<th colspan="6"></th>
				<th align="right">Valor Total:</th>

				<td align="right"><c:out value="${totalLancado}"/></td>
				<td align="right"><c:out value="${totalPago}"/></td>
			</tr>
		</tfoot>

	</table>

</body>
</html>
<script language="JavaScript">
	window.print();
</script>