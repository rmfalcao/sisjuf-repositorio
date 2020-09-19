<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>

<f:view>
	<f:loadBundle basename="sisjuf" var="properties" />
	<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
	<html>
		<head>
			<title>SISJUF - Sistema ASSERJUF</title>
			<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
			<link href="<c:url value="/nucleo/style/sisjuf.css"/>" type="text/css" rel="stylesheet" />
			<link rel="Shortcut Icon" type="image/png" href="<c:url value="/nucleo/images/icone.png"/>">
			<script src="<c:url value="/nucleo/js/sisjuf.js" />" type="text/javascript"></script>
			<script>
				function setAcao(form, acao){
					form.acao.value = acao;
				}
				
				function validaForm(){
					if (document.forms[0].elements['contaCaixa'].checked){
						//alert("Ta selecionado");
						if (!document.forms[0].elements['banco'].disabled){
							document.forms[0].elements['banco'].value = "";
							document.forms[0].elements['banco'].disabled = true;
						}
						if (!document.forms[0].elements['numeroAgencia'].readOnly){
							document.forms[0].elements['numeroAgencia'].value = "";
							document.forms[0].elements['numeroAgencia'].readOnly = true;
						}
						if (!document.forms[0].elements['digitoAgencia'].readOnly){
							document.forms[0].elements['digitoAgencia'].value = "";
							document.forms[0].elements['digitoAgencia'].readOnly = true;
						}
						if (!document.forms[0].elements['numeroConta'].readOnly){
							document.forms[0].elements['numeroConta'].value = "";
							document.forms[0].elements['numeroConta'].readOnly = true;
						}
						if (!document.forms[0].elements['digitoConta'].readOnly){
							document.forms[0].elements['digitoConta'].value = "";
							document.forms[0].elements['digitoConta'].readOnly = true;
						}
						if (!document.forms[0].elements['codigodaOperacao'].readOnly){
							document.forms[0].elements['codigodaOperacao'].value = "";
							document.forms[0].elements['codigodaOperacao'].readOnly = true;
						}
						if (!document.forms[0].elements['cpfCnpj'].readOnly){
							document.forms[0].elements['cpfCnpj'].value = "";
							document.forms[0].elements['cpfCnpj'].readOnly = true;
						}
						if (!document.forms[0].elements['tipoConta'].disabled){
							document.forms[0].elements['tipoConta'].disabled = "";
							document.forms[0].elements['tipoConta'].disabled = true;
						}
						if (!document.forms[0].elements['titularConta'].readOnly){
							document.forms[0].elements['titularConta'].value = "";
							document.forms[0].elements['titularConta'].readOnly = true;
						}
						
						
					}else{
						//alert("Não esta selecionado");
						if (document.forms[0].elements['banco'].disabled)
							document.forms[0].elements['banco'].disabled = false;
							
						if (document.forms[0].elements['numeroAgencia'].readOnly)
							document.forms[0].elements['numeroAgencia'].readOnly = false;
							
						if (document.forms[0].elements['digitoAgencia'].readOnly)
							document.forms[0].elements['digitoAgencia'].readOnly = false;
							
						if (document.forms[0].elements['tipoConta'].disabled)
							document.forms[0].elements['tipoConta'].disabled = false;
							
						if (document.forms[0].elements['numeroConta'].readOnly)
							document.forms[0].elements['numeroConta'].readOnly = false;
							
						if (document.forms[0].elements['digitoConta'].readOnly)
							document.forms[0].elements['digitoConta'].readOnly = false;
						
						if (document.forms[0].elements['codigodaOperacao'].readOnly)
							document.forms[0].elements['codigodaOperacao'].readOnly = false;
						
						if (document.forms[0].elements['cpfCnpj'].readOnly)
							document.forms[0].elements['cpfCnpj'].readOnly = false;
						
						if (document.forms[0].elements['titularConta'].readOnly)
							document.forms[0].elements['titularConta'].readOnly = false;
					}
				}
			</script>
		</head>
		<body>
		
			<div id="geral">
				<div id="topo">
					<div id="sair">
						sair <a href="<c:url value="/seguranca/logout.jsp"/>"><img src="<c:url value="/nucleo/images/botao_x.gif"/>" /></a>
					</div>
					<div id="voltar">
						voltar <a href="<c:url value="/financeiro/contaConsulta.jsf"/>"><img src="<c:url value="/nucleo/images/setinhavoltar.gif"/>" /></a>
					</div>
				</div>
		
				<%@ include file="/nucleo/includes/inc_menuFinanceiro.jsp" %>
				
				<h:form id="ContaBancoForm">
		
					<div id="miolo">
						<h1>Módulo Financeiro</h1>
						<h2>Conta - Cadastro</h2>
						<t:messages id="msgs" showDetail="true" showSummary="false" errorClass="textoMsgErro" infoClass="textoMsgInfo"/>
						<table class="tab_cadastro" cellpadding="2" cellspacing="1">
							<thead>
								<tr>
									<th>Dados da Conta</th>
									<th colspan="3"></th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<th><h:outputLabel for="nomeConta" value="#{properties['lb_nomeConta']}" />:</th>
									<td colspan="2">
										<t:inputText id="nomeConta" size="30" value="#{ContaBancoBean.conta.nome}" 
											required="true" maxlength="30" forceId="true"/>
									</td>
									<td>
										<t:selectBooleanCheckbox id="contaCaixa" value="#{ContaBancoBean.flgContaCaixa}" 
											onclick="validaForm()" forceId="true"/> Conta Caixa
									</td>
								</tr>
								<tr>
									<th>Banco:</th>
									<td colspan="3">
										<t:selectOneMenu id="banco" value="#{ContaBancoBean.conta.bancoVO.codigo}" 
											forceId="true">
											<f:selectItem itemLabel="#{properties['lb_selecione']}" itemValue=""/>
											<f:selectItems id="sel_bancos" value="#{ContaBancoBean.bancos}"/>
										</t:selectOneMenu>
									</td>
								</tr>
								<tr>
									<th width="130">Nº Agência:</th>
									<td width="190">
										<t:inputText id="numeroAgencia" size="5" maxlength="4" 
											value="#{ContaBancoBean.conta.numAgencia}" forceId="true" onkeypress="return justNumber(this,event)"/> - 
										<t:inputText id="digitoAgencia" size="1" maxlength="1" 
											value="#{ContaBancoBean.conta.digAgencia}" forceId="true" onkeypress="return justNumberX(this,event)"/>
									</td>
									<th width="130">Nº Conta:</th>
									<td width="140">
										<t:inputText id="numeroConta" size="7" maxlength="10" 
											value="#{ContaBancoBean.conta.numConta}" forceId="true" onkeypress="return justNumber(this,event)"/> - 
										<t:inputText id="digitoConta" size="1" maxlength="1" 
											value="#{ContaBancoBean.conta.digConta}" forceId="true" onkeypress="return justNumberX(this,event)"/>
									</td>
								</tr>
								<tr>
									<th>Tipo da Conta:</th>
									<td>
										<t:selectOneMenu id="tipoConta" value="#{ContaBancoBean.conta.tipoContaVO.codigo}" 
											forceId="true">
											<f:selectItem itemLabel="#{properties['lb_selecione']}" itemValue=""/>
											<f:selectItems id="sel_tipoContas" value="#{ContaBancoBean.tipoContas}"/>
										</t:selectOneMenu>
									</td>
									<th>Cod. Operação:</th>
									<td>
										<t:inputText id="codigodaOperacao" size="5" maxlength="3" 
											value="#{ContaBancoBean.conta.codOperacao}" forceId="true"  onkeypress="return justNumber(this,event)"/>
									</td>
								</tr>
								<tr>
									<th>Titular da Conta:</th>
									<td>
										<t:inputText id="titularConta" size="30" value="#{ContaBancoBean.conta.nomTitular}" 
											maxlength="60" forceId="true"/>
									</td>
									<th>CPF/CNPJ do Titular:</th>
									<td>
										<t:inputText id="cpfCnpj" size="14" maxlength="14" 
											value="#{ContaBancoBean.conta.cpfCnpjTitular}" forceId="true"  onkeypress="return justNumber(this,event)"/>
									</td>
								</tr>
							</tbody>
						</table>
						<br />
						<t:commandLink action="#{ContaBancoBean.salvar}" title="#{properties['lb_salvar']}" id="salvar" styleClass="botao_salvar"
							onclick="setAcao(document.forms[0], 'Salva_Conta');">
							<h:outputText value="#{properties['lb_salvar']}" />
						</t:commandLink>
						<input type="hidden" name="acao" value=""/>
						<h:inputHidden id="codigo" value="#{ContaBancoBean.conta.codigo}"/>
					</div>
				</h:form>
			</div>
		</body>
		<script>
			if (document.forms[0].elements['contaCaixa'].checked){
				//alert("Ta selecionado");
				if (!document.forms[0].elements['banco'].disabled){
					document.forms[0].elements['banco'].value = "";
					document.forms[0].elements['banco'].disabled = true;
				}
				if (!document.forms[0].elements['numeroAgencia'].readOnly){
					document.forms[0].elements['numeroAgencia'].value = "";
					document.forms[0].elements['numeroAgencia'].readOnly = true;
				}
				if (!document.forms[0].elements['digitoAgencia'].readOnly){
					document.forms[0].elements['digitoAgencia'].value = "";
					document.forms[0].elements['digitoAgencia'].readOnly = true;
				}
				if (!document.forms[0].elements['numeroConta'].readOnly){
					document.forms[0].elements['numeroConta'].value = "";
					document.forms[0].elements['numeroConta'].readOnly = true;
				}
				if (!document.forms[0].elements['digitoConta'].readOnly){
					document.forms[0].elements['digitoConta'].value = "";
					document.forms[0].elements['digitoConta'].readOnly = true;
				}
				if (!document.forms[0].elements['codigodaOperacao'].readOnly){
					document.forms[0].elements['codigodaOperacao'].value = "";
					document.forms[0].elements['codigodaOperacao'].readOnly = true;
				}
				if (!document.forms[0].elements['cpfCnpj'].readOnly){
					document.forms[0].elements['cpfCnpj'].value = "";
					document.forms[0].elements['cpfCnpj'].readOnly = true;
				}
				if (!document.forms[0].elements['tipoConta'].disabled){
					document.forms[0].elements['tipoConta'].disabled = "";
					document.forms[0].elements['tipoConta'].disabled = true;
				}
				if (!document.forms[0].elements['titularConta'].readOnly){
					document.forms[0].elements['titularConta'].value = "";
					document.forms[0].elements['titularConta'].readOnly = true;
				}
			}else{
				//alert("Não esta selecionado");
				if (document.forms[0].elements['banco'].disabled)
					document.forms[0].elements['banco'].disabled = false;
					
				if (document.forms[0].elements['numeroAgencia'].readOnly)
					document.forms[0].elements['numeroAgencia'].readOnly = false;
					
				if (document.forms[0].elements['digitoAgencia'].readOnly)
					document.forms[0].elements['digitoAgencia'].readOnly = false;
					
				if (document.forms[0].elements['tipoConta'].disabled)
					document.forms[0].elements['tipoConta'].disabled = false;
					
				if (document.forms[0].elements['numeroConta'].readOnly)
					document.forms[0].elements['numeroConta'].readOnly = false;
					
				if (document.forms[0].elements['digitoConta'].readOnly)
					document.forms[0].elements['digitoConta'].readOnly = false;
				
				if (document.forms[0].elements['codigodaOperacao'].readOnly)
					document.forms[0].elements['codigodaOperacao'].readOnly = false;
				
				if (document.forms[0].elements['cpfCnpj'].readOnly)
					document.forms[0].elements['cpfCnpj'].readOnly = false;
				
				if (document.forms[0].elements['titularConta'].readOnly)
					document.forms[0].elements['titularConta'].readOnly = false;
			}
		</script>
	</html>
</f:view>