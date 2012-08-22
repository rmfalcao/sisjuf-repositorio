package br.org.asserjuf.sisjuf.financeiro.dados;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.apache.log4j.Logger;

import br.com.falc.smartFW.persistence.SmartPreparedStatement;
import br.com.falc.smartFW.persistence.SmartResultSet;
import br.com.falc.smartFW.exception.SmartAppException;
import br.com.falc.smartFW.exception.SmartEnvException;
import br.org.asserjuf.sisjuf.dados.SisjufDAOPostgres;
import br.org.asserjuf.sisjuf.financeiro.DiretorFinanceiroVO;
import br.com.falc.smartFW.persistence.SmartConnection;

/**
 * Classe de acesso ao banco de dados da entidade "Tipo de Lançamento" do Sisjuf.
 * @author Rodrigo Falcão
 *
 */
public class DiretorFinanceiroDAO extends SisjufDAOPostgres {

	private static transient Logger LOG = Logger.getLogger(DiretorFinanceiroDAO.class);
	
	/**
	 * Obtém a coleção de todos os tipos de lançamento do banco de dados. Tabelas: TIPO_LANCAMENTO.
	 * @return Coleção de tipos de lançamento, encapsulados pela classe TipoLancamentoVO.
	 * @throws SmartEnvException
	 */
	public Collection<DiretorFinanceiroVO> findAll() throws SmartEnvException {
		
		StringBuffer sql = new StringBuffer("SELECT SEQ_DIRETOR_FINANCEIRO, NOM_DIRETOR_FINANCEIRO, DAT_INICIO_GESTAO_DIRETOR_FINANCEIRO FROM DIRETOR_FINANCEIRO ORDER BY NOM_DIRETOR_FINANCEIRO DESC ");

		SmartConnection 		sConn 	= null;
		SmartPreparedStatement 	sStmt 	= null;
		SmartResultSet			sRs		= null;
		try {
		
			sConn 	= new SmartConnection(this.getConn());
			sStmt 	= new SmartPreparedStatement(sConn.prepareStatement(sql.toString()));
			sRs 	= new SmartResultSet(sStmt.getMyPreparedStatement().executeQuery());
			
			ArrayList<DiretorFinanceiroVO> retorno = new ArrayList<DiretorFinanceiroVO>(); 
			
			while (sRs.next()) {

				DiretorFinanceiroVO diretorFinanceiroVO = new DiretorFinanceiroVO();

				diretorFinanceiroVO.setCodigo(sRs.getInteger(1));
				diretorFinanceiroVO.setNome(sRs.getString(2));
				diretorFinanceiroVO.setDataInicioGestao(sRs.getDate(3));
				retorno.add(diretorFinanceiroVO);
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

	public DiretorFinanceiroVO findByDate(Date data) throws SmartEnvException {
		
		StringBuffer sql = new StringBuffer("select seq_diretor_financeiro, nom_diretor_financeiro, dat_inicio_gestao_diretor_financeiro ");
		sql.append("from diretor_financeiro where dat_inicio_gestao_diretor_financeiro = (select max(dat_inicio_gestao_diretor_financeiro) ");
		sql.append("from diretor_financeiro where dat_inicio_gestao_diretor_financeiro < ?)");

		SmartConnection 		sConn 	= null;
		SmartPreparedStatement 	sStmt 	= null;
		
		try {
			sConn 	= new SmartConnection(this.getConn());
			sStmt 	= new SmartPreparedStatement(sConn.prepareStatement(sql.toString()));
			
			sStmt.setDate(1, data);
						
			SmartResultSet sRs = new SmartResultSet(sStmt.getMyPreparedStatement().executeQuery());
			  
			if (sRs.next()) {
				
				DiretorFinanceiroVO retorno = new DiretorFinanceiroVO();

				retorno.setCodigo(sRs.getInteger(1));
				retorno.setNome(sRs.getString(2));
				retorno.setDataInicioGestao(sRs.getDate(3));
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

	public DiretorFinanceiroVO findByPrimaryKey(DiretorFinanceiroVO vo) throws SmartEnvException {
		
		StringBuffer sql = new StringBuffer("SELECT SEQ_DIRETOR_FINANCEIRO, NOM_DIRETOR_FINANCEIRO, DAT_INICIO_GESTAO_DIRETOR_FINANCEIRO FROM DIRETOR_FINANCEIRO WHERE SEQ_DIRETOR_FINANCEIRO = ? ");

		SmartConnection 		sConn 	= null;
		SmartPreparedStatement 	sStmt 	= null;
		try {
			sConn 	= new SmartConnection(this.getConn());
			sStmt 	= new SmartPreparedStatement(sConn.prepareStatement(sql.toString()));
			
			sStmt.setInteger(1, vo.getCodigo());
						
			SmartResultSet sRs = new SmartResultSet(sStmt.getMyPreparedStatement().executeQuery());
			  
			if (sRs.next()) {
				
				DiretorFinanceiroVO retorno = new DiretorFinanceiroVO();

				retorno.setCodigo(sRs.getInteger(1));
				retorno.setNome(sRs.getString(2));
				retorno.setDataInicioGestao(sRs.getDate(3));
				
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
	
	public void update(DiretorFinanceiroVO vo) throws SmartEnvException {
				
		StringBuffer sql = new StringBuffer(" UPDATE DIRETOR_FINANCEIRO SET NOM_DIRETOR_FINANCEIRO = ?, DAT_INICIO_GESTAO_DIRETOR_FINANCEIRO = ? WHERE SEQ_DIRETOR_FINANCEIRO = ? ");
		SmartConnection 		sConn 	= null;
		SmartPreparedStatement 	sStmt 	= null;
		try {
		
			sConn 	= new SmartConnection(this.getConn());
			sStmt 	= new SmartPreparedStatement(sConn.prepareStatement(sql.toString()));
			
			sStmt.setString(1, vo.getNome());
			sStmt.setDate(2, vo.getDataInicioGestao());
			sStmt.setInteger(3, vo.getCodigo());
									
			sStmt.getMyPreparedStatement().execute();
		} catch (SQLException e) {
			throw new SmartEnvException(e);
		} finally {
			sStmt.close();
			sConn.close();
		}
	}
	
	public DiretorFinanceiroVO insert(DiretorFinanceiroVO vo) throws SmartEnvException {
		StringBuffer sql = new StringBuffer(" INSERT INTO DIRETOR_FINANCEIRO (SEQ_DIRETOR_FINANCEIRO, NOM_DIRETOR_FINANCEIRO, DAT_INICIO_GESTAO_DIRETOR_FINANCEIRO) VALUES (?,?,?) ");

		SmartConnection 		sConn 	= null;
		SmartPreparedStatement 	sStmt 	= null;
		
		try {
			sConn 	= new SmartConnection(this.getConn());
			sStmt 	= new SmartPreparedStatement(sConn.prepareStatement(sql.toString()));
		
			vo.setCodigo(new Integer(getSequence("SEQ_DIRETOR_FINANCEIRO").intValue()));
			
			sStmt.setInteger(1, vo.getCodigo());
			sStmt.setString(2, vo.getNome());
			sStmt.setDate(3, vo.getDataInicioGestao());
									
			sStmt.getMyPreparedStatement().execute();
			
			return vo;
		} catch (SQLException e) {
			throw new SmartEnvException(e);
		} finally {
			sStmt.close();
			sConn.close();
		}
	}
	
	public void remove(DiretorFinanceiroVO vo) throws SmartEnvException, SmartAppException {
		
		StringBuffer sql = new StringBuffer("DELETE FROM DIRETOR_FINANCEIRO WHERE SEQ_DIRETOR_FINANCEIRO = ? ");

		SmartConnection 		sConn 	= null;
		SmartPreparedStatement 	sStmt 	= null;
		try {
		
			sConn 	= new SmartConnection(this.getConn());
			sStmt 	= new SmartPreparedStatement(sConn.prepareStatement(sql.toString()));
			
			sStmt.setInteger(1, vo.getCodigo());	
						
			sStmt.getMyPreparedStatement().execute();
		} catch (SQLException e) {
			throw new SmartEnvException(e);
		} finally {
			sStmt.close();
			sConn.close();
		}
	}
}