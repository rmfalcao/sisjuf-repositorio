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
					<h2>Pesquisar Imposto de Renda</h2>
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
									<th width="140">Nº. Matrícula:</th>
									<td width="120"><t:inputText size="8" id="codigo" value="#{ImpostoBean.filtro.associado.codigo}" onkeypress="return justNumber(this,event)"/></td>
									<th width="140">Ano:</th>
									<td width="120"><t:inputText size="4" id="ano" value="#{ImpostoBean.filtro.ano}" onkeypress="return justNumber(this,event)" /></td>
								</tr>
							</tbody>
							
							
						</table>
						<a4j:commandButton action="#{ImpostoBean.consulta}" title="#{properties['lb_filtrar']}" 
							styleClass="botao_filtrar" value="Pesquisar" reRender="faturasMsgs,beneficiarios" id="btPesquisarFaura"/><br/><br/>
							
						
						<t:div id="div_faturas" styleClass="conteudo">
							<h2>&nbsp;</h2><br/>
							<h2>Resultado</h2>
							<rich:dataTable value="#{ImpostoBean.beneficiarios}" var="itens" border="0" id="beneficiarios" width="100%" styleClass="tab_lista">
								<f:facet name="header">
									<rich:columnGroup>
									<rich:column><h:outputText value="Convênio" /></rich:column>
									<rich:column><h:outputText value="CNPJ" /></rich:column>
									<rich:column><h:outputText value="Titular" /></rich:column>
									<rich:column><h:outputText value="Nome" /></rich:column>
									<rich:column><h:outputText value="Valor Pago" /></rich:column>
									</rich:columnGroup>
								</f:facet>
								
								<f:facet name="footer">
			            			<h:commandLink styleClass="botao_imprimir" title="#{properties['lb_imprimir']}" action="#{ImpostoBean.print}" target="_blank">
			            			</h:commandLink>																			           
						        </f:facet>
								
								<rich:column style="width:25%">
									<h:outputText value="#{itens.plano.convenio.nomeFantasia}" />
								</rich:column>
								
								<rich:column style="width:25%">
									<h:outputText value="#{itens.plano.convenio.cnpj}" />
								</rich:column>
								
								<rich:column style="width:20%">
									<h:outputText value="#{itens.titular.nome}"/>
								</rich:column>
								
								<rich:column style="width:20%">
									<h:outputText value="#{itens.nome}"/>
								</rich:column>
								
								<rich:column style="width:10%">
									<h:outputText value="#{itens.valorPago}" converter="DoubleConverter"/>
								</rich:column>
								
							</rich:dataTable>
						</t:div>
					</h:form>
					<br /><br /><br /><br /><br /><br /><hr>
				</div>
			</div>
		</body>
	</html>
</f:view>