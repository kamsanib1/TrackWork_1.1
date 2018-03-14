package com.demo.trackwork;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.demo.trackwork.entities.Users;
import com.demo.trackwork.services.IUserService;

@SpringBootApplication
@EnableAutoConfiguration
public class App 
{
    public static void main( String[] args )
    {
    	
        SpringApplication.run(App.class,args);

        System.out.println( "--------------------Track Work Running------------------------" );
        //Users user = new Users();
        
        //System.out.println("pusing user data...");
    }
}
