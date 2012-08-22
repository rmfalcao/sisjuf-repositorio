package com.vortice.seguranca.cliente.bean;

import java.util.ArrayList;
import java.util.Collection;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import com.vortice.seguranca.abstracao.cliente.SegurancaDelegateIf;
import com.vortice.seguranca.abstracao.cliente.bean.SegurancaPageBean;
import com.vortice.seguranca.vo.FuncaoVO;
import com.vortice.seguranca.vo.LinkVO;

public class FuncaoBean extends SegurancaPageBean {
	
	private SegurancaDelegateIf delegate;
	private Collection 			funcoes;
	private FuncaoVO 			funcao;
	
	public FuncaoBean(){
		try{
			funcoes = new ArrayList();
			funcao = new FuncaoVO();
			funcao.setLink(new LinkVO());
		}catch(Exception e){
			tratarExcecao(e);
		}
	}
	
	public String salvar(){
		try{
			if (getFuncao().getCodigo() != null && getFuncao().getCodigo().intValue() > 0){
				delegate.update(getFuncao());
			}else{
				delegate.insert(getFuncao());
			}
			FacesMessage msgs = new FacesMessage("Registro Salvo com Sucesso.");
			FacesContext.getCurrentInstance().addMessage(null, msgs);
			return getSucesso();
		}catch(Exception e){
			this.getFuncao().setCodigo(null);
			FacesMessage msgs = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msgs);
			return tratarExcecao(e);
		}
	}
	
	public String consultar(){
		try{
			setFuncoes(delegate.findByFilter(getFuncao()));
			return getSucesso();
		}catch(Exception e){
			FacesMessage msgs = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msgs);
			return tratarExcecao(e);
		}
	}
	
	public String remover(){
		try{
			delegate.remove(getFuncao());
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
			setFuncao(delegate.findByPrimaryKey(getFuncao()));
			return getSucesso();
		}catch(Exception e){
			FacesMessage msgs = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msgs);
			return tratarExcecao(e);
		}
	}
	
	public void scrollerAction(ActionEvent event){
    }

	public final FuncaoVO getFuncao() {
		return funcao;
	}

	public final void setFuncao(FuncaoVO funcao) {
		this.funcao = funcao;
	}

	public Collection getFuncoes() {
		return funcoes;
	}

	public void setFuncoes(Collection funcoes) {
		this.funcoes = funcoes;
	}

	public void setDelegate(SegurancaDelegateIf delegate) {
		this.delegate = delegate;
	}
	
}