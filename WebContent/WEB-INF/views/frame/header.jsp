<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
	<header>
		<h1>Open Project</h1>
		<div id="loginInfoArea">
			<c:if test="${empty loginInfo }">
				<a href="${pageContext.request.contextPath }/member/login.do">Login</a>
			</c:if>
			
			<c:if test="${not empty loginInfo }">
			<table>
				<tr>
					<td><img src="${pageContext.request.contextPath }/uploadfile/${loginInfo.photo}"></td>
					<td>	${loginInfo.userName } (${loginInfo.userId }) 	<a href="${pageContext.request.contextPath }/member/login.do">logout</a></td>
				</tr>
			</table>
			</c:if>
		</div>
	</header>