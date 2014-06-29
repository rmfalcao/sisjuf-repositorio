package br.org.asserjuf.sisjuf.associados.dados;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import br.com.falc.smartFW.exception.SmartEnvException;
import br.com.falc.smartFW.persistence.SmartConnection;
import br.com.falc.smartFW.persistence.SmartPreparedStatement;
import br.com.falc.smartFW.persistence.SmartResultSet;
import br.org.asserjuf.sisjuf.associados.AssociadoAssembler;
import br.org.asserjuf.sisjuf.associados.AssociadoVO;
import br.org.asserjuf.sisjuf.associados.HistoricoAssociadoVO;
import br.org.asserjuf.sisjuf.associados.HistoricoFiltroAssembler;
import br.org.asserjuf.sisjuf.associados.TipoEventoVO;
import br.org.asserjuf.sisjuf.dados.SisjufDAOPostgres;

public class HistoricoAssociadoDAO extends SisjufDAOPostgres {
	
	public HistoricoAssociadoVO insert(HistoricoAssociadoVO historicoAssociado) throws SmartEnvException {
		
		StringBuffer sql = new StringBuffer(" insert into historico_evento_associado ")
		.append(" (seq_associado, seq_tipo_evento, dat_historico_evento_associado, seq_historico_evento_associado) ")
		.append(" values (?,?,?,(select nextval('seq_historico_evento_associado'))) ");

		SmartConnection 		sConn 	= null;
		SmartPreparedStatement 	sStmt 	= null;
				
		try {
		
			sConn 	= new SmartConnection(this.getConn());
			sStmt 	= new SmartPreparedStatement(sConn.prepareStatement(sql.toString()));

			sStmt.setParameters(historicoAssociado, new String[] {"associado.codigo", "tipoEvento.codigo" , "data"});
			
			sStmt.getMyPreparedStatement().execute();
			
			return historicoAssociado;
		} catch (SQLException e) {
			throw new SmartEnvException(e);
		} finally {

			sStmt.close();
			sConn.close();
		}
	}

	public Collection<HistoricoAssociadoVO> findByFilter(HistoricoFiltroAssembler assembler) throws SmartEnvException {
		
		StringBuffer sql = new StringBuffer("select hea.seq_associado, a.nom_associado, ")
			.append("te.nom_tipo_evento, hea.dat_historico_evento_associado ")
			.append("from historico_evento_associado hea ")
			.append("inner join vw_associado a on hea.seq_associado = a.seq_associado ")
			.append("inner join tipo_evento te on hea.seq_tipo_evento = te.seq_tipo_evento ")
			.append("where (hea.seq_tipo_evento = ? or ? is null) ")
			.append("and (hea.dat_historico_evento_associado >= ? or ? is null) ")
			.append("and (hea.dat_historico_evento_associado <= ? or ? is null) ");
		
		SmartConnection 		sConn 	= null;
		SmartPreparedStatement 	sStmt 	= null;
		SmartResultSet			sRs		= null;
		
		try {
		
			sConn 	= new SmartConnection(this.getConn());
			sStmt 	= new SmartPreparedStatement(sConn.prepareStatement(sql.toString()));
			
			sStmt.setParameters(assembler, new String[] {"tipoEvento.codigo", 
														"tipoEvento.codigo", 
														"dataInicio", 
														"dataInicioStr", 
														"dataFim", 
														"dataFimStr"});
			sRs 	= new SmartResultSet(sStmt.getMyPreparedStatement().executeQuery());
			
			ArrayList<HistoricoAssociadoVO> retorno = new ArrayList<HistoricoAssociadoVO>(); 
			
			while (sRs.next()) {

				HistoricoAssociadoVO historico = new HistoricoAssociadoVO();

				AssociadoVO associado = new AssociadoVO();
				TipoEventoVO tipoEvento = new TipoEventoVO();
				
				associado.setCodigo(sRs.getInteger(1));
				associado.setNome(sRs.getString(2));
				historico.setAssociado(associado);
				tipoEvento.setNome(sRs.getString(3));
				historico.setTipoEvento(tipoEvento);
				historico.setData(sRs.getDate(4));				

				retorno.add(historico);
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

	public HistoricoAssociadoVO findUltimoNaoCancelado(
			AssociadoAssembler associado) throws SmartEnvException {
		StringBuffer sql = new StringBuffer(" select max(seq_historico_evento_associado) from historico_evento_associado ") 
									.append(" where seq_associado = ? ")
									.append(" and 	seq_tipo_evento in (1,3) ");
		
		SmartConnection 		sConn 	= null;
		SmartPreparedStatement 	sStmt 	= null;
		SmartResultSet			sRs		= null;
		
		try {
		
			sConn 	= new SmartConnection(this.getConn());
			sStmt 	= new SmartPreparedStatement(sConn.prepareStatement(sql.toString()));
			
			sStmt.setParameters(associado, new String[] {"codigo"});
			
			sRs 	= new SmartResultSet(sStmt.getMyPreparedStatement().executeQuery());
			
			
			return (HistoricoAssociadoVO) sRs.getJavaBean(new HistoricoAssociadoVO(), new String[] {"codigo"});
		} catch (SQLException e) {
			throw new SmartEnvException(e);
		} finally {
			sRs.close();
			sStmt.close();
			sConn.close();
		}
	}

	public void updateDate(HistoricoAssociadoVO historico) throws SmartEnvException {
		StringBuffer sql = new StringBuffer(" update historico_evento_associado set dat_historico_evento_associado = ? where seq_historico_evento_associado = ?");

		SmartConnection 		sConn 	= null;
		SmartPreparedStatement 	sStmt 	= null;
				
		try {
		
			sConn 	= new SmartConnection(this.getConn());
			sStmt 	= new SmartPreparedStatement(sConn.prepareStatement(sql.toString()));

			sStmt.setParameters(historico, new String[] {"data", "codigo"});
			
			sStmt.getMyPreparedStatement().execute();
			
		} catch (SQLException e) {
			throw new SmartEnvException(e);
		} finally {

			sStmt.close();
			sConn.close();
		}
		
	}
}