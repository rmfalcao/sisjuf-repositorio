/*
Ex:onkeyup='formatarCampoNumero(this)'
*/
function formatarCampoNumero(campo) {

  soNumero(campo);
  valor = campo.value;
  while(valor.indexOf(" ") >= 0) valor = valor.replace(" ", "");
  while(valor.indexOf(".") >= 0) valor = valor.replace(".", "");
  while(valor.indexOf(",") >= 0) valor = valor.replace(",", "");
  if (valor.length < 2) {
    campo.value = valor;
    return;
  }
  saida = valor.substring(0,1);
  valor = valor.substring(1, valor.length);
  while (valor.length - 2 > 0) {
    if ((valor.length - 2) % 3 == 0) {
      saida += ".";
    }
    saida += valor.substring(0,1);
    valor = valor.substring(1, valor.length);
  }
  saida += "," + valor;
  campo.value = saida;
}

/**
**/
function soNumero(campo) {

  var caracteresIvalidos = "1234567890";
  for (i = 0; i < campo.value.length; i++) {
    if (caracteresIvalidos.indexOf(campo.value.charAt(i)) == -1) {
      temp1 = campo.value.substr(0, i);
      temp2 = campo.value.substr(i + 1, campo.value.length);
      campo.value = temp1 + temp2;
      i = 0;
    }
  }
}


function Numero(e){

 /* Retorna apenas numero, backspace,tab,seta p/ esquerda e direita e delete
 chamada: return Numero(this.event)
   ---------------------------------------
 	Atualiza??o 200502171
 	Autor:		Rodrigo Falc?o
 	
 	- A fun??o retorna tamb?m ENTER.

 */

 	if (document.all) {                 // Internet Explorer
         var tecla = event.keyCode;
     }
 	else if(document.layers) {          // Nestcape
 		var tecla = e.which;
     }
     
	if(window.event.shiftKey || window.event.ctrlKey || window.event.altKey){
	
		return false;
	}
	



 	if ((window.event.keyCode >= 48 && window.event.keyCode <= 57) || (window.event.keyCode>=96 && window.event.keyCode<=105)||(window.event.keyCode == 37 || window.event.keyCode == 39)||(window.event.keyCode == 9 || window.event.keyCode == 46)){
 			return true;
 	}
 	else {
 	    if ((tecla == 8)||(tecla == 13)){        // backspace OU enter
 		    return true;
 		}
         return false
 	}
 }

function NumeroReal(e){

/*
retorna apenas numero, ponto(.),backspace,tab,seta p/ esquerda e direita e delete
propria para numeros reais apenas. nao serve para moeda por nao permitir a virgula(,)
chamada: return NumeroReal(this.event)
*/
	if (document.all) {                 // Internet Explorer
        var tecla = event.keyCode;
    }
	else if(document.layers) {          // Nestcape
		var tecla = e.which;
    }

	if ((window.event.keyCode >= 48 && window.event.keyCode <= 57) || (window.event.keyCode>=96 && window.event.keyCode<=105)||(window.event.keyCode == 37 || window.event.keyCode == 39)||(window.event.keyCode == 190 || window.event.keyCode == 9 || window.event.keyCode == 46 || window.event.keyCode == 194)){
			return true;
	}
	else {
	    if (tecla == 8){        // backspace
		    return true;
		}
        return false
	}
}
    
  function NumeroRealFormatado(campo,e,casaInteira){
  /*
  retorna apenas numero, ponto(.),backspace,tab,seta p/ esquerda e direita e delete
  propria para numeros reais apenas. nao serve para moeda por nao permitir a virgula(,)
  chamada: onkeyDown = " return NumeroRealFormatado(this,this.event,x) " 
  sendo x o numero de casas inteiras.
  
  Autora: Sem?ramis Assis
  */
      tecla = 0;
  	if (document.all) {                 // Internet Explorer
		tecla = event.keyCode;
    }
  	else if(document.layers) {          // Nestcape
        tecla = e.which;
      }
      
      
	if(window.event.shiftKey || window.event.ctrlKey || window.event.altKey){
		return false;
	}


      if(campo.value.length == casaInteira && tecla !=8 && campo.value.search(",")<0 && tecla != 188 && tecla != 110)
      	campo.value += ",";
      	
      if(campo.value.search(",")>0 && (tecla == 188 || tecla == 110))
      	return false;
      
      if(campo.value.search(",")== 0){
      	alert("V?rgula em local inadequado.");
      	campo.value = "";
      	return false;
      }
      
      
  	if ((tecla >= 48 && tecla <= 57) || (tecla>=96 && tecla<=105)||(tecla == 37 || tecla == 39)||( tecla == 9 || tecla == 46  || tecla == 110 || tecla == 188)){
  			return true;
  	}
  	else {
  	    if (tecla == 8){        // backspace
  		    return true;
  		}
          return false
  	}
}

function NumeroEVirgula(e){

/*
retorna apenas numero, virgula(,),backspace,tab,seta p/ esquerda e direita e delete
propria para numeros reais apenas. nao serve para moeda por nao permitir a virgula(,)
chamada: return NumeroReal(this.event)
*/
	if (document.all) {                 // Internet Explorer
        var tecla = event.keyCode;
    }
	else if(document.layers) {          // Nestcape
		var tecla = e.which;
    }

	if ((window.event.keyCode >= 48 && window.event.keyCode <= 57) || (window.event.keyCode>=96 && window.event.keyCode<=105)||(window.event.keyCode == 37 || window.event.keyCode == 39)||(window.event.keyCode == 190 || window.event.keyCode == 9 || window.event.keyCode == 188 || window.event.keyCode == 110 || window.event.keyCode == 194)){
			return true;
	}
	else {
	    if (tecla == 8){        // backspace
		    return true;
		}
        return false
	}
}

function NumeroInteiro(e){

/* Retorna apenas numero, backspace,tab,seta p/ esquerda e direita, delete, enter e menos(h?fen).
chamada: return NumeroInteiro(this.event)
  ---------------------------------------
	Data :      200502171
	Autor:		Rodrigo Falc?o
	

*/

	if (document.all) {                 // Internet Explorer
        var tecla = event.keyCode;
    }
	else if(document.layers) {          // Nestcape
		var tecla = e.which;
    }

	if ((window.event.keyCode >= 48 && window.event.keyCode <= 57) || (window.event.keyCode>=96 && window.event.keyCode<=105)||(window.event.keyCode == 37 || window.event.keyCode == 39)||(window.event.keyCode == 9 || window.event.keyCode == 46)){
			return true;
	}
	else {
	    if ((tecla == 8)||(tecla == 13)||(tecla == 45)){        // backspace OU enter OU menos (h?fen)
		    return true;
		}
        return false
	}
}


function Mascara(objeto, evt, mask) {
 
var LetrasU = 'ABCDEFGHIJKLMNOPQRSTUVWXYZ';
var LetrasL = 'abcdefghijklmnopqrstuvwxyz';
var Letras  = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz';
var Numeros = '0123456789';
var NumerosX = '0123456789Xx';
var Fixos  = '().-:/ ';
var Charset = " !\"#$%&\'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\]^_/`abcdefghijklmnopqrstuvwxyz{|}~";

evt = (evt) ? evt : (window.event) ? window.event : "";
var value = objeto.value;
if (evt) {
 var ntecla = (evt.which) ? evt.which : evt.keyCode;
 tecla = Charset.substr(ntecla - 32, 1);
 if (ntecla < 32) return true;

 var tamanho = value.length;
 if (tamanho >= mask.length) return false;

 var pos = mask.substr(tamanho,1);
 while (Fixos.indexOf(pos) != -1) {
  value += pos;
  tamanho = value.length;
  if (tamanho >= mask.length) return false;
  pos = mask.substr(tamanho,1);
 }

 switch (pos) {
   case '#' : if (Numeros.indexOf(tecla) == -1) return false; break;
   case 'A' : if (LetrasU.indexOf(tecla) == -1) return false; break;
   case 'a' : if (LetrasL.indexOf(tecla) == -1) return false; break;
   case 'Z' : if (Letras.indexOf(tecla) == -1) return false; break;
   case 'X' : if (NumerosX.indexOf(tecla) == -1) return false; break;
   case '*' : objeto.value = value; return true; break;
   default : return false; break;
 }
}
objeto.value = value;
return true;
}


function MaskCEP(objeto, evt) {
return Mascara(objeto, evt, '##.###-###');
}

function MaskTelefone(objeto, evt) {
return Mascara(objeto, evt, '(##) ####-####');
}

function MaskCelular(objeto, evt) {
return Mascara(objeto, evt, '(##) #####-####');
}

function MaskCPF(objeto, evt) {
return Mascara(objeto, evt, '###.###.###-##');
}

function MaskPlacaCarro(objeto, evt) {
return Mascara(objeto, evt, 'AAA-####');
}

function maskDate(objeto, evt) {
return Mascara(objeto, evt, '##/##/####');
}

function justNumber(objeto, evt) {
	return Mascara(objeto, evt, '#######################');
}

function justNumberX(objeto, evt) {
	return Mascara(objeto, evt, 'X');
}


function checkDate(input){
	var validformat=/^\d{2}\/\d{2}\/\d{4}$/ //Basic check for format validity
	var returnval=false
	
	if (input.value=='') {
		return;
	}
	
	if (!validformat.test(input.value)) {
		alert("Data inválida.")
		input.value = '';
		input.focus();
	}
	else{ //Detailed check for valid date ranges
	var dayfield=input.value.split("/")[0]
	var monthfield=input.value.split("/")[1]
	var yearfield=input.value.split("/")[2]
	var dayobj = new Date(yearfield, monthfield-1, dayfield)
	if ((dayobj.getMonth()+1!=monthfield)||(dayobj.getDate()!=dayfield)||(dayobj.getFullYear()!=yearfield)) {
		alert("Data inválida.")
		input.value = '';
		input.focus();
	}
	else
	returnval=true
	}
	if (returnval==false) input.select()
	return returnval
}