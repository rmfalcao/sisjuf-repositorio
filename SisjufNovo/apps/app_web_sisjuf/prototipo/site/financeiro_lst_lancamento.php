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
			<h2>Consultar Lançamentos</h2>
			<a class="botao_novo" href="financeiro_cad_lancamento.php"><span>novo</span></a>
			<br /><br />
			
			<table class="tab_cadastro" cellpadding="2" cellspacing="1">
				<thead>
					<tr>
						<th>Filtro</th>
						<th colspan="4"></th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th>Período do Lançamento:</th>
						<td colspan="4">
							<input name="dia_data1" type="text" size="12" />
							à
							<input name="dia_data1" type="text" size="12" />
						</td>
					</tr>
					<tr>
						<th width="180"></th>
						<td width="20"><input type="checkbox"></td>
						<td width="185">Efetivação</td>
						<td width="20"><input type="checkbox"></td>
						<td width="185">Previsão</td>
					</tr>
					<tr>
						<th>Conta:</th>
						<td colspan="4">
							<select>
								<option></option>
								<option>BB 0041-8/50250-2</option>
								<option>ITAÚ 0705/29627-5</option>
								<option>ITAÚ 1008/55669-0</option>
							</select>
						</td>
					</tr>
					<tr>
						<th>Origem do Lançamento:</th>
						<td colspan="4">
							<select>
								<option></option>
								<option>Usuário</option>
								<option>Xerox</option>
								<option>Convênio</option>
								<option>Associado</option>
							</select>
						</td>
					</tr>
					<tr>
						<th>Tipo do Lançamento:</th>
						<td colspan="4">
							<select>
								<option></option>
								<option>Impostos</option>
								<option>tarifas bancárias</option>
								<option>Materiais de escritório</option>
							</select>
						</td>
					</tr>
					<tr>
						<th>Tipo de Operação:</th>
						<td colspan="4">
							<select>
								<option></option>
								<option>Crédito</option>
								<option>Débito</option>
								<option>À creditar</option>
								<option>À debitar</option>
							</select>
						</td>
					</tr>
					<tr>
						<th>Valor:</th>
						<td colspan="4">
							de <input name="dia_data1" type="text" size="12" />
							à
							<input name="dia_data1" type="text" size="12" />
						</td>
					</tr>
					<tr>
						<th></th>
						<td><input type="checkbox"></td>
						<td colspan="3">Não quitados</td>
					</tr>
				</tbody>
			</table>
			<a class="botao_filtrar" href="#"><span>filtrar</span></a>
			
			<br /><br /><br /><br />
			
			<table class="tab_lista" cellpadding="2" cellspacing="1">
				<thead>
					<tr>
						<th width="60">Efetivação</th>
						<th width="60">Previsão</th>
						<th width="130">Conta</th>
						<th width="60">Operação</th>
						<th width="80">Origem</th>
						<th width="90">Lançado (R$)</th>
						<th width="70">Pago (R$)</th>
						<th width="20"></th>
						<th width="20"></th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>10/09/06</td>
						<td>15/10/06</td>
						<td>ITAÚ 0705/29627-5</td>
						<td>À creditar</td>
						<td>Usuário</td>
						<td>120,00</td>
						<td>100,00</td>
						<td align="center">
							<a class="botao_baixar" title="Dar baixa" href="financeiro_baixar.php"><span>excluir</span></a>
						</td>
						<td align="center">
							<a class="botao_excluir" title="Excluir" href="#"><span>excluir</span></a>
						</td>
					</tr>
					<tr>
						<td>12/09/06</td>
						<td>03/10/06</td>
						<td>ITAÚ 0705/29627-5</td>
						<td>Débito</td>
						<td>Xerox</td>
						<td>42,00</td>
						<td>42,00</td>
						<td align="center">
							<a class="botao_baixar" title="Dar baixa" href="financeiro_baixar.php"><span>excluir</span></a>
						</td>
						<td align="center">
							<a class="botao_excluir" title="Excluir" href="#"><span>excluir</span></a>
						</td>
					</tr>
					<tr>
						<td>29/08/06</td>
						<td>01/09/06</td>
						<td>ITAÚ 0705/29627-5</td>
						<td>Crédito</td>
						<td>Usuário</td>
						<td>55,00</td>
						<td>54,80</td>
						<td align="center">
							<a class="botao_baixar" title="Dar baixa" href="financeiro_baixar.php"><span>excluir</span></a>
						</td>
						<td align="center">
							<a class="botao_excluir" title="Excluir" href="#"><span>excluir</span></a>
						</td>
					</tr>
					<tr>
						<td>11/10/06</td>
						<td>06/10/06</td>
						<td>ITAÚ 0705/29627-5</td>
						<td>À creditar</td>
						<td>Locadora</td>
						<td>15,50</td>
						<td>15,00</td>
						<td align="center">
							<a class="botao_baixar" title="Dar baixa" href="financeiro_baixar.php"><span>excluir</span></a>
						</td>
						<td align="center">
							<a class="botao_excluir" title="Excluir" href="#"><span>excluir</span></a>
						</td>
					</tr>
				</tbody>
				<tfoot>
					<tr>
						<th colspan="4">
							<a class="botao_imprimir" title="Imprimir" href="impressao_lancamentos.htm" target="_blank"><span>imprimir</span></a>
						</th>
						<th>Valor Total:</th>
						<th>232,50</th>
						<th>187,00</th>
						<th></th>
						<th></th>
					</tr>
				</tfoot>
			</table>
		</div>
	</div>

</body>
</html>
