<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<%@ taglib prefix="a4j" uri="http://richfaces.org/a4j"%>

<script type="text/javascript">

	function closePlanoModal(){
		Richfaces.hideModalPanel('panelPlanoConvenio');
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

<rich:modalPanel id="panelPlanoConvenio" width="620" height="410">

	<f:facet name="header">
		<h:panelGroup><h:outputText value="Plano - Cadastro"></h:outputText></h:panelGroup>
	</f:facet>
	
	<f:facet name="controls">
		<h:graphicImage value="/nucleo/images/close.png" style="cursor:pointer" id="hidePlanoModal" 
			onclick="Richfaces.hideModalPanel('panelPlanoConvenio')"/>
	</f:facet>
	
	<h:form id="planoConvenioForm">
		<t:div  id="planoConvenioFormMiolo">
			<h1>Modulo Convênio</h1>
			<h2>Plano - Cadastro</h2>
			<table class="tab_cadastro" cellpadding="2" cellspacing="1">
				<thead>
					<tr>
						<th>Dados do Plano</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th><h:outputLabel for="nome" value="#{properties['lb_nome']}" />:</th>
						<td><t:inputText id="nome" value="#{ConvenioBean.planoConvenio.nome}" size="30" maxlength="30"/>
							<h:inputHidden id="CodConvenio" value="#{ConvenioBean.planoConvenio.codigo}"/></td> 
					</tr>
					<tr>
						<th><h:outputLabel for="flagDedutivel" value="#{properties['lb_dedutivel']}" />:</th>
						<td><t:selectBooleanCheckbox id="flagDedutivel" value="#{ConvenioBean.planoDedutivel}"/></td>
					</tr>
					<tr>
						<th><h:outputLabel for="descricao" value="#{properties['lb_descricao']}" />:</th>
						<td><t:inputText id="descricao" value="#{ConvenioBean.planoConvenio.descricao}" size="30" maxlength="255"/></td> 
					</tr>
					<tr>
						<th><h:outputLabel for="valor" value="#{properties['lb_valor']}" />:</th>
						<td>
							<t:inputText id="valor" value="#{ConvenioBean.planoConvenio.valor}" size="15" converter="DoubleConverter" maxlength="15"  
								onkeyup="formatarCampoNumero(this)"/>
						</td> 
					</tr>
					<tr>
						<th><h:outputLabel for="dataInicio" value="#{properties['lb_data_inicio']}" />:</th>
						<td>
							<rich:calendar id="dataInicio" popup="true" datePattern="dd/MM/yyyy" showApplyButton="false"
								cellWidth="24px" cellHeight="22px" style="width:200px" disabled="false" locale="en/US"
								value="#{ConvenioBean.planoConvenio.dataInicio}" inputClass="inputCalendar" 
								enableManualInput="true" oninputblur="checkDate(this)" 
								oninputkeypress="return maskDate(this,event);" zindex="1" />
						</td>
					</tr>						
					<tr>
						<th><h:outputLabel for="flagDesativado" value="#{properties['lb_desativo']}" />:</th>
						<td><t:selectBooleanCheckbox id="flagDesativado" value="#{ConvenioBean.planoDesativado}"/></td>
					</tr>		
				</tbody>
			</table>
			<br />
			<a4j:commandButton id="salvar" styleClass="botao_salvar" action="#{ConvenioBean.salvarPlano}"  reRender="div_planos" 
				oncomplete="closePlanoModal();" />
		</t:div>
		<h:inputHidden id="CConvenio" value="#{ConvenioBean.planoConvenio.convenio.codigo}"/>
	</h:form>
</rich:modalPanel>
