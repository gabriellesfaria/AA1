<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<table border="1">
	<caption>
		<c:choose>
			<c:when test="${vaga != null}">
				<fmt:message key="vaga.update" />
			</c:when>
			<c:otherwise>
				<fmt:message key="vaga.create" />
			</c:otherwise>
		</c:choose>
	</caption>
	<input type="hidden" name="id" value="<c:out value='<%=request.getParameter("id")%>' />" />
	<tr>
	
		<td><label for="Nome"> <fmt:message key="vaga.nome" />
		</label></td>
		<td><input type="text" id="nome" name="nome" size="90" required
			value="<c:out value='${vaga.nome}' />" /></td>
	</tr>
	<tr><td><label for="Status"><fmt:message key="vaga.status" />: </label></td><td>
			<select id="status" name="status">
			  <option value="Aberta"><fmt:message key="vaga.status1" /></option>
			  <option value="Analise"><fmt:message key="vaga.status2" /></option>
			  <option value="Fechada"><fmt:message key="vaga.status3" /></option>
			</select></td>
	</tr>
	<tr>
		<td><label for="Descricao"> <fmt:message key="vaga.descricao" />
		</label></td>
		<td><input type="text" id="descricao" name="descricao" size="90" required
			value="<c:out value='${vaga.descricao}' />" /></td>
	</tr>
		<tr>
		<td><label for="Salario"> <fmt:message key="vaga.salario" />
		</label></td>
		<td><input type="number" step=0.1 id="salario" name="salario" size="90" required
			value="<c:out value='${vaga.salario}' />" /></td>
	</tr>
	<tr>
		<td><label for="DataLimite"><fmt:message key="vaga.dataLimite" />
		</label></td>
		<td><input type="date" name="dataLimite" size="90" required
			value="<c:out value='${vaga.dataLimite}' />" /></td>
	</tr>
	<tr>
	<tr>
	
		<td colspan="2" align="center"><input type="submit"
			value="<fmt:message key="save.link" />" /></td>
	</tr>
	
</table>