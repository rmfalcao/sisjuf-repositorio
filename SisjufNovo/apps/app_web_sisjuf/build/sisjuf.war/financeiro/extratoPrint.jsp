<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="now" scope="page" class="java.util.Date" />
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
				Data de impressão: <fmt:formatDate value="${now}" pattern="dd/MM/yyyy"/>
				<br />
				Operador: ${nomeUsuario}
				<br />
				Diretoria financeira: ${nomeDiretor}
			</span>
		</div>
		<table class="tabela_extrato" cellpadding="1" cellspacing="1">
			<thead>
				<tr>
					<th >Data</th>
					<th >Tipo / Origem</th>
					<th >Descrição</th>
					<th >Forma de Pagamento</th>
					<th >Operação</th>
					<th width="90" align="right">Valor (R$)</th>
				</tr>
	
			</thead>
	
			<tbody>
				<tr>
					<td colspan="3">&nbsp;</td>
					<td colspan="2" align="right"><b>Saldo inicial:</b></td>
					<th align="right">${requestScope.extrato.saldoInicialFormatado}</th>
				</tr>
	
			
				<c:forEach items="${requestScope.extrato.baixaLancamento}" var="item">
				
					<tr>
						<td>${item.dataFormatada}</td>
						<td>${item.lancamentoVO.origemLancamentoVO.nome}</td>
						<td>${item.lancamentoVO.descricao}&nbsp;</td>
						<td>${item.descricaoCompletaFormaPagamento}&nbsp;</td>
						<td>${item.lancamentoVO.tipoOperacaoVO.sigla}</td>
						<td align="right"><c:out value="${item.valorFormatado}"/></td>
					</tr>
					
				</c:forEach>
				
				<tr>
					<td colspan="3">&nbsp;</td>
					<td colspan="2" align="right"><b>Saldo final:</b></td>
					<th align="right">${extrato.saldoFinalFormatado}</th>
				</tr>
	
			</tbody>
	
		</table>
	
	</body>
</html>
<script language="JavaScript">
	window.print();
</script>