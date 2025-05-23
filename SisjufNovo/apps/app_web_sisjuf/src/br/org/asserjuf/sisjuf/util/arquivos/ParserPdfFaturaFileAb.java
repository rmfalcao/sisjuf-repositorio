package br.org.asserjuf.sisjuf.util.arquivos;

import java.util.ArrayList;
import java.util.List;

import br.org.asserjuf.sisjuf.associados.convenio.ItemFaturaVO;

public abstract class ParserPdfFaturaFileAb extends ParserPdfFileAb {

	public ParserPdfFaturaFileAb(String path) {
		super(path);
	}


	@Override
	protected abstract List<ItemFaturaVO> createItemFromLines(String[] linhasPaginaArquivo) throws Exception ;

	@Override
	protected ArrayList<ItemFaturaVO> initiateList() {

		return new ArrayList<ItemFaturaVO>();
	}
	
}
