package br.org.asserjuf.sisjuf.associados.convenio.cliente.web.bean;

import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.pdfbox.io.IOUtils;
import org.richfaces.event.UploadEvent;
import org.richfaces.model.UploadItem;

import com.vortice.seguranca.vo.UsuarioVO;
import com.vortice.view.BasePageBean;

import br.com.falc.smartFW.exception.SmartAppException;
import br.com.falc.smartFW.exception.SmartEnvException;
import br.org.asserjuf.sisjuf.associados.AssociadoVO;
import br.org.asserjuf.sisjuf.associados.convenio.BeneficiarioVO;
import br.org.asserjuf.sisjuf.associados.convenio.ConvenioVO;
import br.org.asserjuf.sisjuf.associados.convenio.FaturaArquivoVO;
import br.org.asserjuf.sisjuf.associados.convenio.FaturaFiltroAssembler;
import br.org.asserjuf.sisjuf.associados.convenio.FaturaVO;
import br.org.asserjuf.sisjuf.associados.convenio.ItemFaturaInconsistenteVO;
import br.org.asserjuf.sisjuf.associados.convenio.ItemFaturaVO;
import br.org.asserjuf.sisjuf.associados.convenio.PlanoConvenioVO;
import br.org.asserjuf.sisjuf.associados.convenio.VinculacaoPlanoVO;
import br.org.asserjuf.sisjuf.associados.convenio.cliente.ConvenioDelegate;
import br.org.asserjuf.sisjuf.associados.convenio.dados.StatusFaturaVO;
import br.org.asserjuf.sisjuf.financeiro.ContaVO;
import br.org.asserjuf.sisjuf.financeiro.LancamentoVO;
import br.org.asserjuf.sisjuf.financeiro.web.cliente.FinanceiroDelegate;
import br.org.asserjuf.sisjuf.util.ParametroVO;
import br.org.asserjuf.sisjuf.util.arquivos.arquivosfatura.ParserFileOdontosystem;
import br.org.asserjuf.sisjuf.util.arquivos.arquivosfatura.ParserFilePromedica;
import br.org.asserjuf.sisjuf.util.arquivos.arquivosfatura.ParserFileServdonto;
import br.org.asserjuf.sisjuf.util.arquivos.arquivosfatura.ParserFileVitalmed;
import br.org.asserjuf.sisjuf.util.web.UtilDelegate;

public class FaturaPageBean extends BasePageBean {

	private transient ConvenioDelegate	delegate;
	
	private FaturaFiltroAssembler		faturaFiltro;
	
	private Collection<FaturaVO> 		faturas;
	
	private FaturaVO 					fatura;
	
	private LancamentoVO				lancamento;
	
	private FaturaArquivoVO 			faturaProcessda;
	
	private Collection<ItemFaturaInconsistenteVO> relatorioItensInconsistentes;
	
	private List 			data = new ArrayList();
	
	private ArrayList<String>			strPath = new ArrayList();
	
	private static final transient SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	
	private transient UtilDelegate utilDelegate;
	private String tipoArquivoFatura;
	private byte[] conteudoArquivoFatura;
	
	private Boolean		validaProblematica = false;
	
	private transient FinanceiroDelegate	financeiroDelegate;
	
	private static final transient Logger LOG = Logger.getLogger(FaturaPageBean.class);
	
	private ItemFaturaVO					itemFatura;
	private UsuarioVO						usuarioLogado;
	
	public FaturaPageBean()
	{
		

		usuarioLogado = (UsuarioVO)this.getHttpSession().getAttribute("usuario");
		
		faturaFiltro = new FaturaFiltroAssembler();
		faturaFiltro.setConvenio(new ConvenioVO());
		faturaFiltro.setStatus(new StatusFaturaVO());
		
		fatura = new FaturaVO();
		fatura.setConvenio(new ConvenioVO());
		fatura.setStatus(new StatusFaturaVO());
		fatura.setContaCredito(new ContaVO());
		fatura.setContaDebito(new ContaVO());
		fatura.setItens(new ArrayList<ItemFaturaVO>());
		
		fatura.setUsuario(usuarioLogado);
		
		lancamento = new LancamentoVO();
		
		itemFatura = new ItemFaturaVO();
		itemFatura.setBeneficiario(new BeneficiarioVO());
		itemFatura.setPlano(new PlanoConvenioVO());
		itemFatura.setVinculacao(new VinculacaoPlanoVO());
	}
	
	public String carregar(){
		try {
			//System.out.println("VEIO CARREGAR!!!");
			fatura = delegate.findByPrimaryKey(fatura);
			//System.out.println("FATURA CONVENIO " + fatura.getConvenio().getNomeFantasia() + ", data inicial: " + fatura.getDataInicial());
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
	
	public void removerItemFatura(){
		fatura.getItens().remove(itemFatura);
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
				/*FacesMessage msgs = new FacesMessage("Fatura prévia gerada com sucesso.");
				FacesContext.getCurrentInstance().addMessage(null, msgs);*/		
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
			fatura = delegate.gerarFaturaFixa(fatura);
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
	
	public void carregarItensFatura(){
		if (fatura.getItens().contains(itemFatura)){
			for (ItemFaturaVO linha : fatura.getItens()){
				if (linha.equals(itemFatura)){
					linha.setBeneficiario(itemFatura.getBeneficiario());
					linha.setVinculacao(itemFatura.getVinculacao());
				}
			}
		}else{
			try {
				itemFatura.getBeneficiario().setPlano(new PlanoConvenioVO());
				itemFatura.getBeneficiario().getPlano().setConvenio(fatura.getConvenio());
				BeneficiarioVO beneficiario = (BeneficiarioVO)((ArrayList)delegate.findBeneficiariosByFilter(itemFatura.getBeneficiario())).get(0);
				itemFatura.setBeneficiario(beneficiario);
				itemFatura.setVinculacao(beneficiario.getVinculacao());
			} catch (Exception e) {
				e.printStackTrace();
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
	
	public void upload(UploadEvent ue) { 
		try {
			UploadItem uploadItem = ue.getUploadItem();
			if (uploadItem.getFile().exists()){
				conteudoArquivoFatura = IOUtils.toByteArray(new FileInputStream(uploadItem.getFile()));
				strPath.add(uploadItem.getFile().getAbsolutePath());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}	
	} 
	
	public String validarFatura(){
		try {
			if (strPath != null && strPath.size() > 0){
				if(StringUtils.isNotEmpty(tipoArquivoFatura)){
					
					FaturaArquivoVO faturaArquivo = new FaturaArquivoVO();
					faturaArquivo.setCodigo(fatura.getCodigo());
					List<ItemFaturaVO> itens = new ArrayList<ItemFaturaVO>();
					
					for (String path : strPath) {
					
						if (tipoArquivoFatura.equalsIgnoreCase("VITALMED")){
							//ParserFileVitalmed parserFileVitalmed = new ParserFileVitalmed(conteudoArquivoFatura);
							ParserFileVitalmed parserFileVitalmed = new ParserFileVitalmed(path);
							itens.addAll(parserFileVitalmed.parserContentFileToIntensFaturasList());
						} else if(tipoArquivoFatura.equalsIgnoreCase("PROMEDICA")){
							ParserFilePromedica parserFilePromedica = new ParserFilePromedica(path);
							itens.addAll(parserFilePromedica.parserContentFileToIntensFaturasList());
						} else if(tipoArquivoFatura.equalsIgnoreCase("ODONTOSYSTEM")){
							ParserFileOdontosystem parserFileOdontosystem = new ParserFileOdontosystem(path);
							itens.addAll(parserFileOdontosystem.parserContentFileToIntensFaturasList());
						} else if(tipoArquivoFatura.equalsIgnoreCase("SERVDONTO")){
							ParserFileServdonto parserFileServdonto = new ParserFileServdonto(path);
							itens.addAll(parserFileServdonto.parserContentFileToIntensFaturasList());
							
						}
					
					}
					faturaArquivo.setItens(itens);
					
					double valorTotal = 0D;
					for (ItemFaturaVO itemFatura : faturaArquivo.getItens()){
						valorTotal += itemFatura.getValor();
					}
					
					faturaArquivo.setValorFatura(valorTotal);
					
					faturaProcessda = delegate.validarFatura(faturaArquivo);
				}
				if (faturaProcessda != null && (faturaProcessda.getItensInconsistentes() == null || faturaProcessda.getItensInconsistentes().isEmpty())){
					FacesMessage msgs = new FacesMessage("Fatura validada com sucesso.");
					FacesContext facesContext =  FacesContext.getCurrentInstance();
					facesContext.addMessage("convenioMsgs", msgs);	
					carregar();
					return getSucesso();
				}else{
					FacesMessage msgs = new FacesMessage("Foram encontradas inconsistencias na fatura.");
					FacesContext facesContext =  FacesContext.getCurrentInstance();
					validaProblematica = true;
					facesContext.addMessage("convenioMsgs", msgs);
					return getSucesso();
				}
			}else{
				throw new SmartAppException("Carregue primeio o arquivo antes de tentar validar.");
			}
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
			return "falha";
		}catch(Exception e){
			e.printStackTrace();
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
	
	public Collection<ContaVO> getAllContas(){
		try {
			return financeiroDelegate.findAllConta(false);
		} catch (Exception e) {
			tratarExcecao(e);
		}
		return new ArrayList<ContaVO>();
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
	
	public String print(){
		return consulta();
	}
	
	public String printInconsistencias(){
		try {
			// relatorioItensInconsistentes = delegate.findRelatorioInconsistenciasByCodigo(faturaProcessda);
			return "imprimir";
		} catch (Exception e) {
			return tratarExcecao(e);
		}
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
	public FaturaArquivoVO getFaturaProcessda() {
		return faturaProcessda;
	}
	public void setFaturaProcessda(FaturaArquivoVO faturaProcessda) {
		this.faturaProcessda = faturaProcessda;
	}
	public List getData() {
		return data;
	}
	public void setData(List data) {
		this.data = data;
	}
	public Boolean getValidaProblematica() {
		return validaProblematica;
	}
	public void setValidaProblematica(Boolean validaProblematica) {
		this.validaProblematica = validaProblematica;
	}
	public ItemFaturaVO getItemFatura() {
		return itemFatura;
	}
	public void setItemFatura(ItemFaturaVO itemFatura) {
		this.itemFatura = itemFatura;
	}
	public void setFinanceiroDelegate(FinanceiroDelegate financeiroDelegate) {
		this.financeiroDelegate = financeiroDelegate;
	}
}