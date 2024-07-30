package br.org.asserjuf.sisjuf.util.arquivosfatura;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.org.asserjuf.sisjuf.associados.convenio.ItemFaturaVO;


public abstract class ParserFileAb implements ParserFile {
	
	private ParserPdfToStringArray parserPdfToStringArray;
	
	public ParserFileAb(byte[] contentFile) throws IOException {
		this.parserPdfToStringArray = new ParserPdfToStringArray(contentFile);
	}
	
	public ParserFileAb(String path) throws IOException {
		this.parserPdfToStringArray = new ParserPdfToStringArray(path);
	}
	
	public List<ItemFaturaVO> parserContentFileToIntensFaturasList() throws Exception {
		List<ItemFaturaVO> listaItensFaturas = new ArrayList<ItemFaturaVO>();
		for(int paginaCorrente=0;paginaCorrente <= parserPdfToStringArray.getNumeroPaginas();paginaCorrente++){
			String[] linhasPaginaArquivo = parserPdfToStringArray.parserPdfContentToStringArraySpecificPage(paginaCorrente,paginaCorrente);
			if(linhasPaginaArquivo.length > 1){
				List<ItemFaturaVO> listaItensFaturasPagina = parserContentFileToItensFaturasList(linhasPaginaArquivo);
				listaItensFaturas.addAll(listaItensFaturasPagina);
			}
		}
		return listaItensFaturas;
	}
	
}
