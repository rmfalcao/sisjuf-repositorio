package br.org.asserjuf.sisjuf.util.arquivosfatura;

import java.util.List;

import br.org.asserjuf.sisjuf.associados.convenio.ItemFaturaVO;

public interface ParserPdfFile extends ParserFile {

	public List<ItemFaturaVO> parserContentFileToItensFaturasList(String[] linhasPaginaArquivo) throws Exception;

	
}
