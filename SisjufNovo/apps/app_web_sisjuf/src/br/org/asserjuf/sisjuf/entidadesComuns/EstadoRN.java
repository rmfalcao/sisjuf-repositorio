package br.org.asserjuf.sisjuf.entidadesComuns;

import java.util.Collection;

import br.com.falc.smartFW.exception.SmartAppException;
import br.com.falc.smartFW.exception.SmartEnvException;
import br.org.asserjuf.sisjuf.entidadesComuns.dados.EstadoDAO;

public class EstadoRN {

	private EstadoDAO estadoDAO;
	
	
	public Collection<EstadoVO> findAll() throws SmartEnvException, SmartAppException {
		
		return estadoDAO.findAll();
		
	}


	public void setEstadoDAO(EstadoDAO estadoDAO) {
		this.estadoDAO = estadoDAO;
	}
	
	
	
}
