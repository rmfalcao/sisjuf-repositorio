package br.com.sisjuf;


public class Teste {
	
	private static final String PATH_WORKSPACE = "/Users/antoniolazarocarvalhoborges/Desenvolvimento/workspace/sisjuf-repositorio/";
//	private static final String PATH_WORKSPACE = "D:/desenvolvimento/projetos/Sisjuf/sisjuf-repositorio/";
	private static final String PATH_PROJECT = PATH_WORKSPACE+"readPdf/samples/";

	public static void main(String[] args) throws Exception {
	    
//		ParserFileOdontosystem parserFileOdontosystemAssociados = new ParserFileOdontosystem(PATH_PROJECT+"ASSERJUF (Associados)- Extrato Mensal.pdf");
//	    parserFileOdontosystemAssociados.printAssociadosList();
//	    
//		ParserFileOdontosystem parserFileOdontosystemFuncionarios = new ParserFileOdontosystem(PATH_PROJECT+"ASSERJUF (Funcionarios) - Extrato Mensal.pdf");
//		parserFileOdontosystemFuncionarios.printAssociadosList();
//	    
//	    ParserFileFolhaPagamento parserFileFolhaPagamento = new ParserFileFolhaPagamento(PATH_PROJECT+"Julho.pdf");
//	    parserFileFolhaPagamento.printAssociadosList();
	    
	    //QUASE OK
	    ParserFilePromedica parserFilePromedica = new ParserFilePromedica(PATH_PROJECT+"relatorio outubro.2015.pdf");
	    parserFilePromedica.printAssociadosList();
	    
	    //OK
//	    ParserFileVitalmed parserFileVitalmed = new ParserFileVitalmed(PATH_PROJECT+"Relatorio agosto2014.pdf");
//	    parserFileVitalmed.printAssociadosList();
	}
}
