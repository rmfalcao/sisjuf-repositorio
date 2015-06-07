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
			<script type="text/javascript">
				function imprimir() {
					var codigoFatura = document.getElementById("codigoFatura").value;
					var url			= '<c:url value="/associados/convenio/FaturaDetalhadaImpressao?codigoFatura='+codigoFatura+'"/>';
					var name		= 'telaImpressao';
					var features	= 'toolbar=no,status=no,resizable=yes,scrollbars=yes,width=750,height=500';
					window.open(url,name,features);
					return true;
				}
			</script>
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
    					<t:inputHidden id="codigoFatura" forceId="true" value="#{FaturaBean.fatura.codigo}" />
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
						
						<t:commandLink title="#{properties['lb_validar']}" action="#{FaturaBean.validarFatura}" 
							 value="#{properties['lb_validar']}" rendered="#{!FaturaBean.faturaCancelada}" /><br/><br/>
						&nbsp;
						<a4j:commandLink title="#{properties['lb_cancelar']}" action="#{FaturaBean.cancelarFatura}" 
							 onclick="#{rich:component('confirmation')}.show();return false"
							 value="#{properties['lb_cancelar']}" rendered="#{!FaturaBean.faturaCancelada}"/><br/><br/>
						&nbsp;
						<h:outputLink value="javascript:void(0);" onclick="imprimir();" styleClass="botao_imprimir" title="#{properties['lb_imprimir']}" />
						
						<t:div id="div_faturas" styleClass="conteudo">
							<h1>Itens de Fatura</h1>
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
						
						<rich:modalPanel id="confirmation" width="250" height="150">
						   <f:facet name="header">Confirmação</f:facet>
						   <h:panelGrid>
						      <h:panelGrid columns="1">
							 	<h:outputText value="Deseja confirmar o cancelamento da fatura? Essa ação não poderá ser desfeita." style="FONT-SIZE: large;" />
						      </h:panelGrid>
						      <h:panelGroup>
						      		<a4j:commandButton  value="Confirmar" action="#{FaturaBean.cancelarFatura}"  reRender="gerarFaturaForm" oncomplete="#{rich:component('confirmation')}.hide();"/>
						            <a4j:commandButton  value="Cancelar" onclick="#{rich:component('confirmation')}.hide();return false" />
							  </h:panelGroup>
						   </h:panelGrid>
						</rich:modalPanel>
									
						
					</h:form>
					<br /><br /><br /><br /><br /><br /><hr>
				</div>
			</div>
		</body>
	</html>
</f:view>