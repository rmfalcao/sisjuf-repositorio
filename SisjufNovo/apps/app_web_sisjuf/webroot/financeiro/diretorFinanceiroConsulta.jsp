<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
	<html>
		<head>
			<title>SISJUF - Sistema ASSERJUF</title>
			<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
			<link href="<c:url value="/nucleo/style/sisjuf.css"/>" type="text/css" rel="stylesheet" />
			<link rel="Shortcut Icon" type="image/png" href="<c:url value="/nucleo/images/icone.png"/>">
			<script>
				function setAcao(form, acao){
					form.acao.value = acao;
				}
				
				function validaRemocao(codigo){
					if (document.forms[0].elements['acao'].value == 'Remove_DiretorFinanceiro'){
						if (confirm("Deseja realmente remover este registro?")){
							window.location = '<c:url value="/Financeiro/DiretorFinanceiro/Remover"/>?codigo=' + codigo;
						} 
					}
				}

				function detalharDiretorFinanceiro(codigo) {
					window.location	= '<c:url value="/Financeiro/DiretorFinanceiro/Detalhar"/>?codigo=' + codigo;

				}
			</script>
		</head>
		<body>
		<form>		
			<div id="geral">
				<div id="topo">
					<div id="sair">
						sair <a href="<c:url value="/Logout"/>"><img src="<c:url value="/nucleo/images/botao_x.gif"/>" /></a>
					</div>
					<div id="voltar">
						voltar <a href="<c:url value="/financeiro/index.jsp"/>"/><img src="<c:url value="/nucleo/images/setinhavoltar.gif"/>" /></a>
					</div>
				</div>
				<%@ include file="/nucleo/includes/inc_menuFinanceiro.jsp" %>
				<div id="miolo">
					<h1>Módulo Financeiro</h1>
					<h2>Diretores Financeiros</h2>
					<a class="botao_novo" onclick="setAcao(document.forms[0],'Novo_DiretorFinanceiro')" href="<c:url value="/financeiro/diretorFinanceiroForm.jsp"/>"><span>novo</span></a>
					<br /><br />
					<!-- aqui vem a mensagem -->
					<c:choose>
						<c:when test="${empty errorMsg && not empty msg}">
							<ul><li><span class="textoMsgInfo"><c:out value="${requestScope.msg}"/></span></li></ul>
						</c:when>
						<c:when test="${not empty errorMsg}">
							<ul><li><span class="textoMsgErro"><c:out value="${requestScope.errorMsg}"/></span></li></ul>
						</c:when>
					</c:choose>
					
					<table cellpadding="2" cellspacing="1" width="100%" class="tab_lista">
							<tr>
								<th width="400">Diretor financeiro</th>
								<th width="170">Início da gestão</th>
								<th width="20"></th>
							</tr>
						<c:forEach items="${diretoresFinanceiros}" var="item">
							<tr>
								<td><a href="javascript:void(0);" onclick="detalharDiretorFinanceiro(${item.codigo});">${item.nome}</a></td>
								<td>${item.nome}</td>
								<td width="20"><a href="javascript:void(0);" onclick="setAcao(document.forms[0], 'Remove_DiretorFinanceiro'); validaRemocao(<c:out value="${item.codigo}"/>);" title="Remover" class="botao_excluir"></a></td>
							</tr>
						</c:forEach>
					</table>
				</div>
			</div>
			<input type="hidden" name="acao">
			</form>
		</body>
	</html>
</f:view>