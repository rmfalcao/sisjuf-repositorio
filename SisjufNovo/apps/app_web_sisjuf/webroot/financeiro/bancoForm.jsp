<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
				</script>
		</head>
		<body>
			<div id="geral">
				<div id="topo">
					<div id="sair">
						sair <a href="<c:url value="/login.jsf"/>"><img src="<c:url value="/nucleo/images/botao_x.gif"/>" /></a>
					</div>
					<div id="voltar">
						voltar <a href="<c:url value="/financeiro/bancoConsulta.jsf"/>"><img src="<c:url value="/nucleo/images/setinhavoltar.gif"/>" /></a>
					</div>
				</div>
		
				<%@ include file="/nucleo/includes/inc_menuFinanceiro.jsp" %>
				
				<h:form id="BancoForm">
					<div id="miolo">
						<h1>Módulo Financeiro</h1>
						<h2>Banco - Cadastro</h2>
						<t:messages id="msgs" showDetail="true" showSummary="false" errorClass="textoMsgErro" infoClass="textoMsgInfo"/>
						<table class="tab_cadastro" cellpadding="2" cellspacing="1">
							<thead>
								<tr>
									<th>Dados do Banco</th>
									<th colspan="3"></th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<th width="150"><h:outputLabel for="nome" value="#{properties['lb_nome']}" />:</th>
									<td width="200">
										<t:inputText id="nome" value="#{BancoBean.banco.nome}" size="30" maxlength="30" required="true"/>
									</td>
									
									<th width="140"><h:outputLabel for="numero" value="#{properties['lb_numero']}" />:</th>
									<td width="100">
										<t:inputText id="numero" value="#{BancoBean.banco.numero}" size="5" maxlength="3" required="true"  onkeypress="return justNumber(this,event)"/>
									</td>
								</tr>
								<tr>
									<th><h:outputLabel for="sigla" value="#{properties['lb_sigla']}" />:</th>
									<td colspan="3">
										<t:inputText id="sigla" value="#{BancoBean.banco.sigla}" size="10" maxlength="6" required="true"/>
									</td>
								</tr>
							</tbody>
						</table>
						
						<br />
						<t:commandLink action="#{BancoBean.salvar}" title="#{properties['lb_salvar']}" id="salvar" styleClass="botao_salvar"
							onclick="setAcao(document.forms[0], 'Salva_Banco');">
							<h:outputText value="#{properties['lb_salvar']}" />
						</t:commandLink>
					</div>
					<input type="hidden" name="acao" value="Salva_Banco"/>
					<h:inputHidden id="codigo" value="#{BancoBean.banco.codigo}"/>
				</h:form>
			</div>
		</body>
	</html>

</f:view>