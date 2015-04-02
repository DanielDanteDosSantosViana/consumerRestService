package br.com.ws.restservice.core.consumer.core.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import br.com.ws.restservice.core.consumer.core.listener.contextParameter.ApplicationConsumer;

public class ConsumerApplicationListener implements ServletContextListener{

	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("Contexto desligado...");
		
	}

	public void contextInitialized(ServletContextEvent sce) {
		  ServletContext c = sce.getServletContext();
		  if (c != null) { 
		    if (c.getInitParameter("ApplicationConsumer") != null) { 
		    	  if (c.getInitParameter("package-consumer") != null){
		    		  ApplicationConsumer applicationConsumer = null;
		    		 try {
		    			applicationConsumer = (ApplicationConsumer)Class.forName(c.getInitParameter("ApplicationConsumer")).newInstance();
					
		    		 } catch (InstantiationException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					}
		    		  String packageConsumer = c.getInitParameter("package-consumer");
		    		  applicationConsumer.scan(packageConsumer);   
		    	  }
		    	
		    }   
		  }
		
	}

}
