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
import br.org.asserjuf.sisjuf.associados.DependenteVO;
import br.org.asserjuf.sisjuf.associados.ParentescoVO;
import br.org.asserjuf.sisjuf.dados.SisjufDAOPostgres;

public class DependenteDAO extends SisjufDAOPostgres {

	public Collection<DependenteVO> findByAssociado(AssociadoVO associado)  throws SmartEnvException {
		
		StringBuffer sql = new StringBuffer(" select seq_dependente, nom_dependente, dat_nascimento_dependente, sts_sexo_dependente,num_rg_dependente, seq_parentesco, des_parentesco_dependente, seq_associado, num_cpf_dependente ")
		.append(" from vw_dependente where seq_associado = ? ");
	
		SmartConnection 		sConn 	= null;
		SmartPreparedStatement 	sStmt 	= null;
		SmartResultSet			sRs		= null;
		
		try {
		
			sConn 	= new SmartConnection(this.getConn());
			sStmt 	= new SmartPreparedStatement(sConn.prepareStatement(sql.toString()));
			
			sStmt.setInteger(1, associado.getCodigo());
			
			sRs 	= new SmartResultSet(sStmt.getMyPreparedStatement().executeQuery());
		
			ArrayList<DependenteVO> retorno = new ArrayList<DependenteVO>(); 
			
			while (sRs.next()) {
				
				DependenteVO dependente = new DependenteVO();
				AssociadoVO associadoPai = new AssociadoVO();
				ParentescoVO parentesco = new ParentescoVO();

				dependente.setCodigo(sRs.getInteger(1));
				dependente.setNome(sRs.getString(2));
				dependente.setDataNascimento(sRs.getDate(3));
				dependente.setSexo(sRs.getString(4));
				dependente.setRg(sRs.getLong(5));
				parentesco.setCodigo(sRs.getShort(6));
				parentesco.setNome(sRs.getString(7));
				dependente.setParentesco(parentesco);
				associadoPai.setCodigo(sRs.getInteger(8));
				dependente.setAssociado(associadoPai);
				dependente.setCpf(sRs.getLong(9));

				retorno.add(dependente);

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
	
	public DependenteVO insert(DependenteVO dependente) throws SmartEnvException {
		
		StringBuffer sql = new StringBuffer(" insert into dependente (seq_pessoa, num_rg_dependente, seq_parentesco, seq_associado, num_cpf_dependente) values (?, ?, ?, ?, ?) ");

		SmartConnection 		sConn 	= null;
		SmartPreparedStatement 	sStmt 	= null;
				
		try {
		
			sConn 	= new SmartConnection(this.getConn());
			sStmt 	= new SmartPreparedStatement(sConn.prepareStatement(sql.toString()));
			
			sStmt.setInteger(1, dependente.getCodigo());
			sStmt.setLong(2, dependente.getRg());
			sStmt.setShort(3, dependente.getParentesco().getCodigo());
			sStmt.setInteger(4, dependente.getAssociado().getCodigo());
			sStmt.setLong(5, dependente.getCpf());

			sStmt.getMyPreparedStatement().execute();
			
			return dependente;			
		
		} catch (SQLException e) {
			throw new SmartEnvException(e);

		} finally {

			sStmt.close();
			sConn.close();

		}
		
	}
	
	public DependenteVO update(DependenteVO dependente) throws SmartEnvException {
		
		StringBuffer sql = new StringBuffer(" UPDATE dependente SET num_rg_dependente = ?, seq_parentesco = ?, num_cpf_dependente = ? WHERE seq_pessoa = ? AND seq_associado = ? ");

		SmartConnection 		sConn 	= null;
		SmartPreparedStatement 	sStmt 	= null;
				
		try {
		
			sConn 	= new SmartConnection(this.getConn());
			sStmt 	= new SmartPreparedStatement(sConn.prepareStatement(sql.toString()));
			
			sStmt.setLong(1, dependente.getRg());
			sStmt.setShort(2, dependente.getParentesco().getCodigo());
			sStmt.setLong(3, dependente.getCpf());
			sStmt.setInteger(4, dependente.getCodigo());
			sStmt.setInteger(5, dependente.getAssociado().getCodigo());

			sStmt.getMyPreparedStatement().execute();
			
			return dependente;			
		
		} catch (SQLException e) {
			throw new SmartEnvException(e);

		} finally {

			sStmt.close();
			sConn.close();

		}
		
	}	
	
	public void remove(DependenteVO dependente) throws SmartEnvException, SmartAppException {
		
		
		StringBuffer sql = new StringBuffer("DELETE FROM DEPENDENTE WHERE SEQ_PESSOA = ? ");

		SmartConnection 		sConn 	= null;
		SmartPreparedStatement 	sStmt 	= null;
		
		try {
		
			sConn 	= new SmartConnection(this.getConn());
			sStmt 	= new SmartPreparedStatement(sConn.prepareStatement(sql.toString()));			

			sStmt.setInteger(1, dependente.getCodigo());
									
			sStmt.getMyPreparedStatement().execute();
							
		} catch (SQLException e) {
			throw new SmartEnvException(e);

		} finally {
		
			sStmt.close();
			sConn.close();

		}

	}

	public void removeByAssociado(AssociadoVO associado) throws SmartEnvException, SmartAppException {
		
		
		StringBuffer sql = new StringBuffer("DELETE FROM DEPENDENTE WHERE SEQ_ASSOCIADO = ? AND SEQ_PESSOA NOT IN (SELECT SEQ_PESSOA FROM VINCULACAO_PLANO WHERE SEQ_ASSOCIADO = ?) ");

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
	
	
	public DependenteVO findByPrimaryKey(DependenteVO pDependenteVO)  throws SmartEnvException {
		
		StringBuffer sql = new StringBuffer(" select seq_dependente, nom_dependente, dat_nascimento_dependente, sts_sexo_dependente,num_rg_dependente, seq_parentesco, des_parentesco_dependente, seq_associado, num_cpf_dependente ")
		.append(" from vw_dependente where seq_dependente = ? ");
	
		SmartConnection 		sConn 	= null;
		SmartPreparedStatement 	sStmt 	= null;
		SmartResultSet			sRs		= null;
		
		try {
		
			sConn 	= new SmartConnection(this.getConn());
			sStmt 	= new SmartPreparedStatement(sConn.prepareStatement(sql.toString()));
			
			sStmt.setInteger(1, pDependenteVO.getCodigo());
			
			sRs 	= new SmartResultSet(sStmt.getMyPreparedStatement().executeQuery());					 
			
			DependenteVO lDependenteVO = new DependenteVO();
			
			while (sRs.next()) {				
				AssociadoVO associadoPai = new AssociadoVO();
				ParentescoVO parentesco = new ParentescoVO();

				lDependenteVO.setCodigo(sRs.getInteger(1));
				lDependenteVO.setNome(sRs.getString(2));
				lDependenteVO.setDataNascimento(sRs.getDate(3));
				lDependenteVO.setSexo(sRs.getString(4));
				lDependenteVO.setRg(sRs.getLong(5));
				parentesco.setCodigo(sRs.getShort(6));
				parentesco.setNome(sRs.getString(7));
				lDependenteVO.setParentesco(parentesco);
				associadoPai.setCodigo(sRs.getInteger(8));
				lDependenteVO.setAssociado(associadoPai);
				lDependenteVO.setCpf(sRs.getLong(9));
			}
			
			return lDependenteVO;
		
		} catch (SQLException e) {
			throw new SmartEnvException(e);

		} finally {

			sRs.close();
			sStmt.close();
			sConn.close();

		}
		
	}	
	
}
