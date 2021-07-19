/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.AccountDB;
import DAO.URL;
import Model.*;
import Provider.SameSiteAttribute;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author TRAN VAN AN
 */
@WebServlet(name = "SignInController", urlPatterns = {"/sign-in"})
public class SignInController extends HttpServlet {

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
        
        String url = "/Views/Pages/Login/signIn.jsp";
        HttpSession session = request.getSession();
        session.setAttribute("User", null);
        
        String errorSignIn = "";
        
        String email = request.getParameter("email");
        if (email == null || email.length()>100)
        {
            email = "";
        }
        request.setAttribute("Email", email);
        
        String password = request.getParameter("password");
        if (password == null || password.length()>100)
        {
            password = "";
        }
        request.setAttribute("Password", password);
        User user = null;
        if (!email.isEmpty() && !password.isEmpty())
        {
            user = AccountDB.IsLoginSuccess(email, password);
            if (user != null)
            {
//                response.addCookie(new Cookie("userId", String.valueOf(user.getUserId())));
                SameSiteAttribute.addSameSiteAttribute((HttpServletResponse) response);
                url = "/" + user.getRole().getRoleName();
                //url = "/home";
            }
            else
            {
                errorSignIn = "Email hoặc mật khẩu không đúng!";
            }
        }
        
        session.setAttribute("User", user);
        
        request.setAttribute("ErrorSignIn", errorSignIn);
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
