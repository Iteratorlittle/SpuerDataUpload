<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>数据上传页</title>
    <link href="./css/mainpage.css" rel="stylesheet"/>
</head>
<body>
    <div class="NavBar">
        <ul>
            <li style="background-color: #284e99;border-bottom: 2px solid #313845"><a href="./mainpage.jsp">上传数据</a></li>
            <li><a href="./review.jsp">数据审查</a></li>
        </ul>
    </div>
    <div class="Pages" style="height: 400px">
        <div class="from">
            <button onclick="upload('file')">开始Excel文件数据录入</button>
            <form id="forms">
                <label class="ui_button" for="xFile">上传文件</label>
                <input type="file" id="xFile" multiple="multiple" accept=".xls,.xlsx" style="position:absolute;clip:rect(0 0 0 0);" onchange="showselectfile(this.value)">
                <span id="showfilename" style="opacity: 0.6">文件为excel文件</span>
            </form>
        </div>
    </div>
    <script src="js/jquery-3.5.1.min.js"></script>
    <script src="js/upload.js"></script>
</body>
</html>