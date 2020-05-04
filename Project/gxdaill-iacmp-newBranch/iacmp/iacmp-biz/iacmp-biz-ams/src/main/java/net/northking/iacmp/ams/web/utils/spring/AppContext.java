package net.northking.iacmp.ams.web.utils.spring;

import org.springframework.context.ApplicationContext;

public class AppContext {
    private static AppContext instance = new AppContext();
    private ApplicationContext context;

    public static AppContext getInstance() {
        return instance;
    }

    public ApplicationContext getApplicationContext() {
        return this.context;
    }

    public void setApplicationContext(ApplicationContext appContext) {
        this.context = appContext;
    }

    public Object getBean(String name) {
        return this.context.getBean(name);
    }
}