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
                        if(data.probability) {
                            alert("probability of message delivery=" + data.probability*100 +"%");
                        }
                        if (data.error)
                        {
                            alert(data.error);
                            loadMap();
                        }
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
    <tr>
        <td>
        <form id="confEnv" onsubmit="addConflictArea(corX.value, corY.value, radius.value);return false">
            corX:  <input type="text" name="corX" id="corX" value=""><br>
            corY:  <input type="text" name="corY" id="corY" value=""><br>
            radius:<input type="text" name="radius" id="radius" value=""><br>
            <input type="submit" value="Отправить">
        </form>
        </td>
    </tr>
    <tr>
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
            <form id="encryptText" name="encryptText" action="encrypttext" method="post">
                To vertex:<br>
                <input type="text" name="userTo" id="userTo">
                </input><br>

                <p><b>Enter you text:</b></p>

                <p><textarea rows="7" cols="40" name="textEncryptSource" id="textEncryptSource"></textarea></p>

                <p><input type="submit" value="Encrypt"></p>
            </form>
            <p><b>Encripted text:</b></p>

            <p><textarea rows="7" cols="45" name="textEncryptTo" id="textEncryptTo"></textarea></p>
    </tr>
    <tr>
        <td>
            <form id="EdgeRemove" name="EdgeRemove" action="removeedge" method="get">
                <b>Remove Edge</b><br>
                From Vertex:<br>
                <input type="text" name="fromVertex" id="fromVertex">
                </input><br>
                To Vertex:<br>
                <input type="text" name="toVertex" id="toVertex">
                </input>
                <br>
                <input type="submit" value="Remove Edge">
            </form>
        </td>
    </tr>
    <tr>
        <td>
            <form id="VertexRemove" name="VertexRemove" action="removevertex" method="get">
                <b>Remove Vertex</b><br>
                Vertex №:<br>
                <input type="text" name="vertex" id="vertex">
                </input><br>
                <input type="submit" value="Remove Vertex">
            </form>
        </td>
    </tr>
</table>

</body>

<script>
    $(function () {
        $("#encryptText").submit(function (t) {
            t.preventDefault();

            dataStringTwo = $("#encryptText").serialize();

            $.ajax({
                type: "POST",
                url: "/encrypttext",
                data: dataStringTwo,
                dataType: "json",
                success: function (data) {
                    var text = data.msg;
                    document.getElementById("textEncryptTo").value = text;
                }
            });
        });
    });
</script>
<script>
    $(function () {
        $("#EdgeRemove").submit(function (f) {
            f.preventDefault();

            dataStringThree = $("#EdgeRemove").serialize();

            $.ajax({
                type: "GET",
                url: "/removeedge",
                data: dataStringThree,
                dataType: "json",
                success: function (data) {
                    loadMap();
                    myMap.setBounds(myVertexGeoObjects.getBounds());
                }

            });

        });
    });
</script>
<script>
    $(function () {
        $("#VertexRemove").submit(function (f) {
            f.preventDefault();

            dataStringThree = $("#VertexRemove").serialize();

            $.ajax({
                type: "GET",
                url: "/removevertex",
                data: dataStringThree,
                dataType: "json",
                success: function (data) {
                    loadMap();
                    myMap.setBounds(myVertexGeoObjects.getBounds());
                }

            });

        });
    });
</script>
</html>
