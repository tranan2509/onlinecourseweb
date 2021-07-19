/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import static DAO.PartDB.deletePart;
import static DAO.PartDB.getPartByCourseAndChap;
import Model.*;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;


/**
 *
 * @author ad
 */
public class PartFilesDB {

    public static boolean insertPartFiles(PartFiles partfiles) {

        boolean result = true;
        EntityManager entityManager = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction tran = entityManager.getTransaction();
        tran.begin();

        try {
            entityManager.persist(partfiles);
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
    public static boolean deletePartFiles(Part part) {
        boolean result = true;
        EntityManager entityManager = DBUtil.getEmFactory().createEntityManager();
       
        try {
            PartFiles partfiles = getPartFilesByPart(part);
            if(partfiles!=null)
            {
                result = deletePartFiles(partfiles);
            }      
        } catch (Exception ex) {           
            result = false;
        } finally {
            entityManager.close();
        }
        return result;
    }
    public static PartFiles getPartFilesByPart(Part part) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String queryS = "SELECT p from PartFiles p where p.part = :part";
        
        TypedQuery<PartFiles> q = em.createQuery(queryS, PartFiles.class);
        
        q.setParameter("part", part);
       
        PartFiles p=null;
        
        try
        {
            List<PartFiles> partfileList;
            partfileList = q.getResultList();
            if(partfileList!=null)
                p=partfileList.get(0);
//            p= q.getSingleResult();

            
        }
        catch(Exception ex)
        {
            
        }
        finally
        {
            em.close();
        }
        return p;
    }
    public static boolean deletePartFiles(PartFiles partfiles)
    {
        boolean result = true;
        EntityManager entityManager = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction tran = entityManager.getTransaction();
        tran.begin();
        try {
          entityManager.remove(entityManager.merge(partfiles));
          tran.commit();
        } catch (Exception ex) {        
            result = false;
        } finally {
            entityManager.close();
        }
        return result;
    }
    public static int getCountPart(Part part) {
       int countpF=0;
        EntityManager entityManager = DBUtil.getEmFactory().createEntityManager();
        String queryS = "SELECT count(pf.part) as pfile from PartFiles pf where pf.part = :part";

        try {
            if ( entityManager.createNativeQuery(queryS).getSingleResult() == null)
                countpF = 0;
            else
                countpF = Integer.parseInt(entityManager.createNativeQuery(queryS).getSingleResult().toString());
        } catch (NumberFormatException e) {
            countpF=0;
        } finally {
            entityManager.close();
        }
        return countpF;
    }
    public static List<PartFiles> GetPartFilesBy(Part part)
    {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "SELECT p FROM PartFiles AS p WHERE p.part = :part";
        TypedQuery<PartFiles> q = em.createQuery(qString, PartFiles.class);
        q.setParameter("part", part);
        List<PartFiles> partfiles = null;
        try{
            partfiles = q.getResultList();
        }catch(NoResultException ex)
        {
            partfiles = null;
            System.out.println("Kết nối thất bại!");
        }
        finally{
            em.close();
        }
        return partfiles;
    }
    
}
