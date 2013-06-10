package br.org.asserjuf.sisjuf.util;

import java.util.Calendar;
import java.util.Date;

import br.com.falc.smartFW.exception.SmartAppException;
import br.com.falc.smartFW.exception.SmartEnvException;
import br.org.asserjuf.sisjuf.util.dados.DataDAO;

public class DataRN {

	private DataDAO dataDAO;

	
		 
	public void setDataDAO(DataDAO dataDAO) {
		this.dataDAO = dataDAO;
	}

	public Date getCurrentDate() throws SmartEnvException, SmartAppException {
		
		return dataDAO.getCurrentDate();
		
	}
	
	public Date addDays(Date date, int dias) {
		      
		   Calendar calendar = Calendar.getInstance();
		   calendar.setTime(date);
		   calendar.add(Calendar.DATE, dias);

		   return calendar.getTime();
		   
	}
	
}
