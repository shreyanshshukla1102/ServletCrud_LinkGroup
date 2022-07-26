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

@WebServlet("/welcomeEmployee")
public class welcomeEmployee extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out= response.getWriter();
		String email = request.getParameter("email");
		String userPass = request.getParameter("pass");
		empDao dao = new empDao();
		employee e = new employee();
		e.setEmail(email);
		e.setPassword(userPass);
		boolean status = false;
		try {
			status = dao.loginCheck(e);
			System.out.println(status);
		} catch (ClassNotFoundException | SQLException ae) {
			// TODO Auto-generated catch block
			ae.printStackTrace();
		}
		if(status == true)
		{
			RequestDispatcher rs = request.getRequestDispatcher("welcomeServlet");
			rs.forward(request, response);
		}
		else
		{
			RequestDispatcher rs = request.getRequestDispatcher("login.html");
			out.print("<h1 align='center'>Please check your Credentials !!</h1>");
			rs.include(request, response);
		}
	}

}
