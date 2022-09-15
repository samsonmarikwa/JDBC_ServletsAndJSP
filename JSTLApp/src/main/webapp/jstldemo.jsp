<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cout Demo</title>
</head>
<body>
<c:out value="${10 + 9}" />
<c:set var="testScore" value="${10}" scope="session" /> <!-- scope can be page, session, request, application -->
<c:out value="${testScore}" />

<c:if test="${testScore>=80}">
	<p>Your score is awesome ${testScore}</p>
</c:if>

<c:choose>
	<c:when test="${testScore >= 80 }">
		A Grade
	</c:when>
	<c:when test="${testScore >= 60 && testScore < 80 }">
		B Grade
	</c:when>
	<c:when test="${testScore >= 40 && testScore < 60 }">
		C Grade
	</c:when>
	<c:when test="${testScore >= 20 && testScore < 40 }">
		D Grade
	</c:when>
	<c:otherwise>
		Fail
	</c:otherwise>
</c:choose>

<c:forEach var="i" begin="1" end="4">
	<c:out value="${i}" />
</c:forEach>

<%
List<String> studentNames = new ArrayList<>();
studentNames.add("Samson");
studentNames.add("Annet");
studentNames.add("Laureen");
studentNames.add("Walter");
request.setAttribute("studentNames", studentNames);
%>
<br />
<c:forEach var="studentName" items="${studentNames}">
<c:out value="${studentName}" />
<br />
</c:forEach>

<c:remove var="testScore" /><br/>
After removal the value is: <c:out value="${testScore}" />

<c:set var="accountBalance" value="12345.4567" />
<fmt:parseNumber var="i" type="number" value="${accountBalance}"/>
<p>Amount is: <c:out value="${i}" /></p>

<p>Formatted Number: <fmt:formatNumber value="${accountBalance}" type="currency" /></p>
<p>Formatted Number: <fmt:formatNumber value="${accountBalance}" type="number" /></p>
<p>Formatted Number: <fmt:formatNumber value="${accountBalance}" type="number" maxFractionDigits="2" /></p>
<p>Formatted Number: <fmt:formatNumber value="${accountBalance}" type="percent" /></p>
<p>Formatted Number: <fmt:formatNumber value="${accountBalance}" type="number" pattern="#,###.##$" /></p>

<c:set var="myDate" value="28-09-1966" />
<fmt:parseDate var="parsedDate" value="${myDate}" pattern="dd-MM-yyyy" />
<p><c:out value="${parsedDate}" /></p>
</body>
</html>