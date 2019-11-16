<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name = "description" content="">
    <meta name="author" content="">
    <title>Solo5-Authorization</title>
</head>
<body>
<h1>Авторизация</h1>
<p style="color: red">${requestScope.namePassFailed}</p>
<br>
<form action="/login" method="post">
    <table style=" width: 10%; border: 4px double black;">
        <tr>
            <td>Логин</td>
            <td><input name="name" type="text" value="${name}" required/></td>
        </tr>
        <tr>
            <td> Пароль</td>
            <td><input name="password" type="password" value="${password}" required/></td>
        </tr>
        <tr>
            <td><input type="submit" value="Войти"></td>
        </tr>
    </table>
</form>
<form action="/registration" method="get"><input type="submit" value="Зарегестрироваться"></form>
</body>
</html>

