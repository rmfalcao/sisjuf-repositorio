
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<f:view>
	<f:loadBundle basename="sisjuf" var="properties" />
	<html>
		<head>
			<title>SISJUF - Sistema ASSERJUF</title>
			<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
			<link href="<c:url value="/nucleo/style/sisjuf.css"/>" type="text/css" rel="stylesheet" />
			<link rel="Shortcut Icon" type="image/png" href="<c:url value="/nucleo/images/icone.png"/>">
			<script src="<c:url value="/nucleo/js/sisjuf.js" />" type="text/javascript"></script>
			<script type="text/JavaScript">
			<!--
			function MM_findObj(n, d) { //v4.01
			  var p,i,x;  if(!d) d=document; if((p=n.indexOf("?"))>0&&parent.frames.length) {
			    d=parent.frames[n.substring(p+1)].document; n=n.substring(0,p);}
			  if(!(x=d[n])&&d.all) x=d.all[n]; for (i=0;!x&&i<d.forms.length;i++) x=d.forms[i][n];
			  for(i=0;!x&&d.layers&&i<d.layers.length;i++) x=MM_findObj(n,d.layers[i].document);
			  if(!x && d.getElementById) x=d.getElementById(n); return x;
			}
			
			function MM_changeProp(objName,x,theProp,theValue) { //v6.0
			  var obj = MM_findObj(objName);
			  if (obj && (theProp.indexOf("style.")==-1 || obj.style)){
			    if (theValue == true || theValue == false)
			      eval("obj."+theProp+"="+theValue);
			    else eval("obj."+theProp+"='"+theValue+"'");
			  }
			}
			
			function setAcao(form, acao){
				form.acao.value = acao;
			}
			
			function imprimir() {
				var url			= '<c:url value="/associados/associadoPrint.jsf"/>';
				var name		= 'telaImpressao';
				var features	= 'toolbar=no,status=no,resizable=yes,scrollbars=yes,width=750,height=500';
				window.open(url,name,features);
			}
			//-->
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
					<h2>Pesquisa de Associado</h2>
					<a class="botao_novo" href="<c:url value="/associados/associadoForm.jsf"/>"><span>novo</span></a>
					<br /><br />
					<t:messages id="msgs" showDetail="true" showSummary="false" errorClass="textoMsgErro" infoClass="textoMsgInfo"/>
					<h:form id="associadoConsulta">
					<table class="tab_cadastro" cellpadding="2" cellspacing="1">
						<thead>
							<tr>
								<th>Filtro</th>
								<th colspan="3"></th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th><h:outputLabel for="nome" value="#{properties['lb_nomeCompleto']}" />:</th>
								<td colspan="3"><t:inputText size="40" id="nome" value="#{AssociadoBean.filtro.nome}"></t:inputText></td>
							</tr>
							<tr>
								<th width="140">Data de associação:</th>
								<td width="190">
									<rich:calendar id="dataInicioAssociacao" popup="true" datePattern="dd/MM/yyyy" showApplyButton="false"
										cellWidth="24px" cellHeight="22px" style="width:200px" disabled="false" locale="en/US"
										value="#{AssociadoBean.filtro.dataInicioAssociacao}" inputClass="inputCalendar" 
										enableManualInput="true" oninputblur="checkDate(this)" 
										oninputkeypress="return maskDate(this,event);" />
									<rich:calendar id="dataFimAssociacao" popup="true" datePattern="dd/MM/yyyy" showApplyButton="false"
										cellWidth="24px" cellHeight="22px" style="width:200px" disabled="false" locale="en/US"
										value="#{AssociadoBean.filtro.dataFimAssociacao}" inputClass="inputCalendar" 
										enableManualInput="true" oninputblur="checkDate(this)" 
										oninputkeypress="return maskDate(this,event);"/>
								</td>
								<th width="140">Nº. Matrícula:</th>
								<td width="120"><t:inputText size="8" id="codigo" value="#{AssociadoBean.filtro.codigo}" onkeypress="return justNumber(this,event)"/></td>
							</tr>
							<tr>
								<th>Possui Dependentes:</th>
								<td><t:selectBooleanCheckbox id="possuiDependentes" value="#{AssociadoBean.possuiDependentes}"/></td>
								<th>Possui Filhos:</th>
								<td><t:selectBooleanCheckbox id="possuiFilho" value="#{AssociadoBean.possuiFilho}"/></td>
							</tr>
							<tr>
								<th>Incluir Inativos:</th>
								<td><t:selectBooleanCheckbox id="incluiInativo" value="#{AssociadoBean.incluiInativo}"/></td>
								<th>Apenas Pré-Cadastro:</th>
								<td><t:selectBooleanCheckbox id="preCadastro" value="#{AssociadoBean.preCadastro}"/></td>
							</tr>
						</tbody>
					</table>
					<br /><br />
					<div id="pesquisa_avancada1">
					<a href="javascript:void(0)" class="link_h2" 
						onfocus="MM_changeProp('pesquisa_avancada2','','style.display','inline','DIV'); MM_changeProp('pesquisa_avancada1','','style.display','none','DIV'); ">
						<h2>Pesquisa Avançada</h2>
					</a>
					</div>
					
					<div id="pesquisa_avancada2">
					<a href="javascript:void(0)" class="link_h2" onfocus="MM_changeProp('pesquisa_avancada1','','style.display','inline','DIV'); MM_changeProp('pesquisa_avancada2','','style.display','none','DIV');">
						<h2>Pesquisa Avançada</h2>
					</a>
					
					<table class="tab_cadastro" cellpadding="2" cellspacing="1">
						<thead>
							<tr>
								<th>Endereço</th>
								<th colspan="3"></th>
							</tr>
						</thead>
						
						<tbody>
							<tr>
								<th>Logradouro:</th>
								<td><t:inputText size="40" id="logradouro" value="#{AssociadoBean.filtro.endereco.logradouro}" /></td>
								<th>Número:</th>
								<td><t:inputText size="3" id="numero" value="#{AssociadoBean.filtro.endereco.numero}" /></td>
							</tr>
							<tr>
								<th>Complemento:</th>
								<td colspan="3"><t:inputText size="40" id="complemento" value="#{AssociadoBean.filtro.endereco.complemento}" /></td>
							</tr>
							<tr>
								<th>Bairro:</th>
								<td><t:inputText size="30" id="bairro" value="#{AssociadoBean.filtro.endereco.bairro}" /></td>
								<th>CEP:</th>
								<td><t:inputText size="12" id="cep" value="#{AssociadoBean.filtroCep}" onkeypress="return MaskCEP(this,event)"/></td>
							</tr>
							<tr>
								<th>Estado:</th>
								<td colspan="3">
									<h:selectOneMenu id="enderecoEstado" value="#{AssociadoBean.filtro.endereco.municipio.estado.codigo}" >
										<f:selectItem itemLabel="#{properties['lb_selecione']}" itemValue=""/>
										<f:selectItems id="sel_endEstados" value="#{AssociadoBean.estados}"/>
									</h:selectOneMenu>
								</td>
							</tr>
							<tr>
								<th>Cidade:</th>
								<td colspan="3"><t:inputText size="30" id="municipio" value="#{AssociadoBean.filtro.endereco.municipio.nome}" /></td>
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
								<td><t:inputText size="40" id="email" value="#{AssociadoBean.filtro.email}" /></td>
								<th>Tel. Celular:</th>
								<td><t:inputText size="15" id="telefoneCelular" value="#{AssociadoBean.filtroTelefoneCelular}" onkeypress="return MaskTelefone(this,event)"/></td>
							</tr>
							<tr>
								<th>Tel. Residencial:</th>
								<td><t:inputText size="15" id="telefoneResidencial" value="#{AssociadoBean.filtroTelefoneResidencial}" onkeypress="return MaskTelefone(this,event)"/></td>
								<th>Tel. Comercial:</th>
								<td><t:inputText size="15" id="telefoneComercial" value="#{AssociadoBean.filtroTelefoneComercial}" onkeypress="return MaskTelefone(this,event)"/></td>
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
								<th>R.G.:</th>
								<td>
									<t:inputText size="20" id="rg" value="#{AssociadoBean.filtro.rg}" 
										onkeypress="return justNumber(this,event)" maxlength="10"/>
								</td>
								<th>C.P.F.:</th>
								<td>
									<t:inputText size="15" id="cpf" value="#{AssociadoBean.filtroCpf}" 
										onkeypress="return MaskCPF(this,event)"/>
								</td>
							</tr>
							<tr>
								<th>Data de Nasc.:</th>
								<td>
									<rich:calendar id="dataInicioNascimento" popup="true" datePattern="dd/MM/yyyy" showApplyButton="false"
										cellWidth="24px" cellHeight="22px" style="width:200px" disabled="false" locale="en/US"
										value="#{AssociadoBean.filtro.dataInicioNascimento}" inputClass="inputCalendar" 
										enableManualInput="true" oninputblur="checkDate(this)" 
										oninputkeypress="return maskDate(this,event);"/>
									<rich:calendar id="dataFimNascimento" popup="true" datePattern="dd/MM/yyyy" showApplyButton="false"
										cellWidth="24px" cellHeight="22px" style="width:200px" disabled="false" locale="en/US"
										value="#{AssociadoBean.filtro.dataFimNascimento}" inputClass="inputCalendar" 
										enableManualInput="true" oninputblur="checkDate(this)" 
										oninputkeypress="return maskDate(this,event);"/>
								</td>
								<th>Sexo:</th>
								<td>
									<h:selectOneMenu id="fsexo" value="#{AssociadoBean.filtro.sexo}" >
										<f:selectItem itemLabel="#{properties['lb_selecione']}" itemValue=""/>
										<f:selectItem itemLabel="#{properties['lb_masculino']}" itemValue="M"/>
										<f:selectItem itemLabel="#{properties['lb_feminino']}" itemValue="F"/>
									</h:selectOneMenu>
								</td>
							</tr>
							<tr>
								<th>Estado (Naturalidade):</th>
								<td colspan="3">
									<h:selectOneMenu id="naturalidadeEstado" value="#{AssociadoBean.filtro.naturalidade.estado.codigo}" >
										<f:selectItem itemLabel="#{properties['lb_selecione']}" itemValue=""/>
										<f:selectItems id="sel_natEstado" value="#{AssociadoBean.estados}"/>
									</h:selectOneMenu>
								</td>
							</tr>
							<tr>
								<th>Naturalidade:</th>
								<td colspan="3">
									<t:inputText size="30" id="naturalidade" value="#{AssociadoBean.filtro.naturalidade.nome}" />
								</td>
							</tr>
							<tr>
								<th>Estado Civil:</th>
								<td colspan="3">
									<h:selectOneMenu id="estadoCivil" value="#{AssociadoBean.filtro.estadoCivil}" >
										<f:selectItem itemLabel="#{properties['lb_selecione']}" itemValue=""/>
										<f:selectItem itemLabel="#{properties['lb_casado']}" itemValue="C"/>
										<f:selectItem itemLabel="#{properties['lb_desquitado']}" itemValue="D"/>
										<f:selectItem itemLabel="#{properties['lb_divorciado']}" itemValue="I"/>
										<f:selectItem itemLabel="#{properties['lb_solteiro']}" itemValue="S"/>
										<f:selectItem itemLabel="#{properties['lb_viuvo']}" itemValue="V"/>
										<f:selectItem itemLabel="#{properties['lb_outro']}" itemValue="O"/>
									</h:selectOneMenu>
								</td>
							</tr>
							<tr>
								<th>Profissão:</th>
								<td colspan="3"><t:inputText size="40" id="profissao" value="#{AssociadoBean.filtro.profissao.nome}" /></td>
							</tr>
							<tr>
								<th>Grupo Sangüíneo:</th>
								<td>
									<h:selectOneMenu id="grupoSanguineo" value="#{AssociadoBean.filtro.grupoSanguineo}" >
										<f:selectItem itemLabel="#{properties['lb_selecione']}" itemValue=""/>
										<f:selectItem itemLabel="#{properties['lb_A']}" itemValue="A"/>
										<f:selectItem itemLabel="#{properties['lb_B']}" itemValue="B"/>
										<f:selectItem itemLabel="#{properties['lb_AB']}" itemValue="AB"/>
										<f:selectItem itemLabel="#{properties['lb_O']}" itemValue="O"/>
									</h:selectOneMenu>
								</td>
								<th>Fator Rh:</th>
								<td>
									<h:selectOneMenu id="fatorRh" value="#{AssociadoBean.filtro.fatorRh}" >
										<f:selectItem itemLabel="#{properties['lb_selecione']}" itemValue=""/>
										<f:selectItem itemLabel="+" itemValue="+"/>
										<f:selectItem itemLabel="-" itemValue="-"/>
									</h:selectOneMenu>
								</td>
							</tr>
							<tr>
								<th>Nome do Pai:</th>
								<td colspan="3"><t:inputText size="40" id="nomePai" value="#{AssociadoBean.filtro.nomePai}" /></td>
							</tr>
							<tr>
								<th>Nome da Mãe:</th>
								<td colspan="3"><t:inputText size="40" id="nomeMae" value="#{AssociadoBean.filtro.nomeMae}" /></td>
							</tr>
							<tr>
								<th>Nome do Cônjugue:</th>
								<td colspan="3"><t:inputText size="40" id="nomeConjuge" value="#{AssociadoBean.filtro.conjuge.nome}" /></td>
							</tr>
							<tr>
								<th>Calçado nº.:</th>
								<td colspan="3">
									<t:inputText size="5" id="numeroInicioCalcado" value="#{AssociadoBean.filtro.numeroInicioCalcado}" onkeypress="return justNumber(this,event)"/> - 
									<t:inputText size="5" id="numeroFimCalcado" value="#{AssociadoBean.filtro.numeroFimCalcado}" onkeypress="return justNumber(this,event)"/>
								</td>
							</tr>
						</tbody>
						
						<thead>
							<tr>
								<th>Situação na Justiça</th>
								<th colspan="3"></th>
							</tr>
						</thead>
						
						<tbody>
							<tr>
								<th>Matrícula na Justiça:</th>
								<td colspan="3"><t:inputText size="10" id="matriculaJustica" 
										value="#{AssociadoBean.filtro.matriculaJustica}" maxlength="10" /></td>
							</tr>
							<tr>
								<th>Órgão:</th>
								<td><t:inputText size="40" id="orgao" value="#{AssociadoBean.filtro.setor.orgao.nome}" /></td>
								<th>Status:</th>
								<td>
									<h:selectOneMenu id="statusJustica" value="#{AssociadoBean.filtro.statusJustica}" >
										<f:selectItem itemLabel="#{properties['lb_selecione']}" itemValue=""/>
										<f:selectItem itemLabel="#{properties['lb_ativo']}" itemValue="A"/>
										<f:selectItem itemLabel="#{properties['lb_inativo']}" itemValue="I"/>
										<f:selectItem itemLabel="#{properties['lb_requisitado']}" itemValue="R"/>
										<f:selectItem itemLabel="#{properties['lb_removido']}" itemValue="X"/>
									</h:selectOneMenu>
								</td>
							</tr>
							<tr>
								<th>Setor:</th>
								<td colspan="3"><t:inputText size="40" id="setor" value="#{AssociadoBean.filtro.setor.nome}" /></td>
							</tr>
							<tr>
								<th>Endereço do Setor:</th>
								<td colspan="3"><t:inputText size="40" id="setorEndereco" value="#{AssociadoBean.filtro.setor.endereco.logradouro}" /></td>
							</tr>
							<tr>
								<th>Estado do Setor:</th>
								<td colspan="3">
									<h:selectOneMenu id="setorEstado" value="#{AssociadoBean.filtro.setor.endereco.municipio.estado.codigo}" >
										<f:selectItem itemLabel="#{properties['lb_selecione']}" itemValue=""/>
										<f:selectItems id="sel_setorEstado" value="#{AssociadoBean.estados}"/>
									</h:selectOneMenu>
								</td>
							</tr>
							<tr>
								<th>Município do Setor:</th>
								<td colspan="3"><t:inputText size="30" id="setorMunicipio" value="#{AssociadoBean.filtro.setor.endereco.municipio.nome}" /></td>
							</tr>
							<tr>
								<th>Tel. do Setor:</th>
								<td><t:inputText size="15" id="telefone" value="#{AssociadoBean.filtroTelefoneSetor}" onkeypress="return MaskTelefone(this,event)"/></td>
								<th>Ramal do Setor:</th>
								<td><t:inputText size="5" id="ramal" value="#{AssociadoBean.filtro.setor.ramal}" onkeypress="return justNumber(this,event)"/></td>
							</tr>
							<tr>
								<th>Categoria:</th>
								<td>
									<h:selectOneMenu id="statusCategoria" value="#{AssociadoBean.filtro.statusCategoria}" >
										<f:selectItem itemLabel="#{properties['lb_selecione']}" itemValue=""/>
										<f:selectItem itemLabel="#{properties['lb_socioContribuinte']}" itemValue="C"/>
										<f:selectItem itemLabel="#{properties['lb_socioUsuario']}" itemValue="U"/>
									</h:selectOneMenu>
								</td>
								<th>Recebe Jornal:</th>
								<td><t:selectBooleanCheckbox id="statusRecebeJornal" value="#{AssociadoBean.statusRecebeJornalFiltro}"/></td>
							</tr>
							<tr>
								<th>Nome Contribuinte:</th>
								<td colspan="3"><t:inputText size="40" id="contribuinteNome" value="#{AssociadoBean.filtro.contribuinte.nome}" /></td>
							</tr>
							<tr>
								<th>Mensalidade (R$):</th>
								<td colspan="3">
									<t:inputText size="10" id="inicioMensalidade" value="#{AssociadoBean.filtro.valorInicioMensalidade}" converter="DoubleConverter" onkeyup="formatarCampoNumero(this)"/> - 
									<t:inputText size="10" id="fimMensalidade" value="#{AssociadoBean.filtro.valorFimMensalidade}" converter="DoubleConverter" onkeyup="formatarCampoNumero(this)"/>
								</td>
							</tr>
							<tr>
								<th>Banco:</th>
								<td colspan="3">
									<h:selectOneMenu id="contaBanco" value="#{AssociadoBean.filtro.conta.bancoVO.codigo}" >
										<f:selectItem itemLabel="#{properties['lb_selecione']}" itemValue=""/>
										<f:selectItems id="sel_banco" value="#{AssociadoBean.bancos}"/>
									</h:selectOneMenu>
								</td>
							</tr>
							<tr>
								<th>Nº Agência:</th>
								<td>
									<t:inputText size="5" id="numAgencia" value="#{AssociadoBean.filtro.conta.numAgencia}" maxlength="5" onkeypress="return justNumber(this,event)"/> - 
									<t:inputText size="1" id="digAgencia" value="#{AssociadoBean.filtro.conta.digAgencia}" maxlength="1" onkeypress="return justNumber(this,event)"/>
								</td>
								<th>Nº Conta:</th>
								<td>
									<t:inputText size="5" id="numConta" value="#{AssociadoBean.filtro.conta.numConta}" maxlength="5" onkeypress="return justNumber(this,event)"/> - 
									<t:inputText size="1" id="digConta" value="#{AssociadoBean.filtro.conta.digConta}" maxlength="1" onkeypress="return justNumber(this,event)"/>
								</td>
							</tr>
						</tbody>
		
						<thead>
							<tr>
								<th>Dados Dependente</th>
								<th colspan="3"></th>
							</tr>
						</thead>
						
						<tbody>
							<tr>
								<th>Nome completo:</th>
								<td><t:inputText size="40" id="dependenteNome" value="#{AssociadoBean.filtro.dependente.nome}"/></td>
								<th>R.G:</th>
								<td><t:inputText size="20" id="dependenteRG" value="#{AssociadoBean.filtro.dependente.rg}" /></td>
							</tr>
							<tr>
								<th>Data de Nasc.:</th>
								<td>
									<rich:calendar id="dataInicioNascimentoDependente" popup="true" datePattern="dd/MM/yyyy" 
										showApplyButton="false" cellWidth="24px" cellHeight="22px" style="width:200px" disabled="false" 
										locale="en/US" value="#{AssociadoBean.filtro.dataInicioNascimentoDependente}" inputClass="inputCalendar" 
										enableManualInput="true" oninputblur="checkDate(this)" 
										oninputkeypress="return maskDate(this,event);"/>
									<rich:calendar id="dataFimNascimentoDependente" popup="true" datePattern="dd/MM/yyyy" 
										showApplyButton="false" cellWidth="24px" cellHeight="22px" style="width:200px" disabled="false" 
										locale="en/US" value="#{AssociadoBean.filtro.dataFimNascimentoDependente}" inputClass="inputCalendar" 
										enableManualInput="true" oninputblur="checkDate(this)" 
										oninputkeypress="return maskDate(this,event);"/>
								</td>
								<th>Sexo:</th>
								<td>
									<h:selectOneMenu id="dsexo" value="#{AssociadoBean.filtro.dependente.sexo}" >
										<f:selectItem itemLabel="#{properties['lb_selecione']}" itemValue=""/>
										<f:selectItem itemLabel="#{properties['lb_masculino']}" itemValue="M"/>
										<f:selectItem itemLabel="#{properties['lb_feminino']}" itemValue="F"/>
									</h:selectOneMenu>
								</td>
							</tr>
							<tr>
								<th>Parentesco:</th>
								<td colspan="3">
									<h:selectOneMenu id="parentesco" value="#{AssociadoBean.filtro.dependente.parentesco.codigo}" >
										<f:selectItem itemLabel="#{properties['lb_selecione']}" itemValue=""/>
										<f:selectItems id="sel_parentesco" value="#{AssociadoBean.parentescos}"/>
									</h:selectOneMenu>
								</td>
							</tr>
						</tbody>
		
						<thead>
							<tr>
								<th>Dados do Filho (a)</th>
								<th colspan="3"></th>
							</tr>
						</thead>
						
						<tbody>
							<tr>
								<th>Nome completo:</th>
								<td colspan="3"><t:inputText size="40" id="nomeFilho" value="#{AssociadoBean.filtro.filho.nome}" /></td>
							</tr>
							<tr>
								<th>Data de Nasc.:</th>
								<td>
									<rich:calendar id="dataInicioNascimentoFilho" popup="true" datePattern="dd/MM/yyyy" 
										showApplyButton="false" cellWidth="24px" cellHeight="22px" style="width:200px" disabled="false" 
										locale="en/US" value="#{AssociadoBean.filtro.dataInicioNascimentoFilho}" inputClass="inputCalendar" 
										enableManualInput="true" oninputblur="checkDate(this)" 
										oninputkeypress="return maskDate(this,event);"/>
									<rich:calendar id="dataFimNascimentoFilho" popup="true" datePattern="dd/MM/yyyy" 
										showApplyButton="false" cellWidth="24px" cellHeight="22px" style="width:200px" disabled="false" 
										locale="en/US" value="#{AssociadoBean.filtro.dataFimNascimentoFilho}" inputClass="inputCalendar" 
										enableManualInput="true" oninputblur="checkDate(this)" 
										oninputkeypress="return maskDate(this,event);"/>
								</td>
								<th>Sexo:</th>
								<td>
									<h:selectOneMenu id="filho_sexo" value="#{AssociadoBean.filtro.filho.sexo}" >
										<f:selectItem itemLabel="#{properties['lb_selecione']}" itemValue=""/>
										<f:selectItem itemLabel="#{properties['lb_masculino']}" itemValue="M"/>
										<f:selectItem itemLabel="#{properties['lb_feminino']}" itemValue="F"/>
									</h:selectOneMenu>
								</td>
							</tr>
						</tbody>
						
						<thead>
							<tr>
								<th>Eventos</th>
								<th colspan="3"></th>
							</tr>
						</thead>
						
						<tbody>
							<tr>
								<th>Tipo de Evento:</th>
								<td colspan="3">
									<h:selectOneMenu id="tipos" value="#{AssociadoBean.filtro.historico.tipoEvento.codigo}" >
										<f:selectItem itemLabel="#{properties['lb_selecione']}" itemValue=""/>
										<f:selectItems id="sel_tipo_evento" value="#{AssociadoBean.tipos}"/>
									</h:selectOneMenu>
								</td>
							</tr>
							<tr>
								<th>Data do Evento:</th>
								<td colspan="3">
									<rich:calendar id="dataInicioEvento" popup="true" datePattern="dd/MM/yyyy" 
										showApplyButton="false" cellWidth="24px" cellHeight="22px" style="width:200px" disabled="false" 
										locale="en/US" value="#{AssociadoBean.filtro.historico.dataInicio}" inputClass="inputCalendar" 
										enableManualInput="true" oninputblur="checkDate(this)" 
										oninputkeypress="return maskDate(this,event);"/>
									<rich:calendar id="dataFimEvento" popup="true" datePattern="dd/MM/yyyy" 
										showApplyButton="false" cellWidth="24px" cellHeight="22px" style="width:200px" disabled="false" 
										locale="en/US" value="#{AssociadoBean.filtro.historico.dataFim}" inputClass="inputCalendar" 
										enableManualInput="true" oninputblur="checkDate(this)" 
										oninputkeypress="return maskDate(this,event);"/>
								</td>
							</tr>
						</tbody>
					</table>
					</div>
					
					<t:commandLink action="#{AssociadoBean.consultar}" title="#{properties['lb_filtrar']}" id="filtrar"
						styleClass="botao_filtrar" onclick="setAcao(document.forms[0], 'Filtrar_Associado');">
							<h:outputText value="#{properties['lb_filtrar']}" />
					</t:commandLink>
					<input type="hidden" name="acao" value=""/>
					</h:form>
					<br /><br />
					
					<h2>Lista de Associados</h2>
					<h:form id="associadoResultado">
					<t:saveState value="#{AssociadoBean.associados}" />
					<t:saveState value="#{AssociadoBean.associado}" />
					<t:dataTable id="data" var="associados" value="#{AssociadoBean.associados}" 
						cellspacing="1" cellpadding="2" width="100%" styleClass="tab_lista_maior" preserveDataModel="false">
							<t:column width="140">
								<f:facet name="header">
									<h:outputText value="#{properties['lb_matricula']}" />
								</f:facet>
								<h:outputText value="#{associados.codigo}" />
								<f:facet name="footer">
									<t:commandLink action="#{AssociadoBean.print}" title="#{properties['lb_imprimir']}" immediate="true" 
										id="print"  styleClass="botao_imprimir" target="print">
									</t:commandLink>
						        </f:facet>
							</t:column>
				
							<t:column width="230">
								<f:facet name="header">
									<h:outputText value="#{properties['lb_nomeCompleto']}" />
								</f:facet>
								<t:commandLink action="#{AssociadoBean.carregar}" title="#{properties['lb_carregar']}" immediate="true" 
									id="carregar" onclick="setAcao(document.forms[0], 'Carrega_Associado');">
									<t:updateActionListener property="#{AssociadoBean.associado.codigo}" value="#{associados.codigo}" />
									<h:outputText value="#{associados.nome}" />
								</t:commandLink>
								<f:facet name="footer">
									<t:commandLink action="#{AssociadoBean.carregarEmail}" title="#{properties['lb_enviarEmail']}" 
										immediate="true" id="carregarEmail">
										<t:graphicImage value="/nucleo/images/icon_email.gif" border="0"></t:graphicImage>
									</t:commandLink>
								</f:facet>
							</t:column>
							
							<t:column width="100">
								<f:facet name="header">
									<h:outputText value="#{properties['lb_associadoEm']}" />
								</f:facet>
								<h:outputText value="#{associados.dataAssociacao}" />
							</t:column>
							
							<t:column width="100">
								<f:facet name="header">
									<h:outputText value="#{properties['lb_setorOrgao']}" />
								</f:facet>
								<h:outputText value="#{associados.setor.nome}/" /> <h:outputText value="#{associados.setor.orgao.nome}" />
							</t:column>
							
							
						</t:dataTable>
						</h:form>
						<br /><br /><br /><br /><br />
				</div>
			</div>
		</body>
	</html>
</f:view>