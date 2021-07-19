/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.CourseDB;
import Model.Course;
import Model.User;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author A556U
 */
@WebServlet(name = "Process_Delete_Course_Teacher", urlPatterns = {"/Process_Delete_Course_Teacher"})
public class Process_Delete_Course_Teacher extends HttpServlet {

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

        String url = "/teacher";
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("User");

        String message = null;
        if (user == null) {
            url = "/sign-in";
        } else if (user.getRole().getRoleId() != 2) {
            url = "/Views/Pages/Home/home.jsp";
        } else {
//            try {
                int courseid = Integer.parseInt((String) request.getParameter("courseid"));

                Course course = CourseDB.getCourseById(courseid);

                //get courseid if exist, it is sent from teacherprofile
                if (course != null) {
                    if (!CourseDB.courseOfTeacherExists(course.getCourseId(), user)) {
                        request.setAttribute("message", "Bạn không có khóa học này!");
                        url = "/teacher";
                    } else  {
                        if(!CourseDB.deleteCourse(course))
                        {
                            message="Lỗi! Không xóa được khóa học";
                        }
                    }
                } else {
                    message = "Không tìm thấy khóa học1";
                }
//            } catch (Exception ex) {
//                message = "Không tìm thấy khóa học";
//            }
            request.setAttribute("message", message);
        }
        getServletContext().getRequestDispatcher(url).forward(request, response);
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
