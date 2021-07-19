/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.*;
import Model.*;
import com.google.gson.JsonArray;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author TRAN VAN AN
 */
@WebServlet(name = "UsersManagerment", urlPatterns = {"/user-managerment"})
public class UsersManagerment extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        
        String url = "/Views/Pages/Admin/user_managerment.jsp";
        
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("User");
        if (user!= null)
        {
                
                //Get parameter
                String userType = request.getParameter("userType");
                if (userType == null)
                    userType = "all";
                request.setAttribute("UserType", userType);
                String search = request.getParameter("search");
                if (search == null)
                    search = "";
                request.setAttribute("Search", search);
                String isShowInfo = request.getParameter("isShowInfo");
                if (isShowInfo == null)
                    isShowInfo = "false";
                request.setAttribute("IsShowInfo", isShowInfo);
                String userId = request.getParameter("userId");
                if (userId == null)
                    userId = "";
                request.setAttribute("UserId", userId);
                //Get users include teachers and students
                List<User> users;
                if (userType.equals("all"))
                {
                    users = UserDB.GetTeachersAndStudents();
                }
                else
                {
                    Role role = (Role)RoleDB.GetRolesByRole(userType).get(0);
                    users = UserDB.GetUsersByFilter(role, "");
                }
                if (!"".equals(search))
                {
                    users = UserDB.GetTeachersAndStudentsByKeyword(search);
                }
                //Convert list to jsonarry
                JsonArray usersJson;
                if (users != null)
                {
                    usersJson = UserDB.ConvertListUsersToJsonArray(users);
                }else{
                    usersJson = null;
                }
                
                request.setAttribute("Users", users);
                System.out.print(usersJson);
                request.setAttribute("UsersJson", usersJson);
                //Get list courses fill to form 
                if (!"".equals(userId) && isShowInfo.equals("true"))
                {
                    User userInfo = UserDB.GetUserByUserId(Integer.parseInt(userId));
                    request.setAttribute("NameInfo", userInfo.getName());
                    List<Course> courses;
                    if (userInfo.getRole().getRoleName().equals("teacher"))
                        courses = CourseDB.GetCoursesByUserId(Integer.parseInt(userId));
                    else
                        courses = CourseDB.GetCoursesByUser_Registration(userInfo);
                    if (courses != null)
                    {
                        JsonArray coursesJson = CourseDB.ConvertListToJsonArray(courses);
                        request.setAttribute("CoursesJson", coursesJson);
                    }else
                    {
                        request.setAttribute("CoursesJson", null);
                    }
                }
         }
        else{
            url = "/sign-in";
        }
        
        if (!url.contains(".jsp"))
        {
            response.setStatus(HttpServletResponse.SC_MOVED_TEMPORARILY); 
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
