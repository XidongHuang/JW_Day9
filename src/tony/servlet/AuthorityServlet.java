package tony.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tony.authority.Authority;
import tony.authority.User;
import tony.dao.UserDao;



@WebServlet("/authorityServlet")
public class AuthorityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String methodName = request.getParameter("method");
		
		try {
			Method method = getClass().getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);

			method.invoke(this, request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private UserDao userDao = new UserDao();
	
	public void getAuthorities(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String username = request.getParameter("username");
		User user = userDao.get(username);
		System.out.println(user.getAuthorities());
		request.setAttribute("user", user);
		request.setAttribute("authorities", userDao.getAuthorities());
		
		request.getRequestDispatcher("/authority-manager.jsp").forward(request, response);
	}
	
	
	public void updateAuthorities(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		System.out.println("UpdateA test1");
		String username = request.getParameter("username");
		String[] authorities = request.getParameterValues("authority");
		List<Authority> authorities2 = userDao.getAuthorities(authorities);
		
		userDao.update(username,authorities2);
		response.sendRedirect(request.getContextPath()+"/authority-manager.jsp");
		
	}
	
	
}