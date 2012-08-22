package br.org.asserjuf.sisjuf.associados;

import java.util.Collection;

import br.com.falc.smartFW.exception.SmartAppException;
import br.com.falc.smartFW.exception.SmartEnvException;
import br.org.asserjuf.sisjuf.associados.dados.TipoEventoDAO;

public class TipoEventoRN {

	private TipoEventoDAO tipoEventoDAO;


	public Collection<TipoEventoVO> findAll() throws SmartEnvException, SmartAppException {
		return tipoEventoDAO.findAll();

	}


	public void setTipoEventoDAO(TipoEventoDAO tipoEventoDAO) {
		this.tipoEventoDAO = tipoEventoDAO;
	}

	
}
