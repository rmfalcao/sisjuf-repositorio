package br.org.asserjuf.sisjuf.financeiro.facade;

import java.util.Collection;

import br.com.falc.smartFW.exception.SmartAppException;
import br.com.falc.smartFW.exception.SmartEnvException;
import br.org.asserjuf.sisjuf.financeiro.BaixaLancamentoVO;
import br.org.asserjuf.sisjuf.financeiro.BancoRN;
import br.org.asserjuf.sisjuf.financeiro.BancoVO;
import br.org.asserjuf.sisjuf.financeiro.ContaBancoVO;
import br.org.asserjuf.sisjuf.financeiro.ContaRN;
import br.org.asserjuf.sisjuf.financeiro.ContaVO;
import br.org.asserjuf.sisjuf.financeiro.DiretorFinanceiroRN;
import br.org.asserjuf.sisjuf.financeiro.DiretorFinanceiroVO;
import br.org.asserjuf.sisjuf.financeiro.ExtratoAssembler;
import br.org.asserjuf.sisjuf.financeiro.ExtratoFiltroAssembler;
import br.org.asserjuf.sisjuf.financeiro.FormaPagamentoRN;
import br.org.asserjuf.sisjuf.financeiro.LancamentoAssembler;
import br.org.asserjuf.sisjuf.financeiro.LancamentoFiltroAssembler;
import br.org.asserjuf.sisjuf.financeiro.LancamentoRN;
import br.org.asserjuf.sisjuf.financeiro.LancamentoVO;
import br.org.asserjuf.sisjuf.financeiro.MovimentacaoAssembler;
import br.org.asserjuf.sisjuf.financeiro.OrigemLancamentoRN;
import br.org.asserjuf.sisjuf.financeiro.OrigemLancamentoVO;
import br.org.asserjuf.sisjuf.financeiro.TipoContaRN;
import br.org.asserjuf.sisjuf.financeiro.TipoLancamentoRN;
import br.org.asserjuf.sisjuf.financeiro.TipoLancamentoVO;
import br.org.asserjuf.sisjuf.financeiro.TipoOperacaoRN;
import br.org.asserjuf.sisjuf.financeiro.TipoOperacaoVO;

public class FinanceiroFacade {

	/**
	 * Encapsula as regras de negócio de conta.
	 */
	private ContaRN 			contaRN;
	
	/**
	 * Encapsula as regras de negócio de banco.
	 */
	private BancoRN 			bancoRN; 
	
	/**
	 * Encapsula as regras de negócio de tipo de lançamento.
	 */
	private TipoLancamentoRN 	tipoLancamentoRN; 
	
	/**
	 * Encapsula as regras de negócio de origem de lançamento.
	 */
	private OrigemLancamentoRN 	origemLancamentoRN; 
	
	/**
	 * Encapsula as regras de negócio de tipo de operação
	 */
	private TipoOperacaoRN 		tipoOperacaoRN;
	
	/**
	 * Encapsula as regras de negócio de tipo de conta.
	 */
	private TipoContaRN 		tipoContaRN;
	
	/**
	 * Encapsula as regras de negócio de lançamento.
	 */
	private LancamentoRN		lancamentoRN;

	/**
	 * Encapsula as regras de negócio de diretores financeiros.
	 */
	private DiretorFinanceiroRN diretorFinanceiroRN;
	
	private FormaPagamentoRN	formaPagamentoRN;
	
	
	/**
	 * Obtém todos os bancos.
	 * @return Coleção de todos os bancos, encapsulados na classe BancoVO.
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public Collection findAllBanco() throws SmartEnvException, SmartAppException {
		return bancoRN.findAll();
	}
	
	
	/**
	 * Obtém um banco por chave.
	 * @param vo Instância de BancoVO que encapsula o código do banco que se deseja recuperar.
	 * @return Banco desejado.
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public BancoVO findBancoByPrimaryKey(BancoVO vo) throws SmartEnvException, SmartAppException {
		return bancoRN.findByPrimaryKey(vo);
	}
	
	
	/**
	 * Atualiza um banco.
	 * @param vo Banco que se deseja atualizar.
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public void updateBanco(BancoVO vo) throws SmartEnvException, SmartAppException {
		bancoRN.update(vo);
	}
	
	
	/**
	 * Exclui um banco.
	 * @param vo Banco que se deseja excluir.
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public void removeBanco(BancoVO vo) throws SmartEnvException, SmartAppException {
		bancoRN.remove(vo);
	}

	
	/**
	 * Insere um banco.
	 * @param vo Banco que se deseja inserir.
	 * @return Banco que foi inserido.
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public BancoVO insertBanco(BancoVO vo) throws SmartEnvException, SmartAppException {
		return bancoRN.insert(vo);
	}

	
	/**
	 * Obtém todos os tipos de lançamento.
	 * @return Coleção com todos os tipos de lançamento, encapsulados na classe TipoLancamentoVO.
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public Collection findAllTipoLancamento() throws SmartEnvException, SmartAppException {
		return tipoLancamentoRN.findAll();
	}
	
	/**
	 * Obtém um tipo de lançamento por chave.
	 * @param vo Instância de TipoLancamentoVO que encapsula o código do tipo de lançamento que se deseja recuperar.
	 * @return Tipo de lançamento desejado.
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public TipoLancamentoVO findTipoLancamentoByPrimaryKey(TipoLancamentoVO vo) throws SmartEnvException, SmartAppException {
		return tipoLancamentoRN.findByPrimaryKey(vo);
	}
	
	/**
	 * Atualiza um tipo de lançamento.
	 * @param vo Tipo de lançamento que se deseja atualizar.
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public void updateTipoLancamento(TipoLancamentoVO vo) throws SmartEnvException, SmartAppException {
		tipoLancamentoRN.update(vo);
	}
	
	/**
	 * Insere um tipo de lançamento
	 * @param vo Tipo de lançamento que se deseja inserir.
	 * @return Tipo de lançamento inserido.
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public TipoLancamentoVO insertTipoLancamento(TipoLancamentoVO vo) throws SmartEnvException, SmartAppException {
		return tipoLancamentoRN.insert(vo);
	}

	/**
	 * Exclui um tipo de lançamento.
	 * @param vo Tipo de lançamento que se deseja excluir.
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public void removeTipoLancamento(TipoLancamentoVO vo) throws SmartEnvException, SmartAppException {
		tipoLancamentoRN.remove(vo);
	}

	/**
	 * Obtém todas as origens de lançamento ativas.
	 * @return Coleção com todas as origens de lançamento ativas, encapsuladas na classe OrigemLancamentoVO.
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public Collection findAllOrigemLancamento() throws SmartEnvException, SmartAppException {
		return origemLancamentoRN.findAll();
		
	}
	
	
	/**
	 * Obtém uma origem de lançamento por chave.
	 * @param vo Instância de OrigemLancamentoVO que encapsula o código da origem de lançamento desejada.
	 * @return Origem de lançamento desejada.
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public OrigemLancamentoVO findOrigemLancamentoByPrimaryKey(OrigemLancamentoVO vo) throws SmartEnvException, SmartAppException {
		return origemLancamentoRN.findByPrimaryKey(vo);
	}
	
	/**
	 * Atualiza uma origem de lançamento
	 * @param vo Origem de lançamento que se deseja atualizar.
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public void updateOrigemLancamento(OrigemLancamentoVO vo) throws SmartEnvException, SmartAppException {
		origemLancamentoRN.update(vo);
	}
	
	/**
	 * Insere uma origem de lançamento
	 * @param vo Origem de lançamento que se deseja inserir.
	 * @return Origem de lançamento inserida.
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public OrigemLancamentoVO insertOrigemLancamento(OrigemLancamentoVO vo) throws SmartEnvException, SmartAppException {
		return origemLancamentoRN.insert(vo);
	}

	
	/**
	 * Exclui origem de lançamento.
	 * @param vo Origem de lançamento que se deseja excluir.
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public void removeOrigemLancamento(OrigemLancamentoVO vo) throws SmartEnvException, SmartAppException {
		origemLancamentoRN.remove(vo);
	}

	/**
	 * Obtém todos os tipos de operação.
	 * @return Coleção de tipos de operação, encapsuladas na classe TipoOperacaoVO.
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public Collection findAllTipoOperacao() throws SmartEnvException, SmartAppException {
		return tipoOperacaoRN.findAll();
	}

	
	/**
	 * Obter tipo de operação por chave.
	 * @param vo Instância de TipoOperacaoVO que encapsula o código do tipo de operação que se deseja obter.
	 * @return Tipo de operação desejado.
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public TipoOperacaoVO findTipoOperacaoByPrimaryKey(TipoOperacaoVO vo) throws SmartEnvException, SmartAppException {
		return tipoOperacaoRN.findByPrimaryKey(vo);
	}

	/**
	 * Obtém todos os tipos de conta.
	 * @return Coleção de tipos de conta, encapsuladas na classe.
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public Collection findAllTipoConta() throws SmartEnvException, SmartAppException {
		return tipoContaRN.findAll();
	}
	
	/**
	 * Obtém todas as contas.
	 * @return Coleção de contas, encapsuladas na classe ContaBancoVO. 
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public Collection findAllConta() throws SmartEnvException, SmartAppException {
		return contaRN.findAll();
		
	}
	
	/**
	 * Obtém conta por chave.
	 * @param vo Instância de ContaVO que encapsula o código da conta que se deseja obter.
	 * @return Conta desejada.
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public ContaBancoVO findContaByPrimaryKey(ContaVO vo) throws SmartEnvException, SmartAppException {
		return contaRN.findByPrimaryKey(vo); 
	}
	
	/**
	 * Atualiza uma conta.
	 * @param vo Conta que se deseja atualizar.
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public void updateConta(ContaBancoVO vo) throws SmartEnvException, SmartAppException {
		contaRN.update(vo);
	}
	
	/**
	 * Inserir uma conta.
	 * @param vo Conta que se deseja inserir.
	 * @return Conta inserida.
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public ContaVO insertConta(ContaBancoVO vo) throws SmartEnvException, SmartAppException {
		return contaRN.insert(vo);
	}
	
	/**
	 * Exclui uma conta.
	 * @param vo Conta que se deseja excluir.
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public void removeConta(ContaVO vo) throws SmartEnvException, SmartAppException {
		contaRN.remove(vo);
	}
	
	/**
	 * Obtém o saldo de uma conta.
	 * @param vo Conta cujo saldo deseja ser obtido.
	 * @return Conta, contendo o saldo.
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public ContaVO obterSaldoConta(ContaVO vo) throws SmartEnvException, SmartAppException {
		return contaRN.obterSaldo(vo);
	}
	
	/**
	 * Obtém o extrato de uma conta por filtro.
	 * @param assembler Objeto com os filtros a serem aplicados: conta, data início e data fim.
	 * @return Instância de ExtratoAssembler, que encapsula o saldo inicial, o saldo final e a coleção de baixas de lançamento, encapsuladas no objeto BaixaLancamentoVO.
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public ExtratoAssembler obterExtratoConta(ExtratoFiltroAssembler assembler) throws SmartEnvException, SmartAppException {
		return contaRN.obterExtrato(assembler);
	}

	/**
	 * Movimenta valores entre duas contas.
	 * @param assembler Objeto que encapsula os dados da movimentação: conta origem, conta destino, valor e data.
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public void movimentarValoresEntreContas(MovimentacaoAssembler assembler) throws SmartEnvException, SmartAppException {
		lancamentoRN.movimentarValoresEntreContas(assembler);
	}
	
	/**
	 * Efetua a baixa de um lançamento.
	 * @param vo Baixa de lançamento que se deseja realizar.
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public void baixarLancamento(BaixaLancamentoVO vo) throws SmartEnvException, SmartAppException {
		lancamentoRN.baixarLancamento(vo);
	}
	
	/**
	 * Estorna um lançamento.
	 * @param vo Lançamento que se deseja estornar.
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public void estornarLancamento(LancamentoVO vo) throws SmartEnvException, SmartAppException {
		lancamentoRN.estornarLancamento(vo);
	}
	
	/**
	 * Efetua um lançamento.
	 * @param vo Lançamento que se deseja efetuar.
	 * @return Lançamento efetuado.
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public LancamentoVO efetuarLancamento(LancamentoVO vo) throws SmartEnvException, SmartAppException {
		return lancamentoRN.efetuarLancamento(vo);
	}
	
	/**
	 * Obtém lançamentos por filtro.
	 * @param assembler Objeto que encapsula os possíveis filtros de pesquisa.
	 * @return Coleção de lançamentos encapsulados na classe LancamentoVO.
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public LancamentoAssembler findLancamentoByFilter(LancamentoFiltroAssembler assembler) throws SmartEnvException, SmartAppException {
		return lancamentoRN.findByFilter(assembler);
	}
	
	/**
	 * Obtém saldo de um lançamento.
	 * @param vo Lançamento cujo saldo deseja ser obtido.
	 * @return Valor do saldo do lançamento passado como parâmetro.
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public Double obterSaldoLancamento(LancamentoVO vo) throws SmartEnvException, SmartAppException {
		return lancamentoRN.obterSaldoLancamento(vo);
	}
	
	/**
	 * Obtém saldo (em valor absoluto) de um lançamento.
	 * @param vo Lançamento cujo saldo deseja ser obtido.
	 * @return Valor absoluto do saldo do lançamento passado como parâmetro.
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public Double obterSaldoLancamentoAbsoluto(LancamentoVO vo) throws SmartEnvException, SmartAppException {
		return lancamentoRN.obterSaldoLancamentoAbsoluto(vo);
	}
	
	/**
	 * Obtém lançamento por chave.
	 * @param vo Objeto contendo o código do lançamento que se deseja obter.
	 * @return Lançamento desejado.
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public LancamentoVO findLancamentoByPrimaryKey(LancamentoVO vo) throws SmartEnvException, SmartAppException {
		return lancamentoRN.findByPrimaryKey(vo);
	}
	
	
	public Collection findAllDiretorFinanceiro() throws SmartEnvException, SmartAppException {
		return diretorFinanceiroRN.findAll();
	}
	
	
	public DiretorFinanceiroVO findDiretorFinanceiroByPrimaryKey(DiretorFinanceiroVO vo) throws SmartEnvException, SmartAppException {
		return diretorFinanceiroRN.findByPrimaryKey(vo);
	}
	
	public void updateDiretorFinanceiro(DiretorFinanceiroVO vo) throws SmartEnvException, SmartAppException {
		diretorFinanceiroRN.update(vo);
	}
	

	public DiretorFinanceiroVO insertDiretorFinanceiro(DiretorFinanceiroVO vo) throws SmartEnvException, SmartAppException {
		return diretorFinanceiroRN.insert(vo);
	}

	public void removeDiretorFinanceiro(DiretorFinanceiroVO vo) throws SmartEnvException, SmartAppException {
		diretorFinanceiroRN.remove(vo);
	}
	
	public Collection findAllFormaPagamento() throws SmartEnvException, SmartAppException {
		return formaPagamentoRN.findAll();
	}


	public void setBancoRN(BancoRN bancoRN) {
		this.bancoRN = bancoRN;
	}


	public void setContaRN(ContaRN contaRN) {
		this.contaRN = contaRN;
	}


	public void setDiretorFinanceiroRN(DiretorFinanceiroRN diretorFinanceiroRN) {
		this.diretorFinanceiroRN = diretorFinanceiroRN;
	}


	public void setFormaPagamentoRN(FormaPagamentoRN formaPagamentoRN) {
		this.formaPagamentoRN = formaPagamentoRN;
	}


	public void setLancamentoRN(LancamentoRN lancamentoRN) {
		this.lancamentoRN = lancamentoRN;
	}


	public void setOrigemLancamentoRN(OrigemLancamentoRN origemLancamentoRN) {
		this.origemLancamentoRN = origemLancamentoRN;
	}


	public void setTipoContaRN(TipoContaRN tipoContaRN) {
		this.tipoContaRN = tipoContaRN;
	}


	public void setTipoLancamentoRN(TipoLancamentoRN tipoLancamentoRN) {
		this.tipoLancamentoRN = tipoLancamentoRN;
	}


	public void setTipoOperacaoRN(TipoOperacaoRN tipoOperacaoRN) {
		this.tipoOperacaoRN = tipoOperacaoRN;
	}
}