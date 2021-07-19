/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Chap;
import Model.Course;
import DAO.DBUtil;
import Model.Excercise;
import Model.Part;
import Model.StudentExcercise;
import Model.User;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author A556U
 */
public class StudentExcerciseDB {
    public static boolean insert(StudentExcercise stEx)
    {
        boolean result = true;
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction tran = em.getTransaction();
        tran.begin();
        
        try
        {
            em.persist(stEx);
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
    
    
    public static boolean update(StudentExcercise stEx)
    {
        boolean result = true;
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction tran = em.getTransaction();
        tran.begin();
        
        try
        {
            em.merge(stEx);
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
    
     public static boolean delete(StudentExcercise stEx)
    {
        boolean result = true;
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction tran = em.getTransaction();
        tran.begin();
        
        try
        {
            em.remove(em.merge(stEx));
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
     
    public static List<StudentExcercise> getAllAnswerOfPart(User user, Course course, Chap chap, Part part)
    {
      
        EntityManager entityManager = DBUtil.getEmFactory().createEntityManager();
        String queryS = "Select e from StudentExcercise e where e.course = :course and e.chap = :chap and e.part = :part";
        
        
        TypedQuery<StudentExcercise> q = entityManager.createQuery(queryS, StudentExcercise.class);
        q.setParameter("course", course);
        q.setParameter("chap", chap);
        q.setParameter("part", part);
        
        List<StudentExcercise> resultList ;
        
        try
        {
            resultList = q.getResultList();
            if( resultList==null || resultList.isEmpty())
                resultList=null;
        }
        finally
        {
            entityManager.close();
        }
        return resultList;
        
        
    }
    
    public static int getMaxTime(User user, Course course, Chap chap, Part part )
    {
       
       List<StudentExcercise> ansList = getAllAnswerOfPart(user, course, chap, part);
             
        int max=0;
       if(ansList!=null)
       {
           for(int i=0; i<ansList.size(); i++)
           {
               
               if(ansList.get(i).getTime()>max)
                   max=ansList.get(i).getTime();
           }
       }

        return max;
    }
    public static int NumberOfUsersWith25percentCorrectAnswer(Part part, int time) {
        int result = 0;
        int numberOfExercises = part.getExcercises().size();

        List<StudentExcercise> steList = StudentExcerciseDB.getAllAnswerOfPart(part, time);
        //các user đã đếm
        List useridList = new ArrayList<>();
        if (steList != null) {
            for (int i = 0; i < steList.size(); i++) {
                User user = steList.get(i).getUser();
                if (!useridList.contains(user.getUserId())) {
                    useridList.add(user.getUserId());
                    int numberOfCorrectAns = NumberOfCorrectAnwersByUser(user, part, time);
                    if (numberOfCorrectAns <= 0.25 * numberOfExercises) {
                        result++;
                    }
                }
            }
        }
        return result;
    }
    
    public static int NumberOfUsersWith25percentTo50percentCorrectAnswer(Part part, int time) {
        int result = 0;
        int numberOfExercises = part.getExcercises().size();

        List<StudentExcercise> steList = StudentExcerciseDB.getAllAnswerOfPart(part, time);
        //các user đã đếm
        List useridList = new ArrayList<>();
        if (steList != null) {
            for (int i = 0; i < steList.size(); i++) {
                User user = steList.get(i).getUser();
                if (!useridList.contains(user.getUserId())) {
                    useridList.add(user.getUserId());
                    int numberOfCorrectAns = NumberOfCorrectAnwersByUser(user, part, time);
                    if (numberOfCorrectAns > 0.25 * numberOfExercises
                            && numberOfCorrectAns <= 0.5 * numberOfExercises) {
                        result++;
                    }
                }
            }
        }
        return result;
    }
    
     public static int NumberOfUsersWith50percentTo75percentCorrectAnswer(Part part, int time) {
        int result = 0;
        int numberOfExercises = part.getExcercises().size();

        List<StudentExcercise> steList = StudentExcerciseDB.getAllAnswerOfPart(part, time);
        //các user đã đếm
        List useridList = new ArrayList<>();
        if (steList != null) {
            for (int i = 0; i < steList.size(); i++) {
                User user = steList.get(i).getUser();
                if (!useridList.contains(user.getUserId())) {
                    useridList.add(user.getUserId());
                    int numberOfCorrectAns = NumberOfCorrectAnwersByUser(user, part, time);
                    if (numberOfCorrectAns > 0.5 * numberOfExercises
                            && numberOfCorrectAns <= 0.75 * numberOfExercises) {
                        result++;
                    }
                }
            }
        }
        return result;
    }
     
     public static int NumberOfUsersWith75percentTo100percentCorrectAnswer(Part part, int time) {
        int result = 0;
        int numberOfExercises = part.getExcercises().size();

        List<StudentExcercise> steList = StudentExcerciseDB.getAllAnswerOfPart(part, time);
        //các user đã đếm
        List useridList = new ArrayList<>();
        if (steList != null) {
            for (int i = 0; i < steList.size(); i++) {
                User user = steList.get(i).getUser();
                if (!useridList.contains(user.getUserId())) {
                    useridList.add(user.getUserId());
                    int numberOfCorrectAns = NumberOfCorrectAnwersByUser(user, part, time);
                    if ( numberOfCorrectAns > 0.75 * numberOfExercises) {
                        result++;
                    }
                }
            }
        }
        return result;
    }

    public static int NumberOfCorrectAnwersByUser(User user, Part part, int time) {
        int result = 0;
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String queryS = "Select COUNT(ste.correct) from StudentExcercise ste where ste.user=:user and ste.part =:part "
                + " and ste.time=:time and ste.correct=:correct";

        Query q = em.createQuery(queryS);
        q.setParameter("user", user);
        q.setParameter("part", part);
        q.setParameter("time", time);
        q.setParameter("correct", true);

        try {
            result = Integer.parseInt(q.getSingleResult().toString());
        } catch (Exception ex) {

        } finally {
            em.close();
        }
        return result;
    }
    //////////////////////////thêm
    //lấy tổng số lượt làm bài của
    public static int sumOfPeopleAttempting(Part part, int time) {
        int result = 0;
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String queryS = "SELECT COUNT(DISTINCT(ste.user)) from StudentExcercise ste where ste.part=:part and "
                + " ste.time =:time ";

        Query q = em.createQuery(queryS);
        q.setParameter("part", part);
        q.setParameter("time", time);

        try {
            result = Integer.parseInt(q.getSingleResult().toString());
        } catch (Exception ex) {

        } finally {
            em.close();
        }
        return result;
    }
    public static List<StudentExcercise> getAllAnswerOfPart(Part part, int time) {
        EntityManager entityManager = DBUtil.getEmFactory().createEntityManager();
        String queryS = "Select e from StudentExcercise e where e.part = :part and e.time=:time";

        TypedQuery<StudentExcercise> q = entityManager.createQuery(queryS, StudentExcercise.class);
        q.setParameter("part", part);
        q.setParameter("time", time);

        List<StudentExcercise> resultList;

//        try {
        resultList = q.getResultList();
        if (resultList == null || resultList.isEmpty()) {
            resultList = null;
        }
//        } finally {
        entityManager.close();
//        }
        return resultList;

    }


}

