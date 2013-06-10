package br.org.asserjuf.sisjuf.financeiro;

public class ContaAssociadoVO extends ContaVO {
	
	public ContaAssociadoVO(Integer codigo) {
		super(codigo);
	}
	
	public ContaAssociadoVO() {
		
	}
	
	
	/**
	 * Objeto que representa o banco da conta em questão. 
	 */
	private BancoVO bancoVO;
	
	/**
	 * Número da agência da conta
	 */
	private String numAgencia;
	
	/**
	 * Dígito verificador do número da agência
	 */
	private String digAgencia;
	
	/**
	 * Número da conta.
	 */
	private String numConta;
	
	/**
	 * Dígito verificador do número da conta
	 */
	private String digConta;
	
	/**
	 * Código operação da conta (para contas da Caixa Econômica Federal)
	 */
	private String codOperacao;
	
	/**
	 * Objeto que representa o tipo da conta em questão.
	 */
	private TipoContaVO tipoContaVO;
	
	
	
	
	/**
	 * Obtém o objeto que representa o banco da conta em questão.
	 * @return Objeto do banco da conta
	 */
	public BancoVO getBancoVO() {
		return bancoVO;
	}
	
	/**
	 * Seta o objeto do banco da conta em questão.
	 * @param bancoVO Objeto que se deseja setar na propriedade
	 */
	public void setBancoVO(BancoVO bancoVO) {
		this.bancoVO = bancoVO;
	}
	
	/**
	 * Obtém o valor da propriedade codOperacao.
	 * @return Código de operação da conta
	 */
	public String getCodOperacao() {
		return codOperacao;
	}
	
	/**
	 * Seta o valor da propriedade codOperacao.
	 * @param codOperacao Valor que se deseja setar na propriedade
	 */
	public void setCodOperacao(String codOperacao) {
		this.codOperacao = codOperacao;
	}
	
	/**
	 * Obtém o valor da propriedade digAgência
	 * @return Dígito verificador do número da agência.
	 */
	public String getDigAgencia() {
		return digAgencia;
	}
	
	/**
	 * Seta o valor da propriedade digAgencia.
	 * @param digAgencia Valor que se deseja setar na propriedade
	 */
	public void setDigAgencia(String digAgencia) {
		this.digAgencia = digAgencia;
	}
	
	/**
	 * Obtém o valor da propriedade digConta.
	 * @return Dígito verificador do número da conta
	 */
	public String getDigConta() {
		return digConta;
	}
	
	/**
	 * Seta o valor da propriedade digConta.
	 * @param digConta Valor que se deseja setar na propriedade
	 */
	public void setDigConta(String digConta) {
		this.digConta = digConta;
	}
	
	/**
	 * Obtém o valor da propriedade numAgencia.
	 * @return Número da agência
	 */
	public String getNumAgencia() {
		return numAgencia;
	}
	
	/**
	 * Seta o valor da propriedade numAgencia.
	 * @param numAgencia Valor que se deseja setar na propriedade
	 */
	public void setNumAgencia(String numAgencia) {
		this.numAgencia = numAgencia;
	}
	
	/**
	 * Obtém o valor da propriedade numConta.
	 * @return Número da conta	
	 */
	public String getNumConta() {
		return numConta;
	}
	
	/**
	 * Seta o valor da propriedade numConta.
	 * @param numConta Valor que se deseja setar na propriedade
	 */
	public void setNumConta(String numConta) {
		this.numConta = numConta;
	}
	
	
	/**
	 * Obtém o objeto que representa o tipo da conta em questão.
	 * @return Objeto do tipo da conta
	 */
	public TipoContaVO getTipoContaVO() {
		return tipoContaVO;
	}
	
	/**
	 * Seta o objeto do tipo da conta em questão.
	 * @param tipoContaVO Objeto que se deseja setar na propriedade
	 */
	public void setTipoContaVO(TipoContaVO tipoContaVO) {
		this.tipoContaVO = tipoContaVO;
	}
	
	/**
	 * Obtém o texto da forma de identificação da conta de banco (Sigla_Banco Num_Ag-Dig/Num_Conta-Dig). 
	 * @return Texto padrão de identificação da conta de banco
	 */
	public String getNomeDefault() {
		
		String nomeContaDefault	= "";
		
		if (bancoVO!= null && bancoVO.getSigla()!=null && !bancoVO.getSigla().equals("")) {
			nomeContaDefault += bancoVO.getSigla().trim() + " ";
			nomeContaDefault += numAgencia.trim();
			
			if (digAgencia != null && !digAgencia.trim().equals("")) {
				nomeContaDefault += "-" + digAgencia;
				
			}
			
			nomeContaDefault += "/" + numConta + "-" + digConta;
			
			
		} else {
			nomeContaDefault += getNome();
			
		}
		
		
		return nomeContaDefault;
		
	}

}
