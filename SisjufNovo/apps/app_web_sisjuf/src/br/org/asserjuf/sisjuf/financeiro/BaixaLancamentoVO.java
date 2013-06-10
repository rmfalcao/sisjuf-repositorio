
package br.org.asserjuf.sisjuf.financeiro;

import java.io.Serializable;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import br.com.falc.smartFW.exception.SmartAppException;
import br.com.falc.smartFW.exception.SmartEnvException;
import br.org.asserjuf.sisjuf.financeiro.LancamentoVO;
import br.org.asserjuf.sisjuf.util.ParametroRN;
import br.org.asserjuf.sisjuf.util.ParametroVO;

/**
 * 
 * Congrega os dados de uma baixa de lan�amento.
 * 
 * @author Paulo Prado
 *
 */
public class BaixaLancamentoVO implements Serializable{
	
	/**
	 * C�digo sequencial interno da baixa de lan�amento.
	 */
	private Integer codigo;
	
	/**
	 * Objeto que representa o lan�amento ao qual se refere a baixa. 
	 */
	private LancamentoVO lancamentoVO;
	
	/**
	 * Data da baixa do lan�amento. 
	 */
	private Date data;
	
	/**
	 * Valor da baixa do lan�amento.
	 */
	private Double valor;
	
	private FormaPagamentoVO formaPagamentoVO;
	
	private String bancoCheque;
	
	private String agenciaCheque;
	
	private String digitoAgenciaCheque;
	
	private String contaCheque;
	
	private String digitoContaCheque;
	
	private String numeroCheque;	
	
	public String getDescricaoCompletaFormaPagamento() {
		String retorno = new String("");

		if (formaPagamentoVO != null && formaPagamentoVO.getNome() != null && !formaPagamentoVO.getNome().equals("")) {
	
			retorno += formaPagamentoVO.getNome();
			if (bancoCheque != null && !bancoCheque.equals("")) {
				retorno += " N�" + numeroCheque + " " + bancoCheque + " " + agenciaCheque;
				if (digitoAgenciaCheque != null && !digitoAgenciaCheque.equals("")) {
					retorno += "-" + digitoAgenciaCheque;

				}	
				retorno += "/" + contaCheque + "-" + digitoContaCheque;

			}	

		}

		return retorno;
	}
		
	public String getAgenciaCheque() {
		return agenciaCheque;
	}

	public void setAgenciaCheque(String agenciaCheque) {
		this.agenciaCheque = agenciaCheque;
	}

	public String getBancoCheque() {
		return bancoCheque;
	}

	public void setBancoCheque(String bancoCheque) {
		this.bancoCheque = bancoCheque;
	}

	public String getContaCheque() {
		return contaCheque;
	}

	public void setContaCheque(String contaCheque) {
		this.contaCheque = contaCheque;
	}

	public String getDigitoAgenciaCheque() {
		return digitoAgenciaCheque;
	}

	public void setDigitoAgenciaCheque(String digitoAgenciaCheque) {
		this.digitoAgenciaCheque = digitoAgenciaCheque;
	}

	public String getDigitoContaCheque() {
		return digitoContaCheque;
	}

	public void setDigitoContaCheque(String digitoContaCheque) {
		this.digitoContaCheque = digitoContaCheque;
	}

	public FormaPagamentoVO getFormaPagamentoVO() {
		return formaPagamentoVO;
	}

	public void setFormaPagamentoVO(FormaPagamentoVO formaPagamentoVO) {
		this.formaPagamentoVO = formaPagamentoVO;
	}

	public String getNumeroCheque() {
		return numeroCheque;
	}

	public void setNumeroCheque(String numeroCheque) {
		this.numeroCheque = numeroCheque;
	}

	/**
	 * Obt�m o valor formatado para exibi��o na interface.
	 * @return Valor formatado da baixa do lan�amento
	 */
	public String getValorFormatado() {
		if (valor != null) {
			NumberFormat	 numberFormatter	= NumberFormat.getInstance();
			numberFormatter.setMinimumFractionDigits(2);
			return numberFormatter.format(valor);
			
		}
		return "";

	}
	
	/**
	 * Obt�m o valor da propriedade codigo.
	 * @return C�digo sequencial interno da baixa.
	 */
	public Integer getCodigo() {
		return codigo;
	}
	
	/**
	 * Seta um valor na propriedade codigo da baixa de lan�amento. 
	 * @param codigo Valor que se deseja setar na propriedade
	 */
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	
	/**
	 * Obt�m o objeto que representa o lan�amento ao qual se refere a baixa.
	 * @return Objeto do lan�amento da baixa
	 */
	public LancamentoVO getLancamentoVO() {
		return lancamentoVO;
	}
	
	/**
	 * Seta o objeto ao qual se refere a baixa na propriedade lancamentoVO.
	 * @param lancamentoVO Valor que se deseja setar na propriedade
	 */
	public void setLancamentoVO(LancamentoVO lancamentoVO) {
		this.lancamentoVO = lancamentoVO;
	}
	
	/**
	 * Obt�m o valor da propriedade valor.
	 * @return Valor da baixa
	 */
	public Double getValor() {
		return valor;
	}
	
	/**
	 * Seta o valor da propriedade valor do lan�amento.
	 * @param valor Valor que se deseja setar na propriedade
	 */
	public void setValor(Double valor) {
		this.valor = valor;
	}

	/**
	 * Obt�m o valor da propriedade data.
	 * @return Data da baixa
	 */
	public Date getData() {
		return data;
	}
	
	/**
	 * Obt�m a data, setada na propriedade data, formatada.
	 * @return Data da baixa formatada
	 */
	public String getDataFormatada() {
		
		if (data != null) {
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			return formatter.format(data);
		
		}
		return "";
		
	}

	/**
	 * Seta o valor da  propriedade data do lan�amento.
	 * @param data Valor que se deseja setar na propriedade
	 */
	public void setData(Date data) {
		this.data = data;
	}
	
}