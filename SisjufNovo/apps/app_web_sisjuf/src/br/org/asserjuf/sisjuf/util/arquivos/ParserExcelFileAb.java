package br.org.asserjuf.sisjuf.util.arquivos;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import br.org.asserjuf.sisjuf.associados.convenio.ItemFaturaVO;

public abstract class ParserExcelFileAb extends ParserFileAb {

	public ParserExcelFileAb(String path) {
		super(path);
		// TODO Auto-generated constructor stub
	}

	public List parse() throws IOException, Exception {
		// TODO Auto-generated method stub

		
		FileInputStream fis = new FileInputStream(this.path);
        Workbook workbook = getWorkbook(fis);
        
        Sheet sheet = workbook.getSheetAt(0); // Assuming data is in the first sheet
		
        List items = initiateList();
        
        int numLinhaInicialComConteudo = getNumLinhaInicialComConteudo();
        
        for (Row row : sheet) {
        	
        	if (row.getRowNum() < numLinhaInicialComConteudo) {
        		continue;
        	}
        	
            ItemFaturaVO item = parseRow(row);
            if (item != null) {
                items.add(item);
            }
        }
        
        workbook.close();
        
		return items;
	}

	protected abstract List initiateList();

	protected abstract Workbook getWorkbook(FileInputStream fis) throws IOException;

	protected abstract int getNumLinhaInicialComConteudo();

	protected abstract ItemFaturaVO parseRow(Row row);
	 
	
	
}
