<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<table border="1">
	<caption>
		<fmt:message key="inscricao.create" />
	</caption>
	<tr>
		<td><label for="cv"> <fmt:message key="inscricao.cv" />
		</label></td>
		<td><input type="text" id="cv" name="cv" size="90" required
			value="<c:out value='${inscricao.CV}' />" /></td>
	</tr>
	<input type="hidden" name="vaga" value="<c:out value='<%=request.getParameter("vaga")%>' />" />
	<tr>
	
		<td colspan="2" align="center"><input type="submit"
			value="<fmt:message key="save.link" />" /></td>
	</tr>
	
</table>