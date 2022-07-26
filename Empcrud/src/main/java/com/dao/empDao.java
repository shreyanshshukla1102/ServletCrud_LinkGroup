package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.bean.employee;

public class empDao {
	public static Connection connect() throws ClassNotFoundException, SQLException {

		Class.forName("com.mysql.cj.jdbc.Driver");
		// Creating a connection

		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/linkgroup", "root", "");
		return con;

	}
	
	public static int insertion(employee e) throws ClassNotFoundException, SQLException {
		Connection connection = connect();
		String sql = "Insert into employee (name,email, contact,pass) values (?,?,?,?)";
		PreparedStatement statement = connection.prepareStatement(sql);
		
		statement.setString(1, e.getName());
		statement.setString(2, e.getEmail());
		statement.setLong(3, e.getContact());
		statement.setString(4, e.getPassword());

		int i = statement.executeUpdate();
		connection.close();
		return i;
	}

	public static boolean loginCheck(employee e) throws ClassNotFoundException, SQLException
	{
		boolean result = false;
		Connection con= connect();
		PreparedStatement ps = con.prepareStatement("select * from employee where email=? and pass=?");
		ps.setString(1, e.getEmail());
		ps.setString(2, e.getPassword());
		ResultSet rs = ps.executeQuery();
		result = rs.next();
		return result;
	}
	
	public static List<employee> getAllData() throws ClassNotFoundException, SQLException
	{
		List<employee> l = new ArrayList<employee>();
		Connection con= connect();
		Statement st = con.createStatement();
		String sql = "select * from employee";
		ResultSet rs = st.executeQuery(sql);
		while(rs.next())
		{
			employee e = new employee();
			e.setId(rs.getInt(1));
			e.setName(rs.getString(2));
			e.setEmail(rs.getString(3));
			e.setContact(rs.getLong(4));
			e.setPassword(rs.getString(5));
			
			l.add(e);
			
		}
		return l;
	}
}
