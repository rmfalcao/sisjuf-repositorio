package br.org.asserjuf.sisjuf.util.arquivos;

import java.util.ArrayList;
import java.util.List;

import br.org.asserjuf.sisjuf.associados.AssociadoVO;
import br.org.asserjuf.sisjuf.associados.ItemPlanilhaNucreVO;

public class ParserSepag extends ParserPdfFileAb {

	private static final int _LINHA_RUBRICA_ = 10; // linhas comecam a contar do 0
	private static final String _TEXTO_LINHA_RUBRICA_MENSALIDADE_ = "522059Rubrica: ASSERJUF - MENSALIDADE-";
	private static final String _TEXTO_LINHA_ANTES_DOS_CPFS_ = "Nome do Servidor"; 
	
	private static final String _INICIO_TEXTO_LINHA_DEPOIS_DOS_CPFS_ = "C.P.F. Cons"; // o texto completo é C.P.F. ConsignaçãoSeq
	public ParserSepag(String path) {
		super(path);
		
	}

	@Override
	protected ArrayList<ItemPlanilhaNucreVO> initiateList() {
		
		return new ArrayList<ItemPlanilhaNucreVO>();
	}

	@Override
	protected List<ItemPlanilhaNucreVO> createItemFromLines(String[] linhasPaginaArquivo) throws Exception {
		
		boolean temCPFnaLinha = false;
		boolean paginaTemRubricaMensalidade = false;
		List<ItemPlanilhaNucreVO> lista = new ArrayList<ItemPlanilhaNucreVO>();
		
		if (linhasPaginaArquivo[_LINHA_RUBRICA_].trim().equals(_TEXTO_LINHA_RUBRICA_MENSALIDADE_)) {
					
			for (int i = _LINHA_RUBRICA_+1; i < linhasPaginaArquivo.length; i++) {
			
				String linha = linhasPaginaArquivo[i];
				
				System.out.println(i + ": " + linha);
				
				if (linha != null) {

					if (!temCPFnaLinha) {
						if (linha.trim().equals(_TEXTO_LINHA_ANTES_DOS_CPFS_)) {
							temCPFnaLinha = true;
							continue;
						}
					} else {
						if (!linha.trim().startsWith(_INICIO_TEXTO_LINHA_DEPOIS_DOS_CPFS_)) {
							lista.add(createItemPlanilhaFromLine(linha));
						} else {
							break;
						}
						}
					
				}
	
			}
		
		}
		

		return lista;
	}

	private ItemPlanilhaNucreVO createItemPlanilhaFromLine(String linha) {
		ItemPlanilhaNucreVO item= new ItemPlanilhaNucreVO();
		item.setAssociado(new AssociadoVO());
		item.getAssociado().setCpf(new Long(linha.trim()));
		return item;
	}

}
