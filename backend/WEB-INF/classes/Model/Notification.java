package Model;

import java.util.*;
import java.sql.*;
import com.*;
import java.io.*;

public class Notification extends Connect
{
    /////Function for connect to the MySQL Server Database////////////
	public Notification()
    {
		Connect.connect_mysql();
    }
	//////////Save User Details /////
	public String saveNotification(HashMap notificationData)
	{
		String SQL = "INSERT INTO `notification` (`notification_title`, `notification_date`, `notification_image`, `notification_description`) VALUES (?, ?, ?, ?);";
		int record=0; 
		String error = "";
		
		try
		{
			pstmt = connection.prepareStatement(SQL);
			pstmt.setString(1,(String) notificationData.get("notification_title"));
			pstmt.setString(2,(String) notificationData.get("notification_date"));
			pstmt.setString(3,(String) notificationData.get("notification_image"));
			pstmt.setString(4,(String) notificationData.get("notification_description"));
			
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
    public HashMap getNotificationDetails(int notification_id)
	{
        HashMap results = new HashMap();
        int count=0;
		try
		{
			String SQL = "SELECT * FROM `notification` WHERE notification_id = "+notification_id ;
            statement = connection.createStatement();
            rs = statement.executeQuery(SQL);
            while(rs.next())
			{
				results.put("notification_title",rs.getString("notification_title"));
				results.put("notification_date",rs.getString("notification_date"));
				results.put("notification_description",rs.getString("notification_description"));
				results.put("notification_id",rs.getString("notification_id"));
				results.put("notification_image",rs.getString("notification_image"));
				
				count++;
            }
			if(count==0)
			{
				results.put("notification_title","");
				results.put("notification_date","");
				results.put("notification_description","");
				results.put("notification_image","");
				results.put("notification_id","");	
			}
         }
		 catch(Exception e)
		 {
            System.out.println("Error is: "+ e);
       	 }
        return results;
    }
    /// Update the Notification ////
	public String updateNotification(HashMap notificationData)
	{
		String SQL = "UPDATE `notification` SET `notification_title` = ?, `notification_date` = ?, `notification_image` = ?, `notification_description` = ? WHERE `notification_id` = ?;";
		String error = "";
		
		int record=0;	
		
		try
		{
			pstmt = connection.prepareStatement(SQL);
			pstmt.setString(1,(String) notificationData.get("notification_title"));
			pstmt.setString(2,(String) notificationData.get("notification_date"));
			pstmt.setString(3,(String) notificationData.get("notification_image"));
			pstmt.setString(4,(String) notificationData.get("notification_description"));
			pstmt.setString(5,(String) notificationData.get("notification_id"));
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
    public ArrayList getAllNotification(int typeID)
	{
		int count=0;
		String SQL = "SELECT * FROM `notification`";
		
        ArrayList resultArray = new ArrayList();
        try
		{
			statement = connection.createStatement();
            rs = statement.executeQuery(SQL);
            while(rs.next())
			{		
				HashMap results = new HashMap();
				results.put("notification_title",rs.getString("notification_title"));
				results.put("notification_date",rs.getString("notification_date"));
				results.put("notification_description",rs.getString("notification_description"));
				results.put("notification_id",rs.getString("notification_id"));
				results.put("notification_image",rs.getString("notification_image"));
				
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
    /////Function for Getting the List////////////
	public String getTypeOption(Integer SelID)
    {
		int selectedID = SelID.intValue();
    	return Connect.getOptionList("type","type_id","type_name","type_id,type_name",selectedID,"1");
    }
}
