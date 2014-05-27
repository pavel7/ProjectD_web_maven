package com.omsu.cherepanov.graph;

import com.omsu.cherepanov.hibernate.DirectedGraphDAO;

/**
 * Created by Павел on 25.05.2014.
 */
public class SingletonGraph {
    private static volatile DirectedGraph instance;

    public static DirectedGraph getInstance() {
        DirectedGraphDAO directedGraphDAO = new DirectedGraphDAO();
        DirectedGraph localInstance = instance;
        if (localInstance == null) {
            synchronized (DirectedGraph.class) {
                localInstance = instance;
                if (localInstance == null) {
                    try {
                        instance = localInstance = directedGraphDAO.retrieveDirectedGraph();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return localInstance;
    }

    public static void updateInstance() {
        DirectedGraphDAO directedGraphDAO = new DirectedGraphDAO();
        DirectedGraph localInstance = instance;
        if (localInstance == null) {
            synchronized (DirectedGraph.class) {
                localInstance = instance;
                if (localInstance == null) {
                    try {
                        instance = localInstance = directedGraphDAO.retrieveDirectedGraph();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        } else {
            synchronized (DirectedGraph.class) {
                try {
                    instance = localInstance = directedGraphDAO.retrieveDirectedGraph();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
