package br.gov.ancine.ws.restservice.core.protocol;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;

import br.gov.ancine.ws.restservice.core.exception.ServiceErrorException;
import br.gov.ancine.ws.restservice.core.parser.ParserJSON;
import br.gov.ancine.ws.restservice.core.protocol.mensage.ErrorResponse;
import br.gov.ancine.ws.restservice.core.protocol.mensage.Response;

public class CommunicationProtocol<T> {
	
		
	public  Object httpGet(Class<?> tipoObjetoRetorno, String urlWebService) throws ServiceErrorException{
		
		HttpURLConnection connection = null;
			  	
		try{		  
			   URL url = new URL(urlWebService);
			   connection = (HttpURLConnection) url.openConnection();
			   connection.setRequestMethod("GET");
			   connection.setConnectTimeout(15000);
			   connection.connect();
			   int statusCode = connection.getResponseCode();
			   String responseJson = null;
			   
			   if(isStatusCodeOK(statusCode)){
				   	 responseJson = inputStreamToString(connection.getInputStream());
				   	 T objectResponse = (T)ParserJSON.fromJson(responseJson, tipoObjetoRetorno);
				   	 Response<T> responseOK = new Response<T>(null,objectResponse);
				   	 connection.disconnect();
				   	 return responseOK;
				   	
			   }else if(isStatusCode4xx(statusCode)){
				   	 responseJson = inputStreamToString(connection.getErrorStream());
				   	 ErrorResponse erroResponse = (ErrorResponse)ParserJSON.fromJson(responseJson, ErrorResponse.class);
				   	 Response<T> response4xx = new Response<T>(erroResponse,null);
				   	 connection.disconnect();
				   	 return response4xx;
				   	 
			   }else{
				   
				   throw new ServiceErrorException("Error Interno no Servidor : "+inputStreamToString(connection.getErrorStream()));
			   }
	
		}catch(IOException e){
			 connection.disconnect();
			 throw new ServiceErrorException(e.getMessage(),e);
		}finally{
			 connection.disconnect();
		}
				
		   	
			
   }



   private static boolean isStatusCodeOK(int statusCode) {
			return HttpURLConnection.HTTP_OK ==statusCode;
   }
	
	

   private static boolean isStatusCode4xx(int statusCode) {			
			String statusCodeString = Integer.toString(statusCode);
			if(statusCodeString.startsWith("4")){
				return true;
			}
			return false;
	}



	public static Object httpPost(Object objetoEnvio, Class<?> tipoObjetoRetorno, String urlWebService) {
	      Object objetoRetorno = null;
		
	      try {
	         String requestJson = ParserJSON.toJson(objetoEnvio);
			
	         URL url = new URL(urlWebService);
	         HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	         connection.setRequestMethod("POST");
	         connection.setDoOutput(true);
	         connection.setUseCaches(false);
		     connection.setConnectTimeout(15000);
	         connection.setRequestProperty("Content-Type", "application/json");
	         connection.setRequestProperty("Accept", "application/json");
	         connection.setRequestProperty("Content-Length", Integer.toString(requestJson.length()));
				
	         DataOutputStream stream = new DataOutputStream(connection.getOutputStream());
	         stream.write(requestJson.getBytes("UTF-8"));
	         stream.flush();
	         stream.close();
	         connection.connect();

	         String responseJson = inputStreamToString(connection.getInputStream());
	         connection.disconnect();
	         objetoRetorno = ParserJSON.fromJson(responseJson, tipoObjetoRetorno);

	      } catch (Exception e) {
	         e.printStackTrace();
	      }
	      return objetoRetorno;
	   }
	
	public static String inputStreamToString(InputStream is) throws IOException {
		if (is != null) {
			Writer writer = new StringWriter();

			char[] buffer = new char[1024];
			try {
				Reader reader = new BufferedReader(new InputStreamReader(is,"UTF-8"));
				int n;
				while ((n = reader.read(buffer)) != -1) {
					writer.write(buffer, 0, n);
				}
			} finally {
				is.close();
			}
			return writer.toString();
		} else {
			return "";
		}
    }
	


}
