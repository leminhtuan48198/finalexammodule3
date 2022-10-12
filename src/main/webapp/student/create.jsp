<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Student Management Application</title>
</head>
<body>
<center>
    <h1>Student Management</h1>
    <h2>
        <a href="students?action=students">List All Students</a>
    </h2>
</center>
<div align="center">
    <form method="post">
        <table border="1" cellpadding="5">
            <caption>
                <h2>Add New Student</h2>
            </caption>
            <tr>
                <th>Student Name:</th>
                <td>
                    <input type="text" name="name" size="45"/>
                </td>
            </tr>
            <tr>
                <th>Date Of Birth :</th>
                <td>
                    <input type="text" name="dateOfBirth"  size="45"/>
                </td>
            </tr>
            <tr>
                <th>Address:</th>
                <td>
                    <input type="text" name="address"  size="45"/>
                </td>
            </tr>
            <tr>
                <th>Phone Number:</th>
                <td>
                    <input type="text" name="phoneNumber"  size="45"/>
                </td>
            </tr>
            <tr>
                <th>Student Email:</th>
                <td>
                    <input type="text" name="email"  size="45"/>
                </td>
            </tr>
            <tr>
                <th>Classroom:</th>
                <td>
                    <select name="idClass" >
                        <c:forEach var="classroom" items="${classroomList}">
                            <option value="${classroom.idClass}"><c:out value="${classroom.nameClass}"/></option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <input type="submit" value="Save"/>
                </td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>