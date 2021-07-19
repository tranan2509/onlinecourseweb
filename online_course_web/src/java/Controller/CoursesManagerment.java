/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.AccountDB;
import DAO.CourseDB;
import DAO.URL;
import Model.*;
import com.google.gson.JsonArray;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
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
@WebServlet(name = "CoursesManagermentController", urlPatterns = {"/course-managerment"})
public class CoursesManagerment extends HttpServlet {

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
        
        String url = "/Views/Pages/Admin/course_managerment.jsp";
        
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("User");
        if (user!= null)
        {

                //Update approved course
                String errorUpdateApproved = "";
                String courseId = request.getParameter("courseId");
                if (courseId == null)
                    courseId = "";
                request.setAttribute("CourseId", courseId);
                String stateApproved = request.getParameter("stateApproved");
                if (stateApproved == null)
                    stateApproved = "";
                request.setAttribute("StateApproved", stateApproved);
                //Check Show form send email
                String isShowEmail = request.getParameter("isShowEmail");
                if (isShowEmail == null)
                    isShowEmail = "false";
                request.setAttribute("IsShowEmail", isShowEmail);
                //End check show form send email
                
                if (!courseId.equals("") && !stateApproved.equals(""))
                {
                    Course course = CourseDB.GetCourseByCourseId(Integer.parseInt(courseId));
                    if (stateApproved.equals("approved"))
                    {
                        course.setApproved(true);
                        Timestamp modifiedDate = new Timestamp(System.currentTimeMillis());
                        course.setModifiedDate(modifiedDate);
                        boolean isUpdated = CourseDB.updateCourse(course);
                        if(!isUpdated)
                            errorUpdateApproved = "Cập nhật thất bại";
                    }
                    else
                    {
                        //SendMail
                        String isSendEmail = request.getParameter("isSendEmail");
                        if (isSendEmail == null)
                            isSendEmail = "false";
                        request.setAttribute("IsSendEmail", isSendEmail);
                        if (isSendEmail.equals("true"))
                        {
                            String subject = request.getParameter("subject");
                            if (subject == null)
                                subject = "";
                            String textEmail = request.getParameter("textEmail");
                            if (textEmail == null)
                                textEmail = "";
                            String isSended = AccountDB.SendMail(course.getUser().getEmail(), subject, textEmail);
                            //End sendmail
                            if (isSended.equals(""))
                            {
                                errorUpdateApproved = "Gửi thông báo email thất bại";
                            }else{
                                course.setApproved(false);
                                Timestamp modifiedDate = new Timestamp(System.currentTimeMillis());
                                course.setModifiedDate(modifiedDate);
                                boolean isUpdated = CourseDB.updateCourse(course);
                                if(!isUpdated)
                                    errorUpdateApproved = "Cập nhật thất bại";
                                else{
                                    url = "/course-managerment?isSendEmail=false&isShowEmail=false";
                                }
                            }
                        }
                    }
                }
                request.setAttribute("ErrorUpdateApproved", errorUpdateApproved);
                //End update approved course
                
                String approved = request.getParameter("approved");
                if (approved == null)
                    approved = "unapproved";
                request.setAttribute("Approved", approved);
                String search = request.getParameter("search");
                if (search == null)
                    search = "";
                request.setAttribute("Search", search);
                List<Course> courses;
                if ("all".equals(approved))
                {
                    courses = CourseDB.GetCoursesOderByAsc();
                }else{
                    boolean isApproved = approved.equals("approved");
                    courses = CourseDB.GetCourseByApproved(isApproved);
                }
                
                if (!search.equals(""))
                {
                    courses = CourseDB.GetCoursesByFilter(search);
                }
                
                if(courses != null)
                {
                    JsonArray coursesJson = CourseDB.ConvertListToJsonArray(courses);
                    request.setAttribute("CoursesJson", coursesJson);
                }
                else
                {
                    request.setAttribute("CoursesJson", null);
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
