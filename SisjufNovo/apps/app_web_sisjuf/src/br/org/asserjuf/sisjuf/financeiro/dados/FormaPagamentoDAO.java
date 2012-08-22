package br.org.asserjuf.sisjuf.financeiro.dados;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import br.com.falc.smartFW.persistence.SmartPreparedStatement;
import br.com.falc.smartFW.persistence.SmartResultSet;
import br.org.asserjuf.sisjuf.dados.SisjufDAOPostgres;
import br.org.asserjuf.sisjuf.financeiro.FormaPagamentoVO;
import br.com.falc.smartFW.exception.SmartEnvException;
import br.com.falc.smartFW.persistence.SmartConnection;


/**
 * Classe de acesso ao banco de dados da entidade "Forma de Pagamento" do Sisjuf.
 * @author Rodrigo Falcão
 *
 */
public class FormaPagamentoDAO extends SisjufDAOPostgres {
	
	/**
	 * Obtém a coleção de todas as formas de pagamento ativas do banco de dados. Tabelas: FORMA_PAGAMENTO.
	 * @return Coleção de formas de pagamento, encapsuladas pela classe FormaPagamentoVO.
	 * @throws SmartEnvException
	 */
	public Collection<FormaPagamentoVO> findAll() throws SmartEnvException {
		
		StringBuffer sql = new StringBuffer("SELECT SEQ_FORMA_PAGAMENTO, NOM_FORMA_PAGAMENTO ");
		sql.append(" FROM FORMA_PAGAMENTO ORDER BY NOM_FORMA_PAGAMENTO ");

		SmartConnection 		sConn 	= null;
		SmartPreparedStatement 	sStmt 	= null;
		SmartResultSet			sRs		= null;
		
		try {
		
			sConn 	= new SmartConnection(this.getConn());
			sStmt 	= new SmartPreparedStatement(sConn.prepareStatement(sql.toString()));
			sRs 	= new SmartResultSet(sStmt.getMyPreparedStatement().executeQuery());
			
			ArrayList<FormaPagamentoVO> retorno = new ArrayList<FormaPagamentoVO>(); 
			
			while (sRs.next()) {

				FormaPagamentoVO formaPagamentoVO = new FormaPagamentoVO();

				formaPagamentoVO.setCodigo(sRs.getInteger(1));
				formaPagamentoVO.setNome(sRs.getString(2));
				

				retorno.add(formaPagamentoVO);

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
