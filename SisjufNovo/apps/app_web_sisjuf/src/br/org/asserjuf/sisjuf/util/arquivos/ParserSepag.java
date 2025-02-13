package br.org.asserjuf.sisjuf.util.arquivos;

import java.util.ArrayList;

import br.org.asserjuf.sisjuf.associados.ItemPlanilhaNucreVO;

public class ParserSepag extends ParserPdfFileAb {

	public ParserSepag(String path) {
		super(path);
		
	}

	@Override
	protected ArrayList<ItemPlanilhaNucreVO> initiateList() {
		
		return new ArrayList<ItemPlanilhaNucreVO>();
	}

	@Override
	protected ArrayList<ItemPlanilhaNucreVO> createItemFromLines(String[] linhasPaginaArquivo) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
