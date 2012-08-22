<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
 <HEAD>
  <TITLE> IMPRESSÃO DE CARTEIRINHA </TITLE>
  <link href="<c:url value="/nucleo/style/sisjuf.css"/>" type="text/css" rel="stylesheet" />
 </HEAD>

 <BODY class="bgcarteirinha">
 
 <div style="position:absolute;z-index:0">
	<img src="<c:url value="/nucleo/images/carteirinha_asserjuf_final_1.gif"/>">
  </div>
   <div style="position:absolute;z-index:1"> 
  <TABLE WIDTH="518" HEIGHT="142" CLASS="carteirinha" border="0" CELLSPACING="0" CELLPADDING="0">
  <TR>
	<TD width="22">&nbsp;</TD>
	<TD width="28">&nbsp;</TD>
	<TD width="28">&nbsp;</TD>
	<TD width="34">&nbsp;</TD>
	<TD width="28">&nbsp;</TD>
	<TD width="28">&nbsp;</TD>
	<TD width="28">&nbsp;</TD>
	<TD width="28">&nbsp;</TD>
	<TD width="28">&nbsp;</TD>
	<TD width="28">&nbsp;</TD>
	<TD width="28">&nbsp;</TD>
	<TD width="28">&nbsp;</TD>
	<TD width="28">&nbsp;</TD>
	<TD width="28">&nbsp;</TD>
	<TD width="28">&nbsp;</TD>
	<TD width="28">&nbsp;</TD>
	<TD width="28">&nbsp;</TD>
	<TD width="28">&nbsp;</TD>
  </TR>
  <TR height="14">
	<TD>&nbsp;</TD>
	<TD>&nbsp;</TD>
	<TD>&nbsp;</TD>
	<TD>&nbsp;</TD>
	<TD>&nbsp;</TD>
	<TD>&nbsp;</TD>
	<TD>&nbsp;</TD>
	<TD>&nbsp;</TD>
	<TD>&nbsp;</TD>
	<TD>&nbsp;</TD>
	<TD>&nbsp;</TD>
	<TD>&nbsp;</TD>
	<TD>&nbsp;</TD>
	<TD>&nbsp;</TD>
	<TD>&nbsp;</TD>
	<TD>&nbsp;</TD>
	<TD>&nbsp;</TD>
	<TD>&nbsp;</TD>
  </TR>
  <TR>
	<TD rowspan="2" colspan="7" valign="top" align="center"><font color="#777291">Associação dos Servidores da Justiça federal - Bahia</font><FONT size="2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font></TD>
	<TD>&nbsp;</TD>
	<TD>&nbsp;</TD>
	<TD>&nbsp;</TD>
	<TD>&nbsp;</TD>
	<TD>&nbsp;</TD>
	<TD>&nbsp;</TD>
	<TD>&nbsp;</TD>
	<TD>&nbsp;</TD>
	<TD>&nbsp;</TD>
	<TD>&nbsp;</TD>
	<TD>&nbsp;</TD>
  </TR>
  <TR>
	<TD>&nbsp;</TD>
	<TD>&nbsp;</TD>
	<TD>&nbsp;</TD>
	<TD>&nbsp;</TD>
	<TD colspan="6" align="center"><font color="#777291">Associação dos Servidores da Justiça federal - Bahia&nbsp;</font></TD>
	<TD>&nbsp;</TD>
  </TR>
  <TR>
	<TD>&nbsp;</TD>
	<TD rowspan="2" colspan="5"><font color="#312623">Nome: <c:out value="${requestScope.associado.nome}"/></font></TD>
	<TD>&nbsp;</TD>
	<TD>&nbsp;</TD>
	<TD>&nbsp;</TD>
	<TD>&nbsp;</TD>
	<TD colspan="8" align="center" class="carteirinha2"><font color="#777291">Av.: Ulisses Guimarães, 2631, Sussuarana. CEP.: 41.213-000</font></TD>
  </TR>
  <TR>
	<TD>&nbsp;</TD>
	<TD>&nbsp;</TD>
	<TD>&nbsp;</TD>
	<TD>&nbsp;</TD>
	<TD>&nbsp;</TD>
	<TD>&nbsp;</TD>
	<TD colspan="6" align="center" class="carteirinha2"><font color="#777291">Tel.: 71 3617.2638 / 3306.8382</font></TD>
	<TD>&nbsp;</TD>
  </TR>
  <TR>
	<TD>&nbsp;</TD>
	<TD colspan="5" rowspan="2"><font color="#312623">Categoria: <c:out value="${requestScope.categoria}"/></font></TD>
	<TD>&nbsp;</TD>
	<TD>&nbsp;</TD>
	<TD>&nbsp;</TD>
	<TD>&nbsp;</TD>
	<TD colspan="8" align="center" class="carteirinha2"><font color="#777291">Email: asserjuf@uol.com.br - asserjuf@ba.trf1.gov.br</font></TD>
  </TR>
  <TR>
	<TD>&nbsp;</TD>
	<TD>&nbsp;</TD>
	<TD>&nbsp;</TD>
	<TD>&nbsp;</TD>
	<TD>&nbsp;</TD>
	<TD>&nbsp;</TD>
	<TD>&nbsp;</TD>
	<TD>&nbsp;</TD>
	<TD>&nbsp;</TD>
	<TD>&nbsp;</TD>
	<TD>&nbsp;</TD>
	<TD>&nbsp;</TD>
	<TD>&nbsp;</TD>
  </TR>
  <TR>
	<TD>&nbsp;</TD>
	<TD colspan="2" rowspan="2"><font color="#312623">Matricula: <c:out value="${requestScope.associado.codigo}"/></font></TD>
	<TD colspan="3" rowspan="2"><font color="#312623">RG: <c:out value="${requestScope.associado.rg}"/></font></TD>
	<TD>&nbsp;</TD>
	<TD>&nbsp;</TD>
	<TD>&nbsp;</TD>
	<TD>&nbsp;</TD>
	<TD>&nbsp;</TD>
	<TD>&nbsp;</TD>
	<TD>&nbsp;</TD>
	<TD>&nbsp;</TD>
	<TD>&nbsp;</TD>
	<TD>&nbsp;</TD>
	<TD>&nbsp;</TD>
	<TD>&nbsp;</TD>
  </TR>
  <TR>
	<TD>&nbsp;</TD>
	<TD>&nbsp;</TD>
	<TD>&nbsp;</TD>
	<TD>&nbsp;</TD>
	<TD>&nbsp;</TD>
	<TD>&nbsp;</TD>
	<TD>&nbsp;</TD>
	<TD>&nbsp;</TD>
	<TD>&nbsp;</TD>
	<TD>&nbsp;</TD>
	<TD>&nbsp;</TD>
	<TD>&nbsp;</TD>
	<TD>&nbsp;</TD>
  </TR>
  <TR>
	<TD>&nbsp;</TD>
	<TD>&nbsp;</TD>
	<TD>&nbsp;</TD>
	<TD>&nbsp;</TD>
	<TD>&nbsp;</TD>
	<TD>&nbsp;</TD>
	<TD>&nbsp;</TD>
	<TD>&nbsp;</TD>
	<TD>&nbsp;</TD>
	<TD>&nbsp;</TD>
	<TD colspan="8" align="center"><font color="#312623">Válida somente apresentação de identidade</font></TD>
  </TR>
  <TR>
	<TD>&nbsp;</TD>
	<TD>&nbsp;</TD>
	<TD>&nbsp;</TD>
	<TD>&nbsp;</TD>
	<TD>&nbsp;</TD>
	<TD>&nbsp;</TD>
	<TD>&nbsp;</TD>
	<TD>&nbsp;</TD>
	<TD>&nbsp;</TD>
	<TD>&nbsp;</TD>
	<TD colspan="8" align="center"><font color="#312623">Esta carteira é de uso pessoal e intransferível</font></TD>
  </TR>
  <TR>
	<TD>&nbsp;</TD>
	<TD>&nbsp;</TD>
	<TD>&nbsp;</TD>
	<TD>&nbsp;</TD>
	<TD>&nbsp;</TD>
	<TD>&nbsp;</TD>
	<TD>&nbsp;</TD>
	<TD>&nbsp;</TD>
	<TD>&nbsp;</TD>
	<TD>&nbsp;</TD>
	<TD>&nbsp;</TD>
	<TD>&nbsp;</TD>
	<TD>&nbsp;</TD>
	<TD>&nbsp;</TD>
	<TD>&nbsp;</TD>
	<TD>&nbsp;</TD>
	<TD>&nbsp;</TD>
	<TD>&nbsp;</TD>
  </TR>
 </TABLE>
 </div>
 </BODY>
</HTML>
<script language="JavaScript">
window.print();
</script>
