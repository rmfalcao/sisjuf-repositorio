package br.org.asserjuf.sisjuf.dados;

import java.sql.Connection;
import java.sql.SQLException;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import br.com.falc.smartFW.exception.SmartEnvException;
import br.com.falc.smartFW.persistence.SmartConnection;
import br.com.falc.smartFW.persistence.SmartPreparedStatement;
import br.com.falc.smartFW.persistence.SmartResultSet;

/**
 * Abstração para todas as classes de dados (DAO) do Sisjuf.
 * Projetada para trabalhar com o banco da dados relacional PostGreSQL. 
 * 
 * @author Rodrigo Falcão
 *
 */
public abstract class SisjufDAOPostgres extends JdbcDaoSupport {
	 
	

	/**
	 * 
	 * Obtém o próximo valor de uma sequence no banco de dados.
	 * 
	 * @param seqName Nome da sequence cujo próximo valor se deseja recuperar.
	 * @return Próximo valor da sequence passada como parâmetro.
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
	 * Obtém uma instância do objeto de conexão ativo no pool do servidor de aplicação.
	 * @return Objeto da conexão.
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
