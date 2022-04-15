package Model;

import java.util.*;
import java.sql.*;
import com.*;
import java.io.*;

public class Pcomments extends Connect
{
    /////Function for connect to the MySQL Server Database////////////
	public Pcomments()
    {
		Connect.connect_mysql();
    }
	//////////Save User Details /////
	public String savePcomments(HashMap ticketData)
	{
		String SQL = "INSERT INTO `pcomments` (`pcomment_product_id`, `pcomment_name`, `pcomment_email`, `pcomment_description`) VALUES (?, ?, ?, ?);";
		int record=0; 
		String error = "";
		
		try
		{
			pstmt = connection.prepareStatement(SQL);
			pstmt.setString(1,(String) ticketData.get("pcomment_product_id"));
			pstmt.setString(2,(String) ticketData.get("pcomment_name"));
			pstmt.setString(3,(String) ticketData.get("pcomment_email"));
			pstmt.setString(4,(String) ticketData.get("pcomment_description"));
			
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
    public ArrayList getAllPcomments(String product_id)
	{
		String SQL = "SELECT * FROM `pcomments` WHERE pcomment_product_id = "+product_id+" ORDER BY pcomment_id ASC";
		int count=0;
        ArrayList resultArray = new ArrayList();
        try
		{
			statement = connection.createStatement();
            rs = statement.executeQuery(SQL);
            while(rs.next())
			{		
				HashMap results = new HashMap();
				results.put("pcomment_description",rs.getString("pcomment_description"));
				results.put("pcomment_name",rs.getString("pcomment_name"));
				results.put("pcomment_email",rs.getString("pcomment_email"));
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
