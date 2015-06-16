package br.org.asserjuf.sisjuf.financeiro.facade;

import java.util.Collection;

import br.com.falc.smartFW.exception.SmartAppException;
import br.com.falc.smartFW.exception.SmartEnvException;
import br.org.asserjuf.sisjuf.associados.convenio.FaturaVO;
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
import br.org.asserjuf.sisjuf.financeiro.LancamentoFaturaVO;
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
	 * Encapsula as regras de neg�cio de conta.
	 */
	private ContaRN 			contaRN;
	
	/**
	 * Encapsula as regras de neg�cio de banco.
	 */
	private BancoRN 			bancoRN; 
	
	/**
	 * Encapsula as regras de neg�cio de tipo de lan�amento.
	 */
	private TipoLancamentoRN 	tipoLancamentoRN; 
	
	/**
	 * Encapsula as regras de neg�cio de origem de lan�amento.
	 */
	private OrigemLancamentoRN 	origemLancamentoRN; 
	
	/**
	 * Encapsula as regras de neg�cio de tipo de opera��o
	 */
	private TipoOperacaoRN 		tipoOperacaoRN;
	
	/**
	 * Encapsula as regras de neg�cio de tipo de conta.
	 */
	private TipoContaRN 		tipoContaRN;
	
	/**
	 * Encapsula as regras de neg�cio de lan�amento.
	 */
	private LancamentoRN		lancamentoRN;

	/**
	 * Encapsula as regras de neg�cio de diretores financeiros.
	 */
	private DiretorFinanceiroRN diretorFinanceiroRN;
	
	private FormaPagamentoRN	formaPagamentoRN;
	
	
	/**
	 * Obt�m todos os bancos.
	 * @return Cole��o de todos os bancos, encapsulados na classe BancoVO.
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public Collection findAllBanco() throws SmartEnvException, SmartAppException {
		return bancoRN.findAll();
	}
	
	
	/**
	 * Obt�m um banco por chave.
	 * @param vo Inst�ncia de BancoVO que encapsula o c�digo do banco que se deseja recuperar.
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
	 * Obt�m todos os tipos de lan�amento.
	 * @return Cole��o com todos os tipos de lan�amento, encapsulados na classe TipoLancamentoVO.
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public Collection findAllTipoLancamento() throws SmartEnvException, SmartAppException {
		return tipoLancamentoRN.findAll();
	}
	
	/**
	 * Obt�m um tipo de lan�amento por chave.
	 * @param vo Inst�ncia de TipoLancamentoVO que encapsula o c�digo do tipo de lan�amento que se deseja recuperar.
	 * @return Tipo de lan�amento desejado.
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public TipoLancamentoVO findTipoLancamentoByPrimaryKey(TipoLancamentoVO vo) throws SmartEnvException, SmartAppException {
		return tipoLancamentoRN.findByPrimaryKey(vo);
	}
	
	/**
	 * Atualiza um tipo de lan�amento.
	 * @param vo Tipo de lan�amento que se deseja atualizar.
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public void updateTipoLancamento(TipoLancamentoVO vo) throws SmartEnvException, SmartAppException {
		tipoLancamentoRN.update(vo);
	}
	
	/**
	 * Insere um tipo de lan�amento
	 * @param vo Tipo de lan�amento que se deseja inserir.
	 * @return Tipo de lan�amento inserido.
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public TipoLancamentoVO insertTipoLancamento(TipoLancamentoVO vo) throws SmartEnvException, SmartAppException {
		return tipoLancamentoRN.insert(vo);
	}

	/**
	 * Exclui um tipo de lan�amento.
	 * @param vo Tipo de lan�amento que se deseja excluir.
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public void removeTipoLancamento(TipoLancamentoVO vo) throws SmartEnvException, SmartAppException {
		tipoLancamentoRN.remove(vo);
	}

	/**
	 * Obt�m todas as origens de lan�amento ativas.
	 * @return Cole��o com todas as origens de lan�amento ativas, encapsuladas na classe OrigemLancamentoVO.
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public Collection findAllOrigemLancamento() throws SmartEnvException, SmartAppException {
		return origemLancamentoRN.findAll();
		
	}
	
	
	/**
	 * Obt�m uma origem de lan�amento por chave.
	 * @param vo Inst�ncia de OrigemLancamentoVO que encapsula o c�digo da origem de lan�amento desejada.
	 * @return Origem de lan�amento desejada.
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public OrigemLancamentoVO findOrigemLancamentoByPrimaryKey(OrigemLancamentoVO vo) throws SmartEnvException, SmartAppException {
		return origemLancamentoRN.findByPrimaryKey(vo);
	}
	
	/**
	 * Atualiza uma origem de lan�amento
	 * @param vo Origem de lan�amento que se deseja atualizar.
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public void updateOrigemLancamento(OrigemLancamentoVO vo) throws SmartEnvException, SmartAppException {
		origemLancamentoRN.update(vo);
	}
	
	/**
	 * Insere uma origem de lan�amento
	 * @param vo Origem de lan�amento que se deseja inserir.
	 * @return Origem de lan�amento inserida.
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public OrigemLancamentoVO insertOrigemLancamento(OrigemLancamentoVO vo) throws SmartEnvException, SmartAppException {
		return origemLancamentoRN.insert(vo);
	}

	
	/**
	 * Exclui origem de lan�amento.
	 * @param vo Origem de lan�amento que se deseja excluir.
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public void removeOrigemLancamento(OrigemLancamentoVO vo) throws SmartEnvException, SmartAppException {
		origemLancamentoRN.remove(vo);
	}

	/**
	 * Obt�m todos os tipos de opera��o.
	 * @return Cole��o de tipos de opera��o, encapsuladas na classe TipoOperacaoVO.
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public Collection findAllTipoOperacao() throws SmartEnvException, SmartAppException {
		return tipoOperacaoRN.findAll();
	}

	
	/**
	 * Obter tipo de opera��o por chave.
	 * @param vo Inst�ncia de TipoOperacaoVO que encapsula o c�digo do tipo de opera��o que se deseja obter.
	 * @return Tipo de opera��o desejado.
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public TipoOperacaoVO findTipoOperacaoByPrimaryKey(TipoOperacaoVO vo) throws SmartEnvException, SmartAppException {
		return tipoOperacaoRN.findByPrimaryKey(vo);
	}

	/**
	 * Obt�m todos os tipos de conta.
	 * @return Cole��o de tipos de conta, encapsuladas na classe.
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public Collection findAllTipoConta() throws SmartEnvException, SmartAppException {
		return tipoContaRN.findAll();
	}
	
	/**
	 * Obt�m todas as contas.
	 * @return Cole��o de contas, encapsuladas na classe ContaBancoVO. 
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public Collection findAllConta() throws SmartEnvException, SmartAppException {
		return contaRN.findAll();
		
	}
	
	/**
	 * Obt�m conta por chave.
	 * @param vo Inst�ncia de ContaVO que encapsula o c�digo da conta que se deseja obter.
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
	 * Obt�m o saldo de uma conta.
	 * @param vo Conta cujo saldo deseja ser obtido.
	 * @return Conta, contendo o saldo.
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public ContaVO obterSaldoConta(ContaVO vo) throws SmartEnvException, SmartAppException {
		return contaRN.obterSaldo(vo);
	}
	
	/**
	 * Obt�m o extrato de uma conta por filtro.
	 * @param assembler Objeto com os filtros a serem aplicados: conta, data in�cio e data fim.
	 * @return Inst�ncia de ExtratoAssembler, que encapsula o saldo inicial, o saldo final e a cole��o de baixas de lan�amento, encapsuladas no objeto BaixaLancamentoVO.
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public ExtratoAssembler obterExtratoConta(ExtratoFiltroAssembler assembler) throws SmartEnvException, SmartAppException {
		return contaRN.obterExtrato(assembler);
	}

	/**
	 * Movimenta valores entre duas contas.
	 * @param assembler Objeto que encapsula os dados da movimenta��o: conta origem, conta destino, valor e data.
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public void movimentarValoresEntreContas(MovimentacaoAssembler assembler) throws SmartEnvException, SmartAppException {
		lancamentoRN.movimentarValoresEntreContas(assembler);
	}
	
	/**
	 * Efetua a baixa de um lan�amento.
	 * @param vo Baixa de lan�amento que se deseja realizar.
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public void baixarLancamento(BaixaLancamentoVO vo) throws SmartEnvException, SmartAppException {
		lancamentoRN.baixarLancamento(vo);
	}
	
	/**
	 * Estorna um lan�amento.
	 * @param vo Lan�amento que se deseja estornar.
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public void estornarLancamento(LancamentoVO vo) throws SmartEnvException, SmartAppException {
		lancamentoRN.estornarLancamento(vo);
	}
	
	/**
	 * Efetua um lan�amento.
	 * @param vo Lan�amento que se deseja efetuar.
	 * @return Lan�amento efetuado.
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public LancamentoVO efetuarLancamento(LancamentoVO vo) throws SmartEnvException, SmartAppException {
		return lancamentoRN.efetuarLancamento(vo);
	}
	
	/**
	 * Obt�m lan�amentos por filtro.
	 * @param assembler Objeto que encapsula os poss�veis filtros de pesquisa.
	 * @return Cole��o de lan�amentos encapsulados na classe LancamentoVO.
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public LancamentoAssembler findLancamentoByFilter(LancamentoFiltroAssembler assembler) throws SmartEnvException, SmartAppException {
		return lancamentoRN.findByFilter(assembler);
	}
	
	/**
	 * Obt�m saldo de um lan�amento.
	 * @param vo Lan�amento cujo saldo deseja ser obtido.
	 * @return Valor do saldo do lan�amento passado como par�metro.
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public Double obterSaldoLancamento(LancamentoVO vo) throws SmartEnvException, SmartAppException {
		return lancamentoRN.obterSaldoLancamento(vo);
	}
	
	/**
	 * Obt�m saldo (em valor absoluto) de um lan�amento.
	 * @param vo Lan�amento cujo saldo deseja ser obtido.
	 * @return Valor absoluto do saldo do lan�amento passado como par�metro.
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public Double obterSaldoLancamentoAbsoluto(LancamentoVO vo) throws SmartEnvException, SmartAppException {
		return lancamentoRN.obterSaldoLancamentoAbsoluto(vo);
	}
	
	/**
	 * Obt�m lan�amento por chave.
	 * @param vo Objeto contendo o c�digo do lan�amento que se deseja obter.
	 * @return Lan�amento desejado.
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
	
	public void removeLancamentosDuplicados() throws SmartEnvException {
		lancamentoRN.removeDuplicados();
	}


	/**
	 * Obt�m uma cole��o de lan�amentos a partir de uma fatura. Tabelas: LANCAMENTO, LANCAMENTO_FATURA, LANCAMENTO_ITEM_FATURA   
	 * @param fatura Inst�ncia da classe FaturaVO, que encapsula uma fatura.
	 * @return Cole��o de lan�amentos, encapsulados da classe LancamentoVO.
	 * @throws SmartEnvException
	 */
	public Collection<LancamentoFaturaVO> findLancamentoByFatura(FaturaVO fatura) throws SmartEnvException {
		return lancamentoRN.findByFatura(fatura);
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