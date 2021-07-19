/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.AccountDB;
import DAO.URL;
import DAO.UserDB;
import Model.Account;
import Model.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 *
 * @author TRAN VAN AN
 */
@WebServlet(name = "SendMailController", urlPatterns = {"/send-mail"})
public class SendMailController extends HttpServlet {

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
        
        String url = "/Views/Pages/Login/getPassword.jsp";
        
        HttpSession session = request.getSession();
        String errorSendMail = "";
        User user = null;
        String email = request.getParameter("email");
        if (email == null)
            email = "";
        session.setAttribute("Email", email);
        user = UserDB.GetUserByEmail(email);
        Account account = null;
        String code = "";
        if (user != null)
        {
            code = AccountDB.SendMail(email, "", "");
//            account = AccountDB.GetAccountByUserId(user.getUserId());
//            session.setAttribute("Account", account);
            session.setAttribute("Code", code);
            session.setAttribute("UserId", user.getUserId());
        }
        else
        {
            errorSendMail = "Email chưa đăng kí!";
        }
        
        session.setAttribute("ErrorSendMail", errorSendMail);
         if (!url.contains(".jsp"))
        {
            response.setStatus(response.SC_MOVED_TEMPORARILY); 
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
