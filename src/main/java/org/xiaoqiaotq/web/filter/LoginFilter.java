package org.xiaoqiaotq.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author xiaoqiaotq@gmail.com	
 * @date   2015年1月16日  
 */
public class LoginFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req=(HttpServletRequest)request;
		String url=req.getRequestURI();
		System.err.println(req.getRequestURI());
		if(url.startsWith("/login")||url.startsWith("/resources")){
			chain.doFilter(request, response);
			return;
		}
		if(req.getSession().getAttribute("user")==null){
			HttpServletResponse resp=(HttpServletResponse) response;
			if(url.startsWith("/login")){
				resp.sendRedirect(req.getContextPath()+"/login");
				return;
			}
			resp.sendRedirect(req.getContextPath()+"/login?url="+url);
			return;
		}
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
	}

}
