<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.9.0.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<link href="${ pageContext.request.contextPath }/assets/css/user.css"
	rel="stylesheet" type="text/css">
	
<script>
	var messageBox = function (title, message, callback) {
		$('#dialog-message').text(message);
		$('#dialog').attr('title', title);
		
		$("#dialog").dialog({
			width: 300,
			modal: true,
			buttons: {
				"확인": function() {
					$(this).dialog("close");
				}
			},
			close: callback
			
		});
	}
	
	$(function() {
		$('#btn-check-email').click(function() {
			var email = $('#email').val();
			
			if(email === ''){
				return;
			}
			
			$.ajax({
				url: '${ pageContext.request.contextPath }/api/user?email='+ email,
				type: 'get',
				dataType: 'json',
				success: function(response) {
					if(response.result !== 'success'){
						console.log()
					}
					
					if(response.data){
						messageBox('이메일 중복 체크', '사용중인 이메일입니다. 다른 이메일을 사용해주세요.', function() {
							$('#email').focus();
						});					
						return;						
					} else{
						$('#img-check-email').show();
						$('#btn-check-email').hide();
					}
					
				},
				error: function(xhr, status, e) {
					consol.log(status, e)
				}
			});
		});
		
		$("#email").change(function() {
			$('#img-check-email').hide();
			$('#btn-check-email').show();				
		});
		
		$('#join-form').submit(function() {
			event.preventDefault();
			
			// 1. name
			if($('#name').val() === ''){
				messageBox('회원가입', '이름은 필수 항목입니다.', function() {
					$('#name').focus();
				});
				return;
			}
			
			// 2. email
			if($('#email').val() === ''){
				messageBox('회원가입', '이메일은 필수 항목입니다.', function() {
					$('#email').focus();
				});
				return;
			}
			
			// 3. email 중복 체크
			if(!$('#img-check-email').is(':visible')){
				messageBox('이메일 중복 체크', '이메일은 중복 확인을 해주세요.');
				return;
			}

			// 4. password
			if($('#password').val() === ''){
				messageBox('회원가입', '비밀번호는 필수 항목입니다.', function() {
					$('#password').focus();
				});
				return;
			}

			// 5. ok
			this.submit();
		});
	});
</script>
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp"></c:import>
		<div id="content">
			<div id="user">
				<form:form modelAttribute="userVo" id="join-form" 
					name="joinForm" method="post" action="${ pageContext.request.contextPath }/user/join">	
					<label class="block-label" for="name">이름</label> 
					<form:input path="name" />
					<p style="padding:3px; text-align:left; color:red;">
						<form:errors path="name"></form:errors>
					</p>
					
					<label class="block-label" for="email">이메일</label> 
					<form:input path="email" />
					<input id="btn-check-email" type="button" value="id 중복체크"> 
					<img id="img-check-email" src="${ pageContext.request.contextPath }/assets/images/check.png" style="width:18px; vertical-align:middle; display:none">
					<p style="padding:3px; text-align:left; color:red;">
						<form:errors path="email"></form:errors>
					</p>
					
					<label class="block-label">
						<spring:message code="user.join.label.password"></spring:message>
					</label> 
					<form:password path="password"/>
					<p style="padding:3px; text-align:left; color:red;">
						<form:errors path="password"></form:errors>
					</p>

					<fieldset>
						<legend>성별</legend>
						<form:radiobutton path="gender" value="female" label="여" checked="checked"/>
						<form:radiobutton path="gender" value="male" label="남"/>
					</fieldset>

					<fieldset>
						<!-- 체크박스도 폼으로 바꿔보기 -->
						<legend>약관동의</legend>
						<input id="agree-prov" type="checkbox" name="agreeProv" value="y">
						<label>서비스 약관에 동의합니다.</label>
					</fieldset>

					<input type="submit" value="가입하기">

				</form:form>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp"></c:import>
		<c:import url="/WEB-INF/views/includes/footer.jsp"></c:import>
		<div id="dialog" title="회원가입" style="width: 150px; display: none">
			<p id="dialog-message" style="line-height:60px">사용중인 이메일입니다. 다른 이메일을 사용해주세요.</p>
		</div>
	</div>
</body>
</html>