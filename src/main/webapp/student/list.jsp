<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="en">
<head>
    <title>Student Management Application</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css">
</head>
<body>
<center>
    <h1>Student Management</h1>
    <h2>
        <button type="button" class="btn btn-success"><a href="/students?action=create" style="color: white; text-decoration: none">Add</a></button>
    </h2>
</center>
<div align="center">
    <table border="1" cellpadding="5">
<%--        <caption><h2>List of Students</h2></caption>--%>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Date Of Birth</th>
            <th>Address</th>
            <th>Phone Number</th>
            <th>Email</th>
            <th>Classroom</th>
            <th>Actions</th>
        </tr>
        <c:forEach var="student" items="${listStudent}">
            <tr>
                <td><c:out value="${student.id}"/></td>
                <td><c:out value="${student.name}"/></td>
                <td><c:out value="${student.dateOfBirth}"/></td>
                <td><c:out value="${student.address}"/></td>
                <td><c:out value="${student.phoneNumber}"/></td>
                <td><c:out value="${student.email}"/></td>
                <td><c:out value="${student.classroom}"/></td>
                <td>
                    <button class="btn btn-warning"><a href="/students?action=edit&id=${student.id}" style="color: black;text-decoration: none">Edit</a></button>
                    <button class="btn btn-danger"><a href="/students?action=delete&id=${student.id}" style="color: white;text-decoration: none">Delete</a></button>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
<br>
<div align="center">
    <form  action="/students?action=find" method="post">
    <input type="text" name="nameStudent" placeholder="search"/>
    <input type="submit" style="border: 1px solid green; background: white; color: green" value="search"/>
</form>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>