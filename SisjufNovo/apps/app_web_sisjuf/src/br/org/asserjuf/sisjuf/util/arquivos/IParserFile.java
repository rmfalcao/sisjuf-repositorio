package br.org.asserjuf.sisjuf.util.arquivos;

import java.io.IOException;
import java.util.List;

public interface IParserFile {
	
	
	public abstract <T> List<T> parse() throws IOException, Exception;
	
}
