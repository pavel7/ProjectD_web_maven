package com.omsu.cherepanov.hibernate;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by Павел on 24.05.2014.
 */
public class HibernateListener implements ServletContextListener {

    public void contextInitialized(ServletContextEvent event) {
        HibernateUtil.getSessionFactory();
    }

    public void contextDestroyed(ServletContextEvent event) {
        HibernateUtil.closeSession();
    }
}
