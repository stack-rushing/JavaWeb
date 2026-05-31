import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.concurrent.atomic.AtomicInteger;

public class AppContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext application = sce.getServletContext();
        application.setAttribute("visitCount", new AtomicInteger(0));
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Application 已销毁");
    }
}