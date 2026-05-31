<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>人员信息录入</title>
</head>
<body>
    <h3>Person 人员信息录入</h3>
    <form action="person" method="post">
        姓名：<input type="text" name="name"><br><br>
        年龄：<input type="number" name="age"><br><br>
        性别：<input type="text" name="gender"><br><br>
        手机号：<input type="text" name="phone"><br><br>
        <input type="submit" value="提交保存">
    </form>
</body>
</html>
