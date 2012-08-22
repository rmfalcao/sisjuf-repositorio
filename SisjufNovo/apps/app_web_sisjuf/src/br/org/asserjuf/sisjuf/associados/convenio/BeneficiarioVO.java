package br.org.asserjuf.sisjuf.associados.convenio;

import java.util.Date;

import br.org.asserjuf.sisjuf.associados.AssociadoVO;
import br.org.asserjuf.sisjuf.associados.PessoaVO;

public class BeneficiarioVO extends PessoaVO{

//	private Collection<VinculacaoPlanoVO> 	vinculacoes;
	
	private PlanoConvenioVO					plano;
	private	AssociadoVO						titular;
	private	String							tipoBeneficiario;
	
	private Date							dataVinculacao;
	
	private VinculacaoPlanoVO				vinculacao;
	
	
	
	public VinculacaoPlanoVO getVinculacao() {
		return vinculacao;
	}
	public void setVinculacao(VinculacaoPlanoVO vinculacao) {
		this.vinculacao = vinculacao;
	}
	public String getTipoBeneficiario() {
		return tipoBeneficiario;
	}
	public void setTipoBeneficiario(String tipoBeneficiario) {
		this.tipoBeneficiario = tipoBeneficiario;
	}
	public Date getDataVinculacao() {
		return dataVinculacao;
	}
	public void setDataVinculacao(Date dataVinculacao) {
		this.dataVinculacao = dataVinculacao;
	}
	public PlanoConvenioVO getPlano() {
		return plano;
	}
	public void setPlano(PlanoConvenioVO plano) {
		this.plano = plano;
	}
	public AssociadoVO getTitular() {
		return titular;
	}
	public void setTitular(AssociadoVO titular) {
		this.titular = titular;
	}
	
	
}
