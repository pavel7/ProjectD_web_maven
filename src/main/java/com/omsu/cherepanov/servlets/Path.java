package com.omsu.cherepanov.servlets;

import com.omsu.cherepanov.algorithms.Dijkstra;
import com.omsu.cherepanov.graph.DirectedGraph;
import com.omsu.cherepanov.graph.SingletonGraph;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Павел on 28.05.2014.
 */
public class Path extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(true);
        session.removeAttribute("path");
        if (req.getParameter("emptyPath") != null) {
            if (req.getParameter("emptyPath").equals("makeEmpty"))
                resp.sendRedirect("main.jsp");
            else
                resp.sendRedirect("main.jsp");
        } else {
            DirectedGraph directedGraph = SingletonGraph.getInstance();
            if ((req.getParameter("listboxFrom") == null) || (req.getParameter("listboxTo") == null)) {
                session.setAttribute("error", "Empty parameters");
                resp.sendRedirect("main.jsp");
            } else {
                try {
                    int from = Integer.parseInt(req.getParameter("listboxFrom"));
                    int to = Integer.parseInt(req.getParameter("listboxTo"));
                    if ((from < 0) || (from >= directedGraph.getAmountOfVertex()) || (to < 0) || (to >= directedGraph.getAmountOfVertex())) {
                        session.setAttribute("error", "Incorrect parameters");
                        resp.sendRedirect("main.jsp");
                    } else if (from == to) {
                        session.setAttribute("error", "Start coincides with the end");
                        resp.sendRedirect("main.jsp");
                    } else {
                        Dijkstra dijkstra = new Dijkstra(directedGraph, directedGraph.getConnectionOfVertex().get(from).getVertexConnection().get(0).getVertex()
                                , directedGraph.getConnectionOfVertex().get(to).getVertexConnection().get(0).getVertex());
                        int[] pathFromTo = dijkstra.pathFromTo();
                        if (pathFromTo[to] == -1) {
                            session.setAttribute("error", "Vertices not connected");
                            resp.sendRedirect("main.jsp");
                        } else {
                            List<Integer> graphPath = new ArrayList<Integer>(0);
                            List<Integer> graphPathInvert = new ArrayList<Integer>(0);
                            graphPath.add(to);
                            int pathTemp = to;
                            while (pathTemp != from) {
                                graphPath.add(pathFromTo[pathTemp]);
                                pathTemp = pathFromTo[pathTemp];
                            }
                            int pathLenght = graphPath.size();
                            for (int k = 0; k < pathLenght; k++) {
                                graphPathInvert.add(graphPath.get(pathLenght - k - 1));
                            }
                            session.setAttribute("path", graphPathInvert);
                            resp.sendRedirect("main.jsp");
                        }
                    }
                } catch (NumberFormatException e) {
                    session.setAttribute("error", "Incorrect parameters");
                    resp.sendRedirect("main.jsp");
                }
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
