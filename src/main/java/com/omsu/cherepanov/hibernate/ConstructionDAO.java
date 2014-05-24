package com.omsu.cherepanov.hibernate;

import com.omsu.cherepanov.clients.Construction;
import org.hibernate.HibernateException;
import org.hibernate.Query;

/**
 * Created by Павел on 11.05.2014.
 */
public class ConstructionDAO extends DAO {

    public Construction createConstruction(String newName)
            throws Exception {
        try {
            begin();
            Construction construction = new Construction(newName);
            getSession().saveOrUpdate(construction);
            commit();
            return construction;
        } catch (HibernateException e) {
            rollback();
            throw new Exception("Could not create Construction Name:" + newName, e);
        } finally {
            close();
        }
    }

    public void saveConstruction(Construction construction)
            throws Exception {
        try {
            begin();
            getSession().saveOrUpdate(construction);
            commit();
        } catch (HibernateException e) {
            rollback();
            throw new Exception("Could not create Construction ID:" + construction.getObjectID(), e);
        } finally {
            close();
        }
    }

    public Construction retrieveConstruction(int newID) throws Exception {
        try {
            begin();
            Query q = getSession().createQuery("from Construction where objectID = :newID");
            q.setInteger("newID", newID);
            Construction construction = (Construction) q.uniqueResult();
            //Query q1 = getSession().createQuery("from mainclientequ where Mainclient_ObjectID = :newID");
            //q1.setInteger("newID", newID);
            commit();
            return construction;
        } catch (HibernateException e) {
            rollback();
            throw new Exception("Could not get Construction ID:" + newID, e);
        } finally {
            close();
        }
    }

    public void deleteConstruction(Construction construction) throws Exception {
        try {
            begin();
            getSession().delete(construction);
            commit();
        } catch (HibernateException e) {
            rollback();
            throw new Exception("Could not delete Construction ID:" + construction.getObjectID(), e);
        } finally {
            close();
        }
    }
}
