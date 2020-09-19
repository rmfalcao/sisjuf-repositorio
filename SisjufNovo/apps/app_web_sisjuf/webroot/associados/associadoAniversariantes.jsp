<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>

<f:view>
	<f:loadBundle basename="sisjuf" var="properties" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
	<html>
		<head>
			<title>SISJUF - Sistema ASSERJUF</title>
			<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
			<link href="<c:url value="/nucleo/style/sisjuf.css"/>" type="text/css"	rel="stylesheet" />
			<link rel="Shortcut Icon" type="image/png" href="<c:url value="/nucleo/images/icone.png"/>">
			<script>
				function setAcao(form, acao){
					form.acao.value = acao;
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
						voltar <a href="#"><img src="<c:url value="/nucleo/images/setinhavoltar.gif"/>" /></a>
					</div>
				</div>
		
				<%@ include file="/nucleo/includes/inc_menuFinanceiro.jsp"%>
		
				<div id="miolo">
					<h1>Módulo Associados</h1>
					<h2>Aniversariantes</h2>
					
					<h:form id="associadoResultado">
						<t:saveState value="#{AssociadoBean.associadosAniversariantes}" />
						<t:saveState value="#{AssociadoBean.associado}" />
						<t:dataTable id="data" var="associados" value="#{AssociadoBean.associadosAniversariantes}" cellspacing="1" cellpadding="2" width="100%" 
							styleClass="tab_lista_maior" preserveDataModel="false">		
								<t:column width="140">
									<f:facet name="header">
										<h:outputText value="#{properties['lb_matricula']}" />
									</f:facet>
									<h:outputText value="#{associados.codigo}" />
									<f:facet name="footer">
										<t:panelGroup>
											<t:commandLink onclick="setAcao(document.forms[0], 'Imprime_Aniversariantes');" action="#{AssociadoBean.printAniversariantes}" title="#{properties['lb_carregar']}" immediate="true" id="print"  styleClass="botao_imprimir" 
												target="print">
											</t:commandLink>
											<t:commandLink onclick="setAcao(document.forms[0], 'Envia_Email');" action="#{AssociadoBean.carregarEmailAniversariantes}" title="#{properties['lb_enviarEmail']}" immediate="true" id="carregarEmail">
												<t:graphicImage value="/nucleo/images/icon_email.gif" border="0"></t:graphicImage>
											</t:commandLink>
										</t:panelGroup>
							        </f:facet>
								</t:column>
					
								<t:column width="230">
									<f:facet name="header">
										<h:outputText value="#{properties['lb_nomeAssociado']}" />
									</f:facet>
									<t:commandLink action="#{AssociadoBean.carregar}" title="#{properties['lb_carregar']}" immediate="true" id="carregar" 
											onclick="setAcao(document.forms[0], 'Carrega_Aniversariantes');">
										<h:outputText value="#{associados.nome}" />
									</t:commandLink>
								</t:column>
								
								<t:column width="100">
									<f:facet name="header">
										<h:outputText value="#{properties['lb_dataNasc']}" />
									</f:facet>
									<h:outputText value="#{associados.dataNascimento}" />
								</t:column>
								
								<t:column width="100">
									<f:facet name="header">
										<h:outputText value="#{properties['lb_idade']}" />
									</f:facet>
									<h:outputText value="#{associados.idade}" /> 
								</t:column>
							</t:dataTable>
							<input type="hidden" name="acao" value="Carrega_Aniversariantes"/>
						</h:form>
				</div>
			</div>
		</body>
	</html>
</f:view>