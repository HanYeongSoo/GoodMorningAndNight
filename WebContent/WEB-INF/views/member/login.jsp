<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${not loginChk }">
	<script>
		alert('${msg}');
		history.go(-1);
	</script>
</c:if>

<c:if test="${loginChk }">
	<script>
		alert('${msg}');
		location.href = '${pageContext.request.contextPath}/member/index.do';
	</script>
</c:if>