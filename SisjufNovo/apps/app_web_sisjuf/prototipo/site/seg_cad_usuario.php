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
			<h2>Usuário - Cadastro</h2>

			<a class="botao_senha" href="alterar_senha.php"><span>Alterar</span></a>
			
			<table class="tab_cadastro" cellpadding="2" cellspacing="1">
				<thead>
					<tr>
						<th>Dados do Usuário</th>
						<th colspan="2"></th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th>Nome do Usuário:</th>
						<td colspan="2"><input name="" type="text" size="35" /></td>
					</tr>
					<tr>
						<th>Login do Usuário:</th>
						<td colspan="2"><input name="" type="text" size="20" /></td>
					</tr>
					<tr>
					<!-- ATENÇÃO: O número desse rowspan deve ser dinâmico. Deve ser igual ao número de grupos relacionadas (tr) -->
						<th width="150" valign="top" rowspan="2">Grupos:</th>
						<td width="420">Administração</td>
						<td width="20" align="center"><a class="botao_excluir" title="Excluir" href="#"><span>excluir</span></a></td>
					</tr>
					<tr>
						<td>Associado</td>
						<td align="center"><a class="botao_excluir" title="Excluir" href="#"><span>excluir</span></a></td>
					</tr>
				</tbody>
			</table>
			
			<br />
			
			<table class="tab_cadastro" cellpadding="2" cellspacing="1">
				<thead>
					<tr>
						<th>Grupos</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th width="150">Grupos:</th>
						<td width="440">
							<select name="" style="float:left; margin-right:10px; ">
								<option></option>
								<option>Cadastrar Banco</option>
								<option>Excluir Banco</option>
								<option>Alterar Banco</option>
								<option>Efetuar Lançamento</option>
								<option>Estornar Lançamento</option>
								<option>Cadastrar Usuário</option>
								<option>Excluir Usuário</option>
								<option>Cadastrar Grupo</option>
								<option>Excluir Grupo</option>
							</select>
							
							<a class="botao_acima" title="Adicionar" href="#"><span>adicionar</span></a>
						</td>
					</tr>
				</tbody>
			</table>
			
			<br />
			<a class="botao_salvar" href="#"><span>salvar</span></a>
			
		</div>
	</div>

</body>
</html>
