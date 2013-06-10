<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<%@ taglib prefix="a4j" uri="http://richfaces.org/a4j"%>
 
 
  
 <script type="text/javascript">

	function validarCamposObrigatoriosBeneficiario(){
		var nome = document.getElementById('associadoBeneficiarioSubView:associadoBeneficiarioForm:nome').value;
		if (nome == null || nome =='' ){
			alert('Favor preencher o campo Nome.' );
			return false;
		}

		var sexo = document.getElementById('associadoBeneficiarioSubView:associadoBeneficiarioForm:sexo').value;
		if (sexo == null || sexo =='' ){
			alert('Favor preencher o campo Sexo.' );
			return false;
		}

		var dataNascimento = document.getElementById('associadoBeneficiarioSubView:associadoBeneficiarioForm:dataNascimentoInputDate').value;
		if (dataNascimento == null || dataNascimento =='' ){
			alert('Favor preencher o campo Data de Nascimento.' );
			return false;
		}

		return true;
	}

</script>

<f:subview id="associadoBeneficiarioSubView">
	<rich:modalPanel id="panelAssociadoBeneficiario" width="100" height="420">
		<f:facet name="header">
			<h:panelGroup>
				<h:outputText value="Associado - Cadastro"></h:outputText>
			</h:panelGroup>
		</f:facet>
		
		<f:facet name="controls">
			<h:graphicImage value="/nucleo/images/close.png" style="cursor:pointer" id="hidePlanoModal" 
				onclick="Richfaces.hideModalPanel('panelAssociadoBeneficiario')"/>
		</f:facet>
		
		<h:form id="associadoBeneficiarioForm">
			<a4j:keepAlive beanName="AssociadoBean" />
			<h1>Módulo Associado</h1>
			<h2>Outros Beneficiáveis</h2>
	   
			<t:div  id="associadoOutrosBeneficiaveisFormMiolo">
				<t:messages id="msgs" showDetail="true" showSummary="false" errorClass="textoMsgErro" infoClass="textoMsgInfo"/>
				<table class="tab_cadastro" cellpadding="2" cellspacing="1">
					<thead>
						<tr>
							<th>Dados do Beneficiável</th>
							<th></th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th><h:outputLabel for="nome" value="#{properties['lb_nome']}" />:</th>
							<td><t:inputText id="nome" value="#{AssociadoBean.beneficiario.nome}" size="30" maxlength="30" tabindex="55"/>
								<h:inputHidden id="CodBeneficiario" value="#{AssociadoBean.beneficiario.codigo}"/></td> 
						</tr>
						<tr>
							<th>R.G.:</th>
							<td>
								<t:inputText tabindex="56" size="20" id="rg" value="#{AssociadoBean.beneficiario.rg}" onkeypress="return justNumber(this,event)" 
									maxlength="10"/>
							</td>
							<th>C.P.F.:</th>
							<td><t:inputText tabindex="58" size="15" id="beneficiaroCpf" value="#{AssociadoBean.beneficiarioCpf}" onkeypress="return MaskCPF(this,event)"/></td>
						</tr>
						<tr>
							<th>Data de Nasc.:</th>
							<td>
								<rich:calendar id="dataNascimento" popup="true" datePattern="dd/MM/yyyy" showApplyButton="false"
									cellWidth="24px" cellHeight="22px" style="width:200px" disabled="false" locale="en/US"
									value="#{AssociadoBean.beneficiario.dataNascimento}" inputClass="inputCalendar" 
								enableManualInput="true" oninputblur="checkDate(this)" 
								oninputkeypress="return maskDate(this,event);" zindex="16" tabindex="59"/>
							</td>
							<th>Sexo:</th>
							<td>
								<h:selectOneMenu tabindex="60" id="sexo" value="#{AssociadoBean.beneficiario.sexo}" >
									<f:selectItem itemLabel="#{properties['lb_selecione']}" itemValue=""/>
									<f:selectItem itemLabel="#{properties['lb_masculino']}" itemValue="M"/>
									<f:selectItem itemLabel="#{properties['lb_feminino']}" itemValue="F"/>
								</h:selectOneMenu>
							</td>
						</tr>
					</tbody>
				</table><br />
						
				<a4j:commandButton id="salvar" styleClass="botao_salvar" ajaxSingle="false" action="#{AssociadoBean.salvarBeneficiario}"  
					oncomplete="Richfaces.hideModalPanel('panelAssociadoBeneficiario');" onclick="if (!validarCamposObrigatoriosBeneficiario()) return false;" reRender="div_beneficiarios"> 
				</a4j:commandButton>
				<h:inputHidden id="beneficiarioAssociadoCodigo" value="#{AssociadoBean.beneficiario.associado.codigo}"/>
			</t:div>
 		</h:form>
    </rich:modalPanel>
</f:subview>