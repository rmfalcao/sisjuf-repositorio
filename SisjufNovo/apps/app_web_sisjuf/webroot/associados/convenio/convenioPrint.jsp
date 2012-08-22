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
				<h1>Lista de Convênios</h1>
				Operador: <t:outputText value="#{ConvenioBean.operador}" />
				<br />
				Data de impressão: <t:outputText value="#{ConvenioBean.dataAtual}" />
				<br />
				Nome Fantasia: 
				<br />
				Atividade: 
				<br />
				Incluir Inativos: 
			</span>
		</div>
		<h:form id="convenioResultado">
			<t:dataTable id="data" var="convenios" value="#{ConvenioBean.convenios}" cellspacing="1" cellpadding="3" width="100%" preserveDataModel="false"
				styleClass="tabela_impressao">
										
			<t:column width="171">
				<f:facet name="header">
					<h:outputText value="#{properties['lb_nomeFantasia']}" />
				</f:facet>
					<h:outputText value="#{convenios.nomeFantasia}" />
			</t:column>

			<t:column width="91">
				<f:facet name="header">
					<h:outputText value="#{properties['lb_categoria']}" />
				</f:facet>
				<h:outputText value="#{properties['lb_geraFaturaVariavel']}"
					rendered="#{convenios.categoria == 'V'}" />
				<h:outputText value="#{properties['lb_geraFaturaFixa']}"
					rendered="#{convenios.categoria  == 'F'}" />
				<h:outputText value="#{properties['lb_naoGeraFatura']}"
					rendered="#{convenios.categoria  == 'N'}" />
			</t:column>

			<t:column width="68">
				<f:facet name="header">
					<h:outputText value="#{properties['lb_atividade']}" />
				</f:facet>
				<h:outputText value="#{convenios.atividadeConvenio.nome}" />
			</t:column>
			
			<t:column width="88">
				<f:facet name="header">
					<h:outputText value="#{properties['lb_telefone']}" />
				</f:facet>
				<h:outputText value="#{convenios.telefone}" />
			</t:column>
			
			<t:column width="68">
				<f:facet name="header">
					<h:outputText value="#{properties['lb_status']}" />
				</f:facet>
				<h:outputText value="#{properties['lb_desativo']}" rendered="#{convenios.desativado}" />
				<h:outputText value="#{properties['lb_ativo']}" rendered="#{!convenios.desativado}" />
			</t:column>

			</t:dataTable>
		</h:form>
	
	</body>
	</html>

	<script language="JavaScript">
		window.print();
	</script>
 
</f:view>