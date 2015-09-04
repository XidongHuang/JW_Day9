package tony.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.DocumentFilter;


/**
 * Customizing a HttpFilter to achieve Filter interface
 * 
 */

public abstract class HttpFilter implements Filter {

	
	/**
	 * For storing FilterConfig object
	 */
	private FilterConfig fConfig;
	
	
	/**
	 * empty
	 * 
	 */
	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	
	/**
	 * Return init(ServletConfig)'s FilterConfig object
	 * 
	 * @return
	 */
	public FilterConfig getFilterConfig(){
		return fConfig;
	}
	
	
	/**
	 * Original doFilter method, it change ServletRequest arg0, ServletResponse arg1
	 * into HttpServletRequest and HttpServletResponse and invoke doFilter()
	 * 
	 * If compile Filter's filter method, do not advice inherited this method but 
	 * inherited doFilter(HttpServletRequest request,HttpServletResponse response,
	 *		FilterChain chain)
	 */
	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)arg0;
		HttpServletResponse response = (HttpServletResponse)arg1;
		FilterChain chain = (FilterChain)arg2;
		doFilter(request, response, chain);
	}
	
	
	/**
	 * Abstract method, for Http request specific, must be achieved 
	 * 
	 * @param request
	 * @param response
	 * @param chain
	 * @throws IOException
	 * @throws ServletException
	 */
	public abstract void doFilter(HttpServletRequest request,HttpServletResponse response,
			FilterChain chain) throws IOException, ServletException;
	
	
	/**
	 * Do not advice subclass override it directly, if do so, the member parameters
	 * might cannot be initialized 
	 * 
	 */
	@Override
	public void init(FilterConfig arg0) throws ServletException {

		this.fConfig = arg0;
		init();
		
	}
	
	
	/**
	 * For subclass to inherited. Gain FilterConfig object by getFilterConfig()
	 * 
	 */
	protected void init() {}

}
