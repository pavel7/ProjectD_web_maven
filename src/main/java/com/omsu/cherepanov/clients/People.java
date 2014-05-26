/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.omsu.cherepanov.clients;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * @author Павел
 */
@Entity
@Table(name = "people")
@PrimaryKeyJoinColumn(name = "Mainclient_ObjectID", referencedColumnName = "ObjectID")
public class People extends Mainclient {

    private String name;
    private String rank;

    public People() {
        super();
        name = "";
        rank = "";
    }

    public People(String newName, String newRank) {
        super();
        name = newName;
        rank = newRank;
    }

    public People(double newX, double newY, int newID, String newName, String newRank) {
        super(newX, newY, newID);
        name = newName;
        rank = newRank;
    }

    @Column(name = "Name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "Rank")
    public String getRank() {
        return rank;
    }

    public void setRank(String newRank) {
        this.rank = newRank;
    }

    @Override
    public int hashCode() {
        return super.hashCode() * 5 + 7 * name.hashCode() + 11 * rank.hashCode();
    }

    @Override
    public boolean equals(Object otherEquipment) {
        if (this == otherEquipment)
            return true;
        if (otherEquipment == null)
            return false;
        if (getClass() != otherEquipment.getClass())
            return false;
        People other = (People) otherEquipment;
        if (this.getObjectID() != other.getObjectID()) return false;
        return true;
    }

}
