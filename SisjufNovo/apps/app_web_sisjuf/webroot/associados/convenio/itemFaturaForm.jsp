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
		Richfaces.hideModalPanel('panelItemFatura');
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

<rich:modalPanel id="panelItemFatura" width="620" height="410">

	<f:facet name="header">
		<h:panelGroup><h:outputText value="Fatura - Gerar Item Fatura"></h:outputText></h:panelGroup>
	</f:facet>
	
	<f:facet name="controls">
		<h:graphicImage value="/nucleo/images/close.png" style="cursor:pointer" id="hidePlanoModal" 
			onclick="Richfaces.hideModalPanel('panelItemFatura')"/>
	</f:facet>
	
	<h:form id="itemFaturaForm">
		<t:div  id="itemFaturaFormMiolo">
			<h1>Fatura</h1>
			<h2>Item Fatura - Adicionar</h2>
			<table class="tab_cadastro" cellpadding="2" cellspacing="1">
				<thead>
					<tr>
						<th>Dados do Item de Fatura</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th><h:outputLabel for="nome" value="#{properties['lb_beneficiario']}" />:</th>
						<td>
							<h:inputText value="#{ConvenioBean.itemFatura.beneficiario.nome}" id="nome" required="true"/>
							<rich:suggestionbox width="550" height="200" for="nome" 
								suggestionAction="#{ConvenioBean.autocompleteBeneficiario}" var="resultBen">
								<h:column>
									<h:outputText value="#{resultBen.nome}"/>
								</h:column>
								<h:column> 
									<h:outputText value="#{resultBen.cpf}"/>
								</h:column>
								<a4j:support event="onselect" ajaxSingle="true" action="#{ConvenioBean.carregarItensFatura}" >  
									<f:setPropertyActionListener value="#{resultBen.codigo}" target="#{ConvenioBean.itemFatura.beneficiario.codigo}" />
									<f:setPropertyActionListener value="#{resultBen.titular.nome}" target="#{ConvenioBean.itemFatura.beneficiario.titular.nome}" />
									<f:setPropertyActionListener value="#{resultBen.titular.codigo}" target="#{ConvenioBean.itemFatura.beneficiario.titular.codigo}" />
									<f:setPropertyActionListener value="#{resultBen.vinculacao.codigo}" target="#{ConvenioBean.itemFatura.vinculacao.codigo}" />
								</a4j:support>  
							</rich:suggestionbox>
						</td> 
					</tr>
					<tr>
						<th><h:outputLabel for="valor" value="#{properties['lb_valor']}" />:</th>
						<td>
							<h:inputText value="#{ConvenioBean.itemFatura.valor}" id="valor" required="true" converter="DoubleConverter" 
								onkeyup="formatarCampoNumero(this)"/>
						</td> 
					</tr>
				</tbody>
			</table>
			<br />
			<a4j:commandButton id="salvar" styleClass="botao_salvar" action="#{ConvenioBean.adicionarItemFatura}"  reRender="itemFaturaFormMiolo,itensFatura" 
				oncomplete="closeItemFaturaModal();" />
		</t:div>
	</h:form>
</rich:modalPanel>