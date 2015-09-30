package ch.fhnw.webfr.flashcard.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;


@WebFilter(urlPatterns="/*")
public class BasicFilter implements Filter {

	private static final String preLog = "Before request [uri=";
	private static final String postLog = "]";
	private Logger logger = Logger.getLogger(BasicFilter.class);

	@Override
	public void init(FilterConfig filterConfig) throws ServletException { }

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		logger.debug(String.format("%s%s%s", preLog, ((HttpServletRequest)request).getRequestURI(), postLog));
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() { }

}
