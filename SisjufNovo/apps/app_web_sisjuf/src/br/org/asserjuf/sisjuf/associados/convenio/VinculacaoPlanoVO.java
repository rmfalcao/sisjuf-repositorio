package br.org.asserjuf.sisjuf.associados.convenio;

import java.util.Date;

import br.com.falc.smartFW.SmartVO;
import br.org.asserjuf.sisjuf.associados.AssociadoVO;
import br.org.asserjuf.sisjuf.associados.PessoaVO;

public class VinculacaoPlanoVO extends SmartVO {
		
	private Integer codigo;
	private PessoaVO pessoa;
	private AssociadoVO associado;
	private BeneficiarioVO beneficiario;
	private PlanoConvenioVO plano;
	private Date dataVinculacao;
	private Date dataDesVinculacao;
	private Integer codigoHist;
	private ParentescoVO parentesco;
	
	
	
	public BeneficiarioVO getBeneficiario() {
		return beneficiario;
	}
	public void setBeneficiario(BeneficiarioVO beneficiario) {
		this.beneficiario = beneficiario;
	}
	public Integer getCodigo() {
		return codigo;
	}
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	public PessoaVO getPessoa() {
		return pessoa;
	}
	public void setPessoa(PessoaVO pessoa) {
		this.pessoa = pessoa;
	}
	public AssociadoVO getAssociado() {
		return associado;
	}
	public void setAssociado(AssociadoVO associado) {
		this.associado = associado;
	}
	public PlanoConvenioVO getPlano() {
		return plano;
	}
	public void setPlano(PlanoConvenioVO plano) {
		this.plano = plano;
	}
	public Date getDataVinculacao() {
		return dataVinculacao;
	}
	public void setDataVinculacao(Date dataVinculacao) {
		this.dataVinculacao = dataVinculacao;
	}
	public Date getDataDesVinculacao() {
		return dataDesVinculacao;
	}
	public void setDataDesVinculacao(Date dataDesVinculacao) {
		this.dataDesVinculacao = dataDesVinculacao;
	}
	public Integer getCodigoHist() {
		return codigoHist;
	}
	public void setCodigoHist(Integer codigoHist) {
		this.codigoHist = codigoHist;
	}
	public ParentescoVO getParentesco() {
		return parentesco;
	}
	public void setParentesco(ParentescoVO parentesco) {
		this.parentesco = parentesco;
	}
		
}