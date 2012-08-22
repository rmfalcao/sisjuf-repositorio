package com.vortice.seguranca.cliente.bean;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import javax.faces.validator.ValidatorException;

import org.apache.log4j.Logger;

import com.vortice.seguranca.abstracao.cliente.SegurancaDelegateIf;
import com.vortice.seguranca.abstracao.cliente.bean.SegurancaPageBean;
import com.vortice.seguranca.vo.FuncaoVO;
import com.vortice.seguranca.vo.FuncionalidadeVO;

public class FuncionalidadeBean extends SegurancaPageBean {
	
	private FuncionalidadeVO		funcionalidade;
	private List<SelectItem>		funcoes;
	private List 					nfuncoes; 
	private List<FuncionalidadeVO>	funcionalidades;
	private SegurancaDelegateIf		delegate;
	private FacesMessage 			msgs;
	private Logger 					LOG = Logger.getLogger(FuncionalidadeBean.class);
	
	public FuncionalidadeBean(){
		try{
			funcionalidades = new ArrayList<FuncionalidadeVO>();
			funcionalidade = new FuncionalidadeVO();
			funcionalidade.setFuncoes(new ArrayList());
			msgs = new FacesMessage();
			funcoes = new ArrayList<SelectItem>();
		}catch(Exception e){
			tratarExcecao(e);
		}
	}
	
	public String salvar(){
		try{
//			for (FuncaoVO funcao : funcoes){
//				
//			}
			Collection collFuncao = extractSelect(funcoes, "com.vortice.seguranca.vo.FuncaoVO", "codigo", "nome");
			funcionalidade.setFuncoes(collFuncao);
			if (funcionalidade.getCodigo() != null && funcionalidade.getCodigo().intValue() > 0){
				delegate.update(funcionalidade);
			}else{
				delegate.insert(funcionalidade);
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
		try{
			Collection collFuncionalidade = delegate.findByFilter(getFuncionalidade());
			getFuncionalidades().addAll(collFuncionalidade);
			return getSucesso();
		}catch(Exception e){
			FacesMessage msgs = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msgs);
			return tratarExcecao(e);
		}
	}
	
	public String remover(){
		try{
			delegate.remove(getFuncionalidade());
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
			setFuncionalidade(delegate.findByPrimaryKey(getFuncionalidade()));
//			nfuncoes = getSelect(delegate.findFuncaoByNFuncionalidade(getFuncionalidade()), "codigo", "nome");
//			funcoes = getSelect(delegate.findFuncaoByFuncionalidade(getFuncionalidade()), "codigo", "nome");
			Collection<FuncaoVO> funcoesFuncionalidade = delegate.findFuncaoByNFuncionalidade(getFuncionalidade());
			nfuncoes = new ArrayList<SelectItem>();
			for (FuncaoVO funcao : funcoesFuncionalidade){
				SelectItem selectItem = new SelectItem(funcao.getCodigo().toString(), funcao.getNome());
				nfuncoes.add(selectItem);
			}
			
			funcoesFuncionalidade = delegate.findFuncaoByFuncionalidade(getFuncionalidade());
			funcoes = new ArrayList<SelectItem>();
			for (FuncaoVO funcao : funcoesFuncionalidade){
				SelectItem selectItem = new SelectItem(funcao.getCodigo().toString(), funcao.getNome());
				funcoes.add(selectItem);
			}
			return getSucesso();
		}catch(Exception e){
			FacesMessage msgs = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msgs);
			return tratarExcecao(e);
		}
	}
	
	public void scrollerAction(ActionEvent event){
    }


	public List<FuncionalidadeVO> getFuncionalidades() {
		return funcionalidades;
	}

	public void setFuncionalidades(List<FuncionalidadeVO> funcionalidades) {
		this.funcionalidades = funcionalidades;
	}

	public List getFuncoes() {
		return funcoes;
	}

	public void setFuncoes(List funcoes) {
		this.funcoes = funcoes;
	}

	public List getNfuncoes() {
		if (nfuncoes == null){
			nfuncoes = new ArrayList<SelectItem>();
			try {
				Collection<FuncaoVO> collAllfuncao = delegate.findFuncaoAll();
				if (collAllfuncao != null && collAllfuncao.size() > 0){
					for (FuncaoVO funcao : collAllfuncao){
						SelectItem selectItem = new SelectItem(funcao.getCodigo().toString(), funcao.getNome());
						nfuncoes.add(selectItem);
					}
				}
//				nfuncoes = (collAllfuncao != null && collAllfuncao.size() > 0) ? getSelect(collAllfuncao, "codigo", "nome") : new ArrayList();
			} catch (Exception e) {
				LOG.error("ERRO NO MOMENTO DE RECUPERAR A COLEÇÃO DE SELECTITEM", e);
			}
		}
		return nfuncoes;
		
	}

	public void setNfuncoes(List nfuncoes) {
		this.nfuncoes = nfuncoes;
	}

	public final FuncionalidadeVO getFuncionalidade() {
		return funcionalidade;
	}

	public final void setFuncionalidade(FuncionalidadeVO funcionalidade) {
		this.funcionalidade = funcionalidade;
	}
	
	public FacesMessage getMsgs() {
		return msgs;
	}

	public void setMsgs(FacesMessage msgs) {
		this.msgs = msgs;
	}

	public void validaFuncao(FacesContext context, UIComponent component, Object value)  throws ValidatorException{
		funcoes = new ArrayList();
		String[] s = (String[])value;
		for (int i = 0; i < s.length; i++){
			funcoes.add(new SelectItem(s[i], "", null));
		}
	}

	public void setDelegate(SegurancaDelegateIf delegate) {
		this.delegate = delegate;
	}
	
}