<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<%@ taglib prefix="a4j" uri="http://richfaces.org/a4j"%>

<script type="text/javascript">

	function closeItemFaturaModal(){
		Richfaces.hideModalPanel('faturaPrevia');
	}

	function validarCamposObrigatorios(){
		var nome = document.getElementById('planoConvenioForm:nome').value;
		if (nome == null || nome =='' ){
			alert('Favor preencher o campo nome.' );
			return false;
		}

		var valor = document.getElementById('planoConvenioForm:valor').value;
		if (valor == null || valor =='' ){
			alert('Favor preencher o campo valor.' );
			return false;
		}
		return true;
	}
</script>

<rich:modalPanel id="faturaPrevia" width="620" height="410">

	<f:facet name="header">
		<h:panelGroup><h:outputText value="Fatura - Aprovar itens da fatura"></h:outputText></h:panelGroup>
	</f:facet>
	
	<f:facet name="controls">
		<h:graphicImage value="/nucleo/images/close.png" style="cursor:pointer" id="hideFaturaPreviaModal" 
			onclick="Richfaces.hideModalPanel('faturaPrevia')"/>
	</f:facet>
	
	<h:form id="faturaPreviaForm">
		<t:div  id="faturaPreviaFormMiolo">
			<h1>Fatura</h1>
			<h2>Itens da Fatura</h2>
			
							<rich:dataTable value="#{FaturaBean.fatura.itens}" var="itens" border="0" id="itensFaturaPrevia" width="100%">
								<f:facet name="header">
									<rich:columnGroup>
									<rich:column><h:outputText value="Número" /></rich:column>
									<rich:column><h:outputText value="Beneficiário" /></rich:column>
									<rich:column><h:outputText value="Titular" /></rich:column>
									<rich:column><h:outputText value="Valor" /></rich:column>
									<rich:column></rich:column>
									</rich:columnGroup>
								</f:facet>
								
								<rich:column style="width:15%">
									<h:outputText value="#{itens.numero}" />
								</rich:column>
								
								<rich:column style="width:40%">
									<h:outputText value="#{itens.beneficiario.nome}" />
								</rich:column>
								
								<rich:column style="width:30%">
									<h:outputText value="#{itens.beneficiario.titular.nome}"/>
								</rich:column>
								
								<rich:column style="width:10%">
									<h:outputText value="#{itens.valor}" converter="DoubleConverter"/>
								</rich:column>
								
								<rich:column style="text-align:center; width:2%">
									<a4j:commandLink action="#{FaturaBean.removerItemFatura}" title="#{properties['lb_remover']}" 
										immediate="true" id="remover" styleClass="botao_excluir" reRender="itensFatura">
										<t:updateActionListener property="#{FaturaBean.itemFatura.numero}" value="#{itens.numero}" />
									</a4j:commandLink>
								</rich:column>
							</rich:dataTable>
			
			<br />
			<h:commandButton id="salvar" styleClass="botao_salvar" action="#{FaturaBean.gerarFaturaFixa}" /><!-- oncomplete="closeItemFaturaModal();"  reRender="itensFatura,:gerarFaturaForm"-->
		</t:div>
	</h:form>
</rich:modalPanel>