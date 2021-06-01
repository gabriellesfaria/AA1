
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<fmt:bundle basename="message">

	<head>
		<title><fmt:message key="page.title" /></title>
		<meta charset="UTF-8">
	</head>

	<body>
		<%
			String contextPath = request.getContextPath().replace("/", "");
		%>
		<div align="center">
			<h1>
				<fmt:message key="inscricao.welcome" />
			</h1>
			<h2>
				<a href="${pageContext.request.contextPath}/logout.jsp">
					<fmt:message key="exit.link" />
				</a>
				<br/>
				<br/>
				<a href="/<%=contextPath%>/profissionais/listaVagas"> 
					<fmt:message key="inscricao.vagas" />
				</a> 
			</h2>
			<h3><fmt:message key="inscricao.list" /></h3>
			<br/>
		</div>
		<div align="center">
			<table border="1">
				<tr>
					<th><fmt:message key="inscricao.id" /></th>
					<th><fmt:message key="inscricao.cv" /></th>
					<th><fmt:message key="inscricao.status" /></th>
					<th><fmt:message key="inscricao.dataInscricao" /></th>
					<th><fmt:message key="inscricao.vaga" /></th>
					<th><fmt:message key="actions.link" /></th>
						
				</tr>
				<c:forEach var="inscricao" items="${requestScope.listaInscricoes}">
					<tr>
						<td><c:out value="${inscricao.id}" /></td>
						<td><c:out value="${inscricao.CV}" /></td>
						<td><c:out value="${inscricao.status}" /></td>
						<td><c:out value="${inscricao.dataInscricao}" /></td>
						<td><c:out value="${inscricao.vaga}" /></td>
						<td>
                                <a	href="/<%= contextPath %>/profissionais/remocao?id=<c:out value='${inscricao.id}' />"
									onclick="return confirm('<fmt:message key="confirm.link" />');">
									<fmt:message key="inscricao.delete" />
								</a>
						</td>
					</tr>
					
				</c:forEach>
			</table>
		</div>
	</body>
</fmt:bundle>

</html>