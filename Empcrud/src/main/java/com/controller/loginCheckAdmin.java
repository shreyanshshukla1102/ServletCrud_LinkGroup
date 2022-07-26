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

import org.apache.catalina.startup.PasswdUserDatabase;

import com.bean.employee;
import com.dao.empDao;

@WebServlet("/loginCheckAdmin")
public class loginCheckAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out= response.getWriter();
		String email = request.getParameter("email");
		String userPass = request.getParameter("pass");

		if(email.equals("admin@gmail.com")&& userPass.equals("admin"))
		{
			RequestDispatcher rs = request.getRequestDispatcher("viewAllData");
			rs.forward(request, response);
		}
		else
		{
			RequestDispatcher rs = request.getRequestDispatcher("adminLogin.html");
			out.print("<h1 align='center'>Please check your Credentials !!</h1>");
			rs.include(request, response);
		}
	}

}
