/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.ChapDB;
import DAO.CourseDB;
import DAO.ExcerciseDB;
import DAO.PartDB;
import DAO.RegistrationDB;
import Model.Chap;
import Model.Course;
import Model.Excercise;
import Model.Part;
import Model.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSession;

/**
 *
 * @author A556U
 */
public class Display_Excercise_Student extends HttpServlet {

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
        Part part = null;
    
        String url = "/Views/Pages/Course/excercise_student.jsp";
          
        try {
            int courseid = Integer.parseInt(request.getParameter("courseid"));
            int chapOrder = Integer.parseInt(request.getParameter("chapid"));
            int partOrder = Integer.parseInt(request.getParameter("partid"));

            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("User");

            String requirement = request.getParameter("requirement");

            if (user == null) {
                url = "/sign-in";
            } else if (user.getRole().getRoleId() != 3 && user.getRole().getRoleId()!=1) {
                url = "/Views/Pages/Home/home.jsp";
            }          
            else {
                Course course = CourseDB.GetCourseByCourseId(courseid);
                Chap chap = ChapDB.getChapOfCourseByOrder(course, chapOrder);
                part = PartDB.getPartByCourseAndChap(course, chap, partOrder);

                int maxExcercise = 0;

                if (part == null) {
                    request.setAttribute("message", "Kh??ng t??m th???y ph???n b??i t???p c???a b??i h???c!");
                      url = "/Display_Course_Introduction_Student?courseid="+courseid ;
                } 
                else if(RegistrationDB.registrationExists(user, course)==false)
                {
                    request.setAttribute("message", "B???n ch??a ????ng k?? kh??a h???c!");
                    url = "/Display_Course_Introduction_Student?courseid="+courseid ;
                }
                else {

                    session.setAttribute("part", part);
                    
                    List<Excercise> excerciseList = ExcerciseDB.getAllExcercisePartOfPart(course, chap, part);
                    if (excerciseList != null) {
                        for (Excercise e : excerciseList) {
                            request.setAttribute("Excercise" + e.getExcerciseOrder(), e);
                            maxExcercise = e.getExcerciseOrder();
                        }
                    }
                }

                request.setAttribute("maxExcercise", maxExcercise);
            }
        } catch (Exception ex) {
            //url nh??? tr??? v??? trang sinh vi??n
            url = "/Views/Pages/Home/home.jsp";
            request.setAttribute("message", "Kh??ng th???y ph???n b??i t???p");
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
