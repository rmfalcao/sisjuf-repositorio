
<%
if (request.getAttribute("msgP") != null)
	out.println(request.getAttribute("msgP"));
%>
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
//-->
</script>
	<div id="menu">
		<div id="menu_senha1">
			<a class="divisoria" href="<c:url value="/seguranca/alterarSenha.jsf"/>">Alterar senha</a>
		</div>
		<div id="menu_senha2">
			<a class="divisoria" href="<c:url value="/seguranca/alterarSenha.jsf"/>">Alterar senha</a>
		</div>
		
<!-- ADMINISTRAÇÃO -->
		<div id="menu_adm1">
			<a href="javascript:void(0)" class="divisoria" onfocus="MM_changeProp('menu_adm2','','style.display','inline','DIV'); 
																	MM_changeProp('menu_senha1','','style.display','inline','DIV'); 
																	MM_changeProp('menu_financ1','','style.display','inline','DIV'); 
																	MM_changeProp('menu_associado1','','style.display','inline','DIV');  
																	MM_changeProp('menu_convenio1','','style.display','inline','DIV');
																	MM_changeProp('submenu1','','style.display','inline','DIV'); 
																	
																	MM_changeProp('menu_adm1','','style.display','none','DIV');
																	MM_changeProp('menu_senha2','','style.display','none','DIV'); 
																	MM_changeProp('menu_financ2','','style.display','none','DIV'); 
																	MM_changeProp('menu_associado2','','style.display','none','DIV');
																	MM_changeProp('menu_convenio2','','style.display','none','DIV');  
																	MM_changeProp('submenu2','','style.display','none','DIV'); 
																	MM_changeProp('submenu3','','style.display','none','DIV'); 
																	MM_changeProp('submenu4','','style.display','none','DIV');">
																	Adm. do Sistema</a>
		</div>
		<div id="menu_adm2">
			<a href="javascript:void(0)" class="divisoria" onfocus="MM_changeProp('menu_adm1','','style.display','inline','DIV'); 
			
																	MM_changeProp('menu_adm2','','style.display','none','DIV'); 
																	MM_changeProp('submenu1','','style.display','none','DIV'); ">
																	Adm. do Sistema</a>
		</div>
		<div id="submenu1">
			<div><a class="submenu" href="<c:url value="/seguranca/perfilConsulta.jsf"/>">Grupos</a></div>
			<div><a class="submenu" href="<c:url value="/seguranca/usuarioConsulta.jsf"/>">Usuários</a></div>
		</div>
		
		
		
		<br /><br />
		<span>Módulos</span>
		<br /><br />
		
		
<!-- FINANCEIRO -->			
		<div id="menu_financ1">
			<a href="javascript:void(0)" class="divisoria" onfocus="MM_changeProp('menu_financ2','','style.display','inline','DIV'); 
																	MM_changeProp('menu_senha1','','style.display','inline','DIV'); 
																	MM_changeProp('menu_adm1','','style.display','inline','DIV')
																	MM_changeProp('menu_associado1','','style.display','inline','DIV');
																	MM_changeProp('menu_convenio1','','style.display','inline','DIV');
																	MM_changeProp('submenu2','','style.display','inline','DIV');
																	
																	MM_changeProp('menu_financ1','','style.display','none','DIV'); 
																	MM_changeProp('menu_senha2','','style.display','none','DIV'); 
																	MM_changeProp('menu_adm2','','style.display','none','DIV'); 
																	MM_changeProp('menu_associado2','','style.display','none','DIV');
																	MM_changeProp('menu_convenio2','','style.display','none','DIV');
																	MM_changeProp('submenu1','','style.display','none','DIV'); 
																	MM_changeProp('submenu3','','style.display','none','DIV'); 
																	MM_changeProp('submenu4','','style.display','none','DIV');">
																	<b>Financeiro</b></a>
		</div>
		<div id="menu_financ2">
			<a href="javascript:void(0)" class="divisoria" onfocus="MM_changeProp('menu_financ1','','style.display','inline','DIV');
																	 
																	MM_changeProp('menu_financ2','','style.display','none','DIV');
																	MM_changeProp('submenu2','','style.display','none','DIV');  ">
																	<b>Financeiro</b></a>
		</div>
		<div id="submenu2">
			<div><a class="submenu" href="<c:url value="/financeiro/bancoConsulta.jsf"/>">Bancos</a></div>
			<div><a class="submenu" href="<c:url value="/financeiro/contaConsulta.jsf"/>">Contas</a></div>
			<div><a class="submenu" href="<c:url value="/financeiro/tipoLancamentoConsulta.jsf"/>">Tipos de Lançamentos</a></div>
			<div><a class="submenu" href="<c:url value="/financeiro/origemLancamentoConsulta.jsf"/>">Origem de Lançamentos</a></div>
			<div><a class="submenu" href="<c:url value="/Financeiro/DiretorFinanceiro/Listar"/>">Diretoria Financeira</a></div>
			<div><a class="submenu" href="<c:url value="/financeiro/extratoConsulta.jsf"/>">Extratos</a></div>
			<div><a class="submenu" href="<c:url value="/financeiro/saldoForm.jsf"/>">Saldos</a></div>
			<div><a class="submenu" href="<c:url value="/financeiro/lancamentoConsulta.jsf"/>">Consultar Lançamentos</a></div>
			<div><a class="submenu" href="<c:url value="/financeiro/lancamentoForm.jsf"/>">Efetuar Lançamento</a></div>
			<div><a class="submenu" href="<c:url value="/financeiro/movimentarForm.jsf"/>">Movimentar Valores</a></div>
		</div>
		
		
<!-- ASSOCIADOS -->
		<div id="menu_associado1">
			<a href="javascript:void(0)" class="divisoria" onfocus="MM_changeProp('menu_associado2','','style.display','inline','DIV'); 
																	MM_changeProp('menu_senha1','','style.display','inline','DIV'); 
																	MM_changeProp('menu_adm1','','style.display','inline','DIV'); 
																	MM_changeProp('menu_financ1','','style.display','inline','DIV');
																	MM_changeProp('menu_convenio1','','style.display','inline','DIV'); 
																	MM_changeProp('submenu3','','style.display','inline','DIV'); 
																	
																	MM_changeProp('menu_associado1','','style.display','none','DIV'); 
																	MM_changeProp('menu_senha2','','style.display','none','DIV'); 
																	MM_changeProp('menu_adm2','','style.display','none','DIV'); 
																	MM_changeProp('menu_financ2','','style.display','none','DIV');
																	MM_changeProp('menu_convenio2','','style.display','none','DIV'); 
																	MM_changeProp('submenu1','','style.display','none','DIV'); 
																	MM_changeProp('submenu2','','style.display','none','DIV'); 
																	MM_changeProp('submenu4','','style.display','none','DIV');">
																	<b>Associado</b></a>
		</div>
		<div id="menu_associado2">
			<a href="javascript:void(0)" class="divisoria" onfocus="MM_changeProp('menu_associado1','','style.display','inline','DIV'); 
																	
																	MM_changeProp('menu_associado2','','style.display','none','DIV');
																	MM_changeProp('submenu3','','style.display','none','DIV'); ">
																	<b>Associado</b></a>

		</div>
		<div id="submenu3">
			<div><a class="submenu" href="<c:url value="/associados/associadoForm.jsf"/>">Cadastrar</a></div>
			<div><a class="submenu" href="<c:url value="/associados/associadoPesquisa.jsf"/>">Pesquisar</a></div>
			<div><a class="submenu" href="<c:url value="/associados/associadoEnviaEmail.jsf"/>">Enviar E-mail</a></div>
			<div><a class="submenu" href="<c:url value="/associados/associadoEventos.jsf"/>">Histórico de Eventos</a></div>
			<div><a class="submenu" href="<c:url value="/associados/associadoAtualizaValor.jsf"/>">Valor de Sócio Usuário</a></div>
			<div><a class="submenu" href="<c:url value="/associados/associadoAniversariantes.jsf"/>">Próximos Aniversariantes</a></div>
			<div><a class="submenu" href="<c:url value="/associados/associadoImportarNucre.jsf"/>">Importar arquivo NUCRE</a></div>
		</div>
		<!-- CONVÊNIO -->
		<div id="menu_convenio1">
			<a href="javascript:void(0)" class="divisoria" onfocus="MM_changeProp('menu_convenio2','','style.display','inline','DIV'); 
																	MM_changeProp('menu_senha1','','style.display','inline','DIV'); 
																	MM_changeProp('menu_adm1','','style.display','inline','DIV'); 
																	MM_changeProp('menu_financ1','','style.display','inline','DIV');
																	MM_changeProp('menu_associado1','','style.display','inline','DIV'); 
																	MM_changeProp('submenu4','','style.display','inline','DIV'); 
																	
																	MM_changeProp('menu_convenio1','','style.display','none','DIV');
																	MM_changeProp('menu_senha2','','style.display','none','DIV'); 
																	MM_changeProp('menu_adm2','','style.display','none','DIV'); 
																	MM_changeProp('menu_financ2','','style.display','none','DIV');
																	MM_changeProp('menu_associado2','','style.display','none','DIV');  
																	MM_changeProp('submenu1','','style.display','none','DIV'); 
																	MM_changeProp('submenu2','','style.display','none','DIV');
																	MM_changeProp('submenu3','','style.display','none','DIV');">
																	<b>Convênio</b></a>
		</div>
		<div id="menu_convenio2">
			<a href="javascript:void(0)" class="divisoria" onfocus="MM_changeProp('menu_convenio1','','style.display','inline','DIV'); 
																	
																	MM_changeProp('menu_convenio2','','style.display','none','DIV');
																	MM_changeProp('submenu4','','style.display','none','DIV'); ">
																	<b>Convênio</b></a>

		</div>
		<div id="submenu4">
			<div><a class="submenu" href="<c:url value="/associados/convenio/convenioPesquisa.jsf"/>">Convênio</a></div>
			<div><a class="submenu" href="<c:url value="/associados/convenio/gerarFaturas.jsf"/>">Gerar Faturas</a></div>
			<div><a class="submenu" href="<c:url value="/associados/convenio/atividade/atividadePesquisa.jsf"/>">Atividade</a></div>
			<div><a class="submenu" href="<c:url value="/associados/convenio/faturaPesquisa.jsf"/>">Faturas</a></div>
			<div><a class="submenu" href="<c:url value="/associados/convenio/imposto/impostoPesquisa.jsf"/>">Imposto de Renda</a></div>
		</div>
	</div>