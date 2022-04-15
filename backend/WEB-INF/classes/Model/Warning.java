package Model;

import java.util.*;
import java.sql.*;
import com.*;
import java.io.*;

public class Warning extends Connect
{
    /////Function for connect to the MySQL Server Database////////////
	public Warning()
    {
		Connect.connect_mysql();
    }
	//////////Save User Details /////
	public String saveWarning(HashMap warningData)
	{
		String SQL = "INSERT INTO `warning` (`warning_user_id`, `warning_description`) VALUES (?, ?);";
		int record=0; 
		String error = "";
		
		try
		{
			pstmt = connection.prepareStatement(SQL);
			pstmt.setString(1,(String) warningData.get("warning_user_id"));
			pstmt.setString(2,(String) warningData.get("warning_description"));
			
			record = pstmt.executeUpdate();
			pstmt.close();
			connection.close();
		}
		catch(Exception e)
		{
			StringWriter writer = new StringWriter();
			PrintWriter printWriter = new PrintWriter( writer );
			e.printStackTrace( printWriter );
			printWriter.flush();
			String stackTrace = writer.toString();
			error+="Error : "+stackTrace;
			System.out.println(" Error : "+ e.toString());
		}
		return error;
	}
	//////////////////Function for getting Users Details//////////	
    public HashMap getWarningDetails(int warning_id)
	{
        HashMap results = new HashMap();
        int count=0;
		try
		{
            String SQL =  "SELECT * FROM `warning` WHERE warning_id = "+warning_id ;
            statement = connection.createStatement();
            rs = statement.executeQuery(SQL);
            while(rs.next())
			{
				results.put("warning_id",rs.getString("warning_id"));
				results.put("warning_user_id",rs.getString("warning_user_id"));
				results.put("warning_description",rs.getString("warning_description"));
				count++;
            }
			if(count==0)
			{
				results.put("warning_id","");
				results.put("warning_user_id","");
				results.put("warning_description","");
			}
         }
		 catch(Exception e)
		 {
            System.out.println("Error is: "+ e);
       	 }
        return results;
    }
    /// Update the Warning ////
	public String updateWarning(HashMap warningData)
	{
		String SQL = "UPDATE `warning` SET `warning_user_id` = ?, `warning_description` = ? WHERE `warning_id` = ?;";
		String error = "";
		
		int record=0;	
		
		try
		{
			pstmt = connection.prepareStatement(SQL);
			
			pstmt.setString(1,(String) warningData.get("warning_user_id"));
			pstmt.setString(2,(String) warningData.get("warning_description"));
			pstmt.setString(3,(String) warningData.get("warning_id"));
			
			record = pstmt.executeUpdate();
			pstmt.close();
			connection.close();
		}
		catch(Exception e)
		{
			StringWriter writer = new StringWriter();
			PrintWriter printWriter = new PrintWriter( writer );
			e.printStackTrace( printWriter );
			printWriter.flush();
			String stackTrace = writer.toString();
			error+="Error : "+stackTrace;
			System.out.println(" Error : "+ e.toString());
		}
		return error;
	}
	
	////////////////Function for getting all the Airport Details////////////////////  
    public ArrayList getAllWarning(String employee_id)
	{
		String SQL = "SELECT * FROM `warning`, `employee` WHERE warning_user_id = employee_id AND warning_user_id = "+employee_id;
		int count=0;
        ArrayList resultArray = new ArrayList();
        try
		{			
			statement = connection.createStatement();
            rs = statement.executeQuery(SQL);
            while(rs.next())
			{		
				HashMap results = new HashMap();
				results.put("warning_id",rs.getString("warning_id"));
				results.put("warning_user_id",rs.getString("warning_user_id"));
				results.put("employee_first_name",rs.getString("employee_first_name"));
				results.put("warning_description",rs.getString("warning_description"));
				
				count++;
                resultArray.add(results);
            }
         }
		catch(Exception e)
		{
            System.out.println("Error is: "+ e);
        }
        return resultArray;
    }
}
