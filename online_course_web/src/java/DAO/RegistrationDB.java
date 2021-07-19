/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.*;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author A556U
 */
public class RegistrationDB {
    public static boolean insertRegistrationDB(Registration registration)
    {
        boolean result = true;
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction tran = em.getTransaction();
        tran.begin();
        
        try
        {
            em.persist(registration);
            tran.commit();
        }
        catch(Exception ex)
        {
            tran.rollback();
            result =false;
        }
        finally
        {
            em.close();
        }
        return result;
        
    }
    
    public static Registration getRegistrationOfUser(User user, Course course)
    {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String queryS = "SELECT r from Registration r where r.user = :user and r.course = :course";
        
        TypedQuery<Registration> q = em.createQuery(queryS, Registration.class);
        q.setParameter("user", user);
        q.setParameter("course", course);
       
        Registration re = null;
        try
        {
            re= q.getSingleResult();
            
        }
        catch(Exception ex)
        {
            
        }
        finally
        {
            em.close();
        }
        return re;
    }
    
    public static boolean registrationExists(User user, Course course)
    {
        Registration re = getRegistrationOfUser(user, course);
        return re!=null;
    }

    static boolean registrationExists(Course course) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
    