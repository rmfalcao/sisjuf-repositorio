package br.org.asserjuf.sisjuf.associados.convenio;

import java.util.Collection;

import br.com.falc.smartFW.exception.SmartAppException;
import br.com.falc.smartFW.exception.SmartEnvException;
import br.org.asserjuf.sisjuf.associados.convenio.dados.OutrosBeneficiaveisDAO;
import br.org.asserjuf.sisjuf.associados.dados.PessoaDAO;


/**
 * Encapsula as regras de negócio da entidade outros beneficiaveis.
 * @author Paulo
 *
 */
public class OutrosBeneficiaveisRN {

	/**
	 * Classe de acesso ao banco de dados da entidade "Outro Beneficiavel" do Sisjuf.
	 */
	private OutrosBeneficiaveisDAO outrosBeneficiaveisDAO;
	/**
	 * Classe de acesso ao banco de dados da entidade "Outro Beneficiavel" do Sisjuf.
	 */	
	private PessoaDAO pessoaDAO;
	
	
	/**
	 * Obtém todos os outros beneficiaveis de um associado
	 * @return Coleção de outros beneficiaveis, encapsulados na classe OutroBeneficiavelVO.
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public Collection<OutroBeneficiavelVO> findByAssociado(OutroBeneficiavelVO vo) throws SmartEnvException, SmartAppException {
		return outrosBeneficiaveisDAO.findByAssociado(vo);
	}
	
	/**
	 * Obtém um outro beneficiario
	 * @param vo Instância de OutroBeneficiavelVO.
	 * @return OutroBeneficiavelVO desejada.
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public OutroBeneficiavelVO findByPrimaryKey(OutroBeneficiavelVO vo) throws SmartEnvException, SmartAppException {
		
		return outrosBeneficiaveisDAO.findByPrimaryKey(vo);
	}
	
	/**
	 * Atualiza um outro beneficiario.
	 * @param vo OutroBeneficiavelVO que se deseja atualizar.
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public void update(OutroBeneficiavelVO vo) throws SmartEnvException, SmartAppException {
		validateFields(vo);
		
		pessoaDAO.update(vo);
		
		outrosBeneficiaveisDAO.update(vo);
	}
	
	/**
	 * Exclui uma outro beneficiario.
	 * @param vo OutroBeneficiavelVO que se deseja excluir.
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public void remove(OutroBeneficiavelVO vo) throws SmartEnvException, SmartAppException {
		
		if (outrosBeneficiaveisDAO.verificaVinculacao(vo)){
			throw new SmartAppException("Não é possível excluir o registro, pois há plano(s) vinculado(os).");			
		}
		outrosBeneficiaveisDAO.remove(vo);
		
		pessoaDAO.remove(vo);		
	}
	
	/**
	 * Insere um outro beneficiario.
	 * @param vo OutroBeneficiavelVO que se deseja inserir.
	 * @return OutroBeneficiavelVO que foi inserido.
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public OutroBeneficiavelVO insert(OutroBeneficiavelVO vo) throws SmartEnvException, SmartAppException {		
		
		validateFields(vo);
		
		pessoaDAO.insert(vo);
		
		outrosBeneficiaveisDAO.insert(vo);
		
		return vo;
	}
	
	/**
	 * Verifica se os campos obrigatórios de "outro beneficiavel" estão preenchidos.
	 * @param vo Outro Beneficiavel cujos dados se deseja verificar.
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	private void validateFields(OutroBeneficiavelVO vo) throws SmartEnvException, SmartAppException {
		if (vo.getNome() == null || vo.getNome().equals("")) {
			throw new SmartAppException("O nome deve ser preenchido.");
		}
		if (vo.getRg() == null || vo.getRg().equals("")) {
			throw new SmartAppException("O RG deve ser preenchido.");
		}
		if (vo.getCpf() == null || vo.getCpf().equals("")) {
			throw new SmartAppException("O CPF deve ser preenchido.");
		}
		if (vo.getSexo() == null || vo.getSexo().equals("")) {
			throw new SmartAppException("O sexo deve ser preenchido.");
		}
		if (vo.getDataNascimento() == null) {
			throw new SmartAppException("O data de nascimento deve ser preenchido.");
		}				
	}

	public void setOutrosBeneficiaveisDAO(OutrosBeneficiaveisDAO outrosBeneficiaveisDAO) {
		this.outrosBeneficiaveisDAO = outrosBeneficiaveisDAO;
	}

	public void setPessoaDAO(PessoaDAO pessoaDAO) {
		this.pessoaDAO = pessoaDAO;
	}
	
	
}