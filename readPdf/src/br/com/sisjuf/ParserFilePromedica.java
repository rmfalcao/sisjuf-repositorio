
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
	public List<AssociadoModel> parserContentFileToAssociadosList(String[] linhasArquivo) {
		List<AssociadoModel> listaAssociados = new ArrayList<AssociadoModel>();
    	for(int i = INICIO_UTIL_ARQUIVO;i<linhasArquivo.length;i++){
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
		
		for(int indiceAtual=0;indiceAtual<listaAssociados.size();indiceAtual++){
	    	AssociadoModel associado = listaAssociados.get(indiceAtual);
	    	int indicePonteiroArquivo = indiceAtual+quantidadeRegistros+INICIO_UTIL_ARQUIVO;
	    	String linha = linhasArquivo[indicePonteiroArquivo].trim();
	    	associado.setDepartamento(linha);
	    	
	    	indicePonteiroArquivo = calcularNovaPosicao(indicePonteiroArquivo, 1, quantidadeRegistros);
	    	linha = linhasArquivo[indicePonteiroArquivo].trim();
	    	associado.setDataNascimento(linha);
	    	
	    	indicePonteiroArquivo = calcularNovaPosicao(indicePonteiroArquivo, 1, quantidadeRegistros);
	    	linha = linhasArquivo[indicePonteiroArquivo].trim();
	    	associado.setSexo(linha);
	    	
	    	indicePonteiroArquivo = calcularNovaPosicao(indicePonteiroArquivo, 1, quantidadeRegistros);
	    	linha = linhasArquivo[indicePonteiroArquivo].trim();
	    	associado.setNomeServidor(linha);
	    	
	    	indicePonteiroArquivo = calcularNovaPosicao(indicePonteiroArquivo, 2, quantidadeRegistros);
	    	linha = linhasArquivo[indicePonteiroArquivo].trim();
	    	associado.setDataAdesao(linha);
		}
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
