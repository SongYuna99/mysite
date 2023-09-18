<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${ pageContext.request.contextPath }/assets/css/board.css"
	rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp"></c:import>
		<div id="content">
			<div id="board">
				<!-- 검색 -->
				<form id="search_form" action="${ pageContext.request.contextPath }/board/search" method="post">
					<input type="text" id="keyword" name="keyword" value="${ keyword }"> 
					<input type="submit" value="찾기">
				</form>
				
				<!-- 게시글 리스트 -->
				<table class="tbl-ex">
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>글쓴이</th>
						<th>조회수</th>
						<th>작성일</th>
						<th>&nbsp;</th>
					</tr>
					
					<!-- c:forEach 사용 -->
					<c:set var="count" value="${fn:length(list) }" />
					<c:forEach items="${ list }" var="vo" varStatus="status">
						<tr>
							<td>${count - status.index }</td>
							<td style="padding-left:${(vo.depth-1)*30}px">
								<c:if test="${ vo.depth > 1 }">
									<img src="${pageContext.request.contextPath }/assets/images/reply.png">
								</c:if>
								<a href="${ pageContext.request.contextPath }/board/view/${ vo.no }">${ vo.title }</a>
							</td>
							<td>${ vo.userName }</td>
							<td>${ vo.hit }</td>
							<td>${ vo.regDate }</td>
							<td>
								<c:if test="${ authUser.no == vo.userNo }">
									<a href="${ pageContext.request.contextPath }/board/delete/${ vo.no }" class="del">삭제</a>
								</c:if>
							</td>
						</tr>
					</c:forEach>
					
					
				</table>

				<!-- pager 추가 -->
				<div class="pager">
					<ul>
						<li><a href="${ pageContext.request.contextPath }/board/previouspage/${selectedPage}">◀</a></li>
						<c:forEach items="${ pagelist }" var="num">
							<c:choose>
								<c:when test="${ num == selectedPage }">
									<li class="selected"><a href="${ pageContext.request.contextPath }/board/page/${num}">${num}</a></li>
								</c:when>
								<c:otherwise>
									<li><a href="${ pageContext.request.contextPath }/board/page/${num}">${num}</a></li>
								</c:otherwise>
							</c:choose>
						</c:forEach>					
						<li><a href="${ pageContext.request.contextPath }/board/nextpage/${selectedPage}">▶</a></li>
					</ul>
				</div>

				<!-- write.jsp로 이동 -->
				<div class="bottom">
					<c:if test="${ not empty authUser.no }">
						<a href="${ pageContext.request.contextPath }/board/write" id="new-book">글쓰기</a>					
					</c:if>
				</div>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp"></c:import>
		<c:import url="/WEB-INF/views/includes/footer.jsp"></c:import>
	</div>
</body>
</html>