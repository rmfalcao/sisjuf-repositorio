package br.com.sisjuf;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public abstract class ParserFileAb implements ParserFile {
	
	private ParserPdfToStringArray parserPdfToStringArray;
	
	public ParserFileAb(String pathFile) throws IOException {
		this.parserPdfToStringArray = new ParserPdfToStringArray(pathFile);
	}
	
	@Override
	public List<AssociadoModel> parserContentFileToAssociadosList() throws Exception {
		List<AssociadoModel> listaAssociados = new ArrayList<>();
		for(int paginaCorrente=0;paginaCorrente<parserPdfToStringArray.getNumeroPaginas();paginaCorrente++){
			System.out.println("############### NOVA PAGINA ###### "+paginaCorrente);
			String[] linhasPaginaArquivo = parserPdfToStringArray.parserPdfContentToStringArraySpecificPage(paginaCorrente,paginaCorrente);
			if(linhasPaginaArquivo.length > 1){
				List<AssociadoModel> listaAssociadosPagina = parserContentFileToAssociadosList(linhasPaginaArquivo);
				listaAssociados.addAll(listaAssociadosPagina);
			}
		}
		return listaAssociados;
	}
	
	
	public void printAssociadosList() throws Exception{
		List<AssociadoModel> listaAssociados = parserContentFileToAssociadosList();
		if(listaAssociados != null && listaAssociados.size() > 0){
			for(AssociadoModel associado: listaAssociados){
				System.out.println("associado -> "+associado.getNomeServidor());
			}
		}else{
			System.out.println("Lista vazia");
		}
	}
}
