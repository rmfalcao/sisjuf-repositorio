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
				/*var url			= '<c:url value="/associados/convenio/convenioPrint.jsf"/>';
				var name		= 'telaImpressao';
				var features	= 'toolbar=no,status=no,resizable=yes,scrollbars=yes,width=750,height=500';
				window.open(url,name,features);*/
				document.forms[0].target = '_blank';
				return true;
			}
	
			function pesquisa(){
				document.forms[0].target = '_self';
				return true;
			}
				
		</script>
	</head>
	<body>

	<div id="geral">
	<div id="topo">
		<div id="sair">
			sair <a href="<c:url value="/seguranca/logout.jsp"/>"><img src="<c:url value="/nucleo/images/botao_x.gif"/>" /></a>
		</div>
	</div>
	<%@ include file="/nucleo/includes/inc_menuFinanceiro.jsp"%>
	<h:form id="convenioPesquisa">

		<div id="miolo">
		<h1>Módulo de Conv&ecirc;nios</h1>
		<h2>Conv&ecirc;nios</h2>
		<a class="botao_novo"
			href="<c:url value="/associados/convenio/convenioForm.jsf"/>"><span>novo</span></a>
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
					<th>Categoria:</th>
					<td colspan="3"><h:selectOneMenu id="categoria"
						value="#{ConvenioBean.convenio.categoria}">
							<f:selectItem itemLabel="#{properties['lb_selecione']}"	itemValue="" />
							<f:selectItem itemLabel="#{properties['lb_geraFaturaVariavel']}" itemValue="V"/>
							<f:selectItem itemLabel="#{properties['lb_geraFaturaFixa']}" itemValue="F"/>
							<f:selectItem itemLabel="#{properties['lb_naoGeraFatura']}" itemValue="N"/>
					</h:selectOneMenu></td>
				</tr>
				<tr>
					<th width="130">Nome Fantasia:</th>
					<td width="230"><t:inputText size="35" id="nomeFantasia"
						value="#{ConvenioBean.convenio.nomeFantasia}" /></td>
					<th width="120">Atividade:</th>
					<td width="110"><h:selectOneMenu id="atividade"
						value="#{ConvenioBean.convenio.atividadeConvenio.codigo}">
						<f:selectItem itemLabel="#{properties['lb_selecione']}"
							itemValue="" />
						<t:selectItems id="atividades" value="#{ConvenioBean.atividadesAtivas}"
							var="atv" itemLabel="#{atv.nome}" itemValue="#{atv.codigo}" />
					</h:selectOneMenu></td>
				</tr>
			</tbody>
		</table>
		<t:commandLink styleClass="botao_filtrar" onclick="pesquisa();"
			value="#{properties['lb_filtar']}" action="#{ConvenioBean.consultar}"/> <br />
		<br />
		<t:saveState value="#{ConvenioBean.convenios}" /> 
		<t:saveState value="#{ConvenioBean.convenio}" /> 
		<t:dataTable id="data"
			var="convenios" value="#{ConvenioBean.convenios}" cellspacing="1"
			cellpadding="2" width="100%" styleClass="tab_lista_maior"
			preserveDataModel="false">
			
			<f:facet name="footer">
	            <h:commandLink value="javascript:void(0);" action="#{ConvenioBean.print}" onclick="imprimir();" styleClass="botao_imprimir" title="#{properties['lb_imprimir']}">
	            </h:commandLink>
	        </f:facet>
			
			<t:column width="91">
				<f:facet name="header">
					<h:outputText value="#{properties['lb_categoria']}" />
				</f:facet>
				<h:outputText value="#{properties['lb_geraFaturaVariavel']}"
					rendered="#{convenios.categoria == 'V'}" />
				<h:outputText value="#{properties['lb_geraFaturaFixa']}"
					rendered="#{convenios.categoria  == 'F'}" />
				<h:outputText value="#{properties['lb_naoGeraFatura']}"
					rendered="#{convenios.categoria  == 'N'}" />					
			</t:column>
			<t:column width="171">
				<f:facet name="header">
					<h:outputText value="#{properties['lb_nomeFantasia']}" />
				</f:facet>
				<t:commandLink action="#{ConvenioBean.carregar}" title="#{properties['lb_carregar']}" id="carregar"	immediate="true" >
					<t:updateActionListener property="#{ConvenioBean.convenio.codigo}" value="#{convenios.codigo}" />
					<h:outputText value="#{convenios.nomeFantasia}" />
				</t:commandLink>
			</t:column>

			<t:column width="68">
				<f:facet name="header">
					<h:outputText value="#{properties['lb_atividade']}" />
				</f:facet>
				<h:outputText value="#{convenios.atividadeConvenio.nome}" />
			</t:column>

			<t:column width="67">
				<f:facet name="header">
					<h:outputText value="#{properties['lb_cidade']}" />
				</f:facet>
				<h:outputText value="#{convenios.cidade}" />
			</t:column>

			<t:column width="47">
				<f:facet name="header">
					<h:outputText value="#{properties['lb_estado']}" />
				</f:facet>
				<h:outputText value="#{convenios.estado.nome}" />
			</t:column>

			<t:column width="88">
				<f:facet name="header">
					<h:outputText value="#{properties['lb_telefone']}" />
				</f:facet>
				<h:outputText value="#{convenios.telefone}" />
			</t:column>

			<t:column width="20">
				<t:commandLink action="#{ConvenioBean.remover}" title="#{properties['lb_remover']}" immediate="true" id="remover" 
					 styleClass="botao_excluir">
					<t:updateActionListener property="#{ConvenioBean.convenio.codigo}" value="#{convenios.codigo}" />
				</t:commandLink>
			</t:column>
		</t:dataTable></div>
	</h:form></div>

	</body>
	</html>
</f:view>