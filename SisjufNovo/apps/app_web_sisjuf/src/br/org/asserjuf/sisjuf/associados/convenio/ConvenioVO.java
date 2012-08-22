package br.org.asserjuf.sisjuf.associados.convenio;

import java.util.List;

import br.com.falc.smartFW.SmartVO;
import br.org.asserjuf.sisjuf.entidadesComuns.EstadoVO;

public class ConvenioVO extends SmartVO {

	private Integer				codigo;
	private String				categoria;
	private String				nomeFantasia;
	private String				razaoSocial;
	private String				cnpj;
	private String				logradouro;
	private String				complemento;
	private String				bairro;
	private String				cidade;
	private EstadoVO			estado;
	private AtividadeConvenioVO	atividadeConvenio;
	private String				cep;
	private String				nomeContato;
	private String				telefone;
	private String				telefoneAdicional;
	private String				fax;
	private String				email;
	private String				beneficio;
	private String				descricao;
	private Short				diaFechamento;
	private Short				mesVencimento;
	private Short				diaVencimento;
	
	private String				conjugue;
	private String				filhos;
	private String				dependentes;
	private String				outrosBeneficaiveis;
	private String				titular;
	
	
	
	private String				desativado;
	
	private String				statusGeraArquivoFatura;

	private List<BeneficiarioVO> beneficiarios;
	
	
	
	public String getStatusGeraArquivoFatura() {
		return statusGeraArquivoFatura;
	}

	public void setStatusGeraArquivoFatura(String statusGeraArquivoFatura) {
		this.statusGeraArquivoFatura = statusGeraArquivoFatura;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
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

	public EstadoVO getEstado() {
		return estado;
	}

	public void setEstado(EstadoVO estado) {
		this.estado = estado;
	}

	public AtividadeConvenioVO getAtividadeConvenio() {
		return atividadeConvenio;
	}

	public void setAtividadeConvenio(AtividadeConvenioVO atividadeConvenio) {
		this.atividadeConvenio = atividadeConvenio;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getNomeContato() {
		return nomeContato;
	}

	public void setNomeContato(String nomeContato) {
		this.nomeContato = nomeContato;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getTelefoneAdicional() {
		return telefoneAdicional;
	}

	public void setTelefoneAdicional(String telefoneAdicional) {
		this.telefoneAdicional = telefoneAdicional;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBeneficio() {
		return beneficio;
	}

	public void setBeneficio(String beneficio) {
		this.beneficio = beneficio;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Short getDiaFechamento() {
		return diaFechamento;
	}

	public void setDiaFechamento(Short diaFechamento) {
		this.diaFechamento = diaFechamento;
	}

	public Short getMesVencimento() {
		return mesVencimento;
	}

	public void setMesVencimento(Short mesVencimento) {
		this.mesVencimento = mesVencimento;
	}

	public Short getDiaVencimento() {
		return diaVencimento;
	}

	public void setDiaVencimento(Short diaVencimento) {
		this.diaVencimento = diaVencimento;
	}

	public String getDesativado() {
		return desativado;
	}

	public void setDesativado(String desativado) {
		this.desativado = desativado;
	}

	public String getConjugue() {
		return conjugue;
	}

	public void setConjugue(String conjugue) {
		this.conjugue = conjugue;
	}

	public String getFilhos() {
		return filhos;
	}

	public void setFilhos(String filhos) {
		this.filhos = filhos;
	}

	public String getDependentes() {
		return dependentes;
	}

	public void setDependentes(String dependentes) {
		this.dependentes = dependentes;
	}

	public String getOutrosBeneficaiveis() {
		return outrosBeneficaiveis;
	}

	public void setOutrosBeneficaiveis(String outrosBeneficaiveis) {
		this.outrosBeneficaiveis = outrosBeneficaiveis;
	}

	public String getTitular() {
		return titular;
	}

	public void setTitular(String titular) {
		this.titular = titular;
	}

	public void setBeneficiarios(List<BeneficiarioVO> beneficiarios) {
		this.beneficiarios = beneficiarios;
	}

	public List<BeneficiarioVO> getBeneficiarios() {
		return beneficiarios;
	}
}