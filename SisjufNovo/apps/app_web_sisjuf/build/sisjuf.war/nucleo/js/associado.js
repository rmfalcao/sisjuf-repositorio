function carregarPagina(){
	if (document.getElementById('statusCategoria').value == "C"){
		document.getElementById('contribuinteCpf').disabled = true;
	}else if (document.getElementById('statusCategoria').value == "U"){
		document.forms[1].contribuinteCpf.readonly= false;
	}
	if (document.getElementById('associadoForm:codigo').value != ""){
		document.getElementById('associadoBeneficiarioSubView:associadoBeneficiarioForm:beneficiarioAssociadoCodigo').value = document.getElementById('associadoForm:codigo').value;
	}
}

dependenteTR = [function(dependente){return dependente.nome}, function(dependente){return dependente.parentesco.nome}, 
	function(dependente){
		linha = "<a class='botao_excluir' href=\"javascript:removerDependente('"+ dependente.rg +"');\"><span>excluir</span></a>";
		linha += "<input type='hidden' name='dp_codigo' value='"+dependente.codigo+"'/>";
		linha += "<input type='hidden' name='dp_nome' value='"+dependente.nome+"'/>";
		linha += "<input type='hidden' name='dp_rg' value='"+dependente.rg+"'/>";
		linha += "<input type='hidden' name='dp_dataNascimento' value='"+dependente.dataNascimento+"'/>";
		linha += "<input type='hidden' name='dp_sexo' value='"+dependente.sexo+"'/>";
		linha += "<input type='hidden' name='dp_parentesco' value='"+dependente.parentesco.codigo+"'/>";
		linha += "<input type='hidden' name='dp_cpf' value='"+dependente.cpf+"'/>";
		return linha
	}
];

filhoTR = [function(filho){return filho.nome}, function(filho){return filho.dataNascimento}, function(filho){return filho.sexo},
	function(filho){
		linha = "<a class='botao_excluir' href=\"javascript:removerFilho('"+ filho.nome +"');\" ><span>excluir</span></a>";
		linha += "<input type='hidden' name='filho_codigo' value='"+filho.codigo+"'/>";
		linha += "<input type='hidden' name='filho_nome' value='"+filho.nome+"'/>";
		linha += "<input type='hidden' name='filho_dataNascimento' value='"+filho.dataNascimento+"'/>";
		linha += "<input type='hidden' name='filho_sexo' value='"+filho.sexo+"'/>";
		linha += "<input type='hidden' name='filho_cpf' value='"+filho.cpf+"'/>";
		return linha
	}
];

function incluirDependente(){
	var form = document.forms[1];
	if (form.nomeDependente.value != ""){
		if (form.cpfDependente.value != ""){
			if (form.rgDependente.value != ""){
				if (form.elements["associadoForm:dataNascimentoDependenteInputDate"].value != ""){
					if (form.sexoDependente.value != ""){
						if (form.parentesco.value != ""){
							dependente = {
								codigo:'',
								nome:form.nomeDependente.value, 
								rg:form.rgDependente.value, 
								dataNascimento:form.elements["associadoForm:dataNascimentoDependenteInputDate"].value, 
								sexo:form.sexoDependente.value,
								cpf:form.cpfDependente.value,
								parentesco:{
									nome:form.parentesco.options[form.parentesco.selectedIndex].text, 
									codigo:form.parentesco.value
								}
							};
							
							DWRUtil.removeAllRows('tb_parentesco');
							dependenteArray[dependenteArray.length] = dependente;
							
							DWRUtil.addRows('tb_parentesco', dependenteArray, dependenteTR,
								{
								rowCreator:function(options)
								{
									var row = document.createElement("tr");
									return row;
								}
								}
							);
							form.nomeDependente.value = "";
							form.rgDependente.value = "";
							form.elements["associadoForm:dataNascimentoDependenteInputDate"].value = "";
							form.sexoDependente.value = "";
							form.parentesco.value = "";
							form.cpfDependente.value = "";
						}else{alert("O parentesco do dependente não pode ser vazio.");}
					}else{alert("O sexo do dependente não pode ser vazio.");}
				}else{alert("A data de nascimento do dependente não pode ser vazio.");}
			}else{alert("O rg do dependente não pode ser vazio.");}
		}else{alert("O CPF do dependente não pode ser vazio.");}	
	}else{alert("O nome do dependente não pode ser vazio.");}
}

function incluirFilho(){
	var form = document.forms[1];
	if (form.nomeFilho.value != ""){
		if (form.elements["associadoForm:dataNascimentoFilhoInputDate"].value != ""){
			if (form.sexoFilho.value != ""){
				filho = {
					codigo:'', 
					nome:form.nomeFilho.value, 
					dataNascimento:form.elements["associadoForm:dataNascimentoFilhoInputDate"].value, 
					sexo:form.sexoFilho.value,
					cpf:form.cpfFilho.value
				};
				DWRUtil.removeAllRows('tb_filho');
				filhoArray[filhoArray.length] = filho;
				
				DWRUtil.addRows('tb_filho', filhoArray, filhoTR,
					{
					rowCreator:function(options)
					{
						var row = document.createElement("tr");
						return row;
					}
					}
				);
				form.nomeFilho.value = "";
				form.elements["associadoForm:dataNascimentoFilhoInputDate"].value = "";
				form.sexoFilho.value = "";
				form.cpfFilho.value = "";
			}else{alert("O Sexo do filho não pode ser vazio");}
		}else{alert("A data nascimento do filho não pode ser vazia");}
	}else{alert("O nome do filho não pode ser vazio");}
}

function removerDependente(str){
	objArray = new Array();
	x = 0;
	for (i = 0; i < dependenteArray.length; i++)
	{
		obj = dependenteArray[i];
		
		if (obj.rg != str)
		{
			objArray[x] = obj;
			x++
		}
	}
	DWRUtil.removeAllRows('tb_parentesco');
	DWRUtil.addRows('tb_parentesco', objArray, dependenteTR,
		{
			rowCreator:function(options)
			{
				var row = document.createElement("tr");
				return row;
			}
		}
	);
	dependenteArray = objArray;
}

function removerFilho(str){
	objArray = new Array();
	x = 0;
	for (i = 0; i < filhoArray.length; i++)
	{
		obj = filhoArray[i];
		
		if (obj.nome != str)
		{
			objArray[x] = obj;
			x++
		}
	}
	DWRUtil.removeAllRows('tb_filho');
	DWRUtil.addRows('tb_filho', objArray, filhoTR,
		{
			rowCreator:function(options)
			{
				var row = document.createElement("tr");
				return row;
			}
		}
	);
	filhoArray = objArray;
}

function buscarAssociadoPorCpf(){
	if (document.forms[1].contribuinteCpf.value != ""){
		cpf = document.forms[1].contribuinteCpf.value;
		var associadoFiltro = {codigo:null, nome:null, cpf:cpf};
		try{
			associadoBean.findAssociadoByCpf(cpf, carregaAssociado);
		}catch(e){
			alert(e);
		}
	}else {
		alert ("Preencha o cpf do contribuinte para a busca");
	}
}

function carregaAssociado(associado){
	if (associado != null){
		document.forms[1].contribuinteCodigo.value = associado.codigo;
		document.forms[1].contribuinteNome.value = associado.nome;
	}else {
		alert("Contribuinte não encontrado");
		document.forms[1].contribuinteCodigo.value = "";
		document.forms[1].contribuinteNome.value = "";
	}
}

function setStatusCategoria(){
	if (document.forms[1].statusCategoria.value == "C"){
		document.forms[1].contribuinteCpf.value = "";
		document.forms[1].contribuinteCpf.disabled = true;
		document.forms[1].contribuinteCodigo.value = "";
		document.forms[1].contribuinteNome.value = "";
	}else if (document.forms[1].statusCategoria.value == "U"){
		document.forms[1].contribuinteCpf.disabled= false;
	}
}