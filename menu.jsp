<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/header.jsp" %>
<!-- 해당 페이지에서만 개별적으로 관리할 헤더정보는 새롭게 헤더를 만들어서 사용 -->
<head>
	<meta name="description" content="include 공부중">
</head>
<body>
	<h1>메뉴 주문 시스템</h1>
	<h3>메뉴를 선택하세요.</h3>
	<form action="<%=request.getContextPath()%>/menu/order" method="post">
		<select name="food" multiple="multiple">
			<option value="피자">피자</option>
			<option value="햄버거">햄버거</option>
			<option value="회">회</option>
			<option value="치킨">치킨</option>
		</select>
		<button>전송</button>
	</form>
</body>
</html>