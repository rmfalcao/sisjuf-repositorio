<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<f:view>
	<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
	<f:loadBundle basename="sisjuf" var="properties" />
	<html>
		<head>
			<title>SISJUF - Sistema ASSERJUF</title>
			<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
			<link href="<c:url value="/nucleo/style/sisjuf.css"/>" type="text/css"	rel="stylesheet" />
			<link rel="Shortcut Icon" type="image/png" href="<c:url value="/nucleo/images/icone.png"/>">
		</head>
					<script>
				function setAcao(form, acao){
					form.acao.value = acao;
				}
			</script>
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
		<!-- MANU TEMPORÁRIO -->		
				<%@ include file="/nucleo/includes/inc_menuFinanceiro.jsp"%>
				<h:form>
					<div id="miolo">
						<h1>Módulo Associados</h1>
						<h2>Importar Planilha NUCRE</h2>
						<h:messages id="msgs" showDetail="true" showSummary="false" errorClass="textoMsgErro" infoClass="textoMsgInfo"/>
						<table class="tab_cadastro" cellpadding="2" cellspacing="1">
							<thead>
								<tr>
									<th width="190">Importar Arquivo</th>
									<th width="400"></th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<th>Procurar arquivo:</th>
									<td>
										<rich:fileUpload id="arquivo" maxFilesQuantity="1" styleClass="teste" immediateUpload="true" 
											fileUploadListener="#{AssociadoBean.upload}" uploadData="#{AssociadoBean.data}" />
									</td>
								</tr>
								<tr>
									<th><t:outputLabel value="#{properties['lb_dataImportacao']}"/>:</th>
									<td>
										<rich:calendar popup="true" datePattern="dd/MM/yyyy" showApplyButton="false"
											cellWidth="24px" cellHeight="22px" style="width:200px" disabled="false" locale="en/US"
											value="#{AssociadoBean.dataImportacao}" inputClass="inputCalendar" enableManualInput="true"
											oninputblur="checkDate(this)" oninputkeypress="return maskDate(this,event);"/>
									</td>
								</tr>
							</tbody>
						</table>
						<t:commandLink action="#{AssociadoBean.importarArquivo}" title="#{properties['lb_importar']}" id="salvar" 
							styleClass="botao_importar" onmousedown="return $('arquivo').component.beforeSubmit()" >
							<h:outputText value="#{properties['lb_salvar']}" />
						</t:commandLink>
						<t:inputHidden id="acao" value="Importar_Arquivo"/>
					</div>
				</h:form>
			</div>
		</body>
	</html>
</f:view>