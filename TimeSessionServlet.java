package com.example.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/timeoutSession")
public class TimeoutSessionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        
        // 向 Session 中存入测试数据
        session.setAttribute("test_data", "这是一条限时存在的数据");
        
        // 🔥 核心代码：手动设置当前 Session 的最大不活动间隔时间，单位为秒（这里设置为 10 秒）
        // 这意味着：如果用户连续 10 秒钟没有向服务器发送任何请求，这个 Session 将被彻底销毁
        session.setMaxInactiveInterval(10);
        
        response.getWriter().write("<h3>Session 已创建，并设置超时时间为 10 秒！</h3>");
        response.getWriter().write("当前时间戳：" + System.currentTimeMillis() / 1000 + "<br/>");
        response.getWriter().write("<a href='checkSession'>请在 10 秒内或 10 秒后点击此链接验证</a>");
    }
}