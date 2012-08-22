package br.org.asserjuf.sisjuf.associados.convenio.cliente.web.bean;

import java.util.ArrayList;
import java.util.Collection;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;

import br.org.asserjuf.sisjuf.associados.convenio.ConvenioVO;
import br.org.asserjuf.sisjuf.associados.convenio.PlanoConvenioVO;
import br.org.asserjuf.sisjuf.associados.convenio.cliente.ConvenioDelegate;
import br.org.asserjuf.sisjuf.associados.convenio.cliente.ConvenioDelegateTestView;

import com.vortice.view.BasePageBean;

public class PlanoConvenioPageBean extends BasePageBean {

	private PlanoConvenioVO			plano;
	
	private ConvenioDelegate	convenioDelegate;
	
	private Collection<PlanoConvenioVO>	planos;

	private static final transient Logger LOG = Logger.getLogger(PlanoConvenioPageBean.class);
	
	public PlanoConvenioPageBean(){
		try{ 
			this.plano = new PlanoConvenioVO();
			this.plano.setConvenio(new ConvenioVO());
		} catch (Exception e) {
			tratarExcecao(e);
		}
	
	}
	
	public String salvar(){
		try {
			
			System.out.println("SALVARRRRRRRRRRRR PLANOOOOOOOOOOOOO");
			if (this.plano.getCodigo() != null && this.plano.getCodigo().intValue() > 0){
				convenioDelegate.updatePlanoConvenio(plano);
			}else{
				convenioDelegate.insertPlanoConvenio(plano);
			}
			FacesMessage msgs = new FacesMessage("Registro Salvo com sucesso.");
			FacesContext facesContext =  FacesContext.getCurrentInstance();
			facesContext.addMessage(null, msgs);
			this.consultar();
			return getSucesso();
		} catch (Exception e) {
			return tratarExcecao(e);
		}
	}
	
	public String carregar(){
		try {
			this.plano = convenioDelegate.findPlanoConvenioByPrimaryKey(this.plano);
			return getSucesso();
		} catch (Exception e) {
			return tratarExcecao(e);
		}
	}
	
	public String consultar(){
		try {
			this.planos = this.convenioDelegate.findPlanoConvenioByFilter(this.plano);
			return getSucesso();
		} catch (Exception e) { 
			return tratarExcecao(e);
		}
	}
	
	public String remover(){
		try {
			this.convenioDelegate.removePlanoConvenio(this.plano);
			FacesMessage msgs = new FacesMessage("Registro removido com sucesso.");
			FacesContext.getCurrentInstance().addMessage(null, msgs);
			this.consultar();
			return getSucesso();
		} catch (Exception e) {
			return tratarExcecao(e);
		}
	}

	
	public void prepararNovo(){
		this.plano = new PlanoConvenioVO();
		this.plano.setConvenio(new ConvenioVO());
	}
	
	public PlanoConvenioVO getPlano() {
		return this.plano;
	}

	public void setPlano(PlanoConvenioVO plano) {
		ConvenioDelegateTestView.showLog(plano);
		this.plano = plano;
	}

	public void setConvenioDelegate(ConvenioDelegate convenioDelegate) {
		this.convenioDelegate = convenioDelegate;
	}

	public Collection<PlanoConvenioVO> getPlanos() {
		try{			
			return  this.convenioDelegate.findPlanoConvenioByFilter(plano);
		} catch (Exception e) {
			tratarExcecao(e);
		}		
		return new ArrayList<PlanoConvenioVO>();	
	}

	public void setAtualizarConvenio(Integer codigoConvenio){
		try {
			ConvenioVO convenio = new ConvenioVO();
			convenio.setCodigo(codigoConvenio);
			plano.setConvenio(convenio);
			consultar();
		} catch (Exception e) {
			tratarExcecao(e);
		}
	}
}