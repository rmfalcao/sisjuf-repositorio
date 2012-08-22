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
			<h1>M�dulo Financeiro</h1>
			<h2>Origens de Lan�amentos</h2>
			<a class="botao_novo" href="financeiro_cad_origemlanc.php"><span>novo</span></a>
			<br /><br />
			
			<table class="tab_lista" cellpadding="2" cellspacing="1">
				<thead>
					<tr>
						<th width="570">Origens dos Lan�amentos</th>
						<th width="20"></th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td><a href="#">Xerox</a></td>
						<td align="center">
							<a class="botao_excluir" title="Excluir" href="#"><span>excluir</span></a>
						</td>
					</tr>
					<tr>
						<td><a href="#">Usu�rio</a></td>
						<td align="center">
							<a class="botao_excluir" title="Excluir" href="#"><span>excluir</span></a>
						</td>
					</tr>
					<tr>
						<td><a href="#">Locadora</a></td>
						<td align="center">
							<a class="botao_excluir" title="Excluir" href="#"><span>excluir</span></a>
						</td>
					</tr>
					<tr>
						<td><a href="#">Conv�nio</a></td>
						<td align="center">
							<a class="botao_excluir" title="Excluir" href="#"><span>excluir</span></a>
						</td>
					</tr>
					<tr>
						<td><a href="#">Associado</a></td>
						<td align="center">
							<a class="botao_excluir" title="Excluir" href="#"><span>excluir</span></a>
						</td>
					</tr>
					<tr>
						<th colspan="6">
							<a class="botao_imprimir" title="Imprimir" href="impressao_origens_lancamentos.htm" target="_blank"><span>imprimir</span></a>
						</th>
					</tr>
				</tbody>
			</table>
		</div>
	</div>

</body>
</html>
