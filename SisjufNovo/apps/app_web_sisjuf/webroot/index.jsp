<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>SISJUF - Sistema ASSERJUF v3.33.1</title>
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
		<link href="<c:url value="/nucleo/style/sisjuf.css"/>" type="text/css" rel="stylesheet" />
		<link rel="Shortcut Icon" type="image/png" href="<c:url value="/nucleo/images/icone.png"/>">
	</head>
	<body>
	
		<div id="geral"> 
			
			<div id="topo">
				<div id="sair">
					sair <a href="<c:url value="/login.jsf"/>"><img src="<c:url value="/nucleo/images/botao_x.gif"/>" /></a>
				</div>
			</div>
	
			<%@ include file="/nucleo/includes/inc_menuFinanceiro.jsp" %>
			
			<div id="miolo">
			</div>
	
		</div>
	</body>
</html>
