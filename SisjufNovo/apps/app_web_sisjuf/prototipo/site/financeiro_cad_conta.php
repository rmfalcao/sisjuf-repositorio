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
			<h2>Conta - Cadastro</h2>

			<table class="tab_cadastro" cellpadding="2" cellspacing="1">
				<thead>
					<tr>
						<th>Dados da Conta</th>
						<th colspan="3"></th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th>Nome da Conta:</th>
						<td colspan="2"><input name="nome_conta" type="text" size="30" /></td>
						<td><input name="contacaixa" type="checkbox"> Conta Caixa</td>
					</tr>
					<tr>
						<th>Banco:</th>
						<td colspan="3">
							<select name="banco">
								<option></option>
								<option>Banco do Brasil</option>
								<option>Bradesco</option>
								<option>Banco Real</option>
							</select>
						</td>
					</tr>
					<tr>
						<th width="130">Nº Agência:</th>
						<td width="190">
							<input name="numero_agencia" type="text" size="5" maxlength="5"> - 
							<input name="digito_agencia" type="text" size="1" maxlength="1">
						</td>
						<th width="130">Nº Conta:</th>
						<td width="140">
							<input name="numero_conta" type="text" size="5" maxlength="5"> - 
							<input name="digito_conta" type="text" size="1" maxlength="1">
						</td>
					</tr>
					<tr>
						<th>Tipo da Conta:</th>
						<td>
							<select name="ipo_conta">
								<option></option>
								<option>Conta Corrente</option>
								<option>Conta Poupança</option>
							</select>
						</td>
						<th>Cod. Operação:</th>
						<td><input name="cod_operacao" type="text" size="5" maxlength="10"></td>
					</tr>
					<tr>
						<th>Titular da Conta:</th>
						<td><input name="titular_conta" type="text" size="30" /></td>
						<th>CPF/CNPJ do Titular:</th>
						<td><input name="cpf_cnpj" type="text" size="10" maxlength="15" /></td>
					</tr>
				</tbody>
			</table>
			
			<br />
			<a class="botao_salvar" href="#"><span>salvar</span></a>
			
		</div>
	</div>

</body>
</html>
