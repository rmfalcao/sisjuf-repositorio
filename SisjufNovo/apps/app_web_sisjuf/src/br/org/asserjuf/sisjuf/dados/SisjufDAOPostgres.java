package br.org.asserjuf.sisjuf.dados;

import java.sql.Connection;
import java.sql.SQLException;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import br.com.falc.smartFW.exception.SmartEnvException;
import br.com.falc.smartFW.persistence.SmartConnection;
import br.com.falc.smartFW.persistence.SmartPreparedStatement;
import br.com.falc.smartFW.persistence.SmartResultSet;

/**
 * Abstra��o para todas as classes de dados (DAO) do Sisjuf.
 * Projetada para trabalhar com o banco da dados relacional PostGreSQL. 
 * 
 * @author Rodrigo Falc�o
 *
 */
public abstract class SisjufDAOPostgres extends JdbcDaoSupport {
	 
	

	/**
	 * 
	 * Obt�m o pr�ximo valor de uma sequence no banco de dados.
	 * 
	 * @param seqName Nome da sequence cujo pr�ximo valor se deseja recuperar.
	 * @return Pr�ximo valor da sequence passada como par�metro.
	 * @throws SmartEnvException
	 */
	public Long getSequence(String seqName) throws SmartEnvException {
		
		StringBuffer sql = new StringBuffer("SELECT NEXTVAL('").append(seqName).append("')");
		
		SmartConnection 		sConn 	= null;
		SmartPreparedStatement 	sStmt 	= null;
		SmartResultSet			sRs		= null;
		
		try {
			
			sConn 	= new SmartConnection(this.getConnection());
			sStmt 	= new SmartPreparedStatement(sConn.prepareStatement(sql.toString()));
			sRs 	= new SmartResultSet(sStmt.getMyPreparedStatement().executeQuery());
			
			if (sRs.next()) {
				return sRs.getLong(1);

			}
		
		} catch (SQLException e) {
			throw new SmartEnvException(e);
		} finally {
			sRs.close();
			sStmt.close();
			sConn.close();
		}
		
		return null;
		
	}
	
	
	/**
	 * Obt�m uma inst�ncia do objeto de conex�o ativo no pool do servidor de aplica��o.
	 * @return Objeto da conex�o.
	 * @throws SmartEnvException
	 */
	public Connection getConn() throws SmartEnvException {
		

		
		try {
			return this.getDataSource().getConnection();
		} catch (SQLException e) {
			throw new SmartEnvException(e);
		}
		
		

		
	}

}
