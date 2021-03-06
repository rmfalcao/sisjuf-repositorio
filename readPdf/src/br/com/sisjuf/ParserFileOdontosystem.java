package br.com.sisjuf;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ParserFileOdontosystem extends ParserFileAb{
	
	private static final int TAMANHO_CABECALHO_ODONTO_SYSTEM_ASSOCIADO = 10;
	private static final String MENSAGEM_RODAPE_ODONTO_SYSTEM_ASSOCIADO = "PDF created with pdfFactory trial version www.pdffactory.com";
	private static final String MENSAGEM_RESUMO = "RESUMO  DA EMPRESA";
	
	public ParserFileOdontosystem(String pathFile) throws IOException {
		super(pathFile);
	}
	
	@Override
	public List<AssociadoModel> parserContentFileToAssociadosList(String[] linhasArquivo) {
//		ADRIANA DE FREITAS ABBEHUSEN719137 01/09/2004  00:00:00GLOBAL 1  -  C Parcela Não Usuário 0,00Titular0
		List<AssociadoModel> listaAssociados = new ArrayList<AssociadoModel>();
		for(int i = TAMANHO_CABECALHO_ODONTO_SYSTEM_ASSOCIADO;i<linhasArquivo.length;i++){
        	String linha = linhasArquivo[i].trim();
        	System.out.println(linha);
        	if(linha.equalsIgnoreCase(MENSAGEM_RODAPE_ODONTO_SYSTEM_ASSOCIADO) || linha.equalsIgnoreCase("RESUMO  DA EMPRESA")){
        		break;
        	}
        	if(Character.isDigit(linha.charAt(0))){
        		//momento do agrupamento da familia...
        		continue;
        	}
        	AssociadoModel associado = new AssociadoModel();
        	
        	String conteudoNome = linha.replaceAll("[0-9]","#");
        	conteudoNome = conteudoNome.split("#")[0];
        	associado.setNomeServidor(conteudoNome);
        	
        	String conteudoCodigo = linha.replaceAll("[A-Za-z]","#").replaceAll("#","");
        	conteudoCodigo = conteudoCodigo.trim().split(" ")[0];
        	associado.setCodigo(conteudoCodigo);
        	
        	int indicePrimeiraBarraData = linha.indexOf("/");
        	String conteudoData = linha.substring((indicePrimeiraBarraData-2),(indicePrimeiraBarraData+8)).trim();
        	associado.setDataAdesao(conteudoData);
        	
        	int indiceCampoHora = linha.indexOf("00:00:00");
        	int indiceProximoCampo = linha.indexOf("Parcela");
        	String conteudoPlano = linha.substring(indiceCampoHora,indiceProximoCampo).replaceAll("00:00:00","").trim();
        	associado.setPlano(conteudoPlano);
        	
        	int indiceVirgularValorMensalidade = linha.indexOf(",");
        	indiceVirgularValorMensalidade+=3;
        	String valorTipoCliente = linha.substring(indiceVirgularValorMensalidade,linha.length()-1).trim();
        	associado.setTipoCliente(valorTipoCliente);
        	
        	String valorMensalidade = linha.substring(indiceVirgularValorMensalidade-5,indiceVirgularValorMensalidade+2).replaceAll("[A-Za-z]","").trim();;
        	associado.setValorMensalidade(valorMensalidade);
        	
        	listaAssociados.add(associado);

        }
		return listaAssociados;
	}
}
