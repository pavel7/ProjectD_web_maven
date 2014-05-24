package com.omsu.cherepanov.servlets;

import com.omsu.cherepanov.users.UserBean;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Павел on 24.05.2014.
 */
public class InSystem implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        HttpSession session = req.getSession();
        if (session != null)
        {
            UserBean userBean = (UserBean) session.getAttribute("currentSessionUser");
            if (userBean != null)
            {
                if(userBean.isValid())
                {
                    resp.sendRedirect("main.jsp");
                }
                else
                {
                    filterChain.doFilter(req, resp);
                }
            }
            else
            {
                filterChain.doFilter(req, resp);
            }
        }
        else
        {
            filterChain.doFilter(req, resp);
        }
    }

    @Override
    public void destroy() {

    }
}
