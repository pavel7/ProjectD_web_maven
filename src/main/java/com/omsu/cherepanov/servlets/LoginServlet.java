package com.omsu.cherepanov.servlets;

import com.omsu.cherepanov.hibernate.UserDAO;
import com.omsu.cherepanov.users.UserBean;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Павел on 24.05.2014.
 */
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            if ((req.getParameter("login").length() <= 20) && (req.getParameter("password").length() <= 20)) {
                UserDAO userDAO = new UserDAO();
                UserBean user = new UserBean();
                user.setUsername(req.getParameter("login"));
                user.setPassword(req.getParameter("password"));
                user = userDAO.login(user);
                if (user.isValid()) {

                    HttpSession session = req.getSession(true);
                    session.setAttribute("currentSessionUser", user);
                    resp.sendRedirect("main.jsp");
                } else
                    resp.sendRedirect("index.jsp");
            }
            else{
                resp.sendRedirect("index.jsp");
            }
        }


        catch (Throwable theException)
        {
            System.out.println(theException);
        }
    }
}
