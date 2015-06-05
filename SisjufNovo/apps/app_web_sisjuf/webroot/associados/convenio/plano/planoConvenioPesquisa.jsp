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

	function imprimirPlanos() {
		var codigoConvenio = document.getElementById("planoConvenioPesquisa:planoPesquisaForm:codigoConvenio").value;
		var plano = document.getElementById("planoConvenioPesquisa:planoPesquisaForm:plano").value;
		var url			= '<c:url value="/associados/convenio/plano/PlanoConvenioImpressao?codigoConvenio='+codigoConvenio+'&plano='+plano+'"/>';
		var name		= 'telaImpressao';
		var features	= 'toolbar=no,status=no,resizable=yes,scrollbars=yes,width=750,height=500';
		window.open(url,name,features);
	}

	function imprimirValoresHistoricos() {
		var codigoConvenio = document.getElementById("planoConvenioPesquisa:planoPesquisaForm:codigoConvenio").value;
		var plano = document.getElementById("planoConvenioPesquisa:planoPesquisaForm:codigoPlano").value;
		var url			= '<c:url value="/associados/convenio/plano/PlanoConvenioValoresHistoricosImpressao?codigoConvenio='+codigoConvenio+'&plano='+plano+'"/>';
		var name		= 'telaImpressao';
		var features	= 'toolbar=no,status=no,resizable=yes,scrollbars=yes,width=750,height=500';
		window.open(url,name,features);
	}
	
	
	
</script>
<f:subview id="planoConvenioPesquisa">
	<h:form id="planoPesquisaForm">
		<a4j:keepAlive beanName="ConvenioBean" />
		<t:div id="div_planos" styleClass="conteudo">&nbsp;
			<h:inputHidden id="codigoConvenio" value="#{ConvenioBean.convenio.codigo}" />
			<h:inputHidden id="CodConvenio" value="#{ConvenioBean.planoConvenio.convenio.codigo}"/>
			
			<h2>Lista de Planos</h2>
			
			<table class="tab_cadastro" cellpadding="2" cellspacing="1">
				<thead>
					<tr>
						<th>Filtro</th>
						<th colspan="3"></th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th>Convênio:</th>
						<td colspan="3">
							<h:outputText  value="#{ConvenioBean.convenio.nomeFantasia}" />
						</td>
					</tr>
					<tr>
						<th>Plano:</th>
						<td colspan="3">
							<h:selectOneMenu id="plano" value="#{ConvenioBean.planoConvenio.codigo}" >
								<f:selectItem itemLabel="#{properties['lb_selecione']}" itemValue=""/>
								<f:selectItems  value="#{ConvenioBean.planosConvenioCombo}"/>
							</h:selectOneMenu>
						</td>
					</tr>
				</tbody>
			</table>
	
			<br /><br />
			<a4j:commandLink action="#{ConvenioBean.carregarPlano}" title="#{properties['lb_filtrar']}" id="filtrar" styleClass="botao_filtrar" reRender="div_planos">
					<h:outputText value="#{properties['lb_filtrar']}" />
			</a4j:commandLink>
			<br /><br />
					
			<t:saveState value="#{ConvenioBean.planoConvenio}" /> 
			<t:saveState value="#{ConvenioBean.planosConvenio}" />
			
			<a4j:commandLink action="#{ConvenioBean.prepararNovoPlano}" style="border: 0px;" styleClass="botao_novo" 
				reRender="planoConvenioForm:planoConvenioFormMiolo" oncomplete="openPlanoModal();" 
				onclick="if (document.getElementById('planoConvenioPesquisa:planoPesquisaForm:CodConvenio').value == '') { novoPlano(); return false;} ">
		    </a4j:commandLink>
			<t:messages id="msgs" showDetail="true" showSummary="false" errorClass="textoMsgErro" infoClass="textoMsgInfo"/>
	
			<t:dataTable id="planos" var="planos" value="#{ConvenioBean.planosConvenio}"
				cellspacing="1" cellpadding="2" width="100%" styleClass="tab_lista"
				preserveDataModel="true" rowId="#{planos.codigo}">
	
				<f:facet name="footer">
					<h:commandLink value="javascript:void(0);" onclick="imprimirPlanos();" styleClass="botao_imprimir" title="#{properties['lb_imprimir']}">
					</h:commandLink>
				</f:facet>
	
				<t:column width="50%">
					<f:facet name="header">
						<h:outputText value="#{properties['lb_nome']}"/>
					</f:facet>
				    <a4j:commandLink action="#{ConvenioBean.carregarPlano}"
							oncomplete="openPlanoModal();"
							reRender="planoConvenioForm:planoConvenioFormMiolo" value="#{planos.nome}">
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
						<h:outputText value="Data Início Vigência" />
					</f:facet>
					<h:outputText value="#{planos.dataInicio}">
						<f:convertDateTime type="date" pattern="dd/MM/yyyy"/>
					</h:outputText>
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
						onclick="confirmarRemover()">
	           				<a4j:actionparam name="codigo" value="#{planos.codigo}" assignTo="#{ConvenioBean.planoConvenio.codigo}"/>
					</a4j:commandLink>
				</t:column>
				
			</t:dataTable>
		</t:div>
		
		</h:form>
</f:subview>

