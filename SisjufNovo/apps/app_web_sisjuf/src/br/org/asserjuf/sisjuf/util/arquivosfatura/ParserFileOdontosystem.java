package br.org.asserjuf.sisjuf.util.arquivosfatura;

import java.io.IOException;
import org.apache.poi.ss.usermodel.Row;

import br.org.asserjuf.sisjuf.associados.convenio.BeneficiarioVO;
import br.org.asserjuf.sisjuf.associados.convenio.ItemFaturaVO;
import br.org.asserjuf.sisjuf.associados.convenio.VinculacaoPlanoVO;

public class ParserFileOdontosystem extends ParserXlsFileAb {
	
	
	private static final int INICIO_UTIL_ARQUIVO = 1;
	
	
	public ParserFileOdontosystem(String contentFile) throws IOException {
		super(contentFile);		
	}


	@Override
	protected int getNumLinhaInicialComConteudo() {
		return INICIO_UTIL_ARQUIVO;
	}

	@Override
	protected ItemFaturaVO parseRow(Row row) {
				
		if (row.getCell(3).getStringCellValue().toUpperCase().equals("CADASTRO")) {
			return null;
		}
		
		ItemFaturaVO itemFatura = new ItemFaturaVO();
		itemFatura.setVinculacao(new VinculacaoPlanoVO());
		itemFatura.getVinculacao().setBeneficiario(new BeneficiarioVO());
		itemFatura.getVinculacao().getBeneficiario().setNome(row.getCell(2).getStringCellValue().trim());
		itemFatura.getVinculacao().setCodigoBeneficiarioPlano(new Integer((int)row.getCell(0).getNumericCellValue()).toString());
		
		//itemFatura.getVinculacao().getBeneficiario().setTipoBeneficiario(row.getCell(3).getStringCellValue().trim().equals("TITULAR") ? "T" : "D");
		//itemFatura.getVinculacao().getBeneficiario().setTipoBeneficiario("nao identificado");
		
		itemFatura.setValor(row.getCell(4).getNumericCellValue());
		
		/*
        item.setId((int) row.getCell(0).getNumericCellValue());
        item.setDescription(row.getCell(1).getStringCellValue());
        */
        return itemFatura;
	}

	
}