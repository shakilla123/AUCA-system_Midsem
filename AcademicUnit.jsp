<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Academic Unit Form</title>
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
        
        .update-delete-buttons {
            margin-bottom: 20px;
        }
    </style>
</head>
<body>

<h2>Academic Unit Form</h2>

<form action="academicunit" method="post">
    <input type="hidden" name="action" value="${empty academicUnit ? 'create' : 'update'}">
    <input type="hidden" name="academicId" value="${empty academicUnit ? '' : academicUnit.academicId}">
    
    <label for="code">Code:</label>
    <input type="text" id="code" name="code" value="${empty academicUnit ? '' : academicUnit.code}" required><br><br>
    
    <label for="name">Name:</label>
    <input type="text" id="name" name="name" value="${empty academicUnit ? '' : academicUnit.name}" required><br><br>
    
    <label for="unityType">Unity Type:</label>
    <select id="unityType" name="unityType" required>
        <option value="PROGRAMME" ${!empty academicUnit && academicUnit.unityType == 'PROGRAMME' ? 'selected' : ''}>PROGRAMME</option>
        <option value="FACULTY" ${!empty academicUnit && academicUnit.unityType == 'FACULTY' ? 'selected' : ''}>FACULTY</option>
        <option value="DEPARTMENT" ${!empty academicUnit && academicUnit.unityType == 'DEPARTMENT' ? 'selected' : ''}>DEPARTMENT</option>
    </select><br><br>
    
    <button type="submit">${empty academicUnit ? 'Create' : 'Update'}</button>
</form>

<div class="update-delete-buttons">
    <form action="academicunit" method="post">
        <input type="hidden" name="action" value="update">
        <button type="submit">Update</button>
    </form>
    <form action="academicunit" method="post">
        <input type="hidden" name="action" value="delete">
        <button type="submit">Delete</button>
    </form>
</div>

<table>
    <thead>
        <tr>
            <th>Code</th>
            <th>Name</th>
            <th>Unity Type</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="academicUnit" items="${academicUnits}">
            <tr>
                <td>${academicUnit.code}</td>
                <td>${academicUnit.name}</td>
                <td>${academicUnit.unityType}</td>
            </tr>
        </c:forEach>
    </tbody>
</table>

</body>
</html>
