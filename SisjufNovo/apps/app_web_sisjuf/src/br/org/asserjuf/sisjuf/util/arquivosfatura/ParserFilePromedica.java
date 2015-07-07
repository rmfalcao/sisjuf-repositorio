
package br.org.asserjuf.sisjuf.util.arquivosfatura;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.com.falc.smartFW.exception.SmartAppException;
import br.org.asserjuf.sisjuf.associados.convenio.BeneficiarioVO;
import br.org.asserjuf.sisjuf.associados.convenio.ItemFaturaVO;
import br.org.asserjuf.sisjuf.associados.convenio.ItemFaturaVO;
import br.org.asserjuf.sisjuf.associados.convenio.VinculacaoPlanoVO;

public class ParserFilePromedica extends ParserFileAb{
	
	private static final int INICIO_UTIL_ARQUIVO = 10;
	
	public ParserFilePromedica(byte[] contentFile) throws IOException {
		super(contentFile);
	}
	
	public List<ItemFaturaVO> parserContentFileToItensFaturasList(String[] linhasArquivo) throws SmartAppException{
		List<ItemFaturaVO> listaIntensFaturas = new ArrayList<ItemFaturaVO>();
    	for(int i = INICIO_UTIL_ARQUIVO;i<linhasArquivo.length;i++){
        	String linha = linhasArquivo[i].trim();
        	System.out.println(linha);
        	if(linha.matches("[0-9]{6}")) {
        		ItemFaturaVO itemFatura = new ItemFaturaVO();
        		itemFatura.setVinculacao(new VinculacaoPlanoVO());
        		itemFatura.getVinculacao().setBeneficiario(new BeneficiarioVO());
        		try {
        			itemFatura.getVinculacao().getBeneficiario().setCodigo(new Integer(linha));
				} catch (NumberFormatException e) {
					throw new SmartAppException("O arquivo está com a formatação errada. Verifique se está validando o arquivo correto e tente novamente. Caso continue apresentando problemas, entre em contato com o convênio");
				}
        		listaIntensFaturas.add(itemFatura);
        	}else{
        		break;
        	}
        }
    	
    	int quantidadeRegistros = listaIntensFaturas.size();
		
		for(int indiceAtual=0;indiceAtual < listaIntensFaturas.size();indiceAtual++){
			ItemFaturaVO itemFatura = listaIntensFaturas.get(indiceAtual);
	    	int indicePonteiroArquivo = indiceAtual+quantidadeRegistros+INICIO_UTIL_ARQUIVO;
	    	String linha = linhasArquivo[indicePonteiroArquivo].trim();
//	    	associado.setDepartamento(linha);
//	    	
	    	indicePonteiroArquivo = calcularNovaPosicao(indicePonteiroArquivo, 1, quantidadeRegistros);
	    	linha = linhasArquivo[indicePonteiroArquivo].trim();
//	    	associado.setDataNascimento(linha);
//	    	
	    	indicePonteiroArquivo = calcularNovaPosicao(indicePonteiroArquivo, 1, quantidadeRegistros);
	    	linha = linhasArquivo[indicePonteiroArquivo].trim();
//	    	associado.setSexo(linha);
//	    	
	    	indicePonteiroArquivo = calcularNovaPosicao(indicePonteiroArquivo, 1, quantidadeRegistros);
	    	linha = linhasArquivo[indicePonteiroArquivo].trim();
	    	itemFatura.getVinculacao().getBeneficiario().setNome(linha);
//	    	
//	    	indicePonteiroArquivo = calcularNovaPosicao(indicePonteiroArquivo, 2, quantidadeRegistros);
//	    	linha = linhasArquivo[indicePonteiroArquivo].trim();
//	    	associado.setDataAdesao(linha);
		}
		return listaIntensFaturas;
	}
	
	private static int calcularNovaPosicao(int valorAtualPonteiro,int quantidadeSaltos,int quantidadeRegistros){
		if(quantidadeSaltos>0){
			valorAtualPonteiro = valorAtualPonteiro + (quantidadeRegistros*quantidadeSaltos);
		}
		return valorAtualPonteiro;
	}

}
