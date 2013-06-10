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
import br.org.asserjuf.sisjuf.dados.SisjufDAOPostgres;

/**
 * Classe de acesso ao banco de dados da entidade "Atividade Convenio" do Sisjuf
 * @author Paulo
 *
 */
public class AtividadeConvenioDAO extends SisjufDAOPostgres{

	private static transient Logger	LOG = Logger.getLogger(AtividadeConvenioDAO.class);
	
	/**
	 * Obtém todos as atividades de convenios cadastrados na banco de dados. Tabelas: ATIVIDADE_CONVENIO
	 * @return Coleção de AtividadeConvenioVO (objeto que representa a entidade "Atividade Convenio")
	 * @throws SmartEnvException
	 */
	public Collection<AtividadeConvenioVO> findAll() throws SmartEnvException {
		
		StringBuffer sql = new StringBuffer("SELECT SEQ_ATIVIDADE_CONVENIO, NOM_ATIVIDADE_CONVENIO, FLG_DESATIVADO FROM ATIVIDADE_CONVENIO ORDER BY NOM_ATIVIDADE_CONVENIO ");

		SmartConnection 		sConn 	= null;
		SmartPreparedStatement 	sStmt 	= null;
		SmartResultSet			sRs		= null;
		try {
		
			sConn 	= new SmartConnection(this.getConn());
			sStmt 	= new SmartPreparedStatement(sConn.prepareStatement(sql.toString()));
			sRs 	= new SmartResultSet(sStmt.getMyPreparedStatement().executeQuery());
			
			return sRs.getJavaBeans(AtividadeConvenioVO.class, new String[]{"codigo", "nome", "desativo"});
		} catch (SQLException e) {
			throw new SmartEnvException(e);
		} finally {
			sRs.close();
			sStmt.close();
			sConn.close();
		}
	}
	
	/**
	 * Obtém todos as atividades de convenios cadastrados na banco de dados. Tabelas: ATIVIDADE_CONVENIO
	 * @return Coleção de AtividadeConvenioVO (objeto que representa a entidade "Atividade Convenio")
	 * @throws SmartEnvException
	 */
	public Collection<AtividadeConvenioVO> findAllAtivas() throws SmartEnvException {
		
		StringBuffer sql = new StringBuffer("SELECT SEQ_ATIVIDADE_CONVENIO, NOM_ATIVIDADE_CONVENIO, FLG_DESATIVADO FROM ATIVIDADE_CONVENIO WHERE FLG_DESATIVADO = 'N' ORDER BY NOM_ATIVIDADE_CONVENIO ");

		SmartConnection 		sConn 	= null;
		SmartPreparedStatement 	sStmt 	= null;
		SmartResultSet			sRs		= null;
		try {
		
			sConn 	= new SmartConnection(this.getConn());
			sStmt 	= new SmartPreparedStatement(sConn.prepareStatement(sql.toString()));
			sRs 	= new SmartResultSet(sStmt.getMyPreparedStatement().executeQuery());
			
			return sRs.getJavaBeans(AtividadeConvenioVO.class, new String[]{"codigo", "nome", "desativo"});
		} catch (SQLException e) {
			throw new SmartEnvException(e);
		} finally {
			sRs.close();
			sStmt.close();
			sConn.close();
		}
	}	
	
	/**
	 * Obtém uma Atividade (encapsulado no objeto AtividadeConvenioVO) cadastrada no banco de dados. Tabelas: ATIVIDADE_CONVENIO
	 * @param vo Instância da classe AtividadeConvenioVO contendo a propriedade codigo preenchida.
	 * @return Retorna uma instância da classe AtividadeConvenioVO com as propriedades preenchidas.
	 * @throws SmartEnvException
	 */
	public AtividadeConvenioVO findByPrimaryKey(AtividadeConvenioVO vo) throws SmartEnvException {
		
		StringBuffer sql = new StringBuffer("SELECT SEQ_ATIVIDADE_CONVENIO, NOM_ATIVIDADE_CONVENIO, FLG_DESATIVADO FROM ATIVIDADE_CONVENIO WHERE SEQ_ATIVIDADE_CONVENIO = ? ");

		SmartConnection 		sConn 	= null;
		SmartPreparedStatement 	sStmt 	= null;
		SmartResultSet			sRs		= null;
		
		try {
			sConn 	= new SmartConnection(this.getConn());
			sStmt 	= new SmartPreparedStatement(sConn.prepareStatement(sql.toString()));
			
			sStmt.setInteger(1, vo.getCodigo());
			
			sRs = new SmartResultSet(sStmt.getMyPreparedStatement().executeQuery());
			  
			return (AtividadeConvenioVO) sRs.getJavaBean(vo, new String[] {"codigo", "nome", "desativo"});
		} catch (SQLException e) {
			throw new SmartEnvException(e);
		} finally {
			sRs.close();
			sStmt.close();
			sConn.close();
		}
	}
	
	/**
	 * Atualiza os dados de uma atividade no banco de dados. Tabelas: ATIVIDADE_CONVENIO
	 * @param vo Instância da classe AtividadeConvenioVO com os novos valores do registro a ser atualizado.
	 * @throws SmartEnvException
	 */
	public void update(AtividadeConvenioVO vo) throws SmartEnvException {
		
		StringBuffer sql = new StringBuffer("UPDATE ATIVIDADE_CONVENIO SET NOM_ATIVIDADE_CONVENIO = ?, FLG_DESATIVADO = ? WHERE SEQ_ATIVIDADE_CONVENIO = ? ");

		SmartConnection 		sConn 	= null;
		SmartPreparedStatement 	sStmt 	= null;
		
		try {
		
			sConn 	= new SmartConnection(this.getConn());
			sStmt 	= new SmartPreparedStatement(sConn.prepareStatement(sql.toString()));
			
			sStmt.setParameters(vo, new String[]{"nome","desativo","codigo"});			
						
			sStmt.getMyPreparedStatement().execute();
				
		} catch (SQLException e) {
			throw new SmartEnvException(e);
		} finally {
			sStmt.close();
			sConn.close();
		}
	}

	/**
	 * Insere uma atividade no banco de dados. Tabelas: ATIVIDADE_CONVENIO
	 * @param vo Instância da classe AtividadeConvenioVO com os valores do registro a ser inserido.
	 * @throws SmartEnvException
	 */
	public AtividadeConvenioVO insert(AtividadeConvenioVO vo) throws SmartEnvException {
		
		StringBuffer sql = new StringBuffer(" INSERT INTO ATIVIDADE_CONVENIO (SEQ_ATIVIDADE_CONVENIO, NOM_ATIVIDADE_CONVENIO, FLG_DESATIVADO) VALUES (?, ?, ?) ");
		
		SmartConnection 		sConn 	= null;
		SmartPreparedStatement 	sStmt 	= null;
		
		try {
		
			sConn 	= new SmartConnection(this.getConn());
			sStmt 	= new SmartPreparedStatement(sConn.prepareStatement(sql.toString()));
			
			vo.setCodigo(new Integer(getSequence("SEQ_ATIVIDADE_CONVENIO").intValue()));
						
			sStmt.setParameters(vo, new String[]{"codigo", "nome","desativo"});
						
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
	 * Exclui uma atividade do banco de dados. Tabelas: ATIVIDADE_CONVENIO
	 * @param vo Instância da classe AtividadeConvenioVO com o código do registro a ser excluído.
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public void remove(AtividadeConvenioVO vo) throws SmartEnvException, SmartAppException {
		
		StringBuffer sql = new StringBuffer("DELETE FROM ATIVIDADE_CONVENIO WHERE SEQ_ATIVIDADE_CONVENIO = ? ");

		SmartConnection 		sConn 	= null;
		SmartPreparedStatement 	sStmt 	= null;
		
		try {
		
			sConn 	= new SmartConnection(this.getConn());
			sStmt 	= new SmartPreparedStatement(sConn.prepareStatement(sql.toString()));			
	
			sStmt.setInteger(1, vo.getCodigo());
						
			sStmt.getMyPreparedStatement().execute();
				
		} catch (SQLException e) {
			// Verificando se o erro foi por causa da violação de alguma FK.
			
			if (e.getMessage().indexOf("fk_atividade_convenio_convenio") != -1 || e.getMessage().indexOf("fk_c_atividade_convenio") != -1 ) {
				// Exceção levantada pela presença de contas.
				throw new SmartAppException("Não é possível excluir esta atividade, pois há convênio(s) associado(s) a ela.");
			}
			
			// Como não foi violação de FK, levantar exceção de ambiente.			
			throw new SmartEnvException(e);
		}finally {
			sStmt.close();
			sConn.close();
		}
	}	
}
