package br.org.asserjuf.sisjuf.services;



import br.com.falc.smartFW.exception.SmartEnvException;
import br.org.asserjuf.sisjuf.financeiro.web.cliente.FinanceiroDelegate;

public class ServiceCorrigeLancamentosDuplicados extends Thread {

	private 		FinanceiroDelegate 						financeiroDelegate;
	private static 	ServiceCorrigeLancamentosDuplicados 	instance = null;
	
	public static ServiceCorrigeLancamentosDuplicados getInstance(FinanceiroDelegate delegate) {
		if (instance != null) {
			return instance;
		}
		
		return new ServiceCorrigeLancamentosDuplicados(delegate);
	}
	
	private ServiceCorrigeLancamentosDuplicados(FinanceiroDelegate delegate) {
		this.financeiroDelegate = delegate;
	}
	
	public void run() {
		
		try {
		
			int i = 0;
			
			while (true) {
				
					if (i%10 == 0) {
						System.out.println("[ServiceCorrigeLancamentosDuplicados RUNNING...]");
					}
					
					
					financeiroDelegate.removeLancamentosDuplicados();	
				
					sleep(10000);
		
					i++;
		
					
			}
			
		} catch (InterruptedException e) {
			e.printStackTrace();
			
		} catch (SmartEnvException e) {
			e.printStackTrace();	
		}
	
	}
	
}