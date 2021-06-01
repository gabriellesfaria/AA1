<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<fmt:bundle basename="message">
    <head>
	<style>
	h1 {
	  text-align: center;
	}
	
	table.center {
	  margin-left: auto; 
	  margin-right: auto;
	}
	</style>
		<meta name='viewport' content='width=device-width, initial-scale=1'>
		<script src='https://kit.fontawesome.com/a076d05399.js' crossorigin='anonymous'></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><fmt:message key="page.title" /></title>
        <link href="${pageContext.request.contextPath}/layout.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
    <%
		String contextPath = request.getContextPath().replace("/", "");
	%>
    
        <h1><fmt:message key="page.label" /></h1>
        <c:if test="${mensagens.existeErros}">
            <div id="erro">
                <ul>
                    <c:forEach var="erro" items="${mensagens.erros}">
                        <i class='fas fa-exclamation-triangle' style='color:red'>   ${erro} </i><br>
                        </c:forEach>
                </ul>
            </div>
        </c:if>
        <form method="get" action="index.jsp">
            <table class="center">
                <tr>
                    <th><fmt:message key="user.email" />:</th>
                    <td><input type="text" name="email"
                               value="${param.email}"/></td>
                </tr>
                <tr>
                    <th><fmt:message key="user.password" />:</th>
                    <td><input type="password" name="senha" /></td>
                </tr></table>
                <center>
                        <br><input type="checkbox" name="tipo" value="empresa"> <fmt:message key="user.type"/>
                                    <br>
             
                        <br><input type="submit" name="bOK" value="<fmt:message key="user.submit"/>">
                </center> 
           <h1><a href="/<%=contextPath%>/lista"> 
					<fmt:message key="vaga.aberta" />
			</a></h1>
            
            
        </form>
    </body>
</fmt:bundle>
</html>