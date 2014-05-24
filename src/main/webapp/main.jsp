<%--
  Created by IntelliJ IDEA.
  User: Павел
  Date: 23.05.2014
  Time: 22:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.omsu.cherepanov.graph.SingletonGraph" %>
<%@ page import="com.omsu.cherepanov.graph.DirectedGraph" %>
<html>
<head>
    <title>Maps</title>
    <script src="http://api-maps.yandex.ru/2.1/?lang=ru_RU" type="text/javascript"></script>
    <script src="/js/maps.js"></script>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<div class="logout">
    <a href="/logoutservlet">Logout</a>
</div>
<div id="map" style="width: 40%; height: 40%; position:absolute; left:30%; top:30%;"></div>
<script>
<% DirectedGraph directedGraph = SingletonGraph.getInstance();
   for(int i = 0;i < directedGraph.getAmountOfVertex();i++)
   {
    %>ymaps.ready(addVertex(<%= directedGraph.getConnectionOfVertex().get(i).getVertexConnection().get(0).getVertex().getPointX()%>,
                <%= directedGraph.getConnectionOfVertex().get(i).getVertexConnection().get(0).getVertex().getPointY()%>));<%
   }
%>
</script>
</body>
</html>
