package br.org.asserjuf.sisjuf.associados.convenio;

import java.util.Collection;

import br.com.falc.smartFW.exception.SmartEnvException;
import br.org.asserjuf.sisjuf.associados.convenio.dados.BeneficiarioDAO;

public class BeneficiarioRN {

	private BeneficiarioDAO beneficiarioDAO;
	
	public void setBeneficiarioDAO(BeneficiarioDAO beneficiarioDAO) {
		this.beneficiarioDAO = beneficiarioDAO;

	}
	
	public Collection<BeneficiarioVO> findByFilter(BeneficiarioVO beneficiario) throws SmartEnvException {

		return beneficiarioDAO.findByFilter(beneficiario);
		
	}
	
}
