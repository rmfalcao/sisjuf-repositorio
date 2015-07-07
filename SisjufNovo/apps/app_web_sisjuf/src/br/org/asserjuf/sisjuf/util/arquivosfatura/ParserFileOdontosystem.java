package br.org.asserjuf.sisjuf.util.arquivosfatura;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.org.asserjuf.sisjuf.associados.convenio.BeneficiarioVO;
import br.org.asserjuf.sisjuf.associados.convenio.ItemFaturaVO;
import br.org.asserjuf.sisjuf.associados.convenio.VinculacaoPlanoVO;

public class ParserFileOdontosystem extends ParserFileAb{
	
	private static final int TAMANHO_CABECALHO_ODONTO_SYSTEM_ASSOCIADO = 10;
	private static final String MENSAGEM_RODAPE_ODONTO_SYSTEM_ASSOCIADO = "PDF created with pdfFactory trial version www.pdffactory.com";
	private static final String MENSAGEM_RESUMO = "RESUMO  DA EMPRESA";
	
	public ParserFileOdontosystem(byte[] contentFile) throws IOException {
		super(contentFile);
	}
	
	public List<ItemFaturaVO> parserContentFileToItensFaturasList(String[] linhasArquivo) {
		List<ItemFaturaVO> listaItensFaturas = new ArrayList<ItemFaturaVO>();
		for(int i = TAMANHO_CABECALHO_ODONTO_SYSTEM_ASSOCIADO;i<linhasArquivo.length;i++){
			String linha = linhasArquivo[i];
			System.out.println(linha);
        	if(linha.equalsIgnoreCase(MENSAGEM_RODAPE_ODONTO_SYSTEM_ASSOCIADO) || linha.equalsIgnoreCase(MENSAGEM_RESUMO)){
        		break;
        	}
        	if(Character.isDigit(linha.charAt(0))){
//        		//momento do agrupamento da familia...
        		continue;
        	}
        	ItemFaturaVO itemFatura = new ItemFaturaVO();

			String conteudoNome = linha.replaceAll("[0-9]","#");
			conteudoNome = conteudoNome.split("#")[0];
			itemFatura.setVinculacao(new VinculacaoPlanoVO());
			itemFatura.getVinculacao().setBeneficiario(new BeneficiarioVO());
			itemFatura.getVinculacao().getBeneficiario().setNome(conteudoNome);

        	String conteudoCodigo = linha.replaceAll("[A-Za-z]","#").replaceAll("#","");
        	conteudoCodigo = conteudoCodigo.trim().split(" ")[0];
        	itemFatura.getVinculacao().setCodigoBeneficiarioPlano(conteudoCodigo);

//        	int indicePrimeiraBarraData = linha.indexOf("/");
//        	String conteudoData = linha.substring((indicePrimeiraBarraData-2),(indicePrimeiraBarraData+8)).trim();
//        	associado.setDataAdesao(conteudoData);

//        	int indiceCampoHora = linha.indexOf("00:00:00");
//        	int indiceProximoCampo = linha.indexOf("Parcela");
//        	String conteudoPlano = linha.substring(indiceCampoHora,indiceProximoCampo).replaceAll("00:00:00","").trim();
//        	associado.setPlano(conteudoPlano);
        	
        	int indiceVirgularValorMensalidade = linha.indexOf(",");
        	indiceVirgularValorMensalidade+=3;
        	String valorTipoCliente = linha.substring(indiceVirgularValorMensalidade,linha.length()-1).trim();
        	itemFatura.getVinculacao().getBeneficiario().setTipoBeneficiario(valorTipoCliente.equals("Titular") ? "titular" : "dependente");

        	String valorMensalidade = linha.substring(indiceVirgularValorMensalidade-5,indiceVirgularValorMensalidade+2).replaceAll("[A-Za-z]","").trim();;
        	valorMensalidade.replaceAll(",", "\\.");
        	itemFatura.setValor(new Double(valorMensalidade));

        	listaItensFaturas.add(itemFatura);
		}
		return listaItensFaturas;
	}
}