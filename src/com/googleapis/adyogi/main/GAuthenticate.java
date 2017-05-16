package com.googleapis.adyogi.main;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.text.ParseException;
import java.util.Arrays;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.DataStoreFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.tagmanager.TagManager;
import com.google.api.services.tagmanager.TagManagerScopes;
import com.google.api.services.tagmanager.model.Account;
import com.google.api.services.tagmanager.model.Container;
import com.google.api.services.tagmanager.model.ListAccountsResponse;

public class GAuthenticate {
	/*
	//public static final java.io.File DATA_STORE_DIR = new java.io.File(System.getProperty("user.home", "tagmanager_sample"));

	public static final java.io.File DATA_STORE_DIR = new java.io.File("C:\\Users\\bhuvnesh\\Eclipse_Workspace\\Xplanck\\Core\\GoogleTagManagerDesktopApp\\src\\main\\resources");
	
	public static final String CLIENTSECRETS_LOCATION = "C:\\Users\\bhuvnesh\\Eclipse_Workspace\\Xplanck\\Core\\GoogleTagManagerDesktopApp\\src\\main\\resources\\client_secrets.json"; 
	
	public static final GoogleClientSecrets clientSecrets = loadClientSecrets();
	
	private static final Set<String> SCOPES = TagManagerScopes.all();

	private static final String REDIRECT_URI = "urn:ietf:wg:oauth:2.0:oob";
	
	private static final HttpTransport TRANSPORT = new NetHttpTransport();
	
	private static final JsonFactory JSON_FACTORY = new JacksonFactory();
	
	private static GoogleAuthorizationCodeFlow flow = null;
	
	public static TagManager getAuthenticated() throws Exception{
	
		Credential credential = authorize();
		
		TagManager manager = null;
		
		if(null != credential){
			manager = new TagManager(TRANSPORT, JSON_FACTORY, credential);
		}
		else{
			System.out.println("Credentials are null.");
		}
		
		return manager;
	}
	
	public static Credential authorize() throws Exception{
		
		FileDataStoreFactory dataStoreDirectory = new FileDataStoreFactory(DATA_STORE_DIR);
		
		flow = new GoogleAuthorizationCodeFlow.Builder(TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES).build();
		
		if(null != flow){
		
			return new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize("user");
		
		}
		else{
			
			System.out.println("FLOW is empty.");
			
			return null;
			
		}
	}
	
	public static GoogleClientSecrets loadClientSecrets(){
		
		try{
		
		InputStream inputstream = new FileInputStream(CLIENTSECRETS_LOCATION);
		
		Reader reader = new InputStreamReader(inputstream);
		
		GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, reader);
		
		System.out.println("Client Secrets object : "+clientSecrets);
		
		return clientSecrets;
		
		}catch(Exception e){
			
			System.out.println(e);
			
			System.out.println("Client Secrets Exception");
			
			return null;
		}
	}
	*/
	
	  private static final String PROJECT_ID = "stellar-display-145814";
	//  private static final String CLIENTSECRETS_LOCATION = "C:\\Users\\bhuvnesh\\Eclipse_Workspace\\Xplanck\\Core\\bigquery-samples-java\\client_secret_154911048506-ign21bljsc6jmrdvrd84tqt3a6omk5be.apps.googleusercontent.com.json";
	/*Local Windows Machine*///private static final String CLIENTSECRETS_LOCATION = "C:\\Users\\bhuvnesh\\Eclipse_Workspace\\Xplanck\\Core\\GoogleTagManagerDesktopApp\\src\\main\\resources\\client_secret_154911048506-ign21bljsc6jmrdvrd84tqt3a6omk5be.apps.googleusercontent.com.json";
	  
	/*Linux Machine*/private static final String CLIENTSECRETS_LOCATION = "/home/work1/oauth_credentials/client_secret_154911048506-ign21bljsc6jmrdvrd84tqt3a6omk5be.apps.googleusercontent.com.json";
	  
	  static GoogleClientSecrets clientSecrets = loadClientSecrets();

	  // Static variables for API scope, callback URI, and HTTP/JSON functions
	  private static final List<String> SCOPES = Arrays.asList(TagManagerScopes.TAGMANAGER_MANAGE_ACCOUNTS,TagManagerScopes.TAGMANAGER_MANAGE_USERS, TagManagerScopes.TAGMANAGER_PUBLISH, TagManagerScopes.TAGMANAGER_EDIT_CONTAINERS, TagManagerScopes.TAGMANAGER_READONLY);
	  private static final String REDIRECT_URI = "urn:ietf:wg:oauth:2.0:oob";

	  /** Global instances of HTTP transport and JSON factory objects. */
	  private static final HttpTransport TRANSPORT = new NetHttpTransport();
	  private static final JsonFactory JSON_FACTORY = new JacksonFactory();

	  private static GoogleAuthorizationCodeFlow flow = null;

	  /** Directory to store user credentials. */
	  //private static final java.io.File DATA_STORE_DIR = new java.io.File(System.getProperty("user.home"), ".store/bq_sample");

	  private static final java.io.File DATA_STORE_DIR = new java.io.File(System.getProperty("user.home"), "store_data/bq_sample");
	  
	  /**
	   * Global instance of the {@link DataStoreFactory}. The best practice is to make it a single
	   * globally shared instance across your application.
	  */
	  private static FileDataStoreFactory dataStoreFactory;

	  /**
	   * @param args
	   * @throws IOException
	   * @throws InterruptedException
	 * @throws JSONException 
	 * @throws ParseException 
	   */
	  public static TagManager getAuthenticated(PrintWriter out) throws Exception {
	    // Create a new BigQuery client authorized via OAuth 2.0 protocol
	    // dataStoreFactory = new FileDataStoreFactory(DATA_STORE_DIR);
		  TagManager manager = createAuthorizedClient(out);
		
		 // out.println("Manager is : "+manager);
		  
		  return manager;
		  
	  }
	  
	  /** Authorizes the installed application to access user's protected data. */
	  private static Credential authorize(PrintWriter out) throws IOException {
	    
		//out.println(System.getProperty("user.home"));
		  
		dataStoreFactory = new FileDataStoreFactory(DATA_STORE_DIR);
	    // set up authorization code flow
	    GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
	        TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES).setDataStoreFactory(
	        dataStoreFactory).build();
	    // authorize
	    //out.println(flow);
	    
	    return new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize("user");
	    
	  }
	  // [END credentials]

	  public static TagManager createAuthorizedClient(PrintWriter out) throws IOException {

	    Credential credential = authorize(out);
	    
	    //out.println(credential.getAccessToken());
	    //out.println(credential.getRefreshToken());
	    //out.println(credential.toString());
	    //out.println(credential.getExpiresInSeconds());
	    
	    return new TagManager(TRANSPORT, JSON_FACTORY, credential);
	  
	  }

	  private static GoogleClientSecrets loadClientSecrets() {
	    try {
	      InputStream inputStream = new FileInputStream(CLIENTSECRETS_LOCATION);
	      Reader reader =
	          new InputStreamReader(inputStream);
	        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(new JacksonFactory(),
	                reader);
	        return clientSecrets;
	    } catch (Exception e) {
	      //System.out.println("Could not load client secrets file " + CLIENTSECRETS_LOCATION);
	      //e.printStackTrace();
	    }
	    return null;
	  }
	  
	  public static JSONObject createGTM(String hostname, String accountName, PrintWriter out){
		  
		  JSONObject responseData = new JSONObject();
		  
		  try{
			  TagManager manager = getAuthenticated(out);
			  
			  //out.println("Manager in return is : "+manager);
			  
			  ListAccountsResponse accounts = manager.accounts().list().execute();
	    	
			  //out.println("Size of Accounts returned is : "+accounts.getAccount().size());
			  
			  String accountPath = null;
			  
	    	  for (Account account : accounts.getAccount()) {
	    	    //out.println("Account Id = " + account.getAccountId());
	    	    //out.println("Account Name = " + account.getName());
	    	    //out.println("Account Share Data = " + account.getShareData());
	    	    //out.println("Account Fingerprint = " + account.getFingerprint());
	    	    
	    	    if(account.getName().equalsIgnoreCase(accountName)){
	    	    	accountPath = account.getPath();
	    	    }
	    	    
	    	  }
	    	  
	    	  //out.println("Account Path after For : "+accountPath);
	    	  
	    	  if(null == accountPath){
	    		  accountPath = "accounts/1346491955";
	    	  }
	    	  
	    	  //out.println("Account Path after If Condition : "+accountPath);
	    	  
	    	  Container container = new Container();
	      	  container.setName(hostname);
	          container.setUsageContext(Arrays.asList("web"));
	      	  container.setPath(accountPath);
	      	
	      	  Container containerResponse = null;
	      	  
	      	  containerResponse = manager.accounts().containers().create(accountPath, container).execute();

	      	  if(null != containerResponse){
	      	    //  out.println(containerResponse.getPublicId());
	      	    //  out.println(containerResponse.getPath());
	      	      
	      	      responseData.put("success", true);
		      	  responseData.put("gtm_id", containerResponse.getPublicId());
		      	  responseData.put("message", "Created Successfully.");
		      	  responseData.put("gtm_path", containerResponse.getPath());
	      	  
	      	  }else{
	      		
	      		  responseData.put("success", false);
				  responseData.put("gtm_id", "NA");
				  responseData.put("message", "Some Internal Error");
				  responseData.put("gtm_path", "NA");
	      		  
	      	  }
	      	  return responseData;
	      	  
		  }catch(Exception e){
			  
			  //out.println(e);
			  
			  responseData.put("success", false);
			  responseData.put("gtm_id", "NA");
			  responseData.put("message", e.toString());
			  responseData.put("gtm_path", "NA");
			  
			  return responseData;
		  }
		  
	  }

}