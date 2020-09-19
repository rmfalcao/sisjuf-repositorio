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
			</script>
		</head>
		<body>
		
			<div id="geral">
				<div id="topo">
					<div id="sair">
						sair <a href="<c:url value="/login.jsf"/>"><img src="<c:url value="/nucleo/images/botao_x.gif"/>" /></a>
					</div>
					<div id="voltar">
						voltar <a href="<c:url value="/financeiro/origemLancamentoConsulta.jsf"/>"><img src="<c:url value="/nucleo/images/setinhavoltar.gif"/>" /></a>
					</div>
				</div>
		
				<%@ include file="/nucleo/includes/inc_menuFinanceiro.jsp" %>
				<h:form id="OrigemLancamentoForm">
					<div id="miolo">
						<h1>Módulo Financeiro</h1>
						<h2>Origens de Lançamentos - Cadastro</h2>
						<t:messages id="msgs" showDetail="true" showSummary="false" errorClass="textoMsgErro" infoClass="textoMsgInfo"/>
						<table class="tab_cadastro" cellpadding="2" cellspacing="1">
							<thead>
								<tr>
									<th width="190">Origem do Lançamento</th>
									<th width="400"></th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<th><h:outputLabel for="origem" value="#{properties['lb_origem']}" />:</th>
									<td>
										<t:inputText id="origem" value="#{OrigemLancamentoBean.origemLancamento.nome}" size="30" maxlength="20" 
											required="true"/>
									</td>
								</tr>
							</tbody>
						</table>
						
						<br />
						<t:commandLink action="#{OrigemLancamentoBean.salvar}" title="#{properties['lb_salvar']}" id="salvar" styleClass="botao_salvar"
						onclick="setAcao(document.forms[0], 'Salva_OrigemLancamento');">
						<h:outputText value="#{properties['lb_salvar']}" />
						</t:commandLink>
						<input type="hidden" name="acao" value=""/>
						<h:inputHidden id="codigo" value="#{OrigemLancamentoBean.origemLancamento.codigo}"/>
					</div>
				</h:form>
			</div>
		
		</body>
	</html>
</f:view>