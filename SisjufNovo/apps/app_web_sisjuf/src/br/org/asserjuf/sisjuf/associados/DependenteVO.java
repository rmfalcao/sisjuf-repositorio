package br.org.asserjuf.sisjuf.associados;

public class DependenteVO extends PessoaVO {

	private AssociadoVO 	associado;

	private ParentescoVO	parentesco;
	
	
	
	public ParentescoVO getParentesco() {
		return parentesco;
	}
	public void setParentesco(ParentescoVO parentesco) {
		this.parentesco = parentesco;
	}
	public AssociadoVO getAssociado() {
		return associado;
	}
	public void setAssociado(AssociadoVO associado) {
		this.associado = associado;
	}
	
}
