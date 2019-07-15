package br.org.asserjuf.sisjuf.util.arquivosfatura;

import java.util.List;

import br.org.asserjuf.sisjuf.associados.convenio.BeneficiarioVO;
import br.org.asserjuf.sisjuf.associados.convenio.ItemFaturaVO;

public interface ParserFile {
	
	public List<ItemFaturaVO> parserContentFileToIntensFaturasList() throws Exception;
	public List<ItemFaturaVO> parserContentFileToItensFaturasList(String[] linhasPaginaArquivo) throws Exception;
}
