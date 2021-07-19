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
public class FAQDB {
    public static boolean insertFAQ(FAQ faq) {
        boolean result = true;
        EntityManager entityManager = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction tran = entityManager.getTransaction();
        tran.begin();

        try {
            entityManager.persist(faq);
            tran.commit();
        } catch (Exception e) {
            System.out.println(e);
            tran.rollback();
            result = false;
        } finally {
            entityManager.close();
        }
        return result;
    }
    
    public static boolean updateFAQ(FAQ faq) {
        boolean result = true;
        EntityManager entityManager = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction tran = entityManager.getTransaction();
        tran.begin();

        try {
            entityManager.merge(faq);
            tran.commit();
        } catch (Exception e) {
            System.out.println(e);
            tran.rollback();
            result = false;
        } finally {
            entityManager.close();
        }
        return result;
    }
    
    public static boolean deleteFAQ(FAQ faq)
    {
            boolean result = true;
            EntityManager entityManager = DBUtil.getEmFactory().createEntityManager();
          EntityTransaction tran = entityManager.getTransaction();
          tran.begin();
                
       try {      
                entityManager.remove(entityManager.merge(faq));
                tran.commit();
        } catch (Exception e) {
            System.out.println(e);
            result = false;
            tran.rollback();
        } finally {
            entityManager.close();
        }
        return result;
    }
    
    public static boolean deleteFAQByCourse(Course course, int faqOrder)
    {
            boolean result = true;
            EntityManager entityManager = DBUtil.getEmFactory().createEntityManager();
       try {
           FAQ faq = getFAQByCourseAndOrder(course, faqOrder);
           if(faq!=null)
               result = deleteFAQ(faq);
        } catch (Exception e) {
            System.out.println(e);
            result = false;
        } finally {
            entityManager.close();
        }
        return result;
    }
    
     public static FAQ getFAQByCourseAndOrder(Course course, int FAQOrder) {
        EntityManager entityManager = DBUtil.getEmFactory().createEntityManager();
        String queryS = "SELECT f from FAQ f where f.course = :course and f.FAQOrder = :faqorder";
        TypedQuery<FAQ> query = entityManager.createQuery(queryS, FAQ.class);
        query.setParameter("course", course);
        query.setParameter("faqorder", FAQOrder);

         FAQ faq =null;
        try {
            faq= (FAQ)query.getSingleResult();
        } catch (Exception ex) {
            faq =null;
        }
        finally
        {
            entityManager.close();
        }
        return faq;
    }
    
     public static boolean FAQExists(Course course, int FAQOrder) 
    {
        FAQ u = getFAQByCourseAndOrder(course, FAQOrder);
        return u!=null;
    }
     
    public static List<FAQ> getAllFAQOfCourse(int courseid)
    {
        Course course = CourseDB.GetCourseByCourseId(courseid);
        EntityManager entityManager= DBUtil.getEmFactory().createEntityManager();
        String queryS = "SELECT f from FAQ f where f.course = :course";
        
        TypedQuery<FAQ> q = entityManager.createQuery(queryS, FAQ.class);
        q.setParameter("course", course);
        
        List<FAQ> faqList ;
        
        try
        {
            faqList = q.getResultList();
            if(faqList==null || faqList.isEmpty())
                faqList=null;
        }
        finally
        {
            entityManager.close();
        }
        return faqList;
        
    }
    
    public static List<FAQ> getAllFAQOfCourse(Course course)
    {    
        EntityManager entityManager= DBUtil.getEmFactory().createEntityManager();
        String queryS = "SELECT f from FAQ f where f.course = :course";
        
        TypedQuery<FAQ> q = entityManager.createQuery(queryS, FAQ.class);
        q.setParameter("course", course);
        
        List<FAQ> faqList ;
        
        try
        {
            faqList = q.getResultList();
            if(faqList==null || faqList.isEmpty())
                faqList=null;
        }
        finally
        {
            entityManager.close();
        }
        return faqList;
        
    }
}
