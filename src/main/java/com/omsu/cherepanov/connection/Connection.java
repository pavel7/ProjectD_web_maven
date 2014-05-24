/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.omsu.cherepanov.connection;

import com.omsu.cherepanov.clients.ObjectStatus;

import javax.persistence.*;


/**
 * @author Павел
 */
@Entity
@Table(name = "connection")
public class Connection {

    @Id
    private int objectID;
    private byte defence;
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "Status_IdStatus")
    private ObjectStatus isStatus;

    public Connection() {
        objectID = 0;
        defence = 0;
        isStatus = ObjectStatus.isAlive;
    }

    public Connection(byte newDefence, int id) {
        objectID = id;
        defence = newDefence;
        isStatus = ObjectStatus.isAlive;
    }


    public void setIsStatus(ObjectStatus isStatus) {
        this.isStatus = isStatus;
    }

    public void setDefence(byte defence) {

        this.defence = defence;
    }

    public ObjectStatus getIsStatus() {

        return isStatus;
    }

    public void setObjectID(int id) {
        objectID = id;
    }

    public int getObjectID() {
        return objectID;
    }

    public byte getDefence() {
        return defence;
    }

    public void setStatus(ObjectStatus newStatus) {
        isStatus = newStatus;
    }

    public ObjectStatus getStatus() {
        return isStatus;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Connection temp = (Connection) obj;
        if (this.getObjectID() != temp.getObjectID()) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return 7 * objectID + 11 * defence + 13 * this.getIsStatus().toString().hashCode();
    }

}
