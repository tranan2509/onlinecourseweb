package Controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import Model.*;
import DAO.*;
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
 * @author A556U
 */
public class Display_Excercise_Teacher extends HttpServlet {

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
        int courseid = -1;
        int chaporder = -1;
        int partorder = -1;

        String url = "/Views/Pages/Course/excercise_teacher.jsp";

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("User");

        String requirement = request.getParameter("requirement");

         if (user == null ) {
            url = "/sign-in";
        }
        else if(user.getRole().getRoleId()!=2)
        {
             url = "/Views/Pages/Home/home.jsp";
        } else {
            //không phải là tạo kh
                try {
                    courseid = Integer.parseInt(request.getParameter("courseid"));
                    chaporder = Integer.parseInt(request.getParameter("chapid"));
                    partorder = Integer.parseInt(request.getParameter("partid"));
                } catch (Exception ex) {
                     request.setAttribute("message", "Không tìm thấy phần bài học để thêm bài tập!");
                }

                Course course = CourseDB.GetCourseByCourseId(courseid);
                Chap chap = ChapDB.getChapOfCourseByOrder(course, chaporder);
                part = PartDB.getPartByCourseAndChap(course, chap, partorder);
                int maxExcercise = 0;
                        
                if (part == null) {
                    request.setAttribute("message", "Không tìm thấy phần bài học để thêm bài tập!");
                    url= "/"+ (String)request.getParameter("previousPage");
                } 
                else if(!CourseDB.courseOfTeacherExists(course.getCourseId(), user))
                {
                     request.setAttribute("message", "Bạn không có khóa học này!");
                     url="/teacher";
                     
                }else {

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
