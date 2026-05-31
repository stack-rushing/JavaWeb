import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/applicationDemo")
public class ApplicationDemoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        // 1. 获取 Application 对象
        ServletContext application = request.getServletContext();

        // 2. 环境信息获取
        String contextPath = application.getContextPath();
        String realPath = application.getRealPath("/");
        String initParam = application.getInitParameter("appVersion");

        // 3. 全局数据存储
        application.setAttribute("systemName", "JavaWeb教学系统");

        // 4. 输出结果
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.println("应用上下文路径：" + contextPath + "<br>");
        out.println("服务器真实路径：" + realPath + "<br>");
        out.println("全局初始化参数：" + initParam + "<br>");
        out.println("系统名称：" + application.getAttribute("systemName"));
    }
}