package br.org.asserjuf.sisjuf.associados.convenio.dados;

import java.sql.SQLException;
import java.util.Collection;

import org.apache.log4j.Logger;

import br.com.falc.smartFW.exception.SmartAppException;
import br.com.falc.smartFW.exception.SmartEnvException;
import br.com.falc.smartFW.persistence.SmartConnection;
import br.com.falc.smartFW.persistence.SmartPreparedStatement;
import br.com.falc.smartFW.persistence.SmartResultSet;
import br.org.asserjuf.sisjuf.associados.convenio.AtividadeConvenioVO;
import br.org.asserjuf.sisjuf.associados.convenio.BeneficiarioVO;
import br.org.asserjuf.sisjuf.associados.convenio.OutroBeneficiavelVO;
import br.org.asserjuf.sisjuf.dados.SisjufDAOPostgres;

public class OutrosBeneficiaveisDAO extends SisjufDAOPostgres{

	private static transient Logger	LOG = Logger.getLogger(OutrosBeneficiaveisDAO.class);
	

	
	public Collection<OutroBeneficiavelVO> findByAssociado(OutroBeneficiavelVO vo) throws SmartEnvException {
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT "); 
		sql.append(" PA.SEQ_PESSOA, "); 
		sql.append(" PA.NOM_PESSOA, "); 
		sql.append(" P.SEQ_PESSOA, "); 
		sql.append(" P.NOM_PESSOA, "); 
		sql.append(" O.NUM_RG_OUTRO, "); 
		sql.append(" O.NUM_CPF_OUTRO, "); 
		sql.append(" P.STS_SEXO_PESSOA, "); 
		sql.append("P.DAT_NASCIMENTO_PESSOA "); 
		sql.append("FROM "); 
		sql.append("OUTROS_BENEFICIAVEIS O, PESSOA P , PESSOA PA "); 
		sql.append("WHERE "); 
		sql.append("O.SEQ_PESSOA = P.SEQ_PESSOA AND "); 
		sql.append("O.SEQ_ASSOCIADO = PA.SEQ_PESSOA AND ");  
		sql.append("O.SEQ_ASSOCIADO = ? ");

		SmartConnection 		sConn 	= null;
		SmartPreparedStatement 	sStmt 	= null;
		SmartResultSet			sRs		= null;
		try {
		
			sConn 	= new SmartConnection(this.getConn());
			sStmt 	= new SmartPreparedStatement(sConn.prepareStatement(sql.toString()));
			
			sStmt.setInteger(1, vo.getAssociado().getCodigo());
			
			sRs 	= new SmartResultSet(sStmt.getMyPreparedStatement().executeQuery());
			
			return sRs.getJavaBeans(OutroBeneficiavelVO.class, new String[]{
									"associado.codigo",
									"associado.nome",
									"codigo", 
									"nome", 
									"rg",
									"cpf",
									"sexo",
									"dataNascimento"});
		} catch (SQLException e) {
			throw new SmartEnvException(e);
		} finally {
			sRs.close();
			sStmt.close();
			sConn.close();
		}
	}	
	
	/**
	 * Retorna o outro beneficiavel para edição e detalhamento. Tabelas: OUTROS_BENEFICIAVEIS, PESSOA
	 * @param vo
	 * @return
	 * @throws SmartEnvException
	 */
	public OutroBeneficiavelVO findByPrimaryKey(OutroBeneficiavelVO vo) throws SmartEnvException {
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT "); 
		sql.append(" PA.SEQ_PESSOA, "); 
		sql.append(" PA.NOM_PESSOA, "); 
		sql.append(" P.SEQ_PESSOA, "); 
		sql.append(" P.NOM_PESSOA, "); 
		sql.append(" O.NUM_RG_OUTRO, "); 
		sql.append(" O.NUM_CPF_OUTRO, "); 
		sql.append(" P.STS_SEXO_PESSOA, "); 
		sql.append("P.DAT_NASCIMENTO_PESSOA "); 
		sql.append("FROM "); 
		sql.append("OUTROS_BENEFICIAVEIS O, PESSOA P , PESSOA PA "); 
		sql.append("WHERE "); 
		sql.append("O.SEQ_PESSOA = P.SEQ_PESSOA AND "); 
		sql.append("O.SEQ_ASSOCIADO = PA.SEQ_PESSOA AND "); 
		sql.append("O.SEQ_PESSOA = ? AND "); 
		sql.append("O.SEQ_ASSOCIADO = ? ");

		SmartConnection 		sConn 	= null;
		SmartPreparedStatement 	sStmt 	= null;
		SmartResultSet			sRs		= null;
		
		try {
			sConn 	= new SmartConnection(this.getConn());
			sStmt 	= new SmartPreparedStatement(sConn.prepareStatement(sql.toString()));
			
			sStmt.setInteger(1, vo.getCodigo());
			sStmt.setInteger(2, vo.getAssociado().getCodigo());
			
			sRs = new SmartResultSet(sStmt.getMyPreparedStatement().executeQuery());
			  
			return (OutroBeneficiavelVO) sRs.getJavaBean(vo, 
				    new String[] {"associado.codigo",
								  "associado.nome",
								  "codigo", 
								  "nome", 
								  "rg",
								  "cpf",
								  "sexo",
								  "dataNascimento"});
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SmartEnvException(e);
		} finally {
			sRs.close();
			sStmt.close();
			sConn.close();
		}
	}
	
	/**
	 * Atualiza um registro de outro beneficiavel. Tabela: OUTROS_BENEFICIAVEIS
	 * @param vo
	 * @throws SmartEnvException
	 */
	public void update(OutroBeneficiavelVO vo) throws SmartEnvException {
		
		StringBuffer sql = new StringBuffer("UPDATE OUTROS_BENEFICIAVEIS SET NUM_RG_OUTRO = ?, NUM_CPF_OUTRO =? WHERE SEQ_PESSOA = ? AND SEQ_ASSOCIADO = ?");

		SmartConnection 		sConn 	= null;
		SmartPreparedStatement 	sStmt 	= null;
		
		try {
		
			sConn 	= new SmartConnection(this.getConn());
			sStmt 	= new SmartPreparedStatement(sConn.prepareStatement(sql.toString()));
			
			sStmt.setParameters(vo, new String[]{"rg","cpf","codigo","associado.codigo"});			
						
			sStmt.getMyPreparedStatement().execute();
				
		} catch (SQLException e) {
			throw new SmartEnvException(e);
		} finally {
			sStmt.close();
			sConn.close();
		}
	}

	/**
	 * Insere um registro de Outro beneficiavel. Tabela : OUTROS_BENEFICIAVEIS
	 * @param vo
	 * @return
	 * @throws SmartEnvException
	 */
	public OutroBeneficiavelVO insert(OutroBeneficiavelVO vo) throws SmartEnvException {
		
		StringBuffer sql = new StringBuffer(" INSERT INTO OUTROS_BENEFICIAVEIS(SEQ_PESSOA, SEQ_ASSOCIADO,NUM_RG_OUTRO,NUM_CPF_OUTRO) VALUES (?,?,?,?) ");
		
		SmartConnection 		sConn 	= null;
		SmartPreparedStatement 	sStmt 	= null;
		
		try {
		
			sConn 	= new SmartConnection(this.getConn());
			sStmt 	= new SmartPreparedStatement(sConn.prepareStatement(sql.toString()));						
						
			sStmt.setParameters(vo, new String[]{"codigo", "associado.codigo","rg","cpf"});
						
			sStmt.getMyPreparedStatement().execute();
			
			return vo;
				
		} catch (SQLException e) {
			throw new SmartEnvException(e);
		}finally {
			sStmt.close();
			sConn.close();
		}
	}
	
	/**
	 * Exclui um registro de outro beneficiavel. Tabela: OUTROS_BENEFICIAVEIS
	 * @param vo
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public void remove(OutroBeneficiavelVO vo) throws SmartEnvException, SmartAppException {
		
		StringBuffer sql = new StringBuffer("DELETE FROM OUTROS_BENEFICIAVEIS WHERE SEQ_PESSOA = ? AND SEQ_ASSOCIADO = ?");

		SmartConnection 		sConn 	= null;
		SmartPreparedStatement 	sStmt 	= null;
		
		try {
		
			sConn 	= new SmartConnection(this.getConn());
			sStmt 	= new SmartPreparedStatement(sConn.prepareStatement(sql.toString()));			
	
			sStmt.setInteger(1, vo.getCodigo());
			sStmt.setInteger(2, vo.getAssociado().getCodigo());
						
			sStmt.getMyPreparedStatement().execute();
				
		} catch (SQLException e) {
			// Verificando se o erro foi por causa da violação de alguma FK.
			
			if (e.getMessage().indexOf("fk_vp_pessoa") != -1 || e.getMessage().indexOf("fk_vp_pessoa") != -1 ) {
				// Exceção levantada pela presença de contas.
				throw new SmartAppException("Não é possível excluir o registro, pois há plano(s) vinculado(os).");
			}
			
			// Como não foi violação de FK, levantar exceção de ambiente.			
			throw new SmartEnvException(e);
		}finally {
			sStmt.close();
			sConn.close();
		}
	}	
	
	public Boolean verificaVinculacao(OutroBeneficiavelVO vo) throws SmartEnvException {
		
		StringBuffer sql = new StringBuffer("SELECT 1 FROM VINCULACAO_PLANO WHERE SEQ_PESSOA = ? ");

		SmartConnection 		sConn 	= null;
		SmartPreparedStatement 	sStmt 	= null;
		SmartResultSet			sRs		= null;
		
		try {
			sConn 	= new SmartConnection(this.getConn());
			sStmt 	= new SmartPreparedStatement(sConn.prepareStatement(sql.toString()));
			
			sStmt.setInteger(1, vo.getCodigo());
			
			sRs = new SmartResultSet(sStmt.getMyPreparedStatement().executeQuery());
			  
			if (sRs.next()){
				Integer ok = sRs.getInteger(1);
				if (ok != null && ok.intValue() == 1){
					return Boolean.TRUE;
				}
			}
			
			return Boolean.FALSE;
		} catch (SQLException e) {
			throw new SmartEnvException(e);
		} finally {
			sRs.close();
			sStmt.close();
			sConn.close();
		}
	}
	
}

