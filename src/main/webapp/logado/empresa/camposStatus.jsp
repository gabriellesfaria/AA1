<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<table border="1">
	<input type="hidden" name="id" value="<c:out value='<%=request.getParameter("id")%>' />" />
<tr>
		<td><label for="status"><fmt:message key="inscricao.status" />: </label></td><td>
			<select id="status" name="status">
			  <option value="ABERTO"><fmt:message key="inscricao.status1" /></option>
			  <option value="NAO SELECIONADO"><fmt:message key="inscricao.status2" /></option>
			  <option value="ENTREVISTA"><fmt:message key="inscricao.status3" /></option>
			</select>
			</td>
		<tr>
		<td><label for="DataEntrevista"><fmt:message key="vaga.dataEntrevista" />: 
		</label></td>
		<td><input type="date" name="dataEntrevista" size="45" /></td>
	</tr>
	<tr>
		<td><label for="LinkEntrevista"><fmt:message key="vaga.linkEntrevista" />: 
		</label></td>
		<td><input type="text" name="linkEntrevista" size="30"/></td>
	</tr>
</tr>
<tr>
		<td colspan="2" align="center"><input type="submit"
			value="<fmt:message key="save.link"/>" /></td>
</tr>
	
</table>