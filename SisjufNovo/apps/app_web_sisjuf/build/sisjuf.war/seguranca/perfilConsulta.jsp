<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>

<f:view>
	<f:loadBundle basename="seguranca" var="properties" />
	<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
	<html>
		<head>
			<title>SISJUF - Sistema ASSERJUF</title>
			<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
			<link href="<c:url value="/nucleo/style/sisjuf.css"/>" type="text/css" rel="stylesheet" />
			<link rel="Shortcut Icon" type="image/png" href="<c:url value="/nucleo/images/icone.png"/>">
			<script>
				function setAcao(form, acao){
					form.acao.value = acao;
				}
				
				function validaRemocao(){
					if (document.forms[0].elements['acao'].value == 'Remove_Perfil'){
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
						voltar <a href="<c:url value="/index.jsp"/>"><img src="<c:url value="/nucleo/images/setinhavoltar.gif"/>" /></a>
					</div>
				</div>
		
				<%@ include file="/nucleo/includes/inc_menuFinanceiro.jsp" %>
				
				<div id="miolo">
					<h1>Módulo Segurança</h1>
					<h2>Grupos</h2>
					<h:form id="PerfilResultado" onsubmit="return validaRemocao();">
						<t:commandLink onclick="setAcao(document.forms[0], 'Novo_Perfil');" action="#{PerfilBean.reset}" id="novo" styleClass="botao_novo">
							<h:outputText value="#{properties['lb_novo']}" />
						</t:commandLink>
						<br /><br />
					
						<t:messages id="msgs" showDetail="true" showSummary="false" errorClass="textoMsgErro" infoClass="textoMsgInfo"/>
						<t:saveState value="#{PerfilBean.perfis}" />
						<t:saveState value="#{PerfilBean.perfil}" />
						<t:dataTable id="data" var="perfis" value="#{PerfilBean.perfis}" cellspacing="1" cellpadding="2" width="100%"
							 styleClass="tab_lista" preserveDataModel="false" rowId="#{perfis.codigo}">

							<t:column width="570">
								<f:facet name="header">
									<t:outputText value="#{properties['lb_nome']}" 	/>
								</f:facet>
								<t:commandLink action="#{PerfilBean.carregar}" title="#{properties['lb_carregar']}" immediate="true" id="carregar" 
									onclick="setAcao(document.forms[0], 'Carrega_Perfil');">
									<t:updateActionListener property="#{PerfilBean.perfil.codigo}" value="#{perfis.codigo}" />
									<h:outputText value="#{perfis.nome}" />
								</t:commandLink>
							</t:column>
							
							<t:column width="20">
								<f:facet name="header">
								</f:facet>
								<t:commandLink action="#{PerfilBean.remover}" title="#{properties['lb_remover']}" immediate="true" id="remover" 
									onclick="setAcao(document.forms[0], 'Remove_Perfil');" styleClass="botao_excluir">
									<t:updateActionListener property="#{PerfilBean.perfil.codigo}" value="#{perfis.codigo}" />
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