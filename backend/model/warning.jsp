<%@ page import= "Model.*" %>
<%@ page import= "java.util.*" %>

<%
	Warning warningObj = new Warning();
	String emp_id = (String) session.getAttribute("login_emp_id");
	if((request.getParameter("act")).equals("Save"))
	{
		HashMap results = new HashMap();
		results.put("warning_user_id",request.getParameter("warning_user_id"));
		results.put("warning_description",request.getParameter("warning_description"));
		out.println(warningObj.saveWarning(results));
		response.sendRedirect("../report-warning.jsp?user_id="+request.getParameter("warning_user_id")+"&msg=Warning Saved Successfully");
	}
%>
