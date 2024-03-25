<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="Model.Student" %>
<%@ page import="Dao.StudentDao" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Student Form</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f0f0f0;
            margin: 0;
            padding: 20px;
        }
        
        h2 {
            color: #333;
        }
        
        form {
            background-color: #fff;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            margin-bottom: 20px;
        }
        
        label {
            font-weight: bold;
            margin-right: 10px;
        }
        
        input[type="text"],
        input[type="date"],
        button {
            padding: 10px;
            margin-bottom: 15px;
            border: 1px solid #ccc;
            border-radius: 5px;
            font-size: 16px;
        }
        
        button {
            background-color: #007bff;
            color: #fff;
            border: none;
            cursor: pointer;
            transition: background-color 0.3s;
        }
        
        button:hover {
            background-color: #0056b3;
        }
        
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        
        th, td {
            border: 1px solid #ccc;
            padding: 8px;
            text-align: left;
        }
        
        th {
            background-color: #f2f2f2;
        }
        
        /* New CSS for buttons */
        .action-buttons form {
            display: inline;
        }
        
        .action-buttons button {
            margin-right: 5px;
        }
    </style>
</head>
<body>

<h2>Student Form</h2>

<form action="student" method="post">
    <input type="hidden" name="action" value="${empty student ? 'create' : 'update'}">
    <input type="hidden" name="studid" value="${empty student ? '' : student.studid}">
    
    <label for="firstName">First Name:</label>
    <input type="text" id="firstName" name="firstName" value="${empty student ? '' : student.firstName}" required><br><br>
    
    <label for="lastName">Last Name:</label>
    <input type="text" id="lastName" name="lastName" value="${empty student ? '' : student.lastName}" required><br><br>
    
    <label for="dob">Date of Birth:</label>
    <input type="date" id="dob" name="dob" value="${empty student ? '' : student.dob}" required><br><br>
    
    <button type="submit">${empty student ? 'Create' : 'Update'}</button>
</form>

<table>
    <thead>
        <tr>
            <th>Student ID</th>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Date of Birth</th>
            <th>Actions</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="student" items="${students}">
            <tr>
                <td>${student.studid}</td>
                <td>${student.firstName}</td>
                <td>${student.lastName}</td>
                <td>${student.dob}</td>
                <td class="action-buttons">
                    <form action="student" method="post">
                        <input type="hidden" name="action" value="update">
                        <input type="hidden" name="studid" value="${student.studid}">
                     
                        <input type="text" id="updateFirstName" name="firstName" value="${student.firstName}">
                       
                        <input type="text" id="updateLastName" name="lastName" value="${student.lastName}">
                       
                        <input type="date" id="updateDob" name="dob" value="${student.dob}">
                        <button type="submit">Update</button>
                    </form>
                    <form action="student" method="post">
                        <input type="hidden" name="action" value="delete">
                        <input type="hidden" name="studid" value="${student.studid}">
                        <button type="submit">Delete</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>

</body>
</html>
