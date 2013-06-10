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
		
		/*
		 * A vers�o final desta classe s� deve ter a linha abaixo
		 * descomentada, o try catch que segue � somente por causa
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
