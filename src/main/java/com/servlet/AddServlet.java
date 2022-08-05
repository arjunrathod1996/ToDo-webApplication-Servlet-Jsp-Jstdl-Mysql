package com.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.DAO.TodoDAO;
import com.DB.DBConnect;

@WebServlet("/add_todo")
public class AddServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
			String username = req.getParameter("username");
			String todo = req.getParameter("todo");
			String status = req.getParameter("status");
			
			TodoDAO dao = new TodoDAO(DBConnect.getConn());
			boolean f = dao.addTodo(username, todo, status);
			
			HttpSession session = req.getSession();
			
			if(f) {
				/*System.out.println("Data Insert Sucessfully"); */
				session.setAttribute("sucMsg", "Todo Added Sucessfully");
				resp.sendRedirect("index.jsp");
			}else {
			   /*	System.out.println("Error");  */
				session.setAttribute("failedMsg",  "Something Went Wrong");
				resp.sendRedirect("index.jsp");
			}
		
	}
	
	
	
}
