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
			<h2>Contas</h2>
			<a class="botao_novo" href="financeiro_cad_conta.php"><span>novo</span></a>
			<br /><br />
			
			<table class="tab_lista" cellpadding="2" cellspacing="1">
				<thead>
					<tr>
						<th width="120">Nome da Conta</th>
						<th width="110">Banco</th>
						<th width="70">Nº Agência</th>
						<th width="70">Nº Conta</th>
						<th width="200">Nome do Titular</th>
						<th width="20"></th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td><a href="#">Conta Reserva</a></td>
						<td>Banco do Brasil</td>
						<td>3466-2</td>
						<td>10188-5</td>
						<td>Antônio Jorge Andrade Junior</td>
						<td align="center">
							<a class="botao_excluir" title="Excluir" href="#"><span>excluir</span></a>
						</td>
					</tr>
					<tr>
						<td><a href="#">Urgências</a></td>
						<td>Unibanco</td>
						<td>1077-0</td>
						<td>20147-3</td>
						<td>Luis Cláudio Almeida</td>
						<td align="center">
							<a class="botao_excluir" title="Excluir" href="#"><span>excluir</span></a>
						</td>
					</tr>
					<tr>
						<th colspan="6">
							<a class="botao_imprimir" title="Imprimir" href="impressao_contas.htm" target="_blank"><span>imprimir</span></a>
						</th>
					</tr>
				</tbody>
			</table>
		</div>
	</div>

</body>
</html>
