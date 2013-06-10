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
			<h2>Movimentar valores entre Contas</h2>
			
			<table class="tab_cadastro" cellpadding="2" cellspacing="1">
				<thead>
					<tr>
						<th width="200">Dados da Movimentação</th>
						<th width="390"></th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th>Conta origem:</th>
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
						<th>Conta destino:</th>
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
						<th>Data da movimentação:</th>
						<td><input name="" type="text" size="12" /></td>
					</tr>
					<tr>					
						<th>Valor (R$):</th>
						<td><input type="text" size="12" name="" /></td>
					</tr>
				</tbody>
			</table>
			
			<br />
			<a class="botao_salvar" href="#"><span>salvar</span></a>
			
		</div>
	</div>

</body>
</html>
