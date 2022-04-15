import java.io.File;
import java.io.IOException;
import Model.*;
import java.util.*;
 
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
 
@WebServlet("/UploadNotification")
@MultipartConfig(fileSizeThreshold=1024*1024*2, // 2MB
                 maxFileSize=1024*1024*10,      // 10MB
                 maxRequestSize=1024*1024*50)   // 50MB
public class UploadNotification extends HttpServlet {
    private static final String SAVE_DIR = "notificationImages";
     
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//// Logic for Upload the File ////
        String appPath = request.getServletContext().getRealPath("");
        String savePath = appPath + File.separator + SAVE_DIR;
        File fileSaveDir = new File(savePath);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdir();
        }
		long unixTime = System.currentTimeMillis() / 1000L;
	
		Part part;
        part = request.getPart("notification_image");
        String fileName = extractFileName(part);
        if(!fileName.equals("")) {
			fileName = unixTime+"_"+extractFileName(part);
			part.write(savePath + File.separator + fileName);
		} else {
			fileName = request.getParameter("image_name");
		}
		//// Upload File Complete///
		
		/////Save the Notification Details /////
		Notification notificationObj = new Notification();
		String emp_id = "0";
		if((request.getParameter("act")).equals("Save"))
		{
			HashMap results = new HashMap();

			results.put("notification_title",request.getParameter("notification_title"));
			results.put("notification_date",request.getParameter("notification_date"));
			results.put("notification_description",request.getParameter("notification_description"));
			results.put("notification_id",request.getParameter("notification_id"));
			results.put("notification_image",fileName);
					
			if((request.getParameter("notification_id")).equals(""))
			{
				notificationObj.saveNotification(results);
				request.setAttribute("message", "Notification Saved Successfully !!!!");
				getServletContext().getRequestDispatcher("/report-notification.jsp").forward(request, response);
			}
			else
			{
				results.put("notification_id",request.getParameter("notification_id"));
				notificationObj.updateNotification(results);
				request.setAttribute("message", "Notification Updated Successfully !!!!");
				getServletContext().getRequestDispatcher("/report-notification.jsp").forward(request, response);
			}
		}
    }
    /**
     * Extracts file name from HTTP header content-disposition
     */
    private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length()-1);
            }
        }
        return "";
    }
}
