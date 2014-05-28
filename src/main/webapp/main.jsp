<%--
  Created by IntelliJ IDEA.
  User: Павел
  Date: 23.05.2014
  Time: 22:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Main</title>
    <script src="http://api-maps.yandex.ru/2.1/?lang=ru_RU" type="text/javascript"></script>
    <script src="/js/maps.js"></script>
    <script src="/js/jquery-1.11.1.min.js"></script>
    <link rel="stylesheet" href="css/style.css">
    <script>
        $(function () {
            $("#calculationPath").submit(function (e) {
                e.preventDefault();

                dataString = $("#calculationPath").serialize();

                $.ajax({
                    type: "POST",
                    url: "/path",
                    data: dataString,
                    dataType: "json",
                    success: function (data) {
                        removeAllCollection();
                        var VertexAmount = data.amountOfVertex;
                        amountOfVertcies = data.amountOfVertex;
                        for (var i = 0; i < VertexAmount; i++) {
                            addLabelVertex(data.Vertices[i].PointX, data.Vertices[i].PointY, data.Vertices[i].id);
                            var ConnectionAmount = data.Vertices[i].ConnectionSize;
                            for (var j = 0; j < ConnectionAmount; j++) {
                                var isPart = data.Vertices[i].Connection[j].isPath;
                                var connectionWithId = data.Vertices[i].Connection[j].ConnectionWithVertexId;
                                if (isPart) {
                                    addPathEdge(data.Vertices[i].PointX, data.Vertices[i].PointY,
                                            data.Vertices[connectionWithId].PointX, data.Vertices[connectionWithId].PointY, data.Vertices[i].Connection[j].Defence);
                                }
                                else {
                                    addEdge(data.Vertices[i].PointX, data.Vertices[i].PointY,
                                            data.Vertices[connectionWithId].PointX, data.Vertices[connectionWithId].PointY, data.Vertices[i].Connection[j].Defence);
                                }
                            }
                        }
                        addAllCollection();
                        myMap.setBounds(myVertexGeoObjects.getBounds());
                    }

                });

            });
        });

    </script>
</head>
<body>
<div class="logout">
    <a href="/logoutservlet">Logout</a>
</div>

<table cols="3">
    <td class="col1">
        <div id="graph">
            <form id="calculationPath" name="calculationPath" action="path" method="post">
                <b>Calculation most secure method of transmitting
                    information</b><br>
                From:<br>
                <input type="text" name="listboxFrom" id="listboxFrom">
                </input><br>
                To:<br>
                <input type="text" name="listboxTo" id="listboxTo">
                </input>
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
            <input type="button" value="Update Graph" onclick="loadMap()"/>
            <a href="/maps.jsp">Open page with online update map</a>
        </div>
    </td>

    <td class="col3">
    </td>
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
