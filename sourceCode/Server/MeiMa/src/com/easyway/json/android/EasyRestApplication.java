package com.easyway.json.android;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.Consumes;  
import javax.ws.rs.GET;  
import javax.ws.rs.POST;  
import javax.ws.rs.Path;  
import javax.ws.rs.Produces;  
import javax.ws.rs.core.Response;  
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

public class EasyRestApplication extends Application {

    HashSet<Object> singletons = new HashSet<Object>();

    public EasyRestApplication() {
        singletons.add(new CheckCode());
        
    }

    @Override
    public Set<Object> getSingletons() {
        return singletons;
    }
}