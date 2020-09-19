<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
	<html>
		<head>
			<title>SISJUF - Sistema ASSERJUF</title>
			<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
			<link href="<c:url value="/nucleo/style/sisjuf.css"/>" type="text/css" rel="stylesheet" />
			<link rel="Shortcut Icon" type="image/png" href="<c:url value="/nucleo/images/icone.png"/>">
			<script src="<c:url value="/nucleo/js/sisjuf.js" />" type="text/javascript"></script>
			<script>
				function setAcao(form, acao){
					form.acao.value = acao;
				}
				
				function salvar() {
					var url;
					
					if (document.forms[0].codigo.value == '') {
						url	= '<c:url value="/Financeiro/DiretorFinanceiro/Incluir"/>';
					} else {
						url	= '<c:url value="/Financeiro/DiretorFinanceiro/Alterar"/>';
					}
					
					document.forms[0].action = url;
					document.forms[0].submit();

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
						voltar <a href="<c:url value="/Financeiro/DiretorFinanceiro/Listar"/>"><img src="<c:url value="/nucleo/images/setinhavoltar.gif"/>" /></a>
					</div>
				</div>
		
				<%@ include file="/nucleo/includes/inc_menuFinanceiro.jsp" %>
				<form method="post">
					<div id="miolo">
						<h1>Módulo Financeiro</h1>
						<h2>Diretores Financeiros - Cadastro</h2>
						<!-- aqui vem a mensagem -->
						<table class="tab_cadastro" cellpadding="2" cellspacing="1">
							<thead>
								<tr>
									<th width="190">Diretor Financeiro</th>
									<th width="400"></th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<th>Nome:</th>
									<td>
										<input name="nome" type="text" value="<c:out value="${requestScope.diretorFinanceiroVO.nome}"/>" size="30" maxlength="50">
									</td>
								</tr>
								<tr>
									<th>Data de início da gestão:</th>
									<td>
										<input name="dataInicioGestao" type="text" value="<c:out value="${requestScope.diretorFinanceiroVO.dataInicioGestaoFormatada}"/>" size="30" maxlength="10" onblur="checkDate(this)" onkeypress="return maskDate(this,event)">
									</td>
								</tr>

							</tbody>
						</table>
						<br />
						<a href="javascript:void(0);" onclick="setAcao(document.forms[0], 'Salva_DiretorFinanceiro');salvar();" class="botao_salvar"></a>
						<input type="hidden" name="acao" value=""/>
						<input type="hidden" name="codigo" value="<c:out value="${requestScope.diretorFinanceiroVO.codigo}"/>">
					</div>
				</form>
			</div>
		
		</body>
	</html>