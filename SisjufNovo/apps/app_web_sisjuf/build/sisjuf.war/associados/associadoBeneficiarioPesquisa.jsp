<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<%@ taglib prefix="a4j" uri="http://richfaces.org/a4j"%>


<script type="text/javascript">

	function openModal(){
		Richfaces.showModalPanel('panelAssociadoBeneficiario',{width:600, top:220, height:360 });
	}

	function novoBeneficiario(){
		if (document.getElementById('associadoForm:codigo').value == '' || document.getElementById('associadoForm:codigo').value == '1'){
			alert("Cadastre um associado antes de tentar cadastrar um outro beneficiário");
			return false;
		}
	}

	function imprimir() {
		document.getElementById('associadoOutrosBeneficiaveisPesquisa:outrosBeneficiaveisFPesquisa').target = '_blank';
	}

</script>
<f:subview id="associadoOutrosBeneficiaveisPesquisa">
	<h:form id="outrosBeneficiaveisFPesquisa">
		<t:saveState value="#{AssociadoBean.associado}" id="associadoSaved"/>
		<a4j:keepAlive beanName="AssociadoBean" />
		<t:div id="div_beneficiarios" styleClass="conteudo">&nbsp;
			<h2>Lista de Outros Beneficiáveis</h2>
			
		    <a4j:commandButton oncomplete="openModal();" action="#{AssociadoBean.prepararNovoBeneficiario}" reRender="associadoOutrosBeneficiaveisFormMiolo" styleClass="botao_novo" 
		    	value="novo" onclick="if (document.getElementById('associadoForm:codigo').value == ''){ novoBeneficiario(); return false;}"/>
			<t:messages id="msgs" showDetail="true" showSummary="false" errorClass="textoMsgErro" infoClass="textoMsgInfo"/>
	
			<t:dataTable id="data" var="beneficiarios" value="#{AssociadoBean.beneficiarios}" 
				cellspacing="1" cellpadding="2" width="100%" styleClass="tab_lista"
				preserveDataModel="true" rowId="#{beneficiarios.codigo}">
	
				<f:facet name="footer">
					<h:commandLink value="javascript:void(0);" onclick="imprimir();" action="#{AssociadoBean.printOutrosBeneficiaveis}"
						styleClass="botao_imprimir" title="#{properties['lb_imprimir']}" />
				</f:facet>
	
				<t:column width="50%">
					<f:facet name="header">
						<h:outputText value="#{properties['lb_nome']}"/>
					</f:facet>
				    <a4j:commandLink action="#{AssociadoBean.carregarOutroBeneficiaro}" oncomplete="openModal();" 
				    	reRender="associadoOutrosBeneficiaveisFormMiolo" value="#{beneficiarios.nome}">
	           				<a4j:actionparam name="codigo" value="#{beneficiarios.codigo}" assignTo="#{AssociadoBean.beneficiario.codigo}"/>
	           				<a4j:actionparam name="associado.codigo" value="#{beneficiarios.associado.codigo}" assignTo="#{AssociadoBean.beneficiario.associado.codigo}"/>
	 				</a4j:commandLink>	
				</t:column>
	
				<t:column width="10%">
					<f:facet name="header">
					</f:facet>
					<t:commandLink action="#{AssociadoBean.removerBeneficiario}" title="#{properties['lb_remover']}" immediate="true" id="remover"
						styleClass="botao_excluir">
						<t:updateActionListener	property="#{AssociadoBean.beneficiario.codigo}" 	value="#{beneficiarios.codigo}" />
						<t:updateActionListener	property="#{AssociadoBean.beneficiario.associado.codigo}" 	value="#{beneficiarios.associado.codigo}" />
					</t:commandLink>
				</t:column>
			</t:dataTable>
		</t:div>
	</h:form>
</f:subview>