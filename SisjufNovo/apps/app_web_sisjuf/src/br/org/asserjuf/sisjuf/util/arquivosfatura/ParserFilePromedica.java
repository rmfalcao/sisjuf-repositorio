
package br.org.asserjuf.sisjuf.util.arquivosfatura;

import java.io.IOException;
import org.apache.poi.ss.usermodel.Row;

import br.org.asserjuf.sisjuf.associados.convenio.BeneficiarioVO;
import br.org.asserjuf.sisjuf.associados.convenio.ItemFaturaVO;
import br.org.asserjuf.sisjuf.associados.convenio.VinculacaoPlanoVO;


public class ParserFilePromedica extends ParserXlsFileAb {
	
	private static final int INICIO_UTIL_ARQUIVO = 3;
		
	public ParserFilePromedica(String contentFile) throws IOException {
		super(contentFile);		
	}


	@Override
	protected int getNumLinhaInicialComConteudo() {
		return INICIO_UTIL_ARQUIVO;
	}

	@Override
	protected ItemFaturaVO parseRow(Row row) {
				
		ItemFaturaVO itemFatura = new ItemFaturaVO();
		itemFatura.setVinculacao(new VinculacaoPlanoVO());
		itemFatura.getVinculacao().setBeneficiario(new BeneficiarioVO());
		itemFatura.getVinculacao().getBeneficiario().setNome(row.getCell(0).getStringCellValue().trim());
		itemFatura.getVinculacao().setCodigoBeneficiarioPlano(row.getCell(1).getStringCellValue().trim());

		// getCell(2) eh a data de nascimento; irrelevante para a importacao.
		
		itemFatura.getVinculacao().getBeneficiario().setTipoBeneficiario(row.getCell(3).getStringCellValue().trim().equals("TITULAR") ? "T" : "D");
		
		itemFatura.setValor(row.getCell(4).getNumericCellValue());
		
		/*
        item.setId((int) row.getCell(0).getNumericCellValue());
        item.setDescription(row.getCell(1).getStringCellValue());
        */
        return itemFatura;
	}


	
	
}
