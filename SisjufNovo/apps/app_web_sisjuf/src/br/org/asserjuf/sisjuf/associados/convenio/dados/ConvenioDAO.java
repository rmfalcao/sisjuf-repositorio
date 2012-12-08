package br.org.asserjuf.sisjuf.associados.convenio.dados;

import java.sql.SQLException;
import java.util.Collection;

import org.apache.log4j.Logger;

import br.com.falc.smartFW.exception.SmartAppException;
import br.com.falc.smartFW.exception.SmartEnvException;
import br.com.falc.smartFW.persistence.SmartConnection;
import br.com.falc.smartFW.persistence.SmartPreparedStatement;
import br.com.falc.smartFW.persistence.SmartResultSet;
import br.org.asserjuf.sisjuf.associados.convenio.AtividadeConvenioVO;
import br.org.asserjuf.sisjuf.associados.convenio.ConvenioVO;
import br.org.asserjuf.sisjuf.associados.convenio.ReportVitalmedVO;
import br.org.asserjuf.sisjuf.dados.SisjufDAOPostgres;

/**
 * Classe de acesso ao banco de dados da entidade "Convenio" do Sisjuf
 * @author Paulo
 *
 */
public class ConvenioDAO extends SisjufDAOPostgres {

	private static transient Logger	LOG = Logger.getLogger(ConvenioDAO.class);
	
	/**
	 * Obtém todos os convênios cadastrados na banco de dados. Tabelas: CONVENIO, ATIVIDADE_CONVENIO, ESTADO
	 * @return Coleção de ConvenioVO (objeto que representa a entidade "Convenio")
	 * @throws SmartEnvException
	 */
	public Collection<ConvenioVO> findAll() throws SmartEnvException {
		
		StringBuffer sql = new StringBuffer(" ");
		
		sql.append(" SELECT   ");
		sql.append(" SEQ_CONVENIO,  "); 
		sql.append(" FLG_CATEGORIA,  ");
		sql.append(" NOM_FANTASIA,  ");
		sql.append(" NOM_RAZAO_SOCIAL,  ");
		sql.append(" DES_CNPJ,  ");
		sql.append(" DES_LOGRADOURO,  ");
		sql.append(" DES_COMPLEMENTO,  ");
		sql.append(" NOM_BAIRRO,  ");
		sql.append(" NOM_CIDADE,  ");
		sql.append(" E.SEQ_ESTADO,  ");
		sql.append(" E.SIG_ESTADO, ");
		sql.append(" E.NOM_ESTADO,  ");
		sql.append(" AC.SEQ_ATIVIDADE_CONVENIO,  ");
		sql.append(" AC.NOM_ATIVIDADE_CONVENIO,  ");
		sql.append(" AC.FLG_DESATIVADO,  ");
		sql.append(" DES_CEP,  ");
		sql.append(" NOM_CONTATO,  ");
		sql.append(" DES_TELEFONE,  ");
		sql.append(" DES_TELEFONE_ADICIONAL,  ");
		sql.append(" DES_FAX,  ");
		sql.append(" DES_EMAIL,  ");
		sql.append(" DES_BENEFICIO,  ");
		sql.append(" DES_CONVENIO,  ");
		sql.append(" NUM_DIA_FECHAMENTO,  ");
		sql.append(" NUM_FATOR_MES_VCTO,  ");
		sql.append(" NUM_DIA_VCTO,   ");
		sql.append(" FLG_CONJUGE,  ");
		sql.append(" FLG_FILHOS,  ");
		sql.append(" FLG_DEPENDENTES,  ");
		sql.append(" FLG_OUTROS_BENEF,  ");
		sql.append(" FLG_TITULAR,  ");
		sql.append(" C.FLG_DESATIVADO  ");
		sql.append(" FROM  CONVENIO C, ATIVIDADE_CONVENIO  AC, ESTADO E  ");
		sql.append(" WHERE  ");
		sql.append(" C.SEQ_ATIVIDADE_CONVENIO = AC.SEQ_ATIVIDADE_CONVENIO AND  "); 
		sql.append(" C.SEQ_ESTADO = E.SEQ_ESTADO  ");
		sql.append(" ORDER BY NOM_FANTASIA ");	

		SmartConnection 		sConn 	= null;
		SmartPreparedStatement 	sStmt 	= null;
		SmartResultSet			sRs		= null;
		try {
		
			sConn 	= new SmartConnection(this.getConn());
			sStmt 	= new SmartPreparedStatement(sConn.prepareStatement(sql.toString()));
			sRs 	= new SmartResultSet(sStmt.getMyPreparedStatement().executeQuery());
			
			return sRs.getJavaBeans(ConvenioVO.class, new String[]{
									"codigo", 
									"categoria", 
									"nomeFantasia",
									"razaoSocial",
									"cnpj",
									"logradouro",
									"complemento",
									"bairro",
									"cidade",
									"estado.codigo",
									"estado.sigla",
									"estado.nome",
									"atividadeConvenio.codigo",
									"atividadeConvenio.nome",
									"atividadeConvenio.desativo",
									"cep",
									"nomeContato",
									"telefone",
									"telefoneAdicional",
									"fax",
									"email",
									"beneficio",
									"descricao",
									"diaFechamento",
									"mesVencimento",
									"diaVencimento",
									"conjugue",
									"filhos",
									"dependentes",
									"outrosBeneficaiveis",
									"titular",
									"desativado"});
		} catch (SQLException e) {
			throw new SmartEnvException(e);
		} finally {
			sRs.close();
			sStmt.close();
			sConn.close();
		}
	}
	
	/**
	 * Obtém um Convenio (encapsulado no objeto ConvenioVO) cadastrada no banco de dados. Tabelas: CONVENIO, ATIVIDADE_CONVENIO, ESTADO
	 * @param vo Instância da classe ConvenioVO contendo a propriedade codigo preenchida.
	 * @return Retorna uma instância da classe ConvenioVO com as propriedades preenchidas.
	 * @throws SmartEnvException
	 */
	public ConvenioVO findByPrimaryKey(ConvenioVO vo) throws SmartEnvException {
		
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT   ");
		sql.append(" SEQ_CONVENIO,  "); 
		sql.append(" FLG_CATEGORIA,  ");
		sql.append(" NOM_FANTASIA,  ");
		sql.append(" NOM_RAZAO_SOCIAL,  ");
		sql.append(" DES_CNPJ,  ");
		sql.append(" DES_LOGRADOURO,  ");
		sql.append(" DES_COMPLEMENTO,  ");
		sql.append(" NOM_BAIRRO,  ");
		sql.append(" NOM_CIDADE,  ");
		sql.append(" E.SEQ_ESTADO,  ");
		sql.append(" E.SIG_ESTADO, ");
		sql.append(" E.NOM_ESTADO,  ");
		sql.append(" AC.SEQ_ATIVIDADE_CONVENIO,  ");
		sql.append(" AC.NOM_ATIVIDADE_CONVENIO,  ");
		sql.append(" AC.FLG_DESATIVADO,  ");
		sql.append(" DES_CEP,  ");
		sql.append(" NOM_CONTATO,  ");
		sql.append(" DES_TELEFONE,  ");
		sql.append(" DES_TELEFONE_ADICIONAL,  ");
		sql.append(" DES_FAX,  ");
		sql.append(" DES_EMAIL,  ");
		sql.append(" DES_BENEFICIO,  ");
		sql.append(" DES_CONVENIO,  ");
		sql.append(" NUM_DIA_FECHAMENTO,  ");
		sql.append(" NUM_FATOR_MES_VCTO,  ");
		sql.append(" NUM_DIA_VCTO,   ");
		sql.append(" FLG_CONJUGE,  ");
		sql.append(" FLG_FILHOS,  ");
		sql.append(" FLG_DEPENDENTES,  ");
		sql.append(" FLG_OUTROS_BENEF,  ");
		sql.append(" FLG_TITULAR,  ");
		sql.append(" C.FLG_DESATIVADO  ");
		sql.append(" FROM ");
		sql.append(" CONVENIO C inner join ATIVIDADE_CONVENIO  AC on  C.SEQ_ATIVIDADE_CONVENIO = AC.SEQ_ATIVIDADE_CONVENIO ");
		sql.append(" left join ESTADO E  on C.SEQ_ESTADO = E.SEQ_ESTADO " );
		sql.append(" WHERE  ");
		sql.append(" C.SEQ_CONVENIO = ? ");

		SmartConnection 		sConn 	= null;
		SmartPreparedStatement 	sStmt 	= null;
		SmartResultSet			sRs		= null;
		
		try {
			sConn 	= new SmartConnection(this.getConn());
			sStmt 	= new SmartPreparedStatement(sConn.prepareStatement(sql.toString()));
			
			sStmt.setInteger(1, vo.getCodigo());
			
			sRs = new SmartResultSet(sStmt.getMyPreparedStatement().executeQuery());
			  
			return (ConvenioVO) sRs.getJavaBean(vo, new String[] {
											"codigo", 
											"categoria", 
											"nomeFantasia",
											"razaoSocial",
											"cnpj",
											"logradouro",
											"complemento",
											"bairro",
											"cidade",
											"estado.codigo",
											"estado.sigla",
											"estado.nome",
											"atividadeConvenio.codigo",
											"atividadeConvenio.nome",
											"atividadeConvenio.desativo",
											"cep",
											"nomeContato",
											"telefone",
											"telefoneAdicional",
											"fax",
											"email",
											"beneficio",
											"descricao",
											"diaFechamento",
											"mesVencimento",
											"diaVencimento",
											"conjugue",
											"filhos",
											"dependentes",
											"outrosBeneficaiveis",
											"titular",
											"desativado"});
		} catch (SQLException e) {
			throw new SmartEnvException(e);
		} finally {
			sRs.close();
			sStmt.close();
			sConn.close();
		}
	}
	
	/**
	 * Atualiza os dados de uma convenio no banco de dados. Tabelas: CONVENIO
	 * @param vo Instância da classe ConvenioVO com os novos valores do registro a ser atualizado.
	 * @throws SmartEnvException
	 */
	public void update(ConvenioVO vo) throws SmartEnvException {
		
		
		StringBuffer SQL = new StringBuffer();
		SQL.append("UPDATE CONVENIO ");
		SQL.append("SET    FLG_CATEGORIA          = ?, ");
		SQL.append("       NOM_FANTASIA           = ?, ");
		SQL.append("       NOM_RAZAO_SOCIAL       = ?, ");
		SQL.append("       DES_CNPJ               = ?, ");
		SQL.append("       DES_LOGRADOURO         = ?, ");
		SQL.append("       DES_COMPLEMENTO        = ?, ");
		SQL.append("       NOM_BAIRRO             = ?, ");
		SQL.append("       NOM_CIDADE             = ?, ");
		SQL.append("       SEQ_ESTADO             = ?, ");
		SQL.append("       SEQ_ATIVIDADE_CONVENIO = ?, ");
		SQL.append("       DES_CEP                = ?, ");
		SQL.append("       NOM_CONTATO            = ?, ");
		SQL.append("       DES_TELEFONE           = ?, ");
		SQL.append("       DES_TELEFONE_ADICIONAL = ?, ");
		SQL.append("       DES_FAX                = ?, ");
		SQL.append("       DES_EMAIL              = ?, ");
		SQL.append("       DES_BENEFICIO          = ?, ");
		SQL.append("       DES_CONVENIO           = ?, ");
		SQL.append("       NUM_DIA_FECHAMENTO     = ?, ");
		SQL.append("       NUM_FATOR_MES_VCTO     = ?, ");
		SQL.append("       NUM_DIA_VCTO           = ?, ");
		SQL.append("       FLG_CONJUGE            = ?, ");
		SQL.append("       FLG_FILHOS             = ?, ");
		SQL.append("       FLG_DEPENDENTES        = ?, ");
		SQL.append("       FLG_OUTROS_BENEF       = ?, ");
		SQL.append("       FLG_TITULAR            = ?, ");
		SQL.append("       FLG_DESATIVADO              = ? ");
		SQL.append("WHERE  SEQ_CONVENIO           = ?");

		SmartConnection 		sConn 	= null;
		SmartPreparedStatement 	sStmt 	= null;
		
		try {
		
			sConn 	= new SmartConnection(this.getConn());
			sStmt 	= new SmartPreparedStatement(sConn.prepareStatement(SQL.toString()));
			
			sStmt.setParameters(vo, new String[]{ 
					"categoria", 
					"nomeFantasia",
					"razaoSocial",
					"cnpj",
					"logradouro",
					"complemento",
					"bairro",
					"cidade",
					"estado.codigo",
					"atividadeConvenio.codigo",
					"cep",
					"nomeContato",
					"telefone",
					"telefoneAdicional",
					"fax",
					"email",
					"beneficio",
					"descricao",
					"diaFechamento",
					"mesVencimento",
					"diaVencimento",
					"conjugue",
					"filhos",
					"dependentes",
					"outrosBeneficaiveis",
					"titular",
					"desativado",
					"codigo"});			
						
			sStmt.getMyPreparedStatement().execute();
				
		} catch (SQLException e) {
			throw new SmartEnvException(e);
		} finally {
			sStmt.close();
			sConn.close();
		}
	}

	/**
	 * Insere um convênio no banco de dados. Tabelas: CONVENIO
	 * @param vo Instância da classe ConvenioVO com os valores do registro a ser inserido.
	 * @throws SmartEnvException
	 */
	public ConvenioVO insert(ConvenioVO vo) throws SmartEnvException {
		
		StringBuffer sql = new StringBuffer("  ");
		sql.append(" INSERT INTO CONVENIO   ");  
		sql.append(" (SEQ_CONVENIO,  ");
		sql.append(" FLG_CATEGORIA,  ");
		sql.append(" NOM_FANTASIA,  ");
		sql.append(" NOM_RAZAO_SOCIAL,  ");
		sql.append(" DES_CNPJ,  ");
		sql.append(" DES_LOGRADOURO,  ");
		sql.append(" DES_COMPLEMENTO,  ");
		sql.append(" NOM_BAIRRO,  ");
		sql.append(" NOM_CIDADE,  ");
		sql.append(" SEQ_ESTADO,  ");
		sql.append(" SEQ_ATIVIDADE_CONVENIO,  "); 
		sql.append(" DES_CEP,  ");
		sql.append(" NOM_CONTATO,  ");
		sql.append(" DES_TELEFONE,  ");
		sql.append(" DES_TELEFONE_ADICIONAL,  "); 
		sql.append(" DES_FAX,  ");
		sql.append(" DES_EMAIL,  ");
		sql.append(" DES_BENEFICIO,  ");
		sql.append(" DES_CONVENIO,  ");
		sql.append(" NUM_DIA_FECHAMENTO,  ");
		sql.append(" NUM_FATOR_MES_VCTO,  ");
		sql.append(" NUM_DIA_VCTO,  "); 
		sql.append(" FLG_CONJUGE,  ");
		sql.append(" FLG_FILHOS,  ");
		sql.append(" FLG_DEPENDENTES,  ");
		sql.append(" FLG_OUTROS_BENEF,  ");
		sql.append(" FLG_TITULAR,  ");
		sql.append(" FLG_DESATIVADO )  ");
		sql.append(" VALUES  ");
		sql.append(" (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ");		
		
		SmartConnection 		sConn 	= null;
		SmartPreparedStatement 	sStmt 	= null;
		
		try {
		
			sConn 	= new SmartConnection(this.getConn());
			sStmt 	= new SmartPreparedStatement(sConn.prepareStatement(sql.toString()));
			
			vo.setCodigo(new Integer(getSequence("SEQ_CONVENIO").intValue()));
						
			
			sStmt.setParameters(vo, new String[]{
					"codigo", 
					"categoria", 
					"nomeFantasia",
					"razaoSocial",
					"cnpj",
					"logradouro",
					"complemento",
					"bairro",
					"cidade",
					"estado.codigo",
					"atividadeConvenio.codigo",
					"cep",
					"nomeContato",
					"telefone",
					"telefoneAdicional",
					"fax",
					"email",
					"beneficio",
					"descricao",
					"diaFechamento",
					"mesVencimento",
					"diaVencimento",
					"conjugue",
					"filhos",
					"dependentes",
					"outrosBeneficaiveis",
					"titular",
					"desativado"});
						
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
	 * Exclui um convênio do banco de dados. Tabelas: CONVENIO
	 * @param vo Instância da classe ConvenioVO com o código do registro a ser excluído.
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public void remove(ConvenioVO vo) throws SmartEnvException, SmartAppException {
		
		StringBuffer sql = new StringBuffer("DELETE FROM CONVENIO WHERE SEQ_CONVENIO = ? ");

		SmartConnection 		sConn 	= null;
		SmartPreparedStatement 	sStmt 	= null;
		
		try {
		
			sConn 	= new SmartConnection(this.getConn());
			sStmt 	= new SmartPreparedStatement(sConn.prepareStatement(sql.toString()));			
	
			sStmt.setInteger(1, vo.getCodigo());
						
			sStmt.getMyPreparedStatement().execute();
				
		} catch (SQLException e) {
			// Verificando se o erro foi por causa da violação de alguma FK.
			
			if (e.getMessage().indexOf("fk_convenio_plano") != -1 || e.getMessage().indexOf("fk_pc_convenio") != -1) {
				// Exceção levantada pela presença de contas.
				throw new SmartAppException("Não é possível excluir este convênio, pois há plano(s) associado(s) a ele.");
			}
			
			if (e.getMessage().indexOf("fk_convenio_fatura") != -1) {
				// Exceção levantada pela presença de contas.
				throw new SmartAppException("Não é possível excluir este convênio, pois há fatura(s) associada(s) a ele.");
			}			
			// Como não foi violação de FK, levantar exceção de ambiente.			
			throw new SmartEnvException(e);
		}finally {
			sStmt.close();
			sConn.close();
		}
	}		
	
	
	/**
	 * Obtém uma coleção de Convenio (encapsulado no objeto ConvenioVO) cadastrada no banco de dados. Tabelas: CONVENIO, ATIVIDADE_CONVENIO, ESTADO
	 * @param vo Instância da classe ConvenioVO contendo a propriedade de filtro preenchida.
	 * @return Retorna uma coleção de instâncias da classe ConvenioVO com as propriedades preenchidas.
	 * @throws SmartEnvException
	 */
	public Collection<ConvenioVO> findByFilter(ConvenioVO vo) throws SmartEnvException {
		
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT   ");
		sql.append(" SEQ_CONVENIO,  "); 
		sql.append(" FLG_CATEGORIA,  ");
		sql.append(" NOM_FANTASIA,  ");
		sql.append(" NOM_RAZAO_SOCIAL,  ");
		sql.append(" DES_CNPJ,  ");
		sql.append(" DES_LOGRADOURO,  ");
		sql.append(" DES_COMPLEMENTO,  ");
		sql.append(" NOM_BAIRRO,  ");
		sql.append(" NOM_CIDADE,  ");
		sql.append(" E.SEQ_ESTADO,  ");
		sql.append(" E.SIG_ESTADO, ");
		sql.append(" E.NOM_ESTADO,  ");
		sql.append(" AC.SEQ_ATIVIDADE_CONVENIO,  ");
		sql.append(" AC.NOM_ATIVIDADE_CONVENIO,  ");
		sql.append(" AC.FLG_DESATIVADO,  ");
		sql.append(" DES_CEP,  ");
		sql.append(" NOM_CONTATO,  ");
		sql.append(" DES_TELEFONE,  ");
		sql.append(" DES_TELEFONE_ADICIONAL,  ");
		sql.append(" DES_FAX,  ");
		sql.append(" DES_EMAIL,  ");
		sql.append(" DES_BENEFICIO,  ");
		sql.append(" DES_CONVENIO,  ");
		sql.append(" NUM_DIA_FECHAMENTO,  ");
		sql.append(" NUM_FATOR_MES_VCTO,  ");
		sql.append(" NUM_DIA_VCTO,   ");
		sql.append(" FLG_CONJUGE,  ");
		sql.append(" FLG_FILHOS,  ");
		sql.append(" FLG_DEPENDENTES,  ");
		sql.append(" FLG_OUTROS_BENEF,  ");
		sql.append(" FLG_TITULAR,  ");
		sql.append(" C.FLG_DESATIVADO  ");
		sql.append(" FROM ");
		sql.append(" CONVENIO C inner join ATIVIDADE_CONVENIO  AC on  C.SEQ_ATIVIDADE_CONVENIO = AC.SEQ_ATIVIDADE_CONVENIO ");
		sql.append(" left join ESTADO E  on C.SEQ_ESTADO = E.SEQ_ESTADO " );
		sql.append(" WHERE  ");
		sql.append(" ( ? IS NULL OR UPPER(C.NOM_FANTASIA) LIKE  '%' || UPPER(?) || '%') AND ");
		sql.append(" ( ? IS NULL OR C.FLG_CATEGORIA = ?) AND ");
		sql.append(" ( ? IS NULL OR AC.SEQ_ATIVIDADE_CONVENIO = ? ) AND  ");
		sql.append(" ( ? IS NULL OR C.FLG_DESATIVADO = ?)  ");
		sql.append(" ORDER BY NOM_FANTASIA ");

		

		SmartConnection 		sConn 	= null;
		SmartPreparedStatement 	sStmt 	= null;
		SmartResultSet			sRs		= null;
		
		try {
			sConn 	= new SmartConnection(this.getConn());
			sStmt 	= new SmartPreparedStatement(sConn.prepareStatement(sql.toString()));					

			sStmt.setParameters(vo, new String[] {
					"nomeFantasia", 
					"nomeFantasia",
					"categoria", 
					"categoria",
					"atividadeConvenio.codigo",
					"atividadeConvenio.codigo",
					"desativado",
					"desativado"
				});
			
			sRs = new SmartResultSet(sStmt.getMyPreparedStatement().executeQuery());
			
			return (Collection<ConvenioVO>) sRs.getJavaBeans(ConvenioVO.class, new String[]{
				"codigo", 
				"categoria", 
				"nomeFantasia",
				"razaoSocial",
				"cnpj",
				"logradouro",
				"complemento",
				"bairro",
				"cidade",
				"estado.codigo",
				"estado.sigla",
				"estado.nome",
				"atividadeConvenio.codigo",
				"atividadeConvenio.nome",
				"atividadeConvenio.desativo",
				"cep",
				"nomeContato",
				"telefone",
				"telefoneAdicional",
				"fax",
				"email",
				"beneficio",
				"descricao",
				"diaFechamento",
				"mesVencimento",
				"diaVencimento",
				"conjugue",
				"filhos",
				"dependentes",
				"outrosBeneficaiveis",
				"titular",
				"desativado"});
		} catch (SQLException e) {
			throw new SmartEnvException(e);
		} finally {
			sRs.close();
			sStmt.close();
			sConn.close();
		}
	}

	public Collection<ReportVitalmedVO> findReportVitalmed(ReportVitalmedVO report) throws SmartEnvException {
		
		StringBuffer sql = new StringBuffer();
		
		sql.append(" SELECT ");
		sql.append(" LPAD(SUBSTR(B.SEQ_BENEFICIARIO, 1, 15), 15) AS CODIGO_PESSOA_SISJUF, ");
		sql.append(" RPAD(SUBSTR(B.NOM_BENEFICIARIO, 1, 80), 80) AS NOME, ");
		sql.append(" CASE "); 
		sql.append(" WHEN B.TIPO = 'TITULAR' THEN '000000000000000' "); 
		sql.append(" ELSE LPAD(SUBSTR(A.SEQ_ASSOCIADO, 1, 15), 15) ");
		sql.append(" END AS TITULAR, ");
		sql.append(" A.NUM_CPF_ASSOCIADO || '' AS CPF, ");
		sql.append(" P.STS_SEXO_PESSOA AS SEXO, ");
		sql.append(" CASE ");
		sql.append(" WHEN A.STS_ESTADO_CIVIL_ASSOCIADO = 'C' THEN '2' ");
		sql.append(" WHEN A.STS_ESTADO_CIVIL_ASSOCIADO = 'S' THEN '1' ");
		sql.append(" WHEN (A.STS_ESTADO_CIVIL_ASSOCIADO = 'D' OR A.STS_ESTADO_CIVIL_ASSOCIADO = 'I') THEN '3' ");
		sql.append(" WHEN A.STS_ESTADO_CIVIL_ASSOCIADO = 'V' THEN '4' ");
		sql.append(" END AS ESTADO_CIVIL_TITULAR, ");
		sql.append(" to_char(P.DAT_NASCIMENTO_PESSOA, 'DDMMYYYY') AS NASCIMENTO, ");
		sql.append(" RPAD(' ', 50) AS ENDERECO, ");
		sql.append(" RPAD(' ', 25) AS BAIRRO, ");
		sql.append(" RPAD(' ', 17) AS CIDADE, ");
		sql.append(" RPAD(' ', 2) AS UF, ");
		sql.append(" RPAD(' ', 8) AS CEP, ");
		sql.append(" RPAD(' ', 10) AS TELEFONE1, ");
		sql.append(" RPAD(' ', 10) AS TELEFONE2, ");
		sql.append(" RPAD(' ', 1) AS ESTADO_VITALMED, ");
		sql.append(" RPAD(' ', 4) AS COD_BAIRRO_VITALMED, ");
		sql.append(" '1' AS MOVIMENTACAO, ");
		sql.append(" RPAD(SUBSTR(A.STR_VITALMED, 1, 10), 10) AS MATRICULA_VITALMED ");
		sql.append(" FROM VW_BENEFICIARIO B, PESSOA P, VW_ASSOCIADO A, VINCULACAO_PLANO VP ");
		sql.append(" WHERE B.SEQ_ASSOCIADO = VP.SEQ_PESSOA ");
		sql.append(" AND B.SEQ_BENEFICIARIO = P.SEQ_PESSOA ");
		sql.append(" AND B.SEQ_ASSOCIADO = A.SEQ_ASSOCIADO ");
		sql.append(" AND (VP.DAT_DESVINCULACAO IS NULL OR VP.DAT_DESVINCULACAO > ?) ");
		sql.append(" AND VP.DAT_VINCULACAO <= ? ");
		sql.append(" AND NOT EXISTS (SELECT COD_PESSOA_SISJUF FROM HIST_REPORT_VITALMED WHERE COD_PESSOA_SISJUF = LPAD(SUBSTR(B.SEQ_BENEFICIARIO, 1, 15), 15)) ");
		sql.append(" UNION ");
		sql.append(" SELECT ");
		sql.append(" LPAD(SUBSTR(B.SEQ_BENEFICIARIO, 1, 15), 15) AS CODIGO_PESSOA_SISJUF, ");
		sql.append(" RPAD(SUBSTR(B.NOM_BENEFICIARIO, 1, 80), 80) AS NOME, ");
		sql.append(" CASE "); 
		sql.append(" WHEN B.TIPO = 'TITULAR' THEN '000000000000000' "); 
		sql.append(" ELSE LPAD(SUBSTR(A.SEQ_ASSOCIADO, 1, 15), 15) ");
		sql.append(" END AS TITULAR, ");
		sql.append(" A.NUM_CPF_ASSOCIADO || '' AS CPF, ");
		sql.append(" P.STS_SEXO_PESSOA AS SEXO, ");
		sql.append(" CASE ");
		sql.append(" WHEN A.STS_ESTADO_CIVIL_ASSOCIADO = 'C' THEN '2' ");
		sql.append(" WHEN A.STS_ESTADO_CIVIL_ASSOCIADO = 'S' THEN '1' ");
		sql.append(" WHEN (A.STS_ESTADO_CIVIL_ASSOCIADO = 'D' OR A.STS_ESTADO_CIVIL_ASSOCIADO = 'I') THEN '3' ");
		sql.append(" WHEN A.STS_ESTADO_CIVIL_ASSOCIADO = 'V' THEN '4' ");
		sql.append(" END AS ESTADO_CIVIL_TITULAR, ");
		sql.append(" to_char(P.DAT_NASCIMENTO_PESSOA, 'DDMMYYYY') AS NASCIMENTO, ");
		sql.append(" RPAD(' ', 50) AS ENDERECO, ");
		sql.append(" RPAD(' ', 25) AS BAIRRO, ");
		sql.append(" RPAD(' ', 17) AS CIDADE, ");
		sql.append(" RPAD(' ', 2) AS UF, ");
		sql.append(" RPAD(' ', 8) AS CEP, ");
		sql.append(" RPAD(' ', 10) AS TELEFONE1, ");
		sql.append(" RPAD(' ', 10) AS TELEFONE2, ");
		sql.append(" RPAD(' ', 1) AS ESTADO_VITALMED, ");
		sql.append(" RPAD(' ', 4) AS COD_BAIRRO_VITALMED, ");
		sql.append(" '3' AS MOVIMENTACAO, ");
		sql.append(" RPAD(SUBSTR(A.STR_VITALMED, 1, 10), 10) AS MATRICULA_VITALMED ");
		sql.append(" FROM VW_BENEFICIARIO B, PESSOA P, VW_ASSOCIADO A, VINCULACAO_PLANO VP ");
		sql.append(" WHERE B.SEQ_ASSOCIADO = VP.SEQ_PESSOA ");
		sql.append(" AND B.SEQ_BENEFICIARIO = P.SEQ_PESSOA ");
		sql.append(" AND B.SEQ_ASSOCIADO = A.SEQ_ASSOCIADO ");
		sql.append(" AND (VP.DAT_DESVINCULACAO IS NULL OR VP.DAT_DESVINCULACAO > ?) ");
		sql.append(" AND VP.DAT_VINCULACAO <= ? ");
		sql.append(" AND EXISTS (SELECT COD_PESSOA_SISJUF FROM REPORT_VITALMED WHERE COD_PESSOA_SISJUF = LPAD(SUBSTR(B.SEQ_BENEFICIARIO, 1, 15), 15)) ");
		sql.append(" UNION ");
		sql.append(" SELECT ");
		sql.append(" LPAD(SUBSTR(B.SEQ_BENEFICIARIO, 1, 15), 15) AS CODIGO_PESSOA_SISJUF, ");
		sql.append(" RPAD(SUBSTR(B.NOM_BENEFICIARIO, 1, 80), 80) AS NOME, ");
		sql.append(" CASE "); 
		sql.append(" WHEN B.TIPO = 'TITULAR' THEN '000000000000000' "); 
		sql.append(" ELSE LPAD(SUBSTR(A.SEQ_ASSOCIADO, 1, 15), 15) ");
		sql.append(" END AS TITULAR, ");
		sql.append(" A.NUM_CPF_ASSOCIADO || '' AS CPF, ");
		sql.append(" P.STS_SEXO_PESSOA AS SEXO, ");
		sql.append(" CASE ");
		sql.append(" WHEN A.STS_ESTADO_CIVIL_ASSOCIADO = 'C' THEN '2' ");
		sql.append(" WHEN A.STS_ESTADO_CIVIL_ASSOCIADO = 'S' THEN '1' ");
		sql.append(" WHEN (A.STS_ESTADO_CIVIL_ASSOCIADO = 'D' OR A.STS_ESTADO_CIVIL_ASSOCIADO = 'I') THEN '3' ");
		sql.append(" WHEN A.STS_ESTADO_CIVIL_ASSOCIADO = 'V' THEN '4' ");
		sql.append(" END AS ESTADO_CIVIL_TITULAR, ");
		sql.append(" to_char(P.DAT_NASCIMENTO_PESSOA, 'DDMMYYYY') AS NASCIMENTO, ");
		sql.append(" RPAD(' ', 50) AS ENDERECO, ");
		sql.append(" RPAD(' ', 25) AS BAIRRO, ");
		sql.append(" RPAD(' ', 17) AS CIDADE, ");
		sql.append(" RPAD(' ', 2) AS UF, ");
		sql.append(" RPAD(' ', 8) AS CEP, ");
		sql.append(" RPAD(' ', 10) AS TELEFONE1, ");
		sql.append(" RPAD(' ', 10) AS TELEFONE2, ");
		sql.append(" RPAD(' ', 1) AS ESTADO_VITALMED, ");
		sql.append(" RPAD(' ', 4) AS COD_BAIRRO_VITALMED, ");
		sql.append(" '4' AS MOVIMENTACAO, ");
		sql.append(" RPAD(SUBSTR(A.STR_VITALMED, 1, 10), 10) AS MATRICULA_VITALMED ");
		sql.append(" FROM VW_BENEFICIARIO B, PESSOA P, VW_ASSOCIADO A, VINCULACAO_PLANO VP ");
		sql.append(" WHERE B.SEQ_ASSOCIADO = VP.SEQ_PESSOA ");
		sql.append(" AND B.SEQ_BENEFICIARIO = P.SEQ_PESSOA ");
		sql.append(" AND B.SEQ_ASSOCIADO = A.SEQ_ASSOCIADO ");
		sql.append(" AND (VP.DAT_DESVINCULACAO IS NULL OR VP.DAT_DESVINCULACAO > ?) "); 
		sql.append(" AND VP.DAT_VINCULACAO <= ? ");
		sql.append(" AND NOT EXISTS (SELECT COD_PESSOA_SISJUF FROM REPORT_VITALMED WHERE COD_PESSOA_SISJUF = LPAD(SUBSTR(B.SEQ_BENEFICIARIO, 1, 15), 15)) ");
		sql.append(" AND EXISTS (SELECT COD_PESSOA_SISJUF FROM HIST_REPORT_VITALMED WHERE COD_PESSOA_SISJUF = LPAD(SUBSTR(B.SEQ_BENEFICIARIO, 1, 15), 15)) ");
		sql.append(" UNION ");
		sql.append(" SELECT  COD_PESSOA_SISJUF, ");
		sql.append(" NOME, ");
		sql.append(" TITULAR, ");
		sql.append(" CPF, ");
		sql.append(" SEXO, ");
		sql.append(" ESTADO_CIVIL, ");
		sql.append(" TO_CHAR(NASCIMENTO, 'DDMMYYYY') AS NASC, ");
		sql.append(" RPAD(' ', 50) AS ENDERECO, ");
		sql.append(" RPAD(' ', 25) AS BAIRRO, ");
		sql.append(" RPAD(' ', 17) AS CIDADE, ");
		sql.append(" RPAD(' ', 2) AS UF, ");
		sql.append(" RPAD(' ', 8) AS CEP, ");
		sql.append(" RPAD(' ', 10) AS TELEFONE1, ");
		sql.append(" RPAD(' ', 10) AS TELEFONE2, ");
		sql.append(" RPAD(' ', 1) AS ESTADO_VITALMED, ");
		sql.append(" RPAD(' ', 4) AS COD_BAIRRO_VITALMED, ");
		sql.append(" '2' AS MOVIMENTACAO, "); 
		sql.append(" MATRICULA_VITALMED ");
		sql.append(" FROM REPORT_VITALMED RV ");
		sql.append(" WHERE NOT EXISTS ( ");
		sql.append(" SELECT 1 "); 
		sql.append(" FROM VW_BENEFICIARIO B1, PESSOA P1, VW_ASSOCIADO A1, VINCULACAO_PLANO VP1 ");
		sql.append(" WHERE B1.SEQ_ASSOCIADO = VP1.SEQ_PESSOA ");
		sql.append(" AND B1.SEQ_BENEFICIARIO = P1.SEQ_PESSOA ");
		sql.append(" AND B1.SEQ_ASSOCIADO = A1.SEQ_ASSOCIADO ");
		sql.append(" AND (VP1.DAT_DESVINCULACAO IS NULL OR VP1.DAT_DESVINCULACAO > ?) "); 
		sql.append(" AND VP1.DAT_VINCULACAO <= ? ");
		sql.append(" AND LPAD(SUBSTR(B1.SEQ_BENEFICIARIO, 1, 15), 15) = RV.COD_PESSOA_SISJUF ");
		sql.append(" ) ");


		SmartConnection 		sConn 	= null;
		SmartPreparedStatement 	sStmt 	= null;
		SmartResultSet			sRs		= null;
		
		try {
			sConn 	= new SmartConnection(this.getConn());
			sStmt 	= new SmartPreparedStatement(sConn.prepareStatement(sql.toString()));					

			sStmt.setParameters(report, new String[] {
					"dataFiltroAte", 
					"dataFiltroDe",
					"dataFiltroAte", 
					"dataFiltroDe",
					"dataFiltroAte",
					"dataFiltroDe",
					"dataFiltroAte",
					"dataFiltroDe"
				});
			
			sRs = new SmartResultSet(sStmt.getMyPreparedStatement().executeQuery());
			
			return (Collection<ReportVitalmedVO>) sRs.getJavaBeans(ReportVitalmedVO.class, new String[]{
				"codigoPessoaSisjuf",
				"nome",
				"titular",
				"cpf",
				"sexo",
				"estadoCivil",
				"nascimento",
				"endereco",
				"bairro",
				"cidade",
				"uf",
				"cep",
				"telefone1",
				"telefone2",
				"estadoVitalmed",
				"codigoBairroVitalmed",
				"movimentacao",
				"matriculaVitalmed"
				});
		} catch (SQLException e) {
			throw new SmartEnvException(e);
		} finally {
			sRs.close();
			sStmt.close();
			sConn.close();
		}
	}

	public void removeReportVitalmed() throws SmartEnvException {

		StringBuffer sql = new StringBuffer("DELETE FROM REPORT_VITALMED ");

		SmartConnection 		sConn 	= null;
		SmartPreparedStatement 	sStmt 	= null;
		
		try {
		
			sConn 	= new SmartConnection(this.getConn());
			sStmt 	= new SmartPreparedStatement(sConn.prepareStatement(sql.toString()));			
						
			sStmt.getMyPreparedStatement().execute();
				
		} catch (SQLException e) {
			// Verificando se o erro foi por causa da violação de alguma FK.
		
			throw new SmartEnvException(e);
		}finally {
			sStmt.close();
			sConn.close();
		}
	}

	public void insert(ReportVitalmedVO dado) throws SmartEnvException {

		StringBuffer sql = new StringBuffer("  ");
		sql.append(" INSERT INTO REPORT_VITALMED   ");  
		sql.append(" (	COD_PESSOA_VITALMED,  ");
		sql.append(" 	NOME,  ");
		sql.append(" 	TITULAR,  ");
		sql.append(" 	CPF,  ");
		sql.append(" 	SEXO,  ");
		sql.append(" 	ESTADO_CIVIL,  ");
		sql.append(" 	NASCIMENTO,  ");
		sql.append(" 	MOVIMENTACAO,  ");
		sql.append(" 	MATRICULA_VITALMED )  ");
		sql.append(" 	VALUES  ");
		sql.append(" (?,?,?,?,?,?,?,?,?) ");		
		
		SmartConnection 		sConn 	= null;
		SmartPreparedStatement 	sStmt 	= null;
		
		try {
		
			sConn 	= new SmartConnection(this.getConn());
			sStmt 	= new SmartPreparedStatement(sConn.prepareStatement(sql.toString()));
			
			sStmt.setParameters(dado, new String[]{
					"codigoPessoaSisjuf",
					"nome",
					"titular",
					"cpf",
					"sexo",
					"estadoCivil",
					"nascimento",
					"movimentacao",
					"matriculaVitalmed"	
				});
						
			sStmt.getMyPreparedStatement().execute();
			
			
				
		} catch (SQLException e) {
			throw new SmartEnvException(e);
		}finally {
			sStmt.close();
			sConn.close();
		}
		
	}	
}
