package br.org.asserjuf.sisjuf.associados.dados;

import java.sql.SQLException;

import br.com.falc.smartFW.exception.SmartAppException;
import br.com.falc.smartFW.exception.SmartEnvException;
import br.com.falc.smartFW.persistence.SmartConnection;
import br.com.falc.smartFW.persistence.SmartPreparedStatement;
import br.org.asserjuf.sisjuf.associados.PessoaVO;
import br.org.asserjuf.sisjuf.dados.SisjufDAOPostgres;

public class PessoaDAO extends SisjufDAOPostgres {
	
public PessoaVO insert(PessoaVO pessoa) throws SmartEnvException {
		
		StringBuffer sql = new StringBuffer(" insert into pessoa ")
		.append(" (seq_pessoa, nom_pessoa, dat_nascimento_pessoa, sts_sexo_pessoa) ")
		.append(" values (?, ?, ?, ?) ");

		SmartConnection 		sConn 	= null;
		SmartPreparedStatement 	sStmt 	= null;
				
		try {
		
			sConn 	= new SmartConnection(this.getConn());
			sStmt 	= new SmartPreparedStatement(sConn.prepareStatement(sql.toString()));

			pessoa.setCodigo(new Integer(this.getSequence("seq_pessoa").intValue()));
			
			sStmt.setInteger(1, pessoa.getCodigo());
			sStmt.setString(2, pessoa.getNome());
			sStmt.setDate(3, pessoa.getDataNascimento());
			sStmt.setString(4, pessoa.getSexo());
			
			sStmt.getMyPreparedStatement().execute();
			
			return pessoa;
		
		} catch (SQLException e) {
			throw new SmartEnvException(e);

		} finally {

			sStmt.close();
			sConn.close();

		}
		
	}
	

	public void remove(PessoaVO pessoa) throws SmartEnvException, SmartAppException {
		
		
		StringBuffer sql = new StringBuffer("DELETE FROM PESSOA WHERE SEQ_PESSOA = ? ");
	
		SmartConnection 		sConn 	= null;
		SmartPreparedStatement 	sStmt 	= null;
		
		try {
		
			sConn 	= new SmartConnection(this.getConn());
			sStmt 	= new SmartPreparedStatement(sConn.prepareStatement(sql.toString()));			
	
			sStmt.setInteger(1, pessoa.getCodigo());
									
			sStmt.getMyPreparedStatement().execute();
							
		} catch (SQLException e) {
			throw new SmartEnvException(e);
	
		} finally {
		
			sStmt.close();
			sConn.close();
	
		}
		
	}

	public void update(PessoaVO pessoa) throws SmartEnvException {
		
		StringBuffer sql = new StringBuffer("UPDATE pessoa SET nom_pessoa = ?, dat_nascimento_pessoa = ?, sts_sexo_pessoa = ? WHERE seq_pessoa = ? ");
		SmartConnection 		sConn 	= null;
		SmartPreparedStatement 	sStmt 	= null;
		
		try {
		
			sConn 	= new SmartConnection(this.getConn());
			sStmt 	= new SmartPreparedStatement(sConn.prepareStatement(sql.toString()));
			
			sStmt.setString(1, pessoa.getNome());
			sStmt.setDate(2, pessoa.getDataNascimento());
			sStmt.setString(3, pessoa.getSexo());
			sStmt.setInteger(4, pessoa.getCodigo());
									
			sStmt.getMyPreparedStatement().execute();
				
		} catch (SQLException e) {

			throw new SmartEnvException(e);

		} finally {
		
			sStmt.close();
			sConn.close();

		}
		
	}

	
}
