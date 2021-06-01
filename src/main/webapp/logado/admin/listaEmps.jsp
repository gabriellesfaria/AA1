<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<fmt:bundle basename="message">

	<head>
		<title>
			<fmt:message key="page.title" />
		</title>
		<meta charset="UTF-8">
	</head>

	<body>
		<%
			String contextPath = request.getContextPath().replace("/", "");
		%>
		<div align="center">
			<h1>
				<fmt:message key="admin.welcome" />
			</h1>
			<h2>
				<a href="${pageContext.request.contextPath}/logout.jsp">
					<fmt:message key="exit.link" />
				</a>
				<br />
				<br />
				<a href="/<%=contextPath%>/admin/">
					<fmt:message key="admin.professionals" />
				</a>
				<br />
				<br />
				<a href="/<%=contextPath%>/admin/cadastroEmpresa">
					<fmt:message key="empresa.create" />
				</a>
			</h2>
			<h3>
				<fmt:message key="empresa.list" />
			</h3>
			<br />
		</div>


		<div align="center">
			<table border="1">
				<tr>
					<th>
						<fmt:message key="empresa.cnpj" />
					</th>
					<th>
						<fmt:message key="empresa.nome" />
					</th>
					<th>
						<fmt:message key="empresa.descricao" />
					</th>
					<th>
						<fmt:message key="empresa.cidade" />
					</th>
					<th>
						<fmt:message key="empresa.email" />
					</th>
					<th>
						<fmt:message key="actions.link" />
					</th>
				</tr>
				<c:forEach var="emp" items="${requestScope.listaEmps}">
					<tr>
						<td>
							<c:out value="${emp.cnpj}" />
						</td>
						<td>
							<c:out value="${emp.nome}" />
						</td>
						<td>
							<c:out value="${emp.descricao}" />
						</td>
						<td>
							<c:out value="${emp.cidade}" />
						</td>
						<td>
							<c:out value="${emp.email}" />
						</td>
						<td>
							<a href="/<%= contextPath %>/admin/edicaoEmpresa?cnpj=<c:out value='${emp.cnpj}' />">
								<fmt:message key="empresa.edit" />
							</a>
						</td>
						<td>
							<a href="/<%= contextPath %>/admin/remocao?cnpj=<c:out value='${emp.cnpj}' />"
								onclick="return confirm('<fmt:message key=" confirm.deletion" />');">
							<fmt:message key="empresa.delete" />
							</a>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</body>
</fmt:bundle>

</html>