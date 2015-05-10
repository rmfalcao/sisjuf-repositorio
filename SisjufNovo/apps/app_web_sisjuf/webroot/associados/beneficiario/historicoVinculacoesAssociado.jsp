<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<%@ taglib prefix="a4j" uri="http://richfaces.org/a4j"%>

<f:subview id="hitoricoVinculacoes">
	<f:loadBundle basename="sisjuf" var="properties" />
	<script type="text/javascript">
	
		function imprimirVinculacoes() {
			var pessoa = document.getElementById("historicoVinculacoes:historicoVinculacoesForm:pessoaCodigoHistoricoVinculacao").value;
			var convenio = document.getElementById("historicoVinculacoes:historicoVinculacoesForm:convenioCodigoHistoricoVinculacao").value;
			var plano = document.getElementById("historicoVinculacoes:historicoVinculacoesForm:planoCodigoHistoricoVinculacao").value;
			var url			= '<c:url value="/associados/convenio/BeneficiarioImpressao?codigoConvenio='+codigoConvenio+'&plano='+plano;
			url+='&nome='+encodeURI(nome)+'&matricula='+encodeURI(matricula)+'&dataInicio='+escape(dataInicio)+'&dataFim='+escape(dataFim)+'"/>';
			var name		= 'telaImpressao';
			var features	= 'toolbar=no,status=no,resizable=yes,scrollbars=yes,width=750,height=500';
			window.open(url,name,features);
		}
	
	</script>
	<h:form id="historicoVinculacoesForm">
	
		
		<t:div  id="vinculaAssociadoFormMiolo">
			<h1>Modulo Associado</h1>
			<h2>Histórico de Vinculações Associado</h2>
			
			<t:messages id="msgsHistorico" showDetail="true" showSummary="false" errorClass="textoMsgErro" infoClass="textoMsgInfo"/>
			
			<table class="tab_cadastro" cellpadding="2" cellspacing="1">
				<thead>
					<tr>
						<th>Dados do Histórico da Vinculação</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th><h:outputLabel for="pessoaCodigoHistoricoVinculacao" value="#{properties['lb_beneficiario']}" />:</th>
						<td>
							<h:selectOneMenu id="pessoaCodigoHistoricoVinculacao" value="#{AssociadoBean.historicoVinculacaoFiltro.associado.codigo}" >
								<f:selectItem itemLabel="#{properties['lb_selecione']}" itemValue=""/>
								<f:selectItems id="sel_pessoas" value="#{AssociadoBean.pessoasVinculadas}"/>
							</h:selectOneMenu>
					</tr>
					<tr>
						<th><h:outputLabel for="convenioCodigoHistoricoVinculacao" value="#{properties['lb_convenio']}" />:</th>
						<td>
							<h:selectOneMenu id="convenioCodigoHistoricoVinculacao" value="#{AssociadoBean.historicoVinculacaoFiltro.plano.convenio.codigo}" >
								<f:selectItem itemLabel="#{properties['lb_selecione']}" itemValue=""/>
								<f:selectItems id="sel_convenios" value="#{AssociadoBean.conveniosItens}"/>
								<a4j:support event="onchange" ajaxSingle="true" action="#{AssociadoBean.carregarPlanosByConveniosHistoricoVinculacoes}" 
									reRender="planoCodigoHistoricoVinculacao"/>
							</h:selectOneMenu>
						</td>
					</tr>
					<tr>
						<th><h:outputLabel for="planoCodigoHistoricoVinculacao" value="#{properties['lb_plano']}" />:</th>
						<td>
							<h:selectOneMenu id="planoCodigoHistoricoVinculacao" value="#{AssociadoBean.historicoVinculacaoFiltro.plano.codigo}" >
								<f:selectItem itemLabel="#{properties['lb_selecione']}" itemValue=""/>
								<f:selectItems id="sel_planos" value="#{AssociadoBean.planosItens}"/>
							</h:selectOneMenu>
						</td>
					</tr>
					
				</tbody>
			</table>
			<br />
			<a4j:commandButton id="filtrarHistoricoVinculacoes" styleClass="botao_filtrar" action="#{AssociadoBean.consultarHistoricoVinculacoes}"  reRender="historicoVinculacaoMiolo" />
		</t:div>
		
		<t:div  id="historicoVinculacaoMiolo">
			<t:dataTable id="data" var="historico" value="#{AssociadoBean.historicoVinculacoes}"
				cellspacing="1" cellpadding="2" width="100%" styleClass="tab_lista"
				preserveDataModel="false" rowId="#{atividades.codigo}" >
				
				<f:facet name="footer">
		            <h:outputLink value="javascript:void(0);" onclick="imprimirVinculacoes();" styleClass="botao_imprimir" title="#{properties['lb_imprimir']}">
		            </h:outputLink>
		        </f:facet>
				
				<t:column width="50%">
					<f:facet name="header">
						<h:outputText value="#{properties['lb_dataVinculacao']}" />
					</f:facet>
						<h:outputText value="#{historico.dataVinculacao}"/>
				</t:column>
				
				<t:column width="50%">
					<f:facet name="header">
						<h:outputText value="#{properties['lb_dataDesVinculacao']}" />
					</f:facet>
						<h:outputText value="#{historico.dataDesVinculacao}"/>
				</t:column>
				
				<t:column width="50%">
					<f:facet name="header">
						<h:outputText value="#{properties['lb_nome']}" />
					</f:facet>
						<h:outputText value="#{historico.associado.nome}" />
				</t:column>
				
				<t:column width="50%">
					<f:facet name="header">
						<h:outputText value="#{properties['lb_tipoVinculo']}" />
					</f:facet>
						<h:outputText value="#{historico.parentesco.descricao}" />
				</t:column>
				
				<t:column width="50%">
					<f:facet name="header">
						<h:outputText value="#{properties['lb_convenio']}" />
					</f:facet>
						<h:outputText value="#{historico.convenio.nomeFantasia}" />
				</t:column>
				
				<t:column width="50%">
					<f:facet name="header">
						<h:outputText value="#{properties['lb_plano']}" />
					</f:facet>
						<h:outputText value="#{historico.plano.descricao}" />
				</t:column>
			</t:dataTable>
		</t:div>
	</h:form>
</f:subview>