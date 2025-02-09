package br.org.asserjuf.sisjuf.util.arquivos.arquivosfatura;

import java.util.List;

import br.org.asserjuf.sisjuf.associados.convenio.BeneficiarioVO;
import br.org.asserjuf.sisjuf.associados.convenio.ItemFaturaVO;

public interface IParserFaturaFile {
	
	public List<ItemFaturaVO> parserContentFileToIntensFaturasList() throws Exception;
	
}
