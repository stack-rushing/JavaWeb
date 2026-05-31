package com.example.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        
        // 获取当前的 Session，如果没有也不创建
        HttpSession session = request.getSession(false);
        
        if (session != null) {
            // 🔥 核心代码：将当前的 Session 对象立刻宣布“作废”并从服务器内存中抹除
            session.invalidate();
            response.getWriter().write("<h3>安全退出成功！您的 Session 档案已被服务器强制销毁。</h3>");
        } else {
            response.getWriter().write("<h3>您当前本来就没有活跃的会话。</h3>");
        }
        
        response.getWriter().write("<a href='getSession'>去验证 Session 是否真的没有了</a>");
    }
}