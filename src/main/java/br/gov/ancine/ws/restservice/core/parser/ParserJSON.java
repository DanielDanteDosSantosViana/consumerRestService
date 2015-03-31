package br.gov.ancine.ws.restservice.core.parser;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Collection;
import java.util.List;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.MappingJsonFactory;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.gov.ancine.ws.restservice.core.exception.ParserJsonException;

public class ParserJSON {
	static final Logger logger = LoggerFactory.getLogger(ParserJSON.class);

	public static String toJson(Object objeto) throws ParserJsonException {

		try{

			ObjectMapper mapper = new ObjectMapper();
			StringWriter jsonValue = new StringWriter();
			mapper.writeValue(new PrintWriter(jsonValue), objeto);
			return jsonValue.toString();

		} catch (JsonGenerationException e) {
			logger.warn("Erro Generico ao parsear entidade : " , e);
			throw new ParserJsonException(e);
		} catch (JsonMappingException e) {
			logger.warn("Erro no Mapeamento do objeto JSON para Object da entidade   : " , e);
			throw new ParserJsonException(e);

		} catch (IOException e) {
			logger.warn("Erro na escrita do json para o objeto  : " , e);
			throw new ParserJsonException(e);
		}
	}


	public static Object fromJson(String json, Class<?> objectClass) throws ParserJsonException{
		JsonFactory f = new MappingJsonFactory();
		JsonParser jp = null;
		Object obj = null;

		try {
			jp = f.createJsonParser(json);

			obj = jp.readValueAs(objectClass);

		} catch (JsonProcessingException e) {
			logger.warn("Erro ao tentar parsear a entidade  : " , e);
			throw new ParserJsonException(e);
		} catch (IOException e) {
			logger.warn("Erro no Mapeamento do objeto JSON para Object da entidade   : " , e);
			throw new ParserJsonException(e);
		}
		return obj;
	}


	public Collection<Object> collectionfromJson(String json, Class<?> objectClass , Class<? extends Collection> collection) throws ParserJsonException{

		try {

			ObjectMapper objecMapper = new ObjectMapper();
			Collection<Object> objCollection = objecMapper.readValue(json,
					objecMapper.getTypeFactory().constructCollectionType(collection, objectClass)); 

			return objCollection;

		} catch (JsonProcessingException e) {
			logger.warn("Erro ao tentar parsear a entidade  : " , e);
			throw new ParserJsonException(e);
		} catch (IOException e) {
			logger.warn("Erro no Mapeamento do objeto JSON para Object da entidade   : " , e);
			throw new ParserJsonException(e);
		}
	}

}
