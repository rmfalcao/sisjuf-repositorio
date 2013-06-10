<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>

<f:view>
	<f:loadBundle basename="sisjuf" var="properties" />
	<html>
		<head>
			<title>SISJUF - Sistema ASSERJUF</title>
			<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
			<link href="<c:url value="/nucleo/style/sisjuf.css"/>" type="text/css" rel="stylesheet" />
			<link rel="Shortcut Icon" type="image/png" href="<c:url value="/nucleo/images/icone.png"/>">
			<script>
				
				
				function imprimir() {
					var url			= '<c:url value="/Financeiro/Contas/Imprimir"/>';
					var name		= 'telaImpressao';
					var features	= 'toolbar=no,status=no,resizable=yes,scrollbars=yes,width=750,height=500';
					window.open(url,name,features);
				}
				
				function setAcao(form, acao){
					form.acao.value = acao;
				}
				
				function validaRemocao(){
					if (document.forms[0].elements['acao'].value == 'Remove_Conta'){
						if (confirm("Deseja realmente remover este registro?")){
							return true;
						} else return false;
					}return true;
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
					<h2>Contas</h2>
					<a class="botao_novo" onclick="setAcao(document.forms[0], 'Nova_Conta')" href="<c:url value="/financeiro/contaForm.jsf"/>"><span>novo</span></a>
					<br /><br />
					<t:messages id="msgs" showDetail="true" showSummary="false" errorClass="textoMsgErro" infoClass="textoMsgInfo"/>
					<h:form id="ContaBancoResultado" onsubmit="return validaRemocao();">
						<t:saveState value="#{ContaBancoBean.contas}" />
						<t:saveState value="#{ContaBancoBean.conta}" />
						<t:dataTable id="data" var="contas" value="#{ContaBancoBean.contas}"
							cellspacing="1" cellpadding="2" width="100%" styleClass="tab_lista"
							preserveDataModel="false" rowId="#{contas.codigo}">
							
							<f:facet name="footer">
					            <h:outputLink value="javascript:void(0);" onclick="imprimir();"  styleClass="botao_imprimir" title="#{properties['lb_imprimir']}">
					            </h:outputLink>
					        </f:facet>
							
							<t:column width="120">
								<f:facet name="header">
									<h:outputText value="#{properties['lb_contaNome']}" />
								</f:facet>
								<t:commandLink action="#{ContaBancoBean.carregar}" title="#{properties['lb_carregar']}" immediate="true" id="carregar" 
									onclick="setAcao(document.forms[0], 'Carrega_Conta');">
									<t:updateActionListener property="#{ContaBancoBean.conta.codigo}" value="#{contas.codigo}" />
									<h:outputText value="#{contas.nome}" />
								</t:commandLink>
							</t:column>
							
							<t:column width="110">
								<f:facet name="header">
									<h:outputText value="#{properties['lb_banco']}" />
								</f:facet>
								<h:outputText value="#{contas.bancoVO.sigla}" />
							</t:column>
							
							<t:column width="70">
								<f:facet name="header">
									<h:outputText value="#{properties['lb_nAgencia']}" />
								</f:facet>
								<h:outputText value="#{contas.numAgencia}" />
							</t:column>
							
							<t:column width="70">
								<f:facet name="header">
									<h:outputText value="#{properties['lb_nConta']}" />
								</f:facet>
								<h:outputText value="#{contas.numConta}" />
							</t:column>
							
							<t:column width="200">
								<f:facet name="header">
									<h:outputText value="#{properties['lb_nomeTitular']}" />
								</f:facet>
								<h:outputText value="#{contas.nomTitular}" />
							</t:column>
							
							<t:column width="20">
								<f:facet name="header">
								</f:facet>
								<t:commandLink action="#{ContaBancoBean.remover}" title="#{properties['lb_remover']}" immediate="true" id="remover" 
									onclick="setAcao(document.forms[0], 'Remove_Conta');" styleClass="botao_excluir">
									<t:updateActionListener property="#{ContaBancoBean.conta.codigo}" value="#{contas.codigo}" />
								</t:commandLink>
							</t:column>
						</t:dataTable>
						<input type="hidden" name="acao" value=""/>
					</h:form>
				</div>
			</div>
		</body>
	</html>
</f:view>