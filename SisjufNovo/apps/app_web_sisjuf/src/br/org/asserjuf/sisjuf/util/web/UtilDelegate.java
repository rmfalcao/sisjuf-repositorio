package br.org.asserjuf.sisjuf.util.web;

import java.util.Date;

import br.com.falc.smartFW.exception.SmartAppException;
import br.com.falc.smartFW.exception.SmartEnvException;
import br.org.asserjuf.sisjuf.util.ParametroVO;
import br.org.asserjuf.sisjuf.util.facade.UtilFacade;

public class UtilDelegate  {
	
	UtilFacade utilBean;
	
	public void setUtilBean(UtilFacade utilBean) {
		this.utilBean = utilBean;
	}

	public Date getCurrentDate() throws SmartEnvException, SmartAppException {
		return utilBean.getCurrentDate();
	}
	
	public ParametroVO findParametroByPrimaryKey(ParametroVO vo) throws SmartEnvException, SmartAppException {
		return utilBean.findParametroByPrimaryKey(vo);
	}
}