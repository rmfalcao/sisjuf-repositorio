package br.org.asserjuf.sisjuf.financeiro.dados;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import br.com.falc.smartFW.persistence.SmartPreparedStatement;
import br.com.falc.smartFW.persistence.SmartResultSet;
import br.com.falc.smartFW.exception.SmartAppException;
import br.com.falc.smartFW.exception.SmartEnvException;
import br.org.asserjuf.sisjuf.dados.SisjufDAOPostgres;
import br.org.asserjuf.sisjuf.financeiro.TipoLancamentoVO;
import br.com.falc.smartFW.persistence.SmartConnection;


/**
 * Classe de acesso ao banco de dados da entidade "Tipo de Lançamento" do Sisjuf.
 * @author Rodrigo Falcão
 *
 */
public class TipoLancamentoDAO extends SisjufDAOPostgres {

	/**
	 * Obtém a coleção de todos os tipos de lançamento do banco de dados. Tabelas: TIPO_LANCAMENTO.
	 * @return Coleção de tipos de lançamento, encapsulados pela classe TipoLancamentoVO.
	 * @throws SmartEnvException
	 */
	public Collection<TipoLancamentoVO> findAll() throws SmartEnvException {
		
		StringBuffer sql = new StringBuffer("SELECT SEQ_TIPO_LANCAMENTO, NOM_TIPO_LANCAMENTO, FLG_NAO_APAGAVEL_TIPO_LANCAMENTO FROM TIPO_LANCAMENTO ORDER BY NOM_TIPO_LANCAMENTO ");

		SmartConnection 		sConn 	= null;
		SmartPreparedStatement 	sStmt 	= null;
		SmartResultSet			sRs		= null;
		
		try {
		
			sConn 	= new SmartConnection(this.getConn());
			sStmt 	= new SmartPreparedStatement(sConn.prepareStatement(sql.toString()));
			sRs 	= new SmartResultSet(sStmt.getMyPreparedStatement().executeQuery());
			
			ArrayList<TipoLancamentoVO> retorno = new ArrayList<TipoLancamentoVO>(); 
			
			while (sRs.next()) {

				TipoLancamentoVO tipoLancamentoVO = new TipoLancamentoVO();

				tipoLancamentoVO.setCodigo(sRs.getInteger(1));
				tipoLancamentoVO.setNome(sRs.getString(2));
				tipoLancamentoVO.setFlgNaoApagavel(sRs.getString(3));

				retorno.add(tipoLancamentoVO);

			}
			
			return retorno;
		
		} catch (SQLException e) {

			throw new SmartEnvException(e);

		} finally {

			sRs.close();
			sStmt.close();
			sConn.close();

		}
		
	}
	
	public TipoLancamentoVO findByPrimaryKey(TipoLancamentoVO vo) throws SmartEnvException {
		
		
		StringBuffer sql = new StringBuffer("SELECT SEQ_TIPO_LANCAMENTO, NOM_TIPO_LANCAMENTO, FLG_NAO_APAGAVEL_TIPO_LANCAMENTO ");
		sql.append("FROM TIPO_LANCAMENTO WHERE SEQ_TIPO_LANCAMENTO = ? ");

		SmartConnection 		sConn 	= null;
		SmartPreparedStatement 	sStmt 	= null;
		
		try {
		
			sConn 	= new SmartConnection(this.getConn());
			sStmt 	= new SmartPreparedStatement(sConn.prepareStatement(sql.toString()));
			
			sStmt.setInteger(1, vo.getCodigo());
						
			SmartResultSet sRs = new SmartResultSet(sStmt.getMyPreparedStatement().executeQuery());
			  
			
			if (sRs.next()) {
				
				TipoLancamentoVO retorno = new TipoLancamentoVO();

				retorno.setCodigo(sRs.getInteger(1));
				retorno.setNome(sRs.getString(2));
				retorno.setFlgNaoApagavel(sRs.getString(3));
				
				return retorno;
				
			}
			
			return null;
		
		} catch (SQLException e) {

			throw new SmartEnvException(e);

		} finally {

			sStmt.close();
			sConn.close();

		}
		
	}
	
	public void update(TipoLancamentoVO vo) throws SmartEnvException {
				
		StringBuffer sql = new StringBuffer("UPDATE TIPO_LANCAMENTO SET NOM_TIPO_LANCAMENTO = ? WHERE SEQ_TIPO_LANCAMENTO = ? ");
		SmartConnection 		sConn 	= null;
		SmartPreparedStatement 	sStmt 	= null;
		
		try {
		
			sConn 	= new SmartConnection(this.getConn());
			sStmt 	= new SmartPreparedStatement(sConn.prepareStatement(sql.toString()));
			
			
			sStmt.setString(1, vo.getNome());
			sStmt.setInteger(2, vo.getCodigo());
									
			sStmt.getMyPreparedStatement().execute();
				
		} catch (SQLException e) {

			throw new SmartEnvException(e);

		} finally {

			sStmt.close();
			sConn.close();

		}
		
	}
	
	public TipoLancamentoVO insert(TipoLancamentoVO vo) throws SmartEnvException {
		
		
		StringBuffer sql = new StringBuffer(" INSERT INTO TIPO_LANCAMENTO (SEQ_TIPO_LANCAMENTO, NOM_TIPO_LANCAMENTO) VALUES (?, ?) ");

		SmartConnection 		sConn 	= null;
		SmartPreparedStatement 	sStmt 	= null;
		
		try {
		
			sConn 	= new SmartConnection(this.getConn());
			sStmt 	= new SmartPreparedStatement(sConn.prepareStatement(sql.toString()));
		
			vo.setCodigo(new Integer(getSequence("SEQ_TIPO_LANCAMENTO").intValue()));
			
			sStmt.setInteger(1, vo.getCodigo());
			sStmt.setString(2, vo.getNome());
									
			sStmt.getMyPreparedStatement().execute();
			
			return vo;
				
		} catch (SQLException e) {

			throw new SmartEnvException(e);

		} finally {

			sStmt.close();
			sConn.close();

		}
		
	}
	
	public void remove(TipoLancamentoVO vo) throws SmartEnvException, SmartAppException {
		
		
		StringBuffer sql = new StringBuffer("DELETE FROM TIPO_LANCAMENTO WHERE SEQ_TIPO_LANCAMENTO = ? ");

		SmartConnection 		sConn 	= null;
		SmartPreparedStatement 	sStmt 	= null;
		
		try {
		
			sConn 	= new SmartConnection(this.getConn());
			sStmt 	= new SmartPreparedStatement(sConn.prepareStatement(sql.toString()));
			
			sStmt.setInteger(1, vo.getCodigo());	
						
			sStmt.getMyPreparedStatement().execute();
				
		} catch (SQLException e) {
			// Verificando se o erro foi por causa da violação de alguma FK.
			
			if (e.getMessage().indexOf("fk_lancamento_tipo_lancamento") != -1) {
				// Exceção levantada pela presença de lançamentos.
				throw new SmartAppException("Não é possível excluir este tipo de lançamento, pois há lançamentos associados a ele.");
				
			}
			
			// Como não foi violação de FK, levantar exceção de ambiente.			
			throw new SmartEnvException(e);

		} finally {

			sStmt.close();
			sConn.close();

		}
		
	}
	
}
