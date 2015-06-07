package br.org.asserjuf.sisjuf.util.arquivosfatura;

import java.io.IOException;


public abstract class ParserFileAb{// implements ParserFile {
	
	private ParserPdfToStringArray parserPdfToStringArray;
	
	public ParserFileAb(String pathFile) throws IOException {
		this.parserPdfToStringArray = new ParserPdfToStringArray(pathFile);
	}
	
//	@Override
//	public List<AssociadoModel> parserContentFileToAssociadosList() throws Exception {
//		List<AssociadoModel> listaAssociados = new ArrayList<>();
//		for(int paginaCorrente=0;paginaCorrente<parserPdfToStringArray.getNumeroPaginas();paginaCorrente++){
//			String[] linhasPaginaArquivo = parserPdfToStringArray.parserPdfContentToStringArraySpecificPage(paginaCorrente,paginaCorrente);
//			if(linhasPaginaArquivo.length > 1){
//				List<AssociadoModel> listaAssociadosPagina = parserContentFileToAssociadosList(linhasPaginaArquivo);
//				listaAssociados.addAll(listaAssociadosPagina);
//			}
//		}
//		return listaAssociados;
//	}
	
}
