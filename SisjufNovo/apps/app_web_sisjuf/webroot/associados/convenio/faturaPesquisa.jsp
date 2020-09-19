<?xml version="1.0" encoding="UTF-8"?>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<f:view>
	<f:loadBundle basename="sisjuf" var="properties" />
	<html>
		<head>
			<title>SISJUF - Sistema ASSERJUF</title>
			<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
			<link href="<c:url value="/nucleo/style/sisjuf.css"/>" type="text/css" rel="stylesheet" />
			<link rel="Shortcut Icon" type="image/png" href="<c:url value="/nucleo/images/icone.png"/>" />
			<script src="<c:url value="/nucleo/js/sisjuf.js" />" type="text/javascript"></script>
			<script type="text/javascript">
				function imprimir() {
					var url			= '<c:url value="/associados/convenio/faturaPrint.jsf"/>';
					var name		= 'telaImpressao';
					var features	= 'toolbar=no,status=no,resizable=yes,scrollbars=yes,width=750,height=500';
					window.open(url,name,features);
					return true;
				}
			</script>
		</head>
		<body>
			<div id="geral">
				<div id="topo">
					<div id="sair">
						sair <a href="<c:url value="/Logout"/>"><img src="<c:url value="/nucleo/images/botao_x.gif"/>" /></a>
					</div>
				</div>
				<%@ include file="/nucleo/includes/inc_menuFinanceiro.jsp"%>
				
				<div id="miolo">
					<h1>Módulo Convênios</h1>
					<h2>Pesquisar Faturas</h2>
					<a class="botao_novo" href="<c:url value="/associados/convenio/gerarFaturas.jsf"/>"><span>novo</span></a>
					<br /><br />
    				<h:form id="faturaForm" acceptcharset="ISO-8859-1">
    					<a4j:keepAlive beanName="FaturaBean" />
						<h:panelGroup id="faturasMsgs">
							<h:messages showDetail="true" showSummary="false" errorClass="textoMsgErro" infoClass="textoMsgInfo" />
						</h:panelGroup>
						<table class="tab_cadastro" cellpadding="2" cellspacing="1">
							<thead>
								<tr>
									<th>Filtro</th>
									<th colspan="3"></th>
								</tr>
							</thead>
							
							<tbody>
								<tr>
									<th width="140">Código Fatura:</th>
									<td width="140" >
										<h:inputText id="faturaCodigo" value="#{FaturaBean.faturaFiltro.codigo}" tabindex="1"></h:inputText>
									</td>
									<th width="140">Convênio:</th>
									<td width="140" >
										<h:selectOneMenu id="convenio" value="#{FaturaBean.faturaFiltro.convenio.codigo}" tabindex="2">
											<f:selectItem itemLabel="#{properties['lb_selecione']}" itemValue=""/>
											<t:selectItems id="sel_convenios" value="#{FaturaBean.allConvenios}" var="convenio" itemLabel="#{convenio.nomeFantasia}" 
												itemValue="#{convenio.codigo}"/>
										</h:selectOneMenu>
									</td>
								</tr>
								<tr>
								<th width="140">Périodo Início Fatura:</th>
								<td width="190">
									<rich:calendar id="dataInicioDe" popup="true" datePattern="dd/MM/yyyy" showApplyButton="false"
										cellWidth="24px" cellHeight="22px" style="width:200px" disabled="false" locale="en/US"
										value="#{FaturaBean.faturaFiltro.dataInicioDe}" inputClass="inputCalendar" 
										enableManualInput="true" oninputblur="checkDate(this)" oninputkeypress="return maskDate(this,event);" />
									<rich:calendar id="dataInicioAte" popup="true" datePattern="dd/MM/yyyy" showApplyButton="false"
										cellWidth="24px" cellHeight="22px" style="width:200px" disabled="false" locale="en/US"
										value="#{FaturaBean.faturaFiltro.dataInicioAte}" inputClass="inputCalendar" 
										enableManualInput="true" oninputblur="checkDate(this)" oninputkeypress="return maskDate(this,event);"/>
								</td>
								<th width="140">Périodo Fim Fatura:</th>
								<td width="190">
									<rich:calendar id="dataFimDe" popup="true" datePattern="dd/MM/yyyy" showApplyButton="false"
										cellWidth="24px" cellHeight="22px" style="width:200px" disabled="false" locale="en/US"
										value="#{FaturaBean.faturaFiltro.dataFimDe}" inputClass="inputCalendar" 
										enableManualInput="true" oninputblur="checkDate(this)" oninputkeypress="return maskDate(this,event);" />
									<rich:calendar id="dataFimAte" popup="true" datePattern="dd/MM/yyyy" showApplyButton="false"
										cellWidth="24px" cellHeight="22px" style="width:200px" disabled="false" locale="en/US"
										value="#{FaturaBean.faturaFiltro.dataFimAte}" inputClass="inputCalendar" 
										enableManualInput="true" oninputblur="checkDate(this)" oninputkeypress="return maskDate(this,event);"/>
								</td>
							</tr>
							<tr>
								<th width="140">Status:</th>
								<td width="140" colspan="3">
									<h:selectOneMenu id="statusFatura" value="#{FaturaBean.faturaFiltro.status.codigo}" >
										<f:selectItem itemLabel="#{properties['lb_selecione']}" itemValue=""/>
										<t:selectItems id="sel_status" value="#{FaturaBean.allStatusFatura}" var="status" itemLabel="#{status.nome}" 
											itemValue="#{status.codigo}"/>
									</h:selectOneMenu>
								</td>
							</tr>
							</tbody>
							
							
						</table>
						<a4j:commandButton action="#{FaturaBean.consulta}" title="#{properties['lb_filtrar']}" 
							styleClass="botao_filtrar" value="Pesquisar" reRender="faturasMsgs,faturas" id="btPesquisarFaura"/><br/><br/>
							
						
						<t:div id="div_faturas" styleClass="conteudo">
							<h2>&nbsp;</h2><br/>
							<h2>Resultado</h2>
							<rich:dataTable value="#{FaturaBean.faturas}" var="itens" border="0" id="faturas" width="100%" styleClass="tab_lista">
								<f:facet name="header">
									<rich:columnGroup>
									<rich:column><h:outputText value="Codigo Fatura" /></rich:column>
									<rich:column><h:outputText value="Convênio" /></rich:column>
									<rich:column><h:outputText value="Vencimento" /></rich:column>
									<rich:column><h:outputText value="Valor" /></rich:column>
									<rich:column><h:outputText value="Paga" /></rich:column>
									<rich:column><h:outputText value="Recebida" /></rich:column>
									<rich:column><h:outputText value="Status" /></rich:column>
									<!-- rich : column><h:outputText value="" />< / rich :c olumn-->
									</rich:columnGroup>
								</f:facet>
								
								<f:facet name="footer">
			            			<h:commandLink styleClass="botao_imprimir" title="#{properties['lb_imprimir']}" action="#{FaturaBean.print}" target="_blank">
			            			</h:commandLink>																			           
						        </f:facet>
								
								<rich:column style="width:15%">
									<t:commandLink action="#{FaturaBean.carregar}" title="#{properties['lb_carregar']}" immediate="true" id="carregar" >
										<t:updateActionListener property="#{FaturaBean.fatura.codigo}" value="#{itens.codigo}" />
										<h:outputText value="#{itens.codigo}" />
									</t:commandLink>
								</rich:column>
								
								<rich:column style="width:40%">
									<h:outputText value="#{itens.convenio.nomeFantasia}" />
								</rich:column>
								
								<rich:column style="width:30%">
									<h:outputText value="#{itens.dataVencimento}"/>
								</rich:column>
								
								<rich:column style="width:10%">
									<h:outputText value="#{itens.valorFatura}" converter="DoubleConverter"/>
								</rich:column>
								
								<rich:column style="width:30%">
									<h:outputText value="#{itens.pago ? 'Sim' : 'Não'}"/>
								</rich:column>
								
								<rich:column style="width:30%">
									<h:outputText value="#{itens.recebido  ? 'Sim' : 'Não'}"/>
								</rich:column>
								
								<rich:column style="width:30%">
									<h:outputText value="#{itens.status.nome}"/>
								</rich:column>
								
								<!-- rich:column style="width:30%">
									<t : commandLink action="# { LancamentoBean.realizarBaixaFatura}" title="Baixar Fatura" immediate="true" id="baixarFatura" 
									rendered=" # { !item.pago and item.status == 2}" styleClass="botao_baixar">
										< t : updateActionListener property="# { LancamentoBean.fatura.codigo}" value=" # { itens.codigo}" />
									</ t : commandLink>
									
								</ rich : column-->
								
							</rich:dataTable>
						</t:div>
						
						<h:inputHidden id="codigo" value="#{ConvenioBean.fatura.codigo}"/>
					</h:form>
					<%@ include file="itemFaturaForm.jsp"%>
					<br /><br /><br /><br /><br /><br /><hr>
				</div>
			</div>
		</body>
	</html>
</f:view>