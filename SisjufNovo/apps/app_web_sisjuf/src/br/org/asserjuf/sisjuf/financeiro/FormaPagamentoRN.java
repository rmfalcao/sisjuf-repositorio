package br.org.asserjuf.sisjuf.financeiro;

import java.util.Collection;

import br.com.falc.smartFW.exception.SmartAppException;
import br.com.falc.smartFW.exception.SmartEnvException;
import br.org.asserjuf.sisjuf.financeiro.dados.FormaPagamentoDAO;


public class FormaPagamentoRN {
	

	private FormaPagamentoDAO formaPagamentoDAO;
	
	

	public void setFormaPagamentoDAO(FormaPagamentoDAO formaPagamentoDAO) {
		this.formaPagamentoDAO = formaPagamentoDAO;
	}



	public Collection findAll() throws SmartEnvException, SmartAppException {
		return formaPagamentoDAO.findAll();
	}

	
}
