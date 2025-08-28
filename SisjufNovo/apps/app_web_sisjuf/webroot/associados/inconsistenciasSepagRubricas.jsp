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
						sair <a href="<c:url value="/login.jsf"/>"><img src="<c:url value="/nucleo/images/botao_x.gif"/>" /></a>
					</div>
					<div id="voltar">
						voltar <a href="#"><img src="<c:url value="/nucleo/images/setinhavoltar.gif"/>" /></a>
					</div>
				</div>
		
				<%@ include file="/nucleo/includes/inc_menuFinanceiro.jsp"%>
		
				<div id="miolo">
					<h1>Módulo Associados</h1>
					<h2>Resultado da validação SEPAG - RUBRICA(S) "<h:outputText value="#{AssociadoBean.rubricas}" />"</h2>
					<br>
					
					<c:choose>
					    <c:when test="${empty AssociadoBean.inconsistenciasNucre}">
					        <p>TUDO CERTO! Nenhuma inconsistência encontrada.</p>
					    </c:when>
					    <c:otherwise>
					   		<p>Seguem as inconsistências entre o arquivo SEPAG e o cadastro de sócios do SISJUF:</p>
					        <t:dataTable id="data" var="inconsistencia" value="#{AssociadoBean.inconsistenciasNucre}" cellspacing="1" cellpadding="2"  styleClass="tab_lista_maior" preserveDataModel="false">		
								<t:column width="70">
									<f:facet name="header">
										<h:outputText value="CPF" />
									</f:facet>
									<h:outputText value="#{inconsistencia.cpf}" />
									
								</t:column>
										
								<t:column width="150">
									<f:facet name="header">
										<h:outputText value="Nome" />
									</f:facet>
										<h:outputText value="#{inconsistencia.nome}" />
								</t:column>
								
								<t:column width="150">
									<f:facet name="header">
										<h:outputText value="Valor consignado" />
									</f:facet>
										<h:outputText value="#{inconsistencia.valorConsignado}" />
								</t:column>
								
								
								<t:column width="150">
									<f:facet name="header">
										<h:outputText value="Valor no SISJUF" />
									</f:facet>
										<h:outputText value="#{inconsistencia.valorPrevisto}" />
								</t:column>
								
								<t:column width="150">
									<f:facet name="header">
										<h:outputText value="Tipo de inconsistência" />
									</f:facet>
										<h:outputText value="#{inconsistencia.tipoInconsistencia}" />
								</t:column>
								
							</t:dataTable>
					    </c:otherwise>
					</c:choose>
					
				
					
					
				</div>
			</div>
		</body>
	</html>
</f:view>