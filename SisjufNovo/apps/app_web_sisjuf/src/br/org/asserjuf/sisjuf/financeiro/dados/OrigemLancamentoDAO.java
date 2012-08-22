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
 * Classe de acesso ao banco de dados da entidade "Origem de Lan�amento" do Sisjuf.
 * @author Rodrigo Falc�o
 *
 */
public class OrigemLancamentoDAO extends SisjufDAOPostgres {
	
	/**
	 * Obt�m a cole��o de todas as origens de lan�amentos ativas do banco de dados. Tabelas: ORIGEM_LANCAMENTO.
	 * @return Cole��o de origens de lan�amento, encapsuladas pela classe OrigemLancamentoVO.
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
	 * Obt�m uma origem de lan�amento. Tabelas: ORIGEM_LANCAMENTO.
	 * @param vo Inst�ncia da classe OrigemLancamentoVO com o c�digo da origem de lan�amento que se deseja obter.
	 * @return Inst�ncia da classe OrigemLancamentoVO que encapsula os dados da origem de lan�amento.
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
	 * Atualiza os dados da uma origem de lan�amento. Tabelas: ORIGEM_LANCAMENTO.
	 * @param vo Inst�ncia da classe OrigemLancamentoVO que encapsula a origem de lan�amento que se deseja atualizar.
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
	 * Insere uma origem de lan�amento no banco de dados. Tabelas: ORIGEM_LANCAMENTO.
	 * @param vo Inst�ncia de OrigemLancamentoVO que encapsula a origem de lan�amento que se deseja inserir.
	 * @return Inst�ncia da classe OrigemLancamentoVO que encapsula a origem de lan�amento inserida.
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
	 * Exclui (deleta) do banco de dados uma origem de lan�amento. Tabelas: ORIGEM_LANCAMENTO.
	 * @param vo Inst�ncia de OrigemLancamentoVO que encapsula a origem de lan�amento que se deseja excluir.
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
			// Verificando se o erro foi por causa da viola��o de alguma FK.
			
			if (e.getMessage().indexOf("fk_lancamento_origem_lancamento") != -1) {
				// Exce��o levantada pela presen�a de lan�amentos.
				throw new SmartAppException("N�o � poss�vel excluir esta origem, pois h� lan�amentos associados a ela.");
				
			}
			
			// Como n�o foi viola��o de FK, levantar exce��o de ambiente.			
			throw new SmartEnvException(e);

		} finally {

			sStmt.close();
			sConn.close();

		}
		
	}
	
}
