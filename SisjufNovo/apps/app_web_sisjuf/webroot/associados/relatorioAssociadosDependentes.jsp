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
						sair <a href="<c:url value="/login.jsf"/>"><img src="<c:url value="/nucleo/images/botao_x.gif"/>" /></a>
					</div>
					<div id="voltar">
						voltar <a href="#"><img src="<c:url value="/nucleo/images/setinhavoltar.gif"/>" /></a>
					</div>
				</div>
		
				<%@ include file="/nucleo/includes/inc_menuFinanceiro.jsp"%>
		
				<div id="miolo">
					<h1>Módulo Associados</h1>
					<h2>Relatório de Associados e Dependentes</h2>
					
					<h:form id="relatorioAssociadosDependentes">
						<t:saveState value="#{AssociadoBean.relatorioAssociadosDependentes}" />
						<t:dataTable id="data" var="relatorio" value="#{AssociadoBean.relatorioAssociadosDependentes}" cellspacing="1" cellpadding="2" width="100%" 
							styleClass="tab_lista" preserveDataModel="false">		
								<t:column width="70">
									<f:facet name="header">
										<h:outputText value="#{properties['lb_faixa_etaria']}" />
									</f:facet>
									<h:outputText value="#{relatorio.faixaEtaria}" />
									
								</t:column>
					
								<t:column width="118">
									<f:facet name="header">
										<h:outputText value="#{properties['lb_qtd_associados']}" />
									</f:facet>
									<h:outputText value="#{relatorio.quantidadeAssociados}" />
								</t:column>
								
								<t:column width="118">
									<f:facet name="header">
										<h:outputText value="#{properties['lb_qtd_dependentes']}" />
									</f:facet>
									<h:outputText value="#{relatorio.quantidadeDependentes}" />
								</t:column>
								
							</t:dataTable>
							<input type="hidden" name="acao" value="Carrega_RelatorioAssociadosDependentes"/>
						</h:form>
				</div>
			</div>
		</body>
	</html>
</f:view>