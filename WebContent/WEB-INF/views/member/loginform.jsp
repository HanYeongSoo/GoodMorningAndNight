<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<%@ include file="/WEB-INF/views/frame/pageset.jsp" %>
</head>
<body>
	<!-- 헤더 시작 -->
	<%@ include file="/WEB-INF/views/frame/header.jsp" %>
	<!-- 헤더 끝 -->
	
	<!-- 네비 시작 -->
	<%@ include file="/WEB-INF/views/frame/nav.jsp" %>
	<!-- 네비 끝 -->
	
	<!-- 컨텐트 시작 -->
	<div>
		<h3>로그인</h3>
		<hr>
		<!-- form 경로와 처리 경로가 동일 -> method로 구분 -->
		<!-- action="login.do" 생략 가능 -->
		<!-- 사진 업로드 시 form에 method="POST" enctype="multipart/form-data" 필수 작성 -->
		<form method="POST">
			<table>
				<tr>
					<td>아이디</td>
					<td><input type="text" name="userid" value="${cookie.saveId != null ? cookie.saveId.value : '' }"></td>
				</tr>
				<tr>
					<td>비밀번호</td>
					<td><input type="password" name="pw"></td>
				</tr>
				<tr>
					<td></td>
					<td><input type="checkbox" name="saveid" value="on" ${cookie.saveId != null ? 'checked' : '' }>아이디 저장하기</td>
				</tr>
				<tr>
					<td></td>
					<td><input type="submit" value="로그인"><input type="reset"></td>
				</tr>
			</table>
		</form>
	</div>
	<!-- 컨텐트 끝 -->
	
	<!-- javascript 추가 -->
	<%@ include file="/WEB-INF/views/frame/footerset.jsp" %>
	
</body>
</html>