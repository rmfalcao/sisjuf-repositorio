<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>

<f:subview id="hitoricoValorPlano">
	<f:loadBundle basename="sisjuf" var="properties" />
	<t:div  id="historicoValorPlanoMiolo">
		<t:dataTable id="data" var="historico" value="#{ConvenioBean.planoConvenio.historicoValorPlanoConvenio}"
			cellspacing="1" cellpadding="2" width="100%" styleClass="tab_lista"
			preserveDataModel="false" rowId="#{atividades.codigo}" >
			
			<f:facet name="footer">
	            <h:outputLink value="javascript:void(0);" onclick="imprimir();" styleClass="botao_imprimir" title="#{properties['lb_imprimir']}">
	            </h:outputLink>
	        </f:facet>
			
			<t:column width="50%">
				<f:facet name="header">
					<h:outputText value="#{properties['lb_valor']}" />
				</f:facet>
					<h:outputText value="#{historico.valor}" converter="DoubleConverter"/>
			</t:column>
			
			<t:column width="50%">
				<f:facet name="header">
					<h:outputText value="#{properties['lb_dataInicio']}" />
				</f:facet>
					<h:outputText value="#{historico.dataInicio}" />
			</t:column>
		</t:dataTable>
	</t:div>
</f:subview>