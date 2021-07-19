/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.ExcerciseDB;
import DAO.PartDB;
import DAO.StudentExcerciseDB;
import DAO.UserDB;
import Model.Chap;
import Model.Course;
import Model.Excercise;
import Model.Part;
import Model.StudentExcercise;
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
 * @author A556U
 */
public class Process_Excercise_Student extends HttpServlet {

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

        HttpSession session = request.getSession();
        Part part = (Part) session.getAttribute("part");
        User user = (User)session.getAttribute("User");

        //The message sent to jsp
        String message = "";

        String url = "/Views/Pages/Course/excercise_student.jsp";
        //the following part to test
        // part = new Part(1, 1, 1, "");
        int maxExcercise = 0;

        if (part == null) {
            request.setAttribute("message", "Không tìm thấy phần bài tập!");
//            url = "/" + (String) request.getParameter("previousPage");
        }
        if (user == null ) {
            url = "/sign-in";
        }
        else if(user.getRole().getRoleId()!=3)
        {
             url = "/Views/Pages/Home/home.jsp";
        }else {
            session.setAttribute("part", part);
             
            Course course = part.getCourse();
            Chap chap = part.getChap();
            
            List<Excercise> excerciseList = ExcerciseDB.getAllExcercisePartOfPart(course,chap, part);
            
            maxExcercise=excerciseList.size();
            if (excerciseList != null) {
                
                //get max time of attemps
                int maxTime = StudentExcerciseDB.getMaxTime(user, course, chap, part);
                
                for (Excercise e : excerciseList) {
                    request.setAttribute("Excercise" + e.getExcerciseOrder(), e);
                    maxExcercise = e.getExcerciseOrder();
                    
                    String answerOfStudent = (String)request.getParameter("answer"+e.getExcerciseOrder());
                    
                    request.setAttribute("answer"+e.getExcerciseOrder(), answerOfStudent);
                    
                    //whether the ans is correct
                    boolean correct =false;
                    
                    if(answerOfStudent!=null && e.getCorrectAnswer().equals(answerOfStudent))
                    {
                        request.setAttribute("resultOfAnswer"+e.getExcerciseOrder(), "Trả lời đúng");   
                        correct=true;
                    }
                    else 
                    {
                        request.setAttribute("resultOfAnswer"+e.getExcerciseOrder(), "Trả lời sai! Đáp án đúng là "+e.getCorrectAnswer());                     
                    }
                    
                    StudentExcercise stEx= new StudentExcercise(user, course, chap, part, e, correct, maxTime+1, answerOfStudent);

                    //Add anser into database
                    if(!StudentExcerciseDB.insert(stEx))
                    {
                        message="Lưu thông tin thất bại";
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
