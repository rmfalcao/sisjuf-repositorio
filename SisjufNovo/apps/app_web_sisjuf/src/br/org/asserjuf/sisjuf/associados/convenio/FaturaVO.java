package br.org.asserjuf.sisjuf.associados.convenio;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import com.vortice.seguranca.vo.UsuarioVO;

import br.com.falc.smartFW.SmartVO;
import br.org.asserjuf.sisjuf.associados.convenio.dados.StatusFaturaVO;
import br.org.asserjuf.sisjuf.financeiro.ContaVO;

public class FaturaVO extends SmartVO {

	private Integer						codigo;
	private ConvenioVO					convenio;
	
	
	private Boolean						pago;
	private Boolean						recebido;
	
	private String 						statusPago;
	private String						statusRecebido;
	
	
	private Double						valorFatura;
	private Date						dataInicial;
	private Date						dataFinal;
	private Date						dataVencimento;
	
	private Date 						data;
	
	private Collection<ItemFaturaVO> 	itens;
	
	private StatusFaturaVO				status;
	
	private ContaVO						contaCredito;	
	private ContaVO						contaDebito;
	
	private Integer						mes;
	private Integer						ano;
	
	private UsuarioVO 					usuario;
	
	
	
	
	public UsuarioVO getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioVO usuario) {
		this.usuario = usuario;
	}

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public Integer getMes() {
		return mes;
	}

	public void setMes(Integer mes) {
		this.mes = mes;
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public ContaVO getContaCredito() {
		return contaCredito;
	}

	public void setContaCredito(ContaVO contaCredito) {
		this.contaCredito = contaCredito;
	}

	public ContaVO getContaDebito() {
		return contaDebito;
	}

	public void setContaDebito(ContaVO contaDebito) {
		this.contaDebito = contaDebito;
	}

	public String getStatusPago() {
		return statusPago;
	}
	
	public String getDescricaoStatusPago() {
		
		if (statusPago != null) {
			if (statusPago.equals("S")) {
				return "paga";
			} else if (statusPago.equals("N")) {
				return "não paga";
			}
		}
		
		return statusPago;
	}

	public void setStatusPago(String statusPago) {
		this.statusPago = statusPago;
	}

	public String getStatusRecebido() {
		return statusRecebido;
	}
	
	public String getDescricaoStatusRecebido() {
		
		if (statusRecebido!= null) {
			if (statusRecebido.equals("S")) {
				return "recebida";
			} else if (statusRecebido.equals("N")) {
				return "não recebida";
			}
		}
		
		return statusRecebido;
	}

	public void setStatusRecebido(String statusRecebido) {
		this.statusRecebido = statusRecebido;
	}

	
	public StatusFaturaVO getStatus() {
		return status;
	}

	public void setStatus(StatusFaturaVO status) {
		this.status = status;
	}

	public Collection<ItemFaturaVO> getItens() {
		return itens;
	}

	public void setItens(Collection<ItemFaturaVO> itens) {
		this.itens = itens;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public ConvenioVO getConvenio() {
		return convenio;
	}

	public void setConvenio(ConvenioVO convenio) {
		this.convenio = convenio;
	}

	public Boolean getPago() {
		return pago;
	}

	public void setPago(Boolean pago) {
		this.pago = pago;
	}

	public Boolean getRecebido() {
		return recebido;
	}

	public void setRecebido(Boolean recebido) {
		this.recebido = recebido;
	}

	public Double getValorFatura() {
		return valorFatura;
	}

	public void setValorFatura(Double valorFatura) {
		this.valorFatura = valorFatura;
	}

	public Date getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}

	public Date getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}
	
	public String getDataInicialFormatada() {
		
		if (dataInicial != null) {
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			return formatter.format(dataInicial);
		
		}
		return "";
		
	}
	
	public String getDataFinalFormatada() {
		
		if (dataFinal != null) {
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			return formatter.format(dataFinal);
		
		}
		return "";
		
	}
	
}