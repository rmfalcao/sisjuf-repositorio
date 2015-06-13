package br.org.asserjuf.sisjuf.util.arquivosfatura;

import java.util.List;

import br.org.asserjuf.sisjuf.associados.convenio.BeneficiarioVO;

public interface ParserFile {
	
	public List<BeneficiarioVO> parserContentFileToBeneficiariosList() throws Exception;
	public List<BeneficiarioVO> parserContentFileToBeneficiariosList(String[] linhasPaginaArquivo) throws Exception;
}
