package br.org.asserjuf.sisjuf.associados.cliente.web;

import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeansException;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.FrameworkServlet;

import br.org.asserjuf.sisjuf.associados.DocumentoAssociadoVO;
import br.org.asserjuf.sisjuf.associados.cliente.AssociadoDelegate;

public class DownloadDocumentoAssociadoAC extends FrameworkServlet {

	private AssociadoDelegate delegate;
	
	@Override
	protected void doService(HttpServletRequest request, HttpServletResponse response) throws Exception {
		

		DocumentoAssociadoVO documento = new DocumentoAssociadoVO();
		documento.setCodigo(Integer.parseInt(request.getParameter("codigo")));
		
		documento = delegate.findByPrimaryKey(documento);
		
		
		 String filename = documento.getNomeDoArquivo();
         byte[] filedata = documento.getFileData();

         // Set the response content type to the file's MIME type
         String contentType = getServletContext().getMimeType(filename);
         response.setContentType(contentType);

         // Set the Content-Disposition header to trigger a "Save As" dialog in the user's browser
         response.setHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");

         // Write the file data to the servlet response output stream
         OutputStream outputStream = response.getOutputStream();
         outputStream.write(filedata);
         outputStream.flush();
         outputStream.close();
         
		
		
	}
	
	protected void initFrameworkServlet() throws ServletException, BeansException {
		WebApplicationContext context = getWebApplicationContext();
		
		delegate = (AssociadoDelegate)context.getBean("associadoDelegate");
	}
	
	protected final WebApplicationContext createWebApplicationContext(WebApplicationContext parent) throws BeansException{
    	return parent;
   	}

}
