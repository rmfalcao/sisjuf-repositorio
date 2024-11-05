package br.org.asserjuf.sisjuf.util.arquivosfatura;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public abstract class ParserXlsxFileAb extends ParserExcelFileAb{
	
	
	
	public ParserXlsxFileAb(String path) {
		super(path);
		
	}

	protected Workbook getWorkbook(FileInputStream fis) throws IOException {
		return new XSSFWorkbook(fis);
	};

}
