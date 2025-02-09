package br.org.asserjuf.sisjuf.util.arquivos;

import java.util.ArrayList;
import java.util.List;

import br.org.asserjuf.sisjuf.util.arquivos.arquivosfatura.ParserPdfToStringArray;

public abstract class ParserPdfFileAb extends ParserFileAb {


	
	public ParserPdfFileAb(String path) {
		super(path);
		
	}

	public <T> List<T> parse() throws Exception {
		ParserPdfToStringArray parserPdfToStringArray = new ParserPdfToStringArray(path);
		List<T> lista = new ArrayList<T>();
		for(int paginaCorrente=0;paginaCorrente <= parserPdfToStringArray.getNumeroPaginas();paginaCorrente++){
			String[] linhasPaginaArquivo = parserPdfToStringArray.parserPdfContentToStringArraySpecificPage(paginaCorrente,paginaCorrente);
			if(linhasPaginaArquivo.length > 1){
				List<T> listaPagina = createItemFromLines(linhasPaginaArquivo);
				lista.addAll(listaPagina);
			}
		}
		return lista;
		
	}

	protected abstract <T> List<T> createItemFromLines(String[] linhasPaginaArquivo) throws Exception;

}
