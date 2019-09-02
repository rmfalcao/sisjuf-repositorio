package br.org.asserjuf.sisjuf.associados.cliente.web.bean;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.richfaces.event.UploadEvent;
import org.richfaces.model.UploadItem;

import br.com.falc.smartFW.exception.SmartAppException;
import br.com.falc.smartFW.exception.SmartEnvException;
import br.org.asserjuf.sisjuf.associados.AssociadoAssembler;
import br.org.asserjuf.sisjuf.associados.AssociadoFiltroAssembler;
import br.org.asserjuf.sisjuf.associados.AssociadoImportacaoNucreVO;
import br.org.asserjuf.sisjuf.associados.AssociadoVO;
import br.org.asserjuf.sisjuf.associados.ConjugeVO;
import br.org.asserjuf.sisjuf.associados.DependenteVO;
import br.org.asserjuf.sisjuf.associados.EmailVO;
import br.org.asserjuf.sisjuf.associados.FilhoVO;
import br.org.asserjuf.sisjuf.associados.HistoricoAssociadoVO;
import br.org.asserjuf.sisjuf.associados.HistoricoFiltroAssembler;
import br.org.asserjuf.sisjuf.associados.OrgaoVO;
import br.org.asserjuf.sisjuf.associados.ParentescoVO;
import br.org.asserjuf.sisjuf.associados.PessoaVO;
import br.org.asserjuf.sisjuf.associados.PlanilhaNucreVO;
import br.org.asserjuf.sisjuf.associados.ProfissaoVO;
import br.org.asserjuf.sisjuf.associados.SetorVO;
import br.org.asserjuf.sisjuf.associados.TipoEventoVO;
import br.org.asserjuf.sisjuf.associados.cliente.AssociadoDelegate;
import br.org.asserjuf.sisjuf.associados.convenio.ConvenioVO;
import br.org.asserjuf.sisjuf.associados.convenio.OutroBeneficiavelVO;
import br.org.asserjuf.sisjuf.associados.convenio.PlanoConvenioVO;
import br.org.asserjuf.sisjuf.associados.convenio.VinculacaoPlanoFiltroAssembler;
import br.org.asserjuf.sisjuf.associados.convenio.VinculacaoPlanoVO;
import br.org.asserjuf.sisjuf.associados.convenio.VinculadoPlanoAssembler;
import br.org.asserjuf.sisjuf.associados.convenio.cliente.ConvenioDelegate;
import br.org.asserjuf.sisjuf.entidadesComuns.EnderecoVO;
import br.org.asserjuf.sisjuf.entidadesComuns.EstadoVO;
import br.org.asserjuf.sisjuf.entidadesComuns.MunicipioVO;
import br.org.asserjuf.sisjuf.entidadesComuns.cliente.EntidadesComunsDelegate;
import br.org.asserjuf.sisjuf.financeiro.BancoVO;
import br.org.asserjuf.sisjuf.financeiro.ContaAssociadoVO;
import br.org.asserjuf.sisjuf.financeiro.web.cliente.FinanceiroDelegate;
import br.org.asserjuf.sisjuf.util.web.UtilDelegate;

import com.vortice.seguranca.vo.UsuarioVO;
import com.vortice.view.BasePageBean;
import com.vortice.view.Formatador;

public class AssociadoPageBean  extends BasePageBean{

	/**
	 * Classe que implementa o padr�o "proxy" para comunica��o com o "fa�ade" Associado.
	 */
	private transient AssociadoDelegate delegate;

	/**
	 * Representa o encapsulamento dos dados de neg�cio da entidade associado. 
	 */
	private AssociadoVO associado;
	
	/**
	 * Cole��o de associados, encapsulados na classe AssociadoVO.
	 */
	private Collection<AssociadoVO> associados;
	
	/**
	 * Objeto de log (log4j)
	 */
	private static final transient Logger LOG = Logger.getLogger(AssociadoPageBean.class);
	
	/**
	 * Instancia os objetos VO e delegate e invoca o m�todo de consulta de todos os associados.
	 */
	
	/**
	 * Informa��o do valor atual do s�cio usuario. 
	 */
	private Float valorAtual = null;

	/**
	 * Informa��o do valor novo do s�cio usuario. 
	 */
	private Float valorNovo = null;
	
	private Collection	estados;
	
	private Collection	tipos;
	
	private Collection	bancos;
	
	private Collection	parentescos;
	
	private Collection 	tipoEventos;
	
	private transient EntidadesComunsDelegate entidadesComunsDelegate;
	
	private FinanceiroDelegate	financeiroDelegate;
	
	private HistoricoFiltroAssembler historicoFiltro;
	
	private Collection<HistoricoAssociadoVO> historicoAssociado;
	
	private static final transient SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	
	private transient UtilDelegate utilDelegate;
	
	private Collection<AssociadoVO> associadosAniversariantes;

	private Collection<AssociadoImportacaoNucreVO> associadosFaltantes;
	
	private Collection<AssociadoVO> associadoEmails;
	
	/**
	 * E-mails que ser�o carregados na tela de e-mail
	 */
	private String emails;
	
	private EmailVO email;
	
	/**
	 * Formata��o de datas.
	 */
	private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

	/**
	 * Filtro do Associado.
	 */
	private AssociadoFiltroAssembler filtro;
	
	/**
	 * Arquivo importado, para carregar Nucre
	 */
	private List 			data = new ArrayList();
	
	private String 			strPreCadastro;
	
	private Boolean			preCadastro;
	
	private OutroBeneficiavelVO				beneficiario;
	
	private Collection<OutroBeneficiavelVO> beneficiarios;
	
	private Collection<ConvenioVO> 			convenios;

	private Collection<PlanoConvenioVO> 	planos;
	
	private VinculacaoPlanoFiltroAssembler 	vinculacaoFiltro;

	private Collection<VinculacaoPlanoVO> 	vinculacoes;

	private VinculacaoPlanoVO 				vinculacao;
	
	private transient ConvenioDelegate		convenioDelegate;
	
	private Collection						planosItens = new ArrayList();
	
	private Collection<VinculadoPlanoAssembler> historicoVinculacoes;
	private VinculadoPlanoAssembler historicoVinculacaoFiltro;
	
	/**
	 * Data que ser� feita a importa��o do nucre
	 */
	private Date dataImportacao;
	
	public AssociadoPageBean(){
		 try{
			// System.out.println("....................AssociadoPageBean");
			 associados =(associados != null) ? associados :  new ArrayList<AssociadoVO>();
			 associadosAniversariantes =(associadosAniversariantes != null) ? associadosAniversariantes :  new ArrayList<AssociadoVO>();
			 associadoEmails =(associadoEmails != null) ? associadoEmails :  new ArrayList<AssociadoVO>();
			 
			 carregaFiltro();
			 carregaAssociado();
//			 associado.setCodigo();
//			 associado.setNome("Associado1");
			 
			List lista = new ArrayList<AssociadoVO>();
			associado.setBeneficiarios(lista);
			 
			 
			 historicoFiltro = new HistoricoFiltroAssembler();
			 historicoFiltro.setTipoEvento(new TipoEventoVO());
			 
			 historicoAssociado = new ArrayList<HistoricoAssociadoVO>();
			 email = new EmailVO();
			 
			 this.beneficiario = new OutroBeneficiavelVO();
			 this.beneficiario.setAssociado(new AssociadoVO());
			 
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
			 
			 historicoVinculacoes = new ArrayList<VinculadoPlanoAssembler>();
			 historicoVinculacaoFiltro = new VinculadoPlanoAssembler();
			 historicoVinculacaoFiltro.setAssociado(associado);
			 historicoVinculacaoFiltro.setAssociadoDependente(new AssociadoVO());
			 historicoVinculacaoFiltro.setConvenio(new ConvenioVO());
			 historicoVinculacaoFiltro.setPlano(new PlanoConvenioVO());
			 historicoVinculacaoFiltro.getPlano().setConvenio(new ConvenioVO());
		 }catch(Exception e){
			 LOG.error("Error ", e);
			 tratarExcecao(e);
		 }
	}

	public void upload(UploadEvent e) { 
		UploadItem uploadItem = e.getUploadItem();	
		HttpSession sessao = ((HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest()).getSession();
		sessao.setAttribute("FILE_PATH", uploadItem.getFile().getPath());
		data.add(uploadItem);
	} 
	
	/**
	 * Vai importar o arquivo do nucre e gerar uma lista de associados.
	 * @return
	 */
	public String importarArquivo(){
		try {
			HttpSession sessao = ((HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest()).getSession();
//			BufferedReader in = new BufferedReader(new FileReader(((UploadItem)data.get(0)).getFile()));
//			BufferedReader in = new BufferedReader(new InputStreamReader(((UploadItem)data.get(0)).getInputStream()));
			BufferedReader in = new BufferedReader(new FileReader((new File((String)sessao.getAttribute("FILE_PATH")))));
			String str = "";
			boolean continua = false;
			Collection<PlanilhaNucreVO> planilhas = new ArrayList<PlanilhaNucreVO>();
			while ((str = in.readLine()) != null){
				if (!continua)
					if (str.indexOf("(MENSALIDADE)") > 0) continua = true;
				
				if (continua && !(str.indexOf("(MENSALIDADE)") > 0))
				{
					str = str.trim();
					String[] strArray = str.split(" ");
					if (strArray.length > 2)
					{
						try 
						{
							if (!"".equals(strArray[2]))
							{
								PlanilhaNucreVO planilha = new PlanilhaNucreVO();
								String matricula = strArray[2];
								
								if (matricula.indexOf("\"") >= 0)
									matricula = matricula.replaceAll("\"", "");
								if (matricula.indexOf("/") > 0)
									matricula = matricula.substring(0, matricula.indexOf("/"));
								
								planilha.setAssociado(new AssociadoVO());
								planilha.getAssociado().setMatriculaJustica(matricula);
								Float valorDebitado = new Float(0);
								Float custo = new Float(0);
								Float liquido = new Float(0);
								int posicao = 1;
								for (int i = 0; i < strArray.length; i++)
								{
									if(!"".equals(strArray[i].trim()) && strArray[i].indexOf(",") >= 0)
									{
										try {
											String strValor = strArray[i].replaceAll("[.]", "");
											if (strValor.indexOf(",") >= 0)
											{
												strValor = strValor.replaceAll(",", ".");
												if (posicao == 1){
													valorDebitado = new Float(strValor);
													LOG.debug("valorDebitado " + valorDebitado);
													posicao += 1;
												} else if (posicao == 2){
													custo = new Float(strValor);
													LOG.debug("custo " + custo);
													posicao += 1;
												} else if (posicao == 3){
													liquido = new Float(strValor);
													LOG.debug("liquido " + liquido);
													posicao = 1;
													break;
												}
											}
										} catch (NumberFormatException e) {
										}
									}
								}
								planilha.setValorDebitado(valorDebitado);
								planilha.setCustoConsignacao(custo);
								planilha.setValorLiquido(liquido);
								planilha.setData(dataImportacao);
								planilhas.add(planilha);
							}
						} catch (NumberFormatException e) {
						}
					}
				}
				if (continua)
					if (str.indexOf("Pag.") > 0) continua = false;
			}
			this.setAssociadosFaltantes(delegate.importPlanilhaNucre(planilhas));
			FacesMessage msgs = new FacesMessage("Arquivo carregado com sucesso.");
			FacesContext facesContext =  FacesContext.getCurrentInstance();
			facesContext.addMessage(null, msgs);
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
	
	public String salvar(){
		try {
			List<DependenteVO> dependentes = carregaDependentes();
			// comentando abaixo o load de filhos pois esses dados nao serao mais usados pelo sistema.
			//List<FilhoVO> filhos = carregaFilhos();
			//associado.setFilhos(filhos);
			associado.setDependentes(dependentes);
			LOG.debug("associado.getTelefoneCelular " + associado.getTelefoneCelular());

			LOG.debug("........... " + associado.getSetor().getEndereco().getMunicipio().getEstado().getCodigo());
			
			if (associado.getCodigo() != null && associado.getCodigo() > 0){
				delegate.updateAssociado(geraAssembler());
				FacesMessage msgs = new FacesMessage("Registro Atualizado com sucesso.");
				FacesContext facesContext =  FacesContext.getCurrentInstance();
				facesContext.addMessage(null, msgs);
			}else {
				associado = delegate.insertAssociado(geraAssembler());
				FacesMessage msgs = new FacesMessage("Registro Salvo com sucesso.");
				FacesContext facesContext =  FacesContext.getCurrentInstance();
				facesContext.addMessage(null, msgs);
			}
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
	
	public String salvarVinculacao(){
		try {
			if (this.vinculacao.getCodigo() != null && this.vinculacao.getCodigo().intValue() > 0){
				delegate.updateVinculacao(vinculacao);
				FacesMessage msgs = new FacesMessage("Registro Atualizado com sucesso.");
				FacesContext facesContext =  FacesContext.getCurrentInstance();
				facesContext.addMessage(null, msgs);
			}else{
				delegate.insertVinculacao(vinculacao);
				FacesMessage msgs = new FacesMessage("Registro Inserido com sucesso.");
				FacesContext facesContext =  FacesContext.getCurrentInstance();
				facesContext.addMessage(null, msgs);
			}

			return getSucesso();
		} catch(SmartAppException appEx){
			FacesMessage msgs = new FacesMessage(FacesMessage.SEVERITY_ERROR, appEx.getMensagem(), appEx.getMensagem());
			FacesContext facesContext =  FacesContext.getCurrentInstance();
			facesContext.addMessage(null, msgs);
			LOG.error("Error ", appEx);
			return "falha";
		} catch(SmartEnvException envEx){
			String msgErr = "Ocorreu um erro inesperado, contate o seu administrador.";
			FacesMessage msgs = new FacesMessage(FacesMessage.SEVERITY_ERROR, msgErr, msgErr);
			FacesContext facesContext =  FacesContext.getCurrentInstance();
			facesContext.addMessage(null, msgs);
			LOG.error("Error ", envEx);
			return "falha";
		} catch(Exception e){
			String msgErr = "Ocorreu um erro inesperado, contate o seu administrador.";
			LOG.error("Error ", e);
			FacesMessage msgs = new FacesMessage(FacesMessage.SEVERITY_ERROR, msgErr, msgErr);
			FacesContext.getCurrentInstance().addMessage(null, msgs);
			return "falha";
		}
	}
	
	/**
	 * Detalha um associado.
	 * @return Mensagem p�s-opera��o.
	 */
	public String carregar(){
		try{
			setAssociado(delegate.findAssociadoByPrimaryKey(associado));
			VinculacaoPlanoFiltroAssembler vinculacaoPlanoFiltroAssembler = new VinculacaoPlanoFiltroAssembler();
			vinculacaoPlanoFiltroAssembler.setAssociado(associado);
			//associado.setVinculacoes(delegate.findVinculacoes(vinculacaoPlanoFiltroAssembler));
			//associado.setBeneficiarios(delegate.findOutrosBeneficiaveisByAssociado(associado));
			LOG.debug("filhos " + associado.getFilhos());
			OutroBeneficiavelVO outroBeneficiavel = new OutroBeneficiavelVO();
			outroBeneficiavel.setAssociado(associado);
			associado.setBeneficiarios(delegate.findOutrosBeneficiaveisByAssociado(outroBeneficiavel));
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
	
	public String atualizarValor(){
		try{
			valorAtual = delegate.findValorAtualSocioUsuario();
			LOG.debug("valorAtual " + valorAtual);
			valorNovo = null;
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
	public String salvarAtualizarValor(){
		try{
			LOG.debug("valorNovo  " + valorNovo);
			delegate.insertValorSocioUsuario(valorNovo);
			FacesMessage msgs = new FacesMessage("Registro Salvo com sucesso.");
			FacesContext facesContext =  FacesContext.getCurrentInstance();
			facesContext.addMessage(null, msgs);
			this.atualizarValor();
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
	
	public String remover(){
		try {
			delegate.removeAssociado(associado);
			associados = new ArrayList<AssociadoVO>();
			FacesMessage msgs = new FacesMessage("Registro Removido com sucesso.");
			FacesContext facesContext =  FacesContext.getCurrentInstance();
			facesContext.addMessage(null, msgs);
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
	
	public String consultar(){
		try {
			associados = delegate.findAssociadoByFilter(filtro);
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
	
	public String print(){
		try {
			return getSucesso();
		} catch (Exception e) {
			return tratarExcecao(e);
		}
	}
	
	public String printOutrosBeneficiaveis(){
		try {
			return getSucesso();
		} catch (Exception e) {
			return tratarExcecao(e);
		}
	}
	
	public String printVinculacoes(){
		try {
			return getSucesso();
		} catch (Exception e) {
			return tratarExcecao(e);
		}
	}
	
	public String carregarEmail(){
		emails = "";
		if (associados == null || associados.size() == 0)
		{
			try {
				associadoEmails = delegate.findAllAssociados();
			} catch (Exception e) {
				LOG.error("ERRO NA HORA DE CARREGAR OS E-MAILS", e);
			}
		}else associadoEmails = associados;
		
		for (AssociadoVO associado : associadoEmails){
			if (associado.getEmail() != null){
				if (!"".equals(emails)){
					emails += ";" + associado.getEmail();
				}else{
					emails += associado.getEmail();
				}
			}
		}
		return getSucesso();
	}

	public String carregarEmailAniversariantes(){
		LOG.debug("VAI CARREGAR OS E-MAILS DO ANIVERSARIANTE " + associadosAniversariantes.size());
		emails = "";
		if (associadosAniversariantes != null && associadosAniversariantes.size() > 0){
			for (AssociadoVO associado : associadosAniversariantes){
				LOG.debug("associado.getEmail() " + associado.getEmail());
				if (associado.getEmail() != null){
					if (!"".equals(emails)){
						emails += ";" + associado.getEmail();
					}else{
						emails += associado.getEmail();
					}
				}
			}
		}
		LOG.debug("EMAILS " + emails);
		return getSucesso();
	}
	
	public String printAniversariantes(){
		try {
			LOG.debug("associadosAniversariantes " + associadosAniversariantes);
			return getSucesso();
		} catch (Exception e) {
			return tratarExcecao(e);
		}
	}
	
	public String printEventos(){
		try {
			LOG.debug("historicoAssociado " + historicoAssociado);
			return getSucesso();
		} catch (Exception e) {
			return tratarExcecao(e);
		}
	}
	
	public String consultarHistorico(){
		try {
			historicoAssociado = delegate.findHistoricoByFilter(historicoFiltro);
			LOG.debug("historicoAssociado " + historicoAssociado);
			return getSucesso();
		} catch (Exception e) {
			return tratarExcecao(e);
		}
	}
	
	public String consultarHistoricoVinculacoes(){
		try {
			historicoVinculacaoFiltro.setAssociado(associado);
			historicoVinculacoes = delegate.findHistoricoVinculadosPlanoByFilter(historicoVinculacaoFiltro);
			LOG.debug("historicoVinculacaoFiltro " + historicoVinculacaoFiltro);
			return getSucesso();
		} catch (Exception e) {
			return tratarExcecao(e);
		}
	}
	
	/**
	 * Obt�m associado no bean para ser utilizado pela interface.
	 * @return Associado presente no bean.
	 */
	public AssociadoVO getAssociado() {
		return associado;
	}

	/**
	 * Grava no bean o associado vindo da interface.
	 * @param associado Associado com atributos preenchidos na interface.
	 */
	public void setAssociado(AssociadoVO associado) {
		LOG.debug("ASSOCIADO " +associado.getNome());
		this.associado = associado;
	}

	/**
	 * Obt�m cole��o de associados no bean para ser utilizado pela interface.
	 * @return Cole��o de associados presente no bean.
	 */
	public Collection getAssociados() {
		return associados;
	}

	
	public String enviarEmail(){
		try {
			LOG.debug("	VAI ENVIAR E-MAIL");
			email.setAssociados(associados);
			delegate.sendMail(email);
			FacesMessage msgs = new FacesMessage("E-mail enviado com sucesso.");
			FacesContext facesContext =  FacesContext.getCurrentInstance();
			facesContext.addMessage(null, msgs);
			LOG.debug("	ENVIAR E-MAIL");
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
	
	/**
	 * Metodo utilizado pelo DWR para buscar o associado pelo cpf utilizando AJAX
	 * @param associado
	 * @return
	 */
	public AssociadoVO findAssociadoByCpf(String cpf){
		AssociadoVO associadoFiltro = new AssociadoVO();
		associadoFiltro.setCpf(Formatador.parseCPF(cpf));
		try{
			AssociadoVO vo = delegate.findAssociadoByCPF(associadoFiltro);
			return vo;
		}catch(Exception e){
			LOG.error("ERRO NA HORA DE BUSCAR POR CPF", e);
			return null;
		}
	}

	/**
	 * Grava no bean a cole��o de associados vinda da interface.
	 * @param associados Cole��o de associados com atributos preenchidos na interface.
	 */
	public void setAssociados(Collection<AssociadoVO> associados) {
		this.associados = associados;
	}
	
	public Collection getAssociadosAniversariantes(){
		try {
			associadosAniversariantes = delegate.findProximosAniversariantes() ;
		} catch (Exception e) {
			tratarExcecao(e);
		}
		return associadosAniversariantes;
	}

	public void setAssociadosAniversariantes(Collection associadosAniversariantes) {
		this.associadosAniversariantes = associadosAniversariantes;
	}

	public Float getValorAtual() {
		if (valorAtual == null){
			atualizarValor();
		}
		return valorAtual;
	}

	public void setValorAtual(Float valorAtual) {
		this.valorAtual = valorAtual;
	}

	public Float getValorNovo() {
		return valorNovo;
	}

	public void setValorNovo(Float valorNovo) {
		this.valorNovo = valorNovo;
	}
	
	public boolean getPossuiDependentes(){
		if (filtro.getStatusPossuiDependente() == null || "".equals(filtro.getStatusPossuiDependente())){
			return false;
		} else return true;
	}
	
	public void setPossuiDependentes(boolean possuiDependentes){
		if (possuiDependentes) filtro.setStatusPossuiDependente("S");
		else filtro.setStatusPossuiDependente("");
	}
	
	public boolean getPossuiFilho(){
		if (filtro.getStatusPossuiFilho() == null || "".equals(filtro.getStatusPossuiFilho())){
			return false;
		} else return true;
	}
	
	public void setIncluiInativo(boolean possuiFilho){
		if (possuiFilho) filtro.setStatusIncluirInativos("S");
		else filtro.setStatusIncluirInativos("");
	}
	
	public String getStrPreCadastro() {
		return strPreCadastro;
	}

	public void setStrPreCadastro(String strPreCadastro) {
		this.strPreCadastro = strPreCadastro;
	}


	public boolean getPreCadastro(){
		if (filtro.getStatusPreCadastro() == null || !"S".equals(filtro.getStatusPreCadastro())){
			return false;
		} else return true;
	}
	
	public void setPreCadastro(boolean preCadastro){
		if (preCadastro) filtro.setStatusPreCadastro("S");
		else filtro.setStatusPreCadastro("N");
	}
	

	public boolean getIncluiInativo(){
		if (filtro.getStatusIncluirInativos() == null || "".equals(filtro.getStatusIncluirInativos())){
			return false;
		} else return true;
	}
	
	public void setPossuiFilho(boolean possuiFilho){
		if (possuiFilho) filtro.setStatusPossuiFilho("S");
		else filtro.setStatusPossuiFilho("");
	}
	
	public boolean getStatusRecebeJornal(){
		if (associado.getStatusRecebeJornal() == null || "".equals(associado.getStatusRecebeJornal().trim())) return false;
		else return true;
	}
	
	public void setStatusRecebeJornal(boolean recebeJornal){
		if (recebeJornal) associado.setStatusRecebeJornal("S");
		else associado.setStatusRecebeJornal("");
	}
	
	public boolean getStatusRecebeJornalFiltro(){
		if (filtro.getStatusRecebeJornal() == null || "".equals(filtro.getStatusRecebeJornal())) return false;
		else return true;
	}
	
	public void setStatusRecebeJornalFiltro(boolean recebeJornal){
		if (recebeJornal) filtro.setStatusRecebeJornal("S");
		else filtro.setStatusRecebeJornal("");
	}

	/**
	 * Obten��o da cole��o de estados para ser utilizada pela interface
	 * @return
	 */
	public Collection getEstados() {
		if (estados == null){
			try {
				 Collection collEstados = entidadesComunsDelegate.findAllEstado(); 
				 estados = (collEstados != null) ? getSelect(collEstados, "codigo", "sigla") : new ArrayList();
			} catch (Exception e) {
				LOG.error("erro no momento de carregar os estados", e);
			}
		}
		return estados;
	}
	
	public Collection getPessoasVinculadas()
	{
		Collection pessoasVinculadas = new ArrayList();
		
		if (associado != null && associado.getCodigo() != null && associado.getNome() != null && !"".equals(associado.getNome())) {
			pessoasVinculadas.add(new SelectItem(associado.getCodigo(), associado.getNome()));
		}
		
		if (associado.getConjuge() != null && associado.getConjuge().getCodigo() != null && associado.getConjuge().getNome() != null && !"".equals(associado.getConjuge().getNome())) {
			pessoasVinculadas.add(new SelectItem(associado.getConjuge().getCodigo(), associado.getConjuge().getNome()));
		}
		
		if (associado.getDependentes() != null && associado.getDependentes().size() > 0){
			for (DependenteVO dependente : associado.getDependentes()){
				pessoasVinculadas.add(new SelectItem(dependente.getCodigo(), dependente.getNome()));
			}
		}
		if (associado.getFilhos() != null && associado.getFilhos().size() > 0){
			for (FilhoVO filho : associado.getFilhos()){
				pessoasVinculadas.add(new SelectItem(filho.getCodigo(), filho.getNome()));
			}
		}
		try{
			OutroBeneficiavelVO outro = new OutroBeneficiavelVO();
			outro.setAssociado(associado);
			associado.setBeneficiarios(delegate.findOutrosBeneficiaveisByAssociado(outro));
		}catch(Exception e){
			tratarExcecao(e);
		}
		if (associado.getBeneficiarios() != null && associado.getBeneficiarios().size() > 0){
			for (OutroBeneficiavelVO outroBeneficiavel : associado.getBeneficiarios()){
				pessoasVinculadas.add(new SelectItem(outroBeneficiavel.getCodigo(), outroBeneficiavel.getNome()));
			}
		}
		return pessoasVinculadas;
	}
	
	public Collection getConveniosItens() {
		Collection collConvenios = new ArrayList();
		try {
			collConvenios = convenioDelegate.findConvenioByFilter(new ConvenioVO());
			collConvenios = (collConvenios != null) ? getSelect(collConvenios, "codigo", "nomeFantasia") : new ArrayList();
		} catch (Exception e) {
			LOG.error("erro no momento de carregar os planos dos convenios", e);
		}
		return collConvenios;
	}
	
	public Collection getPlanosItens() {
		return planosItens;
	}
	
	public void carregarPlanosByConvenios(){
		try {
			planosItens = (planosItens != null || planosItens.size() == 0) ? getSelect(convenioDelegate.findPlanoConvenioByFilter(vinculacao.getPlano()), "codigo", "nome") : new ArrayList();
		} catch (Exception e) {
			LOG.error("erro no momento de carregar os planos dos convenios", e);
		}
	}
	
	public void carregarPlanosByConveniosHistoricoVinculacoes(){
		try {
			planosItens = (planosItens != null || planosItens.size() == 0) ? getSelect(convenioDelegate.findPlanoConvenioByFilter(historicoVinculacaoFiltro.getPlano()), "codigo", "nome") : new ArrayList();
		} catch (Exception e) {
			LOG.error("erro no momento de carregar os planos dos convenios", e);
		}
	}

	/**
	 * Grava no bean a cole��o usada pela interface
	 * @param estados
	 */
	public void setEstados(Collection estados) {
		this.estados = estados;
	}

	public Collection getBancos() {
		if (bancos == null){
			try {
				Collection collBancos = financeiroDelegate.findAllBanco();
				 bancos = (collBancos != null) ? getSelect(collBancos, "codigo", "nome"): new ArrayList();
			} catch (Exception e) {
				LOG.error("ERRO NO MOMENTO DE CARREGAR OS BANCOS", e);
			}
		}
		return bancos;
	}

	public void setBancos(Collection bancos) {
		this.bancos = bancos;
	}

	public AssociadoFiltroAssembler getFiltro() {
		return filtro;
	}

	public void setFiltro(AssociadoFiltroAssembler filtro) {
		this.filtro = filtro;
	}

	public Collection getParentescos() {
		if (parentescos == null){
			try {
				Collection collParentesco = delegate.findAllParentesco();
				parentescos = (collParentesco != null) ? getSelect(collParentesco, "codigo", "nome") : new ArrayList();
			} catch (Exception e) {
				LOG.error("ERRO NO MOMENTOD E CARREGAR OS PARENTESCOS", e);
			}
		}
		return parentescos;
	}

	public void setParentescos(Collection parentescos) {
		this.parentescos = parentescos;
	}

	public Collection getTipos() {
		if (tipos == null){
			try {
				 Collection collTipos = delegate.findAllTipoEvento();
				 tipos = (collTipos != null) ? getSelect(collTipos, "codigo", "nome") : new ArrayList();
			} catch (Exception e) {
				LOG.error("ERRO NO MOMENTO DE CARREGAR OS TIPOS", e);
			}
		}
		return tipos;
	}

	public void setTipos(Collection tipos) {
		this.tipos = tipos;
	}
	
	public String getCep(){
		if (associado.getEndereco() != null && associado.getEndereco().getCep() != null){
			return Formatador.formatCEP(associado.getEndereco().getCep());
		}
		return "";
	}
	
	public void setCep(String cep){
		if (!"".equals(cep)){
			associado.getEndereco().setCep(Formatador.parseCEP(cep));
		}
	}
	
	public String getFiltroCep(){
		if (filtro.getEndereco().getCep() != null){
			return Formatador.formatCEP(filtro.getEndereco().getCep());
		}
		return "";
	}
	
	public void setFiltroCep(String cep){
		if (!"".equals(cep)){
			filtro.getEndereco().setCep(Formatador.parseCEP(cep));
		}
	}
	
	public String getTelefoneCelular(){
		return Formatador.formatTelefone(associado.getTelefoneCelular());
	}
	
	public void setTelefoneCelular(String celular){
		if (celular != null && !"".equals(celular)){
			Long telefone = (Formatador.parseTelefone(celular) >= new Long(0)) ? Formatador.parseTelefone(celular) : null;
			associado.setTelefoneCelular(telefone);
		}
	}
	
	public String getFiltroTelefoneCelular(){
		return Formatador.formatTelefone(filtro.getTelefoneCelular());
	}
	
	public void setFiltroTelefoneCelular(String celular){
		if (celular != null && !"".equals(celular)){
			Long telefone = (Formatador.parseTelefone(celular) >= new Long(0)) ? Formatador.parseTelefone(celular) : null;
			filtro.setTelefoneCelular(telefone);
		}
	}
	
	public String getTelefoneResidencial(){
		if (associado.getTelefoneResidencial() != null){
			return Formatador.formatTelefone(associado.getTelefoneResidencial());
		}
		return "";
	}
	
	public void setTelefoneResidencial(String telefoneResidencial){
		if (telefoneResidencial != null && !"".equals(telefoneResidencial)){
		Long telefone = (Formatador.parseTelefone(telefoneResidencial) >= new Long(0)) ? Formatador.parseTelefone(telefoneResidencial) : null;
		associado.setTelefoneResidencial(telefone);
		}
	}
	
	public void setFiltroTelefoneResidencial(String telefoneResidencial){
		if (telefoneResidencial != null && !"".equals(telefoneResidencial)){
			Long telefone = (Formatador.parseTelefone(telefoneResidencial) >= new Long(0)) ? Formatador.parseTelefone(telefoneResidencial) : null;
			filtro.setTelefoneResidencial(telefone);
		}
	}
	
	public String getFiltroTelefoneResidencial(){
		if (filtro.getTelefoneResidencial() != null){
			return Formatador.formatTelefone(filtro.getTelefoneResidencial());
		}
		return "";
	}
	
	public String getTelefoneComercial(){
		if (associado.getTelefoneComercial() != null){
			return Formatador.formatTelefone(associado.getTelefoneComercial());
		}
		return "";
	}
	
	public void setTelefoneComercial(String telefoneComercial){
		if (telefoneComercial != null && !"".equals(telefoneComercial)){
			Long telefone = (Formatador.parseTelefone(telefoneComercial) >= new Long(0)) ? Formatador.parseTelefone(telefoneComercial) : null;
			associado.setTelefoneComercial(telefone);
		}
	}
	
	public String getFiltroTelefoneComercial(){
		if (filtro.getTelefoneComercial() != null){
			return Formatador.formatTelefone(filtro.getTelefoneComercial());
		}
		return "";
	}
	
	public void setFiltroTelefoneComercial(String telefoneComercial){
		if (telefoneComercial != null && !"".equals(telefoneComercial)){
			Long telefone = (Formatador.parseTelefone(telefoneComercial) >= new Long(0)) ? Formatador.parseTelefone(telefoneComercial) : null;
			filtro.setTelefoneComercial(telefone);
		}
	}
	
	public String getTelefoneSetor(){
		if (associado.getSetor().getTelefone() != null){
			return Formatador.formatTelefone(associado.getSetor().getTelefone());
		}
		return "";
	}
	
	public void setTelefoneSetor(String telefoneSetor){
		if (telefoneSetor != null && !"".equals(telefoneSetor)){
			Long telefone = (Formatador.parseTelefone(telefoneSetor) >= new Long(0)) ? Formatador.parseTelefone(telefoneSetor) : null;
			associado.getSetor().setTelefone(telefone);
		}
	}
	
	public String getFiltroTelefoneSetor(){
		if (filtro.getSetor().getTelefone() != null){
			return Formatador.formatTelefone(filtro.getSetor().getTelefone());
		}
		return "";
	}
	
	public void setFiltroTelefoneSetor(String telefoneSetor){
		if (telefoneSetor != null && !"".equals(telefoneSetor)){
			Long telefone = (Formatador.parseTelefone(telefoneSetor) >= new Long(0)) ? Formatador.parseTelefone(telefoneSetor) : null;
			filtro.getSetor().setTelefone(telefone);
		}
	}
	
	public void setCpf(String cpf){
		LOG.debug("Formatador.parseCPF(cpf) " + Formatador.parseCPF(cpf));
		associado.setCpf(Formatador.parseCPF(cpf));
	}
	
	public String getCpf(){
		LOG.debug("associado.getCpf() " + associado.getCpf());
		LOG.debug("Formatador.formatCpf(associado.getCpf()) " + Formatador.formatCpf(associado.getCpf()));
		return Formatador.formatCpf(associado.getCpf());
	}
	
	public void setConjugeCpf(String cpf){
		associado.getConjuge().setCpf(Formatador.parseCPF(cpf));
	}
	
	public String getConjugeCpf(){
		return Formatador.formatCpf(associado.getConjuge().getCpf());
	}
	
	public void setBeneficiarioCpf(String cpf){
		beneficiario.setCpf(Formatador.parseCPF(cpf));
	}
	
	public String getBeneficiarioCpf(){
		return (beneficiario.getCpf() != null) ? Formatador.formatCpf(new Long(beneficiario.getCpf())) : "";
	}
	
	public void setFiltroCpf(String cpf){
		filtro.setCpf(Formatador.parseCPF(cpf));
	}
	
	public String getFiltroCpf(){
		return Formatador.formatCpf(filtro.getCpf());
	}
	
	public String getDataAtual(){
		try{
			return formatter.format(utilDelegate.getCurrentDate());
		}catch(Exception e){
			LOG.error("ERRO NA HORA DE BUSCAR A DATA ATUAL", e);
			return "";
		}
	}
	
	public String getEmails() {
		try {
			carregarEmail();
		} catch (Exception e) {
			LOG.error("ERRO NO MOMENTO DE CARREGAR OS EMAILS", e);
		}
		return emails;
	}

	public void setEmails(String emails) {
		this.emails = emails;
	}

	public EmailVO getEmail() {
		return email;
	}

	public void setEmail(EmailVO email) {
		this.email = email;
	}

	public String getOperador(){
		HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
		UsuarioVO usuario = (UsuarioVO)request.getSession().getAttribute("usuario");
		LOG.debug("usuario " + usuario);
		if (usuario != null){
			LOG.debug("usuario.getLogin() " + usuario.getLogin());
			LOG.debug("usuario.getNome() " + usuario.getNome());
			return usuario.getLogin();
		}
		else return "";
	}
	
	/**
	 * Metodo que deve ser implementado caso a tela venha ter ordena��o, pelo nome das colunas
	 */
	@Override
	protected boolean isDefaultAscending(String sortColumn) {
		return false;
	}

	/**
	 * Metodo que deve ser implementado caso a tela venha ter ordena��o pelo nome da coluna
	 */
	@Override
	protected void sort(String column, boolean ascending) {
	}
	
	private void carregaFiltro(){
		if (filtro == null)  filtro = new AssociadoFiltroAssembler();
		
		filtro.setEndereco(new EnderecoVO());
		filtro.getEndereco().setMunicipio(new MunicipioVO());
		filtro.getEndereco().getMunicipio().setEstado(new EstadoVO());
		filtro.setNaturalidade(new MunicipioVO());
		filtro.getNaturalidade().setEstado(new EstadoVO());
		filtro.setProfissao(new ProfissaoVO());
		filtro.setSetor(new SetorVO());
		filtro.getSetor().setOrgao(new OrgaoVO());
		filtro.getSetor().setEndereco(new EnderecoVO());
		filtro.getSetor().getEndereco().setMunicipio(new MunicipioVO());
		filtro.getSetor().getEndereco().getMunicipio().setEstado(new EstadoVO());
		filtro.setContribuinte(new AssociadoVO());
		filtro.setHistorico(new HistoricoFiltroAssembler());
		filtro.setConta(new ContaAssociadoVO());
		filtro.getConta().setBancoVO(new BancoVO());
		filtro.setDependente(new DependenteVO());
		filtro.getDependente().setAssociado(new AssociadoVO());
		filtro.getDependente().setParentesco(new ParentescoVO());
		filtro.setFilho(new FilhoVO());
		filtro.getFilho().setAssociado(new AssociadoVO());
		filtro.setProfissao(new ProfissaoVO());
		filtro.setHistorico(new HistoricoFiltroAssembler());
		filtro.getHistorico().setTipoEvento(new TipoEventoVO());
		filtro.setConjuge(new ConjugeVO());
	}
	
	private void carregaAssociado(){
		if (associado == null) associado = new AssociadoAssembler();
		associado.setEndereco(new EnderecoVO());
		associado.getEndereco().setMunicipio(new MunicipioVO());
		associado.getEndereco().getMunicipio().setEstado(new EstadoVO());
		associado.setNaturalidade(new MunicipioVO());
		associado.getNaturalidade().setEstado(new EstadoVO());
		associado.setSetor(new SetorVO());
		associado.getSetor().setOrgao(new OrgaoVO());
		associado.getSetor().setEndereco(new EnderecoVO());
		associado.getSetor().getEndereco().setMunicipio(new MunicipioVO());
		associado.getSetor().getEndereco().getMunicipio().setEstado(new EstadoVO());
		associado.setConta(new ContaAssociadoVO());
		associado.getConta().setBancoVO(new BancoVO());
		associado.setContribuinte(new AssociadoVO());
		associado.setProfissao(new ProfissaoVO());
		associado.setConjuge(new ConjugeVO());
	}
	
	private AssociadoAssembler geraAssembler(){
		AssociadoAssembler assembler = new AssociadoAssembler();
		try {
			BeanUtils.copyProperties(assembler, associado);

				if (this.getStrPreCadastro() == null || !this.getStrPreCadastro().equals("S")) {
					assembler.setPreCadastro(Boolean.FALSE);
				} else {
					assembler.setPreCadastro(Boolean.TRUE);
				}
		} catch (Exception e) {
			LOG.error("Erro na hora de passar os atributos do associado para o assembler", e);
			return null;

		}
		return assembler;
	}
	
	public List<FilhoVO> carregaFilhos(){
		List<FilhoVO> filhos = new ArrayList<FilhoVO>();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest)facesContext.getExternalContext().getRequest();
		String[] codigoFilho = request.getParameterValues("filho_codigo");
		String[] nomeFilho = request.getParameterValues("filho_nome");
		String[] dataNascimento = request.getParameterValues("filho_dataNascimento");
		String[] sexo = request.getParameterValues("filho_sexo");
		String[] cpf = request.getParameterValues("filho_cpf");
		if (nomeFilho != null && nomeFilho.length > 0)
		{
			for (int i = 0; i < nomeFilho.length; i++)
			{
				try {
					FilhoVO filho = new FilhoVO();
					LOG.debug("<<<<<<<<<<<<<<<< codigoFilho >>>>>>>>>>>>>>> " + codigoFilho);
					if (codigoFilho[i]!= null && !"".equals(codigoFilho[i])){
						filho.setCodigo(Integer.valueOf(codigoFilho[i]));
					}
					filho.setNome(nomeFilho[i]);
					if (!"".equals(dataNascimento[i].trim()))
						filho.setDataNascimento(dateFormat.parse(dataNascimento[i]));
					filho.setSexo(sexo[i]);
					filho.setCpf((!"".equals(cpf[i]) ? new Long(cpf[i]) : null));
					filhos.add(filho);
				} catch (Exception e) {
					LOG.error("ERRO NA HORA DE CARREGAR O FILHO", e);
				}
			}
			return filhos;
		} else return null;
	}
	
	public List<DependenteVO> carregaDependentes(){
		List<DependenteVO> dependentes = new ArrayList<DependenteVO>();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest)facesContext.getExternalContext().getRequest();
		String[] codigoDependente = request.getParameterValues("dp_codigo");
		//System.out.println("codigoDependente " + codigoDependente);
		String[] nomeDependente = request.getParameterValues("dp_nome");
		String[] rg = request.getParameterValues("dp_rg");
		String[] dataNascimento = request.getParameterValues("dp_dataNascimento");
		String[] sexo = request.getParameterValues("dp_sexo");
		String[] parentesco = request.getParameterValues("dp_parentesco");
		String[] cpf = request.getParameterValues("dp_cpf");
		if (nomeDependente != null && nomeDependente.length > 0)
		{
			for (int i = 0; i < nomeDependente.length; i++)
			{
				try {
					DependenteVO dependente = new DependenteVO();
					dependente.setAssociado(associado);
					if (codigoDependente[i]!= null && !"".equals(codigoDependente[i])){
						dependente.setCodigo(Integer.valueOf(codigoDependente[i]));
					}
					dependente.setNome(nomeDependente[i]);
					if (!"".equals(rg[i].trim())) dependente.setRg(new Long(rg[i]));
					if (!"".equals(dataNascimento[i].trim())) dependente.setDataNascimento(dateFormat.parse(dataNascimento[i]));
					dependente.setSexo(sexo[i]);
					if (!"".equals(parentesco[i].trim())) dependente.setParentesco(new ParentescoVO(new Short(parentesco[i])));
					dependente.setCpf((!"".equals(cpf) ? new Long(cpf[i]) : null));
					dependentes.add(dependente);
				} catch (Exception e) {
					LOG.error("ERRO NA HORA DE CARREGAR O DEPENDENTE", e);
				}
			}
			return dependentes;
		}else return null;
	}
	
	public Collection<HistoricoAssociadoVO> getHistoricoAssociado() {
		return historicoAssociado;
	}

	public void setHistoricoAssociado(
			Collection<HistoricoAssociadoVO> historicoAssociado) {
		this.historicoAssociado = historicoAssociado;
	}

	public HistoricoFiltroAssembler getHistoricoFiltro() {
		return historicoFiltro;
	}

	public void setHistoricoFiltro(HistoricoFiltroAssembler historicoFiltro) {
		this.historicoFiltro = historicoFiltro;
	}

	public Date getDataImportacao() {
		return dataImportacao;
	}

	public void setDataImportacao(Date dataImportacao) {
		this.dataImportacao = dataImportacao;
	}

	public Collection<AssociadoImportacaoNucreVO> getAssociadosFaltantes() {
		return associadosFaltantes;
	}

	public void setAssociadosFaltantes(
			Collection<AssociadoImportacaoNucreVO> associadosFaltantes) {
		this.associadosFaltantes = associadosFaltantes;
	}
	
	public String cancelarAssociado(){
		try{
			HistoricoAssociadoVO historico	= new HistoricoAssociadoVO();
			
			historico.setTipoEvento(new TipoEventoVO());
			// TODO parametrizar para obter tipo evento do par�metro
			historico.getTipoEvento().setCodigo(new Short("2"));
			historico.setAssociado(this.associado);
			
			delegate.insertHistoricoAssociado(historico);
			
			associado.setUltimoEvento(new HistoricoAssociadoVO());
			associado.getUltimoEvento().setTipoEvento(new TipoEventoVO());
			associado.getUltimoEvento().getTipoEvento().setCodigo(new Short("2"));
			
			FacesMessage msgs = new FacesMessage("Registro Salvo com sucesso.");
			FacesContext facesContext =  FacesContext.getCurrentInstance();
			facesContext.addMessage(null, msgs);
			
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
	
	public String reativarAssociado(){
		try{
			
			HistoricoAssociadoVO historico	= new HistoricoAssociadoVO();
			
			historico.setTipoEvento(new TipoEventoVO());
			// TODO parametrizar para obter tipo evento do par�metro
			historico.getTipoEvento().setCodigo(new Short("3"));
			historico.setAssociado(this.associado);
			
			delegate.insertHistoricoAssociado(historico);
			
			associado.setUltimoEvento(new HistoricoAssociadoVO());
			associado.getUltimoEvento().setTipoEvento(new TipoEventoVO());
			associado.getUltimoEvento().getTipoEvento().setCodigo(new Short("3"));
			
			FacesMessage msgs = new FacesMessage("Registro Salvo com sucesso.");
			FacesContext facesContext =  FacesContext.getCurrentInstance();
			facesContext.addMessage(null, msgs);
			
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

	public void setDelegate(AssociadoDelegate delegate) {
		this.delegate = delegate;
	}

	public void setEntidadesComunsDelegate(
			EntidadesComunsDelegate entidadesComunsDelegate) {
		this.entidadesComunsDelegate = entidadesComunsDelegate;
	}

	public void setFinanceiroDelegate(FinanceiroDelegate financeiroDelegate) {
		this.financeiroDelegate = financeiroDelegate;
	}

	public void setUtilDelegate(UtilDelegate utilDelegate) {
		this.utilDelegate = utilDelegate;
	}

	public List getData() {
		return data;
	}

	public void setData(List data) {
		this.data = data;
	}
	
	public String salvarBeneficiario(){
		try {
			if (this.beneficiario.getCodigo() != null && this.beneficiario.getCodigo().intValue() > 0){
				delegate.updateOutroBeneficiavel(beneficiario);
			}else{
				delegate.insertOutrobeneficiavel(beneficiario);
			}
			FacesMessage msgs = new FacesMessage("Registro Salvo com sucesso.");
			FacesContext facesContext =  FacesContext.getCurrentInstance();
			facesContext.addMessage(null, msgs);
			this.concultarVinculacoes();
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
	
	
	public String carregarOutroBeneficiaro(){
		try {

			this.beneficiario = delegate.findOutrosBeneficiavelByPrimaryKey(this.beneficiario);
			
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
	
	public String carregarVinculacao(){
		try {
			this.vinculacao = delegate.findVinculacaoByPrimaryKey(vinculacao);
			carregarPlanosByConvenios();
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
	
	public String removerBeneficiario(){
		try {
			this.delegate.removeOutroBeneficiavel(this.beneficiario);
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
	
	public void prepararNovoBeneficiario(){
		this.beneficiario = new OutroBeneficiavelVO();
		this.beneficiario.setAssociado(associado);
		LOG.debug("OLHA AI O BENEFICIARIO " + beneficiario.getAssociado().getCodigo());
	}
	
	public void prepararNovaVinculacao(){
		this.vinculacao = new VinculacaoPlanoVO();
		this.vinculacao.setAssociado(associado);
		this.vinculacao.setPessoa(new PessoaVO());
		this.vinculacao.setPlano(new PlanoConvenioVO());
		this.vinculacao.getPlano().setConvenio(new ConvenioVO());
		LOG.debug("OLHA AI O ASSOCIADO " + vinculacao.getAssociado().getCodigo());
	}
	
	public void concultarVinculacoes(){
		//this.setVinculacoes(this.associadoDelegate.findVinculacoes(this.vinculacaoFiltro));
	}

	public OutroBeneficiavelVO getBeneficiario() {
		return beneficiario;
	}		

	public void setBeneficiario(OutroBeneficiavelVO beneficiario) {
		this.beneficiario = beneficiario;
	}

	public Collection<ConvenioVO> getConvenios() {
		return convenios;
	}

	public void setConvenios(Collection<ConvenioVO> convenios) {
		this.convenios = convenios;
	}

	public VinculacaoPlanoFiltroAssembler getVinculacaoFiltro() {
		return vinculacaoFiltro;
	}

	public void setVinculacaoFiltro(VinculacaoPlanoFiltroAssembler vinculacaoFiltro) {
		this.vinculacaoFiltro = vinculacaoFiltro;
	}

	public Collection<VinculacaoPlanoVO> getVinculacoes() throws SmartEnvException, SmartAppException {
		
		try{
			if (associado != null && associado.getCodigo() != null){
				VinculacaoPlanoVO vo = new VinculacaoPlanoVO();
				vo.setAssociado(associado);
				return convenioDelegate.findVinculadosPlanoByAssociado(vo);
			}
		}catch (Exception e) {
			tratarExcecao(e);
		}	
		return new ArrayList<VinculacaoPlanoVO>();	
	}

	public void setVinculacoes(Collection<VinculacaoPlanoVO> vinculacoes) {
		this.vinculacoes = vinculacoes;
	}

	public Collection<PlanoConvenioVO> getPlanos() {
		return planos;
	}

	public void setPlanos(Collection<PlanoConvenioVO> planos) {
		this.planos = planos;
	}

	public VinculacaoPlanoVO getVinculacao() {
		return vinculacao;
	}

	public void setVinculacao(VinculacaoPlanoVO vinculacao) {
		this.vinculacao = vinculacao;
	}

	public Collection<OutroBeneficiavelVO> getBeneficiarios() {
		try{
			if (associado != null && associado.getCodigo() != null){
				OutroBeneficiavelVO outroBeneficiavel = new OutroBeneficiavelVO();
				outroBeneficiavel.setAssociado(associado);
				beneficiarios = this.delegate.findOutrosBeneficiaveisByAssociado(outroBeneficiavel);
				return beneficiarios;
			}
		} catch (Exception e) {
			tratarExcecao(e);
		}		
		return new ArrayList<OutroBeneficiavelVO>();	
	}

	public void setBeneficiarios(Collection<OutroBeneficiavelVO> beneficiarios) {
		this.beneficiarios = beneficiarios;
	}

	public void setConvenioDelegate(ConvenioDelegate convenioDelegate) {
		this.convenioDelegate = convenioDelegate;
	}

	public Collection<VinculadoPlanoAssembler> getHistoricoVinculacoes() {
		return historicoVinculacoes;
	}

	public void setHistoricoVinculacoes(
			Collection<VinculadoPlanoAssembler> historicoVinculacoes) {
		this.historicoVinculacoes = historicoVinculacoes;
	}

	public VinculadoPlanoAssembler getHistoricoVinculacaoFiltro() {
		return historicoVinculacaoFiltro;
	}

	public void setHistoricoVinculacaoFiltro(
			VinculadoPlanoAssembler historicoVinculacaoFiltro) {
		this.historicoVinculacaoFiltro = historicoVinculacaoFiltro;
	}
	
}