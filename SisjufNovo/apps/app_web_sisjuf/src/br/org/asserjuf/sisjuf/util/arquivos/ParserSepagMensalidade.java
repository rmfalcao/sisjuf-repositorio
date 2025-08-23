package br.org.asserjuf.sisjuf.util.arquivos;

public class ParserSepagMensalidade extends ParserSepag {

	public ParserSepagMensalidade(String path) {
		super(path);
		
	}

	private static final String _TEXTO_LINHA_RUBRICA_MENSALIDADE_ = "522059Rubrica: ASSERJUF - MENSALIDADE-";

	@Override
	protected String getRubrica() {
		// TODO Auto-generated method stub
		return _TEXTO_LINHA_RUBRICA_MENSALIDADE_;
	}

	
	
}
