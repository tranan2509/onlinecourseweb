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
import DAO.PartFilesDB;
import DAO.RegistrationDB;
import Model.Chap;
import Model.Course;
import Model.Excercise;
import Model.Part;
import Model.PartFiles;
import Model.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ad
 */
public class Display_Part_Student extends HttpServlet {

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
        Part part = null;
    
        String url = "/Views/Pages/Course/Lesson.jsp";
          
//        try {
            int courseid = Integer.parseInt(request.getParameter("courseid"));
            int chapOrder = Integer.parseInt(request.getParameter("chapid"));
            int partOrder = Integer.parseInt(request.getParameter("partid"));

            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("User");

            String requirement = request.getParameter("requirement");

            if (user == null) {
                url = "/sign-in";
            } else if (user.getRole().getRoleId() != 3) {
                url = "/Views/Pages/Home/home.jsp";
            }          
            else {
                Course course = CourseDB.GetCourseByCourseId(courseid);
                Chap chap = ChapDB.getChapOfCourseByOrder(course, chapOrder);
                
                part = PartDB.getPartByCourseAndChap(course, chap, partOrder);
               
                
                if (part == null) {
                    request.setAttribute("message", "Không tìm thấy phần bài tập của bài học!");
                      url = "/Display_Course_Introduction_Student?courseid="+courseid ;
                } 
//                else if(RegistrationDB.registrationExists(user, course)==false)
//                {
//                    request.setAttribute("message", "Bạn chưa đăng kí khóa học!");
//                    url = "/Display_Course_Introduction_Student?courseid="+courseid ;
//                }
                else {
                    List<PartFiles> partfiles = PartFilesDB.GetPartFilesBy(part);
                    request.setAttribute("partfiles", partfiles);
                    
                    session.setAttribute("part", part);
                    System.out.println(part);
//                    List<Excercise> excerciseList = ExcerciseDB.getAllExcercisePartOfPart(course, chap, part);
//                    if (excerciseList != null) {
//                        for (Excercise e : excerciseList) {
//                            request.setAttribute("Excercise" + e.getExcerciseOrder(), e);
//                            maxExcercise = e.getExcerciseOrder();
//                        }
//                    }
                }


//            }
//        } catch (Exception ex) {
//            //url nhớ trả về trang sinh viên
//            url = "/Views/Pages/Home/home.jsp";
//            request.setAttribute("message", "Không thấy phần bài học");
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
