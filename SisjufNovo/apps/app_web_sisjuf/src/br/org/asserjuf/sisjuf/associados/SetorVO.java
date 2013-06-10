package br.org.asserjuf.sisjuf.associados;

import br.com.falc.smartFW.SmartVO;
import br.org.asserjuf.sisjuf.entidadesComuns.EnderecoVO;

public class SetorVO extends SmartVO {

	private Integer			codigo;
	private String			nome;
	private EnderecoVO	endereco;
	private Long				telefone;
	private Integer			ramal;
	private OrgaoVO		orgao;
	
	
	public Integer getCodigo() {
		return codigo;
	}
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	public EnderecoVO getEndereco() {
		return endereco;
	}
	public void setEndereco(EnderecoVO endereco) {
		this.endereco = endereco;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public OrgaoVO getOrgao() {
		return orgao;
	}
	public void setOrgao(OrgaoVO orgao) {
		this.orgao = orgao;
	}
	public Long getTelefone() {
		return telefone;
	}
	public void setTelefone(Long telefone) {
		this.telefone = telefone;
	}
	public Integer getRamal() {
		return ramal;
	}
	public void setRamal(Integer ramal) {
		this.ramal = ramal;
	}
	
}
