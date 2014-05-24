/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.omsu.cherepanov.clients;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Павел
 */
@Entity
@Table(name = "equipment")
public class Equipment {

    private String type;
    private String identifier;
    @Id
    private int equipmentID;

    public Equipment() {
        type = "";
        identifier = "";
        equipmentID = 0;
    }

    public Equipment(String newType, String newIdentifier, int newEquipmentID) {
        type = newType;
        identifier = newIdentifier;
        equipmentID = newEquipmentID;
    }

    public Equipment(Equipment newEquipment) {
        this.identifier = newEquipment.getIdentifier();
        this.type = newEquipment.getType();
        this.equipmentID = newEquipment.getEquipmentID();
    }

    public int getEquipmentID() {
        return equipmentID;
    }

    public void setEquipmentID(int equipmentID) {
        this.equipmentID = equipmentID;
    }

    public String getType() {
        return type;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    @Override
    public int hashCode() {
        return 11 * type.hashCode() + 13 * identifier.hashCode();
    }

    @Override
    public boolean equals(Object otherEquipment) {
        if (this == otherEquipment)
            return true;
        if (otherEquipment == null)
            return false;
        if (getClass() != otherEquipment.getClass())
            return false;
        Equipment other = (Equipment) otherEquipment;
        if (!this.getType().equals(other.getType())) return false;
        if (!this.getIdentifier().equals(other.getIdentifier())) return false;
        return true;
    }
}
