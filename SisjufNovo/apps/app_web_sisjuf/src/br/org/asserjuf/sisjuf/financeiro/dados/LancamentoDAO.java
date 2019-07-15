package br.org.asserjuf.sisjuf.financeiro.dados;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import br.org.asserjuf.sisjuf.associados.convenio.FaturaVO;
import br.org.asserjuf.sisjuf.dados.SisjufDAOPostgres;
import br.org.asserjuf.sisjuf.financeiro.BaixaLancamentoVO;
import br.org.asserjuf.sisjuf.financeiro.ContaVO;
import br.org.asserjuf.sisjuf.financeiro.LancamentoFaturaVO;
import br.org.asserjuf.sisjuf.financeiro.LancamentoFiltroAssembler;
import br.org.asserjuf.sisjuf.financeiro.LancamentoItemFaturaVO;
import br.org.asserjuf.sisjuf.financeiro.LancamentoVO;
import br.org.asserjuf.sisjuf.financeiro.OrigemLancamentoVO;
import br.org.asserjuf.sisjuf.financeiro.TipoLancamentoVO;
import br.org.asserjuf.sisjuf.financeiro.TipoOperacaoVO;
import br.com.falc.smartFW.exception.SmartEnvException;
import br.com.falc.smartFW.persistence.SmartConnection;
import br.com.falc.smartFW.persistence.SmartPreparedStatement;
import br.com.falc.smartFW.persistence.SmartResultSet;

/**
 * Classe de acesso ao banco de dados da entidade "Lan�amento" do Sisjuf.
 * @author Rodrigo Falc�o
 *
 */
public class LancamentoDAO extends SisjufDAOPostgres {
	
	public LancamentoDAO() throws SmartEnvException {
		super(); 

	}

	/**
	 * Insere uma baixa de lan�amento no banco de dados. Tabelas: BAIXA_LANCAMENTO
	 * @param vo Inst�ncia da classe BaixaLancamentoVO (que encapsula a baixa de lan�amento).
	 * @throws SmartEnvException
	 */
	public void baixarLancamento(BaixaLancamentoVO vo) throws SmartEnvException{
		
		StringBuffer sql = new StringBuffer("INSERT INTO baixa_lancamento ");
		sql.append(" (seq_baixa_lancamento, seq_lancamento, dat_baixa_lancamento, val_baixa_lancamento, ");
		sql.append("  seq_forma_pagamento, des_banco_cheque_baixa_lancamento, num_agencia_cheque_baixa_lancamento, ");
		sql.append("dig_agencia_cheque_baixa_lancamento, num_conta_cheque_baixa_lancamento, dig_conta_cheque_baixa_lancamento, num_cheque_baixa_lancamento ) ");
		sql.append("VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
		
		SmartConnection 		sConn 	= null;
		SmartPreparedStatement 	sStmt 	= null;

		try {
			
			sConn 	= new SmartConnection(this.getConn());
			sStmt 	= new SmartPreparedStatement(sConn.prepareStatement(sql.toString()));
			
			vo.setCodigo(new Integer(getSequence("SEQ_BAIXA_LANCAMENTO").intValue()));
			 
			sStmt.setInteger(1, vo.getCodigo());
			sStmt.setInteger(2, vo.getLancamentoVO().getCodigo());
			sStmt.setDate(3, vo.getData());
			sStmt.setDouble(4, vo.getValor());
			sStmt.setInteger(5, vo.getFormaPagamentoVO().getCodigo());
			sStmt.setString(6, vo.getBancoCheque());
			sStmt.setString(7, vo.getAgenciaCheque());
			sStmt.setString(8, vo.getDigitoAgenciaCheque());
			sStmt.setString(9, vo.getContaCheque());
			sStmt.setString(10, vo.getDigitoContaCheque());
			sStmt.setString(11, vo.getNumeroCheque());
						
			sStmt.getMyPreparedStatement().execute();
			
		} catch (SQLException e) {

			throw new SmartEnvException(e);

		}finally {

			sStmt.close();
			sConn.close();
		
		}

	}
	
	/**
	 * Quita o saldo em aberto de um lan�amento previsto. Tabelas: LANCAMENTO
	 * @param vo Inst�ncia de LancamentoVO que encapsula o lan�amento previsto cujo saldo deve ser quitado.
	 * @throws SmartEnvException
	 */
	public void quitarLancamentoPrevisto(LancamentoVO vo) throws SmartEnvException{
		
		StringBuffer sql = new StringBuffer("UPDATE lancamento SET seq_tipo_operacao = ?, dat_efetivacao_lancamento = ? ");
		sql.append("WHERE seq_lancamento = ?");
		
		SmartConnection 		sConn 	= null;
		SmartPreparedStatement 	sStmt 	= null;

		try {
			
			sConn 	= new SmartConnection(this.getConn());
			sStmt 	= new SmartPreparedStatement(sConn.prepareStatement(sql.toString()));
			
			sStmt.setInteger(1, vo.getTipoOperacaoVO()==null?null:vo.getTipoOperacaoVO().getCodigo());
			sStmt.setDate(2, vo.getDataEfetivacao());
			sStmt.setInteger(3, vo.getCodigo());
			
			sStmt.getMyPreparedStatement().execute();
			
		} catch (SQLException e) {

			throw new SmartEnvException(e);

		}finally {

			sStmt.close();
			sConn.close();
		
		}

	}

	/**
	 * Efetua (insere) um lan�amento no banco de dados. Tabelas: LANCAMENTO
	 * @param vo Inst�ncia de LancamentoVO que encapsula o lan�amento que est� sendo realizado.
	 * @return Inst�ncia de LancamentoVO contendo os dados do lan�amento realizado.
	 * @throws SmartEnvException
	 */
	public LancamentoVO efetuarLancamento(LancamentoVO vo) throws SmartEnvException {
		
		StringBuffer sql = new StringBuffer("INSERT INTO lancamento (seq_lancamento, seq_conta, ");
		sql.append("seq_origem_lancamento, seq_tipo_lancamento, seq_tipo_operacao, ");
		sql.append("dat_previsao_lancamento, val_lancamento, des_lancamento) ");
		sql.append("VALUES (?, ?, ?, ?, ?, ?, ?, ?) ");
		
		SmartConnection 		sConn 	= null;
		SmartPreparedStatement 	sStmt 	= null;
		
		try {
		
			sConn 	= new SmartConnection(this.getConn());
			sStmt 	= new SmartPreparedStatement(sConn.prepareStatement(sql.toString()));
			
			vo.setCodigo(new Integer(getSequence("SEQ_LANCAMENTO").intValue()));

			sStmt.setInteger(1, vo.getCodigo());
			sStmt.setInteger(2, vo.getContaVO()==null?null:vo.getContaVO().getCodigo());
			sStmt.setInteger(3, vo.getOrigemLancamentoVO()==null?null:vo.getOrigemLancamentoVO().getCodigo());
			sStmt.setInteger(4, vo.getTipoLancamentoVO()==null?null:vo.getTipoLancamentoVO().getCodigo());
			sStmt.setInteger(5, vo.getTipoOperacaoVO()==null?null:vo.getTipoOperacaoVO().getCodigo());
			sStmt.setDate(6, vo.getDataPrevisao() == null ? null : vo.getDataPrevisao());
			sStmt.setDouble(7, vo.getValor());
			sStmt.setString(8, vo.getDescricao());			
			
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
	 * Estorna (deletando do banco de dados) um lan�amento. Tabelas: BAIXA_LANCAMENTO, LANCAMENTO.
	 * @param vo Inst�ncia de LancamentoVO que encapsula o lan�amento que se deseja estornar.
	 * @throws SmartEnvException
	 */
	public void estornarLancamento(LancamentoVO vo) throws SmartEnvException {
		
		StringBuffer sql = new StringBuffer("DELETE FROM baixa_lancamento WHERE seq_lancamento = ? ");
		
		SmartConnection 		sConn 	= null;
		SmartPreparedStatement 	sStmt 	= null;
		
		try {
		
			sConn 	= new SmartConnection(this.getConn());
			sStmt 	= new SmartPreparedStatement(sConn.prepareStatement(sql.toString()));
			
			sStmt.setInteger(1, vo.getCodigo());
						
			sStmt.getMyPreparedStatement().execute();
			
			sql = new StringBuffer("DELETE FROM lancamento WHERE seq_lancamento = ? ");
			
			sStmt 	= new SmartPreparedStatement(sConn.prepareStatement(sql.toString()));

			sStmt.setInteger(1, vo.getCodigo());

			sStmt.getMyPreparedStatement().execute();
					
		} catch (SQLException e) {

			throw new SmartEnvException(e);

		}finally {

		
			sStmt.close();
			sConn.close();

		}
		
	}

	
	/**
	 * Obt�m a data da primeira baixa de um lan�amento. Tabelas: BAIXA_LANCAMENTO.
	 * @param vo Inst�ncia de LancamentoVO que encapsula o lan�amento cuja data da primeira baixa se deseja obter.
	 * @return Data da primeira baixa do lan�amento passado como par�metro.
	 * @throws SmartEnvException
	 */
	public Date findDataPrimeiraBaixaByLancamento(LancamentoVO vo) throws SmartEnvException {
		
		
//		StringBuffer sql = new StringBuffer("SELECT SEQ_LANCAMENTO, MIN(DAT_BAIXA_LANCAMENTO) FROM BAIXA_LANCAMENTO ")
//			.append("WHERE SEQ_LANCAMENTO = ? GROUP BY SEQ_LANCAMENTO ");
		StringBuffer sql = new StringBuffer("SELECT SEQ_LANCAMENTO, DAT_BAIXA_LANCAMENTO FROM BAIXA_LANCAMENTO ")
		.append("WHERE SEQ_LANCAMENTO = ? ");

		SmartConnection 		sConn 	= null;
		SmartPreparedStatement 	sStmt 	= null;
		SmartResultSet			sRs		= null;
		
		try {
		
			sConn 	= new SmartConnection(this.getConn());
			sStmt 	= new SmartPreparedStatement(sConn.prepareStatement(sql.toString()));

			sStmt.setInteger(1, vo.getCodigo());

			sRs 	= new SmartResultSet(sStmt.getMyPreparedStatement().executeQuery());
			
			if (sRs.next()) {
				Date data = sRs.getDate(1);
				
				return data;

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
	 * Obt�m o saldo em aberto de um lan�amento. Tabelas: LANCAMENTO, BAIXA_LANCAMENTO
	 * @param vo Inst�ncia de LancamentoVO que encapsula o lan�amento cujo saldo se deseja conhecer.
	 * @return Saldo do lan�amento passado como par�metro.
	 * @throws SmartEnvException
	 */
	public Double obterSaldoLancamento(LancamentoVO vo) throws SmartEnvException {
		 
		StringBuffer sql = new StringBuffer("select l.val_lancamento - coalesce(sum(bl.val_baixa_lancamento),0) ")
									.append(" from lancamento l left join baixa_lancamento bl on l.seq_lancamento = bl.seq_lancamento ")
									.append(" where l.seq_lancamento = ? ")
									.append(" group by l.val_lancamento ");

		SmartConnection 		sConn 	= null;
		SmartPreparedStatement 	sStmt 	= null;
		SmartResultSet			sRs		= null;
		
		try {
		
			sConn 	= new SmartConnection(this.getConn());
			sStmt 	= new SmartPreparedStatement(sConn.prepareStatement(sql.toString()));

			sStmt.setInteger(1, vo.getCodigo());

			sRs 	= new SmartResultSet(sStmt.getMyPreparedStatement().executeQuery());
			
			if (sRs.next()) {
				
				Double saldo = sRs.getDouble(1);

				return saldo;
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
	 * Obt�m um lan�amento do banco de dados. Tabelas: LANCAMENTO.
	 * @param vo Inst�ncia de LancamentoVO que cont�m o c�digo do lan�amento que se deseja recuperar.
	 * @return Inst�ncia de LancamentoVO com os dados do lan�amento desejado.
	 * @throws SmartEnvException
	 */
	public LancamentoVO findByPrimaryKey(LancamentoVO vo) throws SmartEnvException {
		 
		StringBuffer sql = new StringBuffer("select seq_lancamento, seq_conta, seq_origem_lancamento, seq_tipo_lancamento, ")
							.append("seq_tipo_operacao, dat_previsao_lancamento, val_lancamento, des_lancamento, dat_efetivacao_lancamento ")
							.append("from lancamento where seq_lancamento = ?");

		SmartConnection 		sConn 	= null;
		SmartPreparedStatement 	sStmt 	= null;
		SmartResultSet			sRs		= null;
		
		try {
		
			sConn 	= new SmartConnection(this.getConn());
			sStmt 	= new SmartPreparedStatement(sConn.prepareStatement(sql.toString()));

			sStmt.setInteger(1, vo.getCodigo());

			sRs 	= new SmartResultSet(sStmt.getMyPreparedStatement().executeQuery());
			
			if (sRs.next()) {
				
				LancamentoVO lancamentoVO = new LancamentoVO();
				
				lancamentoVO.setCodigo(sRs.getInteger(1));
				lancamentoVO.setContaVO(new ContaVO(sRs.getInteger(2)));
				lancamentoVO.setOrigemLancamentoVO(new OrigemLancamentoVO(sRs.getInteger(3)));
				lancamentoVO.setTipoLancamentoVO(new TipoLancamentoVO(sRs.getInteger(4)));
				lancamentoVO.setTipoOperacaoVO(new TipoOperacaoVO(sRs.getInteger(5)));
				lancamentoVO.setDataPrevisao(sRs.getDate(6));
				lancamentoVO.setValor(sRs.getDouble(7));
				lancamentoVO.setDescricao(sRs.getString(8));
				lancamentoVO.setDataEfetivacao(sRs.getDate(9));
								
				return lancamentoVO;

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
	 * Obt�m uma cole��o de lan�amentos a partir de um filtro. Views: VW_CONTA. Tabelas: LANCAMENTO, BAIXA_LANCAMENTO, ORIGEM_LANCAMENTO, TIPO_OPERACAO, TIPO_LANCAMENTO, BANCO.   
	 * @param assembler Inst�ncia da classe LancamentoFiltroAssembler, que encapsula os filtros para a pesquisa de lan�amentos.
	 * @return Cole��o de lan�amentos, encapsulados da classe LancamentoVO.
	 * @throws SmartEnvException
	 */
	public Collection<LancamentoVO> findByFilter(LancamentoFiltroAssembler assembler) throws SmartEnvException {
		
		SmartConnection 		sConn 	= null;
		SmartPreparedStatement 	sStmt 	= null;
		SmartResultSet			sRs		= null;

		StringBuffer sql = new StringBuffer("SELECT l.seq_lancamento, l.dat_efetivacao_lancamento, l.dat_previsao_lancamento, ");
		sql.append("(CASE WHEN c.seq_banco IS NULL THEN c.nom_conta ");
		sql.append("ELSE (b.sig_banco || ' ' || c.num_agencia_conta || ");
		sql.append(" (CASE WHEN (c.dig_agencia_conta IS NULL OR c.dig_agencia_conta = '') THEN ''  ");
		sql.append("ELSE '-' || c.dig_agencia_conta END) || '/' || c.num_conta || '-' || c.dig_conta) END) as conta, ");
		sql.append("(CASE  WHEN ((CAST(ol.seq_origem_lancamento as text)) = (select str_val_parametro from parametros where nom_parametro ='ORIGEM_USUARIO')) THEN tl.nom_tipo_lancamento ELSE ol.nom_origem_lancamento END) as origem_tipo,  ");
		sql.append("l.val_lancamento, COALESCE(sum(bl.val_baixa_lancamento), 0) as valor_efetivado, ");
		sql.append("top.sig_tipo_operacao, l.seq_tipo_operacao, obter_forma_pagamento(l.seq_lancamento), l.des_lancamento ");
		sql.append("FROM lancamento l NATURAL LEFT JOIN baixa_lancamento bl ");
		sql.append("INNER JOIN vw_conta c ON l.seq_conta = c.seq_conta ");
		sql.append("INNER JOIN origem_lancamento ol ON l.seq_origem_lancamento = ol.seq_origem_lancamento ");
		sql.append("INNER JOIN tipo_operacao top ON l.seq_tipo_operacao = top.seq_tipo_operacao ");
		sql.append("LEFT JOIN tipo_lancamento tl ON l.seq_tipo_lancamento = tl.seq_tipo_lancamento ");
		sql.append("LEFT JOIN banco b ON c.seq_banco = b.seq_banco ");
		sql.append(" WHERE 1=1 ");
		
		if (assembler.getContaVO()!=null && assembler.getContaVO().getCodigo()!=null) {
			sql.append("AND (l.seq_conta = ?) ");		
		}
		
		if (assembler.getOrigemLancamentoVO()!=null && assembler.getOrigemLancamentoVO().getCodigo()!= null) {
			sql.append("AND (l.seq_origem_lancamento = ?) ");	
		}
		
		if (assembler.getTipoLancamentoVO()!=null && assembler.getTipoLancamentoVO().getCodigo()!= null) {
			sql.append("AND (l.seq_tipo_lancamento = ?) "); 	
		}
		if (assembler.getTipoOperacaoVO()!=null && assembler.getTipoOperacaoVO().getCodigo()!= null) {
			sql.append("AND (l.seq_tipo_operacao = ?) ");		
		}
		if (assembler.getValorMenor() != null) {
			sql.append("AND (abs(l.val_lancamento) >= ?) ");	
		}
		if (assembler.getValorMaior() != null) {
			sql.append("AND (abs(l.val_lancamento) <= ?) ");	
		}
		
		if (assembler.getFlgEfetivadoOuPrevisto()) {
//			N�o foi marcado nenhum dos checkbox.

			sql.append("AND (((l.dat_previsao_lancamento >= ? ) "); 
			sql.append("AND (l.dat_previsao_lancamento <= ? )) ");	
			sql.append("OR ");
			sql.append("((l.dat_efetivacao_lancamento >= ? ) ");	
			sql.append("AND (l.dat_efetivacao_lancamento <= ? ))) ");
			
		} else if (assembler.getFlgEfetivacao() == null || assembler.getFlgEfetivacao().equals("")) {
//			Foi marcado apenas o checkbox de previs�o

			sql.append("AND ((l.dat_previsao_lancamento >= ? ) "); 
			sql.append("AND (l.dat_previsao_lancamento <= ? )) ");	

		} else if (assembler.getFlgPrevisao() == null || assembler.getFlgPrevisao().equals("")) {
//			Foi marcado apenas o checkbox de efetiva��o

			sql.append("AND ((l.dat_efetivacao_lancamento >= ? ) "); 
			sql.append("AND (l.dat_efetivacao_lancamento <= ? )) ");	
			
		} else {
//			Foi marcado os dois checkbox.

			sql.append("AND (((l.dat_previsao_lancamento >= ? ) "); 
			sql.append("AND (l.dat_previsao_lancamento <= ? )) ");	
			sql.append("AND ");
			sql.append("((l.dat_efetivacao_lancamento >= ? ) ");	
			sql.append("AND (l.dat_efetivacao_lancamento <= ? ))) ");
			
		}
		
//		if (assembler.getDataPrevisaoInicial() != null) {
//			sql.append("AND (((l.dat_previsao_lancamento >= ? ) "); 
//		}
//		
//		if (assembler.getDataPrevisaoFinal() != null) {
//			sql.append("AND (l.dat_previsao_lancamento <= ? )) ");	
//		}
//		if (assembler.getFlgEfetivadoOuPrevisto()) {
//			sql.append("OR ");
//		} 
//		else {
//			sql.append("AND ");
//		}
//		
//		if (assembler.getDataEfetivacaoInicial() != null) {
//			sql.append("((l.dat_efetivacao_lancamento >= ? ) ");	
//		}
//		
//		if (assembler.getDataEfetivacaoFinal() != null ) {
//			sql.append("AND (l.dat_efetivacao_lancamento <= ? ))) ");
//		}
		
		if (!(assembler.getFlgNaoQuitado() == null || assembler.getFlgNaoQuitado().equals(""))) {
			sql.append("AND (l.dat_efetivacao_lancamento IS NULL) ");
		}
		
		sql.append("GROUP BY l.seq_lancamento, l.dat_efetivacao_lancamento, l.dat_previsao_lancamento, ");
		sql.append("c.seq_banco, c.num_agencia_conta, c.dig_agencia_conta, c.nom_conta, ");
		sql.append("c.num_conta, c.dig_conta, ol.seq_origem_lancamento, l.val_lancamento, ");
		sql.append("tl.nom_tipo_lancamento, ol.nom_origem_lancamento, b.sig_banco, top.sig_tipo_operacao, "); 
		sql.append("l.seq_tipo_operacao, obter_forma_pagamento(l.seq_lancamento),l.des_lancamento ");
		sql.append(" ORDER BY l.seq_lancamento ");
		
		try {
			
			sConn 	= new SmartConnection(this.getConn());
			sStmt 	= new SmartPreparedStatement(sConn.prepareStatement(sql.toString()));
			
			int indice = 1;
		
			
			if (assembler.getContaVO()!=null && assembler.getContaVO().getCodigo()!=null) {
				sStmt.setInteger(indice++, assembler.getContaVO()==null?null:assembler.getContaVO().getCodigo());
			}
			if (assembler.getOrigemLancamentoVO()!=null && assembler.getOrigemLancamentoVO().getCodigo()!= null) {
				sStmt.setInteger(indice++, assembler.getOrigemLancamentoVO().getCodigo());
			}
			if (assembler.getTipoLancamentoVO()!=null && assembler.getTipoLancamentoVO().getCodigo()!= null) {
				sStmt.setInteger(indice++, assembler.getTipoLancamentoVO().getCodigo());	
			}
			if (assembler.getTipoOperacaoVO()!=null && assembler.getTipoOperacaoVO().getCodigo()!= null) {
				sStmt.setInteger(indice++, assembler.getTipoOperacaoVO()==null?null:assembler.getTipoOperacaoVO().getCodigo());
			}
			if (assembler.getValorMenor() != null) {
				sStmt.setDouble(indice++, assembler.getValorMenor());
			}
			if (assembler.getValorMaior() != null) {
				sStmt.setDouble(indice++, assembler.getValorMaior());
			}
			if (assembler.getDataPrevisaoInicial() != null) {
				sStmt.setDate(indice++, assembler.getDataPrevisaoInicial());
			}
			if (assembler.getDataPrevisaoFinal() != null) {
				sStmt.setDate(indice++, assembler.getDataPrevisaoFinal());
			}
			if (assembler.getDataEfetivacaoInicial() != null) {
				sStmt.setDate(indice++, assembler.getDataEfetivacaoInicial());
			}
			if (assembler.getDataEfetivacaoFinal() != null ) {
				sStmt.setDate(indice++, assembler.getDataEfetivacaoFinal());
			}
			
			sRs 	= new SmartResultSet(sStmt.getMyPreparedStatement().executeQuery());
			
			ArrayList<LancamentoVO> retorno = new ArrayList<LancamentoVO>(); 
			
			while (sRs.next()) {

				LancamentoVO lancamentoVO = new LancamentoVO();
				lancamentoVO.setCodigo(sRs.getInteger(1));
				lancamentoVO.setDataEfetivacao(sRs.getDate(2));
				lancamentoVO.setDataPrevisao(sRs.getDate(3));
				lancamentoVO.setContaVO(new ContaVO());
				lancamentoVO.getContaVO().setNome(sRs.getString(4));
				lancamentoVO.setOrigemLancamentoVO(new OrigemLancamentoVO());
				lancamentoVO.getOrigemLancamentoVO().setNome(sRs.getString(5));
				lancamentoVO.setValor(sRs.getDouble(6));
				lancamentoVO.setValorPago(sRs.getDouble(7));
				lancamentoVO.setTipoOperacaoVO(new TipoOperacaoVO(sRs.getInteger(9)));
				lancamentoVO.getTipoOperacaoVO().setSigla(sRs.getString(8));
				lancamentoVO.setDescricaoCompletaFormaPagamento(sRs.getString(10));
				lancamentoVO.setDescricao(sRs.getString(11));
				
				retorno.add(lancamentoVO);

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

	public void insert(LancamentoFaturaVO lancamentoFatura) throws SmartEnvException {
		StringBuffer sql = new StringBuffer("INSERT INTO LANCAMENTO_FATURA (SEQ_LANCAMENTO, SEQ_FATURA)  ");
								sql.append(" VALUES (?, ?) ");
		
		SmartConnection 		sConn 	= null;
		SmartPreparedStatement 	sStmt 	= null;
		
		try {
		
			sConn 	= new SmartConnection(this.getConn());
			sStmt 	= new SmartPreparedStatement(sConn.prepareStatement(sql.toString()));
			
			sStmt.setParameters(lancamentoFatura, new String[] {"codigo", "fatura.codigo"});
			
			sStmt.getMyPreparedStatement().execute();
				
		} catch (SQLException e) {

			throw new SmartEnvException(e);

		}finally {

		
			sStmt.close();
			sConn.close();

		}
		
	}

	public void remove(LancamentoFaturaVO lancamentoFatura) throws SmartEnvException {
		
		StringBuffer sql = new StringBuffer("DELETE FROM LANCAMENTO_FATURA WHERE SEQ_FATURA = ? ");

		SmartConnection 		sConn 	= null;
		SmartPreparedStatement 	sStmt 	= null;
		
		try {
		
		sConn 	= new SmartConnection(this.getConn());
		sStmt 	= new SmartPreparedStatement(sConn.prepareStatement(sql.toString()));
		
		sStmt.setParameters(lancamentoFatura, new String[] {"fatura.codigo"});
		
		sStmt.getMyPreparedStatement().execute();
		
		} catch (SQLException e) {
		
			throw new SmartEnvException(e);
		
		} finally {
			
			sStmt.close();
			sConn.close();
			
		}

		
	}
	
	public void insert(LancamentoItemFaturaVO lancamentoItemFatura) throws SmartEnvException {
		StringBuffer sql = new StringBuffer("INSERT INTO LANCAMENTO_ITEM_FATURA (SEQ_LANCAMENTO, SEQ_ITEM_FATURA)  ");
								sql.append(" VALUES (?, ?) ");
		
		SmartConnection 		sConn 	= null;
		SmartPreparedStatement 	sStmt 	= null;
		
		try {
		
			sConn 	= new SmartConnection(this.getConn());
			sStmt 	= new SmartPreparedStatement(sConn.prepareStatement(sql.toString()));
			
			sStmt.setParameters(lancamentoItemFatura, new String[] {"codigo", "itemFatura.codigo"});
			
			sStmt.getMyPreparedStatement().execute();
				
		} catch (SQLException e) {

			throw new SmartEnvException(e);

		} finally {

		
			sStmt.close();
			sConn.close();

		}
		
	}

	public void remove(LancamentoItemFaturaVO lancamentoItemFatura) throws SmartEnvException {
		
		StringBuffer sql = new StringBuffer("DELETE FROM LANCAMENTO_ITEM_FATURA WHERE SEQ_ITEM_FATURA = ? ");

		SmartConnection 		sConn 	= null;
		SmartPreparedStatement 	sStmt 	= null;
		
		try {
		
		sConn 	= new SmartConnection(this.getConn());
		sStmt 	= new SmartPreparedStatement(sConn.prepareStatement(sql.toString()));
		
		sStmt.setParameters(lancamentoItemFatura, new String[] {"itemFatura.codigo"});
		
		sStmt.getMyPreparedStatement().execute();
		
		} catch (SQLException e) {
		
			throw new SmartEnvException(e);
		
		} finally {
			
			sStmt.close();
			sConn.close();
			
		}

		
	}
	
	
	/**
	 * Obt�m uma cole��o de lan�amentos a partir de uma fatura. Tabelas: LANCAMENTO, LANCAMENTO_FATURA, LANCAMENTO_ITEM_FATURA   
	 * @param fatura Inst�ncia da classe FaturaVO, que encapsula uma fatura.
	 * @return Cole��o de lan�amentos, encapsulados da classe LancamentoVO.
	 * @throws SmartEnvException
	 */
	public Collection<LancamentoFaturaVO> findByFatura(FaturaVO fatura) throws SmartEnvException {
		
		SmartConnection 		sConn 	= null;
		SmartPreparedStatement 	sStmt 	= null;
		SmartResultSet			sRs		= null;

		StringBuffer sql = new StringBuffer(" SELECT L.SEQ_LANCAMENTO, L.SEQ_TIPO_OPERACAO ")
									.append(" FROM LANCAMENTO L, LANCAMENTO_ITEM_FATURA LIF, ITEM_FATURA IFAT ")
									.append(" WHERE L.SEQ_LANCAMENTO = LIF.SEQ_LANCAMENTO ")
									.append(" AND LIF.SEQ_ITEM_FATURA = IFAT.SEQ_ITEM_FATURA ")
									.append(" AND IFAT.SEQ_FATURA = ? ")
									.append(" UNION ALL ")
									.append(" SELECT L.SEQ_LANCAMENTO, L.SEQ_TIPO_OPERACAO ")
									.append(" FROM LANCAMENTO L, LANCAMENTO_FATURA LF ")
									.append(" WHERE L.SEQ_LANCAMENTO = LF.SEQ_LANCAMENTO ")
									.append(" AND LF.SEQ_FATURA = ? ");
										
		try {
			
			sConn 	= new SmartConnection(this.getConn());
			sStmt 	= new SmartPreparedStatement(sConn.prepareStatement(sql.toString()));
			
			sStmt.setParameters(fatura, new String[] {"codigo", "codigo"});
			
			sRs 	= new SmartResultSet(sStmt.getMyPreparedStatement().executeQuery());
			
			return sRs.getJavaBeans(LancamentoFaturaVO.class, new String[] {"codigo","tipoOperacaoVO.codigo"});
			
		
		} catch (SQLException e) {
			throw new SmartEnvException(e);

		} finally {

			sRs.close();
			sStmt.close();
			sConn.close();

		}
		
	}
	
	
	
	/**
	 * Obt�m a cole��o de lan�amentos duplicados (registros inconsistentes) da base. Tabelas: LANCAMENTO, BAIXA_LANCAMENTO   
	 * @return Cole��o de lan�amentos, encapsulados da classe LancamentoVO.
	 * @throws SmartEnvException
	 */
	public Collection<LancamentoVO> findDuplicadosInconsistentes() throws SmartEnvException {
		
		SmartConnection 		sConn 	= null;
		SmartPreparedStatement 	sStmt 	= null;
		SmartResultSet			sRs		= null;

		StringBuffer sql = new StringBuffer(" SELECT L.SEQ_LANCAMENTO, L.SEQ_CONTA ")
									.append(" from lancamento l, baixa_lancamento bl where l.seq_lancamento = bl.seq_lancamento and dat_efetivacao_lancamento is null ");
										
		try {
			
			sConn 	= new SmartConnection(this.getConn());
			sStmt 	= new SmartPreparedStatement(sConn.prepareStatement(sql.toString()));
			
			sRs 	= new SmartResultSet(sStmt.getMyPreparedStatement().executeQuery());
			
			return sRs.getJavaBeans(LancamentoVO.class, new String[] {"codigo", "contaVO.codigo"});
			
		
		} catch (SQLException e) {
			throw new SmartEnvException(e);

		} finally {

			sRs.close();
			sStmt.close();
			sConn.close();

		}
		
	}
	
	
}