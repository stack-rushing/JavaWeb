<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.entity.Person" %>
<html>
<head>
    <title>人员列表</title>
</head>
<body>
    <h3>Person 人员信息列表</h3>
    <table border="1" cellpadding="5">
        <tr>
            <td>编号</td>
            <td>姓名</td>
            <td>年龄</td>
            <td>性别</td>
            <td>手机号</td>
        </tr>
        <%
            List<Person> list = (List<Person>) request.getAttribute("personList");
            for(Person p : list){
        %>
        <tr>
            <td><%=p.getId()%></td>
            <td><%=p.getName()%></td>
            <td><%=p.getAge()%></td>
            <td><%=p.getGender()%></td>
            <td><%=p.getPhone()%></td>
        </tr>
        <%}%>
    </table>
    <br>
    <a href="index.jsp">返回录入页面</a>
</body>
</html>
