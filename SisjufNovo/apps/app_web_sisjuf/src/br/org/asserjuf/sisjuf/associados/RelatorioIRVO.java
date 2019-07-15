package br.org.asserjuf.sisjuf.associados;

import java.util.Collection;

import br.com.falc.smartFW.SmartVO;
import br.org.asserjuf.sisjuf.associados.convenio.BeneficiarioIRVO;
import br.org.asserjuf.sisjuf.associados.convenio.BeneficiarioVO;

public class RelatorioIRVO extends SmartVO {

	private Integer ano;
	private	AssociadoVO associado;
	
	private Collection<BeneficiarioIRVO> beneficiarios;
	
	public Integer getAno() {
		return ano;
	}
	public void setAno(Integer ano) {
		this.ano = ano;
	}
	public AssociadoVO getAssociado() {
		return associado;
	}
	public void setAssociado(AssociadoVO associado) {
		this.associado = associado;
	}
	public Collection<BeneficiarioIRVO> getBeneficiarios() {
		return beneficiarios;
	}
	public void setBeneficiarios(Collection<BeneficiarioIRVO> beneficiarios) {
		this.beneficiarios = beneficiarios;
	}
	
	
	
}
