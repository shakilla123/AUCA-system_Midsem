<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>AUCA System Dashboard</title>
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
            <h3>AUCA System  Admin Dashboard</h3>
            <p class="sides"><a href="index.jsp">Home</a></p>
            <p class="sides"><a href="Student.jsp">Students Management</a></p>
            <p class="sides"><a href="CourseDefinition.jsp">Course Definitions</a></p>
            <p class="sides"><a href="StudentRegistration.jsp">Student Registrations</a></p>
            <p class="sides"><a href="Semester.jsp">Semesters</a></p>
            <p class="sides"><a href="Teacher.jsp">Teacher Management</a></p>
            <p class="sides"><a href="Course.jsp">Course Management</a></p>
            <p class="sides"><a href="AcademicUnit.jsp">Academic Unit Management</a></p>
        </div>
        <div class="content">
            <!-- Placeholder content -->
            <div class="content-box">
                <h2>Welcome to the AUCA System Dashboard</h2>
                <p>This dashboard provides an integrated platform for managing various aspects of the American University of Central Asia (AUCA) system.</p>
                <p>Explore the options on the sidebar to navigate through different management sections.</p>
            </div>
        </div>
    </div>
</body>
</html>
