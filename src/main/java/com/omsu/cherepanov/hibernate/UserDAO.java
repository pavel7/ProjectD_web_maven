package com.omsu.cherepanov.hibernate;

import com.omsu.cherepanov.algorithms.HashText;
import com.omsu.cherepanov.users.UserBean;
import org.hibernate.HibernateException;
import org.hibernate.Query;

import java.math.BigInteger;

/**
 * Created by Павел on 24.05.2014.
 */
public class UserDAO extends DAO {

    public UserBean login(UserBean userBean) throws Exception {
        userBean.setValid(false);
        try {
            begin();
            String username = userBean.getUsername();
            String password = HashText.sha1(userBean.getPassword());
            Query q = getSession().createSQLQuery("select count(*) from accounts where Login = :user_login and Password = :user_password")
                    .setParameter("user_login", username)
                    .setParameter("user_password", password);
            int amount = ((BigInteger) q.uniqueResult()).intValue();
            if (amount == 1) {
                userBean.setValid(true);
            }
            commit();
            return userBean;
        } catch (HibernateException e) {
            rollback();
            throw new Exception("Could not found user", e);
        } finally {
            close();
        }
    }

}
