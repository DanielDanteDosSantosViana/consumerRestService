package br.com.ws.restservice.core.protocol.mensage;

import br.com.ws.restservice.core.exception.ParserJsonException;

public interface Build<T> {

	Response<T> build(String json)throws ParserJsonException;
}
