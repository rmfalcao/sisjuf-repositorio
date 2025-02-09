package br.org.asserjuf.sisjuf.util.arquivos;

import java.io.IOException;

import org.apache.poi.ss.usermodel.Row;

import br.org.asserjuf.sisjuf.associados.convenio.BeneficiarioVO;
import br.org.asserjuf.sisjuf.associados.convenio.ItemFaturaVO;
import br.org.asserjuf.sisjuf.associados.convenio.VinculacaoPlanoVO;

public class ParserServdonto extends ParserXlsFaturaFileAb {

private static final int INICIO_UTIL_ARQUIVO = 16;
	
	
	public ParserServdonto(String path) throws IOException {
		super(path);		
	}


	@Override
	protected int getNumLinhaInicialComConteudo() {
		return INICIO_UTIL_ARQUIVO;
	}

	@Override
	protected ItemFaturaVO parseRow(Row row) {
		
		//se celula está vazia ou comeca com um número, nao é uma linha com item da fatura.
				
		if (isEmpty(row.getCell(0).getStringCellValue()) || startsWithNumber(row.getCell(0).getStringCellValue()) || row.getCell(0).getStringCellValue().equals("ASSERJUF - FUNC")) {
			return null;
		}
		
		// se chegou até aqui, é uma linha com um item da fatura.
		
		ItemFaturaVO itemFatura = new ItemFaturaVO();
		itemFatura.setVinculacao(new VinculacaoPlanoVO());
		itemFatura.getVinculacao().setBeneficiario(new BeneficiarioVO());
		itemFatura.getVinculacao().getBeneficiario().setNome(row.getCell(0).getStringCellValue().trim());
		itemFatura.getVinculacao().setCodigoBeneficiarioPlano(row.getCell(1).getStringCellValue().trim());
		
		
		//itemFatura.getVinculacao().getBeneficiario().setTipoBeneficiario(row.getCell(3).getStringCellValue().trim().equals("TITULAR") ? "T" : "D");
		//itemFatura.getVinculacao().getBeneficiario().setTipoBeneficiario("nao identificado");
		
		itemFatura.setValor(row.getCell(4).getNumericCellValue());
		
		/*
        item.setId((int) row.getCell(0).getNumericCellValue());
        item.setDescription(row.getCell(1).getStringCellValue());
        */
        return itemFatura;
	}


	private boolean startsWithNumber(String stringCellValue) {
		return Character.isDigit(stringCellValue.charAt(0));
	}


	private boolean isEmpty(String stringCellValue) {
		return (stringCellValue == null || "".equals(stringCellValue));
	}

}
