<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
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
		<h3>회원 가입</h3>
		<hr>
		<h3>
			${result == 1 ? '회원 가입 완료!!' : result == 0 ? '회원 가입 실패!' : '서버 오류' }
		</h3>
	</div>
	<!-- 컨텐트 끝 -->
	
	<!-- javascript 추가 -->
	<%@ include file="/WEB-INF/views/frame/footerset.jsp" %>
	
</body>
</html>