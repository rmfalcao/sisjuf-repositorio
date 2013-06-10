package br.org.asserjuf.sisjuf.associados.convenio;


import java.util.Date;

public class FaturaFiltroAssembler extends FaturaVO {

	private Date	dataInicioDe;
	private Date	dataInicioAte;
	private Date	dataFimDe;
	private Date	dataFimAte;
	
	public Date getDataInicioDe() {
		return dataInicioDe;
	}
	

	
	public void setDataInicioDe(Date dataInicioDe) {
		this.dataInicioDe = dataInicioDe;
	}

	public Date getDataFimDe() {
		return dataFimDe;
	}
	public void setDataFimDe(Date dataFimDe) {
		this.dataFimDe = dataFimDe;
	}
	public Date getDataInicioAte() {
		return dataInicioAte;
	}
	public void setDataInicioAte(Date dataInicioAte) {
		this.dataInicioAte = dataInicioAte;
	}
	public Date getDataFimAte() {
		return dataFimAte;
	}
	public void setDataFimAte(Date dataFimAte) {
		this.dataFimAte = dataFimAte;
	}

	
	public String getStrDataInicioDe() {
		if (dataInicioDe != null) {
			return dataInicioDe.toString();
		}
			
		return null;
		
	}
	
	public String getStrDataInicioAte() {
		if (dataInicioAte != null) {
			return dataInicioAte.toString();
		}
			
		return null;
		
	}
	
	public String getStrDataFimDe() {
		if (dataFimDe != null) {
			return dataFimDe.toString();
		}
			
		return null;
		
	}
	
	public String getStrDataFimAte() {
		if (dataFimAte != null) {
			return dataFimAte.toString();
		}
			
		return null;
		
	}
	
	
}