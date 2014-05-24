package com.omsu.cherepanov.hibernate;

import com.omsu.cherepanov.graph.DirectedGraph;
import com.omsu.cherepanov.graph.VertexConnection;
import org.hibernate.HibernateException;
import org.hibernate.Query;

import java.util.List;

/**
 * Created by Павел on 22.05.2014.
 */
public class DirectedGraphDAO extends DAO {

    public void saveDirectedGraph(DirectedGraph directedGraph)
            throws Exception {
        try {
            begin();
            for (int i = 0; i < directedGraph.getAmountOfVertex(); i++) {
                getSession().saveOrUpdate(directedGraph.getConnectionOfVertex().get(i));
            }
            commit();
        } catch (HibernateException e) {
            rollback();
            throw new Exception("Could not save graph!!!", e);
        } finally {
            close();
        }
    }

    public DirectedGraph retrieveDirectedGraph() throws Exception {
        try {
            begin();
            Query q = getSession().createQuery("select count(*) from VertexConnection");
            //q.setInteger("id", id);
            int amountOfVertex = safeLongToInt((long) q.uniqueResult());
            DirectedGraph graph = new DirectedGraph();
            graph.setConnectionOfVertex((List<VertexConnection>) getSession().createQuery("from VertexConnection").list());
            graph.setAmountOfVertex(amountOfVertex);
            int amountOfEdge = 0;
            for (int i = 0; i < amountOfVertex; i++) {
                graph.getConnectionOfVertex().get(i).VertexToBegin();
                amountOfEdge = amountOfEdge + graph.getConnectionOfVertex().get(i).getVertexConnection().size() - 1;
            }
            graph.setAmountOfEdge(amountOfEdge);
            commit();
            return graph;
        } catch (HibernateException e) {
            rollback();
            throw new Exception("Could not get graph", e);
        } finally {
            close();
        }
    }

    public void deleteDirectedGraph(DirectedGraph directedGraph) throws Exception {
        try {
            begin();
            for (int i = 0; i < directedGraph.getAmountOfVertex(); i++) {
                getSession().delete(directedGraph.getConnectionOfVertex().get(i));
            }
            commit();
        } catch (HibernateException e) {
            rollback();
            throw new Exception("Could not deleteGraph", e);
        } finally {
            close();
        }
    }

    public static int safeLongToInt(long l) {
        if (l < Integer.MIN_VALUE || l > Integer.MAX_VALUE) {
            throw new IllegalArgumentException
                    (l + " cannot be cast to int without changing its value.");
        }
        return (int) l;
    }
}
