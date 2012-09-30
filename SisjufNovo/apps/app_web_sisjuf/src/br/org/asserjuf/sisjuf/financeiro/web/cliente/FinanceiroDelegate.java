package br.org.asserjuf.sisjuf.financeiro.web.cliente;

import java.util.Collection;

import org.apache.log4j.Logger;

import br.com.falc.smartFW.exception.SmartAppException;
import br.com.falc.smartFW.exception.SmartEnvException;
import br.org.asserjuf.sisjuf.financeiro.BaixaLancamentoVO;
import br.org.asserjuf.sisjuf.financeiro.BancoVO;
import br.org.asserjuf.sisjuf.financeiro.ContaBancoVO;
import br.org.asserjuf.sisjuf.financeiro.ContaVO;
import br.org.asserjuf.sisjuf.financeiro.DiretorFinanceiroVO;
import br.org.asserjuf.sisjuf.financeiro.ExtratoAssembler;
import br.org.asserjuf.sisjuf.financeiro.ExtratoFiltroAssembler;
import br.org.asserjuf.sisjuf.financeiro.LancamentoAssembler;
import br.org.asserjuf.sisjuf.financeiro.LancamentoFiltroAssembler;
import br.org.asserjuf.sisjuf.financeiro.LancamentoVO;
import br.org.asserjuf.sisjuf.financeiro.MovimentacaoAssembler;
import br.org.asserjuf.sisjuf.financeiro.OrigemLancamentoVO;
import br.org.asserjuf.sisjuf.financeiro.TipoLancamentoVO;
import br.org.asserjuf.sisjuf.financeiro.TipoOperacaoVO;
import br.org.asserjuf.sisjuf.financeiro.facade.FinanceiroFacade;

/**
 * Implementação da classe que representa o "proxy" para o façade Financeiro.
 * @author Rodrigo Falcão
 *
 */
public class FinanceiroDelegate {
	
	private static transient Logger LOG = Logger.getLogger(FinanceiroDelegate.class);
	
	/**
	 * Façace Financeiro do Sisjuf.
	 */
	private FinanceiroFacade financeiroBean;
	
	/**
	 * "Proxy" para o método findAllBanco() no façade Financeiro
	 * @return Collection de BancoVO
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public Collection findAllBanco() throws SmartEnvException, SmartAppException {
		LOG.debug("financeiroBean " + financeiroBean);
		return financeiroBean.findAllBanco();
	}
	
	/**
	 * "Proxy" para o método findBancoByPrimaryKey() no façade Financeiro.
	 * @param vo 
	 * @return BancoVO
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public BancoVO findBancoByPrimaryKey(BancoVO vo) throws SmartEnvException, SmartAppException {
		return financeiroBean.findBancoByPrimaryKey(vo);
	}
	
	/**
	 * "Proxy" para o método updateBanco() no façade Financeiro.
	 * @param vo
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public void updateBanco(BancoVO vo) throws SmartEnvException, SmartAppException {
		financeiroBean.updateBanco(vo);
	}
	
	/**
	 * "Proxy" para o método removeBanco() no façade Financeiro.
	 * @param vo
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public void removeBanco(BancoVO vo) throws SmartEnvException, SmartAppException {
		financeiroBean.removeBanco(vo);
	}
	
	/**
	 * "Proxy" para o método insertBanco() no façade Financeiro.
	 * @param vo
	 * @return BancoVO 
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public BancoVO insertBanco(BancoVO vo) throws SmartEnvException, SmartAppException {
		return financeiroBean.insertBanco(vo);
	}
	
	/**
	 * "Proxy" para o método findAllTipoLancamento() no façade Financeiro.
	 * @return Collection de TipoLancamentoVO
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public Collection findAllTipoLancamento() throws SmartEnvException, SmartAppException {
		return financeiroBean.findAllTipoLancamento();
	}
	
	/**
	 * "Proxy" para o método findTipoLancamentoByPrimaryKey() no façade Financeiro.
	 * @param vo
	 * @return TipoLancamentoVO
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public TipoLancamentoVO findTipoLancamentoByPrimaryKey(TipoLancamentoVO vo) throws SmartEnvException, SmartAppException {
		return financeiroBean.findTipoLancamentoByPrimaryKey(vo);
	}
	
	/**
	 * "Proxy" para o método updateTipoLancamento() no façade Financeiro.
	 * @param vo
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public void updateTipoLancamento(TipoLancamentoVO vo) throws SmartEnvException, SmartAppException {
		financeiroBean.updateTipoLancamento(vo);
	}
	
	/**
	 * "Proxy" para o método removeTipoLancamento() no façade Financeiro.
	 * @param vo
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public void removeTipoLancamento(TipoLancamentoVO vo) throws SmartEnvException, SmartAppException {
		financeiroBean.removeTipoLancamento(vo);
	}
	
	/**
	 * "Proxy" para o método insertTipoLancamento() no Façade Financeiro.
	 * @param vo
	 * @return TipoLancamentoVO
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public TipoLancamentoVO insertTipoLancamento(TipoLancamentoVO vo) throws SmartEnvException, SmartAppException {
		return financeiroBean.insertTipoLancamento(vo);
	}
	
	/**
	 * "Proxy" para o método findAllOrigemLancamento() no Façade Financeiro.
	 * @return Collection de OrigemLancamentoVO
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public Collection findAllOrigemLancamento() throws SmartEnvException, SmartAppException {
		return financeiroBean.findAllOrigemLancamento();
	}
	
	/**
	 *  "Proxy" para o método findOrigemLancamentoByPrimaryKey() no Façade Financeiro.
	 * @param vo
	 * @return OrigemLancamentoVO
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public OrigemLancamentoVO findOrigemLancamentoByPrimaryKey(OrigemLancamentoVO vo) throws SmartEnvException, SmartAppException {
		return financeiroBean.findOrigemLancamentoByPrimaryKey(vo);
	}
	
	/**
	 * "Proxy" para o método updateOrigemLancamento() no Façade Financeiro.
	 * @param vo
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public void updateOrigemLancamento(OrigemLancamentoVO vo) throws SmartEnvException, SmartAppException {
		financeiroBean.updateOrigemLancamento(vo);
	}
	/**
	 * "Proxy" para o método removeOrigemLancamento() no Façade Financeiro.
	 * @param vo
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public void removeOrigemLancamento(OrigemLancamentoVO vo) throws SmartEnvException, SmartAppException {
		financeiroBean.removeOrigemLancamento(vo);
	}
	
	/**
	 * "Proxy" para o método insertOrigemLancamento() no Façade Financeiro.
	 * @param vo
	 * @return OrigemLancamentoVO
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public OrigemLancamentoVO insertOrigemLancamento(OrigemLancamentoVO vo) throws SmartEnvException, SmartAppException {
		return financeiroBean.insertOrigemLancamento(vo);
	}
	
	/**
	 * "Proxy" para o método findAllTipoOperacao() no Façade Financeiro.
	 * @return Collection de TipoOperacaoVO
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public Collection findAllTipoOperacao() throws SmartEnvException, SmartAppException {
		return financeiroBean.findAllTipoOperacao();
	}

	/**
	 * "Proxy" para o método findTipoOperacaoByPrimaryKey() no Façade Financeiro.
	 * @param vo
	 * @return TipoOperacaoVO
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public TipoOperacaoVO findTipoOperacaoByPrimaryKey(TipoOperacaoVO vo) throws SmartEnvException, SmartAppException {
		return financeiroBean.findTipoOperacaoByPrimaryKey(vo);
	}

	/**
	 * "Proxy" para o método findAllTipoConta() no Façade Financeiro.
	 * @return Collection de TipoContaVO
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public Collection findAllTipoConta() throws SmartEnvException, SmartAppException {
		return financeiroBean.findAllTipoConta();
	}
	
	/**
	 * "Proxy" para o método findAllConta() no Façade Financeiro.
	 * @return Collection de ContaVO
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public Collection findAllConta() throws SmartEnvException, SmartAppException {
		return financeiroBean.findAllConta();
	}
	
	/**
	 * "Proxy" para o método findContaByPrimaryKey() no Façade Financeiro.
	 * @param vo
	 * @return ContaBancoVO
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public ContaBancoVO findContaByPrimaryKey(ContaVO vo) throws SmartEnvException, SmartAppException {
		return financeiroBean.findContaByPrimaryKey(vo);
	}
	
	/**
	 * "Proxy" para o método updateConta() no Façade Financeiro.
	 * @param vo
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public void updateConta(ContaBancoVO vo) throws SmartEnvException, SmartAppException {
		financeiroBean.updateConta(vo);
	}
	
	/**
	 * "Proxy" para o método insertConta() no Façade Financeiro.
	 * @param vo
	 * @return ContaVO
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public ContaVO insertConta(ContaBancoVO vo) throws SmartEnvException, SmartAppException {
		return financeiroBean.insertConta(vo);
	}
	
	/**
	 * "Proxy" para o método removeConta() no Façade Financeiro.
	 * @param vo
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public void removeConta(ContaVO vo) throws SmartEnvException, SmartAppException {
		financeiroBean.removeConta(vo);
	}
	
	/**
	 * "Proxy" para o método obterSaldoConta() no Façade Financeiro.
	 * @param vo
	 * @return ContaVO
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public ContaVO obterSaldoConta(ContaVO vo) throws SmartEnvException, SmartAppException {
		
		return financeiroBean.obterSaldoConta(vo);
		
	}
	
	/**
	 * "Proxy" para o método obterExtratoConta() no Façade Financeiro.
	 * @param assembler
	 * @return ExtratoAssembler
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public ExtratoAssembler obterExtratoConta(ExtratoFiltroAssembler assembler) throws SmartEnvException, SmartAppException {
		return financeiroBean.obterExtratoConta(assembler);
	}

	/**
	 * "Proxy" para o método movimentarValoresEntreContas() no Façade Financeiro.
	 * @param assembler
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public void movimentarValoresEntreContas(MovimentacaoAssembler assembler) throws SmartEnvException, SmartAppException {
		financeiroBean.movimentarValoresEntreContas(assembler);
	}
	
	/**
	 * "Proxy" para o método baixarLancamento() no Façade Financeiro.
	 * @param vo
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public void baixarLancamento(BaixaLancamentoVO vo) throws SmartEnvException, SmartAppException {
		financeiroBean.baixarLancamento(vo);
	}
	
	/**
	 * "Proxy" para o método estornarLancamento() no Façade Financeiro.
	 * @param vo
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public void estornarLancamento(LancamentoVO vo) throws SmartEnvException, SmartAppException {
		financeiroBean.estornarLancamento(vo);
	}
	
	/**
	 * "Proxy" para o método efetuarLancamento() no Façade Financeiro.
	 * @param vo
	 * @return LancamentoVO
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public LancamentoVO efetuarLancamento(LancamentoVO vo) throws SmartEnvException, SmartAppException {
		return financeiroBean.efetuarLancamento(vo);
		
	}
	
	/**
	 * "Proxy" para o método findLancamentoByFilter() no Façade Financeiro.
	 * @param assembler
	 * @return Collection de LancamentoVO
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public LancamentoAssembler findLancamentoByFilter(LancamentoFiltroAssembler assembler) throws SmartEnvException, SmartAppException {
		return financeiroBean.findLancamentoByFilter(assembler);

	}
	
	/**
	 * "Proxy" para o método obterSaldoLancamento() no Façade Financeiro.
	 * @param vo
	 * @return Saldo 
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public Double obterSaldoLancamento(LancamentoVO vo) throws SmartEnvException, SmartAppException {
		return financeiroBean.obterSaldoLancamento(vo);
	}
	
	/**
	 * "Proxy" para o método obterSaldoLancamentoAbsoluto() no Façade Financeiro.
	 * @param vo
	 * @return Saldo absoluto
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public Double obterSaldoLancamentoAbsoluto(LancamentoVO vo) throws SmartEnvException, SmartAppException {
		return financeiroBean.obterSaldoLancamentoAbsoluto(vo);
	}

	/**
	 *  "Proxy" para o método findLancamentoByPrimaryKey() no Façade Financeiro.
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

	public void setFinanceiroBean(FinanceiroFacade financeiroBean) {
		this.financeiroBean = financeiroBean;
	}
	
}