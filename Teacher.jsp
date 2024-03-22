<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Teacher Management</title>
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
        select,
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
    </style>
</head>
<body>

<h2>Teacher Management</h2>

<form action="teacher" method="post">
    <input type="hidden" name="action" value="save">
    
    <label for="firstName">First Name:</label>
    <input type="text" id="firstName" name="firstName" required><br><br>
    
    <label for="lastName">Last Name:</label>
    <input type="text" id="lastName" name="lastName" required><br><br>
    
    <label for="qualification">Qualification:</label>
    <select id="qualification" name="qualification" required>
        <option value="MASTERS">Masters</option>
        <option value="PHD">PhD</option>
        <option value="PROFESSOR">Professor</option>
    </select><br><br>
    
    <button type="submit">Create Teacher</button>
</form>

<table>
    <thead>
        <tr>
            <th>ID</th>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Qualification</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="teacher" items="${teachers}">
            <tr>
                <td>${teacher.teacherId}</td>
                <td>${teacher.firstName}</td>
                <td>${teacher.lastName}</td>
                <td>${teacher.qualification}</td>
            </tr>
        </c:forEach>
    </tbody>
</table>

</body>
</html>
