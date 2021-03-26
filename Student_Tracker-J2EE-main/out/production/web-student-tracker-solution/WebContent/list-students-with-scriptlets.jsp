<%@ page import="com.luv2code.web.jdbc.Student, java.util.List" %>
<!DOCTYPE html>
<html>

<head>
    <title>Student Tracker App</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
    <link type="text/css" rel="stylesheet" href="css/style.css">
</head>

<%
    // get the students from the request object (sent by servlet)
    List<Student> theStudents =
            (List<Student>) request.getAttribute("STUDENT_LIST");
%>

<body>

<div id="wrapper">
    <div id="header">
        <h2>Oxford University</h2>
    </div>
</div>

<div id="container">

    <div id="content">

        <table>

            <tr>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Email</th>
            </tr>

            <% for (Student tempStudent : theStudents) { %>

            <tr>
                <td><%= tempStudent.getFirstName() %>
                </td>
                <td><%= tempStudent.getLastName() %>
                </td>
                <td><%= tempStudent.getEmail() %>
                </td>
            </tr>

            <% } %>

        </table>

    </div>

</div>
</body>


</html>








