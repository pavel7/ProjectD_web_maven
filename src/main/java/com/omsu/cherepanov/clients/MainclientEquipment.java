package com.omsu.cherepanov.clients;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Павел on 02.05.2014.
 */

@Entity
@Table(name = "mainclientequ")
public class MainclientEquipment implements Serializable {

    private static final long serialVersionUID = 4194674729380813963L;
    private MainclientEquipmentID mainclientEquipmentID;
    private Mainclient mainclient;
    private Equipment equipment;
    private int amount;

    @ManyToOne(fetch = FetchType.EAGER)
    @Cascade(CascadeType.ALL)
    @JoinColumn(name = "Mainclient_ObjectID")
    @MapsId("mainclientID")
    public Mainclient getMainclient() {
        return mainclient;
    }

    public void setMainclient(Mainclient mainclient) {
        this.mainclient = mainclient;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @Cascade({CascadeType.SAVE_UPDATE, CascadeType.DETACH, CascadeType.LOCK, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.REPLICATE})
    @JoinColumn(name = "Equipment_EquipmentID")
    @MapsId("equipmentID")
    public Equipment getEquipment() {
        return equipment;
    }

    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }

    @Column(name = "Amount")
    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @EmbeddedId
    public MainclientEquipmentID getMainclientEquipmentID() {
        return mainclientEquipmentID;
    }

    public void setMainclientEquipmentID(MainclientEquipmentID mainclientEquipmentID) {
        this.mainclientEquipmentID = mainclientEquipmentID;
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
        MainclientEquipment temp = (MainclientEquipment) obj;
        if (!this.getMainclientEquipmentID().equals(temp.getMainclientEquipmentID())) return false;
        if (!this.getMainclient().equals(temp.getMainclient())) return false;
        if (!this.getEquipment().equals(temp.getEquipment())) return false;
        if (this.getAmount() != temp.getAmount()) return false;
        return true;
    }

    @Override
    public int hashCode() {
        return this.getAmount() * 7 + this.getEquipment().hashCode() * 11 + this.getMainclientEquipmentID().hashCode() * 13 + this.getMainclient().hashCode() * 17;
    }

}
