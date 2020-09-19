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
	                <script>
	                    function setAcao(form, acao){
	                        form.acao.value = acao;
	                    }
	                    
	                    function limpa(acao){
	                    	
	                    	document.forms[0].acao.value = acao;
	                    	document.forms[0].elements['valor'].value = "";
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
		
				<%@ include file="/nucleo/includes/inc_menuFinanceiro.jsp" %>
				
				<h:form id="SaldoForm">
					<div id="miolo">
						<h1>Módulo Financeiro</h1>
						<h2>Saldo</h2>
						<t:messages id="msgs" showDetail="true" showSummary="false" errorClass="textoMsgErro" infoClass="textoMsgInfo"/>
						<table class="tab_cadastro" cellpadding="2" cellspacing="1">
							<thead>
								<tr>
									<th width="180">Filtro</th>
									<th width="410"></th>
								</tr>
							</thead>
							<tbody>
								<tr>					
									<th><h:outputLabel for="conta" value="#{properties['lb_conta']}" />:</th>
									<td>
										<h:selectOneMenu id="conta" value="#{SaldoBean.conta.codigo}" 
											valueChangeListener="#{SaldoBean.baixaSaldo}" onchange="limpa('Obter_Saldo');submit()" immediate="true">
											<f:selectItem itemLabel="#{properties['lb_selecione']}" itemValue=""/>
											<f:selectItems id="sel_contas" value="#{SaldoBean.contas}" />
										</h:selectOneMenu>
									</td>
								</tr>
								<tr>
									<th>Valor (R$):</th>
									<td>
										<t:inputText id="valor" value="#{SaldoBean.conta.saldo}" size="27" converter="DoubleConverter" readonly="true"
											forceId="true" immediate="true"/>
										
									</td>
								</tr>
							</tbody>
						</table>
					</div>
					<input type="hidden" name="acao" value=""/>
				</h:form>
			</div>
		</body>
	</html>
</f:view>