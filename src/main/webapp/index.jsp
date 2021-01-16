<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
String path="http://"+request.getServerName()+":"+request.getServerPort()+"/";
%>
<html>
<head>
    <title>数据上传平台-登录</title>
    <link rel="stylesheet" href="css/index.css">
    <script src="js/jquery-3.5.1.min.js"></script>
    <script src="js/security.js"></script>
    <script src="js/login.js"></script>
</head>
<body>
    <div class="mainbox">
        <form class="loginform">
            <h5>默认用户名/密码-admin</h5>
            <input type="text" id="uid" placeholder="账号"/>
            <input type="password" id="password" placeholder="密码">
            <input onclick="login()" type="button" id="button" value="登入"/>
        </form>
    </div>
    <div id="toast" class="toast">登陆获签中***</div>
</body>
</html>
