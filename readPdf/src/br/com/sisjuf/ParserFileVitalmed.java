package br.com.sisjuf;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ParserFileVitalmed extends ParserFileAb{
	
	public ParserFileVitalmed(String pathFile) throws IOException {
		super(pathFile);
	}

	@Override
	public List<AssociadoModel> parserContentFileToAssociadosList(String[] linhasArquivo) {                   
		List<AssociadoModel> listaAssociados = new ArrayList<AssociadoModel>();
		for(int i = 0;i<linhasArquivo.length;i++){
        	String linha = linhasArquivo[i].trim();
        	if(linha.startsWith("9")){
        		String linhaModificada = linha.replaceAll("  ","@").replaceAll(" ","").replaceAll("@@","#").replaceFirst("@","#").replaceAll("@"," ").replaceAll("##","");
	        	String[] conteudoLinha = linhaModificada.split("#");
	        	AssociadoModel associado = createAssociado(linhaModificada,conteudoLinha);	
	        	listaAssociados.add(associado);
        	}
        }
		return listaAssociados;
	}
	
	private AssociadoModel createAssociado(String linhaModificada,String[] conteudoLinha) {
		AssociadoModel associado = new AssociadoModel();
		associado.setCodigo(conteudoLinha[0].trim());
		try{
			associado.setNomeServidor(conteudoLinha[1].trim());
			associado.setTipoCliente(conteudoLinha[2].trim());
			associado.setIdade(conteudoLinha[3].trim());
			associado.setValorMensalidade(conteudoLinha[4].trim());
		}catch(Exception ex){
			if(ex instanceof ArrayIndexOutOfBoundsException){
				conteudoLinha = linhaModificada.split("#");
				String nomeServidor = conteudoLinha[1];
				associado.setNomeServidor(nomeServidor.substring(0,nomeServidor.length()-1).trim());
				associado.setTipoCliente(nomeServidor.substring(nomeServidor.length()-1).trim());
				associado.setIdade(conteudoLinha[2].trim());
		    	associado.setValorMensalidade(conteudoLinha[3].trim());
			}else{
				ex.printStackTrace();
			}
		}
		return associado;
	}
}
