<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
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
			<link href="<c:url value="/nucleo/style/sisjuf.css?v=2"/>" type="text/css" rel="stylesheet" />
			<link rel="Shortcut Icon" type="image/png" href="<c:url value="/nucleo/images/icone.png"/>">
			<script src="<c:url value="/nucleo/js/sisjuf.js" />" type="text/javascript"></script>
			<script>
				function imprimir() {
				
					// testando campos obrigatórios:
					
					if (document.forms[0].elements['LancamentoConsulta:dataInicial'].value == '') {
						alert('A data inicial deve ser informada.');
						return;
					}
					
					if (document.forms[0].elements['LancamentoConsulta:_id0'].value == '') {
						alert('A data final deve ser informada.');
						return;					
					}
					
					var url	= '<c:url value="/Financeiro/Lancamento/Imprimir"/>?dataIni=' + document.forms[0].elements['LancamentoConsulta:dataInicial'].value + '&dataFim=' + document.forms[0].elements['LancamentoConsulta:_id0'].value + '&conta=' + document.forms[0].elements['LancamentoConsulta:Conta'].value + '&origem=' + document.forms[0].elements['LancamentoConsulta:Origem'].value + '&tipo=' + document.forms[0].elements['LancamentoConsulta:TipoLançamento'].value + '&op=' + document.forms[0].elements['LancamentoConsulta:TipoOperação'].value + '&vMenor=' + document.forms[0].elements['LancamentoConsulta:valorMenot'].value + '&vMaior=' + document.forms[0].elements['LancamentoConsulta:valorMaior'].value;
					
					if (document.forms[0].elements['LancamentoConsulta:efetivacao'].checked) {
						url += '&fEfet=S';
					}
					if (document.forms[0].elements['LancamentoConsulta:previsao'].checked) {
						url += '&fPrev=S';
					}
					if (document.forms[0].elements['LancamentoConsulta:naoQuitado'].checked) {
						url += '&fNQuit=S';
					}
					var name		= 'telaImpressao';
					var features	= 'toolbar=no,status=no,resizable=yes,scrollbars=yes,width=750,height=500';
					window.open(url,name,features);
	
				}			
			
				function setAcao(form, acao){
					form.acao.value = acao;
				}
				
				function validaRemocao(){
					if (document.forms[1].elements['acao'].value == 'Remove_Lancamento'){
						if (confirm("Você tem certeza de que deseja estornar este lançamento?")){
							return true;
						} else return false;
					}return true;
				}
				
			</script>
		</head>
		<body>
		
			<div id="geral">
				<div id="topo">
					<div id="sair">
						sair <a href="<c:url value="/login.jsf"/>"><img src="<c:url value="/nucleo/images/botao_x.gif"/>" /></a>
					</div>
					<div id="voltar">
						voltar <a href="<c:url value="/financeiro/index.jsp"/>"/><img src="<c:url value="/nucleo/images/setinhavoltar.gif"/>" /></a>
					</div>
				</div>
		
				<%@ include file="/nucleo/includes/inc_menuFinanceiro.jsp" %>
		
				<div id="miolo">
					<h1>Módulo Financeiro</h1>
					<h2>Consultar Lançamentos</h2>
					<a class="botao_novo" href="<c:url value="/financeiro/lancamentoForm.jsf"/>"><span>novo</span></a>
					<br /><br />
					<t:messages id="msgs" showDetail="true" showSummary="false" errorClass="textoMsgErro" infoClass="textoMsgInfo"/>
					<h:form id="LancamentoConsulta">
						<table class="tab_cadastro" cellpadding="2" cellspacing="1">
							<thead>
								<tr>
									<th>Filtro</th>
									<th colspan="4"></th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<th>Período do Lançamento:</th>
									<td colspan="4">
										<rich:calendar id="dataInicial" popup="true" datePattern="dd/MM/yyyy" showApplyButton="false"
											cellWidth="24px" cellHeight="22px" style="width:200px" disabled="false" locale="en/US"
											value="#{LancamentoBean.filtro.dataEfetivacaoInicial}" inputClass="inputCalendar"
											enableManualInput="true"/>
											à
										<rich:calendar id="dataFinal" popup="true" datePattern="dd/MM/yyyy" showApplyButton="false"
										cellWidth="24px" cellHeight="22px" style="width:200px" disabled="false" locale="en/US"
										value="#{LancamentoBean.filtro.dataEfetivacaoFinal}" inputClass="inputCalendar"
											enableManualInput="true"/>
										
									</td>
								</tr>
								<tr>
									<th width="180"></th>
									<td width="20">
										<t:selectBooleanCheckbox id="efetivacao" value="#{LancamentoBean.efetivacao}"/>
									</td>
									<td width="185">Efetivação</td>
									<td width="20">
										<t:selectBooleanCheckbox id="previsao" value="#{LancamentoBean.previsao}"/>
									</td>
									<td width="185">Previsão</td>
								</tr>
								<tr>
									<th>Conta:</th>
									<td colspan="4">
										<h:selectOneMenu id="Conta" value="#{LancamentoBean.filtro.contaVO.codigo}" >
											<f:selectItem itemLabel="#{properties['lb_selecione']}" itemValue=""/>
											<f:selectItems id="sel_contas_inclusive_excluidas" value="#{LancamentoBean.contasInclusiveExcluidas}"/>
										</h:selectOneMenu>
									</td>
								</tr>
								<tr>
									<th>Origem do Lançamento:</th>
									<td colspan="4">
										<h:selectOneMenu id="Origem" value="#{LancamentoBean.filtro.origemLancamentoVO.codigo}" >
											<f:selectItem itemLabel="#{properties['lb_selecione']}" itemValue=""/>
											<f:selectItems id="sel_origemLancamento" value="#{LancamentoBean.origemLancamentos}"/>
										</h:selectOneMenu>
									</td>
								</tr>
								<tr>
									<th>Tipo do Lancamento:</th>
									<td colspan="4">
										<h:selectOneMenu id="TipoLançamento" value="#{LancamentoBean.filtro.tipoLancamentoVO.codigo}" >
											<f:selectItem itemLabel="#{properties['lb_selecione']}" itemValue=""/>
											<f:selectItems id="sel_tipoLancamento" value="#{LancamentoBean.tipoLancamentos}"/>
										</h:selectOneMenu>
									</td>
								</tr>
								<tr>
									<th>Tipo de Operação:</th>
									<td colspan="4">
										<h:selectOneMenu id="TipoOperação" value="#{LancamentoBean.filtro.tipoOperacaoVO.codigo}" >
											<f:selectItem itemLabel="#{properties['lb_selecione']}" itemValue=""/>
											<f:selectItems id="sel_tipoOperacoes" value="#{LancamentoBean.tipoOperacoes}"/>
										</h:selectOneMenu>
									</td>
								</tr>
								<tr>
									<th>Valor:</th>
									<td colspan="4">
										de <t:inputText id="valorMenot" size="15" converter="DoubleConverter" value="#{LancamentoBean.filtro.valorMenor}" 
											onkeyup="formatarCampoNumero(this)"/>
										à
										<t:inputText id="valorMaior" size="15" converter="DoubleConverter" value="#{LancamentoBean.filtro.valorMaior}" 
											onkeyup="formatarCampoNumero(this)"/>
									</td>
								</tr>
								<tr>
									<th></th>
									<td>
										<t:selectBooleanCheckbox id="naoQuitado" value="#{LancamentoBean.naoQuitado}"/>
									</td>
									<td colspan="3">Não Quitado</td>
								</tr>
							</tbody>
						</table>
						<t:commandLink action="#{LancamentoBean.consultar}" title="#{properties['lb_filtrar']}" id="filtrar" styleClass="botao_filtrar"
						onclick="setAcao(document.forms[0], 'Filtrar_Lancamento');">
							<h:outputText value="#{properties['lb_filtrar']}" />
						</t:commandLink>
						<input type="hidden" name="acao" value=""/>
					</h:form>
					<h:form id="LancamentoResultado" onsubmit="return validaRemocao();">
					
						<input type="hidden" name="acao" value=""/>
					
						<t:saveState value="#{LancamentoBean.lancamentoAssembler.lancamentos}" />
						<t:saveState value="#{LancamentoBean.lancamento}" />
						<t:saveState value="#{LancamentoBean.filtro}" />
						<br /><br /><b>Diretoria financeira: </b>
						<h:outputText value="#{LancamentoBean.lancamentoAssembler.diretorFinanceiroVO.nome}" />
						<br /><br />
						<t:dataTable id="data" var="lancamentos" value="#{LancamentoBean.lancamentoAssembler.lancamentos}"
							cellspacing="1" cellpadding="2" width="100%" styleClass="tab_lista_maior"
							preserveDataModel="false" >
								
							<t:column width="60">
								<f:facet name="header">
									<h:outputText value="#{properties['lb_efetivacao']}" />
								</f:facet>
								<h:outputText value="#{lancamentos.dataEfetivacao}" />
								<f:facet name="footer">
					            	<h:outputLink value="javascript:void(0);" onclick="imprimir();" styleClass="botao_imprimir" title="#{properties['lb_imprimir']}"></h:outputLink>
						        </f:facet>
							</t:column>
				
							<t:column width="60">
								<f:facet name="header">
									<h:outputText value="#{properties['lb_previsao']}" />
								</f:facet>
								<h:outputText value="#{lancamentos.dataPrevisao}" />
							</t:column>
							
							<t:column width="130">
								<f:facet name="header">
									<h:outputText value="#{properties['lb_conta']}" />
								</f:facet>
								<h:outputText value="#{lancamentos.contaVO.nome}" />
							</t:column>
							
							<t:column width="60">
								<f:facet name="header">
									<h:outputText value="#{properties['lb_operacao']}" />
								</f:facet>
								<h:outputText value="#{lancamentos.tipoOperacaoVO.sigla}" />
							</t:column>
							
							<t:column width="80">
								<f:facet name="header">
									<h:outputText value="#{properties['lb_origem']}" />
								</f:facet>
								<h:outputText value="#{lancamentos.origemLancamentoVO.nome}" />

							</t:column>

							<t:column width="80">
								<f:facet name="header">
									<h:outputText value="#{properties['lb_descricao']}" />
								</f:facet>
								<h:outputText value="#{lancamentos.descricao}" />

							</t:column>
							
							<t:column width="80">
								<f:facet name="header">
									<h:outputText value="#{properties['lb_forma_pagamento']}" />
								</f:facet>
								<h:outputText value="#{lancamentos.descricaoCompletaFormaPagamento}" />
								<f:facet name="footer">
						            <h:outputText value="#{properties['lb_valorTotal']}" />
						        </f:facet>
							</t:column>
							
							<t:column width="90">
								<f:facet name="header">
									<h:outputText value="#{properties['lb_valor_reais']}" />
								</f:facet>
								<h:outputText value="#{lancamentos.valor}" converter="DoubleConverter" styleClass="#{lancamentos.valor < 0 ? 'valor_negativo' : ''}"/>
								<f:facet name="footer">
					            	<h:outputText value="#{LancamentoBean.totalLancado}" converter="DoubleConverter" />
						        </f:facet>
							</t:column>
							
							
							<t:column width="20">
								<f:facet name="header">
								
								</f:facet>
								
								<a href="javascript:void(0)" onclick="alert('Feito por <h:outputText value="#{lancamentos.usuario.nome}" /> em <h:outputText value="#{lancamentos.dataCadastro}" />')">info</a> 
							</t:column>
							
							
							<t:column width="20">
								<f:facet name="header">
									
								</f:facet>
								<t:commandLink action="#{LancamentoBean.baixar}" title="#{properties['lb_baixar']}" immediate="true" id="baixar" 
									onclick="setAcao(document.forms[1], 'Baixa_Lancamento')" styleClass="botao_baixar" rendered="#{lancamentos.tipoOperacaoVO.codigo == 3 || lancamentos.tipoOperacaoVO.codigo == 4}">
									<t:updateActionListener property="#{LancamentoBean.lancamento.codigo}" value="#{lancamentos.codigo}" />
								</t:commandLink>
							</t:column>

							<t:column width="20">
								<f:facet name="header">
								
								</f:facet>
								<t:commandLink action="#{LancamentoBean.detalhar}" title="#{properties['lb_alterar']}" immediate="true" id="detalhar" 
									onclick="setAcao(document.forms[1], 'Detalha_Lancamento')" styleClass="botao_editar">
									<t:updateActionListener property="#{LancamentoBean.lancamento.codigo}" value="#{lancamentos.codigo}" />
								</t:commandLink>
							</t:column>
							
							<t:column width="20">
								<f:facet name="header">
								
								</f:facet>
								<t:commandLink action="#{LancamentoBean.remover}" title="#{properties['lb_remover']}" immediate="true" id="remover" 
									onclick="setAcao(document.forms[1], 'Remove_Lancamento')" styleClass="botao_excluir">
									<t:updateActionListener property="#{LancamentoBean.lancamento.codigo}" value="#{lancamentos.codigo}" />
								</t:commandLink>
							</t:column>
							

							
							<!-- t:column>
								< t : panelGroup>
									< t : commandLink action=" # { LancamentoBean.detalharVinculacaoLancamento}" title="Detalhar Vinculação" immediate="true" rendered=" # { lancamentos.tipoOperacaoVO.codigo == 3}">
										< t :updateActionListener property=" # { LancamentoBean.lancamento.codigo}" value=" # { lancamentos.codigo}" />
										< h : outputText value="Detalhar Vinculação" />
									</ t : commandLink>
									< t : commandLink action=" # { FaturaBean.carregarFaturaADebitarAPartirLancamento}" title="Detalhar Vinculação" immediate="true" rendered=" # { lancamentos.tipoOperacaoVO.codigo == 4}">
											< t : updateActionListener property=" # { FaturaBean.lancamento.codigo}" value=" # { lancamentos.codigo}" />
											<h : outputText value="Detalhar Vinculação" />
									</ t : commandLink>
								</ t : panelGroup>
								
							</ t :column-->

						</t:dataTable>
						
					</h:form>
	
				</div>
			</div>
		
		</body>
	</html>
</f:view>
