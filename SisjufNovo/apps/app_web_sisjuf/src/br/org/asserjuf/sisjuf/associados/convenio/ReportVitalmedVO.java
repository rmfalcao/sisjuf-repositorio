package br.org.asserjuf.sisjuf.associados.convenio;

import java.util.Date;

import br.com.falc.smartFW.SmartVO;

public class ReportVitalmedVO extends SmartVO {
	
	private String 	codigoPessoaSisjuf;
	private String 	nome;
	private String 	titular;
	private String 	cpf;
	private String 	sexo;
	private String 	estadoCivil;
	private Date 	nascimento;
	private String	movimentacao;
	private String 	endereco;
	private String 	bairro;
	private String 	cidade;
	private String	uf;
	private String	cep;
	private String	telefone1;
	private String	telefone2;
	private String	estadoVitalmed;
	private String 	codigoBairroVitalmed;
	private String	matriculaVitalmed;
	
	
	private Date	dataCadastro;
	private Date	dataFiltroDe;
	private Date	dataFiltroAte;
	

	public String getMatriculaVitalmed() {
		return matriculaVitalmed;
	}
	public void setMatriculaVitalmed(String matriculaVitalmed) {
		this.matriculaVitalmed = matriculaVitalmed;
	}
	
	
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getUf() {
		return uf;
	}
	public void setUf(String uf) {
		this.uf = uf;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getTelefone1() {
		return telefone1;
	}
	public void setTelefone1(String telefone1) {
		this.telefone1 = telefone1;
	}
	public String getTelefone2() {
		return telefone2;
	}
	public void setTelefone2(String telefone2) {
		this.telefone2 = telefone2;
	}
	public String getEstadoVitalmed() {
		return estadoVitalmed;
	}
	public void setEstadoVitalmed(String estadoVitalmed) {
		this.estadoVitalmed = estadoVitalmed;
	}
	public String getCodigoBairroVitalmed() {
		return codigoBairroVitalmed;
	}
	public void setCodigoBairroVitalmed(String codigoBairroVitalmed) {
		this.codigoBairroVitalmed = codigoBairroVitalmed;
	}
	public String getCodigoPessoaSisjuf() {
		return codigoPessoaSisjuf;
	}
	public void setCodigoPessoaSisjuf(String codigoPessoaSisjuf) {
		this.codigoPessoaSisjuf = codigoPessoaSisjuf;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getTitular() {
		return titular;
	}
	public void setTitular(String titular) {
		this.titular = titular;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public String getEstadoCivil() {
		return estadoCivil;
	}
	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}
	public Date getNascimento() {
		return nascimento;
	}
	public void setNascimento(Date nascimento) {
		this.nascimento = nascimento;
	}
	public String getMovimentacao() {
		return movimentacao;
	}
	public void setMovimentacao(String movimentacao) {
		this.movimentacao = movimentacao;
	}
	public Date getDataCadastro() {
		return dataCadastro;
	}
	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	public Date getDataFiltroDe() {
		return dataFiltroDe;
	}
	public void setDataFiltroDe(Date dataFiltroDe) {
		this.dataFiltroDe = dataFiltroDe;
	}
	public Date getDataFiltroAte() {
		return dataFiltroAte;
	}
	public void setDataFiltroAte(Date dataFiltroAte) {
		this.dataFiltroAte = dataFiltroAte;
	}
	

	
	
}
