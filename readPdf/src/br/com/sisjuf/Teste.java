package br.com.sisjuf;


public class Teste {
	
	private static final String PATH_WORKSPACE = "/Users/antoniolazaroborges/desenvolvimento/workspace_eclipse/";
	private static final String PATH_PROJECT = PATH_WORKSPACE+"readPdf/samples/";

	public static void main(String[] args) throws Exception {
	    
//		ParserFileOdontosystem parserFileOdontosystemAssociados = new ParserFileOdontosystem(PATH_PROJECT+"ASSERJUF (Associados)- Extrato Mensal.pdf");
//	    parserFileOdontosystemAssociados.printAssociadosList();
//	    
//		ParserFileOdontosystem parserFileOdontosystemFuncionarios = new ParserFileOdontosystem(PATH_PROJECT+"ASSERJUF (Funcionarios) - Extrato Mensal.pdf");
//		parserFileOdontosystemFuncionarios.printAssociadosList();
//	    
	    ParserFileFolhaPagamento parserFileFolhaPagamento = new ParserFileFolhaPagamento(PATH_PROJECT+"Julho.pdf");
	    parserFileFolhaPagamento.printAssociadosList();
	    
	    //QUASE OK
//	    ParserFilePromedica parserFilePromedica = new ParserFilePromedica(PATH_PROJECT+"Relatorio JUL 2014.PDF");
//	    parserFilePromedica.printAssociadosList();
	    
	    //OK
//	    ParserFileVitalmed parserFileVitalmed = new ParserFileVitalmed(PATH_PROJECT+"Relatorio agosto2014.pdf");
//	    parserFileVitalmed.printAssociadosList();
	}
}
