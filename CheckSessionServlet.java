@WebServlet("/checkSession")
public class CheckSessionServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession(false); // 不自动创建
        
        if (session != null && session.getAttribute("test_data") != null) {
            response.getWriter().write("验证成功！Session 数据依然存在：" + session.getAttribute("test_data"));
        } else {
            response.getWriter().write("验证结果：Session 已超时被服务器销毁！当前时间戳：" + System.currentTimeMillis() / 1000);
        }
    }
}