<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<%@ taglib prefix="a4j" uri="http://richfaces.org/a4j"%>

<script type="text/javascript">

	function closeDocumentosModal(){
		Richfaces.hideModalPanel('panelDocumentosAssociado');
	}

	
</script>

<rich:modalPanel id="panelDocumentosAssociado" width="620" height="450">

	<f:facet name="header">
		<h:panelGroup><h:outputText value="Upload de Documento de Associado"></h:outputText></h:panelGroup>
	</f:facet>
	
	<f:facet name="controls">
		<h:graphicImage value="/nucleo/images/close.png" style="cursor:pointer" id="hideDocumentoModal" 
			onclick="Richfaces.hideModalPanel('panelDocumentosAssociado')"/>
	</f:facet>
	
	<h:form id="documentosAssociadoForm">
		<t:div  id="documentosAssociadoFormMiolo">
			<h1>Modulo Associado</h1>
			<h2>Documento de Associado</h2>
			<table class="tab_cadastro" cellpadding="2" cellspacing="1">
				<thead>
					<tr>
						<th>Dados do Documento</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
				
					<tr>
						<th><h:outputLabel for="nomeDocumento" value="#{properties['lb_nomeDoDocumento']}" />:</th>
						<td>
							<t:inputText id="nomeDocumento" value="#{AssociadoBean.documento.nome}" maxlength="80" />
						</td>
					</tr>
					<tr>
						<th><h:outputLabel for="dataDocumento" value="#{properties['lb_dataDocumento']}" />:</th>
						<td>
							<rich:calendar id="dataDocumento" popup="true" datePattern="dd/MM/yyyy" showApplyButton="false"
								cellWidth="24px" cellHeight="22px" style="width:200px" disabled="false" locale="en/US"
								value="#{AssociadoBean.documento.dataDocumento}" inputClass="inputCalendar" 
								enableManualInput="true" oninputblur="checkDate(this)" 
								oninputkeypress="return maskDate(this,event);" />
						</td>
					</tr>
					<tr>
						<th><h:outputLabel for="arquivoDocumentoAssociado" value="#{properties['lb_arquivo']}" />:</th>
						<td>
							<rich:fileUpload id="arquivoDocumentoAssociado" maxFilesQuantity="1" styleClass="teste" immediateUpload="true" 
											fileUploadListener="#{AssociadoBean.upload}" uploadData="#{AssociadoBean.data}" />
											
							
						</td>
					</tr>
				</tbody>
			</table>
			<br />
			<a4j:commandButton id="salvar" styleClass="botao_salvar" action="#{AssociadoBean.salvarDocumento}"  reRender="div_associadodocumentosPesquisa" 
				oncomplete="closeDocumentosModal();" onclick="#{rich:component('confirmation_doc')}.show();return false" />
			<h:inputHidden id="VAssociado" value="#{AssociadoBean.documento.associado.codigo}"/>
		</t:div>
		<rich:modalPanel id="confirmation_doc" width="250" height="150">
			   <f:facet name="header">Confirmação</f:facet>
			   <h:panelGrid>
			      <h:panelGrid columns="1">
				 	<h:outputText value="Deseja confirmar a ação?" style="FONT-SIZE: large;" />
			      </h:panelGrid>
			      <h:panelGroup>
			      		<a4j:commandButton  value="Salvar" action="#{AssociadoBean.salvarDocumento}"  reRender="div_associadodocumentosPesquisa" oncomplete="#{rich:component('confirmation_doc')}.hide();closeDocumentosModal();"/>
			            <a4j:commandButton  value="Cancelar" onclick="#{rich:component('confirmation_doc')}.hide();return false" />
				  </h:panelGroup>
			   </h:panelGrid>
			</rich:modalPanel>
			
	</h:form>
</rich:modalPanel>