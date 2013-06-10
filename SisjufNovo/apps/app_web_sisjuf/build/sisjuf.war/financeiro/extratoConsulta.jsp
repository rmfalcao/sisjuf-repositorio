<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<f:view>
	<f:loadBundle basename="sisjuf" var="properties" />
	<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
	<html>
		<head>
			<title>SISJUF - Sistema ASSERJUF</title>
			<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
			<link href="<c:url value="/nucleo/style/sisjuf.css"/>" type="text/css" rel="stylesheet" />
			<link rel="Shortcut Icon" type="image/png" href="<c:url value="/nucleo/images/icone.png"/>">
			<script src="<c:url value="/nucleo/js/sisjuf.js" />" type="text/javascript"></script>
				<script>
				function imprimir() {
				
					// testando campos obrigatórios:
					
					if (document.forms[0].elements['ExtratoResultado:dataInicialInputDate'].value == '') {
						alert('A data inicial deve ser informada.');
						return;
					}
					
					if (document.forms[0].elements['ExtratoResultado:dataFinalInputDate'].value == '') {
						alert('A data final deve ser informada.');
						return;					
					}
				
					if (document.forms[0].elements['conta'].value == '') {
						alert('A conta deve ser informada.');
						return;					
					}
					var url			= '<c:url value="/Financeiro/Extrato/Imprimir"/>?dataInicial=' 
						+ document.forms[0].elements['ExtratoResultado:dataInicialInputDate'].value + '&dataFinal=' 
						+ document.forms[0].elements['ExtratoResultado:dataFinalInputDate'].value + '&conta=' 
						+ document.forms[0].conta.value;
					var name		= 'telaImpressao';
					var features	= 'toolbar=no,status=no,resizable=yes,scrollbars=yes,width=750,height=500';
					window.open(url,name,features);
				}
				
				function setAcao(form, acao){
					form.acao.value = acao;
				}
				
				function validaForm(){
					if (document.forms[0].elements['ExtratoResultado:dataInicialInputDate'].value != ""){
						if (document.forms[0].elements['ExtratoResultado:dataFinalInputDate'].value != ""){
							if (document.forms[0].conta.value != ""){
								return true;
							}else{
								alert("O campo Conta n\u00E3o pode ser vazio.");
							}
						}else{
							alert("O campo Data Final n\u00E3o pode ser vazio.");
						}
					}else{
						alert("O campo Data Inicial n\u00E3o pode ser vazio.");
					}
					return false;
				}
			</script>
		</head>
		<body>
		
			<div id="geral">
				<div id="topo">
					<div id="sair">
						sair <a href="<c:url value="/login.jsf"/>"><img src="<c:url value="/nucleo/images/botao_x.gif"/>" /></a>
					</div>
					<div id="voltar">
						voltar <a href="<c:url value="/financeiro/index.jsp"/>"/><img src="<c:url value="/nucleo/images/setinhavoltar.gif"/>" /></a>
					</div>
				</div>
		
				<%@ include file="/nucleo/includes/inc_menuFinanceiro.jsp" %>
		
				<div id="miolo">
					<h1>Módulo Financeiro</h1>
					<h2>Extrato</h2>
					
					<h:form id="ExtratoResultado" onsubmit="return validaForm();">
						<table class="tab_cadastro" cellpadding="2" cellspacing="1">
							<thead>
								<tr>
									<th>Filtro</th>
									<th colspan="4"></th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<th><h:outputLabel value="#{properties['lb_periodo']}" />:</th>
									<td colspan="4">
										<rich:calendar id="dataInicial" popup="true" datePattern="dd/MM/yyyy" showApplyButton="false"
											cellWidth="24px" cellHeight="22px" style="width:200px" disabled="false" locale="en/US"
											value="#{ExtratoBean.filtro.dataInicial}" inputClass="inputCalendar" enableManualInput="true"
											oninputblur="checkDate(this)" oninputkeypress="return maskDate(this,event);"/>
										à
										<rich:calendar id="dataFinal" popup="true" datePattern="dd/MM/yyyy" showApplyButton="false"
											cellWidth="24px" cellHeight="22px" style="width:200px" disabled="false" locale="en/US"
											value="#{ExtratoBean.filtro.dataFinal}" inputClass="inputCalendar" enableManualInput="true"
											oninputblur="checkDate(this)" oninputkeypress="return maskDate(this,event);"/>
									</td>
								</tr>
								<tr>					
									<th><h:outputLabel for="conta" value="#{properties['lb_conta']}" />:</th>
									<td colspan="4">
										<t:selectOneMenu id="conta" value="#{ExtratoBean.filtro.contaVO.codigo}" forceId="true">
											<f:selectItem itemLabel="#{properties['lb_selecione']}" itemValue=""/>
											<f:selectItems id="sel_contas" value="#{ExtratoBean.contas}"/>
										</t:selectOneMenu>
									</td>
								</tr>
							</tbody>
						</table>
						<t:commandLink action="#{ExtratoBean.consultar}" title="#{properties['lb_filtrar']}" id="filtrar" styleClass="botao_filtrar"
						onclick="setAcao(document.forms[0], 'Filtrar_Extrato');">
							<h:outputText value="#{properties['lb_filtrar']}" />
						</t:commandLink>
						
						<br /><br /><br /><br />
						<br>
						<br /><br /><b>Diretoria financeira: </b>
						<h:outputText value="#{ExtratoBean.extrato.diretorFinanceiroVO.nome}" />
						
						<t:dataTable id="data" var="baixaLancamentos" value="#{ExtratoBean.extrato.baixaLancamento}"
							cellspacing="1" cellpadding="2" width="100%" styleClass="tab_lista_maior"
							preserveDataModel="false" >
							<h:column>
								<f:facet name="header">
									<h:outputText value="#{properties['lb_data']}" />
								</f:facet>
								<h:outputText value="#{baixaLancamentos.data}" />
								<f:facet name="footer">
						            <h:outputLink value="javascript:void(0);" onclick="imprimir();" styleClass="botao_imprimir" title="#{properties['lb_imprimir']}">
						            </h:outputLink>
						        </f:facet>
							</h:column>
							<h:column>
								<f:facet name="header">
									<h:outputText value="#{properties['lb_tipoOrigem']}" />
								</f:facet>
								<h:outputText value="#{baixaLancamentos.lancamentoVO.origemLancamentoVO.nome}" />
							</h:column>
							<h:column>
								<f:facet name="header">
									<h:outputText value="#{properties['lb_descricao']}" />
								</f:facet>
								<h:outputText value="#{baixaLancamentos.lancamentoVO.descricao}" />
							</h:column>
							<h:column>
								<f:facet name="header">
									<h:outputText value="#{properties['lb_forma_pagamento']}" />
								</f:facet>
								<h:outputText value="#{baixaLancamentos.descricaoCompletaFormaPagamento}" />
							</h:column>
							<h:column>
								<f:facet name="header">
									<h:outputText value="#{properties['lb_saldoInicial']}" />
								</f:facet>
								<h:outputText value="#{baixaLancamentos.lancamentoVO.tipoOperacaoVO.sigla}" />
								<f:facet name="footer">
						            <h:outputLabel value="#{properties['lb_saldoFinal']}" style="font-weight:bold;"/>
						        </f:facet>
							</h:column>
							<h:column>
								<f:facet name="header">
									<h:outputLabel value="#{ExtratoBean.extrato.saldoInicial}" converter="DoubleConverter"/>
								</f:facet>
								<h:outputText value="#{baixaLancamentos.valor}" converter="DoubleConverter" styleClass="#{baixaLancamentos.valor < 0 ? 'valor_negativo' : ''}"/>
								<f:facet name="footer">
						            <h:outputLabel value="#{ExtratoBean.extrato.saldoFinal}" converter="DoubleConverter" style="font-weight:bold;"/>
						        </f:facet>
							</h:column>
						</t:dataTable>
						<input type="hidden" name="acao" value=""/>
					</h:form>
				</div>
			</div>
		</body>
	</html>
</f:view>