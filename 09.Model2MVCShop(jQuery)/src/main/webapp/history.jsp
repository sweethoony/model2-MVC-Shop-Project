<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page contentType="text/html; charset=EUC-KR" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>

<title>��� ��ǰ ����</title>

</head>
<body>
	����� ��� ��ǰ�� �˰� �ִ�
<br>
<br>
<%
	request.setCharacterEncoding("euc-kr");
	response.setCharacterEncoding("euc-kr");
	String history = null;
	Cookie[] cookies = request.getCookies();
	  System.out.println("/history.jsp cookies  1" + cookies);
	if (cookies!=null && cookies.length > 0) {
		for (int i = 0; i < cookies.length; i++) {
			Cookie cookie = cookies[i];
			 System.out.println("/history.jsp cookie  1" + cookie);
			 System.out.println("/history.jsp cookie.getName()  1" + cookie.getName());
			
			 if (cookie.getName().equals("history")) {
				history = cookie.getValue();
				 System.out.println("/history.jsp history  1" + history);
			}
		}
		if (history != null) {
			String[] h = history.split(":");
			for (int i = 0; i < h.length; i++) {
				if (!h[i].equals("null")) {
%>
<a href="/product/getProduct?prodNo=<%=h[i]%>&menu=search"
	target="rightFrame"><%=h[i]%></a>
<br>
<%
				}
			}
		}
	}
%>

</body>
</html>
