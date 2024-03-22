<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>AUCA Student Dashboard</title>
    <style>
        /* CSS styles */
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }

        .page {
            display: flex;
            flex-direction: row;
            height: 100vh;
        }

        .sidebar {
            width: 200px;
            background-color: #333;
            color: #fff;
            padding: 20px;
        }

        .content {
            flex: 1;
            padding: 20px;
        }

        .sides {
            margin-bottom: 10px;
        }

        .sides a {
            color: #fff;
            text-decoration: none;
        }

        .sides a:hover {
            text-decoration: underline;
        }

        h2 {
            color: #333;
            margin-bottom: 20px;
        }

        p {
            color: #555;
            margin-bottom: 10px;
        }

        .content-box {
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        .action-buttons a {
            background-color: #4CAF50;
            color: white;
            padding: 8px 16px;
            text-decoration: none;
            margin-right: 10px;
            border-radius: 4px;
        }

        .action-buttons a:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
    <div class="page">
        <div class="sidebar">
            <h3>AUCA Student Dashboard</h3>
            <p class="sides"><a href="Student.jsp">Student Profile</a></p>
            <p class="sides"><a href="StudentRegistration.jsp">Student Registration</a></p>
            <p class="sides"><a href="Course.jsp">Courses</a></p>
        </div>
        <div class="content">
            <!-- Placeholder content -->
            <div class="content-box">
                <h2>Welcome to the AUCA Student Dashboard</h2>
                <p>This dashboard provides tools for students to manage their registration and access course information at AUCA.</p>
                <p>As a student, you can:</p>
                <ul>
                    <li>View and update your student profile</li>
                    <li>Register for courses and manage your schedule</li>
                    <li>Access course materials and assignments</li>
                    <li>Interact with instructors and fellow students</li>
                </ul>
                <p>Feel free to navigate through the options on the sidebar to access various features and functionalities.</p>
                <p>Adventist University of Central Africa (AUCA) is dedicated to providing a supportive learning environment where students can grow academically, spiritually, and socially. Our mission is to equip students with the knowledge, skills, and values necessary to make a positive impact in their communities and the world.</p>
            </div>
        </div>
    </div>
</body>
</html>
