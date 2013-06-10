package br.org.asserjuf.sisjuf.financeiro;

import java.util.Collection;

import br.com.falc.smartFW.exception.SmartAppException;
import br.com.falc.smartFW.exception.SmartEnvException;
import br.org.asserjuf.sisjuf.financeiro.dados.TipoContaDAO;

/**
 * Congrega todas as funcionalidades da regra de negócio da entidade tipo de conta.
 * @author paulo
 *
 */
public class TipoContaRN {
	
	/**
	 * Classe de acesso a banco de dados da entidade tipo de conta.
	 */	
	private TipoContaDAO tipoContaDAO;
	


	/**
	 * Obtém todos os tipos de conta cadastrados na base de dados
	 * @return Coleção de tipos de conta.
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public Collection findAll() throws SmartEnvException, SmartAppException {
		return tipoContaDAO.findAll();
	}



	public void setTipoContaDAO(TipoContaDAO tipoContaDAO) {
		this.tipoContaDAO = tipoContaDAO;
	}

}
