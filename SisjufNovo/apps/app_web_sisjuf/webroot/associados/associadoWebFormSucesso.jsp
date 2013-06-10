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
								
				<div id="miolo">
					<h1>Módulo Associados</h1>
					<h2>Cadastro de Associado realizado com sucesso!</h2>

				</div>
				
			</div>
		</body>
	</html>
</f:view>