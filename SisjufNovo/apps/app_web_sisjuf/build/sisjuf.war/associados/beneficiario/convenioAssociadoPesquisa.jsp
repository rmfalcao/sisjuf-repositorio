<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<%@ taglib prefix="a4j" uri="http://richfaces.org/a4j"%>


<script type="text/javascript">
	
	function openModalVinculacao(){
		//if (document.getElementById('planoConvenioPesquisa:planoPesquisaForm:CodConvenio').value != ''){
		Richfaces.showModalPanel('panelVinculaAssociado',{width:600, top:220 });
	}

	function novaVinculacao(){
		if (document.getElementById('associadoForm:codigo').value == '' || document.getElementById('associadoForm:codigo').value == '1'){
			alert("Cadastre um associado antes de tentar cadastrar uma vinculação");
			return false;
		}
	}

	function imprimir() {
		document.getElementById('associadovinculacaoPesquisa:vinculacaoFPesquisa').target = '_blank';
	}
</script>
<f:subview id="associadovinculacaoPesquisa">
	<h:form id="vinculacaoFPesquisa">
		<t:saveState value="#{AssociadoBean.associado}" id="associadoSaved"/>
		<a4j:keepAlive beanName="AssociadoBean" />
		<t:div id="div_associadovinculacaoPesquisa" styleClass="conteudo">&nbsp;
			<h2>Lista de Vinculações</h2>
			<a4j:commandButton oncomplete="openModalVinculacao();" action="#{AssociadoBean.prepararNovaVinculacao}" reRender="vinculaAssociadoFormMiolo" styleClass="botao_novo" 
		    	value="novo" onclick="if (document.getElementById('associadoForm:codigo').value == ''){ novaVinculacao(); return false;}"/>
	
			<t:messages id="msgs" showDetail="true" showSummary="false"
				errorClass="textoMsgErro" infoClass="textoMsgInfo" />
	
			<t:dataTable id="data" var="vinculacoes" value="#{AssociadoBean.vinculacoes}" cellspacing="1" cellpadding="2" width="100%" 
				styleClass="tab_lista_maior" preserveDataModel="true" rowId="#{vinculacoes.codigo}">
				
				<f:facet name="footer">
		            <h:commandLink value="javascript:void(0);" onclick="imprimir();" styleClass="botao_imprimir" title="#{properties['lb_imprimir']}" 
		            	action="#{AssociadoBean.printVinculacoes}"/>
		        </f:facet>
				
				<t:column width="91"> 
					<f:facet name="header">
						<h:outputText value="#{properties['lb_nome']}" />
					</f:facet>
					<a4j:commandLink action="#{AssociadoBean.carregarVinculacao}" oncomplete="openModalVinculacao();" 
				    	reRender="vinculaAssociadoFormMiolo" value="#{vinculacoes.pessoa.nome}" immediate="true">
           				<a4j:actionparam name="vinculacaoCodigo" value="#{vinculacoes.codigo}" assignTo="#{AssociadoBean.vinculacao.codigo}"/>
           				<a4j:actionparam name="associado.codigo" value="#{vinculacoes.associado.codigo}" assignTo="#{AssociadoBean.vinculacao.associado.codigo}"/>
	 				</a4j:commandLink>
				</t:column>
	
				<t:column width="91">
					<f:facet name="header">
						<h:outputText value="#{properties['lb_convenio']}" />
					</f:facet>
					<h:outputText value="#{vinculacoes.plano.convenio.nomeFantasia}" />
				</t:column>
	
	
				<t:column width="91">
					<f:facet name="header">
						<h:outputText value="#{properties['lb_plano']}" />
					</f:facet>
					<h:outputText value="#{vinculacoes.plano.nome}" />
				</t:column>
	
				<t:column width="68">
					<f:facet name="header">
						<h:outputText value="#{properties['lb_Titular']}" />
					</f:facet>
					<h:outputText value="#{vinculacoes.associado.nome}" />
				</t:column>
	
				<t:column width="67">
					<f:facet name="header">
						<h:outputText value="#{properties['lb_tipoVinculo']}" />
					</f:facet>
					<h:outputText value="#{vinculacoes.parentesco.descricao}" />
				</t:column>
	
				<t:column width="67">
					<f:facet name="header">
						<h:outputText value="#{properties['lb_valor']}" />
					</f:facet>
					<h:outputText value="#{vinculacoes.plano.valor}" />
				</t:column>
			</t:dataTable>
		</t:div>
	</h:form>
</f:subview>