<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Student Registration</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script>
    <style>
        table {
            border-collapse: collapse;
            width: 100%;
        }
        th, td {
            border: 1px solid #dddddd;
            text-align: left;
            padding: 8px;
        }
        th {
            background-color: #f2f2f2;
        }
        .container {
            margin-top: 20px;
        }
        label {
            display: block;
            margin-bottom: 5px;
        }
        input[type="text"], input[type="date"], select {
            width: 100%;
            padding: 8px;
            margin-bottom: 10px;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
        }
        button[type="submit"] {
            background-color: #4CAF50;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
        button[type="submit"]:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Student Registration</h2>
        <form action="studentregistration" method="post">
            <input type="hidden" name="action" value="create">
            <label for="studentId">Student:</label>
            <select id="studentId" name="studentId" required>
                <c:forEach var="student" items="${students}">
                    <option value="${student.studentId}">${student.name}</option>
                </c:forEach>
            </select><br>
            <label for="semesterId">Semester:</label>
            <select id="semesterId" name="semesterId" required>
                <c:forEach var="semester" items="${semesters}">
                    <option value="${semester.semesterId}">${semester.name}</option>
                </c:forEach>
            </select><br>
            <label for="academicId">Academic Unit:</label>
            <select id="academicId" name="academicId" required>
                <c:forEach var="academic" items="${academics}">
                    <option value="${academic.academicId}">${academic.name}</option>
                </c:forEach>
            </select><br>
            <label for="registrationDate">Registration Date:</label>
            <input type="text" id="registrationDate" name="registrationDate" required><br>
            <button type="submit">Save</button>
        </form>
        <hr>
        <h3>Student Registrations</h3>
        <table>
            <thead>
                <tr>
                    <th>Registration ID</th>
                    <th>Student Name</th>
                    <th>Semester</th>
                    <th>Academic Unit</th>
                    <th>Registration Date</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="registration" items="${studentregistration}">
                    <tr>
                        <td>${registration.registrationId}</td>
                        <td>${registration.student.name}</td>
                        <td>${registration.semester.name}</td>
                        <td>${registration.academicUnit.name}</td>
                        <td>${registration.registrationDate}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
    <script>
        $(function() {
            $("#registrationDate").datepicker({
                dateFormat: 'yy-mm-dd'
            });
        });
    </script>
</body>
</html>
