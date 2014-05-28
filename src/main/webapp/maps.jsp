<%--
  Created by IntelliJ IDEA.
  User: Павел
  Date: 27.05.2014
  Time: 22:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Maps</title>
    <script src="http://api-maps.yandex.ru/2.1/?lang=ru_RU" type="text/javascript"></script>
    <script src="/js/maps.js"></script>
    <script src="/js/jquery-1.11.1.min.js"></script>
    <link rel="stylesheet" href="css/style.css">
    <script>
        var isUpdate;
        function runUpdate() {
            isUpdate = setInterval(loadMap, 10000);
        }
        function stopUpdate() {
            clearInterval(isUpdate);
        }
    </script>
</head>
<body>
<a href="/main.jsp">Return to main page</a>

<div id="map" style="width: 90%; height: 90%; position:absolute; left:5%; right:5%; top:10%;">
    <input type="button" value="Run auto update map" onclick="runUpdate()"/>
    <input type="button" value="Stop auto update map" onclick="stopUpdate()"/>
</div>
</body>
</html>
