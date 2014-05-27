<%--
  Created by IntelliJ IDEA.
  User: Павел
  Date: 27.05.2014
  Time: 22:45
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

        function loadMap() {
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
        }
        ;
        setInterval(function () {
            $.ajax(
                    {
                        url: "/updategraph",
                        data: { },
                        success: function () {
                            location.reload();
                        }

                    });
        }, 15000);
    </script>
</head>
<body>
<a href="/main.jsp">Return to main page</a>

<div id="map" style="width: 90%; height: 90%; position:absolute; left:5%; right:5%; top:10%;">
</div>
</body>
</html>
