<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<%@ taglib prefix="a4j" uri="http://richfaces.org/a4j"%>

<script type="text/javascript">

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
<f:subview id="beneficiaropConvenioPesquisa">
	<h:form id="beneficiarioPesquisaForm">
		<a4j:keepAlive beanName="ConvenioBean" />
		<t:div id="div_planos" styleClass="conteudo">&nbsp;

			<h2>Lista de Beneficiários</h2>
			<t:messages id="msgs" showDetail="true" showSummary="false" errorClass="textoMsgErro" infoClass="textoMsgInfo"/>
	
			<t:dataTable id="beneficiarios" var="beneficiarios" value="#{ConvenioBean.beneficiariosConvenios}"
				cellspacing="1" cellpadding="2" width="100%" styleClass="tab_lista"
				preserveDataModel="true" rowId="#{beneficiarios.titular.codigo}">
	
				<f:facet name="footer">
					<h:commandLink value="javascript:void(0);" onclick="imprimir();" action="#{ConvenioBean.printPlanosConvenio}"
						styleClass="botao_imprimir" title="#{properties['lb_imprimir']}">
					</h:commandLink>
				</f:facet>
	
				<t:column width="50%">
					<f:facet name="header">
						<h:outputText value="#{properties['lb_nome']}"/>
					</f:facet>
				    <h:outputText value="#{beneficiarios.nome}"/>
				</t:column>
	
				<t:column width="50%">
					<f:facet name="header">
						<h:outputText value="#{properties['lb_descricao']}" />
					</f:facet>
				    <h:outputText value="#{beneficiarios.tipoBeneficiario}"/>
				</t:column>
			</t:dataTable>
		</t:div>
	</h:form>
</f:subview>