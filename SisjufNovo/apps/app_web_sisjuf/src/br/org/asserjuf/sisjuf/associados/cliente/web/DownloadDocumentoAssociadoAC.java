package br.org.asserjuf.sisjuf.associados.cliente.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.FrameworkServlet;

public class DownloadDocumentoAssociadoAC extends FrameworkServlet {

	@Override
	protected void doService(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		/*
		 String filename = rs.getString("filename");
         byte[] filedata = rs.getBytes("filedata");

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
         */
	}

}
