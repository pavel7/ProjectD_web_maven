package com.omsu.cherepanov.hibernate;

import com.omsu.cherepanov.clients.Mainclient;
import org.hibernate.HibernateException;
import org.hibernate.Query;

/**
 * Created by Павел on 29.04.2014.
 */
public class MainclientDAO extends DAO {

    public Mainclient createMainclient(double newX, double newY, int newID)
            throws Exception {
        try {
            begin();
            Mainclient mainclient = new Mainclient(newX, newY, newID);
            getSession().saveOrUpdate(mainclient);
            commit();
            return mainclient;
        } catch (HibernateException e) {
            rollback();
            throw new Exception("Could not create Mainclient ID:" + newID, e);
        } finally {
            close();
        }
    }

    public void saveMainclient(Mainclient mainclient)
            throws Exception {
        try {
            begin();
            getSession().saveOrUpdate(mainclient);
            commit();
        } catch (HibernateException e) {
            rollback();
            throw new Exception("Could not create Mainclient ID:" + mainclient.getObjectID(), e);
        } finally {
            close();
        }
    }

    public Mainclient retrieveMainclient(int newID) throws Exception {
        try {
            begin();
            Query q = getSession().createQuery("from Mainclient where objectID = :newID");
            q.setInteger("newID", newID);
            Mainclient mainclient = (Mainclient) q.uniqueResult();
            //Query q1 = getSession().createQuery("from mainclientequ where Mainclient_ObjectID = :newID");
            //q1.setInteger("newID", newID);
            commit();
            return mainclient;
        } catch (HibernateException e) {
            rollback();
            throw new Exception("Could not get Mainclient ID:" + newID, e);
        } finally {
            close();
        }
    }

    public void deleteMainclient(Mainclient mainclient) throws Exception {
        try {
            begin();
            getSession().delete(mainclient);
            commit();
        } catch (HibernateException e) {
            rollback();
            throw new Exception("Could not delete Mainclient ID:" + mainclient.getObjectID(), e);
        } finally {
            close();
        }
    }
}
