<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<%@ taglib prefix="a4j" uri="http://richfaces.org/a4j"%>
 
 
  
 <script type="text/javascript">

	function closeModal(){
			Richfaces.hideModalPanel('vinculacao_panel');
	}


	function validarCamposObrigatorios(){

		var convenio = document.getElementById('SubViewVincularConvenioAssociadoForm:vincularConvenioAssociadoForm:convenio').value;
		if (convenio == null || convenio =='' ){
			alert('Favor preencher o campo Convenio.' );
			return false;
		}

		var plano = document.getElementById('SubViewVincularConvenioAssociadoForm:vincularConvenioAssociadoForm:plano').value;
		if (plano == null || plano =='' ){
			alert('Favor preencher o campo Plano.' );
			return false;
		}

		var nome = document.getElementById('SubViewVincularConvenioAssociadoForm:vincularConvenioAssociadoForm:nome').value;
		if (nome == null || nome =='' ){
			alert('Favor preencher o campo nome.' );
			return false;
		}

		var dataVinculacao = document.getElementById('SubViewVincularConvenioAssociadoForm:vincularConvenioAssociadoForm:dataVinculacao').value;
		if (dataVinculacao == null || dataVinculacao =='' ){
			alert('Favor preencher o campo Data de Vinculação.' );
			return false;
		}

		return true;
	}

</script>

<f:subview id="SubViewVincularConvenioAssociadoForm">
		    <rich:modalPanel id="vinculacao_panel" width="100" height="230">
		    <h:form id="vincularConvenioAssociadoForm">
		    
					        <f:facet name="header">
					            <h:panelGroup>
					                <h:outputText value="Vinculação"></h:outputText>
					            </h:panelGroup>
					        </f:facet>

        <f:facet name="header">
            <h:panelGroup>
                <h:outputText value="Modal Panel"></h:outputText>
            </h:panelGroup>
        </f:facet>
        <f:facet name="controls">
            <h:panelGroup>
                <h:graphicImage value="/images/modal/close.png" style="cursor:pointer" id="hidelink"/>
                <rich:componentControl for="panel" attachTo="hidelink" operation="hide" event="onclick"/>
            </h:panelGroup>
        </f:facet>

					        
							<t:div  id="vincularConvenioAssociadoFormMiolo">
								<t:messages id="msgs" showDetail="true" showSummary="false" errorClass="textoMsgErro" infoClass="textoMsgInfo"/>
								<table class="tab_cadastro" cellpadding="2" cellspacing="1">
									<thead>
										<tr>
											<th>Dados da vinculação</th>
											<th></th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<th><h:outputLabel for="associado" value="#{properties['lb_associado']}" />:</th>
											<td>
												<t:inputText id="associado" value="#{VinculacaoPlanoBean.vinculacao.associado.nome}" size="30" maxlength="30"/>
											</td> 
										</tr>
										<tr>
											<th><h:outputLabel for="convenio" value="#{properties['lb_convenio']}" />:</th>
											<td>
												<h:selectOneMenu id="convenio" value="#{VinculacaoPlanoBean.vinculacao.plano.convenio}">
													<f:selectItem itemLabel="#{properties['lb_selecione']}" itemValue="" />
													<f:selectItems value="#{VinculacaoPlanoBean.convenios}" />
												</h:selectOneMenu>
											</td>
										</tr>
										<tr>
											<th><h:outputLabel for="plano" value="#{properties['lb_plano']}" />:</th>
											<td>
												<t:inputText id="plano" value="#{VinculacaoPlanoBean.vinculacao.plano.descricao}" size="30" maxlength="30"/>
											</td> 
										</tr>
										<tr>
											<th><h:outputLabel for="nome" value="#{properties['lb_nome']}" />:</th>
											<td>
												<t:inputText id="nome" value="#{VinculacaoPlanoBean.vinculacao.pessoa.nome}" size="15"/>
											</td> 
										</tr>
										<tr>
											<th><h:outputLabel for="dataVinculacao" value="#{properties['lb_dataVinculacao']}" />:</th>
											<td>
												<rich:calendar id="dataVinculacao" popup="true" datePattern="dd/MM/yyyy" showApplyButton="false"
													cellWidth="24px" cellHeight="22px" style="width:200px" disabled="false" locale="en/US"
													value="#{VinculacaoPlanoBean.vinculacao.dataVinculacao}" inputClass="inputCalendar" enableManualInput="true"
													oninputblur="checkDate(this)" oninputkeypress="return maskDate(this,event);"/>
											</td> 
										</tr>
										</tbody>
								</table>
								<br/> 
								
								<a4j:commandButton id="salvar" styleClass="botao_salvar" ajaxSingle="false" action="#{VinculacaoPlanoBean.salvar}"  reRender="div_associadovinculacaoPesquisa"
		     							oncomplete="closeModal();" onclick="validarCamposObrigatorios();">
								</a4j:commandButton>

							</t:div>
		  	  		</h:form>
					
			    </rich:modalPanel>
</f:subview>