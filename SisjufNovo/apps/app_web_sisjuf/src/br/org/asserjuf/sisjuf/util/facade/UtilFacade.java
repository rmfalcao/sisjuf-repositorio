package br.org.asserjuf.sisjuf.util.facade;

import java.util.Date;

import br.com.falc.smartFW.exception.SmartAppException;
import br.com.falc.smartFW.exception.SmartEnvException;
import br.org.asserjuf.sisjuf.util.DataRN;
import br.org.asserjuf.sisjuf.util.ParametroRN;
import br.org.asserjuf.sisjuf.util.ParametroVO;

public class UtilFacade {
	
	private DataRN		dataRN;
	private ParametroRN	parametroRN;

	public void setDataRN(DataRN dataRN) {
		this.dataRN = dataRN;
	}

	public void setParametroRN(ParametroRN parametroRN) {
		this.parametroRN = parametroRN;
	}

	public Date getCurrentDate() throws SmartEnvException, SmartAppException {
		return dataRN.getCurrentDate();
	}

	public ParametroVO findParametroByPrimaryKey(ParametroVO vo) throws SmartEnvException, SmartAppException {
		return parametroRN.findByPrimaryKey(vo);
	}

}
