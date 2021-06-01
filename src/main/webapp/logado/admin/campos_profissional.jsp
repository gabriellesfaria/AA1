<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<table class="center">
    <tr>
        <th>
            <fmt:message key="profissional.email" />:</th>
        <td><input type="text" name="email" required value="<c:out value='${profissional.email}' />" /></td>
    </tr>
    <tr>
        <th>
            <fmt:message key="profissional.senha" />:</th>
        <td><input type="password" name="senha" required value="<c:out value='${profissional.senha}' />"/></td>
    </tr>
    
    <c:choose>
        <c:when test="${profissional != null}">
            <input type="number" name="cpf" hidden required value="<c:out value='${profissional.cpf}' />" />
        </c:when>
        <c:otherwise>
            <tr>
                <th>
                    <fmt:message key="profissional.cpf" />:</th>
                <td><input type="number" name="cpf" required value="<c:out value='${profissional.cpf}' />" /></td>
            </tr>
        </c:otherwise>
    </c:choose>
    <tr>
        <th>
            <fmt:message key="profissional.nome" />:</th>
        <td><input type="text" name="nome" required value="<c:out value='${profissional.nome}' />" /></td>
    </tr>
    <tr>
        <th>
            <fmt:message key="profissional.telefone" />:</th>
        <td><input type="text" name="telefone" required value="<c:out value='${profissional.telefone}' />" /></td>
    </tr>
    <tr>
        <th>
            <fmt:message key="profissional.dataNasc" />:</th>
        <td><input type="date" name="nascimento" required value="<c:out value='${profissional.nascimento}' />" /></td>
    </tr>
    <tr>
        <th>
            <fmt:message key="profissional.sexo" />:</th>
        <td><input type="radio" id="male" name="sexo" value="M" required>
            <label for="male">
                <fmt:message key="sexo.masc" /></label><br>
            <input type="radio" id="female" name="sexo" value="F" required>
            <label for="female">
                <fmt:message key="sexo.fem" /></label><br>
        </td>
    </tr>
</table>
