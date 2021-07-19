package Controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import DAO.*;
import Model.*;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import com.google.gson.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author TRAN VAN AN
 */
@WebServlet(urlPatterns = {"/account-managerment"})
public class AccountManagermentController extends HttpServlet {

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
            throws ServletException, IOException, ParseException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        
        String url = "/Views/Pages/Admin/account_managerment.jsp";
        
        
        String accountType = request.getParameter("accountType");
        if (accountType == null)
            accountType = "all";
        request.setAttribute("AccountType", accountType);
        String accountState = request.getParameter("accountState");
        if (accountState == null)
            accountState = "all";
        request.setAttribute("AccountState", accountState);
        String search = request.getParameter("search");
        if (search == null)
            search = "";
        request.setAttribute("Search", search);
        
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("User");

        if (user != null && user.getRole().getRoleName().equals("admin"))
        {
            String statusString = request.getParameter("status");
            String userIdString = request.getParameter("userId");
            if (statusString != null && userIdString != null)
            {
                boolean status = statusString.equals("true");
                int userId = Integer.parseInt(userIdString);
                User userUpdate = UserDB.GetUserByUserId(userId);
                Account account = userUpdate.getAccount();
                if (account.isStatus() != status)
                {
                    boolean isUpdated = AccountDB.UpdateStatusByUserId(account.getAccountId(), !account.isStatus());
                }
            }
            
            List<Role> roles = RoleDB.GetRolesByRole(accountType);
            //List<Account> accounts = AccountDB.GetAccountsByStatus(accountState);
            List<User> users;
            if (!search.equals(""))
            {
                users = UserDB.GetUsersByFilter(null, search);
            }
            else {
                System.out.println("+++++" + roles.size());
                if (roles.size() == 1)
                {
                    System.out.println("+++++");
                    Role roleFilter = roles.get(0);
                    users = UserDB.GetUsersByFilter(roleFilter, "");
                }
                else
                {
                    users = UserDB.GetUsers();
                }
                users = UserDB.GetUsersByStatus(users, accountState);
            }
            request.setAttribute("Users", users);
            
            JsonArray accountsJson;
            if (users != null)
            {
                accountsJson = AccountDB.ConvertListAccountsToJsonArray(users);
            }else{
                accountsJson = null;
            }
            request.setAttribute("AccountsJson", accountsJson);
            
            //Show form add admin
            String isShowAddAdmin = request.getParameter("isShowAddAdmin");
            if (isShowAddAdmin == null)
                isShowAddAdmin = "false";
            request.setAttribute("IsShowAddAdmin", isShowAddAdmin);
            
            //Code xử lý thống kê
            String isShowReport = request.getParameter("isShowReport");
            if (isShowReport == null)
                isShowReport = "false";
            request.setAttribute("IsShowReport", isShowReport);
            String dateReport = request.getParameter("dateReport");
            if (dateReport == null)
            {
                 Timestamp time = new Timestamp(System.currentTimeMillis());
                 dateReport = (new Date(time.getTime())).toString();
            }
            request.setAttribute("DateReport", dateReport);
            String typeDate = request.getParameter("typeDate");
            if (typeDate == null)
                typeDate = "week";
            request.setAttribute("TypeDate", typeDate);
            
            SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
            //java.sql.Date dateReportDate =  Date.valueOf(dateReport); //formatDate.parse(dateReport);
            java.util.Date dateReportDate = (java.util.Date) formatDate.parse(dateReport);
            List<User> userReport = UserDB.GetUsersByTypeDate(typeDate, dateReportDate);
            String reportAccounts ;
            if (userReport != null)
            {
                Map<String, Integer> maps = UserDB.ReportRegistrationAccount(userReport);
    //            List<String> dates = maps.keySet().stream().collect(Collectors.toCollection(ArrayList::new));
    //            List<Integer> listusers = maps.values().stream().collect(Collectors.toCollection(ArrayList::new));
                Gson gson = new Gson();
                reportAccounts = gson.toJson(maps);
            }
            else
            {
                reportAccounts = "";
            }
            request.setAttribute("ReportAccounts", reportAccounts);
            
            String errorAddAdmin = (String)request.getAttribute("ErrorAddAdmin");
            if (errorAddAdmin == null)
                errorAddAdmin = "";
            request.setAttribute("ErrorAddAdmin", errorAddAdmin);
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
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(AccountManagermentController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(AccountManagermentController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
