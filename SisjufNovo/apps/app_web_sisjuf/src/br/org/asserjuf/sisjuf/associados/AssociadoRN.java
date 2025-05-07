package br.org.asserjuf.sisjuf.associados;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import br.com.falc.smartFW.exception.SmartAppException;
import br.com.falc.smartFW.exception.SmartEnvException;
import br.org.asserjuf.sisjuf.associados.dados.AssociadoDAO;
import br.org.asserjuf.sisjuf.financeiro.FormaPagamentoVO;
import br.org.asserjuf.sisjuf.financeiro.LancamentoAssociadoVO;
import br.org.asserjuf.sisjuf.financeiro.LancamentoRN;
import br.org.asserjuf.sisjuf.financeiro.LancamentoVO;
import br.org.asserjuf.sisjuf.util.ParametroRN;
import br.org.asserjuf.sisjuf.util.ParametroVO;

public class AssociadoRN {

	private AssociadoDAO 			associadoDAO;
	private HistoricoAssociadoRN 	historicoAssociadoRN;
	private ParametroRN 			parametroRN;
	private PessoaRN				pessoaRN;
	private FilhoRN					filhoRN;
	private DependenteRN			dependenteRN;
	private LancamentoRN			lancamentoRN;
	private ConjugeRN				conjugeRN;
	
	
	public Collection<AssociadoImportacaoNucreVO> importPlanilhaNucre(Collection<ItemPlanilhaNucreVO> planilha) throws SmartEnvException, SmartAppException {
		
		Integer operacaoCredito 				= new Integer(parametroRN.findByPrimaryKey(new ParametroVO("TP_OPERACAO_CREDITO")).getValorTextual());
		Integer formaPagamentoDebitoAutomatico 	= new Integer(parametroRN.findByPrimaryKey(new ParametroVO("FORMA_PAGTO_DEBITO_AUT")).getValorTextual());
		
		// Obter cole��o de "faltantes" para exibir relat�rio para o usu�rio:
		
		Collection<AssociadoImportacaoNucreVO> faltantes	= associadoDAO.findFaltantes(planilha);
		
		
		// Obter lan�amentos a serem realizados, e realiz�-los
		// Os lan�amentos a serem realizados ser�o a intercess�o dos associados do arquivo com a base do SISJUF:
		
		Collection<LancamentoAssociadoVO> lancamentosAssociados	= associadoDAO.findIntercessaoLancamentosBase(planilha);
		
		//obtendo a data para setar na efetiva��o:
		
		Date dataImportacao = new Date(); 
		
		for (ItemPlanilhaNucreVO vo : planilha) {
			dataImportacao = vo.getData();
			break;
		}
		
		for (LancamentoAssociadoVO vo : lancamentosAssociados) {
			
			if (vo.getValor().doubleValue() != 0) {
			
				vo.setDataEfetivacao(dataImportacao);
				
				FormaPagamentoVO formaPagamento	= new FormaPagamentoVO();
				formaPagamento.setCodigo(formaPagamentoDebitoAutomatico);
				
				vo.setFormaPagamentoVO(formaPagamento);
				
				if (vo.getTipoOperacaoVO().getCodigo().intValue() == operacaoCredito.intValue()) {
					vo.setDescricao("Mensalidade do usuário de matrícula na justiça " + vo.getAssociado().getMatriculaJustica());
				} else {
					vo.setDescricao("Custo de consignação do usuário de matrícula na justiça " + vo.getAssociado().getMatriculaJustica());
				}
				LancamentoVO lancamento = lancamentoRN.efetuarLancamento(vo);
				
				vo.setCodigo(lancamento.getCodigo());
				
				associadoDAO.insertLancamento(vo);
			}
		}
		return faltantes;
	}
	
	public Collection<AssociadoVO> findAll() throws SmartEnvException, SmartAppException {
		
		return associadoDAO.findAll();
	}
	
	public Float findValorAtualSocioUsuario() throws SmartEnvException, SmartAppException {
		
		return associadoDAO.findValorAtualSocioUsuario();
	}
	
	
	private void validateFields(AssociadoAssembler associado) throws SmartEnvException, SmartAppException {
		// nome
		if (associado.getNome() == null || associado.getNome().equals("")) {
			throw new SmartAppException("O nome do associado deve ser informado.");
		}
		
		// data associa��o
		if (associado.getDataAssociacao() == null) {
			throw new SmartAppException("A data de associação deve ser informada.");
		}
		
		// endere�o do associado
		if (associado.getEndereco() != null) {
			if (associado.getEndereco().getLogradouro() == null || associado.getEndereco().getLogradouro().equals("")) {
				throw new SmartAppException("O logradouro deve ser informado.");
			}
			if (associado.getEndereco().getNumero() == null || associado.getEndereco().getNumero().equals("")) {
				throw new SmartAppException("O número deve ser informado.");
			}
			if (associado.getEndereco().getBairro() == null || associado.getEndereco().getBairro().equals("")) {
				throw new SmartAppException("O bairro deve ser informado.");
			}
			if (associado.getEndereco().getCep() == null) {
				throw new SmartAppException("O CEP deve ser informado.");
			}
			if (associado.getEndereco().getMunicipio() != null) {
				if (associado.getEndereco().getMunicipio().getNome() == null || associado.getEndereco().getMunicipio().getNome().equals("")) {
					throw new SmartAppException("O município deve ser informado.");	
				}
				if (associado.getEndereco().getMunicipio().getEstado() == null || associado.getEndereco().getMunicipio().getEstado().getCodigo() == null) {
					throw new SmartAppException("O estado deve ser informado.");
				}
				
			} else {
				throw new SmartAppException("O município deve ser informado.");
			}
			
		} else {
			throw new SmartAppException("Os dados do endereço devem ser preenchidos.");		
		}
		
		// celular
		/*
		if (associado.getTelefoneCelular() == null) {
			throw new SmartAppException("O telefone celular deve ser informado.");
		}
		
		// telefone residencial
		if (associado.getTelefoneResidencial() == null) {
			throw new SmartAppException("O telefone residencial deve ser informado.");
		}
		*/
		
		// telefone comercial
		if (associado.getTelefoneComercial() == null) {
			throw new SmartAppException("O telefone comercial deve ser informado.");
		}
		
		// e-mail
		/*
		if (associado.getEmail() == null || associado.getEmail().equals("")) {
			throw new SmartAppException("O e-mail deve ser informado.");
		}
		*/
		
		// rg
		if (associado.getRg() == null) {
			throw new SmartAppException("O RG deve ser informado.");
		}
		
		//cpf
		if (associado.getCpf() == null) {
			throw new SmartAppException("O CPF deve ser informado.");
		}
		
		// data de nascimento
		if (associado.getDataNascimento() == null) {
			throw new SmartAppException("A data de nascimento deve ser informada.");
		}
		
		// naturalidade
		if (associado.getNaturalidade() != null) {
			
			if (associado.getNaturalidade().getNome() == null || associado.getNaturalidade().getNome().equals("")) {
				throw new SmartAppException("O municipio da naturalidade deve ser informado.");
			}
			
			if (associado.getNaturalidade().getEstado() == null || associado.getNaturalidade().getEstado().getCodigo() == null) {
				throw new SmartAppException("O estado da naturalidade deve ser informado.");
			}
			
		} else {
			throw new SmartAppException("O município da naturalidade deve ser informado.");

		}
		
		// sexo
		if (associado.getSexo() == null || associado.getSexo().equals("")) {
			throw new SmartAppException("O sexo deve ser informado.");

		}
		
		// estado civil
		if (associado.getEstadoCivil() == null || associado.getEstadoCivil().equals("")) {
			throw new SmartAppException("O estado civil deve ser informado.");

		}
		
		// CPF
		if (associado.getCpf() == null ) {
			throw new SmartAppException("O CPF do associado deve ser informado.");
		}
		
		/*
		// nome do pai
		if (associado.getNomePai() == null || associado.getNomePai().equals("")) {
			throw new SmartAppException("O nome do pai deve ser informado.");
		}
		
		// nome da m�e
		if (associado.getNomeMae() == null || associado.getNomeMae().equals("")) {
			throw new SmartAppException("O nome da mãe deve ser informado.");
		}
		*/
		
		// TODO criar par�metro!
		// nome do c�njuge
		/*
		if (associado.getEstadoCivil().equals("C") && (associado.getConjuge()== null || associado.getConjuge().getNome() == null || associado.getConjuge().getNome().equals("")) ) {
			throw new SmartAppException("O nome do cónjuge deve ser informado.");
		}
		*/
		
		// status na justi�a
		if (associado.getStatusJustica() == null || associado.getStatusJustica().equals("")) {
			throw new SmartAppException("O status na justiça deve ser informado.");
		}
		

		// verifica��es apenas para o cadastro completo
		if (associado.getPreCadastro() == null || !associado.getPreCadastro()) {
			
			if (associado.getStatusJustica().equals("A")) {
				// associado ativo na justica. alguns dados de setor sao obrigatorios.
		
				// Setor		
				if (associado.getSetor() == null) {
					throw new SmartAppException("O setor deve ser informado.");
					
				// �rg�o
				} else 	if (associado.getSetor().getOrgao() == null || associado.getSetor().getOrgao().getNome() == null || associado.getSetor().getOrgao().getNome().equals("")) {
						throw new SmartAppException("O orgão deve ser informado.");
					
				}
				
				// Nome do setor
				if (associado.getSetor().getNome() == null || associado.getSetor().getNome().equals("")) {
					throw new SmartAppException("O nome do setor deve ser informado.");
				}
				
				
				
				// Telefone do setor
				if (associado.getSetor().getTelefone() == null) {
					throw new SmartAppException("O telefone do setor deve ser informado.");
				}
				
				// Ramal do setor
				if (associado.getSetor().getRamal() == null) {
					throw new SmartAppException("O ramal do setor deve ser informado.");
				}
				
				
			}
			
			// Categoria do associado
			if (associado.getStatusCategoria() == null || associado.getStatusCategoria().equals("")) {
				throw new SmartAppException ("A categoria do sócio deve ser informada.");
				
			}	
			
			// S�cio contribuinte do s�cio usu�rio
			else if  (associado.getStatusCategoria().equals("U") && (associado.getContribuinte() == null || associado.getContribuinte().getCodigo() == null)) {
				throw new SmartAppException ("O contribuinte deve ser informado para sócio usuário.");
			}
			
		}

		
		// Matr�cula na juti�a
		if (associado.getMatriculaJustica()==null) {
			throw new SmartAppException("A matrícula na justi�a deve ser informada.");
		}

	}
	
	public AssociadoVO insert(AssociadoAssembler associado) throws SmartEnvException, SmartAppException {
		
		this.validateFields(associado);
		
		// inserir pessoa
		associado	= (AssociadoAssembler) pessoaRN.insert(associado);
		
		AssociadoFiltroAssembler filtro = new AssociadoFiltroAssembler();
		filtro.setMatriculaJustica(associado.getMatriculaJustica());
		Collection<AssociadoVO> associados = associadoDAO.findByFilter(filtro);
		if (associados != null && associados.size() > 0){
			throw new SmartAppException("Já existe um associado com o mesmo número de matricula cadastrado no sistema.");
		}
		
		// inserir associado
		associado 	= (AssociadoAssembler) associadoDAO.insert(associado);
		
		// inserir hist�rico
		HistoricoAssociadoVO historicoAssociadoVO = new HistoricoAssociadoVO();
		historicoAssociadoVO.setTipoEvento(new TipoEventoVO());
		historicoAssociadoVO.getTipoEvento().setCodigo(new Short(parametroRN.findByPrimaryKey(new ParametroVO("TP_EVT_CADASTRO")).getValorTextual()));
		historicoAssociadoVO.setData(associado.getDataAssociacao());
		historicoAssociadoVO.setAssociado(associado);
		
		historicoAssociadoRN.insert(historicoAssociadoVO);
		
		// inserir filhos
		insertFilhos(associado);
		
		// inserir dependentes
		insertDependentes(associado);
		
		// inserir conjuge
		//insertConjuge(associado);
		
		// retornar
		return associado;
	}
	
	private void insertFilhos(AssociadoAssembler associado) throws SmartEnvException, SmartAppException {
		
		if (associado.getFilhos() != null && associado.getFilhos().size() >0 ) {
			
			for (FilhoVO filho : associado.getFilhos()) {
				
				filho.setAssociado(associado);
				filhoRN.insert(filho);
			}
		}
	}
	
	private void insertDependentes(AssociadoAssembler associado) throws SmartEnvException, SmartAppException {
		
		if (associado.getDependentes() != null && associado.getDependentes().size() >0 ) {
			
			for (DependenteVO dependente : associado.getDependentes()) {
				
				dependente.setAssociado(associado);
				dependenteRN.insert(dependente);
			}
		}
	}
	
	private void insertConjuge(AssociadoAssembler associado) throws SmartEnvException, SmartAppException {
		
		if (associado.getConjuge() != null && associado.getNome() != null && !"".equals(associado.getConjuge().getNome())) {
			
			// testar se data de nascimento e CPF do conjuge est�o sendo preenchidos:
			// por enquanto esta regra deve ficar comentada, para n�o travar a manuten��o em produ��o.
						/*
						if (associado.getConjuge().getCpf() == null || associado.getConjuge().getCpf().equals("")) {
							throw new SmartAppException("Voc� deve informar o CPF do c�njuge.");
						}
						
						if (associado.getConjuge().getDataNascimento() == null) {
							throw new SmartAppException("Voc� deve informar a data de nascimento do c�njuge.");
						}
						*/
			
			associado.getConjuge().setAssociado(associado);
			
			associado.setConjuge(conjugeRN.insert(associado.getConjuge()));
			
		}
	}
	
	private void updateFilhos(AssociadoVO associado) throws SmartEnvException, SmartAppException {
		
		filhoRN.removeByAssociado(associado);
		
		if (associado.getFilhos() != null) {
			
			for (FilhoVO filho : associado.getFilhos()) {
				
				
				
				FilhoVO lFilho = filhoRN.findByPrimaryKey(filho);
				
				if (lFilho!= null && lFilho.getCodigo()!= null){
				
					filhoRN.update(filho);
				}else{
					filho.setAssociado(associado);
				
					filhoRN.insert(filho);
				}
			}
		}
	}
	
	private void updateDependentes(AssociadoVO associado) throws SmartEnvException, SmartAppException {
		dependenteRN.removeByAssociado(associado);

		if (associado.getDependentes() != null && associado.getDependentes().size() > 0) {
	
			for (DependenteVO dependente : associado.getDependentes()) {
				
				
				DependenteVO lDependenteVO = dependenteRN.findByPrimaryKey(dependente);
				
				if (lDependenteVO!= null && lDependenteVO.getCodigo()!= null){
					dependenteRN.update(dependente);
				}else{
					dependente.setAssociado(associado);
					dependenteRN.insert(dependente);
				}				
			}
		}
	}
	
	private void updateConjuge(AssociadoVO associado) throws SmartEnvException, SmartAppException {
		
		if (associado.getConjuge() != null && associado.getConjuge().getCodigo()!=null) {
	
			pessoaRN.update(associado.getConjuge());
			
			conjugeRN.update(associado.getConjuge());
			
		}
	}
	
	public void update(AssociadoAssembler associado) throws SmartEnvException, SmartAppException {
		
		associado.setPreCadastro(false);
		
		this.validateFields(associado);
		
		pessoaRN.update(associado);
		
		associadoDAO.update(associado);		
		
		updateDataAssociacao(associado);
		
		updateFilhos(associado);
		
		updateDependentes(associado);
		
		//updateConjuge(associado);
	}
	
	private void updateDataAssociacao(AssociadoAssembler associado) throws SmartEnvException {
		HistoricoAssociadoVO historico	= historicoAssociadoRN.findUltimoNaoCancelado(associado);
		
		historico.setData(associado.getDataAssociacao());
		
		historicoAssociadoRN.updateDate(historico);
		
	}

	public void remove(AssociadoVO associado) throws SmartEnvException, SmartAppException {

		associadoDAO.remove(associado);
	}
	
	public void insertValorSocioUsuario(Float valor) throws SmartEnvException, SmartAppException {
		
		if (findValorAtualSocioUsuario().floatValue() != valor.floatValue()) {
			associadoDAO.insertValorSocioUsuario(valor);
		}
	}
	
	public AssociadoVO findByPrimaryKey(AssociadoVO associado) throws SmartEnvException, SmartAppException {
		
		associado = associadoDAO.findByPrimaryKey(associado); 
		
		// solucao temporaria. por alguma razao consulta esta colocando um espaco apos o grupo sanguineo
		if (associado.getGrupoSanguineo() != null) {
			associado.setGrupoSanguineo(associado.getGrupoSanguineo().trim());
		}
		
		associado.setFilhos(filhoRN.findByAssociado(associado));
		associado.setDependentes(dependenteRN.findByAssociado(associado));
		
		return associado;
	}
	
	private void validateFilter(AssociadoFiltroAssembler assembler) throws SmartEnvException, SmartAppException {
		
		if (assembler != null) {
			
			if (
					!(
							assembler.getHistorico() != null && 
							assembler.getHistorico().getDataFim()!=null && 
							assembler.getHistorico().getDataInicio() != null && 
							assembler.getHistorico().getTipoEvento() != null && 
							assembler.getHistorico().getTipoEvento().getCodigo() != null
					) 
					&&
					!(
							assembler.getHistorico() == null || 
							(
									assembler.getHistorico().getDataFim() == null && 
									assembler.getHistorico().getDataInicio() == null && 
									(
											assembler.getHistorico().getTipoEvento() == null || 
											assembler.getHistorico().getTipoEvento().getCodigo() == null
									)
							)
					)
				) 
			{
				throw new SmartAppException("O filtro de evento não pode ser parcialmente preenchido.");
			}
		}
	}
	
	public Collection<AssociadoVO> findByFilter(AssociadoFiltroAssembler assembler) throws SmartEnvException, SmartAppException {
		
		validateFilter(assembler);
		
		return associadoDAO.findByFilter(assembler);
	}
	
	public Collection<AssociadoVO> listarAniversariantesPorMes(Short mesAniversariante) throws SmartEnvException, SmartAppException {
		return associadoDAO.listarAniversariantesPorMes(mesAniversariante);
	}
	
	public Collection<AssociadoVO> findProximosAniversariantes() throws SmartEnvException, SmartAppException {
		
		return associadoDAO.findProximosAniversariantes();
	}
	
	public AssociadoVO findByName(AssociadoVO associado) throws SmartEnvException, SmartAppException {

		return associadoDAO.findByName(associado);
	}
	
	public AssociadoVO findByCPF(AssociadoVO associado) throws SmartEnvException, SmartAppException {

		return associadoDAO.findByCPF(associado);
	}

	public void setAssociadoDAO(AssociadoDAO associadoDAO) {
		this.associadoDAO = associadoDAO;
	}

	public void setDependenteRN(DependenteRN dependenteRN) {
		this.dependenteRN = dependenteRN;
	}

	public void setFilhoRN(FilhoRN filhoRN) {
		this.filhoRN = filhoRN;
	}

	public void setHistoricoAssociadoRN(HistoricoAssociadoRN historicoAssociadoRN) {
		this.historicoAssociadoRN = historicoAssociadoRN;
	}

	public void setLancamentoRN(LancamentoRN lancamentoRN) {
		this.lancamentoRN = lancamentoRN;
	}

	public void setParametroRN(ParametroRN parametroRN) {
		this.parametroRN = parametroRN;
	}

	public void setPessoaRN(PessoaRN pessoaRN) {
		this.pessoaRN = pessoaRN;
	}
	
	public void setConjugeRN(ConjugeRN conjugeRN) {
		this.conjugeRN = conjugeRN;
	}



	public Collection<RelatorioAssociadosDependentesVO> findRelatorioAssociadosDependentes() throws SmartEnvException {
		
		return associadoDAO.findRelatorioAssociadosDependentes();
	}

	public List<InconsistenciaNucreVO> gerarRelatorioInconsistenciasNUCRE(List<ItemPlanilhaNucreVO> relatorioNucre) throws SmartEnvException, SmartAppException {
		
		
		//  gravar arquivo nucre no banco, retornar codigo gerado do arquivo nucre
		Long codigoNovaPlanilhaNucre = associadoDAO.getSequence("SEQ_PLANILHA_NUCRE");
		for (ItemPlanilhaNucreVO itemNucre : relatorioNucre) {
			itemNucre.setCodigoPlanilha(codigoNovaPlanilhaNucre);
			
			validaItemPlanilhaNucre(itemNucre);
			
			insert(itemNucre);
		}
		
		//  pesquisar inconsistencias em relacao a socios contribuintes passando codigo gerado do arquivo nucre.
		//      (no banco de dados, comparar registros de contribuintes com o arquivo nucre, 
		//       para gerar relatorio de diferencias.)
		
		return associadoDAO.findRelatorioInconsistenciasNUCRE(codigoNovaPlanilhaNucre);
		
		
		
	}

	private void insert(ItemPlanilhaNucreVO itemNucre) throws SmartEnvException {
		associadoDAO.insertItemPlanilhaNucre(itemNucre);
		
	}

	private void validaItemPlanilhaNucre(ItemPlanilhaNucreVO itemNucre) throws SmartAppException {
		// no momento essa validacao so olha os associados (cpfs),
		// pois a unica rubrica que estah sendo verificada na importacao 
		// eh a rubrica de mensalidades. Se outra rubrica for verificada, 
		// serah necessario revisar o metodo, jah que outros campos alem 
		// do cpf podem ser necessarios para outras rubricas.
		
		if (itemNucre == null ) {
			throw new SmartAppException("Item de importacao NUCRE est� vazio.");
		}
		
		if (itemNucre.getCodigoPlanilha() == null) {
			throw new SmartAppException("O c�digo interno de importa��o do arquivo NUCRE nao foi encontrado. Contate o administrador do sistema");
		}
		
		if (itemNucre.getAssociado() == null) {
			throw new SmartAppException("Associado n�o encontrado na planilha NUCRE.");
		}
		
		if (itemNucre.getAssociado().getCpf() == null) {
			throw new SmartAppException("CPF do associado n�o foi emncontrado.");
		}
		
	}
}