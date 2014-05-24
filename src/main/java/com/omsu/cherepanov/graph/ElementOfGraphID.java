package com.omsu.cherepanov.graph;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Created by Павел on 11.05.2014.
 */
@Embeddable
public class ElementOfGraphID implements Serializable {


    private static final long serialVersionUID = 1477801429827368848L;
    private int mainclientID;
    private int connectionID;
    private int vertexconnectionID;

    @Column(name = "Mainclient_ObjectID")
    public int getMainclientID() {
        return mainclientID;
    }

    public void setMainclientID(int mainclientID) {
        this.mainclientID = mainclientID;
    }

    @Column(name = "Connection_ObjectID")
    public int getConnectionID() {
        return connectionID;
    }

    public void setConnectionID(int connectionID) {
        this.connectionID = connectionID;
    }

    @Column(name = "VertexConnection_Id")
    public int getVertexconnectionID() {
        return vertexconnectionID;
    }

    public void setVertexconnectionID(int vertexconnectionID) {
        this.vertexconnectionID = vertexconnectionID;
    }

    @Override
    public int hashCode() {
        return 11 * mainclientID + 19 * connectionID + 23 * vertexconnectionID;
    }

    @Override
    public boolean equals(Object otherEquipment) {
        if (this == otherEquipment)
            return true;
        if (otherEquipment == null)
            return false;
        if (getClass() != otherEquipment.getClass())
            return false;
        ElementOfGraphID other = (ElementOfGraphID) otherEquipment;
        if (this.getConnectionID() != other.getConnectionID()) return false;
        if (this.getMainclientID() != other.getMainclientID()) return false;
        return true;
    }

}
