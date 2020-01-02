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
                url:"${app}/users/queryByPage",
                editurl:"${app}/users/edit",
                styleUI:"Bootstrap",
                datatype:"json",
                autowidth:true,
                pager:"#pager",
                viewrecords:true,
                caption:"我的表格",
                multiselect:true, //是否多选
                toolbar:[true,"top"],
                rowNum:3,
                rowList:[3,8,10],
                colNames:[
                    "ID", "姓名", "密码", "生日", "电话号", "状态"
                ],
                colModel: [
                    {name: "id"},
                    {name: "name", editable: true,editrules:{
                            required:true
                        }
                    },
                    {name: "password",editable: true, edittype:'password',editrules:{
                            required:true
                        }
                    },
                    {name: "birthday", editable: true, edittype: 'date'},
                    {name: "phone", editable: true,edittype:'number',editrules:{
                            required:true
                        }
                    },
                    {
                        name: "status", editable: true, edittype: "select", editoptions: {
                            value: "激活:激活;未激活:未激活"
                        }
                    }
                ],
                ondblClickRow:function (rowid   ,iRow,iCol,e) {
                    console.log(rowid);
                    console.log(iRow);
                    console.log(iCol);
                    console.log(e);
                }
            }).jqGrid("navGrid","#pager",{})

            $("#bedata").click(function() {
                var gr = $("#list").jqGrid('getGridParam', 'selrow');
                if (gr != null)
                    $("#list").jqGrid('delGridRow', gr, {
                        reloadAfterSubmit : false
                    });
                else
                    alert("Please Select Row to delete!");
            });
            $("#del").click(function () {
                $("#list").jqGrid('delRowData',1);
            })

        })
        $("#es").click(function () {
                var text = $("#username1").val();
                $.ajax({
                    url:"${app}/users/elasticsearch",
                    type:"POST",
                    data:"es="+text,
                    success:function (data) {
                        console.log(data);
                        $("#ta").empty();
                        for (var i = 0; i < data.length; i++) {
                            $("#ta").append("<p>" + data[0].id + "</p>")
                                .append("<p>" + data[0].name + "</p>")
                                .append("<p>" + data[0].password + "</p>")
                                .append("<p>" + data[0].phone + "</p>")
                        }
                    },
                    datatype:"json"
                })
            }
        )


        $('#exampleModal').on('show.bs.modal', function (event) {
            var button = $(event.relatedTarget)
            var recipient = button.data('whatever')
            var modal = $(this)
            modal.find('.modal-title').text('New message to ' + recipient)
            modal.find('.modal-body input').val(recipient)
        })
    </script>
</head>
<body>
<div class="col-sm-10">
    <div class="page-header">
        <h3 id="myH3">用户管理</h3>
    </div>
    <ul class="nav nav-tabs">
        <li class="active">
            <a href="#showUser" data-toggle="tab">用户列表</a>
        </li>
        <li>
            <a href="#" data-toggle="modal" data-target="#addModal">用户添加</a>
        </li>
    </ul>
    <div class="tab-content">
        <div class="tab-pane active" id="showUser">
            <div class="panel panel-default">
                <%--
                    heading搜索
                --%>
                <div class="panel-heading text-center">
                    <form id="selectByLikeForm" class="form-inline">
                        <div class="form-group">
                            <label for="username1">姓名</label>
                            <input type="text" name="name" placeholder="输入姓名" class="form-control" id="username1">
                        </div>
                        <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal" data-whatever="@mdo" id="es">查询</button>
                    </form>
                </div>
                    <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
                        <div class="modal-dialog" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                    <h4 class="modal-title" id="exampleModalLabel">全文检索</h4>
                                </div>
                                <div class="modal-body">
                                    <form>
                                        <table id="ta">
                                            <tr>
                                                <td></td>
                                            </tr>
                                        </table>
                                    </form>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                    <button type="button" class="btn btn-primary">Send message</button>
                                </div>
                            </div>
                        </div>
                    </div>
  <div class="col-sm-">
      <table id="list"></table>
  </div>
<div id="pager" style="height: 50px"></div>
<button class="btn btn-primary" id="bedata">编辑</button>
<button class="btn btn-primary" id="del">删除</button>
</body>
</html>
