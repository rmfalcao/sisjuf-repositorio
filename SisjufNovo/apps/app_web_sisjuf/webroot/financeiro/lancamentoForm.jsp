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
				
				function setOrigem(){
					if (document.forms[0].elements['origem'].value != "1"){
						document.forms[0].elements['tipoLancamento'].value = "";
						document.forms[0].elements['tipoLancamento'].disabled = true;
					}else{
						document.forms[0].elements['tipoLancamento'].disabled = false;
					}
				}
				
				function setOperacao(){
					if (document.forms[0].elements['operacao'].value == "1" || document.forms[0].elements['operacao'].value == "2"){
						document.forms[0].elements['LancamentoForm:dataPrevisaoInputDate'].value = "";
						document.forms[0].elements['LancamentoForm:dataPrevisaoInputDate'].disabled = true;
						document.forms[0].elements['LancamentoForm:dataEfetivacaoInputDate'].disabled = false;
					}else if (document.forms[0].elements['operacao'].value == "3" || document.forms[0].elements['operacao'].value == "4"){
						document.forms[0].elements['LancamentoForm:dataEfetivacaoInputDate'].value = "";
						document.forms[0].elements['LancamentoForm:dataEfetivacaoInputDate'].disabled = true;
						document.forms[0].elements['LancamentoForm:dataPrevisaoInputDate'].disabled = false;
					}
				}
				
				function testeFormaPagamento(){
					var form = document.forms[0];
					if (form.elements['operacao'].value == '' || form.elements['operacao'].value == '3' || form.elements['operacao'].value == '4') {
					// A CREDITAR ou A DEBITAR. Desabilita tudo.
						form.elements['formaPagamento'].options[0].selected = true;
						form.elements['formaPagamento'].disabled 		= true;
						form.elements['bancoCheque'].disabled 			= true;
						form.elements['agenciaCheque'].disabled 		= true;
						form.elements['digitoAgenciaCheque'].disabled 	= true;
						form.elements['contaCheque'].disabled 			= true;
						form.elements['digitoContaCheque'].disabled 	= true;
						form.elements['numeroCheque'].disabled 			= true;
					} else {
						form.elements['formaPagamento'].disabled 		= false;
						if (form.elements['formaPagamento'].value == '1'){ // 1 = forma pagamento CHEQUE
							form.elements['numeroCheque'].disabled = false;
							if (form.elements['operacao'].value == '1'){
							// CRÉDITO
								form.elements['bancoCheque'].disabled 			= false;
								form.elements['agenciaCheque'].disabled 		= false;
								form.elements['digitoAgenciaCheque'].disabled 	= false;
								form.elements['contaCheque'].disabled 			= false;
								form.elements['digitoContaCheque'].disabled 	= false;
							}else{
							// DÉBITO
								form.elements['bancoCheque'].disabled 			= true;
								form.elements['agenciaCheque'].disabled 		= true;
								form.elements['digitoAgenciaCheque'].disabled 	= true;
								form.elements['contaCheque'].disabled 			= true;
								form.elements['digitoContaCheque'].disabled 	= true;
							}
						} else {
							form.elements['bancoCheque'].disabled 			= true;
							form.elements['agenciaCheque'].disabled 		= true;
							form.elements['digitoAgenciaCheque'].disabled 	= true;
							form.elements['contaCheque'].disabled 			= true;
							form.elements['digitoContaCheque'].disabled 	= true;
							form.elements['numeroCheque'].disabled 			= true;						
						}
					}
				}
			</script>
		</head>
		<body onload="testeFormaPagamento();">
		
			<div id="geral">
				<div id="topo">
					<div id="sair">
						sair <a href="<c:url value="/login.jsf"/>"><img src="<c:url value="/nucleo/images/botao_x.gif"/>" /></a>
					</div>
					<div id="voltar">
						voltar <a href="<c:url value="/financeiro/lancamentoConsulta.jsf"/>">
							<img src="<c:url value="/nucleo/images/setinhavoltar.gif"/>" />
						</a>
					</div>
				</div>
		
				<%@ include file="/nucleo/includes/inc_menuFinanceiro.jsp" %>
				<h:form id="LancamentoForm">
					<div id="miolo">
						<h1>Módulo Financeiro</h1>
						<c:choose>
							<c:when test="${empty LancamentoBean.lancamento.codigo}">
								<h2>Efetuar Lançamento</h2>
							</c:when>
							<c:otherwise>
								<h2>Alterar Lançamento</h2>
							</c:otherwise>
						</c:choose>
						<t:messages id="msgs" showDetail="true" showSummary="false" errorClass="textoMsgErro" infoClass="textoMsgInfo"/>
						<table class="tab_cadastro" cellpadding="2" cellspacing="1">
							<thead>
								<tr>
									<th>Dados do Lançamento</th>
									<th colspan="3"></th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<th>Conta:</th>
									<td colspan="3">
										<h:selectOneMenu id="Conta" value="#{LancamentoBean.lancamento.contaVO.codigo}">
											<f:selectItem itemLabel="#{properties['lb_selecione']}" itemValue=""/>
											<f:selectItems id="sel_contas" value="#{LancamentoBean.contas}"/>
										</h:selectOneMenu>
									</td>
								</tr>
								<tr>
									<th>Origem:</th>
									<td colspan="3">
										<t:selectOneMenu id="origem" value="#{LancamentoBean.lancamento.origemLancamentoVO.codigo}" forceId="true" 
											onchange="setOrigem();">
											<f:selectItem itemLabel="#{properties['lb_selecione']}" itemValue=""/>
											<f:selectItems id="sel_origens" value="#{LancamentoBean.origemLancamentos}"/>
										</t:selectOneMenu>
									</td>
								</tr>
								<tr>
									<th>Tipo:</th>
									<td colspan="3">
										<t:selectOneMenu id="tipoLancamento" value="#{LancamentoBean.lancamento.tipoLancamentoVO.codigo}" 
											forceId="true">
											<f:selectItem itemLabel="#{properties['lb_selecione']}" itemValue=""/>
											<f:selectItems id="sel_tipos" value="#{LancamentoBean.tipoLancamentos}"/>
										</t:selectOneMenu>
									</td>
								</tr>
								<tr>
									<th width="160">Operação:</th>
									<td width="170">
										<t:selectOneMenu id="operacao" value="#{LancamentoBean.lancamento.tipoOperacaoVO.codigo}" forceId="true" 
											onchange="setOperacao();testeFormaPagamento();">
											<f:selectItem itemLabel="#{properties['lb_selecione']}" itemValue=""/>
											<f:selectItems id="sel_operacoes" value="#{LancamentoBean.tipoOperacoes}"/>
										</t:selectOneMenu>
									</td>
									<th width="120">Valor (R$):</th>
									<td width="140">
										<t:inputText converter="DoubleConverter" id="Valor" value="#{LancamentoBean.lancamento.valor}" 
											onkeyup="formatarCampoNumero(this)"/>
									</td>
								</tr>
								<tr>
									<th>Forma Pagamento:</th>
									<td colspan="3">
										<t:selectOneMenu id="formaPagamento" value="#{LancamentoBean.lancamento.formaPagamentoVO.codigo}" 
											forceId="true" onchange="testeFormaPagamento();">
											<f:selectItem itemLabel="#{properties['lb_selecione']}" itemValue=""/>
											<f:selectItems id="sel_formasPagamentos" value="#{LancamentoBean.formasPagamentos}"/>
										</t:selectOneMenu>
									</td>
								</tr>
								<tr>
									<th width="160">Banco do Cheque:</th>
									<td width="170">
										<t:inputText id="bancoCheque" value="#{LancamentoBean.lancamento.bancoCheque}" forceId="true" />
									</td>
									<th width="120">Agencia Cheque:</th>
									<td width="140">
										<t:inputText id="agenciaCheque" value="#{LancamentoBean.lancamento.agenciaCheque}" forceId="true" 
											size="8"/> -
										<t:inputText id="digitoAgenciaCheque" value="#{LancamentoBean.lancamento.digitoAgenciaCheque}" size="2" 
											forceId="true" />
									</td>
								</tr>
								<tr>
									<th width="160">Conta do Cheque:</th>
									<td width="170">
										<t:inputText id="contaCheque" value="#{LancamentoBean.lancamento.contaCheque}" forceId="true" 
											 size="8"/> -
										<t:inputText id="digitoContaCheque" value="#{LancamentoBean.lancamento.digitoContaCheque}" size="2" 
											forceId="true" />
									</td>
									<th width="120">Numero Cheque:</th>
									<td width="140">
										<t:inputText id="numeroCheque" value="#{LancamentoBean.lancamento.numeroCheque}" forceId="true" />
									</td>
								</tr>
								<tr>
									<th>Data de Efetivação:</th>
									<td>
										<rich:calendar id="dataEfetivacao" popup="true" datePattern="dd/MM/yyyy" showApplyButton="false"
											cellWidth="24px" cellHeight="22px" style="width:200px" disabled="false" locale="en/US"
											value="#{LancamentoBean.lancamento.dataEfetivacao}" inputClass="inputCalendar"
											enableManualInput="true" oninputblur="checkDate(this)" 
											oninputkeypress="return maskDate(this,event);"/>
									</td>
									<th>Data de Previsão:</th>
									<td>
										<rich:calendar id="dataPrevisao" popup="true" datePattern="dd/MM/yyyy" showApplyButton="false"
											cellWidth="24px" cellHeight="22px" style="width:200px" disabled="false" locale="en/US"
											value="#{LancamentoBean.lancamento.dataPrevisao}" inputClass="inputCalendar"
											enableManualInput="true" oninputblur="checkDate(this)" 
											oninputkeypress="return maskDate(this,event);"/>
									</td>
								</tr>
								<tr>
									<th valign="top">Descrição:</th>
									<td colspan="3">
										<t:inputTextarea id="descricao" cols="50" rows="4" value="#{LancamentoBean.lancamento.descricao}">
										</t:inputTextarea>
									</td>
								</tr>
							</tbody>
						</table>
						
						<br />
						<t:commandLink action="#{LancamentoBean.salvar}" title="#{properties['lb_salvar']}" id="salvar" styleClass="botao_salvar"
							onclick="setAcao(document.forms[0], 'Salva_Lancamento');">
							
							<h:outputText value="#{properties['lb_salvar']}" />
							
							
						</t:commandLink>
						
					</div>
					<input type="hidden" name="acao" value=""/>
					<h:inputHidden id="codigo" value="#{LancamentoBean.lancamento.codigo}"/>
					<h:inputHidden id="datasVerificadas" value="#{LancamentoBean.lancamento.datasVerificadas}"/>
				</h:form>
			</div>
		
		</body>
	</html>
</f:view>