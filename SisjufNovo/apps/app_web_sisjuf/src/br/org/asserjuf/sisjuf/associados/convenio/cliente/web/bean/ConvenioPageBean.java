package br.org.asserjuf.sisjuf.associados.convenio.cliente.web.bean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ListIterator;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import br.com.falc.smartFW.exception.SmartAppException;
import br.com.falc.smartFW.exception.SmartEnvException;
import br.org.asserjuf.sisjuf.associados.AssociadoVO;
import br.org.asserjuf.sisjuf.associados.convenio.AtividadeConvenioVO;
import br.org.asserjuf.sisjuf.associados.convenio.BeneficiarioVO;
import br.org.asserjuf.sisjuf.associados.convenio.ConvenioVO;
import br.org.asserjuf.sisjuf.associados.convenio.FaturaVO;
import br.org.asserjuf.sisjuf.associados.convenio.HistoricoValorPlanoConvenioVO;
import br.org.asserjuf.sisjuf.associados.convenio.ItemFaturaVO;
import br.org.asserjuf.sisjuf.associados.convenio.PlanoConvenioVO;
import br.org.asserjuf.sisjuf.associados.convenio.VinculacaoPlanoVO;
import br.org.asserjuf.sisjuf.associados.convenio.cliente.ConvenioDelegate;
import br.org.asserjuf.sisjuf.entidadesComuns.EstadoVO;
import br.org.asserjuf.sisjuf.financeiro.ContaVO;
import br.org.asserjuf.sisjuf.financeiro.web.cliente.FinanceiroDelegate;
import br.org.asserjuf.sisjuf.util.web.UtilDelegate;

import com.vortice.seguranca.vo.UsuarioVO;
import com.vortice.view.BasePageBean;
import com.vortice.view.Formatador;

public class ConvenioPageBean extends BasePageBean {

	private ConvenioVO						convenio;	
	private transient ConvenioDelegate		delegate;	
	private transient FinanceiroDelegate	financeiroDelegate;
	private Collection<AtividadeConvenioVO>	atividades;
	private Collection<EstadoVO> 			estados;
	private Collection<ConvenioVO> 			convenios;
	private Collection<FaturaVO> 			ultimasFaturas;
	private Collection<AtividadeConvenioVO>	atividadesAtivas;
	
	private PlanoConvenioVO					planoConvenio;
	
	private Collection<PlanoConvenioVO>		planosConvenio;
	
	private Collection<PlanoConvenioVO>     planosConvenioHistorico;
	
	private Collection<BeneficiarioVO>		beneficiariosConvenios;
	
	private Collection<PlanoConvenioVO>		printPlanosConvenio;
	
	private transient UtilDelegate 			utilDelegate;
	
	private static final transient SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	
	private FaturaVO						fatura;
	private ItemFaturaVO					itemFatura;
	
	private static final transient Logger LOG = Logger.getLogger(ConvenioPageBean.class);
	
	private BeneficiarioVO beneficiario;
	private Collection planos;
	

	public ConvenioPageBean(){
		try{ 
			this.convenio = new ConvenioVO();
			this.convenio.setEstado(new EstadoVO());
			this.convenio.setAtividadeConvenio(new AtividadeConvenioVO());
			ultimasFaturas = new ArrayList<FaturaVO>();
			planoConvenio = new PlanoConvenioVO();
			
			fatura = new FaturaVO();
			fatura.setContaCredito(new ContaVO());
			fatura.setContaDebito(new ContaVO());
			fatura.setConvenio(new ConvenioVO());
			fatura.setItens(new ArrayList<ItemFaturaVO>());
			
			itemFatura = new ItemFaturaVO();
			itemFatura.setBeneficiario(new BeneficiarioVO());
			itemFatura.setPlano(new PlanoConvenioVO());
			itemFatura.setVinculacao(new VinculacaoPlanoVO());
			
			beneficiario = new BeneficiarioVO();
			beneficiario.setTitular(new AssociadoVO());
			beneficiario.setPlano(new PlanoConvenioVO());
			
			planosConvenioHistorico = new ArrayList<PlanoConvenioVO>();
		} catch (Exception e) {
			tratarExcecao(e);
		}
	}
	
	public String salvar(){
		LOG.info("-----------convenio.getNomeFantasia()------------- " + convenio.getNomeFantasia());
		try {
			if (this.convenio.getCodigo() != null && this.convenio.getCodigo().intValue() > 0){
				delegate.updateConvenio(convenio);
				FacesMessage msgs = new FacesMessage("Registro Atualizado com sucesso.");
				FacesContext facesContext =  FacesContext.getCurrentInstance();
				facesContext.addMessage("convenioMsgs", msgs);				
			}else{
				delegate.insertConvenio(convenio);
				FacesMessage msgs = new FacesMessage("Registro Salvo com sucesso.");
				FacesContext facesContext =  FacesContext.getCurrentInstance();
				facesContext.addMessage("convenioMsgs", msgs);				
			}
			planoConvenio.setConvenio(convenio);
			return getSucesso();
		}catch(SmartAppException appEx){
			FacesMessage msgs = new FacesMessage(FacesMessage.SEVERITY_ERROR, appEx.getMensagem(), appEx.getMensagem());
			FacesContext facesContext =  FacesContext.getCurrentInstance();
			facesContext.addMessage(null, msgs);
			LOG.error("Error ", appEx);
			return "falha";
		}catch(SmartEnvException envEx){
			String msgErr = "Ocorreu um erro inesperado, contate o seu administrador.";
			FacesMessage msgs = new FacesMessage(FacesMessage.SEVERITY_ERROR, msgErr, msgErr);
			FacesContext facesContext =  FacesContext.getCurrentInstance();
			facesContext.addMessage(null, msgs);
			LOG.error("Error ", envEx);
			return "falha";
		}catch(Exception e){
			String msgErr = "Ocorreu um erro inesperado, contate o seu administrador.";
			LOG.error("Error ", e);
			FacesMessage msgs = new FacesMessage(FacesMessage.SEVERITY_ERROR, msgErr, msgErr);
			FacesContext.getCurrentInstance().addMessage(null, msgs);
			return "falha";
		}
	}
	
	public String salvarPlano(){
		try {
			LOG.info(" nome " + planoConvenio.getNome() );
			if (this.planoConvenio.getCodigo() != null && this.planoConvenio.getCodigo().intValue() > 0){				
				LOG.debug("ATUALIZANDOOOOOOOOOOOOOOOOOOO " + this.planoConvenio.getCodigo());
				delegate.updatePlanoConvenio(planoConvenio);
				FacesMessage msgs = new FacesMessage("Plano Atualizado com sucesso.");
				FacesContext facesContext =  FacesContext.getCurrentInstance();
				facesContext.addMessage(null, msgs);
			}else{
				LOG.debug("ATUALIZANDOOOOOOOOOOOOOOOOOOO " + this.planoConvenio.getCodigo());
				delegate.insertPlanoConvenio(planoConvenio);
				FacesMessage msgs = new FacesMessage("Plano Salvo com sucesso.");
				FacesContext facesContext =  FacesContext.getCurrentInstance();
				facesContext.addMessage(null, msgs);
			}
			this.consultar();
			return getSucesso();
		}catch(SmartAppException appEx){
			FacesMessage msgs = new FacesMessage(FacesMessage.SEVERITY_ERROR, appEx.getMensagem(), appEx.getMensagem());
			FacesContext facesContext =  FacesContext.getCurrentInstance();
			facesContext.addMessage(null, msgs);
			LOG.error("Error ", appEx);
			return "falha";
		}catch(SmartEnvException envEx){
			String msgErr = "Ocorreu um erro inesperado, contate o seu administrador.";
			FacesMessage msgs = new FacesMessage(FacesMessage.SEVERITY_ERROR, msgErr, msgErr);
			FacesContext facesContext =  FacesContext.getCurrentInstance();
			facesContext.addMessage(null, msgs);
			LOG.error("Error ", envEx);
			return "falha";
		}catch(Exception e){
			String msgErr = "Ocorreu um erro inesperado, contate o seu administrador.";
			LOG.error("Error ", e);
			FacesMessage msgs = new FacesMessage(FacesMessage.SEVERITY_ERROR, msgErr, msgErr);
			FacesContext.getCurrentInstance().addMessage(null, msgs);
			return "falha";
		}
	}
	
	public String carregar(){
		try {
			this.convenio = delegate.findConvenioByPrimaryKey(convenio);
			prepararNovoPlano();
			
			carregarUltimasFaturas();

			return getSucesso();
		}catch(SmartAppException appEx){
			FacesMessage msgs = new FacesMessage(FacesMessage.SEVERITY_ERROR, appEx.getMensagem(), appEx.getMensagem());
			FacesContext facesContext =  FacesContext.getCurrentInstance();
			facesContext.addMessage(null, msgs);
			LOG.error("Error ", appEx);
			return "falha";
		}catch(SmartEnvException envEx){
			String msgErr = "Ocorreu um erro inesperado, contate o seu administrador.";
			FacesMessage msgs = new FacesMessage(FacesMessage.SEVERITY_ERROR, msgErr, msgErr);
			FacesContext facesContext =  FacesContext.getCurrentInstance();
			facesContext.addMessage(null, msgs);
			LOG.error("Error ", envEx);
			return "falha";
		}catch(Exception e){
			String msgErr = "Ocorreu um erro inesperado, contate o seu administrador.";
			LOG.error("Error ", e);
			FacesMessage msgs = new FacesMessage(FacesMessage.SEVERITY_ERROR, msgErr, msgErr);
			FacesContext.getCurrentInstance().addMessage(null, msgs);
			return "falha";
		}
	}
	
	public String carregarPlano(){
		try {
			this.planoConvenio = delegate.findPlanoConvenioByPrimaryKey(this.planoConvenio);
			return getSucesso();
		}catch(SmartAppException appEx){
			FacesMessage msgs = new FacesMessage(FacesMessage.SEVERITY_ERROR, appEx.getMensagem(), appEx.getMensagem());
			FacesContext facesContext =  FacesContext.getCurrentInstance();
			facesContext.addMessage(null, msgs);
			LOG.error("Error ", appEx);
			return "falha";
		}catch(SmartEnvException envEx){
			String msgErr = "Ocorreu um erro inesperado, contate o seu administrador.";
			FacesMessage msgs = new FacesMessage(FacesMessage.SEVERITY_ERROR, msgErr, msgErr);
			FacesContext facesContext =  FacesContext.getCurrentInstance();
			facesContext.addMessage(null, msgs);
			LOG.error("Error ", envEx);
			return "falha";
		}catch(Exception e){
			String msgErr = "Ocorreu um erro inesperado, contate o seu administrador.";
			LOG.error("Error ", e);
			FacesMessage msgs = new FacesMessage(FacesMessage.SEVERITY_ERROR, msgErr, msgErr);
			FacesContext.getCurrentInstance().addMessage(null, msgs);
			return "falha";
		}
	}

	private void carregarUltimasFaturas() {
		FaturaVO fatura = new FaturaVO();
		fatura.setConvenio(this.convenio);
		this.ultimasFaturas = this.delegate.findUltimasFaturas(fatura);
	}
	
	public String consultar(){
		try {
			this.convenios = this.delegate.findConvenioByFilter(this.convenio);			
			return getSucesso();
		} catch(SmartAppException appEx){
			FacesMessage msgs = new FacesMessage(FacesMessage.SEVERITY_ERROR, appEx.getMensagem(), appEx.getMensagem());
			FacesContext facesContext =  FacesContext.getCurrentInstance();
			facesContext.addMessage(null, msgs);
			LOG.error("Error ", appEx);
			return "falha";
		}catch(SmartEnvException envEx){
			String msgErr = "Ocorreu um erro inesperado, contate o seu administrador.";
			FacesMessage msgs = new FacesMessage(FacesMessage.SEVERITY_ERROR, msgErr, msgErr);
			FacesContext facesContext =  FacesContext.getCurrentInstance();
			facesContext.addMessage(null, msgs);
			LOG.error("Error ", envEx);
			return "falha";
		}catch(Exception e){
			String msgErr = "Ocorreu um erro inesperado, contate o seu administrador.";
			LOG.error("Error ", e);
			FacesMessage msgs = new FacesMessage(FacesMessage.SEVERITY_ERROR, msgErr, msgErr);
			FacesContext.getCurrentInstance().addMessage(null, msgs);
			return "falha";
		}
	}
	
	public String remover(){
		try {
			this.delegate.removeConvenio(this.convenio);
			FacesMessage msgs = new FacesMessage("Registro removido com sucesso.");
			FacesContext.getCurrentInstance().addMessage(null, msgs);
			this.consultar();
			return getSucesso();
		} catch(SmartAppException appEx){
			FacesMessage msgs = new FacesMessage(FacesMessage.SEVERITY_ERROR, appEx.getMensagem(), appEx.getMensagem());
			FacesContext facesContext =  FacesContext.getCurrentInstance();
			facesContext.addMessage(null, msgs);
			LOG.error("Error ", appEx);
			return "falha";
		}catch(SmartEnvException envEx){
			String msgErr = "Ocorreu um erro inesperado, contate o seu administrador.";
			FacesMessage msgs = new FacesMessage(FacesMessage.SEVERITY_ERROR, msgErr, msgErr);
			FacesContext facesContext =  FacesContext.getCurrentInstance();
			facesContext.addMessage(null, msgs);
			LOG.error("Error ", envEx);
			return "falha";
		}catch(Exception e){
			String msgErr = "Ocorreu um erro inesperado, contate o seu administrador.";
			LOG.error("Error ", e);
			FacesMessage msgs = new FacesMessage(FacesMessage.SEVERITY_ERROR, msgErr, msgErr);
			FacesContext.getCurrentInstance().addMessage(null, msgs);
			return "falha";
		}
	}
	
	public String removerPlano(){
		try {
			System.out.println("DELETEEEEEEEEEEEEEEEE PLANOOOOOOO");
			this.delegate.removePlanoConvenio(this.planoConvenio);
			FacesMessage msgs = new FacesMessage("Registro removido com sucesso.");
			FacesContext.getCurrentInstance().addMessage(null, msgs);			
			return getSucesso();
		} catch(SmartAppException appEx){
			FacesMessage msgs = new FacesMessage(FacesMessage.SEVERITY_ERROR, appEx.getMensagem(), appEx.getMensagem());
			FacesContext facesContext =  FacesContext.getCurrentInstance();
			facesContext.addMessage(null, msgs);
			LOG.error("Error ", appEx);
			return "falha";
		}catch(SmartEnvException envEx){
			String msgErr = "Ocorreu um erro inesperado, contate o seu administrador.";
			FacesMessage msgs = new FacesMessage(FacesMessage.SEVERITY_ERROR, msgErr, msgErr);
			FacesContext facesContext =  FacesContext.getCurrentInstance();
			facesContext.addMessage(null, msgs);
			LOG.error("Error ", envEx);
			return "falha";
		}catch(Exception e){
			String msgErr = "Ocorreu um erro inesperado, contate o seu administrador.";
			LOG.error("Error ", e);
			FacesMessage msgs = new FacesMessage(FacesMessage.SEVERITY_ERROR, msgErr, msgErr);
			FacesContext.getCurrentInstance().addMessage(null, msgs);
			return "falha";
		}
	}
	
	public ConvenioVO getConvenio() {
		return convenio;
	}

	public void setConvenio(ConvenioVO convenio) {
		this.convenio = convenio;
	}

	public void setDelegate(ConvenioDelegate delegate) {
		this.delegate = delegate;
	}

	public Collection<AtividadeConvenioVO> getAtividades()  {
		try{
			if (atividades == null){
				atividades = delegate.findAllAtividades();
			}			
		}catch(Exception e){
			e.printStackTrace();			
		}
		return atividades;
	}

	public Collection<EstadoVO> getEstados() {
		try{
			if (estados== null){
				estados= delegate.findAllEstados();
			}			
		}catch(Exception e){
			e.printStackTrace();			
		}
		return estados;
	}

	public void setConvenios(Collection<ConvenioVO> convenios) {
		this.convenios = convenios;
	}

	public Collection<ConvenioVO> getConvenios() {
		if (convenios == null){
			this.consultar();
		}			
		return convenios;		
	}

	/**
	 * Grava no bean a informa��o se a conta � ativa ou n�o.
	 * @param flgContaCaixa
	 */
	public void setFlgAtivo(boolean flgAtivo) {
		if (flgAtivo)
			this.convenio.setDesativado("S");
		else this.convenio.setDesativado("N");
		
		LOG.debug("Set conta.getFlgAtivo() " + convenio.getDesativado());
	}

	public void setUltimasFaturas(Collection<FaturaVO> ultimasFaturas) {
		this.ultimasFaturas = ultimasFaturas;
	}

	public Collection<FaturaVO> getUltimasFaturas() {
		try {
			FaturaVO fatura = new FaturaVO();
			fatura.setConvenio(convenio);
			ultimasFaturas = delegate.findUltimasFaturas(fatura);
		} catch (Exception e) {
			tratarExcecao(e);
		}
		return ultimasFaturas;
	}

	public Boolean getConjugue() {
		if (this.convenio.getConjugue() == null || this.convenio.getConjugue().equals("") || this.convenio.getConjugue().equals("N")) {
			return false;
		} else {
			return true;
		}
	}

	public void setConjugue(Boolean conjugue) {
		if (Boolean.FALSE == conjugue){
			this.convenio.setConjugue("N");
		}else if (Boolean.TRUE == conjugue){
			this.convenio.setConjugue("S");
		}
	}

	public Boolean getFilhos() {
		if (this.convenio.getFilhos() == null || this.convenio.getFilhos().equals("") || this.convenio.getFilhos().equals("N")) {
			return false;
		} else {
			return true;
		}
	}

	public void setFilhos(Boolean filhos) {
		if (Boolean.FALSE == filhos){
			this.convenio.setFilhos("N");
		}else if (Boolean.TRUE == filhos){
			this.convenio.setFilhos("S");
		}		
	}

	public Boolean getDependentes() {
		if (this.convenio.getDependentes() == null || this.convenio.getDependentes().equals("") || this.convenio.getDependentes().equals("N")) {
			return false;
		} else {
			return true;
		}
	}

	public void setDependentes(Boolean dependentes) {
		if (Boolean.FALSE == dependentes){
			this.convenio.setDependentes("N");
		}else if (Boolean.TRUE == dependentes){
			this.convenio.setDependentes("S");
		}				
	}

	public Boolean getOutrosBeneficaiveis() {
		if (this.convenio.getOutrosBeneficaiveis() == null || this.convenio.getOutrosBeneficaiveis().equals("") || this.convenio.getOutrosBeneficaiveis().equals("N")) {
			return false;
		} else {
			return true;
		}
	}

	public void setOutrosBeneficaiveis(Boolean outrosBeneficaiveis) {
		if (Boolean.FALSE == outrosBeneficaiveis){
			this.convenio.setOutrosBeneficaiveis("N");
		}else if (Boolean.TRUE == outrosBeneficaiveis){
			this.convenio.setOutrosBeneficaiveis("S");
		}	
	}

	public Boolean getTitular() {
		if (this.convenio.getTitular() == null || this.convenio.getTitular().equals("") || this.convenio.getTitular().equals("N")) {
			return false;
		} else {
			return true;
		}
	}

	public void setTitular(Boolean titular) {
		if (Boolean.FALSE == titular){
			this.convenio.setTitular("N");
		}else if (Boolean.TRUE == titular){
			this.convenio.setTitular("S");
		}	
	}

	public String getDiaFechamento() {
		if (this.convenio.getDiaFechamento() != null) {
			return convenio.getDiaFechamento().toString();
		} else {
			return "";
		}
	}

	public void setDiaFechamento(String diaFechamento) {
		if (diaFechamento != null && !"".equals(diaFechamento)){
			this.convenio.setDiaFechamento(new Short(diaFechamento));
		}else {//if (Boolean.TRUE == titular){
			this.convenio.setDiaFechamento(null);
		}
	}
	
	public Collection<AtividadeConvenioVO> getAtividadesAtivas() {
		try{
			atividadesAtivas = this.delegate.findAllAtividadesAtivas();
			return atividadesAtivas;
		} catch (Exception e) {
			tratarExcecao(e);
		}		
		return new ArrayList<AtividadeConvenioVO>();
	}
	
	public String getTelefone(){
		if (convenio != null && convenio.getTelefone() != null && !convenio.getTelefone().equals("")){
			return Formatador.formatTelefone(Long.valueOf(convenio.getTelefone()));
		}
		
		return "";
	}
	
	public void setTelefone(String telefone){
		if (telefone != null && !"".equals(telefone)){
			Long tel = (Formatador.parseTelefone(telefone) >= new Long(0)) ? Formatador.parseTelefone(telefone) : null;
			convenio.setTelefone(tel.toString());
		}
	}	
	
	public String getTelefoneAdicional(){
		if (convenio != null && convenio.getTelefoneAdicional() != null && !convenio.getTelefoneAdicional().equals("")){
			String telefoneAdicional = Formatador.formatTelefone(Long.valueOf(convenio.getTelefoneAdicional()));
			return telefoneAdicional;
		}
		return "";
	}
	
	public void setTelefoneAdicional(String telefone){
		if (telefone != null && !"".equals(telefone)){
			Long tel = (Formatador.parseTelefone(telefone) >= new Long(0)) ? Formatador.parseTelefone(telefone) : null;
			convenio.setTelefoneAdicional(tel.toString());
		}
	}
	
	public String getFax(){
		if (convenio != null && convenio.getFax() != null && !convenio.getFax().equals("")){
			return Formatador.formatTelefone(Long.valueOf(convenio.getFax()));
		}
		return "";
	}
	
	public void setFax(String fax){
		if (fax != null && !"".equals(fax)){
			Long tel = (Formatador.parseTelefone(fax) >= new Long(0)) ? Formatador.parseTelefone(fax) : null;
			convenio.setFax(tel.toString());
		}
	}
	
	public String getCep(){
		if (convenio.getCep() != null && convenio.getCep() != null){
			return Formatador.formatCEP(Integer.valueOf(convenio.getCep()));
		}
		return "";
	}
	
	public void setCep(String cep){
		if (!"".equals(cep)){
			convenio.setCep(Formatador.parseCEP(cep).toString());
		}
	}

	public PlanoConvenioVO getPlanoConvenio() {
		if (planoConvenio == null) prepararNovoPlano();
		return planoConvenio;
	}

	public void setPlanoConvenio(PlanoConvenioVO planoConvenio) {
		this.planoConvenio = planoConvenio;
	}

	public Collection<PlanoConvenioVO> getPlanosConvenio() {	
		try{
			if (convenio != null && convenio.getCodigo() != null){
				PlanoConvenioVO plano = new PlanoConvenioVO();
				plano.setConvenio(this.convenio);
				planosConvenio = this.delegate.findPlanoConvenioByFilter(plano);
				return planosConvenio;
			}
		} catch (Exception e) {
			tratarExcecao(e);
		}		
		return new ArrayList<PlanoConvenioVO>();				
	}
	
	public Collection getPlanosConvenioCombo() {
		try {
			 Collection collPlanos = getPlanosConvenio(); 
			 return (collPlanos != null) ? getSelect(collPlanos, "codigo", "nome") : new ArrayList();
		} catch (Exception e) {
			LOG.error("erro no momento de carregar os estados", e);
		}
		return new ArrayList();
	}
	
	public void setPlanosConvenio(Collection<PlanoConvenioVO> planosConvenio) {
		this.planosConvenio = planosConvenio;
	}	
	
	public void prepararNovoPlano(){
		System.out.println("PREPARANDO NOVO PLANO");
		this.planoConvenio = new PlanoConvenioVO();
		this.planoConvenio.setConvenio(convenio);
		this.planoConvenio.setHistoricoValorPlanoConvenio(new ArrayList<HistoricoValorPlanoConvenioVO>());
		
		//planoConvenio.set
	}
	
	public void carregarHistoricoValoresPlano(){
		try{
			planosConvenioHistorico = delegate.findHistoricoValorPlanoByPlano(planoConvenio);
		}catch(Exception appEx){
			tratarExcecao(appEx);
		}
	
	}
	
	public Boolean getDesativado() {
		if (this.convenio.getDesativado() == null || this.convenio.getDesativado().equals("") || this.convenio.getDesativado().equals("N")) {
			return false;
		} else {
			return true;
		}
	}

	public void setDesativado(Boolean desativado) {
		if (Boolean.FALSE == desativado){
			this.convenio.setDesativado("N");
		}else if (Boolean.TRUE == desativado){
			this.convenio.setDesativado("S");
		}				
	}
	
	public Boolean getPlanoDesativado() {
		if (this.planoConvenio.getDesativado() == null || this.planoConvenio.getDesativado().equals("") || this.planoConvenio.getDesativado().equals("N")) {
			return false;
		} else {
			return true;
		}
	}

	public void setPlanoDesativado(Boolean desativado) {
		if (Boolean.FALSE == desativado){
			this.planoConvenio.setDesativado("N");
		}else if (Boolean.TRUE == desativado){
			this.planoConvenio.setDesativado("S");
		}				
	}
	
	public Boolean getPlanoDedutivel() {
		if (this.planoConvenio.getDedutivel() == null || this.planoConvenio.getDedutivel().equals("") || this.planoConvenio.getDedutivel().equals("N")) {
			return false;
		} else {
			return true;
		}
	}

	public void setPlanoDedutivel(Boolean dededutivel) {
		if (Boolean.FALSE == dededutivel){
			this.planoConvenio.setDedutivel("N");
		}else if (Boolean.TRUE == dededutivel){
			this.planoConvenio.setDedutivel("S");
		}				
	}
	
	public String getOperador(){
		HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
		UsuarioVO usuario = (UsuarioVO)request.getSession().getAttribute("usuario");
		LOG.debug("usuario " + usuario);
		if (usuario != null){
			return usuario.getLogin();
		}
		else return "";
	}	
	
	public String getDataAtual(){
		try{
			return formatter.format(utilDelegate.getCurrentDate());
		}catch(Exception e){
			LOG.error("ERRO NA HORA DE BUSCAR A DATA ATUAL", e);
			return "";
		}
	}	
	
	public String print(){
		try {
			return getSucesso();
		} catch (Exception e) {
			return tratarExcecao(e);
		}
	}
	
	public String printPlanosConvenio(){
		try{
			if (planoConvenio.getConvenio() != null && planoConvenio.getConvenio().getCodigo() != null){
				printPlanosConvenio = this.delegate.findPlanoConvenioByFilter(planoConvenio);
			}
			return getSucesso();
		} catch(SmartAppException appEx){
			FacesMessage msgs = new FacesMessage(FacesMessage.SEVERITY_ERROR, appEx.getMensagem(), appEx.getMensagem());
			FacesContext facesContext =  FacesContext.getCurrentInstance();
			facesContext.addMessage(null, msgs);
			LOG.error("Error ", appEx);
			return "falha";
		}catch(SmartEnvException envEx){
			String msgErr = "Ocorreu um erro inesperado, contate o seu administrador.";
			FacesMessage msgs = new FacesMessage(FacesMessage.SEVERITY_ERROR, msgErr, msgErr);
			FacesContext facesContext =  FacesContext.getCurrentInstance();
			facesContext.addMessage(null, msgs);
			LOG.error("Error ", envEx);
			return "falha";
		}catch(Exception e){
			String msgErr = "Ocorreu um erro inesperado, contate o seu administrador.";
			LOG.error("Error ", e);
			FacesMessage msgs = new FacesMessage(FacesMessage.SEVERITY_ERROR, msgErr, msgErr);
			FacesContext.getCurrentInstance().addMessage(null, msgs);
			return "falha";
		}
	}
	
	public String gerarFatura(){
		try {
			if (fatura.getCodigo() != null && fatura.getCodigo() == 0){
				fatura.setCodigo(null);
			}
			if (!fatura.getConvenio().getCategoria().equals("F")) {
				fatura = delegate.gerarFaturaVariavel(fatura);
				FacesMessage msgs = new FacesMessage("Fatura gerada com sucesso.");
				FacesContext.getCurrentInstance().addMessage(null, msgs);		
				return getSucesso();
			} else {
				fatura = delegate.gerarFaturaPrevia(fatura);
				int count = 1;
				for (ItemFaturaVO item : fatura.getItens()){
					item.setNumero(count);
					count++;
				}
				FacesMessage msgs = new FacesMessage("Fatura prévia gerada com sucesso.");
				FacesContext.getCurrentInstance().addMessage(null, msgs);		
				return getSucesso();
			}
		} catch(SmartAppException appEx){
			FacesMessage msgs = new FacesMessage(FacesMessage.SEVERITY_ERROR, appEx.getMensagem(), appEx.getMensagem());
			FacesContext facesContext =  FacesContext.getCurrentInstance();
			facesContext.addMessage(null, msgs);
			LOG.error("Error ", appEx);
			return "falha";
		}catch(SmartEnvException envEx){
			String msgErr = "Ocorreu um erro inesperado, contate o seu administrador.";
			FacesMessage msgs = new FacesMessage(FacesMessage.SEVERITY_ERROR, msgErr, msgErr);
			FacesContext facesContext =  FacesContext.getCurrentInstance();
			facesContext.addMessage(null, msgs);
			LOG.error("Error ", envEx);
			return "falha";
		}catch(Exception e){
			String msgErr = "Ocorreu um erro inesperado, contate o seu administrador.";
			LOG.error("Error ", e);
			FacesMessage msgs = new FacesMessage(FacesMessage.SEVERITY_ERROR, msgErr, msgErr);
			FacesContext.getCurrentInstance().addMessage(null, msgs);
			return "falha";
		}
	}
	
	public String gerarFaturaFixa(){
		try {
			delegate.gerarFaturaFixa(fatura);
			FacesMessage msgs = new FacesMessage("Fatura fixa gerada com sucesso.");
			FacesContext.getCurrentInstance().addMessage(null, msgs);		
			return getSucesso();
		} catch(SmartAppException appEx){
			FacesMessage msgs = new FacesMessage(FacesMessage.SEVERITY_ERROR, appEx.getMensagem(), appEx.getMensagem());
			FacesContext facesContext =  FacesContext.getCurrentInstance();
			facesContext.addMessage(null, msgs);
			LOG.error("Error ", appEx);
			return "falha";
		}catch(SmartEnvException envEx){
			String msgErr = "Ocorreu um erro inesperado, contate o seu administrador.";
			FacesMessage msgs = new FacesMessage(FacesMessage.SEVERITY_ERROR, msgErr, msgErr);
			FacesContext facesContext =  FacesContext.getCurrentInstance();
			facesContext.addMessage(null, msgs);
			LOG.error("Error ", envEx);
			return "falha";
		}catch(Exception e){
			String msgErr = "Ocorreu um erro inesperado, contate o seu administrador.";
			LOG.error("Error ", e);
			FacesMessage msgs = new FacesMessage(FacesMessage.SEVERITY_ERROR, msgErr, msgErr);
			FacesContext.getCurrentInstance().addMessage(null, msgs);
			return "falha";
		}
	}
	
	public void prepararNovoItemFatura(){
		itemFatura = new ItemFaturaVO();
		itemFatura.setBeneficiario(new BeneficiarioVO());
		itemFatura.getBeneficiario().setTitular(new AssociadoVO());
		itemFatura.setVinculacao(new VinculacaoPlanoVO());
	}
	
	public List<BeneficiarioVO> autocompleteBeneficiario(Object suggest){
		ArrayList<BeneficiarioVO> retorno = new ArrayList<BeneficiarioVO>();
		try {
			String nome  = (String)suggest;
			BeneficiarioVO filtro = new BeneficiarioVO();
			filtro.setNome(nome);
			filtro.setPlano(new PlanoConvenioVO());
			filtro.getPlano().setConvenio(fatura.getConvenio());
			retorno.addAll(delegate.findBeneficiariosByFilter(filtro));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retorno;
	}
	
	public void carregarItensFatura(){
		if (fatura.getItens().contains(itemFatura)){
			for (ItemFaturaVO linha : fatura.getItens()){
				if (linha.equals(itemFatura)){
					linha.setBeneficiario(itemFatura.getBeneficiario());
					linha.setVinculacao(itemFatura.getVinculacao());
				}
			}
		}
	}
	
	public void adicionarItemFatura(){
		itemFatura.setNumero(fatura.getItens().size());
		fatura.getItens().add(itemFatura);
		itemFatura = new ItemFaturaVO();
		itemFatura.setBeneficiario(new BeneficiarioVO());
		System.out.println("fatura.getItens() "+ fatura.getItens());
	}
	
	public void limparFaturas(){
		System.out.println("LIMPAR FATURAS");
		fatura.setItens(new ArrayList<ItemFaturaVO>());
		try {
			System.out.println("fatura.convenio.categoria " + fatura.getConvenio().getCategoria());
			fatura.setConvenio(delegate.findConvenioByPrimaryKey(fatura.getConvenio()));
			System.out.println("fatura.convenio.categoria " + fatura.getConvenio().getCategoria());
		} catch (Exception e) {
			LOG.error("ERRO NO MOMENTO DE CARREGAR CONVENIO");
		}
	}
	
	public void carregaConvenio(){
		try {
			System.out.println("fatura.convenio.categoria " + fatura.getConvenio().getCategoria());
			fatura.setConvenio(delegate.findConvenioByPrimaryKey(fatura.getConvenio()));
			System.out.println("fatura.convenio.categoria " + fatura.getConvenio().getCategoria());
		} catch (Exception e) {
			LOG.error("ERRO NO MOMENTO DE CARREGAR CONVENIO");
		}
	}
	
	public void removerItemFatura(){
		fatura.getItens().remove(itemFatura);
	}
	
	public Collection<PlanoConvenioVO> getPrintPlanosConvenio() {
		return printPlanosConvenio;
	}

	public void setUtilDelegate(UtilDelegate utilDelegate) {
		this.utilDelegate = utilDelegate;
	}
	
	public void carregarBeneficiarios(){
		try{
			if (convenio != null && convenio.getCodigo() != null){
				beneficiario.getPlano().setConvenio(convenio);
				beneficiariosConvenios = this.delegate.findBeneficiariosByFilter(beneficiario);
			}
		}catch(Exception appEx){
			tratarExcecao(appEx);
		}
	}
	
	public Collection<BeneficiarioVO> getBeneficiariosConvenios() {
		return beneficiariosConvenios;
	}

	public void setBeneficiariosConvenios(
			Collection<BeneficiarioVO> beneficiariosConvenios) {
		this.beneficiariosConvenios = beneficiariosConvenios;
	}

	public FaturaVO getFatura() {
		return fatura;
	}

	public void setFatura(FaturaVO fatura) {
		this.fatura = fatura;
	}
	
	public Collection<ConvenioVO> getAllConvenios(){
		try {
			return delegate.findAllConvenio();
		} catch (Exception e) {
			tratarExcecao(e);
		}
		return new ArrayList<ConvenioVO>();
	}
	
	public Collection<ContaVO> getAllContas(){
		try {
			return financeiroDelegate.findAllConta();
		} catch (Exception e) {
			tratarExcecao(e);
		}
		return new ArrayList<ContaVO>();
	}

	public void setFinanceiroDelegate(FinanceiroDelegate financeiroDelegate) {
		this.financeiroDelegate = financeiroDelegate;
	}

	public ItemFaturaVO getItemFatura() {
		return itemFatura;
	}

	public void setItemFatura(ItemFaturaVO itemFatura) {
		this.itemFatura = itemFatura;
	}

	public BeneficiarioVO getBeneficiario() {
		return beneficiario;
	}

	public void setBeneficiario(BeneficiarioVO beneficiario) {
		this.beneficiario = beneficiario;
	}

	public Collection<PlanoConvenioVO> getPlanosConvenioHistorico() {
		return planosConvenioHistorico;
	}

	public void setPlanosConvenioHistorico(
			Collection<PlanoConvenioVO> planosConvenioHistorico) {
		this.planosConvenioHistorico = planosConvenioHistorico;
	}
}