package com.omsu.cherepanov.clients;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Created by Павел on 02.05.2014.
 */
@Embeddable
public class MainclientEquipmentID implements Serializable {

    private static final long serialVersionUID = -7352732871315441553L;
    private int mainclientID;
    private int equipmentID;

    @Column(name = "Mainclient_ObjectID")
    public int getMainclientID() {
        return mainclientID;
    }

    public void setMainclientID(int mainclientID) {
        this.mainclientID = mainclientID;
    }

    @Column(name = "Equipment_EquipmentID")
    public int getEquipmentID() {
        return equipmentID;
    }

    public void setEquipmentID(int equipmentID) {
        this.equipmentID = equipmentID;
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
        MainclientEquipmentID temp = (MainclientEquipmentID) obj;
        if (this.getMainclientID() != temp.getMainclientID()) return false;
        if (this.getEquipmentID() != temp.getEquipmentID()) return false;
        return true;
    }

    @Override
    public int hashCode() {
        return 11 * mainclientID + 23 * equipmentID;
    }
}
