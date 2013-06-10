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
				<h1>Lista de Planos</h1>
				Operador: <t:outputText value="#{ConvenioBean.operador}" />
				<br />
				Data de impressão: <t:outputText value="#{ConvenioBean.dataAtual}" />
				<br />
				Convênio: <t:outputText value="#{ConvenioBean.convenio.nomeFantasia}" />
			</span>
		</div>
		<h:form id="planoResultado">
			<t:dataTable id="data" var="planos" value="#{ConvenioBean.printPlanosConvenio}" cellspacing="1" cellpadding="3" width="100%" preserveDataModel="false"
				styleClass="tabela_impressao">
										
			<t:column width="171">
				<f:facet name="header">
					<h:outputText value="#{properties['lb_nome']}" />
				</f:facet>
					<h:outputText value="#{planos.nome}" />
			</t:column>

			<t:column width="50%">
				<f:facet name="header">
					<h:outputText value="#{properties['lb_descricao']}" />
				</f:facet>
					<h:outputText value="#{planos.descricao}" />
			</t:column>

			<t:column width="10%">
				<f:facet name="header">
					<h:outputText value="#{properties['lb_valor']}" />
				</f:facet>
				<h:outputText value="#{planos.valor}" converter="DoubleConverter" />
			</t:column>

			<t:column width="68">
				<f:facet name="header">
					<h:outputText value="#{properties['lb_status']}" />
				</f:facet>
				<h:outputText  value="#{properties['lb_desativo']}" rendered="#{planos.flagDesativado}" />
				<h:outputText value="#{properties['lb_ativo']}" rendered="#{!planos.flagDesativado}" />
			</t:column>

			</t:dataTable>
		</h:form>
	
	</body>
	</html>
 
	<script language="JavaScript">
		window.print();
	</script>
 
</f:view>