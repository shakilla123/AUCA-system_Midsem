<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Course Form</title>
    <style>
        /* Styles for the form and table */
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
        }

        form {
            width: 400px;
            margin-bottom: 20px;
        }

        label {
            display: block;
            margin-bottom: 5px;
        }

        input[type="text"],
        select {
            width: 100%;
            padding: 8px;
            margin-bottom: 10px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }

        button {
            background-color: #4CAF50;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }

        button:hover {
            background-color: #45a049;
        }

        table {
            width: 100%;
            border-collapse: collapse;
        }

        th, td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: left;
        }

        th {
            background-color: #4CAF50;
            color: white;
        }
    </style>
</head>
<body>

<h2>Course Form</h2>

<form action="course" method="post">
    <input type="hidden" name="action" value="create">
    <label for="courseCode">Course Code:</label>
    <input type="text" id="courseCode" name="courseCode" required><br><br>
    
    <label for="courseName">Course Name:</label>
    <input type="text" id="courseName" name="courseName" required><br><br>
    
    <label for="semesterId">Semester:</label>
    <select id="semesterId" name="semesterId" required>
        <c:forEach var="semester" items="${semesters}">
            <option value="${semester.semesterId}">${semester.semesterName}</option>
        </c:forEach>
    </select><br><br>
    
    <label for="academicId">Academic Unit:</label>
    <select id="academicId" name="academicId" required>
        <c:forEach var="academicUnit" items="${academicUnits}">
            <option value="${academicUnit.academicId}">${academicUnit.academicName}</option>
        </c:forEach>
    </select><br><br>
    
    <button type="submit">Save</button>
</form>

<hr>

<h2>Course List</h2>
<table>
    <tr>
        <th>Course Code</th>
        <th>Course Name</th>
        <th>Semester</th>
        <th>Academic Unit</th>
    </tr>
    <c:forEach var="course" items="${course}">
        <tr>
            <td>${course.courseCode}</td>
            <td>${course.courseName}</td>
            <td>${course.semester.semesterName}</td>
            <td>${course.academicUnit.academicName}</td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
