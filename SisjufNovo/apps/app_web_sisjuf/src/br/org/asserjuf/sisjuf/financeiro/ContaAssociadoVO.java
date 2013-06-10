package br.org.asserjuf.sisjuf.financeiro;

public class ContaAssociadoVO extends ContaVO {
	
	public ContaAssociadoVO(Integer codigo) {
		super(codigo);
	}
	
	public ContaAssociadoVO() {
		
	}
	
	
	/**
	 * Objeto que representa o banco da conta em quest�o. 
	 */
	private BancoVO bancoVO;
	
	/**
	 * N�mero da ag�ncia da conta
	 */
	private String numAgencia;
	
	/**
	 * D�gito verificador do n�mero da ag�ncia
	 */
	private String digAgencia;
	
	/**
	 * N�mero da conta.
	 */
	private String numConta;
	
	/**
	 * D�gito verificador do n�mero da conta
	 */
	private String digConta;
	
	/**
	 * C�digo opera��o da conta (para contas da Caixa Econ�mica Federal)
	 */
	private String codOperacao;
	
	/**
	 * Objeto que representa o tipo da conta em quest�o.
	 */
	private TipoContaVO tipoContaVO;
	
	
	
	
	/**
	 * Obt�m o objeto que representa o banco da conta em quest�o.
	 * @return Objeto do banco da conta
	 */
	public BancoVO getBancoVO() {
		return bancoVO;
	}
	
	/**
	 * Seta o objeto do banco da conta em quest�o.
	 * @param bancoVO Objeto que se deseja setar na propriedade
	 */
	public void setBancoVO(BancoVO bancoVO) {
		this.bancoVO = bancoVO;
	}
	
	/**
	 * Obt�m o valor da propriedade codOperacao.
	 * @return C�digo de opera��o da conta
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
	 * Obt�m o valor da propriedade digAg�ncia
	 * @return D�gito verificador do n�mero da ag�ncia.
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
	 * Obt�m o valor da propriedade digConta.
	 * @return D�gito verificador do n�mero da conta
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
	 * Obt�m o valor da propriedade numAgencia.
	 * @return N�mero da ag�ncia
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
	 * Obt�m o valor da propriedade numConta.
	 * @return N�mero da conta	
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
	 * Obt�m o objeto que representa o tipo da conta em quest�o.
	 * @return Objeto do tipo da conta
	 */
	public TipoContaVO getTipoContaVO() {
		return tipoContaVO;
	}
	
	/**
	 * Seta o objeto do tipo da conta em quest�o.
	 * @param tipoContaVO Objeto que se deseja setar na propriedade
	 */
	public void setTipoContaVO(TipoContaVO tipoContaVO) {
		this.tipoContaVO = tipoContaVO;
	}
	
	/**
	 * Obt�m o texto da forma de identifica��o da conta de banco (Sigla_Banco Num_Ag-Dig/Num_Conta-Dig). 
	 * @return Texto padr�o de identifica��o da conta de banco
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
