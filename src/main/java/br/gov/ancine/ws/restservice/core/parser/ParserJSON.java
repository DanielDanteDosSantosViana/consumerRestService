package br.gov.ancine.ws.restservice.core.parser;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.MappingJsonFactory;
import org.codehaus.jackson.map.ObjectMapper;

public class ParserJSON {

	public static String toJson(Object objeto) throws IOException {
	     
		try{
	    	  
	    	  ObjectMapper mapper = new ObjectMapper();
	    	  StringWriter jsonValue = new StringWriter();
	    	  mapper.writeValue(new PrintWriter(jsonValue), objeto);
	    	  return jsonValue.toString();
	   
	      } catch (JsonGenerationException e) {

	    	  throw new JsonGenerationException("Erro Generico ao parsear entidade : " , e);
	      } catch (JsonMappingException e) {
	    
	    	  throw new JsonMappingException("Erro no Mapeamento do objeto JSON para Object da entidade   : " , e);

	      } catch (IOException e) {
 
	    	  throw new IOException("Erro na escrita do json para o objeto  : " , e);
	      }
	 }


	   public static Object fromJson(String json, Class<?> objectClass) throws JsonParseException, IOException {
	      JsonFactory f = new MappingJsonFactory();
	      JsonParser jp = f.createJsonParser(json);
	      Object obj = jp.readValueAs(objectClass);
	      return obj;
	   }
	   
	   
}
