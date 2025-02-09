package br.org.asserjuf.sisjuf.util.arquivos;

import java.util.ArrayList;
import br.org.asserjuf.sisjuf.associados.convenio.ItemFaturaVO;

public abstract  class ParserPdfFileFaturaAb extends ParserPdfFileAb {

	public ParserPdfFileFaturaAb(String path) {
		super(path);
		
	}

	@Override
	protected ArrayList<ItemFaturaVO> initiateList() {

		return new ArrayList<ItemFaturaVO>();
	}

	

}
