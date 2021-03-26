<!DOCTYPE html>
<html>

<head>
    <title>Add Student</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
    <link type="text/css" rel="stylesheet" href="css/style.css">
    <link type="text/css" rel="stylesheet" href="css/add-student-style.css">
</head>

<body>
<div id="wrapper" align="center">
    <div id="header">
        <h2 align="center">Oxford University</h2>
    </div>
</div>

<div id="container" align="center">
    <h3>Add Student</h3>

    <form action="StudentControllerServlet" method="GET">

        <input type="hidden" name="command" value="ADD"/>

        <table>
            <tbody>
            <tr>
                <td><label>First name:</label></td>
                <td><input type="text" name="firstName"/></td>
            </tr>

            <tr>
                <td><label>Last name:</label></td>
                <td><input type="text" name="lastName"/></td>
            </tr>

            <tr>
                <td><label>Email:</label></td>
                <td><input type="text" name="email"/></td>
            </tr>

            <tr>
                <td><label></label></td>
                <td><input type="submit" value="Save" class="save"/></td>
            </tr>

            </tbody>
        </table>
    </form>

    <div style="clear: both;"></div>

    <p align="left">
        <a href="StudentControllerServlet">Back to List</a>
    </p>
</div>
</body>

</html>











