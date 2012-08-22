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
    					<a4j:keepAlive beanName="ConvenioBean" />
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
									<th width="140">Convênio:</th>
									<td width="230" colspan="3">
										<h:selectOneMenu id="convenio" value="#{ConvenioBean.fatura.convenio.codigo}" tabindex="1">
											<f:selectItem itemLabel="#{properties['lb_selecione']}" itemValue=""/>
											<t:selectItems id="sel_convenios" value="#{ConvenioBean.allConvenios}" var="convenio" itemLabel="#{convenio.nomeFantasia}" 
												itemValue="#{convenio.codigo}"/>
											<a4j:support event="onchange" action="#{ConvenioBean.limparFaturas}"/>
											<a4j:support event="onchange" reRender="itensFatura"/>
										</h:selectOneMenu>
									</td>
								</tr>
								<tr>
									<th>Mês/Ano:</th>
									<td>
										<h:selectOneMenu id="mes" value="#{ConvenioBean.fatura.mes}" tabindex="2">
											<f:selectItem itemLabel="#{properties['lb_selecione']}"	itemValue="" />
											<f:selectItem itemLabel="#{properties['lb_janeiro']}" itemValue="1"/>
											<f:selectItem itemLabel="#{properties['lb_fevereiro']}" itemValue="2"/>
											<f:selectItem itemLabel="#{properties['lb_marco']}" itemValue="3"/>
											<f:selectItem itemLabel="#{properties['lb_abril']}" itemValue="4"/>
											<f:selectItem itemLabel="#{properties['lb_maio']}" itemValue="5"/>
											<f:selectItem itemLabel="#{properties['lb_junho']}" itemValue="6"/>
											<f:selectItem itemLabel="#{properties['lb_julho']}" itemValue="7"/>
											<f:selectItem itemLabel="#{properties['lb_agosto']}" itemValue="8"/>
											<f:selectItem itemLabel="#{properties['lb_setembro']}" itemValue="9"/>
											<f:selectItem itemLabel="#{properties['lb_outubro']}" itemValue="10"/>
											<f:selectItem itemLabel="#{properties['lb_novembro']}" itemValue="11"/>
											<f:selectItem itemLabel="#{properties['lb_dezembro']}" itemValue="12"/>
										</h:selectOneMenu>
									</td>
									<td colspan="2">
										<t:inputText size="15"  maxlength="4" id="ano" value="#{ConvenioBean.fatura.ano}" tabindex="3" onkeypress="return justNumber(this,event)"/>
									</td>
								</tr>
							</tbody>
							
							<thead>
								<tr>
									<th>Informações Financeiras</th>
									<th colspan="3"></th>
								</tr>
							</thead>
							
							<tbody>
								<tr>
									<th>Conta Debito:</th>
									<td colspan="3">
										<h:selectOneMenu id="contaDebito" value="#{ConvenioBean.fatura.contaDebito.codigo}" tabindex="1">
											<f:selectItem itemLabel="#{properties['lb_selecione']}" itemValue=""/>
											<t:selectItems id="sel_contasBebitos" value="#{ConvenioBean.allContas}" var="contaDebito" itemLabel="#{contaDebito.nome}" 
												itemValue="#{contaDebito.codigo}"/>
										</h:selectOneMenu>
									</td>
								</tr>
								<tr>
									<th>Conta Credito:</th>
									<td colspan="3">
										<h:selectOneMenu id="contaCredito" value="#{ConvenioBean.fatura.contaCredito.codigo}" tabindex="1">
											<f:selectItem itemLabel="#{properties['lb_selecione']}" itemValue=""/>
											<t:selectItems id="sel_contasCreditos" value="#{ConvenioBean.allContas}" var="contaCredito" itemLabel="#{contaCredito.nome}" 
												itemValue="#{contaCredito.codigo}"/>
										</h:selectOneMenu>
									</td>
								</tr>
							</tbody>
						</table>
						
						<t:div id="div_faturas" styleClass="conteudo">
							<h2>&nbsp;</h2><br/>
							<h2>Itens de Fatura</h2>
							<rich:dataTable value="#{ConvenioBean.fatura.itens}" var="itens" border="0" id="itensFatura" width="100%">
								<f:facet name="header">
									<rich:columnGroup>
									<rich:column><h:outputText value="Número" /></rich:column>
									<rich:column><h:outputText value="Beneficiário" /></rich:column>
									<rich:column><h:outputText value="Titular" /></rich:column>
									<rich:column><h:outputText value="Valor" /></rich:column>
									<rich:column></rich:column>
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
								
								<rich:column style="text-align:center; width:2%">
									<a4j:commandLink action="#{ConvenioBean.removerItemFatura}" title="#{properties['lb_remover']}" 
										immediate="true" id="remover" styleClass="botao_excluir" reRender="itensFatura">
										<t:updateActionListener property="#{ConvenioBean.itemFatura.numero}" value="#{itens.numero}" />
									</a4j:commandLink>
								</rich:column>
							</rich:dataTable>
						</t:div>
						
						<a4j:commandButton action="#{ConvenioBean.gerarFatura}" title="#{properties['lb_Gerar']}" 
							styleClass="botao_salvar" value="salvar" reRender="faturasMsgs, codigo, btGerarFaura" id="btGerarFaura" 
							disabled="#{ConvenioBea.fatura.codigo != null}"/>
						<h:inputHidden id="codigo" value="#{ConvenioBean.fatura.codigo}"/>
					</h:form>
					<%@ include file="itemFaturaForm.jsp"%>
					<br /><br /><br /><br /><br /><br /><hr>
				</div>
			</div>
		</body>
	</html>
</f:view>