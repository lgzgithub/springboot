<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/boot/css/bootstrap.min.css">
    <title>Title</title>
    <style>
        .p{
            color: #ffffff;
        }
        .margin-top{
            margin-top: 15px;
        }
        .o{
            border: 1px solid #f0f0f0;
        }
    </style>
    <script src="${pageContext.request.contextPath}/boot/js/jquery-3.3.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/boot/js/bootstrap.min.js"></script>
</head>
<body>
<div class="">
<nav class="navbar navbar-default navbar-inverse c">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">后台管理系统</a>
        </div>
        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse b" id="bs-example-navbar-collapse-1">
            <div class="margin-top">
            <ul class="nav navbar-nav navbar-right p">
                欢迎:小黑&nbsp;&nbsp;&nbsp;&nbsp;
                <span class="glyphicon glyphicon-hand-right" aria-hidden="true"></span>
                 退出登录
            </ul>
            </div>
        </div>
    </div>
</nav>
</div>
<div class="col-sm-2">
<div class="panel-group" id="parent">
    <!--
        用户
    -->
    <div class="panel panel-default">
        <div class="panel-heading">
            <h3 class="panel-title text-center">
                <a href="#first" data-toggle="collapse" data-parent="#parent">
                    用户管理
                </a>
            </h3>
        </div>

        <div id="first" class="panel-collapse collapse">
            <div class="panel-body">
                <button class="btn btn-danger  col-sm-12">
                    <%--
                     javascript:void(0)  阻止页面提交
                    --%>
                        <a href="javascript:void(0)" onclick="$('#myContent').load('index1.jsp')"
                           class="btn btn-primary">
                            用户列表
                        </a>
                </button>
            </div>
        </div>
    </div>

    <!--
        商品
    -->
    <div class="panel panel-default">
        <div class="panel-heading">
            <h3 class="panel-title text-center">
                <a href="#second" data-toggle="collapse" data-parent="#parent">商品管理</a>
            </h3>
        </div>

        <div class="panel-collapse collapse" id="second">
            <div class="panel-body">
                <ul class="list-group">
                    <button class="btn btn-danger center-block col-sm-12">商品列表</button>
                </ul>
            </div>
        </div>
    </div>

    <!--
        订单
    -->
    <div class="panel panel-default">
        <div class="panel-heading">
            <h3 class="panel-title text-center">
                <a href="#third" data-toggle="collapse" data-parent="#parent">
                    订单管理
                </a>
            </h3>
        </div>
        <div class="panel-collapse collapse" id="third">
            <div class="panel-body">
                <button class="btn btn-danger col-sm-12">
                    订单详情
                </button>
            </div>
        </div>
    </div>
</div>
</div>
<div class="col-sm-10" id="myContent">
    <div class="jumbotron">
        <h1>Hello, world!</h1>
        <p>...</p>
        <p><a class="btn btn-primary btn-lg" href="#" role="button">Learn more</a></p>
    </div>

    <div class="alert alert-danger alert-dismissible fade in" role="alert">
        <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">×</span></button>
        <h4>Oh snap! You got an error!</h4>
        <p>Change this and that and try again. Duis mollis, est non commodo luctus, nisi erat porttitor ligula, eget lacinia odio sem nec elit. Cras mattis consectetur purus sit amet fermentum.</p>
        <p>
            <button type="button" class="btn btn-danger">Take this action</button>
            <button type="button" class="btn btn-default">Or do this</button>
        </p>
    </div>

    <div class="panel panel-default">
        <div class="panel-heading">
            <h3 class="panel-title">
                系统状态
            </h3>
        </div>
        <div class="panel-body">
            <p>
                系统使用率
            </p>
            <div class="progress">
                <div class="progress-bar progress-bar-success progress-bar-striped" role="progressbar" aria-valuenow="40" aria-valuemin="0" aria-valuemax="100" style="width: 40%">
                    <span class="sr-only">40% Complete (success)</span>
                </div>
            </div>
            <p>
                cpu使用率
            </p>
            <div class="progress">
                <div class="progress-bar progress-bar-info progress-bar-striped" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100" style="width: 20%">
                    <span class="sr-only">20% Complete</span>
                </div>
            </div>
            <p>
                内存使用率
            </p>
            <div class="progress">
                <div class="progress-bar progress-bar-warning progress-bar-striped" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 60%">
                    <span class="sr-only">60% Complete (warning)</span>
                </div>
            </div>
            <p>
                硬盘使用率
            </p>
            <div class="progress">
                <div class="progress-bar progress-bar-danger progress-bar-striped" role="progressbar" aria-valuenow="80" aria-valuemin="0" aria-valuemax="100" style="width: 80%">
                    <span class="sr-only">80% Complete (danger)</span>
                </div>
            </div>
        </div>
    </div>
</div>



</body>
</html>