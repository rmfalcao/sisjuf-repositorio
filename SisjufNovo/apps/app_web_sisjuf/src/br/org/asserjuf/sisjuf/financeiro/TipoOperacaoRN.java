package br.org.asserjuf.sisjuf.financeiro;

import java.util.Collection;

import br.com.falc.smartFW.exception.SmartAppException;
import br.com.falc.smartFW.exception.SmartEnvException;
import br.org.asserjuf.sisjuf.financeiro.dados.TipoOperacaoDAO;

/**
 * Congrega todas as funcionalidades da regra de negócio da entidade tipo de operação.
 * @author Paulo Prado
 *
 */
public class TipoOperacaoRN {
	
	/**
	 * Classe de acesso a banco de dados da entidade tipo de operação.
	 */
	private TipoOperacaoDAO tipoOperacaoDAO;
	

	
	public void setTipoOperacaoDAO(TipoOperacaoDAO tipoOperacaoDAO) {
		this.tipoOperacaoDAO = tipoOperacaoDAO;
	}

	/**
	 * Obtém todos os tipos de operação cadastrados na base de dados.
	 * @return Coleção dos tipos de operação.
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public Collection findAll() throws SmartEnvException, SmartAppException {
		return tipoOperacaoDAO.findAll();
	}

	/**
	 * Busca por chave de um tipo de operação.
	 * @param vo
	 * @return Objeto que representa o tipo de operação buscado
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public TipoOperacaoVO findByPrimaryKey(TipoOperacaoVO vo) throws SmartEnvException, SmartAppException {
		return tipoOperacaoDAO.findByPrimaryKey(vo);
	}
}
