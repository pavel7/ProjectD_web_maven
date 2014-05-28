package com.omsu.cherepanov.servlets;

import com.omsu.cherepanov.algorithms.Dijkstra;
import com.omsu.cherepanov.graph.DirectedGraph;
import com.omsu.cherepanov.graph.SingletonGraph;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
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

                            boolean notEmptyPath = false;
                            int currentPos = 0;
                            int endPos = 0;
                            if (graphPathInvert != null) {
                                notEmptyPath = true;
                                endPos = graphPathInvert.size();
                            }

                            resp.setContentType("application/json");
                            resp.setHeader("Cache-Control", "nocache");
                            resp.setCharacterEncoding("utf-8");
                            PrintWriter out = resp.getWriter();

                            JSONObject json = new JSONObject();
                            int amountOfVertex = directedGraph.getAmountOfVertex();
                            int amountOfEdge = directedGraph.getAmountOfEdge();
                            json.put("amountOfVertex", amountOfVertex);
                            json.put("amountOfEdge", amountOfEdge);
                            JSONArray arrayAllParam = new JSONArray();
                            for (int i = 0; i < amountOfVertex; i++) {
                                boolean isPath = false;
                                if (notEmptyPath) {
                                    if (graphPathInvert.get(currentPos) == i) {
                                        isPath = true;
                                        currentPos++;
                                    }
                                    if (currentPos == endPos) {
                                        notEmptyPath = false;
                                    }
                                }
                                JSONArray arrConnection = new JSONArray();
                                int edgeLenght = directedGraph.getConnectionOfVertex().get(i).getVertexConnection().size();
                                for (int j = 1; j < edgeLenght; j++) {
                                    JSONObject mapConnection = new JSONObject();
                                    mapConnection.put("ConnectionWithVertexId", directedGraph.indexOfElementGraph(directedGraph.getConnectionOfVertex().get(i).getVertexConnection().get(j).getVertex()));
                                    mapConnection.put("Defence", directedGraph.getConnectionOfVertex().get(i).getVertexConnection().get(j).getEdge().getDefence());
                                    mapConnection.put("Status", directedGraph.getConnectionOfVertex().get(i).getVertexConnection().get(j).getEdge().getIsStatus());
                                    if ((isPath) && (notEmptyPath) && (graphPathInvert.get(currentPos) == directedGraph.indexOfElementGraph(directedGraph.getConnectionOfVertex().get(i).getVertexConnection().get(j).getVertex()))) {
                                        mapConnection.put("isPath", true);
                                    } else {
                                        mapConnection.put("isPath", false);
                                    }
                                    arrConnection.put(mapConnection);
                                }
                                JSONObject mapVertex = new JSONObject();
                                mapVertex.put("id", i);
                                mapVertex.put("PointX", directedGraph.getConnectionOfVertex().get(i).getVertexConnection().get(0).getVertex().getPointX());
                                mapVertex.put("PointY", directedGraph.getConnectionOfVertex().get(i).getVertexConnection().get(0).getVertex().getPointY());
                                mapVertex.put("ConnectionSize", directedGraph.getConnectionOfVertex().get(i).getVertexConnection().size() - 1);
                                mapVertex.put("Connection", arrConnection);
                                mapVertex.put("Status", directedGraph.getConnectionOfVertex().get(i).getVertexConnection().get(0).getVertex().getIsStatus());
                                arrayAllParam.put(mapVertex);
                                //arr.put(i).put("pointX", directedGraph.getConnectionOfVertex().get(i).getVertexConnection().get(0).getVertex().getPointX());

                            }
                            json.put("Vertices", arrayAllParam);
                            //json.put("Vertex", arr);
                            out.print(json.toString());
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
