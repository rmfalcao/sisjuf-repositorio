package br.org.asserjuf.sisjuf.associados.dados;

import java.sql.SQLException;

import br.com.falc.smartFW.exception.SmartAppException;
import br.com.falc.smartFW.exception.SmartEnvException;
import br.com.falc.smartFW.persistence.SmartConnection;
import br.com.falc.smartFW.persistence.SmartPreparedStatement;
import br.com.falc.smartFW.persistence.SmartResultSet;
import br.org.asserjuf.sisjuf.associados.AssociadoVO;
import br.org.asserjuf.sisjuf.associados.ConjugeVO;
import br.org.asserjuf.sisjuf.dados.SisjufDAOPostgres;

public class ConjugeDAO extends SisjufDAOPostgres {

	public ConjugeVO findByAssociado(AssociadoVO associado)  throws SmartEnvException {
		
		StringBuffer sql = new StringBuffer(" SELECT SEQ_CONJUGE, NOM_CONJUGE, DAT_NASCIMENTO_CONJUGE, STS_SEXO_CONJUGE, NUM_RG_CONJUGE, NUM_CPF_CONJUGE, SEQ_ASSOCIADO FROM VW_CONJUGE ")
										.append(" WHERE SEQ_ASSOCIADO = ? ");
	
		SmartConnection 		sConn 	= null;
		SmartPreparedStatement 	sStmt 	= null;
		SmartResultSet			sRs		= null;
		
		try {
		
			sConn 	= new SmartConnection(this.getConn());
			sStmt 	= new SmartPreparedStatement(sConn.prepareStatement(sql.toString()));
			
			sStmt.setInteger(1, associado.getCodigo());
			
			sRs 	= new SmartResultSet(sStmt.getMyPreparedStatement().executeQuery());
					
			return (ConjugeVO) sRs.getJavaBean(new ConjugeVO(), new String[] {"codigo", "nome", "dataNascimento", "sexo", "rg", "cpf", "associado.codigo"});
			
		
		} catch (SQLException e) {
			throw new SmartEnvException(e);

		} finally {

			sRs.close();
			sStmt.close();
			sConn.close();

		}
		
	}
	
	public ConjugeVO insert(ConjugeVO conjuge) throws SmartEnvException {
		
		StringBuffer sql = new StringBuffer(" insert into conjuge (seq_pessoa, num_cpf_conjuge, num_rg_conjuge, seq_associado) values (?, ?, ?, ?) ");

		SmartConnection 		sConn 	= null;
		SmartPreparedStatement 	sStmt 	= null;
				
		try {
		
			sConn 	= new SmartConnection(this.getConn());
			sStmt 	= new SmartPreparedStatement(sConn.prepareStatement(sql.toString()));
			
			sStmt.setParameters(conjuge, new String[] {"codigo", "cpf", "rg", "associado.codigo"});
			
			sStmt.getMyPreparedStatement().execute();
			
			return conjuge;			
		
		} catch (SQLException e) {
			throw new SmartEnvException(e);

		} finally {

			sStmt.close();
			sConn.close();

		}
		
	}
	
	public ConjugeVO update(ConjugeVO conjuge) throws SmartEnvException {
		
		StringBuffer sql = new StringBuffer(" UPDATE conjuge SET num_cpf_conjuge= ?,num_rg_conjuge = ? WHERE seq_pessoa = ? ");

		SmartConnection 		sConn 	= null;
		SmartPreparedStatement 	sStmt 	= null;
				
		try {
		
			sConn 	= new SmartConnection(this.getConn());
			sStmt 	= new SmartPreparedStatement(sConn.prepareStatement(sql.toString()));
			
			sStmt.setParameters(conjuge, new String[] {"cpf", "rg", "codigo"});
			
			sStmt.getMyPreparedStatement().execute();
			
			return conjuge;
		
		} catch (SQLException e) {
			throw new SmartEnvException(e);

		} finally {

			sStmt.close();
			sConn.close();

		}
		
	}	
	
	public void remove(ConjugeVO conjuge) throws SmartEnvException, SmartAppException {
		
		
		StringBuffer sql = new StringBuffer("DELETE FROM CONJUGE WHERE SEQ_ASSOCIADO = ? ");

		SmartConnection 		sConn 	= null;
		SmartPreparedStatement 	sStmt 	= null;
		
		try {
		
			sConn 	= new SmartConnection(this.getConn());
			sStmt 	= new SmartPreparedStatement(sConn.prepareStatement(sql.toString()));			

			sStmt.setParameters(conjuge, new String[] {"associado.codigo"});
									
			sStmt.getMyPreparedStatement().execute();
							
		} catch (SQLException e) {
			throw new SmartEnvException(e);

		} finally {
		
			sStmt.close();
			sConn.close();

		}

	}
	
	public ConjugeVO findByPrimaryKey(ConjugeVO conjuge)  throws SmartEnvException {
		
		StringBuffer sql = new StringBuffer(" SELECT SEQ_CONJUGE, NOM_CONJUGE, DAT_NASCIMENTO_CONJUGE, STS_SEXO_CONJUGE, NUM_RG_CONJUGE, NUM_CPF_CONJUGE, SEQ_ASSOCIADO FROM VW_CONJUGE ")
										.append(" WHERE SEQ_CONJUGE = ? ");
	
		SmartConnection 		sConn 	= null;
		SmartPreparedStatement 	sStmt 	= null;
		SmartResultSet			sRs		= null;
		
		try {
		
			sConn 	= new SmartConnection(this.getConn());
			sStmt 	= new SmartPreparedStatement(sConn.prepareStatement(sql.toString()));
			
			sStmt.setParameters(conjuge, new String[] {"codigo"});
			
			sRs 	= new SmartResultSet(sStmt.getMyPreparedStatement().executeQuery());
					
			return (ConjugeVO) sRs.getJavaBean(new ConjugeVO(), new String[] {"codigo", "nome", "dataNascimento", "sexo", "rg", "cpf", "associado.codigo"});
			
		
		} catch (SQLException e) {
			throw new SmartEnvException(e);

		} finally {

			sRs.close();
			sStmt.close();
			sConn.close();

		}
		
	}	
	
}
