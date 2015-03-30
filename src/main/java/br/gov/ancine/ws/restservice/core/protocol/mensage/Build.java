package br.gov.ancine.ws.restservice.core.protocol.mensage;

import br.gov.ancine.ws.restservice.core.exception.ParserJsonException;

public interface Build<T> {

	Response<T> build(String json)throws ParserJsonException;
}
