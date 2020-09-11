package br.org.asserjuf.sisjuf.associados;

import java.util.Collection;
import java.util.Date;

import com.vortice.seguranca.vo.UsuarioVO;

import br.org.asserjuf.sisjuf.associados.convenio.OutroBeneficiavelVO;
import br.org.asserjuf.sisjuf.associados.convenio.VinculacaoPlanoVO;
import br.org.asserjuf.sisjuf.entidadesComuns.EnderecoVO;
import br.org.asserjuf.sisjuf.entidadesComuns.MunicipioVO;
import br.org.asserjuf.sisjuf.financeiro.ContaAssociadoVO;

public class AssociadoVO extends PessoaVO {

	private EnderecoVO 						endereco;
	private Long							telefoneResidencial;
	private Long							telefoneComercial;
	private Long							telefoneCelular;
	private String							email;
	private Long							rg;
	private Long							cpf;
	private MunicipioVO						naturalidade;
	private String 							estadoCivil;
	private ProfissaoVO						profissao;
	private String							grupoSanguineo;
	private String							fatorRh;
	private String							nomePai;
	private String							nomeMae;
//	private String							nomeConjuge;
//	private String							cpfConjugue;
//	private String							rgConjugue;
	private Date 							dataNascimentoConjugue = new Date();
	private String 							sexoConjugue;
	private String							statusJustica;
	private SetorVO							setor;
	private Short							ramalSetor;
	private String							statusRecebeJornal;
	private String							statusCategoria;
	private AssociadoVO						contribuinte;
	private Float							valorMensalidade;
	private ContaAssociadoVO 				conta;
	private Byte							numeroCalcado;
	private Short							idade;
	private Date							proximoAniversario;
	private Date							dataAssociacao;
	private String							matriculaJustica;
	private String							statusPreCadastro;
	
	private Collection<DependenteVO> 		dependentes;
	private Collection<FilhoVO>				filhos;
	
	private HistoricoAssociadoVO			ultimoEvento;
	private Collection<OutroBeneficiavelVO> beneficiarios;
	
	private Collection<VinculacaoPlanoVO> 	vinculacoes;
	
	private ConjugeVO						conjuge;

	private UsuarioVO						usuario;
	private UsuarioVO						usuarioAlteracao;
	
	private Date							dataCadastro;
	private Date							dataAlteracao;
	
	
	
	public UsuarioVO getUsuarioAlteracao() {
		return usuarioAlteracao;
	}
	public void setUsuarioAlteracao(UsuarioVO usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}
	public Date getDataCadastro() {
		return dataCadastro;
	}
	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	public Date getDataAlteracao() {
		return dataAlteracao;
	}
	public void setDataAlteracao(Date dataAlteracao) {
		this.dataAlteracao = dataAlteracao;
	}
	public UsuarioVO getUsuario() {
		return usuario;
	}
	public void setUsuario(UsuarioVO usuario) {
		this.usuario = usuario;
	}
	public ConjugeVO getConjuge() {
		return conjuge;
	}
	public void setConjuge(ConjugeVO conjuge) {
		this.conjuge = conjuge;
	}
	public HistoricoAssociadoVO getUltimoEvento() {
		return ultimoEvento;
	}
	public void setUltimoEvento(HistoricoAssociadoVO ultimoEvento) {
		this.ultimoEvento = ultimoEvento;
	}
	public ContaAssociadoVO getConta() {
		return conta;
	}
	public void setConta(ContaAssociadoVO conta) {
		this.conta = conta;
	}
	public AssociadoVO getContribuinte() {
		return contribuinte;
	}
	public void setContribuinte(AssociadoVO contribuinte) {
		this.contribuinte = contribuinte;
	}
	public Long getCpf() {
		return cpf;
	}
	public void setCpf(Long cpf) {
		this.cpf = cpf;
	}
	public Collection<DependenteVO> getDependentes() {
		return dependentes;
	}
	public void setDependentes(Collection<DependenteVO> dependentes) {
		this.dependentes = dependentes;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public EnderecoVO getEndereco() {
		return endereco;
	}
	public void setEndereco(EnderecoVO endereco) {
		this.endereco = endereco;
	}
	public String getEstadoCivil() {
		return estadoCivil;
	}
	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}
	public String getFatorRh() {
		return fatorRh;
	}
	public void setFatorRh(String fatorRh) {
		this.fatorRh = fatorRh;
	}
	public Collection<FilhoVO> getFilhos() {
		return filhos;
	}
	public void setFilhos(Collection<FilhoVO> filhos) {
		this.filhos = filhos;
	}
	public String getGrupoSanguineo() {
		return grupoSanguineo;
	}
	public void setGrupoSanguineo(String grupoSanguineo) {
		this.grupoSanguineo = grupoSanguineo;
	}
	public MunicipioVO getNaturalidade() {
		return naturalidade;
	}
	public void setNaturalidade(MunicipioVO naturalidade) {
		this.naturalidade = naturalidade;
	}
	public Byte getNumeroCalcado() {
		return numeroCalcado;
	}
	public void setNumeroCalcado(Byte numeroCalcado) {
		this.numeroCalcado = numeroCalcado;
	}
//	public String getNomeConjuge() {
//		return nomeConjuge;
//	}
//	public void setNomeConjuge(String nomeConjuge) {
//		this.nomeConjuge = nomeConjuge;
//	}
	public String getNomeMae() {
		return nomeMae;
	}
	public void setNomeMae(String nomeMae) {
		this.nomeMae = nomeMae;
	}
	public String getNomePai() {
		return nomePai;
	}
	public void setNomePai(String nomePai) {
		this.nomePai = nomePai;
	}
	public Short getRamalSetor() {
		return ramalSetor;
	}
	public void setRamalSetor(Short ramalSetor) {
		this.ramalSetor = ramalSetor;
	}
	public Long getRg() {
		return rg;
	}
	public void setRg(Long rg) {
		this.rg = rg;
	}
	public SetorVO getSetor() {
		return setor;
	}
	public void setSetor(SetorVO setor) {
		this.setor = setor;
	}
	public String getStatusCategoria() {
		return statusCategoria;
	}
	public void setStatusCategoria(String statusCategoria) {
		this.statusCategoria = statusCategoria;
	}
	public String getStatusJustica() {
		return statusJustica;
	}
	public void setStatusJustica(String statusJustica) {
		this.statusJustica = statusJustica;
	}
	public String getStatusRecebeJornal() {
		return statusRecebeJornal;
	}
	public void setStatusRecebeJornal(String statusRecebeJornal) {
		this.statusRecebeJornal = statusRecebeJornal;
	}
	public Long getTelefoneCelular() {
		return telefoneCelular;
	}
	public void setTelefoneCelular(Long telefoneCelular) {
		this.telefoneCelular = telefoneCelular;
	}
	public Long getTelefoneComercial() {
		return telefoneComercial;
	}
	public void setTelefoneComercial(Long telefoneComercial) {
		this.telefoneComercial = telefoneComercial;
	}
	public Long getTelefoneResidencial() {
		return telefoneResidencial;
	}
	public void setTelefoneResidencial(Long telefoneResidencial) {
		this.telefoneResidencial = telefoneResidencial;
	}
	public Float getValorMensalidade() {
		return valorMensalidade;
	}
	public void setValorMensalidade(Float valorMensalidade) {
		this.valorMensalidade = valorMensalidade;
	}
	public Short getIdade() {
		return idade;
	}
	public void setIdade(Short idade) {
		this.idade = idade;
	}
	public Date getProximoAniversario() {
		return proximoAniversario;
	}
	public void setProximoAniversario(Date proximoAniversario) {
		this.proximoAniversario = proximoAniversario;
	}
	public ProfissaoVO getProfissao() {
		return profissao;
	}
	public void setProfissao(ProfissaoVO profissao) {
		this.profissao = profissao;
	}
	public Date getDataAssociacao() {
		return dataAssociacao;
	}
	public void setDataAssociacao(Date dataAssociacao) {
		this.dataAssociacao = dataAssociacao;
	}
	public String getMatriculaJustica() {
		return matriculaJustica;
	}
	public void setMatriculaJustica(String matriculaJustica) {
		this.matriculaJustica = matriculaJustica;
	}
	public String getStatusPreCadastro() {
		return statusPreCadastro;
	}
	public void setStatusPreCadastro(String statusPreCadastro) {
		this.statusPreCadastro = statusPreCadastro;
	}
//	public void setRgConjugue(String rgConjuge) {
//		this.rgConjugue = rgConjuge;
//	}
//	public String getRgConjugue() {
//		return rgConjugue;
//	}
//	public void setCpfConjugue(String cpfConjuge) {
//		this.cpfConjugue = cpfConjuge;
//	}
//	public String getCpfConjugue() {
//		return cpfConjugue;
//	}
	public void setDataNascimentoConjugue(Date dataNascimentoConjuge) {
		this.dataNascimentoConjugue = dataNascimentoConjuge;
	}
	public Date getDataNascimentoConjugue() {
		return dataNascimentoConjugue;
	}
	public void setSexoConjugue(String sexoConjuge) {
		this.sexoConjugue = sexoConjuge;
	}
	public String getSexoConjugue() {
		return sexoConjugue;
	}
	public Collection<VinculacaoPlanoVO> getVinculacoes() {
		return vinculacoes;
	}
	public void setVinculacoes(Collection<VinculacaoPlanoVO> vinculacoes) {
		this.vinculacoes = vinculacoes;
	}
	public Collection<OutroBeneficiavelVO> getBeneficiarios() {
		return beneficiarios;
	}
	public void setBeneficiarios(Collection<OutroBeneficiavelVO> beneficiarios) {
		this.beneficiarios = beneficiarios;
	}
	
}