<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>메뉴 주문 결과 확인</h2>
<hr>


<ol>
<c:forEach var="foods" items="${orderList}">
  <li>${foods.food} : ${foods.price}</li>
</c:forEach>
</ol>
<span>주문하신
 <c:forEach var="foods" items="${orderList}" varStatus="status">
 <c:choose>
 <c:when test="${status.count eq 1}">
 ${foods.food}
 </c:when>
 <c:otherwise>, ${foods.food}</c:otherwise>
 </c:choose>
 </c:forEach>
 의 결제 금액은 ${total} 입니다.</span>
<h3>* 이용해 주셔서 감사합니다. *</h3>
</body>
</html>