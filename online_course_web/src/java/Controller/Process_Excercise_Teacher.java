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
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author A556U
 */
public class Process_Excercise_Teacher extends HttpServlet {

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
        System.out.println("=========================");
        HttpSession session = request.getSession();
        Part part = (Part) session.getAttribute("part");
        User user = (User) session.getAttribute("User");
        //The message sent to jsp
        String message = "";

        String url = "/Views/Pages/Course/excercise_teacher.jsp";

        //the following part to test
        // part = new Part(1, 1, 1, "");
         if (user == null ) {
            url = "/sign-in";
        }
        else if(user.getRole().getRoleId()!=2)
        {
             url = "/Views/Pages/Home/home.jsp";
        }else {
            if (part != null) {
                part = PartDB.getPartByCourseAndChap(part.getCourse(), part.getChap(), part.getPartOrder());
            } else {
                message = "Hiện không tìm thấy phần bài để thêm bài tập";
            }

            //MaxExcercise 
            int maxExcercise = 0;
            if (part != null) {
                request.setAttribute("part", part);
                Course course = part.getCourse();
                Chap chap = part.getChap();
//            int partintid = part.getPartId();

                for (int excerciseOrder = 1; excerciseOrder <= 30; excerciseOrder++) {
                    String question = (String) request.getParameter("question" + excerciseOrder);
                    String ansA = (String) request.getParameter("answer" + excerciseOrder + "_A");
                    String ansB = (String) request.getParameter("answer" + excerciseOrder + "_B");
                    String ansC = (String) request.getParameter("answer" + excerciseOrder + "_C");
                    String ansD = (String) request.getParameter("answer" + excerciseOrder + "_D");
                    String correctAns = (String) request.getParameter("correctAnswer" + excerciseOrder);
                    String explaination = (String) request.getParameter("explaination" + excerciseOrder);

                    if (question == null) {
                        question = "";
                    }
                    if (ansA == null) {
                        ansA = "";
                    }
                    if (ansB == null) {
                        ansB = "";
                    }
                    if (ansC == null) {
                        ansC = "";
                    }
                    if (ansD == null) {
                        ansD = "";
                    }
                    if (correctAns == null) {
                        correctAns = "";
                    }
                    if (explaination == null) {
                        explaination = "";
                    }
                    try {
                        if (!question.equals("") || !ansA.equals("") || !ansB.equals("") || !ansC.equals("")
                                || !ansD.equals("")) {
                            Excercise excercise = ExcerciseDB.getExercise(course, chap, part, excerciseOrder);

                            maxExcercise = excerciseOrder;

                            //update if the excercise exists
                            if (excercise != null) {
                                System.out.println("=========================");

                                excercise.setAnswerA(ansA);
                                excercise.setAnswerB(ansB);
                                excercise.setAnswerC(ansC);
                                excercise.setAnswerD(ansD);
                                excercise.setCorrectAnswer(correctAns);
                                excercise.setExplaination(explaination);
//                            if(excercise!=null)
//                                message = String.valueOf( excercise.getExcerciseId());
//                            else
//                                message="null nha";
                                if (!ExcerciseDB.updateExcercise(excercise)) {
                                    message = "Lưu thất bại!";
                                }

                            } else {
                                excercise = new Excercise(excerciseOrder, course, chap, part, question, ansA, ansB, ansC, ansD, correctAns, explaination);
                                if (!ExcerciseDB.insertExcercise(excercise)) {
                                    message = "Lưu thất bại!";
                                }

                            }

                            request.setAttribute("Excercise" + excerciseOrder, excercise);
                        } else//Delete the excercise
                        {
                            if (!ExcerciseDB.deleteExcercise(course, chap, part, excerciseOrder)) {
                                message = "Lưu thất bại!";
                            }
                        }
                    } catch (Exception ex) {
                        message = "Lưu thất bại!";
                    }
                }
            }

            if (message.equals("")) {
                message = "Lưu thành công";
            }
            request.setAttribute("maxExcercise", maxExcercise);
            request.setAttribute("message", message);
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
