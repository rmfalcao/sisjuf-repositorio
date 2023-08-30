<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<%@ taglib prefix="a4j" uri="http://richfaces.org/a4j"%>

<c:url value="/Associados/Documento/Download" var="myUrl">
  <c:param name="codigo" value="value1" />
  <c:param name="param2" value="value2" />
</c:url>

<script type="text/javascript">

	function downloadDocumento(codigo) {
		window.open('<c:url value="/Associados/Documento/Download"/>?codigo=' + codigo, '_blank');
		return false;
	}
	
	function openModalDocumentoAssociado(){
		
		
		//if (document.getElementById('planoConvenioPesquisa:planoPesquisaForm:CodConvenio').value != ''){
		Richfaces.showModalPanel('panelDocumentosAssociado',{width:600, top:200 });
	}

	function novoDocumento(){
		if (document.getElementById('associadoForm:codigo').value == '' || document.getElementById('associadoForm:codigo').value == '1'){
			alert("Cadastre um associado antes de tentar cadastrar um documento");
			return false;
		}
	}

	function imprimir() {
		//document.getElementById('associadovinculacaoPesquisa:vinculacaoFPesquisa').target = '_blank';
		
	}
</script>
<f:subview id="associadoDocumentosPesquisa">
	<h:form id="associadoDocPesquisa">
		<t:saveState value="#{AssociadoBean.associado}" id="associadoSaved"/>
		<a4j:keepAlive beanName="AssociadoBean" />
		<t:div id="div_associadodocumentosPesquisa" styleClass="conteudo">&nbsp;
			<h2>Lista de Documentos</h2>
			<a4j:commandButton oncomplete="openModalDocumentoAssociado();" action="#{AssociadoBean.prepararNovoDocumentoAssociado}" reRender="documentosAssociadoFormMiolo" styleClass="botao_novo" 
		    	value="novo" onclick="if (document.getElementById('associadoForm:codigo').value == ''){ novoDocumento(); return false;}"/>
	
			<t:messages id="msgs" showDetail="true" showSummary="false"
				errorClass="textoMsgErro" infoClass="textoMsgInfo" />
	
			<t:dataTable id="data" var="documentos" value="#{AssociadoBean.documentos}" cellspacing="1" cellpadding="2" width="100%" 
				styleClass="tab_lista_maior" preserveDataModel="true" rowId="#{documentos.codigo}">
				
				<f:facet name="footer">
		            <h:commandLink value="" onclick="imprimir();" styleClass="botao_imprimir" title="#{properties['lb_imprimir']}" 
		            	action="#{AssociadoBean.printDocumentosAssociado}"/>
		        </f:facet>
				
				<t:column width="80"> 
					<f:facet name="header">
						<h:outputText value="#{properties['lb_nome']}" />
					</f:facet>
					<a4j:commandLink value="#{documentos.nome}" onclick="downloadDocumento(#{documentos.codigo});">
	 				</a4j:commandLink>
				</t:column>
				<t:column width="9">
					<f:facet name="header">
						<h:outputText value="#{properties['lb_data']}" />
					</f:facet>
					<h:outputText value="#{documentos.dataDocumento}" />
				</t:column>
				<t:column width="9">
					<f:facet name="header">
						<h:outputText value="#{properties['lb_dataCadastro']}" />
					</f:facet>
					<h:outputText value="#{documentos.dataCriacao}" />
				</t:column>
				<t:column width="2">
					<f:facet name="header">
					
					</f:facet>
					<t:commandLink action="#{AssociadoBean.removerDocumento}" title="#{properties['lb_remover']}" immediate="true" id="remover" 
						onclick="setAcao(document.forms[1], 'Remove_Documento')" styleClass="botao_excluir">
						<t:updateActionListener property="#{AssociadoBean.documento.codigo}" value="#{documentos.codigo}" />
					</t:commandLink>
				</t:column>
				

				
	
			</t:dataTable>
		</t:div>
	</h:form>
</f:subview>