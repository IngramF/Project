package com.example;
import com.example.models.Muster;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

import com.example.models.Person;
import java.util.*;

/**
 *
 * This class launches the web application in an embedded Jetty container.
 * This is the entry point to your application. The Java command that is used for
 * launching should fire this main method.
 *
 */
public class Main {

    /**
     * @param args
     */
    public static void main(String[] args) throws Exception{
    	/*
        String webappDirLocation = "src/main/webapp/";

        // The port that we should run on can be set into an environment variable
        // Look for that variable and default to 8080 if it isn't there.
        String webPort = System.getenv("PORT");
        if (webPort == null || webPort.isEmpty()) {
            webPort = "8080";
        }

        Server server = new Server(Integer.valueOf(webPort));
        WebAppContext root = new WebAppContext();

        root.setContextPath("/");
        root.setDescriptor(webappDirLocation + "/WEB-INF/web.xml");
        root.setResourceBase(webappDirLocation);

        // Parent loader priority is a class loader setting that Jetty accepts.
        // By default Jetty will behave like most web containers in that it will
        // allow your application to replace non-server libraries that are part of the
        // container. Setting parent loader priority to true changes this behavior.
        // Read more here: http://wiki.eclipse.org/Jetty/Reference/Jetty_Classloading
        root.setParentLoaderPriority(true);

        server.setHandler(root);

        server.start();
        server.join();
        */
    	AMSCore core;
    	
    	
    	
    	
    	
    	List<Person> employeeList = new ArrayList<Person>();
    	
    	Person samplePerson = new Person("Test","Person",999,true);
    	
    	employeeList.add(samplePerson);
    	
    	employeeList.add(new Person("Jim","Doe",2,false));
    	employeeList.add(new Person("Tom","Tomlison",3,true));
    	employeeList.add(new Person("Mary","Thomas",4,true));
    	employeeList.add(new Person("Samsun", "Jackson" ,5, false));
    	employeeList.add(new Person("Reginald", "Pierce",6,false));
    	employeeList.add(new Person("Becky", "Anderson",7,false));
    	employeeList.add(new Person("Ann", "Louis",8,true));
    	employeeList.add(new Person("Micheal", "Knight",88,false));
    	employeeList.add(new Person("Johnny", "James", 14, true ));
    	//for (Person pp : employeeList)
    	//	System.out.println(pp);
    	
    	
    	core = new AMSCore(employeeList);
    	 	
    	//for(Person person : core.GetPeople()){
    	//	System.out.println(person);
    	//}
    	
    	Muster testMuster = core.GetMusterStatus();
    	
    	core.Muster(samplePerson, "Please Report Your Status.");
    	core.GetMusterStatus();

    	
    }
    
    

}
