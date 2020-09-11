package br.org.asserjuf.sisjuf.financeiro;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import br.com.falc.smartFW.exception.SmartAppException;
import br.com.falc.smartFW.exception.SmartEnvException;
import br.org.asserjuf.sisjuf.financeiro.dados.ContaDAO;
import br.org.asserjuf.sisjuf.util.DataRN;

/**
 * Congrega todas as funcionalidades da regra de negócio da entidade conta.
 * @author Paulo Prado
 *
 */
public class ContaRN {
	
	/**
	 * Classe de acesso a banco de dados da entidade tipo de lançamento.
	 */
	private ContaDAO 	contaDAO;

	/**
	 * Classe que encapsula as funcionalidades da regra de negócio da entidade conta.
	 */
	private DataRN		dataRN;
	
	private DiretorFinanceiroRN diretorFinanceiroRN;
	
	/**
	 * Verifica validade do CPF
	 * @param strCpf CPF a ser validado
	 * @return TRUE se o CPF for válido ou FALSE caso contrário
	 */
	private boolean validateCpf(String strCpf )
	   {
	      int     d1, d2;
	      int     digito1, digito2, resto;
	      int     digitoCPF;
	      String  nDigResult;

	      d1 = d2 = 0;
	      digito1 = digito2 = resto = 0;

	      for (int nCount = 1; nCount < strCpf.length() -1; nCount++)
	      {
	         digitoCPF = Integer.valueOf (strCpf.substring(nCount -1, nCount)).intValue();

	         //multiplique a ultima casa por 2 a seguinte por 3 a seguinte por 4 e assim por diante.
	         d1 = d1 + ( 11 - nCount ) * digitoCPF;

	         //para o segundo digito repita o procedimento incluindo o primeiro digito calculado no passo anterior.
	         d2 = d2 + ( 12 - nCount ) * digitoCPF;
	      };

	      //Primeiro resto da divisão por 11.
	      resto = (d1 % 11);

	      //Se o resultado for 0 ou 1 o digito é 0 caso contrário o digito é 11 menos o resultado anterior.
	      if (resto < 2)
	         digito1 = 0;
	      else
	         digito1 = 11 - resto;

	      d2 += 2 * digito1;

	      //Segundo resto da divisão por 11.
	      resto = (d2 % 11);

	      //Se o resultado for 0 ou 1 o digito é 0 caso contrário o digito é 11 menos o resultado anterior.
	      if (resto < 2)
	         digito2 = 0;
	      else
	         digito2 = 11 - resto;

	      //Digito verificador do CPF que está sendo validado.
	      String nDigVerific = strCpf.substring (strCpf.length()-2, strCpf.length());

	      //Concatenando o primeiro resto com o segundo.
	      nDigResult = String.valueOf(digito1) + String.valueOf(digito2);

	      //comparar o digito verificador do cpf com o primeiro resto + o segundo resto.
	      return nDigVerific.equals(nDigResult);
	   }

	/**
	 * Verifica validade do CNPJ
	 * @param str_cnpj CNPJ a ser validado
	 * @return TRUE se o CNPJ for válido ou FALSE caso contrário
	 */
	private boolean validateCnpj( String str_cnpj )
	    {
	       int soma = 0, dig;
	       String cnpj_calc = str_cnpj.substring(0,12);

	       if ( str_cnpj.length() != 14 )
	         return false;

	       char[] chr_cnpj = str_cnpj.toCharArray();

	       /* Primeira parte */
	       for( int i = 0; i < 4; i++ )
	         if ( chr_cnpj[i]-48 >=0 && chr_cnpj[i]-48 <=9 )
	           soma += (chr_cnpj[i] - 48) * (6 - (i + 1)) ;
	       for( int i = 0; i < 8; i++ )
	         if ( chr_cnpj[i+4]-48 >=0 && chr_cnpj[i+4]-48 <=9 )
	           soma += (chr_cnpj[i+4] - 48) * (10 - (i + 1)) ;
	       dig = 11 - (soma % 11);

	       cnpj_calc += ( dig == 10 || dig == 11 ) ?
	                      "0" : Integer.toString(dig);

	       /* Segunda parte */
	       soma = 0;
	       for ( int i = 0; i < 5; i++ )
	         if ( chr_cnpj[i]-48 >=0 && chr_cnpj[i]-48 <=9 )
	           soma += (chr_cnpj[i] - 48) * (7 - (i + 1)) ;
	       for ( int i = 0; i < 8; i++ )
	         if ( chr_cnpj[i+5]-48 >=0 && chr_cnpj[i+5]-48 <=9 )
	           soma += (chr_cnpj[i+5] - 48) * (10 - (i + 1)) ;
	       dig = 11 - (soma % 11);
	       cnpj_calc += ( dig == 10 || dig == 11 ) ?
	                      "0" : Integer.toString(dig);

	       return str_cnpj.equals(cnpj_calc);
	    }

	/**
	 * Obtém todas as contas cadastradas na base de dados.
	 * @return Coleção das contas
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public Collection findAll(boolean incluiExcluidas) throws SmartEnvException, SmartAppException {
	
		return contaDAO.findAll(incluiExcluidas);
	}

	/**
	 * Busca por chave de uma conta.
	 * @param vo
	 * @return Objeto que representa a conta buscada
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public ContaBancoVO findByPrimaryKey(ContaVO vo) throws SmartEnvException, SmartAppException {
		ContaBancoVO retorno = contaDAO.findByPrimaryKey(vo);
		if (retorno.getBancoVO()== null || retorno.getBancoVO().getCodigo()== null) {
			retorno.setFlgContaCaixa("S");
		}
		
		return retorno; 
	}
	
	/**
	 * Atualiza os dados de uma conta.
	 * @param vo Objeto com dados atualizados de uma conta
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public void update(ContaBancoVO vo) throws SmartEnvException, SmartAppException {
		
		contaDAO.update(vo);
		
		contaDAO.removeContaBanco(vo);
		
		if (vo.getFlgContaCaixa() == null || vo.getFlgContaCaixa().equals("")) {
			this.validateFieldsContaBanco(vo);
		     contaDAO.insertContaBanco(vo);
		}
		else {
			this.validateFieldsConta(vo);
		}
	}
	
	/**
	 * Insere uma nova conta.
	 * @param vo Nova conta a ser inserida
	 * @return contaVO com chave do registro inserido
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public ContaBancoVO insert(ContaBancoVO vo) throws SmartEnvException, SmartAppException {
		
		ContaBancoVO contaBancoVO = (ContaBancoVO) contaDAO.insert(vo);
		try {
		
			if (vo.getFlgContaCaixa() == null || vo.getFlgContaCaixa().equals("")) {
				this.validateFieldsContaBanco(vo);
				contaBancoVO = contaDAO.insertContaBanco(contaBancoVO);
			}
			else {
				this.validateFieldsConta(vo);
			}
		} catch (SmartAppException e) {
			vo.setCodigo(null);
			throw e;
		}
		return contaBancoVO;
	}
	
	/**
	 * Remove uma conta - exclusao LOGICA desde janeiro/2020
	 * @param vo Objeto com chave da conta a ser removida
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public void remove(ContaVO vo) throws SmartEnvException, SmartAppException {
		
		//contaDAO.removeContaBanco(vo);
		//contaDAO.removeHistoricoSaldoByConta(vo);
		contaDAO.remove(vo);
	}
	
	/**
	 * Obtém o saldo atual da conta passada como parâmetro.
	 * @param vo Conta que se deseja obter o saldo
	 * @return Objeto que representa uma conta com o saldo setado na propriedade saldo
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public ContaVO obterSaldo(ContaVO vo) throws SmartEnvException, SmartAppException {
		
		return contaDAO.obterSaldo(vo);
	}
	
	/**
	 * Obtém o extrato de um determinado período de uma determinada conta.
	 * @param assembler Objeto que encapsula os dados de filtro da operação de obtenção do extrato
	 * @return Objeto que encapsula as informações necessárias de um extrato obtido
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public ExtratoAssembler obterExtrato(ExtratoFiltroAssembler assembler) throws SmartEnvException, SmartAppException {
		
		this.validateFieldsExtrato(assembler);
		
		ExtratoAssembler 	extratoAssembler 	= new ExtratoAssembler();
		SaldoAssembler 	saldoAssembler		= new SaldoAssembler();
		
		saldoAssembler.setContaVO(assembler.getContaVO()); 
		saldoAssembler.setData(assembler.getDataInicial());
		
		extratoAssembler.setSaldoInicial(contaDAO.obterSaldo(saldoAssembler).getSaldo());
		
		extratoAssembler.setBaixaLancamento(contaDAO.obterLancamentos(assembler));
		
		Iterator iterator = extratoAssembler.getBaixaLancamento().iterator();
		
		BaixaLancamentoVO baixaLancamentoVO = new BaixaLancamentoVO();
		
		while (iterator.hasNext()) 
			baixaLancamentoVO = (BaixaLancamentoVO)iterator.next();
		
		extratoAssembler.setDiretorFinanceiroVO(diretorFinanceiroRN.findByDate(baixaLancamentoVO.getData()));
		
		if (!assembler.getDataFinal().before(dataRN.getCurrentDate())) {
			extratoAssembler.setSaldoFinal(contaDAO.obterSaldo(saldoAssembler.getContaVO()).getSaldo());
		} else {
			saldoAssembler.setData(dataRN.addDays(assembler.getDataFinal(), 1));
			
			extratoAssembler.setSaldoFinal(contaDAO.obterSaldo(saldoAssembler).getSaldo());
		}
		return extratoAssembler;
	}
	
	/**
	 * Valida os campos de uma conta a ser inserida ou atualizada.
	 * @param vo
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	private void validateFieldsConta(ContaBancoVO vo) throws SmartEnvException, SmartAppException {
		// Testando Nome
		if (vo.getNome()  == null || vo.getNome().equals("")) {
			throw new SmartAppException("O nome deve ser preenchido para conta caixa.");
		}
	}

	/**
	 * Valida os dados bancários de uma conta a ser inserida ou atualizada.
	 * @param vo
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	private void validateFieldsContaBanco(ContaBancoVO vo) throws SmartEnvException, SmartAppException {
		// Testando Banco
		if (vo.getBancoVO() == null || vo.getBancoVO().getCodigo() == null || vo.getBancoVO().getCodigo().intValue() == 0) {
			throw new SmartAppException("O banco deve ser informado.");

		}
		
		// Testando Agencia
		if (vo.getNumAgencia() == null || vo.getNumAgencia().equals("")) {
			throw new SmartAppException("A agência deve ser informada.");
			
		}
		
		// Testando Conta
		if (vo.getNumConta() == null || vo.getNumConta().equals("")) {
			throw new SmartAppException("A conta deve ser informada.");
		}

		// Testando Digito Conta
		if (vo.getDigConta() == null || vo.getDigConta().equals("")) {
			throw new SmartAppException("O dígito da conta deve ser informado.");
		}
		
		// Testando Tipo Conta
		if (vo.getTipoContaVO() == null || vo.getTipoContaVO().getCodigo() == null || vo.getTipoContaVO().getCodigo().intValue() == 0) {
			throw new SmartAppException("O tipo da conta deve ser informado.");
		}
		
		// Validando CPF/CNPJ Titular
		if (vo.getCpfCnpjTitular() != null && !vo.getCpfCnpjTitular().equals("")) {
			if (vo.getCpfCnpjTitular().length() == 11) {
				if (!validateCpf(vo.getCpfCnpjTitular())) {
					throw new SmartAppException("O número de CPF/CNPJ informado não é válido.");
				}
			}
			else if (vo.getCpfCnpjTitular().length() == 14) {
				if (!validateCnpj(vo.getCpfCnpjTitular())) {
					throw new SmartAppException("O número de CPF/CNPJ informado não é válido.");
				}
			}
			else {
				throw new SmartAppException("O CPF/CNPJ informado é inválido.");
			}
		}
	}

	/**
	 * Valida os campos de filtro da operação de obtenção de extrato.
	 * @param assembler Objeto que encapsula o filtro de extrato
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	private void validateFieldsExtrato(ExtratoFiltroAssembler assembler) throws SmartEnvException, SmartAppException {
		if (assembler.getContaVO() == null || assembler.getContaVO().getCodigo() == null || assembler.getContaVO().getCodigo().intValue() == 0) {
			throw new SmartAppException("A conta deve ser informada.");
		}
		
		if (assembler.getDataInicial() == null) {
			throw new SmartAppException("A data inicial deve ser informada.");
		}
		else if (assembler.getDataInicial().after(dataRN.getCurrentDate())) {
			throw new SmartAppException("A data inicial deve ser menor ou igual a data atual.");
		}
		
		if (assembler.getDataFinal() == null) {
			throw new SmartAppException("A data final deve ser informada.");
		} else if (assembler.getDataFinal().before(assembler.getDataInicial())) {
			throw new SmartAppException("Período inválido.  A data inicial é maior do que a data final.");
		}
	}

	/**
	 * Remove todos os registros de histórico de saldo de uma conta a partir de uma determinada data.
	 * @param contaVO Representa a conta que terá alguns registros de histórico de saldo removidos 
	 * @param data Data a partir da qual se deseja remover os registros de histórico de saldo
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public void removeHistoricoSaldoByData(ContaVO contaVO, Date data) throws SmartEnvException, SmartAppException {

		contaDAO.removeHistoricoSaldoByData(new HistoricoSaldoAssembler(contaVO, data));
	}
	
	/**
	 * Remove todos os registros de histórico de saldo de uma conta a partir da data de um determinado lançamento.
	 * @param contaVO Representa a conta que terá alguns registros de histórico de saldo removidos 
	 * @param lancamentoVO Representa o lançamento que sua data será utilizada para data início da remoção dos registros de histórico de saldo 
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */	
	public void removeHistoricoSaldoByLancamento(ContaVO contaVO, LancamentoVO lancamentoVO) throws SmartEnvException, SmartAppException {
		
		contaDAO.removeHistoricoSaldoByLancamento(new HistoricoSaldoAssembler(contaVO, lancamentoVO));
	}
	
	
	/**
	 * Remove todos os registros de histórico de saldo de uma conta.
	 * @param contaVO Representa a conta que terá os registros de histórico de saldo removidos 
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */	
	public void removeHistoricoSaldo(ContaVO contaVO) throws SmartEnvException, SmartAppException {
		
		contaDAO.removeHistoricoSaldo(contaVO);
	}

	/**
	 * Consolida os históricos de saldo de todas as contas até a data atual
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public void consolidarHistoricoSaldo() throws SmartEnvException, SmartAppException {
		
		contaDAO.consolidarHistoricoSaldo();
	}

	public void setContaDAO(ContaDAO contaDAO) {
		this.contaDAO = contaDAO;
	}

	public void setDataRN(DataRN dataRN) {
		this.dataRN = dataRN;
	}

	public void setDiretorFinanceiroRN(DiretorFinanceiroRN diretorFinanceiroRN) {
		this.diretorFinanceiroRN = diretorFinanceiroRN;
	}
}