package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.employee;
import com.dao.empDao;

@WebServlet("/saveServlet")
public class saveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String nameString = request.getParameter("name");
		String emailString = request.getParameter("email");
		long contact = 	Long.parseLong(request.getParameter("contact"));
		String passString = request.getParameter("pass");
		
		employee emp = new employee();
		
		emp.setName(nameString);
		emp.setEmail(emailString);
		emp.setContact(contact);
		emp.setPassword(passString);
		empDao edDao = new empDao();
		
		int i = 0;
		try {
			i = edDao.insertion(emp);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(i > 0) {
			
			response.sendRedirect("login.html");
//			out.println("Record Inserted");
		}else {
			RequestDispatcher rs = request.getRequestDispatcher("employee.html");
			out.println("Registration Not Completed");
			rs.include(request, response);
		}
	}

}
