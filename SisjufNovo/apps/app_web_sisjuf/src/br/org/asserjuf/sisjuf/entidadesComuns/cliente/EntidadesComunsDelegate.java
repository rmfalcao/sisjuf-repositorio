package br.org.asserjuf.sisjuf.entidadesComuns.cliente;

import java.util.Collection;
import br.com.falc.smartFW.exception.SmartAppException;
import br.com.falc.smartFW.exception.SmartEnvException;
import br.org.asserjuf.sisjuf.entidadesComuns.EstadoVO;
import br.org.asserjuf.sisjuf.entidadesComuns.facade.EntidadesComunsFacade;

public class EntidadesComunsDelegate {

	EntidadesComunsFacade entidadesComunsBean;
	
	
	public Collection<EstadoVO> findAllEstado() throws SmartEnvException, SmartAppException {
	
		return entidadesComunsBean.findAllEstado();
	
	}


	public void setEntidadesComunsBean(EntidadesComunsFacade entidadesComunsBean) {
		this.entidadesComunsBean = entidadesComunsBean;
	}

	
}
