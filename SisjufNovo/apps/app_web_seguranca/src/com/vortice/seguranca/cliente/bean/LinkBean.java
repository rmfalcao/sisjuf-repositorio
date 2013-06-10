package com.vortice.seguranca.cliente.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.apache.log4j.Logger;

import com.vortice.seguranca.abstracao.cliente.SegurancaDelegateIf;
import com.vortice.seguranca.abstracao.cliente.bean.SegurancaPageBean;
import com.vortice.seguranca.vo.LinkVO;

public class LinkBean extends SegurancaPageBean {
	
	private SegurancaDelegateIf delegate;
	private List<LinkVO>		links;
	private LinkVO 				link;
	private Logger 				log = Logger.getLogger(LinkBean.class);
	
	public LinkBean(){
		try{
			links = new ArrayList<LinkVO>();
			link = new LinkVO();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public String salvar(){
		try{
			if (getLink().getCodigo() != null && getLink().getCodigo().intValue() > 0){
				delegate.update(link);
			}else{
				delegate.insert(link);
			}
			FacesMessage msgs = new FacesMessage("Registro Salvo com Sucesso.");
			FacesContext.getCurrentInstance().addMessage(null, msgs);
			return getSucesso();
		}catch(Exception e){
			FacesMessage msgs = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msgs);
			return tratarExcecao(e);
		}
	}
	
	public String consultar(){
		LinkVO vo = new LinkVO();
		vo.setDescricao(getLink().getDescricao());
		vo.setUrl(getLink().getUrl());
		log.debug("getLink().getUrl() " + getLink().getUrl());
		try{
			links.addAll(delegate.findByFilter(vo));
			return getSucesso();
		}catch(Exception e){
			FacesMessage msgs = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msgs);
			return tratarExcecao(e);
		}
	}
	
	public String remover(){
		try{
			delegate.remove(getLink());
			FacesMessage msgs = new FacesMessage("Registro Removido com Sucesso.");
			FacesContext.getCurrentInstance().addMessage(null, msgs);
			return getSucesso();
		}catch(Exception e){
			FacesMessage msgs = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msgs);
			return tratarExcecao(e);
		}
	}
	
	public String carregar(){
		try{
			link = delegate.findByPrimaryKey(getLink());
			return getSucesso();
		}catch(Exception e){
			FacesMessage msgs = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msgs);
			return tratarExcecao(e);
		}
	}
	
	public void scrollerAction(ActionEvent event){
		/*System.out.println("VEIO NO ACTION");
        ScrollerActionEvent scrollerEvent = (ScrollerActionEvent) event;
        FacesContext.getCurrentInstance().getExternalContext().log("scrollerAction: facet: "
                                        + scrollerEvent.getScrollerfacet() + ", pageindex: " 
                                        + scrollerEvent.getPageIndex());*/
        //this.consultar();
        
    }

	public List<LinkVO> getLinks() {
		return links;
	}

	public void setLinks(List<LinkVO> links) {
		this.links = links;
	}

	public LinkVO getLink() {
		return link;
	}

	public void setLink(LinkVO link) {
		this.link = link;
	}

	public void setDelegate(SegurancaDelegateIf delegate) {
		this.delegate = delegate;
	}
	
}