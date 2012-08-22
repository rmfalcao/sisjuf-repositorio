package com.vortice.seguranca.rn;

import java.rmi.RemoteException;
import java.util.Collection;

import com.vortice.exception.AmbienteException;
import com.vortice.exception.AplicacaoException;
import com.vortice.seguranca.abstracao.rn.SegurancaRegraNegocio;
import com.vortice.seguranca.dao.FuncaoDAOIf;
import com.vortice.seguranca.vo.FuncaoVO;
import com.vortice.seguranca.vo.FuncionalidadeVO;
import com.vortice.seguranca.vo.UsuarioVO;

public class FuncaoRN extends SegurancaRegraNegocio {
	
	private FuncaoDAOIf dao;
	
	public FuncaoRN() throws AmbienteException{
	}
	
	public FuncaoVO insert(FuncaoVO vo) throws AmbienteException, AplicacaoException{
		return dao.insert(vo);
	}
	
	public void update(FuncaoVO vo) throws AmbienteException, AplicacaoException{
		dao.update(vo);
	}
	
	public void remove(FuncaoVO vo) throws AmbienteException, AplicacaoException{
		dao.remove(vo);
	}
	
	public FuncaoVO findByPrimaryKey(FuncaoVO vo) throws AmbienteException, AplicacaoException{
		return dao.findByPrimaryKey(vo);
	}
	
	public Collection findAll() throws AmbienteException, AplicacaoException{
		return dao.findAll();
	}
	
	public Collection findByFilter(FuncaoVO vo) throws AmbienteException, AplicacaoException{
		return dao.findByFilter(vo);
	}
	
	public Collection findByUsuario(UsuarioVO vo) throws AmbienteException, AplicacaoException{
		return dao.findByUsuario(vo);
	}
	
	public Collection findByFuncionalidade(FuncionalidadeVO vo) throws AmbienteException, AplicacaoException{
		return dao.findByFuncionalidade(vo);
	}
	
	public Collection findByNFuncionalidade(FuncionalidadeVO vo) throws AmbienteException, AplicacaoException{
		return dao.findByNFuncionalidade(vo);
	}

	public void setDao(FuncaoDAOIf dao) {
		this.dao = dao;
	}
	
}