package br.org.asserjuf.sisjuf.util.arquivosfatura;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.org.asserjuf.sisjuf.associados.convenio.BeneficiarioVO;


public abstract class ParserFileAb implements ParserFile {
	
	private ParserPdfToStringArray parserPdfToStringArray;
	
	public ParserFileAb(byte[] contentFile) throws IOException {
		this.parserPdfToStringArray = new ParserPdfToStringArray(contentFile);
	}
	
	public List<BeneficiarioVO> parserContentFileToBeneficiariosList() throws Exception {
		List<BeneficiarioVO> listaAssociados = new ArrayList<BeneficiarioVO>();
		for(int paginaCorrente=0;paginaCorrente<parserPdfToStringArray.getNumeroPaginas();paginaCorrente++){
			String[] linhasPaginaArquivo = parserPdfToStringArray.parserPdfContentToStringArraySpecificPage(paginaCorrente,paginaCorrente);
			if(linhasPaginaArquivo.length > 1){
				List<BeneficiarioVO> listaAssociadosPagina = parserContentFileToBeneficiariosList(linhasPaginaArquivo);
				listaAssociados.addAll(listaAssociadosPagina);
			}
		}
		return listaAssociados;
	}
	
}
