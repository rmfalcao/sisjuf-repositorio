package com.vortice.seguranca.abstracao.cliente;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.myfaces.renderkit.html.util.AddResource;
import org.apache.myfaces.renderkit.html.util.AddResourceFactory;
import org.apache.myfaces.webapp.filter.ExtensionsResponseWrapper;

public class ExtensionFilter implements Filter {

	private Logger log = Logger.getLogger(ExtensionFilter.class);
	  
    private ServletContext _servletContext;

    public static final String DOFILTER_CALLED = "org.apache.myfaces.component.html.util.ExtensionFilter.doFilterCalled";
    
    public void init(FilterConfig filterConfig) {
      
        _servletContext = filterConfig.getServletContext();
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        if(request.getAttribute(DOFILTER_CALLED)!=null)
        {
            chain.doFilter(request, response);
            return;
        }

        request.setAttribute(DOFILTER_CALLED,"true");

        if (!(response instanceof HttpServletResponse)) {
            chain.doFilter(request, response);
            return;
        }

        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        HttpServletRequest extendedRequest = httpRequest;

        // Serve resources
        AddResource addResource;

        try
        {
            addResource=AddResourceFactory.getInstance(httpRequest);
            if( addResource.isResourceUri(_servletContext, httpRequest ) ){
                addResource.serveResource(_servletContext, httpRequest, httpResponse);
                return;
            }
        }
        catch(Throwable th)
        {
            log.error("Exception wile retrieving addResource",th);
            throw new ServletException(th);
        }

        try
        {
        	addResource.responseStarted();
        	
	        if (addResource.requiresBuffer())
	        {
		        ExtensionsResponseWrapper extendedResponse = new ExtensionsResponseWrapper((HttpServletResponse) response);
		
		        // Standard request
		        chain.doFilter(extendedRequest, extendedResponse);
		
		        extendedResponse.finishResponse();
		
		        // write the javascript stuff for myfaces and headerInfo, if needed
		        HttpServletResponse servletResponse = (HttpServletResponse)response;
		
		        // only parse HTML responses
		        if (extendedResponse.getContentType() != null && isValidContentType(extendedResponse.getContentType()))
		        {
		            addResource.parseResponse(extendedRequest, extendedResponse.toString(),
		                    servletResponse);
		
		            addResource.writeMyFacesJavascriptBeforeBodyEnd(extendedRequest,
		                    servletResponse);
		
		            if( ! addResource.hasHeaderBeginInfos() ){
		                // writes the response if no header info is needed
		                addResource.writeResponse(extendedRequest, servletResponse);
		                return;
		            }
		
		            // Some headerInfo has to be added
		            addResource.writeWithFullHeader(extendedRequest, servletResponse);
		
		            // writes the response
		            addResource.writeResponse(extendedRequest, servletResponse);
		        }
		        else
		        {

		        	byte[] responseArray = extendedResponse.getBytes();

                    if(responseArray.length > 0)
                    {
 			        	// When not filtering due to not valid content-type, deliver the byte-array instead of a charset-converted string.
 			        	// Otherwise a binary stream gets corrupted.
 			            servletResponse.getOutputStream().write(responseArray);
 		        	}
                }
	        }
	        else
	        {
		        chain.doFilter(extendedRequest, response);
	        }
        }
        finally
        {
        	addResource.responseFinished();        	
        }
    }

    public boolean isValidContentType(String contentType)
    {
	    return contentType.startsWith("text/html") ||
	            contentType.startsWith("text/xml") ||
	            contentType.startsWith("application/xhtml+xml") ||
	            contentType.startsWith("application/xml");
    }

    /**
     * Destroy method for this filter
     */
    public void destroy() {
        // NoOp
    }

}