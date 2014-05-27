package com.omsu.cherepanov.servlets;

import com.omsu.cherepanov.graph.SingletonGraph;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Павел on 26.05.2014.
 */
public class UpdateGraph extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SingletonGraph.updateInstance();
        resp.sendRedirect("main.jsp");
    }
}