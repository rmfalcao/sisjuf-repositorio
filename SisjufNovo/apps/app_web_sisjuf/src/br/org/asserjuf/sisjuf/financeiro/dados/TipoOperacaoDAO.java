package br.org.asserjuf.sisjuf.financeiro.dados;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import br.com.falc.smartFW.persistence.SmartPreparedStatement;
import br.com.falc.smartFW.persistence.SmartResultSet;
import br.org.asserjuf.sisjuf.dados.SisjufDAOPostgres;
import br.org.asserjuf.sisjuf.financeiro.TipoOperacaoVO;
import br.com.falc.smartFW.exception.SmartEnvException;
import br.com.falc.smartFW.persistence.SmartConnection;


/**
 * Classe de acesso ao banco de dados da entidade "Tipo de Opera��o" do Sisjuf.
 * @author Rodrigo Falc�o
 *
 */
public class TipoOperacaoDAO extends SisjufDAOPostgres {
	
	/**
	 * Obt�m a cole��o de todos os tipos de opera��o do banco de dados. Tabelas: TIPO_OPERACAO.
	 * @return Cole��o de tipos de opera��o, encapsulados pela classe TipoOperacaoVO.
	 * @throws SmartEnvException
	 */	
	public Collection findAll() throws SmartEnvException {
		
		StringBuffer sql = new StringBuffer("SELECT SEQ_TIPO_OPERACAO, NOM_TIPO_OPERACAO, SIG_TIPO_OPERACAO, ");
		sql.append("FLG_PREVISAO_TIPO_OPERACAO, FLG_DEBITO_TIPO_OPERACAO FROM TIPO_OPERACAO ORDER BY NOM_TIPO_OPERACAO ");
		
		SmartConnection 				sConn 	= null;
		SmartPreparedStatement 	sStmt 	= null;
		SmartResultSet					sRs		= null;
		
		try {
		
			sConn 	= new SmartConnection(this.getConn());
			sStmt 	= new SmartPreparedStatement(sConn.prepareStatement(sql.toString()));
			sRs 	= new SmartResultSet(sStmt.getMyPreparedStatement().executeQuery());
			
			ArrayList retorno = new ArrayList();
			
			while (sRs.next()) {
				
				TipoOperacaoVO vo = new TipoOperacaoVO();
				
				vo.setCodigo(sRs.getInteger(1));
				vo.setNome(sRs.getString(2));
				vo.setSigla(sRs.getString(3));
				vo.setFlgPrevisao(sRs.getString(4));
				vo.setFlgDebito(sRs.getString(5));
								
				retorno.add(vo);
				
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
	 * Obt�m um tipo de opera��o do banco de dados. Tabelas: TIPO_OPERACAO
	 * @param vo Inst�ncia da classe TipoOperacaoVO que cont�m o c�digo do tipo de opera��o que se deseja obter.
	 * @return Tipo de opera��o pesquisada.
	 * @throws SmartEnvException
	 */
	public TipoOperacaoVO findByPrimaryKey(TipoOperacaoVO vo) throws SmartEnvException {
		
		
		StringBuffer sql = new StringBuffer("SELECT SEQ_TIPO_OPERACAO, NOM_TIPO_OPERACAO, SIG_TIPO_OPERACAO, ");
		sql.append("FLG_PREVISAO_TIPO_OPERACAO, FLG_DEBITO_TIPO_OPERACAO FROM TIPO_OPERACAO WHERE SEQ_TIPO_OPERACAO = ? ");

		SmartConnection 		sConn 	= null;
		SmartPreparedStatement 	sStmt 	= null;
		
		try {
		
			sConn 	= new SmartConnection(this.getConn());
			sStmt 	= new SmartPreparedStatement(sConn.prepareStatement(sql.toString()));
			
			sStmt.setInteger(1, vo.getCodigo());
						
			SmartResultSet sRs = new SmartResultSet(sStmt.getMyPreparedStatement().executeQuery());
			  
			
			if (sRs.next()) {

				TipoOperacaoVO retorno = new TipoOperacaoVO();
				
				retorno.setCodigo(sRs.getInteger(1));
				retorno.setNome(sRs.getString(2));
				retorno.setSigla(sRs.getString(3));
				retorno.setFlgPrevisao(sRs.getString(4));
				retorno.setFlgDebito(sRs.getString(5));
				
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

}
