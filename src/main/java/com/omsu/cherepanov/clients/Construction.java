/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.omsu.cherepanov.clients;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Павел
 */

@Entity
@Table(name = "construction")
@PrimaryKeyJoinColumn(name = "Mainclient_ObjectID", referencedColumnName = "ObjectID")
public class Construction extends Mainclient {

    private String name;
    private List<People> staff = new ArrayList<People>(0);

    public Construction() {
        super();
        name = "";
    }

    public Construction(String newName) {
        name = newName;
        //staff = new HashSet();
    }

    public void setStaff(List<People> staff) {
        this.staff = staff;
    }

    @OneToMany()
    @Cascade(CascadeType.ALL)
    @JoinTable(name = "construction_people", joinColumns = @JoinColumn(name = "Construction_Mainclient_ObjectID"), inverseJoinColumns = @JoinColumn(name = "People_Mainclient_ObjectID"))
    public List<People> getStaff() {
        return staff;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addStaff(People newPeople) {
        this.staff.add(newPeople);
    }

    public void removeStaff(People curPeople) {
        this.staff.remove(curPeople);
    }

    public void removeAllStaff() {
        this.staff.clear();
    }

    @Override
    public int hashCode() {
        return super.hashCode() * 7 + this.getName().hashCode() * 11 + this.getStaff().hashCode() * 13;
    }

    @Override
    public boolean equals(Object otherEquipment) {
        if (this == otherEquipment)
            return true;
        if (otherEquipment == null)
            return false;
        if (getClass() != otherEquipment.getClass())
            return false;
        Construction other = (Construction) otherEquipment;
        if (this.getObjectID() != other.getObjectID()) return false;
        return true;
    }

}
