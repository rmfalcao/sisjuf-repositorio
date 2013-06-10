<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>SISJUF - Sistema ASSERJUF</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="css/estilo.css" type="text/css" rel="stylesheet" />
<link rel="Shortcut Icon" type="image/png" href="imagens/icone.png">
</head>
<body>

	<div id="geral">
		<div id="topo">
			<div id="sair">
				sair <a href="index.htm"><img src="imagens/botao_x.gif" /></a>
			</div>
			<div id="voltar">
				voltar <a href="#"><img src="imagens/setinhavoltar.gif" /></a>
			</div>
		</div>

		<?php
		include("menu.htm");
		?>

		<div id="miolo">
			<h1>Módulo Financeiro</h1>
			<h2>Realizar Baixa</h2>
			
			<table class="tab_cadastro" cellpadding="2" cellspacing="1">
				<thead>
					<tr>
						<th>Realizar Baixa</th>
						<th colspan="2"></th>
					</tr>
				</thead>
				<tbody>
					<tr>					
						<th width="200">Valor total (R$):</th>
						<td width="20"><input type="radio" name="1" value="valor_total"></td>
						<td width="370"><input type="text" size="12" name="" value="120,50" /></td>
					</tr>
					<tr>
						<th>Valor Parcial (R$):</th>
						<td><input type="radio" name="1" value="valor_parcial"></td>
						<td><input type="text" name="" size="12" /></td>
					</tr>
				</tbody>
			</table>
			
			<br />
			<a class="botao_salvar" href="#"><span>salvar</span></a>
			
		</div>
	</div>

</body>
</html>
