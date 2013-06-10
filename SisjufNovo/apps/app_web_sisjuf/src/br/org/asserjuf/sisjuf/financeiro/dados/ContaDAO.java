package br.org.asserjuf.sisjuf.financeiro.dados;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import br.com.falc.smartFW.exception.SmartAppException;
import br.com.falc.smartFW.exception.SmartEnvException;
import br.com.falc.smartFW.persistence.SmartConnection;
import br.com.falc.smartFW.persistence.SmartPreparedStatement;
import br.com.falc.smartFW.persistence.SmartResultSet;
import br.org.asserjuf.sisjuf.dados.SisjufDAOPostgres;
import br.org.asserjuf.sisjuf.financeiro.BaixaLancamentoVO;
import br.org.asserjuf.sisjuf.financeiro.ContaBancoVO;
import br.org.asserjuf.sisjuf.financeiro.ExtratoFiltroAssembler;
import br.org.asserjuf.sisjuf.financeiro.FormaPagamentoVO;
import br.org.asserjuf.sisjuf.financeiro.HistoricoSaldoAssembler;
import br.org.asserjuf.sisjuf.financeiro.SaldoAssembler;
import br.org.asserjuf.sisjuf.financeiro.TipoContaVO;
import br.org.asserjuf.sisjuf.financeiro.ContaVO;
import br.org.asserjuf.sisjuf.financeiro.BancoVO;
import br.org.asserjuf.sisjuf.financeiro.LancamentoVO;
import br.org.asserjuf.sisjuf.financeiro.OrigemLancamentoVO;
import br.org.asserjuf.sisjuf.financeiro.TipoLancamentoVO;
import br.org.asserjuf.sisjuf.financeiro.TipoOperacaoVO;

/**
 * Classe de acesso ao banco de dados da entidade "Conta" do Sisjuf.
 * @author Rodrigo Falcão
 *
 */
public class ContaDAO extends SisjufDAOPostgres {

	public ContaDAO() throws SmartEnvException {
		super(); 
		
	}

	/**
	 * Obtém todas as contas cadastradas no banco de dados. Views: VW_CONTA. Tabelas: BANCO, TIPO_CONTA
	 * @return Coleção de ContaBancoVO (entidade que encapsula as contas).
	 * @throws SmartEnvException
	 */
	public Collection<ContaBancoVO> findAll() throws SmartEnvException {
		
		 
		StringBuffer sql = new StringBuffer("SELECT c.seq_conta, c.nom_conta, b.sig_banco, b.nom_banco, ");
		sql.append("c.num_agencia_conta, c.dig_agencia_conta, c.num_conta, c.dig_conta, tc.des_tipo_conta, ");
		sql.append("c.nom_titular_conta, c.cpf_cnpj_titular_conta ");
		sql.append("FROM vw_conta c ");
		sql.append("LEFT OUTER JOIN	banco b ");
		sql.append("USING(seq_banco) ");
		sql.append("LEFT OUTER JOIN tipo_conta tc ");
		sql.append("USING(seq_tipo_conta) ");
		sql.append("ORDER BY c.nom_conta ");

		SmartConnection 		sConn 	= null;
		SmartPreparedStatement 	sStmt 	= null;
		SmartResultSet			sRs		= null;
		
		try {
		
			sConn 	= new SmartConnection(this.getConn());
			sStmt 	= new SmartPreparedStatement(sConn.prepareStatement(sql.toString()));
			sRs 	= new SmartResultSet(sStmt.getMyPreparedStatement().executeQuery());
			
			ArrayList<ContaBancoVO> retorno = new ArrayList<ContaBancoVO>(); 
			
			while (sRs.next()) {

				ContaBancoVO contaBancoVO = new ContaBancoVO();
				BancoVO bancoVO = new BancoVO();
				TipoContaVO tipoContaVO = new TipoContaVO();
				
				contaBancoVO.setCodigo(sRs.getInteger(1));
				contaBancoVO.setNome(sRs.getString(2));
				bancoVO.setSigla(sRs.getString(3));
				bancoVO.setNome(sRs.getString(4));
				contaBancoVO.setBancoVO(bancoVO);
				contaBancoVO.setNumAgencia(sRs.getString(5));
				contaBancoVO.setDigAgencia(sRs.getString(6));
				contaBancoVO.setNumConta(sRs.getString(7));
				contaBancoVO.setDigConta(sRs.getString(8));
				tipoContaVO.setDescricao(sRs.getString(9));
				contaBancoVO.setTipoContaVO(tipoContaVO);
				contaBancoVO.setNomTitular(sRs.getString(10));
				contaBancoVO.setCpfCnpjTitular(sRs.getString(11));
				
				
				retorno.add(contaBancoVO);

			}
			
			return retorno;
		
		} catch (SQLException e) {
			throw new SmartEnvException(e);

		} finally {

			sRs.close();
			sStmt.close();
			sConn.close();

		}
		
	}

	/**
	 * Obtém as conta (encapsulada no objeto ContaBancoVO) cadastrada no banco de dados. Views: VW_CONTA. Tabelas: BANCO, TIPO_CONTA
	 * @param vo Instância da classe ContaVO contendo a propriedade codigo 
	 * @return Retorna uma instância da classe ContaBancoVO com as propriedades da conta preenchidas.
	 * @throws SmartEnvException
	 */
	public ContaBancoVO findByPrimaryKey(ContaVO vo) throws SmartEnvException {
				 
		StringBuffer sql = new StringBuffer("SELECT c.seq_conta, c.nom_conta, c.seq_banco, b.sig_banco, ");
		sql.append("c.num_agencia_conta, c.dig_agencia_conta, c.num_conta, ");
		sql.append("c.dig_conta, c.cod_operacao_conta, c.seq_tipo_conta, ");
		sql.append("tc.des_tipo_conta, c.nom_titular_conta, c.cpf_cnpj_titular_conta ");
		sql.append("FROM vw_conta c ");
		sql.append("LEFT OUTER JOIN	banco b ");
		sql.append("USING(seq_banco) ");
		sql.append("LEFT OUTER JOIN tipo_conta tc ");
		sql.append("USING(seq_tipo_conta) ");
		sql.append("WHERE c.seq_conta = ? ");

		SmartConnection 		sConn 	= null;
		SmartPreparedStatement 	sStmt 	= null;
		SmartResultSet			sRs		= null;
		
		try {
		
			sConn 	= new SmartConnection(this.getConn());
			sStmt 	= new SmartPreparedStatement(sConn.prepareStatement(sql.toString()));

			sStmt.setInteger(1, vo.getCodigo());

			sRs 	= new SmartResultSet(sStmt.getMyPreparedStatement().executeQuery());
			if (sRs.next()) {
				
				
				ContaBancoVO contaBancoVO = new ContaBancoVO();
				BancoVO bancoVO = new BancoVO();
				TipoContaVO tipoContaVO = new TipoContaVO();
				
				contaBancoVO.setCodigo(sRs.getInteger(1));
				contaBancoVO.setNome(sRs.getString(2));
				bancoVO.setCodigo(sRs.getInteger(3));
				bancoVO.setSigla(sRs.getString(4));
				contaBancoVO.setBancoVO(bancoVO);
				contaBancoVO.setNumAgencia(sRs.getString(5));
				contaBancoVO.setDigAgencia(sRs.getString(6));
				contaBancoVO.setNumConta(sRs.getString(7));
				contaBancoVO.setDigConta(sRs.getString(8));
				contaBancoVO.setCodOperacao(sRs.getString(9));
				tipoContaVO.setCodigo(sRs.getInteger(10));
				tipoContaVO.setDescricao(sRs.getString(11));
				contaBancoVO.setTipoContaVO(tipoContaVO);
				contaBancoVO.setNomTitular(sRs.getString(12));
				contaBancoVO.setCpfCnpjTitular(sRs.getString(13));
				
				return contaBancoVO;
			}
			
			return null;			
				
		} catch (SQLException e) {
			throw new SmartEnvException(e);

		} finally {

			sRs.close();
			sStmt.close();
			sConn.close();

		}
		
	}

	
	/**
	 * Atualiza os dados de um banco no banco de dados. Tabelas: CONTA
	 * @param vo Instância da classe ContaBancoVO com os novos valores do registro de conta a ser atualizado.
	 * @throws SmartEnvException
	 */
	public void update(ContaBancoVO vo) throws SmartEnvException {
				
		StringBuffer sql = new StringBuffer("UPDATE conta SET nom_conta = ? WHERE seq_conta = ? ");
		SmartConnection 		sConn 	= null;
		SmartPreparedStatement 	sStmt 	= null;
		
		try {
		
			sConn 	= new SmartConnection(this.getConn());
			sStmt 	= new SmartPreparedStatement(sConn.prepareStatement(sql.toString()));
			
			sStmt.setString(1, vo.getNome());
			sStmt.setInteger(2, vo.getCodigo());
									
			sStmt.getMyPreparedStatement().execute();
				
		} catch (SQLException e) {

			throw new SmartEnvException(e);

		} finally {
		
			sStmt.close();
			sConn.close();

		}
		
	}

	/**
	 * Instância da classe ContaBancoVO com os novos valores do registro de conta de banco a ser atualizado. Tabelas: CONTA_BANCO
	 * @param vo Instância da classe ContaBancoVO com os novos valores do registro de conta de banco a ser atualizado.
	 * @throws SmartEnvException
	 */
	public void updateContaBanco(ContaBancoVO vo) throws SmartEnvException {
		
		StringBuffer sql = new StringBuffer("UPDATE conta_banco ");
		sql.append("SET seq_banco = ?, num_agencia_conta = ?, ");
		sql.append("dig_agencia_conta = ?, num_conta = ?, dig_conta = ?, ");
		sql.append("cod_operacao_conta = ?, seq_tipo_conta = ?, ");
		sql.append("nom_titular_conta = ?, cpf_cnpj_titular_conta = ? ");
		sql.append("WHERE seq_conta = ? ");

		SmartConnection 		sConn 	= null;
		SmartPreparedStatement 	sStmt 	= null;
		
		try {
		
			sConn 	= new SmartConnection(this.getConn());
			sStmt 	= new SmartPreparedStatement(sConn.prepareStatement(sql.toString()));
			
			sStmt.setInteger(1, vo.getBancoVO().getCodigo());
			sStmt.setString(2, vo.getNumAgencia());
			sStmt.setString(3, vo.getDigAgencia());
			sStmt.setString(4, vo.getNumConta());
			sStmt.setString(5, vo.getDigConta());
			sStmt.setString(6, vo.getCodOperacao());
			sStmt.setInteger(7, vo.getTipoContaVO().getCodigo());
			sStmt.setString(8, vo.getNomTitular());
			sStmt.setString(9, vo.getCpfCnpjTitular());
			sStmt.setInteger(10, vo.getCodigo());
									
			sStmt.getMyPreparedStatement().execute();
			
		} catch (SQLException e) {

			throw new SmartEnvException(e);

		} finally {
		
			sStmt.close();
			sConn.close();

		}
		
	}

	/**
	 * Insere uma conta no banco de dados. Tabelas: CONTA
	 * @param vo Instância da classe ContaVO com os valores do registro a ser inserido.
	 * @return Retorna uma instância da classe ContaVO com as propriedades da conta preenchidas. 
	 * @throws SmartEnvException
	 */
	public ContaVO insert(ContaVO vo) throws SmartEnvException {
		
		
		StringBuffer sql = new StringBuffer("INSERT INTO CONTA (SEQ_CONTA, NOM_CONTA) VALUES (?, ?) ");
		
		SmartConnection 		sConn 	= null;
		SmartPreparedStatement 	sStmt 	= null;
		
		try {
		
			sConn 	= new SmartConnection(this.getConn());
			sStmt 	= new SmartPreparedStatement(sConn.prepareStatement(sql.toString()));
			
			vo.setCodigo(new Integer(getSequence("SEQ_CONTA").intValue()));
			
			sStmt.setInteger(1, vo.getCodigo());
			sStmt.setString(2, vo.getNome());
						
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
	 * Insere uma conta de banco no banco de dados. Tabelas: CONTA_BANCO
	 * @param vo Instância da classe ContaBancoVO com os valores do registro a ser inserido.
	 * @return Retorna uma instância da classe ContaBancoVO com as propriedades da conta do banco preenchidas.
	 * @throws SmartEnvException
	 */
	public ContaBancoVO insertContaBanco(ContaBancoVO vo) throws SmartEnvException {
		
		
		StringBuffer sql = new StringBuffer("INSERT INTO CONTA_BANCO ");
		sql.append("(SEQ_CONTA, SEQ_BANCO, NUM_AGENCIA_CONTA, ");
		sql.append("DIG_AGENCIA_CONTA, NUM_CONTA, DIG_CONTA, ");
		sql.append("COD_OPERACAO_CONTA, SEQ_TIPO_CONTA, NOM_TITULAR_CONTA, ");
		sql.append("CPF_CNPJ_TITULAR_CONTA) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ");
		
		SmartConnection 		sConn 	= null;
		SmartPreparedStatement 	sStmt 	= null;
		
		try {
		
			sConn 	= new SmartConnection(this.getConn());
			sStmt 	= new SmartPreparedStatement(sConn.prepareStatement(sql.toString()));
			
			sStmt.setInteger(1, vo.getCodigo());
			sStmt.setInteger(2, vo.getBancoVO().getCodigo());
			sStmt.setString(3, vo.getNumAgencia());
			sStmt.setString(4, vo.getDigAgencia());
			sStmt.setString(5, vo.getNumConta());
			sStmt.setString(6, vo.getDigConta());
			sStmt.setString(7, vo.getCodOperacao());
			sStmt.setInteger(8, vo.getTipoContaVO().getCodigo());
			sStmt.setString(9, vo.getNomTitular());
			sStmt.setString(10, vo.getCpfCnpjTitular());
						
			sStmt.getMyPreparedStatement().execute();

			return vo;
				
		} catch (SQLException e) {

			throw new SmartEnvException(e);

		} finally {

		
			sStmt.close();
			sConn.close();

		}
		
	}

	
	/**
	 * Exclui uma conta de banco do banco de dados. Tabelas: CONTA_BANCO
	 * @param vo Instância da classe ContaVO com o código do registro a ser excluído.
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public void removeContaBanco(ContaVO vo) throws SmartEnvException, SmartAppException {
		
		
		StringBuffer sql = new StringBuffer("DELETE FROM CONTA_BANCO WHERE SEQ_CONTA = ? ");
	
		SmartConnection 		sConn 	= null;
		SmartPreparedStatement 	sStmt 	= null;
		
		try {
		
			sConn 	= new SmartConnection(this.getConn());
			sStmt 	= new SmartPreparedStatement(sConn.prepareStatement(sql.toString()));			
	
			sStmt.setInteger(1, vo.getCodigo());
									
			sStmt.getMyPreparedStatement().execute();
							
		} catch (SQLException e) {
			
			// Verificando se o erro foi por causa da violação de alguma FK.
			
			if (e.getMessage().indexOf("fk_historico_saldo_conta") != -1) {
				// Exceção levantada pela presença de histórico.
				throw new SmartAppException("Não é possível excluir esta conta, pois há registros de histórico de saldo associados a ela.");
				
			} else if (e.getMessage().indexOf("fk_lancamento_conta") != -1) {
				// Exceção levantada pela presença de lancamentos.
				throw new SmartAppException("Não é possível excluir esta conta, pois há registros de lançamentos associados a ela.");
				
			} 
			
			// Caso não se encaixa em nenhuma das exceções anteriores, levantar exceção de ambiente.			
			throw new SmartEnvException(e);

		} finally {

		
			sStmt.close();
			sConn.close();

		}
		
	}

	/**
	 * Exclui uma conta do banco de dados. Tabelas: CONTA
	 * @param vo Instância da classe ContaVO com o código do registro a ser excluído.
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public void remove(ContaVO vo) throws SmartEnvException, SmartAppException {
		
		
		StringBuffer sql = new StringBuffer("DELETE FROM CONTA WHERE SEQ_CONTA = ? ");
	
		SmartConnection 		sConn 	= null;
		SmartPreparedStatement 	sStmt 	= null;
		
		try {
		
			sConn 	= new SmartConnection(this.getConn());
			sStmt 	= new SmartPreparedStatement(sConn.prepareStatement(sql.toString()));			
	
			sStmt.setInteger(1, vo.getCodigo());
									
			sStmt.getMyPreparedStatement().execute();
							
		} catch (SQLException e) {
			// Verificando se o erro foi por causa da violação de alguma FK.
			
			if (e.getMessage().indexOf("fk_historico_saldo_conta") != -1) {
				// Exceção levantada pela presença de histórico.
				throw new SmartAppException("Não é possível excluir esta conta, pois há registros de histórico de saldo associados a ela.");
				
			} else if (e.getMessage().indexOf("fk_lancamento_conta") != -1) {
				// Exceção levantada pela presença de lancamentos.
				throw new SmartAppException("Não é possível excluir esta conta, pois há registros de lançamentos associados a ela.");
				
			} 
			
			// Caso não se encaixa em nenhuma das exceções anteriores, levantar exceção de ambiente.
			throw new SmartEnvException(e);

		} finally {

		
			sStmt.close();
			sConn.close();

		}
		
	}
		
	public void removeHistoricoSaldoByConta(ContaVO vo) throws SmartEnvException, SmartAppException {
		
		
		StringBuffer sql = new StringBuffer("DELETE FROM HISTORICO_SALDO WHERE SEQ_CONTA = ? ");
	
		SmartConnection 		sConn 	= null;
		SmartPreparedStatement 	sStmt 	= null;
		
		try {
		
			sConn 	= new SmartConnection(this.getConn());
			sStmt 	= new SmartPreparedStatement(sConn.prepareStatement(sql.toString()));			
	
			sStmt.setInteger(1, vo.getCodigo());
									
			sStmt.getMyPreparedStatement().execute();
							
		} catch (SQLException e) {
			throw new SmartEnvException(e);

		} finally {

		
			sStmt.close();
			sConn.close();

		}

	}

	/**
	 * Obtém o saldo de uma conta do banco de dados através da procedure "obter_saldo(int2)".
	 * @param vo Instância da classe ContaVO com o código do registro a ser excluído.
	 * @return Retorna uma instância da classe ContaVO com as propriedades da conta preenchidas.
	 * @throws SmartEnvException
	 */
	public ContaVO obterSaldo(ContaVO vo) throws SmartEnvException {
		 
		StringBuffer sql = new StringBuffer("SELECT obter_saldo(int2(?)) ");

		SmartConnection 		sConn 	= null;
		SmartPreparedStatement 	sStmt 	= null;
		SmartResultSet			sRs		= null;
		
		try {
		
			sConn 	= new SmartConnection(this.getConn());
			sStmt 	= new SmartPreparedStatement(sConn.prepareStatement(sql.toString()));

			sStmt.setInteger(1, vo.getCodigo());

			sRs 	= new SmartResultSet(sStmt.getMyPreparedStatement().executeQuery());
			
			if (sRs.next()) {
				
				ContaVO contaVO = new ContaVO();
				
				contaVO.setSaldo(sRs.getDouble(1));

				return contaVO;
			}
			
			return null;			
				
		} catch (SQLException e) {
			throw new SmartEnvException(e);

		} finally {

			sRs.close();
			sStmt.close();
			sConn.close();

		}
		
	}

	
	/**
	 * Consolida o valor do histórico a partir do último histórico e dos lançamentos mais recentes através da procedure "CONSOLIDAR_HISTORICO_SALDO()". 
	 * @throws SmartEnvException
	 */
	public void consolidarHistoricoSaldo() throws SmartEnvException {
		
		StringBuffer sql = new StringBuffer("SELECT CONSOLIDAR_HISTORICO_SALDO() ");
		
		SmartConnection 		sConn 	= null;
		SmartPreparedStatement 	sStmt 	= null;
		
		try {
		
			sConn 	= new SmartConnection(this.getConn());
			sStmt 	= new SmartPreparedStatement(sConn.prepareStatement(sql.toString()));			
			sStmt.getMyPreparedStatement().execute();
							
		} catch (SQLException e) {

			throw new SmartEnvException(e);

		} finally {

		
			sStmt.close();
			sConn.close();

		}
		
	}

	/**
	 * Exclui o histórico do saldo de uma conta no banco de dados a partir de uma data determinada. Tabelas: HISTORICO_SALDO
	 * @param assembler Instância da classe HistoricoSaldoAssembler com a conta e a data a serem usadas para efetuar a exclusão.
	 * @throws SmartEnvException
	 */
	public void removeHistoricoSaldoByData(HistoricoSaldoAssembler assembler) throws SmartEnvException {
		
		StringBuffer sql = new StringBuffer("DELETE FROM HISTORICO_SALDO WHERE SEQ_CONTA = ? AND DAT_HISTORICO_SALDO > ? ");
	
		SmartConnection 		sConn 	= null;
		SmartPreparedStatement 	sStmt 	= null;
		
		try {
		
			sConn 	= new SmartConnection(this.getConn());
			sStmt 	= new SmartPreparedStatement(sConn.prepareStatement(sql.toString()));			
	
			sStmt.setInteger(1, assembler.getContaVO().getCodigo());
			sStmt.setDate(2, assembler.getData());
									
			sStmt.getMyPreparedStatement().execute();
							
		} catch (SQLException e) {

			throw new SmartEnvException(e);

		} finally {

		
			sStmt.close();
			sConn.close();

		}
		
	}
	
	/**
	 * Exclui o histórico do saldo de uma conta no banco de dados a partir de uma lançamento determinado.
	 * @param assembler Instância da classe HistoricoSaldoAssembler com a conta e o lançamento a serem usados para efetuar a exclusão.
	 * @throws SmartEnvException
	 */
	public void removeHistoricoSaldoByLancamento(HistoricoSaldoAssembler assembler) throws SmartEnvException {
		
		//StringBuffer sql = new StringBuffer("DELETE FROM HISTORICO_SALDO WHERE SEQ_CONTA = ? AND DAT_HISTORICO_SALDO > ? ");
		
		StringBuffer sql = new StringBuffer("delete from historico_saldo where dat_historico_saldo > ")
							.append("(select min(dat_baixa_lancamento) from baixa_lancamento where seq_lancamento = ?) ")
							.append("and seq_conta = ? ");
	
		SmartConnection 		sConn 	= null;
		SmartPreparedStatement 	sStmt 	= null;
		
		try {
		
			sConn 	= new SmartConnection(this.getConn());
			sStmt 	= new SmartPreparedStatement(sConn.prepareStatement(sql.toString()));			
	
			sStmt.setInteger(1, assembler.getLancamentoVO().getCodigo());
			sStmt.setInteger(2, assembler.getContaVO().getCodigo());
								
			sStmt.getMyPreparedStatement().execute();
							
		} catch (SQLException e) {

			throw new SmartEnvException(e);

		} finally {

		
			sStmt.close();
			sConn.close();

		}
		
	}
	
	/**
	 * Exclui o histórico do saldo de uma conta no banco de dados a partir de uma lançamento determinado.
	 * @param assembler Instância da classe HistoricoSaldoAssembler com a conta e o lançamento a serem usados para efetuar a exclusão.
	 * @throws SmartEnvException
	 */
	public void removeHistoricoSaldo(ContaVO conta) throws SmartEnvException {
		
		StringBuffer sql = new StringBuffer("DELETE FROM HISTORICO_SALDO WHERE SEQ_CONTA = ? ");
		

	
		SmartConnection 		sConn 	= null;
		SmartPreparedStatement 	sStmt 	= null;
		
		try {
		
			sConn 	= new SmartConnection(this.getConn());
			sStmt 	= new SmartPreparedStatement(sConn.prepareStatement(sql.toString()));			
	
			sStmt.setInteger(1, conta.getCodigo());
								
			sStmt.getMyPreparedStatement().execute();
							
		} catch (SQLException e) {

			throw new SmartEnvException(e);

		} finally {

		
			sStmt.close();
			sConn.close();

		}
		
	}

	/**
	 * Obtém o saldo de uma conta do banco de dados até uma determinada data através da procedure "obter_saldo(int2, date)".
	 * @param assembler Instância da classe SaldoAssembler com o código da conta cujo saldo deseja ser conhecido e a data limite.
	 * @return Retorna uma instância da classe ContaVO com as propriedades da conta preenchidas.
	 * @throws SmartEnvException
	 */
	public ContaVO obterSaldo(SaldoAssembler assembler) throws SmartEnvException {
		 
		StringBuffer sql = new StringBuffer("SELECT obter_saldo(int2(?), date(?)) ");

		SmartConnection 		sConn 	= null;
		SmartPreparedStatement 	sStmt 	= null;
		SmartResultSet			sRs		= null;
		
		try {
		
			sConn 	= new SmartConnection(this.getConn());
			sStmt 	= new SmartPreparedStatement(sConn.prepareStatement(sql.toString()));

			sStmt.setInteger(1, assembler.getContaVO().getCodigo());
			sStmt.setDate(2, assembler.getData());
			
			sRs 	= new SmartResultSet(sStmt.getMyPreparedStatement().executeQuery());
			
			ContaVO contaVO = new ContaVO();
			
			if (sRs.next()) {
							
				contaVO.setCodigo(assembler.getContaVO().getCodigo());
				
				contaVO.setSaldo(sRs.getDouble(1));

			}
					
			return contaVO;
				
		} catch (SQLException e) {
			throw new SmartEnvException(e);

		} finally {

			sRs.close();
			sStmt.close();
			sConn.close();

		}
		
	}

	/**
	 * Obtém uma coleção de lançamentos cadastrados no banco de dados. Tabelas: LANCAMENTO, BAIXA_LANCAMENTO, ORIGEM_LANCAMENTO, TIPO_LANCAMENTO, TIPO_OPERACAO
	 * @param assembler Instância da classe ExtratoFiltroAssembler contendo a data inicial, a data final e o código da conta.
	 * @return Coleção de BaixaLancamentoVO (entidade que encapsula as baixas de lançamento).
	 * @throws SmartEnvException
	 */
	public Collection<BaixaLancamentoVO> obterLancamentos(ExtratoFiltroAssembler assembler) throws SmartEnvException {
		 
		StringBuffer sql = new StringBuffer("select bl.dat_baixa_lancamento, bl.val_baixa_lancamento, ");
		sql.append("(CASE  WHEN (ol.seq_origem_lancamento = (select str_val_parametro from parametros where nom_parametro ='ORIGEM_USUARIO')) THEN tl.nom_tipo_lancamento ELSE ol.nom_origem_lancamento END) as origem_tipo, ");
		sql.append("tl.nom_tipo_lancamento, top.sig_tipo_operacao, ");
		sql.append("fp.nom_forma_pagamento, bl.des_banco_cheque_baixa_lancamento, bl.num_agencia_cheque_baixa_lancamento, bl.dig_agencia_cheque_baixa_lancamento, bl.num_conta_cheque_baixa_lancamento, bl.dig_conta_cheque_baixa_lancamento, bl.num_cheque_baixa_lancamento, ");
		sql.append(" l.des_lancamento ");
		sql.append("from lancamento l natural join baixa_lancamento bl ");
		sql.append("natural join forma_pagamento fp ");
		sql.append("natural join origem_lancamento ol natural left join tipo_lancamento tl ");
		sql.append("natural join tipo_operacao top ");
		sql.append("where bl.dat_baixa_lancamento between ? and ? and l.seq_conta = ? ");
		sql.append("order by bl.dat_baixa_lancamento, ol.nom_origem_lancamento, ");
		sql.append("tl.nom_tipo_lancamento, top.sig_tipo_operacao ");

		SmartConnection 		sConn 	= null;
		SmartPreparedStatement 	sStmt 	= null;
		SmartResultSet			sRs		= null;
		
		try {
		
			sConn 	= new SmartConnection(this.getConn());
			sStmt 	= new SmartPreparedStatement(sConn.prepareStatement(sql.toString()));

			sStmt.setDate(1, assembler.getDataInicial());
			sStmt.setDate(2, assembler.getDataFinal());
			sStmt.setInteger(3, assembler.getContaVO().getCodigo());
			
			sRs 	= new SmartResultSet(sStmt.getMyPreparedStatement().executeQuery());

			ArrayList<BaixaLancamentoVO> retorno = new ArrayList<BaixaLancamentoVO>();
			
			while (sRs.next()) {
				
				LancamentoVO lancamentoVO = new LancamentoVO();
				OrigemLancamentoVO origemLancamentoVO = new OrigemLancamentoVO();
				TipoLancamentoVO tipoLancamentoVO = new TipoLancamentoVO();
				TipoOperacaoVO tipoOperacaoVO = new TipoOperacaoVO();
				BaixaLancamentoVO baixaLancamentoVO = new BaixaLancamentoVO();
				FormaPagamentoVO formaPagamentoVO = new FormaPagamentoVO();
				
				baixaLancamentoVO.setData(sRs.getDate(1));
				baixaLancamentoVO.setValor(sRs.getDouble(2));
				origemLancamentoVO.setNome(sRs.getString(3));
				lancamentoVO.setOrigemLancamentoVO(origemLancamentoVO);
				tipoLancamentoVO.setNome(sRs.getString(4));
				lancamentoVO.setTipoLancamentoVO(tipoLancamentoVO);
				tipoOperacaoVO.setSigla(sRs.getString(5));
				lancamentoVO.setTipoOperacaoVO(tipoOperacaoVO);
				lancamentoVO.setDescricao(sRs.getString(13));
				baixaLancamentoVO.setLancamentoVO(lancamentoVO);
				formaPagamentoVO.setNome(sRs.getString(6));
				baixaLancamentoVO.setFormaPagamentoVO(formaPagamentoVO);
				baixaLancamentoVO.setBancoCheque(sRs.getString(7));
				baixaLancamentoVO.setAgenciaCheque(sRs.getString(8));
				baixaLancamentoVO.setDigitoAgenciaCheque(sRs.getString(9));
				baixaLancamentoVO.setContaCheque(sRs.getString(10));
				baixaLancamentoVO.setDigitoContaCheque(sRs.getString(11));
				baixaLancamentoVO.setNumeroCheque(sRs.getString(12));
				
				retorno.add(baixaLancamentoVO);
				
			}

			return retorno;
				
		} catch (SQLException e) {
			throw new SmartEnvException(e);

		} finally {

			sRs.close();
			sStmt.close();
			sConn.close();

		}
		
	}

}
