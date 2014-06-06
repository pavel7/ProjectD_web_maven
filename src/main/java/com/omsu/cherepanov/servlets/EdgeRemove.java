package com.omsu.cherepanov.servlets;

import com.omsu.cherepanov.graph.DirectedGraph;
import com.omsu.cherepanov.graph.SingletonGraph;
import com.omsu.cherepanov.hibernate.DirectedGraphDAO;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Павел on 06.06.2014.
 */
public class EdgeRemove extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if ((req.getParameter("fromVertex") != null) && (req.getParameter("toVertex") != null)) {
            SingletonGraph.updateInstance();
            DirectedGraph directedGraph = SingletonGraph.getInstance();
            try {
                int from = Integer.parseInt(req.getParameter("fromVertex"));
                int to = Integer.parseInt(req.getParameter("toVertex"));
                if ((from >= 0) || (from < directedGraph.getAmountOfVertex()) || (to >= 0) || (to < directedGraph.getAmountOfVertex())) {
                    if (from != to) {
                        DirectedGraphDAO directedGraphDAO = new DirectedGraphDAO();
                        directedGraphDAO.deleteDirectedGraph(directedGraph);
                        directedGraph.removeEdge(from, to);
                        directedGraphDAO.saveDirectedGraph(directedGraph);
                        resp.setContentType("application/json");
                        resp.setHeader("Cache-Control", "nocache");
                        resp.setCharacterEncoding("utf-8");
                        PrintWriter out = resp.getWriter();
                        JSONObject json = new JSONObject();

                        json.put("success", true);
                        out.print(json.toString());
                    }
                }
            } catch (NumberFormatException e) {
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
