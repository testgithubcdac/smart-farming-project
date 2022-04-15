<%@ page import= "Model.*" %>
<%@ page import= "java.util.*" %>

<%
	Qcomments qcommentObj = new Qcomments();
	String emp_id = (String) session.getAttribute("login_emp_id");
	if((request.getParameter("act")).equals("Save"))
	{
		HashMap results = new HashMap();
		results.put("qcomment_user_id",emp_id);
		results.put("qcomment_question_id",request.getParameter("question_id"));
		results.put("qcomment_name",request.getParameter("qcomment_name"));
		results.put("qcomment_email",request.getParameter("qcomment_email"));
		results.put("qcomment_description",request.getParameter("qcomment_description"));
		out.println(qcommentObj.saveQcomments(results));
		response.sendRedirect("../question-details.jsp?question_id="+request.getParameter("question_id")+"&msg=Qcomment Saved Successfully");
	}
%>
