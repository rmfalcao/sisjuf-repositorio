package com.vortice.seguranca.cliente.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.vortice.seguranca.abstracao.cliente.SegurancaDelegateIf;
import com.vortice.seguranca.vo.FuncaoVO;
import com.vortice.seguranca.vo.FuncionalidadeVO;
import com.vortice.seguranca.vo.LinkVO;
import com.vortice.seguranca.vo.PerfilVO;
import com.vortice.seguranca.vo.UsuarioVO;

public class SegurancaFilter implements Filter {

	private String 							loginPage;
	private String 							indexPage;
	private String 							desenv;
    private ServletContext 					context = null;
    private SegurancaDelegateIf 			delegate;
    private String							strLinksLiberados;
    
    
    private static final Logger 		LOG = Logger.getLogger(SegurancaFilter.class);
	
	public void destroy() {}
	
	public void init(FilterConfig config) throws ServletException {
		loginPage = config.getServletContext().getInitParameter("LOGIN_PAGE");
		indexPage = config.getServletContext().getInitParameter("INDEX_PAGE");
		desenv = config.getServletContext().getInitParameter("DESENV");
		strLinksLiberados = config.getServletContext().getInitParameter("LINKS_LIBERADOS");
		this.context = config.getServletContext();
	}
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest)request;
		HttpSession sessao = httpRequest.getSession();
		//httpRequest.setCharacterEncoding("ISO-8859-1");
		//httpRequest.setCharacterEncoding("UTF-8");
		//System.out.println("httpRequest.getServletPath() " + httpRequest.getServletPath());
		//System.out.println("httpRequest.getRequestURL() " + httpRequest.getRequestURL());
		String servletPath = httpRequest.getServletPath();
		
		if (servletPath.indexOf("?") > 0) servletPath = servletPath.substring(0, servletPath.indexOf("?"));

		boolean linkLiberado = false;
		if (strLinksLiberados != null && !"".equals(strLinksLiberados)){
			String[] arrayLinksLiberados = strLinksLiberados.split(";");
			for (String link : arrayLinksLiberados){
				if (servletPath.indexOf(link) > 0){
					linkLiberado = true;
				}
			}
		}
		
		if (!linkLiberado){
			if (servletPath.indexOf("dwr") <  0){
				String ext = servletPath.substring(servletPath.length()-3, servletPath.length());
				String login = servletPath.substring(servletPath.length()-9, servletPath.length());
				String mudarSenha = (servletPath.length() >=14) ? servletPath.substring(servletPath.length()-14, servletPath.length()) : "";
				if (!ext.equals("css") && !ext.equals("gif") && !ext.equals("jpg") && !ext.equals("png") && !ext.equals(".js")){
					if (!login.equals("login.jsf") && !login.equals("login.jsp")){
						if (!mudarSenha.equals("mudarSenha.jsp") && !mudarSenha.equals("mudarSenha.jsf")){
							if (servletPath.indexOf("applet") < 0){//Só filtra se o diretorio passado nao tiver applet
								if(desenv.equals("0")){
									UsuarioVO usuario = (UsuarioVO)sessao.getAttribute("usuario");
									
									if (usuario == null){//TESTO SE O USUÁRIO NÃO EXISTE NA SESSÃO
						                LOG.debug("Usuario não existe na sessão.");
						                request.getRequestDispatcher(loginPage).forward(request, response);
						                
									}else{//CASO EXISTA
										WebApplicationContext webAppContext = WebApplicationContextUtils.getWebApplicationContext(context);
										delegate = (SegurancaDelegateIf)webAppContext.getBean("segurancaDelegate");
										
	//									System.out.println("delegate" + delegate);
	//									System.out.println("delegate.getUsuarioRN() " + delegate.getUsuarioRN());
	//									System.out.println("delegate.getFuncaoRN() " + delegate.getFuncaoRN());
	//									System.out.println("delegate.getFuncionalidadeRN() " + delegate.getFuncionalidadeRN());
	//									System.out.println("delegate.getLinkRN() " + delegate.getLinkRN());
	//									System.out.println("delegate.getPerfilRN() " + delegate.getPerfilRN());
										
						                String acaoSeguranca = request.getParameter("acao");
						                boolean permitiContinuar = false;
						                Collection collFuncionalidadesUsuario = usuario.getFuncionalidades();
					                	 
						                //usuario.getPerfil().getFuncionalidades();
						                Collection collFuncionalidadesPerfil = null;
						                
						                try{
						                	collFuncionalidadesPerfil = delegate.findFuncionalidadeByPerfis(usuario.getPerfis());
						                }catch(Exception e){
						                	LOG.error("Erro na hora de buscar as funcionalidades pelo perfil", e);
						                	collFuncionalidadesPerfil = new ArrayList<PerfilVO>();
						                }
					                	
						                if (acaoSeguranca != null){//SE A ACAO QUE VAI EXECUTAR ESTA NUMA VARIAVEL
						                	permitiContinuar = testaFuncionalidadesDescricaoFuncao(collFuncionalidadesPerfil, acaoSeguranca);
						                	if (!permitiContinuar){
						                		permitiContinuar = testaFuncionalidadesDescricaoFuncao(collFuncionalidadesUsuario, acaoSeguranca);
						                		if (!permitiContinuar) {
						                			LOG.debug("LINK PROIBIDO : " + servletPath);
						                			System.out.println("LINK PROIBIDO : " + servletPath);
						                			proibeContiuacao(request, response);	
						                		}
						                	}
						                }else{//SE A ACAO QUE VAI EXECUTAR NÃO ESTA NUMA VARIAVEL SETA O LINK COMO A AÇÃO
						                	acaoSeguranca = httpRequest.getServletPath();
						                	permitiContinuar = testaFuncionalidadesLinkFuncao(collFuncionalidadesPerfil, acaoSeguranca);
						                	if (!permitiContinuar){
						                		permitiContinuar = testaFuncionalidadesLinkFuncao(collFuncionalidadesUsuario, acaoSeguranca);
						                		if (!permitiContinuar) {
						                			LOG.debug("LINK PROIBIDO : " + servletPath);
						                			System.out.println("LINK PROIBIDO : " + servletPath);
						                			proibeContiuacao(request, response);			                		
						                		}
						                	}
						                }
						                
						                if (permitiContinuar) chain.doFilter(request, response);
									}
								} else if (desenv.equals("2")){//O proprio filtro vai salvar o link caso o mesmo não exista na base.
									LinkVO linkVO = new LinkVO(servletPath);
									Collection collLink = null;
									try{
										collLink = delegate.findByFilter(linkVO);
										if (collLink == null || collLink.size() <= 0){
											delegate.insert(linkVO);
										}
									}catch(Exception e){
										e.printStackTrace();
									}
									chain.doFilter(request, response);
								}else{
									UsuarioVO usuario = (UsuarioVO)sessao.getAttribute("usuario");
									if (usuario == null){
										usuario = new UsuarioVO(new Integer(1));
										sessao.setAttribute("usuario", usuario);
									}
									chain.doFilter(request, response);
								}
							}else{
								chain.doFilter(request, response);
							}
						}else{
							chain.doFilter(request, response);
						}
					}else{
						chain.doFilter(request, response);
					}
				}else{
					chain.doFilter(request, response);
				}
			}else{
				chain.doFilter(request, response);
			}
		}else{
			chain.doFilter(request, response);
		}
	}
	
	private boolean testaFuncionalidadesDescricaoFuncao(Collection collFuncionalidades, String acaoSeguranca){
		boolean retorno = false;
		if (collFuncionalidades != null){
			Iterator iteratorFuncionalidades = collFuncionalidades.iterator();
			while (iteratorFuncionalidades.hasNext()){
				FuncionalidadeVO funcionalidade = (FuncionalidadeVO)iteratorFuncionalidades.next();
				retorno =  testaDescricaoFuncao(funcionalidade.getFuncoes(), acaoSeguranca); 
				if (retorno) return retorno;
			}
		}
		return false;
	}
	
	private boolean testaFuncionalidadesLinkFuncao(Collection collFuncionalidades, String acaoSeguranca){
		boolean retorno = false;
		if (collFuncionalidades != null){
			Iterator iteratorFuncionalidades = collFuncionalidades.iterator();
			while (iteratorFuncionalidades.hasNext()){
				FuncionalidadeVO funcionalidade = (FuncionalidadeVO)iteratorFuncionalidades.next();
				retorno = testaLinkFuncao(funcionalidade.getFuncoes(), acaoSeguranca);
				if (retorno) return retorno;
			}
		}
		return false;
	}
	
	private boolean testaDescricaoFuncao(Collection collFuncoes, String acaoSeguranca){
		if (collFuncoes != null){
			Iterator iteratorFuncoes = collFuncoes.iterator();
			while(iteratorFuncoes.hasNext()){
				FuncaoVO funcao = (FuncaoVO)iteratorFuncoes.next();
				if (funcao.getDescricao() != null && funcao.getDescricao().equals(acaoSeguranca)) return true;			
			}
		}
		return false;
	}
	
	private boolean testaLinkFuncao(Collection collFuncoes, String acaoSeguranca){
		if (collFuncoes != null){
			Iterator iteratorFuncoes = collFuncoes.iterator();
			while(iteratorFuncoes.hasNext()){
				FuncaoVO funcao = (FuncaoVO)iteratorFuncoes.next();
				if (funcao.getLink() != null && funcao.getLink().getUrl() != null){
					if (funcao.getLink().getUrl().equals(acaoSeguranca)) return true;
				}
			}
		}
		return false;
	}
	
	private void proibeContiuacao(ServletRequest request, ServletResponse response) throws ServletException, IOException{
		StringBuffer script = new StringBuffer();
        script.append("<script> \n");
        script.append("  alert('Você não tem permissão para executar esta função.'); \n");
        script.append("</script> ");
        request.setAttribute("msgP", script.toString());
        LOG.debug("alert('Você não tem permissão para executar esta função.'); ");
        request.getRequestDispatcher(indexPage).forward(request, response);
	}
}