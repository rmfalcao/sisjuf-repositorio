package br.org.asserjuf.sisjuf.util;

import br.com.falc.smartFW.exception.SmartAppException;
import br.com.falc.smartFW.exception.SmartEnvException;
import br.org.asserjuf.sisjuf.util.dados.ParametroDAO;
 
public class ParametroRN {

	private ParametroDAO parametroDAO;
	
	
		
	public void setParametroDAO(ParametroDAO parametroDAO) {
		this.parametroDAO = parametroDAO;
	}



	public ParametroVO findByPrimaryKey(ParametroVO vo) throws SmartEnvException, SmartAppException {
		
		return parametroDAO.findByPrimaryKey(vo);
		
	}
	
}
