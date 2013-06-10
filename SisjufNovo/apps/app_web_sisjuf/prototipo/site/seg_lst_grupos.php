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
			<h1>Módulo Segurança</h1>
			<h2>Grupos</h2>
			<a class="botao_novo" href="seg_cad_grupo.php"><span>novo</span></a>
			<br /><br />
			
			<table class="tab_lista" cellpadding="2" cellspacing="1">
				<thead>
					<tr>
						<th width="570">Nome do Grupo</th>
						<th width="20"></th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td><a href="#">Administrador</a></td>
						<td align="center">
							<a class="botao_excluir" title="Excluir" href="#"><span>excluir</span></a>
						</td>
					</tr>
					<tr>
						<td><a href="#">Diretoria</a></td>
						<td align="center">
							<a class="botao_excluir" title="Excluir" href="#"><span>excluir</span></a>
						</td>
					</tr>
					<tr>
						<td><a href="#">Funcinários</a></td>
						<td align="center">
							<a class="botao_excluir" title="Excluir" href="#"><span>excluir</span></a>
						</td>
					</tr>
					<tr>
						<th colspan="4">
							<a class="botao_imprimir" title="Imprimir" href="#"><span>imprimir</span></a>
						</th>
					</tr>
				</tbody>
			</table>
		</div>
	</div>

</body>
</html>
