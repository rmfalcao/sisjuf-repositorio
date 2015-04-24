<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<%@ taglib prefix="a4j" uri="http://richfaces.org/a4j"%>


<f:subview id="beneficiaropConvenioPesquisa">
	<h:form id="beneficiarioPesquisaForm">
	
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

	function imprimirBeneficiario() {
		var codigoConvenio = document.getElementById("beneficiaropConvenioPesquisa:beneficiarioPesquisaForm:codigoConvenio").value();
		var plano = document.getElementById("beneficiaropConvenioPesquisa:beneficiarioPesquisaForm:plano").value();
		var nome = document.getElementById("beneficiaropConvenioPesquisa:beneficiarioPesquisaForm:nomeBeneficiario").value();
		var matricula = document.getElementById("beneficiaropConvenioPesquisa:beneficiarioPesquisaForm:matriculaBeneficiario").value();
		var dataInicio = document.getElementById("beneficiaropConvenioPesquisa:beneficiarioPesquisaForm:dataInicioVinculacao").value();
		var dataFim = document.getElementById("beneficiaropConvenioPesquisa:beneficiarioPesquisaForm:dataFimVinculacao").value();
		
		var url			= '<c:url value="/associados/convenio/BeneficiarioImpressao?codigoConvenio='+codigoConvenio+'&plano='+plano;
		url+='&nome='+encodeURI(nome)+'&matricula='+encodeURI(matricula)+'&dataInicio='+encodeURI(dataInicio)+'&dataFim='+encodeURI(dataFim)+'"/>';
		var name		= 'telaImpressao';
		var features	= 'toolbar=no,status=no,resizable=yes,scrollbars=yes,width=750,height=500';
		window.open(url,name,features);
	}
	
</script>

		<a4j:keepAlive beanName="ConvenioBean" />
		<t:div id="div_planos" styleClass="conteudo">&nbsp;
			<h:inputHidden id="codigoConvenio" value="#{ConvenioBean.convenio.codigo}" />
			
			<h2>Lista de Beneficiários</h2>
			<t:messages id="msgs" showDetail="true" showSummary="false" errorClass="textoMsgErro" infoClass="textoMsgInfo"/>
	
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
							<h:selectOneMenu id="plano" value="#{ConvenioBean.beneficiario.plano.codigo}" >
								<f:selectItem itemLabel="#{properties['lb_selecione']}" itemValue=""/>
								<f:selectItems  value="#{ConvenioBean.planosConvenioCombo}"/>
							</h:selectOneMenu>
						</td>
					</tr>
					<tr>
						<th width="140">Período de vinculação:</th>
						<td width="190">
							<rich:calendar id="dataInicioVinculacao" popup="true" datePattern="dd/MM/yyyy" showApplyButton="false"
								cellWidth="24px" cellHeight="22px" style="width:200px" disabled="false" locale="en/US"
								value="#{ConvenioBean.beneficiario.dataInicioVinculacao}" inputClass="inputCalendar" 
								enableManualInput="true" oninputblur="checkDate(this)" 
								oninputkeypress="return maskDate(this,event);" />
							<rich:calendar id="dataFimVinculacao" popup="true" datePattern="dd/MM/yyyy" showApplyButton="false"
								cellWidth="24px" cellHeight="22px" style="width:200px" disabled="false" locale="en/US"
								value="#{ConvenioBean.beneficiario.dataFimVinculacao}" inputClass="inputCalendar" 
								enableManualInput="true" oninputblur="checkDate(this)" 
								oninputkeypress="return maskDate(this,event);"/>
						</td>
						<th width="140">Nº. Matrícula:</th>
						<td width="120"><t:inputText size="8" id="matriculaBeneficiario" value="#{ConvenioBean.beneficiario.titular.matriculaJustica}" onkeypress="return justNumber(this,event)"/></td>
					</tr>
					<tr>
						<th><h:outputLabel for="nomeBeneficiario" value="Nome do Titular:" />:</th>
						<td colspan="3"><t:inputText size="40" id="nomeBeneficiario" value="#{ConvenioBean.beneficiario.titular.nome}"></t:inputText></td>
					</tr>
				</tbody>
			</table>
	
			<br /><br />
			<a4j:commandLink action="#{ConvenioBean.carregarBeneficiarios}" title="#{properties['lb_filtrar']}" id="filtrar" styleClass="botao_filtrar" reRender="div_planos">
					<h:outputText value="#{properties['lb_filtrar']}" />
			</a4j:commandLink>
			<br /><br />
					
			<t:saveState value="#{ConvenioBean.beneficiario}" /> 
			<t:saveState value="#{ConvenioBean.beneficiariosConvenios}" />	
			
			<t:dataTable id="beneficiarios" var="beneficiario" value="#{ConvenioBean.beneficiariosConvenios}"
				cellspacing="1" cellpadding="2" width="100%" styleClass="tab_lista"
				rowId="#{beneficiarios.titular.codigo}">
	
				<f:facet name="footer">
					<h:outputLink value="javascript:void(0);" onclick="imprimirBeneficiario();" 
						styleClass="botao_imprimir" title="#{properties['lb_imprimir']}">
					</h:outputLink>
				</f:facet>
				
				<t:column width="50%">
					<f:facet name="header">
						<h:outputText value="#{properties['lb_nome']}"/>
					</f:facet>
				    <h:outputText value="#{beneficiario.nome}"/>
				</t:column>
				
				<t:column width="50%">
					<f:facet name="header">
						<h:outputText value="Nome do Titular"/>
					</f:facet>
				    <h:outputText value="#{beneficiario.titular.nome}"/>
				</t:column>
	
				<t:column width="50%">
					<f:facet name="header">
						<h:outputText value="#{properties['lb_descricao']}" />
					</f:facet>
				    <h:outputText value="#{beneficiario.tipoBeneficiario}"/>
				</t:column>
				
				<t:column width="50%">
					<f:facet name="header">
						<h:outputText value="Plano" />
					</f:facet>
				    <h:outputText value="#{beneficiario.plano.nome}"/>
				</t:column>
				
				<t:column width="50%">
					<f:facet name="header">
						<h:outputText value="Valor" />
					</f:facet>
				    <h:outputText value="#{beneficiario.plano.valor}"/>
				</t:column>
			</t:dataTable>
		</t:div>
	</h:form>
</f:subview>