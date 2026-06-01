<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Java Web 内置对象交互演示</title>
    <style>
        body { font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; background-color: #f4f6f9; margin: 0; padding: 20px; color: #333; }
        .container { max-width: 850px; margin: 0 auto; background: #fff; padding: 30px; border-radius: 12px; box-shadow: 0 4px 15px rgba(0,0,0,0.05); }
        h1 { text-align: center; color: #2c3e50; margin-bottom: 30px; }
        .card { border-left: 5px solid #3498db; background: #f8fafc; padding: 18px; margin-bottom: 20px; border-radius: 0 8px 8px 0; }
        .card h3 { margin-top: 0; color: #2c3e50; font-size: 1.2rem; }
        .highlight { background-color: #ffeaa7; padding: 2px 6px; border-radius: 4px; font-weight: bold; color: #d63031; }
        .btn { display: inline-block; background: #3498db; color: #fff; padding: 8px 16px; text-decoration: none; border-radius: 4px; margin-top: 10px; }
        .btn:hover { background: #2980b9; }
        code { background: #e1e8ed; padding: 2px 6px; border-radius: 4px; font-family: monospace; }
    </style>
</head>
<body>

<div class="container">
    <h1>Java Web 五大内置对象现场演示</h1>

    <%
        // 【后端逻辑处理】

        // 1. 处理 Session 计数器（针对单个浏览器会话）
        Integer sessionCount = (Integer) session.getAttribute("mySessionCount");
        if (sessionCount == null) {
            sessionCount = 1;
        } else {
            sessionCount++;
        }
        session.setAttribute("mySessionCount", sessionCount);

        // 2. 处理 Application 计数器（针对整个Web应用，所有效用户共享）
        Integer appCount = (Integer) application.getAttribute("myAppCount");
        if (appCount == null) {
            appCount = 1;
        } else {
            appCount++;
        }
        application.setAttribute("myAppCount", appCount);
    %>

    <div class="card" style="border-left-color: #3498db;">
        <h3>1. request (请求对象)</h3>
        <p>当浏览器向服务器发送请求时，所有客户端的信息都被封装在这里：</p>
        <ul>
            <li><strong>访问方法 (Method):</strong> <code><%= request.getMethod() %></code></li>
            <li><strong>当前请求的URL:</strong> <code><%= request.getRequestURL() %></code></li>
            <li><strong>你的客户端IP地址:</strong> <code><%= request.getRemoteAddr() %></code></li>
            <li><strong>你的浏览器UA头信息:</strong> <small><%= request.getHeader("User-Agent") %></small></li>
        </ul>
    </div>

    <div class="card" style="border-left-color: #e74c3c;">
        <h3>2. response (响应对象)</h3>
        <p>用于控制服务器返回给客户端的数据和状态：</p>
        <ul>
            <li><strong>当前响应的字符编码:</strong> <code><%= response.getCharacterEncoding() %></code></li>
            <li><strong>当前响应的内容类型:</strong> <code><%= response.getContentType() %></code></li>
        </ul>
        <blockquote>
            <strong>动态测试：</strong> 点击下方按钮可以通过 <code>response</code> 对象传参，模拟带参数请求。<br>
            <a href="?testParam=HelloJavaWeb" class="btn">触发带有参数的 Request</a>
            <%
                String param = request.getParameter("testParam");
                if(param != null) {
                    out.print("<p>检测到您刚才点击了按钮！抓取到 request 参数 <code>testParam</code> = <span class='highlight'>" + param + "</span></p>");
                }
            %>
        </blockquote>
    </div>

    <div class="card" style="border-left-color: #2ecc71;">
        <h3>3. out (输出对象)</h3>
        <p>用来直接向HTML页面输出文本流。下面这行绿色的字就是用 <code>out.print()</code> 吐出来的：</p>
        <p style="color: #2ecc71; font-weight: bold; padding-left: 20px;">
            <%
                out.print("【out 提示】: 嗨！我是服务器直接用 out 流生成的。当前服务器系统时间戳是：" + System.currentTimeMillis());
            %>
        </p>
    </div>

    <div class="card" style="border-left-color: #9b59b6;">
        <h3>4. session (会话对象)</h3>
        <p>用来跟踪<strong>同一个用户（同一个浏览器窗口）</strong>的状态。只要不换浏览器或不关闭隐私模式，这个值就会累加：</p>
        <ul>
            <li><strong>你的专属 Session ID:</strong> <code><%= session.getId() %></code></li>
            <li><strong>你在当前会话中刷新此页面的次数:</strong> <span class="highlight"><%= sessionCount %></span> 次</li>
        </ul>
        <p><small>💡 <i>你可以打开另一个不同的浏览器（比如从 Chrome 换到 Edge）再访问这个网址，会发现计数重新从 1 开始，且 Session ID 发生了变化。</i></small></p>
    </div>

    <div class="card" style="border-left-color: #f1c40f;">
        <h3>5. application (全局应用程序对象)</h3>
        <p>它的作用域最大，属于<strong>整个 Web 应用程序</strong>。所有用户、所有浏览器共享同一个 <code>application</code>：</p>
        <ul>
            <li><strong>当前运行的服务器信息:</strong> <code><%= application.getServerInfo() %></code></li>
            <li><strong>全服（所有浏览器）总共访问本页面的次数:</strong> <span class="highlight"><%= appCount %></span> 次</li>
        </ul>
        <p><small>💡 <i>由于这个计数保存在服务器全局内存中，无论你换几个浏览器，甚至用手机连上同一个网络访问，这个数字都会在上一次的基础上继续往上加。直到服务器重启才会归零。</i></small></p>
    </div>

</div>

</body>
</html>