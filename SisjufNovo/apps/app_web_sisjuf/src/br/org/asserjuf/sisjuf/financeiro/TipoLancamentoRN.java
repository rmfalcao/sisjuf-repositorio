package br.org.asserjuf.sisjuf.financeiro;

import java.util.Collection;

import br.com.falc.smartFW.exception.SmartAppException;
import br.com.falc.smartFW.exception.SmartEnvException;
import br.org.asserjuf.sisjuf.financeiro.dados.TipoLancamentoDAO;

/**
 * Congrega todas as funcionalidades da regra de neg�cio da entidade tipo de lan�amento.
 * @author Paulo Prado
 *
 */
public class TipoLancamentoRN {
	
	/**
	 * Classe de acesso a banco de dados da entidade tipo de lan�amento.
	 */
	private TipoLancamentoDAO tipoLancamentoDAO;
	


	/**
	 * Obt�m todos os tipos de lan�amento cadastrados na base de dados.
	 * @return Cole��o dos tipos de lan�amento.
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public Collection findAll() throws SmartEnvException, SmartAppException {
		 
		return tipoLancamentoDAO.findAll();
		
	}
	
	/**
	 * Busca por chave de um tipo de lan�amento.
	 * @param vo
	 * @return Objeto que representa o tipo de lan�amento buscado
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public TipoLancamentoVO findByPrimaryKey(TipoLancamentoVO vo) throws SmartEnvException, SmartAppException {
		
		return tipoLancamentoDAO.findByPrimaryKey(vo);
		
	}
	
	/**
	 * Atualiza os dados de um tipo de lan�amento.
	 * @param vo Objeto com dados atualizados de um tipo de lan�amento
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public void update(TipoLancamentoVO vo) throws SmartEnvException, SmartAppException {
		
		validateFields(vo);
		
		if (tipoLancamentoDAO.findByPrimaryKey(vo).getFlgNaoApagavel().equals("1")) {
			throw new SmartAppException("Este tipo de lan�amento n�o pode ser apagado e/ou editado.");
			
		}

		tipoLancamentoDAO.update(vo);
		
	}
	
	/**
	 * Remove um tipo de lan�amento
	 * @param vo Objeto com chave do tipo de lan�amento a ser removido
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public void remove(TipoLancamentoVO vo) throws SmartEnvException, SmartAppException {
		
		if (tipoLancamentoDAO.findByPrimaryKey(vo).getFlgNaoApagavel().equals("1")) {
			
			throw new SmartAppException("Este tipo de lan�amento n�o pode ser apagado e/ou editado.");
			
		}
		
		tipoLancamentoDAO.remove(vo);
		
	}
	
	/**
	 * Insere um novo tipo de lan�amento.
	 * @param vo Novo tipo de lan�amento a ser inserido
	 * @return tipoLancamentoVO com chave do registro inserido
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public TipoLancamentoVO insert(TipoLancamentoVO vo) throws SmartEnvException, SmartAppException {
		
		
		validateFields(vo);
		
		
		return tipoLancamentoDAO.insert(vo);
		
	}
	
	/**
	 * Verifica se os campos obrigat�rios de "tipo de lan�amento" est�o preenchidos.
	 * @param vo
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	private void validateFields(TipoLancamentoVO vo) throws SmartEnvException, SmartAppException {
		if (vo.getNome() == null || vo.getNome().equals("")) {
			throw new SmartAppException("O nome deve ser preenchido.");

		}

	}

	public void setTipoLancamentoDAO(TipoLancamentoDAO tipoLancamentoDAO) {
		this.tipoLancamentoDAO = tipoLancamentoDAO;
	}

}
