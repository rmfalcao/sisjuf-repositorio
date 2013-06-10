package com.vortice.seguranca.rn;

import java.util.Collection;
import java.util.Iterator;

import org.apache.log4j.Logger;

import com.vortice.exception.AmbienteException;
import com.vortice.exception.AplicacaoException;
import com.vortice.seguranca.abstracao.rn.SegurancaRegraNegocio;
import com.vortice.seguranca.dao.PerfilDAOIf;
import com.vortice.seguranca.vo.FuncaoVO;
import com.vortice.seguranca.vo.FuncionalidadeVO;
import com.vortice.seguranca.vo.PerfilVO;
import com.vortice.seguranca.vo.UsuarioVO;

public class PerfilRN extends SegurancaRegraNegocio {
	
	private PerfilDAOIf dao;
	
	private Logger log = Logger.getLogger(PerfilRN.class);
	
	public PerfilRN() throws AmbienteException{
	}
	
	public PerfilVO insert(PerfilVO vo) throws AmbienteException, AplicacaoException{
		vo = dao.insert(vo);
		insertFuncao(vo);
		insertFuncionalidade(vo);
		return vo;
	}
	
	public void update(PerfilVO vo) throws AmbienteException, AplicacaoException{
		dao.update(vo);
		removeFuncao(vo);
		log.debug("Vai remover funcionalidades");
		removeFuncionalidade(vo);
		insertFuncao(vo);
		log.debug("Vai inserir funcionalidades " + vo.getFuncionalidades().size());
		insertFuncionalidade(vo);
	}
	
	public void remove(PerfilVO vo) throws AmbienteException, AplicacaoException{
		removeFuncao(vo);
		removeFuncionalidade(vo);
		dao.remove(vo);
	}
	
	public PerfilVO findByPrimaryKey(PerfilVO vo) throws AmbienteException, AplicacaoException{
		return dao.findByPrimaryKey(vo);
	}
	
	public Collection findByFilter(PerfilVO vo) throws AmbienteException, AplicacaoException{
		return dao.findByFilter(vo);
	}
	
	public Collection findAll() throws AmbienteException, AplicacaoException{
		return dao.findAll();
	}
	
	public void insertFuncao(PerfilVO vo) throws AmbienteException, AplicacaoException{
		Collection collFuncao = vo.getFuncoes();
		if (collFuncao != null && collFuncao.size() > 0){
			Iterator iterator = collFuncao.iterator();
			while(iterator.hasNext()){
				FuncaoVO funcao = (FuncaoVO)iterator.next();
				
			}
		}
	}
	
	public void insertFuncionalidade(PerfilVO vo) throws AmbienteException, AplicacaoException{
		Collection collFuncionalidade = vo.getFuncionalidades();
		if (collFuncionalidade != null && collFuncionalidade.size() > 0){
			Iterator iterator = collFuncionalidade.iterator();
			while(iterator.hasNext()){
				FuncionalidadeVO funcionalidade = (FuncionalidadeVO)iterator.next();
				dao.insertFuncionalidade(vo, funcionalidade);
			}
		}
	}
	
	public void removeFuncao(PerfilVO vo) throws AmbienteException, AplicacaoException{
		Collection collFuncao = vo.getFuncoes();
		if (collFuncao != null){
			Iterator iterator = collFuncao.iterator();
			while(iterator.hasNext()){
				FuncaoVO funcao = (FuncaoVO)iterator.next();
				
			}
		}
	}
	
	public void removeFuncionalidade(PerfilVO vo) throws AmbienteException, AplicacaoException{
		dao.removeFuncionalidade(vo);
	}
	
	public Collection<PerfilVO> findByUsuario(UsuarioVO usuario) throws AmbienteException, AplicacaoException{
		return dao.findByUsuario(usuario);
	}
	
	public Collection<PerfilVO> findByNUsuario(UsuarioVO usuario) throws AmbienteException, AplicacaoException{
		return dao.findByNUsuario(usuario);
	}

	public void setDao(PerfilDAOIf dao) {
		this.dao = dao;
	}
}