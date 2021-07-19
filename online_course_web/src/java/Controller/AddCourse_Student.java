/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.CourseDB;
import DAO.RegistrationDB;
import DAO.UserDB;
import Model.Course;
import Model.Registration;
import Model.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ad
 */
@WebServlet(urlPatterns = {"/add-course"})
public class AddCourse_Student extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");

        String url = "/Display_Course_Introduction_Student";
        HttpSession session = request.getSession();

        String message = "";
        User user = (User) session.getAttribute("User");
        if (user == null) {
            url = "/sign-in";
        } else if (user != null && user.getRole().getRoleName().equals("student")) {
            try
            {
            int courseid = Integer.parseInt(request.getParameter("courseId"));
            url = "/Display_Course_Introduction_Student?courseid="+courseid;
            Course course = CourseDB.GetCourseByCourseId(courseid);

            if (course != null) {
                
                Registration registration = new Registration(user, course);

                if (RegistrationDB.registrationExists(user, course)) {
                    message = "Khóa học đã được đăng kí";
                } else {
                    if (!RegistrationDB.insertRegistrationDB(registration)) {
                        message = "Không tìm được khóa học";
                    }
                }

            }

        }
        catch(Exception ex)
            {
                message ="Đăng kí không thành công!";
                url ="/student";
            }

        if (message.equals("")) {
            message = "Đăng kí thành công";
        }
    }

    request.setAttribute (

    "message", message);
    getServletContext()

.getRequestDispatcher(url).forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
        public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
