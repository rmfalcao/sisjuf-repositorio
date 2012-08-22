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
			<h1>M�dulo Seguran�a</h1>
			<h2>Grupo - Cadastro</h2>

			<table class="tab_cadastro" cellpadding="2" cellspacing="1">
				<thead>
					<tr>
						<th>Dados do Grupo</th>
						<th colspan="2"></th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th>Nome do Grupo:</th>
						<td colspan="2"><input name="nome_conta" type="text" size="30" /></td>
					</tr>
					<tr>
					<!-- ATEN��O: O n�mero desse rowspan deve ser din�mico. Deve ser igual ao n�mero de fun��es relacionadas (tr) -->
						<th width="150" valign="top" rowspan="3">Fun��es:</th>
						<td width="420">Cadastrar Banco</td>
						<td width="20" align="center"><a class="botao_excluir" title="Excluir" href="#"><span>excluir</span></a></td>
					</tr>
					<tr>
						<td>Alterar Banco</td>
						<td align="center"><a class="botao_excluir" title="Excluir" href="#"><span>excluir</span></a></td>
					</tr>
					<tr>
						<td>Excluir Banco</td>
						<td align="center"><a class="botao_excluir" title="Excluir" href="#"><span>excluir</span></a></td>
					</tr>
				</tbody>
			</table>
			
			<table class="tab_cadastro" cellpadding="2" cellspacing="1">
				<thead>
					<tr>
						<th>Fun��es do Grupo</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th width="150">Fun��o:</th>
						<td width="440">
							<select name="" style="float:left; margin-right:10px; ">
								<option></option>
								<option>Cadastrar Banco</option>
								<option>Excluir Banco</option>
								<option>Alterar Banco</option>
								<option>Efetuar Lan�amento</option>
								<option>Estornar Lan�amento</option>
								<option>Cadastrar Usu�rio</option>
								<option>Excluir Usu�rio</option>
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
