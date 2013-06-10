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
		
		/*
		 * A versão final desta classe só deve ter a linha abaixo
		 * descomentada, o try catch que segue é somente por causa
		 * do JUnit
		 * */
		
		try {
			return this.getDataSource().getConnection();
		} catch (SQLException e) {
			throw new SmartEnvException(e);
		}
		
		
		// Comentar tudo abaixo
		
		/*
		    //String connectionURLThin  = "jdbc:postgresql://10.0.0.11:5432/bd_asserjuf";
			String connectionURLThin  	= "jdbc:postgresql://localhost:5432/bd_asserjuf";
			
			//String userID             = "admin_asserjuf";
			String userID			  	= "postgres";
			
			//String userPassword       = "asserjuf123";
			String userPassword			= "elefante10";
		   
		    try {
			    
			    DriverManager.registerDriver(new org.postgresql.Driver());
	            return DriverManager.getConnection(connectionURLThin, userID, userPassword);
		    
		    } catch (SQLException ex) {
		    	throw new SmartEnvException(ex);
		    }
		  
		    */
		
	}

}
