<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style type="text/css">
#content>div {
	padding: 15px;
}
#content>table {
	border: 0;
	border-collapse: collapse;
}
#content>table td, #content>table th {
	border: 1px solid #aaa;
	padding: 10px;
	text-align: center;
	width: 900px;
}
#paging {
	overflow: hidden;
}
#paging>a {
	display: block;
	
	width: 30px;
	height: 30px;
	
	border: 1px solid black;
	border-radius: 50%;
	
	text-align: center;
	text-decoration: none;
	line-height: 30px;
	
	float: left;
	margin-right: 5px;
}
.curpage {
	background-color: orange;
	color: white;
}
</style>
<title>회원 리스트</title>
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
	<div id="content">
		<h3>회원 리스트</h3>
		<hr>
		<div id="listInfo">
			전체 회원 수 : ${listView.totalCount }명, 현재 페이지 : ${listView.currentPage }/${listView.pageTotalCount }
		</div>
		
		<table>
			<tr>
				<th>idx</th>
				<th>user ID</th>
				<th>password</th>
				<th>user Name</th>
				<th>photo</th>
				<th>regdate</th>
				<th>manage</th>
			</tr>
			
			<c:if test="${empty listView.list }">
				<tr>
					<td colspan="7">등록된 회원이 존재하지 않습니다.</td>
				</tr>
			</c:if>
			
			<c:if test="${not empty listView.list }">
				<c:forEach var="member" items="${listView.list }">
					<tr>
						<td>${member.idx }</td>
						<td>${member.userid }</td>
						<td>${member.pw }</td>
						<td>	${member.username }</td>
						<td>${member.photo }</td>
						<td>${member.regdate }</td>
						<td>
							<a href="edit.do?idx=${member.idx }">수정</a>
							<a href="javascript:delMember(${member.idx })">삭제</a>
						</td>
					</tr>
				</c:forEach>
			</c:if>
			
		</table>
		
		<div id="paging">
			<c:if test="${listView.pageTotalCount > 0 }">
				<c:forEach begin="1" end="${listView.pageTotalCount }" var="pNum">
					<a href="list.do?p=${pNum }" class="${listView.currentPage == pNum ? 'curpage' : ' ' }">${pNum }</a>  
				</c:forEach>
			</c:if>
		</div>
		
	</div>
	<!-- 컨텐트 끝 -->
	
	<!-- javascript 추가 -->
	<%@ include file="/WEB-INF/views/frame/footerset.jsp" %>
	
<script>
	function delMember(idx) {
			if(confirm("해당 회원 정보를 삭제하시겠습니까?")){
				location.href = 'delete.do?idx=' + idx;
			}
	}
</script>
	
</body>
</html>