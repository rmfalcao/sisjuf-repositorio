package br.org.asserjuf.sisjuf.associados.dados;

import java.sql.SQLException;
import java.util.Collection;

import br.com.falc.smartFW.exception.SmartEnvException;
import br.com.falc.smartFW.persistence.SmartConnection;
import br.com.falc.smartFW.persistence.SmartPreparedStatement;
import br.com.falc.smartFW.persistence.SmartResultSet;
import br.org.asserjuf.sisjuf.associados.AssociadoVO;
import br.org.asserjuf.sisjuf.associados.DocumentoAssociadoVO;
import br.org.asserjuf.sisjuf.associados.convenio.VinculacaoPlanoVO;
import br.org.asserjuf.sisjuf.associados.convenio.VinculadoPlanoAssembler;
import br.org.asserjuf.sisjuf.dados.SisjufDAOPostgres;

/**
 * Classe de acesso ao banco de dados da entidade "Documentos do Associado" do Sisjuf
 * @author Paulo
 *
 */
public class DocumentosAssociadoDAO extends SisjufDAOPostgres {

	
	
	/**
	 * Insere uma um documento de um associado. Tabelas: DOCUMENTOS_ASSOCIADO
	 * @param vo Instância da classe DocumentoAssociadoVO com os valores do registro a ser inserido.
	 * @throws SmartEnvException
	 */
	public DocumentoAssociadoVO insert(DocumentoAssociadoVO vo) throws SmartEnvException {
		
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO DOCUMENTOS_ASSOCIADO ( SEQ_DOCUMENTO, SEQ_ASSOCIADO, NOM_DOCUMENTO, DAT_DOCUMENTO, NOM_ARQUIVO,  SEQ_USUARIO_CRIACAO) "); 
		sql.append("VALUES(?,?,?,?,?,?,?)");
		// TODO falta o BLOB
		
		SmartConnection 		sConn 	= null;
		SmartPreparedStatement 	sStmt 	= null;
		
		try {
		
			sConn 	= new SmartConnection(this.getConn());
			sStmt 	= new SmartPreparedStatement(sConn.prepareStatement(sql.toString()));
			
			vo.setCodigo(new Integer(getSequence("SEQ_DOCUMENTO").intValue()));
						
			sStmt.setParameters(vo, new String[]{"codigo", "associado.codigo","nome","dataDocumento","nomeDoArquivo","associado.codigo"});
						
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
	 * Obtem um documento de um associado. Tabelas: DOCUMENTOS_ASSOCIADO
	 * @param vo Instancia da classe DocumentoAssociadoVO com o codigo do associado
	 * @return Retorna uma instancia da classe DocumentoAssociadoVO com as propriedades preenchidas.
	 * @throws SmartEnvException
	 */
	public DocumentoAssociadoVO findByPrimaryKey(DocumentoAssociadoVO vo) throws SmartEnvException {
		
		StringBuffer sql = new StringBuffer();
		
		sql.append("select nom_arquivo from documentos_associado where seq_documento=  ? ");
		// TODO falta o BLOB
		
		SmartConnection 		sConn 	= null;
		SmartPreparedStatement 	sStmt 	= null;
		SmartResultSet			sRs		= null;
		
		try {
			sConn 	= new SmartConnection(this.getConn());
			sStmt 	= new SmartPreparedStatement(sConn.prepareStatement(sql.toString()));
			
			sStmt.setInteger(1, vo.getCodigo());
			
			sRs = new SmartResultSet(sStmt.getMyPreparedStatement().executeQuery());
			  
			return (DocumentoAssociadoVO) sRs.getJavaBean(new DocumentoAssociadoVO(), new String[] {"nomeDoArquivo"});
		} catch (SQLException e) {
			throw new SmartEnvException(e);
		} finally {
			sRs.close();
			sStmt.close();
			sConn.close();
		}
	}	
	
	/**
	 * Obtem a lista de documentos de um associado. Tabelas: DOCUMENTOS_ASSOCIADO
	 * @param vo Instancia da classe AssociadoVO contendo o codigo de um associado
	 * @return Retorna uma colecao de documentos encapsulados em DocumentoAssociadoVO
	 * @throws SmartEnvException
	 */
	public Collection<DocumentoAssociadoVO> findDocumentosByAssociado(AssociadoVO vo) throws SmartEnvException {
		
		StringBuffer sql = new StringBuffer();

		sql.append("select seq_documento, seq_associado, nom_documento, dat_documento, nom_arquivo, dat_cadastro, seq_usuario_criacao from documentos_associado ") 
				.append(" where seq_associado = ? ");
		
		SmartConnection 		sConn 	= null;
		SmartPreparedStatement 	sStmt 	= null;
		SmartResultSet			sRs		= null;
		
		try {
			sConn 	= new SmartConnection(this.getConn());
			sStmt 	= new SmartPreparedStatement(sConn.prepareStatement(sql.toString()));					
			  
			sStmt.setParameters(vo, new String[] {"codigo"});
			
			sRs = new SmartResultSet(sStmt.getMyPreparedStatement().executeQuery());						
			
			return (Collection<DocumentoAssociadoVO>) sRs.getJavaBeans(DocumentoAssociadoVO.class, new String[]{
					"codigo", "associado.codigo","nome","dataDocumento","nomeDoArquivo", "dataCriacao","usuario.codigo"
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
	
		
	
}
