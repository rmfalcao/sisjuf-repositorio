package br.org.asserjuf.sisjuf.associados.dados;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import br.com.falc.smartFW.exception.SmartAppException;
import br.com.falc.smartFW.exception.SmartEnvException;
import br.com.falc.smartFW.persistence.SmartConnection;
import br.com.falc.smartFW.persistence.SmartPreparedStatement;
import br.com.falc.smartFW.persistence.SmartResultSet;
import br.org.asserjuf.sisjuf.associados.AssociadoVO;
import br.org.asserjuf.sisjuf.associados.FilhoVO;
import br.org.asserjuf.sisjuf.dados.SisjufDAOPostgres;

public class FilhoDAO extends SisjufDAOPostgres {

	public Collection<FilhoVO> findByAssociado(AssociadoVO associado)  throws SmartEnvException {
		
		StringBuffer sql = new StringBuffer(" select seq_filho, nom_filho, dat_nascimento_filho, sts_sexo_filho, seq_associado, num_rg_filho, num_cpf_filho ")
		.append(" from vw_filho where seq_associado = ? ");
		
		SmartConnection 		sConn 	= null;
		SmartPreparedStatement 	sStmt 	= null;
		SmartResultSet			sRs		= null;
		
		try {
		
			sConn 	= new SmartConnection(this.getConn());
			sStmt 	= new SmartPreparedStatement(sConn.prepareStatement(sql.toString()));
			
			sStmt.setInteger(1, associado.getCodigo());
			
			sRs 	= new SmartResultSet(sStmt.getMyPreparedStatement().executeQuery());
		
			ArrayList<FilhoVO> retorno = new ArrayList<FilhoVO>(); 
			
			while (sRs.next()) {
				
				FilhoVO filho = new FilhoVO();
				AssociadoVO associadoPai = new AssociadoVO();

				filho.setCodigo(sRs.getInteger(1));
				filho.setNome(sRs.getString(2));
				filho.setDataNascimento(sRs.getDate(3));
				filho.setSexo(sRs.getString(4));
				associadoPai.setCodigo(sRs.getInteger(5));
				filho.setAssociado(associadoPai);
				filho.setRg(sRs.getLong(6));
				filho.setCpf(sRs.getLong(7));

				retorno.add(filho);

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
	
	public FilhoVO insert(FilhoVO filho) throws SmartEnvException {
		
		StringBuffer sql = new StringBuffer(" insert into filho (seq_pessoa, seq_associado, num_rg_filho, num_cpf_filho) values (?, ?, ?, ?) ");

		SmartConnection 		sConn 	= null;
		SmartPreparedStatement 	sStmt 	= null;
				
		try {
		
			sConn 	= new SmartConnection(this.getConn());
			sStmt 	= new SmartPreparedStatement(sConn.prepareStatement(sql.toString()));
			
			sStmt.setInteger(1, filho.getCodigo());
			sStmt.setInteger(2, filho.getAssociado().getCodigo());
			sStmt.setLong(3, filho.getRg());
			sStmt.setLong(4, filho.getCpf());

			sStmt.getMyPreparedStatement().execute();
			
			return filho;			
		
		} catch (SQLException e) {
			throw new SmartEnvException(e);

		} finally {

			sStmt.close();
			sConn.close();

		}
		
	}
	
	public void remove(FilhoVO filho) throws SmartEnvException, SmartAppException {
		
		
		StringBuffer sql = new StringBuffer("DELETE FROM FILHO WHERE SEQ_PESSOA = ? ");

		SmartConnection 		sConn 	= null;
		SmartPreparedStatement 	sStmt 	= null;
		
		try {
		
			sConn 	= new SmartConnection(this.getConn());
			sStmt 	= new SmartPreparedStatement(sConn.prepareStatement(sql.toString()));			

			sStmt.setInteger(1, filho.getCodigo());
									
			sStmt.getMyPreparedStatement().execute();
							
		} catch (SQLException e) {
			throw new SmartEnvException(e);

		} finally {
		
			sStmt.close();
			sConn.close();

		}

	}
	
	public void removeByAssociado(AssociadoVO associado) throws SmartEnvException, SmartAppException {
		
		
		StringBuffer sql = new StringBuffer("DELETE FROM FILHO WHERE SEQ_ASSOCIADO = ? AND seq_pessoa NOT IN (SELECT seq_pessoa FROM vinculacao_plano WHERE seq_associado = ?) ");

		SmartConnection 		sConn 	= null;
		SmartPreparedStatement 	sStmt 	= null;
		
		try {
		
			sConn 	= new SmartConnection(this.getConn());
			sStmt 	= new SmartPreparedStatement(sConn.prepareStatement(sql.toString()));			

			sStmt.setInteger(1, associado.getCodigo());
			sStmt.setInteger(2, associado.getCodigo());
									
			sStmt.getMyPreparedStatement().execute();
							
		} catch (SQLException e) {
			throw new SmartEnvException(e);

		} finally {
		
			sStmt.close();
			sConn.close();

		}

	}
	
	
	public FilhoVO findByPrimaryKey(FilhoVO filho)  throws SmartEnvException {
		
		StringBuffer sql = new StringBuffer(" select seq_filho, nom_filho, dat_nascimento_filho, sts_sexo_filho, seq_associado, num_rg_filho, num_cpf_filho ")
		.append(" from vw_filho where seq_filho = ? ");
		
		SmartConnection 		sConn 	= null;
		SmartPreparedStatement 	sStmt 	= null;
		SmartResultSet			sRs		= null;
		
		try {
		
			sConn 	= new SmartConnection(this.getConn());
			sStmt 	= new SmartPreparedStatement(sConn.prepareStatement(sql.toString()));
			
			sStmt.setInteger(1, filho.getCodigo());
			
			sRs 	= new SmartResultSet(sStmt.getMyPreparedStatement().executeQuery());					 
			
			FilhoVO lFilho = new FilhoVO();
			while (sRs.next()) {
				AssociadoVO associadoPai = new AssociadoVO();

				lFilho.setCodigo(sRs.getInteger(1));
				lFilho.setNome(sRs.getString(2));
				lFilho.setDataNascimento(sRs.getDate(3));
				lFilho.setSexo(sRs.getString(4));
				associadoPai.setCodigo(sRs.getInteger(5));
				lFilho.setAssociado(associadoPai);
				lFilho.setRg(sRs.getLong(6));
				lFilho.setCpf(sRs.getLong(7));
			}
			
			return lFilho;
		
		} catch (SQLException e) {
			throw new SmartEnvException(e);

		} finally {

			sRs.close();
			sStmt.close();
			sConn.close();

		}
		
	}	
}
