
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<fmt:bundle basename="message">

	<head>
<title><fmt:message key="page.title" /></title>
	</head>

	<body>
		<%
			String contextPath = request.getContextPath().replace("/", "");
		%>
		<div align="center">
			<h1>
				<fmt:message key="vaga.welcome" />
			</h1>
			<h2>
			<a href="/<%=contextPath%>/empresas/listaInscricao">
					<fmt:message key="inscricao.all" />
				</a>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<a href="${pageContext.request.contextPath}/logout.jsp">
					<fmt:message key="exit.link" />
				</a>
				<br/>
				
				<br/>
				<a href="/<%=contextPath%>/empresas/cadastro"> 
					<fmt:message key="vaga.create" />
				</a> 
			</h2>
			<h3><fmt:message key="vaga.list" /></h3>
			<br/>
		</div>
		
		
		<div align="center">
			<table border="1">
				<tr>
					<th><fmt:message key="vaga.id" /></th>
					<th><fmt:message key="vaga.nome" /></th>
					<th><fmt:message key="vaga.status" /></th>
					<th><fmt:message key="vaga.descricao" /></th>
					<th><fmt:message key="vaga.salario" /></th>
					<th><fmt:message key="vaga.dataLimite" /></th>
					<th><fmt:message key="vaga.empresa" /></th>
					<th><fmt:message key="actions.link" /></th>
				</tr>
				<c:forEach var="vaga" items="${requestScope.listaVagas}">
					<tr>
					
						<td><c:out value="${vaga.id}" /></td>
						<td><c:out value="${vaga.nome}" /></td>
						<td><c:out value="${vaga.status}" /></td>
						<td><c:out value="${vaga.descricao}" /></td>
						<td>R$<c:out value="${vaga.salario}" /></td>
						<td><c:out value="${vaga.dataLimite}" /></td>
						<td><c:out value="${vaga.empresa}" /></td>
						
					<td colspan="2" align="center"><a
							href="/<%= contextPath %>/empresas/edicao?id=<c:out value='${vaga.id}' />&empresa=<c:out value='${vaga.empresa}'/>">
								<fmt:message key="vaga.update" />
						</a> 
                                &nbsp;&nbsp;&nbsp;&nbsp;
                                <a
									href="/<%= contextPath %>/empresas/remocao?id=<c:out value='${vaga.id}' />"
									onclick="return confirm('<fmt:message key="confirm.link" />');">
									<fmt:message key="vaga.delete" />
								</a>
						</td>
					</tr>					
				</c:forEach>
			</table>
		</div>
	</body>
</fmt:bundle>

</html>