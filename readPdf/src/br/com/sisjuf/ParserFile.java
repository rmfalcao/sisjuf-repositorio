package br.com.sisjuf;

import java.util.List;

public interface ParserFile {
	
	public List<AssociadoModel> parserContentFileToAssociadosList() throws Exception;
	public List<AssociadoModel> parserContentFileToAssociadosList(String[] linhasPaginaArquivo) throws Exception;
}
