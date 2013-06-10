package br.org.asserjuf.sisjuf.associados.convenio;

import java.util.Collection;

import br.com.falc.smartFW.exception.SmartAppException;
import br.com.falc.smartFW.exception.SmartEnvException;
import br.org.asserjuf.sisjuf.associados.convenio.dados.PlanoConvenioDAO;

/**
 * Classe que encapsula os métodos de acesso a Banco de Plano
 * @author Paulo
 *
 */
public class PlanoConvenioRN {

	/**
	 * Classe de acesso ao banco de dados da entidade "Plano Convênio" do Sisjuf.
	 */
	private PlanoConvenioDAO	planoConvenioDAO;
	

	public void setPlanoConvenioDAO(PlanoConvenioDAO planoConvenioDAO) {
		this.planoConvenioDAO = planoConvenioDAO;
	}		
	
	/**
	 * Obtém todos os planos.
	 * @return Coleção de planos, encapsulados na classe PlanoConvenioVO.
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public Collection<PlanoConvenioVO> findAll() throws SmartEnvException, SmartAppException {
		return planoConvenioDAO.findAll();
	}
	
	/**
	 * Obtém um plano por chave.
	 * @param vo Instância de PlanoConvenioVO que encapsula o código do convênio que se deseja recuperar.
	 * @return Convênio desejada.
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public PlanoConvenioVO findByPrimaryKey(PlanoConvenioVO vo) throws SmartEnvException, SmartAppException {
		
		return planoConvenioDAO.findByPrimaryKey(vo);
	}
	
	/**
	 * Atualiza um plano.
	 * @param vo Plano que se deseja atualizar.
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public void update(PlanoConvenioVO vo) throws SmartEnvException, SmartAppException {			
		
		validateFields(vo);	
		
		PlanoConvenioVO planoVelho = this.findByPrimaryKey(vo);
		
		if (planoVelho.getValor().doubleValue() != vo.getValor().doubleValue()){
			planoConvenioDAO.insertHistorico(planoVelho);
		}	
		
		planoConvenioDAO.update(vo);
		
		System.out.println("FIM");
	}
	
	/**
	 * Exclui um Plano.
	 * @param vo Plano que se deseja excluir.
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public void remove(PlanoConvenioVO vo) throws SmartEnvException, SmartAppException {
		
			planoConvenioDAO.remove(vo);		
	}
	
	/**
	 * Insere um plano.
	 * @param vo Plano que se deseja inserir.
	 * @return Plano que foi inserido.
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public PlanoConvenioVO insert(PlanoConvenioVO vo) throws SmartEnvException, SmartAppException {
		
		validateFields(vo);
		
		return planoConvenioDAO.insert(vo);
	}
	
	/**
	 * Obtém os planos atraves de critérios de filtro.
	 * @param vo PlanoConvenioVO com critérios de filtro preenchidos.
	 * @return Coleção de planos, encapsulados na classe PlanoConvenioVO.
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public Collection<PlanoConvenioVO> findByFilter(PlanoConvenioVO vo) throws SmartEnvException, SmartAppException {
		return planoConvenioDAO.findByFilter(vo);
	}	
	
	/**
	 * Verifica se os campos obrigatórios do "Plano" estão preenchidos.
	 * @param vo Plano cujos dados se deseja verificar.
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	private void validateFields(PlanoConvenioVO vo) throws SmartEnvException, SmartAppException {				
		

		if (vo.getNome() == null || vo.getNome().equals("")) {
			throw new SmartAppException("O nome deve ser preenchido.");
		}
		
		if (vo.getValor() == null) {
			throw new SmartAppException("O valor deve ser preenchido.");
		}	
		
		if (vo.getDataInicio() == null) {
			throw new SmartAppException("A data de início deve ser preenchido.");
		}				
		
		//Caso seja uma atualização
		if (vo.getCodigo() != null){
			PlanoConvenioVO planoVelho =this.findByPrimaryKey(vo);
			//Caso a dataInicio seja mudada então a nova deve ser maior que a anterior caso a mesma exista.
			if (vo.getDataInicio()!= null && planoVelho.getDataInicio() != null
					&& ( vo.getDataInicio().getTime() < planoVelho.getDataInicio().getTime())){
				throw new SmartAppException("A nova data inicio deve ser maior que a data inicio anterior.");
			}
		}
	}
}