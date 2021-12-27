<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${result == 1 }">
	<script>
		alert('성공적으로 수정 되었습니다.');
		location.href='${pageContext.request.contextPath}/member/manager/list.do';
	</script>	
</c:if>

<c:if test="${result != 1 }">
	<script>
		alert('수정이 실패하였습니다. 원인은 앞선 코드를 확인해보세요.');
		histroy.go(-1);
	</script>	
</c:if>