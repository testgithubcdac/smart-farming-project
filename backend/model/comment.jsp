<%@ page import= "Model.*" %>
<%@ page import= "java.util.*" %>

<%
	Comments commentObj = new Comments();
	String emp_id = (String) session.getAttribute("login_emp_id");
	if((request.getParameter("act")).equals("Save"))
	{
		HashMap results = new HashMap();
		results.put("comment_user_id",emp_id);
		results.put("comment_notification_id",request.getParameter("notification_id"));
		results.put("comment_name",request.getParameter("comment_name"));
		results.put("comment_email",request.getParameter("comment_email"));
		results.put("comment_description",request.getParameter("comment_description"));
		out.println(commentObj.saveComments(results));
		response.sendRedirect("../notification-details.jsp?notification_id="+request.getParameter("notification_id")+"&msg=Comment Saved Successfully");
	}
%>
