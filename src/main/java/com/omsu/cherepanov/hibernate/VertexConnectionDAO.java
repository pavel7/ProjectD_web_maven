package com.omsu.cherepanov.hibernate;

import com.omsu.cherepanov.graph.VertexConnection;
import org.hibernate.HibernateException;
import org.hibernate.Query;

/**
 * Created by Павел on 12.05.2014.
 */
public class VertexConnectionDAO extends DAO {

    public void saveVertexConnection(VertexConnection vertexConnection)
            throws Exception {
        try {
            begin();
            getSession().saveOrUpdate(vertexConnection);
            commit();
        } catch (HibernateException e) {
            rollback();
            throw new Exception("Could not save elementOfGraph Identifier:" + vertexConnection.getId(), e);
        } finally {
            close();
        }
    }

    public VertexConnection retrieveVertexConnection(int id) throws Exception {
        try {
            begin();
            Query q = getSession().createQuery("from VertexConnection where id = :id");
            q.setInteger("id", id);
            VertexConnection newVertexConnection = (VertexConnection) q.uniqueResult();
            newVertexConnection.VertexToBegin();
            commit();
            return newVertexConnection;
        } catch (HibernateException e) {
            rollback();
            throw new Exception("Could not get VertexConnection Identifier:" + id, e);
        } finally {
            close();
        }
    }

    public void deleteVertexConnection(VertexConnection vertexConnection) throws Exception {
        try {
            begin();
            getSession().delete(vertexConnection);
            commit();
        } catch (HibernateException e) {
            rollback();
            throw new Exception("Could not delete Vertex connection Identifier:" + vertexConnection.getId(), e);
        } finally {
            close();
        }
    }
}
