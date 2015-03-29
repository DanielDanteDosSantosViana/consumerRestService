package br.gov.ancine.ws.restservice.core.protocol.mensage;

public interface Build<T> {

	Response<T> build();
}
