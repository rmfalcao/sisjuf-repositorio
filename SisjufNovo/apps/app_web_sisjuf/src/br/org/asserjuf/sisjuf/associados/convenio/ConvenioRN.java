package br.org.asserjuf.sisjuf.associados.convenio;

import java.util.Collection;
import java.util.Date;

import br.com.falc.smartFW.exception.SmartAppException;
import br.com.falc.smartFW.exception.SmartEnvException;
import br.org.asserjuf.sisjuf.associados.convenio.dados.ConvenioDAO;
import br.org.asserjuf.sisjuf.util.SisjufConstantesIf;
/**
 * Classe que encapsula os métodos de acesso a Banco de convenio
 * @author Paulo
 *
 */
public class ConvenioRN {

	/**
	 * Classe de acesso ao banco de dados da entidade "Convênio" do Sisjuf.
	 */
	private ConvenioDAO	convenioDAO;
	
	/**
	 * Classe que encapsula as regras de negócio de "Plano de convênio" do Sisjuf.
	 */
	private PlanoConvenioRN	planoConvenioRN;

	public void setConvenioDAO(ConvenioDAO convenioDAO) {
		this.convenioDAO = convenioDAO;
	}		
	
	
	
	public void setPlanoConvenioRN(PlanoConvenioRN planoConvenioRN) {
		this.planoConvenioRN = planoConvenioRN;
	}



	/**
	 * Obtém todos os convênios.
	 * @return Coleção de convênios, encapsulados na classe ConvenioVO.
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public Collection<ConvenioVO> findAll() throws SmartEnvException, SmartAppException {
		return convenioDAO.findAll();
	}
	
	/**
	 * Obtém um convênio por chave.
	 * @param vo Instância de ConvenioVO que encapsula o código do convênio que se deseja recuperar.
	 * @return Convênio desejada.
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public ConvenioVO findByPrimaryKey(ConvenioVO vo) throws SmartEnvException, SmartAppException {
		
		return convenioDAO.findByPrimaryKey(vo);
	}
	
	/**
	 * Atualiza um convênio.
	 * @param vo Convênio que se deseja atualizar.
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public void update(ConvenioVO vo) throws SmartEnvException, SmartAppException {
		
		validateFields(vo);
		
		convenioDAO.update(vo);
	}
	
	/**
	 * Exclui uma convênio.
	 * @param vo Convênio que se deseja excluir.
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public void remove(ConvenioVO vo) throws SmartEnvException, SmartAppException {
		
		convenioDAO.remove(vo);
	}
	
	/**
	 * Insere um convênio.
	 * @param vo Convênio que se deseja inserir.
	 * @return Convênio que foi inserido.
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public ConvenioVO insert(ConvenioVO vo) throws SmartEnvException, SmartAppException {
		
		validateFields(vo);	
		
		vo	= convenioDAO.insert(vo);
		
		// se convenio for de fatura variável, criar um plano padrão:
		if (vo.getCategoria().equals("V")) {
			
			planoConvenioRN.insert(montaPlanoPadrao(vo));
			
		}
		
		return vo;

	}
	
	private PlanoConvenioVO montaPlanoPadrao(ConvenioVO vo) {
		PlanoConvenioVO plano	= new PlanoConvenioVO();
		
		plano.setConvenio(vo);
		plano.setDataInicio(new Date());
		plano.setNome(vo.getNomeFantasia());
		plano.setFlagDedutivel(false);
		plano.setDescricao(vo.getNomeFantasia());
		plano.setValor(new Double(0));
		plano.setFlagDesativado(false);
		
		return plano;
	}



	/**
	 * Obtém os convênios atraves de critérios de filtro.
	 * @param vo Convênio com critérios de filtro preenchidos.
	 * @return Coleção de convênios, encapsulados na classe ConvenioVO.
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public Collection<ConvenioVO> findByFilter(ConvenioVO vo) throws SmartEnvException, SmartAppException {
		return convenioDAO.findByFilter(vo);
	}	
	
	/**
	 * Verifica se os campos obrigatórios de "Convênio" estão preenchidos.
	 * @param vo Convenio cujos dados se deseja verificar.
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	private void validateFields(ConvenioVO vo) throws SmartEnvException, SmartAppException {				
		
		if (vo.getNomeFantasia() == null || vo.getNomeFantasia().equals("")) {
			throw new SmartAppException("O nome fantasia deve ser preenchido.");
		}
		
		if (vo.getCategoria() == null || vo.getCategoria().equals("")) {
			throw new SmartAppException("A categoria deve ser preenchido.");
		}
		
		if (vo.getAtividadeConvenio() == null || vo.getAtividadeConvenio().getCodigo()== null) {
			throw new SmartAppException("A atividade deve ser preenchido.");
		}
		
		if (vo.getTelefone() == null || vo.getTelefone().equals("")) {
			throw new SmartAppException("O telefone deve ser preenchido.");
		}	
		
		if (vo.getBeneficio() == null || vo.getBeneficio().equals("")) {
			throw new SmartAppException("O benefício deve ser preenchido.");
		}
		
		//Caso o convênio tenha fatura fixa ou variavel aplica as validações abaixo.
		if (vo.getCategoria() != null && !vo.getCategoria().equals("") && 
				(vo.getCategoria().equals(SisjufConstantesIf.CATEGORIA_FATURA_FIXA) ||
						vo.getCategoria().equals(SisjufConstantesIf.CATEGORIA_FATURA_VARIAVEL))) {
			
			if (vo.getDiaFechamento() == null) {
				throw new SmartAppException("O dia do fechamento deve ser preenchido.");
			}
			
			if (vo.getMesVencimento() == null) {
				throw new SmartAppException("O mês do vencimento deve ser preenchido.");
			}
			
			if (vo.getDiaVencimento() == null) {
				throw new SmartAppException("O dia do vencimento deve ser preenchido.");
			}		
			
			if (vo.getMesVencimento() == null && vo.getMesVencimento() == 0){
				if (vo.getDiaVencimento() == null && vo.getDiaFechamento() == null &&
						vo.getDiaVencimento() < vo.getDiaFechamento()) {
					throw new SmartAppException("O dia do vencimento deve maior o dia do fechamento.");
				}						
			}
			
		}			
	}
	
	
	public Collection<ReportVitalmedVO> findReportVitalmed(ReportVitalmedVO report) throws SmartEnvException, SmartAppException {
		
		Collection<ReportVitalmedVO> dadosAssociadosVitalmed = convenioDAO.findReportVitalmed(report);
		
		convenioDAO.removeReportVitalmed();
		
		if (dadosAssociadosVitalmed != null && !dadosAssociadosVitalmed.isEmpty()) {
			for (ReportVitalmedVO dado : dadosAssociadosVitalmed) {
				convenioDAO.insert(dado);
			}
		}		
		
		return dadosAssociadosVitalmed;
	}

}
