<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>

<html>
	<title>tsdseste.jsp</title>
	
<script language="JavaScript">

function confirmar() {

	var f = document.forms[0];

	if (f.codigo.value == '') {
		f.action = '<c:url value="/Financeiro/Banco/Inserir"/>';

	} else {
		f.action = '<c:url value="/Financeiro/Banco/Alterar"/>';

	}
	
	f.submit();
	
}

</script>
	
<body>
	
	
	PARAM: <c:out value="${param.codigo}"/>
	
	TIPO_LANCAMENTO: <c:out value="${requestScope.tipoLancamentoVO.flgNaoApagavel}"/>
	
	<form method="post">
	
	<table>
	
		<tr>
			<td>
				codigo
			</td>
			<td>
				nome
			</td>
			<td>
				sigla
			</td>
			<td>
				Número
			</td>
		</tr>		
		<tr>
			<td>
				<input type="text" name="codigo" value="<c:out value="${requestScope.bancoVO.codigo}"/>">
			</td>
			<td>
				<input type="text" name="nome" value="<c:out value="${requestScope.bancoVO.nome}"/>">
			</td>
			<td>
				<input type="text" name="sigla" value="<c:out value="${requestScope.bancoVO.sigla}"/>">
			</td>
			<td>
				<input type="text" name="numero" value="<c:out value="${requestScope.bancoVO.numero}"/>">
			</td>
		</tr>		
	
	</table>
	
	<input type="button" value="Confirmar" onclick="confirmar();">
	
	</form>
	
</body>
</html>