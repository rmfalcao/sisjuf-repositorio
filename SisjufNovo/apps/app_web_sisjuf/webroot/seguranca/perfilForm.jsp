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
				function validaFuncionalidade(){
					if (document.forms[0].acao.value == "Adicionar_Funcionalidade"){
						if (document.forms[0].perfilCodigo.value == ""){
							alert("Você deve primeiro salvar o grupo!")
							return false;
						}else{
							return true;
						}
					}
					return true;
				}
				
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
						voltar <a href="<c:url value="/seguranca/perfilConsulta.jsf"/>"><img src="<c:url value="/nucleo/images/setinhavoltar.gif"/>" /></a>
					</div>
				</div>
		
				<%@ include file="/nucleo/includes/inc_menuFinanceiro.jsp" %>
				<h:form id="PerfilForm" onsubmit="return validaFuncionalidade();">
					<t:saveState value="#{PerfilBean.perfil.funcionalidades}" />
					<t:saveState value="#{PerfilBean.funcionalidade}" />
					<t:saveState value="#{PerfilBean.perfil}" />
					<div id="miolo">
						<h1>Módulo Segurança</h1>
						<h2>Grupo - Cadastro</h2>
						<t:messages id="msgs" showDetail="true" showSummary="false" errorClass="textoMsgErro" infoClass="textoMsgInfo"/>
						<table class="tab_cadastro" cellpadding="2" cellspacing="1">
							<thead>
								<tr>
									<th>Dados do Grupo</th>
									<th colspan="2"></th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<th><h:outputLabel for="nomeGrupo" value="#{properties['lb_nomeGrupo']}" />:</th>
									<td colspan="2">
										<t:inputText id="nomeGrupo" value="#{PerfilBean.perfil.nome}" size="30" maxlength="30" required="true"/>
									</td>
								</tr>
								<tr>
									<!-- ATENÇÃO: O número desse rowspan deve ser dinâmico. Deve ser igual ao número de funções relacionadas (tr) -->
									<th width="150" valign="top" >Funções:</th>
									<td colspan="2" width="440">
										<t:dataTable id="data" var="funcionalidades" value="#{PerfilBean.perfil.funcionalidades}"
											cellspacing="1" cellpadding="2" width="100%" preserveDataModel="false" rowId="#{funcionalidades.codigo}" >
											
											<h:column>
												<f:facet name="header">
												</f:facet>
												<h:outputText value="#{funcionalidades.nome}" />
											</h:column>
											
											<h:column>
												<f:facet name="header">
												</f:facet>
												<t:commandLink action="#{PerfilBean.removerFuncionalidade}" title="#{properties['lb_removerFunc']}" 
													immediate="true"  id="remover" styleClass="botao_excluir" 
													onclick="setAcao(document.forms[0], 'Remover_Funcionalidade');">
													<t:updateActionListener property="#{PerfilBean.funcionalidade.codigo}" value="#{funcionalidades.codigo}" />
													<t:updateActionListener property="#{PerfilBean.perfil.codigo}" value="#{PerfilBean.perfil.codigo}" />
												</t:commandLink>
											</h:column>
										</t:dataTable>
									</td>
								</tr>
							</tbody>
						</table>
						
						<table class="tab_cadastro" cellpadding="2" cellspacing="1">
							<thead>
								<tr>
									<th>Funções do Grupo</th>
									<th></th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<th width="150">Função:</th>
									<td width="440">
										<t:selectOneMenu id="sFuncionalidades" value="#{PerfilBean.funcionalidade.codigo}" forceId="true" style="float:left; margin-right:10px;">
											<f:selectItem itemLabel="#{properties['lb_selecione']}" itemValue=""/>
											<f:selectItems id="sel_funcionalidades" value="#{PerfilBean.nfuncionalidades}"/>
										</t:selectOneMenu>
										
										<t:commandLink action="#{PerfilBean.adicionarFuncionalidade}" title="#{properties['lb_adicionar']}" 
											id="adFuncionalidade" styleClass="botao_acima" 
											onclick="setAcao(document.forms[0], 'Adicionar_Funcionalidade');">
											<h:outputText value="#{properties['lb_adicionar']}" />
										</t:commandLink>
									</td>
								</tr>
							</tbody>
						</table>
						
						<br />
						<t:commandLink action="#{PerfilBean.salvar}" title="#{properties['lb_salvar']}" id="salvar" styleClass="botao_salvar"
							onclick="setAcao(document.forms[0], 'Salvar_Perfil');">
							<h:outputText value="#{properties['lb_salvar']}" />
						</t:commandLink>
						<t:inputHidden id="perfilCodigo" value="#{PerfilBean.perfil.codigo}" forceId="true"/>
					</div>
					<input type="hidden" name="acao" value=""/>
				</h:form>
			</div>
		
		</body>
	</html>
</f:view>