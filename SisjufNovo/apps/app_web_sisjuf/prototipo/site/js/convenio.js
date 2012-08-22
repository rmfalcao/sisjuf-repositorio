// JavaScript Document

function stAba(menu,conteudo){
	this.menu = menu;
	this.conteudo = conteudo;
}

var arConvAbas = new Array();
arConvAbas[0] = new stAba('td_convenio','div_convenio');
arConvAbas[1] = new stAba('td_planos','div_planos');

function AlternarAbas(menu, conteudo){
	for (i=0;i<arConvAbas.length;i++){
		m = document.getElementById(arConvAbas[i].menu);
		m.className = 'menu';
		c = document.getElementById(arConvAbas[i].conteudo)
		c.style.display = 'none';
	}
	m = document.getElementById(menu)
	m.className = 'menu-sel';
	c = document.getElementById(conteudo)
	c.style.display = '';
}

function openModalPlanos(){
	document.getElementById("modal_planos").style.top = document.body.scrollTop;
	el = document.getElementById("modal_planos");
	el.style.visibility = (el.style.visibility == "visible") ? "hidden" : "visible";
}