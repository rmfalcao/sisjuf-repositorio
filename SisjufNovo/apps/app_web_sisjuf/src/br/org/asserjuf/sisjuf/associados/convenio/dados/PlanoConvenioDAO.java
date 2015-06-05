package br.org.asserjuf.sisjuf.associados.convenio.dados;

import java.sql.SQLException;
import java.util.Collection;

import org.apache.log4j.Logger;

import br.com.falc.smartFW.exception.SmartAppException;
import br.com.falc.smartFW.exception.SmartEnvException;
import br.com.falc.smartFW.persistence.SmartConnection;
import br.com.falc.smartFW.persistence.SmartPreparedStatement;
import br.com.falc.smartFW.persistence.SmartResultSet;
import br.org.asserjuf.sisjuf.associados.convenio.PlanoConvenioVO;
import br.org.asserjuf.sisjuf.dados.SisjufDAOPostgres;

/**
 * Classe de acesso ao banco de dados da entidade "Plano Convenio" do Sisjuf
 * @author Paulo
 *
 */
public class PlanoConvenioDAO extends SisjufDAOPostgres{

	private static transient Logger	LOG = Logger.getLogger(PlanoConvenioDAO.class);
	
	/**
	 * Obt�m todos os planos de convenios cadastrados na banco de dados. Tabelas: PLANO_CONVENIO, CONVENIO , ATIVIDADE_CONVENIO , ESTADO
	 * @return Cole��o de PlanoConvenioVO (objeto que representa a entidade "Plano Convenio")
	 * @throws SmartEnvException
	 */
	public Collection<PlanoConvenioVO> findAll() throws SmartEnvException {
		
		StringBuffer SQL = new StringBuffer();
		SQL.append("  SELECT   PC.SEQ_PLANO             , ");
		SQL.append("           PC.NOM_PLANO             , ");
		SQL.append("           PC.DES_PLANO             , ");
		SQL.append("           PC.VAL_PLANO             , ");
		SQL.append("           PC.DAT_INICIO_VALOR      , ");
		SQL.append("           PC.FLG_DESATIVADO             , ");
		SQL.append("           PC.FLG_DEDUTIVEL         , ");
		SQL.append("           C.SEQ_CONVENIO           , ");
		SQL.append("           C.FLG_CATEGORIA          , ");
		SQL.append("           C.NOM_FANTASIA           , ");
		SQL.append("           C.NOM_RAZAO_SOCIAL       , ");
		SQL.append("           C.DES_CNPJ               , ");
		SQL.append("           C.DES_LOGRADOURO         , ");
		SQL.append("           C.DES_COMPLEMENTO        , ");
		SQL.append("           C.NOM_BAIRRO             , ");
		SQL.append("           C.NOM_CIDADE             , ");
		SQL.append("           E.SEQ_ESTADO             , ");
		SQL.append("           E.SIG_ESTADO             , ");
		SQL.append("           E.NOM_ESTADO             , ");
		SQL.append("           AC.SEQ_ATIVIDADE_CONVENIO, ");
		SQL.append("           AC.NOM_ATIVIDADE_CONVENIO, ");
		SQL.append("           AC.FLG_DESATIVADO             , ");
		SQL.append("           C.DES_CEP                , ");
		SQL.append("           C.NOM_CONTATO            , ");
		SQL.append("           C.DES_TELEFONE           , ");
		SQL.append("           C.DES_TELEFONE_ADICIONAL , ");
		SQL.append("           C.DES_FAX                , ");
		SQL.append("           C.DES_EMAIL              , ");
		SQL.append("           C.DES_BENEFICIO          , ");
		SQL.append("           C.DES_CONVENIO           , ");
		SQL.append("           C.NUM_DIA_FECHAMENTO     , ");
		SQL.append("           C.NUM_FATOR_MES_VCTO     , ");
		SQL.append("           C.NUM_DIA_VCTO           , ");
		SQL.append("           C.FLG_CONJUGE            , ");
		SQL.append("           C.FLG_FILHOS             , ");
		SQL.append("           C.FLG_DEPENDENTES        , ");
		SQL.append("           C.FLG_OUTROS_BENEF       , ");
		SQL.append("           C.FLG_TITULAR            , ");
		SQL.append("           C.FLG_DESATIVADO ");
		SQL.append("  FROM     PLANO_CONVENIO PC    , ");
		SQL.append("           CONVENIO C           , ");
		SQL.append("           ATIVIDADE_CONVENIO AC, ");
		SQL.append("           ESTADO E ");
		SQL.append("  WHERE    PC.SEQ_CONVENIO          = C.SEQ_CONVENIO ");
		SQL.append("  AND      C.SEQ_ATIVIDADE_CONVENIO = AC.SEQ_ATIVIDADE_CONVENIO ");
		SQL.append("  AND      C.SEQ_ESTADO             = E.SEQ_ESTADO ");
		SQL.append("  ORDER BY PC.NOM_PLANO");

		SmartConnection 		sConn 	= null;
		SmartPreparedStatement 	sStmt 	= null;
		SmartResultSet			sRs		= null;
		try {
		
			sConn 	= new SmartConnection(this.getConn());
			sStmt 	= new SmartPreparedStatement(sConn.prepareStatement(SQL.toString()));
			sRs 	= new SmartResultSet(sStmt.getMyPreparedStatement().executeQuery());
			
			return sRs.getJavaBeans(PlanoConvenioVO.class, new String[]{
				"codigo",
				"nome",
				"descricao",
				"dataInicio",
				"desativado",
				"dedutivel",
				"convenio.codigo", 
				"convenio.categoria", 
				"convenio.nomeFantasia",
				"convenio.razaoSocial",
				"convenio.cnpj",
				"convenio.logradouro",
				"convenio.complemento",
				"convenio.bairro",
				"convenio.cidade",
				"convenio.estado.codigo",
				"convenio.estado.sigla",
				"convenio.estado.nome",
				"convenio.atividadeConvenio.codigo",
				"convenio.atividadeConvenio.nome",
				"convenio.atividadeConvenio.desativo",
				"convenio.cep",
				"convenio.nomeContato",
				"convenio.telefone",
				"convenio.telefoneAdicional",
				"convenio.fax",
				"convenio.email",
				"convenio.beneficio",
				"convenio.descricao",
				"convenio.diaFechamento",
				"convenio.mesVencimento",
				"convenio.diaVencimento",
				"convenio.conjugue",
				"convenio.filhos",
				"convenio.dependentes",
				"convenio.outrosBeneficaiveis",
				"convenio.titular",
				"convenio.ativo"});
		} catch (SQLException e) {
			throw new SmartEnvException(e);
		} finally {
			sRs.close();
			sStmt.close();
			sConn.close();
		}
	}
	
	/**
	 * Obt�m um Plano (encapsulado no objeto PlanoConvenioVO) cadastrada no banco de dados. Tabelas: PLANO_CONVENIO, CONVENIO , ATIVIDADE_CONVENIO , ESTADO
	 * @param vo Inst�ncia da classe PlanoConvenioVO contendo a propriedade codigo preenchida.
	 * @return Retorna uma inst�ncia da classe PlanoConvenioVO com as propriedades preenchidas.
	 * @throws SmartEnvException
	 */
	public PlanoConvenioVO findByPrimaryKey(PlanoConvenioVO vo) throws SmartEnvException {
		
		StringBuffer sql = new StringBuffer();
		sql.append("  SELECT PC.SEQ_PLANO             , "); 
		sql.append("         PC.NOM_PLANO             , "); 
		sql.append("         PC.DES_PLANO             , "); 
		sql.append("         PC.VAL_PLANO             , "); 
		sql.append("         PC.DAT_INICIO_VALOR      , "); 
		sql.append("         PC.FLG_DESATIVADO             , "); 
		sql.append("         PC.FLG_DEDUTIVEL         , "); 
		sql.append("         PC.SEQ_CONVENIO "); 
		sql.append("  FROM   PLANO_CONVENIO PC "); 
		sql.append("  WHERE "); 
		sql.append("  PC.SEQ_PLANO             = ? ");		

		SmartConnection 		sConn 	= null;
		SmartPreparedStatement 	sStmt 	= null;
		SmartResultSet			sRs		= null;
		
		try {
			sConn 	= new SmartConnection(this.getConn());
			sStmt 	= new SmartPreparedStatement(sConn.prepareStatement(sql.toString()));
			
			sStmt.setInteger(1, vo.getCodigo());
			
			sRs = new SmartResultSet(sStmt.getMyPreparedStatement().executeQuery());
			  
			return (PlanoConvenioVO) sRs.getJavaBean(new PlanoConvenioVO(), new String[] {
					"codigo",
					"nome",
					"descricao",
					"valor",
					"dataInicio",
					"desativado",
					"dedutivel",
					"convenio.codigo"});
		} catch (SQLException e) {
			throw new SmartEnvException(e);
		} finally {
			sRs.close();
			sStmt.close();
			sConn.close();
		}
	}
	
	/**
	 * Atualiza os dados de um plano no banco de dados. Tabelas: PLANO_CONVENIO
	 * @param vo Inst�ncia da classe PlanoConvenioVO com os novos valores do registro a ser atualizado.
	 * @throws SmartEnvException
	 */
	public void update(PlanoConvenioVO vo) throws SmartEnvException {
		
		StringBuffer SQL = new StringBuffer();
		SQL.append("UPDATE PLANO_CONVENIO ");
		SQL.append("SET    SEQ_CONVENIO     = ?, ");
		SQL.append("       NOM_PLANO        = ?, ");
		SQL.append("       DES_PLANO        = ?, ");
		SQL.append("       VAL_PLANO        = ?, ");
		SQL.append("       DAT_INICIO_VALOR = ?, ");
		SQL.append("       FLG_DESATIVADO   = ?, ");
		SQL.append("       FLG_DEDUTIVEL    = ? ");
		SQL.append("WHERE  SEQ_PLANO        = ?");

		SmartConnection 		sConn 	= null;
		SmartPreparedStatement 	sStmt 	= null;
		
		try {
		
			sConn 	= new SmartConnection(this.getConn());
			sStmt 	= new SmartPreparedStatement(sConn.prepareStatement(SQL.toString()));
			
			sStmt.setParameters(vo, new String[]{"convenio.codigo","nome","descricao", "valor","dataInicio","desativado","dedutivel","codigo"});			
			
			System.out.println(" convenio.codigo " + vo.getConvenio().getCodigo());
			System.out.println(" nome " + vo.getNome() );
			System.out.println(" descricao " +  vo.getDescricao());
			System.out.println(" valor " +  vo.getValor());
			System.out.println(" dataInicio " +  vo.getDataInicio());
			System.out.println(" desativado " +  vo.getDesativado());
			System.out.println(" dedutivel " +  vo.getDedutivel());
			System.out.println(" codigo " +  vo.getCodigo());
			
			sStmt.getMyPreparedStatement().execute();
				
		} catch (SQLException e) {
			throw new SmartEnvException(e);
		} finally {
			sStmt.close();
			sConn.close();
		}
	}

	/**
	 * Insere um convenio no banco de dados. Tabelas: PLANO_CONVENIO
	 * @param vo Inst�ncia da classe PlanoConvenioVO com os valores do registro a ser inserido.
	 * @throws SmartEnvException
	 */
	public PlanoConvenioVO insert(PlanoConvenioVO vo) throws SmartEnvException {
		
		StringBuffer SQL = new StringBuffer();
		SQL.append("INSERT ");
		SQL.append("INTO   PLANO_CONVENIO ");
		SQL.append("       ( ");
		SQL.append("              SEQ_PLANO       , ");
		SQL.append("              SEQ_CONVENIO    , ");
		SQL.append("              NOM_PLANO       , ");
		SQL.append("              DES_PLANO       , ");
		SQL.append("              VAL_PLANO       , ");
		SQL.append("              DAT_INICIO_VALOR, ");
		SQL.append("              FLG_DESATIVADO       , ");
		SQL.append("              FLG_DEDUTIVEL ");
		SQL.append("       ) ");
		SQL.append("       VALUES ");
		SQL.append("       ( ");
		SQL.append("              ?, ");
		SQL.append("              ?, ");
		SQL.append("              ?, ");
		SQL.append("              ?, ");
		SQL.append("              ?, ");
		SQL.append("              ?, ");
		SQL.append("              ?, ");
		SQL.append("              ? ");
		SQL.append("       )");
		
		SmartConnection 		sConn 	= null;
		SmartPreparedStatement 	sStmt 	= null;
		
		try {
		
			sConn 	= new SmartConnection(this.getConn());
			sStmt 	= new SmartPreparedStatement(sConn.prepareStatement(SQL.toString()));
			
			vo.setCodigo(new Integer(getSequence("SEQ_PLANO_CONVENIO").intValue()));
						
			sStmt.setParameters(vo, new String[]{"codigo","convenio.codigo","nome","descricao","valor","dataInicio","desativado", "dedutivel"});
						
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
	 * Exclui um plano do banco de dados. Tabelas: PLANO_CONVENIO
	 * @param vo Inst�ncia da classe PlanoConvenioVO com o c�digo do registro a ser exclu�do.
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public void remove(PlanoConvenioVO vo) throws SmartEnvException, SmartAppException {
		
		StringBuffer sql = new StringBuffer("DELETE FROM PLANO_CONVENIO WHERE SEQ_PLANO = ? ");

		SmartConnection 		sConn 	= null;
		SmartPreparedStatement 	sStmt 	= null;
		
		try {
		
			sConn 	= new SmartConnection(this.getConn());
			sStmt 	= new SmartPreparedStatement(sConn.prepareStatement(sql.toString()));			
	
			sStmt.setInteger(1, vo.getCodigo());
						
			sStmt.getMyPreparedStatement().execute();
				
		} catch (SQLException e) {

			if (e.getMessage().indexOf("fk_hvp_plano") != -1) {
				// Exce��o levantada pela exclus�o de plano com hist�rico j� inseridos na base
				throw new SmartAppException("N�o � poss�vel excluir este plano, pois j� existe um hist�rico para o mesmo. Detalhe o plano para desativar o registro.");
			}
			
			throw new SmartEnvException(e);
		}finally {
			sStmt.close();
			sConn.close();
		}
	}	
	
	
	/**
	 * Obt�m uma cole��o de planos (encapsulado no objeto PlanoConvenioVO) cadastrada no banco de dados. Tabelas: PLANO_CONVENIO, CONVENIO , ATIVIDADE_CONVENIO , ESTADO
	 * @param vo Inst�ncia da classe PlanoConvenioVO contendo a propriedade de filtro preenchida.
	 * @return Retorna uma cole��o de inst�ncias da classe PlanoConvenioVO com as propriedades preenchidas.
	 * @throws SmartEnvException
	 */
	public Collection<PlanoConvenioVO> findByFilter(PlanoConvenioVO vo) throws SmartEnvException {
		
		StringBuffer SQL = new StringBuffer();
		SQL.append("  SELECT   PC.SEQ_PLANO             , ");
		SQL.append("           PC.NOM_PLANO             , ");
		SQL.append("           PC.DES_PLANO             , ");
		SQL.append("           PC.VAL_PLANO             , ");
		SQL.append("           PC.DAT_INICIO_VALOR      , ");
		SQL.append("           PC.FLG_DESATIVADO             , ");
		SQL.append("           PC.FLG_DEDUTIVEL         , ");
		SQL.append("           C.SEQ_CONVENIO            ");
		SQL.append("  FROM     PLANO_CONVENIO PC    , ");
		SQL.append("           CONVENIO C             ");
		SQL.append("  WHERE    PC.SEQ_CONVENIO = C.SEQ_CONVENIO AND 							");
		SQL.append(" ( ? IS NULL OR PC.SEQ_PLANO = ?) AND ");	
		SQL.append(" ( ? IS NULL OR UPPER(PC.NOM_PLANO) LIKE  '%' || UPPER(?) || '%') AND		");
		SQL.append(" ( ? IS NULL OR PC.FLG_DEDUTIVEL = ?) AND 									");
		SQL.append(" ( ? IS NULL OR UPPER(PC.DES_PLANO)  LIKE  '%' || UPPER(?) || '%') AND 		");
		SQL.append(" ( ? IS NULL OR PC.FLG_DESATIVADO = ?)  AND ");
		SQL.append(" ( ? IS NULL OR C.SEQ_CONVENIO = ?) ");	
		SQL.append("  ORDER BY PC.NOM_PLANO");

		SmartConnection 		sConn 	= null;
		SmartPreparedStatement 	sStmt 	= null;
		SmartResultSet			sRs		= null;
		try {
		
			sConn 	= new SmartConnection(this.getConn());
			sStmt 	= new SmartPreparedStatement(sConn.prepareStatement(SQL.toString()));
								 
			sStmt.setParameters(vo, new String[] {
					"codigo", 
					"codigo",
					"nome", 
					"nome",
					"dedutivel", 
					"dedutivel",
					"descricao",
					"descricao",
					"desativado",
					"desativado",
					"convenio.codigo",
					"convenio.codigo"
				});			
			
			sRs 	= new SmartResultSet(sStmt.getMyPreparedStatement().executeQuery());
			
			return sRs.getJavaBeans(PlanoConvenioVO.class, new String[]{
				"codigo",
				"nome",
				"descricao",
				"valor",
				"dataInicio",
				"desativado",
				"dedutivel",
				"convenio.codigo"});
		} catch (SQLException e) {
			throw new SmartEnvException(e);
		} finally {
			sRs.close();
			sStmt.close();
			sConn.close();
		}
	}	
	
	/**
	 * Insere um registro no historico de valores de Plano
	 * @param vo Inst�ncia da classe PlanoConvenioVO com os valores do registro antigo do Plano.
	 * @throws SmartEnvException
	 */
	public PlanoConvenioVO insertHistorico(PlanoConvenioVO vo) throws SmartEnvException {
		
		StringBuffer SQL = new StringBuffer();
		SQL.append("INSERT ");
		SQL.append("INTO   HIST_VALOR_PLANO ");
		SQL.append("       ( ");
		SQL.append("              SEQ_HIST_VALOR_PLANO , ");
		SQL.append("              SEQ_PLANO            , ");
		SQL.append("              VAL_PLANO            , ");
		SQL.append("              DAT_INICIO             ");
		SQL.append("              ");
		SQL.append("       ) ");
		SQL.append("       VALUES ");
		SQL.append("       ( ");
		SQL.append("              ?, ");
		SQL.append("              ?, ");
		SQL.append("              ?, ");
		SQL.append("              ? ");		
		SQL.append("       );");
		
		SmartConnection 		sConn 	= null;
		SmartPreparedStatement 	sStmt 	= null;
		
		try {
		
			sConn 	= new SmartConnection(this.getConn());
			sStmt 	= new SmartPreparedStatement(sConn.prepareStatement(SQL.toString()));
			
			vo.setCodigoHist(new Integer(getSequence("SEQ_HIST_VALOR_PLANO").intValue()));
						
			sStmt.setParameters(vo, new String[]{"codigoHist","codigo","valor","dataInicio"});
					
			sStmt.getMyPreparedStatement().execute();
			
			return vo;
				
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SmartEnvException(e);
			
		}finally {
			sStmt.close();
			sConn.close();
		}
	}	
	
	/**
	 * Obt�m uma cole��o de planos (encapsulado no objeto PlanoConvenioVO) cadastrada no banco de dados. Tabelas: PLANO_CONVENIO, HIST_VALOR_PLANO
	 * @param vo Inst�ncia da classe PlanoConvenioVO contendo a propriedade de filtro preenchida.
	 * @return Retorna uma cole��o de inst�ncias da classe PlanoConvenioVO com as propriedades preenchidas.
	 * @throws SmartEnvException
	 */
	public Collection<PlanoConvenioVO> findHistoricoValorPlanoByPlano(PlanoConvenioVO vo) throws SmartEnvException {
		
		StringBuffer SQL = new StringBuffer();
		SQL.append("  SELECT   PC.SEQ_PLANO             , ");
		SQL.append("           PC.NOM_PLANO             , ");
		SQL.append("           PC.DES_PLANO             , ");
		SQL.append("           HVP.VAL_PLANO             , ");
		SQL.append("           HVP.DAT_INICIO      ");
		SQL.append("  FROM     PLANO_CONVENIO PC    , ");
		SQL.append("           HIST_VALOR_PLANO HVP             ");
		SQL.append("  WHERE    PC.SEQ_PLANO = HVP.SEQ_PLANO  							");
		SQL.append("  AND      HVP.SEQ_PLANO = ?		");
		SQL.append("  ORDER BY DAT_CADASTRO DESC		");

		SmartConnection 		sConn 	= null;
		SmartPreparedStatement 	sStmt 	= null;
		SmartResultSet			sRs		= null;
		try {
		
			sConn 	= new SmartConnection(this.getConn());
			sStmt 	= new SmartPreparedStatement(sConn.prepareStatement(SQL.toString()));
								 
			sStmt.setParameters(vo, new String[] {"codigo"});			
			
			sRs 	= new SmartResultSet(sStmt.getMyPreparedStatement().executeQuery());
			
			return sRs.getJavaBeans(PlanoConvenioVO.class, new String[]{
				"codigo",
				"nome",
				"descricao",
				"valor",
				"dataInicio"});
		} catch (SQLException e) {
			throw new SmartEnvException(e);
		} finally {
			sRs.close();
			sStmt.close();
			sConn.close();
		}
	}	
}
