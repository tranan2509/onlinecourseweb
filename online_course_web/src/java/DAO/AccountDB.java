/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.*;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.*;
import javax.persistence.*;
import javax.mail.*;
import javax.mail.internet.*;
import org.apache.commons.text.StringEscapeUtils;
/**
 *
 * @author TRAN VAN AN
 */
public class AccountDB {
    
    public static boolean IsExistEmail(String email)
    {
        User user = UserDB.GetUserByEmail(email);
        return user != null;
    }
    
    public static User GetAccountByAccountId(int userId)
    {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try{
            User user = em.find(User.class, userId);
            return user;
        }finally{
            em.close();
        }
    }
    
    public static boolean InsertAccount(Account account)
    {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        boolean isInserted = false;
        try{
            trans.begin();
            em.persist(account);
            trans.commit();
            isInserted = true;
        }catch(Exception ex)
        {
            trans.rollback();
        }finally{
            em.close();
        }
        return isInserted;
    }
    
    public static User IsLoginSuccess(String email, String password)
    {
        User user = UserDB.GetUserByEmail(email);
        Account account;
        if (user != null)
            account = user.getAccount();
        else
            return null;
        if (account == null || !account.getPassword().equals(password) || !account.isStatus())
            return null;
        return user;
    }
    
    public static Account GetAccountByUser(User user)
    {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try{
            Account acc = em.find(Account.class, user);
            return acc;
        }finally{
            em.close();
        }
    }
    
    
    
     public static List<Account> GetAccounts()
    {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "SELECT a FROM Account AS a";
        TypedQuery<Account> q = em.createQuery(qString, Account.class);
        List<Account> accounts = null;
        try{
            accounts = q.getResultList();
            if (accounts == null || accounts.isEmpty())
                accounts = null;
        }catch (Exception ex){
            System.out.println("Error: " + ex.getMessage());
        }
        finally{
            em.close();
        }
        return accounts;
    }
     
    
     public static List<Account> GetAccountsByStatus(String state)
    {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "";
        if (state.equals("all"))
            qString = "SELECT a FROM Account AS a";
        else
            qString = "SELECT a FROM Account a WHERE a.Status = :status";
        TypedQuery<Account> q = em.createQuery(qString, Account.class);
        if (!state.equals("all"))
        {
            boolean status = GetStatusByState(state);
            q.setParameter("status", status);
        }
        List<Account> accounts = null;
        try{
            accounts = q.getResultList();
            if (accounts == null || accounts.isEmpty())
                accounts = null;
        }catch (Exception ex){
            System.out.println("Error: " + ex.getMessage());
        }
        finally{
            em.close();
        }
        return accounts;
    }
     
    public static boolean GetStatusByState(String state)
    {
        if (state.equals("activated") )
            return true;
        return false;
    }
    
    public static int CountAccountActivated()
    {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "SELECT COUNT(*) FROM Account WHERE status = 1";
        try
        {
            int count = Integer.parseInt(em.createNativeQuery(qString).getSingleResult().toString());
            return count;
        }catch(Exception ex)
        {
            return 0;
        }finally{
            em.close();
        }
    }
     
     public static int CountAccountLocked()
    {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "SELECT COUNT(*) FROM Account WHERE status = 0";
        try
        {
            int count = Integer.parseInt(em.createNativeQuery(qString).getSingleResult().toString());
            return count;
        }catch(Exception ex)
        {
            return 0;
        }finally{
            em.close();
        }
    }
    
    public static String SendMail(String email, String subject, String text)
    {
        String code = CreateCode();
        try{
                Properties props = new Properties();
                props.put("mail.transport.protocol", "smtps");
                props.put("mail.smtps.host", "smtp.gmail.com");
                props.put("mail.smtps.port", 465);
                props.put("mail.smtps.auth", "true");
                props.put("mail.smtps.quitwait", "false");
                Session session = Session.getDefaultInstance(props);
                session.setDebug(true);
                
                if ("".equals(subject) && "".equals(text)){
                    subject = "Đổi mật khẩu ASQ!";
                    text = "Mã code là: " + code;
                }
                
                Message message = new MimeMessage(session);
                message.setSubject(subject);
                message.setText(text);
                
                String fromEmail = "antran2509@gmail.com";
                String password = "01692889894";
                Address fromAddress = new InternetAddress(fromEmail);
                Address toAddress = new InternetAddress(email);
                message.setFrom(fromAddress);
                message.setRecipient(Message.RecipientType.TO, toAddress);
                
                Transport transport = session.getTransport();
                transport.connect(fromEmail, password);
                transport.sendMessage(message, message.getAllRecipients());
                transport.close();
                
            }catch (MessagingException e)
            {
                System.out.println("Gửi mail thất bại!");
                return "";
            }
        return code;
    }
    
    public static String CreateCode()
    {
        String strCode = "";
        Random rd = new Random();
        for (int i = 0; i < 6; i++)
        {
            strCode += String.valueOf(rd.nextInt(10));
        }
        return strCode;
    }
    
    public static boolean ChangePassword(Account account, String newPassword)
    {
        account.setPassword(newPassword);
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        try{
            em.merge(account);
            trans.commit();
            return true;
        } catch(Exception e){
            trans.rollback();
            return false;
        }finally{
            em.close();
        }
    }
    
    public static boolean UpdateStatusByUserId(int accountId, boolean status)
    {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        String qString = "UPDATE Account SET Status = :status WHERE AccountId = :accountId";
        Query q = em.createQuery(qString);
        q.setParameter("status", status);
        q.setParameter("accountId", accountId);
        int count = 0;
        try
        {
            trans.begin();
            count = q.executeUpdate();
            trans.commit();
        }catch(Exception ex)
        {
            trans.rollback();
        }finally{
            em.close();
        }
        return count > 0;
    }
    
     public static int MaxAccountId()
    {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "SELECT Max(AccountId) FROM ACCOUNT";
        int max = -1;
        try{
            String AccountId = em.createNativeQuery(qString).getSingleResult().toString();
            max = Integer.parseInt(AccountId);
        }catch(NoResultException ex)
        {
            max = -1;
            System.out.println("Kết nối thất bại!");
        }
        finally{
            em.close();
        }
        return max;
    }
//     public static Account getAccountInListByUserId(List<Account> accounts, int userId)
//    {
//        for (int i = 0; i < accounts.size(); i++)
//            if (accounts.get(i).getUser().getUserId()== userId)
//                return accounts.get(i);
//        return null;
//    }
     
     public static JsonArray ConvertListAccountsToJsonArray(List<User> users) {
        JsonArray json = new JsonArray();
        JsonArray jsonArray = new JsonArray();
        users.forEach(user -> {
            JsonObject formDetailsJson = new JsonObject();
            formDetailsJson.addProperty("AccountId", user.getAccount().getAccountId());
            formDetailsJson.addProperty("Name", StringEscapeUtils.escapeHtml4(user.getName()));
            formDetailsJson.addProperty("Email", StringEscapeUtils.escapeHtml4(user.getEmail()));
            formDetailsJson.addProperty("RoleName", StringEscapeUtils.escapeHtml4(user.getRole().getRoleName()));
            formDetailsJson.addProperty("Status", user.getAccount().isStatus());
            jsonArray.add(formDetailsJson);
        });
        json.addAll(jsonArray);
        return json;
    }
}
