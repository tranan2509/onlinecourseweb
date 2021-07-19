/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.AccountDB;
import DAO.RoleDB;
import DAO.URL;
import DAO.UserDB;
import Model.Account;
import Model.Role;
import Model.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.Timestamp;
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
@WebServlet(name = "AddAdminController", urlPatterns = {"/add-admin"})
public class AddAdminController extends HttpServlet {

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
        
        String url = "/account-managerment";
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("User");
        if (user != null  && user.getRole().getRoleName().equals("admin"))
        {
            String nameAdd = request.getParameter("nameAdd");
            if (nameAdd == null)
                nameAdd = "";
            request.setAttribute("NameAdd", nameAdd);
            String dateOfBirthAdd = request.getParameter("dateOfBirthAdd");
            if (dateOfBirthAdd == null)
                dateOfBirthAdd = "";
            request.setAttribute("DateOfBirthAdd", dateOfBirthAdd);
            String genderAdd = request.getParameter("genderAdd");
            if (genderAdd == null)
                genderAdd = "";
            request.setAttribute("GenderAdd", genderAdd);
            String emailAdd = request.getParameter("emailAdd");
            if (emailAdd == null)
                emailAdd = "";
            request.setAttribute("EmailAdd", emailAdd);
            String phoneAdd = request.getParameter("phoneAdd");
            if (phoneAdd == null)
                phoneAdd = "";
            request.setAttribute("PhoneAdd", phoneAdd);
            String passwordAdd = request.getParameter("passwordAdd");
            if (passwordAdd == null)
                passwordAdd = "";
             request.setAttribute("PasswordAdd", passwordAdd);
            String confirmPasswordAdd = request.getParameter("confirmPasswordAdd");
            if (confirmPasswordAdd == null)
                confirmPasswordAdd = "";
            request.setAttribute("ConfirmPasswordAdd", confirmPasswordAdd);
            
            String errorAddAdmin = "";
            if (!"".equals(nameAdd.trim()))
            {
                if (passwordAdd.trim().equals(confirmPasswordAdd.trim()))
                {
                    if (UserDB.GetUserByEmail(emailAdd) == null)
                    { 
                        if (UserDB.GetUserByPhone(phoneAdd) == null)
                        {
                            Timestamp createDate = new Timestamp(System.currentTimeMillis());
                            boolean isInserted = false;
                          
                            boolean isMale = "true".equals(genderAdd);
                            int roleId = 1; //Admin
                            try{
                                Date birthDateUser = Date.valueOf(dateOfBirthAdd); 
                                Role roleAdd = RoleDB.GetRoleByRoleId(roleId);
                                
                                Account accountAdd = new Account(passwordAdd, true);
                                boolean isInsertedAcc = AccountDB.InsertAccount(accountAdd);
                                accountAdd.setAccountId(AccountDB.MaxAccountId());
                                User userAdd = new User(nameAdd, birthDateUser, emailAdd, isMale, phoneAdd, roleAdd , accountAdd, createDate);
                                isInserted = UserDB.InsertAccount(userAdd);
                                System.out.println("Result: " + isInserted);
                            }catch(Exception e)
                            {
                                errorAddAdmin = "Ngày sinh không hợp lệ!";
                            }
                            if (!isInserted)
                            {
                                errorAddAdmin = "Thêm nhân viên quản lý thất bại!";
                            }
                            else
                            {
                                url = "/account-managerment";
                            }
                        }
                        else
                        {
                            errorAddAdmin = "Số điện thoại đã tồn tại!";
                        }
                    }
                    else
                    {
                        errorAddAdmin = "Email đã tồn tại!";
                    }
                }
                else
                {
                    errorAddAdmin = "Mật khẩu không khớp! " + passwordAdd + " " + confirmPasswordAdd;
                }
            }
            else{
                errorAddAdmin = "Không để trống dữ liệu!";
            }
            request.setAttribute("ErrorAddAdmin", errorAddAdmin);
            System.out.println("ErrorAddAdmin: " + errorAddAdmin);
        }
        else
        {
            url = "/sign-in";
        }
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
