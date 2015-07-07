
package br.com.sisjuf;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ParserFilePromedica extends ParserFileAb{
	
	private static final int INICIO_UTIL_ARQUIVO = 10;
	
	public ParserFilePromedica(String pathFile) throws IOException {
		super(pathFile);
	}
	
	@Override
	public List<AssociadoModel> parserContentFileToAssociadosList(String[] linhasArquivo) 
	{
		List<AssociadoModel> listaAssociados = new ArrayList<AssociadoModel>();
    	for(int i = INICIO_UTIL_ARQUIVO; i < linhasArquivo.length;i++){
        	String linha = linhasArquivo[i].trim();
        	System.out.println(linha);
        	if(linha.matches("[0-9]{6}")) {
        		AssociadoModel associado = new AssociadoModel();
        		associado.setCodigo(linha);
    			listaAssociados.add(associado);
        	}else{
        		break;
        	}
        }
    	
    	int quantidadeRegistros = listaAssociados.size();
		boolean quebrouLinhdaDTNasc = false;
		for(int indiceAtual=0;indiceAtual < listaAssociados.size();indiceAtual++){
	    	AssociadoModel associado = listaAssociados.get(indiceAtual);
	    	int indicePonteiroArquivo = indiceAtual+quantidadeRegistros+INICIO_UTIL_ARQUIVO;
	    	String linha = linhasArquivo[indicePonteiroArquivo].trim();
	    	associado.setDepartamento(linha);
	    	
	    	indicePonteiroArquivo = calcularNovaPosicao(indicePonteiroArquivo, 1, quantidadeRegistros);
	    	linha = linhasArquivo[indicePonteiroArquivo].trim();
	    	associado.setDataNascimento(linha);
	    	
	    	
	    	indicePonteiroArquivo = calcularNovaPosicao(indicePonteiroArquivo, 1, quantidadeRegistros);
	    	linha = linhasArquivo[indicePonteiroArquivo].trim();
	    	if (!"F".equals(linha) && !"M".equals(linha) || quebrouLinhdaDTNasc){
	    		indicePonteiroArquivo++;
	    		linha = linhasArquivo[indicePonteiroArquivo].trim();
	    		quebrouLinhdaDTNasc = true;
	    	}
	    	associado.setSexo(linha);
	    	
//	    	indicePonteiroArquivo = calcularNovaPosicao(indicePonteiroArquivo, 2, quantidadeRegistros);
//	    	linha = linhasArquivo[indicePonteiroArquivo].trim();
//	    	associado.setDataAdesao(linha);
	    	
	    	indicePonteiroArquivo = calcularNovaPosicao(indicePonteiroArquivo, 7, quantidadeRegistros);
		}
	    
		quebrouLinhdaDTNasc = false;
		boolean quebrouLinhdaDTNome = false;
		int qtdVezesQuebrouNome = 0;
		for(int indiceAtual=0;indiceAtual < listaAssociados.size();indiceAtual++){
			
			if (indiceAtual == 0){
				System.out.println("--------------------------------------------------------------------------------------------");
			}
			
	    	AssociadoModel associado = listaAssociados.get(indiceAtual);
	    	
	    	int indicePonteiroArquivo = indiceAtual+quantidadeRegistros+INICIO_UTIL_ARQUIVO;
	    	indicePonteiroArquivo = calcularNovaPosicao(indicePonteiroArquivo, 1, quantidadeRegistros);
	    	indicePonteiroArquivo = calcularNovaPosicao(indicePonteiroArquivo, 1, quantidadeRegistros);
	    	String linha = linhasArquivo[indicePonteiroArquivo].trim();
	    	if (!"F".equals(linha) && !"M".equals(linha) || quebrouLinhdaDTNasc){
	    		indicePonteiroArquivo++;
	    		quebrouLinhdaDTNasc = true;
	    	}
	    	indicePonteiroArquivo = calcularNovaPosicao(indicePonteiroArquivo, 1, quantidadeRegistros);
	    	
	    	//indiceAtual+quantidadeRegistros+INICIO_UTIL_ARQUIVO;
//	    	indicePonteiroArquivo = calcularNovaPosicao(indicePonteiroArquivo, 1, quantidadeRegistros);
	    	
	    	if (qtdVezesQuebrouNome > 0){
    			indicePonteiroArquivo = qtdVezesQuebrouNome + indicePonteiroArquivo;
    		}
	    	linha = linhasArquivo[indicePonteiroArquivo].trim();
	    	if (linha.length() > 10){
	    		associado.setNomeServidor(linha);
	    	}else{
	    		indicePonteiroArquivo++;
	    		linha = linhasArquivo[indicePonteiroArquivo].trim();
		    	associado.setNomeServidor(linha);
		    	qtdVezesQuebrouNome++;
	    	}
	    	System.out.println(linha);
		}
	    	
//	    	linha = linhasArquivo[indicePonteiroArquivo].trim();
//	    	System.out.println(">>>>>>>>>>>>>" + linha);
		return listaAssociados;
	}
	
	public static void main(String[] args) {
		int i = calcularNovaPosicao(40,1,30);
		System.out.println(i);
		i = calcularNovaPosicao(i,1,30);
		System.out.println(i);
		i = calcularNovaPosicao(i,1,30);
		System.out.println(i);
		i = calcularNovaPosicao(i,1,30);
		System.out.println(i);
	}
	
	
	private static int calcularNovaPosicao(int valorAtualPonteiro,int quantidadeSaltos,int quantidadeRegistros){
		if(quantidadeSaltos>0){
			valorAtualPonteiro = valorAtualPonteiro + (quantidadeRegistros*quantidadeSaltos);
		}
		return valorAtualPonteiro;
	}
}
