package br.org.asserjuf.sisjuf.util.dados;

import java.sql.SQLException;

import br.com.falc.smartFW.exception.SmartEnvException;
import br.com.falc.smartFW.persistence.SmartConnection;
import br.com.falc.smartFW.persistence.SmartPreparedStatement;
import br.com.falc.smartFW.persistence.SmartResultSet;
import br.org.asserjuf.sisjuf.dados.SisjufDAOPostgres;
import br.org.asserjuf.sisjuf.util.ParametroVO;

public class ParametroDAO extends SisjufDAOPostgres {

	public ParametroVO findByPrimaryKey(ParametroVO vo) throws SmartEnvException {
		
		
		StringBuffer sql = new StringBuffer(" SELECT NOM_PARAMETRO, STR_VAL_PARAMETRO FROM PARAMETROS WHERE NOM_PARAMETRO = ? ");

		SmartConnection 		sConn 	= null;
		SmartPreparedStatement 	sStmt 	= null;
		SmartResultSet			sRs		= null;
		
		try {
		 
			sConn 	= new SmartConnection(this.getConn());
			sStmt 	= new SmartPreparedStatement(sConn.prepareStatement(sql.toString()));
			
			sStmt.setString(1, vo.getNome());
			
			sRs = new SmartResultSet(sStmt.getMyPreparedStatement().executeQuery());

			if (sRs.next()) {
				
				ParametroVO retorno = new ParametroVO(sRs.getString(1));
				retorno.setValorTextual(sRs.getString(2));

				return retorno;

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
