package br.org.asserjuf.sisjuf.util.arquivos;

import java.util.ArrayList;
import java.util.List;

import br.com.falc.smartFW.exception.SmartAppException;
import br.org.asserjuf.sisjuf.associados.convenio.BeneficiarioVO;
import br.org.asserjuf.sisjuf.associados.convenio.ItemFaturaVO;
import br.org.asserjuf.sisjuf.associados.convenio.VinculacaoPlanoVO;

public class ParserPdfFaturaFileVitamed extends ParserPdfFaturaFileAb {
	private static String prefixoNaFaturaDoCodigoBeneficiarioVitalmedSSA = "9 0 0 6 5"; // com espacos porque no PDF da fatura há espacos.
	private static String prefixoNaFaturaDoCodigoBeneficiarioVitalmedFeiraDeSantana = "7"; 
	
	public ParserPdfFaturaFileVitamed(String path) {
		super(path);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected List<ItemFaturaVO> createItemFromLines(String[] linhasArquivo) throws Exception {
		List<ItemFaturaVO> listaItens = new ArrayList<ItemFaturaVO>();
		for(int i = 0;i<linhasArquivo.length;i++){
        	String linha = linhasArquivo[i].trim();
        	if(linha.startsWith(prefixoNaFaturaDoCodigoBeneficiarioVitalmedSSA) || linha.startsWith(prefixoNaFaturaDoCodigoBeneficiarioVitalmedFeiraDeSantana)) { //9 indica que é uma linha com um número de matricula na Vitalmed.
        		String linhaModificada = linha.replaceAll("  ","@").trim();
        		linhaModificada = linhaModificada.replaceAll("@@","#").trim();
        		linhaModificada = linhaModificada.replaceAll(" ","").trim();
        		linhaModificada = linhaModificada.replaceFirst("@","#").trim();
        		linhaModificada = linhaModificada.replaceAll("@"," ").trim();
        		linhaModificada = linhaModificada.replaceAll("##","#").trim();
        		linhaModificada = linhaModificada.replaceAll("##","#").trim();
        		linhaModificada = linhaModificada.replaceAll("##","#").trim(); // adicionado pois em caso de nomes muito curtos, ainda restavam ocorrências de ##.
        		
	        	String[] conteudoLinha = linhaModificada.split("#");
	        	ItemFaturaVO associado = createAssociado(linha,conteudoLinha);
	        	listaItens.add(associado);
        	}
        }
		return listaItens;
	}
	
	private ItemFaturaVO createAssociado(String linhaOriginal,String[] conteudoLinha) throws Exception {
		ItemFaturaVO itemFatura = new ItemFaturaVO();
		itemFatura.setVinculacao(new VinculacaoPlanoVO());
		itemFatura.getVinculacao().setBeneficiario(new BeneficiarioVO());
		itemFatura.getVinculacao().setCodigoBeneficiarioPlano(conteudoLinha[0].trim());

		
		try{
			itemFatura.getVinculacao().getBeneficiario().setNome(conteudoLinha[1].trim());
			itemFatura.getVinculacao().getBeneficiario().setTipoBeneficiario(conteudoLinha[2].trim().equals("T") ? "T" : "D");
			itemFatura.setValor(new Double(conteudoLinha[4].trim().replaceAll(",", "\\.")));
			
		}catch(Exception ex){
			if(ex instanceof ArrayIndexOutOfBoundsException){
				if(conteudoLinha.length == 4){
					String nomeServidor = conteudoLinha[1];
					itemFatura.getVinculacao().getBeneficiario().setNome(nomeServidor);
					String tipoBeneficiario = Character.toString(conteudoLinha[1].charAt(0));
					itemFatura.getVinculacao().getBeneficiario().setTipoBeneficiario(tipoBeneficiario);
					itemFatura.setValor(new Double(conteudoLinha[3].trim().replaceAll(",", "\\.")));
				}else if(conteudoLinha.length == 3){
					String nomeServidor = conteudoLinha[1];
					nomeServidor = nomeServidor.replaceAll("[0-9]","").trim();
					String nomeServidorFinal = nomeServidor.substring(0,nomeServidor.length()-1).trim(); 
					itemFatura.getVinculacao().getBeneficiario().setNome(nomeServidorFinal);
					String tipoBeneficiario = nomeServidor.substring(nomeServidor.length()-1).trim().equals("T") ? "T" : "D";
					itemFatura.getVinculacao().getBeneficiario().setTipoBeneficiario(tipoBeneficiario);
					itemFatura.setValor(new Double(conteudoLinha[2].trim().replaceAll(",", "\\.")));
				}else{
					throw new SmartAppException("Layout do arquivo com padrão não previsto pelo sistema. Conteúdo da linha tratada: "+linhaOriginal);
				}
			}else{
				ex.printStackTrace();
				throw ex;
			}
		}
		return itemFatura;		
	}

}
