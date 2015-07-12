package br.org.asserjuf.sisjuf.associados.convenio;

import java.util.Collection;

import br.com.falc.smartFW.exception.SmartAppException;
import br.com.falc.smartFW.exception.SmartEnvException;
import br.org.asserjuf.sisjuf.associados.convenio.dados.StatusFaturaVO;
import br.org.asserjuf.sisjuf.util.ParametroVO;

public class FaturaFixaRN extends FaturaRNAb {


	public  FaturaVO gerarFaturaPrevia(FaturaVO fatura) throws SmartEnvException, SmartAppException {
		
		// montar objeto de filtro. Este objeto, no que diz respeito às datas, 
		//  terá implementações específicas a depender de como o convenio tratar os periodos de associação. 
		
		FaturaPreviaFiltroAssembler filtro =	montaFiltroFaturaPrevia(fatura);
		
		fatura.setItens(this.faturaDAO.gerarFaturaPrevia(filtro));
		
		return fatura;
		
	}

	private FaturaPreviaFiltroAssembler montaFiltroFaturaPrevia(FaturaVO fatura) throws SmartAppException, SmartEnvException {
		
		FaturaPreviaFiltroAssembler filtro	= new FaturaPreviaFiltroAssembler();
		
		filtro.setFatura(fatura);
		
		setaDatasApuracao(filtro);
		
		return filtro;
	}
	
	protected void setaDatasApuracao(FaturaPreviaFiltroAssembler filtro) throws SmartAppException, SmartEnvException {
		
		
		/*
		 * 
		 * O objetivo deste método � setar duas datas do objeto "filtro" que s�o espec�ficas para a Vitalmed:
		 * 
		 * dataInicioApuracaoAssociados e dataFimApuracaoAssociados
		 * 
		 */
		
		// para efeito de testes, ser� considerada a data de inicio da fatura e a data de fechamento da fatura.
		
		setDatasFatura(filtro.getFatura());
		
		filtro.setDataInicioApuracaoAssociados(filtro.getFatura().getDataInicial());
		filtro.setDataFimApuracaoAssociados(filtro.getFatura().getDataFinal());
		
		
	}
	
	public FaturaArquivoVO validar(FaturaArquivoVO faturaArquivo) throws SmartEnvException, SmartAppException {
		
		// inserir fatura na tabela de fatura_arquivo
		
		inserirFaturaArquivo(faturaArquivo);
		
		// obter relat�rio dos registros inconsistentes
		
		Collection<ItemFaturaInconsistenteVO> itensInconsistentes	= this.faturaDAO.findItensInconsistentesByFatura(faturaArquivo);
				
		// verificar relat�rio obtido, se for vazio, validar a fatura.
		// caso contr�rio, 
		
		if (itensInconsistentes != null && itensInconsistentes.size() != 0) {
			faturaArquivo.setItensInconsistentes(itensInconsistentes);
			return faturaArquivo;
		}
		 
		
		// se chegou aqui, n�o houve inconsist�ncias.
		
		// atualizar status da fatura para "validada"
		faturaArquivo.setStatus(new StatusFaturaVO());
		faturaArquivo.getStatus().setCodigo(new Short(parametroRN.findByPrimaryKey(new ParametroVO("STATUS_FATURA_VALIDADA")).getValorTextual()));
		
		this.updateStatus(faturaArquivo);
		
		
		return faturaArquivo;
		
	}

	private void inserirFaturaArquivo(FaturaVO faturaArquivo) throws SmartEnvException, SmartAppException {

		if (faturaArquivo  == null) {
			throw new SmartAppException("Não foi identificada nenhuma fatura no arquivo.");
		}
		
		if (faturaArquivo.getCodigo() == null) {
			throw new SmartAppException("O código da fatura de referência não foi encontrado.");
		};
		
		if (faturaArquivo.getItens() == null || faturaArquivo.getItens().size() == 0) {
			throw new SmartAppException("Não foram identificados itens na fatura do arquivo.");
		}
		
		FaturaArquivoVO faturaOld = this.faturaDAO.findBYPrimaryKey((FaturaArquivoVO)faturaArquivo);
		if (faturaOld == null){
			this.faturaDAO.insertFaturaArquivo(faturaArquivo);
			
			// testa regras e insere itens de fatura:
			for (ItemFaturaVO item : faturaArquivo.getItens()) {
				
				// coloca fatura (codigo) no item
				item.setFatura(faturaArquivo);
				
				// testa regras:
				testaRegrasItemFaturaArquivo(item);
				
				// insere:
				this.faturaDAO.insertItemArquivo(item);
			}
		}else{//CASO FATURA JA EXISTA, ALTERA A MESMA E REMOVE OS ITENS PARA INSERIR NOVAMENTE
			this.faturaDAO.updateFaturaArquivo(faturaArquivo);
			faturaDAO.deleteItemFaturaArquivo(faturaArquivo);
			
			// testa regras e insere itens de fatura:
			for (ItemFaturaVO item : faturaArquivo.getItens()) {
				
				// coloca fatura (codigo) no item
				item.setFatura(faturaArquivo);
				
				// testa regras:
				testaRegrasItemFaturaArquivo(item);
				
				// insere:
				this.faturaDAO.insertItemArquivo(item);
			}
			System.out.println("TESTE");
		}
		
	}

	private void testaRegrasItemFaturaArquivo(ItemFaturaVO item) throws SmartAppException {

		if (item == null) {
			throw new SmartAppException("Pelo menos um item da fatura do arquivo não foi identificado.");
		}
		
		if (item.getVinculacao() == null || item.getVinculacao().getBeneficiario() == null) {
			throw new SmartAppException("Beneficiário não encontrado em item de fatura do arquivo.");
		}
		/*
		if (item.getVinculacao().getBeneficiario().getCpf() == null) {
			throw new SmartAppException("CPF de beneficiário de um item de fatura do arquivo não encontrado.");
		}
		*/
		if (item.getVinculacao().getCodigoBeneficiarioPlano()==null || "".equals(item.getVinculacao().getCodigoBeneficiarioPlano())) {
			throw new SmartAppException("Codigo do beneficiario no plano de um item de fatura do arquivo não encontrado.");
		}
		
		if (item.getValor() == null) {
			throw new SmartAppException("Valor de um item de fatura do arquivo não encontrado.");
		}
		
	}

	
}
