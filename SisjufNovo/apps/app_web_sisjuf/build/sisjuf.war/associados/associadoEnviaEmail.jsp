<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<f:view>
	<html>
			<head>
				<title>SISJUF - Sistema ASSERJUF</title>
				<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
				<link href="<c:url value="/nucleo/style/sisjuf.css"/>" type="text/css"	rel="stylesheet" />
				<link rel="Shortcut Icon" type="image/png" href="<c:url value="/nucleo/images/icone.png"/>">
			</head>
			
			<script language="JavaScript">
			function setAcao(form, acao){
				form.acao.value = acao;
			}
			</script>
			
			<body>
		
			<div id="geral">
				<div id="topo">
					<div id="sair">
						sair <a href="index.htm"><img src="imagens/botao_x.gif" /></a>
					</div>
					<div id="voltar">
						voltar <a href="#"><img src="imagens/setinhavoltar.gif" /></a>
					</div>
				</div>
				
				<%@ include file="/nucleo/includes/inc_menuFinanceiro.jsp"%>
				<h:form id="EnviaEmailAssociadoForm">
					<t:saveState value="#{AssociadoBean.associados}" />
					<div id="miolo">
						<h1>Módulo Associados</h1>
						<h2>Envio de E-mails</h2>
						<br/><br/>
						<t:messages id="msgs" showDetail="true" showSummary="false" errorClass="textoMsgErro" infoClass="textoMsgInfo"/>
						<table class="tab_cadastro" cellpadding="2" cellspacing="1">
							<thead>
								<tr>
									<th>Dados da Mensagem</th>
									<th></th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<th width="190">Destinatário:</th>
									<td width="400"><t:inputText value="#{AssociadoBean.emails}" id="emails" size="50" readonly="true"></t:inputText></td>
								</tr>
								<tr>
									<th>Assunto:</th>
									<td><t:inputText value="#{AssociadoBean.email.assunto}" id="assunto" size="50" maxlength="50"></t:inputText></td>
								</tr>
								<tr>
									<th>Mensagem:</th>
									<td>
										<t:inputTextarea cols="50" rows="4" id="mensagem" value="#{AssociadoBean.email.corpo}"></t:inputTextarea>
									</td>
								</tr>
							</tbody>
						</table>
						
						<br />
						<t:commandLink onclick="setAcao(document.forms[0], 'Envia_Email')" action="#{AssociadoBean.enviarEmail}" title="#{properties['lb_enviar']}" id="enviar" styleClass="botao_enviar">
								<h:outputText value="#{properties['lb_enviar']}" />
						</t:commandLink>
					</div>
					<input type="hidden" name="acao" value=""/>
				</h:form>
			</div>
		</body>
	</html>
</f:view>