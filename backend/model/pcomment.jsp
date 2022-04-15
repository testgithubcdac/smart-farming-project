<%@ page import= "Model.*" %>
<%@ page import= "java.util.*" %>

<%
	Pcomments pcommentObj = new Pcomments();
	String emp_id = (String) session.getAttribute("login_emp_id");
	if((request.getParameter("act")).equals("Save"))
	{
		HashMap results = new HashMap();
		results.put("pcomment_user_id",emp_id);
		results.put("pcomment_product_id",request.getParameter("product_id"));
		results.put("pcomment_name",request.getParameter("pcomment_name"));
		results.put("pcomment_email",request.getParameter("pcomment_email"));
		results.put("pcomment_description",request.getParameter("pcomment_description"));
		out.println(pcommentObj.savePcomments(results));
		response.sendRedirect("../product-details.jsp?product_id="+request.getParameter("product_id")+"&msg=Pcomment Saved Successfully");
	}
%>
