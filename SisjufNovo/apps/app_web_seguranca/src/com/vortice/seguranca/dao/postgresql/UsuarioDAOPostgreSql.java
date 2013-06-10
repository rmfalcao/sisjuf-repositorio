package com.vortice.seguranca.dao.postgresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Collection;

import com.vortice.exception.AmbienteException;
import com.vortice.exception.AplicacaoException;
import com.vortice.seguranca.abstracao.persistencia.SegurancaPostgresSQL;
import com.vortice.seguranca.dao.UsuarioDAOIf;
import com.vortice.seguranca.vo.PerfilVO;
import com.vortice.seguranca.vo.UsuarioVO;

public class UsuarioDAOPostgreSql extends SegurancaPostgresSQL implements UsuarioDAOIf{

	public UsuarioVO insert(UsuarioVO vo) throws AmbienteException, AplicacaoException {
		String sql = new StringBuffer("INSERT INTO USUARIO (SEQ_USUARIO, LOGIN, SENHA, NOME) VALUES (?, ?, ?, ?)")
		.toString();
		Connection conn = null;
		PreparedStatement stmt= null;
		try{
			conn = getConexao().getConexao();
			vo.setCodigo(new Integer(getSequence("SEQ_USUARIO").intValue()));
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, vo.getCodigo());
			stmt.setString(2, vo.getLogin());
			stmt.setString(3, vo.getSenha());
			stmt.setString(4, vo.getNome());
			stmt.execute();
			return vo;
		}catch(SQLException sqlEx){
			tratarExcessaoUnique(sqlEx);
			throw new AmbienteException(sqlEx);
		}finally{
			this.fechar(conn, stmt, null);
		}
	}

	public void update(UsuarioVO vo) throws AmbienteException, AplicacaoException {
		String sql = new StringBuffer("UPDATE USUARIO SET LOGIN=?, NOME=? WHERE SEQ_USUARIO=?").toString();
		Connection conn = null;
		PreparedStatement stmt= null;
		try{
			conn = getConexao().getConexao();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, vo.getLogin());
			stmt.setString(2, vo.getNome());
			stmt.setInt(3, vo.getCodigo());
			stmt.execute();
		}catch(SQLException sqlEx){
			tratarExcessaoUnique(sqlEx);
			throw new AmbienteException(sqlEx);
		}finally{
			this.fechar(conn, stmt, null);
		}
	}
	
	public void updateSenha(UsuarioVO vo)throws AmbienteException, AplicacaoException {
		String sql = new StringBuffer("UPDATE USUARIO SET SENHA=? WHERE SEQ_USUARIO=?").toString();
		Connection conn = null;
		PreparedStatement stmt= null;
		try{
			conn = getConexao().getConexao();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, vo.getSenha());
			stmt.setInt(2, vo.getCodigo());
			stmt.execute();
		}catch(SQLException sqlEx){
			throw new AmbienteException(sqlEx);
		}finally{
			this.fechar(conn, stmt, null);
		}
	}
	
	public void remove(UsuarioVO vo) throws AmbienteException, AplicacaoException {
		String sql = new StringBuffer("DELETE FROM USUARIO WHERE SEQ_USUARIO=?").toString();
		Connection conn = null;
		PreparedStatement stmt= null;
		try{
			conn = getConexao().getConexao();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, vo.getCodigo());
			stmt.execute();
		}catch(SQLException sqlEx){
			tratarExcessaoRemove(sqlEx);
			throw new AmbienteException(sqlEx);
		}finally{
			this.fechar(conn, stmt, null);
		}
	}
	
	public UsuarioVO findByPrimaryKey(UsuarioVO vo) throws AmbienteException{
		String sql = new StringBuffer("SELECT SEQ_USUARIO, LOGIN, SENHA, NOME FROM USUARIO WHERE SEQ_USUARIO=?").toString();
		Connection conn = null;
		PreparedStatement stmt= null;
		ResultSet rs = null;
		try{
			conn = getConexao().getConexao();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, vo.getCodigo().intValue());
			rs = stmt.executeQuery();
			if (rs.next()){
				UsuarioVO usuarioVO = new UsuarioVO();
				usuarioVO.setCodigo(new Integer(rs.getInt("SEQ_USUARIO")));
				usuarioVO.setLogin(rs.getString("LOGIN"));
				usuarioVO.setSenha(rs.getString("SENHA"));
				usuarioVO.setNome(rs.getString("NOME"));
				return usuarioVO;
			}else{
				return null;
			}
		}catch(SQLException sqlEx){
			throw new AmbienteException(sqlEx);
		}finally{
			this.fechar(conn, stmt, rs);
		}
	}
	
	
	public Collection findByFilter(UsuarioVO vo) throws AmbienteException{
		String sql = new StringBuffer("SELECT DISTINCT U.SEQ_USUARIO, U.LOGIN, U.NOME FROM USUARIO AS U  ")
		.append("LEFT JOIN USUARIO_PERFIL AS UP ON U.SEQ_USUARIO=UP.SEQ_USUARIO ")
		.append("WHERE (? IS NULL OR UPPER(U.LOGIN) LIKE '%' || UPPER(?) || '%') AND (? IS NULL OR UPPER(U.NOME) LIKE '%' || UPPER(?) || '%') ")
		.append("AND (? IS NULL OR UP.SEQ_PERFIL=?)")
		.append("ORDER BY U.LOGIN").toString();
		Connection conn = null;
		PreparedStatement stmt= null;
		ResultSet rs = null;
		Collection<UsuarioVO> collUsuario = new ArrayList<UsuarioVO>();
		Collection<PerfilVO> collPerfil = vo.getPerfis();
		Integer codigoPerfil = null;
		if (collPerfil != null && collPerfil.size() > 0){
			for (PerfilVO perfil : collPerfil){
				codigoPerfil = perfil.getCodigo();
			}
		}
		try{
			conn = getConexao().getConexao();
			stmt = conn.prepareStatement(sql);
			if (vo.getLogin() != null){
				stmt.setString(1, vo.getLogin());
				stmt.setString(2, vo.getLogin());
			}else{
				stmt.setNull(1, Types.VARCHAR);
				stmt.setNull(2, Types.VARCHAR);
			}
			if (vo.getNome() != null){
				stmt.setString(3, vo.getNome());
				stmt.setString(4, vo.getNome());
			}else{
				stmt.setNull(3, Types.VARCHAR);
				stmt.setNull(4, Types.VARCHAR);
			}
			if (codigoPerfil != null){
				stmt.setInt(5, codigoPerfil);
				stmt.setInt(6, codigoPerfil);
			}else{
				stmt.setNull(5, Types.INTEGER);
				stmt.setNull(6, Types.INTEGER);
			}
			rs = stmt.executeQuery();
			while(rs.next()){
				UsuarioVO usuarioVO = new UsuarioVO();
				usuarioVO.setCodigo(new Integer(rs.getInt("SEQ_USUARIO")));
				usuarioVO.setLogin(rs.getString("LOGIN"));
				usuarioVO.setNome(rs.getString("NOME"));
				collUsuario.add(usuarioVO);
			}
			return collUsuario;
		}catch(SQLException sqlEx){
			throw new AmbienteException(sqlEx);
		}finally{
			this.fechar(conn, stmt, rs);
		}
	}
	
	public UsuarioVO autenticar(UsuarioVO vo) throws AmbienteException, AplicacaoException{
		String sql = new StringBuffer("SELECT U.SEQ_USUARIO, U.LOGIN, U.NOME FROM USUARIO AS U WHERE U.LOGIN=? AND U.SENHA=?").toString();
		Connection conn = null;
		PreparedStatement stmt= null;
		ResultSet rs = null;
		try{
			conn = getConexao().getConexao();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, vo.getLogin());
			stmt.setString(2, vo.getSenha());
			rs = stmt.executeQuery();
			if(rs.next()){
				UsuarioVO usuarioVO = new UsuarioVO();
				usuarioVO.setCodigo(new Integer(rs.getInt("SEQ_USUARIO")));
				usuarioVO.setLogin(rs.getString("LOGIN"));
				usuarioVO.setNome(rs.getString("NOME"));
				return usuarioVO;
			}else{
				return null;
			}
		}catch(SQLException sqlEx){
			throw new AmbienteException(sqlEx);
		}finally{
			this.fechar(conn, stmt, rs);
		}
	}
	
	public UsuarioVO findByLogin(UsuarioVO vo) throws AmbienteException{
		String sql = new StringBuffer("SELECT U.SEQ_USUARIO, U.LOGIN, U.SENHA FROM USUARIO AS U  ")
		.append("WHERE U.LOGIN=?").toString();
		Connection conn = null;
		PreparedStatement stmt= null;
		ResultSet rs = null;
		try{
			conn = getConexao().getConexao();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, vo.getLogin());
			rs = stmt.executeQuery();
			if(rs.next()){
				UsuarioVO usuarioVO = new UsuarioVO();
				usuarioVO.setCodigo(new Integer(rs.getInt("SEQ_USUARIO")));
				usuarioVO.setLogin(rs.getString("LOGIN"));
				usuarioVO.setSenha(rs.getString("SENHA"));
				return usuarioVO;
			}else{
				return null;
			}
		}catch(SQLException sqlEx){
			throw new AmbienteException(sqlEx);
		}finally{
			this.fechar(conn, stmt, rs);
		}
	}
	
	public void insertPerfil(UsuarioVO usuario, PerfilVO perfil) throws AmbienteException, AplicacaoException {
		String sql = new StringBuffer("INSERT INTO USUARIO_PERFIL (SEQ_USUARIO, SEQ_PERFIL) VALUES (?, ?)").toString();
		Connection conn = null;
		PreparedStatement stmt= null;
		try{
			conn = getConexao().getConexao();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, usuario.getCodigo());
			stmt.setInt(2, perfil.getCodigo());
			stmt.execute();
		}catch(SQLException sqlEx){
			throw new AmbienteException(sqlEx);
		}finally{
			this.fechar(conn, stmt, null);
		}
	}
	
	public void removePerfil(UsuarioVO usuario) throws AmbienteException, AplicacaoException {
		String sql = new StringBuffer("DELETE FROM USUARIO_PERFIL WHERE SEQ_USUARIO=?").toString();
		Connection conn = null;
		PreparedStatement stmt= null;
		try{
			conn = getConexao().getConexao();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, usuario.getCodigo());
			stmt.execute();
		}catch(SQLException sqlEx){
			throw new AmbienteException(sqlEx);
		}finally{
			this.fechar(conn, stmt, null);
		}
	}
}
