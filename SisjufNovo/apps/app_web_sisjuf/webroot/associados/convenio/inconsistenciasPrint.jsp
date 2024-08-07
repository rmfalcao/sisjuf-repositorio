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
					<h1>Relatorio de Inconsistências na Fatura <t:outputText value="#{FaturaBean.faturaProcessda.codigo}"/></h1>
					Data de impressão: <t:outputText value="#{FaturaBean.dataAtual}" />
				</span>
			</div>
			<h:form id="faturaResultado">
				<t:dataTable id="data" var="item" value="#{FaturaBean.faturaProcessda.itensInconsistentes}" cellspacing="1" cellpadding="3" width="100%" preserveDataModel="false"
					styleClass="tabela_impressao">
					<t:column width="25">
						<f:facet name="header">
							<h:outputText value="Matrícula" />
						</f:facet>
							<h:outputText value="#{item.vinculacao.codigoBeneficiarioPlano}" />
					</t:column>
					<t:column width="171">
						<f:facet name="header">
							<h:outputText value="Beneficiário" />
						</f:facet>
							<h:outputText value="#{item.vinculacao.beneficiario.nome}" />
					</t:column>
					<t:column width="50">
						<f:facet name="header">
							<h:outputText value="Titular" />
						</f:facet>
							<h:outputText value="#{item.vinculacao.associado.nome}" />
					</t:column>
					<t:column width="50">
						<f:facet name="header">
							<h:outputText value="Valor SISJUF" />
						</f:facet>
							<h:outputText value="#{item.valor }" converter="DoubleConverter"/>
					</t:column>
					<t:column width="25">
						<f:facet name="header">
							<h:outputText value="Valor fatura" />
						</f:facet>
							<h:outputText value="#{item.valorArquivo }" converter="DoubleConverter"/>
					</t:column>
					<t:column width="25">
						<f:facet name="header">
							<h:outputText value="Crítica" />
						</f:facet>
							<h:outputText value="#{item.tipoInconsistencia}"/>
					</t:column>
				</t:dataTable>
			</h:form>
		</body>
	</html>
	<script language="JavaScript">
		window.print();
	</script>
			
</f:view>