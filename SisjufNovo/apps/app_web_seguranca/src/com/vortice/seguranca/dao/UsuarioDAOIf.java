package com.vortice.seguranca.dao;

import java.util.Collection;

import com.vortice.exception.AmbienteException;
import com.vortice.exception.AplicacaoException;
import com.vortice.seguranca.vo.PerfilVO;
import com.vortice.seguranca.vo.UsuarioVO;

public interface UsuarioDAOIf {
	
	public UsuarioVO insert(UsuarioVO vo) throws AmbienteException, AplicacaoException;
	
	public void update(UsuarioVO vo) throws AmbienteException, AplicacaoException;
	
	public void updateSenha(UsuarioVO vo)throws AmbienteException, AplicacaoException;
	
	public void remove(UsuarioVO vo) throws AmbienteException, AplicacaoException;
	
	public UsuarioVO findByPrimaryKey(UsuarioVO vo) throws AmbienteException;
	
	public Collection findByFilter(UsuarioVO vo) throws AmbienteException;
	
	public UsuarioVO autenticar(UsuarioVO vo) throws AmbienteException, AplicacaoException;
	
	public UsuarioVO findByLogin(UsuarioVO vo) throws AmbienteException;
	
	public void insertPerfil(UsuarioVO usuario, PerfilVO perfil) throws AmbienteException, AplicacaoException;
	
	public void removePerfil(UsuarioVO usuario) throws AmbienteException, AplicacaoException;
}
