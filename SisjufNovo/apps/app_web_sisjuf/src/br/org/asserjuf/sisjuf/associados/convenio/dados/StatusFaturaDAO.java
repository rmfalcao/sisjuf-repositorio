package br.org.asserjuf.sisjuf.associados.convenio.dados;

import java.sql.SQLException;
import java.util.Collection;

//import org.apache.log4j.Logger;

import br.com.falc.smartFW.exception.SmartEnvException;
import br.com.falc.smartFW.persistence.SmartConnection;
import br.com.falc.smartFW.persistence.SmartPreparedStatement;
import br.com.falc.smartFW.persistence.SmartResultSet;
import br.org.asserjuf.sisjuf.dados.SisjufDAOPostgres;

/**
 * Classe de acesso ao banco de dados da entidade "Fatura" do Sisjuf
 * @author Rodrigo Falcão
 *
 */
public class StatusFaturaDAO extends SisjufDAOPostgres {


	/**
	 * Obtém todos os status de fatura
	 * @return Coleção de StatusFaturaVO (objeto que representa a entidade "Status de Fatura")
	 * @throws SmartEnvException
	 */
	public Collection<StatusFaturaVO> findAll() throws SmartEnvException {
		
		StringBuffer sql = new StringBuffer();
		
		sql.append(" SELECT SEQ_STATUS_FATURA, NOM_STATUS_FATURA FROM STATUS_FATURA ");	

		SmartConnection 		sConn 	= null;
		SmartPreparedStatement 	sStmt 	= null;
		SmartResultSet			sRs		= null;

		try {
		
			sConn 	= new SmartConnection(this.getConn());
			sStmt 	= new SmartPreparedStatement(sConn.prepareStatement(sql.toString()));

			sRs 	= new SmartResultSet(sStmt.getMyPreparedStatement().executeQuery());
			
			return sRs.getJavaBeans(StatusFaturaVO.class, new String[]{	"codigo","nome"		});									
																	
			
		} catch (SQLException e) {
			throw new SmartEnvException(e);
		} finally {
			sRs.close();
			sStmt.close();
			sConn.close();
		}
	}
	
}
