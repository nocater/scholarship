package com.scholarship.webapp.listener;

import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.scholarship.module.account.OnlineUser;
import com.scholarship.module.conf.AppConfig;


public class SingleContextListener implements ServletContextListener { 
    
    private transient Log log = LogFactory.getLog(getClass());
     
    public void contextInitialized(ServletContextEvent event) {
    	AppConfig.ctx = event.getServletContext().getRealPath("/");
    	ServletContext context = event.getServletContext();
        context.setAttribute("ONLINE_ACCOUNTLIST", new ArrayList<OnlineUser>());
    }
    
    //Notification that the servlet context is about to be shut down
    public void contextDestroyed(ServletContextEvent event) {
    	ServletContext context = event.getServletContext();
        context.removeAttribute("ONLINE_ACCOUNTLIST");
    }
}
