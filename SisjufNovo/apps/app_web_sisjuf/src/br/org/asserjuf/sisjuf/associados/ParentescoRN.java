package br.org.asserjuf.sisjuf.associados;

import java.util.Collection;

import br.com.falc.smartFW.exception.SmartAppException;
import br.com.falc.smartFW.exception.SmartEnvException;
import br.org.asserjuf.sisjuf.associados.dados.ParentescoDAO;

public class ParentescoRN {

	private ParentescoDAO parentescoDAO;
	

	
	public Collection<ParentescoVO> findAll()  throws SmartEnvException, SmartAppException {
		return parentescoDAO.findAll();
		
	}



	public void setParentescoDAO(ParentescoDAO parentescoDAO) {
		this.parentescoDAO = parentescoDAO;
	}
	
}
