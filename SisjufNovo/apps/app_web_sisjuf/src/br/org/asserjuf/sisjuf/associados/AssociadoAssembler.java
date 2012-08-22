package br.org.asserjuf.sisjuf.associados;

public class AssociadoAssembler extends AssociadoVO {
	
	private Boolean preCadastro;
	

	public Boolean getPreCadastro() {
		return preCadastro;
	}

	public void setPreCadastro(Boolean preCadastro) {
		this.preCadastro = preCadastro;
	}
	
	public String getStatusPreCadastro() {
		if (preCadastro!=null) {
			if (preCadastro) {
				return "S";
			}
		}
		
		return "N";
		
	}

	
	

}
