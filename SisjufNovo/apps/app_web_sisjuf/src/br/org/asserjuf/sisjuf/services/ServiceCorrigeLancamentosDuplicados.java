package br.org.asserjuf.sisjuf.services;



import br.com.falc.smartFW.exception.SmartEnvException;
import br.org.asserjuf.sisjuf.financeiro.web.cliente.FinanceiroDelegate;

public class ServiceCorrigeLancamentosDuplicados extends Thread {

	private FinanceiroDelegate financeiroDelegate;
	
	public ServiceCorrigeLancamentosDuplicados(FinanceiroDelegate delegate) {
		this.financeiroDelegate = delegate;
	}
	
	public void run() {
		
		try {
		
		
			
			while (true) {
				
				
				
					System.out.println("loop service...");
					financeiroDelegate.removeLancamentosDuplicados();	
				
					sleep(10000);
					
		
					
				}
		} catch (InterruptedException e) {
			e.printStackTrace();
			
		} catch (SmartEnvException e) {
			e.printStackTrace();	
		}
	
	}
	
}