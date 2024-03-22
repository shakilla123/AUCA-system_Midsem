<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Semester Management</title>
    <!-- Add jQuery and jQuery UI Datepicker from a CDN -->
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
        }
        h1 {
            text-align: center;
        }
        form {
            margin-bottom: 20px;
        }
        label {
            display: block;
            margin-bottom: 5px;
        }
        input[type="text"] {
            width: 100%;
            padding: 8px;
            margin-bottom: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
            box-sizing: border-box;
        }
        input[type="submit"], .action-btn {
            background-color: #007bff;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            margin-right: 10px;
            text-decoration: none; /* Remove underline */
            display: inline-block; /* Ensure inline display */
            text-align: center; /* Center align text */
            line-height: 20px; /* Adjust line height */
        }
        input[type="submit"]:hover, .action-btn:hover {
            background-color: #0056b3;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        th, td {
            padding: 8px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }
        th {
            background-color: #f2f2f2;
        }
    </style>
    <script>
        // Initialize Datepicker for startDate and endDate inputs
        $(function() {
            $("#startDate, #endDate").datepicker({
                dateFormat: 'yy-mm-dd'
            });
        });
    </script>
</head>
<body>
    <h1>Semester Management</h1>

    <form method="post" action="semesters">
        <input type="hidden" name="action" value="${empty semester ? 'create' : 'update'}">
        <input type="hidden" name="semesterId" value="${empty semester ? '' : semester.semesterId}">
        <label for="name">Semester Name:</label>
        <input type="text" name="name" id="name" value="${empty semester ? '' : semester.name}">
        <label for="startDate">Start Date:</label>
        <input type="text" name="startDate" id="startDate" value="${empty semester ? '' : semester.startDate}" required>
        <label for="endDate">End Date:</label>
        <input type="text" name="endDate" id="endDate" value="${empty semester ? '' : semester.endDate}" required>
        <input type="submit" value="${empty semester ? 'Create Semester' : 'Update Semester'}">
    </form>

    <table>
        <thead>
            <tr>
                <th>Semester ID</th>
                <th>Name</th>
                <th>Start Date</th>
                <th>End Date</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="semester" items="${semesters}">
                <tr>
                    <td>${semester.semesterId}</td>
                    <td>${semester.name}</td>
                    <td>${semester.startDate}</td>
                    <td>${semester.endDate}</td>
                    <td>
                        <form action="semesters" method="post" style="display: inline;">
                            <input type="hidden" name="action" value="update">
                            <input type="hidden" name="semesterId" value="${semester.semesterId}">
                            <input type="submit" value="Update" class="action-btn">
                        </form>
                        <form action="semesters" method="post" style="display: inline;">
                            <input type="hidden" name="action" value="delete">
                            <input type="hidden" name="semesterId" value="${semester.semesterId}">
                            <input type="submit" value="Delete" class="action-btn">
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
