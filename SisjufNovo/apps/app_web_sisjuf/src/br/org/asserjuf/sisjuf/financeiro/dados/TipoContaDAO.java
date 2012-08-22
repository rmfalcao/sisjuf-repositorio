package br.org.asserjuf.sisjuf.financeiro.dados;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import br.com.falc.smartFW.persistence.SmartPreparedStatement;
import br.com.falc.smartFW.persistence.SmartResultSet;
import br.org.asserjuf.sisjuf.dados.SisjufDAOPostgres;
import br.org.asserjuf.sisjuf.financeiro.TipoContaVO;
import br.com.falc.smartFW.exception.SmartEnvException;
import br.com.falc.smartFW.persistence.SmartConnection;


/**
 * Classe de acesso ao banco de dados da entidade "Tipo de Conta" do Sisjuf.
 * @author Rodrigo Falcão
 *
 */
public class TipoContaDAO extends SisjufDAOPostgres {
	
	
	/**
	 * Obtém a coleção de todos os tipos de conta do banco de dados. Tabelas: TIPO_CONTA.
	 * @return Coleção de tipos de conta, encapsulados pela classe TipoContaVO.
	 * @throws SmartEnvException
	 */
	public Collection<TipoContaVO> findAll() throws SmartEnvException {
		
		StringBuffer sql = new StringBuffer(" SELECT SEQ_TIPO_CONTA, DES_TIPO_CONTA FROM TIPO_CONTA ORDER BY DES_TIPO_CONTA ");
		
		SmartConnection 		sConn 	= null;
		SmartPreparedStatement 	sStmt 	= null;
		SmartResultSet			sRs		= null;
		
		try {
		
			sConn 	= new SmartConnection(this.getConn());
			sStmt 	= new SmartPreparedStatement(sConn.prepareStatement(sql.toString()));
			sRs 	= new SmartResultSet(sStmt.getMyPreparedStatement().executeQuery());
			
			ArrayList<TipoContaVO> retorno = new ArrayList<TipoContaVO>();
			
			while (sRs.next()) {
				
				TipoContaVO vo = new TipoContaVO();
				
				vo.setCodigo(sRs.getInteger(1));
				vo.setDescricao(sRs.getString(2));
				
				retorno.add(vo);
				
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

}
