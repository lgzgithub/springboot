<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page pageEncoding="UTF-8" contentType="text/html; UTF-8" isELIgnored="false"%>
<c:set var="app" value="${pageContext.request.contextPath}"/>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <%-- bootstrap的核心css --%>
    <link rel="stylesheet" href="${app}/boot/css/bootstrap.min.css">
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
                url:"${app}/admin/queryByPager",
                editurl:"${app}/admin/edit",
                styleUI:"Bootstrap",
                datatype:"json",
                autowidth:true,
                pager:"#pager",
                viewrecords:true,
                caption:"我的表",
                toolbar:[true,"top"],
                rowNum:3,
                rowList:[3,8,10],
                colNames:[
                    "Id","名字","年龄","生日","部门"
                ],
                colModel:[
                    {name:"id"},
                    {name:"username",editable:true},
                    {name:"age",edittype:'number',editable:true,editrules:{
                            number:true,minValue:10,maxValue:100,required:true
                        }
                    },
                    {name:"bir",editable:true,edittype:'date'},
                    {name:"dep",editable:true,edittype:"select",editoptions:{
                            value:"演员:演员;辅助演员:辅助演员"
                        }
                    }
                ],
                ondblClickRow:function (rowid,iRow,iCol,e) {
                    console.log(rowid);
                    console.log(iRow);
                    console.log(iCol);
                    console.log(e);
                }
            }).jqGrid("navGrid","#pager",{})


            $("#bedata").click(function() {
                var gr = jQuery("#list").jqGrid('getGridParam', 'selrow');
                if (gr != null)
                    jQuery("#list").jqGrid('delGridRow', gr, {
                        reloadAfterSubmit : false
                    });
                else
                    alert("Please Select Row to delete!");
            });

            $("#del").click(function () {
                $("#list").jqGrid('delRowData',1);
            })

        })
    </script>
</head>
<body>
    <table id="list"></table>
<div id="pager" style="height: 50px"></div>
<button class="btn btn-primary" id="bedata">编辑</button>
<button class="btn btn-primary" id="del">删除</button>
</body>
</html>
