package br.org.asserjuf.sisjuf.util.arquivos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.org.asserjuf.sisjuf.associados.AssociadoVO;
import br.org.asserjuf.sisjuf.associados.ItemPlanilhaNucreVO;

public abstract class ParserSepag extends ParserPdfFileAb {

	private static final int _LINHA_RUBRICA_ = 10; // linhas comecam a contar do 0
	private static final String _TEXTO_LINHA_ANTES_DOS_CPFS_ = "Nome do Servidor"; 
	
	private static final String _INICIO_TEXTO_LINHA_DEPOIS_DOS_CPFS_ = "C.P.F. Cons"; // o texto completo é C.P.F. ConsignaçãoSeq
	public ParserSepag(String path) {
		super(path);
		
	}
	
	protected abstract String[] getRubricas();

	@Override
	protected ArrayList<ItemPlanilhaNucreVO> initiateList() {
		
		return new ArrayList<ItemPlanilhaNucreVO>();
	}

	@Override
	protected List<ItemPlanilhaNucreVO> createItemFromLines(String[] linhasPaginaArquivo) throws Exception {
		
		boolean temCPFnaLinha = false;
		boolean temConsignacaoNaLinha = false;
		boolean paginaTemRubricaMensalidade = false;
		ArrayList<String> cpfs = new ArrayList<String>();
		ArrayList<Float> valoresConsignados = new ArrayList<Float>();
		
		System.out.println("Linha rubrica: >" + linhasPaginaArquivo[_LINHA_RUBRICA_].trim() + "<");
		
		if (paginaContemRubrica(linhasPaginaArquivo)) {
					
			for (int i = _LINHA_RUBRICA_+1; i < linhasPaginaArquivo.length; i++) {
			
				String linha = linhasPaginaArquivo[i];
				
				
				if (linha != null) {

					if (!temCPFnaLinha) {
						if (linha.trim().equals(_TEXTO_LINHA_ANTES_DOS_CPFS_)) {
							temCPFnaLinha = true;
							continue;
						}
					} else {
						if (!linha.trim().startsWith(_INICIO_TEXTO_LINHA_DEPOIS_DOS_CPFS_)) {
							cpfs.add(linha.trim());
						} else {
							// acabaram os cpfs. pegar os valores consignados.
							for (int j = i + cpfs.size()+1; j < i+(2*cpfs.size())+1; j++) {
								linha = linhasPaginaArquivo[j];
								valoresConsignados.add(new Float(linha.trim().replace(",", ".")));
							}
						
							break;
						} 
												
						
					}
					
				}
	
			}
		
		}
		
		

		return createListaItensPlanilhaNucre(cpfs, valoresConsignados);
	}


	private boolean paginaContemRubrica(String[] linhasPaginaArquivo) {
		
		return Arrays.stream(getRubricas())
                .anyMatch(s -> s.equals(linhasPaginaArquivo[_LINHA_RUBRICA_].trim()));
		
		
		
	}

	private List<ItemPlanilhaNucreVO> createListaItensPlanilhaNucre(ArrayList<String> cpfs, 
			ArrayList<Float> valoresConsignados) {

		List<ItemPlanilhaNucreVO> lista = new ArrayList<ItemPlanilhaNucreVO>();
		
		for (int i = 0; i < cpfs.size(); i++) {
			String cpf = cpfs.get(i);
			Float valor = valoresConsignados.get(i);
			
			ItemPlanilhaNucreVO item= new ItemPlanilhaNucreVO();
			item.setAssociado(new AssociadoVO());
			item.getAssociado().setCpf(new Long(cpf));
			item.setValorDebitado(valor);
			
			lista.add(item);
		}
		
		return lista;
	}

}
