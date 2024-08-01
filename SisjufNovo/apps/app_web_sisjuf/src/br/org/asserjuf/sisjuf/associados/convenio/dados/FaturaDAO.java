package br.org.asserjuf.sisjuf.associados.convenio.dados;

import java.sql.SQLException;
import java.util.Collection;

//import org.apache.log4j.Logger;



import br.com.falc.smartFW.exception.SmartEnvException;
import br.com.falc.smartFW.persistence.SmartConnection;
import br.com.falc.smartFW.persistence.SmartPreparedStatement;
import br.com.falc.smartFW.persistence.SmartResultSet;
import br.org.asserjuf.sisjuf.associados.convenio.ConvenioVO;
import br.org.asserjuf.sisjuf.associados.convenio.FaturaArquivoVO;
import br.org.asserjuf.sisjuf.associados.convenio.FaturaFiltroAssembler;
import br.org.asserjuf.sisjuf.associados.convenio.FaturaPreviaFiltroAssembler;
import br.org.asserjuf.sisjuf.associados.convenio.FaturaVO;
import br.org.asserjuf.sisjuf.associados.convenio.ItemFaturaVO;
import br.org.asserjuf.sisjuf.associados.convenio.ItemFaturaInconsistenteVO;
import br.org.asserjuf.sisjuf.dados.SisjufDAOPostgres;
import br.org.asserjuf.sisjuf.financeiro.LancamentoVO;

/**
 * Classe de acesso ao banco de dados da entidade "Fatura" do Sisjuf
 * @author Rodrigo Falcao
 *
 */
public class FaturaDAO extends SisjufDAOPostgres {

	//private static transient Logger	LOG = Logger.getLogger(FaturaDAO.class);
	
	/**
	 * Obtem todos as faturas de um determinado convenio.
	 * @return Colecao de ConvenioVO (objeto que representa a entidade "Convenio")
	 * @throws SmartEnvException
	 */
	public Collection<FaturaVO> findByConvenio(ConvenioVO convenio) throws SmartEnvException {
		
		StringBuffer sql = new StringBuffer();
		
		sql.append(" SELECT SEQ_FATURA, DAT_FATURA, VAL_FATURA, DAT_VENCIMENTO_FATURA FROM ( ")
			.append(" SELECT 	F.SEQ_FATURA, ")
			.append(" F.DAT_FATURA, ")
			.append(" F.VAL_FATURA, ")
			.append(" F.DAT_VENCIMENTO_FATURA ")
			.append(" FROM FATURA F ")
			.append(" WHERE F.SEQ_CONVENIO = ?  ")
			.append(" ORDER BY F.SEQ_FATURA DESC ")
			.append(" ) TMP LIMIT (SELECT INT8(STR_VAL_PARAMETRO) FROM PARAMETROS WHERE NOM_PARAMETRO = 'QTD_FATURAS_POR_CONVENIO') ");	

		SmartConnection 		sConn 	= null;
		SmartPreparedStatement 	sStmt 	= null;
		SmartResultSet			sRs		= null;
		try {
		
			sConn 	= new SmartConnection(this.getConn());
			sStmt 	= new SmartPreparedStatement(sConn.prepareStatement(sql.toString()));
			
			sStmt.setParameters(convenio, new String[] {"codigo"});
			
			sRs 	= new SmartResultSet(sStmt.getMyPreparedStatement().executeQuery());
			
			return sRs.getJavaBeans(FaturaVO.class, new String[]{	"codigo", "data", "valorFatura", "dataVencimento" });
			
		} catch (SQLException e) {
			throw new SmartEnvException(e);
		} finally {
			sRs.close();
			sStmt.close();
			sConn.close();
		}
	}
	
	public Collection<FaturaVO> findByLancamento(LancamentoVO lancamento) throws SmartEnvException {
		
		StringBuffer sql = new StringBuffer();
		
			sql.append(" SELECT 	 ")
			.append(" lf.SEQ_FATURA ")
			.append(" FROM lancamento_fatura lf ")
			.append(" WHERE lf.seq_lancamento = ?  ");	

		SmartConnection 		sConn 	= null;
		SmartPreparedStatement 	sStmt 	= null;
		SmartResultSet			sRs		= null;
		try {
		
			sConn 	= new SmartConnection(this.getConn());
			sStmt 	= new SmartPreparedStatement(sConn.prepareStatement(sql.toString()));
			
			sStmt.setParameters(lancamento, new String[] {"codigo"});
			
			sRs 	= new SmartResultSet(sStmt.getMyPreparedStatement().executeQuery());
			
			return sRs.getJavaBeans(FaturaVO.class, new String[]{	"codigo"});
			
		} catch (SQLException e) {
			throw new SmartEnvException(e);
		} finally {
			sRs.close();
			sStmt.close();
			sConn.close();
		}
	}
	
	/**
	 * Obtem todos as faturas por filtro.
	 * @return Colecao de FaturaVO (objeto que representa a entidade "Fatura")
	 * @throws SmartEnvException
	 */
	public Collection<FaturaVO> findByFilter(FaturaFiltroAssembler fatura) throws SmartEnvException {
		
		StringBuffer sql = new StringBuffer();
		
		sql.append(" SELECT  F.SEQ_FATURA, ")
			.append(" F.SEQ_STATUS_FATURA, ")
			.append(" SF.NOM_STATUS_FATURA, ")
			.append(" F.SEQ_CONVENIO, ")
			.append(" C.NOM_FANTASIA, ")
			.append(" F.FLG_PAGO, ")
			.append(" F.FLG_RECEBIDO, ")
			.append(" F.VAL_FATURA, ")
			.append(" F.DAT_INICIO_FATURA, ")
			.append(" F.DAT_FIM_FATURA, ")
			.append(" F.DAT_FATURA, ")
			.append(" F.DAT_VENCIMENTO_FATURA ")
			.append(" FROM 	FATURA F, ")
			.append(" STATUS_FATURA SF, ")
			.append(" CONVENIO C ")
			.append(" WHERE	F.SEQ_STATUS_FATURA = SF.SEQ_STATUS_FATURA ")
			.append(" AND	F.SEQ_CONVENIO	= C.SEQ_CONVENIO ")
			.append(" AND	(? IS NULL OR F.SEQ_CONVENIO = ? ) ")
			.append(" AND	(? IS NULL OR F.SEQ_STATUS_FATURA = ?) ")
			.append(" AND	(? IS NULL OR F.SEQ_FATURA = ?) ")
			.append(" AND   (? IS NULL OR F.DAT_INICIO_FATURA >= ?) ")
			.append(" AND   (? IS NULL OR F.DAT_INICIO_FATURA <= ?) ")
			.append(" AND   (? IS NULL OR F.DAT_FIM_FATURA >= ?) ")
			.append(" AND   (? IS NULL OR F.DAT_FIM_FATURA <= ?) ");	

		SmartConnection 		sConn 	= null;
		SmartPreparedStatement 	sStmt 	= null;
		SmartResultSet			sRs		= null;
		try {
		
			sConn 	= new SmartConnection(this.getConn());
			sStmt 	= new SmartPreparedStatement(sConn.prepareStatement(sql.toString()));
			
			sStmt.setParameters(fatura, new String[] {"convenio.codigo","convenio.codigo",
													  "status.codigo", "status.codigo",
													  "codigo","codigo",
													  "strDataInicioDe","dataInicioDe",
													  "strDataInicioAte","dataInicioAte",
													  "strDataFimDe","dataFimDe",
													  "strDataFimAte","dataFimAte"													  
														});
			
			sRs 	= new SmartResultSet(sStmt.getMyPreparedStatement().executeQuery());
			
			return sRs.getJavaBeans(FaturaVO.class, new String[]{	"codigo",
																	"status.codigo",
																	"status.nome",
																	"convenio.codigo",
																	"convenio.nomeFantasia",
																	"statusPago",
																	"statusRecebido",
																	"valorFatura",
																	"dataInicial",
																	"dataFinal",
																	"data","dataVencimento"			});									
																	
			
		} catch (SQLException e) {
			throw new SmartEnvException(e);
		} finally {
			sRs.close();
			sStmt.close();
			sConn.close();
		}
	}
	
	/**
	 * Obtem fatura por chave primaria.
	 * @return FaturaVO (objeto que representa a entidade "Fatura")
	 * @throws SmartEnvException
	 */
	public FaturaVO findByPrimaryKey(FaturaVO fatura) throws SmartEnvException {
		
		StringBuffer sql = new StringBuffer();
		
		sql.append(" SELECT  F.SEQ_FATURA, ")
			.append(" F.SEQ_STATUS_FATURA, ")
			.append(" SF.NOM_STATUS_FATURA, ")
			.append(" F.SEQ_CONVENIO, ")
			.append(" C.NOM_FANTASIA, ")
			.append(" F.FLG_PAGO, ")
			.append(" F.FLG_RECEBIDO, ")
			.append(" F.VAL_FATURA, ")
			.append(" F.DAT_INICIO_FATURA, ")
			.append(" F.DAT_FIM_FATURA, ")
			.append(" F.DAT_FATURA, ")
			.append(" F.DAT_VENCIMENTO_FATURA ")
			.append(" FROM 	FATURA F JOIN STATUS_FATURA SF ON F.SEQ_STATUS_FATURA = SF.SEQ_STATUS_FATURA ")
			.append(" JOIN CONVENIO C ON F.seq_convenio=C.seq_convenio ")
			.append(" WHERE	F.SEQ_FATURA = ? ");	

		SmartConnection 		sConn 	= null;
		SmartPreparedStatement 	sStmt 	= null;
		SmartResultSet			sRs		= null;
		try {
		
			sConn 	= new SmartConnection(this.getConn());
			sStmt 	= new SmartPreparedStatement(sConn.prepareStatement(sql.toString()));
			
			sStmt.setParameters(fatura, new String[] {"codigo"});
			
			sRs 	= new SmartResultSet(sStmt.getMyPreparedStatement().executeQuery());
			
			return (FaturaVO) sRs.getJavaBean(fatura, new String[]{	"codigo",
																	"status.codigo",
																	"status.nome",
																	"convenio.codigo",
																	"convenio.nomeFantasia",
																	"statusPago",
																	"statusRecebido",
																	"valorFatura",
																	"dataInicial",
																	"dataFinal",
																	"data","dataVencimento"			});									
																	
			
		} catch (SQLException e) {
			throw new SmartEnvException(e);
		} finally {
			sRs.close();
			sStmt.close();
			sConn.close();
		}
	}
	
	/**
	 * Insere uma fatura no banco de dados. Tabelas: FATURA
	 * @param fatura Instï¿½ncia da classe FaturaVO com os valores do registro a ser inserido.
	 * @throws SmartEnvException
	 */
	public FaturaVO insert(FaturaVO fatura) throws SmartEnvException {
		
		StringBuffer sql = new StringBuffer(" INSERT INTO FATURA (SEQ_FATURA, SEQ_STATUS_FATURA, SEQ_CONVENIO, FLG_PAGO, FLG_RECEBIDO, VAL_FATURA, DAT_INICIO_FATURA, DAT_FIM_FATURA, DAT_FATURA, DAT_VENCIMENTO_FATURA) VALUES (?, ?, ?, ?, ?, ?, ?, ?, CURRENT_DATE, ?) ");
		
		SmartConnection 		sConn 	= null;
		SmartPreparedStatement 	sStmt 	= null;
		
		try {
		
			sConn 	= new SmartConnection(this.getConn());
			sStmt 	= new SmartPreparedStatement(sConn.prepareStatement(sql.toString()));
			
			fatura.setCodigo(new Integer(getSequence("SEQ_FATURA").intValue()));
						
			sStmt.setParameters(fatura, new String[]{"codigo", "status.codigo","convenio.codigo","statusPago", "statusRecebido", "valorFatura", "dataInicial", "dataFinal","dataVencimento"});
						
			sStmt.getMyPreparedStatement().execute();
			
			return fatura;
				
		} catch (SQLException e) {
			throw new SmartEnvException(e);
		}finally {
			sStmt.close();
			sConn.close();
		}
	}
	
	/**
	 * Atualiza o status de uma fatura no banco de dados. Tabelas: FATURA
	 * @param fatura Instancia da classe FaturaVO com os valores do registro a ser inserido.
	 * @throws SmartEnvException
	 */
	public void updateStatus(FaturaVO fatura) throws SmartEnvException {
		
		StringBuffer sql = new StringBuffer(" UPDATE FATURA SET SEQ_STATUS_FATURA = ? WHERE SEQ_FATURA = ? ");
		
		SmartConnection 		sConn 	= null;
		SmartPreparedStatement 	sStmt 	= null;
		
		try {
		
			sConn 	= new SmartConnection(this.getConn());
			sStmt 	= new SmartPreparedStatement(sConn.prepareStatement(sql.toString()));
			
						
			sStmt.setParameters(fatura, new String[]{"status.codigo", "codigo"});
						
			sStmt.getMyPreparedStatement().execute();
			
				
		} catch (SQLException e) {
			throw new SmartEnvException(e);
		}finally {
			sStmt.close();
			sConn.close();
		}
	}
	
	/**
	 * Insere um item de fatura no banco de dados. Tabelas: ITEM_FATURA
	 * @param fatura Instï¿½ncia da classe ItemFaturaVO com os valores do registro a ser inserido.
	 * @throws SmartEnvException
	 */
	public ItemFaturaVO insert(ItemFaturaVO item) throws SmartEnvException {
		
		StringBuffer sql = new StringBuffer(" INSERT INTO ITEM_FATURA (SEQ_ITEM_FATURA, SEQ_VINCULACAO, SEQ_FATURA, VAL_ITEM_FATURA, NUM_ITEM_FATURA) VALUES (?, ?, ?, ?, ?) ");
		
		SmartConnection 		sConn 	= null;
		SmartPreparedStatement 	sStmt 	= null;
		
		try {
		
			sConn 	= new SmartConnection(this.getConn());
			sStmt 	= new SmartPreparedStatement(sConn.prepareStatement(sql.toString()));
			
			item.setCodigo(new Integer(getSequence("SEQ_ITEM_FATURA").intValue()));
						
			sStmt.setParameters(item, new String[]{"codigo", "vinculacao.codigo","fatura.codigo","valor", "numero"});
						
			sStmt.getMyPreparedStatement().execute();
			
			return item;
				
		} catch (SQLException e) {
			throw new SmartEnvException(e);
		}finally {
			sStmt.close();
			sConn.close();
		}
	}

	/**
	 * Estorna uma fatura no banco de dados. Tabelas: FATURA
	 * @param fatura Instï¿½ncia da classe FaturaVO com os valores do registro a ser atualizado.
	 * @throws SmartEnvException 
	 * @throws SmartEnvException
	 */
	public void estornar(FaturaVO fatura) throws SmartEnvException {
		StringBuffer sql = new StringBuffer(" UPDATE FATURA SET SEQ_STATUS_FATURA = (SELECT INT2(STR_VAL_PARAMETRO) FROM PARAMETROS WHERE NOM_PARAMETRO = 'STATUS_FATURA_CANCELADA') WHERE SEQ_FATURA = ? ");
		
		SmartConnection 		sConn 	= null;
		SmartPreparedStatement 	sStmt 	= null;
		
		try {
		
			sConn 	= new SmartConnection(this.getConn());
			sStmt 	= new SmartPreparedStatement(sConn.prepareStatement(sql.toString()));
			
						
			sStmt.setParameters(fatura, new String[]{"codigo"});
						
			sStmt.getMyPreparedStatement().execute();
			
			
				
		} catch (SQLException e) {
			throw new SmartEnvException(e);
		}finally {
			sStmt.close();
			sConn.close();
		}
	}

	public Collection<ItemFaturaVO> gerarFaturaPrevia(FaturaPreviaFiltroAssembler filtro) throws SmartEnvException {
		StringBuffer sql = new StringBuffer();
		
		sql.append(" SELECT 	A.SEQ_ASSOCIADO AS MATRICULA, ")
			.append(" A.NOM_ASSOCIADO, ")
			.append(" A.NUM_CPF_ASSOCIADO, ")
			.append(" A.NUM_RG_ASSOCIADO, ")
			.append(" A.SEQ_ASSOCIADO AS MATRICULA, ")
			.append(" A.NOM_ASSOCIADO, ")
			.append(" A.NUM_CPF_ASSOCIADO, ")
			.append(" A.NUM_RG_ASSOCIADO, ")
			.append(" A.SEQ_ASSOCIADO AS MATRICULA, ")
			.append(" A.NOM_ASSOCIADO, ")
			.append(" A.NUM_CPF_ASSOCIADO, ")
			.append(" A.NUM_RG_ASSOCIADO, ")
			.append(" B.NOM_BENEFICIARIO, ")
			.append(" B.NUM_CPF_BENEFICIARIO, ")
			.append(" B.NUM_RG_BENEFICIARIO, ")
			.append(" B.NOM_BENEFICIARIO, ")
			.append(" B.NUM_CPF_BENEFICIARIO, ")
			.append(" B.NUM_RG_BENEFICIARIO, ")
			.append(" C.NOM_FANTASIA, ")
			.append(" P.NOM_PLANO, ")
			.append(" P.VAL_PLANO, ")
			.append(" P.NOM_PLANO, ")
			.append(" P.VAL_PLANO, ")
			.append(" P.VAL_PLANO, ")
			.append(" VP.DAT_VINCULACAO, ")
			.append(" VP.DAT_DESVINCULACAO, ")
			.append(" VP.SEQ_VINCULACAO, ")
			.append(" B.SEQ_BENEFICIARIO, ")
			.append(" VP.CODIGO_BENEFICIARIO_PLANO ")
			.append(" FROM 	VW_ASSOCIADO A,  ")
			.append(" VINCULACAO_PLANO VP, ")
			.append(" VW_BENEFICIARIO B, ")
			.append(" CONVENIO C, ")
			.append(" PLANO_CONVENIO P ")
			.append(" WHERE 	A.SEQ_ASSOCIADO = VP.SEQ_ASSOCIADO ")
			.append(" AND	VP.SEQ_PESSOA 	= B.SEQ_BENEFICIARIO ")
			.append(" AND 	VP.SEQ_PLANO 	= P.SEQ_PLANO ")
			.append(" AND 	P.SEQ_CONVENIO 	= C.SEQ_CONVENIO ")
			.append(" AND	VP.DAT_DESVINCULACAO IS NULL ")
			.append(" AND	C.SEQ_CONVENIO = ? ")
			.append(" AND	VP.DAT_VINCULACAO <= ? ")
			.append(" AND	(VP. DAT_DESVINCULACAO IS NULL OR VP.DAT_DESVINCULACAO >= ?) ")
			.append(" ORDER BY A.NOM_ASSOCIADO, B.NOM_BENEFICIARIO");	

		SmartConnection 		sConn 	= null;
		SmartPreparedStatement 	sStmt 	= null;
		SmartResultSet			sRs		= null;
		try {
		
			sConn 	= new SmartConnection(this.getConn());
			sStmt 	= new SmartPreparedStatement(sConn.prepareStatement(sql.toString()));
			
			sStmt.setParameters(filtro, new String[] {"fatura.convenio.codigo", "dataInicioApuracaoAssociados", "dataFimApuracaoAssociados"});
			
			sRs 	= new SmartResultSet(sStmt.getMyPreparedStatement().executeQuery());
			
			return sRs.getJavaBeans(ItemFaturaVO.class, new String[]{	"vinculacao.associado.codigo", 
																		"vinculacao.associado.nome", 
																		"vinculacao.associado.cpf",	
																		"vinculacao.associado.rg",
																		"beneficiario.titular.codigo", 
																		"beneficiario.titular.nome", 
																		"beneficiario.titular.cpf",	
																		"beneficiario.titular.rg", 
																		"vinculacao.beneficiario.titular.codigo", 
																		"vinculacao.beneficiario.titular.nome", 
																		"vinculacao.beneficiario.titular.cpf",	
																		"vinculacao.beneficiario.titular.rg", 
																		"vinculacao.beneficiario.nome",
																		"vinculacao.beneficiario.cpf",
																		"vinculacao.beneficiario.rg",
																		"beneficiario.nome",
																		"beneficiario.cpf",
																		"beneficiario.rg",
																		"fatura.convenio.nomeFantasia",
																		"vinculacao.plano.nome",
																		"vinculacao.plano.valor",
																		"plano.nome",
																		"plano.valor",
																		"valor",
																		"vinculacao.dataVinculacao",
																		"vinculacao.dataDesVinculacao", 
																		"vinculacao.codigo",
																		"beneficiario.codigo",
																		"vinculacao.codigoBeneficiarioPlano"});
			
		} catch (SQLException e) {
			throw new SmartEnvException(e);
		} finally {
			sRs.close();
			sStmt.close();
			sConn.close();
		}
	}
	
	/**
	 * Obtï¿½m todos os itens de fatura com inconsistï¿½ncias na validaï¿½ï¿½o.
	 * @return Coleï¿½ï¿½o de ItemFaturaValidacaoVO (objeto que representa a entidade "Item de Fatura Inconsistente")
	 * @throws SmartEnvException
	 */
	public Collection<ItemFaturaInconsistenteVO> findItensInconsistentesByFatura(FaturaVO fatura) throws SmartEnvException {
		
		StringBuffer sql = new StringBuffer();

		sql.append(" SELECT 	F.SEQ_FATURA, VP.CODIGO_BENEFICIARIO_PLANO, B.NOM_BENEFICIARIO, B.NOM_TITULAR, IF.VAL_ITEM_FATURA, null as VAL_ITEM_FATURA_ARQUIVO, 'NÃƒO ENCONTRADO NO ARQUIVO DO CONVÊNIO' AS TIPO_INCONSISTENCIA ")
			.append(" FROM 	FATURA F, ITEM_FATURA IF, VINCULACAO_PLANO VP, VW_BENEFICIARIO B ")
			.append(" WHERE 	F.SEQ_FATURA = IF.SEQ_FATURA ")
			.append(" AND	IF.SEQ_VINCULACAO = VP.SEQ_VINCULACAO ")
			.append(" AND	VP.SEQ_PESSOA = B.SEQ_BENEFICIARIO ")
			.append(" AND	VP.CODIGO_BENEFICIARIO_PLANO NOT IN ( ")
			.append(" SELECT 	IFA.CODIGO_BENEFICIARIO_PLANO ")
			.append(" FROM 	ITEM_FATURA_ARQUIVO IFA, FATURA_ARQUIVO FA ")
			.append(" WHERE 	IFA.SEQ_FATURA_ARQUIVO = FA.SEQ_FATURA_ARQUIVO ")
			.append(" AND     IFA.CODIGO_BENEFICIARIO_PLANO = VP.CODIGO_BENEFICIARIO_PLANO ")
			.append(" AND	FA.SEQ_FATURA_ARQUIVO = F.SEQ_FATURA AND FA.seq_fatura_arquivo=?")
			.append(" ) ")
			.append(" AND 	F.SEQ_FATURA = ? ")
			.append(" UNION ALL ")
			.append(" SELECT 	FA.SEQ_FATURA_ARQUIVO, IFA.CODIGO_BENEFICIARIO_PLANO, IFA.NOM_BENEFICIARIO, '(nao identificado)' as NOM_TITULAR, null as VAL_ITEM_FATURA, IFA.VAL_ITEM_FATURA_ARQUIVO, 'NÃƒO ENCONTRADO NO CADASTRO ASSERJUF' AS TIPO_INCONSISTENCIA ")
			.append(" FROM	FATURA_ARQUIVO FA, ITEM_FATURA_ARQUIVO IFA ")
			.append(" WHERE 	FA.SEQ_FATURA_ARQUIVO = IFA.SEQ_FATURA_ARQUIVO ")
			.append(" AND	IFA.CODIGO_BENEFICIARIO_PLANO NOT IN ( ")
			.append(" SELECT 	VP.CODIGO_BENEFICIARIO_PLANO ")
			.append(" FROM 	FATURA F, ITEM_FATURA IF, VINCULACAO_PLANO VP, VW_BENEFICIARIO B ")
			.append(" WHERE 	F.SEQ_FATURA = IF.SEQ_FATURA ")
			.append(" AND	IF.SEQ_VINCULACAO = VP.SEQ_VINCULACAO ")
			.append(" AND	VP.SEQ_PESSOA = B.SEQ_BENEFICIARIO ")
			.append(" AND	F.seq_fatura=?")
			.append(" ) ")
			.append(" AND FA.SEQ_FATURA_ARQUIVO = ? ")
			.append(" UNION ALL ")
			.append(" SELECT 	F.SEQ_FATURA, VP.CODIGO_BENEFICIARIO_PLANO, B.NOM_BENEFICIARIO, B.NOM_TITULAR, IF.VAL_ITEM_FATURA, IFA.VAL_ITEM_FATURA_ARQUIVO, 'VALOR NÃO BATE' AS TIPO_INCONSISTENCIA ")
			.append(" FROM	FATURA F, ITEM_FATURA IF, VINCULACAO_PLANO VP, VW_BENEFICIARIO B, ITEM_FATURA_ARQUIVO IFA ")
			.append(" WHERE 	F.SEQ_FATURA = IF.SEQ_FATURA ")
			.append(" AND	IF.SEQ_VINCULACAO = VP.SEQ_VINCULACAO ")
			.append(" AND	VP.SEQ_PESSOA = B.SEQ_BENEFICIARIO ")
			.append(" AND	IFA.SEQ_FATURA_ARQUIVO = F.SEQ_FATURA ")
			.append(" AND	IFA.CODIGO_BENEFICIARIO_PLANO = VP.CODIGO_BENEFICIARIO_PLANO  ")
			.append(" AND	IFA.VAL_ITEM_FATURA_ARQUIVO <> IF.VAL_ITEM_FATURA ")
			.append(" AND 	F.SEQ_FATURA = ? ")
			.append(" ORDER BY NOM_TITULAR, NOM_BENEFICIARIO, CODIGO_BENEFICIARIO_PLANO ");	
		
		SmartConnection 		sConn 	= null;
		SmartPreparedStatement 	sStmt 	= null;
		SmartResultSet			sRs		= null;
		try {
		
			sConn 	= new SmartConnection(this.getConn());
			sStmt 	= new SmartPreparedStatement(sConn.prepareStatement(sql.toString()));
			
			sStmt.setParameters(fatura, new String[] {"codigo","codigo","codigo", "codigo", "codigo"});
			
			sRs 	= new SmartResultSet(sStmt.getMyPreparedStatement().executeQuery());
			
			return sRs.getJavaBeans(ItemFaturaInconsistenteVO.class, new String[]{	
																			"fatura.codigo",
																			"vinculacao.codigoBeneficiarioPlano",
																			"vinculacao.beneficiario.nome",
																			"vinculacao.associado.nome",
																			"valor",
																			"valorArquivo",
																			"tipoInconsistencia"
																	});									
																	
			
		} catch (SQLException e) {
			throw new SmartEnvException(e);
		} finally {
			sRs.close();
			sStmt.close();
			sConn.close();
		}
	}
	
	public Collection<ItemFaturaInconsistenteVO> findRelatorioInconsistenciasByCodigo(FaturaArquivoVO faturaArquivo) throws SmartEnvException {
		StringBuffer sql = new StringBuffer();

		sql.append(" SELECT 	SEQ_FATURA, CODIGO_BENEFICIARIO_PLANO, NOM_BENEFICIARIO, NOM_TITULAR, VAL_ITEM_FATURA, VAL_ITEM_FATURA_ARQUIVO, TIPO_INCONSISTENCIA ")
			.append(" FROM 	RELATORIO_INCONSISTENCIAS")
			.append(" WHERE 	SEQ_RELATORIO_INCONSISTENCIAS = ? ")
			.append(" ORDER BY NOM_TITULAR, NOM_BENEFICIARIO, CODIGO_BENEFICIARIO_PLANO ");	
		
		SmartConnection 		sConn 	= null;
		SmartPreparedStatement 	sStmt 	= null;
		SmartResultSet			sRs		= null;
		try {
		
			sConn 	= new SmartConnection(this.getConn());
			sStmt 	= new SmartPreparedStatement(sConn.prepareStatement(sql.toString()));
			
			sStmt.setParameters(faturaArquivo, new String[] {"codigoRelatorioInconsistencias"});
			
			sRs 	= new SmartResultSet(sStmt.getMyPreparedStatement().executeQuery());
			
			return sRs.getJavaBeans(ItemFaturaInconsistenteVO.class, new String[]{	
																			"fatura.codigo",
																			"vinculacao.codigoBeneficiarioPlano",
																			"vinculacao.beneficiario.nome",
																			"vinculacao.associado.nome",
																			"valor",
																			"valorArquivo",
																			"tipoInconsistencia"
																	});									
																	
			
		} catch (SQLException e) {
			throw new SmartEnvException(e);
		} finally {
			sRs.close();
			sStmt.close();
			sConn.close();
		}
	}

	public void insertFaturaArquivo(FaturaVO faturaArquivo) throws SmartEnvException {
		StringBuffer sql = new StringBuffer(" INSERT INTO FATURA_ARQUIVO (seq_fatura_arquivo, val_fatura_arquivo) VALUES (?, ?) ");
		
		SmartConnection 		sConn 	= null;
		SmartPreparedStatement 	sStmt 	= null;
		
		try {
		
			sConn 	= new SmartConnection(this.getConn());
			sStmt 	= new SmartPreparedStatement(sConn.prepareStatement(sql.toString()));
						
			sStmt.setParameters(faturaArquivo, new String[]{"codigo","valorFatura"});
						
			sStmt.getMyPreparedStatement().execute();
			
				
		} catch (SQLException e) {
			throw new SmartEnvException(e);
		}finally {
			sStmt.close();
			sConn.close();
		}
		
	}
	
	public void updateFaturaArquivo(FaturaVO faturaArquivo) throws SmartEnvException {
		StringBuffer sql = new StringBuffer(" UPDATE FATURA_ARQUIVO SET val_fatura_arquivo=? WHERE seq_fatura_arquivo=? ");
		
		SmartConnection 		sConn 	= null;
		SmartPreparedStatement 	sStmt 	= null;
		try {
			sConn 	= new SmartConnection(this.getConn());
			sStmt 	= new SmartPreparedStatement(sConn.prepareStatement(sql.toString()));
						
			sStmt.setParameters(faturaArquivo, new String[]{"valorFatura", "codigo"});
						
			sStmt.getMyPreparedStatement().execute();
				
		} catch (SQLException e) {
			throw new SmartEnvException(e);
		}finally {
			sStmt.close();
			sConn.close();
		}
		
	}
	
	public void deleteItemFaturaArquivo(FaturaVO faturaArquivo) throws SmartEnvException {
		StringBuffer sql = new StringBuffer(" DELETE FROM ITEM_FATURA_ARQUIVO WHERE seq_fatura_arquivo=? ");
		
		SmartConnection 		sConn 	= null;
		SmartPreparedStatement 	sStmt 	= null;
		try {
			sConn 	= new SmartConnection(this.getConn());
			sStmt 	= new SmartPreparedStatement(sConn.prepareStatement(sql.toString()));
						
			sStmt.setParameters(faturaArquivo, new String[]{"codigo"});
						
			sStmt.getMyPreparedStatement().execute();
		} catch (SQLException e) {
			throw new SmartEnvException(e);
		}finally {
			sStmt.close();
			sConn.close();
		}
	}
	
	public FaturaArquivoVO findBYPrimaryKey(FaturaArquivoVO faturaArquivo) throws SmartEnvException{
		StringBuffer sql = new StringBuffer();
		
		sql.append("SELECT seq_fatura_arquivo, val_fatura_arquivo FROM fatura_arquivo WHERE seq_fatura_arquivo=? ");	

		SmartConnection 		sConn 	= null;
		SmartPreparedStatement 	sStmt 	= null;
		SmartResultSet			sRs		= null;
		try {
		
			sConn 	= new SmartConnection(this.getConn());
			sStmt 	= new SmartPreparedStatement(sConn.prepareStatement(sql.toString()));
			
			sStmt.setParameters(faturaArquivo, new String[] {"codigo"});
			
			sRs 	= new SmartResultSet(sStmt.getMyPreparedStatement().executeQuery());
			
			return (FaturaArquivoVO) sRs.getJavaBean(faturaArquivo, new String[]{"codigo", "valorFatura"});									
																	
			
		} catch (SQLException e) {
			throw new SmartEnvException(e);
		} finally {
			sRs.close();
			sStmt.close();
			sConn.close();
		} 
	}
	
	
	public ItemFaturaVO insertItemArquivo(ItemFaturaVO item) throws SmartEnvException {

		StringBuffer 			sql		= new StringBuffer(" INSERT INTO ITEM_FATURA_ARQUIVO (SEQ_ITEM_FATURA_ARQUIVO, CODIGO_BENEFICIARIO_PLANO, NOM_BENEFICIARIO, FLG_TIPO_BENEFICIARIO, VAL_ITEM_FATURA_ARQUIVO, SEQ_FATURA_ARQUIVO) VALUES (?, ?, ?, ?, ?, ?) ");
		SmartConnection 		sConn 	= null;
		SmartPreparedStatement 	sStmt 	= null;

		try {

			sConn 	= new SmartConnection(this.getConn());
			sStmt 	= new SmartPreparedStatement(sConn.prepareStatement(sql.toString()));

			item.setCodigo(new Integer(getSequence("SEQ_ITEM_FATURA_ARQUIVO").intValue()));

			sStmt.setParameters(item, new String[]{"codigo", "vinculacao.codigoBeneficiarioPlano","vinculacao.beneficiario.nome","vinculacao.beneficiario.tipoBeneficiario", "valor", "fatura.codigo"});

			sStmt.getMyPreparedStatement().execute();

			return item;

		} catch (SQLException e) {
			throw new SmartEnvException(e);
		}finally {
			sStmt.close();
			sConn.close();
		}
	}

	/**
	 * Obtï¿½m todos as faturas por filtro.
	 * @return Coleï¿½ï¿½o de FaturaVO (objeto que representa a entidade "Fatura")
	 * @throws SmartEnvException
	 */
	public Collection<ItemFaturaVO> findItensByFatura(FaturaVO fatura) throws SmartEnvException {
		
		StringBuffer sql = new StringBuffer();
		
		sql.append(" SELECT  IF.SEQ_ITEM_FATURA, ")
			.append(" IF.SEQ_VINCULACAO, ")
			.append(" IF.SEQ_FATURA, ")
			.append(" IF.VAL_ITEM_FATURA, ")
			.append(" IF.NUM_ITEM_FATURA, ")
			.append(" VP.SEQ_PESSOA AS SEQ_BENEFICIARIO, ")
			.append(" B.NOM_BENEFICIARIO, ")
			.append(" VP.SEQ_ASSOCIADO AS SEQ_TITULAR, ")
			.append(" B.NOM_TITULAR, ")
			.append(" VP.SEQ_PLANO, ")
			.append(" P.NOM_PLANO, ")
			.append(" VP.CODIGO_BENEFICIARIO_PLANO ")
			.append(" FROM 	ITEM_FATURA IF, ")
			.append(" VINCULACAO_PLANO VP, ")
			.append(" VW_BENEFICIARIO B, ")
			.append(" PLANO_CONVENIO P ")
			.append(" WHERE IF.SEQ_VINCULACAO = VP.SEQ_VINCULACAO ")
			.append(" AND	VP.SEQ_PESSOA	= B.SEQ_BENEFICIARIO ")
			.append(" AND	VP.SEQ_ASSOCIADO = B.SEQ_ASSOCIADO ")
			.append(" AND	VP.SEQ_PLANO = P.SEQ_PLANO ")
			.append(" AND	IF.SEQ_FATURA = ? ")
			.append(" ORDER BY B.NOM_TITULAR, B.NOM_BENEFICIARIO ");	

		SmartConnection 		sConn 	= null;
		SmartPreparedStatement 	sStmt 	= null;
		SmartResultSet			sRs		= null;
		try {
		
			sConn 	= new SmartConnection(this.getConn());
			sStmt 	= new SmartPreparedStatement(sConn.prepareStatement(sql.toString()));
			
			sStmt.setParameters(fatura, new String[] {"codigo"});
			
			sRs 	= new SmartResultSet(sStmt.getMyPreparedStatement().executeQuery());
			
			return sRs.getJavaBeans(ItemFaturaVO.class, new String[]{	"codigo","vinculacao.codigo","fatura.codigo","valor","numero","beneficiario.codigo","beneficiario.nome",
																		"beneficiario.titular.codigo","beneficiario.titular.nome","vinculacao.plano.codigo","vinculacao.plano.nome", "vinculacao.codigoBeneficiarioPlano"
																	});									
																	
			
		} catch (SQLException e) {
			throw new SmartEnvException(e);
		} finally {
			sRs.close();
			sStmt.close();
			sConn.close();
		}
	}

	public FaturaArquivoVO salvarRelatorioInconsistencias(FaturaArquivoVO faturaArquivo) throws SmartEnvException {
		StringBuffer sql = new StringBuffer();
		sql.append(" INSERT INTO RELATORIO_INCONSISTENCIAS (SEQ_RELATORIO_INCONSISTENCIAS, DAT_RELATORIO_INCONSISTENCIAS, SEQ_FATURA, CODIGO_BENEFICIARIO_PLANO, NOM_BENEFICIARIO, NOM_TITULAR, VAL_ITEM_FATURA, VAL_ITEM_FATURA_ARQUIVO, TIPO_INCONSISTENCIA) ")
			.append(" SELECT 	?, CURRENT_TIMESTAMP, F.SEQ_FATURA, VP.CODIGO_BENEFICIARIO_PLANO, B.NOM_BENEFICIARIO, B.NOM_TITULAR, IF.VAL_ITEM_FATURA, null as VAL_ITEM_FATURA_ARQUIVO, 'NÃƒO ENCONTRADO NO ARQUIVO DO CONVÊNIO' AS TIPO_INCONSISTENCIA ")
			.append(" FROM 	FATURA F, ITEM_FATURA IF, VINCULACAO_PLANO VP, VW_BENEFICIARIO B ")
			.append(" WHERE 	F.SEQ_FATURA = IF.SEQ_FATURA ")
			.append(" AND	IF.SEQ_VINCULACAO = VP.SEQ_VINCULACAO ")
			.append(" AND	VP.SEQ_PESSOA = B.SEQ_BENEFICIARIO ")
			.append(" AND	VP.CODIGO_BENEFICIARIO_PLANO NOT IN ( ")
			.append(" SELECT 	IFA.CODIGO_BENEFICIARIO_PLANO ")
			.append(" FROM 	ITEM_FATURA_ARQUIVO IFA, FATURA_ARQUIVO FA ")
			.append(" WHERE 	IFA.SEQ_FATURA_ARQUIVO = FA.SEQ_FATURA_ARQUIVO ")
			.append(" AND     IFA.CODIGO_BENEFICIARIO_PLANO = VP.CODIGO_BENEFICIARIO_PLANO ")
			.append(" AND	FA.SEQ_FATURA_ARQUIVO = F.SEQ_FATURA AND FA.seq_fatura_arquivo=?")
			.append(" ) ")
			.append(" AND 	F.SEQ_FATURA = ? ")
			.append(" UNION ALL ")
			.append(" SELECT 	?, CURRENT_TIMESTAMP, FA.SEQ_FATURA_ARQUIVO, IFA.CODIGO_BENEFICIARIO_PLANO, IFA.NOM_BENEFICIARIO, '(nao identificado)' as NOM_TITULAR, null as VAL_ITEM_FATURA, IFA.VAL_ITEM_FATURA_ARQUIVO, 'NÃƒO ENCONTRADO NO CADASTRO ASSERJUF' AS TIPO_INCONSISTENCIA ")
			.append(" FROM	FATURA_ARQUIVO FA, ITEM_FATURA_ARQUIVO IFA ")
			.append(" WHERE 	FA.SEQ_FATURA_ARQUIVO = IFA.SEQ_FATURA_ARQUIVO ")
			.append(" AND	IFA.CODIGO_BENEFICIARIO_PLANO NOT IN ( ")
			.append(" SELECT 	VP.CODIGO_BENEFICIARIO_PLANO ")
			.append(" FROM 	FATURA F, ITEM_FATURA IF, VINCULACAO_PLANO VP, VW_BENEFICIARIO B ")
			.append(" WHERE 	F.SEQ_FATURA = IF.SEQ_FATURA ")
			.append(" AND	IF.SEQ_VINCULACAO = VP.SEQ_VINCULACAO ")
			.append(" AND	VP.SEQ_PESSOA = B.SEQ_BENEFICIARIO ")
			.append(" AND	F.seq_fatura=?")
			.append(" ) ")
			.append(" AND FA.SEQ_FATURA_ARQUIVO = ? ")
			.append(" UNION ALL ")
			.append(" SELECT ?, CURRENT_TIMESTAMP, 	F.SEQ_FATURA, VP.CODIGO_BENEFICIARIO_PLANO, B.NOM_BENEFICIARIO, B.NOM_TITULAR, IF.VAL_ITEM_FATURA, IFA.VAL_ITEM_FATURA_ARQUIVO, 'VALOR NÃO BATE' AS TIPO_INCONSISTENCIA ")
			.append(" FROM	FATURA F, ITEM_FATURA IF, VINCULACAO_PLANO VP, VW_BENEFICIARIO B, ITEM_FATURA_ARQUIVO IFA ")
			.append(" WHERE 	F.SEQ_FATURA = IF.SEQ_FATURA ")
			.append(" AND	IF.SEQ_VINCULACAO = VP.SEQ_VINCULACAO ")
			.append(" AND	VP.SEQ_PESSOA = B.SEQ_BENEFICIARIO ")
			.append(" AND	IFA.SEQ_FATURA_ARQUIVO = F.SEQ_FATURA ")
			.append(" AND	IFA.CODIGO_BENEFICIARIO_PLANO = VP.CODIGO_BENEFICIARIO_PLANO  ")
			.append(" AND	IFA.VAL_ITEM_FATURA_ARQUIVO <> IF.VAL_ITEM_FATURA ")
			.append(" AND 	F.SEQ_FATURA = ? ")
			.append(" ORDER BY NOM_TITULAR, NOM_BENEFICIARIO, CODIGO_BENEFICIARIO_PLANO ");	
		SmartConnection 		sConn 	= null;
		SmartPreparedStatement 	sStmt 	= null;

		try {

			sConn 	= new SmartConnection(this.getConn());
			sStmt 	= new SmartPreparedStatement(sConn.prepareStatement(sql.toString()));

			faturaArquivo.setCodigoRelatorioInconsistencias(new Integer(getSequence("SEQ_RELATORIO_INCONSISTENCIAS").intValue()));

			sStmt.setParameters(faturaArquivo, new String[]{"codigoRelatorioInconsistencias", "codigo", "codigo", "codigoRelatorioInconsistencias", "codigo", "codigo", "codigoRelatorioInconsistencias", "codigo"});

			sStmt.getMyPreparedStatement().execute();

			return faturaArquivo;

		} catch (SQLException e) {
			throw new SmartEnvException(e);
		}finally {
			sStmt.close();
			sConn.close();
		}
		
	}
	
}
