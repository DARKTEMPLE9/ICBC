package net.northking.iacmp.ams.web.utils.spring.sysloader;


import net.northking.iacmp.ams.web.utils.spring.AppContext;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletException;

public class InitListener implements ServletContextListener {
    public void init(ServletConfig config) throws ServletException {
        ApplicationContext ac = WebApplicationContextUtils
                .getWebApplicationContext(config.getServletContext());

        AppContext.getInstance().setApplicationContext(ac);
    }


    public void contextInitialized(ServletContextEvent arg0) {
        ApplicationContext ac = WebApplicationContextUtils
                .getWebApplicationContext(arg0.getServletContext());
        AppContext.getInstance().setApplicationContext(ac);
    }


    @Override
    public void contextDestroyed(ServletContextEvent arg0) {
        //do nothing
    }
}