package controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
import java.io.IOException;

import model.UserManager;
import model.User;

import java.sql.SQLException;

public class DashboardServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
		throws ServletException, IOException{

		try{
			if(req.getSession().getAttribute("username") != null){
				User user = UserManager.getUser((String)req.getSession().getAttribute("username"));
				req.setAttribute("user",user);
				req.getRequestDispatcher("WEB-INF/dashboard.jsp").forward(req, resp);			
			}else{
				resp.sendRedirect("/signin?next="+req.getRequestURI());
			}
		}catch(SQLException sql){
			req.getRequestDispatcher("WEB-INF/404.jsp").forward(req,resp);
			return;
		}
	}
}