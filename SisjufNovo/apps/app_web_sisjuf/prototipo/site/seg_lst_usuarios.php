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
			<h2>Usuários</h2>
			<a class="botao_novo" href="seg_cad_usuario.php"><span>novo</span></a>
			<br /><br />
			
			<table class="tab_cadastro" cellpadding="2" cellspacing="1">
				<thead>
					<tr>
						<th>Filtro</th>
						<th colspan="3"></th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th width="130">Nome do Usuário:</th>
						<td width="230"><input name="" type="text" size="35" /></td>
						<th width="120">Login do Usuário:</th>
						<td width="110"><input name="" type="text" size="20" /></td>
					</tr>
					<tr>
						<th>Grupo:</th>
						<td colspan="3">
							<select>
								<option></option>
								<option>Administrador</option>
								<option>Associado</option>
								<option>Diretoria</option>
								<option>Funcionário</option>
							</select>
						</td>
					</tr>
				</tbody>
			</table>
			<a class="botao_filtrar" href="#"><span>filtrar</span></a>
			
			<br /><br />
			
			<table class="tab_lista" cellpadding="2" cellspacing="1">
				<thead>
					<tr>
						<th width="360">Nome do Usuário</th>
						<th width="210">Login do Usuário</th>
						<th width="20"></th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td><a href="#">Antônio de Andrade Costa</a></td>
						<td>acosta</td>
						<td align="center">
							<a class="botao_excluir" title="Excluir" href="#"><span>excluir</span></a>
						</td>
					</tr>
					<tr>
						<td><a href="#">Raphaelly Farini</a></td>
						<td>raphaelly</td>
						<td align="center">
							<a class="botao_excluir" title="Excluir" href="#"><span>excluir</span></a>
						</td>
					</tr>
					<tr>
						<td><a href="#">Gilmar Gomes do Nascimento Neto</a></td>
						<td>gilneto</td>
						<td align="center">
							<a class="botao_excluir" title="Excluir" href="#"><span>excluir</span></a>
						</td>
					</tr>
					<tr>
						<th colspan="3">
							<a class="botao_imprimir" title="Imprimir" href="#"><span>imprimir</span></a>
						</th>
					</tr>
				</tbody>
			</table>
		</div>
	</div>

</body>
</html>
