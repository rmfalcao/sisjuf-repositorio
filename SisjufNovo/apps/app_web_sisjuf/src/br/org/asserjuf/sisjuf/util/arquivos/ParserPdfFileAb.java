package br.org.asserjuf.sisjuf.util.arquivos;

import java.util.ArrayList;
import java.util.List;

import br.org.asserjuf.sisjuf.util.arquivos.arquivosfatura.ParserPdfToStringArray;

public abstract class ParserPdfFileAb extends ParserFileAb {


	
	public ParserPdfFileAb(String path) {
		super(path);
		
	}

	public List parse() throws Exception {
		ParserPdfToStringArray parserPdfToStringArray = new ParserPdfToStringArray(path);
		List lista = new ArrayList();
		for(int paginaCorrente=0;paginaCorrente <= parserPdfToStringArray.getNumeroPaginas();paginaCorrente++){
			String[] linhasPaginaArquivo = parserPdfToStringArray.parserPdfContentToStringArraySpecificPage(paginaCorrente,paginaCorrente);
			if(linhasPaginaArquivo.length > 1){
				List listaPagina = createItemFromLines(linhasPaginaArquivo);
				lista.addAll(listaPagina);
			}
		}
		return lista;
		
	}

	protected abstract List createItemFromLines(String[] linhasPaginaArquivo) throws Exception;

}
