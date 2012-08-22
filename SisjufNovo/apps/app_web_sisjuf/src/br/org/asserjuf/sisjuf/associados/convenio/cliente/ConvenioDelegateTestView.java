package br.org.asserjuf.sisjuf.associados.convenio.cliente;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import br.com.falc.smartFW.exception.SmartAppException;
import br.com.falc.smartFW.exception.SmartEnvException;
import br.org.asserjuf.sisjuf.associados.convenio.AtividadeConvenioVO;
import br.org.asserjuf.sisjuf.associados.convenio.ConvenioVO;
import br.org.asserjuf.sisjuf.associados.convenio.FaturaVO;
import br.org.asserjuf.sisjuf.associados.convenio.HistoricoValorPlanoConvenioVO;
import br.org.asserjuf.sisjuf.associados.convenio.PlanoConvenioVO;
import br.org.asserjuf.sisjuf.associados.convenio.facade.ConvenioFacade;
import br.org.asserjuf.sisjuf.entidadesComuns.EstadoVO;


public class ConvenioDelegateTestView extends ConvenioDelegate{

	/**
	 * "Proxy" para o método findAllConvenio() no façade Convenio
	 * @return Collection de ConvenioVO
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */	
	public ConvenioDelegateTestView(){
		super.LOG = Logger.getLogger(ConvenioDelegateTestView.class);
	}
	@Override
	public Collection<ConvenioVO> findAllConvenio() throws SmartEnvException, SmartAppException {
		LOG.debug("findAllConvenio");

		Collection<ConvenioVO> convenios = new ArrayList<ConvenioVO>();
		ConvenioVO convenioVO = new ConvenioVO();
		convenioVO.setCodigo(1);
		convenioVO.setCategoria("g");
		convenioVO.setNomeFantasia("Academia da Praia");
		AtividadeConvenioVO atividadeConvenio = new AtividadeConvenioVO();
		atividadeConvenio.setNome("Fitiness");
		convenioVO.setAtividadeConvenio(atividadeConvenio);
		EstadoVO estado = new EstadoVO();
		estado.setNome("Bahia");
		convenioVO.setEstado(estado);
		convenioVO.setCidade("Salvador");
		convenioVO.setTelefone("(71) 8853-3194");
		convenios.add(convenioVO);

		
		convenioVO = new ConvenioVO();
		convenioVO.setCodigo(2);
		convenioVO.setCategoria("n");
		convenioVO.setNomeFantasia("Academia da Praia 2");
		atividadeConvenio = new AtividadeConvenioVO();
		atividadeConvenio.setNome("Fitiness");
		convenioVO.setAtividadeConvenio(atividadeConvenio);
		estado = new EstadoVO();
		estado.setNome("Paraiba");
		convenioVO.setEstado(estado);
		convenioVO.setCidade("Joao Pessoa");
		convenioVO.setTelefone("(71) 3131-2222");
		convenios.add(convenioVO);

		return convenios;
	}

	/**
	 * "Proxy" para o método findConvenioByPrymaryKey() no façade Convenio.
	 * @param vo 
	 * @return ConvenioVO
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */	
	@Override
	public ConvenioVO findConvenioByPrimaryKey(ConvenioVO vo) throws SmartEnvException, SmartAppException {
		LOG.debug("findConvenioByPrymaryKey");
		showLog(vo);
		
		ConvenioVO convenioVO = new ConvenioVO();
		convenioVO.setCodigo(1);
		convenioVO.setCategoria("n");
		convenioVO.setCnpj("26.989.715/0042-80");
		convenioVO.setRazaoSocial("razao social");
		convenioVO.setNomeFantasia("Academia da Praia");
		AtividadeConvenioVO atividadeConvenio = new AtividadeConvenioVO();
		atividadeConvenio.setNome("Fitiness");
		atividadeConvenio.setCodigo(1);
		convenioVO.setAtividadeConvenio(atividadeConvenio);
		EstadoVO estado = new EstadoVO();
		estado.setNome("Bahia");
		estado.setCodigo(new Short("1"));
		convenioVO.setEstado(estado);
		convenioVO.setCidade("Salvador");
		convenioVO.setTelefone("(71) 8853-3194");
		convenioVO.setLogradouro("Logradouro");
		convenioVO.setComplemento("Complemento");
		convenioVO.setBairro("Bairro");
		convenioVO.setCep("41100-710");
		convenioVO.setNomeContato("nome Contato");
		convenioVO.setEmail("glaubercunha@gmail.com");
		convenioVO.setTelefoneAdicional("(71) 8853-3195");
		convenioVO.setFax("(71) 8853-3196");
		convenioVO.setBeneficio("Benef’cios");
		convenioVO.setDescricao("descricao");
		convenioVO.setDiaFechamento(new Short("10"));
		//convenioVO.setConjugue(true);
		return convenioVO;
	}

	/**
	 * "Proxy" para o método updateConvenio() no façade Convenio.
	 * @param vo
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */	
	@Override
	public void updateConvenio(ConvenioVO vo) throws SmartEnvException, SmartAppException {
		LOG.debug("updateConvenio");
		showLog(vo);
	}

	/**
	 * "Proxy" para o método removeConvenio() no façade Convenio.
	 * @param vo
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */	
	@Override
	public void removeConvenio(ConvenioVO vo) throws SmartEnvException, SmartAppException {
		LOG.debug("removeConvenio");
		showLog(vo);
	}

	/**
	 * "Proxy" para o método insertConvenio() no façade Convenio.
	 * @param vo
	 * @return ConvenioVO 
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */	
	@Override
	public ConvenioVO insertConvenio(ConvenioVO vo) throws SmartEnvException, SmartAppException {
		LOG.debug("insertConvenio");
		showLog(vo);
		vo.setCodigo(1);
		return vo; 
	}

	/**
	 * "Proxy" para o método findConvenioByFilter() no façade Convenio.
	 * @param vo Convênio com critérios de filtro preenchidos. 
	 * @return Collection de ConvenioVO
	 * @throws SmartAppException 
	 * @throws SmartEnvException 
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */	
	@Override
	public Collection<ConvenioVO> findConvenioByFilter(ConvenioVO vo) throws SmartEnvException, SmartAppException {
		LOG.debug("findConvenioByFilter");
		showLog(vo);
		
		Collection<ConvenioVO> convenios = new ArrayList<ConvenioVO>();
		ConvenioVO convenioVO = new ConvenioVO();
		convenioVO.setCodigo(1);
		convenioVO.setCategoria("g");
		convenioVO.setNomeFantasia("Academia da Praia");
		AtividadeConvenioVO atividadeConvenio = new AtividadeConvenioVO();
		atividadeConvenio.setNome("Fitiness");
		convenioVO.setAtividadeConvenio(atividadeConvenio);
		EstadoVO estado = new EstadoVO();
		estado.setNome("Bahia");
		convenioVO.setEstado(estado);
		convenioVO.setCidade("Salvador");
		convenioVO.setTelefone("(71) 8853-3194");
		convenios.add(convenioVO);

		
		convenioVO = new ConvenioVO();
		convenioVO.setCodigo(2);
		convenioVO.setCategoria("n");
		convenioVO.setNomeFantasia("Academia da Praia 2");
		atividadeConvenio = new AtividadeConvenioVO();
		atividadeConvenio.setNome("Fitiness");
		convenioVO.setAtividadeConvenio(atividadeConvenio);
		estado = new EstadoVO();
		estado.setNome("Paraiba");
		convenioVO.setEstado(estado);
		convenioVO.setCidade("Joao Pessoa");
		convenioVO.setTelefone("(71) 3131-2222");
		convenios.add(convenioVO);

		return convenios;
	}

	/**
	 * "Proxy" para o método findAllAtividades() no façade Convenio
	 * @return Collection de AtividadeConvenioVO
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */	
	@Override
	public Collection<AtividadeConvenioVO> findAllAtividades() throws SmartEnvException, SmartAppException {
		List<AtividadeConvenioVO> atividades = new ArrayList<AtividadeConvenioVO>();
		AtividadeConvenioVO atividade = new AtividadeConvenioVO();
		atividade.setCodigo(1);
		atividade.setNome("Atividade1");
		atividades.add(atividade);
		atividade = new AtividadeConvenioVO();
		atividade.setCodigo(2);
		atividade.setNome("Atividade2");
		atividades.add(atividade);
		return atividades;
	}

	/**
	 * "Proxy" para o método findAtividadeByPrymaryKey() no façade Convenio.
	 * @param vo 
	 * @return AtividadeConvenioVO
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */	
	@Override
	public AtividadeConvenioVO findAtividadeByPrimaryKey(AtividadeConvenioVO vo) throws SmartEnvException, SmartAppException {
		LOG.debug("findAtividadeByPrimaryKey");
		showLog(vo);
		AtividadeConvenioVO atividade = new AtividadeConvenioVO();
		atividade.setCodigo(1);
		atividade.setNome("AtividadeDetalhe");
		return atividade;
	}

	/**
	 * "Proxy" para o método updateAtividade() no façade Convenio.
	 * @param vo
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */	
	@Override
	public void updateAtividade(AtividadeConvenioVO vo) throws SmartEnvException, SmartAppException {
		LOG.debug("updateAtividade");
		showLog(vo);
	}

	/**
	 * "Proxy" para o método removeAtividade() no façade Convenio.
	 * @param vo
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */	
	@Override
	public void removeAtividade(AtividadeConvenioVO vo) throws SmartEnvException, SmartAppException {
		LOG.debug("removeAtividade");
		showLog(vo);
	}

	/**
	 * "Proxy" para o método insertAtividade() no façade Convenio.
	 * @param vo
	 * @return AtividadeConvenioVO 
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */	
	@Override
	public AtividadeConvenioVO insertAtividade(AtividadeConvenioVO vo) throws SmartEnvException, SmartAppException {
		LOG.debug("insertAtividade");
		vo.setCodigo(1);
		showLog(vo);
		return vo;
	}

	/**
	 * "Proxy" para o método findAtividadeConvenioByFilter() no façade Convenio.
	 * @return Collection de AtividadeConvenioVO
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */		
	@Override
	public Collection<AtividadeConvenioVO> findAtividadeConvenioByFilter(AtividadeConvenioVO vo) {
		Collection<AtividadeConvenioVO> atividades = new ArrayList<AtividadeConvenioVO>();
		AtividadeConvenioVO atividade = new AtividadeConvenioVO();
		atividade.setCodigo(1);
		atividade.setNome("Atividade1");
		atividades.add(atividade);

		atividade = new AtividadeConvenioVO();
		atividade.setCodigo(2);
		atividade.setNome("Atividade2");
		atividades.add(atividade);
		return atividades;
	}

	@Override
	public Collection<EstadoVO> findAllEstados() {
		LOG.debug("findAllEstados");
		 List lista = new ArrayList<EstadoVO>();
			EstadoVO estado = new EstadoVO();
			estado.setNome("Bahia");
			estado.setCodigo(new Short("1"));

			 lista.add(estado);
			estado = new EstadoVO();
			estado.setNome("Bahia2");
			estado.setCodigo(new Short("2"));

			 lista.add(estado);
		 return lista;
	}

	public static void showLog(ConvenioVO vo){
		
//		LOG.debug("ConvenioVO");
//		LOG.debug("getCodigo " + vo.getCodigo());
//		LOG.debug("getCategoria " + vo.getCategoria());
//		LOG.debug("getNomeFantasia " + vo.getNomeFantasia());
//		LOG.debug("getAtividadeConvenio" + vo.getAtividadeConvenio().getCodigo());
	}
	
	
	public static void showLog(AtividadeConvenioVO vo){
//		LOG.debug("AtividadeConvenioVO");
//		LOG.debug("codigo " + vo.getCodigo());
//		LOG.debug("nome " + vo.getNome());
//		LOG.debug("ativo " + vo.getAtivo());
	}
	
	public static void showLog(PlanoConvenioVO vo){
//		LOG.debug("PlanoConvenioVO");
//		LOG.debug("codigo " + vo.getCodigo());
//		LOG.debug("nome " + vo.getNome());
//		LOG.debug("ativo " + vo.getAtivo());
	}
	
	@Override
	public Collection<PlanoConvenioVO> findAllPlanoConvenio(){
		List<PlanoConvenioVO> planos = new ArrayList<PlanoConvenioVO>();
		PlanoConvenioVO plano = new PlanoConvenioVO();
		plano.setCodigo(1);
		plano.setNome("Plano1");
		plano.setDescricao("Descricao Plano1");
		plano.setValor(new Double("2500.46"));
		planos.add(plano);
		plano = new PlanoConvenioVO();
		plano.setCodigo(2);
		plano.setNome("Plano2");
		plano.setDescricao("Descricao Plano2");
		plano.setValor(new Double("50000.00"));
		planos.add(plano);
		return planos;
	}
	
	@Override
	public Collection<PlanoConvenioVO> findPlanoConvenioByFilter(
			PlanoConvenioVO vo) throws SmartEnvException, SmartAppException {
		
		
		showLog(vo);
		List<PlanoConvenioVO> planos = new ArrayList<PlanoConvenioVO>();
		PlanoConvenioVO plano = new PlanoConvenioVO();
		plano.setCodigo(1);
		plano.setNome("Plano1");
		plano.setDescricao("Descricao Plano1");
		plano.setValor(new Double("2500.46"));
		//plano.setAtivo(true);
		planos.add(plano);
		plano = new PlanoConvenioVO();
		plano.setCodigo(2);
		plano.setNome("Plano2");
		plano.setDescricao("Descricao Plano2");
		plano.setValor(new Double("50000.00"));
		//plano.setAtivo(false);
		planos.add(plano);
		return planos;
	}
	
	@Override
	public PlanoConvenioVO findPlanoConvenioByPrimaryKey(PlanoConvenioVO vo)
			throws SmartEnvException, SmartAppException {
		LOG.debug("findPlanoConvenioByPrimaryKey");
		showLog(vo);
		PlanoConvenioVO plano = getPlano1();
		
		plano.setHistoricoValorPlanoConvenio(new ArrayList<HistoricoValorPlanoConvenioVO>());
		
		HistoricoValorPlanoConvenioVO historico = new HistoricoValorPlanoConvenioVO();
		historico.setCodigo(1);
		historico.setDataCadastro(new Date());
		historico.setDataInicio(new Date());
		historico.setPlanoConvenio(plano);
		historico.setValor(Double.valueOf("2000.00"));
		plano.getHistoricoValorPlanoConvenio().add(historico);

		historico = new HistoricoValorPlanoConvenioVO();
		historico.setCodigo(2);
		historico.setDataCadastro(new Date());
		historico.setDataInicio(new Date());
		historico.setPlanoConvenio(plano);
		historico.setValor(Double.valueOf("500.56"));
		plano.getHistoricoValorPlanoConvenio().add(historico);
		
		return plano;
	}
	
	public static  PlanoConvenioVO getPlano1() {
		PlanoConvenioVO plano = new PlanoConvenioVO();
		plano.setCodigo(1);
		plano.setNome("Plano1");
		plano.setDescricao("Descricao Plano2");
		plano.setValor(new Double("50000.00"));
		//plano.setAtivo(false);
		return plano;
	}
	
	@Override
	public PlanoConvenioVO insertPlanoConvenio(PlanoConvenioVO vo)
			throws SmartEnvException, SmartAppException {
		// TODO Auto-generated method stub
		LOG.debug("insertPlanoConvenio");
		showLog(vo);
		return findPlanoConvenioByPrimaryKey(vo);
	}
	
	@Override
	public void removePlanoConvenio(PlanoConvenioVO vo)
			throws SmartEnvException, SmartAppException {
		LOG.debug("removePlanoConvenio");
		showLog(vo);
	}
	
	@Override
	public void updatePlanoConvenio(PlanoConvenioVO vo)
			throws SmartEnvException, SmartAppException {
		// TODO Auto-generated method stub
		LOG.debug("updatePlanoConvenio");
		showLog(vo);
	}
	
	@Override
	public Collection<FaturaVO> findUltimasFaturas(FaturaVO fatura) {
		return new ArrayList<FaturaVO>();
	}
	
		
}