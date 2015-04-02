/*===========================================================================
COPYRIGHT 2015 Daniel Viana ALL RIGHTS RESERVED.
This software cannot be copied, stored, distributed without
Daniel Viana prior authorization.
This file was made available on https://github.com/DanielDanteDosSantosViana and it
is free to be redistributed or used under Creative Commons license 2.5 br:
http://creativecommons.org/licenses/by-sa/2.5/br/
============================================================================*/
package br.com.ws.restservice.core.protocol;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.ws.restservice.core.exception.ComunicationProtocolException;
import br.com.ws.restservice.core.exception.ParserJsonException;
import br.com.ws.restservice.core.parser.ParserJSON;
import br.com.ws.restservice.core.protocol.mensage.ResponseProtocol;
import br.com.ws.restservice.core.searchParameter.Parameter;
import br.com.ws.restservice.core.searchParameter.SearchParameterUri;

public class CommunicationProtocol {
	
	static final Logger logger = LoggerFactory.getLogger(SearchParameterUri.class);

	public ResponseProtocol httpGet(String urlWebService) throws ComunicationProtocolException{
		
			HttpURLConnection connection = null;
			URL url;
			try {
				url = new URL(urlWebService);

			   connection = (HttpURLConnection) url.openConnection();
			   connection.setRequestMethod("GET");
			   connection.setConnectTimeout(15000);
			   connection.connect();
			   int statusCode = connection.getResponseCode();
			   String responseJson = null;
			   
			   if(isStatusCodeOK(statusCode)){
				   responseJson = inputStreamToString(connection.getInputStream());
				   connection.disconnect();
				   return new ResponseProtocol(statusCode, responseJson);
				   	
			   }else if(isStatusCode4xx(statusCode)){
				   	 responseJson = inputStreamToString(connection.getErrorStream());
				   	 connection.disconnect();
				   	 return new ResponseProtocol(statusCode, responseJson);
				   	 
			   }else{
				   logger.warn("Error Interno no Servidor status code "+statusCode);
				   
				   throw new ComunicationProtocolException("Error Interno no Servidor status code : "+statusCode
						   +", Causado por : "+inputStreamToString(connection.getErrorStream()));
			   }
			
			} catch (MalformedURLException e) {
				   logger.warn("Error ao tentar montar URL : ",e);
				   throw new ComunicationProtocolException(e);

			} catch (ProtocolException e) {
				   throw new ComunicationProtocolException(e);

			} catch (IOException e) {
				   logger.warn("Não foi possível recuperar a resposta do servidor ",e);
				   throw new ComunicationProtocolException(e);

			}		
   }
	
	
	public ResponseProtocol httpPost(Parameter parameter, Class<?> tipoObjetoRetorno, String urlWebService) throws ComunicationProtocolException, ParserJsonException {
			
			 String requestJson =null;
			 
			 try
			 {
			 
				 requestJson = getParameterToJSON(parameter);
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
				 int statusCode = connection.getResponseCode();
				 String responseJson = null;

			   if(isStatusCodeOK(statusCode)){
				   responseJson = inputStreamToString(connection.getInputStream());
				   connection.disconnect();
				   return new ResponseProtocol(statusCode, responseJson);
				   	
			   }else if(isStatusCode4xx(statusCode)){
				   	 responseJson = inputStreamToString(connection.getErrorStream());
				   	 connection.disconnect();
				   	 return new ResponseProtocol(statusCode, responseJson);
				   	 
			   }else{
				   logger.warn("Error Interno no Servidor status code "+statusCode);
				   
				   throw new ComunicationProtocolException("Error Interno no Servidor status code : "+statusCode
						   +", Causado por : "+inputStreamToString(connection.getErrorStream()));
			   }
			
			} catch (MalformedURLException e) {
				   logger.warn("Error ao tentar montar URL : ",e);
				   throw new ComunicationProtocolException(e);

			} catch (ProtocolException e) {
				   throw new ComunicationProtocolException(e);

			} catch (IOException e) {
				   logger.warn("Não foi possível recuperar a resposta do servidor ",e);
				   throw new ComunicationProtocolException(e);

			}	
	     
   }

	private String getParameterToJSON(Parameter parameter) throws ParserJsonException {
	    
	    String requestJson = null;
		Collection<Object> listaParamValues = parameter.getParameter().values();
			
		 for (Object object : listaParamValues) {
			 requestJson = ParserJSON.toJson(object);
			 requestJson += ",";
		 }
		 if(requestJson.endsWith(",")){
			 int index = requestJson.lastIndexOf(',');
			 char[] temp = requestJson.toCharArray();
			 temp[index] = ' ';
			 requestJson = new String(temp);
		 }
		 
		 return requestJson;
		
	}


	public ResponseProtocol httpPost( String urlWebService) throws ComunicationProtocolException, ParserJsonException {
		
	      try {
	       
	         URL url = new URL(urlWebService);
	         HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	         connection.setRequestMethod("POST");
	         connection.setDoOutput(true);
	         connection.setUseCaches(false);
		     connection.setConnectTimeout(15000);
	         connection.setRequestProperty("Content-Type", "application/json");
	         connection.setRequestProperty("Accept", "application/json");
	         connection.setRequestProperty("Content-Length", Integer.toString(0));
	         int statusCode = connection.getResponseCode();
			 String responseJson = null;

			   if(isStatusCodeOK(statusCode)){
				   responseJson = inputStreamToString(connection.getInputStream());
				   connection.disconnect();
				   return new ResponseProtocol(statusCode, responseJson);
				   	
			   }else if(isStatusCode4xx(statusCode)){
				   	 responseJson = inputStreamToString(connection.getErrorStream());
				   	 connection.disconnect();
				   	 return new ResponseProtocol(statusCode, responseJson);
				   	 
			   }else{
				   logger.warn("Error Interno no Servidor status code "+statusCode);
				   
				   throw new ComunicationProtocolException("Error Interno no Servidor status code : "+statusCode
						   +", Causado por : "+inputStreamToString(connection.getErrorStream()));
			   }
			
			} catch (MalformedURLException e) {
				   logger.warn("Error ao tentar montar URL : ",e);
				   throw new ComunicationProtocolException(e);

			} catch (ProtocolException e) {
				   throw new ComunicationProtocolException(e);

			} catch (IOException e) {
				   logger.warn("Não foi possível recuperar a resposta do servidor ",e);
				   throw new ComunicationProtocolException(e);

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
