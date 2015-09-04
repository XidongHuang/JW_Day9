package tony.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

import tony.dao.MyHttpServletRequest;


@WebFilter("/bbs.jsp")
public class ContentFilter extends HttpFilter {

	@Override
	public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		String content = request.getParameter("content");
		
		HttpServletRequest req = new MyHttpServletRequest(request);
		
		if(content != null){
			
			
			chain.doFilter(req, response);
			return;
		}
		
		
	}

 
}
