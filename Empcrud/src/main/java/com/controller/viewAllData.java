package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.employee;
import com.dao.empDao;

@WebServlet("/viewAllData")
public class viewAllData extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		List<employee> l = null;
		try {
			l = empDao.getAllData();
			System.out.println(l);
		} catch (ClassNotFoundException | SQLException ae) {
			// TODO Auto-generated catch block
			ae.printStackTrace();
		}
		out.print("<h1 align='center'> All Data of Employee </h1>");
		out.print("<table border=1 align='center'>");
		out.print("<tr><th>Id</th><th>Name</th><th>Email</th><th>Contact</th><th>Password</th><th>Update</th><th>Delete</th></tr>");
		for (employee e : l) {

			out.println("<tr><td>" + e.getId() + "</td><td>" + e.getName() + "</td><td>" + e.getEmail() + "</td><td>"
					+ e.getContact() + "</td><td>" + e.getPassword() + "</td><td><a href='updateServlet?id="+ e.getId()+"'> Update </a></td><td><a href='deleteServlet?id="+ e.getId()+"'> Delete </a></td></tr>");
		}
		out.print("</table>");
	}

}
