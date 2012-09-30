package br.org.asserjuf.sisjuf.financeiro;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Iterator;

import br.com.falc.smartFW.exception.SmartAppException;
import br.com.falc.smartFW.exception.SmartEnvException;
import br.org.asserjuf.sisjuf.financeiro.dados.LancamentoDAO;
import br.org.asserjuf.sisjuf.util.DataRN;
import br.org.asserjuf.sisjuf.util.ParametroRN;
import br.org.asserjuf.sisjuf.util.ParametroVO;

/**
 * Congrega todas as funcionalidades da regra de negócio da entidade lançamento.
 * @author Paulo Prado
 *
 */
public class LancamentoRN {

	/**
	 * Classe de acesso a banco de dados da entidade tipo de lançamento.
	 */
	protected LancamentoDAO 	lancamentoDAO;

	/**
	 * Classe que encapsula as funcionalidades da regra de negócio da entidade tipo de operação.
	 */
	protected TipoOperacaoRN 	tipoOperacaoRN;

	/**
	 * Classe que encapsula as funcionalidades da regra de negócio da entidade conta.
	 */
	protected ContaRN 		contaRN;
	
	/**
	 * Classe que encapsula as funcionalidades da regra de negócio da entidade de parâmetros do sistema.
	 */
	protected ParametroRN		parametroRN;
	
	/**
	 * Classe que encapsula as funcionalidades da regra de negócio da entidade utilitária data.
	 */
	protected DataRN			dataRN;
	
	protected DiretorFinanceiroRN diretorFinanceiroRN;
	
	/**
	 * Método que verifica se o sistema pode ser usado na data atual.
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	private void checkLicence() throws SmartEnvException, SmartAppException {
		SimpleDateFormat format	= new SimpleDateFormat();
		
		format.applyPattern("dd/MM/yyyy");
		try {
			if (dataRN.getCurrentDate().after(format.parse("01/12/2006"))) {
				throw new SmartAppException("Esta operação não é mais permitida na atual versão deste sistema.");
			}
		} catch (ParseException e) {
			throw new SmartEnvException(e);
		}
	}

	/**
	 * Busca por chave um lançamento.
	 * @param vo
	 * @return Objeto que representa o lançamento buscado
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public LancamentoVO findByPrimaryKey(LancamentoVO vo) throws SmartEnvException, SmartAppException {
		
		return lancamentoDAO.findByPrimaryKey(vo);
	}
	
	/**
	 * Efetua a baixa de um lançamento (Registra um recebimento ou pagamento total ou parcial de um lançamento previsto). 
	 * @param vo
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public void baixarLancamento(BaixaLancamentoVO vo) throws SmartEnvException, SmartAppException {
		
		this.validateFieldsBaixa(vo);

		TipoOperacaoVO tipoOperacaoVO = lancamentoDAO.findByPrimaryKey(vo.getLancamentoVO()).getTipoOperacaoVO();
		Double saldoLancamento = lancamentoDAO.obterSaldoLancamento(vo.getLancamentoVO());

		if (tipoOperacaoRN.findByPrimaryKey(tipoOperacaoVO).getFlgDebito().equals("1")) {

			vo.setValor(new Double(vo.getValor().doubleValue() *(-1)));
			
			if (vo.getValor().doubleValue() < saldoLancamento.doubleValue()) {
				throw new SmartAppException("Valor informado superior ao saldo a baixar desse lançamento.");
			}
		}
		else {
	
			if (vo.getValor().doubleValue() > saldoLancamento.doubleValue()) {
				throw new SmartAppException("Valor informado superior ao saldo a baixar desse lançamento.");
			}
		}
		lancamentoDAO.baixarLancamento(vo);
	
	if (vo.getData().before(dataRN.getCurrentDate())) {
			
			if (vo.getLancamentoVO().getContaVO() == null) {
				vo.setLancamentoVO(this.findByPrimaryKey(vo.getLancamentoVO()));
			}
			contaRN.removeHistoricoSaldoByData(vo.getLancamentoVO().getContaVO(), vo.getData());
			
			contaRN.consolidarHistoricoSaldo();
		}
		
		if (saldoLancamento.doubleValue() == vo.getValor().doubleValue()) {
			
			TipoOperacaoVO tipoOperacaoVO2 = new TipoOperacaoVO();

			if ((tipoOperacaoVO.getCodigo().intValue() == (new Integer(parametroRN.findByPrimaryKey(new ParametroVO("TP_OPERACAO_A_CREDITAR")).getValorTextual())).intValue())
					|| (tipoOperacaoVO.getCodigo().intValue() == (new Integer(parametroRN.findByPrimaryKey(new ParametroVO("TP_OPERACAO_CREDITO")).getValorTextual())).intValue())) {

				tipoOperacaoVO2.setCodigo(new Integer(parametroRN.findByPrimaryKey(new ParametroVO("TP_OPERACAO_CREDITO")).getValorTextual()));
			} else {

				tipoOperacaoVO2.setCodigo(new Integer(parametroRN.findByPrimaryKey(new ParametroVO("TP_OPERACAO_DEBITO")).getValorTextual()));
			}
			vo.getLancamentoVO().setTipoOperacaoVO(tipoOperacaoVO2);
			vo.getLancamentoVO().setDataEfetivacao(vo.getData());
			
			lancamentoDAO.quitarLancamentoPrevisto(vo.getLancamentoVO());
		}
	}
	
	/**
	 * Grava um lançamento, executando sua respectiva baixa no caso de lançamento que não seja de previsão.
	 * @param vo
	 * @return lancamentoVO com chave do registro inserido
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public LancamentoVO efetuarLancamento(LancamentoVO vo) throws SmartEnvException, SmartAppException {
		
		//this.checkLicence();
		
		this.validateFieldsLancamento(vo);
		
		if (tipoOperacaoRN.findByPrimaryKey(vo.getTipoOperacaoVO()).getFlgDebito().equals("1")) {
			
			vo.setValor(new Double(vo.getValor().doubleValue() * (-1)));
		}
		vo = lancamentoDAO.efetuarLancamento(vo);

		vo.setTipoOperacaoVO(tipoOperacaoRN.findByPrimaryKey(vo.getTipoOperacaoVO()));
		
		if (vo.getTipoOperacaoVO().getFlgPrevisao().equals("0")) {
			
			BaixaLancamentoVO baixaLancamentoVO = new BaixaLancamentoVO();
			
			baixaLancamentoVO.setLancamentoVO(vo);
			baixaLancamentoVO.setData(vo.getDataEfetivacao());
			baixaLancamentoVO.setValor(vo.getValor().doubleValue()<0?(new Double(vo.getValor().doubleValue()*(-1))):(new Double(vo.getValor().doubleValue())));
			baixaLancamentoVO.setFormaPagamentoVO(vo.getFormaPagamentoVO());
			baixaLancamentoVO.setBancoCheque(vo.getBancoCheque());
			baixaLancamentoVO.setAgenciaCheque(vo.getAgenciaCheque());
			baixaLancamentoVO.setDigitoAgenciaCheque(vo.getDigitoAgenciaCheque());
			baixaLancamentoVO.setContaCheque(vo.getContaCheque());
			baixaLancamentoVO.setDigitoContaCheque(vo.getDigitoContaCheque());
			baixaLancamentoVO.setNumeroCheque(vo.getNumeroCheque());
			
			this.baixarLancamento(baixaLancamentoVO);
		}
		vo.setValor(vo.getValor().doubleValue()<0?(new Double(vo.getValor().doubleValue()*(-1))):(new Double(vo.getValor().doubleValue())));
		
		return vo;
	}
		
	/**
	 * Remove um lançamento.
	 * @param vo Objeto com chave do lançamento a ser removido
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public void estornarLancamento(LancamentoVO vo) throws SmartEnvException, SmartAppException {
		
		if (vo.getContaVO() == null || vo.getContaVO().getCodigo() == null) {
			vo = this.findByPrimaryKey(vo);
			if (vo == null) {
				throw new SmartAppException("Lançamento não existe");
			} else if (vo.getContaVO() == null || vo.getContaVO().getCodigo() == null) {
				throw new SmartAppException("Conta não existe");
			}
		}
		contaRN.removeHistoricoSaldoByLancamento(vo.getContaVO(), vo);
		
		lancamentoDAO.estornarLancamento(vo);

		contaRN.consolidarHistoricoSaldo();
	}
	
	/**
	 * Efetua a movimentação de valores entre duas contas.  Gera um lançamento de débito na conta de origem e um de crédito na conta destino.
	 * @param assembler
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public void movimentarValoresEntreContas(MovimentacaoAssembler assembler) throws SmartEnvException, SmartAppException {
		
		this.validateFieldsMovimentacao(assembler);
		
		LancamentoVO lancamentoVO = new LancamentoVO();
		
		lancamentoVO.setContaVO(assembler.getContaOrigemVO());
		lancamentoVO.setDataEfetivacao(assembler.getData());
		lancamentoVO.setDescricao("Transferido para a conta " + contaRN.findByPrimaryKey(assembler.getContaDestinoVO()).getNome());;
		lancamentoVO.setOrigemLancamentoVO(new OrigemLancamentoVO());

		lancamentoVO.getOrigemLancamentoVO().setCodigo(new Integer(parametroRN.findByPrimaryKey(new ParametroVO("ORIGEM_USUARIO")).getValorTextual())); // usuario 
		lancamentoVO.setTipoOperacaoVO(new TipoOperacaoVO());
		lancamentoVO.getTipoOperacaoVO().setCodigo(new Integer(parametroRN.findByPrimaryKey(new ParametroVO("TP_OPERACAO_DEBITO")).getValorTextual())); // debito
		lancamentoVO.setTipoLancamentoVO(new TipoLancamentoVO(new Integer(parametroRN.findByPrimaryKey(new ParametroVO("TP_LANC_MOVIMENTACAO")).getValorTextual())));
		lancamentoVO.setValor(new Double(assembler.getValor().doubleValue()));
		
		lancamentoVO.setFormaPagamentoVO(assembler.getFormaPagamentoVO());
		lancamentoVO.setNumeroCheque(assembler.getNumeroCheque());

		// LANÇAMENTO PARA DEBITAR DA CONTA ORIGEM:
		this.efetuarLancamento(lancamentoVO);
		
		// CONTA ORIGEM DEBITADA.
		
		// o lançamento anteriormente realizado, por ser de débito,
		// setou o valor para NEGATIVO. Então, devemos torná-lo
		// positivo novamente.
		lancamentoVO.setValor(lancamentoVO.getValor().doubleValue()<0?lancamentoVO.getValor()*(-1):lancamentoVO.getValor());
		
		lancamentoVO.setContaVO(assembler.getContaDestinoVO());
		lancamentoVO.setDescricao("Transferido da conta " + contaRN.findByPrimaryKey(assembler.getContaOrigemVO()).getNome());;
		lancamentoVO.getTipoOperacaoVO().setCodigo(new Integer(parametroRN.findByPrimaryKey(new ParametroVO("TP_OPERACAO_CREDITO")).getValorTextual())); //credtito
		
		// Testando se a forma de pagamento é "CHEQUE".
		ParametroVO parametroVO	= new ParametroVO("FORMA_PAGTO_CHEQUE");
		parametroVO	= parametroRN.findByPrimaryKey(parametroVO);
		
		if (lancamentoVO.getFormaPagamentoVO().getCodigo().toString().equals(parametroVO.getValorTextual())) {

			// a operação é de crédito, mas a origem é uma conta controlada pela Asserjuf. Verificar se a conta informada é uma conta de banco.
			ContaBancoVO contaBancoVO = contaRN.findByPrimaryKey(assembler.getContaOrigemVO());

			if (contaBancoVO.getFlgContaCaixa()==null || !contaBancoVO.getFlgContaCaixa().equals("S")) {
			// a conta é de banco. Setar dados da conta do cheque no lançamento.
				lancamentoVO.setBancoCheque(contaBancoVO.getBancoVO().getSigla());
				lancamentoVO.setAgenciaCheque(contaBancoVO.getNumAgencia());
				lancamentoVO.setDigitoAgenciaCheque(contaBancoVO.getDigAgencia());
				lancamentoVO.setContaCheque(contaBancoVO.getNumConta());
				lancamentoVO.setDigitoContaCheque(contaBancoVO.getDigConta());
			}
		}
		// LANÇAMENTO PARA CREDITAR NA CONTA DESTINO:
		this.efetuarLancamento(lancamentoVO);

		// LANÇAMENTO REALIZADO.
	}

	/**
	 * Busca por filtro de lançamentos.
	 * @param assembler Objeto que encapsula os dados de filtro
	 * @return Coleção de lançamentos buscados
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public LancamentoAssembler findByFilter(LancamentoFiltroAssembler assembler) throws SmartEnvException, SmartAppException {
		this.validateFieldsFilter(assembler);
		
		assembler.setFlgEfetivadoOuPrevisto(new Boolean(false));
		
		// testar checkboxes
		
		// Se nenhum dos checkboxes (previsao ou efetivação) foi marcado
		if ((assembler.getFlgEfetivacao() == null || assembler.getFlgEfetivacao().equals(""))&&(assembler.getFlgPrevisao() == null || assembler.getFlgPrevisao().equals(""))) {
			
			// SETAR data de previsão
			
			assembler.setDataPrevisaoInicial(assembler.getDataEfetivacaoInicial());
			assembler.setDataPrevisaoFinal(assembler.getDataEfetivacaoFinal());
			
			// SETAR para true o flag "Efetivado ou Previsto"
		
			assembler.setFlgEfetivadoOuPrevisto(new Boolean(true));
			
		} else if (assembler.getFlgEfetivacao() == null || assembler.getFlgEfetivacao().equals("")) {
			// Neste caso, apenas o flag PREVISTO foi marcado
			
			// SETAR data de previsão
			assembler.setDataPrevisaoInicial(assembler.getDataEfetivacaoInicial());
			assembler.setDataPrevisaoFinal(assembler.getDataEfetivacaoFinal());

			// limpar data de efetivação
			assembler.setDataEfetivacaoInicial(null);
			assembler.setDataEfetivacaoFinal(null);

			
		} else  if (!((assembler.getFlgEfetivacao() == null || assembler.getFlgEfetivacao().equals(""))||(assembler.getFlgPrevisao() == null || assembler.getFlgPrevisao().equals("")))) {
			// Neste caso, os dois flags estão preenchidos.
			
			// SETAR data de previsão
			
			assembler.setDataPrevisaoInicial(assembler.getDataEfetivacaoInicial());
			assembler.setDataPrevisaoFinal(assembler.getDataEfetivacaoFinal());
			
		}
		
		LancamentoAssembler lancamentoAssembler = new LancamentoAssembler();
		
		lancamentoAssembler.setLancamentos(lancamentoDAO.findByFilter(assembler));
		
		Iterator iterator = lancamentoAssembler.getLancamentos().iterator();
		
		LancamentoVO lancamentoVO = new LancamentoVO();
		
		while (iterator.hasNext())		
			lancamentoVO = (LancamentoVO)iterator.next();
			
		lancamentoAssembler.setDiretorFinanceiroVO( diretorFinanceiroRN.findByDate(lancamentoVO.getDataEfetivacao()==null?lancamentoVO.getDataPrevisao():lancamentoVO.getDataEfetivacao()) );
		
		if (assembler.getDataEfetivacaoInicial() == null) {
			assembler.setDataEfetivacaoInicial(assembler.getDataPrevisaoInicial());
			assembler.setDataEfetivacaoFinal(assembler.getDataPrevisaoFinal());
		}
		
		assembler.setDataPrevisaoInicial(null);
		assembler.setDataPrevisaoFinal(null);
		
		return lancamentoAssembler;
	}

	/**
	 * Valida os campos passados para operação de movimentação de valores entre contas pelo objeto assembler.
	 * @param assembler Objeto que encapsula os dados passados para a operação de movimentação
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	private void validateFieldsMovimentacao(MovimentacaoAssembler assembler) throws SmartEnvException, SmartAppException {
		if (assembler.getContaOrigemVO() == null || assembler.getContaOrigemVO().getCodigo() == null || assembler.getContaOrigemVO().getCodigo().intValue() == 0) {
			throw new SmartAppException("A conta origem deve ser informada.");
		}

		if (assembler.getContaDestinoVO() == null || assembler.getContaDestinoVO().getCodigo() == null || assembler.getContaDestinoVO().getCodigo().intValue() == 0) {
			throw new SmartAppException("A conta destino deve ser informada.");
		}
		
		if (assembler.getContaDestinoVO().getCodigo().intValue() == assembler.getContaOrigemVO().getCodigo().intValue()) {
			throw new SmartAppException("A conta origem e a conta destino não podem ser a mesma.");
		}

		if (assembler.getValor() == null || assembler.getValor().doubleValue() <= 0) {
			throw new SmartAppException("O valor deve ser informado e maior do que 0 (zero).");
		}

		if (assembler.getData() == null || assembler.getData().after(dataRN.getCurrentDate())) {
			throw new SmartAppException("A data da movimentação deve ser informada e deve ser menor ou igual à data atual.");
		}
		
		if (assembler.getFormaPagamentoVO() == null || assembler.getFormaPagamentoVO().getCodigo() == null) {
			throw new SmartAppException("A forma de pagamento deve ser informada.");
		}
		
		// Testando se a forma de pagamento é "CHEQUE".
		ParametroVO parametroVO	= new ParametroVO("FORMA_PAGTO_CHEQUE");
		parametroVO	= parametroRN.findByPrimaryKey(parametroVO);
		
		if (assembler.getFormaPagamentoVO().getCodigo().toString().equals(parametroVO.getValorTextual())) {
			// a forma de pagamento é CHEQUE, o campo número do cheque deve ser informado.
			
			if (assembler.getNumeroCheque() == null || assembler.getNumeroCheque().equals("")) {
				throw new SmartAppException("O número do cheque deve ser informado.");
			}
		}
	}

	/**
	 * Valida os campos passados para operação de baixa de lançamento.
	 * @param vo Objeto que encapsula os dados passados para a operação de baixa
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	private void validateFieldsBaixa(BaixaLancamentoVO vo) throws SmartEnvException, SmartAppException {
		// Testando a data da baixa
		if (vo.getData() == null) {
			throw new SmartAppException("A data deve ser informada.");
		}
		else {
			if (vo.getData().after(dataRN.getCurrentDate())) {
				throw new SmartAppException("A data deve ser menor ou igual a hoje.");				
			}			
		}
			
		// Testando o Lançamento
		if (vo.getLancamentoVO() == null || vo.getLancamentoVO().getCodigo() == null || vo.getLancamentoVO().getCodigo().intValue() == 0) {
			throw new SmartAppException("O Lançamento deve ser informado.");
		}

		// Testando o valor
		if (vo.getValor() == null || vo.getValor().doubleValue() <= 0) {
			throw new SmartAppException("O valor deve ser informado e maior do que 0 (zero).");
		}
		
	
		// Testando a presença da forma de pagamento
		if (vo.getFormaPagamentoVO() == null || vo.getFormaPagamentoVO().getCodigo() == null) {
			throw new SmartAppException("A forma de pagamento deve ser informada.");
		}
		
		// Testando se a forma de pagamento é "CHEQUE".
		ParametroVO parametroVO	= new ParametroVO("FORMA_PAGTO_CHEQUE");
		parametroVO	= parametroRN.findByPrimaryKey(parametroVO);
		
		if (vo.getFormaPagamentoVO().getCodigo().toString().equals(parametroVO.getValorTextual())) {
		// forma de pagamento é CHEQUE. Número do cheque obrigatório.
			if (vo.getNumeroCheque() == null || vo.getNumeroCheque().equals("")) {
				throw new SmartAppException("O número do cheque deve ser informado.");
			}
			
			parametroVO.setNome("TP_OPERACAO_A_CREDITAR");
			parametroVO	= parametroRN.findByPrimaryKey(parametroVO);
			
			TipoOperacaoVO aCreditar	= new TipoOperacaoVO(new Integer(parametroVO.getValorTextual()));
			
		
			parametroVO.setNome("TP_OPERACAO_CREDITO");
			parametroVO	= parametroRN.findByPrimaryKey(parametroVO);

			TipoOperacaoVO credito		= new TipoOperacaoVO(new Integer(parametroVO.getValorTextual()));
			
			LancamentoVO lancamentoTmp	= lancamentoDAO.findByPrimaryKey(vo.getLancamentoVO());
			
			vo.getLancamentoVO().setTipoOperacaoVO(lancamentoTmp.getTipoOperacaoVO());
			vo.getLancamentoVO().setContaVO(lancamentoTmp.getContaVO());

			if (vo.getLancamentoVO().getTipoOperacaoVO().getCodigo().intValue() == aCreditar.getCodigo().intValue() || vo.getLancamentoVO().getTipoOperacaoVO().getCodigo().intValue() == credito.getCodigo().intValue()) {
					
				// a operação é de crédito. Dados de identificação da origem do cheque (no mínimo o banco, agência e a conta com dígito verificador) devem ser informados.
				if (vo.getBancoCheque()==null || vo.getBancoCheque().equals("")) {
					throw new SmartAppException("O banco do cheque deve ser informado.");
					
				}
				
				if (vo.getAgenciaCheque()==null || vo.getAgenciaCheque().equals("")) {
					throw new SmartAppException("A agência do cheque deve ser informada.");
					
				}

				if (vo.getContaCheque()==null || vo.getContaCheque().equals("")) {
					throw new SmartAppException("A conta do cheque deve ser informada.");
					
				}
				
				if (vo.getDigitoContaCheque()==null || vo.getDigitoContaCheque().equals("")) {
					throw new SmartAppException("O dígito verificador da conta do cheque deve ser informado.");
					
				}
			} else {
				
				// a operação é de débito. Verificar se a conta informada é uma conta de banco.
				ContaBancoVO contaBancoVO = contaRN.findByPrimaryKey(vo.getLancamentoVO().getContaVO());
				if (contaBancoVO.getFlgContaCaixa()==null || !contaBancoVO.getFlgContaCaixa().equals("S")) {
				// a conta é de banco. Setar dados da conta na baixa de lançamento.
					vo.setBancoCheque(contaBancoVO.getBancoVO().getSigla());
					vo.setAgenciaCheque(contaBancoVO.getNumAgencia());
					vo.setDigitoAgenciaCheque(contaBancoVO.getDigAgencia());
					vo.setContaCheque(contaBancoVO.getNumConta());
					vo.setDigitoContaCheque(contaBancoVO.getDigConta());
					
				} else {
				// a conta associada à baixa não é de banco. Não é possível realizar débito em cheque sem conhecer o banco.
					throw new SmartAppException("Não é possível escolher a forma de pagamento CHEQUE para realizar baixa em um lançamento de débito ou a debitar de uma conta caixa.");
				}
			}
		}
	}

	/**
	 * Valida os campos passados para operação efetuar lançamento.
	 * @param vo Objeto que encapsula os dados passados para a operação efetuar lançamento
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	private void validateFieldsLancamento(LancamentoVO vo) throws SmartEnvException, SmartAppException {
		// Testando a Conta
		if (vo.getContaVO() == null || vo.getContaVO().getCodigo() == null || vo.getContaVO().getCodigo().intValue() == 0) {
			throw new SmartAppException("A conta deve ser informada.");
		}

		// Testando o Tipo de Operação
		if (vo.getTipoOperacaoVO() == null || vo.getTipoOperacaoVO().getCodigo() == null) {
			throw new SmartAppException("O tipo de operação deve ser informado.");
		}
		
		//Testando a data
		String flgPrevisao = tipoOperacaoRN.findByPrimaryKey(vo.getTipoOperacaoVO()).getFlgPrevisao();
		
		if (flgPrevisao.equals("1") && vo.getDataPrevisao() == null) {
			throw new SmartAppException("A data de previsão deve ser informada.");				
		} else if (flgPrevisao.equals("0") && vo.getDataEfetivacao() == null){
			throw new SmartAppException("A data de efetivação deve ser informada.");
		}		
		
		if (flgPrevisao.equals("1")) {			
			if (vo.getDataPrevisao().before(dataRN.getCurrentDate())) {
				throw new SmartAppException("A data de previsão deve ser maior ou igual a hoje.");
			}			

			if (vo.getDataEfetivacao() != null) {
				throw new SmartAppException("A data de efetivação não pode ser informada para operações a debitar ou a creditar.");
			}
			
		} else {
			if (vo.getDataEfetivacao().after(dataRN.getCurrentDate())) {
				throw new SmartAppException("A data de efetivação deve ser menor ou igual a hoje.");				
			}	
			
			if (vo.getDataPrevisao() != null) {
				throw new SmartAppException("A data de previsão não pode ser informada para operações de crédito ou débito.");
			}
		}		
		
		// Testando a Origem de Lançamento
		if (vo.getOrigemLancamentoVO() == null || vo.getOrigemLancamentoVO().getCodigo() == null) {
			throw new SmartAppException("A Origem de lançamento deve ser informada.");			
		}
		
		// Testando Tipo de lançamento
		if (vo.getOrigemLancamentoVO().getCodigo().intValue() == new Integer(parametroRN.findByPrimaryKey(new ParametroVO("ORIGEM_USUARIO")).getValorTextual()).intValue()) {
			if (vo.getTipoLancamentoVO() == null || vo.getTipoLancamentoVO().getCodigo() == null) {
				throw new SmartAppException("O tipo de lançamento deve ser informado.");				
			}
		}
		else {
			if ((vo.getTipoLancamentoVO() != null) && (vo.getTipoLancamentoVO().getCodigo() != null)) {
				throw new SmartAppException("O tipo de lançamento não deve ser informado para esta Origem de Lançamento.");				
			}			
		}
		
		// Testando o Tipo de operação
		if (vo.getTipoOperacaoVO() == null || vo.getTipoOperacaoVO().getCodigo() == null || vo.getTipoOperacaoVO().getCodigo().intValue() == 0) {
			throw new SmartAppException("O tipo de operação deve ser informado.");			
		}

		// Testando o Valor
		if (vo.getValor() == null || vo.getValor().doubleValue() <= 0) {
			throw new SmartAppException("O valor deve ser informado e maior que 0 (zero).");			
		}
		
		// Testando tamanho de descrição
		if (vo.getDescricao() != null && vo.getDescricao().length() > 255) {
			throw new SmartAppException("A descrição não deve possuir mais do que 255 caracteres.");
		}
	}
	
	/**
	 * Valida os campos passados como filtro para a consulta de lançamentos pelo objeto assembler.
	 * @param assembler Objeto que encapsula os dados de filtro da consulta de lançamentos
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	private void validateFieldsFilter(LancamentoFiltroAssembler assembler) throws SmartEnvException, SmartAppException {
		if (assembler.getDataEfetivacaoInicial() == null) {
			throw new SmartAppException("A data inicial deve ser informada.");
		}
		else if (assembler.getDataEfetivacaoInicial().after(dataRN.getCurrentDate())) {
			throw new SmartAppException("A data inicial deve ser menor ou igual a data atual.");
		}
		
		if (assembler.getDataEfetivacaoFinal() == null) {
			throw new SmartAppException("A data final deve ser informada.");
		} else if (assembler.getDataEfetivacaoFinal().before(assembler.getDataEfetivacaoInicial())) {
			throw new SmartAppException("Período inválido.  A data inicial é maior do que a data final.");
		}
	}
	
	/**
	 * Obtém o saldo a pagar do lançamento.
	 * @param vo Objeto que representa o lançamento
	 * @return Valor do saldo a pagar
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public Double obterSaldoLancamento(LancamentoVO vo) throws SmartEnvException, SmartAppException {
		return lancamentoDAO.obterSaldoLancamento(vo);
	}

	/**
	 * Obtém o saldo a pagar do lançamento em valor absoluto.
	 * @param vo Objeto que representa o lançamento
	 * @return Valor absoluto do saldo a pagar
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public Double obterSaldoLancamentoAbsoluto(LancamentoVO vo) throws SmartEnvException, SmartAppException {
		
		Double retorno = lancamentoDAO.obterSaldoLancamento(vo);
		
		return retorno < 0 ? retorno * (-1 ) :retorno;
	}
	
	/**
	 * Remove lançamentos duplicados (inconsistentes) da base de dados.
	 * @throws SmartEnvException
	 */
	public void removeDuplicados() throws SmartEnvException {
		
		Collection<LancamentoVO> lancamentosDuplicados = lancamentoDAO.findDuplicadosInconsistentes();
		
		// TODO
	}

	public void setContaRN(ContaRN contaRN) {
		this.contaRN = contaRN;
	}

	public void setDataRN(DataRN dataRN) {
		this.dataRN = dataRN;
	}

	public void setLancamentoDAO(LancamentoDAO lancamentoDAO) {
		this.lancamentoDAO = lancamentoDAO;
	}

	public void setParametroRN(ParametroRN parametroRN) {
		this.parametroRN = parametroRN;
	}

	public void setTipoOperacaoRN(TipoOperacaoRN tipoOperacaoRN) {
		this.tipoOperacaoRN = tipoOperacaoRN;
	}

	public void setDiretorFinanceiroRN(DiretorFinanceiroRN diretorFinanceiroRN) {
		this.diretorFinanceiroRN = diretorFinanceiroRN;
	}
}