package br.org.asserjuf.sisjuf.associados;

import br.com.falc.smartFW.exception.SmartAppException;
import br.com.falc.smartFW.exception.SmartEnvException;
import br.org.asserjuf.sisjuf.associados.dados.ConjugeDAO;


public class ConjugeRN {

	private ConjugeDAO conjugeDAO;
	private PessoaRN pessoaRN;

	
	public ConjugeVO insert(ConjugeVO conjuge) throws SmartEnvException, SmartAppException {
		
		return conjugeDAO.insert((ConjugeVO) pessoaRN.insert(conjuge));

	}
	
	public void remove(ConjugeVO conjuge) throws SmartEnvException, SmartAppException {
		
		conjugeDAO.remove(conjuge);

	}

	
	public ConjugeVO findByAssociado(AssociadoVO associado) throws SmartEnvException, SmartAppException {
		
		return conjugeDAO.findByAssociado(associado);
		
	}

	public ConjugeVO findByPrimaryKey(ConjugeVO conjuge) throws SmartEnvException, SmartAppException {
		
		return conjugeDAO.findByPrimaryKey(conjuge);
		
	}	
	
	public void update(ConjugeVO conjuge) throws SmartEnvException, SmartAppException {
		
		conjugeDAO.update(conjuge);
		
		pessoaRN.update(conjuge);

	}	
	
	public void setConjugeDAO(ConjugeDAO conjugeDAO) {
		this.conjugeDAO = conjugeDAO;
	}

	public void setPessoaRN(PessoaRN pessoaRN) {
		this.pessoaRN = pessoaRN;
	}


}
