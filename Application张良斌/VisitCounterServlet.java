import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/counter")
public class CounterServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        ServletContext application = request.getServletContext();

        synchronized (application) {
            Integer count = (Integer) application.getAttribute("visitCount");
            if (count == null) {
                count = 1;
            } else {
                count++;
            }
            application.setAttribute("visitCount", count);
        }

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("当前网站访问量：" + application.getAttribute("visitCount"));
    }
}