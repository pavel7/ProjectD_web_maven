package com.omsu.cherepanov.servlets;

import com.omsu.cherepanov.graph.DirectedGraph;
import com.omsu.cherepanov.graph.SingletonGraph;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Павел on 28.05.2014.
 */
public class GraphJSON extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SingletonGraph.updateInstance();
        DirectedGraph directedGraph = SingletonGraph.getInstance();
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
            JSONArray arrConnection = new JSONArray();
            int edgeLenght = directedGraph.getConnectionOfVertex().get(i).getVertexConnection().size();
            for (int j = 1; j < edgeLenght; j++) {
                JSONObject mapConnection = new JSONObject();
                mapConnection.put("ConnectionWithVertexId", directedGraph.indexOfElementGraph(directedGraph.getConnectionOfVertex().get(i).getVertexConnection().get(j).getVertex()));
                mapConnection.put("Defence", directedGraph.getConnectionOfVertex().get(i).getVertexConnection().get(j).getEdge().getDefence());
                mapConnection.put("Status", directedGraph.getConnectionOfVertex().get(i).getVertexConnection().get(j).getEdge().getIsStatus());
                mapConnection.put("isPath", false);
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
