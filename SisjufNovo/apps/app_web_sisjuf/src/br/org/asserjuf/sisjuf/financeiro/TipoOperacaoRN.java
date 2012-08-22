package br.org.asserjuf.sisjuf.financeiro;

import java.util.Collection;

import br.com.falc.smartFW.exception.SmartAppException;
import br.com.falc.smartFW.exception.SmartEnvException;
import br.org.asserjuf.sisjuf.financeiro.dados.TipoOperacaoDAO;

/**
 * Congrega todas as funcionalidades da regra de neg�cio da entidade tipo de opera��o.
 * @author Paulo Prado
 *
 */
public class TipoOperacaoRN {
	
	/**
	 * Classe de acesso a banco de dados da entidade tipo de opera��o.
	 */
	private TipoOperacaoDAO tipoOperacaoDAO;
	

	
	public void setTipoOperacaoDAO(TipoOperacaoDAO tipoOperacaoDAO) {
		this.tipoOperacaoDAO = tipoOperacaoDAO;
	}

	/**
	 * Obt�m todos os tipos de opera��o cadastrados na base de dados.
	 * @return Cole��o dos tipos de opera��o.
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public Collection findAll() throws SmartEnvException, SmartAppException {
		return tipoOperacaoDAO.findAll();
	}

	/**
	 * Busca por chave de um tipo de opera��o.
	 * @param vo
	 * @return Objeto que representa o tipo de opera��o buscado
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public TipoOperacaoVO findByPrimaryKey(TipoOperacaoVO vo) throws SmartEnvException, SmartAppException {
		return tipoOperacaoDAO.findByPrimaryKey(vo);
	}
}
