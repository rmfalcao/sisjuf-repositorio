package br.org.asserjuf.sisjuf.entidadesComuns.facade;

import java.util.Collection;

import br.com.falc.smartFW.exception.SmartAppException;
import br.com.falc.smartFW.exception.SmartEnvException;
import br.org.asserjuf.sisjuf.entidadesComuns.EstadoRN;
import br.org.asserjuf.sisjuf.entidadesComuns.EstadoVO;

public class EntidadesComunsFacade {

	private EstadoRN	 			estadoRN;
	
	public Collection<EstadoVO> findAllEstado() throws SmartEnvException, SmartAppException {
		return estadoRN.findAll();

	}

	public void setEstadoRN(EstadoRN estadoRN) {
		this.estadoRN = estadoRN;
	}
	
}
