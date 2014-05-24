package com.omsu.cherepanov.hibernate;

/**
 * Created by Павел on 21.04.2014.
 */

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author seostella.com
 */
public class DAO {
    private static final Logger log = Logger.getAnonymousLogger();
    private static final ThreadLocal session = new ThreadLocal();
    private static final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();


    public static Session getSession() {
        Session session = (Session) DAO.session.get();
        if (session == null) {
            session = sessionFactory.openSession();
            DAO.session.set(session);
        }
        return session;
    }

    protected void begin() {
        getSession().beginTransaction();
    }

    protected void commit() {
        getSession().getTransaction().commit();
    }

    protected void rollback() {
        try {
            getSession().getTransaction().rollback();
        } catch (HibernateException e) {
            log.log(Level.WARNING, "Cannot rollback", e);
        }
    }

    public static void close() {
        try {
            getSession().close();
        } catch (HibernateException e) {
            log.log(Level.WARNING, "Cannot close", e);
        }
        DAO.session.set(null);
    }
}
