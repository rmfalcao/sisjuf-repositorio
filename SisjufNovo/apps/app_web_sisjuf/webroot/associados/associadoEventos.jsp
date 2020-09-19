<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<f:view>
	<f:loadBundle basename="sisjuf" var="properties" />
	<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
	<html>
		<head>
			<title>SISJUF - Sistema ASSERJUF</title>
			<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
			<link href="<c:url value="/nucleo/style/sisjuf.css"/>" type="text/css"	rel="stylesheet" />
			<link rel="Shortcut Icon" type="image/png" href="<c:url value="/nucleo/images/icone.png"/>">
			<script src="<c:url value="/nucleo/js/sisjuf.js" />" type="text/javascript"></script>
			<script>
				function setAcao(form, acao){
					form.acao.value = acao;
				}
			</script>
		</head>
		<body>
		
			<div id="geral">
				<div id="topo">
					<div id="sair">
						sair <a href="<c:url value="/Logout"/>"><img src="<c:url value="/nucleo/images/botao_x.gif"/>" /></a>
					</div>
					<div id="voltar">
						voltar <a href="#"><img src="<c:url value="/nucleo/images/setinhavoltar.gif"/>" /></a>
					</div>
				</div>
		
				<%@ include file="/nucleo/includes/inc_menuFinanceiro.jsp"%>
		
				<div id="miolo">
					<h1>Módulo Associados</h1>
					<h2>Histórico de Eventos</h2>
					<h:form id="eventoConsulta">
						<table class="tab_cadastro" cellpadding="2" cellspacing="1">
							<thead>
								<tr>
									<th width="190">Filtro</th>
									<th width="400"></th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<th>Tipo de Evento:</th>
									<td>
										<h:selectOneMenu id="tipos" value="#{AssociadoBean.historicoFiltro.tipoEvento.codigo}" >
											<f:selectItem itemLabel="#{properties['lb_selecione']}" itemValue=""/>
											<f:selectItems id="sel_banco" value="#{AssociadoBean.tipos}"/>
										</h:selectOneMenu>
									</td>
								</tr>
								<tr>
									<th>Data do Evento:</th>
									<td>
										<rich:calendar id="dataInicio" popup="true" datePattern="dd/MM/yyyy" showApplyButton="false"
											cellWidth="24px" cellHeight="22px" style="width:200px" disabled="false" locale="en/US"
											value="#{AssociadoBean.historicoFiltro.dataInicio}" inputClass="inputCalendar" 
											enableManualInput="true" oninputblur="checkDate(this)" 
											oninputkeypress="return maskDate(this,event);"/>
										à
										<rich:calendar id="dataFim" popup="true" datePattern="dd/MM/yyyy" showApplyButton="false"
											cellWidth="24px" cellHeight="22px" style="width:200px" disabled="false" locale="en/US"
											value="#{AssociadoBean.historicoFiltro.dataFim}" inputClass="inputCalendar" enableManualInput="true"
											oninputblur="checkDate(this)" oninputkeypress="return maskDate(this,event);"/>
									</td>
								</tr>
							</tbody>
						</table>
						<t:commandLink action="#{AssociadoBean.consultarHistorico}" title="#{properties['lb_filtrar']}" id="filtrar" 
							styleClass="botao_filtrar" onclick="setAcao(document.forms[0], 'Filtrar_Historico');">
								<h:outputText value="#{properties['lb_filtrar']}" />
						</t:commandLink>
						<input type="hidden" name="acao" value=""/>
					</h:form>
					<br /><br /><br /><br />
					
					<h:form id="eventoResultado">
					<t:saveState value="#{AssociadoBean.historicoAssociado}" />
					<t:dataTable id="data" var="historico" value="#{AssociadoBean.historicoAssociado}" cellspacing="1" cellpadding="2" 
						width="100%" styleClass="tab_lista_maior" preserveDataModel="false">
							<t:column width="140">
								<f:facet name="header">
									<h:outputText value="#{properties['lb_matricula']}" />
								</f:facet>
								<h:outputText value="#{historico.associado.codigo}" />
								<f:facet name="footer">
									<t:commandLink onclick="setAcao(document.forms[0], 'Imprime_historico');" 
										action="#{AssociadoBean.printEventos}" title="#{properties['lb_imprimir']}" immediate="true" 
										id="print" styleClass="botao_imprimir" target="print">
									</t:commandLink>
						        </f:facet>
							</t:column>
				
							<t:column width="230">
								<f:facet name="header">
									<h:outputText value="#{properties['lb_nomeAssociado']}" />
								</f:facet>
								<h:outputText value="#{historico.associado.nome}" />
							</t:column>
							
							<t:column width="100">
								<f:facet name="header">
									<h:outputText value="#{properties['lb_tipoEvento']}" />
								</f:facet>
								<h:outputText value="#{historico.tipoEvento.nome}" />
							</t:column>
							
							<t:column width="100">
								<f:facet name="header">
									<h:outputText value="#{properties['lb_data']}" />
								</f:facet>
								<h:outputText value="#{historico.data}" />
							</t:column>
						</t:dataTable>
						</h:form>
				</div>
			</div>
		</body>
	</html>
</f:view>