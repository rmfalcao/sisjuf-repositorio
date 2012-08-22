package br.org.asserjuf.sisjuf.financeiro;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DiretorFinanceiroVO implements Serializable {

	private Integer codigo;
	private String	nome;
	private Date	dataInicioGestao;

	public Integer getCodigo() {
		return codigo;
	}
	
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	
	public Date getDataInicioGestao() {
		return dataInicioGestao;
	}
	
	public void setDataInicioGestao(Date dataInicioGestao) {
		this.dataInicioGestao = dataInicioGestao;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getDataInicioGestaoFormatada() 
	{
		if (dataInicioGestao != null) 
		{
			SimpleDateFormat formatter	= new SimpleDateFormat("dd/MM/yyyy");
			return formatter.format(dataInicioGestao);
		}
		return "";
	}
}