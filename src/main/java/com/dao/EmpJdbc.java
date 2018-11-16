package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.List;

import com.model.Employee;


public class EmpJdbc {
	private Connection con;
	private PreparedStatement ps;
	
	public Connection myConnection() {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","Newuser123");
			System.out.println("Connection to db...");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		return con;
	}
	
	public int saveData(List<Employee> lst)
	{
		//System.out.println("dbsave1");
		int i=0;
		//Account a=lst.get(0);
		Employee a=null;
		con=myConnection();
		try
		{
			Iterator<Employee> itr=lst.iterator();
			//System.out.println("dbsave2");
			while(itr.hasNext())
			{
				//System.out.println("dbsave3");
				a=itr.next();
				ps=con.prepareStatement("insert into emp values(?,?)");
				ps.setString(1,a.getName());
				ps.setInt(2,a.getEmpid());
		
				
				
				
				i = ps.executeUpdate();
			}
		}
		catch(Exception e)
		{
			//System.out.println(e);
		}
		finally
		{
			try
			{
				con.close();
			} 
			catch (SQLException e) 
			{
				//e.printStackTrace();
			}
		}
		return i;
	}
}
