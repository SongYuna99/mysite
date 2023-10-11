<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> 
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
pageContext.setAttribute("newline", "\n");
%>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath }/assets/css/admin/main.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/admin/include/header.jsp" />
		<div id="wrapper">
			<div id="content">
				<div id="site-form">
					<form method="post" action="${pageContext.request.contextPath }/admin/update" enctype="multipart/form-data">
						<input type="hidden" name="no" value="${siteVo.no }">
						<label class="block-label" for="title">사이트 타이틀</label>
						<input id="title" name="title" type="text" value="${siteVo.title }">
						
						<label class="block-label" for="welcome">환영 메세지</label>
						<input id="welcomeMessage" name="welcome" type="text" value="${siteVo.welcome }">

						<label class="block-label">프로필 이미지</label>
						<c:choose>
							<c:when test="${siteVo.profile == '' }">
								<img id="profile" src="${pageContext.request.contextPath }/assets/images/none_profile.png">
							</c:when>
							<c:otherwise>
								<input type="hidden" name="profile" value="${siteVo.profile }">
								<img id="profile" src="${pageContext.request.contextPath }${siteVo.profile }">
							</c:otherwise>
						</c:choose>
						<input type="file" name="file">

						<label class="block-label">사이트 설명</label>
						<textarea name="description">
							${fn:replace(siteVo.description, newLine, "<br>") }
						</textarea>
						
						<input type="submit" value="변경" />
					</form>
									
				

				</div>
			</div>
			<c:import url="/WEB-INF/views/admin/include/navigation.jsp">
				<c:param name="menu" value="main"/>
			</c:import>
		</div>
	</div>
</body>
</html>