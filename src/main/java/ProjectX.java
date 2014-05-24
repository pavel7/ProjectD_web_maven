import com.omsu.cherepanov.clients.Construction;
import com.omsu.cherepanov.clients.Equipment;
import com.omsu.cherepanov.clients.Mainclient;
import com.omsu.cherepanov.clients.People;
import com.omsu.cherepanov.connection.Connection;
import com.omsu.cherepanov.graph.DirectedGraph;
import com.omsu.cherepanov.graph.ElementOfGraph;
import com.omsu.cherepanov.graph.VertexConnection;
import com.omsu.cherepanov.hibernate.*;
import com.omsu.cherepanov.users.UserBean;

import java.util.HashMap;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * @author Павел
 */
public class ProjectX {

    public static void main(String[] args) {
        System.out.println("qwe");
        Mainclient test = new Mainclient(0, 0, 0);
        Mainclient test1 = new Mainclient(0, 0, 1);
        Connection testC = new Connection((byte) 50, 1);
        Connection testC1 = new Connection((byte) 75, 2);
//        ElementOfGraph testE = new ElementOfGraph(test,test,testC);
//        ElementOfGraph testE1 = new ElementOfGraph(test,test1,testC);
//       System.out.println(test.getObjectID());
//       System.out.println(test1.getObjectID());
//       System.out.println(test.getStatus());
//       System.out.println("isAlive".equals(test.getStatus().toString()) ? "true":"false");
        HashMap apples = new HashMap(); // будем хранить тут количество яблок у детей
        Equipment temp = new Equipment("Weapon", "Gun", 1);
        apples.put(temp, 1);
        apples.put("Mike", 4);
        apples.put("John", 7);

// теперь можно посмотреть сколько яблок у Тома
        System.out.println("Tom have " + apples.get(temp) + " count");
        System.out.println(test.equals(test));
        System.out.println(test.equals(test1));
        System.out.println(test1.equals(test));
        System.out.println(test1.equals(null));
        System.out.println(testC.equals(testC));
        System.out.println(testC.equals(testC1));
        System.out.println(testC1.equals(testC));
        System.out.println(testC1.equals(null));
//        System.out.println(testE.equals(testE));
//        System.out.println(testE.equals(testE1));
//        System.out.println(testE1.equals(testE));
//        System.out.println(testE1.equals(null));
//        ElementOfGraph test2 = new ElementOfGraph(test, testC);
//        ElementOfGraph test3 = new ElementOfGraph(test1, testC1);
//        ElementOfGraph test4 = new ElementOfGraph(test1, testC);
        ElementOfGraph test5 = new ElementOfGraph();
        Connection testC2 = new Connection((byte) 90, 3);
//        DirectedGraph test12=new DirectedGraph(2);
//        test12.addVertex(test2);
//        test12.addVertex(test3);
//        test12.addVertex(test4);
//        test12.addVertexToVertex(test2, test3);
//        test12.addVertexToVertex(test2, test4);
//        DirectedGraph test123=new DirectedGraph(2);
//        map tt=new map(1,2);
//        Dijkstra temp11=new Dijkstra(test12,test,test1);
//        Iterator testt=test12.getIteratorOfElem(0);
//        int[]path=temp11.pathFromTo();
        Mainclient test10 = new Mainclient(0, 0, 2);
        Mainclient test11 = new Mainclient(0, 1, 3);
        Mainclient test12 = new Mainclient(0, 2, 4);
        Mainclient test13 = new Mainclient(0, 3, 5);
        Mainclient test14 = new Mainclient(0, 4, 6);
        Mainclient test15 = new Mainclient(0, 5, 7);
        Connection testC100 = new Connection((byte) 100, 0);
        Connection testC11 = new Connection((byte) 50, 4);
        Connection testC12 = new Connection((byte) 20, 5);
        Connection testC13 = new Connection((byte) 40, 6);
        Connection testC14 = new Connection((byte) 25, 7);
        Connection testC15 = new Connection((byte) 70, 8);
        Connection testC16 = new Connection((byte) 50, 9);
        Connection testC17 = new Connection((byte) 60, 10);
        Connection testC18 = new Connection((byte) 40, 11);
        Connection testC19 = new Connection((byte) 45, 12);//changes
        Connection testC20 = new Connection((byte) 100, 13);
//        ElementOfGraph tes1 = new ElementOfGraph(test10);
//        ElementOfGraph tes2 = new ElementOfGraph(test11);
//        ElementOfGraph tes3 = new ElementOfGraph(test12);
//        ElementOfGraph tes4 = new ElementOfGraph(test13);
//        ElementOfGraph tes5 = new ElementOfGraph(test14);
//        ElementOfGraph tes6 = new ElementOfGraph(test15);
        DirectedGraph test200 = new DirectedGraph(2);
//        test200.addVertex(tes1);
//        test200.addVertex(tes2);
//        test200.addVertex(tes3);
//        test200.addVertex(tes4);
//        test200.addVertex(tes5);
//        test200.addVertex(tes6);
//        ElementOfGraph tes21 = new ElementOfGraph(test11, testC12);
//        test200.addVertexToVertex(tes1, tes21);
//        ElementOfGraph tes31 = new ElementOfGraph(test12, testC11);
//        test200.addVertexToVertex(tes1, tes31);
//        ElementOfGraph tes41 = new ElementOfGraph(test13, testC13);
//        test200.addVertexToVertex(tes1, tes41);
//        ElementOfGraph tes42 = new ElementOfGraph(test13, testC15);
//        test200.addVertexToVertex(tes2, tes42);
//        ElementOfGraph tes43 = new ElementOfGraph(test13, testC16);
//        test200.addVertexToVertex(tes3, tes43);
//        ElementOfGraph tes52 = new ElementOfGraph(test14, testC14);
//        test200.addVertexToVertex(tes2, tes52);
//        ElementOfGraph tes53 = new ElementOfGraph(test14, testC20);
//        test200.addVertexToVertex(tes3, tes53);
//        ElementOfGraph tes63 = new ElementOfGraph(test15, testC18);
//        test200.addVertexToVertex(tes3, tes63);
//        ElementOfGraph tes64 = new ElementOfGraph(test15, testC17);
//        test200.addVertexToVertex(tes4, tes64);
//        ElementOfGraph tes65 = new ElementOfGraph(test15, testC19);
//        test200.addVertexToVertex(tes5, tes65);
//        Dijkstra temp11 = new Dijkstra(test200, test10, test15);
//        Iterator testt = test200.getIteratorOfElem(0);
//        int[] path = temp11.pathFromTo();

        ConnectionDAO connectionDAO = new ConnectionDAO();
        Equipment test5555 = new Equipment("qwe", "qwe", 1);
        MainclientDAO userDAO = new MainclientDAO();
        Mainclient ttt = new Mainclient(1, 1, 257);
        ttt.addEquipment(test5555, 5);
        EquipmentDAO testDAO = new EquipmentDAO();
        PeopleDAO peopleDAO = new PeopleDAO();
        People testPeople = new People(1, 2, 7, "test", "XYI");
        People testPeople1 = new People(1, 3.5, 4, "test1", "XYI1");
        Construction testConstruction = new Construction("test");
        testConstruction.setObjectID(77);
        testConstruction.setPointX(1.4);
        testConstruction.setPointY(3.4);
        testPeople.addEquipment(test5555, 5);
        testConstruction.addStaff(testPeople);
        testConstruction.addStaff(testPeople1);
        ConstructionDAO constructionDAO = new ConstructionDAO();
        VertexConnectionDAO vertexConnectionDAO = new VertexConnectionDAO();
        VertexConnection vertexConnection = new VertexConnection();
        VertexConnection vertexConnection1 = new VertexConnection();
        VertexConnection vertexConnection2 = new VertexConnection();
        vertexConnection.setId(12);
        vertexConnection1.setId(13);
        vertexConnection2.setId(14);
        //vertexConnection.setMainclient(ttt);
        Connection testC101 = new Connection((byte) 100, 1);
        vertexConnection.addVertex(ttt, testC100);
        vertexConnection.addVertex(testPeople, testC12);
        vertexConnection1.addVertex(testPeople, testC101);
        vertexConnection1.addVertex(ttt, testC11);
        DirectedGraph test123 = new DirectedGraph();
        UserDAO test444 = new UserDAO();
        //test123.addVertex();
        //ElementOfGraphDAO elementOfGraphDAO = new ElementOfGraphDAO();
        // ElementOfGraph elementOfGraph = new ElementOfGraph(ttt, testC20, 515);
        DirectedGraphDAO directedGraphDAO = new DirectedGraphDAO();
        try {
            UserBean user5551 = new UserBean();
            user5551.setUsername("test");
            user5551.setPassword("q");
            user5551 = test444.login(user5551);
            //connectionDAO.saveConnection(testC20);
            //userDAO.createEquipment("qwe", "qwe", 1011);
            //testDAO.createEquipment("4","4",4);
            //Mainclient t1 = userDAO.retrieveMainclient(2);
            //Equipment test442 = testDAO.retrieveEquipment("qwe", "qwe");
            //connection newCon1 = connectionDAO.retrieveConnection(13);
            //userDAO.saveMainclient(ttt);
            //peopleDAO.savePeople(testPeople);
            //constructionDAO.saveConstruction(testConstruction);
            //elementOfGraphDAO.saveElementOfGraph(elementOfGraph);
            People test115 = peopleDAO.retrievePeople(7);
            vertexConnectionDAO.saveVertexConnection(vertexConnection);
            vertexConnectionDAO.saveVertexConnection(vertexConnection1);
            VertexConnection t1 = vertexConnectionDAO.retrieveVertexConnection(12);
            VertexConnection t2 = vertexConnectionDAO.retrieveVertexConnection(13);
            //DirectedGraph test123 = directedGraphDAO.retrieveDirectedGraph();
            directedGraphDAO.deleteDirectedGraph(test123);
            vertexConnectionDAO.deleteVertexConnection(t1);
            vertexConnectionDAO.deleteVertexConnection(t2);
            System.out.print("1");

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.print("1");
        Mainclient test151 = new Mainclient(0, 6, 8);
        Connection testC111 = new Connection((byte) 150, 14);
        //test200.addVertex(new ElementOfGraph(test151, testC111));
//        Dijkstra temp111 = new Dijkstra(test200, test10, test151);
//        int[] path1 = temp111.pathFromTo();
        System.out.println("1");
        HibernateUtil.closeSession();
    }
}
