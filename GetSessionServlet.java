package com.example.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/getSession")
public class GetSessionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // 1. 设置响应编码格式
        response.setContentType("text/html;charset=UTF-8");
        
        // 2. 获取 Session 对象。传入 false 表示：如果服务器没有找到对应的 Session，直接返回 null，不创建新 Session
        HttpSession session = request.getSession(false);
        
        if (session != null) {
            // 3. 从 Session 中根据 Key 取出之前存入的数据（注意：取出来的是 Object 类型，需要向下转型）
            String username = (String) session.getAttribute("username");
            String role = (String) session.getAttribute("role");
            
            // 4. 将读取到的数据打印到网页上
            response.getWriter().write("<h3>成功从 Session 中读取到数据：</h3>");
            response.getWriter().write("用户名称：" + username + "<br/>");
            response.getWriter().write("用户角色：" + role + "<br/>");
            response.getWriter().write("当前 Session 的唯一标识 ID 是：" + session.getId());
        } else {
            // 如果 session 为 null，说明会话已失效或者从来没有创建过
            response.getWriter().write("<h3>会话已失效，未找到对应的 Session 档案！</h3>");
        }
    }
}