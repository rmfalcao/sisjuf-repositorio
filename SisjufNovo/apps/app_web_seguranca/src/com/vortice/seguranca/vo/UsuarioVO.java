package com.vortice.seguranca.vo;

import java.util.Collection;

import com.vortice.abstracao.ValueObject;

public class UsuarioVO extends ValueObject {
		
	public UsuarioVO(){}
	
	public UsuarioVO(Integer codigo){
		this.codigo = codigo;
	}
	
    private Integer codigo;

    private String login;

    private String senha;
    
    private String nome;

    private String email;
    
    private Collection<PerfilVO> perfis;

    private Collection funcionalidades;
    
    private Collection funcoes;

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Collection getFuncionalidades() {
		return funcionalidades;
	}

	public void setFuncionalidades(Collection funcionalidades) {
		this.funcionalidades = funcionalidades;
	}

	public Collection getFuncoes() {
		return funcoes;
	}

	public void setFuncoes(Collection funcoes) {
		this.funcoes = funcoes;
	}

	public boolean equals(Object obj) {
		if (obj instanceof UsuarioVO){
			UsuarioVO usuario = (UsuarioVO)obj;
			if (this.getCodigo() != null && this.getCodigo().equals(usuario.getCodigo()))
				return true;
		}
		return false;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Collection<PerfilVO> getPerfis() {
		return perfis;
	}

	public void setPerfis(Collection<PerfilVO> perfis) {
		this.perfis = perfis;
	}
	
}