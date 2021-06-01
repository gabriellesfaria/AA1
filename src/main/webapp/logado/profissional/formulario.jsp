<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
				<fmt:message key="inscricao.welcome" />
			</h1>
			<h2>
				<a href="/<%=contextPath%>/profissionais/listaVagas"> 
					<fmt:message key="profissional.back" />
				</a> 
				&nbsp;&nbsp;&nbsp;
				<a href="${pageContext.request.contextPath}/logout.jsp"> 
					<fmt:message key="exit.link" />
				</a>
				<br/>
				<br/>
				<a href="/<%=contextPath%>/empresas/lista?empresa=<c:out value='${empresaLogado.id}'/>"> 
					<fmt:message key="inscricao.list" />	
				</a>
			</h2>
		</div>
		<div align="center">
					
			<form action="insercao" method="get">
				<%@include file="campos.jsp"%>
			</form>
		</div>
		<c:if test="${!empty requestScope.mensagens}">
			<ul class="erro">
				<c:forEach items="${requestScope.mensagens}" var="mensagem">
					<li>${mensagem}</li>
				</c:forEach>
			</ul>
		</c:if>
	</body>
</fmt:bundle>

</html>