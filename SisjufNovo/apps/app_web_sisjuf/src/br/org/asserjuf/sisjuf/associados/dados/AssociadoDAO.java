package br.org.asserjuf.sisjuf.associados.dados;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import br.com.falc.smartFW.exception.SmartEnvException;
import br.com.falc.smartFW.persistence.SmartConnection;
import br.com.falc.smartFW.persistence.SmartPreparedStatement;
import br.com.falc.smartFW.persistence.SmartResultSet;
import br.org.asserjuf.sisjuf.associados.AssociadoAssembler;
import br.org.asserjuf.sisjuf.associados.AssociadoFiltroAssembler;
import br.org.asserjuf.sisjuf.associados.AssociadoImportacaoNucreVO;
import br.org.asserjuf.sisjuf.associados.AssociadoVO;
import br.org.asserjuf.sisjuf.associados.InconsistenciaNucreVO;
import br.org.asserjuf.sisjuf.associados.ItemPlanilhaNucreVO;
import br.org.asserjuf.sisjuf.associados.RelatorioAssociadosDependentesVO;
import br.org.asserjuf.sisjuf.associados.RelatorioIRVO;
import br.org.asserjuf.sisjuf.associados.convenio.BeneficiarioIRVO;
import br.org.asserjuf.sisjuf.associados.convenio.BeneficiarioVO;
import br.org.asserjuf.sisjuf.associados.convenio.ItemFaturaInconsistenteVO;
import br.org.asserjuf.sisjuf.dados.SisjufDAOPostgres;
import br.org.asserjuf.sisjuf.financeiro.LancamentoAssociadoVO;

public class AssociadoDAO extends SisjufDAOPostgres {

	public Collection<AssociadoVO> findProximosAniversariantes() throws SmartEnvException {
		
		StringBuffer sql = new StringBuffer("select a.seq_associado, a.nom_associado, a.dat_nascimento_associado, proximo_aniversario(a.dat_nascimento_associado), ")
									.append("	(proximo_aniversario(a.dat_nascimento_associado) - a.dat_nascimento_associado)/365 as num_idade_associado ")
									.append(" from vw_associado a ")
									.append(" where dat_exclusao_associado is null and proximo_aniversario(a.dat_nascimento_associado) <= CURRENT_DATE + ")
									.append(" (SELECT INT2(STR_VAL_PARAMETRO) FROM PARAMETROS WHERE NOM_PARAMETRO = 'NUM_DIAS_PROX_ANIVER') ")
									.append(" and ((select h2.seq_tipo_evento from historico_evento_associado h2 	where h2.seq_associado = a.seq_associado	order by h2.seq_historico_evento_associado desc limit 1) <>  (select int2(str_val_parametro) from parametros where nom_parametro = 'TP_EVT_CANCELAMENTO')) ")
									.append(" order by proximo_aniversario(a.dat_nascimento_associado) ");

		SmartConnection 		sConn 	= null;
		SmartPreparedStatement 	sStmt 	= null;
		SmartResultSet			sRs		= null;
		
		try {
		
			sConn 	= new SmartConnection(this.getConn());
			sStmt 	= new SmartPreparedStatement(sConn.prepareStatement(sql.toString()));
			sRs 	= new SmartResultSet(sStmt.getMyPreparedStatement().executeQuery());
			
			ArrayList<AssociadoVO> retorno = new ArrayList<AssociadoVO>(); 
			
			while (sRs.next()) {

				AssociadoVO associado = new AssociadoVO();

				associado.setCodigo(sRs.getInteger(1));
				associado.setNome(sRs.getString(2));
				associado.setDataNascimento(sRs.getDate(3));
				associado.setProximoAniversario(sRs.getDate(4));
				associado.setIdade(sRs.getShort(5));

				retorno.add(associado);

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

	
	public AssociadoVO findByPrimaryKey(AssociadoVO associado) throws SmartEnvException {
		
		StringBuffer sql = new StringBuffer(" select a.seq_associado, a.nom_associado, a.dat_nascimento_associado, a.sts_sexo_associado,  ")
		.append(" a.num_cpf_associado, a.num_rg_associado, a.des_logradouro_associado, a.des_numero_endereco_associado, a.des_complemento_endereco_associado, ")
		.append(" a.des_bairro_associado, a.num_cep_associado, a.des_municipio_endereco_associado, ")
		.append(" a.seq_estado_endereco_associado, a.num_telefone_residencial_associado, a.num_telefone_comercial_associado, ")
		.append(" a.num_telefone_celular_associado, a.des_email_associado, a.des_naturalidade_associado, a.seq_estado_naturalidade_associado, ")
		.append(" a.sts_estado_civil_associado, a.des_profissao_associado, a.des_grupo_sanguineo_associado, a.sts_fator_rh_associado, ")
		.append(" a.nom_pai_associado, a.nom_mae_associado, ")
		.append(" c.nom_conjuge, ")
		.append(" c.seq_conjuge, ")
		.append(" c.dat_nascimento_conjuge, ")
		.append(" c.sts_sexo_conjuge, ")
		.append(" c.num_rg_conjuge, ")
		.append(" c.num_cpf_conjuge, ") 
		.append(" a.num_calcado_associado, a.sts_situacao_justica_associado,  ")
		.append(" a.nom_orgao_associado, a.des_endereco_setor_associado, a.nom_setor_associado, a.seq_estado_setor_associado, ")
		.append(" a.nom_municipio_setor_associado, a.num_telefone_setor_associado, a.num_ramal_setor_associado, a.num_matricula_justica_associado, ")
		.append(" a.sts_recebe_jornal_associado, a.sts_categoria_associado, a.seq_contribuinte_associado, co.nom_pessoa, a.seq_banco, a.num_agencia_associado, ")
		.append(" a.dig_agencia_associado, a.num_conta_associado, a.dig_conta_associado, h.dat_historico_evento_associado, a.seq_tipo_evento, a.nom_tipo_evento, a.dat_historico_evento_associado as data_ultimo_evento, a.nome_usuario_cadastro, a.dat_cadastro, a.nome_usuario_alteracao, a.dat_alteracao ")
		.append(" from vw_associado a inner join historico_evento_associado h on a.seq_associado = h.seq_associado ")
		.append(" left join vw_conjuge c on c.seq_associado = a.seq_associado ")
		.append(" left join pessoa co on co.seq_pessoa = a.seq_contribuinte_associado ")
		.append(" where h.seq_historico_evento_associado = (select max(h2.seq_historico_evento_associado) from historico_evento_associado h2 where h2.seq_tipo_evento in (1,3) and h2.seq_associado = h.seq_associado) and a.seq_associado = ? ");

		SmartConnection 		sConn 	= null;
		SmartPreparedStatement 	sStmt 	= null;
		SmartResultSet			sRs		= null;
		
		try {
		
			sConn 	= new SmartConnection(this.getConn());
			sStmt 	= new SmartPreparedStatement(sConn.prepareStatement(sql.toString()));
			
			sStmt.setInteger(1, associado.getCodigo());
			
			sRs 	= new SmartResultSet(sStmt.getMyPreparedStatement().executeQuery());
			
			return (AssociadoVO) sRs.getJavaBean(associado, new String[] {"codigo", "nome", "dataNascimento", "sexo", "cpf", "rg", "endereco.logradouro", 
					"endereco.numero", "endereco.complemento","endereco.bairro","endereco.cep", "endereco.municipio.nome",
					"endereco.municipio.estado.codigo", "telefoneResidencial", "telefoneComercial", "telefoneCelular", "email", "naturalidade.nome", 
					"naturalidade.estado.codigo", "estadoCivil", "profissao.nome", "grupoSanguineo", "fatorRh", "nomePai", "nomeMae",
					"conjuge.nome",
					"conjuge.codigo",
					"conjuge.dataNascimento",
					"conjuge.sexo",
					"conjuge.rg",
					"conjuge.cpf",
					"numeroCalcado", "statusJustica", "setor.orgao.nome","setor.endereco.logradouro", "setor.nome", 
					"setor.endereco.municipio.estado.codigo", "setor.endereco.municipio.nome", "setor.telefone", "setor.ramal", "matriculaJustica",
					"statusRecebeJornal", "statusCategoria", "contribuinte.codigo", "contribuinte.nome", "conta.bancoVO.codigo", "conta.numAgencia", "conta.digAgencia", 
					"conta.numConta", "conta.digConta", "dataAssociacao", "ultimoEvento.tipoEvento.codigo", "ultimoEvento.tipoEvento.nome", "ultimoEvento.data",
					"usuario.nome", "dataCadastro", "usuarioAlteracao.nome", "dataAlteracao"} );
		
		} catch (SQLException e) {
			throw new SmartEnvException(e);

		} finally {

			sRs.close();
			sStmt.close();
			sConn.close();

		}
		
	}
	
	public void insertValorSocioUsuario(Float valor) throws SmartEnvException {
		
		StringBuffer sql = new StringBuffer(" insert into valor_mensalidade ")
		.append(" (seq_valor_mensalidade, dat_cadastro_valor_mensalidade, val_mensalidade) ")
		.append(" VALUES (nextval('seq_valor_mensalidade'), CURRENT_DATE, ?) ");

		SmartConnection 		sConn 	= null;
		SmartPreparedStatement 	sStmt 	= null;
				
		try {
		
			sConn 	= new SmartConnection(this.getConn());
			sStmt 	= new SmartPreparedStatement(sConn.prepareStatement(sql.toString()));
			
			sStmt.setFloat(1, valor);

			sStmt.getMyPreparedStatement().execute();
			
		
		} catch (SQLException e) {
			throw new SmartEnvException(e);

		} finally {

			sStmt.close();
			sConn.close();

		}
		
	}
	
	public void remove(AssociadoVO associado) throws SmartEnvException {
		
		
		StringBuffer sql = new StringBuffer(" UPDATE ASSOCIADO SET DAT_EXCLUSAO_ASSOCIADO = CURRENT_DATE WHERE SEQ_PESSOA = ?");

		SmartConnection 		sConn 	= null;
		SmartPreparedStatement 	sStmt 	= null;
				
		try {
		
			sConn 	= new SmartConnection(this.getConn());
			sStmt 	= new SmartPreparedStatement(sConn.prepareStatement(sql.toString()));

			sStmt.setInteger(1, associado.getCodigo());
			
			sStmt.getMyPreparedStatement().execute();
			
		
		} catch (SQLException e) {
			throw new SmartEnvException(e);

		} finally {

			sStmt.close();
			sConn.close();

		}
		
	}

	public void update(AssociadoVO associado) throws SmartEnvException {
		
		StringBuffer sql = new StringBuffer("update associado ")
		.append(" set des_logradouro_associado = ?, des_numero_endereco_associado = ?, ")
		.append(" des_complemento_endereco_associado = ?, des_bairro_associado = ?, num_cep_associado = ?, des_municipio_endereco_associado = ?, ")
		.append(" seq_estado_endereco_associado = ?, num_telefone_residencial_associado = ?, num_telefone_comercial_associado = ?, ")
		.append(" num_telefone_celular_associado = ?, des_email_associado = ?, des_naturalidade_associado = ?, seq_estado_naturalidade_associado = ?, ")
		.append(" sts_estado_civil_associado = ?, des_profissao_associado = ?, des_grupo_sanguineo_associado = ?, sts_fator_rh_associado = ?, ")
		.append(" nom_pai_associado = ?, nom_mae_associado = ?, num_calcado_associado = ?, sts_situacao_justica_associado = ?, ")
		.append(" nom_orgao_associado = ?, des_endereco_setor_associado = ?, nom_setor_associado = ?, seq_estado_setor_associado = ?, ")
		.append(" nom_municipio_setor_associado = ?, num_telefone_setor_associado = ?, num_ramal_setor_associado = ?, num_matricula_justica_associado = ?, ")
		.append(" sts_recebe_jornal_associado = ?, sts_categoria_associado = ?, seq_contribuinte_associado = ?, seq_banco = ?, num_agencia_associado = ?, ")
		.append(" dig_agencia_associado = ?, num_conta_associado = ?, dig_conta_associado = ?, num_rg_associado = ?, num_cpf_associado = ?, seq_usuario_alteracao=?, dat_alteracao=now() ")
		.append(" where seq_pessoa = ? ");

		SmartConnection 		sConn 	= null;
		SmartPreparedStatement 	sStmt 	= null;
				
		try {
		
			sConn 	= new SmartConnection(this.getConn());
			sStmt 	= new SmartPreparedStatement(sConn.prepareStatement(sql.toString()));

			sStmt.setParameters(associado, new String[] {"endereco.logradouro", 
														"endereco.numero", 
														"endereco.complemento", 
														"endereco.bairro",
														"endereco.cep", 
														"endereco.municipio.nome",	
														"endereco.municipio.estado.codigo", 
														"telefoneResidencial", 
														"telefoneComercial", 
														"telefoneCelular", 
														"email", 
														"naturalidade.nome", 
														"naturalidade.estado.codigo", 
														"estadoCivil", 
														"profissao.nome", 
														"grupoSanguineo", 
														"fatorRh", 
														"nomePai", 
														"nomeMae",
														"numeroCalcado", 
														"statusJustica", 
														"setor.orgao.nome", 
														"setor.endereco.logradouro", 
														"setor.nome", 
														"setor.endereco.municipio.estado.codigo", 
														"setor.endereco.municipio.nome", 
														"setor.telefone", 
														"setor.ramal", 
														"matriculaJustica",
														"statusRecebeJornal", 
														"statusCategoria", 
														"contribuinte.codigo", 
														"conta.bancoVO.codigo", 
														"conta.numAgencia", 
														"conta.digAgencia", 
														"conta.numConta", 
														"conta.digConta", 
														"rg", 
														"cpf",
														"usuario.codigo",
														"codigo"});
			
			sStmt.getMyPreparedStatement().execute();
			
		} catch (SQLException e) {
			throw new SmartEnvException(e);

		} finally {

			sStmt.close();
			sConn.close();

		}
		
	}

	public AssociadoVO insert(AssociadoAssembler associado) throws SmartEnvException {
		
		StringBuffer sql = new StringBuffer(" insert into associado ")
		.append(" (seq_pessoa, des_logradouro_associado, des_numero_endereco_associado, ")
		.append(" des_complemento_endereco_associado, des_bairro_associado, num_cep_associado, des_municipio_endereco_associado, ")
		.append(" seq_estado_endereco_associado, num_telefone_residencial_associado, num_telefone_comercial_associado, ")
		.append(" num_telefone_celular_associado, des_email_associado, des_naturalidade_associado, seq_estado_naturalidade_associado, ")
		.append(" sts_estado_civil_associado, des_profissao_associado, des_grupo_sanguineo_associado, sts_fator_rh_associado, ")
		.append(" nom_pai_associado, nom_mae_associado, num_calcado_associado, sts_situacao_justica_associado, ")
		.append(" nom_orgao_associado, des_endereco_setor_associado, nom_setor_associado, seq_estado_setor_associado, ")
		.append(" nom_municipio_setor_associado, num_telefone_setor_associado, num_ramal_setor_associado, num_matricula_justica_associado, ")
		.append(" sts_recebe_jornal_associado, sts_categoria_associado, seq_contribuinte_associado, seq_banco, num_agencia_associado, ")
		.append(" dig_agencia_associado, num_conta_associado, dig_conta_associado, num_cpf_associado, num_rg_associado,sts_pre_cadastro_associado, seq_usuario_cadastro, seq_usuario_alteracao) ")
		.append(" values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ");

		SmartConnection 		sConn 	= null;
		SmartPreparedStatement 	sStmt 	= null;
				
		try {
		
			sConn 	= new SmartConnection(this.getConn());
			sStmt 	= new SmartPreparedStatement(sConn.prepareStatement(sql.toString()));

			sStmt.setParameters(associado, new String[] { 	"codigo", 
															"endereco.logradouro", 
															"endereco.numero", 
															"endereco.complemento", 
															"endereco.bairro",
															"endereco.cep",
															"endereco.municipio.nome",	
															"endereco.municipio.estado.codigo", 
															"telefoneResidencial", 
															"telefoneComercial", 
															"telefoneCelular", 
															"email", 
															"naturalidade.nome",
															"naturalidade.estado.codigo", 
															"estadoCivil", 
															"profissao.nome", 
															"grupoSanguineo", 
															"fatorRh", 
															"nomePai", 
															"nomeMae",
															"numeroCalcado", 
															"statusJustica", 
															"setor.orgao.nome", 
															"setor.endereco.logradouro", 
															"setor.nome", 
															"setor.endereco.municipio.estado.codigo", 
															"setor.endereco.municipio.nome", 
															"setor.telefone", 
															"setor.ramal", 
															"matriculaJustica",
															"statusRecebeJornal", 
															"statusCategoria", 
															"contribuinte.codigo", 
															"conta.bancoVO.codigo", 
															"conta.numAgencia", 
															"conta.digAgencia", 
															"conta.numConta", 
															"conta.digConta",
															"cpf", 
															"rg",
															"statusPreCadastro",
															"usuario.codigo",
															"usuario.codigo"
															});
			
			sStmt.getMyPreparedStatement().execute();
			
			return associado;
		
		} catch (SQLException e) {
			throw new SmartEnvException(e);

		} finally {

			sStmt.close();
			sConn.close();

		}
		
	}
	
	public Float findValorAtualSocioUsuario() throws SmartEnvException {
		
		StringBuffer sql = new StringBuffer(" SELECT VM.VAL_MENSALIDADE FROM VALOR_MENSALIDADE VM ")
									.append(" WHERE NOT EXISTS (SELECT VM2.SEQ_VALOR_MENSALIDADE FROM VALOR_MENSALIDADE VM2 WHERE VM2.SEQ_VALOR_MENSALIDADE > VM.SEQ_VALOR_MENSALIDADE) ");

		SmartConnection 		sConn 	= null;
		SmartPreparedStatement 	sStmt 	= null;
		SmartResultSet			sRs		= null;
		
		try {
		
			sConn 	= new SmartConnection(this.getConn());
			sStmt 	= new SmartPreparedStatement(sConn.prepareStatement(sql.toString()));
			sRs 	= new SmartResultSet(sStmt.getMyPreparedStatement().executeQuery());
			
			while (sRs.next()) {

				return sRs.getFloat(1);
				
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
	
	public Collection<AssociadoVO> findAll() throws SmartEnvException {
		
		StringBuffer sql = new StringBuffer(" SELECT A.SEQ_ASSOCIADO, A.NOM_ASSOCIADO, A.DES_EMAIL_ASSOCIADO FROM VW_ASSOCIADO A WHERE DAT_EXCLUSAO_ASSOCIADO IS NULL ");

		SmartConnection 		sConn 	= null;
		SmartPreparedStatement 	sStmt 	= null;
		SmartResultSet			sRs		= null;
		
		try {
		
			sConn 	= new SmartConnection(this.getConn());
			sStmt 	= new SmartPreparedStatement(sConn.prepareStatement(sql.toString()));
			sRs 	= new SmartResultSet(sStmt.getMyPreparedStatement().executeQuery());
			
			return sRs.getJavaBeans(AssociadoVO.class, new String[] {"codigo", "nome", "email"});
			
		} catch (SQLException e) {
			throw new SmartEnvException(e);

		} finally {

			sRs.close();
			sStmt.close();
			sConn.close();
		}
	}
	
	public Collection<AssociadoVO> findByFilter(AssociadoFiltroAssembler assembler) throws SmartEnvException {
		
		SmartConnection 		sConn 	= null;
		SmartPreparedStatement 	sStmt 	= null;
		SmartResultSet			sRs		= null;

		StringBuffer sql = new StringBuffer("select a.seq_associado, ")
		.append("case a.sts_pre_cadastro_associado when 'S' then a.nom_associado || '(pré-cadastro)' else a.nom_associado end as nom_associado, ")
		.append("a.nom_orgao_associado, a.nom_setor_associado, a.des_email_associado, a.dat_historico_evento_associado, a.sts_pre_cadastro_associado ")
		//.append("from vw_associado a ")
		//.append("natural join historico_evento_associado e ")
		.append(" from vw_associado a inner join historico_evento_associado e on a.seq_associado = e.seq_associado ")
		.append("left join vw_associado a2 on a.seq_contribuinte_associado = a2.seq_associado ")
		.append("left join vw_dependente d on d.seq_associado = a.seq_associado ")
		.append("left join vw_filho f on f.seq_associado = a.seq_associado ")
		.append("inner join historico_evento_associado e2 on a.seq_associado = e2.seq_associado ");
		
		/*
		if ((assembler.getStatusPossuiFilho()!=null && !assembler.getStatusPossuiFilho().equals("")) || (assembler.getFilho()!=null && ((assembler.getFilho().getNome()!=null && !assembler.getFilho().getNome().equals("")) ||  (assembler.getFilho().getSexo()!=null && !assembler.getFilho().getSexo().equals("")))) || (assembler.getDataInicioNascimentoFilho()!=null && !assembler.getDataInicioNascimentoFilho().equals("")) || (assembler.getDataFimNascimentoFilho()!=null && !assembler.getDataFimNascimentoFilho().equals(""))) {
			sql.append("inner join vw_filho f on f.seq_filho = a.seq_associado ");		
		}

		if ((assembler.getStatusPossuiDependente()!=null && !assembler.getStatusPossuiDependente().equals("")) || (assembler.getDependente()!=null && ((assembler.getDependente().getNome()!=null && !assembler.getDependente().getNome().equals("")) || (assembler.getDependente().getParentesco()!=null && !assembler.getDependente().getParentesco().equals("")) || (assembler.getDependente().getRg()!=null && !assembler.getDependente().getRg().equals("")) || (assembler.getDependente().getSexo()!=null && !assembler.getDependente().getSexo().equals("")))) || (assembler.getDataInicioNascimentoDependente()!=null && !assembler.getDataInicioNascimentoDependente().equals("")) || (assembler.getDataFimNascimentoDependente()!=null && !assembler.getDataFimNascimentoDependente().equals(""))) {
			sql.append("inner join vw_dependente d on d.seq_dependente = a.seq_associado ");		
		}
		*/
		sql.append("where e.seq_tipo_evento = (select int2(str_val_parametro) from parametros where nom_parametro = 'TP_EVT_CADASTRO')  ");
		
		sql.append("and (a.seq_associado = ? or ? is null) ");	
		
		sql.append("and (upper(a.nom_associado) like '%' || upper(?) || '%' or ? is null) "); 	

		sql.append("and (a.sts_sexo_associado = ?  or ? is null or ? = '')  ");
		sql.append("and (a.dat_nascimento_associado >= ? or ? is null) ");
		sql.append("and (a.dat_nascimento_associado <= ? or ? is null) ");
		sql.append("and (? is null or e.dat_historico_evento_associado >= ?) ");
		sql.append("and (? is null or e.dat_historico_evento_associado <= ? ) ");
		sql.append("and (a.num_rg_associado = ? or ? is null) ");
		sql.append("and (a.num_cpf_associado = ? or ? is null) ");
		sql.append("and (upper(a.des_logradouro_associado) like '%' || upper(?) || '%' or ? is null) ");
		sql.append("and (upper(a.des_numero_endereco_associado) like '%' || upper(?) || '%' or ? is null) ");
		sql.append("and (upper(a.des_complemento_endereco_associado) like '%' || upper(?) || '%' or ? is null) ");
		sql.append("and (upper(a.des_bairro_associado) like '%' || upper(?) || '%' or ? is null) ");
		sql.append("and (a.num_cep_associado = ? or ? is null) ");
		sql.append("and (a.seq_estado_endereco_associado = ?  or ? is null) ");
		sql.append("and (upper(a.des_municipio_endereco_associado) like '%' || upper(?) || '%' or ? is null) ");
		sql.append("and (a.num_telefone_residencial_associado = ? or ? is null) ");
		sql.append("and (a.num_telefone_comercial_associado = ? or ? is null) ");
		sql.append("and (a.num_telefone_celular_associado = ? or ? is null) ");
		sql.append("and (upper(a.des_email_associado) like '%' || upper(?) || '%' or ? is null) ");
		sql.append("and (a.seq_estado_naturalidade_associado = ?  or ? is null) ");
		sql.append("and (upper(a.des_naturalidade_associado) like '%' || upper(?) || '%' or ? is null) ");
		sql.append("and (a.sts_estado_civil_associado = ? or ? is null or ? = '')  ");
		sql.append("and (upper(a.des_profissao_associado) like '%' || upper(?) || '%' or ? is null) ");
		sql.append("and (a.des_grupo_sanguineo_associado = ? or ? is null or ? = '')   ");
		sql.append("and (a.sts_fator_rh_associado= ?  or ? is null or ? = '')  ");
		sql.append("and (upper(a.nom_pai_associado) like '%' || upper(?) || '%' or ? is null) ");
		sql.append("and (upper(a.nom_mae_associado) like '%' || upper(?) || '%' or ? is null) ");
		// TODO filtro por conjuge precisa olhar a tabela CONJUGE
		//sql.append("and (upper(a.nom_conjuge_associado) like '%' || upper(?) || '%' or ? is null) ");
		sql.append("and (a.num_calcado_associado >= ? or ? is null) ");
		sql.append("and (a.num_calcado_associado <= ? or ? is null) ");
		sql.append("and (a.sts_situacao_justica_associado = ?  or ? is null or ? = '')  "); 
		sql.append("and (upper(a.nom_orgao_associado) like '%' || upper(?) || '%' or ? is null) ");
		sql.append("and (upper(a.nom_setor_associado) like '%' || upper(?) || '%' or ? is null) ");
		sql.append("and (a.seq_estado_setor_associado = ?  or ? is null)  ");		
		sql.append("and (upper(a.nom_municipio_setor_associado) like '%' || upper(?) || '%' or ? is null) ");
		sql.append("and (upper(a.des_endereco_setor_associado) like '%' || upper(?) || '%' or ? is null) ");
		sql.append("and (a.num_telefone_setor_associado = ? or ? is null) ");
		sql.append("and (a.num_ramal_setor_associado = ? or ? is null) ");
		sql.append("and (upper(a.num_matricula_justica_associado) like '%' || upper(?) || '%' or ? is null) ");
		sql.append("and (a.sts_categoria_associado = ?  or ? is null or ? = '')  ");
		sql.append("and ((upper(a2.nom_associado) like '%' || upper(?) || '%' or ? is null) or (a2.nom_associado is null and (? is null or ? = ''))) ");
		sql.append("and (a.sts_recebe_jornal_associado = ? or ? is null or '' = ?) ");
		sql.append("and (a.seq_banco = ? or ? is null) ");	//80					
		//sql.append("and (upper(a.num_agencia_associado) like '%' || upper(?) || '%' or ? is null) ");
		//sql.append("and (a.dig_agencia_associado = ? or ? is null or '' = ?) ");
		//sql.append("and (upper(a.num_conta_associado) like '%' || upper(?) || '%' or ? is null) ");
		//sql.append("and (a.dig_conta_associado = ? or ? is null or '' = ?) ");
		sql.append("and (upper(d.nom_dependente) like '%' || upper(?) || '%' or ? is null or ? = '') ");
		sql.append("and ((exists (select d2.seq_dependente from vw_dependente d2 where d2.seq_associado = a.seq_associado)) or ? is null or ? = '') "); // o par�metro aqui � o checkbox POSSUIDEPENDENTE
		sql.append("and ((exists (select f2.seq_filho from vw_filho f2 where f2.seq_associado = a.seq_associado)) or ? is null or ? = '') "); // o par�metro aqui � o checkbox POSSUIFILHO
		sql.append("and (d.num_rg_dependente = ? or ? is null) ");
		sql.append("and (d.dat_nascimento_dependente >= ? or ? is null) ");
		sql.append("and (d.dat_nascimento_dependente <= ? or ? is null) ");
		sql.append("and (d.sts_sexo_dependente = ? or ? is null or ? = '') ");
		sql.append("and (d.seq_parentesco  = ?  or ? is null) ");
		sql.append("and (upper(f.nom_filho) like '%' || upper(?) || '%' or ? is null or ? = '') ");
		sql.append("and (f.dat_nascimento_filho >= ? or ? is null) ");
		sql.append("and (f.dat_nascimento_filho <= ? or ? is null) ");
		sql.append("and (f.sts_sexo_filho = ? or ? is null or ? = '') "); //112
		sql.append("and (a.sts_pre_cadastro_associado = ? or ? is null or ? = '') "); 

		//IncluirInativos
		sql.append("and ((select h2.seq_tipo_evento from historico_evento_associado h2 	where h2.seq_associado = a.seq_associado	order by h2.seq_historico_evento_associado desc limit 1) <>  (select int2(str_val_parametro) from parametros where nom_parametro = 'TP_EVT_CANCELAMENTO') or ? = 'S') ");
		  //sql.append("and (a.seq_tipo_evento) <>  (select int2(str_val_parametro) from parametros where nom_parametro = 'TP_EVT_CANCELAMENTO') or ? = 'S') ");
		
		sql.append("and ((e2.seq_tipo_evento = ? and e2.dat_historico_evento_associado >= ? and e2.dat_historico_evento_associado <= ?) or ? is null)");

		sql.append("and ((? is null and ? is null) or (filtro_val_mensalidade(?, ?, a.seq_associado))) ");

		sql.append("and a.dat_exclusao_associado is null ");
	
		sql.append("group by a.seq_associado, a.nom_associado, a.nom_orgao_associado, a.nom_setor_associado, ")
		   .append("a.dat_historico_evento_associado, a.des_email_associado, a.sts_pre_cadastro_associado ");

		sql.append(" order by a.nom_associado ");
		
		try {
			
			sConn 	= new SmartConnection(this.getConn());
			sStmt 	= new SmartPreparedStatement(sConn.prepareStatement(sql.toString()));
			
			sStmt.setParameters(assembler, new String[] {"codigo", 
														"codigo",
														"nome", 
														"nome",
														"sexo", 
														"sexo", 
														"sexo", 
														"dataInicioNascimento", 
														"strDataInicioNascimento", 
														"dataFimNascimento", 
														"strDataFimNascimento", 
														"strDataInicioAssociacao", 
														"dataInicioAssociacao", 
														"strDataFimAssociacao", 
														"dataFimAssociacao", 
														"rg", 
														"rg",
														"cpf",
														"cpf",
														"endereco.logradouro", 
														"endereco.logradouro",
														"endereco.numero", 
														"endereco.numero", 
														"endereco.complemento", 
														"endereco.complemento",
														"endereco.bairro", 
														"endereco.bairro", 
														"endereco.cep", 
														"endereco.cep",
														"endereco.municipio.estado.codigo",
														"endereco.municipio.estado.codigo",
														"endereco.municipio.nome", 
														"endereco.municipio.nome",
														"telefoneResidencial", 
														"telefoneResidencial", 
														"telefoneComercial", 
														"telefoneComercial", 
														"telefoneCelular", 
														"telefoneCelular", 
														"email",	 
														"email",	
														"naturalidade.estado.codigo", 
														"naturalidade.estado.codigo", 
														"naturalidade.nome",   
														"naturalidade.nome",
														"estadoCivil", 
														"estadoCivil",
														"estadoCivil", 
														"profissao.nome", 
														"profissao.nome", 
														"grupoSanguineo",
														"grupoSanguineo", 
														"grupoSanguineo",	
														"fatorRh",
														"fatorRh", 
														"fatorRh", 
														"nomePai", 
														"nomePai", 
														"nomeMae", 
														"nomeMae", 
														//TODO filtro por conjuge precisa olhar tabela CONJUGE
														//"conjuge.nome",
														//"conjuge.nome",
														"numeroInicioCalcado", 
														"numeroInicioCalcado",
														"numeroFimCalcado", 
														"numeroFimCalcado", 
														"statusJustica",
														"statusJustica", 
														"statusJustica", 
														"setor.orgao.nome", 
														"setor.orgao.nome", 
														"setor.nome", 
														"setor.nome", 
														"setor.endereco.municipio.estado.codigo",
														"setor.endereco.municipio.estado.codigo", 
														"setor.endereco.municipio.nome",
														"setor.endereco.municipio.nome",
														"setor.endereco.logradouro", 
														"setor.endereco.logradouro", 
														"setor.telefone", 
														"setor.telefone", 
														"setor.ramal", 
														"setor.ramal", 
														"matriculaJustica",
														"matriculaJustica", 
														"statusCategoria", 
														"statusCategoria", 
														"statusCategoria",
														//"contribuinte.codigo", 
														"contribuinte.nome", 
														"contribuinte.nome", 
														"contribuinte.nome", 
														"contribuinte.nome", 
														"statusRecebeJornal", 
														"statusRecebeJornal", 
														"statusRecebeJornal", 
														"conta.bancoVO.codigo", 
														"conta.bancoVO.codigo", 
														//"conta.numAgencia", 
														//"conta.numAgencia", 
														//"conta.digAgencia", 
														//"conta.digAgencia", 
														//"conta.digAgencia", 
														//"conta.numConta", 
														//"conta.numConta", 
														//"conta.digConta", 
														//"conta.digConta", 
														//"conta.digConta", 
														"dependente.nome",
														"dependente.nome", 
														"dependente.nome", 
														"statusPossuiDependente", 
														"statusPossuiDependente", 
														"statusPossuiFilho", 
														"statusPossuiFilho", 
														"dependente.rg", 
														"dependente.rg", 
														"dataInicioNascimentoDependente", 
														"dataInicioNascimentoDependente",
														"dataFimNascimentoDependente", 
														"dataFimNascimentoDependente", 
														"dependente.sexo", 
														"dependente.sexo", 
														"dependente.sexo", 
														"dependente.parentesco.codigo", 
														"dependente.parentesco.codigo", 
														"filho.nome",
														"filho.nome",
														"filho.nome",
														"dataInicioNascimentoFilho" , 
														"strDataInicioNascimentoFilho", 
														"dataFimNascimentoFilho", 
														"strDataFimNascimentoFilho", 
														"filho.sexo", 
														"filho.sexo", 
														"filho.sexo", 
														"statusPreCadastro", 
														"statusPreCadastro", 
														"statusPreCadastro", 
														"statusIncluirInativos", 
														"historico.tipoEvento.codigo", 
														"historico.dataInicio", 
														"historico.dataFim", 
														"historico.tipoEvento.codigo", 
														"valorInicioMensalidade", 
														"valorFimMensalidade",
														"valorInicioMensalidade", 
														"valorFimMensalidade"
													});
		sRs = new SmartResultSet(sStmt.getMyPreparedStatement().executeQuery());
			
		return sRs.getJavaBeans(AssociadoVO.class, new String[] {"codigo", "nome", "setor.orgao.nome", "setor.nome", "email", "dataAssociacao", "statusPreCadastro"}); 

		} catch (SQLException e) {
			throw new SmartEnvException(e);
		} finally {
			sRs.close();
			sStmt.close();
			sConn.close();
		}
	}
	
	public AssociadoVO findByName(AssociadoVO associado) throws SmartEnvException {
		
		StringBuffer sql = new StringBuffer(" SELECT SEQ_ASSOCIADO, NOM_ASSOCIADO FROM VW_ASSOCIADO WHERE UPPER(NOM_ASSOCIADO) LIKE '%' || UPPER(?) || '%' AND STS_CATEGORIA_ASSOCIADO = 'C' AND DAT_EXCLUSAO_ASSOCIADO IS NULL ");

		SmartConnection 		sConn 	= null;
		SmartPreparedStatement 	sStmt 	= null;
		SmartResultSet			sRs		= null;
		
		try {
		
			sConn 	= new SmartConnection(this.getConn());
			sStmt 	= new SmartPreparedStatement(sConn.prepareStatement(sql.toString()));
			
		
			sStmt.setString(1, associado.getNome());
			
			sRs 	= new SmartResultSet(sStmt.getMyPreparedStatement().executeQuery());
			
			return (AssociadoVO) sRs.getJavaBean(associado, new String[] {"codigo", "nome"} );
		
		} catch (SQLException e) {
			throw new SmartEnvException(e);

		} finally {

			sRs.close();
			sStmt.close();
			sConn.close();

		}
		
	}
	
	public AssociadoVO findByCPF(AssociadoVO associado) throws SmartEnvException {
		
		StringBuffer sql = new StringBuffer(" SELECT SEQ_ASSOCIADO, NOM_ASSOCIADO, NUM_CPF_ASSOCIADO FROM VW_ASSOCIADO WHERE NUM_CPF_ASSOCIADO = ? AND STS_CATEGORIA_ASSOCIADO = 'C' AND DAT_EXCLUSAO_ASSOCIADO IS NULL ");

		SmartConnection 		sConn 	= null;
		SmartPreparedStatement 	sStmt 	= null;
		SmartResultSet			sRs		= null;
		
		try {
		
			sConn 	= new SmartConnection(this.getConn());
			sStmt 	= new SmartPreparedStatement(sConn.prepareStatement(sql.toString()));
			
		
			sStmt.setLong(1, associado.getCpf());
			
			sRs 	= new SmartResultSet(sStmt.getMyPreparedStatement().executeQuery());
			
			return (AssociadoVO) sRs.getJavaBean(associado, new String[] {"codigo", "nome", "cpf"} );
		
		} catch (SQLException e) {
			throw new SmartEnvException(e);

		} finally {

			sRs.close();
			sStmt.close();
			sConn.close();

		}
		
	}
	
	public Collection<AssociadoImportacaoNucreVO> findFaltantes (Collection<ItemPlanilhaNucreVO> planilha) throws SmartEnvException {
		
		StringBuffer sqlTemp = new StringBuffer(" SELECT NUM_MATRICULA_JUSTICA_ASSOCIADO as matricula, 'arquivo' as falta FROM VW_ASSOCIADO ") 
		.append(" WHERE dat_exclusao_associado is null and NUM_MATRICULA_JUSTICA_ASSOCIADO NOT IN ( SELECT MATRICULA FROM( ");
		
		
		for (ItemPlanilhaNucreVO vo : planilha) {
		
			
			sqlTemp	.append(" select ").append(vo.getAssociado().getMatriculaJustica()).append(" as matricula ")
					.append(" union all ");
									
			
		}
		
		String sql	= sqlTemp.toString().substring(0, sqlTemp.toString().length() - 11);
		
		
		sqlTemp = new StringBuffer(sql);
		
		sqlTemp.append(" ) tab1 ) ")
		.append(" union ")
		.append(" SELECT MATRICULA, 'cadastro' as falta FROM ( ");
		
		for (ItemPlanilhaNucreVO vo : planilha) {
		
			sqlTemp	.append(" select ").append(vo.getAssociado().getMatriculaJustica()).append(" as matricula ")
					.append(" union all ");
				
		
		}
		
		sql	= sqlTemp.toString().substring(0, sqlTemp.toString().length() - 11);
		
		
		
		sqlTemp	= new StringBuffer(sql)		
		
		.append(" ) TAB ")
		.append(" WHERE TAB.MATRICULA NOT IN (SELECT NUM_MATRICULA_JUSTICA_ASSOCIADO FROM VW_ASSOCIADO where dat_exclusao_associado is null) ");

		SmartConnection 		sConn 	= null;
		SmartPreparedStatement 	sStmt 	= null;
		SmartResultSet			sRs		= null;
		
		try {
		
			sConn 	= new SmartConnection(this.getConn());
			sStmt 	= new SmartPreparedStatement(sConn.prepareStatement(sqlTemp.toString()));
			
			sRs 	= new SmartResultSet(sStmt.getMyPreparedStatement().executeQuery());
			
			return  sRs.getJavaBeans(AssociadoImportacaoNucreVO.class, new String[] {"matriculaJustica", "falta"} );
		
		} catch (SQLException e) {
			throw new SmartEnvException(e);

		} finally {

			sRs.close();
			sStmt.close();
			sConn.close();

		}
		
	}
	
	
	public Collection<LancamentoAssociadoVO> findIntercessaoLancamentosBase (Collection<ItemPlanilhaNucreVO> planilha) throws SmartEnvException {
		
		//String 	sqlAux1 				= "";
		//String sqlAux2 = "";
		
		boolean naoPegouDataAinda 	= true;
		Date	dataImportacao		= new Date();
		
		StringBuffer sqlTemp = new StringBuffer(" SELECT seq_associado, ")
										.append(" 		 MATRICULA,  ")
										.append(" 		 CUSTO_CONSIGNACAO, ") 
										.append(" 		 (SELECT STR_VAL_PARAMETRO FROM PARAMETROS WHERE NOM_PARAMETRO = 'CONTA_DEBITO_CUSTO_CONSIG') AS SEQ_CONTA, ") 
										.append(" 		 (SELECT STR_VAL_PARAMETRO FROM PARAMETROS WHERE NOM_PARAMETRO = 'ORIGEM_ASSOCIADOS') AS SEQ_ORIGEM_LANCAMENTO, ") 
										.append(" 		 (SELECT STR_VAL_PARAMETRO FROM PARAMETROS WHERE NOM_PARAMETRO = 'TP_OPERACAO_DEBITO') AS SEQ_TIPO_OPERACAO ") 
										.append(" FROM 	VW_ASSOCIADO, ")		
										.append(" 		(	");
		
		
										for (ItemPlanilhaNucreVO vo : planilha) {
			
											if (naoPegouDataAinda) {
												dataImportacao		= vo.getData();
												naoPegouDataAinda	= false;
												
											}
											
											sqlTemp.append(" select ").append(vo.getAssociado().getMatriculaJustica()).append(" as matricula, ").append(vo.getCustoConsignacao()).append(" AS CUSTO_CONSIGNACAO, ").append(vo.getValorDebitado()).append(" AS VALOR_DEBITADO ")
														.append(" union all ").toString();
		
										}
										
										sqlTemp	= new StringBuffer(sqlTemp.toString().substring(0, sqlTemp.toString().length() - 11));
										
										//sqlTemp.append(sqlAux1);
										
										sqlTemp.append(" 		) ")
										.append("       PLANILHA ")
										
										.append(" WHERE dat_exclusao_associado is null ")
										
										/*
										.append(" WHERE dat_exclusao_associado is null and NUM_MATRICULA_JUSTICA_ASSOCIADO IN ( SELECT MATRICULA FROM( ");									
										
										
										
										for (PlanilhaNucreVO vo : planilha) {
											
											sqlAux2 = new StringBuffer()
											
													.append(" 	select ").append(vo.getAssociado().getMatriculaJustica()).append(" as matricula ")
													.append(" union all ").toString();
											
										}
										
										sqlAux2	= sqlAux2.substring(0, sqlAux2.length() - 11);
										
										sqlTemp.append(sqlAux2);
										
										sqlTemp	.append(" ) tab1 )")
										*/
										
												.append(" AND VW_ASSOCIADO.NUM_MATRICULA_JUSTICA_ASSOCIADO = PLANILHA.MATRICULA ")
												
												
												
												.append(" AND NOT EXISTS (SELECT 1 FROM LANCAMENTO L, LANCAMENTO_ASSOCIADO LA WHERE L.SEQ_LANCAMENTO = LA.SEQ_LANCAMENTO AND LA.SEQ_ASSOCIADO = VW_ASSOCIADO.SEQ_ASSOCIADO AND L.DAT_EFETIVACAO_LANCAMENTO = ?)")
												
												;
										
										
										
										sqlTemp.append(" UNION ");
										
										/*
										sqlTemp.append(" SELECT seq_associado, ")
										.append(" 		 MATRICULA,  ")
										.append(" 		 VALOR_DEBITADO,   ")
										.append(" 		 (SELECT STR_VAL_PARAMETRO FROM PARAMETROS WHERE NOM_PARAMETRO = 'CONTA_CONSIGNACAO_NUCRE') AS SEQ_CONTA, ") 
										.append(" 		 (SELECT STR_VAL_PARAMETRO FROM PARAMETROS WHERE NOM_PARAMETRO = 'ORIGEM_ASSOCIADOS') AS SEQ_ORIGEM_LANCAMENTO, ") 
										.append(" 		 (SELECT STR_VAL_PARAMETRO FROM PARAMETROS WHERE NOM_PARAMETRO = 'TP_OPERACAO_CREDITO') AS SEQ_TIPO_OPERACAO ") 
										.append(" FROM 	VW_ASSOCIADO, ")		
										.append(" 		(	")		
										.append(sqlAux1)											
										.append(" 		) ")
										.append("       PLANILHA ")
										.append(" WHERE dat_exclusao_associado is null and NUM_MATRICULA_JUSTICA_ASSOCIADO IN ( SELECT MATRICULA FROM( ")
										.append(sqlAux2)
										.append(" ) tab2 ) ")
										.append(" AND VW_ASSOCIADO.NUM_MATRICULA_JUSTICA_ASSOCIADO = PLANILHA.MATRICULA ");
										*/
										
										
										sqlTemp.append(" SELECT seq_associado, ")
										.append(" 		 MATRICULA,  ")
										.append(" 		 VALOR_DEBITADO, ") 
										.append(" 		 (SELECT STR_VAL_PARAMETRO FROM PARAMETROS WHERE NOM_PARAMETRO = 'CONTA_CREDITO_MENSALIDADE') AS SEQ_CONTA, ") 
										.append(" 		 (SELECT STR_VAL_PARAMETRO FROM PARAMETROS WHERE NOM_PARAMETRO = 'ORIGEM_ASSOCIADOS') AS SEQ_ORIGEM_LANCAMENTO, ") 
										.append(" 		 (SELECT STR_VAL_PARAMETRO FROM PARAMETROS WHERE NOM_PARAMETRO = 'TP_OPERACAO_CREDITO') AS SEQ_TIPO_OPERACAO ") 
										.append(" FROM 	VW_ASSOCIADO, ")		
										.append(" 		(	");
		
		
										for (ItemPlanilhaNucreVO vo : planilha) {
			
																						
											sqlTemp.append(" select ").append(vo.getAssociado().getMatriculaJustica()).append(" as matricula, ").append(vo.getCustoConsignacao()).append(" AS CUSTO_CONSIGNACAO, ").append(vo.getValorDebitado()).append(" AS VALOR_DEBITADO ")
														.append(" union all ").toString();
		
										}
										
										sqlTemp	= new StringBuffer(sqlTemp.toString().substring(0, sqlTemp.toString().length() - 11));
										
										//sqlTemp.append(sqlAux1);
										
										sqlTemp.append(" 		) ")
										.append("       PLANILHA ")
										
										.append(" WHERE dat_exclusao_associado is null ")
										.append(" AND VW_ASSOCIADO.NUM_MATRICULA_JUSTICA_ASSOCIADO = PLANILHA.MATRICULA ")
										.append(" AND NOT EXISTS (SELECT 1 FROM LANCAMENTO L, LANCAMENTO_ASSOCIADO LA WHERE L.SEQ_LANCAMENTO = LA.SEQ_LANCAMENTO AND LA.SEQ_ASSOCIADO = VW_ASSOCIADO.SEQ_ASSOCIADO AND L.DAT_EFETIVACAO_LANCAMENTO = ?)")
										;
										
		
		
		SmartConnection 		sConn 	= null;
		SmartPreparedStatement 	sStmt 	= null;
		SmartResultSet			sRs		= null;
		
		try {
		
			sConn 	= new SmartConnection(this.getConn());
			sStmt 	= new SmartPreparedStatement(sConn.prepareStatement(sqlTemp.toString()));

			sStmt.setDate(1, dataImportacao);
			sStmt.setDate(2, dataImportacao);
			
			sRs 	= new SmartResultSet(sStmt.getMyPreparedStatement().executeQuery());
			
			return  sRs.getJavaBeans(LancamentoAssociadoVO.class, new String[] {"associado.codigo", "associado.matriculaJustica", "valor", "contaVO.codigo", "origemLancamentoVO.codigo", "tipoOperacaoVO.codigo"} );
		
		} catch (SQLException e) {
			throw new SmartEnvException(e);

		} finally {

			sRs.close();
			sStmt.close();
			sConn.close();

		}
		
	}
	
	public void insertLancamento(LancamentoAssociadoVO lancamentoAssociado) throws SmartEnvException {
		
		StringBuffer sql = new StringBuffer(" INSERT INTO LANCAMENTO_ASSOCIADO (SEQ_LANCAMENTO, SEQ_ASSOCIADO) ")
			.append(" VALUES (?, (SELECT A.SEQ_ASSOCIADO FROM VW_ASSOCIADO A WHERE A.NUM_MATRICULA_JUSTICA_ASSOCIADO = ?)) ");

		
		SmartConnection 		sConn 	= null;
		SmartPreparedStatement 	sStmt 	= null;
		
		try {
		
			sConn 	= new SmartConnection(this.getConn());
			sStmt 	= new SmartPreparedStatement(sConn.prepareStatement(sql.toString()));
			
			sStmt.setParameters(lancamentoAssociado, new String[]{"codigo", "associado.matriculaJustica"});
						
			sStmt.getMyPreparedStatement().execute();
			
			
		} catch (SQLException e) {

			throw new SmartEnvException(e);

		}finally {

		
			sStmt.close();
			sConn.close();

		}
		

		
	}


	public Collection<RelatorioAssociadosDependentesVO> findRelatorioAssociadosDependentes() throws SmartEnvException {
		StringBuffer sql = new StringBuffer(" SELECT COALESCE(titular.faixa_etaria, depend.FAIXA_ETARIA) as faixa_etaria, coalesce(TITULAR.qtde_de_associados, 0) as qtd_associados, coalesce(DEPEND.QTDE_DE_DEPENDENTES,0) as qtd_dependentes FROM ( ") 
				.append("SELECT ") 
				.append("  CASE ") 
				.append("    WHEN DATE_PART('year', AGE(CURRENT_DATE, a.dat_nascimento_associado)) <= 18 THEN '0-18' ") 
				.append("    WHEN DATE_PART('year', AGE(CURRENT_DATE, a.dat_nascimento_associado)) BETWEEN 19 AND 24 THEN '19-24' ") 
				.append("    WHEN DATE_PART('year', AGE(CURRENT_DATE, a.dat_nascimento_associado)) BETWEEN 25 AND 28 THEN '25-28' ") 
				.append("    WHEN DATE_PART('year', AGE(CURRENT_DATE, a.dat_nascimento_associado)) BETWEEN 29 AND 33 THEN '29-33' ") 
				.append("    WHEN DATE_PART('year', AGE(CURRENT_DATE, a.dat_nascimento_associado)) BETWEEN 34 AND 38 THEN '34-38' ") 
				.append("    WHEN DATE_PART('year', AGE(CURRENT_DATE, a.dat_nascimento_associado)) BETWEEN 39 AND 43 THEN '39-43' ") 
				.append("    WHEN DATE_PART('year', AGE(CURRENT_DATE, a.dat_nascimento_associado)) BETWEEN 44 AND 48 THEN '44-48' ") 
				.append("    WHEN DATE_PART('year', AGE(CURRENT_DATE, a.dat_nascimento_associado)) BETWEEN 49 AND 53 THEN '49-53' ") 
				.append("    WHEN DATE_PART('year', AGE(CURRENT_DATE, a.dat_nascimento_associado)) BETWEEN 54 AND 58 THEN '54-58' ") 
				.append("    ELSE '59+' ") 
				.append("  END AS faixa_etaria, ") 
				.append("  COUNT(a.*) as qtde_de_associados ") 
				.append("FROM ") 
				.append("  vw_associado a  ") 
				.append("WHERE ") 
				.append(" a.dat_exclusao_associado is null ") 
				.append(" and ((select h2.seq_tipo_evento from historico_evento_associado h2 	where h2.seq_associado = a.seq_associado	order by h2.seq_historico_evento_associado desc limit 1) <>  (select int2(str_val_parametro) from parametros where nom_parametro = 'TP_EVT_CANCELAMENTO') )  ") 
				.append("GROUP BY  faixa_etaria ") 
				.append(") TITULAR FULL JOIN  ") 
				.append("    ( ") 
				.append("SELECT ") 
				.append("  CASE ") 
				.append("    WHEN DATE_PART('year', AGE(CURRENT_DATE, d.dat_nascimento_dependente)) <= 18 THEN '0-18' ") 
				.append("    WHEN DATE_PART('year', AGE(CURRENT_DATE, d.dat_nascimento_dependente)) BETWEEN 19 AND 24 THEN '19-24' ") 
				.append("    WHEN DATE_PART('year', AGE(CURRENT_DATE, d.dat_nascimento_dependente)) BETWEEN 25 AND 28 THEN '25-28' ") 
				.append("    WHEN DATE_PART('year', AGE(CURRENT_DATE, d.dat_nascimento_dependente)) BETWEEN 29 AND 33 THEN '29-33' ") 
				.append("    WHEN DATE_PART('year', AGE(CURRENT_DATE, d.dat_nascimento_dependente)) BETWEEN 34 AND 38 THEN '34-38' ") 
				.append("    WHEN DATE_PART('year', AGE(CURRENT_DATE, d.dat_nascimento_dependente)) BETWEEN 39 AND 43 THEN '39-43' ") 
				.append("    WHEN DATE_PART('year', AGE(CURRENT_DATE, d.dat_nascimento_dependente)) BETWEEN 44 AND 48 THEN '44-48' ") 
				.append("    WHEN DATE_PART('year', AGE(CURRENT_DATE, d.dat_nascimento_dependente)) BETWEEN 49 AND 53 THEN '49-53' ") 
				.append("    WHEN DATE_PART('year', AGE(CURRENT_DATE, d.dat_nascimento_dependente)) BETWEEN 54 AND 58 THEN '54-58' ") 
				.append("    ELSE '59+' ") 
				.append("  END AS faixa_etaria, ") 
				.append("  COUNT(d.*) as qtde_de_dependentes ") 
				.append("FROM ") 
				.append("  vw_dependente d join vw_associado a using (seq_associado) ") 
				.append("WHERE ") 
				.append(" a.dat_exclusao_associado is null ") 
				.append(" and ((select h2.seq_tipo_evento from historico_evento_associado h2 	where h2.seq_associado = a.seq_associado	order by h2.seq_historico_evento_associado desc limit 1) <>  (select int2(str_val_parametro) from parametros where nom_parametro = 'TP_EVT_CANCELAMENTO') )  ") 
				.append("GROUP BY  faixa_etaria )  ") 
				.append("DEPEND ON TITULAR.FAIXA_ETARIA = DEPEND.FAIXA_ETARIA ");

		SmartConnection 		sConn 	= null;
		SmartPreparedStatement 	sStmt 	= null;
		SmartResultSet			sRs		= null;
		
		try {
		
		sConn 	= new SmartConnection(this.getConn());
		sStmt 	= new SmartPreparedStatement(sConn.prepareStatement(sql.toString()));
		sRs 	= new SmartResultSet(sStmt.getMyPreparedStatement().executeQuery());
		
		ArrayList<RelatorioAssociadosDependentesVO> retorno = new ArrayList<RelatorioAssociadosDependentesVO>(); 
		
		while (sRs.next()) {
		
			RelatorioAssociadosDependentesVO relatorio = new RelatorioAssociadosDependentesVO();
		
			relatorio.setFaixaEtaria(sRs.getString(1));
			relatorio.setQuantidadeAssociados(sRs.getInteger(2));
			relatorio.setQuantidadeDependentes(sRs.getInteger(3));
			
			retorno.add(relatorio);
		
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


	public List<InconsistenciaNucreVO> findRelatorioInconsistenciasNUCRE(Long codigoNovaPlanilhaNucre) throws SmartEnvException {

		
		StringBuffer sql = new StringBuffer("select cpf, nome, tipo_inconsistencia from ( ") 
				.append(" SELECT NUCRE.NUM_CPF_ASSOCIADO as cpf, NUCRE.NOM_ASSOCIADO as nome, 'NAO ENCONTRADO NO CADASTRO SISJUF' as TIPO_INCONSISTENCIA ")
				.append(" FROM ITEM_PLANILHA_NUCRE NUCRE ")
				.append(" WHERE NUCRE.SEQ_PLANILHA_NUCRE = ? ")
				.append(" AND NOT EXISTS (SELECT 1  FROM VW_ASSOCIADO A WHERE A.num_cpf_associado = NUCRE.NUM_CPF_ASSOCIADO AND A.STS_CATEGORIA_ASSOCIADO = 'C') ")
				.append(" UNION ALL ")
				.append(" SELECT A.NUM_CPF_ASSOCIADO as cpf, A.NOM_ASSOCIADO as nome, 'NAO ENCONTRADO NO ARQUIVO SEPAG' as TIPO_INCONSISTENCIA ")
				.append(" FROM VW_ASSOCIADO A ")
				.append(" WHERE NOT EXISTS (SELECT 1 FROM ITEM_PLANILHA_NUCRE NUCRE WHERE NUCRE.NUM_CPF_ASSOCIADO=A.NUM_CPF_ASSOCIADO AND NUCRE.SEQ_PLANILHA_NUCRE = ?) ")
				.append(" ) as TEMP_TABLE order by tipo_inconsistencia, nome, cpf ");
		SmartConnection 		sConn 	= null;
		SmartPreparedStatement 	sStmt 	= null;
		SmartResultSet			sRs		= null;
		
		try {
		
		sConn 	= new SmartConnection(this.getConn());
		sStmt 	= new SmartPreparedStatement(sConn.prepareStatement(sql.toString()));
		
		
		sStmt.setLong(1, codigoNovaPlanilhaNucre);
		sStmt.setLong(2, codigoNovaPlanilhaNucre);
		sRs 	= new SmartResultSet(sStmt.getMyPreparedStatement().executeQuery());
		
		return (List<InconsistenciaNucreVO>) sRs.getJavaBeans(InconsistenciaNucreVO.class, new String[]{	
																		"cpf",
																		"nome",
																		"tipoInconsistencia"
																});						
		
		} catch (SQLException e) {
		throw new SmartEnvException(e);
		
		} finally {
		
		sRs.close();
		sStmt.close();
		sConn.close();
		
		}
	}


	public void insertItemPlanilhaNucre(ItemPlanilhaNucreVO itemNucre) throws SmartEnvException {
		StringBuffer sql = new StringBuffer(" insert into ITEM_PLANILHA_NUCRE ")
				.append(" (SEQ_PLANILHA_NUCRE, SEQ_ITEM_PLANILHA_NUCRE, NUM_CPF_ASSOCIADO, NOM_ASSOCIADO, DAT_IMPORTACAO) ")
				.append(" VALUES (?, nextval('SEQ_ITEM_PLANILHA_NUCRE'), ?, ?, current_timestamp) ");

				SmartConnection 		sConn 	= null;
				SmartPreparedStatement 	sStmt 	= null;
						
				try {
				
					sConn 	= new SmartConnection(this.getConn());
					sStmt 	= new SmartPreparedStatement(sConn.prepareStatement(sql.toString()));
					
					sStmt.setParameters(itemNucre, new String[]{"codigoPlanilha", "associado.cpf", "associado.nome"});
					
					sStmt.getMyPreparedStatement().execute();
					
				
				} catch (SQLException e) {
					throw new SmartEnvException(e);

				} finally {

					sStmt.close();
					sConn.close();

				}
		
	}
	

	
	
}