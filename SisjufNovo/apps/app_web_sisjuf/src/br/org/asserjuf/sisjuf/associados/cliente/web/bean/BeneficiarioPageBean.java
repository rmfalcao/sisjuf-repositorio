package br.org.asserjuf.sisjuf.associados.cliente.web.bean;

import java.util.Collection;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;

import br.org.asserjuf.sisjuf.associados.AssociadoVO;
import br.org.asserjuf.sisjuf.associados.cliente.AssociadoDelegate;
import br.org.asserjuf.sisjuf.associados.convenio.VinculadoPlanoAssembler;
import br.org.asserjuf.sisjuf.associados.convenio.ConvenioVO;
import br.org.asserjuf.sisjuf.associados.convenio.PlanoConvenioVO;
import br.org.asserjuf.sisjuf.associados.convenio.VinculacaoPlanoFiltroAssembler;
import br.org.asserjuf.sisjuf.associados.convenio.VinculacaoPlanoVO;

import com.vortice.view.BasePageBean;

public class BeneficiarioPageBean extends BasePageBean {

	private VinculadoPlanoAssembler			beneficiario;
	
	private AssociadoDelegate				associadoDelegate;
	
	private Collection<ConvenioVO> 			convenios;

	private Collection<PlanoConvenioVO> 	planos;
	
	private static final transient Logger	LOG = Logger.getLogger(BeneficiarioPageBean.class);
	
	private VinculacaoPlanoFiltroAssembler 	vinculacaoFiltro;

	private Collection<VinculacaoPlanoVO> 	vinculacoes;
	
	private VinculacaoPlanoVO 				vinculacao;
	
	
	
	public BeneficiarioPageBean(){
		try{ 
			System.out.println("...............OutroBeneficiavelPageBean");
			this.beneficiario = new VinculadoPlanoAssembler();
			this.beneficiario.setAssociado(new AssociadoVO());
		}catch(Exception e){
			 LOG.error("Error ", e);
			 tratarExcecao(e);
		 }
	
	}
	
	public String salvar(){
		try {
			System.out.println(".................salvar");
			if (this.beneficiario.getCodigo() != null && this.beneficiario.getCodigo().intValue() > 0){
				//associadoDelegate.updateOutroBeneficiavel(beneficiario);
			}else{
				//associadoDelegate.insertBeneficiario(beneficiario);
			}

			FacesMessage msgs = new FacesMessage("Registro Salvo com sucesso.");
			FacesContext facesContext =  FacesContext.getCurrentInstance();
			facesContext.addMessage(null, msgs);
			this.concultarVinculacoes();
			return getSucesso();
		} catch (Exception e) {
			return tratarExcecao(e);
		}
	}
	
	public void carregar(){
		try {
			System.out.println("................carregar");
			//setBeneficiario(associadoDelegate.findBeneficiarioByPrimaryKey(this.beneficiario));
//			return getSucesso();
		} catch (Exception e) {
			tratarExcecao(e);
		}
	}
	
	public String remover(){
		try {
			//this.associadoDelegate.removeBeneficiario(this.beneficiario);
			return getSucesso();
		} catch (Exception e) {
			return tratarExcecao(e);
		}
	}

	
	public void prepararNovo(){
		this.beneficiario = new VinculadoPlanoAssembler();
		this.beneficiario.setAssociado(new AssociadoVO());
	}
	
	public VinculadoPlanoAssembler getBeneficiario() {
		return this.beneficiario;
	}

	public void setBeneficiario(VinculadoPlanoAssembler beneficiario) {
		System.out.println("..........setPlano");
		this.beneficiario = beneficiario;
	}

	public void setAssociadoDelegate(AssociadoDelegate associadoDelegate) {
		this.associadoDelegate = associadoDelegate;
	}
	
	public void concultarVinculacoes(){
		//this.setVinculacoes(this.associadoDelegate.findVinculacoes(this.vinculacaoFiltro));
	}

	public void setConvenios(Collection<ConvenioVO> convenios) {
		this.convenios = convenios;
	}

	public Collection<ConvenioVO> getConvenios() {
		return convenios;
	}

	public void setPlanos(Collection<PlanoConvenioVO> planos) {
		this.planos = planos;
	}

	public Collection<PlanoConvenioVO> getPlanos() {
		return planos;
	}

	public void setVinculacaoFiltro(VinculacaoPlanoFiltroAssembler vinculacaoFiltro) {
		this.vinculacaoFiltro = vinculacaoFiltro;
	}

	public VinculacaoPlanoFiltroAssembler getVinculacaoFiltro() {
		return vinculacaoFiltro;
	}

	public void setVinculacoes(Collection<VinculacaoPlanoVO> vinculacoes) {
		this.vinculacoes = vinculacoes;
	}

	public Collection<VinculacaoPlanoVO> getVinculacoes() {
		return vinculacoes;
	}
}