package com.example.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

// 使用注解配置 Servlet 的访问路径
@WebServlet("/setSession")
public class SetSessionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // 1. 获取当前请求的 Session 对象。如果当前没有 Session，则服务器会自动创建一个新的 Session
        HttpSession session = request.getRequestURI() != null ? request.getSession() : null;
        
        // 2. 向 Session 域对象中存入数据（键值对形式：Key 为字符串，Value 可以是任意 Object 对象）
        session.setAttribute("username", "张三");
        session.setAttribute("role", "管理员");
        
        // 3. 设置响应的内容类型和编码格式，防止浏览器显示乱码
        response.setContentType("text/html;charset=UTF-8");
        
        // 4. 向浏览器输出提示信息
        response.getWriter().write("<h3>Session 数据已成功存入！</h3>");
        response.getWriter().write("<a href='getSession'>点击去另一个页面读取 Session</a>");
    }
}