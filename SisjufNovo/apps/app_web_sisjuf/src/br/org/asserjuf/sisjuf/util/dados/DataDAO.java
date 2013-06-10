package br.org.asserjuf.sisjuf.util.dados;

import java.util.Date;
import java.sql.SQLException;

import br.com.falc.smartFW.exception.SmartEnvException;
import br.com.falc.smartFW.persistence.SmartConnection;
import br.com.falc.smartFW.persistence.SmartPreparedStatement;
import br.com.falc.smartFW.persistence.SmartResultSet;
import br.org.asserjuf.sisjuf.dados.SisjufDAOPostgres;

public class DataDAO extends SisjufDAOPostgres {

	public Date getCurrentDate() throws SmartEnvException {
		
		StringBuffer sql = new StringBuffer(" SELECT CURRENT_DATE ");

		SmartConnection 		sConn 	= null;
		SmartPreparedStatement 	sStmt 	= null;
		SmartResultSet			sRs		= null;
		
		try {
		 
			sConn 	= new SmartConnection(this.getConn());
			sStmt 	= new SmartPreparedStatement(sConn.prepareStatement(sql.toString()));
						
			sRs = new SmartResultSet(sStmt.getMyPreparedStatement().executeQuery());

			if (sRs.next()) {
			
				return sRs.getDate(1);

			}
			
			return null;
		
		} catch (SQLException e) {

			throw new SmartEnvException(e);

		} finally {

			
			
			sRs.close();
			sStmt.close();
			sConn.close();
			
			

		}
		
	}
		
}
