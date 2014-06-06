package com.omsu.cherepanov.servlets;

import com.omsu.cherepanov.algorithms.AESDemo;
import com.omsu.cherepanov.graph.DirectedGraph;
import com.omsu.cherepanov.graph.SingletonGraph;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Павел on 31.05.2014.
 */
public class EncryptText extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SingletonGraph.updateInstance();
        DirectedGraph directedGraph = SingletonGraph.getInstance();
        if ((req.getParameter("textEncryptSource") != null) && (req.getParameter("userTo") != null)) {
            int userTo = Integer.parseInt(req.getParameter("userTo"));
            if ((userTo >= 0) && (userTo < directedGraph.getAmountOfVertex())) {
                String sourceText = req.getParameter("textEncryptSource");
                AESDemo aesDemo = new AESDemo();
                aesDemo.AesCrypt(directedGraph.getConnectionOfVertex().get(userTo).getPassword());
                String encryptedText = "";
                try {
                    encryptedText = aesDemo.encrypt(sourceText);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                resp.setContentType("application/json");
                resp.setHeader("Cache-Control", "nocache");
                resp.setCharacterEncoding("utf-8");
                PrintWriter out = resp.getWriter();
                JSONObject json = new JSONObject();

                json.put("msg", encryptedText);
                out.print(json.toString());
            }
        }
    }
}
