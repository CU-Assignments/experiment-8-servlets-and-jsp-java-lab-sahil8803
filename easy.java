1. HTML Form (login.html)


<!DOCTYPE html>
<html>
<head><title>Login Page</title></head>
<body>
  <h2>Login Form</h2>
  <form action="LoginServlet" method="post">
    Username: <input type="text" name="username" /><br><br>
    Password: <input type="password" name="password" /><br><br>
    <input type="submit" value="Login" />
  </form>
</body>
</html>



----------------------------------------
#2. Servlet Code (LoginServlet.java)

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class LoginServlet extends HttpServlet {
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setContentType("text/html");
    PrintWriter out = response.getWriter();

    String username = request.getParameter("username");
    String password = request.getParameter("password");

    if ("admin".equals(username) && "admin123".equals(password)) {
      out.println("<h2>Welcome, " + username + "!</h2>");
    } else {
      out.println("<h2>Login Failed. Invalid credentials.</h2>");
    }
  }
}

-------------------------------------------

#3. web.xml Configuration

<web-app>
  <servlet>
    <servlet-name>LoginServlet</servlet-name>
    <servlet-class>LoginServlet</servlet-class>
  </servlet>

  <servlet-mapping>
    <servlet-name>LoginServlet</servlet-name>
    <url-pattern>/LoginServlet</url-pattern>
  </servlet-mapping>
</web-app>
