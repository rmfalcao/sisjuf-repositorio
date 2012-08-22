package br.org.asserjuf.sisjuf.associados.convenio.dados;

import java.sql.SQLException;
import java.util.Collection;

import br.com.falc.smartFW.exception.SmartEnvException;
import br.com.falc.smartFW.persistence.SmartConnection;
import br.com.falc.smartFW.persistence.SmartPreparedStatement;
import br.com.falc.smartFW.persistence.SmartResultSet;
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
	 * Obtém todos os beneficiarios de um convênio cadastrados na banco de dados.
	 * @return Coleção de BeneficiarioVO (objeto que representa a entidade "Beneficiario")
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
				.append(" AND	 VP.DAT_VINCULACAO <=  current_date ")
				.append(" AND	VP.DAT_DESVINCULACAO is null ");	

		SmartConnection 		sConn 	= null;
		SmartPreparedStatement 	sStmt 	= null;
		SmartResultSet			sRs		= null;
		try {
		
			sConn 	= new SmartConnection(this.getConn());
			sStmt 	= new SmartPreparedStatement(sConn.prepareStatement(sql.toString()));
			sStmt.setParameters(beneficiario, new String[] {"plano.convenio.codigo", "nome",  "nome", "titular.nome", "titular.nome", "plano.codigo", "plano.codigo"});
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
			sRs.close();
			sStmt.close();
			sConn.close();
		}
	}
		
}
