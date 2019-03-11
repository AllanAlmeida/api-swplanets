<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>SWAPI</title>
	</head>
	<body>
		<div class="container">
			<div class="container">
				<h1>SW-API Endpoints</h1>
				<c:forEach items="${handlerMethods}" var="entry">
					<div class="span-3 colborder">
						<p>
							<span class="alt">Patterns:</span>
							<c:if test="${not empty entry.key.patternsCondition.patterns}">
								${entry.key}
							</c:if>
						</p>
					</div>
				</c:forEach>
			</div>
		</div>
	</body>
</html>