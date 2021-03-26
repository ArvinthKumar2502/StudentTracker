package com.luv2code.web.jdbc;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.List;

/**
 * Servlet implementation class StudentControllerServlet
 */
@WebServlet("/StudentControllerServlet")
public class StudentControllerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private StudentDbUtil studentDbUtil;

    @Resource(name = "jdbc/web_student_tracker")
    private DataSource dataSource;

    @Override
    public void init() throws ServletException {
        super.init();

        // create our student db util ... and pass in the conn pool / datasource
        try {
            studentDbUtil = new StudentDbUtil();
        } catch (Exception exc) {
            throw new ServletException(exc);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Cookie ck = new Cookie("uname", dataSource.toString());//creating cookie object
        response.addCookie(ck);//adding cookie in the response
        try {
            // read the "command" parameter
            String theCommand = request.getParameter("command");

            // if the command is missing, then default to listing students
            if (theCommand == null) {
                theCommand = "LOGIN";
            }

            // route to the appropriate method
            switch (theCommand) {
                case "LOGIN":
                    Login(request, response);
                    break;
                case "LIST":
                    listStudents(request, response);
                    break;

                case "ADD":
                    addStudent(request, response);
                    break;

                case "LOAD":
                    loadStudent(request, response);
                    break;

                case "UPDATE":
                    updateStudent(request, response);
                    break;

                case "DELETE":
                    deleteStudent(request, response);
                    break;

                case "PDF":
                    generatePDF(request, response);
                    break;
                default:
                    listStudents(request, response);

            }

        } catch (Exception exc) {
            throw new ServletException(exc);
        }

    }

    private void deleteStudent(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        // read student id from form data
        String theStudentId = request.getParameter("studentId");

        // delete student from database
        studentDbUtil.deleteStudent(theStudentId);

        // send them back to "list students" page
        listStudents(request, response);
    }

    private void updateStudent(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        // read student info from form data
        int id = Integer.parseInt(request.getParameter("studentId"));
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");

        // create a new student object
        Student theStudent = new Student(id, firstName, lastName, email);

        // perform update on database
        studentDbUtil.updateStudent(theStudent);

        // send them back to the "list students" page
        listStudents(request, response);

    }

    private void loadStudent(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        // read student id from form data
        String theStudentId = request.getParameter("studentId");

        // get student from database (db util)
        Student theStudent = studentDbUtil.getStudent(theStudentId);

        // place student in the request attribute
        request.setAttribute("THE_STUDENT", theStudent);

        // send to jsp page: update-student-form.jsp
        RequestDispatcher dispatcher =
                request.getRequestDispatcher("/update-student-form.jsp");
        dispatcher.forward(request, response);
    }

    private void addStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {

        // read student info from form data
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");

        // create a new student object
        Student theStudent = new Student(firstName, lastName, email);

        // add the student to the database
        studentDbUtil.addStudent(theStudent);

        // send back to main page (the student list)
        listStudents(request, response);
    }

    private void listStudents(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        // get students from db util
        List<Student> students = studentDbUtil.getStudents();

        // add students to the request
        request.setAttribute("STUDENT_LIST", students);

        // send to JSP page (view)
        RequestDispatcher dispatcher = request.getRequestDispatcher("list-students.jsp");
        dispatcher.forward(request, response);
        System.out.println(request.getParameter("username"));
    }

    private void generatePDF(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        // get students from db util
        List<Student> students = studentDbUtil.getStudents();
        String data = "";
        for (int i = 0; i < students.size(); i++) {
            data = data + students.get(i).getId() + " " + students.get(i).getFirstName() + " " + students.get(i).getLastName() + " " + students.get(i).getEmail() + "<br>";
            //s = s+students.get(i).toString() + "\n";
        }
        //System.out.println(data);

        //String filename = "C:\\Users\\Vicky\\Downloads\\StudentData.pdf";
        String filename="C:\\Users\\arvin\\Desktop\\StudentData.pdf";

        PDDocument doc = new PDDocument();
        try {
            PDPage page = new PDPage();
            doc.addPage(page);
            PDFont font = PDType1Font.TIMES_BOLD;
            PDPageContentStream contents = new PDPageContentStream(doc, page);
            contents.beginText();
            contents.setFont(font, 30);
            contents.setLeading(30.5f);
            contents.newLineAtOffset(25, 700);
            for (int i = 0; i < students.size(); i++) {
                data = students.get(i).getId() + " " + students.get(i).getFirstName() + " " + students.get(i).getLastName() + " " + students.get(i).getEmail();


                contents.showText(data);
                contents.newLine();


            }
            contents.endText();
            contents.close();
            doc.save(filename);
            System.out.println("Saved");
        } finally {
            doc.close();
        }
        // add students to the request
        request.setAttribute("STUDENT_LIST", students);

        // send to JSP page (view)
        RequestDispatcher dispatcher = request.getRequestDispatcher("list-students.jsp");
        dispatcher.forward(request, response);
        System.out.println(request.getParameter("username"));
    }

    private void Login(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        // send to JSP page (view)
        RequestDispatcher dispatcher = request.getRequestDispatcher("Login.jsp");
        dispatcher.forward(request, response);
    }
}













