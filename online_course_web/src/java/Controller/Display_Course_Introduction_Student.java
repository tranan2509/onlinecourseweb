/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.*;
import DAO.*;

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
 * @author A556U
 */
@WebServlet(name = "Display_Course_Introduction_Student", urlPatterns = {"/Display_Course_Introduction_Student"})
public class Display_Course_Introduction_Student extends HttpServlet {

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

        String url = "/Views/Pages/Course/Course_Introduction_Student.jsp";
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("User");
            
         if (user == null ) {
            url = "/sign-in";
        }
        else if(user.getRole().getRoleId()!=3 && user.getRole().getRoleId()!=1)
        {
             url = "/Views/Pages/Home/home.jsp";
        } else {
            try
            {   
                int courseid = Integer.parseInt(request.getParameter("courseid"));
            //Course course = (Course) request.getAttribute("course");   
                Course course = CourseDB.getCourseById(courseid);
                

                int maxChap = 0;

                if (course == null) {
                    String message = "Không tìm thấy khóa học!";
                    request.setAttribute("message", message);
                }
                if (course != null) {
                    request.setAttribute("course", course);
                    List<Chap> chapList = ChapDB.getAllChapByCourse(course);
                    if (chapList != null) {
                        for (Chap c : chapList) {
                            int chapOrder = c.getChapOrder();
                            request.setAttribute("chap" + chapOrder, c);
                            maxChap = chapOrder;

                            List<Part> partList = PartDB.getAllPartOfChap(course, c);
                            if (partList != null) {
                                for (Part p : partList) {
                                    request.setAttribute("chap" + chapOrder + "_part" + p.getPartOrder(), p);
                                }
                            }
                        }
                    }

                    //Các câu hỏi thường gặp
                    List<FAQ> faqList = FAQDB.getAllFAQOfCourse(course);
                    if (faqList != null) {
                        for (FAQ f : faqList) {
                            request.setAttribute("FAQ" + f.getFAQOrder(), f);
                        }
                    }
                    
                    List<Instructor> instructorList = InstructorDB.getAllInstructorsByCourse(course);
                    if ( instructorList != null)
                    {
                        for (int i = 0; i < instructorList.size(); i++) {
                            request.setAttribute("instructor" + (i + 1), instructorList.get(i));
                        }
                    }
                }

                request.setAttribute("maxChap", maxChap);
            }
            catch(Exception ex)
            {
                url="/student";
            }
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
