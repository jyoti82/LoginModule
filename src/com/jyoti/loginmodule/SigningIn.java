package com.jyoti.loginmodule;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLEncoder;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.json.JSONArray;
import org.json.JSONObject;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class SigningIn  extends AsyncTask<String,Void,String>{
	
	private static final String logTag = "SigningIn";
   private TextView statusField,roleField;
   private Context context;
   //private int byGetOrPost = 0; 
   //flag 0 means get and 1 means post.(By default it is get.)
   public SigningIn(Context context) {
      this.context = context;
      
   }

   protected void onPreExecute(){

   }
   @SuppressWarnings("resource")
@Override
   protected String doInBackground(String... arg0) {
      
         try{
            String username = (String)arg0[0];
            String password = (String)arg0[1];
            String url="http://10.0.2.2/login/public/login.php";
            String user  = URLEncoder.encode("username", "UTF-8") 
            + "=" + URLEncoder.encode(username, "UTF-8");
            String pass = URLEncoder.encode("password", "UTF-8") 
            + "=" + URLEncoder.encode(password, "UTF-8");
            InputStream is;
            HttpResponse re;
            JSONObject json = new JSONObject();
            
            	json.put("user",user);
    			json.put("password",pass);
    			
            
			re = HTTPPoster.doPost(url, json);
				HttpEntity entity = re.getEntity();
	    	    is = entity.getContent();
	    	  //convert response to string
	        	String result;
				
	        	    BufferedReader reader = new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
	        	    StringBuilder sb = new StringBuilder();
	        	    String line = null;
	        	    while ((line = reader.readLine()) != null) 
	        	    {
	        	        sb.append(line + "\n");
	        	    }
	        	    is.close();
	        	    result=sb.toString();
	        	    Log.i("result", result);
	        	
	        	    JSONArray jArray = new JSONArray(result);
	        	    
	        	    for(int i=0;i<jArray.length();i++)
	        	    {
	        	    	
	        	        JSONObject json_data = jArray.getJSONObject(i);
	        	        String text = json_data.getString("post");
	        	        
	        	        
	        	         if(text.equalsIgnoreCase("1"))
	        	        {
	        	        	 Log.i("logged in", result);
	        	        	 return text;
	        	        	 
	        	        }
	        	         else
	        	         {
	        	        	// pr.setVisibility(View.GONE);
	        	        	 Toast.makeText(context, "invalid", Toast.LENGTH_LONG).show();
	        	         }
	        	    }
         }
         catch (ClientProtocolException e) {
 			
 		} 
 		catch (IOException e) {
 			
 		}
         catch(Exception e){
        	 return new String("Exception: " + e.getMessage());
         }
         finally{
        	 
         }
      
	return null;
	        
         
      
   }
   @Override
   protected void onPostExecute(String result){
     if(result.endsWith("1"))
     {/*
		LogService.d(logTag, "login succes");
		this.
    	 Intent intent=new Intent(Context, Next.class);
		 startActivity(intent);
		 finish();
     */}
   }
}