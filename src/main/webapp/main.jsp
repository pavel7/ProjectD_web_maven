<%--
  Created by IntelliJ IDEA.
  User: Павел
  Date: 23.05.2014
  Time: 22:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.omsu.cherepanov.graph.DirectedGraph" %>
<%@ page import="com.omsu.cherepanov.graph.SingletonGraph" %>
<%@ page import="java.util.ArrayList" %>

<html>
<head>
    <title>Main</title>
    <script src="http://api-maps.yandex.ru/2.1/?lang=ru_RU" type="text/javascript"></script>
    <script src="/js/maps.js"></script>
    <script src="/js/jquery-1.11.1.min.js"></script>
    <link rel="stylesheet" href="css/style.css">
    <script>
        ymaps.ready(init);
        ymaps.ready(loadMap);

        function loadMap() {
            removeAllCollection()
            <% DirectedGraph directedGraph = SingletonGraph.getInstance();
            ArrayList<Integer> path = (ArrayList<Integer>) session.getAttribute("path");
            boolean notEmptyPath = false;
            int currentPos=0;
            int endPos=0;
            if( path!=null )
            {
                notEmptyPath=true;
                endPos=path.size();
            }
               for(int i = 0;i < directedGraph.getAmountOfVertex();i++)
               {
               if(!directedGraph.getConnectionOfVertex().get(i).getVertexConnection().isEmpty()){
                %>addLabelVertex(<%= directedGraph.getConnectionOfVertex().get(i).getVertexConnection().get(0).getVertex().getPointX()%>,
                    <%= directedGraph.getConnectionOfVertex().get(i).getVertexConnection().get(0).getVertex().getPointY()%>,
                    <%=i%>);
            <%              boolean isPath = false;
                            if(notEmptyPath)
                            {
                                if(path.get(currentPos) == i)
                                    {
                                        isPath=true;
                                        currentPos++;
                                    }
                                    if(currentPos == endPos)
                                    {
                                        notEmptyPath=false;
                                    }
                            }
                            int edgeLenght = directedGraph.getConnectionOfVertex().get(i).getVertexConnection().size();
                            for(int j=0;j<edgeLenght;j++)
                            {
                            if((isPath)&&(notEmptyPath)&&(path.get(currentPos) == directedGraph.indexOfElementGraph(directedGraph.getConnectionOfVertex().get(i).getVertexConnection().get(j).getVertex())))
                            {
                            %>addPathEdge(<%= directedGraph.getConnectionOfVertex().get(i).getVertexConnection().get(0).getVertex().getPointX()%>,
                    <%= directedGraph.getConnectionOfVertex().get(i).getVertexConnection().get(0).getVertex().getPointY()%>,
                    <%= directedGraph.getConnectionOfVertex().get(i).getVertexConnection().get(j).getVertex().getPointX()%>,
                    <%= directedGraph.getConnectionOfVertex().get(i).getVertexConnection().get(j).getVertex().getPointY()%>,
                    <%= directedGraph.getConnectionOfVertex().get(i).getVertexConnection().get(j).getEdge().getDefence()%>);
            <%
                            } else{
                                %>addEdge(<%= directedGraph.getConnectionOfVertex().get(i).getVertexConnection().get(0).getVertex().getPointX()%>,
                    <%= directedGraph.getConnectionOfVertex().get(i).getVertexConnection().get(0).getVertex().getPointY()%>,
                    <%= directedGraph.getConnectionOfVertex().get(i).getVertexConnection().get(j).getVertex().getPointX()%>,
                    <%= directedGraph.getConnectionOfVertex().get(i).getVertexConnection().get(j).getVertex().getPointY()%>,
                    <%= directedGraph.getConnectionOfVertex().get(i).getVertexConnection().get(j).getEdge().getDefence()%>);
            <%
            }
                            }
                            }

               }
               %>addAllCollection();
        }
        ;
    </script>
</head>
<body>
<div class="logout">
    <a href="/logoutservlet">Logout</a>
</div>

<input type="button" value="Удалить все коллекци" onclick="removeAllCollection()"/>
<input type="button" value="Показать все коллекци" onclick="loadMap()"/>
<table cols="3">
    <td class="col1">
        <div id="graph">
            <form action="path" method="post">
                <b>Calculation most secure method of transmitting
                    information</b><br>
                From:<br>
                <select name="listboxFrom" id="listboxFrom">
                    <%for (int i = 0; i < directedGraph.getAmountOfVertex(); i++) {%>
                    <option value=<%=i%>>From vertex №<%=i%>
                    </option>
                    <%}%>
                </select><br>
                To:<br>
                <select name="listboxTo" id="listboxTo">
                    <%for (int i = 0; i < directedGraph.getAmountOfVertex(); i++) {%>
                    <option value=<%=i%>>To vertex №<%=i%>
                    </option>
                    <%}%>
                </select>
                <br>
                <input type="submit" value="Calculate path">
            </form>
            <form action="path" method="post">
                <input type="hidden" name="emptyPath" id="emptyPath" value="makeEmpty">
                <input type="submit" value="Remove path">
            </form>
        </div>
    </td>
    <td class="col2">
        <div id="map" style="height: 40%; width: 40%; position: absolute">
            <form method="get" action="updategraph">
                <input class="button" type="submit" value="Update map info"/>
            </form>
            <a href="/maps.jsp">Open page with map</a>
        </div>
    </td>

    <td class="col3"></td>
</table>
</body>
<script>
    <%
        String msg=(String) session.getAttribute("error");
        if(msg != null )
        {
            %>alert('<%=msg%>')<%
        session.removeAttribute("error");
    }
%></script>
</html>
