<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>

<f:view>
	<f:loadBundle basename="sisjuf" var="properties" />
	<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
	<html>
		<head>
			<title>SISJUF - Sistema ASSERJUF</title>
			<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
			<link href="<c:url value="/nucleo/style/sisjuf.css"/>" type="text/css"
				rel="stylesheet" />
			<link rel="Shortcut Icon" type="image/png"
				href="<c:url value="/nucleo/images/icone.png"/>">
			<script src="<c:url value="/nucleo/js/sisjuf.js" />"
				type="text/javascript"></script>
		</head>
		<body>
			<div id="geral">
				<div id="topo">
					<div id="sair">sair <a href="<c:url value="/login.jsf"/>"><img
						src="<c:url value="/nucleo/images/botao_x.gif"/>" /></a>
					</div>
					<div id="voltar">
						voltar <a href="<c:url value="/financeiro/contaConsulta.jsf"/>"><img
						src="<c:url value="/nucleo/images/setinhavoltar.gif"/>" /></a>
					</div>
				</div>
		
				<%@ include file="/nucleo/includes/inc_menuFinanceiro.jsp"%>
			
				<h:form id="ValorSocioUsuarioForm">
			
					<div id="miolo">
						<h1>Módulo Associados</h1>
						<h2>Atualizar Valor Sócio Usuário</h2>
						<t:messages id="msgs" showDetail="true" showSummary="false"
							errorClass="textoMsgErro" infoClass="textoMsgInfo" />
				
						<table class="tab_cadastro" cellpadding="2" cellspacing="1">
							<thead>
								<tr>
									<th width="140">Alterar Valor</th>
									<th width="450"></th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<th>Valor atual:</th>
									<td><t:inputText id="valor_atual" value="#{AssociadoBean.valorAtual}" converter="DecimalConverter" readonly="true"/></td>
								</tr>
								<tr>
									<th>Novo valor:</th>
									<td><t:inputText id="valor_novo" value="#{AssociadoBean.valorNovo}" converter="DecimalConverter" size="10" onkeyup="formatarCampoNumero(this)" /></td>
								</tr>
							</tbody>
						</table>
						<br />
						<t:commandLink action="#{AssociadoBean.salvarAtualizarValor}" title="#{properties['lb_salvar']}" id="salvar" styleClass="botao_salvar">
							<h:outputText value="#{properties['lb_salvar']}" />
						</t:commandLink>
						<input type="hidden" name="acao" value="Atualiza_Valor_Associado"/>
					</div>
				</h:form>
			</div>
		</body>
	</html>
</f:view>
