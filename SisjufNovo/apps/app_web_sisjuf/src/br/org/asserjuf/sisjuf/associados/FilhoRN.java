package br.org.asserjuf.sisjuf.associados;

import java.util.Collection;

import br.com.falc.smartFW.exception.SmartAppException;
import br.com.falc.smartFW.exception.SmartEnvException;
import br.org.asserjuf.sisjuf.associados.dados.FilhoDAO;

public class FilhoRN  {
	
	private FilhoDAO filhoDAO;
	private PessoaRN pessoaRN;

	
	public FilhoVO insert(FilhoVO filho) throws SmartEnvException, SmartAppException {
		
		return filhoDAO.insert((FilhoVO) pessoaRN.insert(filho));

	}
	
	public void remove(FilhoVO filho) throws SmartEnvException, SmartAppException {
		
		filhoDAO.remove(filho);

	}
	
	public void removeByAssociado(AssociadoVO associado) throws SmartEnvException, SmartAppException {
		
		filhoDAO.removeByAssociado(associado);

	}

	public Collection<FilhoVO> findByAssociado(AssociadoVO associado) throws SmartEnvException, SmartAppException {
		
		return filhoDAO.findByAssociado(associado);
		
	}
	
	public FilhoVO findByPrimaryKey(FilhoVO filho) throws SmartEnvException, SmartAppException {
		
		return filhoDAO.findByPrimaryKey(filho);
		
	}
	
	public void update(FilhoVO filho) throws SmartEnvException, SmartAppException {
		
		pessoaRN.update(filho);
		
	}		

	public void setFilhoDAO(FilhoDAO filhoDAO) {
		this.filhoDAO = filhoDAO;
	}

	public void setPessoaRN(PessoaRN pessoaRN) {
		this.pessoaRN = pessoaRN;
	}

}
