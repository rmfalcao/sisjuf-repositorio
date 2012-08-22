<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>

<html>
	<title>ERRO!</title>

<body> 

<h3>ERRO DE AMBIENTE!!</h3>
<br><br>
<b><c:out value="${requestScope.errorEnvException}"/></b>


</body>

</html>