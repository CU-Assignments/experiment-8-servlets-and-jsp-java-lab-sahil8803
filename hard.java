<!DOCTYPE html>
<html>
<head>
    <title>Student Attendance</title>
</head>
<body>
    <h2>Enter Attendance Details</h2>
    <form action="AttendanceServlet" method="post">
        Student ID: <input type="text" name="id" /><br/>
        Name: <input type="text" name="name" /><br/>
        Date: <input type="date" name="date" /><br/>
        Status: 
        <select name="status">
            <option value="Present">Present</option>
            <option value="Absent">Absent</option>
        </select><br/><br/>
        <input type="submit" value="Submit Attendance" />
    </form>
</body>
</html>
---------------------------------
import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class AttendanceServlet extends HttpServlet {
    public void doPost(HttpServletRequest req, HttpServletResponse res)
        throws ServletException, IOException {

        res.setContentType("text/html");
        PrintWriter out = res.getWriter();

        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String date = req.getParameter("date");
        String status = req.getParameter("status");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/student_db", "root", "yourpassword");

            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO attendance (id, name, date, status) VALUES (?, ?, ?, ?)");
            ps.setString(1, id);
            ps.setString(2, name);
            ps.setString(3, date);
            ps.setString(4, status);

            int i = ps.executeUpdate();
            if (i > 0) {
                out.println("<h3>Attendance Recorded Successfully</h3>");
            } else {
                out.println("<h3>Failed to Record Attendance</h3>");
            }

            con.close();
        } catch (Exception e) {
            out.println("Error: " + e.getMessage());
        }
    }
}
---------------------------------------------

<web-app>
    <servlet>
        <servlet-name>AttendanceServlet</servlet-name>
        <servlet-class>AttendanceServlet</servlet-class>
    </servlet>

    <servlet-mapping>
        <servlet-name>AttendanceServlet</servlet-name>
        <url-pattern>/AttendanceServlet</url-pattern>
    </servlet-mapping>
</web-app>
-----------
CREATE DATABASE student_db;
USE student_db;

CREATE TABLE attendance (
    id INT,
    name VARCHAR(100),
    date DATE,
    status VARCHAR(10)
);
