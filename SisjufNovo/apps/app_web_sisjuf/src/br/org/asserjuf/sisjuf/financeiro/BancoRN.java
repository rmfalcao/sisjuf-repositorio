package br.org.asserjuf.sisjuf.financeiro;

import java.util.Collection;

import br.com.falc.smartFW.exception.SmartAppException;
import br.com.falc.smartFW.exception.SmartEnvException;
import br.org.asserjuf.sisjuf.financeiro.dados.BancoDAO;

/**
 * Encapsula as regras de neg�cio da entidade banco.
 * @author Rodrigo Falc�o
 *
 */
public class BancoRN {

	/**
	 * Classe de acesso ao banco de dados da entidade "Banco" do Sisjuf.
	 */
	private BancoDAO bancoDAO;
	
	/**
	 * Obt�m todos os bancos.
	 * @return Cole��o de bancos, encapsulados na classe BancoVO.
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public Collection findAll() throws SmartEnvException, SmartAppException {
		return bancoDAO.findAll();
	}
	
	/**
	 * Obt�m um banco por chave.
	 * @param vo Inst�ncia de BancoVO que encapsula o c�digo do banco que se deseja recuperar.
	 * @return Banco desejado.
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public BancoVO findByPrimaryKey(BancoVO vo) throws SmartEnvException, SmartAppException {
		
		return bancoDAO.findByPrimaryKey(vo);
	}
	
	/**
	 * Atualiza um banco.
	 * @param vo Banco que se deseja atualizar.
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public void update(BancoVO vo) throws SmartEnvException, SmartAppException {
		
		validateFields(vo);
		
		bancoDAO.update(vo);
	}
	
	/**
	 * Exclui um banco.
	 * @param vo Banco que se deseja excluir.
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public void remove(BancoVO vo) throws SmartEnvException, SmartAppException {
		
		bancoDAO.remove(vo);
	}
	
	/**
	 * Insere um banco.
	 * @param vo Banco que se deseja inserir.
	 * @return Banco que foi inserido.
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public BancoVO insert(BancoVO vo) throws SmartEnvException, SmartAppException {
		
		validateFields(vo);
		
		return bancoDAO.insert(vo);
	}
	
	/**
	 * Verifica se os campos obrigat�rios de "banco" est�o preenchidos.
	 * @param vo Banco cujos dados se deseja verificar.
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	private void validateFields(BancoVO vo) throws SmartEnvException, SmartAppException {
		if (vo.getNome() == null || vo.getNome().equals("")) {
			throw new SmartAppException("O nome deve ser preenchido.");

		}
	}

	public void setBancoDAO(BancoDAO bancoDAO) {
		this.bancoDAO = bancoDAO;
	}
}