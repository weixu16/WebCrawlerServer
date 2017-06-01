package com.demo;

import java.io.BufferedReader;
import java.io.FileReader;
import javax.ws.rs.Consumes;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.demo.model.Request;
import com.demo.model.Response;

import webcrawler.Controller;

@Path("mapping") //http://localhost:8080/exercise-services/webapi/mapping
public class MappingResource {
	
	@POST
	@Path("document")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createActivityParams(Request activity) {
		try
		{
			Controller.startCrawler(activity.getUrl());			
			Response response = new Response();
			String[] urls = new String[4];
			try (BufferedReader br = new BufferedReader(new FileReader("/Users/wei/urls.txt"))) {
			    String line;
			    int i=0;
			    while ((line = br.readLine()) != null) {
			       urls[i++]=line;
			    }
			}
			response.setUrls(urls);
			return response;
		}
		catch(Exception e)
		{
			return null;
		}
	}
}
