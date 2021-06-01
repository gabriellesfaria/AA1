<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<table class="center">
    <tr>
        <th>
            <fmt:message key="empresa.email" />:</th>
        <td><input type="text" name="email" required value="<c:out value='${empresa.email}' />" /></td>
    </tr>
    <tr>
        <th>
            <fmt:message key="empresa.senha" />:</th>
        <td><input type="password" name="senha" required value="<c:out value='${empresa.senha}' />" /></td>
    </tr>
    <c:choose>
        <c:when test="${empresa != null}">
            <input type="number" name="cnpj" hidden value="<c:out value='${empresa.cnpj}' />" />
        </c:when>
        <c:otherwise>
            <tr>
                <th>
                    <fmt:message key="empresa.cnpj" />:</th>
                <td><input type="number" name="cnpj" required value="<c:out value='${empresa.cnpj}' />" /></td>
            </tr>
        </c:otherwise>
    </c:choose>
    <tr>
        <th>
            <fmt:message key="empresa.nome" />:</th>
        <td><input type="text" name="nome" required value="<c:out value='${empresa.nome}' />" /></td>
    </tr>
    <tr>
        <th>
            <fmt:message key="empresa.descricao" />:</th>
        <td><textarea name="descricao" required rows="4" cols="25">
            <c:out value='${fn:trim(empresa.descricao)}' />
            </textarea>
        </td>
    </tr>
    <tr>
        <th>
            <fmt:message key="empresa.cidade" />:</th>
        <td><input type="text" name="cidade" required value="<c:out value='${empresa.cidade}' />" /></td>
    </tr>
</table>