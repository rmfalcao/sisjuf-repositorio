package br.org.asserjuf.sisjuf.util.arquivosfatura;


import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import br.org.asserjuf.sisjuf.associados.convenio.ItemFaturaVO;

public abstract class ParserXlsFileAb implements ParserFile {
	
	private String path;
	
	public ParserXlsFileAb(String path) {
		this.path = path;
	}
	
	public List<ItemFaturaVO> parserContentFileToIntensFaturasList() throws Exception {
		 

		// 
		FileInputStream fis = new FileInputStream(this.path);
        Workbook workbook = new HSSFWorkbook(fis);
        Sheet sheet = workbook.getSheetAt(0); // Assuming data is in the first sheet
		
        List<ItemFaturaVO> items = new ArrayList<ItemFaturaVO>();
        
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

	protected abstract int getNumLinhaInicialComConteudo();

	protected abstract ItemFaturaVO parseRow(Row row);
	 

}
