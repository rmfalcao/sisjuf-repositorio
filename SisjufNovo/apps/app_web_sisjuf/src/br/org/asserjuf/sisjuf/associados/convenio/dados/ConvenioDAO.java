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
	
	
	public Collection<ConvenioVO> findRubricas() throws SmartEnvException {
		
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT   ");
		sql.append(" C.SEQ_CONVENIO,  "); 
		sql.append(" C.DES_RUBRICAS_ARQUIVO_SEPAG  ");
		sql.append(" FROM ");
		sql.append(" CONVENIO C ");
		sql.append(" WHERE  ");
		sql.append(" C.DES_RUBRICAS_ARQUIVO_SEPAG is not null  ");
		sql.append(" ORDER BY C.DES_RUBRICAS_ARQUIVO_SEPAG ");

		

		SmartConnection 		sConn 	= null;
		SmartPreparedStatement 	sStmt 	= null;
		SmartResultSet			sRs		= null;
		
		try {
			sConn 	= new SmartConnection(this.getConn());
			sStmt 	= new SmartPreparedStatement(sConn.prepareStatement(sql.toString()));					

						
			sRs = new SmartResultSet(sStmt.getMyPreparedStatement().executeQuery());
			
			return (Collection<ConvenioVO>) sRs.getJavaBeans(ConvenioVO.class, new String[]{
				"codigo", 
				"rubricas"});
		} catch (SQLException e) {
			throw new SmartEnvException(e);
		} finally {
			sRs.close();
			sStmt.close();
			sConn.close();
		}
	}	
	
}
