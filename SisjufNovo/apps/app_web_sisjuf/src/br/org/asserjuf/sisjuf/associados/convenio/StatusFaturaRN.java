package br.org.asserjuf.sisjuf.associados.convenio;


import java.util.Collection;
import br.com.falc.smartFW.exception.SmartEnvException;
import br.org.asserjuf.sisjuf.associados.convenio.dados.StatusFaturaDAO;
import br.org.asserjuf.sisjuf.associados.convenio.dados.StatusFaturaVO;

/**
 * Encapsula as regras de negócio da entidade status fatura.
 * @author rodrigo
 *
 */
public class StatusFaturaRN {

	/**
	 * Classe de acesso ao banco de dados da entidade "Fatura" do Sisjuf.
	 */
	protected StatusFaturaDAO statusFaturaDAO;
	
	

	/**
	 * Obtém todos os status de fatura
	 * @return Coleção de status de faturas, encapsulados na classe StatusFaturaVO.
	 * @throws SmartEnvException
	 */
	public Collection<StatusFaturaVO> findAll() throws SmartEnvException {

		return statusFaturaDAO.findAll();
	}


	public void setStatusFaturaDAO(StatusFaturaDAO statusFaturaDAO) {
		this.statusFaturaDAO = statusFaturaDAO;
	}

	
}