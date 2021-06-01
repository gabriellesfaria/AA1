<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<fmt:bundle basename="message">

	<head>
		<title>
			<fmt:message key="page.title" />
		</title>
	</head>

	<body>
		<%
			String contextPath = request.getContextPath().replace("/", "");
		%>
		<div align="center">

		</div>
		<div align="center">
			<c:choose>
				<c:when test="${empresa != null}">
					<h1>
						<fmt:message key="empresa.update" />
					</h1>
					<h2>
						<a href="${pageContext.request.contextPath}/logout.jsp">
							<fmt:message key="exit.link" />
						</a>
						<br />
						<br />
						<a href="/<%=contextPath%>/admin/listaEmpresas">
							<fmt:message key="empresa.list" />
						</a>
					</h2>

					<form action="atualizaEmpresa" method="get">
						atualiza
						<%@include file="campos_empresa.jsp"%>
						<center>
							<br><input type="submit" name="bOK" value="<fmt:message key="empresa.updatesubmit" />">
						</center>
					</form>
				</c:when>
				<c:otherwise>
					<h1>
						<fmt:message key="empresa.create" />
					</h1>
					<h2>
						<a href="${pageContext.request.contextPath}/logout.jsp">
							<fmt:message key="exit.link" />
						</a>
						<br />
						<br />
						<a href="/<%=contextPath%>/admin/listaEmpresas">
							<fmt:message key="empresa.list" />
						</a>
					</h2>

					<form action="insereEmpresa" method="get">
						<%@include file="campos_empresa.jsp"%>
						<center>
							<br><input type="submit" name="bOK" value="<fmt:message key="empresa.createsubmit" />">
						</center>
					</form>
				</c:otherwise>
			</c:choose>
		</div>
		<c:if test="${mensagens.existeErros}">
            <div id="erro">
                <ul>
                    <c:forEach var="erro" items="${mensagens.erros}">
                        <i class='fas fa-exclamation-triangle' style='color:red'>   ${erro} </i><br>
                        </c:forEach>
                </ul>
            </div>
        </c:if>
	</body>
</fmt:bundle>

</html>