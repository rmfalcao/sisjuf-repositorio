package br.org.asserjuf.sisjuf.associados.convenio.cliente.web.bean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import br.com.falc.smartFW.exception.SmartAppException;
import br.com.falc.smartFW.exception.SmartEnvException;
import br.org.asserjuf.sisjuf.associados.AssociadoVO;
import br.org.asserjuf.sisjuf.associados.RelatorioIRVO;
import br.org.asserjuf.sisjuf.associados.convenio.BeneficiarioIRVO;
import br.org.asserjuf.sisjuf.associados.convenio.cliente.ConvenioDelegate;
import br.org.asserjuf.sisjuf.util.web.UtilDelegate;

import com.vortice.view.BasePageBean;

public class ImpostoPageBean extends BasePageBean {

	private transient ConvenioDelegate	delegate;
	
	private transient UtilDelegate 		utilDelegate;

	private RelatorioIRVO				filtro;
	
	private Collection<BeneficiarioIRVO>		beneficiarios;
	
	private static final transient SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	
	public ImpostoPageBean(){
		filtro = new RelatorioIRVO();
		filtro.setAssociado(new AssociadoVO());
		beneficiarios = new ArrayList<BeneficiarioIRVO>();
	}
	
	public String consulta(){
		try {
			beneficiarios = delegate.findRelatorioIR(filtro).getBeneficiarios();
			System.out.println("++++++" + beneficiarios);
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
	
	public String print(){
		return consulta();
	}
	
	public String getDataAtual(){
		try{
			return formatter.format(utilDelegate.getCurrentDate());
		}catch(Exception e){
			return "";
		}
	}
	
	public void setDelegate(ConvenioDelegate delegate) {
		this.delegate = delegate;
	}
	public Collection<BeneficiarioIRVO> getBeneficiarios() {
		return beneficiarios;
	}
	public RelatorioIRVO getFiltro() {
		return filtro;
	}
	public void setFiltro(RelatorioIRVO filtro) {
		this.filtro = filtro;
	}
	public void setUtilDelegate(UtilDelegate utilDelegate) {
		this.utilDelegate = utilDelegate;
	}
}
