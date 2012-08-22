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
			
		</head>
		<body>
		
			<div id="geral">
				<div id="topo">
					<div id="sair">
						sair <a href="<c:url value="/login.jsf"/>"><img src="<c:url value="/nucleo/images/botao_x.gif"/>" /></a>
					</div>
					<div id="voltar">
						voltar <a href="<c:url value="/seguranca/usuarioConsulta.jsf"/>"><img src="<c:url value="/nucleo/images/setinhavoltar.gif"/>" /></a>
					</div>
				</div>
		
				<%@ include file="/nucleo/includes/inc_menuFinanceiro.jsp" %>
				<h:form id="AlterarSenhaForm">
					<div id="miolo">
						<h1>Alterar Senha</h1>
						<t:messages id="msgs" showDetail="true" showSummary="false" errorClass="textoMsgErro" infoClass="textoMsgInfo"/>
						<table class="tab_cadastro" cellpadding="2" cellspacing="1">
							<thead>
								<tr>
									<th>Dados do Usuário</th>
									<th colspan="3"></th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<th width="180"><h:outputLabel for="login" value="#{properties['lb_loginUsuario']}" />:</th>
									<td width="170"><t:inputText id="login" value="#{UsuarioBean.usuario.login}" size="20" maxlength="20" required="true"/></td>
									<th width="140"><h:outputLabel for="senhaAnt" value="#{properties['lb_senhaAtual']}" />:</th>
									<td width="100"><t:inputSecret id="senhaAnt" value="#{UsuarioBean.usuario.senha}" size="10" maxlength="10" required="true"/></td>
								</tr>
								<tr>
									<th><h:outputLabel for="nSenha" value="#{properties['lb_novaSenha']}" />:</th>
									<td colspan="3"><t:inputSecret id="nSenha" value="#{UsuarioBean.nsenha}" size="10" maxlength="10" required="true"/></td>
								</tr>
								<tr>
									<th><h:outputLabel for="rSenha" value="#{properties['lb_confirmaSenha']}" />:</th>
									<td colspan="3"><t:inputSecret id="rSenha" value="#{UsuarioBean.rsenha}" size="10" maxlength="10" required="true"/></td>
								</tr>
							</tbody>
						</table>
						
						<br />
						<t:commandLink action="#{UsuarioBean.mudarSenha}" title="#{properties['lb_salvar']}" id="salvar" styleClass="botao_salvar">
							<h:outputText value="#{properties['lb_salvar']}" />
						</t:commandLink>
						<input type="hidden" name="acao" value="Muda_Senha"/>
					</div>
				</h:form>
			</div>
		
		</body>
	</html>
</f:view>