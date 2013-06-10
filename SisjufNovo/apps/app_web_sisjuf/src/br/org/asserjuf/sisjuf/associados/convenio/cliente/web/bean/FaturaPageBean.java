package br.org.asserjuf.sisjuf.associados.convenio.cliente.web.bean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import br.com.falc.smartFW.exception.SmartAppException;
import br.com.falc.smartFW.exception.SmartEnvException;
import br.org.asserjuf.sisjuf.associados.convenio.ConvenioVO;
import br.org.asserjuf.sisjuf.associados.convenio.FaturaArquivoVO;
import br.org.asserjuf.sisjuf.associados.convenio.FaturaFiltroAssembler;
import br.org.asserjuf.sisjuf.associados.convenio.FaturaVO;
import br.org.asserjuf.sisjuf.associados.convenio.cliente.ConvenioDelegate;
import br.org.asserjuf.sisjuf.associados.convenio.dados.StatusFaturaVO;
import br.org.asserjuf.sisjuf.util.web.UtilDelegate;

import com.vortice.view.BasePageBean;

public class FaturaPageBean extends BasePageBean {

	private transient ConvenioDelegate	delegate;
	
	private FaturaFiltroAssembler		faturaFiltro;
	
	private Collection<FaturaVO> 		faturas;
	
	private FaturaVO 					fatura;
	
	private static final transient SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	
	private transient UtilDelegate utilDelegate;
	
	private FaturaArquivoVO				faturaArquivo;
	
	public FaturaPageBean()
	{
		faturaFiltro = new FaturaFiltroAssembler();
		faturaFiltro.setConvenio(new ConvenioVO());
		faturaFiltro.setStatus(new StatusFaturaVO());
		
		fatura = new FaturaVO();
		fatura.setConvenio(new ConvenioVO());
		fatura.setStatus(new StatusFaturaVO());
	}
	
	public String carregar(){
		try {
			System.out.println("VEIO CARREGAR " + fatura.getCodigo());
			fatura = delegate.findByPrimaryKey(fatura);
			System.out.println("FATURA CONVENIO " + fatura.getConvenio().getNomeFantasia());
			return getSucesso();
		}catch(SmartEnvException envEx){
			String msgErr = "Ocorreu um erro inesperado, contate o seu administrador.";
			FacesMessage msgs = new FacesMessage(FacesMessage.SEVERITY_ERROR, msgErr, msgErr);
			FacesContext facesContext =  FacesContext.getCurrentInstance();
			facesContext.addMessage(null, msgs);
			return "falha";
		}catch(Exception e){
			String msgErr = "Ocorreu um erro inesperado, contate o seu administrador.";
			FacesMessage msgs = new FacesMessage(FacesMessage.SEVERITY_ERROR, msgErr, msgErr);
			FacesContext.getCurrentInstance().addMessage(null, msgs);
			return "falha";
		}
	}
	
	public String consulta(){
		try {
			faturas = delegate.findFaturaByFilter(faturaFiltro);
			System.out.println("++++++" + faturas);
			return getSucesso();
		} catch(SmartAppException appEx){
			FacesMessage msgs = new FacesMessage(FacesMessage.SEVERITY_ERROR, appEx.getMensagem(), appEx.getMensagem());
			FacesContext facesContext =  FacesContext.getCurrentInstance();
			facesContext.addMessage(null, msgs);
			return "falha";
		}catch(SmartEnvException envEx){
			String msgErr = "Ocorreu um erro inesperado, contate o seu administrador.";
			FacesMessage msgs = new FacesMessage(FacesMessage.SEVERITY_ERROR, msgErr, msgErr);
			FacesContext facesContext =  FacesContext.getCurrentInstance();
			facesContext.addMessage(null, msgs);
			return "falha";
		}catch(Exception e){
			String msgErr = "Ocorreu um erro inesperado, contate o seu administrador.";
			FacesMessage msgs = new FacesMessage(FacesMessage.SEVERITY_ERROR, msgErr, msgErr);
			FacesContext.getCurrentInstance().addMessage(null, msgs);
			return "falha";
		}
	}
	
	public void validarFatura(){
		try {
			delegate.validarFatura(faturaArquivo);
		} catch(SmartAppException appEx){
			FacesMessage msgs = new FacesMessage(FacesMessage.SEVERITY_ERROR, appEx.getMensagem(), appEx.getMensagem());
			FacesContext facesContext =  FacesContext.getCurrentInstance();
			facesContext.addMessage(null, msgs);
		}catch(SmartEnvException envEx){
			String msgErr = "Ocorreu um erro inesperado, contate o seu administrador.";
			FacesMessage msgs = new FacesMessage(FacesMessage.SEVERITY_ERROR, msgErr, msgErr);
			FacesContext facesContext =  FacesContext.getCurrentInstance();
			facesContext.addMessage(null, msgs);
		}catch(Exception e){
			String msgErr = "Ocorreu um erro inesperado, contate o seu administrador.";
			FacesMessage msgs = new FacesMessage(FacesMessage.SEVERITY_ERROR, msgErr, msgErr);
			FacesContext.getCurrentInstance().addMessage(null, msgs);
		}
	}
	
	public String print(){
		return consulta();
	}

	public Collection<ConvenioVO> getAllConvenios(){
		try {
			return delegate.findAllConvenio();
		} catch (Exception e) {
			tratarExcecao(e);
		}
		return new ArrayList<ConvenioVO>();
	}
	
	public Collection<StatusFaturaVO> getAllStatusFatura(){
		try {
			return delegate.findAllStatusFatura();
		} catch (Exception e) {
			tratarExcecao(e);
		}
		return new ArrayList<StatusFaturaVO>();
	}
	
	public FaturaFiltroAssembler getFaturaFiltro() {
		return faturaFiltro;
	}


	public void setFaturaFiltro(FaturaFiltroAssembler faturaFiltro) {
		this.faturaFiltro = faturaFiltro;
	}

	public void setDelegate(ConvenioDelegate delegate) {
		this.delegate = delegate;
	}

	public Collection<FaturaVO> getFaturas() {
		return faturas;
	}

	public void setFaturas(Collection<FaturaVO> faturas) {
		this.faturas = faturas;
	}

	public FaturaVO getFatura() {
		return fatura;
	}

	public void setFatura(FaturaVO fatura) {
		this.fatura = fatura;
	}
	
	public String getDataAtual(){
		try{
			return formatter.format(utilDelegate.getCurrentDate());
		}catch(Exception e){
			return "";
		}
	}
	
	public void setUtilDelegate(UtilDelegate utilDelegate) {
		this.utilDelegate = utilDelegate;
	}
	
	public Boolean getValidavel(){
		//A FATURA TEM QUE EXISTIR.
		System.out.println("fatura " + fatura);
		if (fatura != null && fatura.getCodigo() != null && fatura.getCodigo() > 0){
			System.out.println("fatura.codigo " + fatura.getCodigo());
			//O CONVENIO TEM QUE SER DE CATEGORIA FIXA
			System.out.println("fatura.getConvenio() " + fatura.getConvenio());
			if (fatura.getConvenio() != null && "F".equals(fatura.getConvenio().getCategoria())){
				System.out.println("fatura.getConvenio().getCategoria() " + fatura.getConvenio().getCategoria());
				//A FATURA TEM QUE ESTAR COM O STATUS DE GERADA
				if ((new Integer(1)).equals(fatura.getStatus().getCodigo())){
					return true;
				}
			}
		}
		return true;
	}
}