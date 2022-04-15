<%@ page import= "Model.*" %>
<%@ page import= "java.util.*" %>

<%
	Question questionObj = new Question();
	String emp_id = (String) session.getAttribute("login_emp_id");
	if(session.getAttribute("login_level") != null && session.getAttribute("login_level").equals("3")) {
		emp_id = (String) session.getAttribute("login_emp_id");
	}
	if((request.getParameter("act")).equals("Save"))
	{
		HashMap results = new HashMap();
	
		results.put("question_id",request.getParameter("question_id"));
		results.put("question_user_id",emp_id);
		results.put("question_title",request.getParameter("question_title"));
		results.put("question_description",request.getParameter("question_description"));

		if((request.getParameter("question_id")).equals(""))
		{
			out.println(questionObj.saveQuestion(results));
			response.sendRedirect("../report-question.jsp?emp_id="+emp_id+"&msg=Food Category Saved Successfully");
		}
		else
		{
			results.put("question_id",request.getParameter("question_id"));
			out.println(questionObj.updateQuestion(results));
			response.sendRedirect("../report-question.jsp?emp_id=0&msg=Food Category Updated Successfully");
		}
	}
%>
