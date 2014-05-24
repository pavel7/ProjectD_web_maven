package com.omsu.cherepanov.hibernate;

/**
 * Created by Павел on 24.04.2014.
 */

import com.omsu.cherepanov.connection.Connection;
import org.hibernate.HibernateException;
import org.hibernate.Query;

/**
 * Created by Павел on 22.04.2014.
 */
public class ConnectionDAO extends DAO {

    public Connection createConnection(byte newDefence, int id)
            throws Exception {
        try {
            begin();
            Connection connection = new Connection(newDefence, id);
            getSession().saveOrUpdate(connection);
            commit();
            return connection;
        } catch (HibernateException e) {
            rollback();
            throw new Exception("Could not create connection Defence:" + newDefence + ", Identifier:" + id, e);
        } finally {
            close();
        }
    }

    public void saveConnection(Connection connection)
            throws Exception {
        try {
            begin();
            getSession().saveOrUpdate(connection);
            commit();
        } catch (HibernateException e) {
            rollback();
            throw new Exception("Could not save connection Defence:" + connection.getDefence() + ", Identifier:" + connection.getObjectID(), e);
        } finally {
            close();
        }
    }

    public Connection retrieveConnection(int id) throws Exception {
        try {
            begin();
            Query q = getSession().createQuery("from Connection where objectID = :id");
            q.setInteger("id", id);
            Connection newConnection = (Connection) q.uniqueResult();
            commit();
            return newConnection;
        } catch (HibernateException e) {
            rollback();
            throw new Exception("Could not get connection Identifier:" + id, e);
        } finally {
            close();
        }
    }

    public void deleteConnection(Connection connection) throws Exception {
        try {
            begin();
            getSession().delete(connection);
            commit();
        } catch (HibernateException e) {
            rollback();
            throw new Exception("Could not delete connection Identifier:" + connection.getObjectID(), e);
        } finally {
            close();
        }
    }
}

