<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div id="navigation">
		<ul>
			<li><a href="<%=request.getContextPath()%>/user?a=loginform">송유나</a></li>
			<li><a href="<%=request.getContextPath()%>/guestbook">방명록</a></li>
			<li><a href="<%=request.getContextPath()%>/board">게시판</a></li>
		</ul>
	</div>
</body>
</html>