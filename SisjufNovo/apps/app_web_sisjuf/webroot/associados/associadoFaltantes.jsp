<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>

<f:view>
	<f:loadBundle basename="sisjuf" var="properties" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
	<html>
		<head>
			<title>SISJUF - Sistema ASSERJUF</title>
			<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
			<link href="<c:url value="/nucleo/style/sisjuf.css"/>" type="text/css"	rel="stylesheet" />
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
						sair <a href="<c:url value="/Logout"/>"><img src="<c:url value="/nucleo/images/botao_x.gif"/>" /></a>
					</div>
					<div id="voltar">
						voltar <a href="#"><img src="<c:url value="/nucleo/images/setinhavoltar.gif"/>" /></a>
					</div>
				</div>
		
				<%@ include file="/nucleo/includes/inc_menuFinanceiro.jsp"%>
		
				<div id="miolo">
					<h1>Módulo Associados</h1>
					<h2>Relatório de faltantes</h2>
					<br>
					<p>Os lançamentos referentes aos registros abaixo não puderam ser realizados. Favor conferir o arquivo e/ou o cadastro de associados.</p>
					<p>A importação destes registros falhou porque alguns asociados enviados no arquivo estão ausentes no cadastro (base de dados do SISJUF) e/ou alguns associados existentes no cadastro estão ausentes no arquivo importado.</p>
					<p>Confira abaixo:</p>
					
					<h:form id="associadoaFaltantes">
						<t:saveState value="#{AssociadoBean.associadosFaltantes}" />
						<t:saveState value="#{AssociadoBean.associado}" />
						<t:dataTable id="data" var="associados" value="#{AssociadoBean.associadosFaltantes}" cellspacing="1" cellpadding="2"  styleClass="tab_lista_maior" preserveDataModel="false">		
								<t:column width="140">
									<f:facet name="header">
										<h:outputText value="Matrícula na Justiça" />
									</f:facet>
									<h:outputText value="#{associados.matriculaJustica}" />
									
								</t:column>
										
								<t:column width="230">
									<f:facet name="header">
										<h:outputText value="Ausência" />
									</f:facet>
										<h:outputText value="#{associados.falta}" />
								</t:column>
								
							</t:dataTable>
							<input type="hidden" name="acao" value=""/>
						</h:form>
				</div>
			</div>
		</body>
	</html>
</f:view>