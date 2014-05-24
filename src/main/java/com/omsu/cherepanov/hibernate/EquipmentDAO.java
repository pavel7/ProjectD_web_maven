package com.omsu.cherepanov.hibernate;

import com.omsu.cherepanov.clients.Equipment;
import org.hibernate.HibernateException;
import org.hibernate.Query;

/**
 * Created by Павел on 22.04.2014.
 */
public class EquipmentDAO extends DAO {

    public Equipment createEquipment(String type, String identifier, int newEquipmentID)
            throws Exception {
        try {
            begin();
            Equipment equipment = new Equipment(type, identifier, newEquipmentID);
            getSession().saveOrUpdate(equipment);
            commit();
            return equipment;
        } catch (HibernateException e) {
            rollback();
            throw new Exception("Could not create Equipment Type:" + type + ", Identifier:" + identifier, e);
        } finally {
            close();
        }
    }

    public void saveEquipment(Equipment equipment)
            throws Exception {
        try {
            begin();
            getSession().saveOrUpdate(equipment);
            commit();
        } catch (HibernateException e) {
            rollback();
            throw new Exception("Could not create Equipment Type:" + equipment.getType() + ", Identifier:" + equipment.getIdentifier(), e);
        } finally {
            close();
        }
    }

    public Equipment retrieveEquipment(String type, String identifier) throws Exception {
        try {
            begin();
            Query q = getSession().createQuery("from Equipment where type = :type AND identifier = :identifier");
            q.setString("type", type);
            q.setString("identifier", identifier);
            Equipment equipment = (Equipment) q.uniqueResult();
            commit();
            return equipment;
        } catch (HibernateException e) {
            rollback();
            throw new Exception("Could not get Equipment Type:" + type + ", Identifier:" + identifier, e);
        } finally {
            close();
        }
    }

    public void deleteEquipment(Equipment equipment) throws Exception {
        try {
            begin();
            getSession().delete(equipment);
            commit();
        } catch (HibernateException e) {
            rollback();
            throw new Exception("Could not delete Equipment Type:" + equipment.getType() + ", Identifier:" + equipment.getIdentifier(), e);
        } finally {
            close();
        }
    }
}
