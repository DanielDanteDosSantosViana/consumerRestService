/*===========================================================================
COPYRIGHT 2015 Daniel Viana ALL RIGHTS RESERVED.
This software cannot be copied, stored, distributed without
Daniel Viana prior authorization.
This file was made available on https://github.com/DanielDanteDosSantosViana and it
is free to be redistributed or used under Creative Commons license 2.5 br:
http://creativecommons.org/licenses/by-sa/2.5/br/
============================================================================*/
package br.com.ws.restservice.core.protocol.mensage;


public class ResponseProtocol {

	private int statusCode;
	private String jsonMensage;
	
	public ResponseProtocol(int statusCode , String jsonMensage){
		this.statusCode = statusCode;
		this.jsonMensage = jsonMensage;

	}

	public ResponseProtocol(){
		
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getJsonMensage() {
		return jsonMensage;
	}

	public void setJsonMensage(String jsonMensage) {
		this.jsonMensage = jsonMensage;
	}




}
