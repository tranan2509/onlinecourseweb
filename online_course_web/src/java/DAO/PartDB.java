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
public class PartDB {

    public static boolean insertPart(Part part) {

        boolean result = true;
        EntityManager entityManager = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction tran = entityManager.getTransaction();
        tran.begin();

        try {
            entityManager.persist(part);
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
    public static Part GetPrtByPartId(int partid)
    { 
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
         Part part=null;
        try{
            part = em.find(Part.class, partid);
            
        }finally{
            em.close();
        }
        return part;
    }
    public static boolean updatePart(Part part) {
        boolean result;
        EntityManager entityManager = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction tran = entityManager.getTransaction();
        tran.begin();

        try {
            entityManager.merge(part);
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

    public static boolean partExists(Course course, Chap chap, int partid) {
        Part p = getPartByCourseAndChap(course, chap, partid);
        return p != null;
    }

    public static boolean deletePart(Part part)
    {
        boolean result = true;
        EntityManager entityManager = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction tran = entityManager.getTransaction();
        tran.begin();
        try {
          entityManager.remove(entityManager.merge(part));
          tran.commit();
        } catch (Exception ex) {        
            result = false;
        } finally {
            entityManager.close();
        }
        return result;
    }
    
    public static boolean deletePart(Course course, Chap chap, int partOrder) {
        boolean result = true;
        EntityManager entityManager = DBUtil.getEmFactory().createEntityManager();
       
        try {
            Part part = getPartByCourseAndChap(course, chap, partOrder);
            if(part!=null)
            {
                result = deletePart(part);
            }
      
        } catch (Exception ex) {           
            result = false;
        } finally {
            entityManager.close();
        }
        return result;
    }

    //get Chap defined by primay key
    public static Part getPartByCourseAndChap(Course course, Chap chap, int partorder) {
        EntityManager entityManager = DBUtil.getEmFactory().createEntityManager();
        String queryS = "Select p from Part p where p.course = :course and p.chap = :chap and p.PartOrder = :partorder";

        TypedQuery<Part> query = entityManager.createQuery(queryS, Part.class);
        query.setParameter("course", course);
        query.setParameter("chap", chap);
        query.setParameter("partorder", partorder);

        Part part;
        try {
            part = query.getSingleResult();
        } catch (Exception ex) {
            part = null;
        } finally {
            entityManager.close();
        }
        return part;
    }

    //get all part of a chap 
    public static List<Part> getAllPartOfChap( Course course, Chap chap)
    {
        EntityManager entityManager = DBUtil.getEmFactory().createEntityManager();
        String queryS = "Select p from Part p where p.course = :course and p.chap = :chap";
        
        TypedQuery<Part> q = entityManager.createQuery(queryS, Part.class);
        q.setParameter("course", course);
        q.setParameter("chap", chap);
        
        List<Part> partList ;
        
        try
        {
            partList = q.getResultList();
            if( partList==null || partList.isEmpty())
                partList=null;
        }
        finally
        {
            entityManager.close();
        }
        return partList;
    }
    
     public static List<Part> getAllPartOfChap( int courseid, int chapOrder)
    {
        Course course = CourseDB.GetCourseByCourseId(courseid);
        Chap chap = ChapDB.getChapOfCourseByOrder(course, chapOrder);
        EntityManager entityManager = DBUtil.getEmFactory().createEntityManager();
        String queryS = "Select p from Part p where p.course = :course and p.chap = :chap";
        
        TypedQuery<Part> q = entityManager.createQuery(queryS, Part.class);
        q.setParameter("course", course);
        q.setParameter("chap", chap);
        
        List<Part> partList ;
        
        try
        {
            partList = q.getResultList();
            if( partList==null || partList.isEmpty())
                partList=null;
        }
        finally
        {
            entityManager.close();
        }
        return partList;
    }
      public static Part GetPartByPartId(int partid)
    { 
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
         Part part=null;
        try{
            part = em.find(Part.class, partid);
            
        }finally{
            em.close();
        }
        return part;
    }
}
