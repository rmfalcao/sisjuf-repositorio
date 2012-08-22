package br.org.asserjuf.sisjuf.financeiro;

import java.util.Collection;

import br.com.falc.smartFW.exception.SmartAppException;
import br.com.falc.smartFW.exception.SmartEnvException;
import br.org.asserjuf.sisjuf.financeiro.dados.OrigemLancamentoDAO;

/**
 * Congrega todas as funcionalidades da regra de negócio da entidade origem de lançamento.
 * @author Paulo Prado
 *
 */
public class OrigemLancamentoRN {
	
	/**
	 * Classe de acesso a banco de dados da entidade origem de lançamento.
	 */
	private OrigemLancamentoDAO origemLancamentoDAO;
	


	public void setOrigemLancamentoDAO(OrigemLancamentoDAO origemLancamentoDAO) {
		this.origemLancamentoDAO = origemLancamentoDAO;
	}

	/**
	 * Obtém todos os origens de lançamento cadastrados na base de dados.
	 * @return Coleção de origens de lançamento
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public Collection findAll() throws SmartEnvException, SmartAppException {
		 
		return origemLancamentoDAO.findAll();
		
	}
	
	/**
	 * Busca uma origem de lançamento por chave.
	 * @param vo
	 * @return origemLancamentoVO Objeto que representa uma origem de lançamento.
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public OrigemLancamentoVO findByPrimaryKey(OrigemLancamentoVO vo) throws SmartEnvException, SmartAppException {
		
		return origemLancamentoDAO.findByPrimaryKey(vo);
		
	}
	
	/**
	 * Atualiza os dados de uma origem de lançamento.
	 * @param vo Objeto com dados atualizados de uma origem de lançamento
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public void update(OrigemLancamentoVO vo) throws SmartEnvException, SmartAppException {
		
		this.validateFields(vo);
		
		if (origemLancamentoDAO.findByPrimaryKey(vo).getFlgNaoApagavel().equals("0")) {

			origemLancamentoDAO.update(vo);
			
		}
		else {
			
			throw new SmartAppException("Esta origem de lançamento não pode ser apagada e/ou editada.");
			
		}
		
	}
	
	/**
	 * Remove uma origem de lançamento 
	 * @param vo Objeto com chave do tipo de lançamento a ser removido
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public void remove(OrigemLancamentoVO vo) throws SmartEnvException, SmartAppException {
		
		if (origemLancamentoDAO.findByPrimaryKey(vo).getFlgNaoApagavel().equals("0")) {

			origemLancamentoDAO.remove(vo);
			
		}
		else {
			
			throw new SmartAppException("Esta origem de lançamento não pode ser apagada e/ou editada.");
			
		}
			
		
	}

	/**
	 * Insere uma nova origem de lançamento.
	 * @param vo Nova origem de lançamento a ser inserida
	 * @return origemLancamentoVO com chave do registro inserido
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public OrigemLancamentoVO insert(OrigemLancamentoVO vo) throws SmartEnvException, SmartAppException {
		this.validateFields(vo);
		
		return origemLancamentoDAO.insert(vo);
		
	}
	
	public void validateFields (OrigemLancamentoVO vo) throws SmartEnvException, SmartAppException {
		if (vo.getNome() == null || vo.getNome().equals("")) {
			throw new SmartAppException("O nome deve ser informado.");
			
		}
	}

}
