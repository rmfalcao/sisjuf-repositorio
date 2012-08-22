package br.org.asserjuf.sisjuf.associados.convenio;

import java.util.Collection;

import br.com.falc.smartFW.exception.SmartAppException;
import br.com.falc.smartFW.exception.SmartEnvException;
import br.org.asserjuf.sisjuf.associados.convenio.dados.AtividadeConvenioDAO;

/**
 * Encapsula as regras de negócio da entidade atividade.
 * @author Paulo
 *
 */
public class AtividadeConvenioRN {

	/**
	 * Classe de acesso ao banco de dados da entidade "AtividadeConvenio" do Sisjuf.
	 */
	private AtividadeConvenioDAO atividadeConvenioDAO;
	
	
	/**
	 * Obtém todos as atividades.
	 * @return Coleção de atividade, encapsulados na classe AtividadeConvenioVO.
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public Collection<AtividadeConvenioVO> findAll() throws SmartEnvException, SmartAppException {
		return atividadeConvenioDAO.findAll();
	}
	
	/**
	 * Obtém todos as atividades ativas.
	 * @return Coleção de atividade, encapsulados na classe AtividadeConvenioVO.
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public Collection<AtividadeConvenioVO> findAllAtivas() throws SmartEnvException, SmartAppException {
		return atividadeConvenioDAO.findAllAtivas();
	}	
	
	/**
	 * Obtém uma atividade por chave.
	 * @param vo Instância de AtividadeConvenioVO que encapsula o código da atividade que se deseja recuperar.
	 * @return Atividade desejada.
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public AtividadeConvenioVO findByPrimaryKey(AtividadeConvenioVO vo) throws SmartEnvException, SmartAppException {
		
		return atividadeConvenioDAO.findByPrimaryKey(vo);
	}
	
	/**
	 * Atualiza uma atividade.
	 * @param vo Atividade que se deseja atualizar.
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public void update(AtividadeConvenioVO vo) throws SmartEnvException, SmartAppException {
		
		validateFields(vo);
		
		atividadeConvenioDAO.update(vo);
	}
	
	/**
	 * Exclui uma atividade.
	 * @param vo Atividade que se deseja excluir.
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public void remove(AtividadeConvenioVO vo) throws SmartEnvException, SmartAppException {
		
		atividadeConvenioDAO.remove(vo);
	}
	
	/**
	 * Insere uma atividade.
	 * @param vo Atividade que se deseja inserir.
	 * @return Atividade que foi inserido.
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public AtividadeConvenioVO insert(AtividadeConvenioVO vo) throws SmartEnvException, SmartAppException {		
		
		validateFields(vo);
		
		return atividadeConvenioDAO.insert(vo);
	}
	
	/**
	 * Verifica se os campos obrigatórios de "atividade" estão preenchidos.
	 * @param vo Atividade cujos dados se deseja verificar.
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	private void validateFields(AtividadeConvenioVO vo) throws SmartEnvException, SmartAppException {
		if (vo.getNome() == null || vo.getNome().equals("")) {
			throw new SmartAppException("O nome deve ser preenchido.");
		}
	}

	public void setAtividadeConvenioDAO(AtividadeConvenioDAO atividadeConvenioDAO) {
		this.atividadeConvenioDAO = atividadeConvenioDAO;
	}	
	
}
