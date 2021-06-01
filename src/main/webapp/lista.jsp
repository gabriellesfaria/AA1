<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<fmt:bundle basename="message">

	<head>
<title><fmt:message key="lista.welcome" /></title>
	</head>

	<body>
		<%
			String contextPath = request.getContextPath().replace("/", "");
		%>
		<div align="center">
			<h1>
				<fmt:message key="vaga.list" />
			</h1>
			<h2>
				<a href="${pageContext.request.contextPath}/login.jsp">
					<fmt:message key="exit.link" /> </a>
			</h2>
		</div>
		
		
		<div align="center">
			<table border="1">
				<tr>
					<th><fmt:message key="vaga.id" /></th>
					<th><fmt:message key="vaga.nome" /></th>
					<th><fmt:message key="vaga.descricao" /></th>
					<th><fmt:message key="vaga.salario" /></th>
					<th><fmt:message key="vaga.dataLimite" /></th>
					<th><fmt:message key="vaga.empresa" /></th>
					<th><fmt:message key="actions.link" /></th>
				</tr>
				<c:forEach var="vaga" items="${requestScope.lista}">
					<tr>
						<td><c:out value="${vaga.id}" /></td>
						<td><c:out value="${vaga.nome}" /></td>
						<td><c:out value="${vaga.descricao}" /></td>
						<td>R$<c:out value="${vaga.salario}" /></td>
						<td><c:out value="${vaga.dataLimite}" /></td>
						<td><c:out value="${vaga.empresa}" /></td>
					<td colspan="2" align="center"><a
							href="/<%= contextPath %>/login.jsp">
								<fmt:message key="page.label" />
						</a> 
						</td>
					</tr>					
				</c:forEach>
			</table>
		</div>
	</body>
</fmt:bundle>

</html>