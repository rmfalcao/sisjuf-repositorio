<?xml version="1.0" encoding="UTF-8"?>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<f:view>
	<f:loadBundle basename="sisjuf" var="properties" />
	<html>
		<head>
			<title>SISJUF - Sistema ASSERJUF</title>
			<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
			<script src="<c:url value="/nucleo/js/sisjuf.js" />" type="text/javascript"></script>
			<link href="<c:url value="/nucleo/style/sisjuf.css"/>" type="text/css" rel="stylesheet" />
			<link rel="Shortcut Icon" type="image/png" href="<c:url value="/nucleo/images/icone.png"/>">
		</head>
		<body>
			<div id="geral">
				<div id="topo">
					<div id="sair">
						sair <a href="<c:url value="/login.jsf"/>"><img src="<c:url value="/nucleo/images/botao_x.gif"/>" /></a>
					</div>
				</div>
				<%@ include file="/nucleo/includes/inc_menuFinanceiro.jsp"%>
				
				<div id="miolo">
					<h1>Módulo Convênios</h1>
					<h2>Gerar Faturas Fixas</h2>
					<a class="botao_novo" href="<c:url value="/associados/convenio/gerarFaturas.jsf"/>"><span>novo</span></a>
					<br /><br />
    				<h:form id="gerarFaturaForm" acceptcharset="ISO-8859-1">
    					<a4j:keepAlive beanName="FaturaBean" />
						<h:panelGroup id="faturasMsgs">
							<h:messages showDetail="true" showSummary="false" errorClass="textoMsgErro" infoClass="textoMsgInfo" />
						</h:panelGroup>
						<table class="tab_cadastro" cellpadding="2" cellspacing="1">
							<thead>
								<tr>
									<th>Dados de Básicos</th>
									<th colspan="3"></th>
								</tr>
							</thead>
							
							<tbody>
								<tr>
									<th width="140">Codigo Fatura:</th>
									<td width="230" colspan="3">
										<h:outputText value="#{FaturaBean.fatura.codigo}" />
									</td>
								</tr>
								<tr>
									<th width="140">Convênio:</th>
									<td width="230" colspan="3">
										<h:outputText value="#{FaturaBean.fatura.convenio.nomeFantasia}" />
									</td>
								</tr>
								<tr>
									<th>Data Inicio / Fim:</th>
									<td>
										<h:outputText value="#{FaturaBean.fatura.dataInicial}" />
									</td>
									<td colspan="2">
										<h:outputText value="#{FaturaBean.fatura.dataFinal}" />
									</td>
								</tr>
								<tr>
									<th>Data Vencimento:</th>
									<td colspan="3">
										<h:outputText value="#{FaturaBean.fatura.dataVencimento}" />
									</td>
								</tr>
								<tr>
									<th>Status Fatura:</th>
									<td colspan="3">
										<h:outputText value="#{FaturaBean.fatura.status.nome}" /> 
										(<h:outputText value="#{FaturaBean.fatura.descricaoStatusPago}" /> | <h:outputText value="#{FaturaBean.fatura.descricaoStatusRecebido}" />)
									</td>
								</tr>
								<tr>
									<th>Valor Fatura:</th>
									<td colspan="3">
										<h:outputText value="#{FaturaBean.fatura.valorFatura}" converter="DoubleConverter"/> 
									</td>
								</tr>
							</tbody>
							
						</table>
						
						<t:div id="div_faturas" styleClass="conteudo">
							<h2>&nbsp;</h2><br/>
							<h2>Itens de Fatura</h2>
							<rich:dataTable value="#{FaturaBean.fatura.itens}" var="itens" border="0" id="itensFatura" width="100%">
								<f:facet name="header">
									<rich:columnGroup>
									<rich:column><h:outputText value="Número" /></rich:column>
									<rich:column><h:outputText value="Beneficiário" /></rich:column>
									<rich:column><h:outputText value="Titular" /></rich:column>
									<rich:column><h:outputText value="Valor" /></rich:column>
									</rich:columnGroup>
								</f:facet>
								
								<rich:column style="width:15%">
									<h:outputText value="#{itens.numero}" />
								</rich:column>
								
								<rich:column style="width:40%">
									<h:outputText value="#{itens.beneficiario.nome}" />
								</rich:column>
								
								<rich:column style="width:30%">
									<h:outputText value="#{itens.beneficiario.titular.nome}"/>
								</rich:column>
								
								<rich:column style="width:10%">
									<h:outputText value="#{itens.valor}" converter="DoubleConverter"/>
								</rich:column>
							</rich:dataTable>
						</t:div>
						
					</h:form>
					<br /><br /><br /><br /><br /><br /><hr>
				</div>
			</div>
		</body>
	</html>
</f:view>