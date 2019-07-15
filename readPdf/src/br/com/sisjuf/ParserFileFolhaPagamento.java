package br.com.sisjuf;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ParserFileFolhaPagamento extends ParserFileAb {
	
	private static final int INICIO_UTIL_ARQUIVO = 11;
	private static final int QUANTIDADE_CARACTERES_POSSIVEL_NOME_QUEBRA_LINHA = 19;
	private static final int QUANTIDADE_MINIMA_NOME= 3;
	
	public ParserFileFolhaPagamento(String pathFile) throws IOException {
		super(pathFile);
	}
	
	@Override
	public List<AssociadoModel> parserContentFileToAssociadosList(String[] linhasPaginasArquivo) throws Exception{
		List<AssociadoModel> listaAssociados = new ArrayList<AssociadoModel>();
		int indicePonteiroLeituraArquivo = INICIO_UTIL_ARQUIVO;
		String linha =  null;
		try{
			
	    	for(;indicePonteiroLeituraArquivo<linhasPaginasArquivo.length;indicePonteiroLeituraArquivo++){
	        	linha =  linhasPaginasArquivo[indicePonteiroLeituraArquivo].trim();
//	        	System.out.println("linha-> "+linha);
	        	if(!linha.equalsIgnoreCase("Código")){
	        		AssociadoModel associado = new AssociadoModel();
	        		associado.setCodigo(linha);
	    			listaAssociados.add(associado);
	        	}else{
	        		break;
	        	}
	        }
	    	
	    	indicePonteiroLeituraArquivo++;
	    	int indiceListaAssociados = 0;
	    	for(;indicePonteiroLeituraArquivo<linhasPaginasArquivo.length;indicePonteiroLeituraArquivo++){
	        	linha =  linhasPaginasArquivo[indicePonteiroLeituraArquivo].trim();
	        	if(!linha.equalsIgnoreCase("Nome do Servidor")){
	        		StringBuilder buffer = new StringBuilder();
	        		buffer.append(linha);
	        		if(linha.length() > QUANTIDADE_CARACTERES_POSSIVEL_NOME_QUEBRA_LINHA){
		        		String nomeComQuebraLinha = linhasPaginasArquivo[indicePonteiroLeituraArquivo+1];
		        		if(!nomeComQuebraLinha.equalsIgnoreCase("Nome do Servidor")){
		        			String[] possivelSobra = nomeComQuebraLinha.split(" ");
		        			if(possivelSobra != null && possivelSobra.length < QUANTIDADE_MINIMA_NOME){
		        				for(String conteudoSobra:possivelSobra){
		        					buffer.append(" ").append(conteudoSobra);	
		        				}
		        				indicePonteiroLeituraArquivo++;
		        			}
		        		}
	        		}
	        		if(indiceListaAssociados < listaAssociados.size()){
		        		AssociadoModel associado = listaAssociados.get(indiceListaAssociados);
		        		associado.setNomeServidor(buffer.toString());
		        		indiceListaAssociados++;
	        		}else{
	        			System.out.println(" inconsistencia -> "+linha);
	        		}
	        	}else{
	        		break;
	        	}
	        }
	    	indicePonteiroLeituraArquivo++;
	    	
	    	indiceListaAssociados = 0;
	    	int quantidadeTotalAssociados = listaAssociados.size();
	    	for(;indicePonteiroLeituraArquivo<linhasPaginasArquivo.length;indicePonteiroLeituraArquivo++){
	        	linha =  linhasPaginasArquivo[indicePonteiroLeituraArquivo].trim();
	        	if(indiceListaAssociados < quantidadeTotalAssociados){
	        		AssociadoModel associado = listaAssociados.get(indiceListaAssociados);
	        		associado.setCpf(linha);
	        		indiceListaAssociados++;
	        	}else{
	        		break;
	        	}
	        }
	    	indicePonteiroLeituraArquivo++;
	    	
	    	indicePonteiroLeituraArquivo+=listaAssociados.size();
	    	indiceListaAssociados = 0;
	    	for(;indicePonteiroLeituraArquivo<linhasPaginasArquivo.length;indicePonteiroLeituraArquivo++){
	        	linha =  linhasPaginasArquivo[indicePonteiroLeituraArquivo].trim();
	        	if(indiceListaAssociados < quantidadeTotalAssociados){
	        		AssociadoModel associado = listaAssociados.get(indiceListaAssociados);
	        		associado.setValorMensalidade(linha);
	        		indiceListaAssociados++;
	        	}else{
	        		break;
	        	}
	        }
	    	
	    	indiceListaAssociados = 0;
	    	for(;indicePonteiroLeituraArquivo<linhasPaginasArquivo.length;indicePonteiroLeituraArquivo++){
	        	linha =  linhasPaginasArquivo[indicePonteiroLeituraArquivo].trim();
	        	if(indiceListaAssociados < quantidadeTotalAssociados){
	        		AssociadoModel associado = listaAssociados.get(indiceListaAssociados);
	        		associado.setValorDevolucao(linha);
	        		indiceListaAssociados++;
	        	}else{
	        		break;
	        	}
	        }
	    	
	    	indicePonteiroLeituraArquivo+=listaAssociados.size();
	    	indiceListaAssociados = 0;
	    	for(;indicePonteiroLeituraArquivo<linhasPaginasArquivo.length;indicePonteiroLeituraArquivo++){
	        	linha =  linhasPaginasArquivo[indicePonteiroLeituraArquivo].trim();
	        	if(indiceListaAssociados < quantidadeTotalAssociados){
	        		AssociadoModel associado = listaAssociados.get(indiceListaAssociados);
	        		associado.setValorLiquido(linha);
	        		indiceListaAssociados++;
	        	}else{
	        		break;
	        	}
	        }
		}catch(Exception ex){
			if(ex instanceof IndexOutOfBoundsException){
				throw new Exception("Inconsistencia de dados no layout do arquivo. Numero da Linha -> "+indicePonteiroLeituraArquivo+".\n Conteudo da linha-> "+linha);
			}else{
				throw ex;
			}
		}
    	
        return listaAssociados;
	}

}
