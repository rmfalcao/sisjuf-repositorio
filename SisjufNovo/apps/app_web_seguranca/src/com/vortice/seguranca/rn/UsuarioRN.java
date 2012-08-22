package com.vortice.seguranca.rn;

import java.util.Collection;

import com.vortice.exception.AmbienteException;
import com.vortice.exception.AplicacaoException;
import com.vortice.seguranca.abstracao.rn.SegurancaRegraNegocio;
import com.vortice.seguranca.dao.FuncionalidadeDAOIf;
import com.vortice.seguranca.dao.PerfilDAOIf;
import com.vortice.seguranca.dao.UsuarioDAOIf;
import com.vortice.seguranca.vo.PerfilVO;
import com.vortice.seguranca.vo.UsuarioVO;

public class UsuarioRN extends SegurancaRegraNegocio {
	
	private UsuarioDAOIf 		dao;
	private FuncionalidadeDAOIf funcionalidadeDAO;
	private PerfilDAOIf 		perfilDAO;

	public UsuarioRN() throws AmbienteException{
	}

	public UsuarioVO insert(UsuarioVO vo) throws AmbienteException, AplicacaoException{
		vo = dao.insert(vo);
		insertPerfil(vo);
		return vo;
	}
	
	public void update(UsuarioVO vo) throws AmbienteException, AplicacaoException{
		dao.update(vo);
		removePerfil(vo);
		insertPerfil(vo);
		if (vo.getSenha() != null && !vo.getSenha().equals("")) dao.updateSenha(vo);
	}
	
	public void remove(UsuarioVO vo) throws AmbienteException, AplicacaoException{
		removePerfil(vo);
		dao.remove(vo);
	}
	
	public UsuarioVO findByPrimaryKey(UsuarioVO vo) throws AmbienteException, AplicacaoException{
		UsuarioVO usuario = dao.findByPrimaryKey(vo);
		usuario.setPerfis(perfilDAO.findByUsuario(vo));
		return usuario; 
	}
	
	public Collection findByFilter(UsuarioVO vo) throws AmbienteException, AplicacaoException{
		return dao.findByFilter(vo);
	}
	
	public UsuarioVO autenticar(UsuarioVO vo) throws AmbienteException, AplicacaoException{
		UsuarioVO usuarioVO = dao.autenticar(vo);
		if (usuarioVO != null){
			vo = usuarioVO;
			vo.setFuncionalidades(funcionalidadeDAO.findByUsuario(vo));
			vo.setPerfis(perfilDAO.findByUsuario(vo));
			return vo;
		}else{
			return null;
		}
	}
	
	private void insertPerfil(UsuarioVO usuario)throws AmbienteException, AplicacaoException{
		Collection<PerfilVO> collPerfil = usuario.getPerfis();
		if (collPerfil != null && collPerfil.size() > 0){
			for (PerfilVO perfil : collPerfil){
				dao.insertPerfil(usuario, perfil);
			}
		}
	}
	
	private void removePerfil(UsuarioVO usuario) throws AmbienteException, AplicacaoException{
		dao.removePerfil(usuario);
	}

	public void setDao(UsuarioDAOIf dao) {
		this.dao = dao;
	}

	public void setFuncionalidadeDAO(FuncionalidadeDAOIf funcionalidadeDAO) {
		this.funcionalidadeDAO = funcionalidadeDAO;
	}

	public void setPerfilDAO(PerfilDAOIf perfilDAO) {
		this.perfilDAO = perfilDAO;
	}
	
}