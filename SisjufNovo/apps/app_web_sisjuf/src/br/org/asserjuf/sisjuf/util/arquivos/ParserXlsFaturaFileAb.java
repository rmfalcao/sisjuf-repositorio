package br.org.asserjuf.sisjuf.util.arquivos;

import java.util.ArrayList;


import br.org.asserjuf.sisjuf.associados.convenio.ItemFaturaVO;

public abstract class ParserXlsFaturaFileAb extends ParserXlsFileAb {

	
	
	public ParserXlsFaturaFileAb(String path) {
		super(path);
		
	}

	@Override
	protected ArrayList<ItemFaturaVO> initiateList() {

		return new ArrayList<ItemFaturaVO>();
	}

}
