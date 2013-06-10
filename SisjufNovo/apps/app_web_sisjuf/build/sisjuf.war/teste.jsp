<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>

<script language="JavaScript">

	function detalhar(codigo) {
	
		window.location = '<c:url value="/Financeiro/Banco/Detalhar"/>?codigo=' + codigo; 

	}
</script>

<html>
	<title>tsdseste.jsp</title>
<body>
	
	
	PARAM: <c:out value="${param.codigo}"/>
	
	<table>
	
		<tr>
			<td>
				codigo
			</td>
			<td>
				nome
			</td>
		</tr>		
	
		<c:forEach items="${requestScope.collBancos}" var="item">
			<tr>
				<td><a href="javascript:void(0);" onclick="detalhar(<c:out value="${item.codigo}"/>);"><c:out value="${item.codigo}"/></a></td>
				<td><c:out value="${item.nome}"/></td>
			</tr>
		</c:forEach>
		
	</table>
	
</body>
</html>