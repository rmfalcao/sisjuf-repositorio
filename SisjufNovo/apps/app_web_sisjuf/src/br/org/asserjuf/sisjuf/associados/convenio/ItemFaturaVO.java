package br.org.asserjuf.sisjuf.associados.convenio;

import br.com.falc.smartFW.SmartVO;


public class ItemFaturaVO extends SmartVO {

	private Integer				codigo;	
	private VinculacaoPlanoVO 	vinculacao;
	private FaturaVO			fatura;
	private Double				valor;
	
	private Integer				numero;
	
	private BeneficiarioVO 	beneficiario;
	private PlanoConvenioVO plano;

	public Integer getNumero() {
		return numero;
	}
	public void setNumero(Integer numero) {
		this.numero = numero;
	}
	
	public VinculacaoPlanoVO getVinculacao() {
		return vinculacao;
	}
	public void setVinculacao(VinculacaoPlanoVO vinculacao) {
		this.vinculacao = vinculacao;
	}
	
	public Integer getCodigo() {
		return codigo;
	}
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	public BeneficiarioVO getBeneficiario() {
		return beneficiario;
	}
	public void setBeneficiario(BeneficiarioVO beneficiario) {
		this.beneficiario = beneficiario;
	}
	public PlanoConvenioVO getPlano() {
		return plano;
	}
	public void setPlano(PlanoConvenioVO plano) {
		this.plano = plano;
	}
	public FaturaVO getFatura() {
		return fatura;
	}
	public void setFatura(FaturaVO fatura) {
		this.fatura = fatura;
	}
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof ItemFaturaVO){
			ItemFaturaVO item = (ItemFaturaVO)obj;
			if (item.getNumero().equals(numero)) return true;
			else return false;
		}
		return false;
	}
	@Override
	public int hashCode() {
		return numero.hashCode();
	}
}
