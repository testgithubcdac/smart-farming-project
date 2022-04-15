package Model;

import java.util.*;
import java.sql.*;
import com.*;
import java.io.*;

public class Question extends Connect
{
    /////Function for connect to the MySQL Server Database////////////
	public Question()
    {
		Connect.connect_mysql();
    }
	//////////Save User Details /////
	public String saveQuestion(HashMap questionData)
	{
		String SQL = "INSERT INTO `question` (`question_title`, `question_user_id`, `question_description`) VALUES (?, ?, ?);";
		int record=0; 
		String error = "";
		
		try
		{
			pstmt = connection.prepareStatement(SQL);
			pstmt.setString(1,(String) questionData.get("question_title"));
			pstmt.setString(2,(String) questionData.get("question_user_id"));
			pstmt.setString(3,(String) questionData.get("question_description"));
			
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
    public HashMap getQuestionDetails(int question_id)
	{
        HashMap results = new HashMap();
        int count=0;
		try
		{
            String SQL =  "SELECT * FROM `question` WHERE question_id = "+question_id ;
            statement = connection.createStatement();
            rs = statement.executeQuery(SQL);
            while(rs.next())
			{
				results.put("question_id",rs.getString("question_id"));
				results.put("question_title",rs.getString("question_title"));
				results.put("question_description",rs.getString("question_description"));
				count++;
            }
			if(count==0)
			{
				results.put("question_id","");
				results.put("question_title","");
				results.put("question_description","");
			}
         }
		 catch(Exception e)
		 {
            System.out.println("Error is: "+ e);
       	 }
        return results;
    }
    /// Update the Question ////
	public String updateQuestion(HashMap questionData)
	{
		String SQL = "UPDATE `question` SET `question_title` = ?, `question_description` = ? WHERE `question_id` = ?;";
		String error = "";
		
		int record=0;	
		
		try
		{
			pstmt = connection.prepareStatement(SQL);
			
			pstmt.setString(1,(String) questionData.get("question_title"));
			pstmt.setString(2,(String) questionData.get("question_description"));
			pstmt.setString(3,(String) questionData.get("question_id"));
			
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
    public ArrayList getAllQuestion(int employee_id)
	{
		String SQL = "SELECT * FROM `question`, `employee` WHERE employee_id = question_user_id";
		if(employee_id !=0 ) {
			SQL = "SELECT * FROM `question`, `employee` WHERE employee_id = question_user_id AND employee_id = "+employee_id;
		}
		int count=0;
        ArrayList resultArray = new ArrayList();
        try
		{			
			statement = connection.createStatement();
            rs = statement.executeQuery(SQL);
            while(rs.next())
			{		
				HashMap results = new HashMap();
				results.put("question_id",rs.getString("question_id"));
				results.put("question_title",rs.getString("question_title"));
				results.put("employee_first_name",rs.getString("employee_first_name"));
				results.put("question_description",rs.getString("question_description"));
				
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
