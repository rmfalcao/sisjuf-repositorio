package br.org.asserjuf.sisjuf.associados;

import java.util.Collection;

import br.com.falc.smartFW.exception.SmartAppException;
import br.com.falc.smartFW.exception.SmartEnvException;
import br.org.asserjuf.sisjuf.associados.convenio.dados.VinculacaoPlanoDAO;
import br.org.asserjuf.sisjuf.associados.dados.DocumentosAssociadoDAO;

/**
 * Classe que encapsula os mï¿½todos de acesso a Banco da entidade que guarda a vinculaï¿½ï¿½o da pessoa com o plano
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
		
		validarCamposObrigatorios(vo);
		
		return documentosAssociadoDAO.insert(vo);
	}		
	
	
	
	private void validarCamposObrigatorios(DocumentoAssociadoVO vo) throws SmartAppException {
		if (vo.getNomeDoArquivo() == null || "".equals(vo.getNomeDoArquivo())) {
			throw new SmartAppException("Nome do arquivo não encontrado.");
		}
		
		if (vo.getDataDocumento() == null) {
			throw new SmartAppException("Informe a data do documento.");
		}
		
		if (vo.getNome() == null || "".equals(vo.getNome())) {
			throw new SmartAppException("Informe o nome do documento.");
		}
		
		
	}


	public DocumentoAssociadoVO findByPrimaryKey(DocumentoAssociadoVO vo) throws SmartEnvException, SmartAppException {
		return documentosAssociadoDAO.findByPrimaryKey(vo);		
	}	
	
	
	
	
	public Collection<DocumentoAssociadoVO> findDocumentosByAssociado(AssociadoVO vo) throws SmartEnvException, SmartAppException {
		return documentosAssociadoDAO.findDocumentosByAssociado(vo);
	}		
	
}
