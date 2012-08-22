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
			<h2>Saldo</h2>
			
			<table class="tab_cadastro" cellpadding="2" cellspacing="1">
				<thead>
					<tr>
						<th width="180">Filtro</th>
						<th width="410"></th>
					</tr>
				</thead>
				<tbody>
					<tr>					
						<th>Conta:</th>
						<td>
							<select>
								<option></option>
								<option>BB 0041-8/50250-2</option>
								<option>ITAÚ 0705/29627-5</option>
								<option>ITAÚ 1008/55669-0</option>
							</select>
						</td>
					</tr>
					<tr>
						<th>Valor (R$):</th>
						<td><input type="text" name="" size="12" /></td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>

</body>
</html>
