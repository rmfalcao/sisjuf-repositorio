package br.org.asserjuf.sisjuf.util.arquivosfatura;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;

public abstract class ParserXlsFileAb extends ParserExcelFileAb{
	
		
	public ParserXlsFileAb(String path) {
		super(path);
		
	}

	protected Workbook getWorkbook(FileInputStream fis) throws IOException {
		return new HSSFWorkbook(fis);
	};

}
