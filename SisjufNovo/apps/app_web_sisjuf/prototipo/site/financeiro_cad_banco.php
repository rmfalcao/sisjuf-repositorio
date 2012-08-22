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
			<h2>Banco - Cadastro</h2>

			<table class="tab_cadastro" cellpadding="2" cellspacing="1">
				<thead>
					<tr>
						<th>Dados do Banco</th>
						<th colspan="3"></th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th width="150">Nome:</th>
						<td width="200"><input name="nome_banco" type="text" size="30" maxlength="30" /></td>
						<th width="140">Número:</th>
						<td width="100"><input name="numero_banco" type="text" size="5" maxlength="3" /></td>
					</tr>
					<tr>
						<th>Sigla:</th>
						<td colspan="3"><input name="sigla_banco" type="text" size="10" maxlength="6" /></td>
					</tr>
				</tbody>
			</table>
			
			<br />
			<a class="botao_salvar" href="#"><span>salvar</span></a>
			
		</div>
	</div>

</body>
</html>
