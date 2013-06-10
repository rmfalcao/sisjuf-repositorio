package br.org.asserjuf.sisjuf.associados.cliente.web.bean;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.log4j.Logger;

import br.org.asserjuf.sisjuf.associados.AssociadoVO;
import br.org.asserjuf.sisjuf.associados.PessoaVO;
import br.org.asserjuf.sisjuf.associados.cliente.AssociadoDelegate;
import br.org.asserjuf.sisjuf.associados.convenio.ConvenioVO;
import br.org.asserjuf.sisjuf.associados.convenio.PlanoConvenioVO;
import br.org.asserjuf.sisjuf.associados.convenio.VinculacaoPlanoFiltroAssembler;
import br.org.asserjuf.sisjuf.associados.convenio.VinculacaoPlanoVO;
import br.org.asserjuf.sisjuf.associados.convenio.cliente.ConvenioDelegate;

import com.vortice.view.BasePageBean;

public class VinculacaoPlanoPageBean extends BasePageBean {

	private AssociadoDelegate			associadoDelegate;
	private ConvenioDelegate			convenioDelegate;
	
	private Collection<ConvenioVO> 		convenios;

	private Collection<PlanoConvenioVO> 		planos;
	
	private static final transient Logger LOG = Logger.getLogger(VinculacaoPlanoPageBean.class);
	
	private VinculacaoPlanoFiltroAssembler vinculacaoFiltro;

	private Collection<VinculacaoPlanoVO> vinculacoes;

	private VinculacaoPlanoVO vinculacao;
	
	public VinculacaoPlanoVO getVinculacao() {
		return vinculacao;
	}

	public VinculacaoPlanoPageBean(){
		try{ 
			System.out.println("...............VinculacaoPlanoPageBean");
			this.convenios = convenioDelegate.findConvenioByFilter(new ConvenioVO());
			
			this.vinculacaoFiltro = new VinculacaoPlanoFiltroAssembler();
			this.vinculacaoFiltro.setPessoa(new PessoaVO());
			this.vinculacaoFiltro.setPlano(new PlanoConvenioVO());
			this.vinculacaoFiltro.getPlano().setConvenio(new ConvenioVO());
			this.vinculacaoFiltro.setAssociado(new AssociadoVO());
			
			this.vinculacao = new VinculacaoPlanoVO();
			this.vinculacao.setPessoa(new PessoaVO());
			this.vinculacao.setPlano(new PlanoConvenioVO());
			this.vinculacao.getPlano().setConvenio(new ConvenioVO());
			this.vinculacao.setAssociado(new AssociadoVO());
			
			this.planos = new ArrayList<PlanoConvenioVO>();
			
			
//			this.associadoDelegate = new AssociadoDelegateTestView();
//			this.vinculacoes = associadoDelegate.findVinculacoes(this.vinculacaoFiltro);
		}catch(Exception e){
			 LOG.error("Error ", e);
			 tratarExcecao(e);
		 }
	
	}
	
	public void setCarregarVinculacoes(Integer codAssociado){
		this.vinculacaoFiltro.setAssociado(new AssociadoVO());
		this.vinculacaoFiltro.getAssociado().setCodigo(codAssociado);
	//	this.vinculacoes = associadoDelegate.findVinculacoes(this.vinculacaoFiltro);
	}
	
	public String salvar(){
		try {
			System.out.println(".................salvar");
			if (this.vinculacao.getCodigo() != null && this.vinculacao.getCodigo().intValue() > 0){
				associadoDelegate.updateVinculacao(vinculacao);
			}else{
				associadoDelegate.insertVinculacao(vinculacao);
			}

			return getSucesso();
		} catch (Exception e) {
			return tratarExcecao(e);
		}
	}
	
	public void carregar(){
		try {
			System.out.println("................carregar");
			setVinculacao(associadoDelegate.findVinculacaoByPrimaryKey(this.vinculacao));
//			return getSucesso();
		} catch (Exception e) {
			tratarExcecao(e);
		}
	}
	
	public String remover(){
		try {
		//	this.associadoDelegate.removeVinculacao(this.vinculacao);
			return getSucesso();
		} catch (Exception e) {
			return tratarExcecao(e);
		}
	}

	
	public void prepararNovo(){
		this.vinculacao = new VinculacaoPlanoVO();
		this.vinculacao.setAssociado(new AssociadoVO());
	}
	
	public VinculacaoPlanoVO setVinculacao() {
		return this.vinculacao;
	}

	public void setVinculacao(VinculacaoPlanoVO vinculacao) {
		this.vinculacao = vinculacao;
	}

	public void setAssociadoDelegate(AssociadoDelegate associadoDelegate) {
		this.associadoDelegate = associadoDelegate;
	}
	
	public void consultarVinculacoes(){
		System.out.println("........................ consultarVinculacoes." );
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

	public void setConvenioDelegate(ConvenioDelegate convenioDelegate) {
		this.convenioDelegate = convenioDelegate;
	}
}