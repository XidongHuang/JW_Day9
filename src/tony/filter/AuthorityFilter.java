package tony.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tony.authority.Authority;
import tony.authority.User;

@WebFilter("*.jsp")
public class AuthorityFilter extends HttpFilter {

	@Override
	public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		
		// 1. Gain serveltPath
		String servletPath = request.getServletPath();

		// Those urls which do not need to be checked
		List<String> uncheckedUrls = Arrays.asList("/403.jsp", "/articles.jsp", "/authority-manager.jsp", "/login.jsp",
				"/logout.jsp");

		if (uncheckedUrls.contains(servletPath)) {
			chain.doFilter(request, response);
			return;
		}

		// User is login already, gain user information
		// -->(session.getAttribute("user")

		User user = (User) request.getSession().getAttribute("user");
		if (user == null) {
			response.sendRedirect(request.getContextPath() + "/login.jsp");
			return;
		}

		List<Authority> authorities = user.getAuthorities();
		
		
		Authority authority = new Authority(null, servletPath);
		
		for(Authority a:authorities){
			
			if (a.getURL().equals(authority.getURL())) {
				chain.doFilter(request, response);
				return;
				
			}
		}
		

		response.sendRedirect(request.getContextPath() + "/403.jsp");

	}
}
