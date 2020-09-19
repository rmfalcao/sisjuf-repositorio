<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
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
						if ('<t:outputText value="#{LancamentoBean.lancamento.tipoOperacaoVO.codigo}"/>' == '1' || '<t:outputText value="#{LancamentoBean.lancamento.tipoOperacaoVO.codigo}"/>' == '3'){
							form.elements['bancoCheque'].disabled 			= false;;
							form.elements['agenciaCheque'].disabled 		= false;
							form.elements['digitoAgenciaCheque'].disabled 	= false;
							form.elements['contaCheque'].disabled 			= false;
							form.elements['digitoContaCheque'].disabled 	= false;
						}else{
							form.elements['bancoCheque'].disabled 			= true;;
							form.elements['agenciaCheque'].disabled 		= true;
							form.elements['digitoAgenciaCheque'].disabled 	= true;
							form.elements['contaCheque'].disabled 			= true;
							form.elements['digitoContaCheque'].disabled 	= true;
						}
					}else{
						form.elements['bancoCheque'].disabled 			= true;;
						form.elements['agenciaCheque'].disabled 		= true;
						form.elements['digitoAgenciaCheque'].disabled 	= true;
						form.elements['contaCheque'].disabled 			= true;
						form.elements['digitoContaCheque'].disabled 	= true;
						form.elements['numeroCheque'].disabled 			= true;
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
						voltar <a href="<c:url value="/financeiro/lancamentoConsulta.jsf"/>"/><img src="<c:url value="/nucleo/images/setinhavoltar.gif"/>" /></a>
					</div>
				</div>
		
				<%@ include file="/nucleo/includes/inc_menuFinanceiro.jsp" %>
				
				<h:form id="BaixarForm">
					<div id="radio" style="display:none">
						<t:selectOneRadio id="tipoBaixa" value="#{LancamentoBean.tipoBaixa}">
							<f:selectItem itemValue="1" itemLabel="" />
							<f:selectItem itemValue="2" itemLabel="" />
						</t:selectOneRadio>
					</div>
					<div id="miolo">
						<h1>Módulo Financeiro</h1>
						<h2>Realizar Baixa</h2>
						<t:messages id="msgs" showDetail="true" showSummary="false" errorClass="textoMsgErro" infoClass="textoMsgInfo"/>
						<table class="tab_cadastro" cellpadding="2" cellspacing="1">
							<thead>
								<tr>
									<th>Realizar Baixa</th>
									<th colspan="2"></th>
								</tr>
							</thead>
							<tbody>
								<tr>					
									<th width="200"><h:outputLabel for="numero" value="#{properties['lb_valorTotal']}" />:</th>
									<td width="20">
										<t:radio for="tipoBaixa" index="0" />
									</td>
									<td width="370" colspan="2">
										<t:inputText id="valorTotal" value="#{LancamentoBean.valorTotal}" size="15" converter="DoubleConverter" onkeypress="return false;"/>
									</td>
								</tr>
								<tr>
									<th><h:outputLabel for="numero" value="#{properties['lb_valorParcial']}" />:</th>
									<td><t:radio for="tipoBaixa" index="1" /></td>
									<td colspan="2">
										<t:inputText id="valorParcial" size="15" converter="DoubleConverter" value="#{LancamentoBean.valorParcial}" onkeyup="formatarCampoNumero(this)"/>
									</td>
								</tr>
								<tr>
									<th><h:outputLabel for="numero" value="#{properties['lb_data']}" />:</th>
									<td colspan="3">
										<rich:calendar id="data" popup="true" datePattern="dd/MM/yyyy" showApplyButton="false"
											cellWidth="24px" cellHeight="22px" style="width:200px" disabled="false" locale="en/US"
											value="#{LancamentoBean.baixa.data}" inputClass="inputCalendar" oninputblur="checkDate(this)" 
											oninputkeypress="return maskDate(this,event)" enableManualInput="true"/>
									</td>
								</tr>
								<tr>
									<th>Forma Pagamento:</th>
									<td colspan="3">
										<t:selectOneMenu id="formaPagamento" value="#{LancamentoBean.baixa.formaPagamentoVO.codigo}" 
											forceId="true" onchange="testeFormaPagamento();">
											<f:selectItem itemLabel="#{properties['lb_selecione']}" itemValue=""/>
											<f:selectItems id="sel_tipos" value="#{LancamentoBean.formasPagamentos}"/>
										</t:selectOneMenu>
									</td>
								</tr>
								<tr>
									<th width="160">Banco do Cheque:</th>
									<td width="170">
										<t:inputText id="bancoCheque" value="#{LancamentoBean.baixa.bancoCheque}" forceId="true" />
									</td>
									<th width="120">Agencia Cheque:</th>
									<td width="140">
										<t:inputText id="agenciaCheque" value="#{LancamentoBean.baixa.agenciaCheque}" forceId="true" size="8"/> -
										<t:inputText id="digitoAgenciaCheque" value="#{LancamentoBean.baixa.digitoAgenciaCheque}" size="2" 
											forceId="true"/>
									</td>
								</tr>
								<tr>
									<th width="160">Conta do Cheque:</th>
									<td width="170">
										<t:inputText id="contaCheque" value="#{LancamentoBean.baixa.contaCheque}" forceId="true" size="8"/> -
										<t:inputText id="digitoContaCheque" value="#{LancamentoBean.baixa.digitoContaCheque}" size="2" 
											forceId="true" />
									</td>
									<th width="120">Numero Cheque:</th>
									<td width="140">
										<t:inputText id="numeroCheque" value="#{LancamentoBean.baixa.numeroCheque}" forceId="true"/>
									</td>
								</tr>
							</tbody>
						</table>
						
						<br />
						<t:commandLink action="#{LancamentoBean.salvarBaixa}" title="#{properties['lb_salvar']}" id="salvar" styleClass="botao_salvar"
							onclick="setAcao(document.forms[0], 'Salva_Baixar');">
							<h:outputText value="#{properties['lb_salvar']}" />
						</t:commandLink>
						<h:inputHidden id="codigo" value="#{LancamentoBean.lancamento.codigo}"/>
						<input type="hidden" name="acao" value="Salva_Banco"/>
					</div>
				</h:form>
			</div>
		</body>
	</html>
</f:view>