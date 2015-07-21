package br.org.asserjuf.sisjuf.associados.convenio.dados;

import java.sql.SQLException;
import java.util.Collection;

import br.com.falc.smartFW.exception.SmartEnvException;
import br.com.falc.smartFW.persistence.SmartConnection;
import br.com.falc.smartFW.persistence.SmartPreparedStatement;
import br.com.falc.smartFW.persistence.SmartResultSet;
import br.org.asserjuf.sisjuf.associados.RelatorioIRVO;
import br.org.asserjuf.sisjuf.associados.convenio.BeneficiarioIRVO;
import br.org.asserjuf.sisjuf.associados.convenio.BeneficiarioVO;
import br.org.asserjuf.sisjuf.dados.SisjufDAOPostgres;

/**
 * Classe de acesso ao banco de dados da entidade "Convenio" do Sisjuf
 * @author Paulo
 *
 */
public class BeneficiarioDAO extends SisjufDAOPostgres {

	//private static transient Logger	LOG = Logger.getLogger(BeneficiarioDAO.class);
	
	/**
	 * Obt�m todos os beneficiarios de um conv�nio cadastrados na banco de dados.
	 * @return Cole��o de BeneficiarioVO (objeto que representa a entidade "Beneficiario")
	 * @throws SmartEnvException
	 */
	public Collection<BeneficiarioVO> findByFilter(BeneficiarioVO beneficiario) throws SmartEnvException {
		

		
		StringBuffer sql = new StringBuffer();
	
		sql.append(" SELECT 	C.NOM_FANTASIA, ")
				.append(" PC.NOM_PLANO, ")
				.append(" T.SEQ_PESSOA AS SEQ_TITULAR, ")
				.append(" T.NOM_PESSOA AS TITULAR, ")
				.append(" B.SEQ_PESSOA AS SEQ_BENEFICIARIO, ")
				.append(" B.NOM_PESSOA  AS BENEFICIARIO, ")
				.append(" CASE ")
				.append(" WHEN EXISTS (SELECT D.SEQ_PESSOA FROM DEPENDENTE D WHERE B.SEQ_PESSOA = D.SEQ_PESSOA) THEN 'DEPENDENTE' ")
				.append(" WHEN EXISTS (SELECT F.SEQ_PESSOA FROM PESSOA F WHERE B.SEQ_PESSOA = F.SEQ_PESSOA) THEN 'FILHO' ")
				//.append(" 		WHEN EXISTS (SELECT F.SEQ_PESSOA FROM PESSOA CONJUGE WHERE B.SEQ_PESSOA = F.SEQ_PESSOA) THEN 'FILHO' ")
				.append(" ELSE 'TITULAR' ")
				.append(" END AS TIPO_BENEFICIARIO, ")
				.append(" PC.VAL_PLANO, ")
				.append(" VP.SEQ_VINCULACAO ")
				.append(" FROM 	PESSOA B,  ")
				.append(" ASSOCIADO A, ")
				.append(" PESSOA T, ")
				.append(" VINCULACAO_PLANO VP, ")
				.append(" PLANO_CONVENIO PC, ")
				.append(" CONVENIO C ")
				.append(" WHERE	B.SEQ_PESSOA = VP.SEQ_PESSOA ")
				.append(" AND	VP.SEQ_ASSOCIADO = A.SEQ_PESSOA ")
				.append(" AND	VP.SEQ_PLANO = PC.SEQ_PLANO ")
				.append(" AND	PC.SEQ_CONVENIO = C.SEQ_CONVENIO ")
				.append(" AND	A.SEQ_PESSOA = T.SEQ_PESSOA ")
				.append(" AND	VP.DAT_DESVINCULACAO IS NULL ")
				.append(" AND	C.SEQ_CONVENIO = ? ")
				.append(" AND	(? is null OR upper(B.NOM_PESSOA) LIKE '%' || upper(?) || '%') ")
				.append(" AND	(? is null OR upper(T.NOM_PESSOA) LIKE '%' || upper(?) || '%') ")
				.append(" AND	(? IS NULL OR PC.SEQ_PLANO = ?) ")
				.append(" AND	(? IS NULL OR A.NUM_MATRICULA_JUSTICA_ASSOCIADO = ?) ");
		
				if(beneficiario.getDataInicioVinculacao() == null && beneficiario.getDataFimVinculacao() == null){
					sql.append(" AND	 VP.DAT_VINCULACAO <=  current_date ")
					.append(" AND	VP.DAT_DESVINCULACAO is null ");	
				}else{
					if(beneficiario.getDataInicioVinculacao() != null){
						sql.append(" AND	(VP.DAT_VINCULACAO >= ?) ");	
					}
					if(beneficiario.getDataFimVinculacao() != null){
						sql.append(" AND	(VP.DAT_VINCULACAO < ?) ");	
					}
				}

		SmartConnection 		sConn 	= null;
		SmartPreparedStatement 	sStmt 	= null;
		SmartResultSet			sRs		= null;
		try {
		
			sConn 	= new SmartConnection(this.getConn());
			sStmt 	= new SmartPreparedStatement(sConn.prepareStatement(sql.toString()));
			sStmt.setParameters(beneficiario, new String[] {"plano.convenio.codigo", "nome",  "nome", "titular.nome", "titular.nome", "plano.codigo", "plano.codigo",
					"titular.matriculaJustica","titular.matriculaJustica"});
			
			int i = 10;
			if(beneficiario.getDataInicioVinculacao() != null || beneficiario.getDataFimVinculacao() != null){
				if(beneficiario.getDataInicioVinculacao() != null){
					sStmt.setDate(i++,beneficiario.getDataInicioVinculacao());
				}
				if(beneficiario.getDataFimVinculacao() != null){
					sStmt.setDate(i++,beneficiario.getDataFimVinculacao());
				}
			}
			
			sRs 	= new SmartResultSet(sStmt.getMyPreparedStatement().executeQuery());
			
			return sRs.getJavaBeans(BeneficiarioVO.class, new String[]{
									"plano.convenio.nomeFantasia", 
									"plano.nome", 
									"titular.codigo",
									"titular.nome",
									"codigo",
									"nome",
									"tipoBeneficiario",
									"plano.valor",
									"vinculacao.codigo"
									});
		} catch (SQLException e) {
			throw new SmartEnvException(e);
		} finally {
			if(sRs != null){
				sRs.close();	
			}
			if(sStmt != null){
				sStmt.close();	
			}
			if(sConn != null){
				sConn.close();	
			}
		}
	}
	
	public Collection<BeneficiarioIRVO> findRelatorioIR(RelatorioIRVO filtro) throws SmartEnvException {
		
		StringBuffer sql = new StringBuffer(" SELECT NOM_FANTASIA, DES_CNPJ, NOM_TITULAR, NOM_BENEFICIARIO, TOTAL_PAGO_BENEFICIARIO FROM ( ")
.append(" SELECT  CONVENIO.NOM_FANTASIA, ")
.append(" 	CONVENIO.DES_CNPJ, ")
.append(" 	TIT.NOM_PESSOA || ' (TOTAL CONVENIO ' || CONVENIO.NOM_FANTASIA || ')' AS NOM_TITULAR, ")
.append(" 	null as NOM_BENEFICIARIO, ")
.append(" 	SUM(ITEM_FATURA.VAL_ITEM_FATURA) AS TOTAL_PAGO_BENEFICIARIO ")
.append(" FROM    CONVENIO, ")
.append(" 	PLANO_CONVENIO, ")
.append(" 	VINCULACAO_PLANO, ")
.append(" 	VW_BENEFICIARIO BENEF, ")
.append(" 	PESSOA TIT, ")
.append(" 	FATURA, ")
.append(" 	ITEM_FATURA ")
.append(" WHERE   CONVENIO.SEQ_CONVENIO = PLANO_CONVENIO.SEQ_CONVENIO ")
.append(" AND	VINCULACAO_PLANO.SEQ_PLANO= PLANO_CONVENIO.SEQ_PLANO ")
.append(" AND	VINCULACAO_PLANO.SEQ_PESSOA = BENEF.SEQ_BENEFICIARIO ")
.append(" AND	VINCULACAO_PLANO.SEQ_ASSOCIADO = TIT.SEQ_PESSOA ")
.append(" AND	ITEM_FATURA.SEQ_VINCULACAO = VINCULACAO_PLANO.SEQ_VINCULACAO ")
.append(" AND	ITEM_FATURA.SEQ_FATURA = FATURA.SEQ_FATURA ")
.append(" AND	date_part('year', FATURA.DAT_VENCIMENTO_FATURA) = ? ")
.append(" AND	(? IS NULL OR TIT.SEQ_PESSOA = ?) ")
.append(" GROUP BY CONVENIO.NOM_FANTASIA, ")
.append(" 	CONVENIO.DES_CNPJ, ")
.append(" 	TIT.NOM_PESSOA ")
.append(" UNION ")
.append(" SELECT  CONVENIO.NOM_FANTASIA, ")
.append(" 	CONVENIO.DES_CNPJ, ")
.append(" 	TIT.NOM_PESSOA, ")
.append(" 	BENEF.NOM_BENEFICIARIO, ")
.append(" 	SUM(ITEM_FATURA.VAL_ITEM_FATURA) AS TOTAL_PAGO_BENEFICIARIO ")
.append(" FROM    CONVENIO, ")
.append(" 	PLANO_CONVENIO, ")
.append(" 	VINCULACAO_PLANO, ")
.append(" 	VW_BENEFICIARIO BENEF, ")
.append(" 	PESSOA TIT, ")
.append(" 	FATURA, ")
.append(" 	ITEM_FATURA ")
.append(" WHERE   CONVENIO.SEQ_CONVENIO = PLANO_CONVENIO.SEQ_CONVENIO ")
.append(" AND	VINCULACAO_PLANO.SEQ_PLANO= PLANO_CONVENIO.SEQ_PLANO ")
.append(" AND	VINCULACAO_PLANO.SEQ_PESSOA = BENEF.SEQ_BENEFICIARIO ")
.append(" AND	VINCULACAO_PLANO.SEQ_ASSOCIADO = TIT.SEQ_PESSOA ")
.append(" AND	ITEM_FATURA.SEQ_VINCULACAO = VINCULACAO_PLANO.SEQ_VINCULACAO ")
.append(" AND	ITEM_FATURA.SEQ_FATURA = FATURA.SEQ_FATURA ")
.append(" AND	date_part('year', FATURA.DAT_VENCIMENTO_FATURA) = ? ")
.append(" AND	(? IS NULL OR TIT.SEQ_PESSOA = ?) ")
.append(" GROUP BY CONVENIO.NOM_FANTASIA, ")
.append(" 	CONVENIO.DES_CNPJ, ")
.append(" 	TIT.NOM_PESSOA, ")
.append(" 	BENEF.NOM_BENEFICIARIO ")
.append(" ) TMP ")
.append(" ORDER BY NOM_FANTASIA, NOM_TITULAR, NOM_BENEFICIARIO ");

		SmartConnection 		sConn 	= null;
		SmartPreparedStatement 	sStmt 	= null;
		SmartResultSet			sRs		= null;
		
		try {
		
			sConn 	= new SmartConnection(this.getConn());
			sStmt 	= new SmartPreparedStatement(sConn.prepareStatement(sql.toString()));
			
			sStmt.setParameters(filtro, new String[] {"ano", "associado.codigo", "associado.codigo", "ano", "associado.codigo", "associado.codigo"});
			
			
			sRs 	= new SmartResultSet(sStmt.getMyPreparedStatement().executeQuery());
			
			return sRs.getJavaBeans(BeneficiarioIRVO.class, new String[] {"plano.convenio.nomeFantasia", "plano.convenio.cnpj", "titular.nome", "nome", "valorPago"} );
		
		} catch (SQLException e) {
			throw new SmartEnvException(e);

		} finally {

			sRs.close();
			sStmt.close();
			sConn.close();

		}
		
	}
		
}
