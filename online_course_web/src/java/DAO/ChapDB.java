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
import javax.persistence.TypedQuery;

/**
 *
 * @author A556U
 */
public class ChapDB {
    
   
    public static boolean insertChap(Chap chap)
    {
      
        boolean result = true;
        EntityManager entityManager = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction tran = entityManager.getTransaction();
        tran.begin();

        try {
            entityManager.persist(chap);
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
    
    public static boolean updateChap(Chap chap)
    {
        boolean result;
        EntityManager entityManager= DBUtil.getEmFactory().createEntityManager();
        EntityTransaction tran = entityManager.getTransaction();
        tran.begin();
        
        try
        {
            entityManager.merge(chap);
            tran.commit();
            result = true;
        }
        catch(Exception ex)
        {
            tran.rollback();
            result=false;
        }
        finally
        {
            entityManager.close();
        }
        return result;
    }
    
    public static boolean deleteChap(Chap chap)
    {
        boolean result;
        EntityManager entityManager= DBUtil.getEmFactory().createEntityManager();
        EntityTransaction tran = entityManager.getTransaction();
        tran.begin();
        
        try
        {
            entityManager.remove(chap);
            tran.commit();
            result = true;
        }
        catch(Exception ex)
        {
            tran.rollback();
            result=false;
        }
        finally
        {
            entityManager.close();
        }
        return result;
    }
    
    public static int getMaxChapOfTheCourse(int courseid)
    {
        EntityManager entityManager = DBUtil.getEmFactory().createEntityManager();
        String queryS = "Select Max(ChapOrder) from Chap where courseid = "+ courseid;
        
        try {
            int max = Integer.parseInt(entityManager.createNativeQuery(queryS).getSingleResult().toString());
            return max;
        } catch (Exception e) {
            return 0;
        } finally {
            entityManager.close();
        }
    }
    
    //check whether the chap exists
    public static boolean chapExists (Course course, int chaporder)
    {
        Chap chap = getChapOfCourseByOrder(course, chaporder);
        return chap!=null;              
    }
    
    //get Chap defined by primay key
    public static Chap getChapOfCourseByOrder(Course course, int chaporder)
    {
        EntityManager entityManager = DBUtil.getEmFactory().createEntityManager();
        String queryS = "Select c from Chap c where c.course = :course and c.ChapOrder = :chaporder";
        
        TypedQuery<Chap> query = entityManager.createQuery(queryS, Chap.class);
        query.setParameter("course", course);
        query.setParameter("chaporder", chaporder);
        
        Chap chap;
        try
        {
            chap = query.getSingleResult();         
        }
        catch(Exception ex)
        {
            chap= null;
        }
        finally
        {
            entityManager.close();
        }
        return chap;
    }
    
    
    //The function to get all chap by course
    public static List<Chap> getAllChapByCourse(Course course)
    {
        EntityManager entityManager = DBUtil.getEmFactory().createEntityManager();
        String queryS = "Select c from Chap c where c.course = :course";
        
        TypedQuery<Chap> q = entityManager.createQuery(queryS, Chap.class);
        q.setParameter("course", course);

        
        List<Chap> chaps;
       try
       {
           chaps = q.getResultList();
           if(chaps==null || chaps.isEmpty())
               chaps=null;
       }
       finally
               {
                   entityManager.close();
               }
       return chaps;
               
    }
    
    public static List<Chap> getAllChapByCourseId(int courseid)
    {
        Course course = CourseDB.GetCourseByCourseId(courseid);
        
        EntityManager entityManager = DBUtil.getEmFactory().createEntityManager();
        String queryS = "Select c from Chap c where c.course = :course";
        
        TypedQuery<Chap> q = entityManager.createQuery(queryS, Chap.class);
        q.setParameter("course", course);
        
        List<Chap> chaps;
       try
       {
           chaps = q.getResultList();
           if(chaps==null || chaps.isEmpty())
               chaps=null;
       }
       finally
               {
                   entityManager.close();
               }
       return chaps;
               
    }
}
