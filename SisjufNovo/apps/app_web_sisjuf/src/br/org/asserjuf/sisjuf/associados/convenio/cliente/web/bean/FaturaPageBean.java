package br.org.asserjuf.sisjuf.associados.convenio.cliente.web.bean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.commons.lang.StringUtils;
import org.richfaces.event.UploadEvent;
import org.richfaces.model.UploadItem;

import br.com.falc.smartFW.exception.SmartAppException;
import br.com.falc.smartFW.exception.SmartEnvException;
import br.org.asserjuf.sisjuf.associados.convenio.ConvenioVO;
import br.org.asserjuf.sisjuf.associados.convenio.FaturaFiltroAssembler;
import br.org.asserjuf.sisjuf.associados.convenio.FaturaVO;
import br.org.asserjuf.sisjuf.associados.convenio.cliente.ConvenioDelegate;
import br.org.asserjuf.sisjuf.associados.convenio.dados.StatusFaturaVO;
import br.org.asserjuf.sisjuf.financeiro.LancamentoFaturaVO;
import br.org.asserjuf.sisjuf.financeiro.LancamentoVO;
import br.org.asserjuf.sisjuf.financeiro.web.cliente.FinanceiroDelegate;
import br.org.asserjuf.sisjuf.util.ParametroVO;
import br.org.asserjuf.sisjuf.util.web.UtilDelegate;

import com.vortice.view.BasePageBean;

public class FaturaPageBean extends BasePageBean {

	private transient ConvenioDelegate	delegate;
	
	private FaturaFiltroAssembler		faturaFiltro;
	
	private Collection<FaturaVO> 		faturas;
	
	private FaturaVO 					fatura;
	
	private LancamentoVO				lancamento;
	
	private static final transient SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	
	private transient UtilDelegate utilDelegate;
	private String tipoArquivoFatura;
	private byte[] conteudoArquivoFatura;
	
	public FaturaPageBean()
	{
		faturaFiltro = new FaturaFiltroAssembler();
		faturaFiltro.setConvenio(new ConvenioVO());
		faturaFiltro.setStatus(new StatusFaturaVO());
		
		fatura = new FaturaVO();
		fatura.setConvenio(new ConvenioVO());
		fatura.setStatus(new StatusFaturaVO());
		
		lancamento = new LancamentoVO();
	}
	
	public String carregar(){
		try {
			System.out.println("VEIO CARREGAR!!!");
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
	
	public String carregarFaturaADebitarAPartirLancamento(){
		try {
			
			Collection<FaturaVO> faturas = delegate.findFaturaByLancamento(lancamento);
			if(faturas != null && faturas.size() > 0){
				if(faturas.size() == 1){
					fatura = faturas.iterator().next();
				}else{
					throw new SmartAppException("Lançamento possui mais de uma fatura associada.");
				}
				fatura = delegate.findByPrimaryKey(fatura);
			}else{
				throw new SmartAppException("Lançamento não possui uma fatura associada.");
			}
			return getSucesso();
		}catch(SmartAppException appEx){
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
	
	public void upload(UploadEvent e) { 
		UploadItem uploadItem = e.getUploadItem();	
		conteudoArquivoFatura = uploadItem.getData();
	} 
	
	public String validarFatura(){
		try {
			if(StringUtils.isNotEmpty(tipoArquivoFatura)){
				if(tipoArquivoFatura.equalsIgnoreCase("VITALMED")){
					
				}else if(tipoArquivoFatura.equalsIgnoreCase("PROMEDICA")){
					
				}else if(tipoArquivoFatura.equalsIgnoreCase("ODONTOSYSTEM")){
					
				}
			}
			fatura = delegate.findByPrimaryKey(fatura);
			FacesMessage msgs = new FacesMessage("Fatura validada com sucesso.");
			FacesContext facesContext =  FacesContext.getCurrentInstance();
			facesContext.addMessage("convenioMsgs", msgs);	
			carregar();
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
	
	public String cancelarFatura(){
		try {
			fatura.getStatus().setCodigo(new Short(utilDelegate.findParametroByPrimaryKey(new ParametroVO("STATUS_FATURA_CANCELADA")).getValorTextual()));
			delegate.updateStatus(fatura);
			FacesMessage msgs = new FacesMessage("Fatura cancelada com sucesso.");
			FacesContext facesContext =  FacesContext.getCurrentInstance();
			facesContext.addMessage("convenioMsgs", msgs);	
			carregar();
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
	
	public boolean isFaturaCancelada(){
		if(fatura != null && fatura.getStatus() != null && fatura.getStatus().getCodigo() != null && fatura.getStatus().getCodigo().intValue() == 3){
			return true;
		}
		return false;
	}

	public byte[] getConteudoArquivoFatura() {
		return conteudoArquivoFatura;
	}

	public void setConteudoArquivoFatura(byte[] conteudoArquivoFatura) {
		this.conteudoArquivoFatura = conteudoArquivoFatura;
	}

	public String getTipoArquivoFatura() {
		return tipoArquivoFatura;
	}

	public void setTipoArquivoFatura(String tipoArquivoFatura) {
		this.tipoArquivoFatura = tipoArquivoFatura;
	}

	public LancamentoVO getLancamento() {
		return lancamento;
	}

	public void setLancamento(LancamentoVO lancamento) {
		this.lancamento = lancamento;
	}
	
}