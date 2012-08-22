package br.org.asserjuf.sisjuf.financeiro.dados;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import br.com.falc.smartFW.persistence.SmartPreparedStatement;
import br.com.falc.smartFW.persistence.SmartResultSet;
import br.org.asserjuf.sisjuf.dados.SisjufDAOPostgres;
import br.org.asserjuf.sisjuf.financeiro.OrigemLancamentoVO;
import br.com.falc.smartFW.exception.SmartAppException;
import br.com.falc.smartFW.exception.SmartEnvException;
import br.com.falc.smartFW.persistence.SmartConnection;


/**
 * Classe de acesso ao banco de dados da entidade "Origem de Lançamento" do Sisjuf.
 * @author Rodrigo Falcão
 *
 */
public class OrigemLancamentoDAO extends SisjufDAOPostgres {
	
	/**
	 * Obtém a coleção de todas as origens de lançamentos ativas do banco de dados. Tabelas: ORIGEM_LANCAMENTO.
	 * @return Coleção de origens de lançamento, encapsuladas pela classe OrigemLancamentoVO.
	 * @throws SmartEnvException
	 */
	public Collection<OrigemLancamentoVO> findAll() throws SmartEnvException {
		
		StringBuffer sql = new StringBuffer("SELECT SEQ_ORIGEM_LANCAMENTO, NOM_ORIGEM_LANCAMENTO, FLG_ATIVO_ORIGEM_LANCAMENTO, ");
		sql.append("FLG_NAO_APAGAVEL_ORIGEM_LANCAMENTO FROM ORIGEM_LANCAMENTO WHERE FLG_ATIVO_ORIGEM_LANCAMENTO = B'1' ORDER BY NOM_ORIGEM_LANCAMENTO ");

		SmartConnection 		sConn 	= null;
		SmartPreparedStatement 	sStmt 	= null;
		SmartResultSet			sRs		= null;
		
		try {
		
			sConn 	= new SmartConnection(this.getConn());
			sStmt 	= new SmartPreparedStatement(sConn.prepareStatement(sql.toString()));
			sRs 	= new SmartResultSet(sStmt.getMyPreparedStatement().executeQuery());
			
			ArrayList<OrigemLancamentoVO> retorno = new ArrayList<OrigemLancamentoVO>(); 
			
			while (sRs.next()) {

				OrigemLancamentoVO origemLancamentoVO = new OrigemLancamentoVO();

				origemLancamentoVO.setCodigo(sRs.getInteger(1));
				origemLancamentoVO.setNome(sRs.getString(2));
				origemLancamentoVO.setFlgAtivo(sRs.getString(3));
				origemLancamentoVO.setFlgNaoApagavel(sRs.getString(4));

				retorno.add(origemLancamentoVO);

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

	
	/**
	 * Obtém uma origem de lançamento. Tabelas: ORIGEM_LANCAMENTO.
	 * @param vo Instância da classe OrigemLancamentoVO com o código da origem de lançamento que se deseja obter.
	 * @return Instância da classe OrigemLancamentoVO que encapsula os dados da origem de lançamento.
	 * @throws SmartEnvException
	 */
	public OrigemLancamentoVO findByPrimaryKey(OrigemLancamentoVO vo) throws SmartEnvException {
		
		
		StringBuffer sql = new StringBuffer("SELECT SEQ_ORIGEM_LANCAMENTO, NOM_ORIGEM_LANCAMENTO, FLG_ATIVO_ORIGEM_LANCAMENTO, ");
		sql.append("FLG_NAO_APAGAVEL_ORIGEM_LANCAMENTO FROM ORIGEM_LANCAMENTO WHERE SEQ_ORIGEM_LANCAMENTO = ? ");

		SmartConnection 		sConn 	= null;
		SmartPreparedStatement 	sStmt 	= null;
		
		try {
		
			sConn 	= new SmartConnection(this.getConn());
			sStmt 	= new SmartPreparedStatement(sConn.prepareStatement(sql.toString()));
			 				
			sStmt.setInteger(1, vo.getCodigo());
						
			SmartResultSet sRs = new SmartResultSet(sStmt.getMyPreparedStatement().executeQuery());
		
			if (sRs.next()) {
				
				OrigemLancamentoVO retorno = new OrigemLancamentoVO();

				retorno.setCodigo(sRs.getInteger(1));
				retorno.setNome(sRs.getString(2));
				retorno.setFlgAtivo(sRs.getString(3));
				retorno.setFlgNaoApagavel(sRs.getString(4));

				return retorno;
				
			}
			
			return null;
		
		} catch (SQLException e) {

			throw new SmartEnvException(e);

		} finally {

			sStmt.close();
			sConn.close();

		}
		
	}

	/**
	 * Atualiza os dados da uma origem de lançamento. Tabelas: ORIGEM_LANCAMENTO.
	 * @param vo Instância da classe OrigemLancamentoVO que encapsula a origem de lançamento que se deseja atualizar.
	 * @throws SmartEnvException
	 */
	public void update(OrigemLancamentoVO vo) throws SmartEnvException {
		
		
		StringBuffer sql = new StringBuffer("UPDATE ORIGEM_LANCAMENTO SET NOM_ORIGEM_LANCAMENTO = ? WHERE SEQ_ORIGEM_LANCAMENTO = ? ");

		SmartConnection 		sConn 	= null;
		SmartPreparedStatement 	sStmt 	= null;
		
		try {
		
			sConn 	= new SmartConnection(this.getConn());
			sStmt 	= new SmartPreparedStatement(sConn.prepareStatement(sql.toString()));
			 				
			sStmt.setString(1, vo.getNome());
			sStmt.setInteger(2, vo.getCodigo());
			
						
			sStmt.getMyPreparedStatement().execute();
				
		} catch (SQLException e) {

			throw new SmartEnvException(e);

		} finally {

			sStmt.close();
			sConn.close();

		}
		
	}

	/**
	 * Insere uma origem de lançamento no banco de dados. Tabelas: ORIGEM_LANCAMENTO.
	 * @param vo Instância de OrigemLancamentoVO que encapsula a origem de lançamento que se deseja inserir.
	 * @return Instância da classe OrigemLancamentoVO que encapsula a origem de lançamento inserida.
	 * @throws SmartEnvException
	 */
	public OrigemLancamentoVO insert(OrigemLancamentoVO vo) throws SmartEnvException {
		
		StringBuffer sql = new StringBuffer(" INSERT INTO ORIGEM_LANCAMENTO (SEQ_ORIGEM_LANCAMENTO, NOM_ORIGEM_LANCAMENTO) VALUES (?, ?) ");

		SmartConnection 		sConn 	= null;
		SmartPreparedStatement 	sStmt 	= null;
		
		try {
		
			sConn 	= new SmartConnection(this.getConn());
			sStmt 	= new SmartPreparedStatement(sConn.prepareStatement(sql.toString()));
			 				
			vo.setCodigo(new Integer(getSequence("SEQ_ORIGEM_LANCAMENTO").intValue()));
			
			sStmt.setInteger(1, vo.getCodigo());
			sStmt.setString(2, vo.getNome());
			
			sStmt.getMyPreparedStatement().execute();
			
			return vo;
				
		} catch (SQLException e) {

			throw new SmartEnvException(e);

		} finally {

			sStmt.close();
			sConn.close();

		}
		
	}

	
	/**
	 * Exclui (deleta) do banco de dados uma origem de lançamento. Tabelas: ORIGEM_LANCAMENTO.
	 * @param vo Instância de OrigemLancamentoVO que encapsula a origem de lançamento que se deseja excluir.
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public void remove(OrigemLancamentoVO vo) throws SmartEnvException, SmartAppException {
				
		StringBuffer sql = new StringBuffer("DELETE FROM ORIGEM_LANCAMENTO WHERE SEQ_ORIGEM_LANCAMENTO = ? ");

		SmartConnection 		sConn 	= null;
		SmartPreparedStatement 	sStmt 	= null;
		
		try {
		
			sConn 	= new SmartConnection(this.getConn());
			sStmt 	= new SmartPreparedStatement(sConn.prepareStatement(sql.toString()));
			 				
			sStmt.setInteger(1, vo.getCodigo());	
						
			sStmt.getMyPreparedStatement().execute();
				
		} catch (SQLException e) {
			// Verificando se o erro foi por causa da violação de alguma FK.
			
			if (e.getMessage().indexOf("fk_lancamento_origem_lancamento") != -1) {
				// Exceção levantada pela presença de lançamentos.
				throw new SmartAppException("Não é possível excluir esta origem, pois há lançamentos associados a ela.");
				
			}
			
			// Como não foi violação de FK, levantar exceção de ambiente.			
			throw new SmartEnvException(e);

		} finally {

			sStmt.close();
			sConn.close();

		}
		
	}
	
}
