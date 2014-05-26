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
<html>
<head>
    <title>Maps</title>
    <script src="http://api-maps.yandex.ru/2.1/?lang=ru_RU" type="text/javascript"></script>
    <script src="/js/maps.js"></script>
    <script src="/js/jquery-1.11.1.min.js"></script>
    <link rel="stylesheet" href="css/style.css">
    <script>
        ymaps.ready(init);
        ymaps.ready(loadMap);

        function loadMap(){
        removeAllCollection()
        <% DirectedGraph directedGraph = SingletonGraph.getInstance();
           for(int i = 0;i < directedGraph.getAmountOfVertex();i++)
           {
           if(!directedGraph.getConnectionOfVertex().get(i).getVertexConnection().isEmpty()){
            %>addLabelVertex(<%= directedGraph.getConnectionOfVertex().get(i).getVertexConnection().get(0).getVertex().getPointX()%>,
                        <%= directedGraph.getConnectionOfVertex().get(i).getVertexConnection().get(0).getVertex().getPointY()%>,
                        <%=i%>);
        <%
                        int edgeLenght = directedGraph.getConnectionOfVertex().get(i).getVertexConnection().size();

                        for(int j=0;j<edgeLenght;j++)
                        {
                            %>addEdge(<%= directedGraph.getConnectionOfVertex().get(i).getVertexConnection().get(0).getVertex().getPointX()%>,
                        <%= directedGraph.getConnectionOfVertex().get(i).getVertexConnection().get(0).getVertex().getPointY()%>,
                        <%= directedGraph.getConnectionOfVertex().get(i).getVertexConnection().get(j).getVertex().getPointX()%>,
                        <%= directedGraph.getConnectionOfVertex().get(i).getVertexConnection().get(j).getVertex().getPointY()%>,
                        <%= directedGraph.getConnectionOfVertex().get(i).getVertexConnection().get(j).getEdge().getDefence()%>);
        <%
                        }
                        }

           }
           %>addAllCollection();
        };

            setInterval(function() {$.ajax(
                    {
                        url: "/updategraph",
                        data:      { },
                        success: function() {
                            directedGraph = SingletonGraph.getInstance();
                            loadMap();},
                        error: function(data, stat, th)
                        {
                            if (data.status == 403)
                            {
                                alert("Your session is expired. Please relogin.");
                                document.location.reload();
                            }
                            else
                                alert('Error!\n' + data.responseText + '\n' + stat + '\n' + th);
                        }
                    });
        }, 15000);
    </script>
</head>
<body>
<div class="logout">
    <a href="/logoutservlet">Logout</a>
</div>
<div id="map" style="width: 40%; height: 40%; position:absolute; left:30%; top:30%;">
    <form method="get" action="updategraph">
        <input class="button" type="submit" value="Update map info"/>
    </form>
</div>
<input type="button" value="Удалить все коллекци" onclick="removeAllCollection()"/>
<input type="button" value="Показать все коллекци" onclick="loadMap()"/>
</body>
</html>
