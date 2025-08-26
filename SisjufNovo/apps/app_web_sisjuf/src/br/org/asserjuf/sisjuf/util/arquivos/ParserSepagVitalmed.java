package br.org.asserjuf.sisjuf.util.arquivos;

public class ParserSepagVitalmed extends ParserSepag {

	public ParserSepagVitalmed(String path) {
		super(path);
		
	}

	private static final String[] _TEXTO_LINHA_RUBRICA_MENSALIDADE_ = {"522174Rubrica: ASSERJUF (VITALMED)-"};

	@Override
	protected String[] getRubricas() {
		// TODO Auto-generated method stub
		return _TEXTO_LINHA_RUBRICA_MENSALIDADE_;
	}

	
	
}
