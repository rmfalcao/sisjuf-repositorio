package br.org.asserjuf.sisjuf.associados.convenio;

import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;

import br.com.falc.smartFW.exception.SmartAppException;
import br.com.falc.smartFW.exception.SmartEnvException;
import br.org.asserjuf.sisjuf.associados.convenio.dados.FaturaDAO;
import br.org.asserjuf.sisjuf.associados.convenio.dados.StatusFaturaVO;
import br.org.asserjuf.sisjuf.financeiro.LancamentoFaturaRN;
import br.org.asserjuf.sisjuf.financeiro.LancamentoFaturaVO;
import br.org.asserjuf.sisjuf.financeiro.LancamentoVO;
import br.org.asserjuf.sisjuf.financeiro.OrigemLancamentoVO;
import br.org.asserjuf.sisjuf.financeiro.TipoOperacaoVO;
import br.org.asserjuf.sisjuf.util.ParametroRN;
import br.org.asserjuf.sisjuf.util.ParametroVO;

/**
 * Encapsula as regras de neg�cio da entidade fatura.
 * @author Paulo
 *
 */
public class FaturaRN {

	/**
	 * Classe de acesso ao banco de dados da entidade "Fatura" do Sisjuf.
	 */
	protected FaturaDAO faturaDAO;
	
	/**
	 * Classe que encapsula as funcionalidades da regra de neg�cio da entidade lan�amento fatura.
	 */
	protected LancamentoFaturaRN	lancamentoFaturaRN;
	
	/**
	 * Classe que encapsula as funcionalidades da regra de neg�cio da entidade Convenio.
	 */
	protected ConvenioRN	convenioRN;
	
	/**
	 * Classe que encapsula as funcionalidades da regra de neg�cio da entidade lan�amento item fatura.
	 */
	//private LancamentoFaturaRN	lancamentoItemFaturaRN;
	
	
	/**
	 * Classe que encapsula as funcionalidades da regra de neg�cio da entidade lan�amento item fatura.
	 */
	protected ParametroRN	parametroRN;
	
	
	public void setParametroRN(ParametroRN parametroRN) {
		this.parametroRN = parametroRN;
	}


	/**
	 * Obt�m as faturas recentes de um conv�nio.
	 * @return Cole��o de faturas, encapsulados na classe FaturaVO.
	 * @throws SmartEnvException
	 */
	public Collection<FaturaVO> findByConvenio(ConvenioVO convenio) throws SmartEnvException {

		return faturaDAO.findByConvenio(convenio);
	}
	
	public Collection<FaturaVO> findByLancamento(LancamentoVO lancamento) throws SmartEnvException {
		return faturaDAO.findByLancamento(lancamento);
	}

	public void setFaturaDAO(FaturaDAO faturaDAO) {
		this.faturaDAO = faturaDAO;
	}

	public void setLancamentoFaturaRN(LancamentoFaturaRN lancamentoFaturaRN) {
		this.lancamentoFaturaRN = lancamentoFaturaRN;
	}
	
	public void setConvenioRN(ConvenioRN convenioRN) {
		this.convenioRN = convenioRN;
	}

//	public void setLancamentoItemFaturaRN(LancamentoFaturaRN lancamentoItemFaturaRN) {
//		this.lancamentoItemFaturaRN = lancamentoItemFaturaRN;
//	}


	public void estornar(FaturaVO fatura)  throws SmartEnvException, SmartAppException {
		
		// Estornar lan�amentos relacionados � fatura, caso existam
		// 1. obter lan�amentos by fatura
	
		Collection<LancamentoFaturaVO> lancamentos	= lancamentoFaturaRN.findByFatura(fatura);

		// 2. estornar lan�amentos by pk
		for (LancamentoFaturaVO lancamento : lancamentos) {
			lancamentoFaturaRN.estornarLancamento(lancamento);
		}
		
		// 3. atualizar status da fatura para CANCELADA
		 faturaDAO.estornar(fatura);
	}
	
	public FaturaVO gerar(FaturaVO fatura)  throws SmartEnvException, SmartAppException {
		
		
		// testar regras de fatura
		testaRegrasFatura(fatura);
		
		// obtendo valor da fatura pelos itens
		double valorFatura = 0;
		for (ItemFaturaVO item : fatura.getItens()) {
			valorFatura += (item.getValor()==null?0:item.getValor().doubleValue());
		}
		fatura.setValorFatura(new Double(valorFatura));
		
		// insere fatura
		this.faturaDAO.insert(fatura);
		
		// testa regras e insere itens de fatura:
		for (ItemFaturaVO item : fatura.getItens()) {
			
			// coloca fatura (codigo) no item
			item.setFatura(fatura);
			
			// testa regras:
			testaRegrasItemFatura(item);
			
			// insere:
			this.faturaDAO.insert(item);
		}
		
		// 1) um lan�amento a debitar da conta da ASSERJUF para o conv�nio em quest�o
		debitarContaAsserjuf(fatura);
		
		// 2) "n" lan�amentos a creditar dos associados na conta da ASSERJUF
		creditarContaAsserjuf(fatura);
		
		return fatura;
	}
	
	private LancamentoFaturaVO preparaLancamento(FaturaVO fatura, String nomeParametroTipoOperacao) throws SmartEnvException, SmartAppException {
		
		LancamentoFaturaVO lancamentoFatura	= new LancamentoFaturaVO();
		
		lancamentoFatura.setFatura(fatura);
		
		if (nomeParametroTipoOperacao.equals("TP_OPERACAO_A_DEBITAR")) {
			lancamentoFatura.setContaVO(fatura.getContaDebito());
		} else {
			lancamentoFatura.setContaVO(fatura.getContaCredito());
		}
		
		ParametroVO parametro	= new ParametroVO("ORIGEM_CONVENIOS");
		parametro				= parametroRN.findByPrimaryKey(parametro);
		
		lancamentoFatura.setOrigemLancamentoVO(new OrigemLancamentoVO(new Integer(parametro.getValorTextual())));

		parametro.setNome(nomeParametroTipoOperacao);
		parametro				= parametroRN.findByPrimaryKey(parametro);
		
		lancamentoFatura.setTipoOperacaoVO(new TipoOperacaoVO(new Integer(parametro.getValorTextual())));
		
		lancamentoFatura.setValor(fatura.getValorFatura());
		
		// TODO verificar: data de previs�o est� igual � data de vencimento, pode ser?
		lancamentoFatura.setDataPrevisao(fatura.getDataVencimento());
		
		
		return lancamentoFatura;
		
	}
	
	private void creditarContaAsserjuf(FaturaVO fatura) throws SmartEnvException, SmartAppException {
		
		// Criar objeto com dados do lan�amento a debitar.
		LancamentoFaturaVO lancamentoFatura	= preparaLancamento(fatura, "TP_OPERACAO_CREDITO");
		
		// Invocar rn
		lancamentoFatura	= (LancamentoFaturaVO) lancamentoFaturaRN.efetuarLancamento(lancamentoFatura);
	}


	private void debitarContaAsserjuf(FaturaVO fatura) throws SmartEnvException, SmartAppException {
		
		// Criar objeto com dados do lan�amento a debitar.
		LancamentoFaturaVO lancamentoFatura	= preparaLancamento(fatura, "TP_OPERACAO_DEBITO");
		
		// Invocar rn
		lancamentoFatura	= (LancamentoFaturaVO) lancamentoFaturaRN.efetuarLancamento(lancamentoFatura);
	}

	protected void testaRegrasItemFatura(ItemFaturaVO item) throws SmartAppException {
		
		if (item == null) {
			throw new SmartAppException("Pelo menos um item da fatura não foi identificado.");
		}
		
		if (item.getNumero() == null) {
			throw new SmartAppException("Todos os itens da fatura devem ter numeração.");
		}
		
		if (item.getValor() == null || item.getValor().longValue() == 0) {
			throw new SmartAppException("O item número " + item.getNumero() + " está vazio ou igual a zero.");
		}
		
		if (item.getVinculacao()==null || item.getVinculacao().getCodigo()==null) {
			throw new SmartAppException("O associado não foi identificado no item número " + item.getNumero() + " da fatura.");
		}
		
		item.getVinculacao().setBeneficiario(item.getBeneficiario());
		
		if (item.getVinculacao().getBeneficiario() == null || item.getVinculacao().getBeneficiario().getCodigo() == null) {
			throw new SmartAppException("O beneficiário não foi identificado no item número " + item.getNumero() + " da fatura.");
		}
		
		if (item.getVinculacao().getBeneficiario().getTitular() == null || item.getVinculacao().getBeneficiario().getTitular().getCodigo() == null) {
			throw new SmartAppException("O titular do beneficiário não foi encontrado no item número " + item.getNumero() + " da fatura.");
		}
	}

	private void testaRegrasFatura(FaturaVO fatura) throws SmartAppException, SmartEnvException {
		

		if (fatura.getCodigo() != null) {
			throw new SmartAppException("Fatura já gerada: código " + fatura.getCodigo());
		}
		
		fatura.setConvenio(this.convenioRN.findByPrimaryKey(fatura.getConvenio()));
		
		validarCamposObrigatoriosFatura(fatura);
		setDatasFatura(fatura);	
		setStatusFatura(fatura);
		
		fatura.setStatusPago("N");
		fatura.setStatusRecebido("N");
	}


	protected void setStatusFatura(FaturaVO fatura) throws SmartEnvException,SmartAppException {
		fatura.setStatus(new StatusFaturaVO());
		if (!fatura.getConvenio().getCategoria().equals("F")) {
			fatura.getStatus().setCodigo(new Short(parametroRN.findByPrimaryKey(new ParametroVO("STATUS_FATURA_VALIDADA")).getValorTextual()));
		}else{
			fatura.getStatus().setCodigo(new Short(parametroRN.findByPrimaryKey(new ParametroVO("STATUS_FATURA_GERADA")).getValorTextual()));	
		}
	}


	protected void validarCamposObrigatoriosFatura(FaturaVO fatura) throws SmartAppException {
		if (fatura == null) {
			throw new SmartAppException("Fatura não identificada.");
		}
		
		if (fatura.getConvenio() == null || fatura.getConvenio().getCodigo() == null) {
			throw new SmartAppException("Você deve informar qual número do convênio.");
		}
		
		if (fatura.getContaCredito() == null || fatura.getContaCredito().getCodigo() == null) {
			throw new SmartAppException("A conta ASSERJUF para crédito deve ser informada.");
		}
		
		if (fatura.getContaDebito() == null || fatura.getContaDebito().getCodigo() == null) {
			throw new SmartAppException("A conta ASSERJUF para débito deve ser informada.");
		}
		
		if (fatura.getDataInicial() == null) {
			throw new SmartAppException("A data inicial da fatura deve ser informado.");
		}
		
		if (fatura.getDataFinal() == null)  {
			throw new SmartAppException("A data final da fatura deve ser informado.");
		}
	}


	protected void setDatasFatura(FaturaVO fatura) {
		
		GregorianCalendar calendar	= new GregorianCalendar();
		
		calendar.setTime(fatura.getDataInicial());
		
		// data vencimento:		
		calendar.add(Calendar.MONTH, fatura.getConvenio().getMesVencimento().shortValue());
		calendar.set(Calendar.DAY_OF_MONTH,fatura.getConvenio().getDiaVencimento().intValue());
		
		fatura.setDataVencimento(calendar.getTime());
	}
	
	public void updateStatus(FaturaVO fatura) throws SmartEnvException {
		faturaDAO.updateStatus(fatura);
	}
	
	public Collection<FaturaVO> findByFilter(FaturaFiltroAssembler fatura) throws SmartEnvException, SmartAppException {
		
		testaPreenchimentoDoFiltro(fatura);
		
		Collection<FaturaVO> faturas	= faturaDAO.findByFilter(fatura);
		
		/*if (faturas != null && faturas.size() != 0) {
			
			for (FaturaVO umaFatura : faturas) {
				umaFatura.setItens(this.findItensByFatura(umaFatura));
				
			}
			
		}*/
		
		return faturas;
	}

	private void testaPreenchimentoDoFiltro(FaturaFiltroAssembler fatura) throws SmartAppException {
		
		if (fatura == null || (fatura.getCodigo() == null &&
					(fatura.getConvenio() == null || fatura.getConvenio().getCodigo() == null) &&
					(fatura.getStatus() == null || fatura.getStatus().getCodigo() == null) &&
					fatura.getDataInicioDe() == null &&
					fatura.getDataInicioAte() == null &&
					fatura.getDataFimDe() == null &&
					fatura.getDataFimAte() == null
		 )) {
			throw new SmartAppException("Ao menos um crit�rio de filtro deve ser preenchido.");
		}
	}


	public FaturaVO findByPrimaryKey(FaturaVO fatura) throws SmartEnvException {
		
		fatura	= faturaDAO.findByPrimaryKey(fatura);
		
		fatura.setItens(this.findItensByFatura(fatura));
			
		return fatura;
	}

	private Collection<ItemFaturaVO> findItensByFatura(FaturaVO fatura) throws SmartEnvException {
		
		return faturaDAO.findItensByFatura(fatura);
	}
}