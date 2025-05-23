<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<f:view>
	<f:loadBundle basename="sisjuf" var="properties" />
	<html>
		<head>
			<title>SISJUF - Sistema ASSERJUF</title>
			<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
			<link href="<c:url value="/nucleo/style/sisjuf.css"/>" type="text/css" rel="stylesheet" />
			<link rel="Shortcut Icon" type="image/png" href="<c:url value="/nucleo/images/icone.png"/>">
			<script type="text/javascript" src="<c:url value="/nucleo/js/associado.js"/>"></script>
			<script src="<c:url value="/nucleo/js/sisjuf.js" />" type="text/javascript"></script>
			<script type="text/javascript" src="<c:url value="/dwr/engine.js"/>"></script>
			<script type="text/javascript" src="<c:url value="/dwr/util.js"/>"></script>
			<script type="text/javascript" src="<c:url value="/dwr/interface/associadoBean.js"/>"></script>
			
			<script language="JavaScript">
				function mostraDetalheDependente(nomeDependente, parentesco, rg, dataNascimento, sexo) {
				
					if (nomeDependente <> '') {
						alert('Nome: ' + nomeDependente + '\nParentesco: ' + parentesco + '\nRG: ' + rg + '\nData de nascimento: ' + dataNascimento + '\nSexo: ' + sexo);
					}
					
				}
			</script>
			
		</head>
		<body onload="carregarPagina();">
		
			<div id="geral">
				<div id="topo">
					<div id="sair">
						sair <a href="index.htm"><img src="<c:url value="/nucleo/images/botao_x.gif"/>" /></a>
					</div>
					<div id="voltar">
						voltar <a href="#"><img src="<c:url value="/nucleo/images/setinhavoltar.gif"/>" /></a>
					</div>
				</div>
				<%@ include file="/nucleo/includes/inc_menuFinanceiro.jsp"%>
				<h:form id="associadoForm">
				
				<!-- input type="hidden" name="preCadastro" value="S" -->
				<t:inputHidden id="preCadastro" value="#{AssociadoBean.preCadastro}" forceId="true"/>
				<script language="JavaScript">
					document.forms[0].preCadastro.value = 'S';
				</script>
				<div id="miolo">
					<h1>M�dulo Associados</h1>
					<h2>Cadastro de Associado</h2>
					<a class="botao_novo" href="<c:url value="/associados/associadoForm.jsf"/>"><span>novo</span></a>
					<br /><br />
					<t:messages id="msgs" showDetail="true" showSummary="false" errorClass="textoMsgErro" infoClass="textoMsgInfo"/>
					<table class="tab_cadastro" cellpadding="2" cellspacing="1">
						<thead>
							<tr>
								<th>
									Dados de Matr�cula
								</th>
								<th colspan="3"></th>
							</tr>
						</thead>
						
						<tbody>
							<tr>
								<th width="140">Data de associa��o:</th>
								<td width="230">
									<t:inputCalendar value="#{AssociadoBean.associado.dataAssociacao}" renderAsPopup="true"
											popupTodayString="#{properties['popup_today_string']}" popupDateFormat="dd/MM/yyyy" id="dataAssociacao" forceId="true"
											popupWeekString="#{properties['popup_week_string']}" renderPopupButtonAsImage="true"
											readonly="false" onblur="checkDate(this)" onkeypress="return maskDate(this,event)"
											tabindex="1"
											 />
								</td>
								<th width="100">N�. Matr�cula:</th>
								<td width="120"><t:inputText size="8" id="codigo" value="#{AssociadoBean.associado.codigo}" readonly="true"/></td>
							</tr>
							<tr>
								<th>Nome completo:</th>
								<td colspan="3"><t:inputText size="40" id="nome" value="#{AssociadoBean.associado.nome}" tabindex="2"></t:inputText></td>
							</tr>
						</tbody>
						
						<thead>
							<tr>
								<th>Endere�o</th>
								<th colspan="3"></th>
							</tr>
						</thead>
						
						<tbody>
							<tr>
								<th>Logradouro:</th>
								<td><t:inputText tabindex="3" size="40" id="enderecoLogradouro" value="#{AssociadoBean.associado.endereco.logradouro}"></t:inputText></td>
								<th>N�mero:</th>
								<td><t:inputText tabindex="4" size="3" id="enderecoNumero" value="#{AssociadoBean.associado.endereco.numero}" maxlength="6"></t:inputText></td>
							</tr>
							<tr>
								<th>Complemento:</th>
								<td colspan="3"><t:inputText size="40" tabindex="5" id="enderecoComplemente" value="#{AssociadoBean.associado.endereco.complemento}" /></td>
							</tr>
							<tr>
								<th>Bairro:</th>
								<td><t:inputText tabindex="6" size="30" id="enderecoBairro" value="#{AssociadoBean.associado.endereco.bairro}" /></td>
								<th>CEP:</th>
								<td><t:inputText tabindex="7" size="12" id="enderecoCEP" value="#{AssociadoBean.cep}" onkeypress="return MaskCEP(this,event)"/></td>
							</tr>
							<tr>
								<th>Estado:</th>
								<td colspan="3">
									<h:selectOneMenu tabindex="8" id="enderecoEstado" value="#{AssociadoBean.associado.endereco.municipio.estado.codigo}" >
										<f:selectItem itemLabel="#{properties['lb_selecione']}" itemValue=""/>
										<f:selectItems id="sel_endEstados" value="#{AssociadoBean.estados}"/>
									</h:selectOneMenu>
								</td>
							</tr>
							<tr>
								<th>Cidade:</th>
								<td colspan="3"><t:inputText tabindex="9" size="30" id="cidade" value="#{AssociadoBean.associado.endereco.municipio.nome}" /></td>
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
								<th>E-mail:</th>
								<td><t:inputText tabindex="10" size="40" id="email" value="#{AssociadoBean.associado.email}" /></td>
								<th>Tel. Celular:</th>
								<td><t:inputText tabindex="11" size="15" id="telefoneCelular" value="#{AssociadoBean.telefoneCelular}" onkeypress="return MaskTelefone(this,event)"/></td>
							</tr>
							<tr>
								<th>Tel. Residencial:</th>
								<td><t:inputText tabindex="12" size="15" id="telefoneResidencial" value="#{AssociadoBean.telefoneResidencial}" onkeypress="return MaskTelefone(this,event)"/></td>
								<th>Tel. Comercial:</th>
								<td><t:inputText tabindex="13" size="15" id="telefoneComercial" value="#{AssociadoBean.telefoneComercial}" onkeypress="return MaskTelefone(this,event)"/></td>
							</tr>
						</tbody>
						
						<thead>
							<tr>
								<th>Mais Informa��es</th>
								<th colspan="3"></th>
							</tr>
						</thead>
						
						<tbody>
							<tr>
								<th>R.G.:</th>
								<td><t:inputText tabindex="14" size="20" id="rg" value="#{AssociadoBean.associado.rg}" onkeypress="return justNumber(this,event)" maxlength="10"/></td>
								<th>C.P.F.:</th>
								<td><t:inputText tabindex="15" size="15" id="cpf" value="#{AssociadoBean.cpf}" onkeypress="return MaskCPF(this,event)"/></td>
							</tr>
							<tr>
								<th>Data de Nasc.:</th>
								<td>
									<t:inputCalendar tabindex="16" value="#{AssociadoBean.associado.dataNascimento}" renderAsPopup="true"
											popupTodayString="#{properties['popup_today_string']}" popupDateFormat="dd/MM/yyyy" id="dataNascimento" forceId="true"
											popupWeekString="#{properties['popup_week_string']}" renderPopupButtonAsImage="true" readonly="false" onblur="checkDate(this)" onkeypress="return maskDate(this,event)" />
								</td>
								<th>Sexo:</th>
								<td>
									<h:selectOneMenu tabindex="17" id="sexo" value="#{AssociadoBean.associado.sexo}" >
										<f:selectItem itemLabel="#{properties['lb_selecione']}" itemValue=""/>
										<f:selectItem itemLabel="#{properties['lb_masculino']}" itemValue="M"/>
										<f:selectItem itemLabel="#{properties['lb_feminino']}" itemValue="F"/>
									</h:selectOneMenu>
								</td>
							</tr>
							<tr>
								<th>Estado (Naturalidade):</th>
								<td colspan="3">
									<h:selectOneMenu tabindex="18" id="naturalidadeEstado" value="#{AssociadoBean.associado.naturalidade.estado.codigo}" >
										<f:selectItem itemLabel="#{properties['lb_selecione']}" itemValue=""/>
										<f:selectItems id="sel_natEstado" value="#{AssociadoBean.estados}"/>
									</h:selectOneMenu>
								</td>
							</tr>
							<tr>
								<th>Naturalidade:</th>
								<td colspan="3"><t:inputText tabindex="19" size="30" id="naturalidade" value="#{AssociadoBean.associado.naturalidade.nome}" /></td>
							</tr>
							<tr>
								<th>Estado Civil:</th>
								<td colspan="3">
									<h:selectOneMenu tabindex="20" id="estadoCivil" value="#{AssociadoBean.associado.estadoCivil}" >
										<f:selectItem itemLabel="#{properties['lb_selecione']}" itemValue=""/>
										<f:selectItem itemLabel="#{properties['lb_casado']}" itemValue="C"/>
										<f:selectItem itemLabel="#{properties['lb_desquitado']}" itemValue="D"/>
										<f:selectItem itemLabel="#{properties['lb_divorciado']}" itemValue="I"/>
										<f:selectItem itemLabel="#{properties['lb_solteiro']}" itemValue="S"/>
										<f:selectItem itemLabel="#{properties['lb_viuvo']}" itemValue="V"/>
									</h:selectOneMenu>
								</td>
							</tr>
							<tr>
								<th>Profiss�o:</th>
								<td colspan="3"><t:inputText tabindex="21" size="40" id="profissao" value="#{AssociadoBean.associado.profissao.nome}" /></td>
							</tr>
							<tr>
								<th>Grupo Sang��neo:</th>
								<td>
									<h:selectOneMenu tabindex="22" id="grupoSanguineo" value="#{AssociadoBean.associado.grupoSanguineo}" >
										<f:selectItem itemLabel="#{properties['lb_selecione']}" itemValue=""/>
										<f:selectItem itemLabel="#{properties['lb_A']}" itemValue="A"/>
										<f:selectItem itemLabel="#{properties['lb_B']}" itemValue="B"/>
										<f:selectItem itemLabel="#{properties['lb_AB']}" itemValue="AB"/>
										<f:selectItem itemLabel="#{properties['lb_O']}" itemValue="O"/>
									</h:selectOneMenu>
								</td>
								<th>Fator Rh:</th>
								<td>
									<h:selectOneMenu tabindex="23" id="fatorRh" value="#{AssociadoBean.associado.fatorRh}" >
										<f:selectItem itemLabel="#{properties['lb_selecione']}" itemValue=""/>
										<f:selectItem itemLabel="+" itemValue="+"/>
										<f:selectItem itemLabel="-" itemValue="-"/>
									</h:selectOneMenu>
								</td>
							</tr>
							<tr>
								<th>Nome do Pai:</th>
								<td colspan="3"><t:inputText tabindex="24" size="40" id="nomePai" value="#{AssociadoBean.associado.nomePai}" /></td>
							</tr>
							<tr>
								<th>Nome da M�e:</th>
								<td colspan="3"><t:inputText tabindex="25" size="40" id="nomeMae" value="#{AssociadoBean.associado.nomeMae}" /></td>
							</tr>
							<tr>
								<th>Nome do C�njugue:</th>
								<td colspan="3"><t:inputText tabindex="26" size="40" id="nomeConjuge" value="#{AssociadoBean.associado.nomeConjuge}" /></td>
							</tr>
							<tr>
								<th>Cal�ado n�.:</th>
								<td colspan="3"><t:inputText tabindex="27" size="5" id="numeroInicioCalcado" value="#{AssociadoBean.associado.numeroCalcado}" onkeypress="return justNumber(this,event)"/></td>
							</tr>
						</tbody>
						
						<thead>
							<tr>
								<th>Situa��o na Justi�a</th>
								<th colspan="3"></th>
							</tr>
						</thead>
						
						<tbody>
							<tr>
								<th>M�tricula da Justica:</th>
								<td><t:inputText tabindex="28" size="10" id="matriculaJustica" value="#{AssociadoBean.associado.matriculaJustica}" maxlength="10" onkeypress="return justNumber(this,event)"/></td>
								<td colspan="2">&nbsp;
								</td>
							</tr>
							<tr>
								<th>�rg�o:</th>
								<td><t:inputText tabindex="29" size="40" id="orgaoNome" value="#{AssociadoBean.associado.setor.orgao.nome}" /></td>
								<th>Status:</th>
								<td>
									<h:selectOneMenu tabindex="30" id="statusJustica" value="#{AssociadoBean.associado.statusJustica}" >
										<f:selectItem itemLabel="#{properties['lb_selecione']}" itemValue=""/>
										<f:selectItem itemLabel="#{properties['lb_ativo']}" itemValue="A"/>
										<f:selectItem itemLabel="#{properties['lb_inativo']}" itemValue="I"/>
									</h:selectOneMenu>
								</td>
							</tr>
							<tr>
								<th>Setor:</th>
								<td colspan="3"><t:inputText tabindex="31" size="40" id="setor" value="#{AssociadoBean.associado.setor.nome}" /></td>
							</tr>
							<tr>
								<th>Endere�o do Setor:</th>
								<td colspan="3"><t:inputText tabindex="32" size="40" id="setorEndereco" value="#{AssociadoBean.associado.setor.endereco.logradouro}" /></td>
							</tr>
							<tr>
								<th>Estado do Setor:</th>
								<td colspan="3">
									<h:selectOneMenu tabindex="33" id="setorEstado" value="#{AssociadoBean.associado.setor.endereco.municipio.estado.codigo}" >
										<f:selectItem itemLabel="#{properties['lb_selecione']}" itemValue=""/>
										<f:selectItems id="sel_setorEstado" value="#{AssociadoBean.estados}"/>
									</h:selectOneMenu>
								</td>
							</tr>
							<tr>
								<th>Munic�pio do Setor:</th>
								<td colspan="3"><t:inputText tabindex="34" size="30" id="setorMunicipio" value="#{AssociadoBean.associado.setor.endereco.municipio.nome}" /></td>
							</tr>
							<tr>
								<th>Tel. do Setor:</th>
								<td><t:inputText tabindex="35" size="15" id="setorTelefone" value="#{AssociadoBean.telefoneSetor}" onkeypress="return MaskTelefone(this,event)"/></td>
								<th>Ramal do Setor:</th>
								<td><t:inputText tabindex="36" size="5" id="setorRamal" value="#{AssociadoBean.associado.setor.ramal}" onkeypress="return justNumber(this,event)"/></td>
							</tr>
							<tr>
								<th>Categoria:</th>
								<td>
									<t:selectOneMenu tabindex="37" id="statusCategoria" value="#{AssociadoBean.associado.statusCategoria}" onchange="setStatusCategoria()" forceId="true">
										<f:selectItem itemLabel="#{properties['lb_selecione']}" itemValue=""/>
										<f:selectItem itemLabel="#{properties['lb_socioContribuinte']}" itemValue="C"/>
										<f:selectItem itemLabel="#{properties['lb_socioUsuario']}" itemValue="U"/>
										<f:selectItem itemLabel="#{properties['lb_socioConveniado']}" itemValue="O"/>
									</t:selectOneMenu>
								</td>
								<th>Recebe Jornal:</th>
								<td><t:selectBooleanCheckbox tabindex="38" id="statusRecebeJornal" value="#{AssociadoBean.statusRecebeJornal}"/></td>
							</tr>
							<tr>
								<th>CPF do Contribuinte:</th>
								<td colspan="3">
									<t:inputText tabindex="39" size="40" id="contribuinteCpf" value="#{AssociadoBean.associado.contribuinte.cpf}" forceId="true" onkeypress="return MaskCPF(this,event)" disabled="true"/>
									<a href="javascript:buscarAssociadoPorCpf();" class="botao_adicionar"></a>
								</td>
							</tr>
							<tr>
								<th>Nome do Contribuinte:</th>
								<td colspan="3">
									<t:inputText tabindex="40" size="40" id="contribuinteNome" value="#{AssociadoBean.associado.contribuinte.nome}" readonly="true" forceId="true"/>
									<t:inputHidden id="contribuinteCodigo" value="#{AssociadoBean.associado.contribuinte.codigo}" forceId="true"/>
								</td>
							</tr>
							<tr>
								<th>Banco:</th>
								<td colspan="3">
									<h:selectOneMenu tabindex="41" id="contaBanco" value="#{AssociadoBean.associado.conta.bancoVO.codigo}" >
										<f:selectItem itemLabel="#{properties['lb_selecione']}" itemValue=""/>
										<f:selectItems id="sel_banco" value="#{AssociadoBean.bancos}"/>
									</h:selectOneMenu>
								</td>
							</tr>
							<tr>
								<th>N� Ag�ncia:</th>
								<td>
									<t:inputText tabindex="42" size="5" id="numAgencia" value="#{AssociadoBean.associado.conta.numAgencia}" maxlength="5" onkeypress="return justNumber(this,event)"/> - 
									<t:inputText tabindex="43" size="1" id="digAgencia" value="#{AssociadoBean.associado.conta.digAgencia}" maxlength="1" onkeypress="return justNumber(this,event)"/>
								</td>
								<th>N� Conta:</th>
								<td>
									<t:inputText tabindex="44" size="5" id="numConta" value="#{AssociadoBean.associado.conta.numConta}" maxlength="5" onkeypress="return justNumber(this,event)"/> - 
									<t:inputText tabindex="45" size="1" id="digConta" value="#{AssociadoBean.associado.conta.digConta}" maxlength="1" onkeypress="return justNumber(this,event)"/>
								</td>
							</tr>
						</tbody>
					</table>
					
					<br /><br /><br /><br />
					<h2>Cadastro de Dependentes</h2>
					
					<table class="tab_cadastro" cellpadding="2" cellspacing="1">
						<thead>
							<tr>
								<th>Dados Dependente</th>
								<th colspan="3"></th>
							</tr>
						</thead>
						
						<tbody>
							<tr>
								<th width="140">Nome completo:</th>
								<td width="230"><input tabindex="46" name="nomeDependente" type="text" size="40" /></td>
								<th width="100">R.G:</th>
								<td width="120"><input tabindex="47" name="rgDependente" type="text" size="20" /></td>
							</tr>
							<tr>
								<th>Data de Nasc.:</th>
								<td>
									<t:inputCalendar  tabindex="48" renderAsPopup="true" popupTodayString="#{properties['popup_today_string']}" popupDateFormat="dd/MM/yyyy" id="dataNascimentoDependente" forceId="true"
										popupWeekString="#{properties['popup_week_string']}" renderPopupButtonAsImage="true" readonly="false" onblur="checkDate(this)" onkeypress="return maskDate(this,event)" />
								</td>
								<th>Sexo:</th>
								<td>
									<select tabindex="49" name="sexoDependente">
										<option></option>
										<option value="M">Masculino</option>
										<option value="F">Feminino</option>
									</select>
								</td>
							</tr>
							<tr>
								<th>Parentesco:</th>
								<td colspan="3">
									<t:selectOneMenu tabindex="50" id="parentesco" forceId="true">
										<f:selectItem itemLabel="#{properties['lb_selecione']}" itemValue=""/>
										<f:selectItems id="sel_parentesco" value="#{AssociadoBean.parentescos}"/>
									</t:selectOneMenu>
								</td>
							</tr>
						</tbody>
						
						<tfoot>
							<tr>
								<th>Adicionar Dependente</th>
								<td colspan="3">
									<a class="botao_adicionar" title="Adicionar" href="javascript:incluirDependente();" onclick=""><span>adicionar</span></a>
								</td>
							</tr>
						</tfoot>
					</table>
					
					<br /><br />
					<h2>Lista de Dependentes</h2>
					
					<table class="tab_lista" cellpadding="2" cellspacing="1">
						<thead>
							<tr>
								<th width="370">Nome do Dependente</th>
								<th width="200">Parentesco</th>
								<th width="20"></th>
							</tr>
						</thead>
						<tbody id="tb_parentesco">
							<c:if test="${not empty requestScope.AssociadoBean.associado.dependentes}">
								<c:forEach var="parente" items="${requestScope.AssociadoBean.associado.dependentes}">
									<tr>
										<td><c:out value="${parente.nome }"/> <a href="javascript:void(0);" onclick="javascript:alert('Nome: <c:out value="${parente.nome }"/>\nParentesco: <c:out value="${parente.parentesco.nome}"/>\nRG: <c:out value="${parente.rg }"/>\nData de nascimento: <fmt:formatDate value="${parente.dataNascimento }" pattern="dd/MM/yyyy"/>\nSexo: <c:out value="${parente.sexo}"/>');"> <img src="<c:url value="/nucleo/images/lupa.png"/>" border="0"></a></td>
										<td><c:out value="${parente.parentesco.nome}"/></td>
										<td>
											<a class='botao_excluir' href="javascript:removerDependente('<c:out value="${parente.rg}"/>');"><span>excluir</span></a>
											<input type='hidden' name='dp_nome' value='<c:out value="${parente.nome }"/>'>
											<input type='hidden' name='dp_rg' value='<c:out value="${parente.rg }"/>'/>
											<input type='hidden' name='dp_dataNascimento' value='<fmt:formatDate value="${parente.dataNascimento }" pattern="dd/MM/yyyy"/>'/>
											<input type='hidden' name='dp_sexo' value='<c:out value="${parente.sexo}"/>'/>
											<input type='hidden' name='dp_parentesco' value='<c:out value="${parente.parentesco.nome}"/>'/>
										</td>
									</tr>
								</c:forEach>
							</c:if>
						</tbody>
					</table>
					
					<br /><br /><br /><br />
					<h2>Cadastro de Filhos</h2>
					
					<table class="tab_cadastro" cellpadding="2" cellspacing="1">
						<thead>
							<tr>
								<th>Dados do Filho (a)</th>
								<th colspan="3"></th>
							</tr>
						</thead>
						
						<tbody>
							<tr>
								<th>Nome completo:</th>
								<td colspan="3"><input tabindex="51" name="nomeFilho" type="text" size="40" /></td>
							</tr>
							<tr>
								<th width="140">Data de Nasc.:</th>
								<td width="230">
									<t:inputCalendar tabindex="52"  renderAsPopup="true" popupTodayString="#{properties['popup_today_string']}" popupDateFormat="dd/MM/yyyy" id="dataNascimentoFilho" forceId="true"
										popupWeekString="#{properties['popup_week_string']}" renderPopupButtonAsImage="true" readonly="false" onblur="checkDate(this)" onkeypress="return maskDate(this,event)" />
								</td>
								<th width="100">Sexo:</th>
								<td width="120">
									<select tabindex="53" name="sexoFilho">
										<option></option>
										<option value="M">Masculino</option>
										<option value="F">Feminino</option>
									</select>
								</td>
							</tr>
						</tbody>
						
						<tfoot>
							<tr>
								<th>Adicionar Filho (a)</th>
								<td colspan="3">
									<a class="botao_adicionar" title="Adicionar" href="javascript:incluirFilho();" onclick=""><span>adicionar</span></a>
								</td>
							</tr>
						</tfoot>
					</table>
					
					<br /><br />
					<h2>Lista de Filhos</h2>
					
					<table class="tab_lista" cellpadding="2" cellspacing="1">
						<thead>
							<tr>
								<th width="370">Nome do Filho (a)</th>
								<th width="100">Data de Nasc.</th>
								<th width="100">Sexo</th>
								<th width="20"></th>
							</tr>
						</thead>
						<tbody id="tb_filho">
							<c:if test="${not empty requestScope.AssociadoBean.associado.filhos}">
								<c:forEach var="filho" items="${requestScope.AssociadoBean.associado.filhos}">
									<tr>
										<td><c:out value="${filho.nome }"/></td>
										<td><fmt:formatDate value="${filho.dataNascimento}" pattern="dd/MM/yyyy"/></td>
										<td><c:out value="${filho.sexo}"/></td>
										<td>
											<a class='botao_excluir' href="javascript:removerFilho('<c:out value="${filho.nome}"/>');"><span>excluir</span></a>
											<input type='hidden' name='filho_nome' value='<c:out value="${filho.nome }"/>'>
											<input type='hidden' name='filho_dataNascimento' value='<fmt:formatDate value="${filho.dataNascimento }" pattern="dd/MM/yyyy"/>'/>
											<input type='hidden' name='filho_sexo' value='<c:out value="${filho.sexo }"/>'/>
										</td>
									</tr>
								</c:forEach>
							</c:if>
						</tbody>
					</table>
					<t:commandLink action="#{AssociadoBean.salvar}" title="#{properties['lb_salvar']}"  styleClass="botao_salvar"></t:commandLink>
					<br /><br /><br /><br /><br /><br />
				</div>
				</h:form>
			</div>
		</body>
	</html>
</f:view>