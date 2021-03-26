<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>

<head>
    <title>Student Tracker App</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
    <link type="text/css" rel="stylesheet" href="css/style.css">
</head>

<body>

<div id="wrapper" align="center">
    <div id="header">
        <h2 align="center">Oxford University</h2>
    </div>
</div>

<div id="container" align="center">

    <div id="content">

        <!-- put new button: Add Student -->

        <input type="button" value="Add Student"
               onclick="window.location.href='add-student-form.jsp'; return false;"

               class="btn btn-outline-primary"/>
        <!--class="add-student-button"
        />-->
        <c:url var="genPDF" value="StudentControllerServlet">
            <c:param name="command" value="PDF"/>
        </c:url>
        <a href="${genPDF}" class="btn btn-outline-primary"/>Generate PDF</a>
        <table class="table-light">
            <br><br>
            <tr>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Email</th>
                <th>Action</th>
            </tr>

            <c:forEach var="tempStudent" items="${STUDENT_LIST}">

                <!-- set up a link for each student -->
                <c:url var="tempLink" value="StudentControllerServlet">
                    <c:param name="command" value="LOAD"/>
                    <c:param name="studentId" value="${tempStudent.id}"/>
                </c:url>

                <!-- set up a link to delete a student -->
                <c:url var="deleteLink" value="StudentControllerServlet">
                    <c:param name="command" value="DELETE"/>
                    <c:param name="studentId" value="${tempStudent.id}"/>
                </c:url>

                <tr>
                    <td> ${tempStudent.firstName} </td>
                    <td> ${tempStudent.lastName} </td>
                    <td> ${tempStudent.email} </td>
                    <td>
                        <a href="${tempLink}">Update</a>
                        |
                        <a href="${deleteLink}"
                           onclick="if (!(confirm('Are you sure you want to delete this student?'))) return false">
                            Delete</a>
                    </td>
                </tr>

            </c:forEach>

        </table>

    </div>

</div>
</body>


</html>








