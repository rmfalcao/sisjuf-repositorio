package br.org.asserjuf.sisjuf.associados;

import java.util.Date;

import com.vortice.seguranca.vo.UsuarioVO;

import br.com.falc.smartFW.SmartVO;

public class DocumentoAssociadoVO extends SmartVO {

	private Integer	codigo;
	private String 	nome;
	private String	nomeDoArquivo;
	private Date 	dataCriacao;
	private Date	dataDocumento;
	private AssociadoVO	associado;
	private byte[]	fileData;
	
	private UsuarioVO usuario;
	
	
	
	
	
	public UsuarioVO getUsuario() {
		return usuario;
	}
	public void setUsuario(UsuarioVO usuario) {
		this.usuario = usuario;
	}
	public AssociadoVO getAssociado() {
		return associado;
	}
	public void setAssociado(AssociadoVO associado) {
		this.associado = associado;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Date getDataCriacao() {
		return dataCriacao;
	}
	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
	public Date getDataDocumento() {
		return dataDocumento;
	}
	public void setDataDocumento(Date dataDocumento) {
		this.dataDocumento = dataDocumento;
	}
	public byte[] getFileData() {
		return fileData;
	}
	public void setFileData(byte[] fileData) {
		this.fileData = fileData;
	}
	public Integer getCodigo() {
		return codigo;
	}
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	public String getNomeDoArquivo() {
		return nomeDoArquivo;
	}
	public void setNomeDoArquivo(String nomeDoArquivo) {
		this.nomeDoArquivo = nomeDoArquivo;
	}
	
	
	
	
}
