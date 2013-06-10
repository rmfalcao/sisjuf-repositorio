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
			<h2>Extrato</h2>
			
			<table class="tab_cadastro" cellpadding="2" cellspacing="1">
				<thead>
					<tr>
						<th>Filtro</th>
						<th colspan="4"></th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th>Período:</th>
						<td colspan="4">
							<input name="" type="text" size="12" />
							à
							<input name="" type="text" size="12" />
						</td>
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
				</tbody>
			</table>
			<a class="botao_filtrar" href="#"><span>filtrar</span></a>
			
			<br /><br /><br /><br />
			
			<table class="tab_lista" cellpadding="2" cellspacing="1">
				<thead>
					<tr>
						<th width="100">Data</th>
						<th width="280">Tipo / Origem</th>
						<th width="120">Operação</th>
						<th width="90">Valor (R$)</th>
					</tr>
				</thead>
				
				<tbody>
					<tr>
						<td></td>
						<td></td>
						<td><strong>Saldo inicial</strong></td>
						<td><strong>100,00</strong></td>
					</tr>
					<tr>
						<td>10/09/06</td>
						<td>Tarifas Bancárias</td>
						<td>DBT</td>
						<td class="valor_negativo">10,40</td>
					</tr>
					<tr>
						<td>11/09/06</td>
						<td>Locadora</td>
						<td>CRD</td>
						<td>150,00</td>
					</tr>
					<tr>
						<td>12/09/06</td>
						<td>Pagamento Internet</td>
						<td>DBT</td>
						<td class="valor_negativo">230,00</td>
					</tr>
					<tr>
						<td>13/09/06</td>
						<td>Associado</td>
						<td>CRD</td>
						<td>55,00</td>
					</tr>
					<tr>
						<td>13/09/06</td>
						<td>Associado</td>
						<td>CRD</td>
						<td>80,00</td>
					</tr>
				</tbody>
				
				<tfoot>
					<tr>
						<th colspan="2">
							<a class="botao_imprimir" title="Imprimir" href="impressao_extrato.htm" target="_blank">
								<span>imprimir</span>
							</a>
						</th>
						<th>Saldo final</th>
						<th class="valor_negativo">1,90</th>
					</tr>
				</tfoot>
			</table>
		</div>
	</div>

</body>
</html>
