package br.org.asserjuf.sisjuf.associados;

import br.com.falc.smartFW.communication.email.SmartMail;
import br.com.falc.smartFW.exception.SmartAppException;
import br.com.falc.smartFW.exception.SmartEnvException;
import br.org.asserjuf.sisjuf.util.ParametroRN;
import br.org.asserjuf.sisjuf.util.ParametroVO;

public class EmailRN {
	
	private ParametroRN	parametroRN;
	
	public void send(EmailVO email) throws SmartEnvException, SmartAppException {

		this.validate(email);
	
		SmartMail smartMail = new SmartMail();
		
		smartMail.setSmtp(parametroRN.findByPrimaryKey(new ParametroVO("SMTP_SERVER")).getValorTextual());	
		smartMail.setUser(parametroRN.findByPrimaryKey(new ParametroVO("SMTP_USER_ACCOUNT")).getValorTextual());// colocar aqui conta do email *sem @dominio
		smartMail.setPassword(parametroRN.findByPrimaryKey(new ParametroVO("SMTP_USER_PWD")).getValorTextual());// colocar aqui senha do email
		smartMail.setSubject(email.getAssunto());
		smartMail.setBody(email.getCorpo());
		smartMail.setFromAddress(parametroRN.findByPrimaryKey(new ParametroVO("EMAIL_SISJUF")).getValorTextual());// colocar aqui conta do email *com @dominio
		
		smartMail.setGmailParameters(false);

		StringBuffer destinatarios	= new StringBuffer();
		
		for (AssociadoVO associadoVO : email.getAssociados()) {
			destinatarios.append(associadoVO.getEmail()).append(";");
		}

		smartMail.setToBccAddress(destinatarios.toString());
		smartMail.send();
	}
	
	private void validate(EmailVO email) throws SmartEnvException, SmartAppException {
		
		if (email == null) {
			throw new SmartAppException("Objeto de e-mail não identificado.");
		} 
		
		if (email.getAssunto() == null || email.getAssunto().equals("")) {
			throw new SmartAppException("O assunto do e-mail deve ser informado.");
		}
		
		if (email.getCorpo() == null || email.getCorpo().equals("")) {
			throw new SmartAppException("O corpo do e-mail deve ser preenchido.");
		}
		
		if (email.getAssociados() == null || email.getAssociados().size() == 0) {
			throw new SmartAppException("Nenhum destinatário foi encontrado.");
		}
	}

	public void setParametroRN(ParametroRN parametroRN) {
		this.parametroRN = parametroRN;
	}
}