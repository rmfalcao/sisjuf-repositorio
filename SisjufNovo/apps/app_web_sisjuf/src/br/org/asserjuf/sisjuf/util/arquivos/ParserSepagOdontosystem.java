package br.org.asserjuf.sisjuf.util.arquivos;

public class ParserSepagOdontosystem extends ParserSepag {

	public ParserSepagOdontosystem(String path) {
		super(path);
		
	}

	private static final String[] _TEXTO_LINHA_RUBRICA_MENSALIDADE_ = {"522175Rubrica: ASSERJUF ODONTOSYSTEM-",
																		"522176Rubrica: ASSERJUF ODONTOSYSTEM/OURO-"};

	@Override
	protected String[] getRubricas() {
		// TODO Auto-generated method stub
		return _TEXTO_LINHA_RUBRICA_MENSALIDADE_;
	}

	
	
}
