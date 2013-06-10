package br.org.asserjuf.sisjuf.associados;

import br.com.falc.smartFW.exception.SmartAppException;
import br.com.falc.smartFW.exception.SmartEnvException;
import br.org.asserjuf.sisjuf.associados.dados.PessoaDAO;

public class PessoaRN {

	private PessoaDAO pessoaDAO;
	
	
	public PessoaVO insert(PessoaVO pessoa) throws SmartEnvException, SmartAppException {
		
		return pessoaDAO.insert(pessoa);
		
	}
	
	public void update(PessoaVO pessoa) throws SmartEnvException, SmartAppException {
		
		pessoaDAO.update(pessoa);
		
	}
	
	public void remove(PessoaVO pessoa) throws SmartEnvException, SmartAppException {
		
		pessoaDAO.remove(pessoa);

	}

	public void setPessoaDAO(PessoaDAO pessoaDAO) {
		this.pessoaDAO = pessoaDAO;
	}

}	
