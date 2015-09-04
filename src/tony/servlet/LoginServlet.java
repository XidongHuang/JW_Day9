package tony.servlet;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tony.authority.User;
import tony.dao.UserDao;

@WebServlet("/loginServlet")
public final class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UserDao userDao = new UserDao();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String methodName = request.getParameter("method");

		try {
			Method method = getClass().getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);

			method.invoke(this, request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	
	
	public void login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//1. Gain username
		String name = request.getParameter("name");
		
		//2. Invoke UserDao to gain user information, add information into HttpSession
		User user = userDao.get(name);
		request.getSession().setAttribute("user", user);
		
		//3. Redirect articles.jsp
		response.sendRedirect(request.getContextPath()+"/articles.jsp");
	}
	
	public void logout(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getSession().invalidate();
		
		response.sendRedirect(request.getContextPath()+"/login.jsp");
	}
	
	
	

}
