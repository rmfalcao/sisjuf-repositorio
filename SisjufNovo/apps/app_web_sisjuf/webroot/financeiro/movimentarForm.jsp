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
				function setAcao(form, acao){
					form.acao.value = acao;
				}
				
				function testeFormaPagamento(){
					var form = document.forms[0];
					if (form.elements['formaPagamento'].value == '1'){
						form.elements['numeroCheque'].disabled = false;
					}else{
						form.elements['numeroCheque'].disabled = true;
					}
				}
			</script>
		</head>
		<body onload="testeFormaPagamento();">
		
			<div id="geral">
				<div id="topo">
					<div id="sair">
						sair <a href="<c:url value="/seguranca/logout.jsp"/>"><img src="<c:url value="/nucleo/images/botao_x.gif"/>" /></a>
					</div>
					<div id="voltar">
						voltar <a href="<c:url value="/financeiro/index.jsp"/>"/><img src="<c:url value="/nucleo/images/setinhavoltar.gif"/>" /></a>
					</div>
				</div>
		
				<%@ include file="/nucleo/includes/inc_menuFinanceiro.jsp" %>
				<h:form id="MovimentarForm">
					<div id="miolo">
						<h1>Módulo Financeiro</h1>
						<h2>Movimentar valores entre Contas</h2>
						<t:messages id="msgs" showDetail="true" showSummary="false" errorClass="textoMsgErro" infoClass="textoMsgInfo"/>
						<table class="tab_cadastro" cellpadding="2" cellspacing="1">
							<thead>
								<tr>
									<th width="200">Dados da Movimentação</th>
									<th width="390"></th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<th><h:outputLabel for="contaOrigem" value="#{properties['lb_contaOrigem']}" />:</th>
									<td>
										<h:selectOneMenu id="contaOrigem" value="#{MovimentarBean.movimentacao.contaOrigemVO.codigo}" required="true">
											<f:selectItem itemLabel="#{properties['lb_selecione']}" itemValue=""/>
											<f:selectItems id="sel_contasOrigem" value="#{MovimentarBean.contasOrigem}"/>
										</h:selectOneMenu>
									</td>
								</tr>
								<tr>
									<th><h:outputLabel for="contaDestino" value="#{properties['lb_contaDestino']}" />:</th>
									<td>
										<h:selectOneMenu id="contaDestino" value="#{MovimentarBean.movimentacao.contaDestinoVO.codigo}" required="true">
											<f:selectItem itemLabel="#{properties['lb_selecione']}" itemValue=""/>
											<f:selectItems id="sel_contasDestino" value="#{MovimentarBean.contasDestino}"/>
										</h:selectOneMenu>
									</td>
								</tr>
								<tr>
									<th><h:outputLabel for="dataMovimentacao" value="#{properties['lb_dataMovimentacao']}" />:</th>
									<td>
										<rich:calendar id="dataMovimentacao" popup="true" datePattern="dd/MM/yyyy" showApplyButton="false"
											cellWidth="24px" cellHeight="22px" style="width:200px" disabled="false" locale="en/US"
											value="#{MovimentarBean.movimentacao.data}" inputClass="inputCalendar" enableManualInput="true"
											oninputblur="checkDate(this)" oninputkeypress="return maskDate(this,event);"/>
									</td>
								</tr>
								<tr>					
									<th width="200"><h:outputLabel for="valor" value="#{properties['lb_valor']}" />:</th>
									<td width="370">
										<t:inputText converter="DoubleConverter" id="valor" value="#{MovimentarBean.movimentacao.valor}" 
											required="true" onkeyup="formatarCampoNumero(this)"/>
									</td>
								</tr>
								<tr>
									<th>Forma Pagamento:</th>
									<td>
										<t:selectOneMenu id="formaPagamento" value="#{MovimentarBean.movimentacao.formaPagamentoVO.codigo}" 
											forceId="true" onchange="testeFormaPagamento();">
											<f:selectItem itemLabel="#{properties['lb_selecione']}" itemValue=""/>
											<f:selectItems id="sel_tipos" value="#{MovimentarBean.formasPagamentos}"/>
										</t:selectOneMenu>
									</td>
								</tr>
								<tr>
									<th>Numero Cheque:</th>
									<td>
										<t:inputText id="numeroCheque" value="#{MovimentarBean.movimentacao.numeroCheque}" forceId="true"/>
									</td>
								</tr>
							</tbody>
						</table>
						
						<br />
						<t:commandLink action="#{MovimentarBean.salvar}" title="#{properties['lb_salvar']}" id="salvar" styleClass="botao_salvar"
							onclick="setAcao(document.forms[0], 'Movimentar_Conta');">
							<h:outputText value="#{properties['lb_salvar']}" />
						</t:commandLink>
						<input type="hidden" name="acao" value="Salva_Banco"/>
					</div>
				</h:form>
			</div>
		
		</body>
	</html>
</f:view>