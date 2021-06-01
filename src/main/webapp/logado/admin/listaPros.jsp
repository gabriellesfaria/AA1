
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
				<fmt:message key="admin.welcome" />
			</h1>
			<h2>
				<a href="${pageContext.request.contextPath}/logout.jsp">
					<fmt:message key="exit.link" />
				</a>
				<br/>
				<br/>
				<a href="/<%=contextPath%>/admin/listaEmpresas"> 
					<fmt:message key="admin.companies" />
				</a>
				<br/>
				<br/>
				<a href="/<%=contextPath%>/admin/cadastroProfissional"> 
					<fmt:message key="profissional.create" />
				</a>
			</h2>
			<h3><fmt:message key="profissional.list" /></h3>
			<br/>
		</div>
		
		
		<div align="center">
			<table border="1">
				<tr>
					<th><fmt:message key="profissional.cpf" /></th>
					<th><fmt:message key="profissional.nome" /></th>
					<th><fmt:message key="profissional.email" /></th>
					<th><fmt:message key="profissional.telefone" /></th>
					<th><fmt:message key="profissional.sexo" /></th>
					<th><fmt:message key="profissional.dataNasc" /></th>
					<!-- <th><fmt:message key="vaga.empresa" /></th> -->
					<!-- <th><fmt:message key="actions.link" /></th> -->
				</tr>
				<c:forEach var="pro" items="${requestScope.listaPros}">
					<tr>
						<td><c:out value="${pro.cpf}" /></td>
						<td><c:out value="${pro.nome}" /></td>
						<td><c:out value="${pro.email}" /></td>
						<td><c:out value="${pro.telefone}" /></td>
						<td><c:out value="${pro.sexo}" /></td>
						<td><c:out value="${pro.nascimento}" /></td>
						<td>
							<a	href="/<%= contextPath %>/admin/edicaoProfissional?cpf=<c:out value='${pro.cpf}' />">
								<fmt:message key="profissional.edit" />
							</a>
						</td>
						<td>
							<a	href="/<%= contextPath %>/admin/remocao?cpf=<c:out value='${pro.cpf}' />"
								onclick="return confirm('<fmt:message key="confirm.link" />');">
								<fmt:message key="profissional.delete" />
							</a>
						</td>
					</tr>					
				</c:forEach>
			</table>
		</div>
	</body>
</fmt:bundle>

</html>