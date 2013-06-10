<?xml version="1.0" encoding="UTF-8"?>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<f:view>
	<f:loadBundle basename="sisjuf" var="properties" />
	<html>
		<head>
			<title>SISJUF - Sistema ASSERJUF</title>
			<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
			<link href="<c:url value="/nucleo/style/sisjuf.css"/>" type="text/css" rel="stylesheet" />
			<link rel="Shortcut Icon" type="image/png" href="<c:url value="/nucleo/images/icone.png"/>">
			<script src="<c:url value="/nucleo/js/sisjuf.js" />" type="text/javascript"></script>
			<script language="JavaScript">
			</script>
		</head>
		<body>
			<div id="geral">
				<div id="topo">
					<div id="sair">
						sair <a href="<c:url value="/login.jsf"/>"><img src="<c:url value="/nucleo/images/botao_x.gif"/>" /></a>
					</div>
					<div id="voltar">
						voltar <a href="<c:url value="/associados/convenio/convenioPesquisa.jsf"/>"><img src="<c:url value="/nucleo/images/setinhavoltar.gif"/>" /></a>
					</div>
				</div>
				<%@ include file="/nucleo/includes/inc_menuFinanceiro.jsp"%>

				<div id="miolo">
					<h1>Módulo Convênios</h1>
					<h2>Cadastro de Convênio</h2>
					<a class="botao_novo" href="<c:url value="/associados/convenio/convenioForm.jsf"/>"><span>novo</span></a>
					<br /><br />
						<rich:tabPanel switchType="client">
       						<rich:tab label="Convênio">
       							<h:form id="convenioForm" acceptcharset="ISO-8859-1">
								<h:panelGroup id="convenioMsgs">
									<h:messages showDetail="true" showSummary="false" errorClass="textoMsgErro" infoClass="textoMsgInfo" />
								</h:panelGroup>
								<table class="tab_cadastro" cellpadding="2" cellspacing="1">
									<thead>
										<tr>
											<th>Dados de Básicos</th>
											<th colspan="3"></th>
										</tr>
									</thead>
									
									<tbody>
										<tr>
											<th width="140">Nome Fantasia:</th>
											<td width="230" colspan="3">
												<h:inputText size="60" maxlength="30" id="nomeFantasia" value="#{ConvenioBean.convenio.nomeFantasia}" tabindex="2" />
											</td>
										</tr>
										
										<tr>
											<th>Razão Social:</th>
											<td colspan="3"><t:inputText size="60" maxlength="60" id="razaoSocial" value="#{ConvenioBean.convenio.razaoSocial}" tabindex="3" /></td>
										</tr>
										<tr>
											<th>Categoria:</th>
											<td>
												<h:selectOneMenu id="categoria" value="#{ConvenioBean.convenio.categoria}" tabindex="1">
													<f:selectItem itemLabel="#{properties['lb_selecione']}"	itemValue="" />
													<f:selectItem itemLabel="#{properties['lb_geraFaturaVariavel']}" itemValue="V"/>
													<f:selectItem itemLabel="#{properties['lb_geraFaturaFixa']}" itemValue="F"/>
													<f:selectItem itemLabel="#{properties['lb_naoGeraFatura']}" itemValue="N"/>
												</h:selectOneMenu>
											</td>
											<th>Atividade:</th>
											<td>
												<h:selectOneMenu id="atividade" value="#{ConvenioBean.convenio.atividadeConvenio.codigo}" tabindex="4" >
													<f:selectItem itemLabel="#{properties['lb_selecione']}"/>
													<t:selectItems id="atividades" value="#{ConvenioBean.atividadesAtivas}" var="atv" itemLabel="#{atv.nome}" itemValue="#{atv.codigo}"/>
												</h:selectOneMenu>
											</td>
										</tr>
										
										<tr>
											<th>CNPJ:</th>
											<td><t:inputText size="15"  maxlength="14" id="cnpj" value="#{ConvenioBean.convenio.cnpj}" tabindex="5" onkeypress="return justNumber(this,event)"/></td>
										</tr>
									</tbody>
									
									<thead>
										<tr>
											<th>Endereço</th>
											<th colspan="3"></th>
										</tr>
									</thead>
									
									<tbody>
										<tr>
											<th>Logradouro:</th>
											<td colspan="3">
												<t:inputText size="40" maxlength="100" id="logradouro" value="#{ConvenioBean.convenio.logradouro}" tabindex="6"/>
											</td>
										</tr>
										<tr>
											<th>Complemento:</th>
											<td colspan="3">
												<t:inputText size="40"  maxlength="100" id="complemento" value="#{ConvenioBean.convenio.complemento}" tabindex="7"/>
											</td>
										</tr>
										<tr>
											<th>Bairro:</th>
											<td>
												<t:inputText size="30"  maxlength="20" id="bairro" value="#{ConvenioBean.convenio.bairro}" tabindex="8"/>
											</td>
											<th>CEP:</th>
											<td>
												<t:inputText size="12" id="cep" maxlength="10" value="#{ConvenioBean.cep}" tabindex="9" onkeypress="return MaskCEP(this,event)"/>
											</td>
										</tr>
										<tr>
											<th>Estado:</th>
											<td colspan="3">
												<h:selectOneMenu id="estado" value="#{ConvenioBean.convenio.estado.codigo}" tabindex="10">
													<f:selectItem itemLabel="#{properties['lb_selecione']}" itemValue=""/>
													<t:selectItems id="sel_estados" value="#{ConvenioBean.estados}" var="estado" itemLabel="#{estado.nome}" itemValue="#{estado.codigo}"/>
												</h:selectOneMenu>
											</td>
										</tr>
										<tr>
											<th>Cidade:</th>
											<td colspan="3">
												<t:inputText size="30" maxlength="60" id="cidade" value="#{ConvenioBean.convenio.cidade}" tabindex="11"/>
											</td>
										</tr>
									</tbody>
									
									<thead>
										<tr>
											<th>Contatos</th> 
											<th colspan="3"></th>
										</tr>
									</thead>
									
									<tbody>
										<tr>
											<th>Nome Contato:</th>
											<td>
												<t:inputText size="40" maxlength="60"  id="contato" value="#{ConvenioBean.convenio.nomeContato}" tabindex="12"/>
											</td>
											<th>Telefone:</th>
											<td>
												<t:inputText size="15" id="telefone" value="#{ConvenioBean.telefone}" tabindex="13"  onkeypress="return MaskTelefone(this,event)"/>
											</td>
										</tr>
										<tr>
											<th>E-mail:</th>
											<td>
												<t:inputText size="40"  maxlength="100" id="email" value="#{ConvenioBean.convenio.email}" tabindex="14"/>
											</td>
											<th>Tel. Adicional:</th>
											<td>
												<t:inputText size="15" id="telefoneAdicional" value="#{ConvenioBean.telefoneAdicional}" tabindex="15" onkeypress="return MaskTelefone(this,event)"/>
											</td>
										</tr>
										<tr>
										<th>Fax:</th>
											<td colspan="3">
												<t:inputText size="15" id="fax" value="#{ConvenioBean.fax}" tabindex="16" onkeypress="return MaskTelefone(this,event)"/>
											</td>
										</tr>
									</tbody>
									
									<thead>
										<tr>
											<th>Mais Informações</th>
											<th colspan="3"></th>
										</tr>
									</thead>
									
									<tbody>
										<tr>
											<th>Benef&iacute;cios:</th>
											<td colspan="3">
												<t:inputTextarea cols="80" rows="3" id="beneficio" value="#{ConvenioBean.convenio.beneficio}"  tabindex="17"></t:inputTextarea>
											</td>
										</tr>
										<tr>
											<th>Descri&ccedil;&atilde;o:</th>
											<td colspan="3">
												<t:inputTextarea cols="80" rows="3" id="descricao" value="#{ConvenioBean.convenio.descricao}" tabindex="18"></t:inputTextarea>
											</td>
										</tr> 
										<tr>
											<th>Dia do Fechamento:</th>
											<td>
												<t:inputText size="12"  maxlength="2"  onkeyup="soNumero(this)"  id="diaFechamento" value="#{ConvenioBean.convenio.diaFechamento}" tabindex="20"/>
											</td>
										</tr>
										<tr>
											<th>Dia do Vencimento:</th>
											<td>
												<t:inputText size="12"  maxlength="2"  onkeyup="soNumero(this)"  id="diaVencimanto" value="#{ConvenioBean.convenio.diaVencimento}" tabindex="21"/>
											</td>
											<th>Mês de Vencimento:</th>
											<td>
												<t:inputText size="12" id="mesVencimento" value="#{ConvenioBean.convenio.mesVencimento}" tabindex="22" maxlength="2"/>
											</td>
										</tr>
										<tr>
											<th>Quem Pode Utilizar:</th>
											<td>
												<t:selectBooleanCheckbox  value="#{ConvenioBean.titular}"/>Titulares<br/>
												<t:selectBooleanCheckbox  value="#{ConvenioBean.conjugue}"/>Cônjuge<br/>
												<t:selectBooleanCheckbox  value="#{ConvenioBean.filhos}"/>Filhos<br/>
												<t:selectBooleanCheckbox  value="#{ConvenioBean.dependentes}"/>Dependentes<br/>
												<t:selectBooleanCheckbox  value="#{ConvenioBean.outrosBeneficaiveis}"/>Outros Beneficiaveis<br/>
											</td>
										</tr>
										<tr>
											<th>Desativado:</th>
											<td>
												<t:selectBooleanCheckbox  value="#{ConvenioBean.desativado}"/>
											</td>
										</tr>
									</tbody>
									
								</table>
								<a4j:commandButton action="#{ConvenioBean.salvar}" title="#{properties['lb_salvar']}" 
									styleClass="botao_salvar" value="salvar" reRender="convenioMsgs, codigo, CodConvenio, CConvenio"/>
								<h:inputHidden id="codigo" value="#{ConvenioBean.convenio.codigo}"/>
								</h:form>
							</rich:tab>
							
							<rich:tab label="Planos">
								<jsp:include page="plano/planoConvenioPesquisa.jsp" />
								<%@ include file="plano/planoConvenioForm.jsp"%>
							</rich:tab>
							<rich:tab label="Últimas Faturas">
								<jsp:include page="ultimasFaturasConvenioPesquisa.jsp"></jsp:include>
							</rich:tab>
							<rich:tab label="Beneficiários">
								<jsp:include page="beneficiariosPesquisa.jsp"></jsp:include>
							</rich:tab>
						</rich:tabPanel>
					
					<br /><br /><br /><br /><br /><br /><hr>
				</div>
			</div>
		</body>
	</html>
</f:view>\