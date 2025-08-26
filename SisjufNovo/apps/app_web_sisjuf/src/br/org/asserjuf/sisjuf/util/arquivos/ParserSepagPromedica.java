package br.org.asserjuf.sisjuf.util.arquivos;

public class ParserSepagPromedica extends ParserSepag {

	public ParserSepagPromedica(String path) {
		super(path);
		
	}

	private static final String[] _TEXTO_LINHA_RUBRICA_MENSALIDADE_ = {"525079Rubrica: PROMEDICA-", 
																		"525111Rubrica: PROMEDICA ULTRA EXTRA-",
																		"525113Rubrica: PROMEDICA ESPECIAL-"};

	@Override
	protected String[] getRubricas() {
		// TODO Auto-generated method stub
		return _TEXTO_LINHA_RUBRICA_MENSALIDADE_;
	}

	
	
}
