package com.vortice.seguranca.dao.postgresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.log4j.Logger;

import com.vortice.exception.AmbienteException;
import com.vortice.exception.AplicacaoException;
import com.vortice.seguranca.abstracao.persistencia.SegurancaPostgresSQL;
import com.vortice.seguranca.dao.FuncaoDAOIf;
import com.vortice.seguranca.vo.FuncaoVO;
import com.vortice.seguranca.vo.FuncionalidadeVO;
import com.vortice.seguranca.vo.LinkVO;
import com.vortice.seguranca.vo.UsuarioVO;

public class FuncaoDAOPostgreSql extends SegurancaPostgresSQL implements FuncaoDAOIf {
	
	private Logger LOG = Logger.getLogger(FuncaoDAOPostgreSql.class);
	
	public FuncaoVO insert(FuncaoVO vo) throws AmbienteException, AplicacaoException {
		String sql = new StringBuffer("INSERT INTO FUNCAO (SEQ_FUNCAO, SEQ_LINK, NOME, DESCRICAO) VALUES ")
		.append("(?, ?, ?, ?)").toString();
		Connection conn = null;
		PreparedStatement stmt = null;
		try{
			conn = getConexao().getConexao();
			vo.setCodigo(new Integer(getSequence("SEQ_FUNCAO").intValue()));
			LOG.debug("Funcao.getCodigo> " + vo.getCodigo());
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, vo.getCodigo().intValue());
			if (vo.getLink() != null && vo.getLink().getCodigo() != null){
				stmt.setInt(2, vo.getLink().getCodigo().intValue());
			}else{
				stmt.setNull(2, Types.INTEGER);
			}
			LOG.debug("Funcao.getLink().getCodigo()> " + vo.getLink().getCodigo());
			stmt.setString(3, vo.getNome());
			stmt.setString(4, vo.getDescricao());
			stmt.execute();
			return vo;
		}catch(SQLException sqlEx){
			throw new AmbienteException(sqlEx);
		}finally{
			this.fechar(conn, stmt, null);
		}
	}

	public void update(FuncaoVO vo) throws AmbienteException, AplicacaoException {
		String sql = new StringBuffer("UPDATE FUNCAO SET SEQ_LINK=?, NOME=?, DESCRICAO=? WHERE SEQ_FUNCAO=?")
		.toString();
		Connection conn = null;
		PreparedStatement stmt = null;
		try{
			conn = getConexao().getConexao();
			stmt = conn.prepareStatement(sql);
			System.out.println("Link " + vo.getLink().getCodigo() + " -0");
			if (vo.getLink() != null && vo.getLink().getCodigo() != null && vo.getLink().getCodigo().intValue() != 0){
				stmt.setInt(1, vo.getLink().getCodigo().intValue());
				System.out.println("AQUI 1");
			}else{
				stmt.setNull(1, Types.INTEGER);
				System.out.println("AQUI 2");
			}
			stmt.setString(2, vo.getNome());
			stmt.setString(3, vo.getDescricao());
			stmt.setInt(4, vo.getCodigo().intValue());
			stmt.execute();
		}catch(SQLException sqlEx){
			throw new AmbienteException(sqlEx);
		}finally{
			this.fechar(conn, stmt, null);
		}

	}

	public void remove(FuncaoVO vo) throws AmbienteException, AplicacaoException {
		String sql = new StringBuffer("DELETE FROM FUNCAO WHERE SEQ_FUNCAO=?").toString();
		Connection conn = null;
		PreparedStatement stmt = null;
		try{
			conn = getConexao().getConexao();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, vo.getCodigo().intValue());
			stmt.execute();
		}catch(SQLException sqlEx){
			throw new AmbienteException(sqlEx);
		}finally{
			this.fechar(conn, stmt, null);
		}

	}

	public FuncaoVO findByPrimaryKey(FuncaoVO vo) throws AmbienteException, AplicacaoException {
		String sql = new StringBuffer("SELECT F.SEQ_FUNCAO, F.NOME, F.DESCRICAO, L.SEQ_LINK, L.URL  FROM FUNCAO ")
		.append("AS F LEFT JOIN LINK_FUNCAO AS L ON F.SEQ_LINK=L.SEQ_LINK WHERE F.SEQ_FUNCAO=?").toString();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			conn = getConexao().getConexao();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, vo.getCodigo().intValue());
			rs = stmt.executeQuery();
			if (rs.next()){
				FuncaoVO funcaoVO = new FuncaoVO();
				funcaoVO.setCodigo(new Integer(rs.getInt("SEQ_FUNCAO")));
				funcaoVO.setLink(new LinkVO(new Integer(rs.getInt("SEQ_LINK"))));
				funcaoVO.getLink().setUrl(rs.getString("URL"));
				funcaoVO.setNome(rs.getString("NOME"));
				funcaoVO.setDescricao(rs.getString("DESCRICAO"));
				return funcaoVO;
			}else{
				return null;
			}
		}catch(SQLException sqlEx){
			throw new AmbienteException(sqlEx);
		}finally{
			this.fechar(conn, stmt, rs);
		}
	}
	
	public Collection findAll() throws AmbienteException, AplicacaoException {
		String sql = new StringBuffer("SELECT SEQ_FUNCAO, SEQ_LINK, NOME, DESCRICAO FROM FUNCAO ORDER BY NOME").toString();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Collection collFuncao = new ArrayList();
		try{
			conn = getConexao().getConexao();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()){
				FuncaoVO funcaoVO = new FuncaoVO();
				funcaoVO.setCodigo(new Integer(rs.getInt("SEQ_FUNCAO")));
				funcaoVO.setLink(new LinkVO(new Integer(rs.getInt("SEQ_LINK"))));
				funcaoVO.setNome(rs.getString("NOME"));
				funcaoVO.setDescricao(rs.getString("DESCRICAO"));
				collFuncao.add(funcaoVO);
			}
			return collFuncao;
		}catch(SQLException sqlEx){
			throw new AmbienteException(sqlEx);
		}finally{
			this.fechar(conn, stmt, rs);
		}
	}

	public Collection findByFilter(FuncaoVO vo) throws AmbienteException, AplicacaoException {
		String sql = new StringBuffer("SELECT SEQ_FUNCAO, SEQ_LINK, NOME, DESCRICAO FROM FUNCAO WHERE ")
		.append("(? IS NULL OR SEQ_LINK=?) AND (? IS NULL OR UPPER(NOME) LIKE '%' || UPPER(?) || '%') ")
		.append("AND (? IS NULL OR UPPER(DESCRICAO) LIKE '%' || UPPER(?) || '%') ORDER BY NOME")
		.toString();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Collection collFuncao = new ArrayList();
		try{
			conn = getConexao().getConexao();
			stmt = conn.prepareStatement(sql);
			if (vo.getLink() != null && vo.getLink().getCodigo() != null){
				stmt.setInt(1, vo.getCodigo().intValue());
				stmt.setInt(2, vo.getCodigo().intValue());
			}else{
				stmt.setNull(1, Types.INTEGER);
				stmt.setNull(2, Types.INTEGER);
			}
			stmt.setString(3, vo.getNome());
			stmt.setString(4, vo.getNome());
			stmt.setString(5, vo.getDescricao());
			stmt.setString(6, vo.getDescricao());
			rs = stmt.executeQuery();
			while (rs.next()){
				FuncaoVO funcaoVO = new FuncaoVO();
				funcaoVO.setCodigo(new Integer(rs.getInt("SEQ_FUNCAO")));
				funcaoVO.setLink(new LinkVO(new Integer(rs.getInt("SEQ_LINK"))));
				funcaoVO.setNome(rs.getString("NOME"));
				funcaoVO.setDescricao(rs.getString("DESCRICAO"));
				collFuncao.add(funcaoVO);
			}
			return collFuncao;
		}catch(SQLException sqlEx){
			throw new AmbienteException(sqlEx);
		}finally{
			this.fechar(conn, stmt, rs);
		}
	}
	
	public Collection findByUsuario(UsuarioVO vo) throws AmbienteException, AplicacaoException {
		String sql = new StringBuffer("SELECT F.SEQ_FUNCAO, F.SEQ_LINK, F.NOME, F.DESCRICAO FROM FUNCAO AS F ")
		.append("JOIN USUARIO_FUNCAO AS UF ON F.SEQ_FUNCAO=UF.SEQ_FUNCAO ")
		.append("LEFT JOIN LINK_FUNCAO AS L ON F.SEQ_LINK=L.SEQ_LINK WHERE UF.SEQ_USUARIO=? ORDER BY F.NOME").toString();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Collection collFuncao = new ArrayList();
		try{
			conn = getConexao().getConexao();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, vo.getCodigo().intValue());
			rs = stmt.executeQuery();
			while (rs.next()){
				FuncaoVO funcaoVO = new FuncaoVO();
				funcaoVO.setCodigo(new Integer(rs.getInt("SEQ_FUNCAO")));
				funcaoVO.setLink(new LinkVO(new Integer(rs.getInt("SEQ_LINK"))));
				funcaoVO.setNome(rs.getString("NOME"));
				funcaoVO.setDescricao(rs.getString("DESCRICAO"));
				collFuncao.add(funcaoVO);
			}
			return collFuncao;
		}catch(SQLException sqlEx){
			throw new AmbienteException(sqlEx);
		}finally{
			this.fechar(conn, stmt, rs);
		}
	}
	
	public Collection findByFuncionalidade(FuncionalidadeVO vo) throws AmbienteException, AplicacaoException {
		String sql = new StringBuffer("SELECT F.SEQ_FUNCAO, F.SEQ_LINK, F.NOME, F.DESCRICAO, L.URL FROM FUNCAO AS F ")
		.append("JOIN FUNCAO_FUNCIONALIDADE AS FF ON F.SEQ_FUNCAO=FF.SEQ_FUNCAO ")
		.append("LEFT JOIN LINK_FUNCAO AS L ON F.SEQ_LINK=L.SEQ_LINK WHERE FF.SEQ_FUNCIONALIDADE=? ORDER BY F.NOME")
		.toString();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Collection collFuncao = new ArrayList();
		try{
			conn = getConexao().getConexao();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, vo.getCodigo().intValue());
			rs = stmt.executeQuery();
			while (rs.next()){
				FuncaoVO funcaoVO = new FuncaoVO();
				funcaoVO.setCodigo(new Integer(rs.getInt("SEQ_FUNCAO")));
				funcaoVO.setNome(rs.getString("NOME"));
				funcaoVO.setDescricao(rs.getString("DESCRICAO"));
				if (rs.getInt("SEQ_LINK") > 0){
					funcaoVO.setLink(new LinkVO(new Integer(rs.getInt("SEQ_LINK"))));
					funcaoVO.getLink().setUrl(rs.getString("URL"));
				}
				collFuncao.add(funcaoVO);
			}
			return collFuncao;
		}catch(SQLException sqlEx){
			throw new AmbienteException(sqlEx);
		}finally{
			this.fechar(conn, stmt, rs);
		}
	}
	
	public Collection findByNFuncionalidade(FuncionalidadeVO vo) throws AmbienteException, AplicacaoException {
		String sql = new StringBuffer("SELECT DISTINCT F.SEQ_FUNCAO, F.SEQ_LINK, F.NOME, F.DESCRICAO FROM FUNCAO AS F LEFT ")
		.append("JOIN FUNCAO_FUNCIONALIDADE AS FF ON F.SEQ_FUNCAO=FF.SEQ_FUNCAO WHERE F.SEQ_FUNCAO NOT IN ")
		.append("(SELECT SEQ_FUNCAO FROM FUNCAO_FUNCIONALIDADE WHERE SEQ_FUNCIONALIDADE=?) ORDER BY F.NOME").toString();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Collection collFuncao = new ArrayList();
		try{
			conn = getConexao().getConexao();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, vo.getCodigo().intValue());
			rs = stmt.executeQuery();
			while (rs.next()){
				FuncaoVO funcaoVO = new FuncaoVO();
				funcaoVO.setCodigo(new Integer(rs.getInt("SEQ_FUNCAO")));
				funcaoVO.setLink(new LinkVO(new Integer(rs.getInt("SEQ_LINK"))));
				funcaoVO.setNome(rs.getString("NOME"));
				funcaoVO.setDescricao(rs.getString("DESCRICAO"));
				collFuncao.add(funcaoVO);
			}
			return collFuncao;
		}catch(SQLException sqlEx){
			throw new AmbienteException(sqlEx);
		}finally{
			this.fechar(conn, stmt, rs);
		}
	}
}
