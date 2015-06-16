package br.org.asserjuf.sisjuf.financeiro.web.cliente;

import java.util.Collection;

import org.apache.log4j.Logger;

import br.com.falc.smartFW.exception.SmartAppException;
import br.com.falc.smartFW.exception.SmartEnvException;
import br.org.asserjuf.sisjuf.associados.convenio.FaturaVO;
import br.org.asserjuf.sisjuf.financeiro.BaixaLancamentoVO;
import br.org.asserjuf.sisjuf.financeiro.BancoVO;
import br.org.asserjuf.sisjuf.financeiro.ContaBancoVO;
import br.org.asserjuf.sisjuf.financeiro.ContaVO;
import br.org.asserjuf.sisjuf.financeiro.DiretorFinanceiroVO;
import br.org.asserjuf.sisjuf.financeiro.ExtratoAssembler;
import br.org.asserjuf.sisjuf.financeiro.ExtratoFiltroAssembler;
import br.org.asserjuf.sisjuf.financeiro.LancamentoAssembler;
import br.org.asserjuf.sisjuf.financeiro.LancamentoFaturaVO;
import br.org.asserjuf.sisjuf.financeiro.LancamentoFiltroAssembler;
import br.org.asserjuf.sisjuf.financeiro.LancamentoVO;
import br.org.asserjuf.sisjuf.financeiro.MovimentacaoAssembler;
import br.org.asserjuf.sisjuf.financeiro.OrigemLancamentoVO;
import br.org.asserjuf.sisjuf.financeiro.TipoLancamentoVO;
import br.org.asserjuf.sisjuf.financeiro.TipoOperacaoVO;
import br.org.asserjuf.sisjuf.financeiro.facade.FinanceiroFacade;

/**
 * Implementa��o da classe que representa o "proxy" para o fa�ade Financeiro.
 * @author Rodrigo Falc�o
 *
 */
public class FinanceiroDelegate {
	
	private static transient Logger LOG = Logger.getLogger(FinanceiroDelegate.class);
	
	/**
	 * Fa�ace Financeiro do Sisjuf.
	 */
	private FinanceiroFacade financeiroBean;
	
	/**
	 * "Proxy" para o m�todo findAllBanco() no fa�ade Financeiro
	 * @return Collection de BancoVO
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public Collection findAllBanco() throws SmartEnvException, SmartAppException {
		LOG.debug("financeiroBean " + financeiroBean);
		return financeiroBean.findAllBanco();
	}
	
	/**
	 * "Proxy" para o m�todo findBancoByPrimaryKey() no fa�ade Financeiro.
	 * @param vo 
	 * @return BancoVO
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public BancoVO findBancoByPrimaryKey(BancoVO vo) throws SmartEnvException, SmartAppException {
		return financeiroBean.findBancoByPrimaryKey(vo);
	}
	
	/**
	 * "Proxy" para o m�todo updateBanco() no fa�ade Financeiro.
	 * @param vo
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public void updateBanco(BancoVO vo) throws SmartEnvException, SmartAppException {
		financeiroBean.updateBanco(vo);
	}
	
	/**
	 * "Proxy" para o m�todo removeBanco() no fa�ade Financeiro.
	 * @param vo
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public void removeBanco(BancoVO vo) throws SmartEnvException, SmartAppException {
		financeiroBean.removeBanco(vo);
	}
	
	/**
	 * "Proxy" para o m�todo insertBanco() no fa�ade Financeiro.
	 * @param vo
	 * @return BancoVO 
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public BancoVO insertBanco(BancoVO vo) throws SmartEnvException, SmartAppException {
		return financeiroBean.insertBanco(vo);
	}
	
	/**
	 * "Proxy" para o m�todo findAllTipoLancamento() no fa�ade Financeiro.
	 * @return Collection de TipoLancamentoVO
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public Collection findAllTipoLancamento() throws SmartEnvException, SmartAppException {
		return financeiroBean.findAllTipoLancamento();
	}
	
	/**
	 * "Proxy" para o m�todo findTipoLancamentoByPrimaryKey() no fa�ade Financeiro.
	 * @param vo
	 * @return TipoLancamentoVO
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public TipoLancamentoVO findTipoLancamentoByPrimaryKey(TipoLancamentoVO vo) throws SmartEnvException, SmartAppException {
		return financeiroBean.findTipoLancamentoByPrimaryKey(vo);
	}
	
	/**
	 * "Proxy" para o m�todo updateTipoLancamento() no fa�ade Financeiro.
	 * @param vo
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public void updateTipoLancamento(TipoLancamentoVO vo) throws SmartEnvException, SmartAppException {
		financeiroBean.updateTipoLancamento(vo);
	}
	
	/**
	 * "Proxy" para o m�todo removeTipoLancamento() no fa�ade Financeiro.
	 * @param vo
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public void removeTipoLancamento(TipoLancamentoVO vo) throws SmartEnvException, SmartAppException {
		financeiroBean.removeTipoLancamento(vo);
	}
	
	/**
	 * "Proxy" para o m�todo insertTipoLancamento() no Fa�ade Financeiro.
	 * @param vo
	 * @return TipoLancamentoVO
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public TipoLancamentoVO insertTipoLancamento(TipoLancamentoVO vo) throws SmartEnvException, SmartAppException {
		return financeiroBean.insertTipoLancamento(vo);
	}
	
	/**
	 * "Proxy" para o m�todo findAllOrigemLancamento() no Fa�ade Financeiro.
	 * @return Collection de OrigemLancamentoVO
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public Collection findAllOrigemLancamento() throws SmartEnvException, SmartAppException {
		return financeiroBean.findAllOrigemLancamento();
	}
	
	/**
	 *  "Proxy" para o m�todo findOrigemLancamentoByPrimaryKey() no Fa�ade Financeiro.
	 * @param vo
	 * @return OrigemLancamentoVO
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public OrigemLancamentoVO findOrigemLancamentoByPrimaryKey(OrigemLancamentoVO vo) throws SmartEnvException, SmartAppException {
		return financeiroBean.findOrigemLancamentoByPrimaryKey(vo);
	}
	
	/**
	 * "Proxy" para o m�todo updateOrigemLancamento() no Fa�ade Financeiro.
	 * @param vo
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public void updateOrigemLancamento(OrigemLancamentoVO vo) throws SmartEnvException, SmartAppException {
		financeiroBean.updateOrigemLancamento(vo);
	}
	/**
	 * "Proxy" para o m�todo removeOrigemLancamento() no Fa�ade Financeiro.
	 * @param vo
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public void removeOrigemLancamento(OrigemLancamentoVO vo) throws SmartEnvException, SmartAppException {
		financeiroBean.removeOrigemLancamento(vo);
	}
	
	/**
	 * "Proxy" para o m�todo insertOrigemLancamento() no Fa�ade Financeiro.
	 * @param vo
	 * @return OrigemLancamentoVO
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public OrigemLancamentoVO insertOrigemLancamento(OrigemLancamentoVO vo) throws SmartEnvException, SmartAppException {
		return financeiroBean.insertOrigemLancamento(vo);
	}
	
	/**
	 * "Proxy" para o m�todo findAllTipoOperacao() no Fa�ade Financeiro.
	 * @return Collection de TipoOperacaoVO
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public Collection findAllTipoOperacao() throws SmartEnvException, SmartAppException {
		return financeiroBean.findAllTipoOperacao();
	}

	/**
	 * "Proxy" para o m�todo findTipoOperacaoByPrimaryKey() no Fa�ade Financeiro.
	 * @param vo
	 * @return TipoOperacaoVO
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public TipoOperacaoVO findTipoOperacaoByPrimaryKey(TipoOperacaoVO vo) throws SmartEnvException, SmartAppException {
		return financeiroBean.findTipoOperacaoByPrimaryKey(vo);
	}

	/**
	 * "Proxy" para o m�todo findAllTipoConta() no Fa�ade Financeiro.
	 * @return Collection de TipoContaVO
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public Collection findAllTipoConta() throws SmartEnvException, SmartAppException {
		return financeiroBean.findAllTipoConta();
	}
	
	/**
	 * "Proxy" para o m�todo findAllConta() no Fa�ade Financeiro.
	 * @return Collection de ContaVO
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public Collection findAllConta() throws SmartEnvException, SmartAppException {
		return financeiroBean.findAllConta();
	}
	
	/**
	 * "Proxy" para o m�todo findContaByPrimaryKey() no Fa�ade Financeiro.
	 * @param vo
	 * @return ContaBancoVO
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public ContaBancoVO findContaByPrimaryKey(ContaVO vo) throws SmartEnvException, SmartAppException {
		return financeiroBean.findContaByPrimaryKey(vo);
	}
	
	/**
	 * "Proxy" para o m�todo updateConta() no Fa�ade Financeiro.
	 * @param vo
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public void updateConta(ContaBancoVO vo) throws SmartEnvException, SmartAppException {
		financeiroBean.updateConta(vo);
	}
	
	/**
	 * "Proxy" para o m�todo insertConta() no Fa�ade Financeiro.
	 * @param vo
	 * @return ContaVO
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public ContaVO insertConta(ContaBancoVO vo) throws SmartEnvException, SmartAppException {
		return financeiroBean.insertConta(vo);
	}
	
	/**
	 * "Proxy" para o m�todo removeConta() no Fa�ade Financeiro.
	 * @param vo
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public void removeConta(ContaVO vo) throws SmartEnvException, SmartAppException {
		financeiroBean.removeConta(vo);
	}
	
	/**
	 * "Proxy" para o m�todo obterSaldoConta() no Fa�ade Financeiro.
	 * @param vo
	 * @return ContaVO
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public ContaVO obterSaldoConta(ContaVO vo) throws SmartEnvException, SmartAppException {
		
		return financeiroBean.obterSaldoConta(vo);
		
	}
	
	/**
	 * "Proxy" para o m�todo obterExtratoConta() no Fa�ade Financeiro.
	 * @param assembler
	 * @return ExtratoAssembler
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public ExtratoAssembler obterExtratoConta(ExtratoFiltroAssembler assembler) throws SmartEnvException, SmartAppException {
		return financeiroBean.obterExtratoConta(assembler);
	}

	/**
	 * "Proxy" para o m�todo movimentarValoresEntreContas() no Fa�ade Financeiro.
	 * @param assembler
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public void movimentarValoresEntreContas(MovimentacaoAssembler assembler) throws SmartEnvException, SmartAppException {
		financeiroBean.movimentarValoresEntreContas(assembler);
	}
	
	/**
	 * "Proxy" para o m�todo baixarLancamento() no Fa�ade Financeiro.
	 * @param vo
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public void baixarLancamento(BaixaLancamentoVO vo) throws SmartEnvException, SmartAppException {
		financeiroBean.baixarLancamento(vo);
	}
	
	/**
	 * "Proxy" para o m�todo estornarLancamento() no Fa�ade Financeiro.
	 * @param vo
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public void estornarLancamento(LancamentoVO vo) throws SmartEnvException, SmartAppException {
		financeiroBean.estornarLancamento(vo);
	}
	
	/**
	 * "Proxy" para o m�todo efetuarLancamento() no Fa�ade Financeiro.
	 * @param vo
	 * @return LancamentoVO
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public LancamentoVO efetuarLancamento(LancamentoVO vo) throws SmartEnvException, SmartAppException {
		return financeiroBean.efetuarLancamento(vo);
		
	}
	
	/**
	 * "Proxy" para o m�todo findLancamentoByFilter() no Fa�ade Financeiro.
	 * @param assembler
	 * @return Collection de LancamentoVO
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public LancamentoAssembler findLancamentoByFilter(LancamentoFiltroAssembler assembler) throws SmartEnvException, SmartAppException {
		return financeiroBean.findLancamentoByFilter(assembler);

	}
	
	/**
	 * "Proxy" para o m�todo obterSaldoLancamento() no Fa�ade Financeiro.
	 * @param vo
	 * @return Saldo 
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public Double obterSaldoLancamento(LancamentoVO vo) throws SmartEnvException, SmartAppException {
		return financeiroBean.obterSaldoLancamento(vo);
	}
	
	/**
	 * "Proxy" para o m�todo obterSaldoLancamentoAbsoluto() no Fa�ade Financeiro.
	 * @param vo
	 * @return Saldo absoluto
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public Double obterSaldoLancamentoAbsoluto(LancamentoVO vo) throws SmartEnvException, SmartAppException {
		return financeiroBean.obterSaldoLancamentoAbsoluto(vo);
	}

	/**
	 *  "Proxy" para o m�todo findLancamentoByPrimaryKey() no Fa�ade Financeiro.
	 * @param vo
	 * @return LancamentoVO
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public LancamentoVO findLancamentoByPrimaryKey(LancamentoVO vo) throws SmartEnvException, SmartAppException {
		return financeiroBean.findLancamentoByPrimaryKey(vo);
	}
	
	
	public Collection findAllDiretorFinanceiro() throws SmartEnvException, SmartAppException {
//		return financeiroBean.findAllBanco();
		return financeiroBean.findAllDiretorFinanceiro();
	}
	
	public DiretorFinanceiroVO findDiretorFinanceiroByPrimaryKey(DiretorFinanceiroVO vo) throws SmartEnvException, SmartAppException {
		return financeiroBean.findDiretorFinanceiroByPrimaryKey(vo);
	}

	public void updateDiretorFinanceiro(DiretorFinanceiroVO vo) throws SmartEnvException, SmartAppException {
		financeiroBean.updateDiretorFinanceiro(vo);
	}
	
	public void removeDiretorFinanceiro(DiretorFinanceiroVO vo) throws SmartEnvException, SmartAppException {
		financeiroBean.removeDiretorFinanceiro(vo);
	}
	
	public DiretorFinanceiroVO insertDiretorFinanceiro(DiretorFinanceiroVO vo) throws SmartEnvException, SmartAppException {
		return financeiroBean.insertDiretorFinanceiro(vo);
	}
	
	public Collection findAllFormaPagamento() throws SmartEnvException, SmartAppException {
		return financeiroBean.findAllFormaPagamento();
	}
	
	public void removeLancamentosDuplicados() throws SmartEnvException {
		financeiroBean.removeLancamentosDuplicados();
	}
	
	/**
	 * Obt�m uma cole��o de lan�amentos a partir de uma fatura. Tabelas: LANCAMENTO, LANCAMENTO_FATURA, LANCAMENTO_ITEM_FATURA   
	 * @param fatura Inst�ncia da classe FaturaVO, que encapsula uma fatura.
	 * @return Cole��o de lan�amentos, encapsulados da classe LancamentoVO.
	 * @throws SmartEnvException
	 */
	public Collection<LancamentoFaturaVO> findLancamentoByFatura(FaturaVO fatura) throws SmartEnvException {
		return financeiroBean.findLancamentoByFatura(fatura);
	}

	public void setFinanceiroBean(FinanceiroFacade financeiroBean) {
		this.financeiroBean = financeiroBean;
	}
	
}