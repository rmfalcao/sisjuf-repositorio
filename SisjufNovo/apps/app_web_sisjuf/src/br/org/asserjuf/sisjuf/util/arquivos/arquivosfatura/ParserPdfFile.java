package br.org.asserjuf.sisjuf.util.arquivos.arquivosfatura;

import java.util.List;

import br.org.asserjuf.sisjuf.associados.convenio.ItemFaturaVO;

public interface ParserPdfFile {

	public List<ItemFaturaVO> parserContentFileToItensFaturasList(String[] linhasPaginaArquivo) throws Exception;

	
}
