<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
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
			<h:form id="UsuarioLogarForm">
				<div id="geral_menor">
					<img src="<c:url value="/nucleo/images/login01.gif"/>" />
					<div id="login">
						<table id="tabela_login" align="center" cellpadding="2" cellspacing="1" style="border: 25px solid transparent">
							<tr>
								<td>Digite seu login:</td>
								<td>
									<t:inputText id="Login" value="#{UsuarioBean.usuario.login}" size="18" required="true"/>
								</td>
							</tr>
							<tr>
								<td>Digite sua senha:</td>
								<td>
									<t:inputSecret id="Senha" value="#{UsuarioBean.usuario.senha}" size="15" required="true"/>
									<t:commandLink action="#{UsuarioBean.logar}" id="autentica">
										<t:graphicImage value="/nucleo/images/botao_ok.gif"></t:graphicImage>
									</t:commandLink>
								</td>
							</tr>
						</table>
					</div>
					<img src="<c:url value="/nucleo/images/login03.gif"/>" />
				</div>
			</h:form>
		</body>
	</html>
</f:view>