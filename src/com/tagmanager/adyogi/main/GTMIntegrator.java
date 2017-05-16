package com.tagmanager.adyogi.main;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.googleapis.adyogi.main.GAuthenticate;

public class GTMIntegrator extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String testString = request.getQueryString();
		
		response.getWriter().print("Version Final : "+testString);
		
		response.setStatus(HttpServletResponse.SC_CREATED);
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		StringBuilder buffer = new StringBuilder();
		BufferedReader reader = request.getReader();
		String line;
	    while ((line = reader.readLine()) != null) {
	        buffer.append(line);
	    }  
	    String query = buffer.toString();
	    JSONObject jsonData = new JSONObject(query);
	    
	    if(jsonData.has("hostname")){
	    	
	    	String hostname = jsonData.getString("hostname");
	    	
	    	String accountName;
	    	
	    	if(jsonData.has("accountName")){
	    		accountName = jsonData.getString("accountName");
	    	}
	    	else{
	    		accountName = "AdYogi";
	    	}
	    	
	    	JSONObject responseJSON = GAuthenticate.createGTM(hostname, accountName, response.getWriter());
	    
	    	response.getWriter().print(responseJSON.toString());
	    	
	    	if(responseJSON.getBoolean("success") == true){
	    		response.setStatus(HttpServletResponse.SC_CREATED);
	    	}else if(responseJSON.getBoolean("success") == false){
	    		response.sendError(402, "Please refer the Message.");
	    	}else{
	    		response.sendError(403, "Something went wrong.");
	    	}
	    	
	    }
	    else{
	    	response.getWriter().println(new JSONObject().put("success", false).put("gtm_id", "NA").put("message", "Only Hostname (hostname) as a Parameter is accepted.").put("gtm_path", "NA").toString());
	    	response.sendError(401, "Only Hostname (hostname) as a Parameter is accepted.");
	    }
	    
	}

}