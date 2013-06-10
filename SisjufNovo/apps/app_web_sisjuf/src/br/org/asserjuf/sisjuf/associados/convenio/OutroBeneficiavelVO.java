package br.org.asserjuf.sisjuf.associados.convenio;

import br.org.asserjuf.sisjuf.associados.AssociadoVO;
import br.org.asserjuf.sisjuf.associados.PessoaVO;

public class OutroBeneficiavelVO  extends PessoaVO {

	private static final long serialVersionUID = 1L;
	
	private AssociadoVO associado;
	
	public AssociadoVO getAssociado() {
		return associado;
	}
	public void setAssociado(AssociadoVO associado) {
		this.associado = associado;
	}
	
}