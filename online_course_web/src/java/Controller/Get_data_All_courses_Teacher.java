/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.PartDB;
import DAO.StudentExcerciseDB;
import DAO.UserDB;
import Model.Part;
import Model.StudentExcercise;
import Model.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
 * @author Thuy Dung
 */
@WebServlet(name = "Get_data_All_courses_Teacher", urlPatterns = {"/Get_data_All_courses_Teacher"})
public class Get_data_All_courses_Teacher extends HttpServlet {

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
        User user = (User) session.getAttribute("User");

        String url = "/Views/Pages/Teacher/exercise_statistic_teacher.jsp";
        String message = "";

        if (user == null) {
            url = "/sign-in";
        } else if (user.getRole().getRoleId() != 2) {
            url = "/home";
        } else {
            user = UserDB.GetUserByUserId(user.getUserId());

            if (user == null) {
                url = "/sign-in";
            } else {
                session.setAttribute("User", user);
                String partid = request.getParameter("partid");

               List sumPeople = new ArrayList<>();
               sumPeople.add(0);
               List NumberOf25percent = new ArrayList<>();
               NumberOf25percent.add(0);
                 List NumberOf25To50percent = new ArrayList<>();
               NumberOf25To50percent.add(0);
                List NumberOf50To75percent = new ArrayList<>();
               NumberOf50To75percent.add(0);
               List NumberOf75To100percent = new ArrayList<>();
               NumberOf75To100percent.add(0);
                if (partid != null && partid != "") {
                    Part part = PartDB.GetPartByPartId(Integer.parseInt(partid));
                    if (part != null) {
                        
                        
//                        User u = UserDB.GetUserByUserId(5);
//                        int t = StudentExcerciseDB.NumberOfCorrectAnwersByUser(u, part, 1);
//                        request.setAttribute("test", t);
                        
                        int sumExcercises = part.getExcercises().size();
                        request.setAttribute("sumExcercises", sumExcercises);
                        int soLanThu = 3;
                        for (int i = 1; i <= soLanThu; i++) {
                            int sum = StudentExcerciseDB.sumOfPeopleAttempting(part, i);
                           sumPeople.add(sum);
                           int sum25 = StudentExcerciseDB.NumberOfUsersWith25percentCorrectAnswer(part, i);
                           NumberOf25percent.add(sum25);
                           int sum50 = StudentExcerciseDB.NumberOfUsersWith25percentTo50percentCorrectAnswer(part, i);
                           NumberOf25To50percent.add(sum50);
                            int sum75 = StudentExcerciseDB.NumberOfUsersWith50percentTo75percentCorrectAnswer(part, i);
                           NumberOf50To75percent.add(sum75);
                            int sum100 = StudentExcerciseDB.NumberOfUsersWith75percentTo100percentCorrectAnswer(part, i);
                           NumberOf75To100percent.add(sum100);
                        }
                    }
                }
                request.setAttribute("sumPeople", sumPeople);
                 request.setAttribute("NumberOf25percent", NumberOf25percent);
                  request.setAttribute("NumberOf25To50percent", NumberOf25To50percent);
                  request.setAttribute("NumberOf50To75percent", NumberOf50To75percent);
                    request.setAttribute("NumberOf75To100percent", NumberOf75To100percent);
            }
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
