<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<f:view>
	<f:loadBundle basename="sisjuf" var="properties" />
	<html>
		<head>
			<title>SISJUF - IMPRESSÃO</title>
			<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
			<link href="<c:url value="/nucleo/style/impressao.css"/>" type="text/css" rel="stylesheet" />
			<link rel="Shortcut Icon" type="image/png" href="imagens/icone.png">
		</head>
		
		<body>
	
		<div id="logo_impressao"><img src="<c:url value="/nucleo/images/logo_impressao.gif"/>" /></div>
		<div id="topo_impressao">
			<strong>Sistema da Associação dos Servidores da Justiça Federal na Bahia</strong>
	
			<span>
				<h1>Lista de Associados (<h:outputText value="#{AssociadoBean.size}" />)</h1>
				<!-- Esses dados são vocês que irão determinar o que será exibido -->
				Data de impressão: <t:outputText value="#{AssociadoBean.dataAtual}" />
				<br />
				Operador: <t:outputText value="#{AssociadoBean.operador}" />
			</span>
		</div>
		<h:form id="associadoResultado">
			<t:dataTable id="data" var="associados" value="#{AssociadoBean.associados}" cellspacing="1" cellpadding="3" width="100%" preserveDataModel="false"
				styleClass="tabela_impressao">
										
				<t:column width="55">
					<f:facet name="header">
						<h:outputText value="#{properties['lb_matricula']}" />
					</f:facet>
					<h:outputText value="#{associados.codigo}" />
				</t:column>
	
				<t:column width="225">
					<f:facet name="header">
						<h:outputText value="#{properties['lb_nomeCompleto']}" />
					</f:facet>
					<h:outputText value="#{associados.nome}" />
				</t:column>
				
				<t:column width="90">
					<f:facet name="header">
						<h:outputText value="#{properties['lb_associadoEm']}" />
					</f:facet>
					<h:outputText value="#{associados.dataAssociacao}" />
				</t:column>
				
				<t:column width="200">
					<f:facet name="header">
						<h:outputText value="#{properties['lb_setorOrgao']}" />
					</f:facet>
					<h:outputText value="#{associados.setor.nome}" /> / <h:outputText value="#{associados.setor.orgao.nome}" />
				</t:column>
			</t:dataTable>
		</h:form>
	
	</body>
	</html>
	<script language="JavaScript">
		window.print();
	</script>
</f:view>