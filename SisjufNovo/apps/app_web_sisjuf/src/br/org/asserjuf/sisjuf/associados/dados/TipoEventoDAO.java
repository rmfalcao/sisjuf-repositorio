package br.org.asserjuf.sisjuf.associados.dados;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import br.com.falc.smartFW.exception.SmartEnvException;
import br.com.falc.smartFW.persistence.SmartConnection;
import br.com.falc.smartFW.persistence.SmartPreparedStatement;
import br.com.falc.smartFW.persistence.SmartResultSet;
import br.org.asserjuf.sisjuf.associados.AssociadoVO;
import br.org.asserjuf.sisjuf.associados.HistoricoAssociadoVO;
import br.org.asserjuf.sisjuf.associados.HistoricoFiltroAssembler;
import br.org.asserjuf.sisjuf.associados.TipoEventoVO;
import br.org.asserjuf.sisjuf.dados.SisjufDAOPostgres;

public class TipoEventoDAO extends SisjufDAOPostgres {
	
	public Collection<TipoEventoVO> findAll() throws SmartEnvException {
		
		StringBuffer sql = new StringBuffer(" SELECT SEQ_TIPO_EVENTO, NOM_TIPO_EVENTO FROM TIPO_EVENTO ");
		
		SmartConnection 		sConn 	= null;
		SmartPreparedStatement 	sStmt 	= null;
		SmartResultSet			sRs		= null;

		try {

			sConn 	= new SmartConnection(this.getConn());
			sStmt 	= new SmartPreparedStatement(sConn.prepareStatement(sql.toString()));

			sRs 	= new SmartResultSet(sStmt.getMyPreparedStatement().executeQuery());

			return sRs.getJavaBeans(TipoEventoVO.class, new String[] {"codigo", "nome"}); 

		
		} catch (SQLException e) {
			throw new SmartEnvException(e);

		} finally {

			sRs.close();
			sStmt.close();
			sConn.close();

		}
		
	}	
	
}
