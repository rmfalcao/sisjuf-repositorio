package br.org.asserjuf.sisjuf.associados.dados;

import java.sql.SQLException;
import java.util.Collection;


import br.com.falc.smartFW.exception.SmartEnvException;
import br.com.falc.smartFW.persistence.SmartConnection;
import br.com.falc.smartFW.persistence.SmartPreparedStatement;
import br.com.falc.smartFW.persistence.SmartResultSet;
import br.org.asserjuf.sisjuf.associados.ParentescoVO;
import br.org.asserjuf.sisjuf.dados.SisjufDAOPostgres;

public class ParentescoDAO extends SisjufDAOPostgres {

	public Collection<ParentescoVO> findAll()  throws SmartEnvException {
		
		StringBuffer sql = new StringBuffer(" SELECT SEQ_PARENTESCO, NOM_PARENTESCO FROM PARENTESCO ");
	
		SmartConnection 		sConn 	= null;
		SmartPreparedStatement 	sStmt 	= null;
		SmartResultSet			sRs		= null;
		
		try {
		
			sConn 	= new SmartConnection(this.getConn());
			sStmt 	= new SmartPreparedStatement(sConn.prepareStatement(sql.toString()));
						
			sRs 	= new SmartResultSet(sStmt.getMyPreparedStatement().executeQuery());
		
			return sRs.getJavaBeans(ParentescoVO.class, new String[]{"codigo", "nome"});
		
		} catch (SQLException e) {
			throw new SmartEnvException(e);

		} finally {

			sRs.close();
			sStmt.close();
			sConn.close();

		}
		
	}
	
}
