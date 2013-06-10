package br.org.asserjuf.sisjuf.associados.convenio;

import java.util.Date;

import br.org.asserjuf.sisjuf.associados.AssociadoVO;
import br.org.asserjuf.sisjuf.associados.PessoaVO;

/**
 * Classe que encapsula as informações de um vinculado
 * @author Paulo
 *
 */
public class VinculadoPlanoAssembler extends PessoaVO {

	private AssociadoVO associado;
	private ConvenioVO convenio;
	private PlanoConvenioVO plano;
	private ParentescoVO parentesco;
	private Date dataVinculacao;
	private Date dataDesVinculacao;

	public ConvenioVO getConvenio() {
		return convenio;
	}
	public void setConvenio(ConvenioVO convenio) {
		this.convenio = convenio;
	}
	public PlanoConvenioVO getPlano() {
		return plano;
	}
	public void setPlano(PlanoConvenioVO plano) {
		this.plano = plano;
	}
	public ParentescoVO getParentesco() {
		return parentesco;
	}
	public void setParentesco(ParentescoVO parentesco) {
		this.parentesco = parentesco;
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
	public AssociadoVO getAssociado() {
		return associado;
	}
	public void setAssociado(AssociadoVO associado) {
		this.associado = associado;
	}
	
	
}
