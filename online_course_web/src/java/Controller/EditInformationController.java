/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.URL;
import DAO.UserDB;
import Model.User;
import Provider.SameSiteAttribute;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
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
@WebServlet(name = "EditInformationController", urlPatterns = {"/edit-information"})
public class EditInformationController extends HttpServlet {

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
        
        String url = "/admin";
        HttpSession session = request.getSession();
        String urlShowEditInfo = "";
        User user = (User)session.getAttribute("User");
        if (user != null)
        {
            response.addCookie(new Cookie("userId", String.valueOf(user.getUserId())));
            SameSiteAttribute.addSameSiteAttribute((HttpServletResponse) response);
            //Show form edit information
            urlShowEditInfo = "/account-profile?isShowEditInfo";
            
            String errorEditInformation = "";
            url = "/" + user.getRole().getRoleName();
            String userIdEdit = request.getParameter("userIdEdit");
            if (userIdEdit == null)
                userIdEdit = "";
            request.setAttribute("UserIdEdit", userIdEdit);
            String nameEdit = request.getParameter("nameEdit");
            if (nameEdit == null)
                nameEdit = "";
            request.setAttribute("NameEdit", nameEdit);
            String dateOfBirthEdit = request.getParameter("dateOfBirthEdit");
            if (dateOfBirthEdit == null)
                dateOfBirthEdit = "";
            request.setAttribute("DateOfBirthEdit", dateOfBirthEdit);
            String genderEdit = request.getParameter("genderEdit");
            if (genderEdit == null)
                genderEdit = "";
            request.setAttribute("GenderEdit", genderEdit);
            String emailEdit = request.getParameter("emailEdit");
            if (emailEdit == null)
                emailEdit = "";
            request.setAttribute("EmailEdit", emailEdit);
            String phoneEdit = request.getParameter("phoneEdit");
            if (phoneEdit == null)
                phoneEdit = "";
            request.setAttribute("PhoneEdit", phoneEdit);
            
            if (!"".equals(userIdEdit) && !"".equals(nameEdit) && !"".equals(dateOfBirthEdit) && !"".equals(genderEdit) && !"".equals(emailEdit) && phoneEdit != "")
            {
                User userEdit = UserDB.GetUserByEmail(emailEdit);
                if (userEdit == null)
                {
                    boolean gender = genderEdit.equals("true");
                    Date birthDateUser = Date.valueOf(dateOfBirthEdit); 
                    if (userIdEdit.equals(String.valueOf(user.getUserId()))){
                         boolean isUpdated = UserDB.UpdateUser(Integer.parseInt(userIdEdit), nameEdit, birthDateUser, gender, emailEdit, phoneEdit);
                        userEdit = UserDB.GetUserByUserId(Integer.parseInt(userIdEdit));
                        if (!isUpdated)
                            urlShowEditInfo = "/account-profile?isShowEditInfo=true";
                        if (url.equals("/admin"))
                            session.setAttribute("User", userEdit);
                    }
                    else{
                        urlShowEditInfo = "/account-profile?isShowEditInfo=true";
                        errorEditInformation = "Bạn không có quyền thay đổi thông tin tài khoản người khác!";
                    }
                }
                else
                {
                    if (userIdEdit.equals(String.valueOf(user.getUserId()))){
                        if (userEdit.getEmail().equals(user.getEmail()))
                        {
                            boolean gender = genderEdit.equals("true");
                            Date birthDateUser = Date.valueOf(dateOfBirthEdit); 
                            boolean isUpdated = UserDB.UpdateUser(Integer.parseInt(userIdEdit), nameEdit, birthDateUser, gender, emailEdit, phoneEdit);
                            userEdit = UserDB.GetUserByUserId(Integer.parseInt(userIdEdit));
                            if (!isUpdated)
                                urlShowEditInfo = "/account-profile?isShowEditInfo=true";
                            if (url.equals("/admin"))
                                session.setAttribute("User", userEdit);
                        }
                          else
                        {
                            urlShowEditInfo = "/account-profile?isShowEditInfo=true";
                            errorEditInformation = "Địa chỉ email đã tồn tại!";
                        }
                    }
                     else{
                        urlShowEditInfo = "/account-profile?isShowEditInfo=true";
                        errorEditInformation = "Bạn không có quyền thay đổi thông tin tài khoản người khác!";
                    }
                }
            }
            session.setAttribute("ErrorEditInformation", errorEditInformation);
            url = urlShowEditInfo;
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
