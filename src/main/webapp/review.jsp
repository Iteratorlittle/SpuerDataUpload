<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="./css/mainpage.css" rel="stylesheet"/>
    <script src="js/jquery-3.5.1.min.js"></script>
    <script src="js/security.js"></script>
    <script src="js/getdata.js"></script>
</head>
<body>
<div class="NavBar">
    <ul>
        <li><a href="./mainpage.jsp">上传数据</a></li>
        <li style="background-color: #284e99;border-bottom: 2px solid #313845"><a href="./review.jsp">数据审查</a></li>
    </ul>
</div>
<div class="Pages">
    <div class="databox">
        <div id="datashower"></div>
        <div>
            <button onclick="getmoredata()">更多数据</button>
        </div>
    </div>
</div>
</body>
</html>