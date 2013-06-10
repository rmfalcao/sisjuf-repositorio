<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<f:view>
	<f:loadBundle basename="sisjuf" var="properties" />
	<html>
	<head>
	<title>SISJUF - Sistema ASSERJUF</title>
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
	<link href="<c:url value="/nucleo/style/sisjuf.css"/>" type="text/css"
		rel="stylesheet" />
	<link rel="Shortcut Icon" type="image/png"
		href="<c:url value="/nucleo/images/icone.png"/>">
	<script src="<c:url value="/nucleo/js/sisjuf.js" />"
		type="text/javascript"></script>

	<script language="JavaScript">

		function setAcao(form, acao){
			form.acao.value = acao;
		}

		function imprimir() {
			var url			= '<c:url value="/associados/beneficiario/vinculacaoPrint.jsf"/>';
			var name		= 'telaImpressao';
			var features	= 'toolbar=no,status=no,resizable=yes,scrollbars=yes,width=750,height=500';
			window.open(url,name,features);
		}
			
			</script>
	</head>
	<body>

	<div id="geral">
	<div id="topo">
	<div id="sair">sair <a href="../index.htm"><img
		src="../imagens/botao_x.gif" /></a></div>
	<div id="voltar">voltar <a href="#"><img
		src="../imagens/setinhavoltar.gif" /></a></div>
	</div>
	<%@ include file="/nucleo/includes/inc_menuFinanceiro.jsp"%>
	<h:form id="vinculacaoPesquisa">

		<div id="miolo">
		<h1>Módulo de Associados</h1>
		<h2>Beneficiarios</h2>
		<br />
		<br />
		<t:messages id="msgs" showDetail="true" showSummary="false"
			errorClass="textoMsgErro" infoClass="textoMsgInfo" />

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
					<td colspan="3"><h:selectOneMenu id="convenios"
						value="#{VinculacaoPlanoBean.vinculacaoFiltro.convenio.codigo}">
						<f:selectItem itemLabel="#{properties['lb_selecione']}"
							itemValue="" />
						<t:selectItems id="listconvenios" value="#{VinculacaoPlanoBean.convenios}"
							var="con" itemLabel="#{con.nome}" itemValue="#{con.codigo}" />
					</h:selectOneMenu></td>
				</tr>
				<tr>
					<th>Plano:</th>
					<td colspan="3"><h:selectOneMenu id="planos"
						value="#{VinculacaoPlanoBean.vinculacaoFiltro.plano.codigo}">
						<f:selectItem itemLabel="#{properties['lb_selecione']}"
							itemValue="" />
						<t:selectItems id="listplanos" value="#{VinculacaoPlanoBean.planos}"
							var="plano" itemLabel="#{plano.nome}" itemValue="#{plano.codigo}" />
					</h:selectOneMenu></td>
				</tr>
				<tr>
					<th>Nome:</th>
						<td><t:inputText size="40" maxlength="60"  id="nome" value="#{VinculacaoPlanoBean.vinculacaoFiltro.nome}" tabindex="2" /></td>
				</tr>
								<tr>
									<th><h:outputLabel value="#{properties['lb_periodo']}" />:</th>
									<td colspan="4">
										<rich:calendar id="dataInicial" popup="true" datePattern="dd/MM/yyyy" showApplyButton="false"
											cellWidth="24px" cellHeight="22px" style="width:200px" disabled="false" locale="en/US"
											value="#{VinculacaoPlanoBean.vinculacaoFiltro.dataVinculacaoInicial}" inputClass="inputCalendar" enableManualInput="true"
											oninputblur="checkDate(this)" oninputkeypress="return maskDate(this,event);"/>
										à
										<rich:calendar id="dataFinal" popup="true" datePattern="dd/MM/yyyy" showApplyButton="false"
											cellWidth="24px" cellHeight="22px" style="width:200px" disabled="false" locale="en/US"
											value="#{VinculacaoPlanoBean.vinculacaoFiltro.dataVinculacaoFinal}" inputClass="inputCalendar" enableManualInput="true"
											oninputblur="checkDate(this)" oninputkeypress="return maskDate(this,event);"/>
									</td>
								</tr>
			</tbody>
		</table>
		<h:commandButton styleClass="botao_filtrar"
			value="#{properties['lb_filtar']}" action="#{VinculacaoPlanoBean.consultarVinculacoes}"
			onclick="setAcao(document.forms[0], 'Consulta_Beneficiario');" /> <br />
		<br />
		<t:saveState value="#{VinculacaoPlanoBean.vinculacoes}" /> 
		<t:saveState value="#{VinculacaoPlanoBean.vinculacao}" /> 
		<t:dataTable id="data"
			var="vinculacoes" value="#{VinculacaoPlanoBean.vinculacoes}" cellspacing="1"
			cellpadding="2" width="100%" styleClass="tab_lista_maior"
			preserveDataModel="false">
			
			<f:facet name="footer">
	            <h:outputLink value="javascript:void(0);" onclick="imprimir();" styleClass="botao_imprimir" title="#{properties['lb_imprimir']}">
	            </h:outputLink>
	        </f:facet>
			
			<t:column width="91">
				<f:facet name="header">
					<h:outputText value="#{properties['lb_nome']}" />
				</f:facet>
					<h:outputText value="#{vinculacoes.pessoa.nome}" />
			</t:column>

			<t:column width="68">
				<f:facet name="header">
					<h:outputText value="#{properties['lb_Titular']}" />
				</f:facet>
				<h:outputText value="#{vinculacoes.associado.nome}" />
			</t:column>

			<t:column width="67">
				<f:facet name="header">
					<h:outputText value="#{properties['lb_tipoVinculo']}" />
				</f:facet>
				<h:outputText value="??????????" />
			</t:column>
		</t:dataTable> <input type="hidden" name="acao" value="" /></div>
	</h:form></div>

	</body>
	</html>
</f:view>