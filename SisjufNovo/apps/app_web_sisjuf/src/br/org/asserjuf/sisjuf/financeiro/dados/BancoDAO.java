package br.org.asserjuf.sisjuf.financeiro.dados;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

import org.apache.log4j.Logger;

import br.com.falc.smartFW.exception.SmartAppException;
import br.com.falc.smartFW.exception.SmartEnvException;
import br.com.falc.smartFW.persistence.SmartConnection;
import br.com.falc.smartFW.persistence.SmartPreparedStatement;
import br.com.falc.smartFW.persistence.SmartResultSet;
import br.org.asserjuf.sisjuf.dados.SisjufDAOPostgres;
import br.org.asserjuf.sisjuf.financeiro.BancoVO;

/**
 * Classe de acesso ao banco de dados da entidade "Banco" do Sisjuf.
 * @author Rodrigo Falcão
 *
 */
public class BancoDAO extends SisjufDAOPostgres {

	private static transient Logger	LOG = Logger.getLogger(BancoDAO.class);
	
	/**
	 * Obtém todos os bancos cadastrados na banco de dados. Tabelas: BANCO
	 * @return Coleção de BancoVO (objeto que representa a entidade "Banco")
	 * @throws SmartEnvException
	 */
	public Collection<BancoVO> findAll() throws SmartEnvException {
		
		StringBuffer sql = new StringBuffer("SELECT SEQ_BANCO, NOM_BANCO, NUM_BANCO, SIG_BANCO FROM BANCO ORDER BY NOM_BANCO ");

		SmartConnection 		sConn 	= null;
		SmartPreparedStatement 	sStmt 	= null;
		SmartResultSet			sRs		= null;
		try {
		
			sConn 	= new SmartConnection(this.getConn());
			sStmt 	= new SmartPreparedStatement(sConn.prepareStatement(sql.toString()));
			sRs 	= new SmartResultSet(sStmt.getMyPreparedStatement().executeQuery());
			
			return sRs.getJavaBeans(BancoVO.class, new String[]{"codigo", "nome", "numero", "sigla"});
		} catch (SQLException e) {
			throw new SmartEnvException(e);
		} finally {
			sRs.close();
			sStmt.close();
			sConn.close();
		}
	}
	
	/**
	 * Obtém um banco (encapsulado no objeto BancoVO) cadastrado no banco de dados. Tabelas: BANCO
	 * @param vo Instância da classe BancoVO contendo a propriedade codigo preenchida.
	 * @return Retorna uma instância da classe BancoVO com as propriedades do banco preenchidas.
	 * @throws SmartEnvException
	 */
	public BancoVO findByPrimaryKey(BancoVO vo) throws SmartEnvException {
		
		StringBuffer sql = new StringBuffer("SELECT SEQ_BANCO, NOM_BANCO, NUM_BANCO, SIG_BANCO FROM BANCO WHERE SEQ_BANCO = ? ");

		SmartConnection 		sConn 	= null;
		SmartPreparedStatement 	sStmt 	= null;
		SmartResultSet			sRs		= null;
		
		try {
			sConn 	= new SmartConnection(this.getConn());
			sStmt 	= new SmartPreparedStatement(sConn.prepareStatement(sql.toString()));
			
			sStmt.setInteger(1, vo.getCodigo());
			
			sRs = new SmartResultSet(sStmt.getMyPreparedStatement().executeQuery());
			  
			return (BancoVO) sRs.getJavaBean(vo, new String[] {"codigo", "nome", "numero", "sigla"});
		} catch (SQLException e) {
			throw new SmartEnvException(e);
		} finally {
			sRs.close();
			sStmt.close();
			sConn.close();
		}
	}
	
	/**
	 * Atualiza os dados de um banco no banco de dados. Tabelas: BANCO
	 * @param vo Instância da classe BancoVO com os novos valores do registro a ser atualizado.
	 * @throws SmartEnvException
	 */
	public void update(BancoVO vo) throws SmartEnvException {
		
		StringBuffer sql = new StringBuffer("UPDATE BANCO SET NOM_BANCO = ?, NUM_BANCO = ?, SIG_BANCO = ? WHERE SEQ_BANCO = ? ");

		SmartConnection 		sConn 	= null;
		SmartPreparedStatement 	sStmt 	= null;
		
		try {
		
			sConn 	= new SmartConnection(this.getConn());
			sStmt 	= new SmartPreparedStatement(sConn.prepareStatement(sql.toString()));
			
			sStmt.setParameters(vo, new String[]{"nome","numero","sigla","codigo"});			
						
			sStmt.getMyPreparedStatement().execute();
				
		} catch (SQLException e) {
			throw new SmartEnvException(e);
		} finally {
			sStmt.close();
			sConn.close();
		}
	}

	/**
	 * Insere um banco no banco de dados. Tabelas: BANCO
	 * @param vo Instância da classe BancoVO com os valores do registro a ser inserido.
	 * @throws SmartEnvException
	 */
	public BancoVO insert(BancoVO vo) throws SmartEnvException {
		
		StringBuffer sql = new StringBuffer(" INSERT INTO BANCO (SEQ_BANCO, NOM_BANCO, NUM_BANCO, SIG_BANCO) VALUES (?, ?, ?, ?) ");
		
		SmartConnection 		sConn 	= null;
		SmartPreparedStatement 	sStmt 	= null;
		
		try {
		
			sConn 	= new SmartConnection(this.getConn());
			sStmt 	= new SmartPreparedStatement(sConn.prepareStatement(sql.toString()));
			
			vo.setCodigo(new Integer(getSequence("SEQ_BANCO").intValue()));
						
			sStmt.setParameters(vo, new String[]{"codigo", "nome","numero","sigla"});
						
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
	 * Exclui um banco do banco de dados. Tabelas: BANCO
	 * @param vo Instância da classe BancoVO com o código do registro a ser excluído.
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public void remove(BancoVO vo) throws SmartEnvException, SmartAppException {
		
		StringBuffer sql = new StringBuffer("DELETE FROM BANCO WHERE SEQ_BANCO = ? ");

		SmartConnection 		sConn 	= null;
		SmartPreparedStatement 	sStmt 	= null;
		
		try {
		
			sConn 	= new SmartConnection(this.getConn());
			sStmt 	= new SmartPreparedStatement(sConn.prepareStatement(sql.toString()));			
	
			sStmt.setInteger(1, vo.getCodigo());
						
			sStmt.getMyPreparedStatement().execute();
				
		} catch (SQLException e) {
			// Verificando se o erro foi por causa da violação de alguma FK.
			
			if (e.getMessage().indexOf("fk_conta_banco_banco") != -1) {
				// Exceção levantada pela presença de contas.
				throw new SmartAppException("Não é possível excluir este banco, pois há contas associadas a ele.");
			}
			
			// Como não foi violação de FK, levantar exceção de ambiente.			
			throw new SmartEnvException(e);
		}finally {
			sStmt.close();
			sConn.close();
		}
	}
}