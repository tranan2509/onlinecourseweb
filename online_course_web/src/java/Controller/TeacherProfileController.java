/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.CourseDB;
import DAO.UserDB;
import Model.*;
import Provider.SameSiteAttribute;
import java.io.IOException;
import java.io.PrintWriter;
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
 * @author TRAN VAN AN
 */
@WebServlet(name = "TeacherProfileController", urlPatterns = {"/teacher"})
public class TeacherProfileController extends HttpServlet {

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
        
        String url = "/Views/Pages/Teacher/teacher.jsp";
        
        HttpSession session = request.getSession();
        //User user = (User)session.getAttribute("User");
        User user = (User)session.getAttribute("User");
        if (user != null && user.getRole().getRoleName().equals("teacher") && user.getRole().getRoleId()==2)
        {
//            response.addCookie(new Cookie("userId", String.valueOf(user.getUserId())));
//            SameSiteAttribute.addSameSiteAttribute((HttpServletResponse) response);
            List<Course> Approvedcourses = CourseDB.GetAllCoursesApprovedByUser(user);
            request.setAttribute("ApprovedCoursesTeacher", Approvedcourses);
            
            List<Course> NotApprovedcourses = CourseDB.GetAllCoursesNotApprovedByUser(user);
             request.setAttribute("NotApprovedCoursesTeacher", NotApprovedcourses);
//            request.setAttribute("message", ""+NotApprovedcourses.size());
        }
        else
        {
            url = "/sign-in";
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
