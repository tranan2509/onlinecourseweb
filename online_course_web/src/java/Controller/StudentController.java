/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.AccountDB;
import DAO.CourseDB;
import DAO.URL;
import DAO.UserDB;
import Model.*;
import Provider.SameSiteAttribute;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ad
 */
@WebServlet(urlPatterns = {"/student"})
public class StudentController extends HttpServlet {

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
        String url = "/Views/Pages/User/student.jsp";
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("User");
        if (user != null && user.getRole().getRoleName().equals("student"))
        {
//            response.addCookie(new Cookie("userId", String.valueOf(user.getUserId())));
            SameSiteAttribute.addSameSiteAttribute((HttpServletResponse) response);
           //List<Course> studying = CourseDB.GetCourseByUser(user);
            List<Course> studying = CourseDB.GetCoursesByUser_Registration(user);
            List<Course> studyingEsc = CourseDB.EscapeCourses(studying);
            request.setAttribute("studying", studyingEsc);
            //ds khóa học mở chưa đăng kí
            List<Course> courses = CourseDB.NotRegistration(user);
             List<Course> coursesEsc = CourseDB.EscapeCourses(courses);
            request.setAttribute("Courses", coursesEsc); 
            
            request.setAttribute("Role", user.getRole());
            //Get Error Edit Information
            String errorEditInformation = (String)session.getAttribute("ErrorEditInformation");
            if (errorEditInformation == null)
                errorEditInformation = "";
            session.setAttribute("ErrorEditInformation", errorEditInformation);
             //Get Error Edit Information
            String errorChangePassword = (String)session.getAttribute("ErrorChangePassword");
            if (errorChangePassword == null)
                errorChangePassword = "";
            session.setAttribute("ErrorChangePassword", errorChangePassword);
            
            //Show form edit information
            String isShowEditInfo = request.getParameter("isShowEditInfo");
            if (isShowEditInfo == null)
                isShowEditInfo = "false";
            if ("false".equals(isShowEditInfo))
                session.setAttribute("ErrorEditInformation", null);
            request.setAttribute("IsShowEditInfo", isShowEditInfo);
            //Show form edit password
            String isShowEditPass = request.getParameter("isShowEditPass");
            if (isShowEditPass == null)
                isShowEditPass = "false";
            if ("false".equals(isShowEditPass))
                session.setAttribute("ErrorChangePassword", null);
            request.setAttribute("IsShowEditPass", isShowEditPass);
           
        }
        else{
            url = "/sign-in";
        }
        
        if (!url.contains(".jsp"))
        {
            response.setStatus(response.SC_MOVED_TEMPORARILY); 
            response.setHeader("Location", URL.url + url); 
        }
        RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
        rd.forward(request, response);
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
