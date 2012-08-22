<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<%@ taglib prefix="a4j" uri="http://richfaces.org/a4j"%>

<script type="text/javascript">
	function openPlanoModal(){
		Richfaces.showModalPanel('panelPlanoConvenio');
	}

	function novoPlano(){
		if (document.getElementById('planoConvenioPesquisa:planoPesquisaForm:CodConvenio').value != ''){
			return true;
		}else{
			alert("Cadastre um plano primeiro.");
			return false;
		}
	}

	function setAcao(form, acao){
		form.acao.value = acao;
	}
	
	function confirmarRemover(){
		if (confirm("Deseja realmente remover este registro?")){
			return true;
		} else{
			 return false;
		}
	}

	function imprimir() {
		/*var url			= '<c:url value="/associados/convenio/plano/planoConvenioPrint.jsf"/>';
		var name		= 'telaImpressao';
		var features	= 'toolbar=no,status=no,resizable=yes,scrollbars=yes,width=750,height=500';
		window.open(url,name,features);*/
		document.getElementById('planoConvenioPesquisa:planoPesquisaForm').target = '_blank';
	}
	
</script>
<f:subview id="planoConvenioPesquisa">
	<h:form id="planoPesquisaForm">
		<a4j:keepAlive beanName="ConvenioBean" />
		<t:div id="div_planos" styleClass="conteudo">&nbsp;

			<h2>Lista de Planos</h2>
			<a4j:commandLink action="#{ConvenioBean.prepararNovoPlano}" style="border: 0px;" styleClass="botao_novo" 
				reRender="planoConvenioFormMiolo" oncomplete="openPlanoModal();" onclick="if (document.getElementById('planoConvenioPesquisa:planoPesquisaForm:CodConvenio').value == '') { novoPlano(); return false;} ">
		    </a4j:commandLink>
			<t:messages id="msgs" showDetail="true" showSummary="false" errorClass="textoMsgErro" infoClass="textoMsgInfo"/>
	
			<t:dataTable id="planos" var="planos" value="#{ConvenioBean.planosConvenio}"
				cellspacing="1" cellpadding="2" width="100%" styleClass="tab_lista"
				preserveDataModel="true" rowId="#{planos.codigo}">
	
				<f:facet name="footer">
					<h:commandLink value="javascript:void(0);" onclick="imprimir();" action="#{ConvenioBean.printPlanosConvenio}"
						styleClass="botao_imprimir" title="#{properties['lb_imprimir']}">
					</h:commandLink>
				</f:facet>
	
				<t:column width="50%">
					<f:facet name="header">
						<h:outputText value="#{properties['lb_nome']}"/>
					</f:facet>
				    <a4j:commandLink action="#{ConvenioBean.carregarPlano}"
						oncomplete="openPlanoModal();"
							reRender="planoConvenioFormMiolo" value="#{planos.nome}">
	           				<a4j:actionparam name="codigo" value="#{planos.codigo}" assignTo="#{ConvenioBean.planoConvenio.codigo}"/>
	 				</a4j:commandLink>
	
				</t:column>
	
				<t:column width="50%">
					<f:facet name="header">
						<h:outputText value="#{properties['lb_descricao']}" />
					</f:facet>
				    <a4j:commandLink action="#{ConvenioBean.carregarPlano}"
						oncomplete="openPlanoModal();"
							reRender="planoConvenioForm:planoConvenioFormMiolo" value="#{planos.descricao}">
	           				<a4j:actionparam name="codigo" value="#{planos.codigo}" assignTo="#{ConvenioBean.planoConvenio.codigo}"/>
	 				</a4j:commandLink>
				</t:column>
	
				<t:column width="10%">
					<f:facet name="header">
						<h:outputText value="#{properties['lb_valor']}" />
					</f:facet>
					<h:outputText value="#{planos.valor}" converter="DoubleConverter" />
				</t:column>
	
				<t:column width="10%">
					<f:facet name="header">
						<h:outputText value="#{properties['lb_status']}" />
					</f:facet>
					<h:outputText value="#{properties['lb_desativo']}" rendered="#{planos.flagDesativado}"/>
					<h:outputText value="#{properties['lb_ativo']}" rendered="#{!planos.flagDesativado}"/>
				</t:column>
	
				<t:column width="10%"> 
					<f:facet name="header">
					</f:facet>
					
					<a4j:commandLink action="#{ConvenioBean.removerPlano}" title="#{properties['lb_remover']}" id="remover"  reRender="div_planos" styleClass="botao_excluir"
						onclick="if (!window.confirm('Deseja realmente remover este registro?')){return false}else{document.getElementById('planoConvenioPesquisa:planoPesquisaForm').target = '_self'; return true}"  >
	           				<a4j:actionparam name="codigo" value="#{planos.codigo}" assignTo="#{ConvenioBean.planoConvenio.codigo}"/>
					</a4j:commandLink>
				</t:column>
			</t:dataTable>
		</t:div>
		<h:inputHidden id="CodConvenio" value="#{ConvenioBean.planoConvenio.convenio.codigo}"/>
	</h:form>
</f:subview>