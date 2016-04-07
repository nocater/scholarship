package com.scholarship.webapp.listener;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.scholarship.module.account.Account;
import com.scholarship.module.account.OnlineUser;


public class SingleSessionListener implements HttpSessionListener  { 
    
    private transient Log log = LogFactory.getLog(getClass());
  
      
    //Notification that a session was created
    public void sessionCreated(HttpSessionEvent event) {
    	
    	return;
    }
    	  
    //Notification that a session was invalidated
    public void sessionDestroyed(HttpSessionEvent event) {
    	// 用户离线触发
    	Account account = (Account)event.getSession().getAttribute("LOGON_ACCOUNT");
    	if(account != null) {
	    	List<OnlineUser> userList = (List<OnlineUser>)event.getSession().getServletContext().getAttribute("ONLINE_ACCOUNTLIST");
	    	
	    	// 遍历在线用户列表，将该离线用户从列表中移除
	    	List<OnlineUser> singleUserList = new ArrayList<OnlineUser>(); 
	    	for(OnlineUser user : userList) {
	    		if(user.getUserId() == account.getId() && user.getSessionId().equals(event.getSession().getId())) {
	    			userList.remove(user);
	    			break;
	    		}
	    	}
    	}
    	return;  
    }
    
}
