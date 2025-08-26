package br.org.asserjuf.sisjuf.util.arquivos;

public class ParserSepagServdonto extends ParserSepag {

	public ParserSepagServdonto(String path) {
		super(path);
		
	}

	private static final String[] _TEXTO_LINHA_RUBRICA_MENSALIDADE_ = {"525161Rubrica: ASSERJUF - SERVIDONTO-"};

	@Override
	protected String[] getRubricas() {
		// TODO Auto-generated method stub
		return _TEXTO_LINHA_RUBRICA_MENSALIDADE_;
	}

	
	
}
