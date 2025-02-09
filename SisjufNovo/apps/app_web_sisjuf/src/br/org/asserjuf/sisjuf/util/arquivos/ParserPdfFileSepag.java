package br.org.asserjuf.sisjuf.util.arquivos;

import java.util.List;

public class ParserPdfFileSepag extends ParserPdfFileAb {

	public ParserPdfFileSepag(String path) {
		super(path);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected <T> List<T> createItemFromLines(String[] linhasPaginaArquivo) throws Exception {
		// TODO trocar retorno generico por classe representando SEPAG
		return null;
	}

}
