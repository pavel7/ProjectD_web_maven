package com.omsu.cherepanov.hibernate;

import com.omsu.cherepanov.clients.People;
import org.hibernate.HibernateException;
import org.hibernate.Query;

/**
 * Created by Павел on 05.05.2014.
 */
public class PeopleDAO extends DAO {

    public People createPeople(double newX, double newY, int newID, String newName, String newRank)
            throws Exception {
        try {
            begin();
            People people = new People(newX, newY, newID, newName, newRank);
            getSession().saveOrUpdate(people);
            commit();
            return people;
        } catch (HibernateException e) {
            rollback();
            throw new Exception("Could not create People ID:" + newID + ", Name:" + newName, e);
        } finally {
            close();
        }
    }

    public void savePeople(People people)
            throws Exception {
        try {
            begin();
            getSession().saveOrUpdate(people);
            commit();
        } catch (HibernateException e) {
            rollback();
            throw new Exception("Could not create People ID:" + people.getObjectID() + ", Name:" + people.getName(), e);
        } finally {
            close();
        }
    }

    public People retrievePeople(int id) throws Exception {
        try {
            begin();
            Query q = getSession().createQuery("from People where objectID = :id");
            q.setInteger("id", id);
            People newPeople = (People) q.uniqueResult();
            commit();
            return newPeople;
        } catch (HibernateException e) {
            rollback();
            throw new Exception("Could not get People Identifier:" + id, e);
        } finally {
            close();
        }
    }

    public void deletePeople(People people) throws Exception {
        try {
            begin();
            getSession().delete(people);
            commit();
        } catch (HibernateException e) {
            rollback();
            throw new Exception("Could not delete People Identifier:" + people.getObjectID() + ", Name:" + people.getName(), e);
        } finally {
            close();
        }
    }
}
