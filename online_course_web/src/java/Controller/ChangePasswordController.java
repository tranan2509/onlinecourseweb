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
import Provider.Validate;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "ChangePasswordController", urlPatterns = {"/change-password"})
public class ChangePasswordController extends HttpServlet {

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
        
        String url = "/Views/Pages/Login/changePassword.jsp";
        HttpSession session = request.getSession();
        //Account account = (Account)session.getAttribute("Account");
        User user = (User)session.getAttribute("User");
        User userChangePassword;
        String errorChangePassword = "";
        boolean allowChange = true;
        session.setAttribute("ErrorSendMail", null);
        int userId;
        if (user == null)
        {
            userId = (int)session.getAttribute("UserId");
            userChangePassword = UserDB.GetUserByUserId(userId);
        }
        else{
            userChangePassword = user;
            String roleName = user.getRole().getRoleName();
            url = "/" + roleName;
            String oldPassword = request.getParameter("oldPassword");
            if (oldPassword == null)
                oldPassword = "";
            if (!oldPassword.equals(user.getAccount().getPassword()))
            {
                allowChange = false;
            }
        }
       
        if (allowChange)
        {
            Account account = userChangePassword.getAccount();
            String password = request.getParameter("password");
            if (password == null)
                password = "";
            request.setAttribute("Password", password);
            String rePassword = request.getParameter("rePassword");
            if (rePassword == null)
                rePassword = "";
            request.setAttribute("RePassword", rePassword);

            if (!"".equals(rePassword) && !"".equals(password) && account != null)
            {
                if (Validate.validatePassword(password)){
                    errorChangePassword = "Mật khẩu phải nhiều hơn 8 kí tự và phải bao gồm chữ hoa, chữ thường và số!!";
                }else if (password.equals(rePassword))
                {
                    boolean isChange = AccountDB.ChangePassword(account, password);
                    if (isChange)
                    {
                        if (user == null)
                        {
                            url = "/sign-in";
                            session.setAttribute("User", null);
                        }
                        else
                        {
                            url = "/account-profile?isShowEditPass=true";
                            errorChangePassword = "Đổi mật khẩu thành công!";
                            session.setAttribute("Code", null);
                        }
                    }
                    else
                    {
                        if (user != null) url = "/account-profile?isShowEditPass=true";
                        errorChangePassword = "Đổi mật khẩu không thành công!";
                    }
                }
                else
                {
                    if (user != null) url = "/account-profile?isShowEditPass=true";
                    errorChangePassword = "Xác nhận mật khẩu không khớp!";
                }
            }
            else
            {
                if (user != null) url = "/account-profile?isShowEditPass=true";
                 errorChangePassword = "Không được để trống dữ liệu!";
            }
        }
        else
        {
            if (user != null) url += "/account-profile?isShowEditPass=true";
            errorChangePassword = "Mật khẩu cũ không đúng!";
        }
        session.setAttribute("ErrorChangePassword", errorChangePassword);
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
