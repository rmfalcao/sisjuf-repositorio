
package br.org.asserjuf.sisjuf.util.arquivosfatura;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.org.asserjuf.sisjuf.associados.convenio.BeneficiarioVO;

public class ParserFilePromedica extends ParserFileAb{
	
	private static final int INICIO_UTIL_ARQUIVO = 10;
	
	public ParserFilePromedica(byte[] contentFile) throws IOException {
		super(contentFile);
	}
	
	public List<BeneficiarioVO> parserContentFileToBeneficiariosList(String[] linhasArquivo) {
		List<BeneficiarioVO> listaAssociados = new ArrayList<BeneficiarioVO>();
//    	for(int i = INICIO_UTIL_ARQUIVO;i<linhasArquivo.length;i++){
//        	String linha = linhasArquivo[i].trim();
//        	System.out.println(linha);
//        	if(linha.matches("[0-9]{6}")) {
//        		BeneficiarioVO associado = new BeneficiarioVO();
//        		associado.setCodigo(linha);
//    			listaAssociados.add(associado);
//        	}else{
//        		break;
//        	}
//        }
//    	
//    	int quantidadeRegistros = listaAssociados.size();
//		
//		for(int indiceAtual=0;indiceAtual<listaAssociados.size();indiceAtual++){
//			BeneficiarioVO associado = listaAssociados.get(indiceAtual);
//	    	int indicePonteiroArquivo = indiceAtual+quantidadeRegistros+INICIO_UTIL_ARQUIVO;
//	    	String linha = linhasArquivo[indicePonteiroArquivo].trim();
//	    	associado.setDepartamento(linha);
//	    	
//	    	indicePonteiroArquivo = calcularNovaPosicao(indicePonteiroArquivo, 1, quantidadeRegistros);
//	    	linha = linhasArquivo[indicePonteiroArquivo].trim();
//	    	associado.setDataNascimento(linha);
//	    	
//	    	indicePonteiroArquivo = calcularNovaPosicao(indicePonteiroArquivo, 1, quantidadeRegistros);
//	    	linha = linhasArquivo[indicePonteiroArquivo].trim();
//	    	associado.setSexo(linha);
//	    	
//	    	indicePonteiroArquivo = calcularNovaPosicao(indicePonteiroArquivo, 1, quantidadeRegistros);
//	    	linha = linhasArquivo[indicePonteiroArquivo].trim();
//	    	associado.setNomeServidor(linha);
//	    	
//	    	indicePonteiroArquivo = calcularNovaPosicao(indicePonteiroArquivo, 2, quantidadeRegistros);
//	    	linha = linhasArquivo[indicePonteiroArquivo].trim();
//	    	associado.setDataAdesao(linha);
//		}
		return listaAssociados;
	}

}
