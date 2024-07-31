package br.org.asserjuf.sisjuf.associados.convenio.dados;

import java.sql.SQLException;
import java.util.Collection;

import br.com.falc.smartFW.exception.SmartEnvException;
import br.com.falc.smartFW.persistence.SmartConnection;
import br.com.falc.smartFW.persistence.SmartPreparedStatement;
import br.com.falc.smartFW.persistence.SmartResultSet;
import br.org.asserjuf.sisjuf.associados.AssociadoVO;
import br.org.asserjuf.sisjuf.associados.convenio.VinculacaoPlanoVO;
import br.org.asserjuf.sisjuf.associados.convenio.VinculadoPlanoAssembler;
import br.org.asserjuf.sisjuf.dados.SisjufDAOPostgres;

/**
 * Classe de acesso ao banco de dados da entidade "Beneficiario" do Sisjuf
 * @author Paulo
 *
 */
public class VinculacaoPlanoDAO extends SisjufDAOPostgres {

	
	
	/**
	 * Insere uma vincula��o de um beneficiario a um plano no banco de dados. Tabelas: VINCULACAO_PLANO
	 * @param vo Inst�ncia da classe AtividadeConvenioVO com os valores do registro a ser inserido.
	 * @throws SmartEnvException
	 */
	public VinculacaoPlanoVO insert(VinculacaoPlanoVO vo) throws SmartEnvException {
		
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO VINCULACAO_PLANO ( SEQ_VINCULACAO, SEQ_PESSOA, SEQ_ASSOCIADO, SEQ_PLANO, DAT_VINCULACAO, DAT_DESVINCULACAO,CODIGO_BENEFICIARIO_PLANO) "); 
		sql.append("VALUES(?,?,?,?,?,?,?)");
		
		SmartConnection 		sConn 	= null;
		SmartPreparedStatement 	sStmt 	= null;
		
		try {
		
			sConn 	= new SmartConnection(this.getConn());
			sStmt 	= new SmartPreparedStatement(sConn.prepareStatement(sql.toString()));
			
			vo.setCodigo(new Integer(getSequence("SEQ_VINCULACAO").intValue()));
						
			sStmt.setParameters(vo, new String[]{"codigo", "pessoa.codigo","associado.codigo","plano.codigo","dataVinculacao","dataDesVinculacao","codigoBeneficiarioPlano"});
						
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
	 * Atualiza os dados de uma vincula��o no banco de dados. Tabelas: VINCULACAO_PLANO
	 * @param vo Inst�ncia da classe VinculacaoPlanoVO com os novos valores do registro a ser atualizado.
	 * @throws SmartEnvException
	 */
	public void update(VinculacaoPlanoVO vo) throws SmartEnvException {
		
		StringBuffer sql = new StringBuffer("UPDATE VINCULACAO_PLANO SET DAT_DESVINCULACAO = ?, DAT_VINCULACAO = ?,CODIGO_BENEFICIARIO_PLANO = ? WHERE SEQ_VINCULACAO = ?");

		SmartConnection 		sConn 	= null;
		SmartPreparedStatement 	sStmt 	= null;
		
		try {
		
			sConn 	= new SmartConnection(this.getConn());
			sStmt 	= new SmartPreparedStatement(sConn.prepareStatement(sql.toString()));
			
			sStmt.setParameters(vo, new String[]{ "dataDesVinculacao", "dataVinculacao","codigoBeneficiarioPlano", "codigo"});			
						
			sStmt.getMyPreparedStatement().execute();
			
				
		} catch (Exception e) {
			e.printStackTrace();
			throw new SmartEnvException(e);
		} finally {
			sStmt.close();
			sConn.close();
		}
	}	
	
	/**
	 * Obt�m uma Vinculacao (encapsulado no objeto VinculacaoPlanoVO) cadastrada no banco de dados. Tabelas: VINCULACAO_PLANO
	 * @param vo Inst�ncia da classe VinculacaoPlanoVO contendo a propriedade codigo preenchida.
	 * @return Retorna uma inst�ncia da classe VinculacaoPlanoVO com as propriedades preenchidas.
	 * @throws SmartEnvException
	 */
	public VinculacaoPlanoVO findByPrimaryKey(VinculacaoPlanoVO vo) throws SmartEnvException {
		
		StringBuffer sql = new StringBuffer();
		
		sql.append("SELECT "); 
		sql.append("VP.SEQ_VINCULACAO, "); 
		sql.append("P.SEQ_PESSOA, "); 
		sql.append("P.NOM_PESSOA, "); 
		sql.append("PA.SEQ_PESSOA , "); 
		sql.append("PA.NOM_PESSOA, "); 
		sql.append("PL.SEQ_PLANO , "); 
		sql.append("PL.NOM_PLANO, "); 
		sql.append("VP.DAT_VINCULACAO, "); 
		sql.append("VP.DAT_DESVINCULACAO, "); 
		sql.append("C.SEQ_CONVENIO, ");
		sql.append("VP.CODIGO_BENEFICIARIO_PLANO ");
		sql.append("FROM VINCULACAO_PLANO VP, PESSOA P, PESSOA PA, PLANO_CONVENIO PL, CONVENIO C "); 
		sql.append("WHERE "); 
		sql.append("VP.SEQ_PESSOA = P.SEQ_PESSOA AND "); 
		sql.append("VP.SEQ_ASSOCIADO = PA.SEQ_PESSOA AND "); 
		sql.append("VP.SEQ_PLANO = PL.SEQ_PLANO AND "); 
		sql.append("PL.SEQ_CONVENIO = C.SEQ_CONVENIO AND "); 
		sql.append("VP.SEQ_VINCULACAO = ? ");
		
		SmartConnection 		sConn 	= null;
		SmartPreparedStatement 	sStmt 	= null;
		SmartResultSet			sRs		= null;
		
		try {
			sConn 	= new SmartConnection(this.getConn());
			sStmt 	= new SmartPreparedStatement(sConn.prepareStatement(sql.toString()));
			
			sStmt.setInteger(1, vo.getCodigo());
			
			sRs = new SmartResultSet(sStmt.getMyPreparedStatement().executeQuery());
			  
			return (VinculacaoPlanoVO) sRs.getJavaBean(new VinculacaoPlanoVO(), new String[] {"codigo", "pessoa.codigo","pessoa.nome", "associado.codigo","associado.nome","plano.codigo", "plano.nome" , "dataVinculacao","dataDesVinculacao","plano.convenio.codigo","codigoBeneficiarioPlano"});
		} catch (SQLException e) {
			throw new SmartEnvException(e);
		} finally {
			sRs.close();
			sStmt.close();
			sConn.close();
		}
	}	
	
	/**
	 * Obt�m uma cole��o de vinculados (encapsulado no objeto VinculadoPlanoAssembler) cadastrada no banco de dados. Tabelas: CONVENIO , PLANO_CONVENIO , VINCULACAO_PLANO  , PESSOA , DEPENDENTE , ASSOCIADO , PARENTESCO , FILHO
	 * @param vo Inst�ncia da classe VinculacaoPlanoVO contendo a propriedade de filtro preenchida.
	 * @return Retorna uma cole��o de inst�ncias da classe BeneficiarioAssembler com propriedades preenchidas.
	 * @throws SmartEnvException
	 */
	public Collection<VinculacaoPlanoVO> findVinculadosPlanoByAssociado(VinculacaoPlanoVO vo) throws SmartEnvException {
		
		StringBuffer sql = new StringBuffer();

		sql.append("SELECT "); 
		sql.append(" SEQ_VINCULACAO, "); 
		sql.append(" NOM_PESSOA , "); 
		sql.append(" NOM_FANTASIA, "); 
		sql.append(" SEQ_ASSOCIADO, "); 
		sql.append(" NOM_TITULAR, "); 
		sql.append(" TIPO, "); 
		sql.append(" NOM_PLANO, "); 
		sql.append(" VAL_PLANO, "); 
		sql.append(" CODIGO_BENEFICIARIO_PLANO ");
		sql.append(" FROM "); 
		sql.append(" ( "); 
		sql.append(" SELECT "); 
		sql.append(" VP.SEQ_VINCULACAO, "); 
		sql.append(" P.NOM_PESSOA , "); 
		sql.append(" C.NOM_FANTASIA, "); 
		sql.append(" P.SEQ_PESSOA AS SEQ_ASSOCIADO, "); 
		sql.append(" NULL AS NOM_TITULAR, "); 
		sql.append(" 'ASSOCIADO' AS TIPO , "); 
		sql.append(" PC.NOM_PLANO, "); 
		sql.append(" PC.VAL_PLANO, "); 
		sql.append(" VP.CODIGO_BENEFICIARIO_PLANO ");
		sql.append(" FROM "); 
		sql.append(" CONVENIO C, PLANO_CONVENIO PC, VINCULACAO_PLANO VP , PESSOA P, ASSOCIADO A "); 
		sql.append(" WHERE "); 
		sql.append(" C.SEQ_CONVENIO = PC.SEQ_CONVENIO AND "); 
		sql.append(" PC.SEQ_PLANO = VP.SEQ_PLANO AND "); 
		sql.append(" VP.SEQ_PESSOA = P.SEQ_PESSOA AND "); 
		sql.append(" P.SEQ_PESSOA = A.SEQ_PESSOA AND "); 
		sql.append(" (VP.DAT_DESVINCULACAO IS NULL OR VP.DAT_DESVINCULACAO > CURRENT_DATE ) AND "); 
		sql.append(" A.seq_pessoa  = ? "); 
		sql.append(" UNION "); 
		sql.append(" SELECT "); 
		sql.append(" VP.SEQ_VINCULACAO, "); 
		sql.append(" P.NOM_PESSOA , "); 
		sql.append(" C.NOM_FANTASIA, "); 
		sql.append(" PA.SEQ_PESSOA AS SEQ_ASSOCIADO, "); 
		sql.append(" PA.NOM_PESSOA AS NOM_TITULAR, "); 
		sql.append(" 'FILHO' AS TIPO , "); 
		sql.append(" PC.NOM_PLANO, "); 
		sql.append(" PC.VAL_PLANO, "); 
		sql.append(" VP.CODIGO_BENEFICIARIO_PLANO "); 
		sql.append(" FROM "); 
		sql.append(" CONVENIO C, PLANO_CONVENIO PC, VINCULACAO_PLANO VP , PESSOA P, FILHO F, ASSOCIADO A , PESSOA PA "); 
		sql.append(" WHERE "); 
		sql.append(" C.SEQ_CONVENIO = PC.SEQ_CONVENIO AND "); 
		sql.append(" PC.SEQ_PLANO = VP.SEQ_PLANO AND "); 
		sql.append(" VP.SEQ_PESSOA = P.SEQ_PESSOA AND "); 
		sql.append(" P.SEQ_PESSOA = F.SEQ_PESSOA AND "); 
		sql.append(" F.SEQ_ASSOCIADO = A.seq_pessoa AND "); 
		sql.append(" A.SEQ_PESSOA = PA.SEQ_PESSOA AND "); 
		sql.append(" (VP.DAT_DESVINCULACAO IS NULL OR VP.DAT_DESVINCULACAO > CURRENT_DATE ) AND "); 
		sql.append(" A.seq_pessoa =  ? "); 
		sql.append(" UNION "); 
		sql.append(" SELECT "); 
		sql.append(" VP.SEQ_VINCULACAO, "); 
		sql.append(" P.NOM_PESSOA , "); 
		sql.append(" C.NOM_FANTASIA, "); 
		sql.append(" PA.SEQ_PESSOA AS SEQ_ASSOCIADO, "); 
		sql.append(" PA.NOM_PESSOA AS NOM_TITULAR, "); 
		sql.append(" 'DEPENDENTE : ' || PR.NOM_PARENTESCO  AS TIPO , "); 
		sql.append(" PC.NOM_PLANO, "); 
		sql.append(" PC.VAL_PLANO, "); 
		sql.append(" VP.CODIGO_BENEFICIARIO_PLANO "); 
		sql.append(" FROM "); 
		sql.append(" CONVENIO C, PLANO_CONVENIO PC, VINCULACAO_PLANO VP , PESSOA P, DEPENDENTE D, ASSOCIADO A , PESSOA PA, PARENTESCO PR "); 
		sql.append(" WHERE "); 
		sql.append(" C.SEQ_CONVENIO = PC.SEQ_CONVENIO AND "); 
		sql.append(" PC.SEQ_PLANO = VP.SEQ_PLANO AND "); 
		sql.append(" VP.SEQ_PESSOA = P.SEQ_PESSOA AND "); 
		sql.append(" P.SEQ_PESSOA = D.SEQ_PESSOA AND "); 
		sql.append(" D.SEQ_ASSOCIADO = A.SEQ_PESSOA AND "); 
		sql.append(" A.SEQ_PESSOA = PA.SEQ_PESSOA AND "); 
		sql.append(" D.SEQ_PARENTESCO  = PR.SEQ_PARENTESCO AND "); 
		sql.append(" (VP.DAT_DESVINCULACAO IS NULL OR VP.DAT_DESVINCULACAO > CURRENT_DATE ) AND "); 
		sql.append(" A.seq_pessoa = ? "); 
		sql.append(" UNION "); 
		sql.append(" SELECT "); 
		sql.append(" VP.SEQ_VINCULACAO, "); 
		sql.append(" PO.NOM_PESSOA , "); 
		sql.append(" C.NOM_FANTASIA, "); 
		sql.append(" P.SEQ_PESSOA AS SEQ_ASSOCIADO, "); 
		sql.append(" P.NOM_PESSOA AS NOM_TITULAR, "); 
		sql.append(" 'OUTRO BENEFICIAVEL'  AS TIPO , "); 
		sql.append(" PC.NOM_PLANO, "); 
		sql.append(" PC.VAL_PLANO, "); 
		sql.append(" VP.CODIGO_BENEFICIARIO_PLANO "); 
		sql.append(" FROM "); 
		sql.append(" CONVENIO C, PLANO_CONVENIO PC, VINCULACAO_PLANO VP , PESSOA P, PESSOA PO, OUTROS_BENEFICIAVEIS OB "); 
		sql.append(" WHERE "); 
		sql.append(" C.SEQ_CONVENIO = PC.SEQ_CONVENIO AND "); 
		sql.append(" PC.SEQ_PLANO = VP.SEQ_PLANO AND "); 
		sql.append(" VP.SEQ_PESSOA = PO.SEQ_PESSOA AND "); 
		sql.append(" PO.SEQ_PESSOA = OB.SEQ_PESSOA AND "); 
		sql.append(" VP.SEQ_ASSOCIADO = P.SEQ_PESSOA AND "); 
		sql.append(" P.SEQ_PESSOA = OB.SEQ_ASSOCIADO AND "); 
		sql.append(" (VP.DAT_DESVINCULACAO IS NULL OR VP.DAT_DESVINCULACAO > CURRENT_DATE ) AND "); 
		sql.append(" VP.SEQ_ASSOCIADO = ? "); 
		sql.append(" ) TEMP  ORDER BY NOM_PESSOA ");
		
		SmartConnection 		sConn 	= null;
		SmartPreparedStatement 	sStmt 	= null;
		SmartResultSet			sRs		= null;
		
		try {
			sConn 	= new SmartConnection(this.getConn());
			sStmt 	= new SmartPreparedStatement(sConn.prepareStatement(sql.toString()));					
			  
			sStmt.setParameters(vo, new String[] {"associado.codigo","associado.codigo","associado.codigo","associado.codigo"});
			
			sRs = new SmartResultSet(sStmt.getMyPreparedStatement().executeQuery());						
			
			return (Collection<VinculacaoPlanoVO>) sRs.getJavaBeans(VinculacaoPlanoVO.class, new String[]{
				"codigo",
				"pessoa.nome",
				"plano.convenio.nomeFantasia",
				"associado.codigo",
				"associado.nome",
				"parentesco.descricao",
				"plano.nome",
				"plano.valor",
				"codigoBeneficiarioPlano"
				});
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
	 * Insere um registro no hist�rico de vincula��o de um beneficiario a um plano no banco de dados. Tabelas: VINCULACAO_PLANO
	 * @param vo Inst�ncia da classe VinculacaoPlanoVO com os valores do registro a ser inserido.
	 * @throws SmartEnvException
	 */
	public VinculacaoPlanoVO insertHistorico(VinculacaoPlanoVO vo) throws SmartEnvException {
		
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO HIST_VINCULACAO_PLANO (SEQ_HIST_VINCULACAO_PLANO,SEQ_VINCULACAO,DAT_VINCULACAO,DAT_DESVINCULACAO) VALUES(?,?,?,?) "); 
		
		
		SmartConnection 		sConn 	= null;
		SmartPreparedStatement 	sStmt 	= null;
		
		try {
		
			sConn 	= new SmartConnection(this.getConn());
			sStmt 	= new SmartPreparedStatement(sConn.prepareStatement(sql.toString()));
			
			vo.setCodigoHist(new Integer(getSequence("SEQ_HIST_VINCULACAO_PLANO").intValue()));
						
			sStmt.setParameters(vo, new String[]{"codigoHist", "codigo","dataVinculacao","dataDesVinculacao"});
						
			sStmt.getMyPreparedStatement().execute();
			
			return vo;
				
		} catch (Exception e) {
			e.printStackTrace();
			throw new SmartEnvException(e);
		}finally {
			sStmt.close();
			sConn.close();
		}
	}	
	
public Collection<VinculadoPlanoAssembler> findHistoricoVinculadosPlanoByFilter(VinculadoPlanoAssembler vo) throws SmartEnvException {
		
		StringBuffer sql = new StringBuffer();

		sql.append("SELECT HVP.dat_vinculacao,HVP.dat_desvinculacao,p.nom_pessoa, PC.des_plano,C.NOM_FANTASIA  "); 
		sql.append(" FROM HIST_VINCULACAO_PLANO HVP, VINCULACAO_PLANO VP, PESSOA P, PLANO_CONVENIO PC,CONVENIO C "); 
		sql.append(" WHERE "); 
		sql.append(" HVP.SEQ_VINCULACAO = VP.SEQ_VINCULACAO AND "); 
		sql.append(" VP.SEQ_PLANO = PC.SEQ_PLANO AND "); 
		sql.append(" VP.SEQ_PESSOA = P.SEQ_PESSOA AND ");
		sql.append(" PC.SEQ_CONVENIO = C.SEQ_CONVENIO AND ");
		sql.append(" VP.SEQ_ASSOCIADO = ? AND "); 
		sql.append(" (? IS NULL OR PC.SEQ_CONVENIO = ?) AND "); 
		sql.append(" (? IS NULL OR PC.SEQ_PLANO = ?)  AND "); 
		sql.append(" (? IS NULL OR P.SEQ_PESSOA = ?) "); 
		sql.append(" order by p.nom_pessoa,c.nom_fantasia,pc.des_plano");

		SmartConnection 		sConn 	= null;
		SmartPreparedStatement 	sStmt 	= null;
		SmartResultSet			sRs		= null;
		
		try {
			sConn 	= new SmartConnection(this.getConn());
			sStmt 	= new SmartPreparedStatement(sConn.prepareStatement(sql.toString()));					
			  
			
			sStmt.setParameters(vo, new String[] {"associado.codigo","plano.convenio.codigo","plano.convenio.codigo","plano.codigo","plano.codigo","associadoDependente.codigo","associadoDependente.codigo"});
			
			sRs = new SmartResultSet(sStmt.getMyPreparedStatement().executeQuery());
			
			return (Collection<VinculadoPlanoAssembler>) sRs.getJavaBeans(VinculadoPlanoAssembler.class, new String[]{"dataVinculacao","dataDesVinculacao","associado.nome",
				"convenio.nomeFantasia","plano.descricao"});
		} catch (SQLException e) {
			throw new SmartEnvException(e);
		} finally {
			sRs.close();
			sStmt.close();
			sConn.close();
		}
	}	


	/**
	 * Recupera o registro de historico mais novo poara uma determinada vincula��o.
	 * @param vo
	 * @return
	 * @throws SmartEnvException
	 */
	public VinculacaoPlanoVO findHistoricoByVinculacao(VinculacaoPlanoVO vo) throws SmartEnvException {
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT "); 
		sql.append(" SEQ_HIST_VINCULACAO_PLANO, "); 
		sql.append(" SEQ_VINCULACAO, "); 
		sql.append(" DAT_VINCULACAO, "); 
		sql.append(" DAT_DESVINCULACAO "); 
		sql.append("FROM HIST_VINCULACAO_PLANO WHERE "); 
		sql.append("SEQ_HIST_VINCULACAO_PLANO = (SELECT MAX(SEQ_HIST_VINCULACAO_PLANO) FROM HIST_VINCULACAO_PLANO WHERE SEQ_VINCULACAO = ?) "); 
		
		
		SmartConnection 		sConn 	= null;
		SmartPreparedStatement 	sStmt 	= null;
		SmartResultSet			sRs		= null;
		
		try {
		
			sConn 	= new SmartConnection(this.getConn());
			sStmt 	= new SmartPreparedStatement(sConn.prepareStatement(sql.toString()));			
						
			sStmt.setParameters(vo, new String[]{"codigo"});
						
			sRs = new SmartResultSet(sStmt.getMyPreparedStatement().executeQuery());
			  
			return (VinculacaoPlanoVO) sRs.getJavaBean(new VinculacaoPlanoVO(), new String[] {"codigoHist", "codigo", "dataVinculacao","dataDesVinculacao"});			
				
		} catch (SQLException e) {
			throw new SmartEnvException(e);
		}finally {
			sStmt.close();
			sConn.close();
		}
	}	

	/**
	 * Atualiza um registro de historico
	 * @param vo
	 * @throws SmartEnvException
	 */
	public void updateHistorico(VinculacaoPlanoVO vo) throws SmartEnvException {
		
		StringBuffer sql = new StringBuffer("UPDATE HIST_VINCULACAO_PLANO SET DAT_DESVINCULACAO = ?, DAT_VINCULACAO = ? WHERE SEQ_HIST_VINCULACAO_PLANO = ? ");

		SmartConnection 		sConn 	= null;
		SmartPreparedStatement 	sStmt 	= null;
		
		try {
		
			sConn 	= new SmartConnection(this.getConn());
			sStmt 	= new SmartPreparedStatement(sConn.prepareStatement(sql.toString()));
			
			sStmt.setParameters(vo, new String[]{ "dataDesVinculacao", "dataVinculacao", "codigo"});			
						
			sStmt.getMyPreparedStatement().execute();			
				
		} catch (SQLException e) {
			throw new SmartEnvException(e);
		} finally {
			sStmt.close();
			sConn.close();
		}
	}		
	
	public VinculacaoPlanoVO findByFilter(VinculacaoPlanoVO vo) throws SmartEnvException {
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT "); 
		sql.append("VP.SEQ_VINCULACAO, "); 
		sql.append("P.SEQ_PESSOA, "); 
		sql.append("P.nom_pessoa, "); 
		sql.append("PA.seq_pessoa , "); 
		sql.append("PA.nom_pessoa, "); 
		sql.append("PL.SEQ_PLANO , "); 
		sql.append("PL.nom_plano, "); 
		sql.append("VP.DAT_VINCULACAO, "); 
		sql.append("VP.DAT_DESVINCULACAO, ");
		sql.append(" VP.CODIGO_BENEFICIARIO_PLANO ");
		sql.append("FROM VINCULACAO_PLANO VP, PESSOA P, PESSOA PA, PLANO_CONVENIO PL "); 
		sql.append("WHERE "); 
		sql.append("VP.SEQ_PESSOA = P.SEQ_PESSOA AND "); 
		sql.append("VP.SEQ_ASSOCIADO = PA.SEQ_PESSOA AND "); 
		sql.append("VP.SEQ_PLANO = PL.SEQ_PLANO AND "); 
		sql.append("P.SEQ_PESSOA = ? AND PA.SEQ_PESSOA = ? AND PL.SEQ_PLANO = ? ");
		
		SmartConnection 		sConn 	= null;
		SmartPreparedStatement 	sStmt 	= null;
		SmartResultSet			sRs		= null;
		
		try {
			sConn 	= new SmartConnection(this.getConn());
			sStmt 	= new SmartPreparedStatement(sConn.prepareStatement(sql.toString()));
			
			sStmt.setInteger(1, vo.getPessoa().getCodigo());
			sStmt.setInteger(2, vo.getAssociado().getCodigo());
			sStmt.setInteger(3, vo.getPlano().getCodigo());
			
			sRs = new SmartResultSet(sStmt.getMyPreparedStatement().executeQuery());
			  
			return (VinculacaoPlanoVO) sRs.getJavaBean(new VinculacaoPlanoVO(), new String[] {"codigo", "pessoa.codigo","pessoa.nome", "associado.codigo","associado.nome","plano.codigo", "plano.nome" , "dataVinculacao","dataDesVinculacao","codigoBeneficiarioPlano"});
		} catch (SQLException e) {
			throw new SmartEnvException(e);
		} finally {
			sRs.close();
			sStmt.close();
			sConn.close();
		}
	}


	public void removeAllByAssociado(AssociadoVO associado) throws SmartEnvException {
		StringBuffer sql = new StringBuffer("update vinculacao_plano set dat_desvinculacao = current_timestamp where seq_associado =  ? ");

		SmartConnection 		sConn 	= null;
		SmartPreparedStatement 	sStmt 	= null;
		
		try {
		
			sConn 	= new SmartConnection(this.getConn());
			sStmt 	= new SmartPreparedStatement(sConn.prepareStatement(sql.toString()));
			
			sStmt.setParameters(associado, new String[]{"codigo"});			
						
			sStmt.getMyPreparedStatement().execute();			
				
		} catch (SQLException e) {
			throw new SmartEnvException(e);
		} finally {
			sStmt.close();
			sConn.close();
		}
		
	}		
	
}
