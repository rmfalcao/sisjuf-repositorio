package br.org.asserjuf.sisjuf.util.arquivos;

import java.util.List;

public class ParserPdfFileSepag extends ParserPdfFileAb {

	public ParserPdfFileSepag(String path) {
		super(path);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected List createItemFromLines(String[] linhasPaginaArquivo) throws Exception {
		// TODO trocar retorno generico por classe representando SEPAG
		return null;
	}

	@Override
	protected List initiateList() {
		// TODO alterar para returnar objeto do arquivo SEPAG
		return null;
	}

}
