<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>

<f:view>
	<f:loadBundle basename="seguranca" var="properties" />
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
			
				function validaRemocao(){
					if (document.forms[0].elements['acao'].value == 'Remove_Usuario'){
						if (confirm("Deseja realmente remover este registro?")){
							return true;
						} else return false;
					}return true;
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
						voltar <a href="<c:url value="/index.jsp"/>"><img src="<c:url value="/nucleo/images/setinhavoltar.gif"/>" /></a>
					</div>
				</div>
		
				<%@ include file="/nucleo/includes/inc_menuFinanceiro.jsp" %>
		
				<div id="miolo">
					<h1>Módulo Segurança</h1>
					<h2>Usuários</h2>
					<h:form id="UsuarioPesquisa" >
						<t:commandLink  onclick="setAcao(document.forms[0], 'Novo_Usuario');" action="#{UsuarioBean.reset}" id="novo" styleClass="botao_novo">
							<h:outputText value="#{properties['lb_novo']}" />
						</t:commandLink>
						<br /><br />
						<t:messages id="msgs" showDetail="true" showSummary="false" errorClass="textoMsgErro" infoClass="textoMsgInfo"/>
						<table class="tab_cadastro" cellpadding="2" cellspacing="1">
							<thead>
								<tr>
									<th>Filtro</th>
									<th colspan="3"></th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<th width="130">Nome do Usuário:</th>
									<td width="230">
										<t:inputText id="nome" value="#{UsuarioBean.usuario.nome}" size="30" maxlength="30" />
									</td>
									<th width="120">Login do Usuário:</th>
									<td width="110"><t:inputText id="login" value="#{UsuarioBean.usuario.login}" size="25" maxlength="25" /></td>
								</tr>
								<tr>
									<th>Grupo:</th>
									<td colspan="3">
										<t:selectOneMenu id="perfis" value="#{UsuarioBean.perfil.codigo}" forceId="true">
											<f:selectItem itemLabel="#{properties['lb_selecione']}" itemValue=""/>
											<f:selectItems id="sel_perfis" value="#{UsuarioBean.perfis}"/>
										</t:selectOneMenu>
									</td>
								</tr>
							</tbody>
						</table>
						<t:commandLink onclick="setAcao(document.forms[0], 'Filtrar_Usuario');" action="#{UsuarioBean.consultar}" title="#{properties['lb_filtrar']}" id="consultar" styleClass="botao_filtrar">
							<h:outputText value="#{properties['lb_filtrar']}" />
						</t:commandLink>
						<input type="hidden" name="acao" value=""/>
					</h:form>
					<br /><br />
					<h:form id="UsuarioResultado" onsubmit="return validaRemocao();">
						<t:saveState value="#{UsuarioBean.usuarios}" />
						<t:saveState value="#{UsuarioBean.usuario}" />
						<t:dataTable id="data" var="usuarios" value="#{UsuarioBean.usuarios}" cellspacing="1" cellpadding="2" width="100%"
							 styleClass="tab_lista" preserveDataModel="false" rowId="#{usuarios.codigo}">

							<t:column width="360">
								<f:facet name="header">
									<t:outputText value="#{properties['lb_nome']}" 	/>
								</f:facet>
								<t:commandLink action="#{UsuarioBean.carregar}" title="#{properties['lb_carregar']}" immediate="true" id="carregar"
									onclick="setAcao(document.forms[0], 'Carregar_Usuario');" >
									<t:updateActionListener property="#{UsuarioBean.usuario.codigo}" value="#{usuarios.codigo}" />
									<h:outputText value="#{usuarios.nome}" />
								</t:commandLink>
							</t:column>
							
							<t:column width="210">
								<f:facet name="header">
									<h:outputText value="#{properties['lb_login']}" />
								</f:facet>
								<h:outputText value="#{usuarios.login}" />
							</t:column>
							
							<t:column width="20">
								<f:facet name="header">
								</f:facet>
								<t:commandLink action="#{UsuarioBean.remover}" title="#{properties['lb_remover']}" immediate="true" id="remover" 
									styleClass="botao_excluir" onclick="setAcao(document.forms[0], 'Remove_Usuario');">
									<t:updateActionListener property="#{UsuarioBean.usuario.codigo}" value="#{usuarios.codigo}" />
								</t:commandLink>
							</t:column>
						</t:dataTable>
					</h:form>
				</div>
			</div>
		
		</body>
	</html>
</f:view>