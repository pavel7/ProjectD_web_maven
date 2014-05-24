/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.omsu.cherepanov.graph;

import com.omsu.cherepanov.clients.Mainclient;
import com.omsu.cherepanov.connection.Connection;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Павел
 */
@Embeddable
@Table(name = "vertexconnection")
public class DirectedGraph {

    private int amountOfVertex;
    private int amountOfEdge;
    //private final static byte maxDefence = 100;
    private List<VertexConnection> connectionOfVertex = new ArrayList<VertexConnection>(0);

    public DirectedGraph() {
        amountOfVertex = 0;
        amountOfEdge = 0;
    }

    public DirectedGraph(int amountOfVertex) {
        if (amountOfVertex < 0) {
            throw new IllegalArgumentException("Number of vertices must be nonnegative");
        }
        this.amountOfVertex = amountOfVertex;
        amountOfEdge = 0;
        connectionOfVertex = new ArrayList<VertexConnection>(amountOfVertex);
        for (int i = 0; i < amountOfVertex; i++) {
            connectionOfVertex.add(new VertexConnection());
        }
    }

    @OneToMany(fetch = FetchType.EAGER,
            targetEntity = VertexConnection.class)
    @Cascade(CascadeType.ALL)
    public List<VertexConnection> getConnectionOfVertex() {
        return connectionOfVertex;
    }

    public void setConnectionOfVertex(List<VertexConnection> connectionOfVertex) {
        this.connectionOfVertex = connectionOfVertex;
    }

    public int getAmountOfVertex() {
        return amountOfVertex;
    }

    public void setAmountOfVertex(int amountOfVertex) {
        this.amountOfVertex = amountOfVertex;
    }

    public int getAmountOfEdge() {
        return amountOfEdge;
    }

    public void setAmountOfEdge(int amountOfEdge) {
        this.amountOfEdge = amountOfEdge;
    }

    public boolean isPresent(Mainclient client) {
        if (connectionOfVertex.isEmpty()) {
            return false;
        }
        for (int i = 0; i < amountOfVertex; i++) {
            if (connectionOfVertex.get(i).getVertexConnection().isEmpty() == false) {
                if (connectionOfVertex.get(i).getVertexConnection().get(0).getVertex().equals(client)) {
                    return true;
                }
            }

        }
        return false;
    }

    public boolean addVertex(ElementOfGraph vertex) {
        if (vertex == null) {
            return false;
        }
        if (this.isPresent(vertex.getVertex())) {
            return false;
        }
        byte maxDefence = 100;
        Connection selfEdge = new Connection();
        selfEdge.setDefence(maxDefence);
        //vertex.setEdge(selfEdge);
        try {
            for (int i = 0; i < amountOfVertex; i++) {
                if (connectionOfVertex.get(i).getVertexConnection().isEmpty()) {
                    //graph.get(i).set(i, (ArrayList<ElementOfGraph>) new ArrayList());
                    try {
                        connectionOfVertex.get(i).getVertexConnection().add(vertex.clone());
                        connectionOfVertex.get(i).getVertexConnection().get(0).setEdge(selfEdge);
                        return true;
                    } catch (CloneNotSupportedException ex) {
                        Logger.getLogger(DirectedGraph.class.getName()).log(Level.SEVERE, null, ex);
                        return false;
                    }
                }
            }
            try {
                connectionOfVertex.add(new VertexConnection());
                connectionOfVertex.get(amountOfVertex).getVertexConnection().add(vertex.clone());
                connectionOfVertex.get(amountOfVertex).getVertexConnection().get(0).setEdge(selfEdge);
                amountOfVertex++;
                return true;
            } catch (CloneNotSupportedException ex) {
                Logger.getLogger(DirectedGraph.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        } catch (RuntimeException e) {
            Logger.getLogger(DirectedGraph.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }

    }

    public boolean addVertexToVertex(ElementOfGraph fromVertex, ElementOfGraph toVertex) {
        if ((fromVertex == null) || (toVertex == null)) {
            return false;
        }

        if ((this.isPresent(fromVertex.getVertex())) && (this.isPresent(toVertex.getVertex()))) {
            int position = this.indexOfElementGraph(fromVertex.getVertex());
            if (position >= 0) {
                try {
                    connectionOfVertex.get(position).getVertexConnection().add(toVertex.clone());
                    amountOfEdge++;
                    return true;
                } catch (CloneNotSupportedException ex) {
                    Logger.getLogger(DirectedGraph.class.getName()).log(Level.SEVERE, null, ex);
                    return false;
                }

            }
            return false;
        } else if ((this.isPresent(fromVertex.getVertex())) && (this.isPresent(toVertex.getVertex()) == false)) {
            this.addVertex(toVertex);
            int position = this.indexOfElementGraph(fromVertex.getVertex());
            if (position >= 0) {
                try {
                    connectionOfVertex.get(position).getVertexConnection().add(toVertex.clone());
                    amountOfEdge++;
                    return true;
                } catch (CloneNotSupportedException ex) {
                    Logger.getLogger(DirectedGraph.class.getName()).log(Level.SEVERE, null, ex);
                    return false;
                }
            }
            return false;
        } else if ((this.isPresent(fromVertex.getVertex()) == false) && (this.isPresent(toVertex.getVertex()))) {
            this.addVertex(fromVertex);
            int position = this.indexOfElementGraph(fromVertex.getVertex());
            if (position >= 0) {
                try {
                    connectionOfVertex.get(position).getVertexConnection().add(toVertex.clone());
                    amountOfEdge++;
                    return true;
                } catch (CloneNotSupportedException ex) {
                    Logger.getLogger(DirectedGraph.class.getName()).log(Level.SEVERE, null, ex);
                    return false;
                }
            }
            return false;
        } else if ((this.isPresent(fromVertex.getVertex()) == false) && (this.isPresent(toVertex.getVertex()) == false)) {
            this.addVertex(fromVertex);
            this.addVertex(toVertex);
            int position = this.indexOfElementGraph(fromVertex.getVertex());
            if (position >= 0) {
                try {
                    connectionOfVertex.get(position).getVertexConnection().add(toVertex.clone());
                    amountOfEdge++;
                    return true;
                } catch (CloneNotSupportedException ex) {
                    Logger.getLogger(DirectedGraph.class.getName()).log(Level.SEVERE, null, ex);
                    return false;
                }
            }
            return false;
        }

        return false;

    }

    //    public boolean addEdgeBetweenVertices(ElementOfGraph fromVertex, ElementOfGraph toVertex, Connection edge) {
//        if ((fromVertex == null) || (toVertex == null) || (edge == null)) {
//            return false;
//        }
//        if ((this.isPresent(fromVertex)) && (this.isPresent(toVertex))) {
//            int position = this.indexOfElementGraph(fromVertex);
//            if (position >= 0) {
//                int positionOfTo = graph.get(position).indexOf(toVertex);
//                graph.get(position).get(positionOfTo).setEdge(edge);
//                amountOfEdge++;
//                return true;
//            }
//            return false;
//        } else if ((this.isPresent(fromVertex)) && (this.isPresent(toVertex) == false)) {
//            this.addVertex(toVertex);
//            this.addVertexToVertex(fromVertex, toVertex);
//            int position = this.indexOfElementGraph(fromVertex);
//            if (position >= 0) {
//                int positionOfTo = graph.get(position).indexOf(toVertex);
//                graph.get(position).get(positionOfTo).setEdge(edge);
//                amountOfEdge++;
//                return true;
//            }
//            return false;
//        } else if ((this.isPresent(fromVertex) == false) && (this.isPresent(toVertex))) {
//            this.addVertex(fromVertex);
//            this.addVertexToVertex(fromVertex, toVertex);
//            int position = this.indexOfElementGraph(fromVertex);
//            if (position >= 0) {
//                int positionOfTo = graph.get(position).indexOf(toVertex);
//                graph.get(position).get(positionOfTo).setEdge(edge);
//                amountOfEdge++;
//                return true;
//            }
//            return false;
//        } else if ((this.isPresent(fromVertex) == false) && (this.isPresent(toVertex) == false)) {
//            this.addVertex(fromVertex);
//            this.addVertex(toVertex);
//            this.addVertexToVertex(fromVertex, toVertex);
//            int position = this.indexOfElementGraph(fromVertex);
//            if (position >= 0) {
//                int positionOfTo = graph.get(position).indexOf(toVertex);
//                graph.get(position).get(positionOfTo).setEdge(edge);
//                amountOfEdge++;
//                return true;
//            }
//            return false;
//        }
//        return false;
//    }
    public int indexOfElementGraph(Mainclient desiredClient) {
        if (desiredClient == null) {
            return -1;
        }
        if (this.isPresent(desiredClient) == false) {
            return -1;
        }
        for (int i = 0; i < amountOfVertex; i++) {
            if (connectionOfVertex.get(i).getVertexConnection().isEmpty() == false) {
                if (connectionOfVertex.get(i).getVertexConnection().get(0).getVertex().equals(desiredClient)) {
                    return i;
                }
            }
        }
        return -1;
    }

    public Iterator getIteratorOfElem(int indexOfElem) {
        return connectionOfVertex.get(indexOfElem).getVertexConnection().iterator();
    }

}
