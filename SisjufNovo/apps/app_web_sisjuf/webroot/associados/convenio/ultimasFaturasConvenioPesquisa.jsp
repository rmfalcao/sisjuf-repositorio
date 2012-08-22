<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<%@ taglib prefix="a4j" uri="http://richfaces.org/a4j"%>

<script type="text/javascript">
</script>

<f:subview id="ultimasfaturasConvenioPesquisa">
	<h:form>
		<t:div id="div_planos" styleClass="conteudo">&nbsp;

		<h2>Lista das últimas faturas</h2>
		<t:messages id="msgs" showDetail="true" showSummary="false" errorClass="textoMsgErro" 
			infoClass="textoMsgInfo"/>

		<t:dataTable id="planos" var="planos" value="#{ConvenioBean.ultimasFaturas}"
			cellspacing="1" cellpadding="2" width="100%" styleClass="tab_lista" 
			preserveDataModel="true" rowId="#{ultimasFaturas.codigo}">

			<f:facet name="footer">
				<h:outputLink value="javascript:void(0);" onclick="imprimir();"
					styleClass="botao_imprimir" title="#{properties['lb_imprimir']}">
				</h:outputLink>
			</f:facet>

			<t:column width="10%">
				<f:facet name="header">
					<h:outputText value="#{properties['lb_valor']}" />
				</f:facet>
				<h:outputText value="#{ultimasFaturas.valor}" converter="DoubleConverter" />
			</t:column>

		</t:dataTable></t:div>
	</h:form>
</f:subview>