package br.org.asserjuf.sisjuf.util.arquivos;

import java.io.IOException;
import java.util.List;

public interface IParserFile {
	
	
	public abstract List<?> parse() throws IOException, Exception;
	
}
