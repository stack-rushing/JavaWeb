package com.example.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/urlRewrite")
public class UrlRewriteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        session.setAttribute("user", "老王");
        
        response.getWriter().write("<h3>URL 重写测试页面</h3>");
        
        // ❌ 普通链接：如果禁用了 Cookie，点击此链接跳转后 Session 必定丢失
        String normalUrl = "getSession";
        
        //  核心方法：调用 response.encodeURL()
        // 它的底层原理是：自动检测浏览器是否支持 Cookie。
        // 如果支持，它就返回原 URL "getSession"；如果检测到禁用了 Cookie，它会自动在 URL 后面追加 ";jsessionid=xxxx"
        String encodedUrl = response.encodeURL("getSession");
        
        response.getWriter().write("<a href='" + normalUrl + "'>1. 普通超链接（禁用 Cookie 后点击会丢失登录态）</a><br/><br/>");
        response.getWriter().write("<a href='" + encodedUrl + "'>2. 经过 URL 重写后的超链接（禁用 Cookie 后依然有效）</a>");
    }
}