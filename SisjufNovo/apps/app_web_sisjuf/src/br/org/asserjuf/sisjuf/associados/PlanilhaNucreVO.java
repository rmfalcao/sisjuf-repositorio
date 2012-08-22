package br.org.asserjuf.sisjuf.associados;

import java.util.Date;

import br.com.falc.smartFW.SmartVO;

public class PlanilhaNucreVO extends SmartVO {

	private AssociadoVO associado;
	private Float		valorDebitado;
	private Float		custoConsignacao;
	private Float		valorLiquido;
	private Date		data;
	
	public AssociadoVO getAssociado() {
		return associado;
	}
	public void setAssociado(AssociadoVO associado) {
		this.associado = associado;
	}
	public Float getCustoConsignacao() {
		return custoConsignacao;
	}
	public void setCustoConsignacao(Float custoConsignacao) {
		this.custoConsignacao = custoConsignacao;
	}
	public Float getValorDebitado() {
		return valorDebitado;
	}
	public void setValorDebitado(Float valorDebitado) {
		this.valorDebitado = valorDebitado;
	}
	public Float getValorLiquido() {
		return valorLiquido;
	}
	public void setValorLiquido(Float valorLiquido) {
		this.valorLiquido = valorLiquido;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	
}
