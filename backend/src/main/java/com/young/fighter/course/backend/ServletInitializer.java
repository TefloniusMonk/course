package com.young.fighter.course.backend;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class ServletInitializer implements WebApplicationInitializer {

    public void onStartup(ServletContext container) throws ServletException {
        XmlWebApplicationContext ctx = new XmlWebApplicationContext();
        ctx.setConfigLocations("classpath:/hatefulXml.xml");
        ctx.setServletContext(container);
        ServletRegistration.Dynamic servlet = container.addServlet("dispatcher", new DispatcherServlet(ctx));
        servlet.setLoadOnStartup(1);
        servlet.addMapping("/*");
        System.out.println("UP!!!!!!!!!!!");
    }
}