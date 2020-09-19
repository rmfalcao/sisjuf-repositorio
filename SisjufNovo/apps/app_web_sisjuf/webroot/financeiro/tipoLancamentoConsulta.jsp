<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>

<f:view>
	<f:loadBundle basename="sisjuf" var="properties" />
	<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
	<html>
		<head>
			<title>SISJUF - Sistema ASSERJUF</title>
			<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
			<link href="<c:url value="/nucleo/style/sisjuf.css"/>" type="text/css" rel="stylesheet" />
			<link rel="Shortcut Icon" type="image/png" href="<c:url value="/nucleo/images/icone.png"/>">
			<script>
			
				function imprimir() {
					var url			= '<c:url value="/Financeiro/TiposLancamento/Imprimir"/>';
					var name		= 'telaImpressao';
					var features	= 'toolbar=no,status=no,resizable=yes,scrollbars=yes,width=750,height=500';
					window.open(url,name,features);
				}
			 
				function setAcao(form, acao){
					form.acao.value = acao;
				}
				
				function validaRemocao(){
					if (document.forms[0].elements['acao'].value == 'Remove_TipoLancamento'){
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
						sair <a href="<c:url value="/seguranca/logout.jsp"/>"><img src="<c:url value="/nucleo/images/botao_x.gif"/>" /></a>
					</div>
					<div id="voltar">
						voltar <a href="<c:url value="/financeiro/index.jsp"/>"/><img src="<c:url value="/nucleo/images/setinhavoltar.gif"/>" /></a>
					</div>
				</div>
		
				<%@ include file="/nucleo/includes/inc_menuFinanceiro.jsp" %>
		
				<div id="miolo">
					<h1>Módulo Financeiro</h1>
					<h2>Tipos de Lançamentos</h2>
					<a class="botao_novo" onclick="setAcao(document.forms[0],'Novo_TipoLancamento')" href="<c:url value="/financeiro/tipoLancamentoForm.jsf"/>"><span>novo</span></a>
					<br /><br />
					<t:messages id="msgs" showDetail="true" showSummary="false" errorClass="textoMsgErro" infoClass="textoMsgInfo"/>
					<h:form id="TipoLancamentoResultado" onsubmit="return validaRemocao();">
						<t:saveState value="#{TipoLancamentoBean.tipoLancamentos}" />
						<t:saveState value="#{TipoLancamentoBean.tipoLancamento}" />
						<t:dataTable id="data" var="tipoLancamentos" value="#{TipoLancamentoBean.tipoLancamentos}"
						cellspacing="1" cellpadding="2" width="100%" styleClass="tab_lista"
						preserveDataModel="false" rowId="#{tipoLancamentos.codigo}">
							
							<f:facet name="footer">
					            <h:outputLink value="javascript:void(0);" onclick="imprimir();" styleClass="botao_imprimir" title="#{properties['lb_imprimir']}">
					            </h:outputLink>
					        </f:facet>
							
							<t:column  width="570">
								<f:facet name="header">
									<h:outputText value="#{properties['lb_tipoLancamento']}" />
								</f:facet>
								<t:commandLink action="#{TipoLancamentoBean.carregar}" title="#{properties['lb_carregar']}" immediate="true" 
									id="carregar"  onclick="setAcao(document.forms[0], 'Carrega_TipoLancamento');">
									<t:updateActionListener property="#{TipoLancamentoBean.tipoLancamento.codigo}" value="#{tipoLancamentos.codigo}" />
									<h:outputText value="#{tipoLancamentos.nome}" />
								</t:commandLink>
							</t:column>
							
							<t:column  width="20">
								<f:facet name="header">
								</f:facet>
								<t:commandLink action="#{TipoLancamentoBean.remover}" title="#{properties['lb_remover']}" immediate="true" id="remover" 
									onclick="setAcao(document.forms[0], 'Remove_TipoLancamento');" styleClass="botao_excluir">
									<t:updateActionListener property="#{TipoLancamentoBean.tipoLancamento.codigo}" value="#{tipoLancamentos.codigo}" />
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