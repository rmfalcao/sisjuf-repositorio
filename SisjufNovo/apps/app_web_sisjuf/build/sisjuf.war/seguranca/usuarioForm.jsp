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
				function validaPerfil(){
					if (document.forms[0].acao.value == "Adicionar_Perfil_Usuario"){
						if (document.forms[0].usuarioCodigo.value == ""){
							alert("Selecione primeiro um usuario.")
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
						voltar <a href="<c:url value="/seguranca/usuarioConsulta.jsf"/>"><img src="<c:url value="/nucleo/images/setinhavoltar.gif"/>" /></a>
					</div>
				</div>
		
				<%@ include file="/nucleo/includes/inc_menuFinanceiro.jsp" %>
				<h:form id="UsuarioForm" onsubmit="return validaPerfil();">
					<t:saveState value="#{UsuarioBean.usuario.perfis}" />
					<t:saveState value="#{UsuarioBean.perfil}" />
					<t:saveState value="#{UsuarioBean.usuario}" />
					<div id="miolo">
						<h1>Módulo Segurança</h1>
						<h2>Usuário - Cadastro</h2>
						
						<a class="botao_senha" href="<c:url value="/seguranca/alterarSenha.jsf"/>"><span>Alterar</span></a>
						<t:messages id="msgs" showDetail="true" showSummary="false" errorClass="textoMsgErro" infoClass="textoMsgInfo"/>
						<table class="tab_cadastro" cellpadding="2" cellspacing="1">
							<thead>
								<tr>
									<th>Dados do Usuário</th>
									<th colspan="2"></th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<th><h:outputLabel for="nome" value="#{properties['lb_nomeUsuario']}" />:</th>
									<td colspan="2">
										<t:inputText id="nome" value="#{UsuarioBean.usuario.nome}" size="35" maxlength="35" required="true"/>
									</td>
								</tr>
								<tr>
									<th><h:outputLabel for="login" value="#{properties['lb_loginUsuario']}" />:</th>
									<td colspan="2">
										<t:inputText id="login" value="#{UsuarioBean.usuario.login}" size="20" maxlength="20" required="true"/>
									</td>
								</tr>
								<tr>
								<!-- ATENÇÃO: O número desse rowspan deve ser dinâmico. Deve ser igual ao número de grupos relacionadas (tr) -->
									<th width="150" valign="top">Grupos:</th>
									<td colspan="2" width="440">
										<t:dataTable id="data" var="perfis" value="#{UsuarioBean.usuario.perfis}" cellspacing="1" cellpadding="2" 
											width="100%" preserveDataModel="false" rowId="#{perfis.codigo}" >
											
											<h:column>
												<f:facet name="header">
												</f:facet>
												<h:outputText value="#{perfis.nome}" />
											</h:column>
											
											<h:column>
												<f:facet name="header">
												</f:facet>
												<t:commandLink action="#{UsuarioBean.removerPerfil}" title="#{properties['lb_removerPerf']}" 
													immediate="true"  id="remover" styleClass="botao_excluir" onclick="setAcao(document.forms[0], 'Remover_Perfil_Usuario');">
													<t:updateActionListener property="#{UsuarioBean.perfil.codigo}" value="#{perfis.codigo}" />
													<t:updateActionListener property="#{UsuarioBean.usuario.codigo}" value="#{UsuarioBean.usuario.codigo}" />
												</t:commandLink>
											</h:column>
										</t:dataTable>
									</td>
								</tr>
								<!-- tr>
									<td>Associado</td>
									<td align="center"><a class="botao_excluir" title="Excluir" href="#"><span>excluir</span></a></td>
								</tr-->
							</tbody>
						</table>
						
						<br />
						
						<table class="tab_cadastro" cellpadding="2" cellspacing="1">
							<thead>
								<tr>
									<th>Grupos</th>
									<th></th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<th width="150">Grupos:</th>
									<td width="440">
										<t:selectOneMenu id="sPerfis" value="#{UsuarioBean.perfil.codigo}" forceId="true" style="float:left; margin-right:10px;">
											<f:selectItem itemLabel="#{properties['lb_selecione']}" itemValue=""/>
											<f:selectItems id="sel_perfis" value="#{UsuarioBean.perfis}"/>
										</t:selectOneMenu>
										
										<t:commandLink action="#{UsuarioBean.adicionarPerfil}" title="#{properties['lb_adicionar']}" 
											id="adPerfil" styleClass="botao_acima" onclick="setAcao(document.forms[0], 'Adicionar_Perfil_Usuario');">
											<h:outputText value="#{properties['lb_adicionar']}" />
										</t:commandLink>
									</td>
								</tr>
							</tbody>
						</table>
						
						<br />
						<t:commandLink action="#{UsuarioBean.salvar}" title="#{properties['lb_salvar']}" id="salvar" styleClass="botao_salvar"
							onclick="setAcao(document.forms[0], 'Salva_Usuario')">
							<h:outputText value="#{properties['lb_salvar']}" />
						</t:commandLink>
						<t:inputHidden id="usuarioCodigo" value="#{UsuarioBean.usuario.codigo}" forceId="true"/>
						<input type="hidden" name="acao" value=""/>
					</div>
				</h:form>
			</div>
		
		</body>
	</html>
</f:view>