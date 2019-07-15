package br.org.asserjuf.sisjuf.associados.convenio;

import java.util.Collection;

import br.com.falc.smartFW.exception.SmartAppException;
import br.com.falc.smartFW.exception.SmartEnvException;
import br.org.asserjuf.sisjuf.associados.RelatorioIRVO;
import br.org.asserjuf.sisjuf.associados.convenio.dados.BeneficiarioDAO;

public class BeneficiarioRN {

	private BeneficiarioDAO beneficiarioDAO;
	
	public void setBeneficiarioDAO(BeneficiarioDAO beneficiarioDAO) {
		this.beneficiarioDAO = beneficiarioDAO;

	}
	
	public Collection<BeneficiarioVO> findByFilter(BeneficiarioVO beneficiario) throws SmartEnvException {

		if(beneficiario.getNome() == ""){
			beneficiario.setNome(null);
		}
		if(beneficiario.getTitular() != null){
			if(beneficiario.getTitular().getNome() == ""){
				beneficiario.getTitular().setNome(null);
			}
			if(beneficiario.getTitular().getMatriculaJustica() != null && beneficiario.getTitular().getMatriculaJustica().equals("")){
				beneficiario.getTitular().setMatriculaJustica(null);
			}
		}
		
		if(beneficiario.getPlano() != null && beneficiario.getPlano().getCodigo() != null && beneficiario.getPlano().getCodigo() == 0){
			beneficiario.getPlano().setCodigo(null);
		}
		
		return beneficiarioDAO.findByFilter(beneficiario);
		
	}
	
	public RelatorioIRVO findRelatorioIR(RelatorioIRVO filtro) throws SmartEnvException, SmartAppException {
		
		if (filtro == null || filtro.getAno() == null) {
			throw new SmartAppException("O ano deve ser preenchido.");
		}
		
		filtro.setBeneficiarios(beneficiarioDAO.findRelatorioIR(filtro));
		
		
		return filtro;
		
	}
	
}
