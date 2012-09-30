package br.org.asserjuf.sisjuf.services;

import br.org.asserjuf.sisjuf.financeiro.web.cliente.FinanceiroDelegate;

public class ServiceCorrigeLancamentosDuplicados extends Thread {

	public void run() {
		while (true) {
			
			FinanceiroDelegate financeiroDelegate	= new FinanceiroDelegate();
			
			//TODO
			/*
				financeiroDelegate.removeLancamentosDuplicados();
			
			  no RN terá algo do tipo:
			    
			    coll = findLancamentosDuplicados();
			    
				for (int i=0; i < coll.size(); i++) {
				
					this.removeBaixaLancamento();
					this.removeLancamento();
					this.removeHistoricoSaldo(data_referencia);
					
					sysout "encontrado algo para remover. sysdate".
				
				}
			
			
			*/
			try {
				sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
