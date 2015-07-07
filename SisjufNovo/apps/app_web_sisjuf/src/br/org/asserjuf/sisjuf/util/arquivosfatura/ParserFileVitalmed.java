package br.org.asserjuf.sisjuf.util.arquivosfatura;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.org.asserjuf.sisjuf.associados.convenio.BeneficiarioVO;
import br.org.asserjuf.sisjuf.associados.convenio.ItemFaturaVO;
import br.org.asserjuf.sisjuf.associados.convenio.VinculacaoPlanoVO;

public class ParserFileVitalmed extends ParserFileAb{
	
	public ParserFileVitalmed(byte[] contentFile) throws IOException {
		super(contentFile);
	}

	public List<ItemFaturaVO> parserContentFileToItensFaturasList(String[] linhasArquivo) {                   
		List<ItemFaturaVO> listaItens = new ArrayList<ItemFaturaVO>();
		for(int i = 0;i<linhasArquivo.length;i++){
        	String linha = linhasArquivo[i].trim();
        	if(linha.startsWith("9")){
        		String linhaModificada = linha.replaceAll("  ","@").replaceAll(" ","").replaceAll("@@","#").replaceFirst("@","#").replaceAll("@"," ").replaceAll("##","");
	        	String[] conteudoLinha = linhaModificada.split("#");
	        	ItemFaturaVO associado = createAssociado(linhaModificada,conteudoLinha);	
	        	listaItens.add(associado);
        	}
        }
		return listaItens;
	}
	
	private ItemFaturaVO createAssociado(String linhaModificada,String[] conteudoLinha) {
		ItemFaturaVO itemFatura = new ItemFaturaVO();
		itemFatura.setVinculacao(new VinculacaoPlanoVO());
		itemFatura.getVinculacao().setBeneficiario(new BeneficiarioVO());
		itemFatura.getVinculacao().setCodigoBeneficiarioPlano(conteudoLinha[0].trim());
		try{
			itemFatura.getVinculacao().getBeneficiario().setNome(conteudoLinha[1].trim());
			itemFatura.getVinculacao().getBeneficiario().setTipoBeneficiario(conteudoLinha[2].trim().equals("T") ? "titular" : "dependente");
			itemFatura.setValor(new Double(conteudoLinha[4].trim().replaceAll(",", "\\.")));
		}catch(Exception ex){
			if(ex instanceof ArrayIndexOutOfBoundsException){
				conteudoLinha = linhaModificada.split("#");
				String nomeServidor = conteudoLinha[1];
				itemFatura.getVinculacao().getBeneficiario().setNome(nomeServidor.substring(0,nomeServidor.length()-1).trim());
				itemFatura.getVinculacao().getBeneficiario().setTipoBeneficiario(nomeServidor.substring(nomeServidor.length()-1).trim().equals("T") ? "titular" : "dependente");
				itemFatura.setValor(new Double(conteudoLinha[3].trim().replaceAll(",", "\\.")));
			}else{
				ex.printStackTrace();
			}
		}
		return itemFatura;
	}
}
