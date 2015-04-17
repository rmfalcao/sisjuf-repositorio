package br.com.sisjuf;

import java.util.List;

public class AssociadoModel {
	
	private String codigo;
	private String nomeServidor;
	private String cpf;
	private String consignacao;
	private String devolucao;
	private String ccf;
	private List<AssociadoModel> listaAssociados;
	private String dataAdesao;
	private String tipoCliente;
	private String valorMensalidade;
	private String valorDevolucao;
	private String valorLiquido;
	private String idade;
	private String dataNascimento;
	private String departamento;
	private String sexo;
	private String plano;
	
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getNomeServidor() {
		return nomeServidor;
	}
	public void setNomeServidor(String nomeServidor) {
		this.nomeServidor = nomeServidor;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getConsignacao() {
		return consignacao;
	}
	public void setConsignacao(String consignacao) {
		this.consignacao = consignacao;
	}
	public String getDevolucao() {
		return devolucao;
	}
	public void setDevolucao(String devolucao) {
		this.devolucao = devolucao;
	}
	public String getCcf() {
		return ccf;
	}
	public void setCcf(String ccf) {
		this.ccf = ccf;
	}
	public List<AssociadoModel> getListaAssociados() {
		return listaAssociados;
	}
	public void setListaAssociados(List<AssociadoModel> listaAssociados) {
		this.listaAssociados = listaAssociados;
	}
	public String getDataAdesao() {
		return dataAdesao;
	}
	public void setDataAdesao(String dataAdesao) {
		this.dataAdesao = dataAdesao;
	}
	public String getTipoCliente() {
		return tipoCliente;
	}
	public void setTipoCliente(String tipoCliente) {
		this.tipoCliente = tipoCliente;
	}
	public String getValorMensalidade() {
		return valorMensalidade;
	}
	public void setValorMensalidade(String valorMensalidade) {
		this.valorMensalidade = valorMensalidade;
	}
	public String getIdade() {
		return idade;
	}
	public void setIdade(String idade) {
		this.idade = idade;
	}
	public String getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public String getDepartamento() {
		return departamento;
	}
	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public String getValorDevolucao() {
		return valorDevolucao;
	}
	public void setValorDevolucao(String valorDevolucao) {
		this.valorDevolucao = valorDevolucao;
	}
	public String getValorLiquido() {
		return valorLiquido;
	}
	public void setValorLiquido(String valorLiquido) {
		this.valorLiquido = valorLiquido;
	}
	public String getPlano() {
		return plano;
	}
	public void setPlano(String plano) {
		this.plano = plano;
	}

}
