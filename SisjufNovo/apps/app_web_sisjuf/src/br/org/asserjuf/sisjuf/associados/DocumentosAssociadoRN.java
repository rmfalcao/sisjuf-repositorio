package br.org.asserjuf.sisjuf.associados;

import java.util.Collection;

import br.com.falc.smartFW.exception.SmartAppException;
import br.com.falc.smartFW.exception.SmartEnvException;
import br.org.asserjuf.sisjuf.associados.convenio.dados.VinculacaoPlanoDAO;
import br.org.asserjuf.sisjuf.associados.dados.DocumentosAssociadoDAO;

/**
 * Classe que encapsula os m�todos de acesso a Banco da entidade que guarda a vincula��o da pessoa com o plano
 * @author Paulo
 *
 */
public class DocumentosAssociadoRN {

	
	
	/**
	 * Classe de acesso ao banco de dados da entidade "VinculadoPlano" do Sisjuf.
	 */
	private DocumentosAssociadoDAO	documentosAssociadoDAO;

	public void setDocumentosAssociadoDAO(DocumentosAssociadoDAO documentosAssociadoDAO) {
		this.documentosAssociadoDAO = documentosAssociadoDAO;
	}
	
	
	public DocumentoAssociadoVO insert(DocumentoAssociadoVO vo) throws SmartEnvException, SmartAppException {
		return documentosAssociadoDAO.insert(vo);
	}		
	
	
	
	public DocumentoAssociadoVO findByPrimaryKey(DocumentoAssociadoVO vo) throws SmartEnvException, SmartAppException {
		return documentosAssociadoDAO.findByPrimaryKey(vo);		
	}	
	
	
	
	
	public Collection<DocumentoAssociadoVO> findDocumentosByAssociado(AssociadoVO vo) throws SmartEnvException, SmartAppException {
		return documentosAssociadoDAO.findDocumentosByAssociado(vo);
	}		
	
}
