package br.org.asserjuf.sisjuf.associados;

import java.util.Collection;

import br.com.falc.smartFW.exception.SmartAppException;
import br.com.falc.smartFW.exception.SmartEnvException;
import br.org.asserjuf.sisjuf.associados.dados.DependenteDAO;

public class DependenteRN {

	private DependenteDAO dependenteDAO;
	private PessoaRN pessoaRN;

	
	public DependenteVO insert(DependenteVO dependente) throws SmartEnvException, SmartAppException {
		
		return dependenteDAO.insert((DependenteVO) pessoaRN.insert(dependente));

	}
	
	public void remove(DependenteVO dependente) throws SmartEnvException, SmartAppException {
		
		dependenteDAO.remove(dependente);

	}

	public void removeByAssociado(AssociadoVO associado) throws SmartEnvException, SmartAppException {
		
		dependenteDAO.removeByAssociado(associado);

	}

	public Collection<DependenteVO> findByAssociado(AssociadoVO associado) throws SmartEnvException, SmartAppException {
		
		return dependenteDAO.findByAssociado(associado);
		
	}

	public DependenteVO findByPrimaryKey(DependenteVO pDependenteVO) throws SmartEnvException, SmartAppException {
		
		return dependenteDAO.findByPrimaryKey(pDependenteVO);
		
	}	
	
	public void update(DependenteVO dependente) throws SmartEnvException, SmartAppException {
		
		dependenteDAO.update(dependente);
		
		pessoaRN.update(dependente);

	}	
	
	public void setDependenteDAO(DependenteDAO dependenteDAO) {
		this.dependenteDAO = dependenteDAO;
	}

	public void setPessoaRN(PessoaRN pessoaRN) {
		this.pessoaRN = pessoaRN;
	}


}
