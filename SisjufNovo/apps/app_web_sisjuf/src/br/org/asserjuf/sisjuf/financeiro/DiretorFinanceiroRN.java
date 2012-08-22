package br.org.asserjuf.sisjuf.financeiro;

import java.util.Collection;
import java.util.Date;

import org.apache.log4j.Logger;

import br.com.falc.smartFW.exception.SmartAppException;
import br.com.falc.smartFW.exception.SmartEnvException;
import br.org.asserjuf.sisjuf.financeiro.dados.DiretorFinanceiroDAO;

/**
 * Encapsula as regras de negócio da entidade banco.
 * @author Rodrigo Falcão
 *
 */
public class DiretorFinanceiroRN {

	private transient static Logger LOG = Logger.getLogger(DiretorFinanceiroRN.class);
	
	/**
	 * Classe de acesso ao banco de dados da entidade "Banco" do Sisjuf.
	 */
	private DiretorFinanceiroDAO diretorFinanceiroDAO;

	public Collection findAll() throws SmartEnvException, SmartAppException {
		Collection<DiretorFinanceiroVO> diretores = diretorFinanceiroDAO.findAll();
		return diretores;
	}
	
	public DiretorFinanceiroVO findByPrimaryKey(DiretorFinanceiroVO vo) throws SmartEnvException, SmartAppException {
		return diretorFinanceiroDAO.findByPrimaryKey(vo);
	}
	
	public DiretorFinanceiroVO findByDate(Date data) throws SmartEnvException, SmartAppException {
		return diretorFinanceiroDAO.findByDate(data);
	}
	
	public void update(DiretorFinanceiroVO vo) throws SmartEnvException, SmartAppException {
		validateFields(vo);
		diretorFinanceiroDAO.update(vo);
	}
	
	public void remove(DiretorFinanceiroVO vo) throws SmartEnvException, SmartAppException {
		
		diretorFinanceiroDAO.remove(vo);
	}

	public DiretorFinanceiroVO insert(DiretorFinanceiroVO vo) throws SmartEnvException, SmartAppException {
		validateFields(vo);
		return diretorFinanceiroDAO.insert(vo);
	}
	
	private void validateFields(DiretorFinanceiroVO vo) throws SmartEnvException, SmartAppException {
		if (vo.getNome() == null || vo.getNome().equals("")) {
			throw new SmartAppException("O nome deve ser preenchido.");

		} else if (vo.getDataInicioGestao() == null) {
			throw new SmartAppException("A data de início da gestão deve ser preenchida.");
		}
	}

	public void setDiretorFinanceiroDAO(DiretorFinanceiroDAO diretorFinanceiroDAO) {
		this.diretorFinanceiroDAO = diretorFinanceiroDAO;
	}
}