/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Chap;
import Model.Course;
import Model.Instructor;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

/**
 *
 * @author A556U
 */
public class InstructorDB {
    public static  boolean insertInstructor (Instructor instructor)
    {
        boolean result = true;
        EntityManager entityManager = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction tran = entityManager.getTransaction();
        tran.begin();

        try {
            entityManager.persist(instructor);
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
    
    public static boolean updateInstructor(Instructor instructo) {
        boolean result = true;
        EntityManager entityManager = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction tran = entityManager.getTransaction();
        tran.begin();

        try {
            entityManager.merge(instructo);
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
     
    public static boolean deleteInstructor(Instructor instructor)
    {
        boolean result = true;
        EntityManager entityManager = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction tran = entityManager.getTransaction();
        tran.begin();

        try {
            entityManager.remove(entityManager.merge(instructor));
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
    
    public static List<Instructor> getAllInstructorsByCourse(Course course)
    {
        EntityManager entityManager = DBUtil.getEmFactory().createEntityManager();
        String queryS = "Select i from Instructor i where i.course = :course";
        
        TypedQuery<Instructor> q = entityManager.createQuery(queryS, Instructor.class);
        q.setParameter("course", course);

        
        List<Instructor> instructors;
       try
       {
           instructors = q.getResultList();
           if(instructors==null || instructors.isEmpty())
               instructors=null;
       }
       finally
               {
                   entityManager.close();
               }
       return instructors;
               
    }
    
}
