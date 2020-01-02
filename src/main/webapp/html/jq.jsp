<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="app" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <%-- bootstrap的核心css --%>
    <link rel="stylesheet" href="${app}/boot/css/bootstrap.min.css">
    <%-- jqGrid样式 --%>
    <%-- jqGrid --%>
    <link rel="stylesheet" href="${app}/jqGrid/css/trirand/ui.jqgrid-bootstrap.css">
    <%-- jquery.js --%>
    <script src="${app}/boot/js/jquery-3.3.1.min.js"></script>
    <%-- bootstrap.js --%>
    <script src="${app}/boot/js/bootstrap.min.js"></script>
    <%-- 国际化 --%>
    <script src="${app}/jqGrid/js/trirand/i18n/grid.locale-cn.js"></script>
    <%-- jqGrid核心js --%>
    <script src="${app}/jqGrid/js/trirand/jquery.jqGrid.min.js"></script>
    <script>
        $(function () {
            $("#list").jqGrid({
                url: "${app}/users/queryByPage",
                editurl: '${app}/users/edit',
                styleUI: "Bootstrap",
                datatype: "json",
                autowidth: true,
                pager: "#pager",
                viewrecords: true,
                caption: "我的表",
                toolbar: [true, "top"],
                rowNum: 3,
                rowList: [3, 8, 10],
                colNames: [
                    "ID", "姓名", "密码", "生日", "电话号", "状态"
                ],
                colModel: [
                    {name: "id"},
                    {name: "username", editable: true,required:true},
                    {name: "password"},
                    {name: "birthday", editable: true, edittype: 'date'},
                    {name: "phone", edittype: 'number'},
                    {
                        name: "status", editable: true, edittype: "select", editoptions: {
                            value: "激活:激活;未激活:未激活"
                        }
                    }
                ],
            }).jqGrid("navGrid", "#pager", {})
        })
    </script>
</head>
<body>
       <table id="list"></table>
<div id="pager" style="height: 50px"></div>
</body>
</html>
