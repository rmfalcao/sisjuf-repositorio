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
						<h2>Importar Planilha SEPAG</h2>
						<h:messages id="msgs" showDetail="true" showSummary="false" errorClass="textoMsgErro" infoClass="textoMsgInfo"/>
						<a4j:status id="statusLoading">
			                <f:facet name="start">
			                    <h:graphicImage  value="/nucleo/images/loading-green.gif"/>
			                </f:facet>
			            </a4j:status>
						<table class="tab_cadastro" cellpadding="2" cellspacing="1">
							<thead>
								<tr>
									<th width="190">Importar Arquivo</th>
									<th width="400"></th>
								</tr>
										
							<tbody>
									<tr>
										<th width="190">Rubrica(s)</th>
										<td width="400">
											<h:selectOneMenu id="mes" value="#{AssociadoBean.tipoValidacaoSepag}" tabindex="2">
												<f:selectItem itemLabel="522059 - MENSALIDADE"	itemValue="MENSALIDADE" />
												<f:selectItem itemLabel="522174 - VITALMED"	itemValue="VITALMED" />
												<f:selectItem itemLabel="525079 - PROMEDICA, 525111 - PROMEDICA ULTRA EXTRA e 525113 - PROMEDICA ESPECIAL" itemValue="PROMEDICA"/>
												<f:selectItem itemLabel="522175 - ODONTOSYSTEM e 522176 - ODONTOSYSTEM/OURO" itemValue="ODONTOSYSTEM"/>
												<f:selectItem itemLabel="525161 - SERVIDONTO" itemValue="SERVDONTO"/>
											</h:selectOneMenu>
										</td>
									</tr>
									<tr>
									<th>Procurar arquivo:</th>
									<td>
										<rich:fileUpload id="arquivo" maxFilesQuantity="3" styleClass="teste" immediateUpload="true" 
											fileUploadListener="#{AssociadoBean.uploadNucre}" uploadData="#{AssociadoBean.dataArquivoNucre}" />
									</td>
								</tr>

							</tbody>
						</table>
						<a4j:commandLink action="#{AssociadoBean.importarArquivo}" title="#{properties['lb_importar']}" id="importar" styleClass="botao_importar" reRender="statusLoading" onmousedown="return $('arquivo').component.beforeSubmit()">
    <!-- Você pode adicionar um texto ou uma imagem aqui -->
</a4j:commandLink>


						<t:inputHidden id="acao" value="Importar_Arquivo"/>
					</div>
				</h:form>
			</div>
		</body>
	</html>
</f:view>