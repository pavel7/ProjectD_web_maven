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

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Павел
 */
@Entity
@Table(name = "elementofgraph")
public class ElementOfGraph implements Cloneable, Serializable {

    private static final long serialVersionUID = -277901234348581225L;
    private Mainclient vertex;
    private Connection edge;
    private VertexConnection vertexConnection;
    private ElementOfGraphID elementOfGraphID;
//    private final static byte initialDefenceMin = 0;
//    private final static byte initialDefenceMax = 100;
//    private final static Mainclient deadClient = new Mainclient(0, 0, 999);
//    private final static Connection destroyedConnection = new Connection(initialDefenceMin, 20);
//    private final static Connection selfConnection = new Connection(initialDefenceMax, 1);
//
//    static {
//        deadClient.setIsStatus(ObjectStatus.isDead);
//        destroyedConnection.setStatus(ObjectStatus.isDead);
//    }

//    public ElementOfGraph(Mainclient newVertex) {
//        vertex = newVertex;
//        edge = selfConnection;
//    }

    @EmbeddedId
    public ElementOfGraphID getElementOfGraphID() {
        return elementOfGraphID;
    }

    public void setElementOfGraphID(ElementOfGraphID elementOfGraphID) {
        this.elementOfGraphID = elementOfGraphID;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @Cascade({CascadeType.SAVE_UPDATE, CascadeType.DETACH, CascadeType.LOCK, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.REPLICATE})
    @JoinColumn(name = "Mainclient_ObjectID")
    @MapsId("mainclientID")
    public Mainclient getVertex() {
        return vertex;
    }

    public void setVertex(Mainclient newVertex) {
        vertex = newVertex;
    }

    @OneToOne(fetch = FetchType.EAGER)
    @Cascade(CascadeType.ALL)
    @JoinColumn(name = "Connection_ObjectID")
    @MapsId("connectionID")
    public Connection getEdge() {
        return edge;
    }

    public void setEdge(Connection newEdge) {
        edge = newEdge;
    }

    @ManyToOne()
    @JoinColumn(name = "VertexConnection_Id")
    @MapsId("vertexconnectionID")
    public VertexConnection getVertexConnection() {
        return vertexConnection;
    }

    public void setVertexConnection(VertexConnection vertexConnection) {
        this.vertexConnection = vertexConnection;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        ElementOfGraph temp = (ElementOfGraph) obj;
        if (this.edge.equals(temp.edge) && this.vertex.equals(temp.vertex)) {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return 7 * this.getEdge().hashCode() + 11 * this.getVertex().hashCode() + 19 * this.getElementOfGraphID().hashCode();
    }

    @Override
    public ElementOfGraph clone() throws CloneNotSupportedException {
        ElementOfGraph newElem = (ElementOfGraph) super.clone();
        newElem.setEdge(this.getEdge());
        newElem.setVertex(this.getVertex());
        return newElem;
    }

}
