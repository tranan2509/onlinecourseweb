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
public class ExcerciseDB {
    public static boolean insertExcercise(Excercise exercise) {

        boolean result = true;
        EntityManager entityManager = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction tran = entityManager.getTransaction();
        tran.begin();

        try {
            entityManager.persist(exercise);
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
    
    public static boolean updateExcercise(Excercise exercise) {
        boolean result=true;
        EntityManager entityManager = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction tran = entityManager.getTransaction();
        tran.begin();

        try {
            entityManager.merge(exercise);
            tran.commit();
            result = true;
        } catch (Exception ex) {
            tran.rollback();
            result = false;
        } finally {
            entityManager.close();
        }
        return result;
    }
    
     public static Excercise getExercise(Course course,Chap chap, Part part, int exerciseOrder) {
        EntityManager entityManager = DBUtil.getEmFactory().createEntityManager();
        String queryS = "Select e from Excercise e where e.course = :course and e.chap = :chap"
                + " and e.part= :part and e.ExcerciseOrder= :exerciseOrder";

        TypedQuery<Excercise> query = entityManager.createQuery(queryS, Excercise.class);
        query.setParameter("course", course);
        query.setParameter("chap", chap);
        query.setParameter("part", part);
        query.setParameter("exerciseOrder", exerciseOrder);

        Excercise exercise=null;
        try {
            exercise = query.getSingleResult();
        } catch (Exception ex) {
            exercise = null;
        } finally {
            entityManager.close();
        }
        return exercise;
    }
     
     public static boolean excericseExists(Course course,Chap chap, Part part, int exerciseOrder) 
     {
        Excercise e = getExercise(course, chap, part, exerciseOrder);
        return e != null;
    }

    public static boolean deleteExcercise(Excercise exercise) {
        boolean result = true;
        EntityManager entityManager = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction tran = entityManager.getTransaction();
        tran.begin();
        try {          
            entityManager.remove(entityManager.merge(exercise));
            tran.commit();
        } catch (Exception ex) {
            tran.rollback();
            result = false;
        } finally {
            entityManager.close();
        }
        return result;
    }
    
    public static boolean deleteExcercise(Course course,Chap chap, Part part, int exerciseOrder) {
        boolean result = true;
        EntityManager entityManager = DBUtil.getEmFactory().createEntityManager();

        try {          
            Excercise e = getExercise(course, chap, part, exerciseOrder);
            if(e!=null)
                result = deleteExcercise(e);
        } catch (Exception ex) {
            result = false;
        } finally {
            entityManager.close();
        }
        return result;
    }
    
    //get all exercise of a part 
    public static List<Excercise> getAllExcercisePartOfPart( Course course, Chap chap, Part part)
    {
        EntityManager entityManager = DBUtil.getEmFactory().createEntityManager();
        String queryS = "Select e from Excercise e where e.course = :course and e.chap = :chap and e.part = :part";
        
        TypedQuery<Excercise> q = entityManager.createQuery(queryS, Excercise.class);
        q.setParameter("course", course);
        q.setParameter("chap", chap);
        q.setParameter("part", part);
        
        List<Excercise> exerciseList ;
        
        try
        {
            exerciseList = q.getResultList();
            if( exerciseList==null || exerciseList.isEmpty())
                exerciseList=null;
        }
        finally
        {
            entityManager.close();
        }
        return exerciseList;
    }
    
     public static List<Excercise> getAllExcercisePartOfPart( int courseid, int chapOrder, int partOrder)
    {
        Course course = CourseDB.getCourseById(courseid);
        Chap chap = ChapDB.getChapOfCourseByOrder(course, chapOrder);
        Part part = PartDB.getPartByCourseAndChap(course, chap, partOrder);
        
        EntityManager entityManager = DBUtil.getEmFactory().createEntityManager();
        String queryS = "Select e from Excercise e where e.course = :course and e.chap = :chap and e.part = :part";
        
        TypedQuery<Excercise> q = entityManager.createQuery(queryS, Excercise.class);
        q.setParameter("course", course);
        q.setParameter("chap", chap);
        q.setParameter("part", part);
        
        List<Excercise> exerciseList ;
        
        try
        {
            exerciseList = q.getResultList();
            if( exerciseList==null || exerciseList.isEmpty())
                exerciseList=null;
        }
        finally
        {
            entityManager.close();
        }
        return exerciseList;
    }

}
