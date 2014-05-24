/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.omsu.cherepanov.algorithms;

import com.omsu.cherepanov.clients.Mainclient;
import com.omsu.cherepanov.connection.Connection;
import com.omsu.cherepanov.graph.DirectedGraph;
import com.omsu.cherepanov.graph.ElementOfGraph;

import java.util.Iterator;

/**
 * @author Павел
 */
public class Dijkstra {

    private double[] defenceArray;
    private boolean[] vertexFlag;
    private int[] arrayPath;
    private DirectedGraph graph;
    private Mainclient fromClient;
    private Mainclient toClient;
    private int amountOfVertex;

    public Dijkstra(DirectedGraph initialGraph, Mainclient from, Mainclient to) {
        graph = initialGraph;
        fromClient = from;
        toClient = to;
        amountOfVertex = initialGraph.getAmountOfVertex();
        defenceArray = new double[amountOfVertex];
        for (int i = 0; i < amountOfVertex; i++) {
            defenceArray[i] = 0;
        }
        int indexOfFromClient = graph.indexOfElementGraph(fromClient);
        defenceArray[indexOfFromClient] = 1;
        //all elements creates as false by default
        vertexFlag = new boolean[amountOfVertex];
        //all elements creates as 0 by default
        arrayPath = new int[amountOfVertex];
    }

    public int[] pathFromTo() {
        if (fromClient == null) {
            return arrayPath.clone();
        }

        if (toClient == null) {
            return arrayPath;
        }

        if (amountOfVertex == 0) {
            return arrayPath;
        }

        if (graph.isPresent(toClient) == false) {
            return arrayPath;
        }

        if (graph.isPresent(fromClient) == false) {
            return arrayPath;
        }

        for (int i = 0; i < amountOfVertex; i++) {
            int indexOfMaximum = this.indexOfMaximumValue();
            if (indexOfMaximum == -1) {
                return arrayPath;
            }
            vertexFlag[indexOfMaximum] = true;
            Iterator linkWithCurrent = graph.getIteratorOfElem(indexOfMaximum);
            linkWithCurrent.next();
            while (linkWithCurrent.hasNext()) {
                ElementOfGraph link = (ElementOfGraph) linkWithCurrent.next();
                Mainclient client = link.getVertex();
                Connection clientConnection = link.getEdge();
                int indexClientInGraph = graph.indexOfElementGraph(client);
                double defenceInDouble = clientConnection.getDefence() / 100.00;
                if (defenceArray[indexClientInGraph] < defenceInDouble * defenceArray[indexOfMaximum]) {
                    defenceArray[indexClientInGraph] = defenceInDouble * defenceArray[indexOfMaximum];
                    arrayPath[indexClientInGraph] = indexOfMaximum;
                }
            }

        }
        return arrayPath;

    }

    private int indexOfMaximumValue() {
        int indexOfMaximum = -1;
        double maximumDefence = 0;
        for (int i = 0; i < amountOfVertex; i++) {
            if (vertexFlag[i] == false) {
                if (maximumDefence <= defenceArray[i]) {
                    maximumDefence = defenceArray[i];
                    indexOfMaximum = i;
                }
            }
        }
        return indexOfMaximum;
    }

}
